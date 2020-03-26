/*
 * Created on 12/04/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.certdigital.facade;

import br.certdigital.dao.SecurityDAO;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.tools.database.BaseFacade;
import br.certdigital.vo.OperadorVO;

/**
 * @author elisio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SecurityFacade extends BaseFacade {

    public SecurityFacade() {}


    public void getAccess(OperadorVO vo) throws CertdigitalException {

    	SecurityDAO dao = new SecurityDAO();
    	
        try {
        	
        	vo.setListaAccess(dao.getAccess(vo.getIdOperador()));
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    
    public OperadorVO buscaInfOperador(Long idOperador) throws CertdigitalException {

    	SecurityDAO dao = new SecurityDAO();
    	
        try {
        	
        	return dao.buscaInfOperador(idOperador);
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    
    public boolean verificaOperadorSenha(Long idOperador, String senha) throws CertdigitalException {

    	SecurityDAO dao = new SecurityDAO();
    	
        try {
        	
        	return dao.verificaOperadorSenha(idOperador, senha);
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
}
