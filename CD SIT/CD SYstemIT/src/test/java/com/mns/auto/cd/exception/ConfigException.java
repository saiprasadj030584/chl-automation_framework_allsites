package com.mns.auto.cd.exception;

public class ConfigException extends RuntimeException {

	private static final long serialVersionUID = 2173823287304033128L;

	public ConfigException(String string) {
		
		super(string);
		System.out.println("123");
	}

}
