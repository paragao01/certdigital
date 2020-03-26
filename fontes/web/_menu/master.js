// 'DO NOT WRITE TO OR ALTER THIS FILE
// 'Even if you edit the JavaScript your risking
// 'not being able to load it back into Creata-Tree
// '
// '
// '
// '
//# Generator = Creata-Tree
//# Version = 2.0.3
// '
// 'About: Creata-Tree by NeoText, download free at http://www.winternet.com/~chrome/maxftp
// 'Copyright 1999, by NeoText
// '
// '
//# TreeX = 5
//# TreeY = 5
//# TreeSize = 1
//# TreeLines = 1
//# TreeLineColor = Black
//# TreePMColor = Black
//# TreeBNonExpanded = 
//# TreeBExpanded = 
//# TreeLeaf = 
//# TreeTarget = 
//# TreeBackground = 
//# TreeFontSize = 2
//# TreeFontColor = Olive
//\\
var BrowserSwitch =  false;
var BackGroundColor = "";
var pmPercent = 50;
var trans2x2=new Image();
trans2x2.src="treePics/2x2.gif";
var menuBase = new menuObject(5,5,20,true,"treePics/plus.gif","treePics/minus.gif","treePics/top.gif","treePics/mid.gif","treePics/btm.gif","treePics/hline.gif","treePics/vline.gif");

