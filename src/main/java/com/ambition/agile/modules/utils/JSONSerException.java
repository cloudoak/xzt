package com.ambition.agile.modules.utils;

/**
 * 
 * @author javaoak
 *
 */
public class JSONSerException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 290542831556278454L;
	
	public JSONSerException(Throwable cause){
		super("JSON Serialize Error", cause);
	}
	
}
