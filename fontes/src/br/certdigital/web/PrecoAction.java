package br.certdigital.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.certdigital.facade.PrecoFacade;
import br.certdigital.shared.security.OperadorInfo;
import br.certdigital.shared.web.BaseAction;
import br.certdigital.shared.web.CertdigitalForm;
import br.certdigital.view.PrecoHelper;
import br.certdigital.vo.OperadorVO;
import br.certdigital.vo.PrecoVO;

/**
 * Action para cadastro de precos
 */
public class PrecoAction extends BaseAction {
	
	public PrecoAction(){}

	/**
	 * Tela inicial de consulta de precos
     */	
	public ActionForward prepararPesquisa( ActionMapping mapping, 
										   ActionForm form, 
										   HttpServletRequest request, 
										   HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   // Limpa o objeto do formulario
		   frm.setPrecoHelper(new PrecoHelper());

		   resetToken(request);
		   saveToken(request);
		   return mapping.getInputForward();
    }

	/**
	 * Pesquisa os precos de acordo com o filtro
     */	
	public ActionForward pesquisar( ActionMapping mapping, 
								    ActionForm form, 
									HttpServletRequest request, 
									HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   PrecoHelper helper = frm.getPrecoHelper();
		   PrecoFacade facade = new PrecoFacade();
		   		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);

		   // pesquisa os dados
		   facade.pesquisar(helper, operadorVo);
		   
		   helper.formatFields();
		   
		   frm.setPrecoHelper(helper);
		   
		   saveToken(request);
		   return mapping.findForward("listarPreco");
    }

	/**
	 * Pesquisa um determinado preco
     */	
	public ActionForward pesquisarPreco( ActionMapping mapping, 
								    	 ActionForm form, 
										 HttpServletRequest request, 
										 HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   PrecoHelper helper = frm.getPrecoHelper();
		   PrecoFacade facade = new PrecoFacade();	
		   
		   frm.setPrecoHelper(facade.pesquisarPreco(helper));
		   
		   helper.formatFields();
		   
		   frm.setPrecoHelper(helper);

		   saveToken(request);
		   return mapping.getInputForward();
    }
	
	/**
	 * Preparara a tela de inclusao de um novo preco 
     */	
	public ActionForward prepararFiltro( ActionMapping mapping, 
								    	 ActionForm form, 
										 HttpServletRequest request, 
										 HttpServletResponse response ) throws Exception {
		   // prepara a tela para inclusao de uma nova empresa
		   CertdigitalForm frm = (CertdigitalForm) form;
		   PrecoHelper helper = frm.getPrecoHelper();
		   helper.getPrecoVO().getIdEntidade();// So para tirar o warn;
		   
		   saveToken(request);
		   return mapping.getInputForward();
    }
	
	/**
	 * Insere um novo preco
	 */
	public ActionForward inserir( ActionMapping mapping,
								  ActionForm form, 
								  HttpServletRequest request, 
								  HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   
		   if (!isTokenValid(request)) {
		   		return prepararFiltro(mapping, form, request, response);
		   }
		   PrecoHelper helper = frm.getPrecoHelper();
		   PrecoFacade facade = new PrecoFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   // vo a ser incluido
		   PrecoVO itemVo = helper.getPrecoVO();
		   itemVo.setIdOperadorInclusao(operadorVo.getIdOperador());
		   		  
		   helper.parseFields();		   
		   facade.insert(itemVo);
		   
		   super.setMessage("preco.incluido.sucesso",null,request);
		   resetToken(request);
		   saveToken(request);
		   return mapping.findForward("sucesso");
	}

	/**
	 * Atualiza os dados de um preco
	 */
	public ActionForward atualizar( ActionMapping mapping, 
									ActionForm form, 
									HttpServletRequest request, 
									HttpServletResponse response ) throws Exception {

		   CertdigitalForm frm = (CertdigitalForm) form;
		   PrecoHelper helper = frm.getPrecoHelper();
		   PrecoFacade facade = new PrecoFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   // vo a ser alterado
		   PrecoVO itemVo = helper.getPrecoVO();
		   itemVo.setIdOperadorInclusao(operadorVo.getIdOperador());
		   
		   helper.parseFields();
		   
		   facade.update(itemVo);
		   
		   super.setMessage("preco.alterado.sucesso",null,request);
		   resetToken(request);		
		   saveToken(request);
		   
		   return mapping.findForward("sucesso");
	}
	
	/**
	 * Remove um preco
	 */
	public ActionForward remover(ActionMapping mapping, 
								 ActionForm form, HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		PrecoHelper helper = frm.getPrecoHelper();
		PrecoFacade facade = new PrecoFacade();
		
		PrecoVO itemVo = helper.getPrecoVO();

		facade.remove(itemVo);

		frm.setPrecoHelper(new PrecoHelper());
		super.setMessage("preco.removido.sucesso", null, request);
		return mapping.findForward("incluir");
	}

}
