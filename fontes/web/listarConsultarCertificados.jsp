<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<%@ page import ="br.certdigital.shared.web.*" contentType="text/html;charset=ISO-8859-1" %>
<%@ page import ="br.certdigital.shared.util.*" %>

<% br.certdigital.vo.OperadorVO operador = (br.certdigital.vo.OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO); %>

<html>
<head>
<title>Certdigital</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link href="_css/cdlrio.css" rel="stylesheet" type="text/css">
<link href="_css/cdlrioStilo.css" rel="stylesheet" type="text/css">
<script src="_js/script_menu.js"></script>
<script src="SCRIPT/controle_teclas.js"></script>
<script src="_js/cdlScript.js"></script>
<jsp:include page="APP_TEMPLATES/waitLayer.jsp" /></head>

<script>function imprimir(mes, ano) {window.open("imprimirConsultarCertificado.do?metodo=imprimirConsultarCertificado&mes="+mes+"&ano="+ano);}</script>

<body onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >
<table width="991" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="987" height="133" valign="top"><table width="987" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="963"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="78%" class="titTab">CONSULTAR CERTIFICADO</td>
            <td width="15%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
            <td width="7%" class="bgTab">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
      </tr>
      <tr>
        <td width="10" height="257" class="bgLeft">&nbsp;</td>
        <td width="963" height="332" valign="top"><table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
          <tr><td colspan="11" align="right">
          <button onclick="imprimir(<bean:write name='certdigitalForm'  property='consultarHelper.mes' />, <bean:write name='certdigitalForm'  property='consultarHelper.ano' />)">Preparar Impress&atilde;o</button>
          </td></tr>
          <tr><td colspan="11">&nbsp;</td></tr>          
          <tr class="titGrid">
                <td colspan="11">RELA&Ccedil;&Atilde;O DE CERTIFICAC&Atilde;O DIGITAL A VENCER</td>
          </tr>
          <tr>
            <!--<td width="10" height="266">&nbsp;</td>-->
            <td width="960" height="266" valign="top"> 
            <table width="100%" border="0" cellspacing="1" cellpadding="1">
              <tr class="subTextoAviso">
                <td colspan="1" align="left">Documento</td>
                <td colspan="1" align="left">Cliente</td>
                <td colspan="1" align="left">Associado</td>                
                <td colspan="1" align="center">Tipo</td>
                <td colspan="1" align="left">Produto</td>
                <td colspan="1" align="center">Validade</td>
                <td colspan="1" align="right">Pre&ccedil;o Sugerido</td>
                <td colspan="1" align="left">Respons&aacute;vel</td>
                <td colspan="1" align="left">Telefone</td>
                <td colspan="1" align="left">Email</td>
				<td colspan="1" align="center">Data Emiss&atilde;o</td>                
          	  </tr>
  		  	  <% 
    		  int i=0;
    		  String classCSS = "linhaGrid1";
			  if (operador.getMenuGerencialVO().isConsultarCertificado()) {
  		  	  %>
  		  	  <logic:present name="certdigitalForm" property="consultarHelper.listaItemConsultaVO">
      			<logic:iterate name="certdigitalForm" property="consultarHelper.listaItemConsultaVO" id="lista">
              		<tr align="left" class=<%=classCSS%> >
                		<logic:equal name="lista" property="tipo_pessoa" value="F">
                			<td colspan="1" align="left">CPF:<bean:write name="lista" property="documentoAsString" /></td>
                		</logic:equal>
                		<logic:equal name="lista" property="tipo_pessoa" value="J">
                			<td colspan="1" align="left">CNPJ:<bean:write name="lista" property="documentoAsString" /></td>
                		</logic:equal>                
                		<td colspan="1" align="left"><bean:write name="lista"  property="razaoRequerente" /></td>
                		<logic:equal name="lista" property="associado" value="S">
                			<td colspan="1" align="center">Sim</td>
                		</logic:equal>
                		<logic:equal name="lista" property="associado" value="N">
                			<td colspan="1" align="center">Não</td>
                		</logic:equal>                
                		<td colspan="1" align="center"><bean:write name="lista"  property="tipoProduto" /></td>
                		<td colspan="1" align="left"><bean:write name="lista"  property="nomeProduto" /></td> 
                		<logic:equal name="lista" property="validadeProduto" value="1">               		
                			<td colspan="1" align="center"><bean:write name="lista" property="validadeProduto" />&nbsp;Ano</td>
                		</logic:equal>
                		<logic:greaterThan name="lista" property="validadeProduto" value="1">               		
                			<td colspan="1" align="center"><bean:write name="lista" property="validadeProduto" />&nbsp;Anos</td>
                		</logic:greaterThan>
                		<td colspan="1" align="right">R$&nbsp;<bean:write name="lista"  property="custoSugeridoAsString" /></td>
                		<td colspan="1" align="left"><bean:write name="lista"  property="nomeResponsavel" /></td>
                		<td colspan="1" align="left">(<bean:write name="lista" property="dddRequerente" />)&nbsp;
                		                              <bean:write name="lista" property="telRequerente" /></td>                		
                		<td colspan="1" align="left"><bean:write name="lista"  property="emailResponsavel" /></td>
                		<td colspan="1" align="center"><bean:write name="lista"  property="dataEmissaoAsString" /></td>                		
              		</tr>
          	  		<%
          			classCSS=(i%2==0)?"linhaGrid":"linhaGrid1";
          			i++;
          	  		%>    
      	  		</logic:iterate>
  			  </logic:present>
  		    <%}%>
        	<tr align="left" class=<%=classCSS%> >
          		<td colspan="11">&nbsp;</td>
        	</tr>
        	<tr><td colspan="11">&nbsp;</td></tr>
        	<tr><td colspan="11">&nbsp;</td></tr>        	
            <tr> 
               <td colspan="11" align="right"><a href="javascript:window.history.back();"><img src="_img/_bts/voltar.gif" width="55" height="18" border="0"></a></td>
            </tr>
        </table>
        </td>
        </tr>
        </table></td>
        <td width="14" class="bgRight">&nbsp;</td>
      </tr>
      <tr>
        <td width="10" valign="top"><span class="bgLeft"><img name="tla_r3_c1" src="_img/_recortes/tla_r3_c1.gif" width="10" height="13" border="0" alt=""></span></td>
        <td class="bgTabRoda">&nbsp;</td>
        <td width="14" valign="top"><span class="bgRight"><img name="tla_r3_c5" src="_img/_recortes/tla_r3_c5.gif" width="13" height="13" border="0" alt=""></span></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
