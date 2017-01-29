<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
	body {
	    background-image: url("image/survey-form-bg.jpg");
		
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
	    border-radius: 12px;
	}

</style>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		$('#submit').click(function(){
			
			var resArray = [];
			var qIdArray = [];

			$('.looping').each(function(i){
				var res = $(this).find('.resp').val();
				var qId = $(this).find('.qId').val();
				
				resArray.push(res);
				qIdArray.push(qId);
				
			});
				
				$.ajax({
					
					type:'POST',
					data:{
						res: resArray,
						qId: qIdArray,
					},
					url:'Response',
					success: function(){
						window.location='SurveyDone.jsp'
					}
				
				})
			
		});
	});
</script>
</head>
<body>
	<div class="table-responsive">  
	<center>
		<table class="table">
			<tr>
				<td><h3><strong>No</strong></h3></td>
				<td><h3><strong>Question</strong></h3></td>
				<td><h3><strong>Response</strong></h3></td>
			</tr>
			<c:forEach items="${questList}" var="quest" varStatus="status"> 		<!-- array from RespondentServlet -->
			<tr class="looping">
				<td><h4><strong><c:out value="${status.index + 1}" ></c:out></strong></h4></td>
				<td><h4><strong>${quest.questText}</strong></h4></td>
				<td><textarea class="resp" rows="5" cols="40"></textarea></td>
				<td style="visibility:hidden"><textarea class="qId" >${quest.questId}</textarea></td>
				<td style="visibility:hidden"><textarea class="secName" >${quest.secName}</textarea></td>
			</tr>
			</c:forEach>
		</table>
		
		<input class="button" id="submit" type="submit" value="Submit" />
	</center>
	</div>
	
</body>
</html>