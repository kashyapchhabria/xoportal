package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "site")
public class Site {

	@XmlAttribute
	public String id;

	@XmlAttribute
	public String name;

	@XmlAttribute
	public String contentUrl;

	@XmlValue
	public String siteName;

	@XmlAttribute public String state;

	@XmlAttribute public String statusReason;

}
