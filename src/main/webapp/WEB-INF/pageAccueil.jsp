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

<title>Accueil</title>
</head>
<body>
	<nav class="navbar bg-light mb-2">
		<div class="container-fluid">
			<a class="navbar-brand" href="<%=request.getContextPath()%>/login">ENI-Enchères</a>
			<div>
        		<a href="/ProjetJavaPPR/creationCompte">S'inscrire</a> - <a href="/ProjetJavaPPR/login">Se déconnecter</a>
        	</div>
		</div>
	</nav>
	
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
    				<label class for="recherche" style="height:100px"> Rechercher </label>
				</button>
			</div>
		</div>
	</div>
	<div class="form-outline">
		<div class="row mb-1">
			<div class="col-md-6">
				<i class="fas fa-search" aria-hidden="true"></i>
 					<input class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Rechercher un mot clé"aria-label="Search">
    		</div>
    	</div>
	</div>
    <br>
    <div>
    	<label class="form-label" aria-label="Default select example" for="catImput"> Catégorie  : </label>
    	<select class="form-control-sm ml-3 w-75" name="genre" id="" value="a" >
    		<option value="1"> Informatique</option>
    		<option value="0"> Ameublement</option>
    		<option value="0"> Vêtements</option>
    		<option value="0"> Sports & Loisirs</option>
    	</select>
    <br>
    </div>	
    
<div class="card mb-3" style="max-width: 540px;">
  <div class="row g-0">  
  	<c:forEach var="element"items="${articles}">
   	 <div class="col-md-4">
    	<figure class="image is-128x128">
 			<img src="https://bulma.io/images/placeholders/256x256.png" class="img-fluid rounded-start">
		</figure>
    </div>
    <div class="col-md-8">
      <div class="card-body">
         <h5 class="card-title"> ${ArticleVendu.nomArticle}</h5>
   					 <p class="card-text"> ${ArticleVendu.description}</p>
   					 <p class="card-text"> Prix : ${ArticleVendu.prixVente}</p>
   					 <p class="card-text"> Fin de l'enchère : ${ArticleVendu.dateFinEncheres}</p>
    				<p class="card-text"><small class="text-muted">Dernière mise à jour il y a  minutes</small></p> 
    				<a href="#" class="btn btn-primary">Enchérir</a>
       </div>
    </div>
   </c:forEach>
  </div>
</div>
	<br>
    </form>
    					
	
		<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
</body>
</html>