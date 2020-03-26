package br.certdigital.facade;

import br.certdigital.dao.EmpresaDAO;
import br.certdigital.exception.CNPJAlreadyExistsException;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.shared.util.GlobalConstants;
import br.certdigital.tools.database.BaseFacade;
import br.certdigital.view.EmpresaHelper;
import br.certdigital.vo.EmpresaVO;
import br.certdigital.vo.OperadorVO;

/**
 * Business facade para o cadastro de empresa
 */
public class EmpresaFacade extends BaseFacade {

    /**
     * Pesquisa as empresas de uma unidade de negocio
     * 
     * @param idUnidadeNegocio
     * @param tipoVisualizacao
     * @param idGestor
     * @param helper
     * @throws CertdigitalException
     */
    public void pesquisar(Long idEntidade, EmpresaHelper helper, OperadorVO operadorVo) throws CertdigitalException {
    	EmpresaDAO dao = new EmpresaDAO();
        try {
        	Long idEmpresa = null;
        	Long numCNPJ = null;
        	
        	if (helper.getEmpresaVO() != null) {
            	if (helper.getEmpresaVO().getIdEmpresa() != null) {
            		idEmpresa = helper.getEmpresaVO().getIdEmpresa();
            	}        	        		
            	if (helper.getEmpresaVO().getNumCNPJ() != null) {
            		numCNPJ = helper.getEmpresaVO().getNumCNPJ();
            	}
        	}
        	if(operadorVo.getIdTipoOperador().longValue()==GlobalConstants.OPERADOR_MASTER.longValue()){
        		helper.setListaEmpresaVO(dao.pesquisar(null, idEmpresa, numCNPJ, operadorVo));
        	}else{
        		helper.setListaEmpresaVO(dao.pesquisar(idEntidade, idEmpresa, numCNPJ, operadorVo));
        	}
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Pesquisa uma determinada empresas de uma unidade de negocio
     * 
     * @param idUnidadeNegocio
     * @param helper
     * @throws CertdigitalException
     */
    public void pesquisarDetalhesEmpresa(Long idEntidade, EmpresaHelper helper) throws CertdigitalException {
    	EmpresaDAO dao = new EmpresaDAO();
        try {
        	EmpresaVO voTemp = dao.pesquisarDetalhesEmpresa(idEntidade, helper.getEmpresaVO().getIdEmpresa());
        	helper.setEmpresaVO( (voTemp != null) ? voTemp : helper.getEmpresaVO() );
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Pesquisa uma determinada empresa pelo seu numero de CNPJ
     * 
     * @param cnpj
     * @param idUnidadeNegocio
	 * @return Long -> id da empresa
     * @throws CertdigitalException
     */
    public Long pesquisarEmpresaPorCNPJ(Long cnpj, Long idEntidade) throws CertdigitalException {
    	EmpresaDAO dao = new EmpresaDAO();
        try {
        	return dao.pesquisarEmpresaPorCnpj(cnpj, idEntidade);
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
	/**
	 * Insere uma nova empresa
	 * 
	 * @param vo
	 * @throws CertdigitalException
	 */
	public void insert(EmpresaVO vo) throws CertdigitalException {
    	EmpresaDAO dao = new EmpresaDAO();    	
    	// verifica se o cnpj ja esta cadastrado
    	Long empresa = this.pesquisarEmpresaPorCNPJ(vo.getNumCNPJ(), vo.getIdEntidade());
    	if (empresa != null) {
    		throw new CNPJAlreadyExistsException();
    	}
    	dao.insert(vo);
    }
	
    /**
     * Atualiza uma empresa
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void update(EmpresaVO vo) throws CertdigitalException {
    	EmpresaDAO empresaDao = new EmpresaDAO();
    	// verifica se o cnpj ja esta cadastrado
    	Long empresa = this.pesquisarEmpresaPorCNPJ(vo.getNumCNPJ(), vo.getIdEntidade());
    	if (empresa != null && empresa.longValue() != vo.getIdEmpresa().longValue()) {
    		throw new CNPJAlreadyExistsException();
    	}
    	empresaDao.update(vo);
    }

    /**
     * Remove uma empresa
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void remove(EmpresaVO vo) throws CertdigitalException {
    	EmpresaDAO empresaDao = new EmpresaDAO();
    	// remove a empresa
        empresaDao.remove(vo);
    }
    	
}
