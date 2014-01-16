package Utilities;

public class InvalidEmailException extends Exception {
	private static final long serialVersionUID = 2560972177738813068L;

	private InvalidEmailException() {
		super();
	}

	public static void throwNewInvalidEmailException()
			throws InvalidEmailException {
		throw new InvalidEmailException();
	}
}
