package applicationcustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class InvalidUserNamePasswordException extends BaseAutomationException {

	private static final long serialVersionUID = 1L;

	public InvalidUserNamePasswordException() {
		super();
	}

	public InvalidUserNamePasswordException(String message) {
		super(message);
	}

	public InvalidUserNamePasswordException(String message, Exception ex) {
		super(message, ex);
	}

}