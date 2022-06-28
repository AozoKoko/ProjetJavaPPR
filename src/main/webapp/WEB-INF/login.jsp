<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

<title>Enchere - login</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row justify-content-center align-items-center">
			<form action="ProjetJavaPPR/login" method="post" style="margin: auto; width: 300px;">
				<div class="form-outline mb-4">
					<input type="text" id="form2Example1" class="form-control"
						name="pseudo" /> <label class="form-label" for="form2Example1">Pseudo</label>
				</div>
				<div class="form-outline mb-4">
					<input type="password" id="form2Example2" class="form-control"
						name="password" /> <label class="form-label" for="form2Example2">Password</label>
				</div>
				<div class="row mb-4">
					<div class="col d-flex justify-content-center">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="form2Example31" checked /> <label class="form-check-label"
								for="form2Example31"> Se souvenir de moi </label>
						</div>
					</div>
					<div class="col">
						<!-- lien vers page modifier profil-->
						<a href="#!">Mot de passe oublié?</a>
					</div>
				</div>

				<!-- Submit button -->
				<button type="submit" class="btn btn-primary btn-block mb-4">Connectez vous</button>

				<!-- Register buttons -->
				<div class="text-center">
					<p>
						<!-- lien vers la page d'inscription en tant que nouveau memebre -->
						Pas inscrits? <a href="<%=request.getContextPath()%>/creationCompte">Inscription</a>
					</p>
				</div>
			</form>
		</div>
	</div>


	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

</body>
</html>