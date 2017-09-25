var host = window.location.origin + "/MyHouseTech/";

//***********************************/
//*      Util						*/
//***********************************/

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

//***********************************/
//*      Area Restrita              */
//***********************************/

function AlteraRele(id, status, ip){
    if(status == 'Ligar') {
    	$.get("http://" + ip + "/?Ligado=1");
    	$('#' + id + ' img').attr('src','images/lampOn.png');
        $('#' + id + ' img').attr('alt','lamp on');
        $('#' + id + ' img').attr('title','lamp on');
        $('#' + id + ' input#btnLigar').attr("value", "Desligar");
        $('#' + id + ' input#btnLigar').attr("class", "btnDesligar");
    }
    else if (status == 'Desligar') {
    	$.get("http://" + ip + "/?Desligado=1");
        $('#' + id + ' img').attr('src','images/lampOff.png');
        $('#' + id + ' img').attr('alt','lamp off');
        $('#' + id + ' img').attr('title','lamp off');
        $('#' + id + ' input#btnLigar').attr("value", "Ligar");
        $('#' + id + ' input#btnLigar').attr("class", "btnLigar");
    }
}

//==============================

function montaListaUsuario(){
	majax = $.ajax({
		type: "GET",
		async: "false",
		crossDomain: false,
		timeout: 9000,
		dataType: "json",
		url: host + "rest/gerenciaJson.jsp?Method=getUsuarios"
	}).done(function(json){
		var x = 0;
		var itemHtml = "";
		itemHtml += "<li>";
		itemHtml += '<div class="B">Nome</div>';
		itemHtml += "</li>";
		while (x < json.Status.rows) {
			itemHtml += "<li>";
			itemHtml += '<input type="checkbox" id=' + x + '" value="' + json.Resultado[x].idUsuario + '">'
			itemHtml += json.Resultado[x].nome;
			itemHtml += "</li>";
			x++;
		}
		$('#lstUsuarios > ul').html(itemHtml);
	});	
}

//==============================

function editUsuario(){
	if( $('#lstUsuarios input[type="checkbox"]:checked').size() == 0) {
		alert("É necessário selecionar ao menos um item.");
		return false;
	}
	else if($('#lstUsuarios input[type="checkbox"]:checked').size() > 1){
		alert("Mais do que um item selecionado.");
		return false;
	}
	else {
		$('#lstUsuarios').fancybox({
			type: 'ajax',
			href: 'cadastro.jsp'
		});
	}
}


//==============================

function showInputKey(){
	if($('#likey').is(":visible"))
		$('#likey').hide("slow");
	else
		$('#likey').show("slow");
}

//==============================

function inputOnlyNumberValidator() {
	$('input[id^="temp"]').keyup(function(e){
		if (/\D/g.test(this.value)) {
			this.value = this.value.replace(/\D/g, '');
		}
	});
}

//==============================

$( document ).ready(function() {
	montaListaUsuario();
});