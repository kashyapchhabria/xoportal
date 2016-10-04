package com.xo.web.models.dao;

import com.xo.web.persistence.DAOUtil;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Implementation of <code>GenericDAO</code> using Hibernate.
 * The SessionFactory property is annotated for automatic resource injection.
 *
 * @param <T>
 *            The type of the domain object for which this instance is to be
 *            used.
 * @param <ID>
 *            The type of the id of the domain object for which this instance is
 *            to be used.
 */
@SuppressWarnings("unchecked")
public class GenericDAOImpl<T, ID extends Serializable> extends
                JPABaseDAO implements GenericDAO<T, ID> {

        public Class<T> persistentClass = (Class<T>) DAOUtil.getTypeArguments(GenericDAOImpl.class, this.getClass()).get(0);

        public Long count() {
                return _count(persistentClass);
        }

        public Long count(String queryString) {
            return _count(queryString);
        }

        public T find(ID id) {
                return _find(persistentClass, id);
        }

        public T[] find(ID... ids) {
                return _find(persistentClass, ids);
        }

        public List<T> findAll() {
                return _all(persistentClass);
        }

        public Map<ID, T> findAllAsMap() {
            return (Map<ID, T>) _allAsMap(persistentClass);
        }

        public List<T> findAll(Integer fromIndex, Integer rowLimit) {
            return _all(persistentClass, fromIndex, rowLimit);
        }

        public Collection<?> paginatedQuery(String queryString, Integer fromIndex, Integer rowLimit) {
            return _all(queryString, fromIndex, rowLimit);
        }

		public void flush() {
                _flush();
        }

        public T getReference(ID id) {
                return _getReference(persistentClass, id);
        }

        public T[] getReferences(ID... ids) {
                return _getReferences(persistentClass, ids);
        }

        public boolean isAttached(T entity) {
                return _contains(entity);
        }

        public void refresh(T... entities) {
                _refresh(entities);
        }

        public boolean remove(T entity) {
                return removeByEntity(entity);
        }

        public void remove(T... entities) {
                removeEntityByObjects((Object[]) entities);
        }

        public boolean removeById(ID id) {
                return removeById(persistentClass, id);
        }

        public void removeByIds(ID... ids) {
                removeByIds(persistentClass, ids);
        }

        public T merge(T entity) {
                return mergeByEntity(entity);
        }

        public T[] merge(T... entities) {
                return mergeByEntities(persistentClass, entities);
        }

        public void persist(T... entities) {
                persistEntity(entities);
        }
       
        public T save(T entity) {
                return persistOrMerge(entity);
        }

        public T[] save(T... entities) {
                return persistOrMerge(persistentClass, entities);
        }

        public Query getNamedQuery(String queryName) {
        	return _getNamedQuery(persistentClass, queryName);
        }
}

