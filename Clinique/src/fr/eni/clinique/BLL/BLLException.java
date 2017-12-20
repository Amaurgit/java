package fr.eni.clinique.BLL;

public class BLLException extends Exception {

	public BLLException() {

	}

	public BLLException(String message) {
		super(message);
	}

	public BLLException(String message, Throwable exception) {
		super(message, exception);
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Couche BLL - ");
		sb.append(super.getMessage());

		return sb.toString();
	}
}
