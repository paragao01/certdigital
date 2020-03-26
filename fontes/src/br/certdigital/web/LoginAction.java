/*
 * Created on 20/04/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.certdigital.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.certdigital.facade.SecurityFacade;
import br.certdigital.shared.util.GlobalConstants;
import br.certdigital.shared.web.BaseAction;
import br.certdigital.shared.web.CertdigitalForm;
import br.certdigital.view.LoginHelper;
import br.certdigital.vo.OperadorVO;

/**
 * @author elisio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class LoginAction extends BaseAction {
	
	public LoginAction()
	  {
	  }

		/**
		 * Executa a autenticação do usuário
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
	  public ActionForward verificaOperadorSenha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {	  	  	
	  	CertdigitalForm frm = (CertdigitalForm) form;
	    
	    LoginHelper helper = frm.getLoginHelper();
		
	    OperadorVO vo = helper.getOperadorVO();
	    
    	String senhaEmpresa = vo.getSenha();
    			 
	    SecurityFacade facade = new SecurityFacade();

	    if(facade.verificaOperadorSenha(vo.getIdOperador(), vo.getSenha())){	    	
	    	vo = facade.buscaInfOperador(vo.getIdOperador());

	    	vo.setSenha(senhaEmpresa);
	    	
	    	HttpSession session = request.getSession();
	    	session.setAttribute(GlobalConstants.OPERADOR_INFO, vo);
	    	helper.setOperadorVO(vo);
	    	return mapping.findForward("index");
	    }
	    	    
	    super.setMessage("login.invalido",null,request);
	    
	    return mapping.getInputForward();
	  }

	  /**
	   * Executa o logout do usuario
	   * @param mapping
	   * @param form
	   * @param request
	   * @param response
	   * @return
	   * @throws Exception
	   */
	  public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      	HttpSession session = request.getSession();
		session.removeAttribute(GlobalConstants.OPERADOR_INFO);
		
      	return mapping.getInputForward();
	  }
}
