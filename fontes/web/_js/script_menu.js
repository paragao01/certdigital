var enablepersist="on" //Enable saving state of content structure using session cookies? (on/off)
var collapseprevious="yes" //Collapse previously open content when opening present? (yes/no)
var cookiename = "mmain"
var seta = new Image();
seta.src = "_img/nvmenu0_out.gif";
var setab = new Image();
setab.src = "_img/nvmenu0_in.gif";
var seta_preta = new Image();
seta_preta.src = "_img/setapreta.gif";
var seta_pretab = new Image();
seta_pretab.src = "_img/setapreta_baixo.gif";

if (document.getElementById){
document.write('<style type="text/css">')
document.write('.switchcontent{display:none;}')
document.write('</style>')
}

function getElementbyClass(classname){
ccollect=new Array()
var inc=0
var alltags=document.all? document.all : document.getElementsByTagName("*")
for (i=0; i<alltags.length; i++){
if (alltags[i].className==classname)
ccollect[inc++]=alltags[i]
}
}

function contractcontent(omit){
var inc=0
while (ccollect[inc]){
if (ccollect[inc].id!=omit)
ccollect[inc].style.display="none";
if(typeof document[ccollect[inc].id+"img"] !=  "undefined")
{
	document[ccollect[inc].id+"img"].src = seta.src;
}
inc++
}
}

function expandcontent(cid){
if (typeof ccollect!="undefined"){
if (collapseprevious=="yes")
contractcontent(cid)
	if(document.getElementById(cid).style.display!="block")
	{
		document.getElementById(cid).style.display = "block";
		if(typeof document[cid+"img"] !=  "undefined")
		{
			document[cid+"img"].src = setab.src;
		}
	}
	else
	{
		document.getElementById(cid).style.display = "none";
		if(typeof document[cid+"img"] !=  "undefined")
		{
			document[cid+"img"].src = seta.src;
		}
	}
}
}

function revivecontent(){
contractcontent("omitnothing")
selectedItem=getselectedItem()
selectedComponents=selectedItem.split("|")
for (i=0; i<selectedComponents.length-1; i++)
if (document.getElementById(selectedComponents[i]) != null && typeof document.getElementById(selectedComponents[i]) != "undefined")
{
	document.getElementById(selectedComponents[i]).style.display="block";
	if(typeof document[selectedComponents[i]+"img"] !=  "undefined")
	{
		document[selectedComponents[i]+"img"].src = setab.src;
	}
}
}

function get_cookie(Name) { 
var search = Name + "="
var returnvalue = "";
if (document.cookie.length > 0) {
offset = document.cookie.indexOf(search)
if (offset != -1) { 
offset += search.length
end = document.cookie.indexOf(";", offset);
if (end == -1) end = document.cookie.length;
returnvalue=unescape(document.cookie.substring(offset, end))
}
}
return returnvalue;
}

function getselectedItem(){
	var scookie = get_cookie(cookiename);
if (scookie != ""){
selectedItem=scookie
return selectedItem
}
else
return ""
}

function saveswitchstate(){
var inc=0, selectedItem=""
while (ccollect[inc]){
if (ccollect[inc].style.display=="block")
selectedItem+=ccollect[inc].id+"|"
inc++
}
document.cookie=cookiename+"="+selectedItem + ";Path=";
}

function do_onload(){
getElementbyClass("switchcontent")
if (enablepersist=="on" && typeof ccollect!="undefined")
revivecontent()
}


if (window.addEventListener)
window.addEventListener("load", do_onload, false)
else if (window.attachEvent)
window.attachEvent("onload", do_onload)
else if (document.getElementById)
window.onload=do_onload

if (enablepersist=="on" && document.getElementById)
window.onunload=saveswitchstate;