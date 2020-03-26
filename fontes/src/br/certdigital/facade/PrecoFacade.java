package br.certdigital.facade;


import br.certdigital.dao.PrecoDAO;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.tools.database.BaseFacade;
import br.certdigital.view.PrecoHelper;
import br.certdigital.vo.OperadorVO;
import br.certdigital.vo.PrecoVO;

/**
 * Business Facade do cadastro de preco
 */
public class PrecoFacade extends BaseFacade {

    /**
     * Pesquisa todos os precos
     * 
     * @param idUnidadeNegocio
     * @param helper
     * @throws CertdigitalException
     */
    public void pesquisar(PrecoHelper helper, OperadorVO operadorVo) throws CertdigitalException {
    	PrecoDAO dao = new PrecoDAO();
        try {
        	helper.setListaPrecoVO( dao.pesquisar(helper, operadorVo) );
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Pesquisa um determinado preco
     * 
     * @param idUnidadeNegocio
     * @param helper
     * @throws CertdigitalException
     */
    public PrecoHelper pesquisarPreco(PrecoHelper helper) throws CertdigitalException {
    	PrecoDAO dao = new PrecoDAO();
        try {
        	helper.setPrecoVO( dao.pesquisarPreco(helper) );        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
        return helper;
    }
    
    /**
     * Insere um preco
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void insert(PrecoVO vo) throws CertdigitalException {
    	PrecoDAO dao = new PrecoDAO();
        try {
        	dao.insert(vo);
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Altera um preco
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void update(PrecoVO vo) throws CertdigitalException {
    	PrecoDAO dao = new PrecoDAO();
        try {
        	dao.update(vo);
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
    /**
     * Remover um preco
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void remove(PrecoVO vo) throws CertdigitalException {

    	PrecoDAO dao = new PrecoDAO();
    	
        try {
        	
        	dao.remove(vo);
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

}
