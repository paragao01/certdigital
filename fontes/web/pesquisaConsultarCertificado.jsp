<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<%@ page import ="br.certdigital.shared.util.*" contentType="text/html;charset=ISO-8859-1" %>

<% br.certdigital.vo.OperadorVO operador = (br.certdigital.vo.OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO); %>

<html>
<head>
<title>Certdigital</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link href="_css/cdlrio.css" rel="stylesheet" type="text/css">
<link href="_css/cdlrioStilo.css" rel="stylesheet" type="text/css">
<script src="_js/script_menu.js"></script>
<script src="_js/cdlScript.js"></script>
<script src="SCRIPT/util.js"></script>
<script src="SCRIPT/funcoes.js"></script>
<script src="SCRIPT/general.js"></script>
<script src="SCRIPT/generic.jsp"></script>
<script src="SCRIPT/controle_teclas.js"></script>
<script>
	<jsp:include page="SCRIPT/js_pesquisas.jsp" /> 
</script>
<jsp:include page="APP_TEMPLATES/waitLayer.jsp" /> 
</head>

<body onload="init();" onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >

<html:form action="pesquisaConsultarCertificado.do" >
  <input type="hidden" name="metodo" value="consultarCertificado">

<table width="591" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="587" height="133" valign="top"><table width="587" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="563"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
                <td width="78%" class="titTab">CONSULTAR</td>
            <td width="15%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
            <td width="7%" class="bgTab">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
      </tr>
      <tr>
        <td width="10" height="257" class="bgLeft">&nbsp;</td>
        <td width="560" height="332" valign="top"><table width="560" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
              <tr class="titGrid"> 
                <td colspan="2">PESQUISAR CONSULTA CERTIFICADO</td>
              </tr>
              <tr> 
                <td colspan="2">&nbsp;</td>
              </tr>              
              <tr class="linhaGrid1"> 
                <td class="fontLogin">M&ecirc;s</td>
                <td class="fontVerdana">
                  <html:text property="consultarHelper.mes" styleClass="textsField" size="2" styleId="N|S|2|Mes|S" />
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Ano</td>
                <td class="fontVerdana">
                  <html:text property="consultarHelper.ano" styleClass="textsField" size="4" styleId="N|S|4|Ano|S" />
                </td>
              </tr>
              <tr> 
                <td width="75" height="126">&nbsp;</td>
                <td width="474" height="126" valign="top">&nbsp;</td>
              </tr>
              <tr> 
                <td colspan="2"><table border="0" align="right" cellpadding="2" cellspacing="2">
                    <tr>
                      <%
						if (operador.getMenuGerencialVO().isConsultarCertificado()) {
  				  	  %>
                      <td><a href="javascript:executarPesquisa();"><img src="_img/_bts/pesquisar.gif" width="74" height="18" border="0"></a></td>
                      <td><a href="javascript:limpar();"><img src="_img/_bts/limpar.gif" width="54" height="18" border="0"></a></td>
                      <%
						}
  				  	  %>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        <td width="14" class="bgRight">&nbsp;</td>
      </tr>
      <tr>
        <td width="10" valign="top"><span class="bgLeft"><img name="tla_r3_c1" src="_img/_recortes/tla_r3_c1.gif" width="10" height="13" border="0" alt=""></span></td>
        <td class="bgTabRoda">&nbsp;</td>
        <td width="14" valign="top"><span class="bgRight"><img name="tla_r3_c5" src="_img/_recortes/tla_r3_c5.gif" width="13" height="13" border="0" alt=""></span></td>
      </tr>
    </table>    </td>
  </tr>
</table>
</html:form>
  
<!-- Container das mensagens dos Actions -->
<jsp:include page="APP_TEMPLATES/msgBuffer.jsp" />

</body>
</html>
