function popjan(objName, objW, objH)
{
//	if(navigator.appName.indexOf("Netscape") > (-1) || navigator.platform.indexOf("Win") < 0 )
//	{
		window.open(objName, "", "menubar=no, toolbar=no, location=no, scrollbars=no, status=no,resizable=no, width="+objW+", height="+objH+", left="+(screen.width/2 - (objW/2))+", top="+(screen.height/2 - (objH/2)));
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
