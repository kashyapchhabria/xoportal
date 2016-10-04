package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "credentials")
public class Credential {

	@XmlAttribute
	public String name;

	@XmlAttribute 
	public String password;

	@XmlAttribute
	public String token;
	
	@XmlElement
	public Site site;

	@XmlElement
	public User user;
}
