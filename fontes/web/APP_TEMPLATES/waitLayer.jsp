<script language="javascript">

//------------------------------------------------------------------------------
//  Inibe o layer com ampulheta
//------------------------------------------------------------------------------
function hideWaitLayer(layerName) {
    var waitLayer = document.getElementById(layerName == null ? "waitLayer" : layerName);
    // Remove a layer
    waitLayer.style["visibility"] = "hidden";
    // Exibe o scroll da página
    document.body.scroll = "auto";
}
 
//------------------------------------------------------------------------------
//  Exibir o layer com ampulheta
//------------------------------------------------------------------------------
function showWaitLayer(layerName) {
    //alert("Wait Layer");
    var waitLayer = document.getElementById(layerName == null ? "waitLayer" : layerName);
    // Seta o topo da layer igual o topo da janela
    waitLayer.style["top"] = document.body.scrollTop;
    // Exibe a layer
    waitLayer.style["visibility"] = "visible";
    // Remove o scroll da página    
    // document.body.scroll = "no";    
}
 
//------------------------------------------------------------------------------
//  Verifica se o layer com ampulheta está visível
//------------------------------------------------------------------------------
function isWaitLayerVisible(layerName) {
    var waitLayer = document.getElementById(layerName == null ? "waitLayer" : layerName);
    // verifica se a layer está visivel
    return (waitLayer.style["visibility"] == "visible");
}

</script>

<!-- Layer modal para ampulheta -->
<div id="waitLayer" style="
                           position:absolute; 
                           left:0px; 
                           top:0px; 
                           width:100%; 
                           height:100%; 
                           cursor:wait; 
                           background-image:url(_img/waitLayer.gif); 
                           visibility:hidden; 
                           cursor:wait; 
                           z-index:2002; 
                           border:0px solid red
                         ">
</div>                         