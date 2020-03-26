package br.certdigital.exception;

import br.certdigital.shared.exception.CertdigitalException;


public class CNPJAlreadyExistsException extends CertdigitalException {
	
	private static final long serialVersionUID = 1L;

  public CNPJAlreadyExistsException() { }

	public CNPJAlreadyExistsException(String msg) { super(msg); }
}
