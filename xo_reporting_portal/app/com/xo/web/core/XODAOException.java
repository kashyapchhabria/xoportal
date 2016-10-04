package com.xo.web.core;

@SuppressWarnings("serial")
public class XODAOException extends XOException {

	public XODAOException(){
		
	}

	public XODAOException(String message){
		super(message);
	}

	public XODAOException(String message, Throwable throwable){
		super(message, throwable);
	}

	public XODAOException(Throwable throwable){
		super(throwable);
	}

}
