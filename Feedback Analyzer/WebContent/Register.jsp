<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	body{
		margin 0 auto;
		background: url(./image/log_in_back.jpg);
		background-size: 100% 850px;
		background-repeat: no-repeat;
	}

	.container{
		width: 500px;
		height: 400px;
		text-align: center;
		background-color: rgba(52, 73, 94, 0.7);
		border-radius: 4px;
		margin:0 auto;
		margin-top: 150px;
				
	
	}
	input{
		height:45px;
		width: 300px;
		font-size: 18px;
		margin-bottom: 10px;
		margin-top: 50px;
		background-color: #fff;
		padding-left:30px;
	}
	.btn-login{
		padding: 15px 30px;
		color: #fff;
		border-radius: 4px;
		border: none;
		background-color: #2ECC71;
		font-size: 20px;
	}

	/* visited link */
	a:visited {
		color: green;
	}

	/* mouse over link */
	a:hover {
		color: red;
	}
	
    

</style>
</head>
<body>
	<div class="container">
		
		<form action="Admin" method="POST">
			<div class="form-input">
				<input type ="text" name="name" placeholder="Enter Username">
			</div>
			<div class="form-input">
				<input type ="text" name="id" placeholder="Enter User Id">
			</div>	
			<div class="form-input">
				<input type ="password" name="password" placeholder="Enter Password">
			</div>	
			
			
			<label> &nbsp </label>
			<label><button class="btn-login" type="submit">Register</button></label>
		</form>
		
	</div>

	
</body>
</html>