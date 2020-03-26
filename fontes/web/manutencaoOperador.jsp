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

<html:form action="manutencaoOperador.do" >
  <input type="hidden" name="metodo" value="atualizar"> 

  <html:hidden property="loginHelper.operadorVO.idOperador" />
  <html:hidden property="loginHelper.operadorVO.idEntidade" />  
  <html:hidden property="loginHelper.operadorVO.nomeEntidade" />  
  <html:hidden property="loginHelper.operadorVO.idEmpresa" />  
  <html:hidden property="loginHelper.operadorVO.nomeEmpresa" />  
  
  <html:hidden property="loginHelper.operadorVO.menuGerencialVO.alterarEntidade" />
  <html:hidden property="loginHelper.operadorVO.menuGerencialVO.consultarEntidade" />
  <html:hidden property="loginHelper.operadorVO.menuGerencialVO.alterarEmpresa" />
  <html:hidden property="loginHelper.operadorVO.menuGerencialVO.consultarEmpresa" />
  <html:hidden property="loginHelper.operadorVO.menuGerencialVO.alterarProduto" />
  <html:hidden property="loginHelper.operadorVO.menuGerencialVO.consultarProduto" />
  <html:hidden property="loginHelper.operadorVO.menuGerencialVO.alterarPreco" />
  <html:hidden property="loginHelper.operadorVO.menuGerencialVO.consultarPreco" />
  <html:hidden property="loginHelper.operadorVO.menuGerencialVO.consultarCertificado" />

  <html:hidden property="loginHelper.operadorVO.menuOperacionalVO.alterarOperador" />
  <html:hidden property="loginHelper.operadorVO.menuOperacionalVO.consultarOperador" />
  <html:hidden property="loginHelper.operadorVO.menuOperacionalVO.alterarCertificado" />
  <html:hidden property="loginHelper.operadorVO.menuOperacionalVO.consultarCertificado" />

<table width="591" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="587" height="133" valign="top"><table width="587" border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="563"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="78%" class="titTab">ALTERAR OPERADOR</td>
            <td width="15%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
            <td width="7%" class="bgTab">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
      </tr>
      <tr>
        <td width="10" height="257" class="bgLeft">&nbsp;</td>
          <td width="560" height="332" valign="top" class="fontLogin">
			<table width="560" height="246" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
              <tr class="titGrid"> 
                <td colspan="4">&nbsp;</td>
              </tr>
              <tr> 
                <td colspan="4" class="fontLogin"><table width="543" border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td width="76" class="fontLogin">&nbsp;</td>
                      <td width="452" class="fontVerdana">&nbsp;</td>
                    </tr>
                  </table></td>
              </tr>
              <tr class="destAuto"> 
                <td height="26" colspan="4" class="destAuto">Identificador:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <bean:write name="certdigitalForm" property="loginHelper.operadorVO.idOperador" /></td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="26" class="fontLogin">Entidade</td>
                <td colspan="3"><bean:write name="certdigitalForm" property="loginHelper.operadorVO.nomeEntidade" /></td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="26" class="fontLogin">Empresa</td>
                <td colspan="3"><bean:write name="certdigitalForm" property="loginHelper.operadorVO.nomeEmpresa" /></td>
              </tr>
              <tr class="linhaGrid1"> 
                <td width="136" height="20" class="fontLogin">Nome Operador:</td>
                <td colspan="3">
                	<html:text property="loginHelper.operadorVO.nomeOperador" style="width:150px" styleClass="textsField" maxlength="50" onkeyup="this.value = this.value.toUpperCase()"  styleId="S|S|50|Nome Operador|N" />                	
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td height="20" class="fontLogin">Senha:</td>
                <td colspan="3">
                	<html:text property="loginHelper.operadorVO.senha" style="width:150px" styleClass="textsField" maxlength="10" onkeyup="this.value = this.value.toUpperCase()"  styleId="S|S|10|Senha|N" />
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="23" class="fontLogin">Tipo operador:</td>
                <td colspan="3">
	                <html:select property="loginHelper.operadorVO.idTipoOperador" styleClass="lists" style="width:140px" styleId="S|S||Tipo de Operador|N">
	                	<option value="">...</option>
	                	<html:optionsCollection property="loginHelper.listaTipoOperadorVO" value="codigoTipoOperador" label="descricaoTipoOperador" />
	                </html:select>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="23" class="fontLogin">Status:</td>
                <td colspan="3">
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
               <tr class="linhaGrid"> 
                <td height="20" class="fontLogin">&nbsp;</td>
                <td colspan="3">&nbsp;</td>
              </tr>              
              <tr class="linhaGrid"> 
                <td height="20" class="fontLogin">&nbsp;</td>
                <td colspan="3">&nbsp;</td>
              </tr>              
              <tr class="linhaGrid"> 
                <td height="20" class="fontLogin">&nbsp;</td>
                <td colspan="3">&nbsp;</td>
              </tr>             
              <tr>
                <td height="28" colspan="4" align="right"> <table border="0" align="right" cellpadding="2" cellspacing="2">
                    <tr>
                      <%
		       			if (operador.getMenuOperacionalVO().isAlterarOperador()) {
                  	  %>
                      <td><a href="javascript:alterar();"><img src="_img/_bts/salvar.gif" width="55" height="18" border="0"></a></td>
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
