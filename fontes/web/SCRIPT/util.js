
//-------------------------------------------------------------------
// This function accepts a string variable and verifies if it is in
// the proper format for an e-mail address or not.
// The function returns true if the format is valid, false if not.
//-------------------------------------------------------------------

function isEmail(email) {
    invalidChars = " ~\'^\`\"*+=\\|][(){}$&!#%/:,;";

    // Check for null
    if (email == "") {
        return true;
    }

    // Check for invalid characters as defined above
    for (i=0; i<invalidChars.length; i++) {
        badChar = invalidChars.charAt(i);
        if (email.indexOf(badChar,0) > -1) {
            return false;
        }
    }
    lengthOfEmail = email.length;
    if ((email.charAt(lengthOfEmail - 1) == ".") || (email.charAt(lengthOfEmail - 2) == ".")) {
        return false;
    }
    Pos = email.indexOf("@",1);
    if (email.charAt(Pos + 1) == ".") {
        return false;
    }
    while ((Pos < lengthOfEmail) && ( Pos != -1)) {
        Pos = email.indexOf(".",Pos);
        if (email.charAt(Pos + 1) == ".") {
            return false;
        }
        if (Pos != -1) {
            Pos++;
        }
    }

    // There must be at least one @ symbol
    atPos = email.indexOf("@",1);
    if (atPos == -1) {
        return false;
    }

    // But only ONE @ symbol
    if (email.indexOf("@",atPos+1) != -1) {
        return false;
    }

    // Also check for at least one period after the @ symbol
    periodPos = email.indexOf(".",atPos);
    if (periodPos == -1) {
        return false;
    }
    if (periodPos+3 > email.length) {
        return false;
    }
    return true;
}


//-------------------------------------------------------------------
// Verify if the string passed is a valid time (HH:MM:SS)
//   Returns true or false
//-------------------------------------------------------------------
function isTime(timeStr) {
  if(timeStr.length == 8) {
    for(var i=0; i<timeStr.length; i++) {
      if((i==2 || i==5) && (timeStr.charAt(i)!=":")) return false;
      if((i!=2 && i!=5) && !isDigit(timeStr.charAt(i))) return false;
    }
    var hour = timeStr.charAt(0) + timeStr.charAt(1);
    var minute = timeStr.charAt(3) + timeStr.charAt(4);
    var second = timeStr.charAt(6) + timeStr.charAt(7);
    if(((hour >=0) && (hour <= 24)) &&
       ((minute >= 0) && (minute <= 59)) &&
       ((second >= 0) && (second <= 59))) {
      return true;
    }
    else {
      return false;
    }
  }

  return false;
}


//-------------------------------------------------------------------
// Verify if the string passed is a valid date (DD/MM/YYYY)
//   Returns true or false
//-------------------------------------------------------------------
function isDate(dateStr) {
  var datePat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
  var matchArray = dateStr.match(datePat); // is the format ok?
  if (matchArray == null) {
    //alert("Entre uma data valida, no formato (dd/mm/yyyy).");
    return false;
  }
  month = matchArray[3]; // parse date into variables
  day   = matchArray[1];
  year  = matchArray[5];
  if (month < 1 || month > 12) { // check month range
    //alert("O mes deve estar entre 1 e 12.");
    return false;
  }
  if (day < 1 || day > 31) {
    //alert("O dia deve estar entre 1 e 31.");
    return false;
  }
  if ((month==4 || month==6 || month==9 || month==11) && day==31) {
    //alert("O mes "+month+" nao tem 31 dias!")
    return false;
  }
  if (month == 2) { // check for february 29th
    var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    if (day > 29 || (day==29 && !isleap)) {
      //alert("Fevereiro de " + year + " nao tem " + day + " dias!");
      return false;
    }
  }
  return true; // date is valid
}

//-------------------------------------------------------------------
// Trim functions
//   Returns string with whitespace trimmed
//-------------------------------------------------------------------
function trimL(str) {
var n=0;
var i=0;

  if (str=='') return '';

  for (i=0; i<str.length; i++) {
   n = str.charCodeAt(i);   
   if (n>32) break;   	
  }

  if (i<str.length)
  	return str.substring(i,str.length);
  else return str;
}

function trimR(str) {
var n=0;
var i=0;

  if (str=='') return '';

  for (i=str.length-1; i>=0; i--) {
   n = str.charCodeAt(i);

   if (n>32) break;   	
  }

  if (i>=0)
  	return str.substring(0,i+1);
  else return str;
}


// verifica se a data <dt> maior que a data corrente
function isDateGreaterThanNow(dt) {
var res= isDate(dt);

var today = new Date();
var dtParts = dt.split('/');
var day=0, month=0, year=0;
var dtTarget,dif=0;

day   = parseInt(dtParts[0]);
month = parseInt(dtParts[1]);
year  = parseInt(dtParts[2]);

dtTarget = new Date(year, month, day);

dif = today.getTime() - dtTarget.getTime();
return (dif<0);
}


function trim(str) { 
  var r = trimR(str);
  var l= trimL(r);
  return l;
}

//-------------------------------------------------------------------
// isNull(value)
//   Returns true if value is null
//-------------------------------------------------------------------
function isNull(val) {
  if (val == null) { return true; }
  return false;
}

