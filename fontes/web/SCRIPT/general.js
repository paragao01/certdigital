function initLayersOnClick(compName) {
  var i=0;
  var frm = document.forms[0];
  
  for (i=0; i<frm.length; i++) 
    if (frm[i].type=='radio' && frm[i].name==compName && 
        frm[i].checked) {
        // chama o onclick para setar os layers
        frm[i].onclick();
        return;
        }
  }

// siteTemplate
function setCheckboxes(compName,marked) {
 var comp,frm;
 var i=0,j=0;

 for (i=0; i<document.forms.length; i++) {
   frm = document.forms[i];

   for (j=0; j<frm.length; j++)
      if (frm[j].name == compName && frm[j].type=='checkbox')
        frm[j].checked = marked;
  }
}

// Seta o radio da sequencia idxCheck para checked=true e os demais para checked=false
function setRadio(frm,compName,idxCheck) {
 var comp;
 var cnt=0,i,j;


 for (i=0; i<frm.length; i++) {

  if (frm[i].name==compName && frm[i].type=='radio') {
   cnt++;
   if (cnt==idxCheck) frm[i].checked = true;
   else frm[i].checked = false;
   }
  }
}

// Seta para checked=true um checkbox se o seu value = <compValue>
function setCheckboxValue(frm,compName,compValue,disableOthers) {
 var comp;
 var cnt=0,i,j;


 for (i=0; i<frm.length; i++)

  if (frm[i].name==compName && frm[i].type=='checkbox') {
   if (frm[i].value==compValue)
   	frm[i].checked = true;
   else if (disableOthers)
   	frm[i].checked = false;
   }

}

function setSelect(comp,marked) {
 var i=0,j=0,x=0;

  if (comp.type=='select-multiple')
        for (x=0; x<comp.length; x++)
          comp.options[x].selected = marked;
  else alert('setSelect - '+comp.name+' nao estah setado com selecao multipla!');  
}

function mountURL(frm,actionValue) {
  var url='';
  var i;  
  url=actionValue;
   
  if (frm.length>0) {
    url+='?';
    for (i=0; i<frm.length; i++) {
      if (frm[i].disabled) continue;
      
      if (frm[i].type=='select') {
          url+= (frm[i].name+'=');
          
          if (frm[i].selectedIndex!=-1) 
            url+= escape(frm[i].options[frm[i].selectedIndex].value)+'&';
          else url+='&';
          }
      else if ((frm[i].type=='checkbox' || frm[i].type=='radio')) {
        if (frm[i].checked) {
          url+= (frm[i].name+'=');
          url+= escape(frm[i].value);
          url+='&';
          }
        }
      else {
          url+= (frm[i].name+'=');
          url+= escape(frm[i].value);
          url+='&';
        }                
      } // for

    url+='rndz='+Math.round(Math.random()*1000);
    // remove o ultimo '&'
    //url= url.substr(0,url.length-1);
    }

  return url;
}

function getSelectedItem(comp) {
  var idxRecSelected=-1;
  var vRecSelected = '';
  
  if (comp.type!='select' && comp.type!='select-one' && comp.type!='select-multiple') 
    return '';
  
  idxRecSelected = comp.selectedIndex;  
  
  if (idxRecSelected<0) 
    alert('Selecione um item!');    
  else vRecSelected = comp.options[idxRecSelected].value;

  return vRecSelected;  
}

function unselectItem(comp) {
  var idxRecSelected=-1;
  var vRecSelected = '';

  if (comp.type!='select' && comp.type!='select-one' && comp.type!='select-multiple') {
    return;
  }
  
  idxRecSelected = comp.selectedIndex;   

  if (idxRecSelected<0) {
    return;
  } else { 
    comp.options[idxRecSelected].selected= false; 
    if (comp.options[idxRecSelected].selected) {
      // se continua selecionado é pq é IE
      comp.options.selectedIndex = -1;
    }
  }

}

// Encontra um componente com nome misto no form.
// ex: compname= 'filtro.metodo'
// form.filtro.metodo causa erro!
function getComponent(form, compName) {
  var i;
  
  for (i=0; i<form.length; i++) 
    if (form[i].name==compName) {
      //alert('componente '+compName+' encontrado!');
      return form[i];
      }

  //alert('getComponent - Componente '+compName+' nao encontrado no form '+form.name+'!');
  return null;
}

function countComp(frm, cname) {
 var i=0,cnt=0;
 
 for (i=0; i<frm.length; i++) 
   if (frm[i].name==cname) cnt++;
 
 return cnt;
}

function checkForFormChanges(frmOrig, frmCurrent) {
 var i,valorCheck='';

 for (i=0; i<frmOrig.length; i++) {
 // alert('frmOrig[i]='+frmOrig[i].name );
        
   if ( frmCurrent.elements[ frmOrig[i].name ]!=null && 
        (frmCurrent.elements[ frmOrig[i].name ].type=='select-multiple' ) ||
         (frmCurrent.elements[ frmOrig[i].name ].type=='checkbox' &&
           countComp(frmCurrent,frmOrig[i].name)>1) ) {
       //alert('checkForFormChanges - tipos nao tratados : '+frmCurrent.elements[ frmOrig[i].name ].type);
       //return false;
       return true;
       }

   if ( frmCurrent.elements[ frmOrig[i].name ]!=null && 
        frmCurrent.elements[ frmOrig[i].name ].value != frmOrig[i].value ) {
   	//alert('campo alterado: '+frmOrig[i].name+', orig='+frmOrig[i].value+', novo='+frmCurrent.elements[ frmOrig[i].name ].value);
   	return true;
   	}

   }

  return false;
}

