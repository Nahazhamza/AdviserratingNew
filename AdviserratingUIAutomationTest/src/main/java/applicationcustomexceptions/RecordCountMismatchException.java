package applicationcustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class RecordCountMismatchException extends BaseAutomationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordCountMismatchException() {
		super();
	}

	public RecordCountMismatchException(String message) {
		super(message);
	}

	public RecordCountMismatchException(String message, Exception ex) {
		super(message, ex);
	}

}