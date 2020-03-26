<%@ taglib uri="struts-bean.tld" prefix="bean"%>


function validateForm(frm) {
  var tagValue="";
  var fieldType="";
  var javaType="";
  var required="";
  var idValue="";
  var maxLen=0;
  var minLen=0;
  var defsLen=4; //definition field length
  var defs="";
  var elem;
  var i=0;
  var ckbName='';
  var compname='';
  var acceptZero = '';
  var retCkb=0;
  var minMax;

  for(i=0; i < frm.length; i++) {   //loop all the elements within the form
    elem = frm.elements[i];
    compname = elem.name;
    fieldType = elem.type;
    idValue = elem.id + "";
    if( idValue.length <= 0 ) continue; //no ID, no check
    
    defs = idValue.split("|");    //separate the infos about the field definition

    // alterado para >= pois o 4 e 5 parametro são opcionais
    if(defs.length >= defsLen) {  //found different informations
      	javaType = defs[0];
      	required = defs[1];
      	maxLen   = defs[2];

      	if(maxLen.indexOf("-") >= 0) {
        	minMax = maxLen.split("-");
        	minLen = minMax[0];
        	maxLen = minMax[1];
      	}
      	if (defs.length>3) compname = defs[3];
      	if (defs.length>4) acceptZero = defs[4];
      	if(typeof(defs[4]) == 'undefined') acceptZero = 'S';
    }else{
      	alert("<bean:message key="definicao.incorreta"/> ["+compname+"]");
      	break;//return false;
    }

    tagValue = getInputValue(elem);

    //alert('type='+javaType+', required='+required+', compname='+elem.name+',value='+tagValue);

    if(maxLen>0 && tagValue.length > maxLen) {
	    alert('<bean:message key="tamanho.maximo.excedido"/> : '+compname);

      if(fieldType!="hidden" && !elem.disabled) elem.focus(); //hiddens can not be focused
      return false;
    }

    if((required=="S") && (minLen > 0) && (tagValue.length < minLen)) {
      alert('<bean:message key="tamanho.minimo.excedido"/> : '+compname);

      if(fieldType!="hidden" && !elem.disabled) elem.focus(); //hiddens can not be focused
      return false;
    }

    // Acrescentado por emurai em 19/04. Tratamento de checkboxes: basta 1 checkbox estar selecionado.
    if (required=="S" && fieldType=="checkbox") {

      	retCkb= handleCheckbox(frm,i);
      	if (retCkb==-100) {
        	alert("<bean:message key="checkbox.requerido"/> "+compname+"!");
        	return false;
      	}

      	i= retCkb; // continua validacoes 'apos' os checkboxes
      	continue;
    }

    if(required=="S" && trim(tagValue)=='') { //required
      	alert("<bean:message key="campo.requerido"/> : "+compname);

      	if(fieldType!="hidden" && !elem.disabled)
      		elem.focus(); //hiddens can not be focused

      	return false;
    }

    if(tagValue.length>0 && acceptZero=="N" && (trim(tagValue) == '0' || isZeroValue(trim(tagValue))) ) {  
      	alert("<bean:message key="dado.invalido"/> : "+compname);
      
      	if(fieldType!="hidden" && !elem.disabled)
      		elem.focus(); //hiddens can not be focused

		return false;
    }
    
    if(tagValue.length>0) { //we got some data
    
    	if(!validateByType(tagValue, javaType, compname, elem)) {

        	if (javaType != "K") {
            	alert("<bean:message key="dado.invalido"/> : "+compname);
        	}

        	if(fieldType!="hidden" && !elem.disabled) elem.focus(); //hiddens can not be focused
        		return false;
      	}
    }
  } //for(i)

  return true; //form is valid
}

