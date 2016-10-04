package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User {

	@XmlAttribute public String id;
	@XmlAttribute public String name;
	@XmlAttribute public String role;
	@XmlAttribute public String publish;
	@XmlAttribute public String contentAdmin;
	@XmlAttribute public String lastLogin;
	@XmlAttribute public String externalAuthUserId;
}