//-------------------------------------------------------------------
// isBlank(value)
//   Returns true if value only contains spaces
//-------------------------------------------------------------------
function isBlank(val) {
  if (val == null) { return true; }
  for (var i=0; i < val.length; i++) {
    if ((val.charAt(i) != ' ') && (val.charAt(i) != "\t") && (val.charAt(i) != "\n")) { return false; }
  }
  return true;
}

//-------------------------------------------------------------------
// isPositiveNegativeInteger(value)
//   Returns true if value contains all digits (positive or negative)
//-------------------------------------------------------------------
function isPositiveNegativeInteger(val) {
  for (var i=0; i < val.length; i++) {
    if (val.charAt(i) == '-' && i == 0) {
    	continue;
    }
    if (!isDigit(val.charAt(i))) { return false; }
  }
  return true;
}

//-------------------------------------------------------------------
// isNegativeOrZeroInteger(value)
//   Returns true if value contains all digits (positive or negative)
//-------------------------------------------------------------------
function isNegativeOrZeroInteger(val) {
  if (isPositiveNegativeInteger(val)) {
  	if (parseInt(val, 10) > 0) {
  		return false;
  	}
  }	
  return true;
}

//-------------------------------------------------------------------
// isInteger(value)
//   Returns true if value contains all digits
//-------------------------------------------------------------------
function isInteger(val) {
  for (var i=0; i < val.length; i++) {
    if (!isDigit(val.charAt(i))) { return false; }
  }
  return true;
}

//-------------------------------------------------------------------
// isNumeric(value)
//   Returns true if value contains a positive float value
//-------------------------------------------------------------------
function isNumeric(val) {
  var dp = false;
  for (var i=0; i < val.length; i++) {
  	if (val.charAt(i) == '.') {
  		continue;
  	}
    if (!isDigit(val.charAt(i))) {
      if (val.charAt(i) == ',') { // padrao brasileiro...
        if (dp == true) { return false; } // already saw a decimal point
        else { dp = true; }
      }
      else {
        return false;
      }
    }
  }
  return true;
}

//-------------------------------------------------------------------
// isZeroValue(value)
//   Returns true if value is zero
//-------------------------------------------------------------------
function isZeroValue(val) {
	if (val != null) {
	    val = val.replace(".", "");
	    val = val.replace(",", ".");
		if (parseFloat(val) == 0.00) {
			return true;
		}
	}
	return false;
}

//-------------------------------------------------------------------
// isDigit(value)
//   Returns true if value is a 1-character digit
//-------------------------------------------------------------------
function isDigit(num) {
  var string="1234567890";
  if (string.indexOf(num) != -1) {
    return true;
  }
  return false;
}

//-------------------------------------------------------------------
// isMonth(string)
//   Returns true if string is either a full month name or a month
//   abbreviation.
//-------------------------------------------------------------------
function isMonth(val) {
  val = val+"";
  val = val.toLowerCase();
  if((val=="jan") || (val=="feb") || (val=="mar") || (val=="apr") || (val=="may") || (val=="jun") ||
     (val=="jul") || (val=="aug") || (val=="sep") || (val=="oct") || (val=="nov") || (val=="dec")) {
    return true;
  }
  if((val=="january") || (val=="february") || (val=="march") || (val=="april") || (val=="may") ||
     (val=="june") || (val=="july") || (val=="august") || (val=="september") || (val=="october") ||
     (val=="november") || (val=="december")) {
    return true;
  }
  return false;
}

//-------------------------------------------------------------------
// setNullIfBlank(input_object)
//   Sets a form field to "" if it isBlank()
//-------------------------------------------------------------------
function setNullIfBlank(obj) {
  if (isBlank(obj.value)) {
    obj.value = "";
  }
}

//-------------------------------------------------------------------
// setFieldsToUpperCase(input_object)
//   Sets value of form field toUpperCase() for all fields passed
//-------------------------------------------------------------------
function setFieldsToUpperCase() {
  for (var i=0; i<arguments.length; i++) {
    var obj = arguments[i];
    obj.value = obj.value.toUpperCase();
  }
}

//-------------------------------------------------------------------
// disallowBlank(input_object[,message[,true]])
//   Checks a form field for a blank value. Optionally alerts if
//   blank and focuses
//-------------------------------------------------------------------
function disallowBlank(obj) {
  var msg;
  var dofocus;
  if (arguments.length>1) { msg = arguments[1]; }
  if (arguments.length>2) { dofocus = arguments[2]; } else { dofocus=false; }
  if (isBlank(obj.value)) {
    if (!isBlank(msg)) {
      alert(msg);
    }
    if (dofocus) {
      obj.select();
      obj.focus();
    }
    return true;
  }
  return false;
}

//-------------------------------------------------------------------
// disallowModify(input_object[,message[,true]])
//   Checks a form field for a value different than defaultValue.
//   Optionally alerts and focuses
//-------------------------------------------------------------------
function disallowModify(obj) {
  var msg;
  var dofocus;
  if (arguments.length>1) { msg = arguments[1]; }
  if (arguments.length>2) { dofocus = arguments[2]; } else { dofocus=false; }
  if (getInputValue(obj) != getInputDefaultValue(obj)) {
    if (!isBlank(msg)) {
      alert(msg);
    }
    if (dofocus) {
      obj.select();
      obj.focus();
    }
    setInputValue(obj,getInputDefaultValue(obj));
    return true;
  }
  return false;
}

