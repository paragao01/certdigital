<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<%@ page import ="br.certdigital.shared.util.*" contentType="text/html;charset=ISO-8859-1" %>

<% br.certdigital.vo.OperadorVO operador = (br.certdigital.vo.OperadorVO) session.getAttribute(GlobalConstants.OPERADOR_INFO); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Certdigital</title>
<link href="_css/cdlrio.css" rel="stylesheet" type="text/css">
<link href="_css/cdlrioStilo.css" rel="stylesheet" type="text/css">
<script src="_js/script_menu.js"></script>
<script src="_js/cdlScript.js"></script></head>
<script src="SCRIPT/util.js"></script>
<script src="SCRIPT/funcoes.js"></script>
<script src="SCRIPT/general.js"></script>
<script src="SCRIPT/generic.jsp"></script>
<script src="SCRIPT/controle_teclas.js"></script>
<script>
<jsp:include page="SCRIPT/js_manutencaoPreco.jsp" /> 
</script>

<body onLoad="init();" onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >

<html:form action="manutencaoPreco.do" >
  <html:hidden property="metodo" />
  <html:hidden property="precoHelper.precoVO.idPreco" />  
  <html:hidden property="precoHelper.precoVO.idProduto" />  
  
<table width="591" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="587" height="133" valign="top"><table width="587" border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="563"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
                <td width="78%" class="titTab">PRE&Ccedil;O MANUTEN&Ccedil;&Atilde;O</td>
            <td width="15%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
            <td width="7%" class="bgTab">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
      </tr>
      <tr>
        <td width="10" height="257" class="bgLeft">&nbsp;</td>
          <td width="560" height="332" valign="top" class="fontLogin">
			<table width="560" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
              <tr class="titGrid"> 
                <td colspan="4">&nbsp;</td>
              </tr>
              <tr> 
                <td colspan="4" class="fontLogin"><table width="543" border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td width="76" class="fontLogin">&nbsp;</td>
                      <td width="452" class="fontVerdana">&nbsp;</td>
                    </tr>
                  </table></td>
              </tr>
			  <logic:notEqual name="certdigitalForm" property="precoHelper.precoVO.idPreco" value="" >
              	<tr class="linhaGrid"> 
                	<td width="140" class="fontLogin">C&oacute;digo</td>
                	<td width="411" colspan="3">
                	<bean:write name="certdigitalForm" property="precoHelper.precoVO.idPreco" />
                	</td>
              	</tr>			  
			  </logic:notEqual>
              <tr class="linhaGrid1"> 
                <td width="140" class="fontLogin">Produto:</td>
                <td width="411" colspan="3">
                <html:text property="precoHelper.precoVO.nomeProduto" styleId="S|S|50|Descricao" styleClass="textsField" style="width:150px" maxlength="50" onkeyup="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Data Refer&ecirc;ncia:</td>
                <td colspan="3">
                      <html:text property="precoHelper.precoVO.dataReferenciaAsString" styleId="D|S|10|Data Referencia|N" styleClass="textsField" style="width:70px" maxlength="10" onkeyup="mascaraDiaMesAno(this);" />
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Pre&ccedil;o Entidade:</td>
                <td colspan="3">
                      <html:text property="precoHelper.precoVO.precoEntidadeAsString" styleId="F|S|12|Preco Entidade|N" styleClass="textsField" style="width:70px" maxlength="12" onkeyup="javascript:fValidateLength(this);" />
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Pre&ccedil;o Sugerido:</td>
                <td colspan="3">
                <html:text property="precoHelper.precoVO.precoSugeridoAsString" styleId="F|S|12|Preco Sugerido|N" styleClass="textsField" style="width:70px" maxlength="12" onkeyup="javascript:fValidateLength(this);" />                
                </td>
              </tr>
              <tr> 
                <td height="28" colspan="4" align="right"> <table border="0" align="right" cellpadding="2" cellspacing="2">
              <tr>         
		      <% if (operador.getMenuGerencialVO().isAlterarEntidade()) {%>
                    <td><a href="javascript:atualizar();"><img src="_img/_bts/salvar.gif" width="55" height="18" border="0"></a></td>
                    <td><a href="javascript:remover();"><img src="_img/_bts/excluir.gif" width="57" height="18" border="0"></a></td>
                    <td><a href="javascript:limpar();"><img src="_img/_bts/limpar.gif" width="54" height="18" border="0" ></a></td>
              <%}%>
                    
              <td><a href="javascript:history.back(-1)"><img src="_img/_bts/voltar.gif" width="55" height="18" border="0"></a></td>
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
