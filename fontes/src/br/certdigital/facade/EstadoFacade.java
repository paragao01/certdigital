package br.certdigital.facade;

import br.certdigital.dao.EstadoDAO;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.tools.database.BaseFacade;
import br.certdigital.view.EstadoHelper;

/**
 * Business facade para pesquisas de Estados
 *  
 * @author elisio
 */
public class EstadoFacade extends BaseFacade {

    /**
     * Pesquisa todos os estados disponiveis
     * 
     * @param helper
     * @throws CertdigitalException
     */
    public void pesquisar(EstadoHelper helper) throws CertdigitalException {
    	EstadoDAO dao = new EstadoDAO();
        try {
        	helper.setListEstadoVO(dao.pesquisar());
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
}
