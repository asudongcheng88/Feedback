<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="admin.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>

	* {
		margin: 0px;
		padding: 0px;
		}
	body{
		
		padding:50px;
	}
	
	
	div#main{
		
		border-style:solid;
		border-color: black;
		height: 175px;
		width: 520px;
		margin-left: 532px;
		margin-top:-305px;
		z-index: 3;
		text-align:center;
		
		
	}
	div{
		position: relative;
	}
	
	

	.img1
	{
		
		position: relative;
		width:15%;
		z-index: -1;
		left: 320px;
	
	}

	.img2
	{
		
		position: relative;
		height:60%;
		width:60%;
		z-index: -2;
		top:150px;
		left:70px;
		
	}
	.but{
		z-index: 3;
		position: relative;
		left: 620px;
		top: 60px;
		background-color: #4CAF50; /* Green */
		border: none;
		border-radius: 22px;
		color: white;
		padding: 15px 32px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 20px;
		margin: 4px 2px;
		cursor: pointer;
	}
	.button {
		
	}
	
</style>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">	
$(document).on('click', '#but', function(){
	alert("hello");
});

</script>
</head>
<body>

<%
	//Admin currentUser = (Admin) session.getAttribute("currentSessionUser");
	//String userId = String.valueOf(session.getAttribute("x"));
%>
		<div>
		<img class="img1" src="./image/home-back-1.png">
		<img class="img2"src="./image/home-back-2.png">
		
		</div>
		<form action="CreateProgram.jsp">
			<button class="but" type="submit">Create Survey</button>
			<div id="main">
				<p>Welcome</p>
			</div>
		</form>
		<input type="submit" value="Button" id="but" />
	
</body>
</html>