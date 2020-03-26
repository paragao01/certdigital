<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>

<%@ page import="br.certdigital.shared.web.*"
	contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.certdigital.shared.util.*"%>

<%
	br.certdigital.vo.OperadorVO operador = (br.certdigital.vo.OperadorVO) session
			.getAttribute(GlobalConstants.OPERADOR_INFO);
%>

<html>
<head>
<title>Certdigital</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link href="_css/cdlrio.css" rel="stylesheet" type="text/css">
<link href="_css/cdlrioStilo.css" rel="stylesheet" type="text/css">
<script src="_js/script_menu.js"></script>
<script src="SCRIPT/controle_teclas.js"></script>
<script src="_js/cdlScript.js"></script>
</head>

<body onMouseDown="return bloquearBotaoDireitoMouse(event)"
	onKeyDown="return bloquearTeclas(event)">
	<table width="591" border="0" align="center" cellpadding="1" cellspacing="1">
		<tr id="noprint">
			<td colspan="11" align="right"><button onclick="window.print();">Imprimir</button></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<table width="591" border="1" align="center" cellpadding="1" cellspacing="1">
			<tr class="titGrid">
				<td colspan="3">TABELA DE PRE&Ccedil;OS CERTIFICAC&Atilde;O	DIGITAL</td>
			</tr>
			<tr class="subTextoAviso">
				<td><p align="left">TIPO DE CERTIFICADO</p></td>
				<td><p align="center">CUSTO ENTIDADE</p></td>
				<td><p align="center">CUSTO SUGERIDO</p></td>
			</tr>
			<%
				int i = 0;
				String classCSS = "linhaGrid1";
				if (operador.getMenuGerencialVO().isConsultarTabelaPreco()) {
			%>
			<logic:present name="certdigitalForm"
				property="consultarHelper.listaItemConsultaVO">
				<logic:iterate name="certdigitalForm"
					property="consultarHelper.listaItemConsultaVO" id="lista">
					<tr align="left" class=<%=classCSS%>>
						<td width="300" align="left"><bean:write name="lista"
								property="nomeProduto" /></td>
						<td width="100" align="center"><bean:write name="lista"
								property="custoEntidade" /></td>
						<td width="100" align="center"><bean:write name="lista"
								property="custoSugerido" /></td>
					</tr>
					<%
						classCSS = (i % 2 == 0) ? "linhaGrid" : "linhaGrid1";
									i++;
					%>
				</logic:iterate>
			</logic:present>
			<%
				}
			%>
		</table>
	</table>
</body>
</html>
