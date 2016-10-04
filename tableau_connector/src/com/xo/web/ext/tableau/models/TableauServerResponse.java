package com.xo.web.ext.tableau.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tsResponse")
public class TableauServerResponse {

	@XmlElement public Credential credentials;

	@XmlElement(nillable = true) public DataSource datasource;

	@XmlElement(nillable = true) public Project project;

	@XmlElement public Site site;

	@XmlElement(nillable = true) public WorkBook workbook;

	@XmlElement(nillable = true) public User user;

	@XmlElement(nillable = true) public Pagination pagination;

	@XmlElementWrapper(name="sites", nillable = true) 
	@XmlElement 
	public List<Site> sites = new ArrayList<Site>();

	@XmlElementWrapper(name = "customizedViews", nillable = true) 
	@XmlElement(name = "customizedView") 
	public List<CustomizedView> customizedViews = new ArrayList<CustomizedView>();

	@XmlElementWrapper(name="projects", nillable = true) 
	@XmlElement(name = "project")
	public List<Project> projects = new ArrayList<Project>();
	
	@XmlElementWrapper(name="datasources", nillable = true) 
	@XmlElement(name = "datasource")
	public List<DataSource> datasources = new ArrayList<DataSource>();

	@XmlElementWrapper(name="workbooks", nillable = true)
	@XmlElement(name = "workbook")
	public List<WorkBook> workbooks = new ArrayList<WorkBook>();

	@XmlElementWrapper(name="users", nillable = true)
	@XmlElement(name = "user")
	public List<User> users = new ArrayList<User>();

	@XmlElementWrapper(name="views", nillable = true)
	@XmlElement(name = "view")
	public List<View> views = new ArrayList<View>();

	@XmlElement public Domain domain = new Domain();
}
