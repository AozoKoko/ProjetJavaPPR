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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">

<title>Accueil</title>
</head>
<body>
	<div class="container">
		<div class="row mt-2">
			<div class="col-12">
				<c:if test="${empty modeConnecte}">
					<nav class="navbar bg-light mb-2">
						<div class="container-fluid">
							<figure class="image is-128x128">
								<a href="<%=request.getContextPath()%>/pageAccueil"><img
									src="img/logo.png" alt="logo" width="80px"
									class="d-inline-block align-text-top"></a>
							</figure>
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
							<figure class="image is-128x128">
								<a href="<%=request.getContextPath()%>/pageAccueil"><img
									src="img/logo.png" alt="logo" width="80px"
									class="d-inline-block align-text-top"></a>
							</figure>
							<div>
								<ul class="nav justify-content-end">
									<li class="nav-item"><a class="nav-link active"
										aria-current="page"
										href="<%=request.getContextPath()%>/pageAccueil">Enchères</a></li>
									<li class="nav-item"><a class="nav-link"
										href="<%=request.getContextPath()%>/nouvelleVente?param1=${modeConnecte }">Vendre
											un article</a></li>
									<li class="nav-item"><a class="nav-link"
										href="<%=request.getContextPath()%>/modifProfil?param1=${modeConnecte }">Mon
											profil</a></li>
									<li class="nav-item"><a class="nav-link"
										href="<%=request.getContextPath()%>/pageAccueil">Deconnexion</a>
									</li>
								</ul>
							</div>
						</div>
					</nav>
				</c:if>
			</div>
		</div>
		<div class="row my-5">
			<div class="col" style="text-align: center;">Liste des enchères</div>
		</div>
		<form action="/pageAccueil" method="post">
			<div class="row mb-2">
				<!-- insertion formulaire de recherche action a modifier en fonction de la servlet -->
				<div class="col">
					<div class="row">
						<div class="col-4">
							<div class="text-left">Filtres :</div>
						</div>
						<div class="col-8 mb-1">
							<div class="input-group">
								<input type="search" class="form-control" placeholder="mot cle"
									aria-label="Search" aria-describedby="search-addon"
									name="search" />
							</div>
						</div>
					</div>
					<div class="row">
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
				</div>
				<div class="col">
					<div class="row">
						<div class="col offset-md-4">
							<button type="submit" class="btn btn-outline-primary"
								name="search">Recherche</button>
						</div>
					</div>
				</div>

			</div>
			<div class="row mt-5">
				<c:if test="${not empty modeConnecte}">
					<div class="col">
						<div class="form-check">
							<input class="form-check-input" type="radio"
								name="flexRadioDefault1" id="flexRadioDefault1"> <label
								class="form-check-label" for="flexRadioDefault1"> Achats
							</label>
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
			</div>
		</form>
	</div>

	<div class="container-fluid">
		<div class="row">

			<c:forEach var="articleVendu" items="${articles}">
			
					<div class="card m-2"
						style="width: 18rem; display: inline-block;">
						<div class="row g-0">
							<div class="col-md-3">
								<img src="${articleVendu.urlImage }"
									class="img-fluid h-50 rounded" alt="image du produit vendu">
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<h5 class="card-title">${articleVendu.nomArticle}</h5>
									<p class="card-text">${articleVendu.description}</p>
									<p class="card-text">Prix : ${articleVendu.prixVente}</p>
									<p class="card-text">Fin de l'enchère :
										${articleVendu.dateFinEncheres}</p>
									<a href="<%=request.getContextPath()%>/detailVente?param1=${modeConnecte }" class="btn btn-primary">Enchérir</a>
								</div>
							</div>
						</div>
					</div>
			
			</c:forEach>

		</div>
	</div>





	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
</body>
</html>