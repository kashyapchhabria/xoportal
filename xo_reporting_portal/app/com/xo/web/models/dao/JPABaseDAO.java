package com.xo.web.models.dao;

import com.xo.web.core.XODAOException;
import com.xo.web.persistence.HibernateMetadataUtil;
import com.xo.web.persistence.MetadataUtil;
import com.xo.web.util.XoUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SessionImplementor;
import play.Logger;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Base class for DAOs that uses JPA EnityManagers and JPA Query Language.
 *
 * <p>
 * The <code>Metadata Util</code> and <code>EntityManager</code> must be set
 * in order for the DAO to function. Generally, a single
 * SearchProcessor will be associated with an instance of a DAO for
 * the lifetime of the instance, while a new "current" EntityManager will be
 * injected as needed. Make sure that any EntityManager that is used is
 * associated with the same persistence unit (i.e. EntityManagerFactory) as the
 * SearchProcessor.
 *
 */
@SuppressWarnings("unchecked")
public class JPABaseDAO {

	public static final HibernateMetadataUtil HIBERNATE_METADATA_UTIL = HibernateMetadataUtil.getInstance();
	private static final String SYMPOL_DOT = ".";

	private EntityManager entityManager;

	/**
	 * Set the current EntityManager
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Get the current EntityManager
	 */
	protected EntityManager em() {
		return entityManager;
	}

	protected MetadataUtil getMetadataUtil() {
		return HIBERNATE_METADATA_UTIL;
	}

	/**
	 * <p>
	 * Make a transient instance persistent and add it to the datastore. This
	 * operation cascades to associated instances if the association is mapped
	 * with cascade="persist". Throws an error if the entity already exists.
	 * 
	 * <p>
	 * Does not guarantee that the object will be assigned an identifier
	 * immediately. With <code>persist</code> a datastore-generated id may not
	 * be pulled until flush time.
	 */
	//@XODBTransaction
	protected void persistEntity(Object... entities) {
		for (Object entity : entities) {
			if (entity != null)
				((Session)this.em().getDelegate()).persist(entity);
		}
	}

	/**
	 * Remove the entity of the specified class with the specified id from the
	 * datastore.
	 * 
	 * @return <code>true</code> if the object is found in the datastore and
	 *         deleted, <code>false</code> if the item is not found.
	 * @throws XODAOException 
	 */
	//@XODBTransaction
	protected boolean removeById(Class<?> type, Serializable id) {
		if (id != null) {
			try{
				Session session = ((Session)this.em().getDelegate());
				/*StringBuilder deleteQueryStr = new StringBuilder("DELETE FROM ");
				deleteQueryStr.append(getMetadataUtil().get(type).getEntityName());
				deleteQueryStr.append(" WHERE ");
				deleteQueryStr.append(getMetadataUtil().get(type).getIdProperty());
				deleteQueryStr.append(" = :idValue ");

				Query query = session.createQuery(deleteQueryStr.toString());
				query.setParameter("idValue", id);
				query.executeUpdate();*/
				Object entityObj = this._find(type, id);
				if(entityObj != null) {					
					session.delete(entityObj);
				}
			} catch(Exception e) {
				Logger.error("Error while removing entity by id ", e);
				return false;
			}
		}
		return true;
	}

	/**
	 * Remove all the entities of the given type from the datastore that have
	 * one of these ids.
	 */
	//@XODBTransaction
	protected void removeByIds(Class<?> type, Serializable... ids) {
		Session session = ((Session)this.em().getDelegate());
		for (Serializable id : (List<Serializable>) pullByIds("select _it_.id",
				type, ids)) {
			session.delete(this.em().getReference(type, id));
		}
	}

	/**
	 * Remove the specified entity from the datastore.
	 * 
	 * @return <code>true</code> if the object is found in the datastore and
	 *         removed, <code>false</code> if the item is not found.
	 */
	//@XODBTransaction
	protected boolean removeByEntity(Object entity) {
		if (entity != null) {
			if (this.em().contains(entity)) {
				this.em().remove(entity);
				return true;
			} else {
				Serializable id = getMetadataUtil().getId(entity);
				return removeById(entity.getClass(), id);
			}
		}
		return false;
	}

	/**
	 * Remove the specified entities from the datastore.
	 */
	protected void removeEntityByObjects(Object... entities) {
		for (Object entity : entities) {
			removeByEntity(entity);
		}
	}

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or null if there is no such persistent instance.
	 */
	protected <T> T _find(Class<T> type, Serializable id) {
		return this.em().find(type, id);
	}

	/**
	 * Return the all the persistent instances of the given entity class with
	 * the given identifiers. An array of entities is returned that matches the
	 * same order of the ids listed in the call. For each entity that is not
	 * found in the datastore, a null will be inserted in its place in the
	 * return array.
	 */
	protected <T> T[] _find(Class<T> type, Serializable... ids) {
		Object[] retList = (Object[]) Array.newInstance(type, ids.length);
		for (Object entity : pullByIds("select _it_", type, ids)) {
			Serializable id = getMetadataUtil().getId(entity);

			for (int i = 0; i < ids.length; i++) {
				if (id.equals(ids[i])) {
					retList[i] = entity;
					// don't break. the same id could be in the list twice.
				}
			}
		}

		return (T[]) retList;
	}

