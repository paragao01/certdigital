package br.certdigital.shared.exception;

import org.apache.log4j.Logger;

public class DAOException extends CertdigitalException  {
	
	private static final long serialVersionUID = 1L;

	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Constructor
	 */
	public DAOException() {
		super();
		log.debug("DAOException");
	}

	/**
	 * Constructor
	 */
	public DAOException(String s) {
		super(s);
		log.debug("DAOException");
		log.debug(s);
	}

	/**
	 * Constructor
	 */
	public DAOException(Throwable t) {
		super(t);
		log.debug("DAOException");
		log.debug(t.toString());
		log.debug(t.getMessage());
	}
	
}

