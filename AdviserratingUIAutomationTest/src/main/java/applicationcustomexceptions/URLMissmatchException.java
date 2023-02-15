package applicationcustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class URLMissmatchException extends BaseAutomationException {

	private static final long serialVersionUID = 1L;

	public URLMissmatchException() {
		super();
	}

	public URLMissmatchException(String message) {
		super(message);
	}

	public URLMissmatchException(String message, Exception ex) {
		super(message, ex);
	}

}