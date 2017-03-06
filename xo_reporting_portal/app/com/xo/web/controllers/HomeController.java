package com.xo.web.controllers;



import play.mvc.*;
import views.html.*;
import play.db.*;
import java.io.*;
import java.sql.*;
import java.util.Properties;

import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.User;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends XOBaseController {
    

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    
    public Result index() {
    	System.out.println("Called");
    	User currentUser = this.getCurrentRestUser();
    		return ok(com.xo.web.views.html.index.render(kpi_get.findAll(),target.getSegment()));
    }
    
    public Result update(String kpi_selected){
        return ok(com.xo.web.views.html.update.render(kpi_get.update(kpi_selected,kpi_get.findAll())));
    }

    public Result disp(String segment){
    	System.out.println("Called dnskgnkdfngkdnsfkng");
        return ok(com.xo.web.views.html.disp.render(target.findAll(segment),target.kpiname(segment)));
    }
    
    public Result update_target(String segment_name, String kpi_selected_name,String kpi_start_value,String kpi_weight_value){
        return ok(com.xo.web.views.html.update.render(target.update_target(segment_name, kpi_selected_name, kpi_start_value,kpi_weight_value)));
    }
    
    public Result get_target(String kpi_selected_name){
        return ok(com.xo.web.views.html.kpi_segment_disp.render(kpi_selected_name,kpi_get.get_kpi_target(kpi_selected_name)));
    }
}