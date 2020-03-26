<%@ page import="br.certdigital.vo.OperadorVO" %>
<%@ page import="br.certdigital.shared.util.GlobalConstants" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Garantia de Cheque</title>
<link href="_css/cdlrio.css" rel="stylesheet" type="text/css">
<link href="_css/cdlrioStilo.css" rel="stylesheet" type="text/css">
<script language="javascript" src="_js/script_menu.js"></script>
<script language="JavaScript" src="_js/cdlScript.js"></script>
<script language="javascript" src="SCRIPT/controle_teclas.js"></script>
</head>

<%
	OperadorVO operador = (OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO);
%>

<body onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >
<table width="592" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="588" height="133" valign="top"><table width="587" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="563"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="78%" class="titTab">&nbsp;</td>
            <td width="15%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
            <td width="7%" class="bgTab">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
      </tr>
      <tr>
        <td width="10" height="257" class="bgLeft">&nbsp;</td>
        <td width="560" height="332" valign="top"><table width="560" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
          <tr>
            <td class="fontLogin"><table width="543" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td class="fontLogin">Ol&aacute;!! <%=operador.getNomeOperador()%>, seja bem-vindo!! </td>
                  </tr>
            </table></td>
          </tr>
          <tr>
            <td height="285">
              <%--object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="550" height="162">
                <param name="movie" value="abertura.swf">
                <param name="quality" value="high">
                <embed src="abertura.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="550" height="162"></embed>
              </object--%>
              <%--center><img name="lig_cheque" src="_img/logoLigChequeGrande2.jpg" width="500" height="162"/></center--%>  
            </td>
          </tr>

<%--  Comentario
          <tr>
            <td class="fontLogin" align="right">Segura Serviços © 2006</td>
            </tr>
--%>

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
</body>
</html>
