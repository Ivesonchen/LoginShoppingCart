<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Please Log In</title>
<script src="webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="webjars/bootstrap/4.4.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="./style/login.css">
</head>
<body>

	<div class="container">
		<div class="title row">
			<div class="col-2"></div>
			<div class="col-8 header">
				<span>Login</span>
			</div>
		</div>

		<div class="main row">
			<div class="login col-8 blue_border">
				<span>Login Here</span>
				<form action="LoginServlet" method="post">
					<div class="form-group">
						<label for="firstName">First Name: </label> <input type="text"
							class="form-control" id="firstName" name="firstName"
							placeholder="Enter your first name">
					</div>
					<div class="form-group">
						<label for="lastName">Last Name: </label> <input type="text"
							class="form-control" id="lastName" name="lastName"
							placeholder="Enter your last name">
					</div>
					<div class="form-group">
						<label for="password">Password: </label> <input type="password"
							class="form-control" id="password" name = "password"
							placeholder="Enter your password">
					</div>
					
					<button class="btn btn-primary" type="submit">Submit</button>
					<a href ="regist.jsp">Not a member? Regist here.</a>
				</form>
			</div>

			<div class="info col-4 green_border">
				${message}
			</div>
				
		</div>

	</div>

</body>
</html>