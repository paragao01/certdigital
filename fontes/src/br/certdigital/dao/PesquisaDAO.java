package br.certdigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.certdigital.shared.exception.DAOException;
import br.certdigital.shared.exception.DatabaseException;
import br.certdigital.shared.util.GlobalConstants;
import br.certdigital.tools.database.BaseDAO;
import br.certdigital.vo.ItemPesquisaVO;
import br.certdigital.vo.OperadorVO;


/**
 * Business DAO para pesquisas
 * 
 * @author elisio
 */
public class PesquisaDAO extends BaseDAO {
	
	//private Logger log = Logger.getLogger(this.getClass());
	
	public PesquisaDAO(){}
 
	/**
	 * Pesquisa todas as empresas de uma Unidade de Negocio 
	 * 
	 * @param idUnidadeNegocio
	 * @param idGestor
	 * @param chavePesquisa
	 */
    public List pesquisarEmpresas(String chavePesquisa, OperadorVO operadorVo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        ItemPesquisaVO vo = null;
        List list = Collections.EMPTY_LIST;
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT EMP.IDEMP,      \n");
            sql.append("     	EMP.NOM_COM     \n");
            sql.append("   FROM EMP             \n");
            
            if(operadorVo.getIdTipoOperador().longValue()==GlobalConstants.OPERADOR_MASTER.longValue()) {
            	sql.append("  WHERE EMP.IDENT IS NOT NULL  \n");
            }else {
            	sql.append("  WHERE EMP.IDENT = ?   \n");
            }
                        
            if (chavePesquisa != null && !"".equals(chavePesquisa)) {
            	sql.append("  AND EMP.NOM_COM LIKE ?   \n");
            }
            sql.append("ORDER BY EMP.NOM_COM       \n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            
            if(operadorVo.getIdTipoOperador().longValue()!=GlobalConstants.OPERADOR_MASTER.longValue()) {
            	ps.setLong(++i,operadorVo.getIdEntidade().longValue());
            }
            if (chavePesquisa != null && !"".equals(chavePesquisa)) {
            	ps.setString(++i, "%"+chavePesquisa+"%");
            }
            
            rs = ps.executeQuery();
            
            list = new ArrayList();
            
			while (rs.next()) {
                vo = new ItemPesquisaVO();
                vo.setCodigo(rs.getString("IDEMP"));
                vo.setNome(rs.getString("NOM_COM"));
                list.add(vo);
            }
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
	 * Pesquisa todos os gestores de uma Unidade de Negocio 
	 * 
	 * @param idUnidadeNegocio
	 * @param idGestor
	 * @param chavePesquisa
	 */
    public List pesquisarGestores(Long idUnidadeNegocio, Long idGestor, String chavePesquisa) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        ItemPesquisaVO vo = null;
        List list = Collections.EMPTY_LIST;
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT IDUNDNGC,          ");
            sql.append("    	IDGTO,             ");
            sql.append("     	NOM_COM            ");
            sql.append("   FROM GTO                ");      	
            sql.append("  WHERE IDUNDNGC = ?       ");
            if (idGestor != null && idGestor.longValue() != 0) {
            	sql.append("   AND IDGTO = ?       ");
            }
            if (chavePesquisa != null && !"".equals(chavePesquisa)) {
            	sql.append("  AND NOM_COM LIKE ?   ");
            }
            sql.append("ORDER BY NOM_COM           ");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i,idUnidadeNegocio.longValue());
            if (idGestor != null && idGestor.longValue() != 0) {
            	ps.setLong(++i, idGestor.longValue());
            }
            if (chavePesquisa != null && !"".equals(chavePesquisa)) {
            	ps.setString(++i, "%"+chavePesquisa+"%");
            }
            
            rs = ps.executeQuery();
            
            list = new ArrayList();

			while (rs.next()) {
                vo = new ItemPesquisaVO();
                vo.setCodigo(rs.getString("IDGTO"));
                vo.setNome(rs.getString("NOM_COM"));
                list.add(vo);
            }
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
	 * Pesquisa todos os operadores de uma Unidade de Negocio 
	 * 
	 * @param idUnidadeNegocio
	 * @param idGestor
	 * @param idEmpresa
	 * @param chavePesquisa
	 */
    public List pesquisarOperadores(Long idEntidade, Long idEmpresa, String chavePesquisa) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        ItemPesquisaVO vo = null;
        List list = Collections.EMPTY_LIST;
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT IDOPE,     \n");
            sql.append("     	NOM_OPE    \n");
            sql.append("   FROM OPE        \n");      	
           	sql.append("  WHERE IDENT = ?  \n");
            if (idEmpresa != null && idEmpresa.longValue() != 0) {
            	sql.append("   AND IDEMP = ?       \n");
            }
            if (chavePesquisa != null && !"".equals(chavePesquisa)) {
            	sql.append("  AND NOM_OPE LIKE ?   \n");
            }
            sql.append("ORDER BY NOM_OPE           \n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            
            ps.setLong(++i, idEntidade.longValue());
            if (idEmpresa != null && idEmpresa.longValue() != 0) {
            	ps.setLong(++i, idEmpresa.longValue());
            }
            if (chavePesquisa != null && !"".equals(chavePesquisa)) {
            	ps.setString(++i, "%"+chavePesquisa+"%");
            }
            
            rs = ps.executeQuery();
            
            list = new ArrayList();

			while (rs.next()) {
                vo = new ItemPesquisaVO();
                vo.setCodigo(rs.getString("IDOPE"));
                vo.setNome(rs.getString("NOM_OPE"));
                list.add(vo);
            }
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
	 * Pesquisa todos os operadores de uma Entidade
	 * 
	 * @param idUnidadeNegocio
	 * @param idGestor
	 * @param idEmpresa
	 * @param chavePesquisa
	 */
    public List pesquisarProdutos(Long idEntidade, Long idEmpresa, String chavePesquisa) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        ItemPesquisaVO vo = null;
        List list = Collections.EMPTY_LIST;
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT IDPRO,     \n");
            sql.append("     	NOM_PRO    \n");
            sql.append("   FROM PRO        \n");      	
           	sql.append("  WHERE IDENT = ?  \n");
            if (idEmpresa != null && idEmpresa.longValue() != 0) {
            	sql.append("   AND IDEMP = ?     \n");
            }
            if (chavePesquisa != null && !"".equals(chavePesquisa)) {
            	sql.append("  AND NOM_PRO LIKE ? \n");
            }
            sql.append("ORDER BY NOM_PRO         \n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            
            ps.setLong(++i, idEntidade.longValue());
            if (idEmpresa != null && idEmpresa.longValue() != 0) {
            	ps.setLong(++i, idEmpresa.longValue());
            }
            if (chavePesquisa != null && !"".equals(chavePesquisa)) {
            	ps.setString(++i, "%"+chavePesquisa+"%");
            }
            
            rs = ps.executeQuery();
            
            list = new ArrayList();

			while (rs.next()) {
                vo = new ItemPesquisaVO();
                vo.setCodigo(rs.getString("IDPRO"));
                vo.setNome(rs.getString("NOM_PRO"));
                list.add(vo);
            }
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
    
}
