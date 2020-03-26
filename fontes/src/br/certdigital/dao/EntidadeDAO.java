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
import br.certdigital.shared.util.GlobalConstants;
import br.certdigital.shared.util.Parser;
import br.certdigital.tools.database.BaseDAO;
import br.certdigital.vo.ItemEntidadeVO;
import br.certdigital.vo.OperadorVO;

public class EntidadeDAO extends BaseDAO {
	
	//private Logger log = Logger.getLogger(this.getClass());
	
	public EntidadeDAO(){}
	
	
    public ItemEntidadeVO insert(ItemEntidadeVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
            sql.append(" INSERT INTO ENT ");
            sql.append(" (IDENT,NUM_CNPJ,RAZ_SOC,NOM_COM,END_COM,BAIR,DTA_INC,OPE_INC,CID,UF,CEP,NOM_CON,MAIL,DDD_FONE,FONE,DDD_FAX,FAX,DTA_ALT,OPE_ALT,PERC_ASSOCIADO) ");
            sql.append("  VALUES( ?, ?, ?, ?, ?, ?, CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  NULL, NULL, ?)");

            int i=0;

            vo.setIdEntidade(this.getAutoIncrement("ENT","IDENT"));
            
            ps = conn.prepareStatement(sql.toString());
            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEntidade() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumCNPJ() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getRazaoSocial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEnderecoComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getBairro() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getOperadorInlcusao() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getCidade() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEstado() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getCep() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeContato() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getMail() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddFone() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumFone() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddFax() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumFax() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getPerc_associado() ) ;
            
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
	
