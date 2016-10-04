package com.xo.web.persistence;


/**
 * Created by sekar on 10/3/14.
 */
public class JpaConfig {/*


    *//**
     * Create the loadDataSource Bean.
     * @return loadDataSource Bean
     *//*
    public DataSource loadDataSource() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            BoneCPDataSource ds = new BoneCPDataSource();
            ds.setJdbcUrl("jdbc:mysql://localhost/database_2");
            ds.setUsername("root");
            ds.setPassword("root");


            // Pool configuration goes here

            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    *//**
     * Create the hibernateJpaVendorAdapter Bean.
     * @return hibernateJpaVendorAdapter Bean
     *//*
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        setHibernateProperties(adapter.getJpaPropertyMap());
        return adapter;
    }

    private static void setHibernateProperties(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        hibernateProperties.put("hibernate.connection.url", "jdbc:mysql://localhost/database_2");
        hibernateProperties.put("hibernate.connection.username", "root");
        hibernateProperties.put("hibernate.connection.password", "root");
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.ejb.event.post-insert", "org.hibernate.ejb.event.EJB3PostInsertEventListener,org.hibernate.envers.event.AuditEventListener");
        hibernateProperties.put("hibernate.ejb.event.post-update", "org.hibernate.ejb.event.EJB3PostUpdateEventListener,org.hibernate.envers.event.AuditEventListener");
        hibernateProperties.put("hibernate.ejb.event.post-delete", "org.hibernate.ejb.event.EJB3PostDeleteEventListener,org.hibernate.envers.event.AuditEventListener");
        hibernateProperties.put("hibernate.ejb.event.pre-collection-update", "org.hibernate.envers.event.AuditEventListener");
        hibernateProperties.put("hibernate.ejb.event.pre-collection-remove", "org.hibernate.envers.event.AuditEventListener");
        hibernateProperties.put("hibernate.ejb.event.pre-collection-recreate", "org.hibernate.envers.event.AuditEventListener");
    }

    *//**
     * Create the entityManagerFactory Bean.
     * @return entityManagerFactory Bean
     *//*
    public EntityManagerFactory entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPersistenceUnitName("testing");
        entityManagerFactory.setDataSource(loadDataSource());
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());

        //entityManagerFactory.get.getManagedClassNames().add("com.xo.web.models.User");
        //entityManagerFactory.setMappingResources(com.xo.web.models.system.User.class.getName()); // Specify hbm files or orm.xml files
        entityManagerFactory.setPackagesToScan("com.xo.web.models");
        return entityManagerFactory;
    }

*/}
