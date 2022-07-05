<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail vente</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar bg-light mb-2">
			<div class="container-fluid">
				<figure class="image is-128x128">
					<a href="<%=request.getContextPath()%>/pageAccueil"><img
						src="img/logo.png" alt="logo" width="100px"
						class="d-inline-block align-text-top"></a>
				</figure>
			</div>
		</nav>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Nom
				de l'article : </label>
			<div class="col-sm-10">${article.nomArticle }</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Description
				: </label>
			<div class="col-sm-10">${article.description }</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Categorie
				: </label>
			<div class="col-sm-10">${cat.libelle }</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Meilleur
				offre : </label>
			<div class="col-sm-10">${profil.email }</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Mise
				à prix : </label>
			<div class="col-sm-10">${article.miseAPrix }</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Fin
				de l'enchère : </label>
			<div class="col-sm-10">${article.dateFinEncheres }</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">retrait
				postal : </label>
			<div class="col-sm-10">${user.rue}
				<br> ${user.codePostal} <br> ${user.ville }
			</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabel" class="col-sm-2 col-form-label">Vendeur
				: </label>
			<div class="col-sm-10">${user.pseudo }</div>
		</div>
		<form name="form_compte" action="detailVente" method="post">
			<div>
				<label for="quantity">Proposition : </label> <input type="number"
					id="quantity" style="height: 40px; width: 70px" name="quantity"
					min="${empty article?'':article.prixVente}"
					max="${empty utilisateur?'':utilisateur.credit}"><br>
				<br>
				<div class="col offset-md-6">
					<a
						href="<%=request.getContextPath()%>/detailVente?param3=${enchere.noEnchere}"
						class="btn btn-primary">Enchérir</a>
				</div>
			</div>
			
			<div class="btn-group me-2" role="group" aria-label="Second group">
				<c:if test="visible">
					<button type="submit" class="btn btn-secondary"
						name="submitEnchere" value="encherir">Enchérir</button>
				</c:if>
				<c:if test="!visible">
					<button type="submit" class="btn btn-secondary" disabled
						name="submitEnchere" value="encherir">Enchérir</button>
					<p>Vous n'avez pas assez de crédits pour enchérir :(</p>
				</c:if>
			</div>
		</form>
	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
</body>
</html>