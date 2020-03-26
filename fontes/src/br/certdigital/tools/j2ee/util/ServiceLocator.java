package br.certdigital.tools.j2ee.util;



import javax.naming.NamingException;
import javax.sql.DataSource;

import br.certdigital.tools.util.Logger;



/**

 *  Pattern J2EE para localizacao de servicos JNDI.


 */

public class ServiceLocator {

  

  /**

   * Busca uma propriedade (env-entry) do ambiente J2EE no servico JNDI.

   *

   * @param jndiName Nome JNDI da propriedade

   * @return Valor da propriedade solicitada convertida para String

   * @exception ServiceLocatorException

   */

  public static String lookupEnvStr(String jndiEnvName) throws ServiceLocatorException {

    Object env = null;



    try {

      env =  lookupEnv(jndiEnvName);

      return (env!=null?env.toString():null);

    }

    catch (ServiceLocatorException e) {

      Logger.fatal(ServiceLocator.class, e);

      throw (ServiceLocatorException) e;

    }

    catch (Throwable e) {

      Logger.fatal(ServiceLocator.class, e);

      throw new ServiceLocatorException(e);

    }

  }



  /**

   * Busca uma propriedade (env-entry) do ambiente J2EE no servico JNDI.

   *

   * @param jndiName Nome JNDI da propriedade

   * @return Valor da propriedade solicitada

   * @exception ServiceLocatorException

   */

  public static Object lookupEnv(String jndiEnvName) throws ServiceLocatorException {

    EnvFactory envFac = null;



    try {

      envFac = EnvFactory.getInstance();

      return envFac.lookupEnv(jndiEnvName);

    }

    catch (NamingException ne) {

      String msg= "ServiceLocator.lookupEnv: Propriedade "+jndiEnvName+"(env-entry) nao encontrada no contexto JNDI da aplica��o.";

      Logger.fatal(ServiceLocator.class, msg, ne);

      throw new ServiceLocatorException(msg);

    }

    catch (Throwable e) {

      Logger.fatal(ServiceLocator.class, e);

      throw new ServiceLocatorException(e);

    }

  }



  /**

   * Busca o DataSource usando o servi�o JNDI.

   *

   * @param jndiName Nome do servi�o JNDI do DataSource.

   * @return DataSource solicitado

   * @exception ServiceLocatorException

   */

  public static DataSource lookupDataSource(String jndiName) throws ServiceLocatorException {

    DataSource dataSource = null;

    try {

      DataSourceFactory dsFac = DataSourceFactory.getInstance();

      dataSource = dsFac.lookupDataSource(jndiName);

      return  dataSource;

    }

    catch (NamingException ne) {

      String msg= "ServiceLocator.lookupDataSource: DataSource "+jndiName+" nao encontrada no contexto JNDI da aplica��o.";

      Logger.fatal(ServiceLocator.class, msg, ne);

      throw new ServiceLocatorException(msg);

    }

    catch (Throwable e) {

      Logger.fatal(ServiceLocator.class, e);

      throw new ServiceLocatorException(e);

    }

  }

}