menuBase.menuItems[0]= new menuItemObject("","","","","Gerencial","2","Black","","","");
menuBase.menuItems[0].subMenu[5]= new menuItemObject("","","","","Gestor Garantia","2","Black","","","");
menuBase.menuItems[0].subMenu[5].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirGestor.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[5].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaGestor.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[0].subMenu[7]= new menuItemObject("","","","","Parceiro Garantia","2","Black","","","");
menuBase.menuItems[0].subMenu[7].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirParceiro.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[7].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaParceiro.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[0].subMenu[9]= new menuItemObject("","","","","Segmento","2","Black","","","");
menuBase.menuItems[0].subMenu[9].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirSegmento.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[9].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaSegmento.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[0].subMenu[20]= new menuItemObject("","","","","Parametro Garantia","2","Black","","","");
menuBase.menuItems[0].subMenu[20].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirParametro.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[20].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaParametro.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[0].subMenu[25]= new menuItemObject("","","","","Períodos","2","Black","","",""); 
menuBase.menuItems[0].subMenu[25].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../manutencaoPeriodo.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[25].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaPeriodo.do?metodo=pesquisar","mainFrame","");
menuBase.menuItems[0].subMenu[30]= new menuItemObject("","","","","Redutor Abert. Cta.","2","Black","","",""); 
menuBase.menuItems[0].subMenu[30].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../redutorAberturaConta.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[30].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../redutorAberturaConta.do?metodo=prepararPesquisa","mainFrame","");
menuBase.menuItems[0].subMenu[50]= new menuItemObject("","","","","Redutor Praça","2","Black","","","");
menuBase.menuItems[0].subMenu[50].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../redutorPraca.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[50].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../redutorPraca.do?metodo=prepararPesquisa","mainFrame","");
menuBase.menuItems[0].subMenu[60]= new menuItemObject("","","","","Redutor Banco","2","Black","","","");
menuBase.menuItems[0].subMenu[60].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../redutorBanco.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[60].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../redutorBanco.do?metodo=prepararPesquisa","mainFrame",""); 
menuBase.menuItems[0].subMenu[62]= new menuItemObject("","","","","Redutor Agência","2","Black","","","");
menuBase.menuItems[0].subMenu[62].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../redutorAgencia.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[62].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../redutorAgencia.do?metodo=prepararPesquisa","mainFrame",""); 
menuBase.menuItems[0].subMenu[65]= new menuItemObject("","","","","Regra Garantir Chq","2","Black","","","");
menuBase.menuItems[0].subMenu[65].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirRegraGarantiaCheque.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[65].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaRegraGarantiaCheque.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[0].subMenu[67]= new menuItemObject("","","","","Flexibilizar Créd.","2","Black","","","");
menuBase.menuItems[0].subMenu[67].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirFlexibilizarCredito.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[67].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaFlexibilizarCredito.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[0].subMenu[68]= new menuItemObject("","","","","Alínea Ress. Reduzido","2","Black","","","");
menuBase.menuItems[0].subMenu[68].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirAlineaRessarcimentoReduzido.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[68].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaAlineaRessarcimentoReduzido.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[0].subMenu[70]= new menuItemObject("","","","","Planos Créditos","2","Black","","","");
menuBase.menuItems[0].subMenu[70].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirPlanoGarantia.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[70].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaPlanoGarantia.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[0].subMenu[80]= new menuItemObject("","","","","Grupo Regra Negoc.","2","Black","","","");
menuBase.menuItems[0].subMenu[80].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirGrupoRegraNegocio.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[80].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaGrupoRegraNegocio.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[0].subMenu[90]= new menuItemObject("","","","","Situação Cheque","2","Black","","","");
menuBase.menuItems[0].subMenu[90].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirSituacaoGarantia.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[90].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaSituacaoGarantia.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[0].subMenu[100]= new menuItemObject("","","","","Motivo Cheque","2","Black","","",""); 
menuBase.menuItems[0].subMenu[100].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirMotivoGarantia.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[0].subMenu[100].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaMotivoGarantia.do?metodo=pesquisar","mainFrame","");
menuBase.menuItems[0].subMenu[110]= new menuItemObject("","","","","Depto. Garantia","2","Black","","","");
menuBase.menuItems[0].subMenu[110].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirDepartamento.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[110].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaDepartamento.do?metodo=pesquisar","mainFrame","");
menuBase.menuItems[0].subMenu[120]= new menuItemObject("","","","","Alínea x Sit. x Depto","2","Black","","","");
menuBase.menuItems[0].subMenu[120].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirAlineaMotivoDepto.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[120].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaAlineaMotivoDepto.do?metodo=pesquisar","mainFrame","");
menuBase.menuItems[0].subMenu[130]= new menuItemObject("","","","","Motivo Cancelamento","2","Black","","","");
menuBase.menuItems[0].subMenu[130].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirMotivoCancelamento.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[130].subMenu[1]= new menuItemObject("","","","","Listar","2","Black","../pesquisaMotivoCancelamento.do?metodo=pesquisar","mainFrame","");
menuBase.menuItems[0].subMenu[140]= new menuItemObject("","","","","Consultar","2","Black","","","");
menuBase.menuItems[0].subMenu[140].subMenu[0]= new menuItemObject("","","","","Carteira","2","Black","../consultarCarteira.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[140].subMenu[1]= new menuItemObject("","","","","Depósito Futuro","2","Black","../consultarDepositoFuturo.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[140].subMenu[2]= new menuItemObject("","","","","Totalização p/Respostas","2","Black","../consultarTotalResposta.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[140].subMenu[3]= new menuItemObject("","","","","Análise Regra de Crédito","2","Black","../consultarAnaliseRegraCredito.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[0].subMenu[150]= new menuItemObject("","","","","Mensagem Garantia","2","Black","","","");
menuBase.menuItems[0].subMenu[150].subMenu[0]= new menuItemObject("","","","","Listar","2","Black","../pesquisaMensagem.do?metodo=pesquisar","mainFrame",""); 
menuBase.menuItems[1]= new menuItemObject("","","","","Operacional","2","Black","","","");
menuBase.menuItems[1].subMenu[5]= new menuItemObject("","","","","Empresa Garantia","2","Black","","","");
menuBase.menuItems[1].subMenu[5].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirEmpresa.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[1].subMenu[5].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../pesquisaEmpresa.do?metodo=prepararPesquisa","mainFrame",""); 
menuBase.menuItems[1].subMenu[7]= new menuItemObject("","","","","Contrato Empresa","2","Black","","","");
menuBase.menuItems[1].subMenu[7].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirContratoEmpresa.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[1].subMenu[7].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../pesquisaContratoEmpresa.do?metodo=prepararPesquisa","mainFrame",""); 
menuBase.menuItems[1].subMenu[50]= new menuItemObject("","","","","Plano Contrato","2","Black","","","");
menuBase.menuItems[1].subMenu[50].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirPlanoContrato.do?metodo=prepararPesquisa&operation=INSERT","mainFrame","");
menuBase.menuItems[1].subMenu[50].subMenu[1]= new menuItemObject("","","","","Alterar","2","Black","../incluirPlanoContrato.do?metodo=prepararPesquisa&operation=UPDATE","mainFrame",""); 
menuBase.menuItems[1].subMenu[55]= new menuItemObject("","","","","Operador","2","Black","","","");
menuBase.menuItems[1].subMenu[55].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirOperador.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[1].subMenu[55].subMenu[1]= new menuItemObject("","","","","Pesquisar","2","Black","../pesquisaOperador.do?metodo=prepararPesquisa","mainFrame",""); 
menuBase.menuItems[1].subMenu[60]= new menuItemObject("","","","","Controle Docto","2","Black","","","");
menuBase.menuItems[1].subMenu[60].subMenu[0]= new menuItemObject("","","","","Bloquear Docto","2","Black","../bloquearControleDocumento.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[1].subMenu[60].subMenu[1]= new menuItemObject("","","","","Desbloquear Docto","2","Black","../desbloquearControleDocumento.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[1].subMenu[60].subMenu[2]= new menuItemObject("","","","","Listar Docto","2","Black","../pesquisaControleDocumento.do?metodo=prepararPesquisa","mainFrame",""); 
menuBase.menuItems[1].subMenu[90]= new menuItemObject("","","","","Garantir Cheque","2","Black","../garantirCheque.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[1].subMenu[100]= new menuItemObject("","","","","Alterar Cheque","2","Black","../alterarChequeGarantido.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[1].subMenu[110]= new menuItemObject("","","","","Liberar Cheque","2","Black","../liberarChequeGarantido.do?metodo=prepararFiltro","mainFrame","");
menuBase.menuItems[1].subMenu[120]= new menuItemObject("","","","","Cheque Devolvido","2","Black","","","");
menuBase.menuItems[1].subMenu[120].subMenu[0]= new menuItemObject("","","","","Adicionar","2","Black","../incluirChequeDevolvido.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[1].subMenu[120].subMenu[1]= new menuItemObject("","","","","Alterar","2","Black","../alterarChequeDevolvido.do?metodo=prepararFiltroAlteracao","mainFrame",""); 
menuBase.menuItems[1].subMenu[140]= new menuItemObject("","","","","Consultar","2","Black","","","");
menuBase.menuItems[1].subMenu[140].subMenu[0]= new menuItemObject("","","","","Cheque","2","Black","../consultarCheque.do?metodo=prepararFiltro","mainFrame",""); 
menuBase.menuItems[1].subMenu[140].subMenu[1]= new menuItemObject("","","","","Compr. CPF/CNPJ","2","Black","../consultarComprometimento.do?metodo=prepararFiltro","mainFrame",""); 
