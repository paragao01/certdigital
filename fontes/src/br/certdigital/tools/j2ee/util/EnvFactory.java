package br.certdigital.tools.j2ee.util;



import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.certdigital.tools.util.Logger;



/**

 *  Factory para variaveis de ambiente J2EE.

 *

 */

public class EnvFactory {

  private static EnvFactory singleton;

  private Context ctx;



  /**

   *  Inicializa o Contexto JNDI. Se existir o arquivo de properties jndi.properties

   *  no classpath root ele sera usado, senao ser� criado um contexto default.

   *

   * @exception NamingException

   */

  private EnvFactory() throws NamingException {

    try {

      // load the properties file from the classpath root

      InputStream inputStream = getClass().getResourceAsStream(

        "/jndi.properties" );

      Logger.info(this.getClass(), inputStream);

      if ( inputStream != null) {

        Properties jndiParams = new Properties();

        jndiParams.load( inputStream );

        Logger.info(this.getClass(), jndiParams);



        Hashtable props = new Hashtable();

        if (jndiParams.get(Context.INITIAL_CONTEXT_FACTORY) != null) {

            props.put(Context.INITIAL_CONTEXT_FACTORY,

                jndiParams.get(Context.INITIAL_CONTEXT_FACTORY));

        }

        else {

            Logger.debug(this.getClass(), "Context.INITIAL_CONTEXT_FACTORY nao encontrado no arquivo jndi.properties (EnvFactory.EnvFactory)");

        }

        if (jndiParams.get(Context.PROVIDER_URL) != null) {

            props.put(Context.PROVIDER_URL, jndiParams.get(Context.PROVIDER_URL));

        }

        else {

            Logger.debug(this.getClass(), "Context.PROVIDER_URL nao encontrado no arquivo jndi.properties (EnvFactory.EnvFactory)");

        }

        ctx = new InitialContext(props);

      }

      else {

        Logger.debug(this.getClass(), "Arquivo jndi.properties nao encontrado. Criando o context default. (EnvFactory.EnvFactory)");

        // use default provider

        ctx = new InitialContext();

      }

    } catch( IOException ex ){

      Logger.debug(this.getClass(), ex.toString(), ex);

      Logger.debug(this.getClass(), "Arquivo jndi.properties nao encontrado. Criando o context default. (EnvFactory.EnvFactory)");

      // use default provider

      ctx = new InitialContext();

    }

  }



  /**

   *

   * @return Instancia Singleton da classe

   * @exception NamingException

   */

  public static synchronized EnvFactory getInstance() throws NamingException {

    if (singleton == null) {

      singleton = new EnvFactory();

    }

    return singleton;

  }





   /**

   * Recupera uma propriedade de ambiente (env-entry).

   *

   * @param jndiEnvName Nome JNDI da propriedade

   * @return Valor da propriedade

   * @exception NamingException

   */

  public Object lookupEnv(String jndiEnvName) throws ServiceLocatorException {

    Object env = null;



    try {

      env = ctx.lookup(jndiEnvName);

      return env;

    } catch (NamingException ne) {

      Logger.fatal(this.getClass(), ne.toString(), ne);

      throw new ServiceLocatorException(ne);

    } catch (Exception e) {

      Logger.fatal(this.getClass(), e.toString(), e);

      throw new ServiceLocatorException(e);

    }

  }

}