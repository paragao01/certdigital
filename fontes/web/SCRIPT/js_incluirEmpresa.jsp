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
 	 if(validateForm(frm)) {
 	 	showWaitLayer();
 	 	frm.submit();
	 }
 }

 function limpar(){
	 var frm = document.forms[0];
	 frm.reset();
 }

 function voltar(){
	 var frm = document.forms[0];
 	 var metodo = frm.elements['metodo'];
 	 frm.action = 'pesquisaEmpresa.do';
 	 
 	 metodo.value = 'prepararPesquisa';
 	 showWaitLayer();
	 frm.submit();
 }

 function alterar(){
	 var frm = document.forms[0];
 	 var metodo = frm.elements['metodo'];
 	 metodo.value = 'atualizar';
 	 if(validateForm(frm)) {
 	 	showWaitLayer();
 	 	frm.submit();
	 }
 }

 function remover(){
	 var frm = document.forms[0];
 	 var metodo = frm.elements['metodo'];
 	 
 	 metodo.value = 'remover';
 	 
 	 frm.submit();
 }
