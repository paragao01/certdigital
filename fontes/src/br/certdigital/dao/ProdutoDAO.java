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
import br.certdigital.view.ProdutoHelper;
import br.certdigital.vo.OperadorVO;
import br.certdigital.vo.ProdutoVO;

/**
 * Business DAO para cadastro de produtoes
 */
public class ProdutoDAO extends BaseDAO {
	
	//private Logger log = Logger.getLogger(this.getClass());
	
	public ProdutoDAO(){}

	/**
	 * Pesquisa todos os produtos de uma Entidade e Empresa 
	 * 
	 * @param idEntidade
	 * @param idEmpresa
	 * @param idProduto
	 */
    public List pesquisar(ProdutoHelper helper, OperadorVO operadorVo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        ProdutoVO vo = null;
        List list = Collections.EMPTY_LIST;
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT PRO.IDPRO,            \n");
            sql.append("     	PRO.NOM_PRO,          \n");        	
            sql.append("     	PRO.IDENT,            \n");        	
        	sql.append("     	ENT.NOM_COM,          \n");
            sql.append("     	PRO.TIPO,             \n");
            sql.append("     	PRO.IDEMP,            \n");
            sql.append("     	EMP.NOM_COM AS EMPRESA\n");
            sql.append("   FROM PRO, EMP		      \n");
            sql.append("   LEFT OUTER JOIN ENT   	  \n");
            sql.append("   ON ENT.IDENT = EMP.IDENT   \n");            
            sql.append("  WHERE PRO.IDENT = ENT.IDENT \n");
            sql.append("    AND PRO.IDEMP = EMP.IDEMP \n");
           	sql.append("  AND PRO.IDEMP = ? 		  \n");
            
            sql.append("ORDER BY PRO.NOM_PRO   \n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
             
            if(helper.getProdutoVO().getIdEmpresa()!=null) {
            	ps.setLong(++i, helper.getProdutoVO().getIdEmpresa().longValue());
            }else{
            	ps.setLong(++i, operadorVo.getIdEmpresa().longValue());
            }
            
            rs = ps.executeQuery();
            
            list = new ArrayList();

			while (rs.next()) {
                vo = new ProdutoVO();
                vo.setIdProduto(Parser.parseLong(rs.getBigDecimal("IDPRO")));
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
	 * Pesquisa um determinado produto 
	 * 
	 * @param idProduto
	 */
    public ProdutoVO pesquisarProduto(ProdutoHelper helper) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        ProdutoVO vo = new ProdutoVO();
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT PRO.IDPRO,				\n");
            sql.append("     	PRO.NOM_PRO,			\n");
            sql.append("     	PRO.PESSOA,			    \n");
            sql.append("     	PRO.TIPO,				\n");
            sql.append("     	PRO.VALIDADE,			\n");
        	sql.append("     	ENT.IDENT,				\n");
        	sql.append("     	ENT.NOM_COM AS NOM_ENT, \n");        	
        	sql.append("     	EMP.IDEMP,				\n");
        	sql.append("     	EMP.NOM_COM AS NOM_EMP  \n");
        	sql.append("   FROM PRO, ENT, EMP			\n");
        	sql.append("  WHERE PRO.IDEMP = ?			\n");
            sql.append("    AND PRO.IDPRO = ?			\n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            
            ps.setLong(++i, helper.getProdutoVO().getIdEmpresa().longValue());
            ps.setLong(++i, helper.getProdutoVO().getIdProduto().longValue());
            
            rs = ps.executeQuery();
            
			if (rs.next()) {
                vo.setIdProduto(Parser.parseLong(rs.getBigDecimal("IDPRO")));
                vo.setNomeProduto(rs.getString("NOM_PRO"));                
                vo.setPessoaProduto(rs.getString("PESSOA"));
                vo.setTipoProduto(rs.getString("TIPO"));
                vo.setValidadeProduto(rs.getString("VALIDADE"));
                vo.setIdEntidade(Parser.parseLong(rs.getBigDecimal("IDENT")));                
                vo.setIdEmpresa(Parser.parseLong(rs.getBigDecimal("IDEMP")));
                vo.setNomeEntidade(rs.getString("NOM_ENT"));                
                vo.setNomeEmpresa(rs.getString("NOM_EMP"));
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
	 * Insere um produto
	 * 
	 * @param vo
	 * @return
	 * @throws DAOException
	 */
	public Long insert(ProdutoVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        
        Long nextId;
        
        try {
        	vo = pesquisarEmpresa(vo);
        	
        	conn = this.getConnection();

            sql.append(" INSERT INTO PRO (IDENT, IDEMP, IDPRO, PESSOA, TIPO, NOM_PRO, VALIDADE, OPE_INC,      DTA_INC, DTA_ALT, OPE_ALT) \n");
            sql.append("          VALUES (    ?,     ?,     ?,      ?,    ?,       ?,        ?,       ?, CURRENT_DATE,    NULL,    NULL) \n");
            
            int i=0;
            
            nextId = this.getAutoIncrementByEntidade("PRO","IDPRO", vo.getIdEntidade());
            
            vo.setIdProduto(nextId);

            ps = conn.prepareStatement(sql.toString());
            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEntidade() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEmpresa() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdProduto() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getPessoaProduto() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getTipoProduto() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeProduto() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getValidadeProduto() ) ;
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
	 * Atualiza um produto
	 * 
	 * @param vo
	 * @return
	 * @throws DAOException
	 */
	public void update(ProdutoVO vo) throws DAOException {
        PreparedStatement psUpdate = null;
        ResultSet rs = null;
        StringBuffer sqlUpdate = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();

            sqlUpdate.append(" UPDATE PRO SET PESSOA = ?, TIPO = ?, NOM_PRO = ?, VALIDADE = ?, OPE_ALT = ?, DTA_ALT = CURRENT_DATE \n");
            sqlUpdate.append("  WHERE IDPRO = ? \n");
            int i=0;

            psUpdate = conn.prepareStatement(sqlUpdate.toString());
            
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getPessoaProduto() ) ;
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getTipoProduto() ) ;
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getNomeProduto() ) ;
            BaseDAO.setObject(psUpdate,Types.VARCHAR, ++i, vo.getValidadeProduto() ) ;
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getIdOperadorInclusao() ) ;
            
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getIdProduto() ) ;     
            
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
	
    public void remove(ProdutoVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
            sql.append(" DELETE FROM PRO ");
            sql.append(" WHERE IDPRO = ? ");
            
            int i=0;

            ps = conn.prepareStatement(sql.toString());

            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdProduto() ) ;
            
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
	 * Pesquisa uma determinada empresa 
	 * 
	 * @param idProduto
	 */
    public ProdutoVO pesquisarEmpresa(ProdutoVO vo) throws DAOException {
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