// Copia os dados alterados para <containerName> (select-multiple)
// retorna true/false indicando se houve alteracoes
function getFormChanges(frmOrig, frmCurrent,containerName) {
 var i,v,aux,pk,len,res=false;
 var compVal = frmCurrent.elements[containerName];
 // limpa o buffer
 compVal.length = 0;

 for (i=0; i<frmOrig.length; i++) {
   if ( frmCurrent.elements[ frmOrig[i].name ] != null && 
        (frmCurrent.elements[ frmOrig[i].name ].type=='select-multiple' ||
         frmCurrent.elements[ frmOrig[i].name ].type=='checkbox') ) {
       //alert('getFormChanges - tipos nao tratados : '+frmCurrent.elements[ frmOrig[i].name ].type);
       //return false;
       return true;
       }

   if ( frmCurrent.elements[ frmOrig[i].name ]!=null &&
        frmCurrent.elements[ frmOrig[i].name ].value != frmOrig[i].value ) {
   	// padrao do nome: codExtUsuario_<cod_usuario_perfil>
	aux = frmOrig[i].name.split('_');
	pk  = aux[1]; // isola <cod_usuario_perfil> em pk

	// padrao do valor: <cod_usuario_perfil>_<cod_ext_usuario>
   	v = pk + '_' + frmCurrent.elements[ frmOrig[i].name ].value;
	len = compVal.length;
	compVal.options[len] = new Option(v, v);
	compVal.options[len].selected = true;
   	res=true;
   	}

   }

  return res;
}

function addSelectOption(compVal, val, tex) {
//alert("addSelectOption 1");
//alert("compVal=" + compVal);
    var len = compVal.length;
//    alert("len="+len);
//    alert("val="+val);
//    alert("tex="+tex);
//    alert("addSelectOption 2");
    var opt = new Option(tex, val, false, false);
//    alert("addSelectOption 3");    
	compVal.options[len] = opt;
//    alert("addSelectOption 4");
    compVal.options[len].selected = true;
}

function setSelectedItem(comp, itemValue) {
  if (comp.type!='select' && comp.type!='select-one' && comp.type!='select-multiple') 
    return '';

//  alert("itemValue" + itemValue);
  
  for (var x=0; x<comp.length; x++){
    if (comp.options[x].value == itemValue){
        comp.options[x].selected = true
        if(!comp.options[x].selected)
            comp.options[x].selectedIndex = x;
    }
  }
  return;  
}

function enableFormFields(frm) {
 var i=0;
 
  for (i=0; i<frm.length; i++) {
   if (frm[i].disabled)  frm[i].disabled = false;
  }   
}

function disableFormFields(frm) {
 var i=0;
 
  for (i=0; i<frm.length; i++) {
   if (frm[i].disabled==false)  frm[i].disabled = true;
  }   
}

function setDefaultComps(frm) {
  
  for (i=0; i<frm.length; i++) {
   if (frm[i].type=='radio' || frm[i].type=='checkbox')    	
   	frm[i].defaultChecked = frm[i].checked;
   else if (frm[i].type=='select-one' || frm[i].type=='select-multiple')    	
   	frm[i].defaultSelected = frm[i].selected;
  }
}

function changeCampoObrigat(obj, newValue) {
  var params = obj.id.split("|");
  var i=0;
  var styleId='';

  if (params!=null) {
  	styleId= params[0]; // javatype
  	styleId+='|';
  	styleId+= newValue;
  	styleId+='|';

  	for (i=2; i<params.length; i++) {
  	 styleId+= params[i];
  	 if (i<(params.length-1)) styleId+='|';
  	}
  }

  //alert('oldId='+obj.id+', newId='+styleId);
  obj.id = styleId;
}

function mensagem(){
  var frm = document.forms[0];

  if(typeof(frm.mensagem) != 'undefined' && frm.mensagem.value != ''){
    alert(frm.mensagem.value);
    frm.mensagem.value = '';
  }
}

function mensagemDecisao() {
  var frm = document.forms[0];

  if(typeof(frm.mensagemDecisao) != 'undefined'){
    var message = frm.mensagemDecisao.value;
    var url = 'popMessageDecision.jsp?message=' + message;

    frm.mensagemDecisao.value = '';

    popjan(url,380,120);
  }
}

function refreshPesquisa(){
  if(typeof(opener.document.forms[0].elements['consFornecHelper.filtroVo.numDocumento'].value) != 'undefined'){
    var doc = opener.document.forms[0].elements['consFornecHelper.filtroVo.numDocumento'].value;
    var nome = opener.document.forms[0].elements['consFornecHelper.filtroVo.razaoSocial'].value;
    var status = opener.document.forms[0].elements['consFornecHelper.filtroVo.codStatus'].value;
    var solicitante = opener.document.forms[0].elements['consFornecHelper.filtroVo.nomeSolicitante'].value;

    if(doc!='' || nome!='' || status>0 || solicitante!=''){
      opener.document.forms[0].submit();
      return;
    }
  }
}