		return vo;
	}

    public boolean verificarCNPJ(Long numCNPJ, Long idEntidade) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        try {
    
        	conn = this.getConnection();

            sql.append("SELECT 1            \n");
            sql.append("  FROM ENT          \n");
            sql.append(" WHERE NUM_CNPJ = ? \n");
            sql.append("   AND IDENT <> ?   \n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i, numCNPJ.longValue());
            ps.setLong(++i, idEntidade.longValue());
            rs = ps.executeQuery();

			if (rs.next()) {
				return true;
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
	
		return false;
	}

    public boolean verificarCNPJ(Long numCNPJ) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        try {
    
        	conn = this.getConnection();

            sql.append("SELECT 1            \n");
            sql.append("  FROM ENT          \n");
            sql.append(" WHERE NUM_CNPJ = ? \n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i, numCNPJ.longValue());
            rs = ps.executeQuery();

			if (rs.next()) {
				return true;
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
	
		return false;
	}
    
    public void update(ItemEntidadeVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
            sql.append(" UPDATE ENT SET");
            sql.append("    	NUM_CNPJ = ?, ");
            sql.append("     	RAZ_SOC = ?, ");
            sql.append(" 		NOM_COM = ?, ");
            sql.append(" 		END_COM = ?, ");
            sql.append(" 		BAIR = ?, ");
            sql.append(" 		CID = ?, ");
            sql.append(" 		UF = ?, ");
            sql.append(" 		CEP = ?, ");
            sql.append(" 		NOM_CON = ?, ");
            sql.append(" 		MAIL = ?, ");
            sql.append(" 		DDD_FONE = ?, ");
            sql.append(" 		FONE = ?, ");
            sql.append(" 		DDD_FAX = ?, ");
            sql.append(" 		FAX = ?, ");
            sql.append(" 		DTA_ALT = CURRENT_DATE, ");
            sql.append(" 		OPE_ALT = ?, ");
            sql.append(" 		PERC_ASSOCIADO = ? ");
            sql.append(" WHERE  IDENT = ? ");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumCNPJ() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getRazaoSocial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEnderecoComercial() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getBairro() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getCidade() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEstado() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getCep() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeContato() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getMail() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddFone() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumFone() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddFax() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNumFax() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getOperadorAlteracao() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getPerc_associado() ) ;            
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
    
    public List pesquisar(OperadorVO operadorVo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        List itens = Collections.EMPTY_LIST;
        ItemEntidadeVO vo;

        try {
    
        	conn = this.getConnection();
        	
            sql.append(" SELECT IDENT, ");
            sql.append("    	NUM_CNPJ, ");
            sql.append("     	RAZ_SOC, ");
            sql.append(" 		NOM_COM, ");
            sql.append(" 		END_COM, ");
            sql.append(" 		BAIR, ");
            sql.append("		DTA_INC, ");
            sql.append(" 		OPE_INC, ");
            sql.append(" 		CID, ");
            sql.append(" 		UF, ");
            sql.append(" 		CEP, ");
            sql.append(" 		NOM_CON, ");
            sql.append(" 		MAIL, ");
            sql.append(" 		DDD_FONE, ");
            sql.append(" 		FONE, ");
            sql.append(" 		DDD_FAX, ");
            sql.append(" 		FAX, ");
            sql.append(" 		DTA_ALT, ");
            sql.append(" 		OPE_ALT, ");
            sql.append(" 		PERC_ASSOCIADO ");            
            sql.append(" 		FROM ENT    ");
            
            if(operadorVo.getIdTipoOperador().longValue()!=GlobalConstants.OPERADOR_MASTER.longValue()){
            	sql.append(" WHERE  IDENT = ? ");
            }
            sql.append(" ORDER BY NOM_COM ");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            if(operadorVo.getIdTipoOperador().longValue()!=GlobalConstants.OPERADOR_MASTER.longValue()){            
            	ps.setLong(++i,operadorVo.getIdEntidade().longValue());
            }
            
            rs = ps.executeQuery();

			if (rs.next()) {
				
				itens = new ArrayList();

				do {
	                vo = new ItemEntidadeVO();
	                vo.setIdEntidade(Parser.parseLong(rs.getBigDecimal("IDENT")));
	                vo.setNumCNPJ(Parser.parseLong(rs.getBigDecimal("NUM_CNPJ")));
	                vo.setRazaoSocial(rs.getString("RAZ_SOC"));
	                vo.setNomeComercial(rs.getString("NOM_COM"));
	                vo.setEnderecoComercial(rs.getString("END_COM"));
	                vo.setBairro(rs.getString("BAIR"));
	                vo.setDataInclusao(Parser.parseDate(rs.getTimestamp("DTA_INC")));
	                vo.setOperadorInlcusao(Parser.parseLong(rs.getBigDecimal("OPE_INC")));
	                vo.setCidade(rs.getString("CID"));
	                vo.setEstado(rs.getString("UF"));
	                vo.setCep(Parser.parseLong(rs.getBigDecimal("CEP")));
	                vo.setNomeContato(rs.getString("NOM_CON"));
	                vo.setMail(rs.getString("MAIL"));
	                vo.setDddFone(Parser.parseLong(rs.getBigDecimal("DDD_FONE")));
	                vo.setNumFone(Parser.parseLong(rs.getBigDecimal("FONE")));
	                vo.setDddFax(Parser.parseLong(rs.getBigDecimal("DDD_FAX")));
	                vo.setNumFax(Parser.parseLong(rs.getBigDecimal("FAX")));
	                vo.setDataAlteracao(Parser.parseDate(rs.getTimestamp("DTA_ALT")));
	                vo.setOperadorAlteracao(Parser.parseLong(rs.getBigDecimal("OPE_ALT")));
	                vo.setPerc_associado(Parser.parseLong(rs.getBigDecimal("PERC_ASSOCIADO")));
	                
					itens.add(vo);
				} while (rs.next());
				
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
	
		return itens;
	}

    public ItemEntidadeVO pesquisarEntidade(Long idEntidade) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        ItemEntidadeVO vo = null;

        try {
    
        	conn = this.getConnection();
        	
            sql.append(" SELECT IDENT, ");
            sql.append("    	NUM_CNPJ, ");
            sql.append("     	RAZ_SOC, ");
            sql.append(" 		NOM_COM, ");
            sql.append(" 		END_COM, ");
            sql.append(" 		BAIR, ");
            sql.append("		DTA_INC, ");
            sql.append(" 		OPE_INC, ");
            sql.append(" 		CID, ");
            sql.append(" 		UF, ");
            sql.append(" 		CEP, ");
            sql.append(" 		NOM_CON, ");
            sql.append(" 		MAIL, ");
            sql.append(" 		DDD_FONE, ");
            sql.append(" 		FONE, ");
            sql.append(" 		DDD_FAX, ");
            sql.append(" 		FAX, ");
            sql.append(" 		DTA_ALT, ");
            sql.append(" 		OPE_ALT, ");
            sql.append(" 		PERC_ASSOCIADO ");
            sql.append(" 		FROM ENT    ");      	
            sql.append(" WHERE  IDENT = ? ");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i,idEntidade.longValue());
            rs = ps.executeQuery();

			if (rs.next()) {
				
	                vo = new ItemEntidadeVO();
	                vo.setIdEntidade(Parser.parseLong(rs.getBigDecimal("IDENT")));
	                vo.setNumCNPJ(Parser.parseLong(rs.getBigDecimal("NUM_CNPJ")));
	                vo.setRazaoSocial(rs.getString("RAZ_SOC"));
	                vo.setNomeComercial(rs.getString("NOM_COM"));
	                vo.setEnderecoComercial(rs.getString("END_COM"));
	                vo.setBairro(rs.getString("BAIR"));
	                vo.setDataInclusao(Parser.parseDate(rs.getTimestamp("DTA_INC")));
	                vo.setOperadorInlcusao(Parser.parseLong(rs.getBigDecimal("OPE_INC")));
	                vo.setCidade(rs.getString("CID"));
	                vo.setEstado(rs.getString("UF"));
	                vo.setCep(Parser.parseLong(rs.getBigDecimal("CEP")));
	                vo.setNomeContato(rs.getString("NOM_CON"));
	                vo.setMail(rs.getString("MAIL"));
	                vo.setDddFone(Parser.parseLong(rs.getBigDecimal("DDD_FONE")));
	                vo.setNumFone(Parser.parseLong(rs.getBigDecimal("FONE")));
	                vo.setDddFax(Parser.parseLong(rs.getBigDecimal("DDD_FAX")));
	                vo.setNumFax(Parser.parseLong(rs.getBigDecimal("FAX")));
	                vo.setDataAlteracao(Parser.parseDate(rs.getTimestamp("DTA_ALT")));
	                vo.setOperadorAlteracao(Parser.parseLong(rs.getBigDecimal("OPE_ALT")));
	                vo.setPerc_associado(Parser.parseLong(rs.getBigDecimal("PERC_ASSOCIADO")));     
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
    
    public void remove(ItemEntidadeVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
            sql.append(" DELETE FROM ENT ");
            sql.append(" WHERE IDENT = ? ");
            
            int i=0;

            ps = conn.prepareStatement(sql.toString());

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
