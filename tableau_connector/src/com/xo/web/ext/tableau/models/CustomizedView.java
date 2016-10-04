package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customizedView")
public class CustomizedView {

	@XmlAttribute public String id;

	@XmlAttribute public String name;

	@XmlAttribute public String description;	
	
}
