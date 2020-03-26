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
import br.certdigital.view.ConsultarHelper;
import br.certdigital.vo.ItemConsultaVO;
import br.certdigital.vo.OperadorVO;


/**
 * Business DAO para consultas
 * 
 * @author aragao
 */
public class ConsultarDAO extends BaseDAO {
		
	public ConsultarDAO(){}
 
    public List consultarCertificado(ConsultarHelper helper, OperadorVO operadorVo) throws DAOException, ParserException {
        PreparedStatement ps = null;
        ResultSet rsPro = null;
        ResultSet rsPre = null;        
        StringBuffer sqlPro = new StringBuffer();
        StringBuffer sqlPre = new StringBuffer();
        Connection conn = null;
        ItemConsultaVO vo = null;
        List list = Collections.EMPTY_LIST;
        
        try {
        	
        	conn = this.getConnection();

        	sqlPro.append("  SELECT CER.DOCUMENTO,									\n");
        	sqlPro.append("         CER.TIPO_PESSOA,								\n");
        	sqlPro.append("         CER.RAZAO_REQUERENTE,							\n");        	
        	sqlPro.append("         PRO.IDPRO,										\n");
        	sqlPro.append("         PRO.TIPO,										\n");        	
        	sqlPro.append("         PRO.NOM_PRO,									\n");
        	sqlPro.append("         PRO.VALIDADE,									\n");
        	sqlPro.append("         CER.NOME_RESPONSAVEL,							\n");
        	sqlPro.append("         CER.EMAIL_RESPONSAVEL,							\n");
        	sqlPro.append("         CER.DDD_REQUERENTE,								\n");
        	sqlPro.append("         CER.TEL_REQUERENTE,								\n");
        	sqlPro.append("         CER.DATA_EMISSAO,								\n");
        	sqlPro.append("         CER.ASSOCIADO									\n");        	
        	sqlPro.append("    FROM CER												\n");
        	sqlPro.append("   INNER JOIN PRO										\n");
        	sqlPro.append("      ON CER.IDPRO = PRO.IDPRO							\n");
        	sqlPro.append("   WHERE CER.IDENT = PRO.IDENT							\n");
        	sqlPro.append("     AND CER.IDEMP = PRO.IDENT							\n");
        	sqlPro.append("     AND EXTRACT(MONTH FROM DATA_EMISSAO) = ?			\n");
        	sqlPro.append("     AND EXTRACT(YEAR  FROM DATA_EMISSAO) +			    \n");
        	sqlPro.append("         CAST(COALESCE(VALIDADE, '0') AS INTEGER) <= ?	\n");
        	sqlPro.append("     AND CER.IDENT = ?									\n");
        	sqlPro.append("     AND CER.IDEMP = ?									\n");        	
        	sqlPro.append("ORDER BY CEr.DATA_EMISSAO DESC						 	\n");
        	
            
        	sqlPre.append(" SELECT PRECO_ENTIDADE, 			\n");
            sqlPre.append("        PRECO_SUGERIDO  			\n");
            sqlPre.append("   FROM PRE             			\n");
            sqlPre.append("  WHERE IDENT = ? 	   			\n");
            sqlPre.append("    AND IDEMP = ? 				\n");            
            sqlPre.append("    AND IDPRO = ?       			\n");
            sqlPre.append("  ORDER BY DATA_REFERENCIA DESC 	\n");
            
            int i=0;
            
            ps = conn.prepareStatement(sqlPro.toString());
           	ps.setLong(++i, helper.getMes());
           	ps.setLong(++i, helper.getAno());
           	ps.setLong(++i, operadorVo.getIdEntidade().longValue());
           	ps.setLong(++i, operadorVo.getIdEmpresa().longValue());
          
            rsPro = ps.executeQuery();
            
            list = new ArrayList();
            
			while (rsPro.next()) {
                vo = new ItemConsultaVO();
                
                i=0;
                ps = conn.prepareStatement(sqlPre.toString());
               	ps.setLong(++i, operadorVo.getIdEntidade().longValue());
               	ps.setLong(++i, operadorVo.getIdEmpresa().longValue());                
               	ps.setLong(++i, Parser.parseLong(rsPro.getBigDecimal("IDPRO")).longValue());
               	
               	rsPre = ps.executeQuery();
               	
               	if (rsPre.next()) {
               		vo.setDocumento(Parser.parseLong(rsPro.getBigDecimal("DOCUMENTO")));
               		vo.setTipo_pessoa(rsPro.getString("TIPO_PESSOA"));               		
               		vo.setRazaoRequerente(rsPro.getString("RAZAO_REQUERENTE"));
               		vo.setTipoProduto(rsPro.getString("TIPO"));
               		vo.setNomeProduto(rsPro.getString("NOM_PRO"));
               		vo.setValidadeProduto(rsPro.getString("VALIDADE"));
               		vo.setNomeResponsavel(rsPro.getString("NOME_RESPONSAVEL"));
               		vo.setEmailResponsavel(rsPro.getString("EMAIL_RESPONSAVEL"));
                    vo.setDddRequerente(Parser.parseLong(rsPro.getBigDecimal("DDD_REQUERENTE")));
                    vo.setTelRequerente(Parser.parseLong(rsPro.getBigDecimal("TEL_REQUERENTE")));
                    vo.setDataEmissao(rsPro.getDate("DATA_EMISSAO")); 
                    vo.setAssociado(rsPro.getString("ASSOCIADO"));
                    //Verifica se eh associado, se sim busca o percentual na tabela ent
                    if(vo.getAssociado().equals("S")) {
                    	vo.setPercentual(pesquisarEntidade(operadorVo.getIdEntidade()));
                    }
                    vo.setCustoSugerido(Parser.parseDouble(rsPre.getBigDecimal("PRECO_SUGERIDO")));

               		list.add(vo);
               	}
				closeResultSet(rsPre);
            }
		} catch (SQLException e) {
			throw new DatabaseException(e);
		} catch (ParserException e) {
			throw new ParserException(e); 
		} finally {
			try {
				closeResultSet(rsPro);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
	
		return list;
	}
	
    public List consultarTabelaPreco(ConsultarHelper helper, OperadorVO operadorVo) throws DAOException, ParserException {
        PreparedStatement ps = null;
        ResultSet rsPro = null;
        ResultSet rsPre = null;        
        StringBuffer sqlPro = new StringBuffer();
        StringBuffer sqlPre = new StringBuffer();
        Connection conn = null;
        ItemConsultaVO vo = null;
        List list = Collections.EMPTY_LIST;
        
        try {
        	
        	conn = this.getConnection();

        	sqlPro.append(" SELECT IDPRO,    \n");
            sqlPro.append("        NOM_PRO   \n");
            sqlPro.append("   FROM PRO       \n");
            sqlPro.append("  WHERE IDENT = ? \n");
            sqlPro.append("    AND IDEMP = ? \n");
            sqlPro.append("  ORDER BY PRO    \n");
            
        	sqlPre.append(" SELECT PRECO_ENTIDADE, 			\n");
            sqlPre.append("        PRECO_SUGERIDO  			\n");
            sqlPre.append("   FROM PRE             			\n");
            sqlPre.append("  WHERE IDENT = ? 	   			\n");
            sqlPre.append("    AND IDEMP = ? 				\n");            
            sqlPre.append("    AND IDPRO = ?       			\n");
            sqlPre.append("  ORDER BY DATA_REFERENCIA DESC 	\n");
            
            int i=0;
            
            ps = conn.prepareStatement(sqlPro.toString());
           	ps.setLong(++i, operadorVo.getIdEntidade().longValue());
           	ps.setLong(++i, operadorVo.getIdEmpresa().longValue());
          
            rsPro = ps.executeQuery();
            
            list = new ArrayList();
            
			while (rsPro.next()) {
                vo = new ItemConsultaVO();
                
                i=0;
                ps = conn.prepareStatement(sqlPre.toString());
               	ps.setLong(++i, operadorVo.getIdEntidade().longValue());
               	ps.setLong(++i, operadorVo.getIdEmpresa().longValue());                
               	ps.setLong(++i, Parser.parseLong(rsPro.getBigDecimal("IDPRO")).longValue());
               	
               	rsPre = ps.executeQuery();
               	
               	if (rsPre.next()) {
               		vo.setNomeProduto(rsPro.getString("NOM_PRO"));
                    vo.setCustoEntidade(Parser.parseDouble(rsPre.getBigDecimal("PRECO_ENTIDADE")));
                    vo.setCustoSugerido(Parser.parseDouble(rsPre.getBigDecimal("PRECO_SUGERIDO")));

               		list.add(vo);
               	}
				closeResultSet(rsPre);
            }
		} catch (SQLException e) {
			throw new DatabaseException(e);
		} catch (ParserException e) {
			throw new ParserException(e); 
		} finally {
			try {
				closeResultSet(rsPro);
				closeStatement(ps);
				closeConnection(conn);
			} catch (DAOException e) {
			}
		}
	
		return list;
	}
    
    public Long pesquisarEntidade(Long idEntidade) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        Long percentual = null;

        try {
    
        	conn = this.getConnection();
        	
            sql.append("SELECT PERC_ASSOCIADO	\n");
            sql.append("  FROM ENT    			\n");      	
            sql.append(" WHERE IDENT = ? 		\n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i,idEntidade.longValue());
            rs = ps.executeQuery();

			if (rs.next()) {
	           percentual = Parser.parseLong(rs.getBigDecimal("PERC_ASSOCIADO"));     
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
	
		return percentual;
	}
    
}
