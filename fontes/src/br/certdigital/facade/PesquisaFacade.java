package br.certdigital.facade;

import br.certdigital.dao.PesquisaDAO;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.tools.database.BaseFacade;
import br.certdigital.view.PesquisaHelper;
import br.certdigital.vo.OperadorVO;

/**
 * Business facade para Pesquisas
 * 
 * @author 
 */
public class PesquisaFacade extends BaseFacade {
	
    /**
     * Pesquisa todas as empresas de uma unidade de negocio
     * 
     * @param idUnidadeNegocio
     * @param tipoVisualizacao
     * @param idGestor
     * @param helper
     * @throws CertdigitalException
     */
    public void pesquisarEmpresas(PesquisaHelper helper, OperadorVO operadorVo) throws CertdigitalException {
    	PesquisaDAO dao = new PesquisaDAO();
        try {
       		helper.setListaItemPesquisaVO(dao.pesquisarEmpresas(helper.getChavePesquisa(), operadorVo) );
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Pesquisa todos os operadores de uma unidade de negocio
     * 
     * @param idUnidadeNegocio
     * @param tipoVisualizacao
     * @param idGestor
     * @param idEmpresa
     * @param helper
     * @throws CertdigitalException
     */
    public void pesquisarOperadores(Long idEntidade, Long idEmpresa, PesquisaHelper helper) throws CertdigitalException {
    	PesquisaDAO dao = new PesquisaDAO();
        try {
        	helper.setListaItemPesquisaVO(dao.pesquisarOperadores(idEntidade, idEmpresa, helper.getChavePesquisa()));
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Pesquisa todos os produtos de uma entidade/empresa
     * 
     * @param idUnidadeNegocio
     * @param tipoVisualizacao
     * @param idGestor
     * @param idEmpresa
     * @param helper
     * @throws CertdigitalException
     */
    public void pesquisarProdutos(Long idEntidade, Long idEmpresa, PesquisaHelper helper) throws CertdigitalException {
    	PesquisaDAO dao = new PesquisaDAO();
        try {
        	helper.setListaItemPesquisaVO(dao.pesquisarProdutos(idEntidade, idEmpresa, helper.getChavePesquisa()));
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
}
