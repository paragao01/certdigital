package br.certdigital.facade;

import br.certdigital.dao.CertificadoDAO;
import br.certdigital.dao.EmpresaDAO;
import br.certdigital.exception.CNPJAlreadyExistsException;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.shared.util.GlobalConstants;
import br.certdigital.tools.database.BaseFacade;
import br.certdigital.view.CertificadoHelper;
import br.certdigital.view.EmpresaHelper;
import br.certdigital.vo.CertificadoVO;
import br.certdigital.vo.EmpresaVO;
import br.certdigital.vo.OperadorVO;

/**
 * Business facade para o cadastro de empresa
 */
public class CertificadoFacade extends BaseFacade {

    /**
     * Pesquisa os certificados
     * 
     */
    public void pesquisar(CertificadoHelper helper, OperadorVO operadorVo) throws CertdigitalException {
    	CertificadoDAO dao = new CertificadoDAO();
        helper.setListaCertificadoVO(dao.pesquisar(helper, operadorVo));
    }

    /**
     * Pesquisa um determinado certificado
     * 
     */
    public void pesquisarCertificado(CertificadoHelper helper, OperadorVO operadorVo) throws CertdigitalException {
    	CertificadoDAO dao = new CertificadoDAO();
        try {
        	helper.setCertificadoVO(dao.pesquisarCertificado(helper, operadorVo));
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    /**
     * Pesquisa uma determinado certificado pelo seu numero de CNPJ
     * 
     */
    public Long pesquisarCertificadoPorCNPJ(CertificadoVO vo, OperadorVO operadorVo) throws CertdigitalException {
    	CertificadoDAO dao = new CertificadoDAO();
        try {
        	return dao.pesquisarCertificadoPorCnpj(vo, operadorVo);
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
	/**
	 * Insere uma novo certificado
	 * 
	 * @param vo
	 * @throws CertdigitalException
	 */
	public void insert(CertificadoVO vo, OperadorVO operadorVo) throws CertdigitalException {
    	CertificadoDAO dao = new CertificadoDAO();    	
    	// verifica se o cnpj ja esta cadastrado
    	Long certificado = this.pesquisarCertificadoPorCNPJ(vo, operadorVo);
    	if (certificado != null) {
    		throw new CNPJAlreadyExistsException();
    	}
    	dao.insert(vo, operadorVo);
    }
	
    /**
     * Atualiza um certificado
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void update(CertificadoVO vo, OperadorVO operadorVo) throws CertdigitalException {
    	CertificadoDAO dao = new CertificadoDAO(); 
    	
    	// verifica se o cnpj ja esta cadastrado
    	Long certificado = this.pesquisarCertificadoPorCNPJ(vo, operadorVo);
    	if (certificado != null && certificado.longValue() != vo.getIdCertificado().longValue()) {
    		throw new CNPJAlreadyExistsException();
    	}
    	dao.update(vo, operadorVo);
    }

    /**
     * Remove uma empresa
     * 
     * @param vo
     * @throws CertdigitalException
     */
    public void remove(CertificadoVO vo, OperadorVO operadorVo) throws CertdigitalException {
    	CertificadoDAO dao = new CertificadoDAO();
    	// remove a empresa
        dao.remove(vo, operadorVo);
    }
    	
}
