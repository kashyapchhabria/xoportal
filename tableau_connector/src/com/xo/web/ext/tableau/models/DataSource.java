package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dataSource")
public class DataSource {

	@XmlAttribute public String id;
	@XmlAttribute public String name;
	@XmlAttribute public String type;

	@XmlElement public Site site;
	@XmlElement(nillable = true) public Project project;
}
