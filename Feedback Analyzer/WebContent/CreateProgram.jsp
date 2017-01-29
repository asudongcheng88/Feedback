<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>

	#program-field{
  		border: 4px solid rgb(66, 220, 244);
  		width: 600px;
  		margin:auto;
	}
	
	.container{
		margin-top: 50px;
		float: right;
	}
	
	
	
</style>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/createProgram.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>


	<!-- 
    	<fieldset id="program-field">
    		<legend align="center"><h2>Create Program</h2></legend>
    		
    		<form action="Create" method="POST">		
    		
    		<table>
    			<tr>
    				<td>Program code</td>
    				<td><input type="text" name="code"/></td>
    			</tr>
    			<tr>
    				<td>Program name</td>
    				<td><input type="text" name="name"/></td>
    			</tr>
    			<tr>	
    				<td>Program date</td>
    				<td><input type="text" name="date"/></td>
    			</tr>
    			<tr>	
    				<td>Program description</td>
    				<td><textarea name="description" rows="4" cols="50"></textarea></td>
    			</tr>	
    			

    		</table>
    		
	       
        
    		<button name="create">Create Program</button>
		</form>
    	</fieldset>
    	
    	 -->

	<div class="container">
		<div class="row">
	        <!-- panel preview -->
	        <div class="col-sm-5" style="width:550px">
	            <h4>Program Details:</h4>
	            <div class="panel panel-default">
	            	
		                <div class="panel-body form-horizontal payment-form">
		                    <div class="form-group">
		                        <label for="concept" class="col-sm-3 control-label">Program Code</label>
		                        <div class="col-sm-9">
		                            <input type="text" class="form-control" id="code" name="code">
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label for="description" class="col-sm-3 control-label">Program Name</label>
		                        <div class="col-sm-9">
		                            <input type="text" class="form-control" id="name" name="name">
		                        </div>
		                    </div> 
		                    <div class="form-group">
		                        <label for="date" class="col-sm-3 control-label">Date</label>
		                        <div class="col-sm-9">
		                            <input type="date" class="form-control" id="date" name="date">
		                        </div>
		                    </div> 
		                    <div class="form-group">
		                        <label for="amount" class="col-sm-3 control-label">Description</label>
		                        <div class="col-sm-9">
		                        	<textarea name="description" rows="4" cols="50" class="form-control" id="desc"></textarea>
		                        </div>
		                    </div>  
		                    <div class="form-group">
		                        <div class="col-sm-12 text-right">
		                            <button type="button" class="btn btn-default preview-add-button" id="create">
		                                Create
		                            </button>
		                        </div>
		                    </div>
		                </div>
	               
	            </div>            
	        </div> 
		</div>
	</div>

    	
	
</body>
</html>