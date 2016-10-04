package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "project")
public class Project {

	@XmlAttribute public String id;
	@XmlAttribute public String name;

}
