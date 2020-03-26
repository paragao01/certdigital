package br.certdigital.shared.web;
  

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;


public class BaseAction extends DispatchAction {


    public BaseAction() { }


    protected void setMessage(String key, Object param, HttpServletRequest request) {
        // mensagem de sucesso da operacao
        ActionMessages messages = new ActionMessages();
        ActionMessage acMsg = (param != null ? new ActionMessage(key, param) : new ActionMessage(key));

        messages.add(ActionMessages.GLOBAL_MESSAGE, acMsg);
        saveMessages(request, messages);
    }


    protected void setMessage(String key, Object[] param, HttpServletRequest request) {
        // mensagem de sucesso da operacao
        ActionMessages messages = new ActionMessages();
        ActionMessage acMsg = (param != null ? new ActionMessage(key, param) : new ActionMessage(key));

        messages.add(ActionMessages.GLOBAL_MESSAGE, acMsg);
        saveMessages(request, messages);
    }


    protected void setError(String key, HttpServletRequest request, Exception e) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(key, e));
        saveErrors(request, errors);
    }

}
