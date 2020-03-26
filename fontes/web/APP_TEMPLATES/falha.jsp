<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
<head>
<script language="javascript" src="SCRIPT/controle_teclas.js"></script>
</head>
<body onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" onload="setFormFocusElement()" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
<h2> Mensagem </h2>
<br>
<logic:messagesPresent>
<UL>
<html:messages id="error">
 <bean:write name="error" filter="true" />
</html:messages>
</UL>
</logic:messagesPresent>

<%--
<logic:messagesNotPresent>
 <bean:message key="falha.sistema" />
<logic:/messagesNotPresent>
--%>

<br>

<a href="javascript:history.back();">Voltar</a>
</body>
</html>