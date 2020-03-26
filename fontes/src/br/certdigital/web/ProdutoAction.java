package br.certdigital.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.certdigital.facade.ProdutoFacade;
import br.certdigital.shared.security.OperadorInfo;
import br.certdigital.shared.web.BaseAction;
import br.certdigital.shared.web.CertdigitalForm;
import br.certdigital.view.ProdutoHelper;
import br.certdigital.vo.OperadorVO;
import br.certdigital.vo.ProdutoVO;

/**
 * Action para cadastro de produtos
 */
public class ProdutoAction extends BaseAction {
	
	public ProdutoAction(){}

	/**
	 * Tela inicial de consulta de produtos
     */	
	public ActionForward prepararPesquisa( ActionMapping mapping, 
										   ActionForm form, 
										   HttpServletRequest request, 
										   HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   // Limpa o objeto do formulario
		   frm.setProdutoHelper(new ProdutoHelper());

		   resetToken(request);
		   saveToken(request);	
		   
		   return mapping.getInputForward();
    }

	/**
	 * Pesquisa os produtos de acordo com o filtro
     */	
	public ActionForward pesquisar( ActionMapping mapping, 
								    ActionForm form, 
									HttpServletRequest request, 
									HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   ProdutoHelper helper = frm.getProdutoHelper();
		   ProdutoFacade facade = new ProdutoFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   		   
		   // pesquisa os dados das situacoes
		   facade.pesquisar(helper, operadorVo);
		   saveToken(request);
		   return mapping.findForward("listarProduto");
    }

	/**
	 * Pesquisa um determinado produto
     */	
	public ActionForward pesquisarProduto( 	ActionMapping mapping, 
								    		ActionForm form, 
											HttpServletRequest request, 
											HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   ProdutoHelper helper = frm.getProdutoHelper();
		   ProdutoFacade facade = new ProdutoFacade();	
		   
		   frm.setProdutoHelper(facade.pesquisarProduto(helper));
		   
		   saveToken(request);
		   return mapping.getInputForward();
    }
	
	/**
	 * Preparara a tela de inclusao de um novo produto 
     */	
	public ActionForward prepararFiltro( ActionMapping mapping, 
								    	 ActionForm form, 
										 HttpServletRequest request, 
										 HttpServletResponse response ) throws Exception {
		   // prepara a tela para inclusao de uma nova empresa
		   CertdigitalForm frm = (CertdigitalForm) form;
		   ProdutoHelper helper = frm.getProdutoHelper();
		   helper.getProdutoVO().getIdEntidade();// So para tirar o warn;
		   
		   saveToken(request);
		   return mapping.getInputForward();
    }
	
	/**
	 * Insere um novo produto
	 */
	public ActionForward inserir( ActionMapping mapping,
								  ActionForm form, 
								  HttpServletRequest request, 
								  HttpServletResponse response ) throws Exception {
		   CertdigitalForm frm = (CertdigitalForm) form;
		   
		   if (!isTokenValid(request)) {
		   		return prepararFiltro(mapping, form, request, response);
		   }
		   ProdutoHelper helper = frm.getProdutoHelper();
		   ProdutoFacade facade = new ProdutoFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   // vo a ser incluido
		   ProdutoVO itemVo = helper.getProdutoVO();
		   itemVo.setIdOperadorInclusao(operadorVo.getIdOperador());
		   		   
		   facade.insert(itemVo);
		   
		   super.setMessage("produto.incluido.sucesso",null,request);
		   resetToken(request);
		   saveToken(request);
		   return mapping.findForward("sucesso");
	}

	/**
	 * Atualiza os dados de um produto
	 */
	public ActionForward atualizar( ActionMapping mapping, 
									ActionForm form, 
									HttpServletRequest request, 
									HttpServletResponse response ) throws Exception {

		   CertdigitalForm frm = (CertdigitalForm) form;
		   ProdutoHelper helper = frm.getProdutoHelper();
		   ProdutoFacade facade = new ProdutoFacade();
		   
		   // retorna as informacoes do operador logado
		   OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		   
		   // vo a ser alterado
		   ProdutoVO itemVo = helper.getProdutoVO();
		   itemVo.setIdOperadorInclusao(operadorVo.getIdOperador());
		   
		   facade.update(itemVo);
		   
		   super.setMessage("produto.alterado.sucesso",null,request);
		   resetToken(request);		
		   saveToken(request);
		   
		   return mapping.findForward("sucesso");
	}
	
	/**
	 * Remove um produto
	 */
	public ActionForward remover(ActionMapping mapping, 
								 ActionForm form, HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		ProdutoHelper helper = frm.getProdutoHelper();
		ProdutoFacade facade = new ProdutoFacade();
		
		ProdutoVO itemVo = helper.getProdutoVO();

		facade.remove(itemVo);

		frm.setProdutoHelper(new ProdutoHelper());
		resetToken(request);
		saveToken(request);		
		super.setMessage("produto.removido.sucesso", null, request);
		return mapping.findForward("incluir");
	}

}
