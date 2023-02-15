package com.adviserratinguiautomation.customexceptions.drivercustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class ElementNotFoundException extends BaseAutomationException {

	public ElementNotFoundException() {
		super();
	}

	public ElementNotFoundException(String message) {
		super(message);
	}

	public ElementNotFoundException(String message, Exception ex) {
		super(message, ex);
	}
}