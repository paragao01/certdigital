package br.certdigital.shared.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;


public class CertdigitalException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(this.getClass());
	private Throwable error;
	private String errorRuleKey;
	private String errorRuleKeyParam;
	private List errorRuleKeys;
	private List errorRuleKeyParams;

	public CertdigitalException() {
		super();
		log.error(" GarantiaException");
	}

	public CertdigitalException(Throwable t) {
		this.error = t;
		log.error(" GarantiaException");
		log.error(" Exception  : " + t.toString());
		log.error(" Info  : " + t.getMessage());
	}

	public CertdigitalException(String s) {
		super(s);
		log.error(" GarantiaException");
		log.error(s);
	}

	public String getMessage() {
	  if (error!=null) return error.getMessage();
	  else return super.getMessage();
	}

	public String toString() {
	  if (error!=null) return error.toString();
	  else return super.toString();
	}

	public void printStackTrace() {
	  if (error!=null) error.printStackTrace();
	  else super.printStackTrace();
	}

	public void printStackTrace(PrintStream s) {
	  if (error!=null) error.printStackTrace(s);
	  else super.printStackTrace(s);
	}

	public void printStackTrace(PrintWriter s) {
	  if (error!=null) error.printStackTrace(s);
	  else super.printStackTrace(s);
	}

	/**
	  *
	  * @return Exception salva na instancia.
	*/
	public Throwable getErrorSaved() {
	  return error;
	}

	/**
	  *
	  * @return A primeira Exception do erro (no caso de uma cadeia).
	*/
	public Throwable getSourceError() {
	  Throwable err = error;

	  while (err!=null && err instanceof CertdigitalException)
		err = ((CertdigitalException) err).getErrorSaved();

	  if (err!=null) return err;
	  else return this;
	}

    /**
     *  Obtem o codigo da mensagem de erro de regra de negocio que foi lancada.
     * @return String
     */
    public String getErrorRuleKey() {
    	return this.errorRuleKey;
    }

	/**
	 *  Seta o codigo da mensagem de erro de regra de negocio que foi lancada.
	 * @return String
	 */
	public void setErrorRuleKey(String errorRuleKey) {
		this.errorRuleKey = errorRuleKey;
	}

	/**
	 *  Obtem o parametro do codigo da mensagem de erro de regra de negocio que foi lancada.
	 * @return String
	 */
	public String getErrorRuleKeyParam() {
		return this.errorRuleKeyParam;
	}

	/**
	 *  Seta o parametro do codigo da mensagem de erro de regra de negocio que foi lancada.
	 * @return String
	 */
	public void setErrorRuleKeyParam(String errorRuleKeyParam) {
		this.errorRuleKeyParam = errorRuleKeyParam;
	}


	/**
	 *  Obtem a lista de codigos das mensagens de erro de regra de negocio que foi lancada.
	 * @return List
	 */
	public List getErrorRuleKeys() {
		return this.errorRuleKeys;
	}

	/**
	 *  Seta uma lista de codigos de mensagens de erro de regra de negocio que foi lancada.
	 */
	public void setErrorRuleKeys(List errorRuleKeys) {
		this.errorRuleKeys = errorRuleKeys;
	}

	/**
	 *  Obtem a lista de parametros para os codigos da mensagem de erro de regra de negocio que foi lancada.
	 * @return List
	 */
	public List getErrorRuleKeyParams() {
		return this.errorRuleKeyParams;
	}

	/**
	 *  Seta uma lista de parametros para cada codigo da mensagem de erro de regra de negocio que foi lancada.
	 */
	public void setErrorRuleKeyParams(List errorRuleKeyParams) {
		this.errorRuleKeyParams = errorRuleKeyParams;
	}

}
