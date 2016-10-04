package com.xo.web.models.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Common domain object access interface. 
 * A single instance implementing this interface can be used only for the type of domain object 
 * specified in the type parameters.
 *
 * @author Maria Sekar
 *
 * @param <T>
 *            The type of the domain object for which this instance is to be
 *            used.
 * @param <ID>
 *            The type of the id of the domain object for which this instance is
 *            to be used.
 */
@SuppressWarnings("unchecked")
public interface GenericDAO<T, ID extends Serializable> {

        /**
         * <p>
         * Get the entity with the specified type and id from the datastore.
         *
         * <p>
         * If none is found, return null.
         */
        T find(ID id);

        /**
         * Get all entities of the specified type from the datastore that have one
         * of these ids.
         */
        T[] find(ID... ids);

        /**
         * <p>
         * Get a reference to the entity with the specified type and id from the
         * datastore.
         *
         * <p>
         * This does not require a call to the datastore and does not populate any
         * of the entity's values. Values may be fetched lazily at a later time.
         * This increases performance if a another entity is being saved that should
         * reference this entity but the values of this entity are not needed.
         *
         * @throws IllegalArgumentException if the first argument does
     *         not denote an entity type or the second argument is
     *         not a valid type for that entitys primary key or
     *         is null
     * @throws javax.persistence.EntityNotFoundException if the entity state
     *         cannot be accessed
         */
        T getReference(ID id);

        /**
         * <p>
         * Get a reference to the entities of the specified type with the given ids
         * from the datastore.
         *
         * <p>
         * This does not require a call to the datastore and does not populate any
         * of the entities' values. Values may be fetched lazily at a later time.
         * This increases performance if a another entity is being saved that should
         * reference these entities but the values of these entities are not needed.
         *
         * @throws IllegalArgumentException if the first argument does
     *         not denote an entity type or the second argument is
     *         not a valid type for that entitys primary key or
     *         is null
     * @throws javax.persistence.EntityNotFoundException if the entity state
     *         cannot be accessed
         */
        T[] getReferences(ID... ids);

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
        void persist(T... entities);

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
        T merge(T entity);

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
        T[] merge(T... entities);
       
        /**
         * If an entity with the same ID already exists in the database, merge the
         * changes into that entity. If not persist the given entity. In either
         * case, a managed entity with the changed values is returned. It may or may
         * not be the same object as was passed in.
         */
        T save(T entity);

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
        T[] save(T... entities);

        /**
         * Remove the specified entity from the datastore.
         *
         * @return <code>true</code> if the entity is found in the datastore and
         *         removed, <code>false</code> if it is not found.
         */
        boolean remove(T entity);

        /**
         * Remove all of the specified entities from the datastore.
         */
        void remove(T... entities);

        /**
         * Remove the entity with the specified type and id from the datastore.
         *
         * @return <code>true</code> if the entity is found in the datastore and
         *         removed, <code>false</code> if it is not found.
         */
        boolean removeById(ID id);

        /**
         * Remove all the entities of the given type from the datastore that have
         * one of these ids.
         */
        void removeByIds(ID... ids);

        /**
         * Get a list of all the objects of the specified type.
         */
        Collection<T> findAll();

        /**
         * Get all the entities as a map
         * @return
         */
        Map<ID, T> findAllAsMap();

        /**
         * Returns the total number of results that would be returned.
         */
        Long count();

        /**
         * Returns the total number of results that would be returned for the given query.
         * @param queryString
         * @return
         */
        Long count(String queryString);

        /**
         * Returns <code>true</code> if the object is connected to the current
         * Hibernate session.
         */
        boolean isAttached(T entity);

        /**
         * Refresh the content of the given entity from the current datastore state.
         */
        void refresh(T... entities);

        /**
         * Flushes changes in the Hibernate session to the datastore.
         */
        void flush();

        /**
         * Paginated entity response
         * @param fromIndex
         * @param rowLimit
         * @return
         */
		Collection<T> findAll(Integer fromIndex, Integer rowLimit);

		/**
         * Paginated query response
         * @param queryString to be executed against the db
         * @param fromIndex to be retrieved from the available results.
         * @param rowLimit to be set for the available results
         * @return
         */
		Collection<?> paginatedQuery(String queryString, Integer fromIndex, Integer rowLimit);
}
