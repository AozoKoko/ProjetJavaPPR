<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nouvelle Vente</title>
</head>
<body>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
	
	<nav class="navbar bg-light mb-2">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">
		<figure class="image is-128x128">
    		<a href="<%=request.getContextPath()%>/pageAccueil"><img src="img/logo.png" alt="logo" width="100px" class="d-inline-block align-text-top"></a>
	 	</figure>
   		</a>
   		<div>
			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page"
					href="<%=request.getContextPath()%>/pageAccueil">Enchères</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/nouvelleVente">Vendre un article</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/MonProfil">Mon profil</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/pageAccueil">Deconnexion</a>
				</li>
			</ul>
		</div>
    </div>
	</nav>
	
	<tr>
		<td><img class="imgArt" src="${article.imageUrl}" alt="${article.nom}" /></td>
	</tr>
	
	<div>
	 <form name="form_compte" action="" method="get">
	 	<div class="d-flex justify-content-center">
	 		<div class="d-flex flex-column">
         	<div class="form-outline">
				<div class="row mb-5">
						<div class="col-md-5">
							<label class="form-label" for="form3Example1">Article : </label>
						</div>
							<div class="col-md-9">
								<input type="text" id="form3Example1" class="form-control"
									name="pseudo" value="${empty article?'':article.nom }" />
							</div>
						</div>
					</div>
					<div class="form-outline">
						<div class="row mb-5">
							<div class="col-md-5">
								<label class="form-label" for="form3Example1">Description : </label>
							</div>
							<div class="col-md-9">
								<input type="text" id="form3Example1" class="form-control" style="height:100px"
									name="prenom" value="${empty article?'':article.description }" />
							</div>
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