<%@ taglib uri="struts-bean.tld" prefix="bean" %>

    function fSetSelectedValue(object, formName, fieldName, comboName) {
        if (formName != null) {
            var form = document.forms[formName];
            var field = form.elements[fieldName];
            var combo = form.elements[comboName];
            if (field != null && combo != null) {
                if (object == field) {
                    combo.value = field.value;
                    if (field.value != '' && combo.selectedIndex == -1) {
                        alert('<bean:message key="componente.selecao.valor.invalido" />');
                        field.value = '';
                        combo.value = '';
                        field.focus();
                    }
                    if (field.value == '') {
                        combo.value = '';
					}                    
                } else {
                    field.value = combo.value;
                }
            }
        }
    }