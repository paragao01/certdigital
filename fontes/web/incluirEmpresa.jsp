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
<script src="_js/cdlScript.js"></script></head>
<script src="SCRIPT/util.js"></script>
<script src="SCRIPT/funcoes.js"></script>
<script src="SCRIPT/general.js"></script>
<script src="SCRIPT/generic.jsp"></script>
<script src="SCRIPT/controle_teclas.js"></script>
<script>
	<jsp:include page="SCRIPT/js_incluirEmpresa.jsp" /> 
</script>
<jsp:include page="APP_TEMPLATES/waitLayer.jsp" /> 
</head>

<body onLoad="init();" onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >

<html:form action="incluirEmpresa.do" >
  <html:hidden property="metodo" />
  
<table width="591" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="587" height="133" valign="top"><table width="587" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="563"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="78%" class="titTab">EMPRESA INCLUS&Atilde;O</td>
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
              <tr class="linhaGrid"> 
                <td width="140" class="fontLogin">CNPJ: </td>
                <td width="411" colspan="3">
                   	<html:text property="empresaHelper.empresaVO.numCNPJAsString" styleId="J|S|18|CNPJ|N" styleClass="textsField" style="width:150px" maxlength="18" onkeypress="return mascara(this, cnpj);" />                
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td width="140" class="fontLogin">Raz&atilde;o Social:</td>
                <td width="411" colspan="3">
                <html:text property="empresaHelper.empresaVO.razaoSocial" styleId="S|S|50|Razão Social|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Nome Comercial:</td>
                <td colspan="3">
                <html:text property="empresaHelper.empresaVO.nomeComercial" styleId="S|S|50|Nome Comercial|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Inscri&ccedil;&atilde;o Estadual:</td>
                <td colspan="3">
                <html:text property="empresaHelper.empresaVO.inscricaoEstadual" styleId="N|N|14|Inscrição Estadual|N" styleClass="textsField" size="16" maxlength="14" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td class="fontLogin">Nome do Contato:</td>
                <td colspan="3">
                <html:text property="empresaHelper.empresaVO.nomeContato" styleId="S|N|50|Nome do Contato|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Email:</td>
                <td colspan="3"> 
                <html:text property="empresaHelper.empresaVO.mail" styleId="E|N|50|EMail|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Telefone:</td>
                <td colspan="3">
                	<html:text property="empresaHelper.empresaVO.dddFone" styleId="N|S|3|DDD Telefone|N" styleClass="textsField" size="5" maxlength="3" onblur="this.value = this.value.toUpperCase()"/>
                  	- 
                	<html:text property="empresaHelper.empresaVO.numFone" styleId="N|S|9|Telefone|N" styleClass="textsField" size="11" maxlength="9" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Fax:</td>
                <td colspan="3">
                	<html:text property="empresaHelper.empresaVO.dddFax" styleId="N|S|3|DDD Fax|N" styleClass="textsField" size="5" maxlength="3" onblur="this.value = this.value.toUpperCase()"/>
                  	- 
                	<html:text property="empresaHelper.empresaVO.numFax" styleId="N|S|9|Fax|N" styleClass="textsField" size="11" maxlength="9" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid"> 
                <td class="fontLogin">Celular:</td>
                <td colspan="3">
                	<html:text property="empresaHelper.empresaVO.dddCelular" styleId="N|N|3|DDD Celular|N" styleClass="textsField" size="5" maxlength="3" onblur="this.value = this.value.toUpperCase()"/>
                  	- 
                	<html:text property="empresaHelper.empresaVO.numCelular" styleId="N|N|9|Celular|N" styleClass="textsField" size="11" maxlength="9" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>              
               <tr> 
                <td height="16" colspan="4" class="titGrid">ENDERE&Ccedil;O COMERCIAL</td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="22" class="fontLogin">Endere&ccedil;o:</td>
                <td colspan="3">
                <html:text property="empresaHelper.empresaVO.enderecoComercial" styleId="S|S|150|Endereço Comercial|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="22" class="fontLogin">Bairro:</td>
                <td colspan="3">
                <html:text property="empresaHelper.empresaVO.bairroComercial" styleId="S|S|150|Bairro do Endereço Comercial|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="24" class="fontLogin">Cidade:</td>
                <td colspan="3">
                <html:text property="empresaHelper.empresaVO.cidadeComercial" styleId="S|S|150|Cidade do Endereço Comercial|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="22" class="fontLogin">Estado:</td>
                <td colspan="3">
	                <html:select property="empresaHelper.empresaVO.estadoComercial" styleClass="lists" style="width:100px" styleId="S|S||Estado do Endereço Comercial|N">
	                	<option value="">...</option>
	                	<html:optionsCollection property="estadoHelper.listEstadoVO" value="sigla" label="descricao" />
	                </html:select>
                  <span class="fontLogin">Cep:</span>
                  <html:text property="empresaHelper.empresaVO.cepComercialAsString" styleId="N|S|8|CEP do Endereço Comercial|N" styleClass="textsField" size="8" maxlength="8" onblur="this.value = this.value.toUpperCase()"/>
                  </td>
              </tr>
              <tr> 
                <td colspan="4" class="titGrid">ENDERE&Ccedil;O COBRAN&Ccedil;A</td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="22" class="fontLogin">Endere&ccedil;o:</td>
                <td colspan="3">
                <html:text property="empresaHelper.empresaVO.enderecoCobranca" styleId="S|N|150|Endereço de Cobrança|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="22" class="fontLogin">Bairro:</td>
                <td colspan="3">
                <html:text property="empresaHelper.empresaVO.bairroCobranca" styleId="S|N|150|Bairro do Endereço de Cobrança|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="24" class="fontLogin">Cidade:</td>
                <td colspan="3">
                <html:text property="empresaHelper.empresaVO.cidadeCobranca" styleId="S|N|150|Cidade do Endereço de Cobrança|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr class="linhaGrid1"> 
                <td height="22" class="fontLogin">Estado:</td>
                <td colspan="3">
	                <html:select property="empresaHelper.empresaVO.estadoCobranca" styleClass="lists" style="width:100px" styleId="S|N||Estado do Endereço de Cobrança|N">
	                	<option value="">...</option>
	                	<html:optionsCollection property="estadoHelper.listEstadoVO" value="sigla" label="descricao" />
	                </html:select>
                  <span class="fontLogin">Cep:</span>
                  <html:text property="empresaHelper.empresaVO.cepCobrancaAsString" styleId="N|N|8|CEP do Endereço Cobrança|N" styleClass="textsField" size="8" maxlength="8" onblur="this.value = this.value.toUpperCase()"/>
              </tr>
              <tr> 
                <td height="10" class="fontLogin">&nbsp;</td>
                <td height="10" colspan="3">&nbsp;</td>
              </tr>
              <tr> 
                <td height="28" colspan="4" align="right"> <table border="0" align="right" cellpadding="2" cellspacing="2">
                    <tr>	
                      <%
		       		    if (operador.getMenuGerencialVO().isAlterarEmpresa()) {
                	  %>
                      <td><a href="javascript:inserir();"><img src="_img/_bts/salvar.gif" width="55" height="18" border="0"></a></td>
                      <td><a href="javascript:limpar();"><img src="_img/_bts/limpar.gif" width="54" height="18" border="0"></a></td>
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
