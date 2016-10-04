package com.xo.web.persistence;

import com.xo.web.util.XoUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;
import org.hibernate.HibernateException;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.hibernate.service.spi.Configurable;
import org.hibernate.service.spi.Stoppable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * Hibernate Connection Provider.
 * 
 * @author wallacew
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class XOHikariCPConnectionProvider implements ConnectionProvider, Configurable, Stoppable{

	/** Config key. */
	public static final String CONFIG_CONNECTION_DRIVER_CLASS = "hibernate.connection.driver_class";
	/** Config key. */
	public static final String CONFIG_CONNECTION_PASSWORD = "hibernate.connection.password";
	/** Config key. */
	public static final String CONFIG_CONNECTION_USERNAME = "hibernate.connection.username";
	/** Config key. */
	public static final String CONFIG_CONNECTION_URL = "hibernate.connection.url";
	/** Config key. */
	protected static final String CONFIG_CONNECTION_DRIVER_CLASS_ALTERNATE = "javax.persistence.jdbc.driver";
	/** Config key. */
	protected static final String CONFIG_CONNECTION_PASSWORD_ALTERNATE = "javax.persistence.jdbc.password";
	/** Config key. */
	protected static final String CONFIG_CONNECTION_USERNAME_ALTERNATE = "javax.persistence.jdbc.user";
	/** Config key. */
	protected static final String CONFIG_CONNECTION_URL_ALTERNATE = "javax.persistence.jdbc.url";

	protected static final String CONFIG_MAX_CONNECTIONS_PER_PARTITION = "maximumPoolSize";
	protected static final String CONFIG_MIN_CONNECTIONS_PER_PARTITION = "minimumPoolSize";
	protected static final String CONFIG_IDLE_MAX_AGE = "idleTimeout";
	protected static final String CONFIG_MINIMUM_POOL_SIZE = "minimumPoolSize";
	protected static final String CONFIG_CONNECTION_TIMEOUT = "connectionTimeout";
	protected static final String CONFIG_VALIDATION_TIMEOUT = "validationTimeout";
	protected static final String CONFIG_CACHE_PREP_STMTS = "cachePrepStmts";
	protected static final String CONFIG_PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
	protected static final String CONFIG_PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";

	/** Connection pool handle. */
	private HikariPool pool;
	/** Isolation level. */
	private Integer isolation;
	/** Autocommit option. */
	private boolean autocommit;
	/** Classloader to use to load the jdbc driver. */
	private ClassLoader classLoader;
	/** Configuration handle. */
	private HikariConfig config;
	/** Class logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(XOHikariCPConnectionProvider.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.hibernate.engine.jdbc.connections.spi.ConnectionProvider#closeConnection(java.sql.Connection)
	 */
	public void closeConnection(Connection conn) throws SQLException {
		try{
			LOGGER.debug("Closing connection in pool...");
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
			LOGGER.debug("Connection closed successfully in pool.");
		} catch(Throwable e) {
			LOGGER.error("Error occurred while closing the connection in pool.", e);
			throw new SQLException("Connection Close error.", e);
		} finally {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	/**
	 * Pool configuration.
	 * 
	 * @param props
	 * @throws HibernateException
	 */
	public void configure(Properties props) throws HibernateException {
		try {

			this.config = new HikariConfig();

			String url = props.getProperty(CONFIG_CONNECTION_URL);
			String username = props.getProperty(CONFIG_CONNECTION_USERNAME);
			String password = props.getProperty(CONFIG_CONNECTION_PASSWORD);
			String driver = props.getProperty(CONFIG_CONNECTION_DRIVER_CLASS);
			String minimumPoolSize =  props.getProperty(CONFIG_MINIMUM_POOL_SIZE);
			String connectionTimeout = props.getProperty(CONFIG_CONNECTION_TIMEOUT);
			String validationTimeout = props.getProperty(CONFIG_VALIDATION_TIMEOUT);
			/*String cachePrepStmts = props.getProperty(CONFIG_CACHE_PREP_STMTS);
			String prepStmtCacheSize = props.getProperty(CONFIG_PREP_STMT_CACHE_SIZE);
			String prepStmtCacheSqlLimit = props.getProperty(CONFIG_PREP_STMT_CACHE_SQL_LIMIT);*/

			Integer maxConnections = ConfigurationHelper.getInteger(CONFIG_MAX_CONNECTIONS_PER_PARTITION, props);
			Integer idleTime = ConfigurationHelper.getInteger(CONFIG_IDLE_MAX_AGE, props);

			if (!XoUtil.isNotNull(url)) {
				url = props.getProperty(CONFIG_CONNECTION_URL_ALTERNATE);
			}
			if (!XoUtil.isNotNull(username)) {
				username = props.getProperty(CONFIG_CONNECTION_USERNAME_ALTERNATE);
			}
			if (!XoUtil.isNotNull(password)) {
				password = props.getProperty(CONFIG_CONNECTION_PASSWORD_ALTERNATE);
			}
			if (!XoUtil.isNotNull(driver)) {
				driver = props.getProperty(CONFIG_CONNECTION_DRIVER_CLASS_ALTERNATE);
			}
			if (XoUtil.isNotNull(url)) {
				this.config.setJdbcUrl(url);
			}
			if (XoUtil.isNotNull(username)) {
				this.config.setUsername(username);
			}
			if (XoUtil.isNotNull(password)) {
				this.config.setPassword(password);
			}
			if (XoUtil.isNotNull(minimumPoolSize)) {
				this.config.setMinimumIdle(Integer.parseInt(minimumPoolSize));
			}
			if (XoUtil.isNotNull(connectionTimeout)) {
				this.config.setConnectionTimeout(Integer.parseInt(connectionTimeout));
			}
			if (XoUtil.isNotNull(validationTimeout)) {
				this.config.setValidationTimeout(Integer.parseInt(validationTimeout));
			}

			// Remember Isolation level
			this.isolation = ConfigurationHelper.getInteger(AvailableSettings.ISOLATION, props);
			this.autocommit = ConfigurationHelper.getBoolean(AvailableSettings.AUTOCOMMIT, props);

			LOGGER.debug(this.config.toString());
			if (XoUtil.isNotNull(driver)) {
				loadClass(driver);
			}

			if (maxConnections != null && maxConnections > 0) {
				this.config.setMaximumPoolSize(maxConnections);
			}
			if (idleTime != null && idleTime > 0) {
				this.config.setIdleTimeout(idleTime);
			}

			this.config.setAutoCommit(this.autocommit);
			this.config.setMaxLifetime(60000);
			this.config.setConnectionTestQuery("select 1");
			this.config.setConnectionTimeout(60000);
			this.config.setInitializationFailFast(true);
			this.pool = createPool(this.config);
		} catch (Exception e) {
			throw new HibernateException(e);
		}
	}

	/**
	 * Loads the given class, respecting the given classloader.
	 * 
	 * @param clazz
	 *            class to load
	 * @return Loaded class
	 * @throws ClassNotFoundException
	 */
	protected Class<?> loadClass(String clazz) throws ClassNotFoundException {
		if (this.classLoader == null) {
			return Class.forName(clazz);
		}
		return Class.forName(clazz, true, this.classLoader);
	}

	/**
	 * Creates the given connection pool with the given configuration. Extracted
	 * here to make unit mocking easier.
	 * 
	 * @param config
	 *            configuration object.
	 * @return BoneCP connection pool handle.
	 */
	protected HikariPool createPool(HikariConfig config) {
		return new HikariPool(config);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.hibernate.engine.jdbc.connections.spi.ConnectionProvider#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		if(this.pool != null) {
			connection = this.pool.getConnection();
			// set the Transaction Isolation if defined
			try {
				// set the Transaction Isolation if defined
				if ((this.isolation != null) && (connection.getTransactionIsolation() != this.isolation.intValue())) {
					connection.setTransactionIsolation(this.isolation.intValue());
				}
				// toggle autoCommit to false if set
				if (connection.getAutoCommit() != this.autocommit) {
					connection.setAutoCommit(this.autocommit);
				}
			} catch (SQLException e) {
				try {
					connection.close();
				} catch (Exception e2) {
					LOGGER.warn("Setting connection properties failed and closing this connection failed again", e);
				}
				throw e;
			}
		}
		return connection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.hibernate.engine.jdbc.connections.spi.ConnectionProvider#supportsAggressiveRelease()
	 */
	public boolean supportsAggressiveRelease() {
		return true;
	}

	/**
	 * Returns the configuration object being used.
	 * 
	 * @return configuration object
	 */
	protected HikariConfig getConfig() {
		return this.config;
	}

	/**
	 * Returns the classloader to use when attempting to load the jdbc driver
	 * (if a value is given).
	 * 
	 * @return the classLoader currently set.
	 */
	public ClassLoader getClassLoader() {
		return this.classLoader;
	}

	/**
	 * Specifies the classloader to use when attempting to load the jdbc driver
	 * (if a value is given). Set to null to use the default loader.
	 * 
	 * @param classLoader
	 *            the classLoader to set
	 */
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		return ConnectionProvider.class.equals(unwrapType)
				|| XOHikariCPConnectionProvider.class.isAssignableFrom(unwrapType);
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		if (ConnectionProvider.class.equals(unwrapType)
				|| XOHikariCPConnectionProvider.class.isAssignableFrom(unwrapType)) {
			return (T) this;
		}
		throw new UnknownUnwrapTypeException(unwrapType);
	}

	/**
	 * Legacy conversion.
	 * 
	 * @param map
	 * @return Properties
	 */
	private Properties mapToProperties(Map<String, String> map) {
		Properties p = new Properties();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			p.put(entry.getKey(), entry.getValue());
		}
		return p;
	}

	@Override
	public void configure(Map configurationValues) {
		configure(mapToProperties(configurationValues));
	}

	@Override
	public void stop() {
		this.close();
	}

	/**
	 * alias for stop.
	 */
	public void close() {
		try {
			this.pool.shutdown();
		} catch (InterruptedException e) {
			LOGGER.error("Error while closing the connections.", e);;
		}
	}
}
