
	$(document).ready(function(){
	
	
		$('#create').click(function(){
       
       		var progCode = $('#code').val();
       		var progName = $('#name').val();
       		var progDate = $('#date').val();
       		var description = $('#desc').val();
       		
       		var split = progDate.split("-");
       		var actualDate = split[2]+"/"+split[1]+"/"+split[0];
       		
       	
       		if(progCode == "" || progName == "" || progDate== " " || description == ""){
       			
       			alert("Please fill all the field");
       			
       		}else{
       		
       			
       		       		
	       		$.ajax({
					type:'POST',
					url: 'Create',
					data:{
						code: progCode,
						name: progName,
						date: actualDate,
						description: description
					},	
					hearders:{
						Accept: "application/json; charset=utf-8",
						"Content-type" : "application/json; charset=utf-8"
					},
					success: function(){
						
						window.location = "CreateSurvey.jsp";
										
					}
					
				})	
	       		
       		
       		
       		}
       		
       		
    	});
	
	});


      
