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
	<nav class="navbar bg-light">
		<div class="container-fluid">
			<figure class="image is-128x128">
				<a href="<%=request.getContextPath()%>/pageAccueil"><img
					src="img/logo.png" alt="logo" width="80px"
					class="d-inline-block align-text-top"></a>
			</figure>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row mb-4">
			<div class="text-center">Mon profil</div>
		</div>
		<form action="/ProjetJavaPPR/creationCompte" method="post">
			<div class="row">
				<div class="col">
					<input type="hidden" name="idUtilisateur"
						value="${empty user?'':user.noUtilisateur }" />

					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-3">
								<label class="form-label" for="form3Example1">Pseudo : </label>
							</div>
							<div class="col-md-9">
								<input type="text" id="form3Example1" class="form-control"
									name="pseudo" pattern="[a-zA-Z0-9]+" required
									value="${empty user?'':user.pseudo }" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-3">
								<label class="form-label" for="form3Example1">Prénom : </label>
							</div>
							<div class="col-md-9">
								<input type="text" id="form3Example1" class="form-control"
									name="prenom" pattern="[a-zA-Z0-9]+" required
									value="${empty user?'':user.prenom }" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-3">
								<label class="form-label" for="form3Example1">Téléphone
									: </label>
							</div>
							<div class="col-md-9">
								<input type="tel" id="form3Example1" class="form-control"
									name="telephone"
									pattern="[0-9]{2}-[0-9]{2}-[0-9]{2}-[0-9]{2}-[0-9]{2}" required
									value="${empty user?'':user.telephone }" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-3">
								<label class="form-label" for="form3Example1">Code
									postal : </label>
							</div>
							<div class="col-md-9">
								<input type="tel" id="form3Example1" class="form-control"
									name="codePostal" value="${empty user?'':user.codePostal }"
									required />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-3">
								<label class="form-label" for="form3Example1">Password :
								</label>
							</div>
							<div class="col-md-9">
								<input type="password" id="form3Example1" class="form-control"
									name="password" required />
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-3">
								<label class="form-label" for="form3Example1">Nom : </label>
							</div>
							<div class="col-md-9">
								<input type="text" id="form3Example1" class="form-control"
									name="nom" value="${empty user?'':user.nom }" required />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-3">
								<label class="form-label" for="form3Example1">Email : </label>
							</div>
							<div class="col-md-9">
								<input type="email" id="form3Example1" class="form-control"
									name="email" value="${empty user?'':user.email }" required />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-3">
								<label class="form-label" for="form3Example1">Rue : </label>
							</div>
							<div class="col-md-9">
								<input type="text" id="form3Example1" class="form-control"
									name="rue" value="${empty user?'':user.rue }" required />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-3">
								<label class="form-label" for="form3Example1">Ville : </label>
							</div>
							<div class="col-md-9">
								<input type="text" id="form3Example1" class="form-control"
									name="ville" value="${empty user?'':user.ville }" required />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-2">
							<div class="col-md-3">
								<label class="form-label" for="form3Example1">Confirm :
								</label>
							</div>
							<div class="col-md-9">
								<input type="password" id="form3Example1" class="form-control"
									name="passwordConfirm" required />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row mb-4">
				<div class="col text-center">
					<button type="submit" class="btn btn-outline-dark">Creer</button>
				</div>
				<div class="col text-center">
					<button class="btn btn-outline-dark">
						<a href="/ProjetJavaPPR/login">Annuler</a>
					</button>
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