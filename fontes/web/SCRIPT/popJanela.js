function popjan(objName, objW, objH)
{
//	if(navigator.appName.indexOf("Netscape") > (-1) || navigator.platform.indexOf("Win") < 0 )
//	{
		window.open(objName, "", "menubar=no, toolbar=no, location=no, scrollbars=yes, status=no,resizable=no, width="+objW+", height="+objH+", left="+(screen.width/2 - (objW/2))+", top="+(screen.height/2 - (objH/2)));
//	}
/*	else
	{
		var h = objH + 25;
		var w = objW + 5;
		
		var sFeatures="dialogHeight: " + h + "px;";
		sFeatures += "dialogWidth: " + w + "px;";
		sFeatures += "center: yes;";
		sFeatures += "scroll: no;";
		sFeatures += "status: no;";
		sFeatures += "edge: raised;";
		sFeatures += "help: no;"; 
		sFeatures += "resizable: no;";
		
		window.showModalDialog(objName,"",sFeatures);		
	}*/
}

function popjanscroll(objName, objW, objH)
{
//	if(navigator.appName.indexOf("Netscape") > (-1) || navigator.platform.indexOf("Win") < 0 )
//	{
		window.open(objName, "", "menubar=no, toolbar=no, location=no, scrollbars=yes, status=no,resizable=no, width="+objW+", height="+objH+", left="+(screen.width/2 - (objW/2))+", top="+(screen.height/2 - (objH/2)));
//	}
/*	else
	{
		var h = objH + 25;
		var w = objW + 5;
		
		var sFeatures="dialogHeight: " + h + "px;";
		sFeatures += "dialogWidth: " + w + "px;";
		sFeatures += "center: yes;";
		sFeatures += "scroll: no;";
		sFeatures += "status: no;";
		sFeatures += "edge: raised;";
		sFeatures += "help: no;"; 
		sFeatures += "resizable: no;";
		
		window.showModalDialog(objName,"",sFeatures);		
	}*/
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v3.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
<!--
function CheckAll() {
  for (var i=0;i<document.form.elements.length;i++) {
    var x = document.form.elements[i];
    if (x.name == 'empresas') {
      x.checked = document.form.selall.checked;
    }
  }
}
// -->
<!--

function MM_popupMsg(msg) { //v1.0
  confirm(msg);
}
//-->

function onChangeSmart(that) {
	// Suspenso
    if (that.selectedIndex == 1) {
		lySusp.style["visibility"] = "visible";
		lyAssocSmart.style["visibility"] = "hidden";
	} else {
		lySusp.style["visibility"] = "hidden";
		lyAssocSmart.style["visibility"] = "visible";
	}
}

function onChangeAlim(that) {
	// Suspenso
    if (that.selectedIndex == 1) {
		lySusp.style["visibility"] = "visible";
		lyAssocAlimentacao.style["visibility"] = "hidden";
	} else {
		lySusp.style["visibility"] = "hidden";
		lyAssocAlimentacao.style["visibility"] = "visible";
	}
}
function onChangeRef(that) {
	// Suspenso
    if (that.selectedIndex == 1) {
     		lyBanco.style["visibility"] = "visible";
	} else {
			lyBanco.style["visibility"] = "hidden";
	}
}