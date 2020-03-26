package br.certdigital.tools.exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ApplicationException() {
	}

	public ApplicationException(String msg) {
		super(msg);
	}
}
