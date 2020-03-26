package br.certdigital.tools.j2ee.util;



import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.certdigital.tools.util.Logger;



/**
 *  Factory para Data Sources. Faz o lookup e cache dos Datasources.
  */

public class DataSourceFactory {

  private Map cache;

  private static DataSourceFactory singleton;

  private Context ctx;



  /**
   *  Inicializa o Contexto JNDI. Se existir o arquivo de properties jndi.properties
   *  no classpath root ele sera usado, senao sera criado um contexto default.
   *  @exception NamingException
   */

  private DataSourceFactory() throws NamingException {

    cache = Collections.synchronizedMap(new HashMap());

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

            Logger.debug(this.getClass(), "Context.INITIAL_CONTEXT_FACTORY nao encontrado no arquivo jndi.properties (DataSourceFactory.DataSourceFactory)");

        }

        if (jndiParams.get(Context.PROVIDER_URL) != null) {

            props.put(Context.PROVIDER_URL, jndiParams.get(Context.PROVIDER_URL));

        }

        else {

            Logger.debug(this.getClass(), "Context.PROVIDER_URL nao encontrado arquivo jndi.properties (DataSourceFactory.DataSourceFactory)");

        }

        ctx = new InitialContext(props);

      }

      else {

        Logger.debug(this.getClass(), "Arquivo jndi.properties nao encontrado. Criando o context default. (DataSourceFactory.DataSourceFactory)");

        // use default provider

        ctx = new InitialContext();

      }

    } catch( IOException ex ){

      Logger.debug(this.getClass(), ex.toString(), ex);

      Logger.debug(this.getClass(), "Arquivo jndi.properties nao encontrado. Criando o context default. (DataSourceFactory.DataSourceFactory)");

      // use default provider

      ctx = new InitialContext();

    }

  }

  /**
   * @return Instancia Singleton da classe
   * @exception NamingException
   */

  public static synchronized DataSourceFactory getInstance() throws NamingException {

    if (singleton == null) {

      singleton = new DataSourceFactory();

    }

    return singleton;

  }

  /**
   * Busca o datasource usando o servico JNDI. Verifica primeiro no cache:
   * se existir retorna a referencia caso contrario um novo datasource e obtido
   * e adicionado no cache.
   * @param dsJNDI nome do servico JNDI
   * @return DataSource para o nome correspondente definido no parametro dsJNDI.
   */

  public DataSource lookupDataSource(String jndiName) throws ServiceLocatorException {

    DataSource dataSource = null;

    try {
      if (cache.containsKey(jndiName)) {
         dataSource = (DataSource) cache.get(jndiName);
      }else{
         dataSource = (DataSource) ctx.lookup(jndiName);
         if (dataSource != null) {
             cache.put(jndiName, dataSource );
         }else{
            Logger.warn(this.getClass(), "DataSource nao encontrado para o jndi-name [" + jndiName + "] no metodo DataSourceFactory.lookupDataSource");
         }
      }
    } catch (NamingException ne) {
      Logger.fatal(this.getClass(), ne.toString(), ne);
      throw new ServiceLocatorException(ne);
    } catch (Exception e) {
      Logger.fatal(this.getClass(), e.toString(), e);
      throw new ServiceLocatorException(e);
    }
    return dataSource;
  }

}