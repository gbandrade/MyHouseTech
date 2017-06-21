$(document).ready(function(){
	
//***********************************/
//*      Mascaras           	    */
//***********************************/
	
var telMask = function (val) {
	return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
	},
	spOptions = {
	  onKeyPress: function(val, e, field, options) {
	      field.mask(telMask.apply({}, arguments), options);
	    }
	};
	
$('.telefone').focus(function() {
	if ($('.telefone').val() == 'Telefone'){
		$('.telefone').val("");
		$('.telefone').mask(telMask, spOptions);
	}
});
	
$('.cpf').focus(function() {
	if ($('.cpf').val() == 'CPF'){
		$('.cpf').val("");
		$('.cpf').mask('000.000.000-00', {reverse: true});
	}
});
		

//***********************************/
//*      Validacao           	    */
//***********************************/
	
$('.email').blur(function() {
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	if (reg.test(this.value) == false) {
		//$('.email').focus();
		alert('Email inválido.');
		this.value = "E-mail";
	}
});
		
		
});

//***********************************/
//*      Funcoes            	    */
//***********************************/

function validaCampos(frm){
	var erros = 0;
	var msg = "";
	
	if(frm.nome.value == '' || frm.nome.value == 'Nome'){
		msg += "Informe um nome válido.\n"
		erros++;
	}
	if(frm.sobrenome.value == '' || frm.sobrenome.value == 'Sobrenome'){
		msg += "Informe um sobrenome válido.\n"
		erros++;
	}
	if(frm.email.value == '' || frm.email.value == 'E-mail'){
		msg += "Informe um e-mail válido.\n"
		erros++;
	}
	if(frm.cpf.value == '' || frm.cpf.value == 'CPF'){
		msg += "Informe um CPF válido.\n"
		erros++;
	}
	if(frm.endereco.value == '' || frm.endereco.value == 'Endereço'){
		msg += "Informe um endereço válido.\n"
		erros++;
	}
	if(frm.telefone.value == '' || frm.telefone.value == 'Telefone'){
		msg += "Informe um telefone válido.\n"
		erros++;
	}
	if(frm.login.value == '' || frm.login.value == 'Login'){
		msg += "Informe um login válido.\n"
		erros++;
	}
	if(frm.senha.value == ''){
		msg += "Informe uma senha válida.\n"
		erros++;
	}
	if(erros>0){
		alert(msg);
		return false;
	}
	frm.submit();
}