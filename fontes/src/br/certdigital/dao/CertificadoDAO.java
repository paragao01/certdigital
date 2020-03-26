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
import br.certdigital.view.CertificadoHelper;
import br.certdigital.vo.CertificadoVO;
import br.certdigital.vo.OperadorVO;

/**
 * Business DAO para o cadastro de Empresas
 * 
 * @author elisio
 */
public class CertificadoDAO extends BaseDAO {
	
	//private Logger log = Logger.getLogger(this.getClass());
	
	public CertificadoDAO(){}
 
    public List pesquisar(CertificadoHelper helper, OperadorVO operadorVo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        CertificadoVO vo = null;
        List list = Collections.EMPTY_LIST;
         
        try {
    
        	conn = this.getConnection();

        	sql.append(" SELECT CER.IDENT,            \n");
            sql.append("    	CER.IDEMP,            \n"); 
            sql.append("    	CER.IDCER,            \n");
            sql.append("    	CER.TIPO_PESSOA,      \n");        	                        
            sql.append("    	CER.DOCUMENTO,  	  \n");
            sql.append("     	CER.RAZAO_REQUERENTE, \n");
            sql.append("     	CER.DATA_EMISSAO      \n");            
            sql.append(" 		FROM CER              \n");
            sql.append("  WHERE CER.IDENT = ?         \n");
            
            if(helper.getCertificadoVO().getIdEmpresa() != null){
            	sql.append("    AND CER.IDEMP = ?     \n");
            }
            if(helper.getCertificadoVO().getDocumento() != null){
            	sql.append("   AND CER.TIPO_PESSOA = ? \n");            	
            	sql.append("   AND CER.DOCUMENTO = ?    \n");
            }
            
            sql.append("ORDER BY CER.RAZAO_REQUERENTE \n");
            
            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            
            ps.setLong(++i, operadorVo.getIdEntidade().longValue());
            if(helper.getCertificadoVO().getIdEmpresa() != null){
            	ps.setLong(++i, helper.getCertificadoVO().getIdEmpresa().longValue());
            }
            if(helper.getCertificadoVO().getDocumento() != null){
            	ps.setString(++i, helper.getCertificadoVO().getTipo_pessoa());            	
            	ps.setLong(++i, helper.getCertificadoVO().getDocumento().longValue());
            }
            
            rs = ps.executeQuery();
            
            list = new ArrayList();

			while (rs.next()) {
                vo = new CertificadoVO();
                vo.setIdEntidade(Parser.parseLong(rs.getBigDecimal("IDENT")));
                vo.setIdEmpresa(Parser.parseLong(rs.getBigDecimal("IDEMP")));                
                vo.setIdCertificado(Parser.parseLong(rs.getBigDecimal("IDCER")));
                vo.setTipo_pessoa(rs.getString("TIPO_PESSOA"));                
                vo.setDocumento(Parser.parseLong(rs.getBigDecimal("DOCUMENTO")));
                vo.setRazaoRequerente(rs.getString("RAZAO_REQUERENTE"));     
                vo.setDataEmissao(rs.getDate("DATA_EMISSAO"));                                
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
	 * Pesquisa os detalhes de uma empresa
	 * 
	 * @param idUnidadeNegocio
	 * @param idEmpresa
	 */
    public CertificadoVO pesquisarCertificado(CertificadoHelper helper, OperadorVO operadorVo) throws DAOException {

    	PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        CertificadoVO vo = null;
        
        try {
    
        	conn = this.getConnection();
        	
        	sql.append("SELECT 	ident, 						\n");
        	sql.append("		idemp, 						\n");
        	sql.append("		idcer, 						\n");
        	sql.append("		idpro, 						\n");
        	sql.append("		razao_requerente, 			\n");
        	sql.append("		fantasia_requerente, 		\n");
        	sql.append("		tipo_pessoa,				\n");        	
        	sql.append("		documento, 					\n");
        	sql.append("		inss_requerente, 			\n");
        	sql.append("		cidade_requerente, 			\n");
        	sql.append("		uf_requerente, 				\n");
        	sql.append("		ddd_requerente, 			\n");
        	sql.append("		tel_requerente, 			\n");
        	sql.append("		nome_responsavel, 			\n");
        	sql.append("		cpf_responsavel, 			\n");
        	sql.append("		data_nasc_responsavel, 		\n");
        	sql.append("		email_responsavel, 			\n");
        	sql.append("		logradouro_responsavel, 	\n");
        	sql.append("		numero_responsavel, 		\n");
        	sql.append("		complemento_responsavel, 	\n");
        	sql.append("		bairro_responsavel, 		\n");
        	sql.append("		cidade_responsavel, 		\n");
        	sql.append("		uf_responsavel, 			\n");
        	sql.append("		cep_responsavel, 			\n");
        	sql.append("		data_emissao,	 			\n");
        	sql.append("		associado,		 			\n");
        	sql.append("		protocolo	 				\n");        	
        	sql.append("  FROM 	CER							\n");
            sql.append(" WHERE 	IDENT    = ?   				\n");
            sql.append("   AND 	IDEMP    = ?   				\n");
            sql.append("   AND 	IDCER    = ?   				\n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i, operadorVo.getIdEntidade().longValue());
            ps.setLong(++i, operadorVo.getIdEmpresa().longValue());
            ps.setLong(++i, helper.getCertificadoVO().getIdCertificado().longValue());

            rs = ps.executeQuery();

			if (rs.next()) {
                vo = new CertificadoVO();
                vo.setIdEntidade(Parser.parseLong(rs.getBigDecimal("IDENT")));                
                vo.setIdEmpresa(Parser.parseLong(rs.getBigDecimal("IDEMP")));
                vo.setIdCertificado(Parser.parseLong(rs.getBigDecimal("IDCER")));
                vo.setIdProduto(Parser.parseLong(rs.getBigDecimal("IDPRO")));
                vo.setRazaoRequerente(rs.getString("razao_requerente"));                
                vo.setFantasiaRequerente(rs.getString("fantasia_requerente"));
                vo.setTipo_pessoa(rs.getString("tipo_pessoa"));                                
                vo.setDocumento(Parser.parseLong(rs.getBigDecimal("documento")));                
                vo.setInssRequerente(Parser.parseLong(rs.getBigDecimal("inss_requerente")));
                vo.setCidadeRequerente(rs.getString("cidade_requerente"));
                vo.setEstadoRequerente(rs.getString("uf_requerente"));
                vo.setDddRequerente(Parser.parseLong(rs.getBigDecimal("ddd_requerente")));
                vo.setTelRequerente(Parser.parseLong(rs.getBigDecimal("tel_requerente")));
                vo.setNomeResponsavel(rs.getString("nome_responsavel"));
                vo.setCpfResponsavel(Parser.parseLong(rs.getBigDecimal("cpf_responsavel")));
                vo.setDataNascResponsavel(rs.getDate("data_nasc_responsavel"));
                vo.setEmailResponsavel(rs.getString("email_responsavel"));
                vo.setLogradouroResponsavel(rs.getString("logradouro_responsavel"));
                vo.setNumeroResponsavel(rs.getString("numero_responsavel"));
                vo.setComplementoResponsavel(rs.getString("complemento_responsavel"));
                vo.setBairroResponsavel(rs.getString("bairro_responsavel"));
                vo.setCidadeResponsavell(rs.getString("cidade_responsavel"));
                vo.setEstadoResponsavel(rs.getString("uf_responsavel"));
                vo.setCepResponsavel(Parser.parseLong(rs.getBigDecimal("cep_responsavel")));
                vo.setDataEmissao(rs.getDate("data_emissao"));
                vo.setAssociado(rs.getString("associado"));
                vo.setProtocolo(Parser.parseLong(rs.getBigDecimal("protocolo")));
                
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
	 * Pesquisa os detalhes de um Certificado
	 * 
	 * @param cnpj
	 * @param idUnidadeNegocio
	 * @return Long -> id da empresa
	 */
    public Long pesquisarCertificadoPorCnpj(CertificadoVO vo, OperadorVO operadorVo) throws DAOException {

    	PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        Long result = null;
        if (vo.getDocumento() == null) {
        	return result;
        }
        
        try {
    
        	conn = this.getConnection();

            sql.append(" SELECT	IDCER           \n");
            sql.append("   FROM	CER             \n");
            sql.append("  WHERE IDENT       = ? \n");
            sql.append("    AND IDEMP       = ? \n");
            sql.append("    AND TIPO_PESSOA = ? \n");                        
            sql.append("    AND DOCUMENTO   = ? \n");

            int i=0;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(++i, operadorVo.getIdEntidade().longValue());
            ps.setLong(++i, operadorVo.getIdEmpresa().longValue());
            ps.setString(++i, vo.getTipo_pessoa());
            ps.setLong(++i, vo.getDocumento().longValue());
            

            rs = ps.executeQuery();            

			if (rs.next()) {
                result = Parser.parseLong(rs.getBigDecimal("IDCER"));                
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
     * Insere uma novo Certificado
     * 
     * @param empresaVO
     * @throws DAOException
     */
    public CertificadoVO insert(CertificadoVO vo, OperadorVO operadorVo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
        	sql.append(" INSERT INTO CER (ident, idemp, idcer, idpro, razao_requerente, fantasia_requerente, documento, inss_requerente, cidade_requerente, uf_requerente, ddd_requerente, tel_requerente, nome_responsavel, cpf_responsavel, data_nasc_responsavel, email_responsavel, logradouro_responsavel, numero_responsavel, complemento_responsavel, bairro_responsavel, cidade_responsavel, uf_responsavel, cep_responsavel, data_emissao, dta_inc, ope_inc, dta_alt, ope_alt, tipo_pessoa, associado, protocolo) \n");
        	sql.append("           VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE, ?, NULL, NULL, ?, ?, ?) \n");

            int i=0;

            vo.setIdCertificado(this.getAutoIncrementByEntidadeEmpresa("CER", "IDCER", operadorVo.getIdEmpresa(), operadorVo.getIdEmpresa()));            

            ps = conn.prepareStatement(sql.toString());
            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, operadorVo.getIdEntidade() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, operadorVo.getIdEmpresa() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdCertificado() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdProduto() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getRazaoRequerente() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getFantasiaRequerente() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDocumento() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getInssRequerente() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getCidadeRequerente() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEstadoRequerente() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddRequerente() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getTelRequerente() ) ;   
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeResponsavel() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getCpfResponsavel() ) ;
            BaseDAO.setObject(ps,Types.DATE,    ++i, vo.getDataNascResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEmailResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getLogradouroResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNumeroResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getComplementoResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getBairroResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getCidadeResponsavell() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEstadoResponsavel() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getCepResponsavel() ) ;
            BaseDAO.setObject(ps,Types.DATE,    ++i, vo.getDataEmissao() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, operadorVo.getIdOperador() ) ;            
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getTipo_pessoa() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getAssociado() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getProtocolo() ) ;
            
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
     * Atualiza um Certificado
     * 
     * @param empresaVO
     * @throws DAOException
     */
    public void update(CertificadoVO vo, OperadorVO operadorVo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
        	sql.append(" UPDATE CER \n");
        	sql.append("    SET idpro=?, razao_requerente=?, fantasia_requerente=?, documento=?, inss_requerente=?, cidade_requerente=?, uf_requerente=?, ddd_requerente=?, tel_requerente=?, nome_responsavel=?, cpf_responsavel=?, data_nasc_responsavel=?, email_responsavel=?, logradouro_responsavel=?, numero_responsavel=?, complemento_responsavel=?, bairro_responsavel=?, cidade_responsavel=?, uf_responsavel=?, cep_responsavel=?, data_emissao=?, dta_alt=CURRENT_DATE, ope_alt=?, tipo_pessoa=?, associado=?, protocolo=?  \n");
        	sql.append("  WHERE ident=? \n");
        	sql.append("    AND idemp=? \n");
        	sql.append("    AND idcer=? \n");
            
            int i=0;

            ps = conn.prepareStatement(sql.toString());

            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdProduto() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getRazaoRequerente() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getFantasiaRequerente() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDocumento() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getInssRequerente() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getCidadeRequerente() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEstadoRequerente() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getDddRequerente() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getTelRequerente() ) ;   
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNomeResponsavel() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getCpfResponsavel() ) ;
            BaseDAO.setObject(ps,Types.DATE,    ++i, vo.getDataNascResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEmailResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getLogradouroResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getNumeroResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getComplementoResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getBairroResponsavel() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getCidadeResponsavell() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getEstadoResponsavel() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getCepResponsavel() ) ;
            BaseDAO.setObject(ps,Types.DATE,    ++i, vo.getDataEmissao() ) ;            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, operadorVo.getIdOperador() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getTipo_pessoa() ) ;
            BaseDAO.setObject(ps,Types.VARCHAR, ++i, vo.getAssociado() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getProtocolo() ) ;
            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEntidade() ) ;            
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdEmpresa() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdCertificado() ) ;
            
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
     * Remove um Certificado
     * 
     * @param empresaVO
     * @throws DAOException
     */
    public void remove(CertificadoVO vo, OperadorVO operadorVo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
    
        try {
    
        	conn = this.getConnection();
        	
            sql.append(" DELETE FROM CER     \n");
            sql.append("  WHERE IDENT    = ? \n");
            sql.append("    AND IDEMP    = ? \n");
            sql.append("    AND IDCER    = ? \n");
            
            int i=0;

            ps = conn.prepareStatement(sql.toString());

            BaseDAO.setObject(ps,Types.NUMERIC, ++i, operadorVo.getIdEntidade() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, operadorVo.getIdEmpresa() ) ;
            BaseDAO.setObject(ps,Types.NUMERIC, ++i, vo.getIdCertificado() ) ;
            
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
