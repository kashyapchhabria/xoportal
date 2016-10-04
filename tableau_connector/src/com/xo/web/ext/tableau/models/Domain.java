package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "domain")
public class Domain {

	@XmlAttribute public String name = "corp.xoanonanalytics.net";

}
