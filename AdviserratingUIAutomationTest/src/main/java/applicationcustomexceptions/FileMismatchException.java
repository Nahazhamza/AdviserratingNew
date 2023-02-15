package applicationcustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class FileMismatchException extends BaseAutomationException {

	private static final long serialVersionUID = 1L;

	public FileMismatchException() {
		super();
	}

	public FileMismatchException(String message) {
		super(message);
	}

	public FileMismatchException(String message, Exception ex) {
		super(message, ex);
	}

}
