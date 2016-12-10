<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Authentification</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/bootstrap-theme.min.css" />
	<link rel="stylesheet" href="css/myStyle.css" />
</head>

<body>

	<% 
		if(session.getAttribute("idUser") != null && (Integer)session.getAttribute("idUser") != -1)	response.sendRedirect("home.jsp");
	%>

	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="account-wall"
					style="background-image: url(img/fond6.jpg)">
					<img class="profile-img" src="img/mdp.png" alt="">
						<form class="form-signin" action="LoginServlet">
							<input type="text" class="form-control" id="usernameL"
								name="usernameL" placeholder="Nom d'utilisateur/Email" required
								autofocus> <input type="password" class="form-control"
								id="passwordL" name="passwordL" placeholder="Mot de passe"
								required>
									<button class="btn btn-lg btn-primary btn-block" type="submit">
										Connexion</button> <label class="checkbox pull-left"
									style="color: #fff"> <input type="checkbox"
										value="remember-me"> Se souvenir de moi </label> <a href="#"
									class="pull-right need-help">Mot de passe oublié? </a><span
									class="clearfix"></span>
						</form>
				</div>
				<a href="#" class="text-center new-account" data-toggle="modal"
					data-target="#signUpPopUp">Créer un compte</a>
			</div>
		</div>
	</div>

	<div class="modal fade" id="signUpPopUp">
		<div class="modal-dialog">
			<div class="modal-content"
				style="background-image: url(img/fond6.jpg)">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title t">S'enregistrer</h3>
				</div>

				<div class="modal-body">
					<form role="form" action="SignupServlet">
						<div class="form-group">
							<!-- Username -->
							<label class="control-label t" for="username">Nom
								d'utilisateur</label>
							<div class="controls">
								<input type="text" id="username" name="username" placeholder=""
									class="form-control">
							</div>
						</div>

						<div class="form-group">
							<!-- Username -->
							<label class="control-label t" for="username">Tel</label>
							<div class="controls">
								<input type="text" id="tel" name="tel" placeholder=""
									class="form-control">
							</div>
						</div>

						<div class="form-group">
							<!-- E-mail -->
							<label class="control-label t" for="email">E-mail</label>
							<div class="controls">
								<input type="text" id="email" name="email" placeholder=""
									class="form-control">
							</div>
						</div>

						<div class="form-group">
							<!-- E-mail -->
							<label class="control-label t" for="email">CIN</label>
							<div class="controls">
								<input type="text" id="cin" name="cin" placeholder=""
									class="form-control">
							</div>
						</div>

						<div class="form-group">
							<!-- Password-->
							<label class="control-label t" for="password">Mot de
								passe</label>
							<div class="controls">
								<input type="password" id="password" name="password"
									placeholder="" class="form-control">
							</div>
						</div>

						<div class="modal-footer">
							<button class="btn btn-lg btn-primary btn-block" type="submit">Confirmer</button>
						</div>
					</form>
				</div>


			</div>
		</div>
	</div>


	<script src="js/jquery-2.1.4.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>