//-------------------------------------------------------------------
// isChanged(input_object)
//   Returns true if input object's state has changed since it was
//   created.
//-------------------------------------------------------------------
function isChanged(obj) {
    if ((typeof obj.type != "string") && (obj.length > 0) && (obj[0] != null) && (obj[0].type=="radio")) {
        for (var i=0; i<obj.length; i++) {
            if (obj[i].checked != obj[i].defaultChecked) { return true; }
            }
        return false;
        }
    if ((obj.type=="text") || (obj.type=="hidden") || (obj.type=="textarea"))
        { return (obj.value != obj.defaultValue); }
    if (obj.type=="checkbox") {
        return (obj.checked != obj.defaultChecked);
        }
    if (obj.type=="select-one") {
        if (obj.options.length > 0) {
            var x=0;
            for (var i=0; i<obj.options.length; i++) {
                if (obj.options[i].defaultSelected) { x++; }
                }
            if (x==0 && obj.selectedIndex==0) { return false; }
            for (var i=0; i<obj.options.length; i++) {
                if (obj.options[i].selected != obj.options[i].defaultSelected) {
                    return true;
                    }
                }
            }
        return false;
        }
    if (obj.type=="select-multiple") {
        if (obj.options.length > 0) {
            for (var i=0; i<obj.options.length; i++) {
                if (obj.options[i].selected != obj.options[i].defaultSelected) {
                    return true;
                    }
                }
            }
        return false;
        }
    // return false for all other input types (button, image, etc)
    return false;
}

//-------------------------------------------------------------------
// getInputValue(input_object)
//   Get the value of any form input field
//   Multiple-select fields are returned as comma-separated values
//   (Doesn't support input types: button,file,reset,submit)
//-------------------------------------------------------------------
function getInputValue(obj) {
  if ((obj.type=="radio")) {
    obj = eval("obj.form."+obj.name);
    for (var i=0; i<obj.length; i++) {
      if (obj[i].checked == true) { return obj[i].value; }
    }
    return "";
  }
  if (obj.type=="text" || obj.type=="password") { return obj.value; }
  if (obj.type=="hidden") { return obj.value; }
  if (obj.type=="textarea") { return obj.value; }
  if (obj.type=="checkbox") {
    if (obj.checked == true) { return obj.value; }
    return "";
  }
  if (obj.type=="select-one") {
    if (obj.options.length > 0) {
      return obj.options[obj.selectedIndex].value;
    } else {
      return "";
    }
  }
  if (obj.type=="select-multiple") {
    var val = "";
    for (var i=0; i<obj.options.length; i++) {
      if (obj.options[i].selected) {
        val = val + "" + obj.options[i].value + ",";
      }
    }
    if (val.length > 0) {
      val = val.substring(0,val.length-1); // remove trailing comma
    }
    return val;
  }
  return "";
}

//-------------------------------------------------------------------
// getInputDefaultValue(input_object)
//   Get the default value of any form input field when it was created
//   Multiple-select fields are returned as comma-separated values
//   (Doesn't support input types: button,file,reset,submit)
//-------------------------------------------------------------------
function getInputDefaultValue(obj) {
    if ((typeof obj.type != "string") && (obj.length > 0) && (obj[0] != null) && (obj[0].type=="radio")) {
        for (var i=0; i<obj.length; i++) {
            if (obj[i].defaultChecked == true) { return obj[i].value; }
            }
        return "";
        }
    if (obj.type=="text" || obj.type=="password") { return obj.defaultValue; }
    if (obj.type=="hidden") { return obj.defaultValue; }
    if (obj.type=="textarea") { return obj.defaultValue; }
    if (obj.type=="checkbox") {
        if (obj.defaultChecked == true) {
            return obj.value;
            }
        return "";
        }
    if (obj.type=="select-one") {
        if (obj.options.length > 0) {
            for (var i=0; i<obj.options.length; i++) {
                if (obj.options[i].defaultSelected) {
                    return obj.options[i].value;
                    }
                }
            }
        return "";
        }
    if (obj.type=="select-multiple") {
        var val = "";
        for (var i=0; i<obj.options.length; i++) {
            if (obj.options[i].defaultSelected) {
                val = val + "" + obj.options[i].value + ",";
                }
            }
        if (val.length > 0) {
            val = val.substring(0,val.length-1); // remove trailing comma
            }
        return val;
        }
    return "";
}

//-------------------------------------------------------------------
// setInputValue()
//   Set the value of any form field. In cases where no matching value
//   is available (select, radio, etc) then no option will be selected
//   (Doesn't support input types: button,file,reset,submit)
//-------------------------------------------------------------------
function setInputValue(obj,val) {
    if ((typeof obj.type != "string") && (obj.length > 0) && (obj[0] != null) && (obj[0].type=="radio")) {
        for (var i=0; i<obj.length; i++) {
            if (obj[i].value == val) {
                obj[i].checked = true;
                }
            else {
                obj[i].checked = false;
                }
            }
        }
    if (obj.type=="text" || obj.type=="password")
        { obj.value = val; }
    if (obj.type=="hidden")
        { obj.value = val; }
    if (obj.type=="textarea")
        { obj.value = val; }
    if (obj.type=="checkbox") {
        if (obj.value == val) { obj.checked = true; }
        else { obj.checked = false; }
        }
    if ((obj.type=="select-one") || (obj.type=="select-multiple")) {
        for (var i=0; i<obj.options.length; i++) {
            if (obj.options[i].value == val) {
                obj.options[i].selected = true;
                }
            else {
                obj.options[i].selected = false;
                }
            }
        }
}

