package br.certdigital.facade;


import br.certdigital.dao.ProdutoDAO;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.tools.database.BaseFacade;
import br.certdigital.view.ProdutoHelper;
import br.certdigital.vo.OperadorVO;
import br.certdigital.vo.ProdutoVO;

/**
 * Business Facade do cadastro de produtoes
 */
public class ProdutoFacade extends BaseFacade {

    /**
     * Pesquisa todos os produtos
     * 
     * @param idUnidadeNegocio
     * @param helper
     * @throws CertdigitalException
     */
    public void pesquisar(ProdutoHelper helper, OperadorVO operadorVo) throws CertdigitalException {
    	ProdutoDAO dao = new ProdutoDAO();
        try {
        	helper.setListaProdutoVO( dao.pesquisar(helper, operadorVo) );
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Pesquisa um determinado produto
     * 
     * @param idUnidadeNegocio
     * @param helper
     * @throws CertdigitalException
     */
    public ProdutoHelper pesquisarProduto(ProdutoHelper helper) throws CertdigitalException {
    	ProdutoDAO dao = new ProdutoDAO();
        try {
        	helper.setProdutoVO( dao.pesquisarProduto(helper) );        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
        return helper;
    }
    
    /**
     * Insere um produto
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void insert(ProdutoVO vo) throws CertdigitalException {
    	ProdutoDAO dao = new ProdutoDAO();
        try {
        	dao.insert(vo);
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Altera um produto
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void update(ProdutoVO vo) throws CertdigitalException {
    	ProdutoDAO dao = new ProdutoDAO();
        try {
        	dao.update(vo);
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
    /**
     * Remover um produto
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void remove(ProdutoVO vo) throws CertdigitalException {

    	ProdutoDAO dao = new ProdutoDAO();
    	
        try {
        	
        	dao.remove(vo);
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

}
