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
<script src="_js/cdlScript.js"></script></head>

<script>function imprimir() {window.open("imprimirTabelaPreco.do?metodo=consultarTabelaPreco");}</script>

<body onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >
<table width="591" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="587" height="133" valign="top"><table width="587" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="563"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="78%" class="titTab">TABELA PRE&Ccedil;O</td>
            <td width="15%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
            <td width="7%" class="bgTab">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
      </tr>
      <tr>
        <td width="10" height="257" class="bgLeft">&nbsp;</td>
        <td width="560" height="332" valign="top"><table width="560" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
          <tr><td colspan="2" align="right"><button onclick="imprimir()">Preparar Impress&atilde;o</button></td></tr>
          <tr><td colspan="2">&nbsp;</td></tr>          
          <tr class="titGrid">
                <td colspan="2">TABELA DE PRE&Ccedil;OS CERTIFICAC&Atilde;O DIGITAL</td>
          </tr>
          <tr>
            <td width="75" height="266">&nbsp;</td>
            <td width="574" height="266" valign="top">
            <table width="570" border="0" cellspacing="1" cellpadding="1">
              <tr class="subTextoAviso">
                <td><p align="left">TIPO DE CERTIFICADO</p></td>
                <td><p align="center">CUSTO ENTIDADE</p></td>
                <td><p align="center">CUSTO SUGERIDO</p></td>
          	  </tr>
  		  	  <% 
    		  int i=0;
    		  String classCSS = "linhaGrid1";
			  if (operador.getMenuGerencialVO().isConsultarTabelaPreco()) {
  		  	  %>
  		  	  <logic:present name="certdigitalForm" property="consultarHelper.listaItemConsultaVO">
      			<logic:iterate name="certdigitalForm" property="consultarHelper.listaItemConsultaVO" id="lista">
              		<tr align="left" class=<%=classCSS%> >
                		<td width="300" align="left"><bean:write name="lista" property="nomeProduto" /></td>
                		<td width="100" align="center"><bean:write name="lista" property="custoEntidade" /></td>
                		<td width="100" align="center"><bean:write name="lista" property="custoSugerido" /></td>                
              		</tr>
          	  		<%
          			classCSS=(i%2==0)?"linhaGrid":"linhaGrid1";
          			i++;
          	  		%>    
      	  		</logic:iterate>
  			  </logic:present>
  		      <%}%>
        	<tr><td colspan="3">&nbsp;</td></tr>
        	<tr><td colspan="3">&nbsp;</td></tr>        	
            <tr> 
               <td colspan="3" align="right"><a href="javascript:window.history.back();"><img src="_img/_bts/voltar.gif" width="55" height="18" border="0"></a></td>
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
