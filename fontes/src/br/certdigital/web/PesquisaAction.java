package br.certdigital.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.certdigital.facade.PesquisaFacade;
import br.certdigital.shared.security.OperadorInfo;
import br.certdigital.shared.web.CertdigitalForm;
import br.certdigital.view.PesquisaHelper;
import br.certdigital.vo.OperadorVO;

/**
 * Action para as Pesquisas Genericas
 *
 * @author 
 */
public class PesquisaAction extends DispatchAction {
	
	public PesquisaAction(){}

	/**
	 * Prepara tela para pesquisa
     */	
	public ActionForward prepararPesquisa( ActionMapping mapping, 
										   ActionForm form, 
										   HttpServletRequest request, 
										   HttpServletResponse response ) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		PesquisaHelper helper = frm.getPesquisaHelper();
		frm.setMetodo(helper.getMetodoPesquisaExecutar());
		
		return mapping.getInputForward();
    }
										 
	/**
	 * Pesquisa empresas
     */	
	public ActionForward pesquisarEmpresas( ActionMapping mapping, 
										    ActionForm form, 
											HttpServletRequest request, 
											HttpServletResponse response ) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		PesquisaHelper helper = frm.getPesquisaHelper();
		PesquisaFacade facade = new PesquisaFacade();
		
		// retorna as informacoes do operador logado
		OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		
		if (helper.getValorRestricaoOpcional() != null && !"".equals(helper.getValorRestricaoOpcional())) {
			//Long idEntidade = null;
			// se foi passado o id de uma entidade, forca a visualizacao somente das empresas dessa entidade
			if(!helper.getLabelValorRestricaoOpcional().equals("Parceiro")){
				//idEntidade = Long.valueOf(helper.getValorRestricaoOpcional());
				//facade.pesquisarEmpresas(idEntidade,  helper, operadorVo);
			}
		} else {
			// pesquisa os dados de toda as empresas
			facade.pesquisarEmpresas(helper, operadorVo);
		}
		return mapping.getInputForward();
    }

	/**
	 * Pesquisa entidades
     */	
	public ActionForward pesquisarEntidades( ActionMapping mapping, 
										    ActionForm form, 
											HttpServletRequest request, 
											HttpServletResponse response ) throws Exception {
		//CertdigitalForm frm = (CertdigitalForm) form;
		//PesquisaHelper helper = frm.getPesquisaHelper();
		//PesquisaFacade facade = new PesquisaFacade();		
		// retorna as informacoes do operador logado
		//OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		// pesquisa os dados dos gestores
		// facade.pesquisarGestores(operadorVo.getIdUnidadeNegocio(), operadorVo.getIdNivel(), operadorVo.getIdGestor(), helper);
		return mapping.getInputForward();
    }

	/**
	 * Pesquisa operadores
     */	
	public ActionForward pesquisarOperadores( ActionMapping mapping, 
										     ActionForm form, 
										 	 HttpServletRequest request, 
											 HttpServletResponse response ) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		PesquisaHelper pesquisaHelper = frm.getPesquisaHelper();
		PesquisaFacade facade = new PesquisaFacade();
		Long idEmpresa = null;
		if (pesquisaHelper.getValorRestricaoOpcional() != null && !"".equals(pesquisaHelper.getValorRestricaoOpcional())) {
			idEmpresa = Long.valueOf(pesquisaHelper.getValorRestricaoOpcional());
		}
		// retorna as informacoes do operador logado
		OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		// pesquisa os dados dos operadores
		
		facade.pesquisarOperadores(operadorVo.getIdEntidade(),	idEmpresa, pesquisaHelper );
		return mapping.getInputForward();
    }

	/**
	 * Pesquisa operadores
     */	
	public ActionForward pesquisarProdutos( ActionMapping mapping, 
										    ActionForm form, 
										 	HttpServletRequest request, 
											HttpServletResponse response ) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		PesquisaHelper pesquisaHelper = frm.getPesquisaHelper();
		PesquisaFacade facade = new PesquisaFacade();
		Long idEmpresa = null;
		
		if (pesquisaHelper.getValorRestricaoOpcional() != null && !"".equals(pesquisaHelper.getValorRestricaoOpcional())) {
			idEmpresa = Long.valueOf(pesquisaHelper.getValorRestricaoOpcional());
		}
		// retorna as informacoes do operador logado
		OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		// pesquisa os dados dos operadores
		
		facade.pesquisarProdutos(operadorVo.getIdEntidade(), idEmpresa, pesquisaHelper );
		return mapping.getInputForward();
    }
	
}
