<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>

<form name="frmBuffer">
<textarea name="errorBuffer" style="visibility:hidden"> 
<%-- verifica se ocorreu algum erro no Action  --%>
<logic:messagesPresent>
 <html:messages id="error"> 
  <bean:write name="error" filter="true" />
</html:messages>
</logic:messagesPresent>  

<logic:messagesPresent message="true">
 <html:messages id="message" message="true"> 
  <bean:write name="message" filter="true" />
</html:messages>
</logic:messagesPresent>  
</textarea>
</form>

