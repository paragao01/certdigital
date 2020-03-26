package br.certdigital.shared.struts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import br.certdigital.shared.exception.DatabaseException;
import br.certdigital.tools.util.Logger;
import br.certdigital.tools.util.ServletTransform;


/**
 *  Handler para qualquer excecao .
 *
 *  Faz o log da exception. Principalmente, NullPointerExceptions.
 */
public class GenericExceptionHandler extends ExceptionHandler {

    public ActionForward execute(Exception ex, ExceptionConfig econfig,
            ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws ServletException {

        //ex.printStackTrace();
        String msgs= "" + ex;
        Logger.fatal(getClass(), "***Exception : ", ex);
        
        if(ex.getMessage()!=null)
        	Logger.fatal(getClass(), "***Message   : " + ex.getMessage());
        
        Logger.fatal(getClass(), "***Page      : " + econfig.getPath());
 
        StringBuffer paramInfo = new StringBuffer();
        ServletTransform.getAppParametersFlat(request, paramInfo);

        Logger.fatal(getClass(), "***Parametros: "+paramInfo.toString());

        //Throwable t = ex;
        
        ActionError actionError = new ActionError(econfig.getKey(),
                msgs, request.getParameter("metodo"),
                mapping.getType());

        ActionForward actionForward = new ActionForward(econfig.getPath());
        
        // seta no request a key de erro de database
        if (ex instanceof DatabaseException) {
        	request.setAttribute(DatabaseException.DATABASE_EXCEPTION_KEY, ex.getMessage());
        }
        
        storeException(request, econfig.getKey(), actionError, actionForward, econfig.getScope());
        return actionForward;
    }


    /**
     *  Formata a cadeia de excecoes para apresentar para o usuario.
     *
     *@param    t - a excecao mais externa
     *@return   String formata listando a cadeia de excecoes
     */
    public static String formatExceptionMessages(Throwable t) {
        StringBuffer msgs = new StringBuffer();
        StringBuffer indent = new StringBuffer("\t");
        int i = 0;

            msgs.append("\n");

            if (i++ > 0) {
                msgs.append(indent + "|");
                msgs.append("\n" + indent + "+--");
            }

            msgs.append(t.getClass().getName() + ": " + t.getMessage());

            indent.append(indent);


        return msgs.toString();
    }
}



