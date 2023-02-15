package com.adviserratinguiautomation.customexceptions;

public class GenricException extends BaseAutomationException {

	public GenricException() {
		super();
	}

	public GenricException(String message) {
		super(message);
	}

	public GenricException(String message, Exception innerException) {
		super(message, innerException);
	}

}