<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	#main-div{
		width: 500px;
		margin: 0 auto; 
	}
</style>
</head>
<body>
	
	<div id="main-div">
	
		<font size="24">Respondent Registration</font>
		<div id="sub-div">
			 
			 <form method="POST" action="UploadResponse" enctype="multipart/form-data" >
			 	<p>&nbsp;</p>
				File :
		            <input type="file" name="file" id="file" /> <br>
		            <br>
		    	Program Code : 
		            <input type = "text" name="upload-prog-code" /><br>
		            <br>
		            <input type="submit" value="Upload" name="upload" id="upload-btn" />        
			 </form>
		</div>
	</div>

</body>
</html>