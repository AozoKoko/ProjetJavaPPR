<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nouvelle Vente</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
</head>
<body>



	<nav class="navbar bg-light mb-2">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">
				<figure class="image is-128x128">
					<a href="<%=request.getContextPath()%>/pageAccueil"><img
						src="img/logo.png" alt="logo" width="100px"
						class="d-inline-block align-text-top"></a>
				</figure>
			</a>
			<div>
				<ul class="nav justify-content-end">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="<%=request.getContextPath()%>/pageAccueil">Enchères</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/nouvelleVente">Vendre un
							article</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/MonProfil">Mon profil</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/pageAccueil">Deconnexion</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row justify-content-center mb-5">Nouvelle vente</div>
	</div>
	<div class="container">
		<form action="/ProjetJavaPPR/nouvelleVente" method="post">
			<input type="hidden" name="idArticle" />
			<div class="row">
				<div class="col-3">
					<div name="urlImage">
						<img src="${article.urlImage}" alt="image du produit a vendre"
							name="urlImage" />
					</div>
				</div>
				<div class="col-9">
					<div class="row mb-2">
						<div class="col">
							<div class="input-group">
								<span class="input-group-text">Article : </span> <input
									type="text" aria-label="article" class="form-control"
									name="nomArticle">
							</div>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col">
							<div class="input-group">
								<span class="input-group-text">Description : </span>
								<textarea class="form-control" aria-label="With textarea"
									name="descriptionArticle"></textarea>
							</div>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-4">
							<label class="form-label" aria-label="Default select example"
								for="catImput"> Catégorie : </label>
						</div>
						<div class="col-8 mb-1">
							<select class="form-control-sm ml-3 w-75" name="genre" id=""
								value="a">
								<option value=default>Toutes</option>
								<option value="1">Informatique</option>
								<option value="2">Ameublement</option>
								<option value="3">Vêtements</option>
								<option value="4">Sports & Loisirs</option>
							</select>
						</div>
					</div>
					<div class="row mb-2">
						<div class="input-group mb-3">
							<span class="input-group-text" id="basic-addon2">Url photo
								:</span> <input type="text" class="form-control"
								aria-label="Recipient's username"
								aria-describedby="basic-addon2" name="url">
						</div>
					</div>
					<div class="row mb-2">
						<div class="input-group mb-3">
							<span class="input-group-text">Prix initial :</span> <input
								type="text" class="form-control"
								aria-label="Amount (to the nearest dollar)" name="prixInit">
						</div>
					</div>
					<div class="row mb-2">
						<div class="input-group mb-3">
							<span class="input-group-text">Prix de vente:</span> <input
								type="text" class="form-control"
								aria-label="Amount (to the nearest dollar)" name="prixVente">
						</div>
					</div>
					<div class="row mb-2">
						<div class="col">
							<label for="dateDebut">Début de l'enchère :</label> <input
								type="date" id="datemin" min="2022-07-01" name="debutEnchere">
						</div>
					</div>
					<div class="row mb-2">
						<div class="col">
							<label for="dateDebut">Fin de l'enchère :</label> <input
								type="date" id="datemin" min="2022-07-01" name="finEnchere">
						</div>
					</div>
					<div class="row mb-2">
						<div class="col">
							<fieldset class="border p-2">
								<legend class="float-none w-auto"> Retrait </legend>
								<div class="input-group">
									<span class="input-group-text">Rue : </span> <input type="text"
										aria-label="article" class="form-control" name="rue"
										value="${empty profil?'':profil.rue }">
								</div>
								<div class="input-group">
									<span class="input-group-text">Code postal : </span> <input
										type="text" aria-label="article" class="form-control"
										name="codePostal"
										value="${empty profil?'':profil.codePostal }">
								</div>
								<div class="input-group">
									<span class="input-group-text">Ville : </span> <input
										type="text" aria-label="article" class="form-control"
										name="ville" value="${empty profil?'':profil.ville }">
								</div>
							</fieldset>
						</div>
					</div>
					<div class="row mb-2">
						<div class="btn-toolbar" role="toolbar"
							aria-label="Toolbar with button groups">
							<div class="btn-group me-2" role="group" aria-label="First group">
								<button type="submit" class="btn btn-secondary"
									name="newEnchere" value="save">Enregistrer</button>
							</div>
							<div class="btn-group me-2" role="group"
								aria-label="Second group">
								<button type="submit" class="btn btn-secondary"
									name="newEnchere" value="annule">Annuler</button>
							</div>
						</div>
					</div>
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