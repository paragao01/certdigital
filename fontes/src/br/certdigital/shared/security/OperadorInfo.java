package br.certdigital.shared.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.certdigital.shared.util.GlobalConstants;
import br.certdigital.vo.OperadorVO;

public class OperadorInfo {
	
	public static OperadorVO getOperadorInfo(HttpServletRequest request){
	
		HttpSession session = request.getSession();
		
		OperadorVO operador = (OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO);
		
		return operador;
	}
}
