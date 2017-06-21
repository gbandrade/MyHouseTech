//***********************************/
//*      Area Restrita              */
//***********************************/

function AlteraRele(id, status){
    if(status == 'Ligar') {
        $('#' + id + ' img').attr('src','images/lampOn.png');
        $('#' + id + ' img').attr('alt','lamp on');
        $('#' + id + ' img').attr('title','lamp on');
        $('#' + id + ' input').attr("value", "Desligar");
        $('#' + id + ' input').attr("class", "btnDesligar");
    }
    else if (status == 'Desligar') {
        $('#' + id + ' img').attr('src','images/lampOff.png');
        $('#' + id + ' img').attr('alt','lamp off');
        $('#' + id + ' img').attr('title','lamp off');
        $('#' + id + ' input').attr("value", "Ligar");
        $('#' + id + ' input').attr("class", "btnLigar");
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
		url: "http://localhost:8080/MyHouseTech/rest/gerenciaJson.jsp?Method=getUsuarios"
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

$( document ).ready(function() {
	montaListaUsuario();
});