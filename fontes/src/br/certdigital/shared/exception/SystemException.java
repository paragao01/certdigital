package br.certdigital.shared.exception;

import org.apache.log4j.Logger;

public class SystemException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(this.getClass());

	Throwable throwable = null;

	public SystemException() {
		super();
		log.debug("SystemException");
	}

	public SystemException(String s) {
		super(s);
		log.debug("SystemException");
		log.debug(s);
	}

	public SystemException(Throwable newThrowable) {
		log.debug("SystemException");
		log.debug(newThrowable.toString());
		log.debug(newThrowable.getMessage());
		this.throwable = newThrowable;
	}

	public String toString() {
		if (throwable != null)
			return throwable.toString();
		else
			return super.toString();
	}

}
