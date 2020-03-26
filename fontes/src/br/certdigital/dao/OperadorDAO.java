package br.certdigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import br.certdigital.vo.OperadorVO;
import br.certdigital.vo.TipoOperadorVO;

/**
 * Business DAO para cadastro de operadores
 */
public class OperadorDAO extends BaseDAO {
	
	//private Logger log = Logger.getLogger(this.getClass());
	
	public OperadorDAO(){}

	/**
	 * Pesquisa todos os operadores de uma Unidade de Negocio 
	 * 
	 * @param idUnidadeNegocio
	 * @param idGestor
	 * @param idEmpresa
	 * @param idOperador
	 */
    public List pesquisar(Long idEntidade, Long idEmpresa, Long idOperador,String status, OperadorVO operadorVo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        OperadorVO vo = null;
        List list = Collections.EMPTY_LIST;
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT OPE.IDOPE,            \n");
        	sql.append("     	ENT.NOM_COM,          \n");
            sql.append("     	OPE.NOM_OPE,          \n");
            sql.append("     	OPE.STA_OPE,          \n");
            sql.append("     	OPE.IDEMP,            \n");
            sql.append("     	EMP.NOM_COM AS EMPRESA\n");
            sql.append("   FROM OPE, EMP		      \n");
            sql.append("   LEFT OUTER JOIN ENT   	  \n");
            sql.append("   ON ENT.IDENT = EMP.IDENT   \n");            
            sql.append("  WHERE OPE.IDENT = ENT.IDENT \n");
            sql.append("    AND OPE.IDEMP = EMP.IDEMP \n");
            if(operadorVo.getIdTipoOperador().longValue()!=GlobalConstants.OPERADOR_MASTER.longValue()) {
            	sql.append("  AND OPE.IDENT = ?   \n");
            }
            if (idEmpresa != null && idEmpresa.longValue() != 0) {
            	sql.append("   AND OPE.IDEMP = ? \n");
            }
            if (idOperador != null && idOperador.longValue() != 0) {
            	sql.append("   AND OPE.IDOPE = ? \n");
            }
            if(status.trim().length()>0&&status!=null){
                sql.append("   AND OPE.STA_OPE = ?	\n");
            }
            
            sql.append("ORDER BY OPE.NOM_OPE        \n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
             
            if(operadorVo.getIdTipoOperador().longValue()!=GlobalConstants.OPERADOR_MASTER.longValue()) {
            	ps.setLong(++i, idEntidade.longValue());
            }
            
            if (idEmpresa != null && idEmpresa.longValue() != 0) {
            	ps.setLong(++i, idEmpresa.longValue());
            }
            
            if (idOperador != null && idOperador.longValue() != 0) {
            	ps.setLong(++i, idOperador.longValue());
            }
                        
            if(status.trim().length()>0&&status!=null){
            	ps.setString(++i, status);            	
            }
            
            rs = ps.executeQuery();
            
            list = new ArrayList();

			while (rs.next()) {
                vo = new OperadorVO();
                vo.setIdOperador(Parser.parseLong(rs.getBigDecimal("IDOPE")));
                vo.setNomeEntidade(rs.getString("NOM_COM"));
                vo.setNomeOperador(rs.getString("NOM_OPE"));
                vo.setStatusOperador(rs.getString("STA_OPE"));
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
	 * Pesquisa todos os tipos de operadores 
	 *
	 * @return List 
	 */
    public List pesquisarTipoOperador(Long idTipoOperador) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        TipoOperadorVO vo = null;
        List list = Collections.EMPTY_LIST;
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT IDTIP,    \n");
        	sql.append("     	NOM_TIP   \n");
            sql.append("   FROM TIP_OPE   \n");
            sql.append("ORDER BY NOM_TIP  \n");
            
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            
            list = new ArrayList();

			while (rs.next()) {
                vo = new TipoOperadorVO();
                vo.setCodigoTipoOperador(Parser.parseLong(rs.getBigDecimal("IDTIP")));
                vo.setDescricaoTipoOperador(rs.getString("NOM_TIP"));
                if(vo.getCodigoTipoOperador().longValue()==1) {
                	if(idTipoOperador.longValue()==1) {
                		list.add(vo);
                	}
                }else{
                	list.add(vo);
                }
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
	 * Pesquisa um determinado operador 
	 * 
	 * @param idOperador
	 */
    public OperadorVO pesquisarOperador(Long idOperador) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        OperadorVO vo = new OperadorVO();
        
        try {
        	
        	conn = this.getConnection();

        	sql.append(" SELECT OPE.IDOPE,				\n");
            sql.append("     	OPE.NOM_OPE,			\n");
            sql.append("     	OPE.STA_OPE,			\n");
            sql.append("     	OPE.SNH,				\n");
            sql.append("     	OPE.IDTIP,				\n");
        	sql.append("     	ENT.IDENT,				\n");
        	sql.append("     	ENT.NOM_COM AS NOM_ENT, \n");        	
        	sql.append("     	EMP.IDEMP,				\n");
        	sql.append("     	EMP.NOM_COM AS NOM_EMP  \n");
        	sql.append("   FROM OPE, ENT, EMP			\n");
        	sql.append("  WHERE OPE.IDENT = ENT.IDENT	\n");
        	sql.append("    AND OPE.IDEMP = EMP.IDEMP	\n"); 
            sql.append("    AND OPE.IDOPE    = ?		\n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i, idOperador.longValue());
            
            rs = ps.executeQuery();
            
			while (rs.next()) {
                vo.setIdOperador(Parser.parseLong(rs.getBigDecimal("IDOPE")));
                vo.setNomeOperador(rs.getString("NOM_OPE"));                
                vo.setStatusOperador(rs.getString("STA_OPE"));
                vo.setSenha(rs.getString("SNH"));
                vo.setIdTipoOperador(Parser.parseLong(rs.getBigDecimal("IDTIP")));
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
	 * Pesquisa um determinado operador e coloca as definicoes dos menus no objeto
	 * 
	 * @param idOperador
	 */
    public OperadorVO pesquisarOperadorMenus(Long idOperador) throws DAOException {
    	
    	OperadorVO vo = this.pesquisarOperador(idOperador);
    	
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        
        try {
        	
        	conn = this.getConnection();

        	sql.append("SELECT IDACESSO,   \n");
            sql.append("       ID_TIP_OPE, \n");
            sql.append("       IDOPE,      \n");
            sql.append("       ID_ITEM,    \n");
            sql.append("       ACESSO      \n");
        	sql.append("  FROM ACESSO      \n");
        	
        	if (vo.getIdTipoOperador().longValue()==3) { //Administrativo
        		sql.append("WHERE IDOPE = ? \n");
        	}
        	else { // Master (1) ou Operador Empresa (2)
        		sql.append("WHERE ID_TIP_OPE = ? \n");
        	}
        	
        	sql.append("ORDER BY ID_ITEM \n");
        	
        	ps = conn.prepareStatement(sql.toString());
            
        	if (vo.getIdTipoOperador().longValue()==3) { // Administrativo
        		ps.setLong(1, vo.getIdOperador().longValue());
        	} else { // Master (1) ou Operador Empresa (2)
        		ps.setLong(1, vo.getIdTipoOperador().longValue());
        	}

            rs = ps.executeQuery();
            
            rs.next(); // "Pula" o item Gerencial
            
            rs.next(); vo.getMenuGerencialVO().setAlterarEntidade(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuGerencialVO().setConsultarEntidade(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuGerencialVO().setAlterarEmpresa(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuGerencialVO().setConsultarEmpresa(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuGerencialVO().setAlterarProduto(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuGerencialVO().setConsultarProduto(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuGerencialVO().setAlterarPreco(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuGerencialVO().setConsultarPreco(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuGerencialVO().setConsultarCertificado(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuGerencialVO().setConsultarTabelaPreco(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuGerencialVO().setConsultarFaturamento(getBoolean(rs.getInt("ACESSO")));
            
            rs.next(); // "Pula" o item Operacional"
            
            rs.next(); vo.getMenuOperacionalVO().setAlterarOperador(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuOperacionalVO().setConsultarOperador(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuOperacionalVO().setAlterarCertificado(getBoolean(rs.getInt("ACESSO")));
            rs.next(); vo.getMenuOperacionalVO().setConsultarCertificado(getBoolean(rs.getInt("ACESSO")));
            
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
    
    public boolean getBoolean (int indicador) {
    	
    	if (indicador == 1) {
    		return true;
    	}
    	else return false;
    }
    
	/**
	 * Insere um operador
	 * 
	 * @param vo
	 * @return
	 * @throws DAOException
	 */
	public Long insert(OperadorVO vo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        
        Long nextId;
        
        try {
    
        	conn = this.getConnection();

            sql.append(" INSERT INTO OPE (IDOPE, IDTIP, NOM_OPE, SNH, STA_OPE, IDENT, IDEMP, OPE_INC,      DTA_INC, DTA_ALT, OPE_ALT  \n)");
            sql.append("          VALUES (    ?,     ?,       ?,   ?,       ?,     ?,     ?,       ?, CURRENT_DATE,    NULL,    NULL) \n");
            
            int i=0;
            
            nextId = this.getAutoIncrement("OPE","IDOPE");
            
            vo.setIdOperador(nextId);
            /*
            System.out.println("IDOPE  : "+vo.getIdOperador());
            System.out.println("TIPOPE : "+vo.getIdTipoOperador());
            System.out.println("NOMOPE : "+vo.getNomeOperador());
            System.out.println("SNHOPE : "+vo.getSenha());
            System.out.println("STAOPE : "+vo.getStatusOperador());
            System.out.println("IDENT  : "+vo.getIdEntidade());
            System.out.println("IDEMP  : "+vo.getIdEmpresa());
            System.out.println("OPEINC : "+vo.getIdOperadorInclusao());
            */
            ps = conn.prepareStatement(sql.toString());
            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdOperador() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdTipoOperador() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getNomeOperador() ) ;
            if(vo.getSenha().length() > 0) {
            	BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getSenha() ) ;
            }else{
            	BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdOperador() ) ;
            }
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getStatusOperador() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEntidade() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEmpresa() ) ;
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
	 * Atualiza um operador
	 * 
	 * @param vo
	 * @return
	 * @throws DAOException
	 */
	public void update(OperadorVO vo) throws DAOException {
        PreparedStatement psUpdate = null;
        PreparedStatement psDelete = null;
        ResultSet rs = null;
        StringBuffer sqlUpdate = new StringBuffer();
        StringBuffer sqlDelete = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();

            sqlUpdate.append(" UPDATE OPE SET IDTIP = ?, NOM_OPE = ?, SNH = ?, STA_OPE = ?, IDENT = ?, IDEMP = ?, OPE_ALT = ?, DTA_ALT = CURRENT_DATE \n");
            sqlUpdate.append("          WHERE IDOPE = ? \n");
            int i=0;

            psUpdate = conn.prepareStatement(sqlUpdate.toString());
            
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getIdTipoOperador() ) ;
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getNomeOperador() ) ;
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getSenha() ) ;
            BaseDAO.setObject(psUpdate,Types.VARCHAR, ++i, vo.getStatusOperador() ) ;
            BaseDAO.setObject(psUpdate,Types.VARCHAR, ++i, vo.getIdEntidade() ) ;
            BaseDAO.setObject(psUpdate,Types.VARCHAR, ++i, vo.getIdEmpresa() ) ;
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getIdOperadorInclusao() ) ;
            
            BaseDAO.setObject(psUpdate,Types.NUMERIC, ++i, vo.getIdOperador() ) ;     
            
            psUpdate.execute();
            
            if(vo.getIdTipoOperador().longValue() < 3) {
            	sqlDelete.append("DELETE FROM ACESSO \n");
            	sqlDelete.append("WHERE IDOPE = ? \n");
 			   
            	psDelete = conn.prepareStatement(sqlDelete.toString());
 			   
            	psDelete.setLong(1, vo.getIdOperador().longValue());
 			   
            	psDelete.executeUpdate();
        	}
		} catch (SQLException e) {
			throw new DatabaseException(e);
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(psUpdate);
				closeStatement(psDelete);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
	}
		
	/**
	 * Define regras de acesso para o Operador
	 * 
	 * @param vo
	 */
	public void defineAcesso(OperadorVO vo) throws DAOException {

		Statement stmt = null;
        Connection conn = null;

        try {
    
        	Long idOperador = this.insert(vo);
        	
        	vo.setIdOperador(idOperador);
        	        	
        	conn = this.getConnection();        	
        	stmt = conn.createStatement();
        	            
        	stmt.addBatch(mountInsertBatchString(vo, 1,  vo.getMenuGerencialVO().atLeastOneTrue())); // Gerencial
        	stmt.addBatch(mountInsertBatchString(vo, 2,  vo.getMenuGerencialVO().isAlterarEntidade()));
        	stmt.addBatch(mountInsertBatchString(vo, 3,  vo.getMenuGerencialVO().isConsultarEntidade()));
        	stmt.addBatch(mountInsertBatchString(vo, 4,  vo.getMenuGerencialVO().isAlterarEmpresa()));
        	stmt.addBatch(mountInsertBatchString(vo, 5,  vo.getMenuGerencialVO().isConsultarEmpresa()));
        	stmt.addBatch(mountInsertBatchString(vo, 6,  vo.getMenuGerencialVO().isAlterarProduto()));
        	stmt.addBatch(mountInsertBatchString(vo, 7,  vo.getMenuGerencialVO().isConsultarProduto()));
        	stmt.addBatch(mountInsertBatchString(vo, 8,  vo.getMenuGerencialVO().isAlterarPreco()));
        	stmt.addBatch(mountInsertBatchString(vo, 9,  vo.getMenuGerencialVO().isConsultarPreco()));
        	stmt.addBatch(mountInsertBatchString(vo, 10, vo.getMenuGerencialVO().isConsultarCertificado()));
        	stmt.addBatch(mountInsertBatchString(vo, 11, vo.getMenuGerencialVO().isConsultarTabelaPreco()));
        	stmt.addBatch(mountInsertBatchString(vo, 12, vo.getMenuGerencialVO().isConsultarFaturamento()));

        	stmt.addBatch(mountInsertBatchString(vo, 13, vo.getMenuOperacionalVO().atLeastOneTrue())); // Operacional          
        	stmt.addBatch(mountInsertBatchString(vo, 14, vo.getMenuOperacionalVO().isAlterarOperador()));
        	stmt.addBatch(mountInsertBatchString(vo, 15, vo.getMenuOperacionalVO().isConsultarOperador()));
        	stmt.addBatch(mountInsertBatchString(vo, 16, vo.getMenuOperacionalVO().isAlterarCertificado()));
        	stmt.addBatch(mountInsertBatchString(vo, 17, vo.getMenuOperacionalVO().isConsultarCertificado()));

        	stmt.executeBatch();
        	
            //int[] updCnt = stmt.executeBatch();
        } catch (SQLException e) {
        	System.out.println("********************* : "+e.getNextException());
			throw new DatabaseException(e);
		} finally {
			try {
				closeStatement(stmt);
				closeConnection(conn);
			} catch (DAOException e) { }
		}
	}
	
	/**
	 * Altera regras de acesso para o Operador
	 * 
	 * @param vo
	 */
	public void alteraAcesso(OperadorVO vo) throws DAOException {

		Statement stmt = null;
		PreparedStatement ps = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;;
        
        try {

        	//this.update(vo);

        	conn = this.getConnection();

        	sql.append("DELETE FROM ACESSO \n");
        	sql.append("WHERE IDOPE = ? \n");
        	
        	ps = conn.prepareStatement(sql.toString());
        	
        	ps.setLong(1, vo.getIdOperador().longValue());
        	
        	ps.executeUpdate();

        	stmt = conn.createStatement();
        	
        	stmt.addBatch(mountInsertBatchString(vo, 1,  vo.getMenuGerencialVO().atLeastOneTrue())); // Gerencial
        	stmt.addBatch(mountInsertBatchString(vo, 2,  vo.getMenuGerencialVO().isAlterarEntidade()));
        	stmt.addBatch(mountInsertBatchString(vo, 3,  vo.getMenuGerencialVO().isConsultarEntidade()));
        	stmt.addBatch(mountInsertBatchString(vo, 4,  vo.getMenuGerencialVO().isAlterarEmpresa()));
        	stmt.addBatch(mountInsertBatchString(vo, 5,  vo.getMenuGerencialVO().isConsultarEmpresa()));
        	stmt.addBatch(mountInsertBatchString(vo, 6,  vo.getMenuGerencialVO().isAlterarProduto()));
        	stmt.addBatch(mountInsertBatchString(vo, 7,  vo.getMenuGerencialVO().isConsultarProduto()));
        	stmt.addBatch(mountInsertBatchString(vo, 8,  vo.getMenuGerencialVO().isAlterarPreco()));
        	stmt.addBatch(mountInsertBatchString(vo, 9,  vo.getMenuGerencialVO().isConsultarPreco()));
        	stmt.addBatch(mountInsertBatchString(vo, 10, vo.getMenuGerencialVO().isConsultarCertificado()));
        	stmt.addBatch(mountInsertBatchString(vo, 11, vo.getMenuGerencialVO().isConsultarTabelaPreco()));
        	stmt.addBatch(mountInsertBatchString(vo, 12, vo.getMenuGerencialVO().isConsultarFaturamento()));

        	stmt.addBatch(mountInsertBatchString(vo, 13, vo.getMenuOperacionalVO().atLeastOneTrue())); // Operacional          
        	stmt.addBatch(mountInsertBatchString(vo, 14, vo.getMenuOperacionalVO().isAlterarOperador()));
        	stmt.addBatch(mountInsertBatchString(vo, 15, vo.getMenuOperacionalVO().isConsultarOperador()));
        	stmt.addBatch(mountInsertBatchString(vo, 16, vo.getMenuOperacionalVO().isAlterarCertificado()));
        	stmt.addBatch(mountInsertBatchString(vo, 17, vo.getMenuOperacionalVO().isConsultarCertificado()));
       	
            stmt.executeBatch();
            
            //int[] updCnt = stmt.executeBatch();
        } catch (SQLException e) {
			throw new DatabaseException(e);
		} finally {
			try {
				closeStatement(stmt);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) { }
		}
	}

	private String mountInsertBatchString(OperadorVO vo, int codMenu, boolean acesso) {
		
		String insert = "";
		
			insert = "INSERT INTO ACESSO (" +
							"ID_TIP_OPE, "  +
							"IDOPE, "      +
							"ID_ITEM, "     +
							"ACESSO) \n"    +
							"VALUES ( "     +
							vo.getIdTipoOperador().longValue() + ", " +
							vo.getIdOperador().longValue() + ", "     +
							codMenu + ", "                            +
							(acesso ? "1" : "0") + ") \n";		
		return insert;
	}
}

