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
	<jsp:include page="SCRIPT/js_incluirOperador.jsp" /> 	
</script>
<jsp:include page="APP_TEMPLATES/waitLayer.jsp" /> 
</head>

<body onload="init();" onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >

<html:form action="pesquisaOperador.do" >
  <input type="hidden" name="metodo" value="pesquisar">

<table width="591" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="587" height="133" valign="top"><table width="587" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="563"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
                <td width="78%" class="titTab">OPERADOR</td>
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
                <td colspan="2">PESQUISAR OPERADOR</td>
              </tr>
              <tr> 
                <td colspan="2">&nbsp;</td>
              </tr>              
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Empresa</td>
                <td class="fontVerdana">
                  <html:text property="loginHelper.operadorVO.idEmpresa" styleClass="textsField" size="10" onkeyup="this.value = this.value.toUpperCase()" readonly="false" styleId="N|N|10|Empresa|N" />
                  -
				  <html:text property="loginHelper.operadorVO.nomeEmpresa" styleClass="textsField" size="10" readonly="false" style="width:150px" />
				  <%
					if (operador.getMenuOperacionalVO().isConsultarOperador()) {
  				  %>
				  <a href="javascript:limparOperador();pesquisar('pesquisarEmpresas','loginHelper.operadorVO.idEmpresa','loginHelper.operadorVO.nomeEmpresa','EMPRESA')" ><img src="_img/_bts/lupa.gif" width="18" height="16" border="0"></a>
				  <%
					}
  				  %>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Operador</td>
                <td class="fontVerdana">
                  <html:text property="loginHelper.operadorVO.idOperador" styleClass="textsField" size="10" onkeyup="this.value = this.value.toUpperCase()" readonly="false" styleId="N|N|10|Operador|N" />
                  -
				  <html:text property="loginHelper.operadorVO.nomeOperador" styleClass="textsField" size="10" readonly="false" style="width:150px" />
				  <%
					if (operador.getMenuOperacionalVO().isConsultarOperador()) {
  				  %>
                  <a href="javascript:pesquisar('pesquisarOperadores','loginHelper.operadorVO.idOperador','loginHelper.operadorVO.nomeOperador','OPERADOR','loginHelper.operadorVO.idEmpresa','Empresa')" ><img src="_img/_bts/lupa.gif" width="18" height="16" border="0"></a>
                  <%
					}
  				  %>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Status Operador</td>
                <td class="fontVerdana">
	                <html:select property="loginHelper.operadorVO.statusOperador" styleClass="lists" style="width:140px" styleId="S|S||Status|N">
	                	<option value="">...</option>
	                	<html:option value="<%=GlobalConstants.STATUS_OPERADOR_ATIVO%>">
	                		<%=GlobalConstants.DESCRICAO_STATUS_OPERADOR_ATIVO%>
	                	</html:option>
	                	<html:option value="<%=GlobalConstants.STATUS_OPERADOR_INATIVO%>">
	                		<%=GlobalConstants.DESCRICAO_STATUS_OPERADOR_INATIVO%>
	                	</html:option>
	                	<html:option value="<%=GlobalConstants.STATUS_OPERADOR_BLOQUEADO%>">
	                		<%=GlobalConstants.DESCRICAO_STATUS_OPERADOR_BLOQUEADO%>
	                	</html:option>	                	
	                </html:select>
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
						if (operador.getMenuOperacionalVO().isConsultarOperador()) {
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
