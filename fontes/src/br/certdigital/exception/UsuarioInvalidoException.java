package br.certdigital.exception;

import br.certdigital.shared.exception.CertdigitalException;


public class UsuarioInvalidoException extends CertdigitalException {

	private static final long serialVersionUID = 1L;
	
  public UsuarioInvalidoException() { }

	public UsuarioInvalidoException(String msg) { super(msg); }
}
