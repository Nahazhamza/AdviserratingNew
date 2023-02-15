package com.adviserratinguiautomation.customexceptions.drivercustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class InvalidInputException extends BaseAutomationException {

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(String message) {
		super(message);
	}

	public InvalidInputException(String message, Exception ex) {
		super(message, ex);
	}
}