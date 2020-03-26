package br.certdigital.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.certdigital.exception.CNPJAlreadyExistsException;
import br.certdigital.facade.EntidadeFacade;
import br.certdigital.shared.security.OperadorInfo;
import br.certdigital.shared.web.BaseAction;
import br.certdigital.shared.web.CertdigitalForm;
import br.certdigital.view.EntidadeHelper;
import br.certdigital.vo.EntidadeVO;
import br.certdigital.vo.ItemEntidadeVO;
import br.certdigital.vo.OperadorVO;

public class EntidadeAction extends BaseAction {
	
	public EntidadeAction(){}
	
	  public ActionForward prepararFiltro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	  {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   EntidadeHelper helper = frm.getEntidadeHelper();
		   helper.setEntidadeVO(new EntidadeVO());
		   EntidadeFacade facade = new EntidadeFacade();
		   EntidadeVO vo = helper.getEntidadeVO();
		   facade.prepararFiltro(vo);
		   
	    return mapping.getInputForward();
	  }
	
	  public ActionForward inserir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	  {
	   CertdigitalForm frm = (CertdigitalForm) form;
	   
	   EntidadeHelper helper = frm.getEntidadeHelper();
		
	   EntidadeFacade facade = new EntidadeFacade();
	   
	   // retorna as informacoes do operador logado
	   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
	
	   EntidadeVO EntidadeVO = helper.getEntidadeVO();
	   ItemEntidadeVO itemVo = EntidadeVO.getItemEntidadeVO();
	   helper.setParamItemEntidadeVO(itemVo, operadorVo);
	   
	   if (itemVo.getNumCNPJ() != null && itemVo.getNumCNPJ().longValue() > 0) {
		   if (facade.verificarCNPJ(itemVo.getNumCNPJ()) ) {
			   facade.prepararFiltro(EntidadeVO);
			   throw new CNPJAlreadyExistsException();
		   }
	   }
    
	   itemVo = facade.insert(itemVo, operadorVo);
	   
	   helper.getEntidadeVO().getItemEntidadeVO().setIdOperador(itemVo.getIdOperador());
	   
	   facade.prepararFiltro(EntidadeVO);

	   super.setMessage("entidade.incluido.sucesso",null,request);
	   resetToken(request);
	   saveToken(request);
	   return mapping.getInputForward();
	  }
	  
	  public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {
	    CertdigitalForm frm = (CertdigitalForm) form;
	    
	    EntidadeHelper helper = frm.getEntidadeHelper();
		
	    EntidadeVO vo = helper.getEntidadeVO();

	    EntidadeFacade facade = new EntidadeFacade();
	    
	    // retorna as informacoes do operador logado
	    OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
	    
	    facade.pesquisar(operadorVo, vo);
	    
	    helper.setEntidadeVO(vo);
	    
	    return mapping.getInputForward();

	   }

	  public ActionForward pesquisarEntidade(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	   {
	    CertdigitalForm frm = (CertdigitalForm) form;
	    
	    EntidadeHelper helper = frm.getEntidadeHelper();
		
	    EntidadeVO vo = helper.getEntidadeVO();

	    EntidadeFacade facade = new EntidadeFacade();
	       
	    // retorna as informacoes do operador logado
	    //OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
	    
	    vo.setItemEntidadeVO(facade.pesquisarEntidade(vo.getItemEntidadeVO().getIdEntidade()));
	    
	    facade.prepararFiltro(vo);
	    helper.setEntidadeVO(vo);
	    
	    return mapping.getInputForward();
	    
	    //return prepararFiltro( mapping,  form,  request,  response);
	   }
	  
	  public ActionForward remover(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	  {
	   CertdigitalForm frm = (CertdigitalForm) form;
	   
	   EntidadeHelper helper = frm.getEntidadeHelper();
	  		
	   EntidadeFacade facade = new EntidadeFacade();
	   // retorna as informacoes do operador logado
	   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		
	   ItemEntidadeVO itemVo = helper.getEntidadeVO().getItemEntidadeVO();
	   
	   helper.setParamItemEntidadeVO(itemVo, operadorVo);
	   facade.remove(itemVo);
	   
	   helper = new EntidadeHelper(); 
	   frm.setEntidadeHelper(helper);
	   super.setMessage("entidade.removido.sucesso",null,request);
	   return mapping.findForward("incluir");
	  }	  
	  
	  public ActionForward atualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	  {
	   CertdigitalForm frm = (CertdigitalForm) form;
	   
	   EntidadeHelper helper = frm.getEntidadeHelper();
		
	   EntidadeFacade facade = new EntidadeFacade();
	   
	   // retorna as informacoes do operador logado
	   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
	   EntidadeVO EntidadeVO = helper.getEntidadeVO();	
	   ItemEntidadeVO itemVo = helper.getEntidadeVO().getItemEntidadeVO();
	   helper.setParamItemEntidadeVO(itemVo, operadorVo);
	   
  	   if (itemVo.getNumCNPJ() != null && itemVo.getNumCNPJ().longValue() > 0) {
			if (facade.verificarCNPJ(itemVo.getNumCNPJ(),itemVo.getIdEntidade()) ) {
				 facade.prepararFiltro(EntidadeVO);
				 throw new CNPJAlreadyExistsException();
			}
		}

	   facade.update(itemVo);
	   
	   facade.prepararFiltro(EntidadeVO);
	   
       super.setMessage("entidade.alterado.sucesso",null,request);
       return mapping.getInputForward();
	  }	  
}
