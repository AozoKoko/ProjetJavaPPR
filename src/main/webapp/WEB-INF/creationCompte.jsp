<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil et creation compte</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">

</head>
<body>
	<div class="container-fluid">
		<div class="row mb-4">
			<div class="text-center">Mon profil</div>
		</div>
		<div class="row">
			<div class="col">
				<input type="hidden" name="idUtilisateur"
					value="${empty utlisateur?'':utilisateur.no_utilisateur }" />
				<div class="form-outline">
					<input type="text" id="form3Example1" class="form-control"
						name="pseudo" /> <label class="form-label" for="form3Example1">Pseudo
						: </label>
				</div>
				<div class="form-outline">
					<input type="text" id="form3Example1" class="form-control"
						name="prenom" /> <label class="form-label" for="form3Example1">Prénom
						: </label>
				</div>
				<div class="form-outline">
					<input type="tel" id="form3Example1" class="form-control"
						name="telephone" /> <label class="form-label" for="form3Example1">Téléphone
						: </label>
				</div>
				<div class="form-outline">
					<input type="number" id="form3Example1" class="form-control"
						name="codePostal" /> <label class="form-label"
						for="form3Example1">Code postal : </label>
				</div>
				<div class="form-outline">
					<input type="password" id="form3Example1" class="form-control"
						name="password" /> <label class="form-label" for="form3Example1">Password
						: </label>
				</div>
			</div>
			<div class="col">
				<div class="form-outline">
					<input type="text" id="form3Example2" class="form-control"
						name="nom" /> <label class="form-label" for="form3Example2">Nom
						:</label>
				</div>
				<div class="form-outline">
					<input type="email" id="form3Example2" class="form-control"
						name="nom" /> <label class="form-label" for="form3Example2">Email
						:</label>
				</div>
				<div class="form-outline">
					<input type="text" id="form3Example2" class="form-control"
						name="rue" /> <label class="form-label" for="form3Example2">Rue
						:</label>
				</div>
				<div class="form-outline">
					<input type="text" id="form3Example2" class="form-control"
						name="ville" /> <label class="form-label" for="form3Example2">Ville
						:</label>
				</div>
				<div class="form-outline">
					<input type="password" id="form3Example2" class="form-control"
						name="passwordConfirm" /> <label class="form-label"
						for="form3Example2">Confirmation :</label>
				</div>
			</div>
		</div>
		<div class="row mb-4">
			<div class="col">
				<button><a href="/ProjetJavaPPR/accueilModeConnecte">Creer</a></button>
			</div>
			<div class="col">
				<button><a href="/ProjetJavaPPR/accueilModeDeconnecte">Annuler</a></button>
			</div>
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>

</body>
</html>