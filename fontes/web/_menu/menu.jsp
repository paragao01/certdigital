<%@ page import ="br.certdigital.shared.util.*" contentType="text/html;charset=ISO-8859-1" %>
<%@ page import ="br.certdigital.vo.*"  %>
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Style-Type" CONTENT="text/css">
<script>
<!--
// Copyright 1999 by NeoText All Rights Reserved
// JavaScript Tree for Internet Explorer 4+ and Netscape Navigator 4+
// This code is protected by US Law.
//
// Conditions of use:         (For these conditions Software is defined as source code, programs,
//                        applets or compiled executables.  Conditions do not apply to NeoText Software)
//
//  1. The code has not heen altered and this copyright notice is in it's original wording.
//  2. The code is not publicly distributed compressed.
//  3. The code is not part of commercial software other then NeoText software.
//  4. The code is not accompanied by 3rd party software other then NeoText software.




// JavaScript code for setting up the tree

function menuObject(treeTop,treeLeft,itemHeight,usePlusLines,plusIconSrc,minusIconSrc,topJointIconSrc,midJointIconSrc,endJointIconSrc,hLineIconSrc,vLineIconSrc) {
// this function sets up the base object of the tree.
// all of these functions params are required.
// the height and width of the plus and minus icons are equal and set by
//  the equation (pmPercent of this.itemHeight), pmPercent stands for
//  Plus Minus Percent which is a variable declared below..

	this.treeTop = treeTop;
	this.treeLeft = treeLeft;
		// Starting X and Y position of the tree for painting.

	this.itemHeight=itemHeight;
		// Height of each and all items in the tree.

	this.menuItems=new Array();
		// holds all the menu items of this objects tree, of type menuItemObject

	this.usePlusLines=usePlusLines;
		// this variable set to true displays the icons below as treelines.

		this.plusIcon = new Image();
		this.plusIcon.src=plusIconSrc;
			// the icon used to indicate a tree may be expanded.

		this.minusIcon = new Image();
		this.minusIcon.src=minusIconSrc;
			// the icon used to indicate a tree may be retracted.

		this.topJointIcon = new Image();
		this.topJointIcon.src=topJointIconSrc;
			// the icon used for the item at the top of the tree if it's not expandable.

		this.midJointIcon = new Image();
		this.midJointIcon.src=midJointIconSrc;
			// the icon used for a non expandable item thats inbetween items on the tree.

		this.endJointIcon = new Image();
		this.endJointIcon.src=endJointIconSrc;
			// the icon used for a non expandable item at the end of a tree.

		this.hLineIcon = new Image();
		this.hLineIcon.src=hLineIconSrc;
			// the icon used for horizontal tree lines.

		this.vLineIcon = new Image();
		this.vLineIcon.src=vLineIconSrc;
			// the icon used for vertical tree lines.
}
function menuItemObject(bulletIconSrc,expandedBulletSrc,menuPicSrc,menuPicOverSrc,menuText,menuTextSize,menuTextColor,menuLink,menuLinkTarget,menuDescription) {
// this function sets up menu items of a tree object.
// bulletIconSrc can be set to "" to disable the bullet on the menuitem.
// if there are no submenus just leave the subMenu variable alone.
// either of menuPicSrc or menuText can be set to "" if both are set
//  they layer each other.
// menuPicOverSrc is the location of a pic for the menu to replace menuPicSrc when the mouse is over the pic.
// if menuPicOverSrc is set to "" then the normal
//  menuPicSrc is used when mouse over occurs.
// the menuLink can also be set to "" to disable the linking.


	this.subMenu=new Array();
		//submenu items an array of type menuItemObject.

	this.menuBullet = new Image();
	this.menuBullet.src=bulletIconSrc;
		//bullet icon for the menu item.

	this.expandedBullet = new Image();
	this.expandedBullet.src=expandedBulletSrc;
		//bullet icon for the menu item.

	this.menuPic = new Image();
	this.menuPic.src=menuPicSrc;
	this.menuPicOver = new Image();
	this.menuPicOver.src=menuPicOverSrc;
		//pic for the context (or background) of the menu item.

	if (navigator.appName=="Netscape") {this.picPtr="null";}
		//Pointer to find onMouseOver pic only once in nutscrape

	this.menuText=menuText;
	this.menuTextSize=menuTextSize;
	this.menuTextColor=menuTextColor;
		//text for the context of the menu item.

	this.menuLink=menuLink;
	this.menuLinkTarget=menuLinkTarget;
		//the HREF to link to when item is clicked.

	this.menuDescription=menuDescription;
		//ALT text of the menu item

	this.expanded=false;
		//this is a variable the code uses to show which menuItems are expanded.
		//this variable does not effect root items or items with out subitems.
}

