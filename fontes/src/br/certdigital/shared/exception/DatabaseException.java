package br.certdigital.shared.exception;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author elisio
 */
public class DatabaseException extends DAOException {
	
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(this.getClass());

	/** chave para armazenamento da mensagem de erro */
	public static String DATABASE_EXCEPTION_KEY = "DATABASE_EXCEPTION_KEY";

	private ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources");
	
	/** Armazena o codigo de erro da exception atual */
	private Integer sqlErrorCode;
	
	/** Armazena a mensagem de erro original do banco de dados*/
	private String sqlOriginalErrorMessage;
	
	/**
	 * Retorna a mensagem de erro da SQLException
     */	
	public String getMessage() {		
		String errorMessage = null;
		if (sqlErrorCode != null) {
			try {
				errorMessage = rb.getString("database.exception.error.code."+sqlErrorCode);
			} catch (MissingResourceException e) {}
		} 
		if (errorMessage == null || "".equals(errorMessage)) {
			try {
				errorMessage = rb.getString("database.exception.default");
			} catch (MissingResourceException e) {}
		}
		if (errorMessage != null && !"".equals(errorMessage) && sqlOriginalErrorMessage != null) {
			Object[] arg = {sqlOriginalErrorMessage};
			errorMessage = MessageFormat.format(errorMessage, arg);
			//errorMessage.replaceFirst("{0}", sqlOriginalErrorMessage);
		}
		return (errorMessage != null ? errorMessage : super.getMessage());  
	} 

	/**
	 * @return Returns the sqlErrorCode.
	 */
	public Integer getSqlErrorCode() {
		return sqlErrorCode;
	}
	
	/**
	 * @param sqlErrorCode The sqlErrorCode to set.
	 */
	public void setSqlErrorCode(Integer sqlErrorCode) {
		this.sqlErrorCode = sqlErrorCode;
	}
	/**
	 * @return Returns the sqlOriginalErrorMessage.
	 */
	public String getSqlOriginalErrorMessage() {
		return sqlOriginalErrorMessage;
	}
	/**
	 * @param sqlOriginalErrorMessage The sqlOriginalErrorMessage to set.
	 */
	public void setSqlOriginalErrorMessage(String sqlOriginalErrorMessage) {
		this.sqlOriginalErrorMessage = sqlOriginalErrorMessage;
	}

	public DatabaseException() {
		super();
		log.error(" DatabaseException");
	}

	public DatabaseException(Throwable t) {
		super(t);
		log.error(" DatabaseException");
		// se for uma SQLException, seta o codigo de erro
		if (t instanceof SQLException) {
			setSqlErrorCode(new Integer(((SQLException)t).getErrorCode()));
			setSqlOriginalErrorMessage(((SQLException)t).getMessage());
		}
	}

	public DatabaseException(String s) {
		super(s);
		log.error(" DatabaseException");
	}
	
}
