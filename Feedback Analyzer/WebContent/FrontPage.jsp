<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	
	#background{
		background-image: url(../image/log_in_back.jpg);
		overflow: hidden;
	
	}
	
	.position{
		margin-top: -150px;
		margin-left: 450px;
	}
	.position2{
		margin-top: -500px;
		margin-left: 420px;
	}
	
	.button {
		background-color: #4CAF50; /* Green */
		border: none;
		color: white;
		padding: 15px 32px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 16px;
		margin: 4px 2px;
		cursor: pointer;
	}
	.button1 {border-radius: 12px;}
	
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Log In</title>
</head>
<body id="background">
<img src="./image/log_in_back.jpg"/>
	
	<div class="position2">
		<form action="LogIn.jsp">
			<button class="button button1" href="LogIn.jsp">Create Your Own Survey</button>
		</form>
	</div>
	<div class="position">
		<form action="RespondentLogIn.jsp">
			<button class="button button1" href="#">Answer Survey</button>
		</form>
	</div>
</body>
</html>