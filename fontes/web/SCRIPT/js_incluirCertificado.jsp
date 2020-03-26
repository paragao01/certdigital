<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

 function init() {
 <%@ include file="/APP_TEMPLATES/showMessage.jsp" %>
 }
  
 function inserir(){
	 var frm = document.forms[0];
 	 var metodo = frm.elements['metodo'];
 	 var doc    = frm.elements['certificadoHelper.certificadoVO.documentoAsString'].value;
 	 var tipoPessoa	= frm.elements['certificadoHelper.certificadoVO.tipo_pessoa'].value;
 	  
 	 if(tipoPessoa == ""){
	 	alert('<bean:message key="selecione.tipo.pessoa" />')
	 	frm.elements['certificadoHelper.certificadoVO.documentoAsString'].value = "";
	 	return;
	 }
	 
	 if(tipoPessoa == 'F'){
     	//if(!verificarCPF(doc)){
     	if(!valida_cpf(doc)){     	
     		alert('<bean:message key="cpf.invalido" />');
     		return;
     	}
     }

	 if(tipoPessoa == 'J'){
     	//if(!verificarCGC(doc)){
     	if(!valida_cnpj(doc)){     	
     		alert('<bean:message key="cnpj.invalido" />');
     		return;
     	}
     }
 	  	 
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
 	 frm.action = 'pesquisaCertificado.do';
 	 
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