//-------------------------------------------------------------------
// isFormModified(form_object,hidden_fields,ignore_fields)
//   Check to see if anything in a form has been changed. By default
//   it will check all visible form elements and ignore all hidden
//   fields.
//   You can pass a comma-separated list of field names to check in
//   addition to visible fields (for hiddens, etc).
//   You can also pass a comma-separated list of field names to be
//   ignored in the check.
//-------------------------------------------------------------------
function isFormModified(theform, hidden_fields, ignore_fields) {
    if (hidden_fields == null) { hidden_fields = ""; }
    if (ignore_fields == null) { ignore_fields = ""; }

    var hiddenFields = new Object();
    var ignoreFields = new Object();
    var i,field;

    var hidden_fields_array = hidden_fields.split(',');
    for (i=0; i<hidden_fields_array.length; i++) {
        hiddenFields[Trim(hidden_fields_array[i])] = true;
        }
    var ignore_fields_array = ignore_fields.split(',');
    for (i=0; i<ignore_fields_array.length; i++) {
        ignoreFields[Trim(ignore_fields_array[i])] = true;
        }
    for (i=0; i<theform.elements.length; i++) {
        var changed = false;
        var name = theform.elements[i].name;
        if (!isBlank(name)) {
            var type = theform[name].type;
            if (!ignoreFields[name]) {
                if (type=="hidden" && hiddenFields[name]) {
                    changed = isChanged(theform[name]);
                    }
                else if (type=="hidden") {
                    changed = false;
                    }
                else {
                    changed = isChanged(theform[name]);
                    }
                }
            }
        if (changed) {
            return true;
            }
        }
        return false;
}


//-------------------------------------------------------------------
// mascaraDiaMesAno( campo_data )
// Mascara o campo para a digitacao de dia, mes e ano (dia/mes/ano)
//-------------------------------------------------------------------
function mascaraDiaMesAno( data ) {
  var mydata = data.value;
  if (mydata.length == 2){
    if (mydata.charAt(1) == "/") {
      data.value = "0" + mydata;
    } else {
      data.value += "/";
    }
  }
  if (mydata.length == 5) {
    if (mydata.charAt(4) == "/") {
      var lastChar = mydata.charAt(3);
      var mydata = mydata.substring(0, 3);
      data.value = mydata + "0" + lastChar + "/";
    } else {
      data.value += "/";
    }
  }
}

//-------------------------------------------------------------------
// Daqui pra baixo, foi o Eduardo Gouvea que fez. Se precisar mudar de
// arquivo para um lugar melhor, tudo bem.
//-------------------------------------------------------------------


//-------------------------------------------------------------------
// mascaraMesAno( campo_data )
// Mascara o campo para a digitacao de mes e ano (mes/ano)
//-------------------------------------------------------------------
function mascaraMesAno( data ) {
  var mydata = data.value;
  if (mydata.length == 2){
    if (mydata.charAt(1) == "/") {
      data.value = "0" + mydata;
    } else {
      data.value += "/";
    }
  }
}

//-------------------------------------------------------------------
//  Conta o numero de checkboxes selecionadas
//-------------------------------------------------------------------
function countSelected(umaCheckBox) {
  var contador = 0;

  if (umaCheckBox != null) {
    if (umaCheckBox.length != null) {
      for (var i = 0; i < umaCheckBox.length && contador < 2; i++) {
        if(umaCheckBox[i].checked) {
          contador++;
        }
      }
    } else {
      if(umaCheckBox.checked) {
        contador++;
      }
    }
  }
  return contador;
}


//------------------------------------------------------------------------------
//  Conta o numero de itens selecionados em um array de option
//------------------------------------------------------------------------------
function countSelectedOption(arrayOption) {
  var contador = 0;

  if (arrayOption != null) {
    if (arrayOption.length != null) {
      for (var i = 0; i < arrayOption.length && contador < 2; i++) {
        if(arrayOption[i].selected) {
          contador++;
        }
      }
    } else {
      if(arrayOption.checked) {
        contador++;
      }
    }
  }
  return contador;
}


//-------------------------------------------------------------------
//  Retorna true se mais de uma checkbox estiver selecionada
//-------------------------------------------------------------------
function maisDeUmaSelecionada(umaCheckBox) {
  return (countSelected(umaCheckBox) > 1);
}


//------------------------------------------------------------------------------
//  Retorna true se mais de um item estiver selecionado no array de option
//  ou checkbox
//------------------------------------------------------------------------------
function maisDeUmItemSelecionado(arrayOption) {
  return (countSelectedOption(arrayOption) > 1);
}


//-------------------------------------------------------------------
//  Retorna true se nenhuma checkbox estiver selecionada
//-------------------------------------------------------------------
function nenhumaSelecionada(umaCheckBox) {
  return (countSelected(umaCheckBox) == 0);
}


