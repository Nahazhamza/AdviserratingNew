package applicationcustomexceptions;

import com.adviserratinguiautomation.customexceptions.BaseAutomationException;

public class PageTittleMissmatchException extends BaseAutomationException {

	private static final long serialVersionUID = 1L;

	public PageTittleMissmatchException() {
		super();
	}

	public PageTittleMissmatchException(String message) {
		super(message);
	}

	public PageTittleMissmatchException(String message, Exception ex) {
		super(message, ex);
	}

}