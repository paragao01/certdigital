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
import br.certdigital.view.PrecoHelper;
import br.certdigital.vo.OperadorVO;
import br.certdigital.vo.PrecoVO;

/**
 * Business DAO para cadastro de precos
 */
public class PrecoDAO extends BaseDAO {
	
	
	public PrecoDAO(){}

	/**
	 * Pesquisa todos os preco de uma Entidade e Empresa 
	 * 
	 * @param idEntidade
	 * @param idEmpresa
	 * @param idPreco
	 */
    public List pesquisar(PrecoHelper helper, OperadorVO operadorVo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        PrecoVO vo = null;
        List list = Collections.EMPTY_LIST;
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT PRE.IDPRE,            \n");
        	sql.append("        PRE.IDPRO,            \n");        	
            sql.append("     	PRO.NOM_PRO,          \n");        	
            sql.append("     	PRE.IDENT,            \n");        	
        	sql.append("     	ENT.NOM_COM,          \n");
            sql.append("     	PRE.DATA_REFERENCIA,  \n");
            sql.append("     	PRE.PRECO_ENTIDADE,   \n");
            sql.append("     	PRE.PRECO_SUGERIDO,   \n");            
            sql.append("     	PRE.IDEMP,            \n");
            sql.append("     	EMP.NOM_COM AS EMPRESA\n");
            sql.append("   FROM PRE, PRO, EMP		  \n");
            sql.append("   LEFT OUTER JOIN ENT   	  \n");
            sql.append("   ON ENT.IDENT = EMP.IDENT   \n");            
            sql.append("  WHERE PRE.IDENT = ENT.IDENT \n");
            sql.append("    AND PRE.IDEMP = EMP.IDEMP \n");
           	sql.append("    AND PRE.IDPRO = PRO.IDPRO \n");
           	if(helper.getPrecoVO().getIdProduto()!=null) {
           		sql.append("    AND PRO.IDPRO = ? 		  \n");
           	}else {
           		sql.append("    AND PRO.IDEMP = ? 		  \n");
           	}
            sql.append("ORDER BY PRE.DATA_REFERENCIA  \n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            if(helper.getPrecoVO().getIdProduto()!=null) { 
            	ps.setLong(++i, helper.getPrecoVO().getIdProduto().longValue());
            }else{
            	ps.setLong(++i, operadorVo.getIdEmpresa().longValue());
            }
            
            rs = ps.executeQuery();
            
            list = new ArrayList();

			while (rs.next()) {
                vo = new PrecoVO();
                vo.setIdPreco(Parser.parseLong(rs.getBigDecimal("IDPRE")));
                vo.setIdProduto(Parser.parseLong(rs.getBigDecimal("IDPRO")));                
                vo.setDataReferencia(rs.getDate("DATA_REFERENCIA"));                
                vo.setNomeProduto(rs.getString("NOM_PRO"));                
                vo.setIdEntidade(Parser.parseLong(rs.getBigDecimal("IDENT")));
                vo.setNomeEntidade(rs.getString("NOM_COM"));
                vo.setIdEmpresa(Parser.parseLong(rs.getBigDecimal("IDEMP")));                
                vo.setNomeEmpresa(rs.getString("EMPRESA"));
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
	 * Pesquisa um determinado preco 
	 * 
	 * @param idPreco
	 * @throws ParserException 
	 */
    public PrecoVO pesquisarPreco(PrecoHelper helper) throws DAOException, ParserException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        PrecoVO vo = new PrecoVO();
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT PRE.IDPRE,            \n");
        	sql.append("        PRE.IDPRO,            \n");        	
            sql.append("     	PRO.NOM_PRO,          \n");        	
            sql.append("     	PRE.IDENT,            \n");        	
        	sql.append("     	ENT.NOM_COM,          \n");
            sql.append("     	PRE.DATA_REFERENCIA,  \n");
            sql.append("     	PRE.PRECO_ENTIDADE,   \n");
            sql.append("     	PRE.PRECO_SUGERIDO,   \n");            
            sql.append("     	PRE.IDEMP,            \n");
            sql.append("     	EMP.NOM_COM AS EMPRESA\n");
            sql.append("   FROM PRE, PRO, EMP		  \n");
            sql.append("   LEFT OUTER JOIN ENT   	  \n");
            sql.append("   ON ENT.IDENT = EMP.IDENT   \n");            
            sql.append("  WHERE PRE.IDENT = ENT.IDENT \n");
            sql.append("    AND PRE.IDEMP = EMP.IDEMP \n");
           	sql.append("    AND PRE.IDPRO = PRO.IDPRO \n");
           	sql.append("    AND PRE.IDPRO = ? 		  \n");
           	sql.append("    AND PRE.IDPRE = ? 		  \n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            
            ps.setLong(++i, helper.getPrecoVO().getIdProduto().longValue());
            ps.setLong(++i, helper.getPrecoVO().getIdPreco().longValue());

            rs = ps.executeQuery();
			if (rs.next()) {
                vo.setIdPreco(Parser.parseLong(rs.getBigDecimal("IDPRE")));
                vo.setIdProduto(Parser.parseLong(rs.getBigDecimal("IDPRO")));
                vo.setNomeProduto(rs.getString("NOM_PRO"));                
                vo.setDataReferencia(rs.getDate("DATA_REFERENCIA"));
                vo.setPrecoEntidade(Parser.parseDouble(rs.getBigDecimal("PRECO_ENTIDADE")));
                vo.setPrecoSugerido(Parser.parseDouble(rs.getBigDecimal("PRECO_SUGERIDO")));
                vo.setIdEntidade(Parser.parseLong(rs.getBigDecimal("IDENT")));                
                vo.setIdEmpresa(Parser.parseLong(rs.getBigDecimal("IDEMP")));
                vo.setNomeEntidade(rs.getString("NOM_COM"));                
                vo.setNomeEmpresa(rs.getString("EMPRESA"));
            }
		} catch (ParserException e) {
			throw new ParserException(e);          
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
	 * Insere um preco
	 * 
	 * @param vo
	 * @return
	 * @throws DAOException
	 */
	public Long insert(PrecoVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        
        Long nextId;
        
        try {
        	vo = pesquisarEmpresa(vo);
        	
        	conn = this.getConnection();

            sql.append(" INSERT INTO PRE (IDENT, IDEMP, IDPRO, IDPRE, DATA_REFERENCIA, PRECO_ENTIDADE, PRECO_SUGERIDO, OPE_INC,      DTA_INC, DTA_ALT, OPE_ALT) \n");
            sql.append("          VALUES (    ?,     ?,     ?,     ?,               ?,              ?,              ?,       ?, CURRENT_DATE,    NULL,    NULL) \n");
            
            int i=0;
            
            nextId = this.getAutoIncrementByProdutoPreco("PRE", "IDPRE", vo.getIdEmpresa(), vo.getIdProduto());
            
            vo.setIdPreco(nextId);

            ps = conn.prepareStatement(sql.toString());
            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEntidade() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEmpresa() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdProduto() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdPreco() ) ;            
            BaseDAO.setObject(ps,Types.DATE,    ++i, vo.getDataReferencia() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getPrecoEntidade() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getPrecoSugerido() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdOperadorInclusao() ) ;
            
            ps.execute();
			
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
		
		return nextId;
	}

	
	/**
	 * Atualiza um preco
	 * 
	 * @param vo
	 * @return
	 * @throws DAOException
	 */
	public void update(PrecoVO vo) throws DAOException {
        PreparedStatement psUpdate = null;
        ResultSet rs = null;
        StringBuffer sqlUpdate = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();

            sqlUpdate.append(" UPDATE PRE SET DATA_REFERENCIA = ?, PRECO_ENTIDADE = ?, PRECO_SUGERIDO = ?, OPE_ALT = ?, DTA_ALT = CURRENT_DATE \n");
            sqlUpdate.append("  WHERE IDPRO = ? \n");
            sqlUpdate.append("    AND IDPRE = ? \n");
            
            int i=0;

            psUpdate = conn.prepareStatement(sqlUpdate.toString());
            
            BaseDAO.setObject(psUpdate,Types.DATE,    ++i, vo.getDataReferencia() ) ;
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getPrecoEntidade() ) ;
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getPrecoSugerido() ) ;
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getIdOperadorInclusao() ) ;
            
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getIdProduto() ) ;     
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getIdPreco() ) ;     

            psUpdate.execute();
            
		} catch (SQLException e) {
			throw new DatabaseException(e);
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(psUpdate);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
	}
	
    public void remove(PrecoVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
            sql.append(" DELETE FROM PRE \n");
            sql.append(" WHERE IDPRO = ? \n");
            sql.append("   AND IDPRE = ? \n");
            
            
            int i=0;

            ps = conn.prepareStatement(sql.toString());

            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdProduto() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdPreco() ) ;

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
	 * Pesquisa uma determinad empresa 
	 * 
	 * @param idPreco
	 */
    public PrecoVO pesquisarEmpresa(PrecoVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        
        try {
        	conn = this.getConnection();

        	sql.append(" SELECT EMP.IDENT,				\n");
            sql.append("     	ENT.NOM_COM AS ENTIDADE,\n");
            sql.append("     	EMP.IDEMP,			    \n");
            sql.append("     	EMP.NOM_COM AS EMPRESA  \n");
        	sql.append("   FROM EMP, ENT				\n");
        	sql.append("  WHERE EMP.IDENT = ENT.IDENT	\n");
        	sql.append("    AND EMP.IDEMP = ?			\n"); 

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            
            ps.setLong(++i, vo.getIdEmpresa().longValue());
            
            rs = ps.executeQuery();
            
			if (rs.next()) {
                vo.setIdEntidade(Parser.parseLong(rs.getBigDecimal("IDENT")));                
                vo.setIdEmpresa(Parser.parseLong(rs.getBigDecimal("IDEMP")));
                vo.setNomeEntidade(rs.getString("ENTIDADE"));                
                vo.setNomeEmpresa(rs.getString("EMPRESA"));
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

}