//-------------------------------------------------------------------
//  Pega o "value" da primeira checkbox selecionada
//
//  @deprecated
//  Dever� ser substitu�da por <code>getFirstSelectedCheckbox(umaCheckBox)</code>
//-------------------------------------------------------------------
function getSelected(umaCheckBox) {
  var valor = "";

  if (umaCheckBox != null) {
    if (umaCheckBox.length != null) {
      for (var i = 0; i < umaCheckBox.length; i++) {
        if(umaCheckBox[i].checked) {
          valor = umaCheckBox[i].value;
          break;
        }
      }
    } else {
      valor = umaCheckBox.value;
    }
  }
  return valor;
}


//------------------------------------------------------------------------------
//  Retorna a unica ou a primeira check box selecionada
//------------------------------------------------------------------------------
function getFirstSelectedCheckbox(umaCheckbox) {
  var checkboxSel = null;

  if (umaCheckbox != null) {
    if (umaCheckbox.length != null) {
      for (var i = 0; i < umaCheckbox.length; i++) {
        if(umaCheckbox[i].checked) {
          checkboxSel = umaCheckbox[i];
          break;
        }
      }
    } else {
      checkboxSel = umaCheckbox;
    }
  }
  return checkboxSel;
}


//------------------------------------------------------------------------------
//  Retorna todas as checkboxes selecionadas.
//  Mesmo que haja apenas 1 checkbox selecionada, sempre retorna um array.
//------------------------------------------------------------------------------
function getAllSelectedCheckboxes(umArrayCheckbox) {
    var subArray = null;

    if (umArrayCheckbox != null) {
        if (umArrayCheckbox.length != null) {
            for (var i = 0; i < umArrayCheckbox.length; i++) {
                if(umArrayCheckbox[i].checked) {
                    if (subArray == null) {
                        subArray = new Array();
                    }
                    subArray[subArray.length] = umArrayCheckbox[i];
                }
            }
        } else {
            if (subArray == null) {
                subArray = new Array();
            }
            subArray[subArray.length] = umArrayCheckbox;
        }
    }
    return subArray;
}


//------------------------------------------------------------------------------
//  Retorna o primeiro <option> selecionado de um <select>
//------------------------------------------------------------------------------
function getSelectedOption(umSelect) {
  var optionSel = null;

  if (umSelect != null) {
    for (var i = 0; i < umSelect.length; i++) {
      if(umSelect[i].selected) {
        optionSel = umSelect[i];
        break;
      }
    }
  }
  return optionSel;
}


//------------------------------------------------------------------------------
//  Retorna o primeiro radio selecionado de um <input type=radio>
//------------------------------------------------------------------------------
function getSelectedRadio(umRadio) {
  var radioSel = null;

  if (umRadio != null) {
      if (umRadio.length != null && umRadio.length > 0) {
          for (var i = 0; i < umRadio.length; i++) {
              if(umRadio[i].checked) {
                  radioSel = umRadio[i];
                  break;
              }
          }
      } else {
          if(umRadio.checked) {
              radioSel = umRadio;
          }
      }
  }
  return radioSel;
}


//------------------------------------------------------------------------------
// Verifica se o valor inteiro esta contido no conjunto (array) de valores
//------------------------------------------------------------------------------
function estahContido(valor, valores) {
  var encontrou = false;

  if (valores != null) {
    if (valores.length != null) {
      for (var i = 0; i < valores.length && !encontrou; i++) {
        if (parseInt(valor, 10) == parseInt(valores[i])) {
          encontrou = true;
        }
      }
    }
  }
  return encontrou;
}

//-----------------------------------------------------------
// Functions acrescentadas por emurai

function getOptionIndex(comp, pkey) {
  var i;

  for (i=0; i<comp.options.length; i++) 
  	if (comp.options[i].value==pkey) 
		return i;

  return -1;
}


function extrairMascaraCPF(cpf) {
  var tam = cpf.length;
  var cpfNumerico = "";
  for (var idx = 0; idx < tam; idx++) {
    ch = cpf.substring(idx, idx+1);
    if (ch != '.' && ch != '-' && ch != ' ')
      cpfNumerico += ch;
  }
  return cpfNumerico;
}

function validarMascaraCPF(cpf) {
  var posDot1 = cpf.indexOf('.', 0);
  var posDot2 = cpf.indexOf('.', posDot1+1);
  var posBar = cpf.indexOf('-');

  if (posDot1 > -1 || posDot2 > -1 || posBar > -1) {
    if (posDot1 != 3 || posDot2 != 7 || posBar != 11) {
      return false;
    }
  }
  return true;
}


function extrairMascaraCGC(cgc) {
  var tam = cgc.length;
  var cgcNumerico = "";
  for (var idx = 0; idx < tam; idx++) {
    ch = cgc.charAt(idx);
    if (ch != '.' && ch != '-' && ch != '/' && ch != ' ')
      cgcNumerico += ch;
  }

  return cgcNumerico;
}

function validarMascaraCGC(cgc) {
  var posDot1 = cgc.indexOf('.', 0);
  var posDot2 = cgc.indexOf('.', posDot1+1);
  var posSlash = cgc.indexOf('/');
  var posBar = cgc.indexOf('-');

  if (posDot1 > -1 || posDot2 > -1 || posSlash > -1 || posBar > -1) {
    if (posDot1 != 2 || posDot2 != 6 || posSlash != 10 || posBar != 15) {
      return false;
    }
  }
  return true;
}

