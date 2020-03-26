<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>

// Verifica por mensagens emitidas pelo Action
<logic:messagesPresent message="true">
<html:messages id="message" message="true">
  var buf= document.frmBuffer.errorBuffer.value;

  // substitui os '\n' da mensagem por ' '
  var bufNew = buf.replace(/\n/ig," ");
  bufNew = trim(bufNew);
  alert(bufNew);
</html:messages>
</logic:messagesPresent>

<logic:messagesPresent>
 <html:messages id="error">
  var buf= document.frmBuffer.errorBuffer.value;
  // substitui os '\n' da mensagem por ' '
  var bufNew = buf.replace(/\n/ig," ");
  bufNew = trim(bufNew);
  alert(bufNew);
</html:messages>
</logic:messagesPresent>

