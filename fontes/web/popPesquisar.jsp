<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<%@ page import ="br.certdigital.shared.util.*" contentType="text/html;charset=ISO-8859-1" %>

<% br.certdigital.vo.OperadorVO operador = (br.certdigital.vo.OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Certdigital</title>
<link href="_css/cdlrio.css" rel="stylesheet" type="text/css">
<link href="_css/cdlrioStilo.css" rel="stylesheet" type="text/css">
<script src="_js/script_menu.js"></script>
<script src="_js/cdlScript.js"></script>
<script src="SCRIPT/controle_teclas.js"></script>
</head>

<script>
<jsp:include page="SCRIPT/js_pesquisas.jsp" /> 
</script>
<jsp:include page="APP_TEMPLATES/waitLayer.jsp" /> 
</head>

<body onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >
<%try{%>
<html:form action="pesquisar.do" >
  <html:hidden property="metodo" />
  <html:hidden property="pesquisaHelper.campoCodigo" />
  <html:hidden property="pesquisaHelper.campoNome" />
  <html:hidden property="pesquisaHelper.valorRestricaoOpcional" />
  <html:hidden property="pesquisaHelper.labelValorRestricaoOpcional" />  
  <html:hidden property="pesquisaHelper.labelJanela" />

<table width="475" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="471" height="133" valign="top"><table width="471" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="11" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="446"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="64%" class="titTab">PESQUISAR&nbsp;<bean:write name="certdigitalForm" property="pesquisaHelper.labelJanela" /></td>
            <td width="29%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
            <td width="7%" class="bgTab">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
      </tr>
      <tr>
        <td width="11" height="229" class="bgLeft">&nbsp;</td>
        <td width="446" height="229" valign="top"><table width="446" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
          <tr class="linhaGrid1">
            <td width="70" height="21" class="fontVerdanaBold">Descrição:</td>
            <td width="367" height="21"><table width="100%"  border="0" cellspacing="1" cellpadding="1">
              <tr>
                <td width="57%">
                	<html:text property="pesquisaHelper.chavePesquisa" styleClass="textsField" style="width:250px" maxlength="50" onblur="this.value = this.value.toUpperCase()" />
                </td>
                <td width="43%" valign="middle"><a href="javascript:executarPesquisa();"><img src="_img/_bts/pesquisar.gif" width="74" height="18" border="0"></a></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td colspan="2">
            <div id="lyCorpo" style="position:relative; width:443px; height:195px; top: 0px; left: 0; overflow: auto; z-index: 1; visibility: visible;">
	            <table border="0" cellpadding="1" cellspacing="1">
	              <tr align="left" class="subTextoAviso">
	                <td height="15" width="57" align="left">CÓDIGO</td>
	                <td height="15" width="360" align="left">DESCRIÇÃO</td>
	              </tr>

                <% int linha = 0; %>
                <logic:present name="certdigitalForm" property="pesquisaHelper.listaItemPesquisaVO">
	                <logic:iterate name="certdigitalForm" property="pesquisaHelper.listaItemPesquisaVO" id="item">

		                <tr align="center" class="linhaGrid<%=((linha%2)==0)?"":"1" %>">
		                  <td height="15" align="right"><a href='javascript:selecionar("<bean:write name="item" property="codigo" />", "<bean:write name="item" property="nome" />");'><bean:write name="item" property="codigo" /></a></td>
		                  <td height="15" align="left"><bean:write name="item" property="nome" /></td>
		                </tr>
	
		                <% linha++; %>

	                </logic:iterate>
	            </logic:present>

	            </table>
            </div>
            </td>
          </tr>
        </table></td>
        <td width="14" class="bgRight">&nbsp;</td>
      </tr>
      <tr>
        <td width="11" height="19" valign="top"><span class="bgLeft"><img name="tla_r3_c1" src="_img/_recortes/tla_r3_c1.gif" width="10" height="13" border="0" alt=""></span></td>
        <td class="bgTabRoda">&nbsp;</td>
        <td width="14" valign="top"><span class="bgRight"><img name="tla_r3_c5" src="_img/_recortes/tla_r3_c5.gif" width="13" height="13" border="0" alt=""></span></td>
      </tr>
    </table>    </td>
  </tr>
</table>
</html:form>
<%}catch (Exception e) {
    e.printStackTrace();
}%>

</body>
</html>
