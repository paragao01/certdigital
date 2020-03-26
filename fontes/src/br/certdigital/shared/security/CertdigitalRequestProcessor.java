package br.certdigital.shared.security;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.RequestProcessor;

import br.certdigital.facade.SecurityFacade;
import br.certdigital.shared.exception.CertdigitalException;
import br.certdigital.shared.util.GlobalConstants;
import br.certdigital.vo.OperadorVO;

public class CertdigitalRequestProcessor extends RequestProcessor {
	
    protected boolean processPreprocess (
            HttpServletRequest request,
            HttpServletResponse response) {
    	
            HttpSession session = request.getSession(false);
            

        //If user is trying to access login page
        // then don't check
        if( request.getServletPath().equals(GlobalConstants.LOGIN_PAGE_DO) )
            return true;
        
        
        try {
        
        //Check if userName attribute is there is session.
        //If so, it means user has allready logged in
        if(!verificaOperadorLogado(session)){
        	request.getRequestDispatcher(GlobalConstants.LOGIN_PAGE).forward(request,response);
        	return false;
        }
        else{
        		
        	loadOperadorAccess(session);
        	
        	if(checkOperadorAccess(request.getServletPath(),session))
        		return true;
        	else
        		request.getRequestDispatcher(GlobalConstants.ACCESS_DENIED_PAGE).forward(request,response);

            return false;
        }
        
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        
        return false;    
    }
    
    
	public static void loadOperadorAccess(HttpSession session) throws CertdigitalException {
		
		OperadorVO operador = (OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO);
		
		if(operador.getListaAccess()!=null && operador.getListaAccess().size()>0)//a lista ja foi carregada
			return;
		
    	SecurityFacade facade = new SecurityFacade();
    	facade.getAccess(operador);
		session.setAttribute(GlobalConstants.OPERADOR_INFO,operador);
		
	}
    
	public static boolean checkOperadorAccess(String page, HttpSession session) {
		Date dataHoje = new Date();
		dataHoje.getTime();
		
		OperadorVO operador = (OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO);

		// Invoca o garbage collector
		//System.gc();
		System.out.println("### CERTIDIGITAL PAGE: " + page + "   [" + dataHoje +"]");
		
		if(operador!=null) {	
			// se o operador for master tem acesso a tudo.
			if(operador.getIdTipoOperador().compareTo(GlobalConstants.OPERADOR_MASTER)==0)
				return true;
			
			List access = null;
			try {
				access = operador.getListaAccess();
			} catch (Exception e) {
				e.printStackTrace();
			}
/*			
			//TESTE
			System.out.println("######### ListaAcess: ");
			for (int cont = 0; cont < access.size(); cont++) {
				System.out.println(access.get(cont));
			}
*/			
			if(page!=null && access.contains(page)){
				return true;
			}
		}
		return true;
	}
	
	public static boolean verificaOperadorLogado(HttpSession session) throws CertdigitalException {
		
		OperadorVO operador = (OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO);
		
		if(operador==null || operador.getIdTipoOperador()==null)
			return false;
		
		return true;

	}
	

	
}
