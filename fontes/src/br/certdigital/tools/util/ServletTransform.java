package br.certdigital.tools.util;



import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;



/**

 *  Transforma informacoes contidas no Servlet em outros objetos.

 *

 

 */

public class ServletTransform {

    /**

     *  Converte os parametros da aplicacao armazenados no request HTTP do

     *  servlet para um objeto Properties. Se o parametro for um array, entao se

     *  possuir apenas 1 elemento sera tratado como um parametro normal

     *  (String). Caso exista varios elementos, entao um array de Strings

     *  (String[])sera armazenado no Properties.

     *

     *@param  request  Parametros do Request HTTP do servlet

     *@return          Parametros em um objeto Properties

     */

    public static Properties getAppParameters(HttpServletRequest request) {

        Enumeration paramsReq = request.getParameterNames();

        String param;

        Properties appParams = new Properties();

        String prArray[] = null;

        Object value = null;



        while (paramsReq.hasMoreElements()) {

            param = (String) paramsReq.nextElement();

            prArray = request.getParameterValues(param);



            if (prArray == null) {

                value = null;

            } else if (prArray.length == 1) {

                value = prArray[0];

            } else {

                value = prArray;

            }



            appParams.put(param, value);

        }



        //--> 10/06/2002

        javax.servlet.http.HttpSession session = request.getSession(true);

        appParams.put("session", session);

        appParams.put("request", request);

        //-->



        return appParams;

    }





    /**

     *  Recupera todos os parametros do request HTTP e armazena em uma String.

     *  Util para debug. 

     *  

     *  Formato da String : 

     *

     *  <parametro>=<valor parametro><\n><parametro>=<valor parametro>[...] 

     *

     *  Se o parametro for um array e existir varios

     *  elementos, entao os valores estarao separados por virgula.

     *

     *@param  request  Parametros do Request HTTP do servlet

     *@param  buffer   Buffer para gravacao dos parametros do Request

     */

    public static void getAppParametersFlat(HttpServletRequest request, StringBuffer buffer) {

        Enumeration params = request.getParameterNames();

        String paramName = "";

        String paramValue = "";

        String prArray[] = null;

        int i = 0;



        while (params.hasMoreElements()) {

            paramName = (String) params.nextElement();

            prArray = request.getParameterValues(paramName);

            if (prArray == null) {

                paramValue = "";

            } else if (prArray.length == 1) {

                paramValue = prArray[0];

            } else {

                paramValue = "";

                paramValue += prArray[0];



                for (i = 1; i < prArray.length; i++) {

                    paramValue += ",";

                    paramValue += prArray[i];

                }

            }



            buffer.append(paramName);

            buffer.append("=");

            buffer.append(paramValue);

            buffer.append("\n");

        }

    }

}



