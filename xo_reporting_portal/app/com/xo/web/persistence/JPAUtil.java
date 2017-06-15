package com.xo.web.persistence;

import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;
import play.Application;
import play.Logger;
import play.Play;

import javax.persistence.EntityManager;

public final class JPAUtil {

    private static final ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<EntityManager>();
    private static XODataSourcePlugin jpaPlugin = null;
    public static final String END_USER = XoUtil.getConfig(XoAppConfigKeys.XO_END_USER);
    public static final String END_USER_FAILOVER = END_USER + "_Failover";

    /**
     * Get the EntityManager for specified persistence unit for this thread.
     */
    public static final EntityManager em(String key) {
        Application app = Play.application();
        if (app == null) {
            throw new RuntimeException("No application running");
        }

        if (jpaPlugin == null) {
        	jpaPlugin = (XODataSourcePlugin) XoUtil.getPluginByClass(XODataSourcePlugin.class);
        }
        if (jpaPlugin == null) {
            throw new RuntimeException("No JPA EntityManagerFactory configured for name [" + key + "]");
        }

       	//key = getDBKey(jpaPlugin, key);

        EntityManager entityManager = jpaPlugin.em(key);
        if (entityManager == null) {
        	if (entityManager == null) {        		
        		throw new RuntimeException("No JPA EntityManagerFactory configured for name [" + key + "]");
        	}
        }

        currentEntityManager.set(entityManager);

        return entityManager;
    }

    /**
     * Get the default EntityManager for this thread.
     */
    public static final EntityManager em() {
        EntityManager em = currentEntityManager.get();
        if (jpaPlugin == null || em == null) {
            return em(END_USER);
        }
        return em;
    }

    public static final void closeEM() {
    	Logger.debug("Closing entity manager...");
        EntityManager em = currentEntityManager.get();
        if (em != null && em.isOpen()) {
            em.close();
        }
        Logger.debug("Entity manager closed successfully.");
        currentEntityManager.set(null);
    }

    public static final void beginTransaction() {
        em().getTransaction().begin();
    }

    public static final void commitTransaction() {
        em().getTransaction().commit();
    }

}
