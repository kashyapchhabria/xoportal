package com.xo.web.ext.tableau.models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;

import com.xo.web.ext.tableau.models.TableauProject;
import com.xo.web.models.dao.GenericDAOImpl;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;

public class TableauProjectDaoImpl implements TableauProjectDao {
	
	private static final String IMPALA_DB = XoUtil.getConfig(XoAppConfigKeys.IMPALA_DB);
	private static final String IMPALA_DB_URL = XoUtil.getConfig(XoAppConfigKeys.IMPALA_DB_URL);
	public static String connectionUrl = "jdbc:impala://"+IMPALA_DB_URL+":21050/"+IMPALA_DB;
	public static String jdbcDriverName="com.cloudera.impala.jdbc41.Driver";
	public static Connection con = null;
	public static Statement stmt;
	public static ResultSet rs;	
	public static String sqlStatement;
	
	public void dbconnect(){
        try{
    		
        }
        catch(Exception e){}
    }
	
	public ResultSet getFilterList() {
		try {
			Class.forName(jdbcDriverName);
			con = DriverManager.getConnection(connectionUrl);
			stmt = con.createStatement();
            sqlStatement = "select distinct status from diffusionmap;"; 
			rs = stmt.executeQuery(sqlStatement);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return rs;
	}

	@Override
	public TableauProject find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableauProject[] find(String... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableauProject getReference(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableauProject[] getReferences(String... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(TableauProject... entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TableauProject merge(TableauProject entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableauProject[] merge(TableauProject... entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableauProject save(TableauProject entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableauProject[] save(TableauProject... entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(TableauProject entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(TableauProject... entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeByIds(String... ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<TableauProject> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, TableauProject> findAllAsMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAttached(TableauProject entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void refresh(TableauProject... entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<TableauProject> findAll(Integer fromIndex, Integer rowLimit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> paginatedQuery(String queryString, Integer fromIndex, Integer rowLimit) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
