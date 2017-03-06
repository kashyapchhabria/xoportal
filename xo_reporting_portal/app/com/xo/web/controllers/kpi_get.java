package com.xo.web.controllers;

import play.db.*;
import java.util.*;
import java.io.*;
import java.sql.*;

import javax.sql.DataSource;

public class kpi_get
{
    public int id;
    public String name;
    public String description;
    public int checked;
    public String segment;
    public String kpi_name;
    public float kpi_start;
    public float kpi_weight;    
    private static List<kpi_get> kpi_list;
    public static String connectionUrl = "jdbc:impala://10.10.10.17:21050/everest_v2";
	public static String jdbcDriverName="com.cloudera.impala.jdbc41.Driver";
    public static Connection con = null;	
	public static String sqlStatement;
	public static ResultSet rs;
	public static Statement stmt;
    
    public kpi_get() {
    }
    
    public kpi_get(String kpi_name,String segment,float start,float weight) {
        this.kpi_name = kpi_name;
        this.segment = segment;
        this.kpi_start = start;
        this.kpi_weight = weight;
    }

    public kpi_get(int id, String name, String description,int checked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.checked = checked;
    }
    
    public static void dbconnect(){
        try{
    		Class.forName(jdbcDriverName);
    		con = DriverManager.getConnection(connectionUrl);
    		stmt = con.createStatement();
        }
        catch(Exception e){}
    }
    
    public static String update(String kpi_selected,List<kpi_get> kpi_list){
        dbconnect();
        try{
            sqlStatement = "truncate xo_kpi_master"; 
            stmt.execute(sqlStatement);
            String kpi_selected_list[] = kpi_selected.split(",");
            for (int i=0;i<kpi_list.size();i++) {
                kpi_get k =kpi_list.get(i);
                if(Arrays.asList(kpi_selected_list).contains(k.name)){
                    sqlStatement = "insert into xo_kpi_master values("+k.id+",\""+k.name+"\",\""+k.description+"\",1)";
                }
                else{
                    sqlStatement = "insert into xo_kpi_master values("+k.id+",\""+k.name+"\",\""+k.description+"\",0)";
                }
                System.out.println(sqlStatement);
                stmt.execute(sqlStatement);                
            }
        }
        catch(Exception e){}
        return kpi_selected;
    }
    public static List<kpi_get> findAll() {
        dbconnect();
        int i=0;
        try{
            sqlStatement = "select * from xo_kpi_master order by kpi_id"; 
            rs = stmt.executeQuery(sqlStatement);
            kpi_list = new ArrayList<kpi_get>();    		
            while(rs.next()) {
                kpi_list.add( new kpi_get( rs.getInt("kpi_id"), rs.getString("kpi_disp_name"), rs.getString("description"), rs.getInt("checked")) );
                i++;
            }
        }
        catch(Exception e){}
        return kpi_list;
    }
    
    public static List<kpi_get> get_kpi_target(String kpi_selected_name){
        dbconnect();
        int i=0;
        try{
            sqlStatement = "select * from xo_seg_target_val where kpi_name in ("+kpi_selected_name+") order by kpi_name";
            rs = stmt.executeQuery(sqlStatement);
            kpi_list = new ArrayList<kpi_get>();    		
            while(rs.next()) {
                kpi_list.add( new kpi_get(rs.getString("kpi_name"),rs.getString("segment_name"), rs.getFloat("target_val"), rs.getFloat("weightage")));
                i++;
            }
        }
        catch(Exception e){}
        return kpi_list;
    }
}