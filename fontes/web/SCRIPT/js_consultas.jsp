<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ page import ="br.certdigital.shared.util.*" contentType="text/html;charset=ISO-8859-1" %>

<% br.certdigital.vo.OperadorVO operador = (br.certdigital.vo.OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO); %>

 function init() {
 <%@ include file="/APP_TEMPLATES/showMessage.jsp" %>
 }

 function consultarSAC(cpfCnpjObrigatorio){
	 var frm = document.forms[0];	
 	 var datainicial = frm.elements['pesquisaHelper.filtroConsultaVO.dataInicialAsString'].value;
 	 var datafinal   = frm.elements['pesquisaHelper.filtroConsultaVO.dataFinalAsString'].value;
 	 var codigoEmp   = frm.elements['pesquisaHelper.filtroConsultaVO.idEmpresa'].value; 	 
 	 var dias;
	 
 	 if ((codigoEmp==null)||(trim(codigoEmp).length== 0)){	
		alert('<bean:message key="empresa.nulo" />');		
	  	return;
	 }
  	 
 	 if ((datainicial==null)||(trim(datainicial).length== 0)){	
		alert('<bean:message key="parametro.nulo" />');		
	  	return;
	 }
	      	
 	 if (datainicial!= '') {
 	    dias = dateDiff(datainicial, datafinal); 	     
 	    if ( dias > 40) {
 	 	  alert('<bean:message key="selecione.outro.intervalo" />');
	  	  return;
	  	} 
	 }
	  	
	 var metodo = frm.elements['metodo'];
 	 metodo.value = 'consultar';
 	 if(validateForm(frm)) {
 	 	 showWaitLayer();
	 	 frm.submit();
	 }
	 
 }

 function carteiraAnalitico(tipo){
 	var frm = document.forms[0];
	var metodo = frm.elements['metodo'];
	
	frm.elements['pesquisaHelper.filtroConsultaVO.tipoConsultaCarteira'].value = tipo;
 	metodo.value = 'consultar';
 	frm.action = 'consultarCheque.do';
 	
	if(validateForm(frm)) {
		showWaitLayer();
		frm.submit();
	}
 }
 function alterarValidacaoCpfCnpj(cpfCnpjObrigatorio) {
	 var frm = document.forms[0];
	 var tipoPessoaCombo = frm.elements['pesquisaHelper.filtroConsultaVO.tipoPessoa'];
 	 var cpfCnpjField = frm.elements['pesquisaHelper.filtroConsultaVO.cpfCnpjAsString'];
	 if ( cpfCnpjObrigatorio ) {
	 	 if ( tipoPessoaCombo.value == "" ) {
	 	 	alert('<bean:message key="selecione.tipo.pessoa" />');
	 	 	return;
	 	 }
 	 }
 	 if ( tipoPessoaCombo.value == "<%=GlobalConstants.VALOR_FISICA%>" ) {
 	 	if ( cpfCnpjObrigatorio ) {
 	 		cpfCnpjField.id = "P|S|11|CPF|N";
 	 	} else {
 	 		cpfCnpjField.id = "P|N|11|CPF|N";
 	 	}
 	 } else {
 	 	if ( cpfCnpjObrigatorio ) {
 	    	cpfCnpjField.id = "J|S|14|CNPJ|N";
 	    } else {
 	    	cpfCnpjField.id = "J|N|14|CNPJ|N";
 	    }
 	 }
 }

 function dateDiff(strDate1,strDate2) {
	var day1, day2;
	var month1, month2;
	var year1, year2;

	value1 = strDate1;
	value2 = strDate2;

	day1 = value1.substring (0, value1.indexOf ("/"));
	month1 = value1.substring (value1.indexOf ("/")+1, value1.lastIndexOf ("/"));
	year1 = value1.substring (value1.lastIndexOf ("/")+1, value1.length);

	day2 = value2.substring (0, value2.indexOf ("/"));
	month2 = value2.substring (value2.indexOf ("/")+1, value2.lastIndexOf ("/"));
	year2 = value2.substring (value2.lastIndexOf ("/")+1, value2.length);

	date1 = year1+"/"+month1+"/"+day1;
	date2 = year2+"/"+month2+"/"+day2;
 
	firstDate = Date.parse(date1)
	secondDate= Date.parse(date2)

	msPerDay = 24 * 60 * 60 * 1000
	dbd = Math.round((secondDate.valueOf()-firstDate.valueOf())/ msPerDay) + 1;
 
	return (dbd)
 }

 function consultar(cpfCnpjObrigatorio){
	 var frm = document.forms[0];	
 	 var datainicial = '';
 	 var datafinal   = ''; 	 
 	 var doc         = '';
 	 var tipoPessoa	 = '';
 	 var autorizacao = '';
 	 var dias;
     var numElementos = frm.elements.length;
	 var dados_usuario = '';
	 
     for(var i = 0; i < numElementos; i++){
      //alert(frm.elements[i].name+ '& Valor ' +frm.elements[i].value);
      if (frm.elements[i].name == 'pesquisaHelper.filtroConsultaVO.dataInicialAsString')
          datainicial = frm.elements[i].value;
      if (frm.elements[i].name == 'pesquisaHelper.filtroConsultaVO.dataFinalAsString')
          datafinal = frm.elements[i].value;
      if (frm.elements[i].name == 'pesquisaHelper.filtroConsultaVO.cpfCnpjAsString')
	      doc =  frm.elements[i].value;
	  if (frm.elements[i].name == 'pesquisaHelper.filtroConsultaVO.autorizacao')
	  	  autorizacao =  frm.elements[i].value;
	  if (frm.elements[i].name == 'pesquisaHelper.filtroConsultaVO.tipoPessoa')
	  	  tipoPessoa =  frm.elements[i].value;	  	  
     }
  	 
 	 if (((datainicial==null)||(trim(datainicial).length== 0))          &&
        ((doc         ==null)||(trim(doc).length        == 0||doc==" "))&&
        ((autorizacao ==null)||(trim(autorizacao).length== 0||autorizacao==" "))){	
		alert('<bean:message key="parametro.nulo" />');		
	  	return;
	 }
	 
	 if(tipoPessoa == "" && doc != ""){
	 	alert("Tipo pessoa nao informado")
	 	frm.elements['pesquisaHelper.filtroConsultaVO.cpfCnpjAsString'].value = "";
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
     	
 	 if (datainicial!= '') {
 	    dias = dateDiff(datainicial, datafinal); 	     
 	    if ( dias > 40) {
 	 	  alert('<bean:message key="selecione.outro.intervalo" />');
	  	  return;
	  	} 
	 }
	 
	 if ( cpfCnpjObrigatorio == null || cpfCnpjObrigatorio == 'false') {
 	 	cpfCnpjObrigatorio = false;
 	 } else {
 	 	cpfCnpjObrigatorio = true;
 	 }
 	
	 var metodo = frm.elements['metodo'];
 	 metodo.value = 'consultar';
 	 if(validateForm(frm)) {
 	 	 showWaitLayer();
	 	 frm.submit();
	 }
	 
 }
 
 function limpar(){
	 var frm = document.forms[0];
	 frm.reset();
 }

 function OperacaoDiaria() {
	 var frm = document.forms[0];
	 var metodo = frm.elements['metodo'];
	 
 	 metodo.value = 'consultar';
 	 if(validateForm(frm)) {
 	 	 showWaitLayer();
	 	 frm.submit();
	 }
	 
 }
 