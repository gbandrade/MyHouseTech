var host = window.location.origin + "/MyHouseTech/";

//***********************************/
//*      Funcoes            	    */
//***********************************/

function montaListaDispositivos(IdUsuario){
	majax = $.ajax({
		type: "GET",
		async: "false",
		crossDomain: false,
		timeout: 9000,
		dataType: "json",
		url: host + "rest/dispositivo.jsp?Method=getDispositivosUsuario&IdUsuario=" + IdUsuario
	}).done(function(json){
		var x = 0;
		var itemHtml = "";
		
		while (x < json.Status.rows) {
			itemHtml += '<li id="disp' + x + '">';
			itemHtml += '	<div class="fLeft imgLampada">';
			itemHtml += '		<img src="images/lampOff.png" alt="lamp off" title="lamp off">';
			itemHtml += '	</div>';
			itemHtml += '	<div class="fLeft infoComponete">';
			itemHtml += '		<div>' + json.Dispositivos[x].Ip + '</div>';
			itemHtml += '		<form name="form" method="Post" action="AtualizaTemperatura">';
			itemHtml += '			<input type="hidden" value="' + json.Dispositivos[x].IdDispositivo + '" name="hidDisp">';
			itemHtml += '			<div class="fLeft">';
			itemHtml += '				<div>Temperatura minima:</div>';
			itemHtml += '				<input id="tempMin" type="text" name="tempMin" value="';
			if(json.Dispositivos[x].TempMin != null)
				itemHtml += json.Dispositivos[x].TempMin;
			itemHtml += '" maxlength="5" pattern="\\d*">';			
			itemHtml += '			</div>';
			itemHtml += '			<div class="fLeft">';
			itemHtml += '				<div>Temperatura máxima:</div>';
			itemHtml += '				<input id="tempMax" type="text" name="tempMax" value="';
			if(json.Dispositivos[x].TempMax != null)
				itemHtml += json.Dispositivos[x].TempMax;
			itemHtml += '" maxlength="5" pattern="\\d*">';			
			itemHtml += '			</div>';
			itemHtml += '			<div class="fLeft">';
			itemHtml += '				<input id="btnSalvar" type="submit" name="Submit" class="btnSalvar" value="Salvar">';
			itemHtml += '			</div>';	
			itemHtml += '		</form>';
			itemHtml += '		<div class="clear"/>';		
			itemHtml += '		<div>';
			itemHtml += '			<input id="btnLigar" type="button" class="btnLigar" value="Ligar" onclick="AlteraRele(\'disp' + x + '\', this.value,\''+ json.Dispositivos[x].Ip +'\' )">';
			itemHtml += '		</div>';
			itemHtml += '	</div>';
			itemHtml += '	<div class="clear">';
			itemHtml += '	</div>';
			itemHtml += '</li>';
			x++;
		}
		
		if(itemHtml == "")
			itemHtml += '<li id="msgNenhumDispositivo">Você não possui nenhum dispositivo ainda.</li>';
		
		$('.dispList ul').append(itemHtml);
		inputOnlyNumberValidator();
	});	
}

//***********************************/

$(document).ready(function(){
	montaListaDispositivos(getCookie("userId"));
});
