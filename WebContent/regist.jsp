<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Please register as a member</title>

<script src="webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="webjars/bootstrap/4.4.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="./style/regist.css">
</head>
<body>
<div class="container-fluid full">
		<div class="title row">
			<div class="col-2"></div>
			<div class="col-8 header">
				<span>Register</span>
			</div>
		</div>

		<div class="main row">
			<div class="login col-8">
				<span>Enter your info here</span>
				<form action="RegistServlet" method="post">
					<div class="form-group">
						<label for="firstName">First Name: </label> <input type="text"
							class="form-control" id="firstName" name="firstName"
							placeholder="Enter your first name">
					</div>
					<div class="form-group">
						<label for="lastName">Last Name: </label> <input type="text"
							class="form-control" id="lastName" name = "lastName"
							placeholder="Enter your last name">
					</div>
					<div class="form-group">
						<label for="password">Password: </label> <input type="password"
							class="form-control" id="password"	name= "password"
							placeholder="Enter your password">
					</div>
					
					<button class="btn btn-outline-primary" type="submit">Submit</button>
				</form>
			</div>

			<div class="more col-4"></div>

		</div>

	</div>
</body>
</html>