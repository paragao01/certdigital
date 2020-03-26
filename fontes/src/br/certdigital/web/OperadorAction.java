package br.certdigital.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.certdigital.facade.OperadorFacade;
import br.certdigital.shared.security.OperadorInfo;
import br.certdigital.shared.web.BaseAction;
import br.certdigital.shared.web.CertdigitalForm;
import br.certdigital.view.LoginHelper;
import br.certdigital.vo.OperadorVO;

/**
 * Action para cadastro de operadores
 */
public class OperadorAction extends BaseAction {
	
	public OperadorAction(){}

	/**
	 * Tela inicial de consulta de operadores
     */	
	public ActionForward prepararPesquisa( ActionMapping mapping, 
										   ActionForm form, 
										   HttpServletRequest request, 
										   HttpServletResponse response ) throws Exception {
		   
		   return mapping.getInputForward();
    }

	/**
	 * Pesquisa os operadores de acordo com o filtro
     */	
	public ActionForward pesquisar( ActionMapping mapping, 
								    ActionForm form, 
									HttpServletRequest request, 
									HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   LoginHelper helper = frm.getLoginHelper();
		   OperadorFacade facade = new OperadorFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   // pesquisa os dados das situacoes
		   facade.pesquisar(helper, operadorVo);
		   saveToken(request);
		   return mapping.findForward("listarOperadores");
    }

	/**
	 * Pesquisa um determinado operador
     */	
	public ActionForward pesquisarOperador( ActionMapping mapping, 
								    		ActionForm form, 
											HttpServletRequest request, 
											HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   LoginHelper helper = frm.getLoginHelper();
		   OperadorFacade facade = new OperadorFacade();	
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   helper.getOperadorVO().setIdTipoOperador(operadorVo.getIdTipoOperador());
		   
		   facade.pesquisarOperador(helper);
		   saveToken(request);
		   return mapping.getInputForward();
    }
	
	/**
	 * Preparara a tela de inclusao de um novo operador 
     */	
	public ActionForward prepararFiltro( ActionMapping mapping, 
								    	 ActionForm form, 
										 HttpServletRequest request, 
										 HttpServletResponse response ) throws Exception {
		   // prepara a tela para inclusao de uma nova empresa
		   CertdigitalForm frm = (CertdigitalForm) form;
		   LoginHelper helper = frm.getLoginHelper();
		   OperadorFacade facade = new OperadorFacade();	
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   // pesquisa os dados das situacoes
		   helper.getOperadorVO().setIdTipoOperador(operadorVo.getIdTipoOperador());
		   facade.prepararInclusao(helper);
		   saveToken(request);
		   return mapping.getInputForward();
    }
	
	/**
	 * Insere um novo operador
	 */
	public ActionForward inserir( ActionMapping mapping,
								  ActionForm form, 
								  HttpServletRequest request, 
								  HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   
		   if (!isTokenValid(request)) {
		   		return prepararFiltro(mapping, form, request, response);
		   }
		   LoginHelper helper = frm.getLoginHelper();
		   OperadorFacade facade = new OperadorFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   // vo a ser incluido
		   OperadorVO itemVo = helper.getOperadorVO();
		   itemVo.setIdOperadorInclusao(operadorVo.getIdOperador());
		   
		   if (itemVo.getIdTipoOperador().longValue()==3) {
	   			return definirRegrasAcesso(mapping, form, request, response);
		   }else{ // se idTipoOperador = 1 ou = 2
		   		itemVo.setIdEntidade(operadorVo.getIdEntidade());
		   		facade.insert(itemVo);
		   		super.setMessage("operador.incluido.sucesso",null,request);
				resetToken(request);
				saveToken(request);
				return mapping.findForward("sucesso");
		   }
	}

	/**
	 * Atualiza os dados de um operador
	 */
	public ActionForward atualizar( ActionMapping mapping, 
									ActionForm form, 
									HttpServletRequest request, 
									HttpServletResponse response ) throws Exception {

		   CertdigitalForm frm = (CertdigitalForm) form;
		   if (!isTokenValid(request)) {
		   		return mapping.findForward("incluir");
		   }
		   LoginHelper helper = frm.getLoginHelper();
		   OperadorFacade facade = new OperadorFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   // vo a ser incluido
		   OperadorVO itemVo = helper.getOperadorVO();
		   itemVo.setIdOperadorInclusao(operadorVo.getIdOperador());
		   
		   //helper.parseFields(helper);
		   if (itemVo.getIdTipoOperador().longValue()==3) { //Adminstrativo
			    facade.update(itemVo);
		   		return definirRegrasAcesso(mapping, form, request, response);
		   }else { // se idTipoOperador 1 ou 2
		   		//Apagar dados da tabela de acesso para este operador
		   		facade.update(itemVo);
		   		super.setMessage("operador.atualizado.sucesso",null,request);
				resetToken(request);		
				saveToken(request);
				return mapping.findForward("sucesso");
		   }
	}

	/**
	 * Leva para a tela de definicao de regras de acesso para o operador
	 */
	public ActionForward definirRegrasAcesso( ActionMapping mapping, 
											  ActionForm form, 
											  HttpServletRequest request, 
											  HttpServletResponse response ) throws Exception {

		   //GarantiaForm frm = (GarantiaForm) form;

		   //LoginHelper helper = frm.getLoginHelper();
		   //OperadorFacade facade = new OperadorFacade();

		   //OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);

		   //OperadorVO itemVo = helper.getOperadorVO();

		   saveToken(request);
		   
		   return mapping.findForward("definirRegrasAcesso");

	}
	
	/**
	 * Define regras de acesso para o operador
	 */
	public ActionForward sucessoDefinirRegrasAcesso( ActionMapping mapping, 
											  ActionForm form, 
											  HttpServletRequest request, 
											  HttpServletResponse response ) throws Exception {

		   CertdigitalForm frm = (CertdigitalForm) form;
   
		   LoginHelper helper = frm.getLoginHelper();
		   OperadorFacade facade = new OperadorFacade();
		   
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   OperadorVO itemVo = helper.getOperadorVO();
		   
		   itemVo.setIdEntidade(operadorVo.getIdEntidade());
		   itemVo.setIdOperadorInclusao(operadorVo.getIdOperador());
		   
		   facade.defineAcesso(itemVo);
		   
		   super.setMessage("operador.incluido.sucesso", null, request);
		   resetToken(request);		
		   saveToken(request);
		   return mapping.findForward("sucesso");
	}
	
	/**
	 * Altera regras de acesso para o operador
	 */
	public ActionForward sucessoManutencaoRegrasAcesso( ActionMapping mapping, 
											  ActionForm form, 
											  HttpServletRequest request, 
											  HttpServletResponse response ) throws Exception {

		   CertdigitalForm frm = (CertdigitalForm) form;
   
		   LoginHelper helper = frm.getLoginHelper();
		   OperadorFacade facade = new OperadorFacade();
		   OperadorVO itemVo = helper.getOperadorVO();
		   
		   facade.alteraAcesso(itemVo);
		   
		   super.setMessage("operador.atualizado.sucesso",null,request);
		   resetToken(request);		
		   saveToken(request);
		   return mapping.findForward("sucesso");
	}
}
