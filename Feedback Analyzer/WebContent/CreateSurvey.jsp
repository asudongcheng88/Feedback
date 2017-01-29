<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="questions.*, java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>

	fieldset{
		
  		border: 4px solid rgb(66, 220, 244);
  		width: 600px;
  		margin:auto;
	
	}
	
</style>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/createSurvey.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script>

	
	
	
</script>
</head>
<body>


    
    	<!-- 
    	<p>&nbsp</p>
    	
    	<fieldset>
    		<legend align="center"><h2>Create Survey</h2></legend>
    		<table>
    			<tr>
    				<td>Section</td>
    				<td>:</td>
    				<td> <input type="text" name="section" /></td>
    			</tr>
    			<tr>
    				<td>Question</td>
    				<td>:</td>
    				<td><textarea name="input" rows="4" cols="50"></textarea></td>
    			</tr>
    			<tr>
    				<td><input name="action" type="submit"  id="addQuest" value="Add"/>		
    					<input name="action" type="submit" value="List"/></td>
    			</tr>
    		</table>
    	</fieldset>
    	<div id="quest-list">
    		
    	</div>
    	
    	 -->
<div class="container">
	<div class="row">
        <div class="col-sm-12">
        	<center>
	        	<p>&nbsp;</p>
	            <legend><h3><strong>Create Questionnaire</strong></h3><p>&nbsp;</p></legend>
            </center>
        </div>
        <!-- panel preview -->
        <div class="col-sm-5">
            <h4>Add question:</h4>
            <div class="panel panel-default">
                <div class="panel-body form-horizontal payment-form">
                    <div class="form-group">
                        <label for="section" class="col-sm-3 control-label">Section</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="section" name="section">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="question" class="col-sm-3 control-label">Question</label>
                        <div class="col-sm-9">
                            
                            <textarea name="question" rows="4" cols="50" class="form-control" id="question"></textarea>
                        </div>
                    </div> 
                       
                    <div class="form-group">
                        <div class="col-sm-12 text-right">
                            <button type="button" class="btn btn-default preview-add-button">
                                <span class="glyphicon glyphicon-plus"></span> Add
                            </button>
                        </div>
                    </div>
                </div>
            </div>            
        </div> <!-- / panel preview -->
        <div class="col-sm-7">
            <h4>Preview:</h4>
            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive">
                        <table class="table preview-table">
                            <thead>
                                <tr>
                                    <th>Section</th>
                                   	<th>Question</th>
                                </tr>
                            </thead>
                            <tbody></tbody> <!-- preview content goes here-->
                        </table>
                    </div>                            
                </div>
            </div>
            
            <div class="row">
                <div class="col-xs-12">
                    <hr style="border:1px dashed #dddddd;">
                    <button type="button" class="btn btn-primary btn-block">Submit all and finish</button>
                </div>                
            </div>
        </div>
	</div>
</div>

    	

	   

	
	
	
</body>
</html>