package br.certdigital.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.certdigital.facade.ConsultarFacade;
import br.certdigital.shared.security.OperadorInfo;
import br.certdigital.shared.web.CertdigitalForm;
import br.certdigital.view.ConsultarHelper;
import br.certdigital.view.PesquisaHelper;
import br.certdigital.vo.OperadorVO;

/**
 * Action para as Consultas Genericas
 *
 * @author 
 */
public class ConsultarAction extends DispatchAction {
	
	public ConsultarAction(){}

	/**
	 * Prepara tela para pesquisa
     */	
	public ActionForward prepararConsulta( ActionMapping mapping, 
										   ActionForm form, 
										   HttpServletRequest request, 
										   HttpServletResponse response ) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		PesquisaHelper helper = frm.getPesquisaHelper();
		frm.setMetodo(helper.getMetodoPesquisaExecutar());
		
		return mapping.getInputForward();
    }
	
	/**
	 * Consultar Certificado
     */	
	public ActionForward consultarCertificado( ActionMapping mapping, 
										       ActionForm form, 
										       HttpServletRequest request, 
										       HttpServletResponse response ) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		ConsultarHelper helper = frm.getConsultarHelper();
		ConsultarFacade facade = new ConsultarFacade();
		
		// retorna as informacoes do operador logado
		OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		
		// pesquisa os dados de toda as empresas
		facade.consultarCertificado(helper, operadorVo);
		
		//Formato os campos para print na tela
		helper.formatFields();
		
		return mapping.findForward("listarConsultarCertificado");
    }
	
	/**
	 * ImprimirConsultar Certificado
     */	
	public ActionForward imprimirConsultarCertificado( ActionMapping mapping, 
										       ActionForm form, 
										       HttpServletRequest request, 
										       HttpServletResponse response ) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		ConsultarHelper helper = frm.getConsultarHelper();
		ConsultarFacade facade = new ConsultarFacade();
		
		helper.setMes(Integer.parseInt(request.getParameter("mes")));
		helper.setAno(Integer.parseInt(request.getParameter("ano")));
		
		// retorna as informacoes do operador logado
		OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);

		// pesquisa os dados de toda as empresas
		facade.consultarCertificado(helper, operadorVo);
		
		//Formato os campos para print na tela
		helper.formatFields();
		
		return mapping.getInputForward();
    }

	
	/**
	 * Consultar Tabela de Preco
     */	
	public ActionForward consultarTabelaPreco( ActionMapping mapping, 
										       ActionForm form, 
										       HttpServletRequest request, 
										       HttpServletResponse response ) throws Exception {
		CertdigitalForm frm = (CertdigitalForm) form;
		ConsultarHelper helper = frm.getConsultarHelper();
		ConsultarFacade facade = new ConsultarFacade();
		
		// retorna as informacoes do operador logado
		OperadorVO operadorVo = OperadorInfo.getOperadorInfo(request);
		
		// pesquisa os dados de toda as empresas
		facade.consultarTabelaPreco(helper, operadorVo);

		return mapping.getInputForward();
    }
	
}
