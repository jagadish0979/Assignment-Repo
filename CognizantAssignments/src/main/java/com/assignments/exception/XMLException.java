package com.assignments.exception;

/**
 * @author Jagadish Anala
 *
 */
public class XMLException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a XMLException with no detail message. A detail message is a
	 * String that describes this particular exception.
	 */
	public XMLException() {
		super();
	}

	/**
	 * Constructs a XMLException with the specified detail message. A detail message
	 * is a String that describes this particular exception.
	 *
	 * <p>
	 *
	 * @param msg the detail message.
	 */
	public XMLException(String msg) {
		super(msg);
	}
}
