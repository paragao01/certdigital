<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>


 
 
 function init() {
 <%@ include file="/APP_TEMPLATES/showMessage.jsp" %>
}
 
 
 
 function inserir(){
	 var frm = document.forms[0];
 	 var metodo = frm.elements['metodo'];
 	 
 	 metodo.value = 'inserir';
 	 
 	 if(validateForm(frm))
	 	 frm.submit();
 }


 function limpar(){
	 var frm = document.forms[0];
	 frm.reset();
 }
