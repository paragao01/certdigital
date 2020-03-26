<%@ page import="br.garantia.vo.OperadorVO" %>
<%@ page import="br.garantia.shared.util.GlobalConstants" %>

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
<table width="775" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="775" class="barra"><table width="775" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="49%" rowspan="2" valign="top"><img src="_img/logo1.jpg" width="265" height="75"></td>
          <td width="43%" height="8" valign="bottom" class="fontVerdana">
   
           <script language="JavaScript">
		//<!--
		var data = new Date();
		dia_semana   = data.getDay();
		dia      = data.getDate();
		mes      = data.getMonth();
		ano      = data.getFullYear();
		dias = new Array ('Domingo','Segunda-feira','Ter&ccedil;a-feira','Quarta-feira','Quinta-feira','Sexta-feira','S&aacute;bado');
		meses = new Array ('Janeiro','Fevereiro','Mar&ccedil;o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro');
		document.write(dias[dia_semana],', ',dia,' de ',meses[mes], ' de ', ano);
		//-->
					</script>
          </td>
          <td width="8%" valign="bottom" class="fontVerdana"><a href="login.do?metodo=logout" target="_parent"><img src="_img/_bts/sair.gif" width="39" height="18" border="0"></a></td>
        </tr>
        <tr>
          <td width="40%" class="fontLogin">Usuario: <%=operador.getNomeOperador()%> </td>
          <td valign="bottom" class="fontVerdana">&nbsp;</td>
          <td valign="bottom" class="fontVerdana">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
