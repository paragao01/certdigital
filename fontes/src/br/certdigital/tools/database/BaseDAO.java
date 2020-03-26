package br.certdigital.tools.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import br.certdigital.shared.exception.DAOException;
import br.certdigital.shared.util.GlobalConstants;
import br.certdigital.tools.j2ee.util.ServiceLocator;
import br.certdigital.tools.j2ee.util.ServiceLocatorException;

public abstract class BaseDAO {

	private Logger log = Logger.getLogger(this.getClass());
	
	private final String URL = GlobalConstants.URL,
		 				 PWR = GlobalConstants.PWR,
		 				 USR = GlobalConstants.USR,
		 				 DRV = GlobalConstants.DRV;

	private Connection linkBanco = null;
	
    public BaseDAO() { }

	/**
	 * Metodo que gerencia as conexoes.
	 */
	public Connection getConnection() throws DAOException {
		try {
			Class.forName(DRV);
		    linkBanco = DriverManager.getConnection(URL, USR,PWR);
			return linkBanco;
		} catch (SQLException ex) {
			log.error("Erro no acesso a base de dados : " + ex.getMessage());
			throw new DAOException("Erro no acesso a base de dados : " + ex.getMessage());
		} catch (ClassNotFoundException e) {
			log.error("Erro no look up :  " + e.getMessage());
			throw new DAOException("Erro no look up :  " + e.getMessage());			
		}
	}

	/**
	 * Metodo que gerencia o pool de conexoes.
	 */
	public Connection getConnection1() throws DAOException {
		try {
			log.debug("Solicitacao para uma nova conexao");
			DataSource datasource = ServiceLocator.lookupDataSource(GlobalConstants.JNDI_DS);
			log.debug("Retornando a conexao ");
			return datasource.getConnection();
		} catch (ServiceLocatorException e) {
			log.error("Erro no look up :  " + e.getMessage());
			throw new DAOException("Erro no look up :  " + e.getMessage());
		} catch (SQLException ex) {
			log.error("Erro no acesso a base de dados : " + ex.getMessage());
			throw new DAOException("Erro no acesso a base de dados : " + ex.getMessage());
		}
	}
	
	/**
	 * Metodo que fecha o ResultSet.
	 * @param ResultSet
	 * @return void
	 */
	public void closeResultSet(ResultSet rs) throws DAOException {
		try {
			if (rs != null ) {
              rs.close();
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	/**
	 * Metodo que fecha o Statement.*/
	public void closeStatement(Statement stmt) throws DAOException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	/**
	 * Metodo que fecha a conexao.*/
	public void closeConnection(Connection conn) throws DAOException {
		try {
			if ( (conn != null) && !conn.isClosed() ) {				
//				conn.setAutoCommit(true);
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
    
	public Long getAutoIncrementByEntidadeEmpresa(String table, String rowName, Long idEntidade, Long idEmpresa) throws DAOException {
		long returnId = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = getConnection();
			sql.append(" SELECT MAX( ");
			sql.append(rowName);
			sql.append(" ) ID FROM ");
			sql.append(table);
			sql.append(" WHERE IDENT = ? ");
			sql.append("   AND IDEMP = ? ");

			int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i,idEntidade.longValue());
            ps.setLong(++i,idEmpresa.longValue());
            
			rs = ps.executeQuery();
			
			if (rs.next()) {
				returnId = rs.getLong("ID");
			}
			
			returnId++;
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
		return new Long(returnId);
	}

	public Long getAutoIncrementByProdutoPreco(String table, String rowName, Long idEmpresa, Long idProduto) throws DAOException {
		long returnId = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = getConnection();
			sql.append(" SELECT MAX( ");
			sql.append(rowName);
			sql.append(" ) ID FROM ");
			sql.append(table);
			sql.append(" WHERE IDEMP = ? ");
			sql.append("   AND IDPRO = ? ");

			int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i,idEmpresa.longValue());
            ps.setLong(++i,idProduto.longValue());
            
			rs = ps.executeQuery();
			
			if (rs.next()) {
				returnId = rs.getLong("ID");
			}
			
			returnId++;
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
		return new Long(returnId);
	}
	
	public Long getAutoIncrementByEntidade(String table, String rowName, Long idEntidade) throws DAOException {
		long returnId = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = getConnection();
			sql.append(" SELECT MAX( ");
			sql.append(rowName);
			sql.append(" ) ID FROM ");
			sql.append(table);
			sql.append(" WHERE IDENT = ? ");

			int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i,idEntidade.longValue());
            
			rs = ps.executeQuery();
			
			if (rs.next()) {
				returnId = rs.getLong("ID");
			}
			
			returnId++;
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
		return new Long(returnId);
	}


	public Long getAutoIncrement(String table, String rowName)	throws DAOException {
		long returnId = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = getConnection();
			sql.append(" SELECT MAX( ");
			sql.append(rowName);
			sql.append(" ) ID FROM ");
			sql.append(table);
            
            ps = conn.prepareStatement(sql.toString());
    		rs = ps.executeQuery();
			
			if (rs.next()) {
				returnId = rs.getLong("ID");
			}
			
			returnId++;
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
		return new Long(returnId);
	}
	
	// captura o codigo(PK) do registro recem-incluido na tabela
	public long getUltimoCodigo(PreparedStatement pst) throws DAOException {
	    ResultSet rs = null;
	    try {
	        rs = pst.getGeneratedKeys();
	        long codigo = 0;
	        while (rs.next()) {
	            codigo = rs.getLong(1);
	        }
	        return codigo;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new DAOException(e.getMessage());
	    }
	}
	
	/*
	 * Seta objetos em PreparedStatement verificando se nulo
	 * @param PreparedStatement pstmt - Statement alvo
	 * @param int dataType - um dos valores em java.sql.Types
	 * @param int idx - indice do parametro no sql
	 * @param Object obj - valor que sera setado no PreparedStatement
	 */
	public static void setObject(PreparedStatement pstmt, int dataType, int idx, Object obj) throws SQLException {
		if ( obj == null){
			pstmt.setNull(idx, dataType); 
	    } else {
	    	pstmt.setObject(idx, obj); 
	    }
	}
	public Date getDataHoje(){
		Date dataHoje = new Date();
		dataHoje.getTime();
		return dataHoje;
	}
}
