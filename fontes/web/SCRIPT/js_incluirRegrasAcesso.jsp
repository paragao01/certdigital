<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ page import ="br.certdigital.shared.util.*" contentType="text/html;charset=ISO-8859-1" %>


 
 function init() {
  <%@ include file="/APP_TEMPLATES/showMessage.jsp" %>
 }

 function limpar(){
	 var frm = document.forms[0];
	 frm.reset();
 }
 
 function sucessoDefinirRegrasAcesso() {
 	 var frm = document.forms[0];
	 var metodo = frm.elements['metodo']; 	 
	 metodo.value = 'sucessoDefinirRegrasAcesso';
	 showWaitLayer();
	 frm.submit();
 }
 
 function sucessoManutencaoRegrasAcesso() {
 	 var frm = document.forms[0];
	 var metodo = frm.elements['metodo']; 	 
	 metodo.value = 'sucessoManutencaoRegrasAcesso';
	 showWaitLayer();
	 frm.submit();
 }
 
 function checkCorrespondente(checkBox, checkBoxCorresp) {

   	checkObj = document.getElementsByName(checkBoxCorresp)[0];

   	if (checkBox.checked == true) {
   		checkObj.checked = true;
   	}

 }
 
 function uncheckCorrespondente(checkBoxCorresp) {
 
 	uncheckObj = document.getElementsByName(checkBoxCorresp)[0];
 	uncheckObj.checked = false;
 }