function valida_cpf(cpf){
cpf = extrairMascaraCPF(cpf);
var numeros, digitos, soma, i, resultado, digitos_iguais;
digitos_iguais = 1;
if (cpf.length < 11)
      return false;
for (i = 0; i < cpf.length - 1; i++)
      if (cpf.charAt(i) != cpf.charAt(i + 1))
            {
            digitos_iguais = 0;
            break;
            }
if (!digitos_iguais)
      {
      numeros = cpf.substring(0,9);
      digitos = cpf.substring(9);
      soma = 0;
      for (i = 10; i > 1; i--)
            soma += numeros.charAt(10 - i) * i;
      resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
      if (resultado != digitos.charAt(0))
            return false;
      numeros = cpf.substring(0,10);
      soma = 0;
      for (i = 11; i > 1; i--)
            soma += numeros.charAt(11 - i) * i;
      resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
      if (resultado != digitos.charAt(1))
            return false;
      return true;
      }
else
      return false;
}


function valida_cnpj(cnpj){
cnpj = extrairMascaraCGC(cnpj);
var numeros, digitos, soma, i, resultado, pos, tamanho, digitos_iguais;
digitos_iguais = 1;
if (cnpj.length < 14 && cnpj.length < 15)
      return false;
for (i = 0; i < cnpj.length - 1; i++)
      if (cnpj.charAt(i) != cnpj.charAt(i + 1))
            {
            digitos_iguais = 0;
            break;
            }
if (!digitos_iguais)
      {
      tamanho = cnpj.length - 2
      numeros = cnpj.substring(0,tamanho);
      digitos = cnpj.substring(tamanho);
      soma = 0;
      pos = tamanho - 7;
      for (i = tamanho; i >= 1; i--)
            {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2)
                  pos = 9;
            }
      resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
      if (resultado != digitos.charAt(0))
            return false;
      tamanho = tamanho + 1;
      numeros = cnpj.substring(0,tamanho);
      soma = 0;
      pos = tamanho - 7;
      for (i = tamanho; i >= 1; i--)
            {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2)
                  pos = 9;
            }
      resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
      if (resultado != digitos.charAt(1))
            return false;
      return true;
      }
else
      return false;
} 

function verificarCGC(CGCValue) {
  var dig1 = 0;
  var dig2 = 0;
  var i;
  var fator;
  
  CGCValue = extrairMascaraCGC(CGCValue);

  if(CGCValue==null||
  	 trim(CGCValue).length>14||
  	 trim(CGCValue).length==0||
  	 CGCValue==" ")
     return false;
  
  if (trim(CGCValue).length < 14)
         CGCValue = fillZerosLeft(CGCValue,'0',14);

  // primeiro digito
  fator=14;
  for (i=12; i>=1; i--){ 
	if (i==4) 
	  fator= 6;
    
      dig1= dig1 + parseInt(CGCValue.substring(i-1,i),10)*(fator-i);
     }

  dig1= dig1%11;
  if (dig1==0 || dig1==1) dig1=0;
  else dig1= 11-dig1;

  if (!parseInt(dig1,10) == CGCValue.substring(12,13) ) return false;

  // segundo digito
  fator=15;
  for (i=13; i>=1; i--){
	  if (i==5) fator= 7;
	  
      dig2= dig2 + parseInt(CGCValue.substring(i-1,i),10)*(fator-i);
     }

  dig2= dig2%11;
  if (dig2==0 || dig2==1) dig2=0;
  else dig2= 11-dig2;

  if( parseInt(dig2,10) != (CGCValue.substring(13,14)) ){
    return false;
  }
  else{
    return true;
  }
  
}

function fillZerosLeft(s, ch, tam) {
var s1='';
var dif=0;
var i=0;

 if (s=='') return '';
 
 if (s.length<tam) {
  dif = tam - s.length;
    
  for (i=1; i<=dif; i++) 
    s1+= ch;
    
  s1+= s;
  return s1;
 }
 else return s;
}

 
function verificarCPF(CPFValue) {
    var i;
    var dig1 = 0;
    var dig2 = 0;
	           
    CPFValue = extrairMascaraCPF(CPFValue);

    if (CPFValue==null||
    	trim(CPFValue).length>11||
    	trim(CPFValue).length==0||
    	CPFValue == " ")
        return false;
        
    if (trim(CPFValue).length < 11)
       CPFValue = fillZerosLeft(CPFValue,'0',11);   

    for (i=1; i<=9; i++){
      dig1= dig1 + parseInt(CPFValue.substring(i-1,i),10) *i;
    }

    dig1= dig1%11;
    
    if (dig1==10) dig1=0;

    if (dig1 != CPFValue.substring(9,10)) return false;

    for (i=2; i<=10; i++){
       dig2= dig2 + parseInt(CPFValue.substring(i-1,i),10)*(i-1); 
    }

    dig2= dig2%11;

    if (dig2==10) dig2=0;

    if(dig2 != CPFValue.substring(10,11)){
      return false;
    }
    else{
      return true;
    }

}

