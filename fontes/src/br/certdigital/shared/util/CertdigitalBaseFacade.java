package br.certdigital.shared.util;

import br.certdigital.tools.database.BaseFacade;

public class CertdigitalBaseFacade extends BaseFacade
{
  public CertdigitalBaseFacade()
  {
  }

  protected String getDataSourceJNDI() 
  {
    return GlobalConstants.JNDI_DS;
  }
}