// Verifica se existe algum checkbox com 'checked' by emurai
function handleCheckbox(frm,i) {
  var ckbName = frm.elements[i].name;
  var x=0;
  var checkedItem = false;

  // percorre todos os checkboxes para verificar se existe um selecionado (supoe que os checkboxes estao na sequencia)
  for (x=i; x < frm.length; x++) {
    if (frm.elements[x].name!=ckbName) {
      //alert('ops.. cbkName= '+ckbName+', atual='+frm.elements[x].name);
      if (!checkedItem) return -100;
      else return (x-1);
	  }

    if (!checkedItem) {
      tagValue = getInputValue(frm.elements[x]);
      checkedItem = (tagValue.length>0);
		}
	}
  return (x-1);
}

function filterSpecialChars(v) {
  var i=0,buf='';
  if (v=='') return '';

  for (i=0; i < v.length; i++)

   if ( (v.charAt(i)>='a' && v.charAt(i)<='z') ||
        (v.charAt(i)>='A' && v.charAt(i)<='Z') ||
        (v.charAt(i)>='0' && v.charAt(i)<='9') ||
         v.charAt(i)==' ' || v.charAt(i)=='.' ||
         v.charAt(i)=='/' || v.charAt(i)=='@')
      buf += v.charAt(i);

  return buf;
}

function validateByType(tagValue, fieldType,compname, elem) {
  
  if(fieldType == "S") { //is String
  	//elem.value = filterSpecialChars(tagValue);

  } else if(fieldType == "D") { //is Date
    if( !isDate(tagValue) ) return false;

  } else if(fieldType == "N") { //is Integer/Long Negative or Positive
    if( !isPositiveNegativeInteger(tagValue) ) return false;

  } else if(fieldType == "Z") { //is Integer/Long Negative or Zero
    if( !isNegativeOrZeroInteger(tagValue) ) return false;

  } else if(fieldType == "I") { //is Integer/Long
    if( !isInteger(tagValue) ) return false;

  } else if(fieldType == "F") { //is Float/Double
    if( !isNumeric(tagValue) ) return false;

  } else if(fieldType == "T") { //is Time
    if( !isTime(tagValue) ) return false;

  } else if(fieldType == "E") { //is E-mail
    if( !isEmail(tagValue) ) return false;
    
  } else if (fieldType == "P") { // CPF
    //alert('CPF : ['+tagValue+']\n['+fieldType+']\n['+compname+']\n['+elem+']');
    //if( !verificarCPF(tagValue) ) return false;
    if( !valida_cpf(tagValue) ) return false;    
    elem.value = extrairMascaraCPF(tagValue);
    
  } else if (fieldType == "J") { // CNPJ
    //if( !verificarCGC(tagValue) ) return false;
    if( !valida_cnpj(tagValue) ) return false;    
    elem.value = extrairMascaraCGC(tagValue);
    
  } else if (fieldType == "R") { // RG
	return true;
    
  } else if (fieldType == "H") { // Dia (do mes)
    return (isNumeric(tagValue) && (parseInt(tagValue, 10) >= parseInt(1, 10)) && (parseInt(tagValue, 10) <= parseInt(31, 10)));
    
  } else if (fieldType == "K") {
    if( !isDate(tagValue) ) {
		alert("<bean:message key="dado.invalido"/> : "+compname);
		return false;
  	}
  	if (isDateGreaterThanNow(tagValue)) {
		alert("<bean:message key="data.maior.que.data.corrente"/> : "+compname);
		return false;
  	}
  }
    
  return true;
}

function imprimirFornecedor(codPessoaDetalhe, codOrigem){
  var frm = document.forms[0];
  var url = 'relatorioFornecedor.do?metodo=imprimir&relatorioHelper.codPessoaDetalhe=' + codPessoaDetalhe;
  url += '&relatorioHelper.origem=' + codOrigem;

  var x = screen.height - (screen.height /5);
  var l = (screen.width - 800) / 2;
  var t = (screen.height / 100);

  window.open(url, "JANELA", "height = " + x + ", width = 800,  left=" + l + ", top = "  + t);
}