<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Ajouter Option</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/test.css" />
</head>

<body
	style="background: url(img/fondflou.png); background-size: cover; background-repeat: no-repeat;">
	
	<% 
		if(session.getAttribute("idUser") == null || (Integer)session.getAttribute("idUser") == -1)	response.sendRedirect("index.jsp");
		String s = request.getParameter("idBallot");
		if(s == null) s = "";
	%>
	<header class='masthead'>
	<div class='brand-container'>
		<a href='#'> <span class='brand-initials'>VA</span> <span
			class='brand-name'>Voting App</span>
		</a>
	</div>
	<nav>
	<div class='nav-container'>
		<div>
			<input id='slider1' name='slider1' type='checkbox'> <label
				class='slide has-child' for='slider1'> <span class='element'>VT</span>
				<span class='name'>Voter</span>
			</label>
			<div class='child-menu'>
				<a href='voter1.jsp'>Voter</a> <a href='#'>Archives</a>
			</div>
		</div>
		<div>
			<a class='slide' href='#'> <span class='element'>VH</span> <span
				class='name'>Historique de votes</span>
			</a>
		</div>
		<div>
			<input id='slider2' name='slider2' type='checkbox'> <label
				class='slide has-child' for='slider2'> <span class='element'>GV</span>
				<span class='name'>Gerer Votes</span>
			</label>
			<div class='child-menu'>
				<a href='createVote.jsp'>Nouveau vote</a> <a href='ajouterOpt1.jsp'>Ajouter
					Options</a> <a href='finirVote.jsp'>Finir Vote</a> <a href='voirRes.jsp'>Voir Resultats</a>
			</div>
		</div>
		<div>
			<a class='slide' href='#'> <span class='element'>Qt</span> <span
				class='name'>Quitter</span>
			</a>
		</div>
	</div>
	</nav>
	<div class='social-container'>
		<span> <a class='social-roll github' href='#'></a>
		</span> <span> <a class='social-roll twitter' href='#'></a>
		</span> <span> <a class='social-roll linkedin' href='#'></a>
		</span> <span> <a class='social-roll rss' href='#'></a>
		</span>
	</div>
	</header>

	<form role="form" action="ajouterOpt2.jsp">
		<div class="form-group">
			<!-- Username -->
			<label class="col-md-4 control-label t" for="username">Id
				Vote</label>
			<div class="col-md-4 controls">
				<input type="text" id="idBallot" name="idBallot" value="<% out.print(s); %>" placeholder="<% out.print(s); %>"
					class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- Username -->
			<label class="col-md-4 control-label t" for="username">Nombre
				Options</label>
			<div class="col-md-4 controls">
				<input type="text" id="nbOpt" name="nbOpt" placeholder=""
					class="form-control">
			</div>
		</div>

		<div class="col-md-4 form-group">
			<button class="col-md-4 btn btn-lg btn-primary btn-block"
				type="submit">Confirmer</button>
		</div>
	</form>

	<script src="js/jquery-2.1.4.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>