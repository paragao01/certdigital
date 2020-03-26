<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<%-- Nome do form --%>
<bean:parameter id="form"             name="form" />
<%-- Altura do campo texto --%>
<bean:parameter id="fieldTextHeigth"  name="fieldTextHeigth" />
<%-- Largura do campo texto --%>
<bean:parameter id="fieldTextWidth"   name="fieldTextWidth" />
<%-- Style do campo texto --%>
<bean:parameter id="fieldTextClass"   name="fieldTextClass" />
<%-- Nome do campo - texto --%>
<bean:parameter id="fieldText"        name="fieldText" />
<%-- Altura do campo combo --%>
<bean:parameter id="fieldComboHeigth" name="fieldComboHeigth" />
<%-- Largura do campo combo --%>
<bean:parameter id="fieldComboWidth"  name="fieldComboWidth" />
<%-- Style do campo combo --%>
<bean:parameter id="fieldComboClass"  name="fieldComboClass" />
<%-- Descrição do campo - combo --%>
<bean:parameter id="fieldComboDesc"   name="fieldComboDesc" />
<%-- Nome da property que representa o valor do combo --%>
<bean:parameter id="fieldCombo"       name="fieldCombo" />
<%-- Nome da collection - itens do combo --%>
<bean:parameter id="collectionCombo"  name="collectionCombo" />
<%-- Nome da property a ser exibida nos valores do combo --%>
<bean:parameter id="valueCombo"       name="valueCombo" />
<%-- Nome da property a ser exibida nos labels do combo --%>
<bean:parameter id="labelCombo"       name="labelCombo" />
<%-- Obrigatoriedade de seleção do combo --%>
<bean:parameter id="obrigatorio"      name="obrigatorio" />
<%-- Permite seleção de valor zero no combo --%>
<bean:parameter id="permiteZero"      name="permiteZero" />
<%-- Função javascript a ser executado no metodo onChange do combo --%>
<bean:parameter id="onChangeFunction" name="onChangeFunction" />

<%
    obrigatorio       = "S|"+obrigatorio+"||"+fieldComboDesc+"|"+permiteZero;
    String textStyle  = "HEIGHT:"+fieldTextHeigth+"px; WIDTH:"+fieldTextWidth+"px";
    String comboStyle = "HEIGHT:"+fieldComboHeigth+"px; WIDTH:"+fieldComboWidth+"px";
    String function   = "javascript:fSetSelectedValue(this, '"+form+"', '"+fieldText+"', '"+fieldCombo+"')";
    if (onChangeFunction != null && !"".equals(onChangeFunction)) {
    	if(!onChangeFunction.equals("RelDiario"))
    		function += ";"+onChangeFunction;
    }
	if(!onChangeFunction.equals("RelDiario")){    
%>
    <input type="text" name="<%=fieldText%>" class="<%=fieldTextClass%>" style="<%=textStyle%>" maxlength="6" onblur="<%=function%>" value="<bean:write name="<%=form%>" property="<%=fieldCombo%>" />" >
    -
<%  } %>
    <html:select name="<%=form%>" property="<%=fieldCombo%>" styleClass="<%=fieldComboClass%>" style="<%=comboStyle%>" onchange="<%=function%>" styleId="<%=obrigatorio%>" >
	<%if(!onChangeFunction.equals("RelDiario")){%>        
        <option value="">...</option>
    <%}%>
        <html:optionsCollection property="<%=collectionCombo%>" name="<%=form%>" value="<%=valueCombo%>" label="<%=labelCombo%>" />
    </html:select>