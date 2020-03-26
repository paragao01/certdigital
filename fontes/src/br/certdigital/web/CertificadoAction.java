package br.certdigital.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.certdigital.exception.CNPJAlreadyExistsException;
import br.certdigital.facade.CertificadoFacade;
import br.certdigital.facade.EstadoFacade;
import br.certdigital.facade.ProdutoFacade;
import br.certdigital.shared.security.OperadorInfo;
import br.certdigital.shared.web.BaseAction;
import br.certdigital.shared.web.CertdigitalForm;
import br.certdigital.view.CertificadoHelper;
import br.certdigital.view.EstadoHelper;
import br.certdigital.view.ProdutoHelper;
import br.certdigital.vo.CertificadoVO;
import br.certdigital.vo.OperadorVO;

/**
 * Action para as Pesquisas
 *
 * @author 
 */
public class CertificadoAction extends BaseAction {
	
	public CertificadoAction(){}

	/**
	 * Tela inicial de consulta de empresas
     */	
	public ActionForward prepararPesquisa( ActionMapping mapping, 
										   ActionForm form, 
										   HttpServletRequest request, 
										   HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   // Limpa o objeto do formulario
		   frm.setCertificadoHelper(new CertificadoHelper());
		   EstadoHelper estadoHelper = frm.getEstadoHelper();
		   ProdutoHelper produtoHelper = frm.getProdutoHelper();
		   EstadoFacade estadoFacade = new EstadoFacade();
		   ProdutoFacade produtoFacade = new ProdutoFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   // pesquisa os estados
		   estadoFacade.pesquisar(estadoHelper);
		   
		   // pesquisa os produtos
		   produtoFacade.pesquisar(produtoHelper, operadorVo);

		   resetToken(request);
		   saveToken(request);	
		   
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
		   CertificadoHelper helper = frm.getCertificadoHelper();
		   CertificadoFacade facade = new CertificadoFacade();	
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   helper.parseFields();
		   
		   //pesquisa os dados das empresas
		   facade.pesquisar( helper, operadorVo);
		   		   
		   helper.formatFields();
		   
		   return mapping.findForward("listarCertificado");
    }

	/**
	 * Seta os dados necessarios para compor a tela de inclusao/alteracao do Certificado
	 * 
	 * @param form
	 * @param request
	 */
	private void pesquisaDadosTela(ActionForm form, HttpServletRequest request) throws Exception{
		   CertdigitalForm frm = (CertdigitalForm) form;
		   CertificadoHelper helper = frm.getCertificadoHelper();
		   CertificadoFacade facade = new CertificadoFacade();	
		   EstadoHelper estadoHelper = frm.getEstadoHelper();
		   ProdutoHelper produtoHelper = frm.getProdutoHelper();
		   EstadoFacade estadoFacade = new EstadoFacade();
		   ProdutoFacade produtoFacade = new ProdutoFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   // pesquisa os dados do certificado 
		   if(helper.getCertificadoVO().getIdCertificado() != null) {
			   facade.pesquisarCertificado(helper, operadorVo);
		   }
		   // pesquisa os estados
		   estadoFacade.pesquisar(estadoHelper);
		   
		   // pesquisa os produtos
		   produtoFacade.pesquisar(produtoHelper, operadorVo);
		   
		   // formata os campos para exibicao
		   helper.formatFields();
	}
	
	/**
	 * Preparara a tela de inclusao de um novo certificado
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
	 * Insere um novovo certificado
	 */
	public ActionForward inserir( ActionMapping mapping, 
								  ActionForm form, 
								  HttpServletRequest request, 
								  HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   CertificadoHelper helper = frm.getCertificadoHelper();
		   CertificadoFacade facade = new CertificadoFacade();
		   helper.parseFields();

		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   CertificadoVO certificadoVO = helper.getCertificadoVO();
		   		   
		   try {
			   facade.insert(certificadoVO, operadorVo);
		   } catch (CNPJAlreadyExistsException e) {
			   super.setMessage("cnpj.ja.cadastrado",null,request);	   
			   return prepararFiltro(mapping, form, request, response);		   		
		   }
		   super.setMessage("certificado.incluido.sucesso",null,request);	   
		   resetToken(request);
		   saveToken(request);
		   return mapping.findForward("sucesso");
	}

	/**
	 * Atualiza os dados de um certificado
	 */
	public ActionForward atualizar( ActionMapping mapping, 
									ActionForm form, 
									HttpServletRequest request, 
									HttpServletResponse response ) throws Exception {
			CertdigitalForm frm = (CertdigitalForm) form;
			if (!isTokenValid(request)) {
	   			return prepararFiltro(mapping, form, request, response);
		    }
			CertificadoHelper helper = frm.getCertificadoHelper();
			helper.parseFields();
			CertificadoFacade facade = new CertificadoFacade();
			
			// retorna as informacoes do operador logado
			OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
			
			//Atualizo as modificacoes da tela.			
			CertificadoVO certificadoVO = helper.getCertificadoVO();
			try {
		   		facade.update(certificadoVO, operadorVo);
		    } catch (CNPJAlreadyExistsException e) {
			    super.setMessage("cnpj.ja.cadastrado",null,request);	   
			    return prepararFiltro(mapping, form, request, response);		   		
		    }
			resetToken(request);
			saveToken(request);
			super.setMessage("certificado.alterado.sucesso",null,request);
			return mapping.findForward("sucesso");
	}

	/*
	 * Remove os dados de um certificado
	 */
	public ActionForward remover( ActionMapping mapping, 
								  ActionForm form, 
								  HttpServletRequest request, 
								  HttpServletResponse response ) throws Exception {
			CertdigitalForm frm = (CertdigitalForm) form;
			CertificadoHelper helper = frm.getCertificadoHelper();
			helper.parseFields();
			CertificadoFacade facade = new CertificadoFacade();
			
			// retorna as informacoes do operador logado
			OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
			
			CertificadoVO certificadoVO = helper.getCertificadoVO();
			
			facade.remove(certificadoVO, operadorVo);
			
			helper.setCertificadoVO(new CertificadoVO());
			
			resetToken(request);
			saveToken(request);
			super.setMessage("certificado.removido.sucesso",null,request);
			return mapping.findForward("incluir");
	}
	
}	
