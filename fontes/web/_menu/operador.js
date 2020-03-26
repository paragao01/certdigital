// 'DO NOT WRITE TO OR ALTER THIS FILE
// 'Even if you edit the JavaScript your risking
// 'not being able to load it back into Creata-Tree
// '
// '
// '
// '
//# Generator = Creata-Tree
//# Version = 2.0.3
// '
// 'About: Creata-Tree by NeoText, download free at http://www.winternet.com/~chrome/maxftp
// 'Copyright 1999, by NeoText
// '
// '
//# TreeX = 5
//# TreeY = 5
//# TreeSize = 1
//# TreeLines = 1
//# TreeLineColor = Black
//# TreePMColor = Black
//# TreeBNonExpanded = 
//# TreeBExpanded = 
//# TreeLeaf = 
//# TreeTarget = 
//# TreeBackground = 
//# TreeFontSize = 2
//# TreeFontColor = Olive
//\\
var BrowserSwitch =  false;
var BackGroundColor = "";
var pmPercent = 50;
var trans2x2=new Image();
trans2x2.src="treePics/2x2.gif";
var menuBase = new menuObject(5,5,20,true,"treePics/plus.gif","treePics/minus.gif","treePics/top.gif","treePics/mid.gif","treePics/btm.gif","treePics/hline.gif","treePics/vline.gif");

menuBase.menuItems[0]= new menuItemObject("","","","","Consultar","2","Black","","","");
menuBase.menuItems[0].subMenu[10]= new menuItemObject("","","","","Garantir Cheque","2","Black","../garantirCheque.do?metodo=prepararFiltro","mainFrame","");