function toCpf(cpf) {

    if(trim(cpf.value)!="" && cpf.value.length == 11) {

      cpf = cpf.value.substring(0,3) + "." + cpf.value.substring(3,6) +
            "." + cpf.value.substring(6,9) + "-" + cpf.value.substring(9,11);

    }

    return cpf;
}

function toCnpj(cnpj) {

    if(trim(cnpj.value)!="" && cnpj.value.length == 14) {

      cnpj = cnpj.value.substring(0,2) + "." + cnpj.value.substring(2,5) + "." 
             + cnpj.value.substring(5,8) + "/" + cnpj.value.substring(8,12) 
             + "-" + cnpj.value.substring(12,14);

    }

    return cnpj;
}


function getSelectedRadioCheckbox(umaCheckBox) {
  var valor = "";

  if (umaCheckBox != null) {
    if (umaCheckBox.length != null) {
      for (var i = 0; i < umaCheckBox.length; i++) {
        if(umaCheckBox[i].checked) {
          valor = umaCheckBox[i].value;
          break;
        }
      }
    } else {
      valor = umaCheckBox.value;
    }
  }
  return valor;
}

function filtrarDados(obj, grouping) {
  var keyDecimal = [190, 194];
  if (grouping == '.') keyDecimal = [190, 194];
  else if (grouping == ',') keyDecimal = [188, 110];

  if (event.keyCode != 9 && event.keyCode != 16 && event.keyCode != 8 && event.KeyCode != 13 &&
      event.keyCode != 35 && event.keyCode != 36) {
    if (event.keyCode == keyDecimal[0] || event.keyCode == keyDecimal[1]) {
      obj.value = obj.value.substring(0, obj.value.length-1);
    }
  }
}

function valueToMaskDec(obj, numDecimal, grouping, decimal) {
//if ( isNaN(numDecimal) || trim(numDecimal) == "") numDecimal = 2;
//numDecimal = parseInt(''+numDecimal, 10);
//if (event.keyCode != 9 && event.keyCode != 16) {
    var menor = false;
    var dp = false;
    var dec = false;
    var tamOrig;
    if(obj.value.indexOf(decimal) >= 0) {
      dec = true;
      var indexPos = obj.value.indexOf(decimal);
      var subAux = obj.value.substring(indexPos+1);
      if (subAux.length < numDecimal) {
        obj.value += lPad('', '0', (numDecimal - subAux.length)); //'00';
      }
      else if (subAux.length > numDecimal) {
        obj.value = setDecimalDec(obj.value, numDecimal, grouping, decimal);

        indexPos = obj.value.indexOf(decimal);
        subAux = obj.value.substring(indexPos+1);
        if (subAux.length < numDecimal) {
          obj.value += lPad('', '0', (numDecimal - subAux.length)); //'00';
        }
      }

    }
    while(obj.value.match(/\D/)) {
      obj.value = obj.value.replace(/\D/,'');
      dp = true;
    }
    while(obj.value.substr(0,1)=='0') {
      obj.value = obj.value.substr(1);
    }

    if (obj.value.length == 0) tamOrig = 1;
    else tamOrig = obj.value.length;
    while(obj.value.length<(numDecimal+1)) {
      if (dec)
        obj.value = '' + 0 + obj.value;        
      else {
        var strAux='';
        for (var x=0; x<tamOrig; x++) {
          strAux += '0';
        }
        obj.value = obj.value + strAux + ''; 
      }

      menor = true;
    }
    
    var valor = "";
    var tam;
    if (menor) tam = obj.value.length-(numDecimal+1);
    else if (dp) {
      tam = obj.value.length-numDecimal;
    }
    else tam = obj.value.length;

    for (var idx = tam; idx > 0; idx-=3) {
      valor = obj.value.substring(idx, idx - 3) + grouping + valor;
    }
    if (valor.charAt(valor.length-1) == grouping) valor = valor.substring(0, valor.length-1);

    if (!menor && dp) obj.value = valor + obj.value.substr(obj.value.length-numDecimal);
    else if (!menor && !dp) obj.value = valor + lPad('', '0', numDecimal);
    obj.value = obj.value.substr(0,obj.value.length-numDecimal)+decimal+obj.value.substr(obj.value.length-numDecimal);
 // }
}

function maskToValue(obj) {
  var valor = obj.value;
  valor = valor.replace(".", "");
  valor = valor.replace(",", ".");
  obj.value = valor;
}

function maskFloatToValue(obj) {
  var valor = obj.value;
  valor = valor.replace(".", "");
  valor = valor.replace(",", ".");
  return valor;
}

function setDecimalDec(parametro, numCasas, grouping, decimal){
  pos = parametro.indexOf(decimal);
  if (pos > 0) {
     pInt = parametro.substring(0, pos);
     pDec = parametro.substring(pos + 1, parametro.length);
     if (pDec.length > numCasas) {
        ind = pDec.substring(numCasas, (numCasas+1));
        if (Math.abs(ind) >= 5) {
           pDec = Math.abs(pDec.substring(0,numCasas)) + 1;
           if ( pDec == eval('1'+lPad('', '0', numCasas)) ) {
              pInt++;
              pDec = lPad('', '0', numCasas);
           }
           else if (pDec == 1)
             pDec = lPad('', '0', (numCasas-1)) + "1";
        }
        else pDec = pDec.substring(0,numCasas);
           return (pInt + decimal + pDec);
     }
     else {
        if (pDec.length < (numCasas -1))
           pDec = pDec + lPad('', '0', (numCasas-pDec.length));
           return (pInt + decimal + pDec);
     }
  }
  else {
     return (trim(parametro) != "" ) ? parametro+decimal+lPad('', '0', numCasas) : parametro;
  }
}

