package com.xo.web.ext.tableau.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authinfo")
public class AuthInfo {

	@XmlElement
	public String modulus;

	@XmlElement
	public String exponent;

	@XmlElement(name = "authenticity_token")
	public String authToken;
}
