<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
	#main-div{
		text-align:center;
	}
	#sub-div{
		width: 500px;
		height: 200px;
		text-align: center;
		background-color: rgba(52, 73, 94, 0.7);
		border-radius: 4px;
		margin:0 auto;
		margin-top: 100px;

	}

	form{
		width: 500px;
		height: 400px;
		text-align: center;
	
		border-radius: 4px;
		margin:0 auto;
		margin-top: 80px;
	}
	
	table{
		margin-left: 100px;
	}
	
	.hideoption { 
		display:none; visibility:hidden; height:0; font-size:0; 		/* for program id - hidden selected program id option*/
	}
	
	
</style>

<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">	

/*--------------------------UPLOAD EXCEL FILE-------------------------------------------------*/
/*
	function performAjaxSubmit() {
		
		var select = $('.opt-prog-id').find('option:selected').text();
		var inputFile = $('[type=file]').val();
		
		
		if(inputFile === ""){
			
			alert("Select file to upload");
			
		}else if(select === "SELECT"){
			
			alert("Please choose Program id");
			
		}else{
			
			
			var progId = $('.opt-prog-id').find('option:selected').text();	
			var selectedFile = document.getElementById("input-file").files[0];

			var formdata = new FormData();
			
			formdata.append("progId", progId);
			
			formdata.append("selectedFile", selectedFile);
			
			var xhr = new XMLHttpRequest();       
			
			xhr.open("POST","Respondent", true);
			
			xhr.send(formdata);
			
			xhr.onload = function(e) {
			
			if (this.status == 200) {
			
				alert(this.responseText);
			    window.location='Menu.html'
			 	}

		    };	
		}

	                     
	}
	
	*/
	
	
	
	$(document).on('change', '.opt-prog-id', function(){
			
		var selectProgId = $('.opt-prog-id').find('option:selected').text();
						
		$.ajax({
						
			type:'GET',
			data:{
							
				progId: selectProgId,
				action: 'program name list'
						
			},
			url:'ListForRespondent',
			hearders:{
				Accept: "application/json; charset=utf-8",
				"Content-type" : "application/json; charset=utf-8"
			},
			success: function(progName){
				
				var progName = $.parseJSON(progName);
	
				$showProgName = $('.show-prog-name');
				$showProgName.empty();
						
				var tr = [];
						
					tr.push('<td>Program Name : </td>');
					tr.push('<td><textarea>' + progName + '</textarea></td>');

					$showProgName.append($(tr.join('')));
			}
		})	
		
		/*
		$(document).on('click','#reg-res',function(){
			
			var select = $('.opt-prog-id').find('option:selected').text();
			var inputFile = $('[type=file]').val();
			
			
			if(inputFile === ""){
				
				alert("Select file to upload");
				
			}else if(select === "SELECT"){
				
				alert("Please choose Program id");
				
			}else{
			
				var progId = $('.opt-prog-id').find('option:selected').text();	
				var selectedFile = document.getElementById("input-file").files[0];

				var formdata = new FormData();
				
				formdata.append("progId", progId);
				
				formdata.append("selectedFile", selectedFile);
				
				var xhr = new XMLHttpRequest();       
				
				xhr.open("POST","Upload", true);
				
				xhr.send(formdata);
				
				xhr.onload = function(e) {
				
				if (this.status == 200) {
				
					alert(this.responseText);
				    window.location='Menu.html'
				 	}

			    };	
			}
			
		})
		
		*/
		
	});
	
</script>

</head>
<body>
	<div id="main-div">
	
		<font size="24">Respondent Registration</font>
		<div id="sub-div">
		<!-- enctype="multipart/form-data" -->
		<!-- 
			<form method="POST" action="Upload" enctype="multipart/form-data" >
				<table>
					<tr>
						<td colspan="2"><input type="file" id="file" name="file" accept="application/vnd.ms-excel" /></td>
					</tr>
					<tr>
						<td>&nbsp</td>
					</tr>
					<tr>
						<td>Program Id :</td>
						<td>
							<select class="opt-prog-id">
								 <option selected disabled class="hideoption">SELECT</option>
								 <c:forEach items="${requestScope.progIdList}" var="listId" > 
								 <option>${listId.progId}</option>
								 </c:forEach>
							</select>
						</td>
					</tr>
					<tr class="show-prog-name">
					</tr>
					<tr>
						<td colspan="2"><h5>*Program id will be used as password for the respondents.</h5></td>
					</tr>
					<tr>
						<td><input type="button" id="reg-res" value="Register" ></input></td>
					</tr>
				</table>
			</form>
			 -->
			 
			 <form method="POST" action="Upload" enctype="multipart/form-data" >
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