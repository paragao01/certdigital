<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>**</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="CSS/abril.css" rel="stylesheet" type="text/css">
<script src="SCRIPT/controle_teclas.js"></script>
<script src="SCRIPT/util.js"></script>
<script src="SCRIPT/general.js"></script>
<script src="SCRIPT/generic.jsp"></script>
</head>

<body onLoad="document.frm.msg.value= trim(document.frm.msg.value);" onunload="javascript:refreshPesquisa();" onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
  <input type="hidden" name="imprimir">

  <table width="513" border="0" cellpadding="0" cellspacing="0" class="barraSuperior">
    <tr>
      <td height="25" class="TituloCopy">&nbsp;&nbsp;MENSAGEM</td>
    </tr>
  </table>

  <table width="506" border="0" bordercolor="#000000" class="fontTextosBgBege">
    <tr>
      <td width="500">

        <table width="500" border="0" cellpadding="1" cellspacing="1">

        <form name="frm">
          <tr>
            <td height="25" class="fontTextosBg">&nbsp;&nbsp;Detalhes</td>
          </tr>
          <tr><td class="fonteNormal">  &nbsp;</td></tr>
          <tr>
            <td class="fonteNormal" height="100" align="center" valign="middle">
            	<textarea name="msg" class="text" cols="100" row="10" readonly="true" style="HEIGHT: 80px; WIDTH: 435px" >

                <logic:messagesPresent>
                  <html:messages id="error">
                    <bean:write name="error" filter="true" />
                  </html:messages>
                </logic:messagesPresent>

                <logic:messagesPresent message="true">
                  <html:messages id="message" message="true">
                    <bean:write name="message" filter="true" />
                  </html:messages>
                </logic:messagesPresent>
            	</textarea>

            </td>
          </tr>
        </form>
          
        <tr>
          <td class="fonteNormal" align="center" valign="middle">

          <logic:messagesPresent>
            <html:messages id="error">
              <a href="javascript:history.back();">VOLTAR </a>
            </html:messages>
          </logic:messagesPresent>

          <logic:messagesPresent message="true">

            <html:messages id="message" message="true">

              <% if(request.getParameter("imprimir") != null && request.getParameter("imprimir").equals("true")){ %>
                <a href="javascript:imprimirFornecedor('<%=request.getParameter("codPessoaDetalhe")%>','<%=request.getParameter("origem")%>');">
                  <img src="imagens/btImprimir.gif" border="0" >
                </a>
              <%}%>

              <a href="javascript:window.close();">
                <img src="imagens/btFechar.gif" border="0" >
              </a>
            </html:messages>

    			</logic:messagesPresent>

        </td>
      </tr>
      <tr> <td class="fonteNormal">  &nbsp;</td> </tr>

    </table>
  </td>
  <td> &nbsp; </td>
</table>

</body>
</html>