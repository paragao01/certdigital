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
<script src="SCRIPT/controle_teclas.js"></script>
<script src="_js/cdlScript.js"></script></head>

<body onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >
<table width="691" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="688" height="133" valign="top"><table width="687" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="663"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="78%" class="titTab">OPERADOR CERTIDIGITAL</td>
            <td width="15%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
            <td width="7%" class="bgTab">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
      </tr>
      <tr>
        <td width="10" height="257" class="bgLeft">&nbsp;</td>
        <td width="660" height="332" valign="top"><table width="660" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
          <tr class="titGrid">
            <td colspan="2">LISTAR OPERADOR</td>
          </tr>
          <tr>
            <td colspan="2" class="fontLogin"><table width="643" border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td width="76" class="fontLogin">&nbsp;</td>
                      <td width="652" class="fontVerdana">&nbsp;</td>
                    </tr>
                  </table></td>
          </tr>
          <tr>
            <td width="100%" height="266" valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="1">
	            <tr class="subTextoAviso"> 
	              <td width="48"><div align="left">C&Oacute;DIGO</div></td>
	              <td><p align="left">NOME</p></td>
	              <td align="left" >ENTIDADE</td>
	              <td align="left" >STATUS</td>
	              <td align="left" >EMPRESA</td>
	            </tr>

  <% 
    int i=0;
    String classCSS = "linhaGrid1";
  %>

  <%
	if (operador.getMenuOperacionalVO().isConsultarOperador()) {
  %>
  <logic:present name="certdigitalForm" property="loginHelper.listaOperadorVO">
      <logic:iterate name="certdigitalForm" property="loginHelper.listaOperadorVO" id="lista">
            <tr align="left" class=<%=classCSS%> >
                <td align="center"><a href="manutencaoOperador.do?metodo=pesquisarOperador&loginHelper.operadorVO.idOperador=<bean:write name="lista" property="idOperador" />"><bean:write name="lista" property="idOperador" /></a></td>
                <td><bean:write name="lista" property="nomeOperador" /></td>
                <td><bean:write name="lista" property="nomeEntidade" /></td>
                <td>
                	<logic:equal name="lista" property="statusOperador" value="<%=GlobalConstants.STATUS_OPERADOR_BLOQUEADO%>" >
                		<%=GlobalConstants.DESCRICAO_STATUS_OPERADOR_BLOQUEADO%>
                	</logic:equal>
                	<logic:equal name="lista" property="statusOperador" value="<%=GlobalConstants.STATUS_OPERADOR_INATIVO%>" >
                		<%=GlobalConstants.DESCRICAO_STATUS_OPERADOR_INATIVO%>
                	</logic:equal>
                	<logic:equal name="lista" property="statusOperador" value="<%=GlobalConstants.STATUS_OPERADOR_ATIVO%>" >
                		<%=GlobalConstants.DESCRICAO_STATUS_OPERADOR_ATIVO%>
                	</logic:equal>
                </td>
                <td><bean:write name="lista" property="idEmpresa" /> - <bean:write name="lista" property="nomeEmpresa" /></td>
            </tr>
        <%classCSS=(i%2==0)?"linhaGrid":"linhaGrid1";
          i++;
        %>
              
      </logic:iterate>
  </logic:present>
  <%
	}
  %> 			 	  
    <tr align="left" class=<%=classCSS%> >
        <td colspan="5">&nbsp;</td>
    </tr>
    </table></td>
    </tr>
          <tr>
            <td colspan="2"><table border="0" align="right" cellpadding="2" cellspacing="2">
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>                                                            
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
</body>
</html>
