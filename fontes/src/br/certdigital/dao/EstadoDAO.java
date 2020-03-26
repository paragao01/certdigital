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
import br.certdigital.tools.database.BaseDAO;
import br.certdigital.vo.EstadoVO;

public class EstadoDAO  extends BaseDAO {
	
	//private Logger log = Logger.getLogger(this.getClass());

    public EstadoDAO() { }


    public List pesquisar() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        Connection conn = null;
        List itens = Collections.EMPTY_LIST;
        EstadoVO vo;

        try {
    
        	conn = this.getConnection();
        	
            sql.append(" SELECT SLG_EST,  \n");
            sql.append("        DES_EST   \n");
            sql.append(" FROM   EST       \n");
            //sql.append(" ORDER BY SLG_EST \n");
            
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

			if (rs.next()) {
				
				itens = new ArrayList();

				do {
	                vo = new EstadoVO();
	                vo.setSigla(rs.getString("SLG_EST"));
	                vo.setDescricao(rs.getString("DES_EST"));
	                
					itens.add(vo);
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

}
