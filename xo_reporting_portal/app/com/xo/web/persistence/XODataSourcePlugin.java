package com.xo.web.persistence;

import com.xo.web.util.XoUtil;
import play.Application;
import play.Configuration;
import play.Logger;
import play.Plugin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class XODataSourcePlugin extends Plugin{

	public static boolean isLoaded = false;
	public final String END_USER_KEY = "xo.enduser";
	public final String END_USER;
	public final String END_USER_FAILOVER;
	public final String END_USER_TEST;
	public static String DEFAULT_PERSISTENCE_UNIT ;
	public static final String TEST_PERSISTENCE_UNIT = "test_xoportal";
	public final String XO_APP_DB_URL;
	public final String XO_APP_DB_USER;
	public final String XO_APP_DB_PASSWORD;
	public static final Map<String, Object> XOAPP_DB_PROPERTIES = new HashMap<String, Object>(); 
	
	private static final Map<String, EntityManagerFactory> ENTITY_FACTORIES = new HashMap<String, EntityManagerFactory>();

    public XODataSourcePlugin(Application currentApplication) throws IOException, SQLException, PropertyVetoException {
    	if(currentApplication.isTest()==false){
    		 DEFAULT_PERSISTENCE_UNIT = "xoportal";
    	}
    	else{
    		DEFAULT_PERSISTENCE_UNIT ="test_xoapp";
    	}
    	END_USER = currentApplication.configuration().getString(END_USER_KEY);
    	END_USER_FAILOVER = END_USER + "_Failover";
    	END_USER_TEST = END_USER + "_Test";
    	Configuration config = currentApplication.configuration();
        XO_APP_DB_URL = "db." + DEFAULT_PERSISTENCE_UNIT + ".jdbcUrl";
        XO_APP_DB_USER = "db." + DEFAULT_PERSISTENCE_UNIT + ".username";
        XO_APP_DB_PASSWORD = "db." + DEFAULT_PERSISTENCE_UNIT + ".password";
    	XOAPP_DB_PROPERTIES.put(XOHikariCPConnectionProvider.CONFIG_CONNECTION_URL, config.getString(XO_APP_DB_URL));
    	XOAPP_DB_PROPERTIES.put(XOHikariCPConnectionProvider.CONFIG_CONNECTION_PASSWORD, config.getString(XO_APP_DB_PASSWORD));
    	XOAPP_DB_PROPERTIES.put(XOHikariCPConnectionProvider.CONFIG_CONNECTION_USERNAME, config.getString(XO_APP_DB_USER));
    	
    }

	@Override
	public boolean enabled() {
		return true;
	}

	@Override
	public void onStart() {
  		this.buildEntityManagerFactories();
	}

	@Override
	public void onStop() {
		closeEMFactories();
	}

	private void closeEMFactories() {
		try {
			if(XoUtil.hasData(ENTITY_FACTORIES)) {
				for(Entry<String, EntityManagerFactory> entityFactoryMgr : ENTITY_FACTORIES.entrySet()) {
					EntityManagerFactory entityManagerFactory = entityFactoryMgr.getValue();
					if(entityManagerFactory.isOpen()) {
						entityManagerFactory.close();
					}
				}
				isLoaded = false;
			}
		} catch (Exception e) {
			Logger.error("Error while shutting down the xo datasource plugin", e);
		}
	}

	/**
     * Create the entityManagerFactory Bean.
     * @return entityManagerFactory Bean
     */
    public void buildEntityManagerFactories() {
    	buildEMFactory(END_USER, DEFAULT_PERSISTENCE_UNIT);
    }

	private void buildEMFactory(String key, String persistenceUnitName) {
		if(!isLoaded) {
			try{
				ENTITY_FACTORIES.put(key, Persistence.createEntityManagerFactory(persistenceUnitName, XOAPP_DB_PROPERTIES));
				isLoaded = true;
			}catch(Exception e) {
				Logger.error("Error while building the entity manager factory for the persistence unit :" + persistenceUnitName, e);
			}
		}
	}

    public EntityManager em(String persistenceName) {
    	EntityManagerFactory entityManagerFactory = ENTITY_FACTORIES.get(persistenceName);
    	if(entityManagerFactory != null) {
    		return entityManagerFactory.createEntityManager();
    	}
    	return null;
    }

}