	protected <T> T _getReference(Class<T> type, Serializable id) {
		return this.em().getReference(type, id);
	}

	protected <T> T[] _getReferences(Class<T> type, Serializable... ids) {
		T[] retList = (T[]) Array.newInstance(type, ids.length);
		for (int i = 0; i < ids.length; i++) {
			retList[i] = _getReference(type, ids[i]);
		}
		return retList;
	}

	/**
	 * Get a list of all the entities of the specified class.
	 */
	protected <T> List<T> _all(Class<T> type) {
		Session session = ((Session)this.em().getDelegate());
		return session
				.createQuery(
						"select _it_ from "
								+ getMetadataUtil().get(type).getEntityName()
								+ " _it_").list();
	}

	/**
	 * Get a list of all the entities of the specified class as map
	 */
	protected <T> Map<Serializable, T> _allAsMap(Class<T> type) {
		Map<Serializable, T> entityMap = new HashMap<Serializable, T>();
		List<T> entities = this._all(type);
		if(XoUtil.hasData(entities)) {
			for(T entity: entities) {
				entityMap.put(getMetadataUtil().getId(entity), entity);
			}
		}
		return entityMap;
	}

	/**
	 * <p>
	 * Copy the state of the given object onto the persistent object with the
	 * same identifier. If there is no persistent instance currently associated
	 * with the session, it will be loaded. Return the persistent instance. If
	 * the given instance is unsaved, save a copy and return it as a newly
	 * persistent instance.
	 * 
	 * <p>
	 * The instance that is passed in does not become associated with the
	 * session. This operation cascades to associated instances if the
	 * association is mapped with cascade="merge".
	 */
	//@XODBTransaction
	protected <T> T mergeByEntity(T entity) {
		return this.em().merge(entity);
	}

	/**
	 * <p>
	 * Copy the state of the given objects onto the persistent objects with the
	 * same identifier. If there is no persistent instance currently associated
	 * with the session, it will be loaded. Return the persistent instances. If
	 * a given instance is unsaved, save a copy and return it as a newly
	 * persistent instance.
	 * 
	 * <p>
	 * The instances that are passed in do not become associated with the
	 * session. This operation cascades to associated instances if the
	 * association is mapped with cascade="merge".
	 */
	protected <T> T[] mergeByEntities(Class<T> arrayType, T... entities) {
		T[] retList = (T[]) Array.newInstance(arrayType, entities.length);
		for (int i = 0; i < entities.length; i++) {
			retList[i] = mergeByEntity(entities[i]);
		}
		return retList;
	}

	/**
	 * If an entity with the same ID already exists in the database, merge the
	 * changes into that entity. If not persist the given entity. In either
	 * case, a managed entity with the changed values is returned. It may or may
	 * not be the same object as was passed in.
	 */
	//@XODBTransaction
	protected <T> T persistOrMerge(T entity) {
		if (entity == null)
			return null;
		if (this.em().contains(entity))
			return entity;
		Serializable id = getMetadataUtil().getId(entity);
		if (!validId(id)) {
			persistEntity(entity);
			return entity;
		}
		T prev = this.em().find((Class<T>) entity.getClass(), id);
		if (prev == null) {
			persistEntity(entity);
			return entity;
		} else {
			return mergeByEntity(entity);
		}
	}

	/**
	 * <p>
	 * For each entity: If an entity with the same ID already exists in the
	 * database, merge the changes into that entity. If not persist the given
	 * entity. In either case, a managed entity with the changed values is
	 * returned. It may or may not be the same object as was passed in.
	 * 
	 * <p>
	 * This version of the method allows the array type to be specified.
	 * 
	 * @return an array containing each managed entity corresponding to the
	 *         entities passed in.
	 */
	protected <T> T[] persistOrMerge(Class<T> arrayType, T... entities) {
		T[] retList = (T[]) Array.newInstance(arrayType, entities.length);
		for (int i = 0; i < entities.length; i++) {
			retList[i] = persistOrMerge(entities[i]);
		}
		return retList;
	}

	/**
	 * Returns the number of instances of this entity in the datastore.
	 */
	protected Long _count(Class<?> type) {
		Session session = ((Session)this.em().getDelegate());
		return ((Number) session
				.createQuery(
						"select count(_it_) from "
								+ getMetadataUtil().get(type).getEntityName()
								+ " _it_").uniqueResult()).longValue();
	}

	/**
	 * Returns the number of records available for the given query.
	 */
	protected Long _count(String queryString) {
		Long totalRecords = 0l;
		if( queryString != null && queryString.trim().length() > 0) {
			Session session = ((Session)this.em().getDelegate());
			totalRecords = ((Number) session
					.createQuery(
							"select count(*) from (" + queryString + ") __temp__").uniqueResult()).longValue();
		}
		return totalRecords;
	}

