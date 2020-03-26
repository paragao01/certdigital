/*
  Bloqueia as teclas:
  - F5: para evitar o refresh da página
  - F11: para evitar o modo de exibição do browser
  - CTRL+N: para evitar que o usuário abra uma nova janela pela combinação <CTRL>+<N>
  Chamar essa função no evento "on key down" do body
*/
function bloquearTeclas(evt) {

	var keyCode = document.layers ? evt.which : document.all ? evt.keyCode : evt.keyCode;	

	if ( keyCode    == 116 // <F5>
	     || keyCode == 122 // <F11>
	     || keyCode == 17 // <CTRL>
	     || (keyCode == 78 && evt.ctrlKey == true) // <CTRL>+<N>
	     //|| keyCode == 8 // <BACKSPACE>
	   ) {

		if (evt.srcElement.type != "file") {
	   		evt.keyCode = 0;
			evt.returnValue = 0;
			return false;
		}
	} 

}

/*
  Bloqueia botão direito do mouse
  Chamar essa função no evento "on mouse down" do body
*/
function bloquearBotaoDireitoMouse(event) {

	if(event.button > 1) {  
//		alert("Segura Serviços\n© 2006");
		return false;
	}

}

