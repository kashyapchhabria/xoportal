package com.xo.web.mgr;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import play.mvc.Http.Context;

import com.xo.web.models.dao.GenericDAO;
import com.xo.web.models.system.IEntity;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.KeyValueDTO;
import com.xo.web.viewdtos.PaginatedRecords;


/**
 * @author sekar
 *
 */
@SuppressWarnings("unchecked")
public class BaseLogic<T extends IEntity, ID extends Serializable> {

	protected GenericDAO<T, ID> entityDao;
	public BaseLogic(GenericDAO<T, ID> entityDao) {
		this.entityDao = entityDao;
	}

	/**
     * <p>
     * Get the entity with the specified type and surveyId from the datastore.
     *
     * <p>
     * If none is found, return null.
     */
    public T find(ID id){
    	return this.entityDao.find(id);
    }

    /**
     * Get all entities of the specified type from the datastore that have one
     * of these ids.
     */
    public T[] find(ID... ids) {
    	return this.entityDao.find(ids);
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
    public T update(T entity) {
    	return this.entityDao.merge(entity);
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
    public T[] update(T... entities) {
    	return this.entityDao.merge(entities);
    }
   
    /**
     * If an entity with the same ID already exists in the database, merge the
     * changes into that entity. If not persist the given entity. In either
     * case, a managed entity with the changed values is returned. It may or may
     * not be the same object as was passed in.
     */
    public T save(T entity) {
    	return this.entityDao.save(entity);
    }

    /**
     * <p>
     * For each entity: If an entity with the same ID already exists in the
     * database, merge the changes into that entity. If not persist the given
     * entity. In either case, a managed entity with the changed values is
     * returned. It may or may not be the same object as was passed in.
     *
     * @return an array containing each managed entity corresponding to the
     *         entities passed in.
     */
    public T[] save(T... entities) {
    	return this.entityDao.save(entities);
    }

    /**
     * Remove the specified entity from the datastore.
     *
     * @return <code>true</code> if the entity is found in the datastore and
     *         removed, <code>false</code> if it is not found.
     */
    public boolean remove(T entity) {
    	return this.entityDao.remove(entity);
    }

    /**
     * Remove all of the specified entities from the datastore.
     */
    public void remove(T... entities) {
    	this.entityDao.remove(entities);
    }

    /**
     * Remove the entity with the specified type and surveyId from the datastore.
     *
     * @return <code>true</code> if the entity is found in the datastore and
     *         removed, <code>false</code> if it is not found.
     */
    public boolean removeById(ID id) {
    	return this.entityDao.removeById(id);
    }

    /**
     * Remove all the entities of the given type from the datastore that have
     * one of these ids.
     */
    public void removeByIds(ID... ids) {
    	this.entityDao.removeByIds(ids);
    }

    public void softRemove(ID id) {
    	T entity = this.find(id);
		if(entity != null) {
			entity.setActive(false);
			entity.setDeleted(true);
			entity.setLastModifiedDate(new Date());
			this.update(entity);
		}
    }

    /**
     * Get a list of all the objects of the specified type.
     */
    public Collection<T> findAll() {
    	return this.entityDao.findAll();
    }

    /**
     * Get a list of all the objects of the specified type.
     */
    public Collection<T> findAll(Integer fromIndex, Integer rowLimit) {
    	return this.entityDao.findAll(fromIndex, rowLimit);
    }

    public PaginatedRecords readPaginatedEntities(Integer fromIndex, Integer rowLimit) {
    	PaginatedRecords paginatedRecords = new PaginatedRecords(
    			fromIndex, rowLimit, count(), findAll(fromIndex, rowLimit));
    	return paginatedRecords;
    }
    
    /**
     * Returns the total number of results that would be returned.
     */
    public Long count() {
    	return this.entityDao.count();
    }

    public void toggleActiveStatus(ID id) {
    	if(id != null) {
    		T entity = this.entityDao.find(id);
    		if(entity != null) {
    			entity.setActive(!entity.isActive());
    			this.entityDao.merge(entity);
    		}
    	}
    }

    public Set<KeyValueDTO> readAllAsKeyValues() {
		return this.convertKeyValueDtos(this.findAll());
    }

    protected Set<KeyValueDTO> convertKeyValueDtos(Collection<T> entities) {
		Set<KeyValueDTO> keyValueDtos = new HashSet<KeyValueDTO>();
		if(XoUtil.hasData(entities)) {
			for(T entity : entities) {
				if(!entity.isSystemResource()) {
					keyValueDtos.add(entity.asKeyValue());
				}
			}
		}
		return keyValueDtos;
	}

    public final String getCurrentAuthToken() {
		String authToken = Context.current().request().getHeader(XoUtil.HEADER_AUTH_TOKEN);
		if(!XoUtil.isNotNull(authToken)) {	// User logged in first time so header entries won't be available.
			authToken = Context.current().session().get(XoUtil.HEADER_AUTH_TOKEN);
		}
		if(!XoUtil.isNotNull(authToken)) {	// Get it from query params.
			authToken = Context.current().request().getQueryString(XoUtil.AUTH_TOKEN_QUERY_PARAM);
		}
		return authToken;
	}
}