//-------------------------------------------------------------------
// LPad e RPad functions
//   Retorna uma string ajustada um numero definido do caracter de preenchimento
//-------------------------------------------------------------------
function lPad(str, charac, length) {
  var strAux = str;
  for (var i=strAux.length; strAux.length<length; i++) strAux = charac + strAux;
  return strAux;
}

function rPad(str, charac, length) {
  var strAux = str;
  for (var i=strAux.length; strAux.length<length; i++) strAux += charac;
  return strAux;
}

/********************************************************
* Nome: fApplyCurrencyMask
* Descricao: Aplica ao campo a mascara para valores:#.##0,00 .
* Parqmetros: 
*	oField - O Objeto do campo
*
********************************************************/
function fApplyCurrencyMask(oField)
{
	var reg_exp = / /g;
	var currvalue = oField.value.replace(reg_exp,'');
	if(currvalue != "")
	{
		reg_exp = /,/g;
		var aux = currvalue.replace(reg_exp,'');
		
		reg_exp = /\./g;
		aux = aux.replace(reg_exp,'');
		if(aux != "")
		{
			if(aux.length == 1)
			{
				currvalue = "0,0" + aux;
			}
			else
			{
				var decimalpos = aux.length - 3;
				var qtdemilhar = 3, auxMilhar = 0;
				currvalue = "";
				for(iCount = aux.length-1; iCount >= 0;iCount--)
				{
					if(iCount == decimalpos)
						currvalue = "," + currvalue;
						
					if(iCount < decimalpos)
						auxMilhar++;
					
					if(auxMilhar == qtdemilhar)
					{
						currvalue = "." + currvalue;
						auxMilhar = 0;
					}

					currvalue = aux.charAt(iCount) + currvalue;
						
				}
				while(currvalue.charAt(0) == "0")
				{
					if(currvalue.charAt(1) != ",")
						currvalue = currvalue.substring(1);
					else
						break;
				}
			}
			
		}
		
	}
	oField.value = currvalue;
}

/********************************************************
* Nome: fValidateLength
* Descricao: Verifica se na digitacao o campo ja estourou o tamanho maximo permitido, excluindo "." e ","
* Parametros: 
*	oField  - O Objeto do campo
*       oLength - O tamanho maximo permitido
*
********************************************************/
function fValidateLength(oField, oLength)
{
	var reg_exp = /,/g;
	var currvalue = oField.value;
	var valorOrigem = oField.value;

	if(currvalue != "") {
		var aux = currvalue.replace(reg_exp,'');
		
		reg_exp = /\./g;
		aux = aux.replace(reg_exp,'');
		if (aux != "") {
			if (aux.length > parseInt(oLength)) {
				if (parseInt(aux, 10) != 100000) {
				    if (parseInt(aux, 10) != 10000) {
						currvalue = aux.substring(0, oLength);
					}
				}else{
					currvalue = aux.substring(0, aux.length-1);
				}
			}
		}
	}
	oField.value = currvalue;
	fApplyCurrencyMask(oField);
}

function Diferenca_Entre_Datas(Dt_Face, Dt_Boleto) {
	var dias = 0;
	var date2 = new String(Dt_Face);
	var date1 = new String(Dt_Boleto);
	date1 = date1.split("/");
	date2 = date2.split("/");
	var sDate = new Date(date1[1]+"/"+date1[0]+"/"+date1[2]);
	var eDate = new Date(date2[1]+"/"+date2[0]+"/"+date2[2]);
	dias = Math.round((sDate-eDate)/86400000);
	return dias;
 }

function mascara(o, f) {
    v_obj = o;
    v_fun = f;
    setTimeout("execmascara()", 1);
}

function execmascara() {
    v_obj.value = v_fun(v_obj.value);
}

function cpf(v) {
    v = v.replace(/\D/g, "");                       //Remove tudo o que não é dígito
    v = v.replace(/(\d{3})(\d)/, "$1.$2");          //Coloca um ponto entre o terceiro e o quarto dígitos
    v = v.replace(/(\d{3})(\d)/, "$1.$2");          //Coloca um ponto entre o terceiro e o quarto dígitos de novo (para o segundo bloco de números)
    v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");    //Coloca um hífen entre o terceiro e o quarto dígitos
    return v;
}

function cnpj(v) {
	v = v.replace(/\D/g, "");                           //Remove tudo o que não é dígito
	v = v.replace(/^(\d{2})(\d)/, "$1.$2");             //Coloca ponto entre o segundo e o terceiro dígitos
	v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3"); //Coloca ponto entre o quinto e o sexto dígitos
	v = v.replace(/\.(\d{3})(\d)/, ".$1/$2");           //Coloca uma barra entre o oitavo e o nono dígitos
	v = v.replace(/(\d{4})(\d)/, "$1-$2");              //Coloca um hífen depois do bloco de quatro dígitos
	return v;
}