package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pagination")
public class Pagination {

	@XmlAttribute Integer pageNumber;
	@XmlAttribute Integer pageSize;
	@XmlAttribute Integer totalAvailable;
	
}
