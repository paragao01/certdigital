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
<script src="_js/cdlScript.js"></script>
<script src="SCRIPT/util.js"></script>
<script src="SCRIPT/funcoes.js"></script>
<script src="SCRIPT/general.js"></script>
<script src="SCRIPT/generic.jsp"></script>
<script src="SCRIPT/controle_teclas.js"></script>
<script>
	<jsp:include page="SCRIPT/js_incluirCertificado.jsp" /> 
</script>
<jsp:include page="APP_TEMPLATES/waitLayer.jsp" /> 
</head>

<body onLoad="init();" onMouseDown="return bloquearBotaoDireitoMouse(event)" onKeyDown="return bloquearTeclas(event)" >

<html:form action="manutencaoCertificado.do" >
  <html:hidden property="metodo" />
  <html:hidden property="certificadoHelper.certificadoVO.idEntidade" />    
  <html:hidden property="certificadoHelper.certificadoVO.idEmpresa" />  
  <html:hidden property="certificadoHelper.certificadoVO.idCertificado" />  
  
<table width="591" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td width="587" height="133" valign="top"><table width="587" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10" class="titTab"><img name="tla_r1_c1" src="_img/_recortes/tla_r1_c1.gif" width="10" height="23" border="0" alt=""></td>
        <td width="563"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="78%" class="titTab">CERTIFICADO MANUTEN&Ccedil;&Atilde;O</td>
            <td width="15%" class="bgTab"><span class="bgTab"><img name="tla_r1_c3" src="_img/_recortes/tla_r1_c3.gif" width="62" height="23" border="0" alt=""></span></td>
            <td width="7%" class="bgTab">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img name="tla_r1_c5" src="_img/_recortes/tla_r1_c5.gif" width="13" height="23" border="0" alt=""></td>
      </tr>
      <tr>
        <td width="10" height="257" class="bgLeft">&nbsp;</td>
          <td width="560" height="332" valign="top"><table width="560" border="0" align="center" cellpadding="1" cellspacing="1" class="borda">
              <tr> 
                <td colspan="4" class="titGrid">Dados do Requerente</td>
              </tr>
              <tr> 
                <td colspan="4" class="linhaGrid1">&nbsp;</td>
              </tr>              
              <tr class="linhaGrid1"> 
                <td colspan="2" class="fontLogin">Raz&atilde;o Social:<br>
                <html:text property="certificadoHelper.certificadoVO.razaoRequerente" styleId="S|S|50|Razão Social|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
                <td colspan="1" class="fontLogin">Tipo Pessoa: <br>
                <html:select styleClass="lists" property="certificadoHelper.certificadoVO.tipo_pessoa" styleId="S|S||Tipo Pessoa|N" >
					<option value="">...</option>                
                  	<html:option value="<%=GlobalConstants.VALOR_FISICA%>"><%=GlobalConstants.LABEL_FISICA%></html:option>
                  	<html:option value="<%=GlobalConstants.VALOR_JURIDICA%>"><%=GlobalConstants.LABEL_JURIDICA%></html:option>                  	
       			</html:select>
                </td>
                <td colspan="1" class="fontLogin">Documento: <br>
                   	<html:text property="certificadoHelper.certificadoVO.documentoAsString" styleClass="textsField" style="width:150px" maxlength="18" onkeypress="return mascara(this, (document.forms[0].elements['certificadoHelper.certificadoVO.tipo_pessoa'].value=='F'?cpf:cnpj));" />                
                </td>                
              </tr>
              <tr> 
                <td colspan="4" class="linhaGrid1">&nbsp;</td>
              </tr>                            
              <tr class="linhaGrid1"> 
                <td colspan="2" class="fontLogin">Nome Fantasia:<br>
                <html:text property="certificadoHelper.certificadoVO.fantasiaRequerente" styleId="S|N|50|Nome Fantasia|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
                <td colspan="1" class="fontLogin">Protocolo:<br>
                <html:text property="certificadoHelper.certificadoVO.protocolo" styleId="N|S|10|Protocolo|N" styleClass="textsField" size="10" maxlength="10" onblur="this.value = this.value.toUpperCase()"/>
                </td>
                <td colspan="1" class="fontLogin">Cadastro Espec&iacute;fico do INSS - CEI:<br>
                <html:text property="certificadoHelper.certificadoVO.inssRequerente" styleId="N|N|14|INSS - CEI|N" styleClass="textsField" size="16" maxlength="14" onblur="this.value = this.value.toUpperCase()"/>
                </td>                
              </tr>
              <tr> 
                <td colspan="4" class="linhaGrid1">&nbsp;</td>
              </tr>                            
              <tr class="linhaGrid1"> 
                <td colspan="1" class="fontLogin">UF:<br>
	                <html:select property="certificadoHelper.certificadoVO.estadoRequerente" styleClass="lists" style="width:150px" styleId="S|N||UF Requerente|N">
	                	<option value="">...</option>
	                	<html:optionsCollection property="estadoHelper.listEstadoVO" value="sigla" label="descricao" />
	                </html:select>
                </td>
                <td colspan="1" class="fontLogin">Cidade:<br>
                	<html:text property="certificadoHelper.certificadoVO.cidadeRequerente" styleId="S|N|30|Cidade Requerente|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
                <td colspan="1" class="fontLogin">DDD:<br>
                	<html:text property="certificadoHelper.certificadoVO.dddRequerente" styleId="N|S|3|DDD Requerente|N" styleClass="textsField" size="5" maxlength="3" onblur="this.value = this.value.toUpperCase()"/>
                </td>
                <td colspan="1" class="fontLogin">Telefone:<br>
                	<html:text property="certificadoHelper.certificadoVO.telRequerente" styleId="N|S|9|Telefone Requerente|N" styleClass="textsField" size="11" maxlength="9" onblur="this.value = this.value.toUpperCase()"/>
                </td>                
              </tr>
              <tr> 
                <td colspan="4" class="linhaGrid1">&nbsp;</td>
              </tr>              
              <tr> 
                <td colspan="4" class="titGrid">Dados do Respons&aacute;vel Pelo uso e Representantes Legal</td>
              </tr>
              <tr> 
                <td colspan="4" class="linhaGrid">&nbsp;</td>
              </tr> 
              <tr class="linhaGrid"> 
                <td colspan="2" class="fontLogin">Nome Completo:<br>
                <html:text property="certificadoHelper.certificadoVO.nomeResponsavel" styleId="S|S|50|Nome Completo|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
                <td colspan="2" class="fontLogin">CPF: <br>
                   	<html:text property="certificadoHelper.certificadoVO.cpfResponsavelAsString" styleId="P|N|14|CPF|N" styleClass="textsField" style="width:150px" maxlength="14" onkeypress="return mascara(this, cpf);" />                
                </td>
              </tr>
              <tr> 
                <td colspan="4" class="linhaGrid">&nbsp;</td>
              </tr>                            
              <tr class="linhaGrid"> 
                <td colspan="2" class="fontLogin">Email:<br>
                <html:text property="certificadoHelper.certificadoVO.emailResponsavel" styleId="E|S|50|EMail|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.tolowerCase()"/>
                </td>
                <td colspan="2" class="fontLogin">Data Nascimento: <br>
                <html:text property="certificadoHelper.certificadoVO.dataNascResponsavelAsString" styleId="D|N|10|Data Nascimento|N" styleClass="textsField" style="width:70px" maxlength="10" onkeyup="mascaraDiaMesAno(this);" />                
                </td>
              </tr>
              <tr> 
                <td colspan="4" class="linhaGrid">&nbsp;</td>
              </tr>                            
              <tr class="linhaGrid"> 
                <td colspan="2" class="fontLogin">Logradouro:<br>
                <html:text property="certificadoHelper.certificadoVO.logradouroResponsavel" styleId="S|N|150|Logradouro|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>                
                </td>
                <td colspan="1" class="fontLogin">N&uacute;mero:<br>
                	<html:text property="certificadoHelper.certificadoVO.numeroResponsavel" styleId="S|N|6|Numero|N" styleClass="textsField" size="5" maxlength="6" onblur="this.value = this.value.toUpperCase()"/>
                </td>
                <td colspan="1" class="fontLogin">Complemento:<br>
                <html:text property="certificadoHelper.certificadoVO.complementoResponsavel" styleId="S|N|50|Nome Completo|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>                
              </tr>
              <tr> 
                <td colspan="4" class="linhaGrid">&nbsp;</td>
              </tr>                            
              <tr class="linhaGrid"> 
                <td colspan="1" class="fontLogin">Bairro:<br>
                <html:text property="certificadoHelper.certificadoVO.bairroResponsavel" styleId="S|N|150|Bairro do Responsavel|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
                <td colspan="1" class="fontLogin">CEP:<br>
                  <html:text property="certificadoHelper.certificadoVO.cepResponsavelAsString" styleId="N|N|8|CEP|N" styleClass="textsField" size="8" maxlength="8" onblur="this.value = this.value.toUpperCase()"/>
                </td> 
                <td colspan="1" class="fontLogin">UF:<br>
	                <html:select property="certificadoHelper.certificadoVO.estadoResponsavel" styleClass="lists" style="width:150px" styleId="S|N||UF Requerente|N">
	                	<option value="">...</option>
	                	<html:optionsCollection property="estadoHelper.listEstadoVO" value="sigla" label="descricao" />
	                </html:select>
                </td>
                <td colspan="1" class="fontLogin">Munic&iacute;pio:<br>
                	<html:text property="certificadoHelper.certificadoVO.cidadeResponsavell" styleId="S|N|30|Cidade Requerente|N" styleClass="textsField" style="width:200px" maxlength="50" onblur="this.value = this.value.toUpperCase()"/>
                </td>
              </tr>
              <tr> 
                <td colspan="4" class="linhaGrid">&nbsp;</td>
              </tr>
              <tr class="linhaGrid"> 
                <td colspan="2" class="fontLogin">Produto<br>
	                <html:select property="certificadoHelper.certificadoVO.idProduto" styleClass="lists" style="width:150px" styleId="S|S||Produto|N">
	                	<option value="">...</option>
	                	<html:optionsCollection property="produtoHelper.listaProdutoVO" value="idProduto" label="nomeProduto" />
	                </html:select>
                </td>
                <td colspan="1" class="fontLogin">Data Emiss&atilde;o: <br>
                <html:text property="certificadoHelper.certificadoVO.dataEmissaoAsString" styleId="D|S|10|Data Emissao|N" styleClass="textsField" style="width:70px" maxlength="10" onkeyup="mascaraDiaMesAno(this);" />                
                </td>  
                 <td colspan="1" class="fontLogin">Associado: <br>
                <html:select styleClass="lists" property="certificadoHelper.certificadoVO.associado" styleId="S|S||Associado|N" >
					<option value="">...</option>                
                  	<html:option value="<%=GlobalConstants.VALOR_FLAG_NAO%>"><%=GlobalConstants.LABEL_FLAG_NAO%></html:option>>
                  	<html:option value="<%=GlobalConstants.VALOR_FLAG_SIM%>"><%=GlobalConstants.LABEL_FLAG_SIM%></html:option>                  	
       			</html:select>
                </td>                                            
              </tr>
              <tr> 
                <td height="10" class="fontLogin">&nbsp;</td>
                <td height="10" colspan="3">&nbsp;</td>
              </tr>
              <tr> 
                <td height="28" colspan="4" align="right"> <table border="0" align="right" cellpadding="2" cellspacing="2">
				<%if (operador.getMenuOperacionalVO().isAlterarCertificado() || operador.getIdTipoOperador().longValue()==2){%>
	               <tr> 
                      <td><a href="javascript:alterar();"><img src="_img/_bts/salvar.gif" width="55" height="18" border="0"></a></td>
                      <td><a href="javascript:remover();"><img src="_img/_bts/excluir.gif" width="57" height="18" border="0"></a></td>
                      <td><a href="javascript:limpar();"><img src="_img/_bts/limpar.gif" width="54" height="18" border="0"></a></td>
                      <td><a href="javascript:voltar();"><img src="_img/_bts/voltar.gif" width="55" height="18" border="0"></a></td>
                    </tr>
                <%}else{%>
	               <tr> 
                      <td><a href="javascript:voltar();"><img src="_img/_bts/voltar.gif" width="55" height="18" border="0"></a></td>
                    </tr>
				<%}%>
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
