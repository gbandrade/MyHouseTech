<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.MyHouseTech.model.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cadastro - My House Tech</title>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="javascript/jquery.mask.min.js"></script>
		<script src="javascript/cadastro.js"></script>
	</head>
	<body>
		<!-- Inicio Header -->
		<header id="header">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-xs-12">
					  <a class="logoSite" title="My house tech" href="areaRestrita.jsp"></a>
					</div>
					<div class="col-md-4 hidden-xs userInfo">
						<form name="form"	method="Post"	action="Logout">
							Bem vindo(a) 
							<% Usuario objUsuario = (Usuario) session.getAttribute("objUser"); 
								out.print(objUsuario.getNome()); 
							%>
							- <input type="submit" name="Submit" class="btnLogout" value="Sair"/>
						</form>
					</div>
				</div>
			</div>
		</header>
		<!-- Fim Header -->
		<!-- Inicio Menu -->
		<div class="mainMenu">
			<nav class="container">
				<ul class="first fLeft">
					<li>
						<a href="Minhas informações/" title="Minhas informações">Minhas informações</a>
					</li>
					<li>
						<a href="Controle de consumo/" title="Controle de consumo">Controle de consumo</a>
					</li>
				</ul>
				<div class="clear"></div>
			</nav>
		</div>
		<!-- Fim Menu -->
		<!-- Inicio conteudo -->
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<h2>Formulário de cadastro</h2>
				</div>
			</div>
			<div class="row dispList">
				<div  class="col-md-3 hidden-xs"></div>
				<div  class="col-xs-12 col-md-12">
					<div role="containerCadastro">
					<form name="form"	method="Post"	action="Cadastro" onsubmit="validaCampos(this); return false;">
						<input id="nome"			type="text" 		name="nome" 			value="Nome" 			maxlength="50"		onfocus="if (this.value == 'Nome')			{this.value = '';}" 								onblur="if (this.value == '') {this.value = 'Nome';}"							class="margin30Bottom">
						<input id="sobrenome"	type="text" 		name="sobrenome" 		value="Sobrenome" 	maxlength="150"	onfocus="if (this.value == 'Sobrenome')	{this.value = '';}" 								onblur="if (this.value == '') {this.value = 'Sobrenome';}"					class="margin30Bottom">
						<input id="email"			type="text" 		name="email" 			value="E-mail" 		maxlength="150"	onfocus="if (this.value == 'E-mail')		{this.value = '';}" 																																class="margin30Bottom email">
						<input id="cpf"			type="text" 		name="cpf" 				value="CPF" 			maxlength="14"																														onblur="if (this.value == '') {this.value = 'CPF';}"							class="margin30Bottom cpf">
						<input id="endereco"		type="text" 		name="endereco"		value="Endereço"		maxlength="200"	onfocus="if (this.value == 'Endereço')		{this.value = '';}"								onblur="if (this.value == '') {this.value = 'Endereço';}"					class="margin30Bottom">
						<input id="telefone"		type="text" 		name="telefone"		value="Telefone"		maxlength="14"																														onblur="if (this.value == '') {this.value = 'Telefone';}"					class="margin30Bottom telefone">
						<input id="login"			type="text" 		name="login" 			value="Login" 			maxlength="100"	onfocus="if (this.value == 'Login')			{this.value = '';}" 								onblur="if (this.value == '') {this.value = 'Login';}"						class="margin30Bottom">
						<input id="senha"			type="text"			name="senha" 			value="Senha" 			maxlength="200"	onfocus="if (this.value == 'Senha')			{this.value = '';this.type='password';}"	onblur="if (this.value == '') {this.value = 'Senha';this.type='text'}"	class="margin30Bottom">
						<div   id="tipoPlano">Tipo de plano</div>
						<label class="radio-inline"><input type="radio" name="optradio" value="0">Plano Basic</label>
						<label class="radio-inline"><input type="radio" name="optradio" value="1">Plano Premium</label>
						<label class="radio-inline"><input type="radio" name="optradio" value="2">Plano Pro</label>
						<!--input id="password" 	type="text"			name="password" 	value="Password" 	onfocus="this.value = '';"  onblur="if (this.value == '') {this.value = 'Password';}"-->
						<div class="button-row">
							<input type="submit" name="Submit" value="Cadastrar"	class="btnCadastro">
							<!--input type="button" class="reset" 		value="Esqueci a senha"-->
							<div class="clear"></div>
						</div>
					</form>
					</div>
				</div>
				<div  class="col-md-3 hidden-xs"></div>
			</div>
		</div>
		<!-- Fim conteudo -->
		<!-- Inicio footer -->
		<footer id="footer">
			<div class="container">
				<div class="row">
					<p>&copy 2016 Login Form. All Rights Reserved | Design by Gabriel Andrade</p>
				</div>
			</div>
		</footer>
		<!-- Fim footer -->
	</body>
</html>