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
<title>Modifier mon profil</title>
</head>
<body>

	<nav class="navbar bg-light">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/pageAccueil">ENI-Enchères</a>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row mb-4">
			<div class="text-center">Mon profil</div>
		</div>
		<!-- appel a la servlet modifValeur pour enregistrer les modif et faire un update en base -->
		<form action="/ProjetJavaPPR/modifValeur" method="post">
			<div class="row">
				<div class="col">
					<input type="hidden" name="idUtilisateur"
						value="${empty user?'':user.noUtilisateur }" />

					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">Pseudo : </label>
							</div>
							<div class="col-md-6">
								<input type="text" id="form3Example1" class="form-control"
									name="pseudo" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">Prénom : </label>
							</div>
							<div class="col-md-6">
								<input type="text" id="form3Example1" class="form-control"
									name="prenom" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">Téléphone
									: </label>
							</div>
							<div class="col-md-6">
								<input type="tel" id="form3Example1" class="form-control"
									name="telephone" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">Code
									postal : </label>
							</div>
							<div class="col-md-6">
								<input type="tel" id="form3Example1" class="form-control"
									name="codePostal" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">Password
									actuel : </label>
							</div>
							<div class="col-md-6">
								<input type="password" id="form3Example1" class="form-control"
									name="password" value="${empty user?'':user.password }" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">New
									password : </label>
							</div>
							<div class="col-md-6">
								<input type="password" id="form3Example1" class="form-control"
									name="password" value="${empty user?'':user.password }" />
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">Nom : </label>
							</div>
							<div class="col-md-6">
								<input type="text" id="form3Example1" class="form-control"
									name="nom" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">Email : </label>
							</div>
							<div class="col-md-6">
								<input type="email" id="form3Example1" class="form-control"
									name="email" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">Rue : </label>
							</div>
							<div class="col-md-6">
								<input type="text" id="form3Example1" class="form-control"
									name="rue" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">Ville : </label>
							</div>
							<div class="col-md-6">
								<input type="text" id="form3Example1" class="form-control"
									name="ville" />
							</div>
						</div>
					</div>
					<!-- a voir pour ecart au dessus du password confirmation -->
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4"><label class="form-label" for="form3Example1"></label></div>
							<div class="col-md-6"><label class="form-label" for="form3Example1"></label></div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-4">
								<label class="form-label" for="form3Example1">Confirm :
								</label>
							</div>
							<div class="col-md-6">
								<input type="password" id="form3Example1" class="form-control"
									name="passwordConfirm" value="${empty user?'':user.password }" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row mb-4">
				<div class="col text-center">
					<button type="submit" class="btn btn-outline-dark" name="save">Enregistrer</button>
				</div>
				<div class="col text-center">
					<button class="btn btn-outline-dark" name="delete">Supprimer mon compte</button>
				</div>
			</div>
		</form>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>




</body>
</html>