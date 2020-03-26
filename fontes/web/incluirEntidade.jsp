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
<jsp:include page="SCRIPT/js_incluirEntidade.jsp" /> 
</script>

<body onLoad="init();" onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >

<html:form action="incluirEntidade.do" >
  <html:hidden property="metodo" />
  
<table width="591" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="587" height="133" valign="top"><table width="587" border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="563"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
                <td width="78%" class="titTab">ENTIDADE INCLIS&Atilde;O</td>
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
              <logic:notEqual name="certdigitalForm" property="entidadeHelper.entidadeVO.itemEntidadeVO.idOperador" value="" >
                <tr class="destAuto"> 
                <td class="destAuto" colspan="4">Operador Login:&nbsp;&nbsp;&nbsp;&nbsp;
			    <bean:write name="certdigitalForm" property="entidadeHelper.entidadeVO.itemEntidadeVO.idOperador" />	               
                <span class="fontLogin">&nbsp;&nbsp;&nbsp;&nbsp;Senha:&nbsp;&nbsp;&nbsp;&nbsp;
                <bean:write name="certdigitalForm" property="entidadeHelper.entidadeVO.itemEntidadeVO.idOperador" />
                </span>
                </td>
                </tr>
              </logic:notEqual>
              <tr class="linhaGrid"> 
                <td height="39" colspan="4" class="fontLogin"> <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td width="11%" class="fontLogin">CNPJ: </td>
                      <td width="27%">
                      <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.numCNPJ" styleId="J|S|20|CNPJ" styleClass="textsField" style="width:150px" maxlength="50" onkeyup="this.value = this.value.toUpperCase()"/>
                      </td>
                      <td width="0%">&nbsp;</td>
                      <td width="13%">&nbsp;</td>
                      <td width="49%" valign="middle">&nbsp;</td>
                    </tr>
                  </table></td>
              </tr>
              <tr class="linhaGrid1"> 
                <td width="140" class="fontLogin">Raz&atilde;o Social:</td>
                <td width="411" colspan="3">
                <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.razaoSocial" styleId="S|S|50|Razão Social" styleClass="textsField" style="width:150px" maxlength="50" onkeyup="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Nome Comercial:</td>
                <td colspan="3">
                      <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.nomeComercial" styleId="S|S|50|Nome Comercial" styleClass="textsField" style="width:150px" maxlength="50" onkeyup="this.value = this.value.toUpperCase()"/>
                
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Endere&ccedil;o:</td>
                <td colspan="3">
                      <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.enderecoComercial" styleId="S|S|50|Endereco Comercial" styleClass="textsField" style="width:150px" maxlength="50" onkeyup="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Bairro:</td>
                <td colspan="3">
                      <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.bairro" styleId="S|S|50|Bairro" styleClass="textsField" style="width:150px" maxlength="50" onkeyup="this.value = this.value.toUpperCase()"/>

                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Cidade:</td>
                <td colspan="3">
                      <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.cidade" styleId="S|S|50|Cidade" styleClass="textsField" style="width:150px" maxlength="50" onkeyup="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Estado:</td>
                <td colspan="3"> 
      <html:select styleClass="lists" style="HEIGHT: 18px; WIDTH: 140px"
                   property="entidadeHelper.entidadeVO.itemEntidadeVO.estado"  styleId="S|S||Estado" >
                  <option value="" selected>...</option>
                  <html:optionsCollection name="certdigitalForm"  property="entidadeHelper.entidadeVO.listaEstadoVO" 
                                          value="sigla" label="descricao"/>
      </html:select>
                  <span class="fontLogin">Cep:
                      <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.cep" styleId="I|S|8|Cep|N" styleClass="textsField" style="width:78px" maxlength="10" onkeyup="this.value = this.value.toUpperCase()"/>
                  </span></td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Contato</td>
                <td colspan="3">
                      <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.nomeContato" styleId="S|S|50|Nome Contato" styleClass="textsField" style="width:150px" maxlength="50" onkeyup="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Email</td>
                <td colspan="3">
                      <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.mail" styleId="S|N|320|E-mail" styleClass="textsField" style="width:150px" maxlength="50" onkeyup="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Percentual Associado</td>
                <td colspan="3">
                   <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.perc_associado" styleId="I|N|2|Percentual Asociado|" styleClass="textsField" style="width:20px" maxlength="2" onkeyup="this.value = this.value.toUpperCase()"/>%
                </td>
              </tr>                            
              <tr class="linhaGrid"> 
                <td class="fontLogin">Telefone</td>
                <td colspan="3">
                   <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.dddFone" styleId="I|S|2|DDD|N"  styleClass="textsField" style="width:50px" maxlength="2" onkeyup="this.value = this.value.toUpperCase()"/>
                  - 
                   <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.numFone" styleClass="textsField" styleId="S|S|15|Número do Telefone|N"  style="width:50px" maxlength="9" onkeyup="this.value = this.value.toUpperCase()"/>

                </td>
              </tr>
              <tr class="linhaGrid">
                <td class="fontLogin">Fax</td>
                <td colspan="3">
                      <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.dddFax" styleId="I|S|2|DDD|N"  styleClass="textsField" style="width:50px" maxlength="2" onkeyup="this.value = this.value.toUpperCase()"/>
                  - 
                   <html:text property="entidadeHelper.entidadeVO.itemEntidadeVO.numFax" styleClass="textsField"  styleId="S|S|15|Número do Fax|N" style="width:50px" maxlength="9" onkeyup="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr> 
                <td height="28" colspan="4" align="right">
                <%if (operador.getMenuGerencialVO().isAlterarEntidade()){ %>
                  <table border="0" align="right" cellpadding="2" cellspacing="2">
                    <tr> 
                      <td><a href="javascript:inserir();"><img src="_img/_bts/salvar.gif" width="55" height="18" border="0"></a></td>
                      <td><img src="_img/_bts/limpar.gif" width="54" height="18"></td>
                    </tr>
                  </table>
                <%}%>
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
    </table>    </td>
  </tr>
</table>

  </html:form>
  
<!-- Container das mensagens dos Actions -->
<jsp:include page="APP_TEMPLATES/msgBuffer.jsp" />

</body>
</html>
