package applicationcustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class DuplicateDataException extends BaseAutomationException {

	private static final long serialVersionUID = 1L;

	public DuplicateDataException() {
		super();
	}

	public DuplicateDataException(String message) {
		super(message);
	}

	public DuplicateDataException(String message, Exception ex) {
		super(message, ex);
	}

}
