$(document).ready(function(){
	
		function back(){
			
			document.forms["back"].submit();
		}
		
	
		
		function showQuestList(){
			alert("hello");
			$.ajax({
			
				type:'GET',
				data:{
					//analyzeProgName: progName,
					action: "List"
				},
				url:'CreateQuest',
				hearders:{
					Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
				},
				success: function(data){
					
					var quesList = $.parseJSON(data);
					
					$questListDiv = $('#quest-list');
					$questListDiv.empty();
					
					var li = [];
					
					li.push('<ol>');
					
					for(var i=0; i<questList.length; i++){
						
						li.push('<li>' + questlist[i].questText + '</li>')
						
					}
					
					li.push('</ol>');
					
					$questListDiv.append($(li.join('')));
					
					
				}
				
			})
			
			
		}
		
		
		/*-----section questionnaire------*/
		
		$(document).on('click', '.input-remove-row', function(){ 
		    var tr = $(this).closest('tr');
		    tr.fadeOut(200, function(){
		    	tr.remove();
			   
			});
		});
		
		$(function(){
		    $('.preview-add-button').click(function(){
		        var form_data = {};
		        form_data["section"] = $('.payment-form input[name="section"]').val();
		        form_data["question"] = $('.payment-form textarea[name="question"]').val();
		       
		        form_data["remove-row"] = '<span class="glyphicon glyphicon-remove"></span>';
		        var row = $('<tr class="input-quest"></tr>');
		        
		       
		        
		        $.each(form_data, function( type, value ) {
		            $('<td class="input-'+type+'"></td>').html(value).appendTo(row);
		        });
		        $('.preview-table > tbody:last').append(row); 
		        
		    });  
		});
		
		$(document).on('click', '.btn-block', function(){ 
			
			var sec = [];
			var quest = [];
			
			$("tr.input-quest").each(function() {
			       var section = $(this).find("td.input-section").text();
			       var question = $(this).find("td.input-question").text();
			       
			       sec.push(section);
			       quest.push(question);
			           
			           
			});

			
			$.ajax({
				
				type:'POST',
				data:{
					section: sec,
					question: quest,
					action: "Add"
				},
				url:'CreateQuest',
				hearders:{
					Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
				},
				success: function(){
					
					alert("Your questionnaire has been created");
					
					window.location = "Menu.html";
					
					
				}
				
				
			}) // close add ajax
			
			
			
		})
		
		
		
		
	})