/*
 * Created on 12/04/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
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
import br.certdigital.shared.exception.ParserException;
import br.certdigital.shared.util.Parser;
import br.certdigital.tools.database.BaseDAO;
import br.certdigital.vo.OperadorVO;


/**
 * @author elisio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SecurityDAO extends BaseDAO {
	
	//private Logger log = Logger.getLogger(this.getClass());
	
	
	public SecurityDAO(){}
	
	/**
	 * Busca todos os acessos do operador
	 * @param idOperador
	 * @return List
	 * @throws DAOException
	 */
	public List getAccess(Long idOperador) throws DAOException {
		
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        List itens = Collections.EMPTY_LIST;

        try {
    
        	conn = this.getConnection();
        	        	
        	sql.append(" SELECT NOM_ACT ");
        	sql.append(" FROM   OPE, ");
        	sql.append("        TIP_OPE, ");
        	sql.append("        RLE_ACT, ");
        	sql.append("        ACT ");
        	sql.append(" WHERE IDOPE = ? ");
        	sql.append(" AND TIP_OPE.IDTIP = OPE.IDTIP ");
        	sql.append(" AND RLE_ACT.IDRLE = TIP_OPE.IDRLE ");
        	sql.append(" AND ACT.IDACT = RLE_ACT.IDACT ");
        	
        	int i = 0;
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i,idOperador.longValue());
            rs = ps.executeQuery();

			if (rs.next()) {
				
				itens = new ArrayList();

				do {
					itens.add(rs.getString("NOM_ACT"));
				} while (rs.next());
				
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
	
		return itens;
	}

	/**
	 * 
	 * @param idOperador
	 * @param senha
	 * @return boolean (false caso nao autenticado)
	 * @throws DAOException
	 */
	public boolean verificaOperadorSenha(Long idOperador, String senha) throws DAOException {
		
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        boolean validacao = false;

        try {
    
        	conn = this.getConnection();
        	
        	sql.append(" SELECT IDOPE,			\n");
        	sql.append("        IDTIP,			\n");
        	sql.append("        NOM_OPE			\n");
        	sql.append("   FROM OPE				\n");
        	sql.append("  WHERE IDOPE = ?		\n");
        	sql.append("    AND STA_OPE = 'A'	\n");
        	sql.append("    AND SNH LIKE ?		\n");
        	
        	int i = 0;
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i,idOperador.longValue());
            ps.setString(++i,senha);
            rs = ps.executeQuery();
                        
			if (rs.next()) {
				return !validacao;
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
	
		return validacao;
	}

	/**
	 * Busca todas as informacoes do operador para serem colocadas na sessao
	 * @param idOperador
	 * @return
	 * @throws DAOException
	 */
	public OperadorVO buscaInfOperador(Long idOperador) throws DAOException {
		
        PreparedStatement psOpe = null;
        ResultSet rsOpe = null;
        StringBuffer sqlOpe = new StringBuffer();
        PreparedStatement psMenu = null;
        ResultSet rsMenu = null;
        StringBuffer sqlMenu = new StringBuffer();
        Connection conn = null;
        List itens = Collections.EMPTY_LIST;
        OperadorVO vo = null;

        try {
        	conn = this.getConnection();
        	
        	sqlOpe.append(" SELECT OPE.IDOPE, 	\n");
        	sqlOpe.append(" TIP_OPE.IDTIP, 		\n");
        	sqlOpe.append(" TIP_OPE.NOM_MNU, 	\n");
        	sqlOpe.append(" OPE.NOM_OPE, 		\n");
        	sqlOpe.append(" TIP_OPE.NOM_TIP, 	\n");
        	sqlOpe.append(" OPE.DTA_INC, 		\n");
        	sqlOpe.append(" OPE.OPE_INC, 		\n");
        	sqlOpe.append(" OPE.STA_OPE, 		\n");
        	sqlOpe.append(" OPE.IDENT, 			\n");
        	sqlOpe.append(" OPE.IDEMP 			\n");
        	sqlOpe.append(" FROM OPE            \n");
        	sqlOpe.append(" LEFT OUTER JOIN TIP_OPE      \n");
        	sqlOpe.append(" ON TIP_OPE.IDTIP = OPE.IDTIP \n");        	
        	sqlOpe.append(" WHERE OPE.IDOPE = ? 		 \n");
        			
        	int i = 0;
            psOpe = conn.prepareStatement(sqlOpe.toString());
            psOpe.setLong(++i,idOperador.longValue());
            rsOpe = psOpe.executeQuery();
            
			if (rsOpe.next()) {
				
				vo = new OperadorVO();
				
				vo.setIdOperador(Parser.parseLong(rsOpe.getBigDecimal("IDOPE")));
				vo.setIdTipoOperador(Parser.parseLong(rsOpe.getBigDecimal("IDTIP")));
				vo.setNomeMenu(rsOpe.getString("NOM_MNU"));
				vo.setNomeOperador(rsOpe.getString("NOM_OPE"));
				vo.setNomeTipoOperador(rsOpe.getString("NOM_TIP"));
				vo.setDataInc(Parser.parseDate(rsOpe.getTimestamp("DTA_INC")));
				vo.setIdOperadorInclusao(Parser.parseLong(rsOpe.getBigDecimal("OPE_INC")));
				vo.setStatusOperador(rsOpe.getString("STA_OPE"));
				vo.setIdEntidade(Parser.parseLong(rsOpe.getBigDecimal("IDENT")));
				vo.setIdEmpresa(Parser.parseLong(rsOpe.getBigDecimal("IDEMP")));
				
				//busca os acessos permitidos ao usuario
				itens = this.getAccess(idOperador);
				vo.setListaAccess(itens);
				
				vo.setListaAccess(itens);
				
				// Busca as informacoes de acesso dos Menus
				sqlMenu.append("SELECT IDACESSO,    \n");
	            sqlMenu.append("     	ID_TIP_OPE, \n");
	            sqlMenu.append("     	IDOPE,      \n");
	            sqlMenu.append("     	ID_ITEM,    \n");
	            sqlMenu.append("     	ACESSO      \n");
	        	sqlMenu.append("FROM ACESSO         \n");
	        	
	        	if (vo.getIdTipoOperador().longValue()==3) { // Administrativo(3)
	        		sqlMenu.append("WHERE IDOPE = ? \n");
	        	}
	        	else { // Master(1) ou Operador Em	presa(2)
	        		sqlMenu.append("WHERE ID_TIP_OPE = ? \n");
	        	}
	        	sqlMenu.append("ORDER BY ID_ITEM \n");
	        	
	        	psMenu = conn.prepareStatement(sqlMenu.toString());
	            
	        	if (vo.getIdTipoOperador().longValue()==3) { // Administrativo(3)
	        		psMenu.setLong(1, vo.getIdOperador().longValue());
	        	}
	        	else { // Master(1) ou Operador Empresa(2)
	        		psMenu.setLong(1, vo.getIdTipoOperador().longValue());
	        	}
	            	        	
	            rsMenu = psMenu.executeQuery();
	            
	            rsMenu.next(); // "Pula" o item Gerencial
	            
	            rsMenu.next(); vo.getMenuGerencialVO().setAlterarEntidade(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuGerencialVO().setConsultarEntidade(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuGerencialVO().setAlterarEmpresa(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuGerencialVO().setConsultarEmpresa(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuGerencialVO().setAlterarProduto(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuGerencialVO().setConsultarProduto(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuGerencialVO().setAlterarPreco(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuGerencialVO().setConsultarPreco(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuGerencialVO().setConsultarCertificado(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuGerencialVO().setConsultarTabelaPreco(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuGerencialVO().setConsultarFaturamento(getBoolean(rsMenu.getInt("ACESSO")));
	            
	            rsMenu.next(); // "Pula" o item Operacional"
	            
	            rsMenu.next(); vo.getMenuOperacionalVO().setAlterarOperador(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuOperacionalVO().setConsultarOperador(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuOperacionalVO().setAlterarCertificado(getBoolean(rsMenu.getInt("ACESSO")));
	            rsMenu.next(); vo.getMenuOperacionalVO().setConsultarCertificado(getBoolean(rsMenu.getInt("ACESSO")));
	            
            }
			
		} catch (ParserException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		} finally {
			try {
				closeResultSet(rsOpe);
				closeStatement(psOpe);
				closeResultSet(rsMenu);
				closeStatement(psMenu);
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
	 * Busca empresa do Operador
	 * @param idOperador
	 * @return
	 * @throws DAOException
	 */
	public String buscaEmpOperador(Long idUniNgc, Long idOperador) throws DAOException {
		String Emp;
		
		// 1. Etapa Verificar todos operadores subordinados - recursivo
				
		Emp = "";
		
		PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        
        boolean supervisor = true;
        
        int tamope       = 0;
        int indope       = 0;
        int operadores[] = new int[1024];        
        
        operadores[indope] = idOperador.intValue();
        indope++;
        tamope++;
        int indpesquisa = 0;
        
        try {
        	supervisor = true;
        	
    		conn = this.getConnection();  
        	
        	while (supervisor) {		
        		sql = new StringBuffer();
        		sql.append(" SELECT IDOPE  				\n");
        		sql.append(" FROM  OPE  				\n");
        		sql.append(" WHERE IDUNDNGC = ? 		\n");
        		sql.append("   AND OPE_SUPERVISOR = ?	\n");
        		sql.append("   AND IDOPE <> ? 			\n");        		
        		
        		int i = 0;
        		
        		ps = conn.prepareStatement(sql.toString());
        		ps.setLong(++i,idUniNgc.longValue());
        		ps.setLong(++i,operadores[indpesquisa]);
        		ps.setLong(++i,operadores[indpesquisa]);
        		
        		rs = ps.executeQuery();   
        		if (rs.next()) {	
        			do { 
        				operadores[indope] = rs.getBigDecimal("IDOPE").intValue();
						//System.out.println("Achou "+rs.getBigDecimal("IDOPE").intValue());        				
        				indope++;
        				tamope++;
        			}
        			while (rs.next());
        		}
        		
				indpesquisa++;
				
				if (indpesquisa == tamope) {	
				   supervisor = false;
				}else{	
				   supervisor = true;
				}
				closeResultSet(rs);
				closeStatement(ps);				
        	}
				
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
		if (tamope > 0) {
			indope = 0;
			int tamemp       = 0;
			int indemp       = 0;
			int empresas[] = new int[1024];
         
			String Operadoresbusca = "(";
			for (indope=0; indope < tamope; indope++) {	
				if (indope > 0)  Operadoresbusca += ", ";		
				Operadoresbusca += operadores[indope]; 
			}	 
			Operadoresbusca += ")";
	
			try {
				sql = new StringBuffer();
				sql.append(" SELECT IDEMP				\n");      	
				sql.append(" FROM   EMP					\n");
				sql.append(" WHERE  IDUNDNGC = ?		\n");
				sql.append("   AND  OPE_GESTOR_CONTA IN	\n");				
				sql.append(Operadoresbusca);
    		
        		int j = 0;				
				ps = conn.prepareStatement(sql.toString());
        		ps.setLong(++j,idUniNgc.longValue());				
				//System.out.println("Pesquisando Empresas"+sql.toString());
    		
				rs = ps.executeQuery();   
				if (rs.next())	{	
					do { 
						empresas[indemp] = rs.getBigDecimal("IDEMP").intValue();
						indemp++;
						tamemp++;
					}
					while (rs.next());
				}	
			
				if (tamemp > 0)	{	
					String Empresabusca = "(";
					for (indemp=0; indemp < tamemp; indemp++) {	
						if (indemp > 0)  Empresabusca += ", ";		
						Empresabusca += empresas[indemp]; 
					}	 
					Empresabusca += ")";
					Emp = Empresabusca;
					//System.out.println("Achou empresas"+Empresabusca);
				}
				closeResultSet(rs);
				closeStatement(ps);			
			}catch (SQLException e) {     
				throw new DatabaseException(e);
			}			
        }
		
		closeConnection(conn);
		
		return Emp;
	}

}
