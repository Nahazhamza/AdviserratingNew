package com.adviserratinguiautomation.customexceptions.drivercustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class ElementNotClickableException extends BaseAutomationException {

	public ElementNotClickableException() {
		super();
	}

	public ElementNotClickableException(String message) {
		super(message);
	}

	public ElementNotClickableException(String message, Exception ex) {
		super(message, ex);
	}
}