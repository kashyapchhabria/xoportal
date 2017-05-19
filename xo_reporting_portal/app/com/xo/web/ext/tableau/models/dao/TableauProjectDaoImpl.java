package com.xo.web.ext.tableau.models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.RowSet;

import com.sun.rowset.CachedRowSetImpl;
import com.xo.web.ext.tableau.models.TableauProject;
import com.xo.web.models.dao.GenericDAOImpl;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;

public class TableauProjectDaoImpl extends GenericDAOImpl<TableauProject, String> implements TableauProjectDao {

	private static final String IMPALA_DB = XoUtil.getConfig(XoAppConfigKeys.IMPALA_DB);
	private static final String IMPALA_DB_URL = XoUtil.getConfig(XoAppConfigKeys.IMPALA_DB_URL);
	public static String connectionUrl = "jdbc:impala://"+IMPALA_DB_URL+":21050/"+IMPALA_DB;
	public static final String jdbcDriverName="com.cloudera.impala.jdbc41.Driver";
	public static final String sqlStatement = "select distinct status from diffusionmap order by status asc";

	static {
		try {
			Class.forName(jdbcDriverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public RowSet getFilterList() {
		Connection con = null;
		CachedRowSetImpl crs = null;
		try {
			con = DriverManager.getConnection(connectionUrl);
			final Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(con != null) {
						con.close();
					}
				} catch(Exception e){
					e.printStackTrace();	
				}
			}
		return crs;
	}
	
}
