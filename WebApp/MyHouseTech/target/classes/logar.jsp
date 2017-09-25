<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="pt-br">
	<head>
	  <title>Home - My House Tech</title>
	  <meta charset="ISO-8859-1">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  <link rel="stylesheet" href="css/style.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head> 
	<body>
		<!-- Inicio Header -->
		<header id="header">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-xs-12">
					  <a class="logoSite" title="My house tech" href="logar.jsp"></a>
					</div>
				</div>
			</div>
		</header>
		<!-- Fim Header -->
		<!-- Inicio conteudo -->
		<div class="container">
			<div class="row">
				<div  class="col-md-3 hidden-xs"></div>
				<div  class="col-xs-12 col-md-6">
					<div role="containerLogin">
					<form name="form"	method="Post"	action="Login">
						<input id="login"			type="text" 		name="login" 		value="Login" 		onfocus="this.value = '';"	onblur="if (this.value == '') {this.value = 'Login';}"	class="margin30Bottom">
						<input id="password" 	type="password"	name="password" 	value="Password" 	onfocus="this.value = '';"	onblur="if (this.value == '') {this.value = 'Password';}">
						<div class="button-row">
							<input type="submit" name="Submit" 		value="Logar"		class="btnLogin">
							<input type="button" class="reset" 		value="Esqueci a senha">
							<div class="clear"></div>
						</div>
						<div class="button-row center">
						    <a href="cadastro.jsp">Quero me cadastrar</a>
						</div>
					</form>
					</div>
				</div>
				<div  class="col-md-3 hidden-xs"></div>
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