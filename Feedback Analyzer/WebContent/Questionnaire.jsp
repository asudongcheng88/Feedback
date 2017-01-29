<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>


	div{
	
		width:1000px;
		height:450px;
		border-style:solid;
		border-color: black;
		margin-left:250px;

	}
	
	
</style>
</head>
<body>
<body>
		
		<div>
		<table>
			<tr>
				<c:forEach items="${requestScope.questList}" var="quest"> 
				<td>${quest.questText}</td>
				</c:forEach>
			</tr>
		</table>
		</div>
		
	
</body>
</body>
</html>