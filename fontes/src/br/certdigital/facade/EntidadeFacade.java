package br.certdigital.facade;

import br.certdigital.dao.EmpresaDAO;
import br.certdigital.dao.EntidadeDAO;
import br.certdigital.dao.EstadoDAO;
import br.certdigital.dao.OperadorDAO;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.tools.database.BaseFacade;
import br.certdigital.shared.util.GlobalConstants;
import br.certdigital.vo.EmpresaVO;
import br.certdigital.vo.EntidadeVO;
import br.certdigital.vo.ItemEntidadeVO;
import br.certdigital.vo.MenuGerencialVO;
import br.certdigital.vo.MenuOperacionalVO;
import br.certdigital.vo.OperadorVO;

public class EntidadeFacade extends BaseFacade {
	
	
    public ItemEntidadeVO insert(ItemEntidadeVO vo, OperadorVO operadorVo) throws CertdigitalException {

    	EntidadeDAO dao = new EntidadeDAO();
    	
        try {
        	
        	vo = dao.insert(vo);
        	
        	if(operadorVo.getIdTipoOperador().equals(GlobalConstants.OPERADOR_MASTER)) {
        		EmpresaDAO  empDAO = new EmpresaDAO();
        		OperadorDAO opeDAO = new OperadorDAO();
        		EmpresaVO   empVO  = new EmpresaVO();
        		OperadorVO  opeVO  = new OperadorVO();
        		        		
                //inserir uma empresa para o Entidade                 
                empVO.setIdEntidade(vo.getIdEntidade());
                empVO.setNumCNPJ(vo.getNumCNPJ());
                empVO.setNomeComercial(vo.getNomeComercial());                
                empVO.setRazaoSocial(vo.getRazaoSocial());
                empVO.setNomeContato(vo.getNomeContato());
                empVO.setEstadoComercial(vo.getEstado());
                empVO.setMail(vo.getMail());
                empVO.setDddFone(vo.getDddFone());
                empVO.setNumFone(vo.getNumFone());
                empVO.setDddFax(vo.getDddFax());
                empVO.setNumFax(vo.getNumFax());
                empVO.setEnderecoComercial(vo.getEnderecoComercial());
                empVO.setBairroComercial(vo.getBairro());
                empVO.setCidadeComercial(vo.getCidade());
                empVO.setCepComercial(vo.getCep());
                empVO.setEstadoCobranca(vo.getEstado());
                empVO.setEnderecoCobranca(vo.getEnderecoComercial());
                empVO.setBairroCobranca(vo.getBairro());
                empVO.setCidadeCobranca(vo.getCidade());
                empVO.setCepCobranca(vo.getCep());
                empVO.setOperadorInc(operadorVo.getIdOperador());
                
                empVO = empDAO.insert(empVO);
                
                //inserir operador para o Entidade
                opeVO.setIdTipoOperador(Long.valueOf(3));
                opeVO.setNomeOperador(vo.getNomeComercial());
                opeVO.setSenha("");
                opeVO.setStatusOperador(GlobalConstants.STATUS_OPERADOR_ATIVO);
                opeVO.setIdEntidade(vo.getIdEntidade());
                opeVO.setIdEmpresa(empVO.getIdEmpresa());
                opeVO.setIdOperadorInclusao(operadorVo.getIdOperador());
                                
                MenuGerencialVO menuGerencial = new MenuGerencialVO();
                MenuOperacionalVO menuOperacional = new MenuOperacionalVO();
                
                menuGerencial.setAllTrue();
                opeVO.setMenuGerencialVO(menuGerencial);
                
                menuOperacional.setAllTrue();
                opeVO.setMenuOperacionalVO(menuOperacional);
                
                opeDAO.defineAcesso(opeVO);
                vo.setIdOperador(opeVO.getIdOperador());
        	}
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
        return vo;
    }

    public boolean verificarCNPJ(Long numCNPJ) throws CertdigitalException {
    	EntidadeDAO dao = new EntidadeDAO();
    	return dao.verificarCNPJ(numCNPJ);
    }
    
    public boolean verificarCNPJ(Long numCNPJ,Long idEntidade) throws CertdigitalException {
    	EntidadeDAO dao = new EntidadeDAO();
    	return dao.verificarCNPJ(numCNPJ,idEntidade);
    }

    public void prepararFiltro(EntidadeVO vo) throws CertdigitalException {
    	EstadoDAO dao = new EstadoDAO();
    	
        try {
        	vo.setListaEstadoVO(dao.pesquisar());
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    public void pesquisar(OperadorVO operadorVo, EntidadeVO vo) throws CertdigitalException {

    	EntidadeDAO dao = new EntidadeDAO();
    	
        try {
        	
        	vo.setListaItemEntidadeVO(dao.pesquisar(operadorVo));
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    public ItemEntidadeVO pesquisarEntidade(Long idEntidade) throws CertdigitalException {

    	EntidadeDAO dao = new EntidadeDAO();
    	
        try {
        	
        	return dao.pesquisarEntidade(idEntidade);
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
    
    public void update(ItemEntidadeVO vo) throws CertdigitalException {

    	EntidadeDAO dao = new EntidadeDAO();
    	
        try {
        	
        	dao.update(vo);
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }

    public void remove(ItemEntidadeVO vo) throws CertdigitalException {

    	EntidadeDAO dao = new EntidadeDAO();
    	
        try {
        	
        	dao.remove(vo);
        	
        }
        catch (CertdigitalException ex) {
            throw ex;
        }
    }
    
    
}
