package br.certdigital.tools.j2ee.util;



import br.certdigital.tools.error.BaseException;



/**

 *  Essa exce��o ser� lan�ada quando ocorrer uma exce��o de 

 *  "NamingException" na tentativa de procurar algum servi�o ou 

 *  alguma exce��o inesperada (exce��es de runtime).

 */

public class ServiceLocatorException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	/**

	 * Constructor for ServiceLocatorException.

	 */

	public ServiceLocatorException() {

		super();

	}



	/**

	 * Constructor for ServiceLocatorException.

	 * @param message

	 */

	public ServiceLocatorException(String message) {

		super(message);

	}



	/**

	 * Constructor for ServiceLocatorException.

	 * @param e

	 */

	public ServiceLocatorException(Throwable e) {

		super(e);

	}



}