<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">

<title>Accueil</title>
</head>
<body>
	<div>
		<a href="#" class="pull-left"><img src=""></a>
	</div>

	<div class="container mt-5 mb-5">
		<c:if test="${empty modeConnecte}">
			<nav class="navbar bg-light mb-2">
				<div class="container-fluid">
					<a class="navbar-brand"
						href="<%=request.getContextPath()%>/pageAccueil">ENI-Enchères</a>
					<div>
						<ul class="nav justify-content-end">
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/creationCompte">S'inscrire</a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/login">Se connecter</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</c:if>
		<c:if test="${not empty modeConnecte}">
			<nav class="navbar bg-light mb-2">
				<div class="container-fluid">
					<a class="navbar-brand"
						href="<%=request.getContextPath()%>/pageAccueil">ENI-Enchères</a>
					<div>
						<ul class="nav justify-content-end">
							<li class="nav-item"><a class="nav-link active"
								aria-current="page"
								href="<%=request.getContextPath()%>/pageAccueil">Enchères</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/nouvelleVente">Vendre un
									article</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/modifProfil">Mon profil</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/pageAccueil">Deconnexion</a>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		</c:if>
	</div>

	<div class="container-fluid">
		<div class="row mb-4">
			<div class="text-center">Liste des enchères</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row mb-5">
			<div class="text-left">Filtres</div>
		</div>
	</div>


	<form action="/pageAccueil">
		<div class="form-outline">
			<div class="row mb-2">
				<div class="col-md-3">
					<label class="form-label" for="form3Example1"></label>
				</div>
				<div class="col-md-9">
					<button class="btn btn-outline-dark">
						<label class for="recherche" style="height: 75px">
							Rechercher </label>
					</button>
				</div>
			</div>
		</div>
		<div class="form-outline">
			<div class="row mb-1">
				<div class="col-md-6">
					<i class="fas fa-search" aria-hidden="true"></i> <input
						class="form-control form-control-sm ml-3 w-75" type="text"
						placeholder="Rechercher un mot clé" aria-label="Search">
				</div>
			</div>
		</div>
		<br>
		<div>
			<label class="form-label" aria-label="Default select example"
				for="catImput"> Catégorie : </label> <select
				class="form-control-sm ml-3 w-75" name="genre" id="" value="a">
				<option value=default>Toutes</option>
				<option value="1">Informatique</option>
				<option value="2">Ameublement</option>
				<option value="3">Vêtements</option>
				<option value="4">Sports & Loisirs</option>
			</select> <br>
		</div>

		<br>

		<c:if test="${ModeConnecte}">
			<div class="form-check">
				<input class="form-check-input" type="radio"
					name="flexRadioDefault1" id="flexRadioDefault1"> <label
					class="form-check-label" for="flexRadioDefault1"> Achats </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="checkbox" value=""
					id="flexCheckDefault" disabled> <label
					class="form-check-label" for="flexCheckedDisabled">
					Enchères ouvertes </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="checkbox" value=""
					id="flexCheckChecked" checked> <label
					class="form-check-label" for="flexCheckChecked"> Mes
					enchères </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="checkbox" value=""
					id="flexCheckDefault"> <label class="form-check-label"
					for="flexCheckDefault"> Mes enchères remportées </label>

			</div>

			<div class="col">
				<div class="form-check">
					<input class="form-check-input" type="radio"
						name="flexRadioDefault" id="flexRadioDefault1"> <label
						class="form-check-label" for="flexRadioDefault1"> Mes
						ventes </label>
				</div>

				<div class="form-check">
					<input class="form-check-input" type="checkbox" value=""
						id="flexCheckDefault"> <label class="form-check-label"
						for="flexCheckDefault"> Mes ventes en cours </label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value=""
						id="flexCheckChecked" checked> <label
						class="form-check-label" for="flexCheckChecked"> Ventes
						non débutées </label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value=""
						id="flexCheckDefault"> <label class="form-check-label"
						for="flexCheckDefault"> Ventes terminées </label>
				</div>
			</div>

		</c:if>

		<div class="card mb-3" style="max-width: 540px;">
			<div class="row g-0">
				<c:forEach var="element" items="${articles}">
					<div class="col-md-4">
						<figure class="image is-128x128">
							<img src="https://bulma.io/images/placeholders/256x256.png"
								class="img-fluid rounded-start">
						</figure>
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title">${ArticleVendu.nomArticle}</h5>
							<p class="card-text">${ArticleVendu.description}</p>
							<p class="card-text">Prix : ${ArticleVendu.prixVente}</p>
							<p class="card-text">Fin de l'enchère :
								${ArticleVendu.dateFinEncheres}</p>
							<p class="card-text">
								<small class="text-muted">Dernière mise à jour il y a
									minutes</small>
							</p>
							<a href="#" class="btn btn-primary">Enchérir</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

	</form>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
</body>
</html>