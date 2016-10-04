package com.xo.web.core;

@SuppressWarnings("serial")
public class XOException extends Exception {

	protected String message;

	public XOException(){
		
	}

	public XOException(String message){
		super(message);
		this.message = message;
	}

	public XOException(String message, Throwable throwable){
		super(message, throwable);
		this.message = message;
	}

	public XOException(Throwable throwable){
		super(throwable);
		this.message = throwable.getMessage();
	}

	public String getMessage() {
        return this.message;
    }

	public String toString() {
		return this.message;
	}
}
