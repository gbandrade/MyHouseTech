<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.MyHouseTech.model.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<% String PERFIL_ADMINISTRADOR = "a18f9ea8068211e7bb070050568d4390";%>
		<% String PERFIL_GESTOR = "a38ca58a068211e7bb070050568d4390";%>
		<title>Área Restrita - My House Tech</title>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="javascript/fancybox/jquery.fancybox.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="javascript/fancybox/jquery.fancybox.min.js"></script>
		<script src="javascript/script.js"></script>
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
					<% if(objUsuario.getIdPerfil().equals(PERFIL_ADMINISTRADOR) || objUsuario.getIdPerfil().equals(PERFIL_GESTOR)) {%>
					<li>
						<a href="cadastro.jsp" title="Cadastro">Cadastro</a>
					</li>
					<li>
						<a href="usuarios.jsp" title="Cadastro">Usuários</a>
					</li>
					<% }%>
				</ul>
				<div class="clear"></div>
			</nav>
		</div>
		<!-- Fim Menu -->
		<!-- Inicio conteudo -->
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<h2>Usuários</h2>
				</div>
			</div>
			<div class="row dispList">
				<div id="lstUsuarios" class="col-xs-12">
					<div>
						<a data-fancybox data-type="ajax" data-src="cadastro.jsp" href="javascript:;">
							Editar
						</a>
					</div>
					<ul>
						
					</ul>
				</div>
			</div>
		</div>
		<!-- Fim conteudo -->
		<!-- Inicio footer >
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