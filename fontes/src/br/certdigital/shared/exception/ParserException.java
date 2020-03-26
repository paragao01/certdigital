package br.certdigital.shared.exception;
import org.apache.log4j.Logger;




public class ParserException extends CertdigitalException {
	
	private static final long serialVersionUID = 1L;
	
    private Logger log = Logger.getLogger(this.getClass());
	/**
	 * Constructor for ParserException.
	 */
	public ParserException() {
		super();
		log.error("ParserException");
	}

	/**
	 * Constructor for ParserException.
	 * @param message
	 */
	public ParserException(String message) {
		super(message);
		log.error("ParserException");
		log.error("Exception  : " + message);
	}

	/**
	 * Constructor for ParserException.
	 * @param e
	 */
	public ParserException(Throwable e) {
		super(e);
		log.error("ParserException");
	}
}
