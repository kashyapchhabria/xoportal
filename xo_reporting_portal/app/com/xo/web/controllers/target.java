package com.xo.web.controllers;

import play.db.*;
import java.util.*;
import java.io.*;
import java.sql.*;

import javax.sql.DataSource;

public class target
{
    public int id;
    public String name;
    public static String kpi_name;
    public static String segment;
    public static float kpi_start;
    public static float kpi_weight;    
    public float start;
    public float weight;
    public int checked;
    private static List<target> targets;
    public static String connectionUrl = "jdbc:impala://10.10.10.17:21050/everest_v2";
	public static String jdbcDriverName="com.cloudera.impala.jdbc41.Driver";
    public static Connection con = null;	
	public static String sqlStatement;
	public static ResultSet rs;
	public static Statement stmt;
    
    public target() {
    }

    public target(int id, String name, float target_val, float weight ,int checked) {
        this.id = id;
        this.name = name;
        this.start = target_val;
        this.weight = weight;
        this.checked = checked;
    }
    
    public target(String segment,String name, float target_val, float weight) {
        this.segment = segment;
        this.kpi_name = name;
        this.kpi_start = target_val;
        this.kpi_weight = weight;
    }
    
    public static void dbconnect(){
        try{
    		Class.forName(jdbcDriverName);
    		con = DriverManager.getConnection(connectionUrl);
    		stmt = con.createStatement();
        }
        catch(Exception e){}
    }
    
    public static List<String>getSegment(){
        dbconnect();
        List<String> kpi_name=new ArrayList<String>();
        int i=0;
        try{
            sqlStatement = "select * from segment_dim order by segment_name";
            rs = stmt.executeQuery(sqlStatement);
            while(rs.next()) {
                kpi_name.add(rs.getString("segment_name"));
                i++;
            }
        }
        catch(Exception e){}
        return kpi_name;        
    }
    public static List<target> findAll(String segment) {
        dbconnect();
        int i=0;
        try{
            sqlStatement = "    SELECT * FROM xo_kpi_master left join (select * from xo_seg_target_val where segment_name = \""+segment+"\") as xo ON xo_kpi_master.kpi_disp_name = xo.kpi_name order by kpi_id;"; 
            rs = stmt.executeQuery(sqlStatement);
            targets = new ArrayList<target>();    		
            while(rs.next()) {
                targets.add( new target( rs.getInt("kpi_id"), rs.getString("kpi_disp_name"), rs.getFloat("target_val"), rs.getFloat("weightage"), rs.getInt("checked")) );
                i++;
            }
        }
        catch(Exception e){}
        return targets;
    }

    public static List<String> kpiname(String segment){
        dbconnect();
        List<String> kpi_name=new ArrayList<String>();
        int i=0;
        try{
            sqlStatement = "select * from xo_seg_target_val where segment_name= \""+segment+"\"";
            rs = stmt.executeQuery(sqlStatement);
            while(rs.next()) {
                kpi_name.add(rs.getString("kpi_name"));
                i++;
            }
        }
        catch(Exception e){}
        return kpi_name;
    }
    
    public static String update_target(String segment_name, String kpi_selected_name,String kpi_start_value,String kpi_weight_value){
        dbconnect();
        List<target> target_db = null;
        String seg = segment_name;
        try{
            sqlStatement = "select * from xo_seg_target_val where segment_name != \""+segment_name+"\"";
            rs = stmt.executeQuery(sqlStatement);
            target_db = new ArrayList<target>();    		
            while(rs.next()) {
                target_db.add( new target(rs.getString("segment_name"),rs.getString("kpi_name"), rs.getFloat("target_val"), rs.getFloat("weightage")));
            }
            sqlStatement = "truncate xo_seg_target_val"; 
            stmt.execute(sqlStatement);            
        }
        catch(Exception e){}
        
        try{
            String kpi_name[] = kpi_selected_name.split(",");
            String kpi_start[] = kpi_start_value.split(",");
            String kpi_weight[] = kpi_weight_value.split(",");
            for(int i=0;i<kpi_name.length;i++){
                sqlStatement = "insert into xo_seg_target_val values(\""+segment_name+"\",\""+kpi_name[i]+"\","+Float.parseFloat(kpi_start[i])+","+Float.parseFloat(kpi_weight[i])+")";
                System.out.println(sqlStatement);
                stmt.execute(sqlStatement);
            }
        }
        catch(Exception e){}
        
        try{
            for(target t:target_db){
                sqlStatement = "insert into xo_seg_target_val values(\""+segment+"\",\""+kpi_name+"\","+kpi_start+","+kpi_weight+")";
                System.out.println(sqlStatement);
                stmt.execute(sqlStatement);
            }
        }
        catch(Exception e){}        
        return "updated";
    }
}
