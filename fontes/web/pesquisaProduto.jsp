<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<%@ page import ="br.certdigital.shared.util.*" contentType="text/html;charset=ISO-8859-1" %>

<% br.certdigital.vo.OperadorVO operador = (br.certdigital.vo.OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO); %>

<html>
<head>
<title>Certdigital</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

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
	<jsp:include page="SCRIPT/js_pesquisas.jsp" />
	<jsp:include page="SCRIPT/js_incluirProduto.jsp" />
</script>
<jsp:include page="APP_TEMPLATES/waitLayer.jsp" /> 
</head>

<body onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >

<html:form action="pesquisaProduto.do" >
  <input type="hidden" name="metodo" value="pesquisar">

<table width="591" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="587" height="133" valign="top"><table width="587" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="563"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
                <td width="78%" class="titTab">PRODUTO</td>
            <td width="15%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
            <td width="7%" class="bgTab">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
      </tr>
      <tr>
        <td width="10" height="257" class="bgLeft">&nbsp;</td>
        <td width="560" height="332" valign="top"><table width="560" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
          <tr class="titGrid">
                <td colspan="2">PESQUISAR PRODUTO</td>
          </tr>
          <tr>
            <td colspan="2" class="fontLogin"><table width="543" border="0" cellspacing="0" cellpadding="0">
                <tr>
                      <td width="76" class="fontLogin">&nbsp;</td>
                      <td width="452" class="fontVerdana">&nbsp;</td>
                </tr>
                <tr class="linhaGrid1">
                      <td class="fontLogin">Empresa :</td>
                  <td class="fontVerdana"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="14%">
                      <html:text property="produtoHelper.produtoVO.idEmpresa" styleClass="textsField" size="10" onkeyup="this.value = this.value.toUpperCase()" readonly="false" styleId="N|S|10|Codigo Empresa|N" />
                      </td>
                      <td width="2%"> - </td>
                      <td width="34%">                      
                      <html:text property="produtoHelper.produtoVO.nomeEmpresa" styleClass="textsField" size="10" onkeyup="this.value = this.value.toUpperCase()" readonly="false" style="width:150px" readonly="true" />
                      </td>
                      <td width="50%" valign="middle">
                        <%
		       		      if (operador.getMenuGerencialVO().isConsultarProduto()) {
                	  	%>
                      	<a href="javascript:pesquisar('pesquisarEmpresas','produtoHelper.produtoVO.idEmpresa','produtoHelper.produtoVO.nomeEmpresa','EMPRESA')" >
                      		<img src="_img/_bts/lupa.gif" width="18" height="16" border="0">
                      	</a>
                      	<%
			   		      }
  			 		  	%>
                      </td>
                    </tr>
                  </table></td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td width="75" height="248">&nbsp;</td>
            <td width="474" height="248" valign="top">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2"><table border="0" align="right" cellpadding="2" cellspacing="2">
                  <tr>
                     <%
		       		   if (operador.getMenuGerencialVO().isConsultarEmpresa()) {
                	 %>
                      <td><a href="javascript:executarPesquisa();"><img src="_img/_bts/pesquisar.gif" width="74" height="18" border="0"></a></td>
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
        <td width="10" valign="top"><span class="bgLeft"><img name="tla_r3_c1" src="_img/_recortes/tla_r3_c1.gif" width="10" height="13" border="0" alt=""></span></td>
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

