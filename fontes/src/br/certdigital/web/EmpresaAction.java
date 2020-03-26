package br.certdigital.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.certdigital.exception.CNPJAlreadyExistsException;
import br.certdigital.facade.EmpresaFacade;
import br.certdigital.facade.EstadoFacade;
import br.certdigital.shared.security.OperadorInfo;
import br.certdigital.shared.web.BaseAction;
//import br.certdigital.facade.EstadoFacade;
//import br.certdigital.facade.MeioAcessoEmpresaFacade;
//import br.certdigital.facade.ParceiroFacade;
//import br.certdigital.facade.SegmentoFacade;
//import br.certdigital.shared.security.OperadorInfo;
//import br.certdigital.shared.web.BaseAction;
import br.certdigital.shared.web.CertdigitalForm;
import br.certdigital.view.EmpresaHelper;
import br.certdigital.view.EstadoHelper;
//import br.certdigital.view.EstadoHelper;
//import br.certdigital.view.ParceiroHelper;
//import br.certdigital.view.SegmentoHelper;
import br.certdigital.vo.EmpresaVO;
import br.certdigital.vo.OperadorVO;

/**
 * Action para as Pesquisas
 *
 * @author 
 */
public class EmpresaAction extends BaseAction {
	
	public EmpresaAction(){}

	/**
	 * Tela inicial de consulta de empresas
     */	
	public ActionForward prepararPesquisa( ActionMapping mapping, 
										   ActionForm form, 
										   HttpServletRequest request, 
										   HttpServletResponse response ) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		// Limpa o objeto do formulario
		frm.setEmpresaHelper(new EmpresaHelper());

		EstadoHelper estadoHelper = frm.getEstadoHelper();
		EstadoFacade estadoFacade = new EstadoFacade();
		   
		// pesquisa os estados
		estadoFacade.pesquisar(estadoHelper);
		
	    return mapping.getInputForward();
    }

	/**
	 * Pesquisa as empresas de acordo com o filtro
     */	
	public ActionForward pesquisar( ActionMapping mapping, 
								    ActionForm form, 
									HttpServletRequest request, 
									HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   EmpresaHelper helper = frm.getEmpresaHelper();
		   EmpresaFacade facade = new EmpresaFacade();		
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   //pesquisa os dados das empresas
		   facade.pesquisar(operadorVo.getIdEntidade(), helper, operadorVo);
		   return mapping.findForward("listarEmpresas");
    }

	/**
	 * Seta os dados necessarios para compor a tela de inclusao/alteracao de empresas
	 * 
	 * @param form
	 * @param request
	 */
	private void pesquisaDadosTela(ActionForm form, HttpServletRequest request) throws Exception{
		   CertdigitalForm frm = (CertdigitalForm) form;
		   EmpresaHelper empresaHelper = frm.getEmpresaHelper();
		   EstadoHelper estadoHelper = frm.getEstadoHelper();
		   EmpresaFacade empresaFacade = new EmpresaFacade();
		   EstadoFacade estadoFacade = new EstadoFacade();
		   
		   // pesquisa os dados da empresa 
		   empresaFacade.pesquisarDetalhesEmpresa(empresaHelper.getEmpresaVO().getIdEntidade(), empresaHelper);
		   
		   // pesquisa os estados
		   estadoFacade.pesquisar(estadoHelper);
		   
		   // formata os campos para exibicao
		   empresaHelper.formatFields();
	}
	
	/**
	 * Preparara a tela de inclusao de uma nova empresa 
     */	
	public ActionForward prepararFiltro( ActionMapping mapping, 
								    	 ActionForm form, 
										 HttpServletRequest request, 
										 HttpServletResponse response ) throws Exception {
		   // prepara a tela para inclusao de uma nova empresa
		   pesquisaDadosTela(form, request);
		   saveToken(request);
		   return mapping.getInputForward();
    }
		
	/**
	 * Insere uma nova empresa
	 */
	public ActionForward inserir( ActionMapping mapping, 
								  ActionForm form, 
								  HttpServletRequest request, 
								  HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   if (!isTokenValid(request)) {
   				return prepararFiltro(mapping, form, request, response);
		   }  
		   EmpresaHelper helper = frm.getEmpresaHelper();
		   helper.parseFields();
		   EmpresaFacade facade = new EmpresaFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVO = OperadorInfo.getOperadorInfo(request);
		   
		   EmpresaVO empresaVO = helper.getEmpresaVO();
		   helper.setParamEmpresaVO(empresaVO, operadorVO);
		   empresaVO.setIdEntidade(operadorVO.getIdEntidade());
		   		   
		   try {
			   facade.insert(empresaVO);
		   } catch (CNPJAlreadyExistsException e) {
			   super.setMessage("cnpj.ja.cadastrado",null,request);	   
			   return prepararFiltro(mapping, form, request, response);		   		
		   }
		   super.setMessage("empresa.incluida.sucesso",null,request);	   
		   resetToken(request);
		   saveToken(request);
		   return mapping.findForward("sucesso");
	}

	/**
	 * Atualiza os dados de uma empresa
	 */
	public ActionForward atualizar( ActionMapping mapping, 
									ActionForm form, 
									HttpServletRequest request, 
									HttpServletResponse response ) throws Exception {
			CertdigitalForm frm = (CertdigitalForm) form;
			if (!isTokenValid(request)) {
	   			return prepararFiltro(mapping, form, request, response);
		    }
			EmpresaHelper helper = frm.getEmpresaHelper();
			helper.parseFields();
			EmpresaFacade facade = new EmpresaFacade();
			// retorna as informacoes do operador logado
			OperadorVO operadorVO = OperadorInfo.getOperadorInfo(request);
			//Atualizo as modificacoes da tela.			
			EmpresaVO empresaVO = helper.getEmpresaVO();
			helper.setParamEmpresaVO(empresaVO, operadorVO);
			try {
		   		facade.update(empresaVO);
		    } catch (CNPJAlreadyExistsException e) {
			    super.setMessage("cnpj.ja.cadastrado",null,request);	   
			    return prepararFiltro(mapping, form, request, response);		   		
		    }
			resetToken(request);
			saveToken(request);
			super.setMessage("empresa.alterada.sucesso",null,request);	   
			return prepararFiltro(mapping, form, request, response);
	}

	/*
	 * Remove os dados de uma empresa
	 */
	public ActionForward remover( ActionMapping mapping, 
								  ActionForm form, 
								  HttpServletRequest request, 
								  HttpServletResponse response ) throws Exception {
			CertdigitalForm frm = (CertdigitalForm) form;
			if (!isTokenValid(request)) {
	   			return mapping.findForward("incluir");
		    }
			EmpresaHelper helper = frm.getEmpresaHelper();
			helper.parseFields();
			EmpresaFacade facade = new EmpresaFacade();
			// retorna as informacoes do operador logado
			OperadorVO operadorVO = OperadorInfo.getOperadorInfo(request);
			EmpresaVO empresaVO = helper.getEmpresaVO();			
			helper.setParamEmpresaVO(empresaVO, operadorVO);
			//imprimeDadosVO(empresaVO);
			facade.remove(empresaVO);
			
			helper.setEmpresaVO(new EmpresaVO());
			
			resetToken(request);
			saveToken(request);
			super.setMessage("empresa.removida.sucesso",null,request);
			return mapping.findForward("incluir");
			//return prepararFiltro(mapping, form, request, response);
	}
	
}	
