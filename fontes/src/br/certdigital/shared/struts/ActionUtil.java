package br.certdigital.shared.struts;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.struts.action.ActionForward;

public class ActionUtil
{
  /**
   * Adiciona parametros a um ActionForward. Os valores dos parametros serao codificados atraves de java.net.URLEncoder.
   * @param origForward ActionForward original
   * @param params Parametros a serem acrescentados
   * @return Novo ActionForwad contemplando os parametros
   */
  public static ActionForward addParamToActionForward(ActionForward origForward, Properties params) {
      String path = origForward.getPath();
      Enumeration keys = params.keys();
      String k;
      String value;

      if (!keys.hasMoreElements()) return origForward;

      // monta os parametros
      StringBuffer url = new StringBuffer(path);
      if (path.indexOf("?")==-1) url.append("?");
      else url.append("&");

      while (keys.hasMoreElements()) {
        k = (String) keys.nextElement();
        value = params.getProperty(k);

        url.append(k);
        url.append("=");
        try {
			url.append(URLEncoder.encode(value, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        url.append("&");
        }
      // remove o ultimo '&'
      if (url.length()>0) url.setLength(url.length()-1);

      // retorna novo ActionForward contemplando os parametros
      return new ActionForward(url.toString());

  }
}