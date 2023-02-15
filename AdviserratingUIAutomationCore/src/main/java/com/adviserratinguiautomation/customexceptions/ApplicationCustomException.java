package com.adviserratinguiautomation.customexceptions;

public class ApplicationCustomException extends BaseAutomationException {

	public ApplicationCustomException() {
		super();
	}

	public ApplicationCustomException(String message) {
		super(message);
	}

	public ApplicationCustomException(String message, Exception ex) {
		super(message, ex);
	}

}