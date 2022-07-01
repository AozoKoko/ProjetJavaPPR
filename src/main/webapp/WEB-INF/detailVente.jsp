<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail vente</title>
</head>
<body>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
	
	<nav class="navbar bg-light mb-2">
	<div class="container-fluid">
	<div class="nav justify-content-start">
		<a class="navbar-brand" href="#">
		<figure class="image is-128x128">
    		<a href="<%=request.getContextPath()%>/pageAccueil"><img src="img/logo.png" alt="logo" width="100px" class="d-inline-block align-text-top"></a>
	 	</figure>
   		</a>
   	</div>
   	</div>
	</nav>
	
	<tr>
		<td><img class="imgArt" src="${empty article?'':article.imageUrl}" alt="${empty article?'':article.nom}" /></td>
	</tr>
	
	<form name="form_compte" action="" method="get">
		<div>
  		<label for="quantity">Proposition : </label>
  		<input type="number" id="quantity" style="height: 40px; width: 70px" name="quantity" min="${empty article?'':article.prixVente}" max="${empty utilisateur?'':utilisateur.credit}"><br><br>
  			<div class="col offset-md-6">		
  					<input type="submit" value="Enchérir"/>
  			</div>
		</div>
		<div>
  		<label for="quantity">Proposition : </label>
  		<input type="number" id="quantity" style="height: 40px; width: 70px" name="quantity" min="${empty article?'':article.prixVente}" max="${empty utilisateur?'':utilisateur.credit}"><br><br>
  			<div class="col offset-md-6">		
  					<input type="submit" value="Enchérir"/>
  			</div>
		</div>
		
	</form>
	
<!-- <form act


































































ion="/detailVente" method="post">
		<div class="row mb-2">
				<div class="col"></div>
					<div class="row">
						<div class="col-6">
							<div class="text-left">Description :</div>
						</div>
						<div class="col-9 mb-2">
							<div class="text-left">${empty article?'':article.description}</div>
						</div>
					</div>
				</div>
			</div>
	</form> -->	



<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
</body>
</html>