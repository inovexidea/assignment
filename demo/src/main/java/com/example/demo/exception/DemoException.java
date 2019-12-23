package com.example.demo.exception;

/**
 * 
 * @author Faruk Hossain
 * 
 * DemoException class
 *
 */
public class DemoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public DemoException(String message) {
		super(message, null, true, false);
		this.message = message;
	}

	public DemoException(String message, Throwable t) {
		super(message, t);
		this.message = message;
	}

	@Override
	public String toString() {
		return ("Exception (DemoException): " + message);
	}

}