function initImage(theImg,theSrc) {
	if (loadAll==true) {
		eval(theImg+'=new Image();');
		eval(theImg+'.src = '+theSrc+';');
		tmpstr=tmeImg+'.src';
	} else {
		eval('var '+theImg+'_src = '+theSrc+';');
	}
}
function getImage(theImg) {
	getImage=(loadAll==true)?tmeImg+'.src':tmeImg+'_src';
}


function expandTreeItem(menuItems,tItem) {
	var cnt;
	var found = false;
	for (cnt in menuItems) {
		if (!found) {
			if (menuItems[cnt].menuLink == tItem+'.html') {
				found = true;
			} else {
				found = expandTreeItem(menuItems[cnt].subMenu,tItem);
				if (found) {
					menuItems[cnt].expanded=true;
				}
			}
		}

	}
	return found;
}

// -->

<%
	OperadorVO operador = (OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO);
%>

var BrowserSwitch =  false;
var BackGroundColor = "";
var pmPercent = 50;
var trans2x2=new Image();
trans2x2.src="treePics/2x2.gif";
var menuBase = new menuObject(5,5,20,true,"treePics/plus.gif","treePics/minus.gif","treePics/top.gif","treePics/mid.gif","treePics/btm.gif","treePics/hline.gif","treePics/vline.gif");

