package com.xo.web.ext.tableau.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorSite {

	@XmlElementWrapper(name="sites")
    @XmlElement(name="site")
	public List<Site> sites = new ArrayList<Site>();
	
}
