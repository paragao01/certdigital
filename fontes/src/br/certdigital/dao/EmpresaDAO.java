package br.certdigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.certdigital.shared.exception.DAOException;
import br.certdigital.shared.exception.DatabaseException;
import br.certdigital.shared.exception.ParserException;
import br.certdigital.shared.util.Parser;
import br.certdigital.tools.database.BaseDAO;
import br.certdigital.vo.EmpresaVO;
import br.certdigital.vo.OperadorVO;

/**
 * Business DAO para o cadastro de Empresas
 * 
 * @author elisio
 */
public class EmpresaDAO extends BaseDAO {
	
	//private Logger log = Logger.getLogger(this.getClass());
	
	public EmpresaDAO(){}
 
    public List pesquisar(Long idEntidade, Long idEmpresa, Long numCNPJ, OperadorVO operadorVo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        EmpresaVO vo = null;
        List list = Collections.EMPTY_LIST;
         
        try {
    
        	conn = this.getConnection();

        	sql.append(" SELECT EMP.IDENT,             \n");
            sql.append("    	EMP.IDEMP,             \n");
            sql.append("     	EMP.NOM_COM,            \n");
            sql.append("     	EMP.NOM_CON            \n");            
            sql.append(" 		FROM EMP               \n");
            
            if (idEntidade != null && idEntidade.longValue() != 0) {
            	sql.append("  WHERE EMP.IDENT = ?      \n");
            }else {
            	sql.append("  WHERE EMP.IDENT IS NOT NULL  \n");

            }
            if (idEmpresa != null && idEmpresa.longValue() != 0) {
            	sql.append("   AND EMP.IDEMP = ?       \n");
            }
            if(numCNPJ != null){
            	sql.append("   AND EMP.NUM_CNPJ = ?    \n");
            }
            
            sql.append("ORDER BY EMP.NOM_COM           \n");
            
            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            
            if (idEntidade != null && idEntidade.longValue() != 0) {            
            	ps.setLong(++i,idEntidade.longValue());
            }
            if (idEmpresa != null && idEmpresa.longValue() != 0) {
            	ps.setLong(++i, idEmpresa.longValue());
            }
            if(numCNPJ != null) {
            	ps.setLong(++i, numCNPJ.longValue());
            }
            
            rs = ps.executeQuery();
            
            list = new ArrayList();

			while (rs.next()) {
                vo = new EmpresaVO();
                vo.setIdEntidade(Parser.parseLong(rs.getBigDecimal("IDENT")));
                vo.setIdEmpresa(Parser.parseLong(rs.getBigDecimal("IDEMP")));
                vo.setNomeComercial(rs.getString("NOM_COM"));
                vo.setNomeContato(rs.getString("NOM_CON"));                
                list.add(vo);
            }
		} catch (ParserException e) {
			throw new DAOException(e);
        } catch (SQLException e) {
        	throw new DatabaseException(e);
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
	
		return list;
	}

    /**
     * Retorna o CNPJ de uma empresa atraves do seu codigo
     * 
     * @param idEmpresa
     * @param idUnidadeNegocio
     * @return
     * @throws DAOException
     */
    public Long pesquisarCnpjEmpresa(Long idEmpresa, Long idEntidade) throws DAOException {
    	PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        
        try {
    
        	conn = this.getConnection();

        	sql.append(" SELECT NUM_CNPJ    ");
            sql.append(" 		FROM EMP    ");      	
            sql.append("  WHERE IDENT = ?   ");
            sql.append("    AND IDEMP = ?   ");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i,idEntidade.longValue());
            ps.setLong(++i, idEmpresa.longValue());
            
            rs = ps.executeQuery();

			if (rs.next()) {
                return  Parser.parseLong(rs.getBigDecimal("NUM_CNPJ"));
            }
		} catch (ParserException e) {
			throw new DAOException(e);
        } catch (SQLException e) {
        	throw new DatabaseException(e);
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
	
		return null;
	}
	
	/**
	 * Pesquisa os detalhes de uma empresa
	 * 
	 * @param idUnidadeNegocio
	 * @param idEmpresa
	 */
    public EmpresaVO pesquisarDetalhesEmpresa(Long idEntidade, Long idEmpresa) throws DAOException {

    	if (idEntidade == null || idEmpresa == null) {
    		return null;
    	}
    	PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        EmpresaVO vo = null;
        
        try {
    
        	conn = this.getConnection();

        	sql.append(" SELECT IDEMP        , ");
            sql.append("        IDENT        , ");
            sql.append("        NUM_CNPJ     , ");
            sql.append("        NOM_COM      , ");
            sql.append("        RAZ_SOC      , ");
            sql.append("        INSC_EST     , ");
            sql.append("        NOM_CON      , ");
            sql.append("        SLG_EST_COM  , ");
            sql.append("        MAIL         , ");
            sql.append("        DDD_FONE     , ");
            sql.append("        FONE         , ");
            sql.append("        DDD_FAX      , ");
            sql.append("        FONE_FAX     , ");
            sql.append("        END_COM      , ");
            sql.append("        BAIR_COM     , ");
            sql.append("        CID_COM      , ");
            sql.append("        CEP_COM      , ");
            sql.append("        SLG_EST_COB  , ");
            sql.append("        END_COB      , ");
            sql.append("        BAIR_COB     , ");
            sql.append("        CID_COB      , ");
            sql.append("        CEP_COB      , ");
            sql.append("        DDD_CELULAR  , ");
            sql.append("        FONE_CELULAR   ");
            sql.append(" 		FROM EMP       ");      	
            sql.append("  WHERE IDENT    = ?   ");
            sql.append("    AND IDEMP    = ?   ");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i,idEntidade.longValue());
            ps.setLong(++i, idEmpresa.longValue());
            
            rs = ps.executeQuery();

			if (rs.next()) {
                vo = new EmpresaVO();
                vo.setIdEmpresa(Parser.parseLong(rs.getBigDecimal("IDEMP")));
                vo.setIdEntidade(Parser.parseLong(rs.getBigDecimal("IDENT")));
                vo.setNumCNPJ(Parser.parseLong(rs.getBigDecimal("NUM_CNPJ")));
                vo.setNomeComercial(rs.getString("NOM_COM"));                
                vo.setRazaoSocial(rs.getString("RAZ_SOC"));
                vo.setInscricaoEstadual(Parser.parseLong(rs.getBigDecimal("INSC_EST")));
                vo.setNomeContato(rs.getString("NOM_CON"));
                vo.setEstadoComercial(rs.getString("SLG_EST_COM"));
                vo.setMail(rs.getString("MAIL"));
                vo.setDddFone(Parser.parseLong(rs.getBigDecimal("DDD_FONE")));
                vo.setNumFone(Parser.parseLong(rs.getBigDecimal("FONE")));
                vo.setDddFax(Parser.parseLong(rs.getBigDecimal("DDD_FAX")));
                vo.setNumFax(Parser.parseLong(rs.getBigDecimal("FONE_FAX")));
                vo.setEnderecoComercial(rs.getString("END_COM"));
                vo.setBairroComercial(rs.getString("BAIR_COM"));
                vo.setCidadeComercial(rs.getString("CID_COM"));
                vo.setCepComercial(Parser.parseLong(rs.getBigDecimal("CEP_COM")));
                vo.setEstadoCobranca(rs.getString("SLG_EST_COB"));
                vo.setEnderecoCobranca(rs.getString("END_COB"));
                vo.setBairroCobranca(rs.getString("BAIR_COB"));
                vo.setCidadeCobranca(rs.getString("CID_COB"));
                vo.setCepCobranca(Parser.parseLong(rs.getBigDecimal("CEP_COB")));
                vo.setDddCelular(Parser.parseLong(rs.getBigDecimal("DDD_CELULAR")));
                vo.setNumCelular(Parser.parseLong(rs.getBigDecimal("FONE_CELULAR")));
            }
		} catch (ParserException e) {
			throw new DAOException(e);
        } catch (SQLException e) {
        	throw new DatabaseException(e);
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
	
		return vo;
	}

	/**
	 * Pesquisa os detalhes de uma empresa
	 * 
	 * @param cnpj
	 * @param idUnidadeNegocio
	 * @return Long -> id da empresa
	 */
    public Long pesquisarEmpresaPorCnpj(Long cnpj, Long idEntidade) throws DAOException {

    	PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        Long result = null;
        if (cnpj == null) {
        	return result;
        }
        
        try {
    
        	conn = this.getConnection();

            sql.append(" SELECT	IDEMP              \n");
            sql.append("   FROM	EMP                \n");
            sql.append("  WHERE NUM_CNPJ = ?       \n");
            sql.append("    AND IDENT    = ?       \n");            

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i, cnpj.longValue());
            ps.setLong(++i, idEntidade.longValue());
            
            rs = ps.executeQuery();            

			if (rs.next()) {
                result = Parser.parseLong(rs.getBigDecimal("IDEMP"));                
            }
		} catch (ParserException e) {
			throw new DAOException(e);
        } catch (SQLException e) {
        	throw new DatabaseException(e);
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
	
		return result;
	}
    
    /**
     * Insere uma nova empresa
     * 
     * @param empresaVO
     * @throws DAOException
     */
    public EmpresaVO insert(EmpresaVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
            sql.append(" INSERT INTO EMP (IDEMP,IDENT,NUM_CNPJ,NOM_COM,RAZ_SOC,INSC_EST,NOM_CON,SLG_EST_COM,MAIL,DDD_FONE,FONE,DDD_FAX,FONE_FAX,END_COM,BAIR_COM,CID_COM,CEP_COM,SLG_EST_COB,END_COB,BAIR_COB,CID_COB,CEP_COB,OPE_INC,DTA_INC,OPE_ALT,DTA_ALT,  DDD_CELULAR, FONE_CELULAR) \n");
            sql.append("          VALUES ( ?   ,?    ,?       , ?     , ?     , ?      , ?      , ?         , ?  , ?      , ?  , ?     , ?      , ?     , ?      , ?     , ?     , ?         , ?     , ?      , ?     , ?     , ?  , CURRENT_DATE, NULL, NULL, ?, ? ) \n");
            
            int i=0;

            vo.setIdEmpresa(this.getAutoIncrement("EMP","IDEMP"));            

            ps = conn.prepareStatement(sql.toString());
            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEmpresa() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEntidade() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumCNPJ() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getRazaoSocial() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getInscricaoEstadual() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeContato() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEstadoComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getMail() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddFone() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumFone() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddFax() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumFax() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEnderecoComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getBairroComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getCidadeComercial() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getCepComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEstadoCobranca() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEnderecoCobranca() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getBairroCobranca() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getCidadeCobranca() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getCepCobranca() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getOperadorInc() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddCelular()) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumCelular() ) ;            
            
            ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DatabaseException(e);
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}    
		return vo;
    }

    /**
     * Atualiza uma empresa
     * 
     * @param empresaVO
     * @throws DAOException
     */
    public void update(EmpresaVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
            sql.append(" UPDATE EMP               \n");
            sql.append("    SET NUM_CNPJ     = ?, \n");
            sql.append("        NOM_COM      = ?, \n");
            sql.append("        RAZ_SOC      = ?, \n");
            sql.append("        INSC_EST     = ?, \n");
            sql.append("        NOM_CON      = ?, \n");
            sql.append("        SLG_EST_COM  = ?, \n");
            sql.append("        MAIL         = ?, \n");
            sql.append("        DDD_FONE     = ?, \n");
            sql.append("        FONE         = ?, \n");
            sql.append("        DDD_FAX      = ?, \n");
            sql.append("        FONE_FAX     = ?, \n");
            sql.append("        END_COM      = ?, \n");
            sql.append("        BAIR_COM     = ?, \n");
            sql.append("        CID_COM      = ?, \n");
            sql.append("        CEP_COM      = ?, \n");
            sql.append("        SLG_EST_COB	 = ?, \n");
            sql.append("        END_COB		 = ?, \n");
            sql.append("        BAIR_COB	 = ?, \n");
            sql.append("        CID_COB		 = ?, \n");
            sql.append("        CEP_COB		 = ?, \n");
            sql.append("        OPE_ALT		 = ?, \n");
            sql.append("        DTA_ALT      = CURRENT_DATE, \n");
            sql.append("        DDD_CELULAR  = ?, \n");
            sql.append("        FONE_CELULAR = ?  \n");
            sql.append("  WHERE IDEMP        = ? \n");
            sql.append("    AND IDENT        = ? \n");
            
            int i=0;

            ps = conn.prepareStatement(sql.toString());

            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumCNPJ() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getRazaoSocial() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getInscricaoEstadual() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeContato() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEstadoComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getMail() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddFone() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumFone() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddFax() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumFax() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEnderecoComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getBairroComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getCidadeComercial() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getCepComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEstadoCobranca() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEnderecoCobranca() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getBairroCobranca() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getCidadeCobranca() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getCepCobranca() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getOperadorAlt() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddCelular() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumCelular() ) ;
            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEmpresa() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEntidade() ) ;
            
            ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DatabaseException(e);
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
    }

    /**
     * Remove uma empresa
     * 
     * @param empresaVO
     * @throws DAOException
     */
    public void remove(EmpresaVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
            sql.append(" DELETE FROM EMP     ");
            sql.append("  WHERE IDEMP    = ? ");
            sql.append("    AND IDENT    = ? ");
            
            int i=0;

            ps = conn.prepareStatement(sql.toString());

            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEmpresa() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEntidade() ) ;
            
            ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DatabaseException(e);
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
	
	}
    
}