if(<%= operador.getMenuGerencialVO().atLeastOneTrue() %>) {
   menuBase.menuItems[0]= new menuItemObject("","","","","Gerencial","2","Black","","","");
   if(<%= operador.getMenuGerencialVO().isAlterarEntidade() %> || <%= operador.getMenuGerencialVO().isConsultarEntidade() %> ) {
		menuBase.menuItems[0].subMenu[10]= new menuItemObject("","","","","Entidade","2","Black","","","");
		if(<%= operador.getMenuGerencialVO().isAlterarEntidade() %> && <%=operador.getIdTipoOperador().longValue()==1%>) {
		   menuBase.menuItems[0].subMenu[10].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirEntidade.do?metodo=prepararFiltro","mainFrame","");
		}
		if(<%= operador.getMenuGerencialVO().isConsultarEntidade() %>) {
		   menuBase.menuItems[0].subMenu[10].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaEntidade.do?metodo=pesquisar","mainFrame","");
		}
   }
   if(<%= operador.getMenuGerencialVO().isAlterarEmpresa() %> || <%= operador.getMenuGerencialVO().isConsultarEmpresa() %> ) {
		menuBase.menuItems[0].subMenu[20]= new menuItemObject("","","","","Empresa","2","Black","","","");
		if(<%= operador.getMenuGerencialVO().isAlterarEmpresa() %> ) {
		   menuBase.menuItems[0].subMenu[20].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirEmpresa.do?metodo=prepararFiltro","mainFrame","");
		}
		if(<%= operador.getMenuGerencialVO().isConsultarEmpresa() %>) {
		   menuBase.menuItems[0].subMenu[20].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../pesquisaEmpresa.do?metodo=prepararPesquisa","mainFrame","");
		}
   }
   if(<%= operador.getMenuGerencialVO().isAlterarProduto() %> || <%= operador.getMenuGerencialVO().isConsultarProduto() %> ) {
		menuBase.menuItems[0].subMenu[30]= new menuItemObject("","","","","Produto","2","Black","","","");
		if(<%= operador.getMenuGerencialVO().isAlterarProduto() %> ) {
		   menuBase.menuItems[0].subMenu[30].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirProduto.do?metodo=prepararFiltro","mainFrame","");
		}
		if(<%= operador.getMenuGerencialVO().isConsultarProduto() %>) {
		   menuBase.menuItems[0].subMenu[30].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../pesquisaProduto.do?metodo=prepararPesquisa","mainFrame","");
		}
   }
   if(<%= operador.getMenuGerencialVO().isAlterarPreco() %> || <%= operador.getMenuGerencialVO().isConsultarPreco() %> ) {
		menuBase.menuItems[0].subMenu[40]= new menuItemObject("","","","","Pre&ccedil;o","2","Black","","","");
		if(<%= operador.getMenuGerencialVO().isAlterarPreco() %> ) {
		   menuBase.menuItems[0].subMenu[40].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirPreco.do?metodo=prepararFiltro","mainFrame","");
		}
		if(<%= operador.getMenuGerencialVO().isConsultarPreco() %>) {
		   menuBase.menuItems[0].subMenu[40].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../pesquisaPreco.do?metodo=prepararPesquisa","mainFrame","");
		}
   }
   if(<%= operador.getMenuGerencialVO().isConsultarCertificado() %> || <%= operador.getMenuGerencialVO().isConsultarTabelaPreco() %> || <%= operador.getMenuGerencialVO().isConsultarFaturamento() %>) {
		menuBase.menuItems[0].subMenu[50]= new menuItemObject("","","","","Consultar","2","Black","","","");
		if(<%= operador.getMenuGerencialVO().isConsultarCertificado() %>) {
	   		menuBase.menuItems[0].subMenu[50].subMenu[0]= new menuItemObject("","","","","Certificado","2","Black","../pesquisaConsultarCertificado.do?metodo=prepararConsulta","mainFrame","");
		}
		if(<%= operador.getMenuGerencialVO().isConsultarTabelaPreco() %>) {
	   		menuBase.menuItems[0].subMenu[50].subMenu[1]= new menuItemObject("","","","","Tabela de Pre&ccedil;o","2","Black","../consultarTabelaPreco.do?metodo=consultarTabelaPreco","mainFrame","");
		}
		if(<%= operador.getMenuGerencialVO().isConsultarFaturamento() %>) {
	   		//menuBase.menuItems[0].subMenu[50].subMenu[2]= new menuItemObject("","","","","Faturamento","2","Black","../consultarFaturamento.do?metodo=prepararFiltro","mainFrame","");
		}
   }
}
if(<%= operador.getMenuOperacionalVO().atLeastOneTrue() %>) {
   menuBase.menuItems[1]= new menuItemObject("","","","","Operacional","2","Black","","","");
   if(<%= operador.getMenuOperacionalVO().isAlterarOperador() %> || <%= operador.getMenuOperacionalVO().isConsultarOperador() %>) {	
		menuBase.menuItems[1].subMenu[10]= new menuItemObject("","","","","Operador","2","Black","","","");
		if(<%= operador.getMenuOperacionalVO().isAlterarOperador() %>) {
	   	menuBase.menuItems[1].subMenu[10].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirOperador.do?metodo=prepararFiltro","mainFrame","");
		}
		if(<%= operador.getMenuOperacionalVO().isConsultarOperador() %>) {	
	   	menuBase.menuItems[1].subMenu[10].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../pesquisaOperador.do?metodo=prepararPesquisa","mainFrame",""); 
		}
   }
   if(<%= operador.getMenuOperacionalVO().isAlterarCertificado() %> || <%= operador.getMenuOperacionalVO().isConsultarCertificado() %>) {
	menuBase.menuItems[1].subMenu[20]= new menuItemObject("","","","","Certificado","2","Black","","","");
	if(<%= operador.getMenuOperacionalVO().isAlterarCertificado() %>) {
	   menuBase.menuItems[1].subMenu[20].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirCertificado.do?metodo=prepararFiltro","mainFrame","");
	}
	if(<%= operador.getMenuOperacionalVO().isConsultarCertificado() %>) {
	   menuBase.menuItems[1].subMenu[20].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../pesquisaCertificado.do?metodo=prepararPesquisa","mainFrame",""); 
	}
   }
}
</SCRIPT>