	/**
	 * Returns true if the object is connected to the current hibernate session.
	 */
	protected boolean _contains(Object o) {
		return this.em().contains(o);
	}

	/**
	 * Flushes changes in the hibernate cache to the datastore.
	 */
	protected void _flush() {
		this.em().flush();
	}

	/**
	 * Refresh the content of the given entity from the current datastore state.
	 */
	protected void _refresh(Object... entities) {
		for (Object entity : entities) {
			if (entity != null)
				this.em().refresh(entity);
		}
	}

	protected boolean _exists(Object entity) {
		if (entity == null)
			return false;
		if (em().contains(entity))
			return true;
		return _exists(entity.getClass(), getMetadataUtil().getId(entity));
	}

	protected boolean _exists(Class<?> type, Serializable id) {
		if (type == null)
			throw new NullPointerException("Type is null.");
		if (!validId(id))
			return false;

		Session session = ((Session)this.em().getDelegate());
		Query query = session.createQuery(
				"select _it_.id from "
						+ getMetadataUtil().get(type).getEntityName()
						+ " _it_ where _it_.id = :id");
		query.setParameter("id", id);
		return XoUtil.hasData(query.list());
	}

	protected boolean[] _exists(Class<?> type, Serializable... ids) {
		if (type == null)
			throw new NullPointerException("Type is null.");

		boolean[] ret = new boolean[ids.length];

		for (Serializable id : (List<Serializable>) pullByIds("select _it_.id",
				type, ids)) {
			for (int i = 0; i < ids.length; i++) {
				if (id.equals(ids[i])) {
					ret[i] = true;
					// don't break. the same id could be in the list twice.
				}
			}
		}

		return ret;
	}

	private List<?> pullByIds(String select, Class<?> type, Serializable[] ids) {
		List<Serializable> nonNulls = new LinkedList<Serializable>();

		StringBuilder sb = new StringBuilder(select);
		sb.append(" from ");
		sb.append(getMetadataUtil().get(type).getEntityName());
		sb.append(" _it_ where ");
		for (Serializable id : ids) {
			if (id != null) {
				if (nonNulls.size() == 0)
					sb.append("_it_.id = ?1");
				else
					sb.append(" or _it_.id = ?").append(nonNulls.size() + 1);
				nonNulls.add(id);
			}
		}
		if (nonNulls.size() == 0)
			return new ArrayList<Object>(0);

		Session session = ((Session)this.em().getDelegate());
		Query query = session.createQuery(sb.toString());
		int idx = 1;
		for (Serializable id : nonNulls) {
			query.setParameter(idx++, id);
		}
		return query.list();
	}

	private boolean validId(Serializable id) {
		if (id == null)
			return false;
		if (id instanceof Number && ((Number) id).equals(0))
			return false;
		if (id instanceof String && "".equals(id))
			return false;
		return true;
	}

	protected Query _getNamedQuery(Class<?> type, String queryName){
		Query query = null;
		if(XoUtil.isNotNull(queryName)) {			
			Session session = ((Session)this.em().getDelegate());
			query = session.getNamedQuery(getMetadataUtil().get(type).getEntityName() + SYMPOL_DOT + queryName);
		}
		return query;
	}

	public Connection getRawJDBCConnection() {
		Connection conn = null;
		try {
			SessionImplementor session = ((SessionImplementor)this.em().getDelegate());
			JdbcConnectionAccess cp = session.getJdbcConnectionAccess();
			conn = cp.obtainConnection();
		} catch (SQLException e) {
			Logger.error("Error while getting the raw connection.", e);
		}
		return conn;
	}

	/**
	 * Get a list of all the entities of the specified class.
	 * @param type from which the records to be retrieved
	 * @param fromIndex to be retrieved.
	 * @param rowLimit of total available records
	 * @return
	 */
	protected <T> List<T> _all(Class<T> type, Integer fromIndex, Integer rowLimit) {
		Session session = ((Session)this.em().getDelegate());
		Query query = session.createQuery("select _it_ from " + getMetadataUtil().get(type).getEntityName()	+ " _it_");
		if(fromIndex != null && fromIndex > 0) {
			query.setFirstResult(fromIndex);
		}
		if(rowLimit != null && rowLimit > 0) {
			query.setMaxResults(rowLimit);
		}
		return query.list();
	}

	/**
	 * Retrieving the paginated results.
	 * @param queryString
	 * @param fromIndex
	 * @param rowLimit
	 * @return
	 */
	protected Collection<?> _all(String queryString, Integer fromIndex, Integer rowLimit) {
		Collection<?> results = null;
		if( queryString != null && queryString.trim().length() > 0) {
			Session session = ((Session)this.em().getDelegate());
			Query query = session.createQuery(queryString);
			if(fromIndex != null && fromIndex > 0) {
				query.setFirstResult(fromIndex);
			}
			if(rowLimit != null && rowLimit > 0) {
				query.setMaxResults(rowLimit);
			}
			results = query.list();
		}
		return results;
	}
}
