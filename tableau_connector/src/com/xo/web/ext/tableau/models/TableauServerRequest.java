package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tsRequest")
public class TableauServerRequest {

	@XmlElement public Credential credentials;

	@XmlElement(nillable = true) public DataSource datasource;

	@XmlElement(nillable = true) public Project project;

	@XmlElement public Site site;

	@XmlElement(nillable = true) public WorkBook workbook;
}