<link rel="stylesheet" href="../_css/cdlrioStilo.css" type="text/css">
<link href="../_css/cdlrio.css" rel="stylesheet" type="text/css">
<script src="../SCRIPT/controle_teclas.js"></script>
</HEAD>

<body onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)">

  <script>
<!--
if (BackGroundColor!="") {
	document.write('<BODY BGCOLOR="'+BackGroundColor+'" TEXT="#000000" VLINK="#636363" ALINK="#000000" LINK="#FF0000">');
} else {
	document.write('<BODY TEXT="#000000" VLINK="#636363" ALINK="#000000" LINK="#FF0000">');
}
// JavaScript code for creating the tree

//the fallowing variables and functions should not be changed unless you absolutly know what your doing.
bVersion = parseInt(navigator.appVersion);

var blankSrc = new Image();
blankSrc.src="";



//*********************************************************************************************************
//Browsers 4.0 and above (Animated engine)
//*********************************************************************************************************

if ((bVersion > 3)&&(!BrowserSwitch)) {

var itemNum=0;
var itemsPtr=new Array();
var cTop=0;
var cLeft=0;
var itemCode="";

function reverseString(theStr) {
	tmpStr="";
	var cnt;
	for (cnt=theStr.length-1;cnt>=0;cnt--) {
		tmpStr=tmpStr+theStr.substring(cnt,cnt+1);
	}
	return tmpStr;
}
function pushNum(theNum,newNum) {
	theNum=theNum+"_"+newNum;
	return theNum;
}
function popNum(theNum) {
	theNum=reverseString(theNum);
	var start=theNum.indexOf("_",0);
	var end=theNum.length-1;
	theNum=theNum.substring(start,end);
	theNum=reverseString(theNum);
	return theNum;
}
function menuItemChange(itemNum,newPic) {
	if (navigator.appName=="Netscape") {
		if (itemsPtr[itemNum].picPtr=="null") {
			var cnt;
			var cnt2;
			var theImg;
			for(cnt=0;cnt<document.layers[0].document.layers.length;cnt++) {
				for(cnt2=0;cnt2<eval('document.layers[0].document.layers['+cnt+'].document.images.length');cnt2++) {
					if (eval('document.layers[0].document.layers['+cnt+'].document.images['+cnt2+'].name')==('imageNum'+itemNum)) {
						theImg=eval('document.layers[0].document.layers['+cnt+'].document.images[cnt2]');
					}
				}
			}
			itemsPtr[itemNum].picPtr=theImg;
		}
			itemsPtr[itemNum].picPtr.src=newPic.src;
	} else {
		eval('imageNum'+itemNum+'.src = newPic.src;');
	}
}

function menuItemClick(itemNum) {
	//The fallowing code thats commented out is for trees with one subitem,
	//the code will unexpand all but the clicked item.  The line of code that
	//isn't commented out is the regular style.

	itemsPtr[itemNum].expanded= (!itemsPtr[itemNum].expanded);

	//var cnt;
	//for (cnt in itemsPtr) {
	//	if (itemNum==cnt) {
	//		itemsPtr[cnt].expanded= (!itemsPtr[cnt].expanded);
	//	} else {
	//		itemsPtr[cnt].expanded= false;
	//	}
	//}
	refreshTree();
}
function paintMenu(newCode) {
/*
	if (navigator.appName=="Netscape") {
		A = document.getElementById("tree");
		A.document.open("text/html","replace");
		A.document.write(newCode);
		A.document.close();
	}
	else {
*/
A = document.getElementById("tree");
A.innerHTML=newCode;
//	}
}
function addLink(menuItem,capCode) {
	var tmpCode="";
	tmpCode=tmpCode+'<A HREF="';

	if (menuItem.menuLink!="") {
		tmpCode=tmpCode+menuItem.menuLink;
	} else {
		tmpCode=tmpCode+'#';
	}
	tmpCode=tmpCode+'"';

	if (menuItem.menuLinkTarget!="") {
		tmpCode=tmpCode+'TARGET="'+menuItem.menuLinkTarget+'"';
	}
	if (menuItem.menuPicOver.src!=blankSrc.src) {
		tmpCode=tmpCode+' OnMouseOver="menuItemChange(';
		tmpCode=tmpCode+"'"+itemNum+"',itemsPtr["+itemNum+"].menuPicOver";
		tmpCode=tmpCode+');return true;" OnMouseOut="menuItemChange(';
		tmpCode=tmpCode+"'"+itemNum+"',itemsPtr["+itemNum+"].menuPic";
		tmpCode=tmpCode+');return true;"';
	}
	if (menuItem.subMenu.length>0) {
		tmpCode=tmpCode+' OnClick="menuItemClick(';
		tmpCode=tmpCode+"'"+itemNum+"'";
		tmpCode=tmpCode+');return false;"';
	}

	tmpCode=tmpCode+'>';

	tmpCode=tmpCode+capCode;

	tmpCode=tmpCode+'</A>';
	return tmpCode;

}
function addSpan(left,top,height,width,capCode) {
	var tmpWidth = (width==""?'':'width:'+width+'; ');
	capCode='<SPAN STYLE="position: absolute; left:'+left+'; top:'+top+'; '+tmpWidth+'height:'+height+'">'+capCode;
	capCode=capCode+'</SPAN>';
	return capCode;
}

var plusSize=0;
var lineSize=0;
var headerCnt=0;

function refreshTree2(menuItem,headerLines) {
var cnt;
var cnt2;
var tmpCode;
var expIcon;
var currentHeader;
var tmpIndent;
for (cnt in menuItem) {
	tmpCode="";
	currentHeader="";
	if (menuBase.usePlusLines==true) {
		if (menuItem[cnt].subMenu.length>0) {
			//Item as plus/minus
			if (menuItem[cnt].expanded==true) {
				expIcon=menuBase.minusIcon.src;
			} else {
				expIcon=menuBase.plusIcon.src;
			}
			if ((cnt==0) && (itemNum==0)){
				currentHeader=currentHeader+addSpan(cLeft,cTop,lineSize,menuBase.itemHeight,'<IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+lineSize+' WIDTH='+menuBase.itemHeight+'>');
			} else {
				currentHeader=currentHeader+addSpan(cLeft,cTop,lineSize,menuBase.itemHeight,'<IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+lineSize+' WIDTH='+lineSize+'><IMG SRC="'+menuBase.vLineIcon.src+'" BORDER=0 HEIGHT='+lineSize+' WIDTH='+plusSize+'><IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+lineSize+' WIDTH='+lineSize+'>');
			}
			currentHeader=currentHeader+addSpan(cLeft,cTop+lineSize,plusSize,menuBase.itemHeight,addLink(menuItem[cnt],'<IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+plusSize+' WIDTH='+lineSize+'><IMG SRC="'+expIcon+'" BORDER=0 HEIGHT='+plusSize+' WIDTH='+plusSize+'><IMG SRC="'+menuBase.hLineIcon.src+'" BORDER=0 HEIGHT='+plusSize+' WIDTH='+lineSize+'>'));
			if (cnt==menuItem.length-1)  {
				currentHeader=currentHeader+addSpan(cLeft,cTop+lineSize+plusSize,lineSize,menuBase.itemHeight,'<IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+lineSize+' WIDTH='+menuBase.itemHeight+'>');
			} else {
				currentHeader=currentHeader+addSpan(cLeft,cTop+lineSize+plusSize,lineSize,menuBase.itemHeight,'<IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+lineSize+' WIDTH='+lineSize+'><IMG SRC="'+menuBase.vLineIcon.src+'" BORDER=0 HEIGHT='+lineSize+' WIDTH='+plusSize+'><IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+lineSize+' WIDTH='+lineSize+'>');
			}
		} else {
			//Item does not have plus/minus

			if ((cnt==0) && (itemNum==0)) {
					if (menuItem.length>1) {
						currentHeader=currentHeader+addSpan(cLeft,cTop,menuBase.itemHeight,menuBase.itemHeight,'<IMG SRC="'+menuBase.topJointIcon.src+'" BORDER=0 HEIGHT='+menuBase.itemHeight+' WIDTH='+menuBase.itemHeight+'>');
					} else {
						currentHeader=currentHeader+addSpan(cLeft,cTop,menuBase.itemHeight,menuBase.itemHeight,'<IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+menuBase.itemHeight+' WIDTH='+menuBase.itemHeight+'>');
					}
			} else {
				if (cnt==menuItem.length-1) {
						currentHeader=currentHeader+addSpan(cLeft,cTop,menuBase.itemHeight,menuBase.itemHeight,'<IMG SRC="'+menuBase.endJointIcon.src+'" BORDER=0 HEIGHT='+menuBase.itemHeight+' WIDTH='+menuBase.itemHeight+'>');
				} else {
						currentHeader=currentHeader+addSpan(cLeft,cTop,menuBase.itemHeight,menuBase.itemHeight,'<IMG SRC="'+menuBase.midJointIcon.src+'" BORDER=0 HEIGHT='+menuBase.itemHeight+' WIDTH='+menuBase.itemHeight+'>');
				}
			}
		}
		for (cnt2=0;cnt2<headerCnt;cnt2++) {
			tmpCode=tmpCode+addSpan(menuBase.treeLeft+(cnt2*menuBase.itemHeight),cTop,menuBase.itemHeight,menuBase.itemHeight,headerLines[cnt2])
		}

		tmpCode=tmpCode+currentHeader;
		cLeft=cLeft+menuBase.itemHeight;
	}

	if (menuItem[cnt].menuBullet.src!=blankSrc.src) {
		if ((menuItem[cnt].expanded==true) && (menuItem[cnt].expandedBullet.src!=blankSrc.src)) {
			expIcon = menuItem[cnt].expandedBullet.src;
		} else {
			expIcon = menuItem[cnt].menuBullet.src;
		}
		tmpCode=tmpCode+addSpan(cLeft,cTop,menuBase.itemHeight,menuBase.itemHeight,addLink(menuItem[cnt],'<IMG SRC="'+expIcon+'" BORDER=0>'));
		cLeft=cLeft+menuBase.itemHeight;
	}
	if (menuItem[cnt].menuPic.src!=blankSrc.src) {
		tmpCode=tmpCode+addSpan(cLeft,cTop,menuBase.itemHeight,'',addLink(menuItem[cnt],'<IMG SRC="'+menuItem[cnt].menuPic.src+'" NAME="imageNum'+itemNum+'" BORDER=0>'));
		}
	if (menuItem[cnt].menuText!="") {
		tmpCode=tmpCode+addSpan(cLeft,cTop,menuBase.itemHeight,'',addLink(menuItem[cnt],'<NOBR><FONT SIZE="'+menuItem[cnt].menuTextSize+'" COLOR="'+menuItem[cnt].menuTextColor+'">'+menuItem[cnt].menuText+'</FONT></NOBR>'));
		}
	if (menuItem[cnt].menuBullet.src!=blankSrc.src) {
		cLeft=cLeft-menuBase.itemHeight;
	}
	itemCode=itemCode+tmpCode;
	if (navigator.appName=="Netscape") {menuItem[cnt].picPtr="null";}
	itemsPtr[itemNum]=menuItem[cnt];
	itemNum++;
	cTop=cTop+menuBase.itemHeight;
	if (menuBase.usePlusLines==true) {cLeft=cLeft-menuBase.itemHeight};
	if ((menuItem[cnt].subMenu.length>0) && (menuItem[cnt].expanded==true)) {
		if (menuBase.usePlusLines==true) {
			tmpIndent="";
			if (cnt==menuItem.length-1)  {
				tmpIndent='<IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+menuBase.itemHeight+' WIDTH='+menuBase.itemHeight+'>';
			} else {
				tmpIndent='<IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+lineSize+' WIDTH='+lineSize+'><IMG SRC="'+menuBase.vLineIcon.src+'" BORDER=0 HEIGHT='+menuBase.itemHeight+' WIDTH='+plusSize+'><IMG SRC="'+trans2x2.src+'" BORDER=0 HEIGHT='+lineSize+' WIDTH='+lineSize+'>';
			}
			headerLines[headerCnt] =tmpIndent;
			headerCnt++;
		}
		cLeft=cLeft+menuBase.itemHeight;
		refreshTree2(menuItem[cnt].subMenu,headerLines);
		if (menuBase.usePlusLines==true) {
			headerLines[headerCnt]="";
			headerCnt--;
		}
		cLeft=cLeft-menuBase.itemHeight;
		}
	}
}
function refreshTree() {
//this function is called to refresh a tree initialized by paintTree().
	var headerLines=new Array();
	itemNum=0;
	cTop=menuBase.treeTop;
	cLeft=menuBase.treeLeft;
	plusSize = Math.round(((pmPercent /100) * menuBase.itemHeight));
	lineSize = Math.round( ((menuBase.itemHeight-plusSize)/2) );
	itemCode="";
        refreshTree2(menuBase.menuItems,headerLines);
	paintMenu(itemCode);
        itemCode="";
	itemNum=0;
	return true;
}

if (navigator.appName=="Netscape") {
	var nutScrape=1;
	function reTime() {
		if (nutScrape==1) {
			nutScrape=2;
			document.location.reload();
		} else {
			if (nutScrape==2) {nutScrape=1}
		}
		return false;
	}
}

document.write('<SPAN ID=tree STYLE="position: absolute; left:'+menuBase.treeLeft+'; top:'+menuBase.treeTop+'; width:100%; height:100%"> </SPAN>');

//show refresh the tree

window.onload=refreshTree;

if (navigator.appName=="Netscape") {
	window.onResize=reTime;
}

//*********************************************************************************************************
//Browsers 3.0 and below (Nonanimated engine) (Note: Not perfected but usefull)
//*********************************************************************************************************

} else {

//*********************************************************************************************************
//Browsers 3.0 and below (Nonanimated engine) (Note: Not perfected but usefull)
//*********************************************************************************************************

function naPaintTree(menuItems,indent) {
	var cnt;
	var nextLine;
	for (cnt in menuItems) {


		nextLine=indent+'<FONT';

		if (menuItems[cnt].menuTextColor!="") {
			nextLine+=' COLOR="'+menuItems[cnt].menuTextColor+'"';
		}
		if (menuItems[cnt].menuTextSize!="") {
			nextLine+=' SIZE="'+menuItems[cnt].menuTextSize+'"';
		}

		nextLine+='>';

		if (menuItems[cnt].menuLink!="") {
			nextLine+='<A HREF="'+menuItems[cnt].menuLink+'" TARGET="'+menuItems[cnt].menuLinkTarget+'">';
		}

		if (menuItems[cnt].menuBullet.src!=blankSrc.src) {
			nextLine+='<IMG SRC="'+menuItems[cnt].menuBullet.src+'" BORDER=0>&nbsp;';
		}

		if (menuItems[cnt].menuPic.src!=blankSrc.src) {
			nextLine+='<IMG SRC="'+menuItems[cnt].menuPic.src+'" BORDER=0>';
		} else {
			nextLine+=menuItems[cnt].menuText;
		}


		if (menuItems[cnt].menuLink!="") {
			nextLine+='</A>';}


		nextLine+='</FONT><BR>';

		document.write(nextLine);

		naPaintTree(menuItems[cnt].subMenu,'&nbsp;&nbsp;&nbsp;&nbsp;'+indent);
	}
}

naPaintTree(menuBase.menuItems,"");

}


document.write('</BODY>');

// -->
</script>
</HTML>