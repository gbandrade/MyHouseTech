<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Erro</title>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="javascript/script.js"></script>
	</head>
	<body>
		<!-- Inicio Header -->
		<header id="header">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-xs-12">
					  <a class="logoSite" title="My house tech" href="logar.jsp"></a>
					</div>
				</div>
			</div>
		</header>
		<!-- Fim Header -->
		<!-- Inicio conteudo -->
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<h2>Erro</h2>
				</div>
			</div>
			<div class="row errorList">
				<div class="col-xs-12">
					<ul>
						<li id="error">						
							Ops, a página solicitada não foi encontrada! <br/>
        					<span>Error code:</span> ${pageContext.errorData.statusCode} <br/>
						</li>
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