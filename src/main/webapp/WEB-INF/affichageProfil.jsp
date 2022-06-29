<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<title>Affichage profil</title>
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar bg-light mb-2">
			<div class="container-fluid">
				<a class="navbar-brand"
					href="<%=request.getContextPath()%>/pageAccueil">ENI-Enchères</a>
			</div>
		</nav>
		
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Pseudo :</label>
			<div class="col-sm-10">
				${profil.pseudo }
			</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Nom :</label>
			<div class="col-sm-10">
				${profil.nom }
			</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Prenom :</label>
			<div class="col-sm-10">
				${profil.prenom }
			</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Email :</label>
			<div class="col-sm-10">
				${profil.email }
			</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Téléphone :</label>
			<div class="col-sm-10">
				${profil.telephone }
			</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Rue :</label>
			<div class="col-sm-10">
				${profil.rue }
			</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Code postal :</label>
			<div class="col-sm-10">
				${profil.codePostal }
			</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Ville :</label>
			<div class="col-sm-10">
				${profil.ville }
			</div>
		</div>
	</div>
	<div class="row mb-3">
		<a class="btn btn-dark" href="<%=request.getContextPath()%>/modifProfil" role="button">Modifier</a>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
</body>
</html>