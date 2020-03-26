<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>


 function doLogin(){
	var frm = document.forms[0];
	 
  	if(validaDados()) {
  		showWaitLayer();
		frm.submit();
	}
 }
 
 
 function initLogin() {
 <%@ include file="/APP_TEMPLATES/showMessage.jsp" %>
}
 
 
 function validaDados(){
	 var frm = document.forms[0];
 	 var idOperador = frm.elements['loginHelper.operadorVO.idOperador'];
	 var senha = frm.elements['loginHelper.operadorVO.senha'];
	 
	 
	 if(trim(idOperador.value)==''){
	 	alert('<bean:message key="codigo.operador.nao.informado"/>');
	 	idOperador.focus();
	 	return false;
	 }

	 if(!isInteger(idOperador.value)){
	 	alert('<bean:message key="codigo.operador.apenas.numeros"/>');
	 	idOperador.value='';
	 	idOperador.focus();
	 	return false;
	 }
	 
	 if(trim(senha.value)==''){
	 	alert('<bean:message key="informar.senha"/>');
	 	senha.focus();
	 	return false;
	 }
	 
	 senha.value = trim(senha.value.toUpperCase());
	 
	 return true;
 
 }

function checkKey() {
	var keycode = window.event.keyCode;
	
	if(keycode == "13") 
		doLogin();
}

 function limpar(){
	 var frm = document.forms[0];
 	 var idOperador = frm.elements['loginHelper.operadorVO.idOperador'];
	 var senha = frm.elements['loginHelper.operadorVO.senha'];
	 
	 idOperador.value='';
	 senha.value='';
 }
