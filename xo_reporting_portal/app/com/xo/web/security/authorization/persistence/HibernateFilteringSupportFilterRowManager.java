package com.xo.web.security.authorization.persistence;

import com.xo.web.models.system.ResourceFilter;
import com.xo.web.models.system.ResourceFilterParameter;
import com.xo.web.persistence.JPAUtil;
import org.hibernate.Filter;
import org.hibernate.Session;

import java.util.Collection;
import java.util.Set;

@SuppressWarnings("rawtypes")
public class HibernateFilteringSupportFilterRowManager implements FilterRowManager {

	public void applyFilter(ResourceFilter filter) {

		/* Get the session associated with the current thread.
		 * and enable the filter
		 */
		Session session = (Session)JPAUtil.em().getDelegate();
		Filter hibernateFilter = session.enableFilter(filter.getFullyQualifiedName());

		/* Get the nameValue pairs from our Filter and add them to the Hibernate filters. */
		Set<ResourceFilterParameter> resourceFilterParameters = filter.getResourceFilterParameters();

		for (ResourceFilterParameter resourceFilterParameter : resourceFilterParameters) {
			Object values = resourceFilterParameter.getParameterValues();
			if(values instanceof Collection) {
				hibernateFilter.setParameterList(resourceFilterParameter.getName(), (Collection) values);
			} else {				
				hibernateFilter.setParameter(resourceFilterParameter.getName(), values);
			}
		}
	}

	@Override
	public void disableFilter(ResourceFilter filter) {

		/* Get the session associated with the current thread.
		 * and disable the filter
		 */
		Session session = (Session)JPAUtil.em().getDelegate();
		session.disableFilter(filter.getFullyQualifiedName());

	}

}
