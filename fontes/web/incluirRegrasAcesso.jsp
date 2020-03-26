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
<jsp:include page="SCRIPT/js_incluirRegrasAcesso.jsp" /> 
</script>

<jsp:include page="APP_TEMPLATES/waitLayer.jsp" /> 

</head>

<body onLoad="init();" onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >

<html:form action="incluirOperador.do" >
  <html:hidden property="metodo" />
  <html:hidden property="loginHelper.operadorVO.idTipoOperador" />
  <html:hidden property="loginHelper.operadorVO.nomeOperador" />
  <html:hidden property="loginHelper.operadorVO.senha" />
  <html:hidden property="loginHelper.operadorVO.statusOperador" />
  <html:hidden property="loginHelper.operadorVO.idEntidade" />
  <html:hidden property="loginHelper.operadorVO.idEmpresa" />
  
<table width="545" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="564" height="133" valign="top"><table width="540" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td width="11" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
          <td width="536"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="78%" class="titTab">DEFINI&Ccedil;&Otilde;ES DE ACESSO DO OPERADOR</td>
                <td width="15%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
                <td width="7%" class="bgTab">&nbsp;</td>
              </tr>
            </table></td>
          <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
        </tr>
        <tr>
          <td height="21" class="bgLeft">&nbsp;</td>
          <td height="21" valign="top" class="fontLogin"></td>
          <td class="bgRight">&nbsp;</td>
        </tr>
        <tr> 
          <td width="11" height="257" class="bgLeft">&nbsp;</td>
          <td height="332" valign="top">
            <table width="470" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
              <tr class="titGrid"> 
                <td colspan="4">GERENCIAL </td>
              </tr>
              <tr class="subTextoAviso"> 
                <td colspan="2" class="fontLogin">MENU</td>
                <td width="90" class="fontVerdanaBold">Alterar</td>
                <td width="90" class="fontVerdanaBold">Consultar</td>
              </tr>
              <tr class="linhaGrid"> 
                <td width="11" class="fontLogin">&nbsp;</td>
                <td width="283" class="fontLogin">Entidade</td>
     
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.alterarEntidade" onclick="javascript:checkCorrespondente(this, 'loginHelper.operadorVO.menuGerencialVO.consultarEntidade')"/></td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.consultarEntidade" onclick="javascript:uncheckCorrespondente('loginHelper.operadorVO.menuGerencialVO.alterarEntidade')" /></td>
              </tr>
              <tr class="linhaGrid1"> 
                <td width="11" class="fontLogin">&nbsp;</td>
                <td width="283" class="fontLogin">Empresa</td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.alterarEmpresa" onclick="javascript:checkCorrespondente(this, 'loginHelper.operadorVO.menuGerencialVO.consultarEmpresa')"/></td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.consultarEmpresa" onclick="javascript:uncheckCorrespondente('loginHelper.operadorVO.menuGerencialVO.alterarEmpresa')" /></td>
              </tr>              
              <tr class="linhaGrid"> 
                <td width="11" class="fontLogin">&nbsp;</td>
                <td width="283" class="fontLogin">Produto</td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.alterarProduto" onclick="javascript:checkCorrespondente(this, 'loginHelper.operadorVO.menuGerencialVO.consultarProduto')"/></td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.consultarProduto" onclick="javascript:uncheckCorrespondente('loginHelper.operadorVO.menuGerencialVO.alterarProduto')" /></td>
              </tr>
              <tr class="linhaGrid1"> 
                <td width="11" class="fontLogin">&nbsp;</td>
                <td width="283" class="fontLogin">Pre&ccedil;o</td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.alterarPreco" onclick="javascript:checkCorrespondente(this, 'loginHelper.operadorVO.menuGerencialVO.consultarPreco')"/></td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.consultarPreco" onclick="javascript:uncheckCorrespondente('loginHelper.operadorVO.menuGerencialVO.alterarPreco')" /></td>
              </tr> 
            </table>
            <table width="470" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
              <tr class="subTextoAviso"> 
                <td colspan="2" class="fontLogin">MENU CONSULTAR</td>
                <td width="120" class="fontVerdanaBold">Permitir</td>
              </tr>
              <tr class="linhaGrid1">
                <td colspan="2" class="fontLogin">Certificado</td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.consultarCertificado"/></td>
              </tr>
              <tr class="linhaGrid1">
                <td colspan="2" class="fontLogin">Tabela de Pre&ccedil;o</td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.consultarTabelaPreco"/></td>
              </tr>
              <tr class="linhaGrid1">
                <td colspan="2" class="fontLogin">Faturamento</td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuGerencialVO.consultarFaturamento"/></td>
              </tr>
              <tr> 
	      		<td colspan="2" class="fontLogin">&nbsp;</td>
	      		<td align="center">&nbsp;</td>
              </tr>
            </table>
            <table width="470" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
              <tr class="titGrid"> 
                <td colspan="4">OPERACIONAL </td>
              </tr>
              <tr class="subTextoAviso"> 
                <td colspan="2" class="fontLogin">MENU</td>
                <td width="90" class="fontVerdanaBold">Alterar</td>
                <td width="90" class="fontVerdanaBold">Consultar</td>
              </tr>
              <tr class="linhaGrid1"> 
                <td width="11" class="fontLogin">&nbsp;</td>
                <td width="283" class="fontLogin">Operador</td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuOperacionalVO.alterarOperador" onclick="javascript:checkCorrespondente(this, 'loginHelper.operadorVO.menuOperacionalVO.consultarOperador')"/></td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuOperacionalVO.consultarOperador" onclick="javascript:uncheckCorrespondente('loginHelper.operadorVO.menuOperacionalVO.alterarOperador')" /></td>
              </tr>
              <tr class="linhaGrid1"> 
                <td width="11" class="fontLogin">&nbsp;</td>
                <td width="283" class="fontLogin">Certificado</td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuOperacionalVO.alterarCertificado" onclick="javascript:checkCorrespondente(this, 'loginHelper.operadorVO.menuOperacionalVO.consultarCertificado')"/></td>
                <td align="center"><html:checkbox property="loginHelper.operadorVO.menuOperacionalVO.consultarCertificado" onclick="javascript:uncheckCorrespondente('loginHelper.operadorVO.menuOperacionalVO.alterarCertificado')" /></td>
              </tr>              
              <tr> 
	      		<td colspan="2" class="fontLogin">&nbsp;</td>
	      		<td align="center">&nbsp;</td>
              </tr>
            </table>
            <table width="470" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
              <tr> 
                <td colspan="4">
                  <table border="0" align="right" cellpadding="2" cellspacing="2">
                    <tr>
                      <%
		       			if (operador.getMenuOperacionalVO().isAlterarOperador()) {
                  	  %>
                      <td><a href="javascript:sucessoDefinirRegrasAcesso();"><img src="_img/_bts/salvar.gif" width="55" height="18" border="0"></a></td>
                      <td><img src="_img/_bts/limpar.gif" width="54" height="18"></td>
                      <td><img src="_img/_bts/voltar.gif" width="55" height="18"></td>
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
          <td width="11" valign="top"><span class="bgLeft"><img name="tla_r3_c1" src="_img/_recortes/tla_r3_c1.gif" width="10" height="13" border="0" alt=""></span></td>
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