<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

    function pesquisar(metodo, campoCodigo, campoNome, labelJanelaOpcional, campoRestricaoOpcional, labelCampoRestricaoOpcional) {
    	var frm = document.forms[0];
    	var objW = 475;
    	var objH = 295;
    	var url = "pesquisar.do?metodo=prepararPesquisa&pesquisaHelper.metodoPesquisaExecutar="+metodo+"&pesquisaHelper.campoCodigo="+campoCodigo+"&pesquisaHelper.campoNome="+campoNome;
    	    	
    	if (labelJanelaOpcional != null) {
    		url += "&pesquisaHelper.labelJanela="+labelJanelaOpcional;
		}
    	if (campoRestricaoOpcional != null) {
	    	if (frm.elements[campoRestricaoOpcional] != null) {
	    		if (frm.elements[campoRestricaoOpcional].value == '') {
	    			alert('Para selecionar esse campo, selecione primeiro o campo '+labelCampoRestricaoOpcional);
	    			frm.elements[campoRestricaoOpcional].focus();
	    			return;
	    		}
				url += "&pesquisaHelper.valorRestricaoOpcional="+frm.elements[campoRestricaoOpcional].value;
				url += "&pesquisaHelper.labelValorRestricaoOpcional="+labelCampoRestricaoOpcional;				
			}    	
		}
				
	 	window.open(url, "", "menubar=no, toolbar=no, location=no, scrollbars=no, status=no,resizable=no, width="+objW+", height="+objH+", left="+(screen.width/2 - (objW/2))+", top="+(screen.height/2 - (objH/2)));
    }
    
    function selecionar(codigo, nome) {
    	//if (opener != null) {
	    	var frm = document.forms[0];
	    	var campoCodigo = frm.elements['pesquisaHelper.campoCodigo'].value;
	    	var campoNome = frm.elements['pesquisaHelper.campoNome'].value;
	 	    var frmOpener = opener.document.forms[0];
		 	frmOpener.elements[campoCodigo].value = codigo;
		 	frmOpener.elements[campoNome].value = nome;
		 	frmOpener.elements[campoCodigo].focus();
		//}
    	window.close();
    }
    
    function executarPesquisa() {
    	var frm = document.forms[0];
    	showWaitLayer();
		frm.submit();
    }

    function pesquisaCertificado() {
    	var frm = document.forms[0];
  	 	var doc = frm.elements['certificadoHelper.certificadoVO.documentoAsString'].value;
 	 	var tipoPessoa	= frm.elements['certificadoHelper.certificadoVO.tipo_pessoa'].value;
 	  
 	 	if(tipoPessoa != ""){ 
	 		if(tipoPessoa == 'F'){
	     		if(!valida_cpf(doc)){     	
    	 			alert('<bean:message key="cpf.invalido" />');
     				return;
     			}
     		}
	 		if(tipoPessoa == 'J'){
	     		if(!valida_cnpj(doc)){     	
    	 			alert('<bean:message key="cnpj.invalido" />');
     				return;
     			}
     		}
    	}    	
    	showWaitLayer();
		frm.submit();
    }
    