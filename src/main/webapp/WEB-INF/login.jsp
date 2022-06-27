<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet" />
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet" />
<!-- MDB -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.2.0/mdb.min.css"
	rel="stylesheet" />
<title>Enchere - login</title>
</head>
<body>
	<div class="container-fluid">
		<form action="ProjetJavaPPR/login" method="post">
		<input type="hidden" name="idUtilisateur"
			value="${empty utlisateur?'':utilisateur.no_utilisateur }" />
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
						for="form2Example31"> Remember me </label>
				</div>
			</div>
			<div class="col">
				<!-- lien vers page modifier profil-->
				<a href="#!">Forgot password?</a>
			</div>
		</div>

		<!-- Submit button -->
		<button type="submit" class="btn btn-primary btn-block mb-4">Sign
			in</button>

		<!-- Register buttons -->
		<div class="text-center">
			<p>
				<!-- lien vers la page d'inscription en tant que nouveau memebre -->
				Not a member? <a href="#!">Register</a>
			</p>
		</div>
	</form>
	</div>
	

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.2.0/mdb.min.js"></script>
</body>
</html>