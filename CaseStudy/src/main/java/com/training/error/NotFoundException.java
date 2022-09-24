package com.training.error;

@SuppressWarnings("serial")
public class NotFoundException extends Exception {

	private String messsage;

	public NotFoundException(String messsage) {
		super();
		this.messsage = messsage;
	}
	
	public String getMessage() {
		 return this.messsage ; 
	}
	
	
}
