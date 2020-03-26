package br.certdigital.facade;


import br.certdigital.dao.OperadorDAO;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.shared.util.GlobalConstants;
import br.certdigital.tools.database.BaseFacade;
import br.certdigital.view.LoginHelper;
import br.certdigital.vo.OperadorVO;

/**
 * Business Facade do cadastro de operadores
 */
public class OperadorFacade extends BaseFacade {

    /**
     * Prepara tela para inclusao
     * 
     * @param idUnidadeNegocio
     * @param helper
     * @throws CertdigitalException
     */
    public void prepararInclusao(LoginHelper helper) throws CertdigitalException {
    	OperadorDAO dao = new OperadorDAO();
    	Long idTipoOperador = helper.getOperadorVO().getIdTipoOperador();
        try {
        	helper.setListaTipoOperadorVO( dao.pesquisarTipoOperador(idTipoOperador) );
        	helper.setOperadorVO(new OperadorVO());
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Pesquisa todos os operadores
     * 
     * @param idUnidadeNegocio
     * @param helper
     * @throws CertdigitalException
     */
    public void pesquisar(LoginHelper helper, OperadorVO operadorVo) throws CertdigitalException {
    	OperadorDAO dao = new OperadorDAO();
        try {
        	helper.setListaOperadorVO( dao.pesquisar(operadorVo.getIdEntidade(), 
									   				 helper.getOperadorVO().getIdEmpresa(), 
									   				 helper.getOperadorVO().getIdOperador(),
									   				 helper.getOperadorVO().getStatusOperador(),
									   				 operadorVo) );
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Pesquisa um determinado operador
     * 
     * @param idUnidadeNegocio
     * @param helper
     * @throws CertdigitalException
     */
    public void pesquisarOperador(LoginHelper helper) throws CertdigitalException {
    	OperadorDAO dao = new OperadorDAO();
    	Long idTipoOperador = helper.getOperadorVO().getIdTipoOperador();
        try {
        	helper.setOperadorVO( dao.pesquisarOperador(helper.getOperadorVO().getIdOperador()) );
        	helper.setOperadorVO( dao.pesquisarOperadorMenus(helper.getOperadorVO().getIdOperador()) );
        	helper.setListaTipoOperadorVO( dao.pesquisarTipoOperador(idTipoOperador) );
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
    /**
     * Insere um operador
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void insert(OperadorVO vo) throws CertdigitalException {
    	OperadorDAO dao = new OperadorDAO();
        try {
        	vo.setStatusOperador(GlobalConstants.STATUS_OPERADOR_ATIVO);
        	dao.insert(vo);
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Altera um operador
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void update(OperadorVO vo) throws CertdigitalException {
    	OperadorDAO dao = new OperadorDAO();
        try {
        	dao.update(vo);
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
    /**
     * Define regras de acesso para o Operador
     * 
     * @param vo
     */
    public void defineAcesso(OperadorVO vo) throws CertdigitalException {
    	
    	OperadorDAO dao = new OperadorDAO();
    	
    	vo.setStatusOperador(GlobalConstants.STATUS_OPERADOR_ATIVO);
    	
    	if(vo.getIdTipoOperador().longValue() == 1) { // Operador Master
    		vo.getMenuGerencialVO().setAllTrue();
    		vo.getMenuOperacionalVO().setAllTrue();
    	}
    	else if(vo.getIdTipoOperador().longValue() == 2) { // Operador Empresa
    		vo.getMenuGerencialVO().setAllFalse();
    		vo.getMenuOperacionalVO().setAllTrue();
    	}
    	
    	try {
    		dao.defineAcesso(vo);
    	}
    	catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
    /**
     * Altera regras de acesso para o Operador
     * 
     * @param vo
     */
    public void alteraAcesso(OperadorVO vo) throws CertdigitalException {
    	
    	OperadorDAO dao = new OperadorDAO();
    	
    	vo.setStatusOperador(GlobalConstants.STATUS_OPERADOR_ATIVO);
    	
    	try {
    		dao.alteraAcesso(vo);
    	}
    	catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
}
