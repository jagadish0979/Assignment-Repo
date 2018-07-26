package com.assignments.exception;

/**
 * @author Jagadish Anala
 *
 */
public class CSVException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a CSVException with no detail message. A detail message is a
	 * String that describes this particular exception.
	 */
	public CSVException() {
		super();
	}

	/**
	 * Constructs a CSVException with the specified detail message. A detail message
	 * is a String that describes this particular exception.
	 *
	 * <p>
	 *
	 * @param msg the detail message.
	 */
	public CSVException(String msg) {
		super(msg);
	}
}
