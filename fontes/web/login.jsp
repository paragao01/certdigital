<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>

<%@ page import="br.certdigital.shared.util.*" contentType="text/html;charset=ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>CertDigital</title>
<link href="_css/cdlrio.css" rel="stylesheet" type="text/css">
<link href="_css/cdlrioStilo.css" rel="stylesheet" type="text/css">
<script src="_js/script_menu.js"></script>
<script src="_js/cdlScript.js"></script>
<script src="SCRIPT/util.js"></script>
<script src="SCRIPT/controle_teclas.js"></script>
<script><jsp:include page="SCRIPT/js_login.jsp" /></script>
</head>
<body onLoad="initLogin();"	onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)">
<jsp:include page="APP_TEMPLATES/waitLayer.jsp" />
<html:form action="/login.do" method="POST">
	<input type="hidden" name="metodo" value="verificaOperadorSenha">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="100%" class="barra">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="49%" rowspan="2" valign="middle" class="fontCertdigital">
					&nbsp;&nbsp;&nbsp;Certdigital</td>
					<td width="47%" height="61" valign="bottom" class="fontVerdana">
					<script>
					//<!--
					var data = new Date();
					dia_semana = data.getDay();
					dia      = data.getDate();
					mes      = data.getMonth();
					ano      = data.getFullYear();
					dias  = new Array ('Domingo','Segunda-feira','Ter&ccedil;a-feira','Quarta-feira','Quinta-feira','Sexta-feira','S&aacute;bado');
					meses = new Array ('Janeiro','Fevereiro','Mar&ccedil;o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro');
					document.write(dias[dia_semana],', ',dia,' de ',meses[mes], ' de ', ano);
					//-->
					</script></td>
					<td width="4%" rowspan="2" valign="bottom" class="fontVerdana"></td>
				</tr>
				<tr>
					<td valign="bottom" class="fontVerdana">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table width="770" border="0" cellspacing="1" cellpadding="1">
		<tr>
			<td width="140"></td>
			<td width="339" valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="242">
					<table width="339" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="_img/titLogin.gif" width="339" height="22"></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="439" height="19" valign="top" class="bgMeioLog">
							<table width="320" border="0" align="center" cellpadding="1"
								cellspacing="1">
								<tr class="fontLogin">
									<td>Login</td>
									<td><html:text name="certdigitalForm"
										property="loginHelper.operadorVO.idOperador"
										styleClass="textsField" style="width:200px" maxlength="10" /></td>
								</tr>
								<tr class="fontLogin">
									<td>Senha</td>
									<td><html:password redisplay="false" name="certdigitalForm"
										property="loginHelper.operadorVO.senha"
										styleClass="textsField" style="width:200px;" maxlength="10"
										onkeyup="checkKey();" /></td>
								</tr>
								<tr class="fontLogin">
									<td>&nbsp;</td>
									<td><a href="javascript:doLogin();"><img
										src="_img/_bts/confirmar.gif" width="69" height="18"
										hspace="1" border="0"></a> <a href="javascript:limpar();"><img
										src="_img/_bts/limpar.gif" width="54" height="18" hspace="3"
										border="0"></a></td>
								</tr>
								<tr class="textoAviso">
									<td height="14" colspan="2">&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="18" valign="top"><img src="_img/rodaLogin.gif"
								width="339" height="7"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<!-- Container das mensagens dos Actions -->
<jsp:include page="APP_TEMPLATES/msgBuffer.jsp" />
</body>
</html>
