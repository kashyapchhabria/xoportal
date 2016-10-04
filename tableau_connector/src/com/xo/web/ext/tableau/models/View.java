package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "view")
public class View {

	@XmlAttribute public String id;
	@XmlAttribute public String name;

}
