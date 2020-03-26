package br.certdigital.facade;

import br.certdigital.dao.ConsultarDAO;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.tools.database.BaseFacade;
import br.certdigital.view.ConsultarHelper;
import br.certdigital.vo.OperadorVO;

/**
 * Business facade para Pesquisas
 * 
 */

public class ConsultarFacade extends BaseFacade {

    public void consultarCertificado(ConsultarHelper helper, OperadorVO operadorVo) throws CertdigitalException {
    	ConsultarDAO dao = new ConsultarDAO();
        try {
       		helper.setListaItemConsultaVO(dao.consultarCertificado(helper, operadorVo) );
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
	
    public void consultarTabelaPreco(ConsultarHelper helper, OperadorVO operadorVo) throws CertdigitalException {
    	ConsultarDAO dao = new ConsultarDAO();
        try {
       		helper.setListaItemConsultaVO(dao.consultarTabelaPreco(helper, operadorVo) );
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
}
