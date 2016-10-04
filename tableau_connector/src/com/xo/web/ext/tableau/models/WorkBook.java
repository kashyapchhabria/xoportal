package com.xo.web.ext.tableau.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "workbook")
public class WorkBook {

	@XmlElement public Site site;

	@XmlElement public Project project;
	
	@XmlElementWrapper(name="views", nillable = true) @XmlElement(name="view", nillable = true) public List<View> views;
	
	@XmlAttribute public String id;

	@XmlAttribute public String name;

	@XmlAttribute public String description;	
	
}
