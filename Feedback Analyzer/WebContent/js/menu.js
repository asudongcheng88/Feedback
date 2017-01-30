/*----------------------create bar cahrt-----------------------------------*/

		
	
	// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['corechart']});

	// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(function(){
	
	// When the page is loaded, all the function inside this will execute 
	
	$(document).ready(function(){
		
		
		$.ajax({
			
			type:'GET',
			url:'Logout',
			success: function(id){
				
				console.log("This is id "+id);
				
				if(id === "null"){
					
					window.location = "LogIn.jsp";
				}
					
				
	
				
				
			
		
		
		
		$(document).on('click','#upload-btn', function(){
			
			window.location = "UploadResponse.jsp";
			
		})
		
		$(document).on('click','.program',function(){
			alert("hey");
			//var programSubMenu = document.getElementById('program');
			$(this).programSubMenu.style.display = 'block';
		})
		
		

		var homeDiv = document.getElementById('homeDiv');
		var progDiv = document.getElementById('progDiv');
		var compareDiv = document.getElementById('compareDiv');
		var analyzeResultDiv = document.getElementById('analyzeResultDiv');
		
		
		var visualContainer = document.getElementById('visualization');
		var compare_visualContainer_1 = document.getElementById('compare-visualization-1');
		var compare_visualContainer_2 = document.getElementById('compare-visualization-2');
		
		var twoProgram = document.getElementById('twoProgramDiv');
		var programSelectionDiv = document.getElementById('selectCompareDiv');
		
		
		
		
		
	/********************************NAVIGATION MENU PART**************************************************/
		
		/*
			*this ajax goes to ProgramListServlet
			*this ajax will return list of program from database into sub menu for program
		*/
		
		
		
			$.ajax({
				type:'GET',
				url: 'ProgList',		//go to ProgListServlet
				hearders:{
					Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
				},
				success: function(progList){		//progList array is returned from the method selectAllProg
					
					var progList = $.parseJSON(progList);
					var li1=[];
					var li2=[];
					$subMenu1 = $('.sub1');	//get the sub menu class for PROGRAM
					$subMenu2 = $('.sub2');	//get the sub menu class for ANALYZE
					
					//push data in sub menu for program
					
					for(var i=0; i<progList.length; i++){
						li1.push('<a class="listProgMenu" href="#">' + progList[i].progName + '</a>');
						
					}
					$($subMenu1).append($(li1.join('')));
					
					//push data in sub menu for analyze
					
					for(var i=0; i<progList.length; i++){
						
						li2.push('<a class="listAnalyzeMenu" href="#">' + progList[i].progName + '</a>');
						
					}

					$($subMenu2).append($(li2.join('')));
					
				}
				
				
			})
			
			
			$(document).on('click', '#program', function(){
				
				
				$.ajax({
					
					type:'GET',
					url:'Create',
					data:{
						action :"check program"
					},
					hearders:{
						Accept: "application/json; charset=utf-8",
						"Content-type" : "application/json; charset=utf-8"
					},
					success: function(result){
						
						var exist = $.parseJSON(result);
						
						if(exist == false){
							
							alert("You not create any program yet");
							
						}
						
					}
				})
				
			});
			
			
			$(document).on('click', '#analyze', function(){
				
				
				$.ajax({
					
					type:'GET',
					url:'Create',
					data:{
						action :"check program"
					},
					hearders:{
						Accept: "application/json; charset=utf-8",
						"Content-type" : "application/json; charset=utf-8"
					},
					success: function(result){
						
						var exist = $.parseJSON(result);
						
						if(exist == false){
							
							alert("You have no program to analyze");
							
						}
						
					}
				})
				
			});
			
			
			
	/****************************************PROGRAM PART******************************************************/		
		/*	
			*event when one of listed sub menu PROGRAM is clicked
			*show list of questions based on the PROGRAM cliked
		*/
		$(document).on('click', '.listProgMenu', function(){
			

			var progName = $(this).text();
			
			
			if(progDiv.style.display == 'none'){				//display PROGRAM blockfff
				
				homeDiv.style.display = 'none';
				progDiv.style.display = 'block';
				compareDiv.style.display = 'none';
				analyzeResultDiv.style.display = 'none';
				$('#finalResultDiv').hide();
			}
			
			$.ajax({
				type: 'GET',		//go to Program Servlet
				data:{
					progName: progName,
					action: 'list program'
				},
				url:'Create',
				hearders:{
					Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
				},
				success: function(questList){

					$table = $('#progTable');
					$table.empty();
					
					//console.log("lol "+questList);
		
					
		/*-----------------------Append PRGOGRAM NAME and PROGRAM DATE----------------------*/		
		
					var tr=[];
					tr.push('<tr class="tr-prog-table">');
					tr.push("<th style='visibility:hidden'>Id</th>");
					tr.push("<th>No</th>");
					tr.push("<th>Questions</th>");
					tr.push('</tr>');
					
					//console.log(questList[0].progDate);
			
					$('#progDate').val(questList[0].progDate);
					$('#progDivName').val(progName);
						
						
		/*------------------Question list in PROGRAM----------------------------------*/	
		
					var questList = $.parseJSON(questList);
						
						
						for(var i=0; i<questList.length; i++){
						
							tr.push('<tr>');
							tr.push("<td style='visibility:hidden'>" + questList[i].questId + "</td>");
							tr.push("<td>" + (i+1) + "</td>");
							tr.push("<td>" + questList[i].questText + "</td>");
							tr.push('</tr>');
							
						}
						
						$table.append($(tr.join('')));
				
				}
			})
		});
		
/***************************************************HOME PART**************************************************************/
		
		
/*--------------------event when HOME menu is clicked----------------------*/
		
		$('#showHomeMenu').click(function(){
			
			if(homeDiv.style.display == 'none'){			//display HOME block
				
				homeDiv.style.display = 'block';
				progDiv.style.display = 'none';
				compareDiv.style.display = 'none';
				analyzeResultDiv.style.display = 'none';
				$('#finalResultDiv').hide();
			}
			
		});
		
		
		
/**************************************ANALYZE PART***************************************/		
		
		
		/*
--------------------event when ANALYZE SUB MENU is clicked----------------------------------

			* when user first click, it already gives overall result
		*/
		
		//$(document).on('click', '#navmenu .sub2 .listAnalyzeMenu', function() {
		$(document).on('click', '.listAnalyzeMenu', function(){
			var progName = $(this).text();
			
			//console.log(progName);
			
			if(analyzeResultDiv.style.display == 'none'){					//display ANALYZE block
				
				homeDiv.style.display = 'none';
				progDiv.style.display = 'none';
				compareDiv.style.display = 'none';
				analyzeResultDiv.style.display = 'block';
				$('#finalResultDiv').hide();
			}
			
			
			
			$menuUl = $('.menuUl');
			$selections = $('#selections');
			$sign = $('#sign');
			$section = $('#section');
			$noResult = $(".noResult");	
			$table = $('.resultTable');
			$orderList = $('.questOl');
			
			$orderList.empty();			//delete existing data
			$menuUl.empty();
			$selections.empty();
			$sign.empty();
			$section.empty();
			$noResult.empty();
			$table.empty();
			
			
			$.ajax({
				
				type:'GET',
				data:{
					analyzeProgName: progName,     //send selected PROGRAM NAME from ANALYZE SUB MENU to servlet
					action: 'Overall result'
				},
				url:'Analyze',				//go to ANALYZE SERVLET
				hearders:{
					Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
				},
				success: function(data){

					/*
					 * the DATA can contain two or one array based on code in servlet
					 * the data will be split if it have two object	
					 * if one array, it contain empty string because no response to analyze
					 * if two array
					 * first array have analyze result for visualization
					 * second array contain list of section to put in menu div
					*/
					
					var split = data.split("%");		
					
					var analyzeResult = $.parseJSON(split[0]);		//parse the JSON object to array
					
					visualContainer.innerHTML = "";		//clear overall chart
					
					//document.getElementById('questResultChart').innerHTML = "";
					
					$('#analyzeDivName').text(progName);
					
					console.log(split[2]);
					$('#total-res').val(split[2]);

					if(analyzeResult.length !== 0){			//if the array is not empty
						
						
			/*-------------------Append Overall Result menu-------------------------------*/
						
						//var $menuUl = $('.menuUl');
						var menuUl=[];
						
						menuUl.push('<li class="bullet-li"><p><a href="#" class="overallResult">Overall result</a></p>');
						menuUl.push('<p>&nbsp</p>');
						menuUl.push('<li class="bullet-li"><p>Top 5 result based on :</p><br>');
						
						$menuUl.append($(menuUl.join('')));
						

				/*---------------------result for Overall Result--------------------------*/
					
						var tr=[];
						var resultObject={};
						
						
						
						var chartData = new google.visualization.DataTable();
						chartData.addColumn('string', 'Section');
						chartData.addColumn('number',"Lexicon points");
						//chartData.addColumn('number');
		    		  	
						chartData.addRows(analyzeResult.length + 1);

							for(var i=0; i<analyzeResult.length; i++){
								
								//alert("section name = "+analyzeResult[i].secName);
								//alert("point = "+analyzeResult[i].totalPoint);
								
								//tr.push('<tr>');
								//tr.push('<td id="resultText">' + analyzeResult[i].secName + "</td>");
								//tr.push('<td id="resultPoint">' + analyzeResult[i].totalPoint + "</td>");
								//tr.push('</tr>');
								
								var sect = analyzeResult[i].secName;
								var point = analyzeResult[i].totalPoint;
								
								
								chartData.setCell(i, 0, sect);
								chartData.setCell(i, 1, point);
								
							}
						
						
						new google.visualization.ColumnChart(visualContainer).draw(chartData,
		    	        {
		    	      		title:"Overall Result", 
		    	            width:400, height:400,
		    	            isStacked:"true",
		    	            legend:"none" }
		    	      	);
							
							
						
							//$table.append($(tr.join('')));
							
							
			/*----------------------- List of section-------------------------------------*/	
							
							//$selections = $('#selections');
							//alert("hey");
							var sec=[];
							var selSec=[];
							
							selSec.push('<select id="section" ></select>');
							$selections.append($(selSec.join('')));
							
							$section = $('#section');

							sec.push('<option selected disabled class="hideoption">Section</option>');
							
							var secList = $.parseJSON(split[1]);		//get the data from section object
							
								for(var i=0; i<secList.length; i++){			
									
									sec.push('<option>'+secList[i].secName+'</option>')
								}

							$section.append($(sec.join('')));
							
							
			 /*--------------------SIGN selection--------------------------------*/			
							
							//$sign = $('#sign');
					
							var sel=[];
							sel.push('<select id="sign">');
							sel.push('<option>Positive</option>');
							sel.push('<option>Negative</option>');
							sel.push('</select>');

							$selections.append($(sel.join('')));
							
							
			/*----------------If the there is NO RESPONSE yet---------------------*/
				
					}else{								//array is empty
						
						//$(".noResult").empty();			//delete current append in "noResult Div" before do a new one  
						//$('.resultTable').empty();
						var para=[];
						para.push("Your survey has not been answered yet");
						para.push("You can check it later");
						
						for(var i=0; i<para.length;i++ ){
							$('.noResult').append('<p class="noResultText">' + para[i] + '</p>');
						}
					}	
				}
			})
			
			
		});	
		
		
/*--------------------event when OVERALL RESULT is clicked---------------------*/
		
		$(document).on('click', '.overallResult', function(){
			
			//console.log("overall result");
			
			$noResult = $(".noResult");
			$table = $('.resultTable');
			$orderList = $('.questOl');
			
			$orderList.empty();
			$noResult.empty();
			$table.empty();
			
			$.ajax({
				
				type:'GET',
				data:{
					//analyzeProgName: progName,
					action: "overall result menu"
				},
				url:'Analyze',
				hearders:{
					Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
				},
				success: function(data){
					
					var split = data.split("%");
					
					/*
					 *the DATA can contain two or one array based on code in servlet
					 *the data will be split if it have two object				
					*/
					
					
			/*---------------------result for Overall Result--------------------------*/
			
							
					var analyzeResult = $.parseJSON(split[0]);		//parse the JSON object to array
					
					visualContainer.innerHTML = "";
					
					if(analyzeResult.length != 0){			//if the array is not empty
						
						
						
						var tr=[];
						
						//$(".noResult").empty();	
						//$table = $('.resultTable');
						//$table.empty();
						
						var resultObject={};
						
						var chartData = new google.visualization.DataTable();
						chartData.addColumn('string', 'Section');
						chartData.addColumn('number',"Lexicon points");
						//chartData.addColumn('number');
		    		  	
						chartData.addRows(analyzeResult.length + 1);
	
							for(var i=0; i<analyzeResult.length; i++){
	
								//tr.push('<tr>');
								//tr.push('<td id="resultText">' + analyzeResult[i].secName + "</td>");
								//tr.push('<td id="resultPoint">' + analyzeResult[i].totalPoint + "</td>");
								//tr.push('</tr>');
								var sect = analyzeResult[i].secName;
								var point = analyzeResult[i].totalPoint;
								
								
								chartData.setCell(i, 0, sect);
								chartData.setCell(i, 1, point);
								
							}
						
						//var visualContainer = document.getElementById('visualization');
						new google.visualization.ColumnChart(visualContainer).draw(chartData,
		    	        {
		    	      		title:"Overall Result", 
		    	            width:400, height:400,
		    	            isStacked:"true",
		    	            legend:"none" }
		    	      	);
						//$table.append($(tr.join('')));
						
						
					}else{								//array is empty
						
						//$(".noResult").empty();			//delete current append in "noResult Div" before do a new one  
						//$('.resultTable').empty();
						var para=[];
						para.push("Your survey has not been answered yet");
						para.push("You can check it later");
						
						for(var i=0; i<para.length;i++ ){
							$('.noResult').append('<p class="noResultText">' + para[i] + '</p>');
						}
					}
					
				}
				
			})	
		});
		
		
/*------------------------------event when selecting QUESTION LIST------------------------*/

		$(document).on('click', '.questOl .questList', function() { 
			
			$question = $(this).text();
			$sign = $('#sign').find('option:selected').text();
			$section = $('#section').find('option:selected').text();
			
			alert("Question = "+$question);
			//alert("Sign = "+$sign);
			//alert("SEction = "+$section);
			
			
			$.ajax({
				
				type:'GET',
				data:{
					question: $question,    //send selected QUESTION from the list to servlet
					sign : $sign,			//send selected SIGN from the dropdown list to servlet
					section : $section,		//send selected SECTION from the dropdown list to servlet	
					action: 'questionResult'
				},
				url:'Analyze',				//go to ANALYZE SERVLET
				hearders:{
					Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
				},
				success: function(questAnalysisArray){
						
					//alert(questAnalysisArray);
				/*-------------Result per Question----------------*/		
				
					var questAnalyzeResult = $.parseJSON(questAnalysisArray);
				
					$noResult = $(".noResult");
					$noResult.empty();
					
					//$overallChart = $("#visualization");
					//$overallChart.empty();
					
					visualContainer.innerHTML = "";
				
					if(questAnalyzeResult.length !== 0){

						var tr=[];
						
						$table = $('.resultTable');
						$table.empty();						//delete existing data
						
						//visualize using chart.js
						/*
						var textData = [];
						var pointData = [];
							for(var i=0; i<questAnalyzeResult.length; i++){
								
								textData[i] = questAnalyzeResult[i].lexText;
								pointData[i] = questAnalyzeResult[i].totalPoint;
								
								//tr.push('<tr>');
								//tr.push('<td>' + questAnalyzeResult[i].lexText + "</td>");
								//tr.push('<td>' + questAnalyzeResult[i].totalPoint + "</td>");
								//tr.push('</tr>');
							}
							//$table.append($(tr.join('')));
							
						var questResultChart = document.getElementById('questResultChart');
						document.getElementById('questResultChart').innerHTML =radarChart(questResultChart, textData, pointData);
						*/
						
						var chartData = new google.visualization.DataTable();
						chartData.addColumn('string', 'Lexicons');
						chartData.addColumn('number',"Points");
						//chartData.addColumn('number');
		    		  	
						chartData.addRows(questAnalyzeResult.length + 1);

							for(var i=0; i<questAnalyzeResult.length; i++){
								
								//alert("section name = "+analyzeResult[i].secName);
								//alert("point = "+analyzeResult[i].totalPoint);
								
								//tr.push('<tr>');
								//tr.push('<td id="resultText">' + analyzeResult[i].secName + "</td>");
								//tr.push('<td id="resultPoint">' + analyzeResult[i].totalPoint + "</td>");
								//tr.push('</tr>');
								
								var sect = questAnalyzeResult[i].lexText;
								var point = questAnalyzeResult[i].totalPoint;
								
								
								chartData.setCell(i, 0, sect);
								chartData.setCell(i, 1, point);
								
							}
						
						
						new google.visualization.ColumnChart(visualContainer).draw(chartData,
		    	        {
		    	      		title:"Overall Result", 
		    	            width:400, height:400,
		    	            isStacked:"true",
		    	            legend:"none" }
		    	      	);
						
							
					}
					
				/*-------------NO POSITIVE/NEGATIVE value----------------*/	
				
					else{
				
						//$(".noResult").empty();			//delete current append before do a new one  
						//$('.resultTable').empty();
						var para=[];
							
						para.push("No positive/negative value");
							
							
						$('.noResult').append('<p>' + para[0] + '</p>');
						
							
					}
				}
			})	
		});
		

/*---------------------------Event when selecting SECTION----------------------*/ // ON TRIAL
 
 
 		$(document).on('change', '#section', function(){
 			//$(document).on('click', '#goButton', function(){
 				
 			//alert("hey i've been selected!!");
 				
 			var section = $('#section').find('option:selected').text();
 				
 				$.ajax({
 						
 					type:'GET',
 					data:{
 							
 						section: section,
 						action: 'section'
 						
 					},
 					url:'Analyze',
 					hearders:{
 						Accept: "application/json; charset=utf-8",
 						"Content-type" : "application/json; charset=utf-8"
 					},
 						
 					success: function(questArray){
 				
 							
 				/*------------LISTING all the QUESTION from the current pogram----------------------*/		
 			
 						$orderList = $('.questOl');
 						$orderList.empty();			//delete existing data
 						var questList = $.parseJSON(questArray);
 						var tr=[];
 								
 							for(var i=0; i<questList.length; i++){
 								tr.push('<li class="questList"><a href="#">' + questList[i].questText + '</a></li>');
 								tr.push('<p>&nbsp</p>')
 							}
 							$orderList.append($(tr.join('')));
 					}
 				})	
 				
 		});
		
/**************************************COMPARE PART*************************************/
 /*
 		function getCheckedBox() {         
			     var allVals = [];
			     $('.menuCompareRightDiv :checked').each(function() {
			       allVals.push($(this).text());
			     });
			     //$('#t').val(allVals);
			     
			     return allVals;
			  }
	*/	
		/***************event when COMPARE menu is clicked*******************/
		
		$('#showCompareMenu').click(function(){
			
			
			
			
			$.ajax({
				
				type:'GET',
				url:'Create',
				data:{
					action :"check program"
				},
				hearders:{
					Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
				},
				success: function(result){
					
					var exist = $.parseJSON(result);
					
					if(exist == true){
						
						if(compareDiv.style.display == 'none'){			//display COMPARE block
							
							homeDiv.style.display = 'none';
							progDiv.style.display = 'none';
							compareDiv.style.display = 'block';
							analyzeResultDiv.style.display = 'none';
							$('#finalResultDiv').hide();
							
						}
						
						
						programSelectionDiv.style.display = 'block';
						twoProgram.style.display = 'none';
						
						$('.compare-menuUl').empty();
						$('#compare-selections').empty();
						$('.compare-questOl').empty();
						
							
						$.ajax({
								
							type:'GET',
							url:'ProgList',
							hearders:{
								Accept: "application/json; charset=utf-8",
								"Content-type" : "application/json; charset=utf-8"
							},
							success: function(progName){
								
								var progName = $.parseJSON(progName);
								var list = [];
								
								$compareList = $('#selectCompareDiv');
								$compareList.empty();
								
								list.push('<div id="check-box">');
								list.push('<table>');
								
								for(var i=0; i<progName.length; i++){
									
									list.push('<tr>');
									list.push('<td><label><h4>'+progName[i].progName+ '</h4></label></td>');
									list.push('<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>')
									list.push('<td><input type="checkbox" class="progName" value="'+progName[i].progName+'"></td>');
									
									list.push('</tr>');
								}
								
								list.push('</table>');
								list.push('<p>&nbsp</p>');
								list.push('<input id="compButton" type="submit" value="Compare" />');
								list.push('</div>');
								$compareList.append($(list.join('')));
								
								$note = $('#noteText');
								$note.empty();
								
								var note=[];
								note.push('<a><strong>Note</strong> : To compare the data, it have to be a same program.</a>');
								$note.append($(note.join('')));
								
							}
						})
						
					}else{
						
						alert("You have no program to compare");
						
					}
					
					
				}
				
			})
			
			
			
		});//close compare menu button
		

		
		/*-----------limit selecting CHECK BOX----------------------------------*/
		
		
		var $checkbox = $('.progName');

		function disableCheckbox ($checkbox) {
	
	  		var checkbox = $checkbox.filter(":checked").length,
	      	max = parseInt(1, 10);
	
	  		if(checkbox > max){ 
	  			
		  		$checkbox.prop({checked:false, disabled:false});

	  		}else{
		  		
	  			$checkbox.not(":checked").prop({disabled: checkbox >= max});
	  		
	  		}
		}
		$checkbox.on("change", disableCheckbox);
		
		
/*************************event when push Compare button*********************/
		
		
		$(document).on('click', '#compButton', function(){
			
			$compare_menuUl = $('.compare-menuUl');
			
			
			
			//$note = $('.menuCompareLeftDiv');
			//$note.empty();
			
			
			var allProgName = [];
		    
		    $('#selectCompareDiv :checked').each(function() {
		      	var value = $(this).val();
		      	
		      	allProgName.push(value);
		    	
		    });
		    
			if(allProgName.length == 2){

				programSelectionDiv.style.display = 'none';
				twoProgram.style.display = 'block';
				
				$.ajax({
					
					type:'GET',
					//dataType:'text',
					data:{
						compareProgramName: allProgName,     //send selected PROGRAM NAME from ANALYZE SUB MENU to servlet
						action: 'Overall result'
					},
					url:'Compare',				//go to ANALYZE SERVLET
					hearders:{
						Accept: "application/json; charset=utf-8",
						"Content-type" : "application/json; charset=utf-8"
					},
					success: function(data){
						
						/*
						 * the DATA can contain two or one array based on code in servlet
						 * the data will be split if it have two object	
						 * if one array, it contain empty string because no response to analyze
						 * if two array
						 * first array have analyze result for visualization
						 * second array contain list of section to put in menu div
						*/
						
						//console.log("hey");
						//console.log(data);
						
						
						
						var split = data.split("%");		
						
						var analyzeResult_1 = $.parseJSON(split[0]);		//parse the JSON object to array
						var analyzeResult_2 = $.parseJSON(split[2]);		//parse the JSON object to array
						
						compare_visualContainer_1.innerHTML = "";
						
						if(analyzeResult_1.length !== 0 && analyzeResult_2.length !== 0){			//if the array is not empty
											
							
							/*---------------------result for Overall Result--------------------------*/
	
							/*------chart for program 1--------*/
							
							var chartData_1 = new google.visualization.DataTable();
							chartData_1.addColumn('string', 'Lexicons');
							chartData_1.addColumn('number',"Points");
							//chartData.addColumn('number');
			    		  	
							chartData_1.addRows(analyzeResult_1.length + 1);
	
								for(var i=0; i<analyzeResult_1.length; i++){
									
									//alert("section name = "+analyzeResult[i].secName);
									//alert("point = "+analyzeResult[i].totalPoint);
									
									//tr.push('<tr>');
									//tr.push('<td id="resultText">' + analyzeResult[i].secName + "</td>");
									//tr.push('<td id="resultPoint">' + analyzeResult[i].totalPoint + "</td>");
									//tr.push('</tr>');
									
									var sect = analyzeResult_1[i].secName;
									var point = analyzeResult_1[i].totalPoint;
									
									
									chartData_1.setCell(i, 0, sect);
									chartData_1.setCell(i, 1, point);
									
								}
							
							var title1 = "Overall Result for "+ allProgName[0];
							
							new google.visualization.ColumnChart(compare_visualContainer_1).draw(chartData_1,
			    	        {
			    	      		title:title1, 
			    	            width:525, height:400,
			    	            isStacked:"true",
			    	            legend:"none" }
			    	      	);
								
								
							
							/*-------chart program 2--------*/
								
							
							var chartData_2 = new google.visualization.DataTable();
							chartData_2.addColumn('string', 'Lexicons');
							chartData_2.addColumn('number',"Points");
							//chartData.addColumn('number');
			    		  	
							chartData_2.addRows(analyzeResult_2.length + 1);
	
								for(var i=0; i<analyzeResult_2.length; i++){
									
									//alert("section name = "+analyzeResult[i].secName);
									//alert("point = "+analyzeResult[i].totalPoint);
									
									//tr.push('<tr>');
									//tr.push('<td id="resultText">' + analyzeResult[i].secName + "</td>");
									//tr.push('<td id="resultPoint">' + analyzeResult[i].totalPoint + "</td>");
									//tr.push('</tr>');
									
									var sect = analyzeResult_2[i].secName;
									var point = analyzeResult_2[i].totalPoint;
									
									
									chartData_2.setCell(i, 0, sect);
									chartData_2.setCell(i, 1, point);
									
								}
							
							var title2 = "Overall Result for "+ allProgName[1];
							
							new google.visualization.ColumnChart(compare_visualContainer_2).draw(chartData_2,
			    	        {
			    	      		title: title2, 
			    	            width:525, height:400,
			    	            isStacked:"true",
			    	            legend:"none" }
			    	      	);
							
							
							/*-------------------Append Overall Result menu-------------------------------*/
									
				
							$('#noteText').empty();
				
									//var $menuUl = $('.menuUl');
							var menuUl=[];
									
							menuUl.push('<li class="bullet-li"><p><a href="#" class="overallResult">Overall result</a></p>');
							menuUl.push('<p>&nbsp</p>');
							menuUl.push('<li class="bullet-li"><p>Top 5 result based on :</p><br>');
									
							$compare_menuUl.append($(menuUl.join('')));
							
							
							
							/*----------------------- List of section-------------------------------------*/	
							
							//console.log("this is = " +split[0]);
							
							$compare_selections = $('#compare-selections');
							$compare_selections.empty();
							//alert("hey");
							var sec=[];
							var selSec=[];
							
							selSec.push('<select id="compare-section" ></select>');
							$compare_selections.append($(selSec.join('')));
							
							$compare_section = $('#compare-section');
	
							sec.push('<option selected disabled class="hideoption">Section</option>');
							
							var secList = $.parseJSON(split[1]);		//get the data from section object
							
								for(var i=0; i<secList.length; i++){			
									
									sec.push('<option>'+secList[i].secName+'</option>')
								}
	
							$compare_section.append($(sec.join('')));
							
							
							/*--------------------SIGN selection--------------------------------*/			
							
							//$sign = $('#sign');
					
							var sel=[];
							sel.push('<select id="compare-sign">');
							sel.push('<option>Positive</option>');
							sel.push('<option>Negative</option>');
							sel.push('</select>');
	
							$compare_selections.append($(sel.join('')));
							
							
							/*----------------If the there is NO RESPONSE yet---------------------*/
							
						}else{								//array is empty
							
							//$(".noResult").empty();			//delete current append in "noResult Div" before do a new one  
							//$('.compare_resultTable').empty();
							var para=[];
							para.push("One/Both of your selected survey has not been answered yet");
							para.push("You can check it later");
							
							for(var i=0; i<para.length;i++ ){
								$('.compare-noResult-1').append('<p class="compare-noResultText">' + para[i] + '</p>');
								$('.compare-noResult-2').append('<p class="compare-noResultText">' + para[i] + '</p>');
							}
						}	
	
					}
				
				})
		    
			}else{
				alert("You can only compare two program");
			}
			
		});//close push compare button
		
		
/*---------------------------Event when selecting SECTION----------------------*/ 
		
		
		$(document).on('change', '#compare-section', function(){
 			//$(document).on('click', '#goButton', function(){
 				
 			var section = $('#compare-section').find('option:selected').text();
 				
 				$.ajax({
 						
 					type:'GET',
 					data:{
 							
 						section: section,
 						action: 'section'
 						
 					},
 					url:'Analyze',
 					hearders:{
 						Accept: "application/json; charset=utf-8",
 						"Content-type" : "application/json; charset=utf-8"
 					},
 						
 					success: function(questArray){
 				
 							
 				/*------------LISTING all the QUESTION from the current pogram----------------------*/		
 			
 						$orderList = $('.compare-questOl');
 						$orderList.empty();			//delete existing data
 						var questList = $.parseJSON(questArray);
 						var tr=[];
 								
 							for(var i=0; i<questList.length; i++){
 								tr.push('<li class="compare-questList"><a href="#">' + questList[i].questText + '</a></li>');
 								tr.push('<p>&nbsp</p>')
 							}
 							$orderList.append($(tr.join('')));
 					}
 				})	
 				
 		});
 			
 			
/*------------------------------event when selecting QUESTION LIST------------------------*/

		$(document).on('click', '.compare-questOl .compare-questList', function() { 
			
			$question = $(this).text();
			$sign = $('#compare-sign').find('option:selected').text();
			$section = $('#compare-section').find('option:selected').text();
			
			//alert("Question = "+$question);
			//alert("Sign = "+$sign);
			//alert("SEction = "+$section);
			
			
			$.ajax({
				
				type:'GET',
				data:{
					question: $question,    //send selected QUESTION from the list to servlet
					sign : $sign,			//send selected SIGN from the dropdown list to servlet
					section : $section,		//send selected SECTION from the dropdown list to servlet	
					action: 'questionResult'
				},
				url:'Compare',				//go to ANALYZE SERVLET
				hearders:{
					Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
				},
				success: function(bigData){
						

				/*-------------Result per Question----------------*/	
				
					var split = bigData.split("%");
				
					var questAnalyzeResult_1 = $.parseJSON(split[0]);
					var questAnalyzeResult_2 = $.parseJSON(split[1]);
				
					$noResult = $(".compare-noResult-1");
					$noResult.empty();
					

					
					compare_visualContainer_1.innerHTML = "";
					compare_visualContainer_2.innerHTML = "";
					
					$('.compare-noResult-1').empty();
					$('.compare-noResult-2').empty();
				
					if(questAnalyzeResult_1.length !== 0 && questAnalyzeResult_2.length !== 0){

						
						
						//visualize using chart.js
						/*
						var textData = [];
						var pointData = [];
							for(var i=0; i<questAnalyzeResult.length; i++){
								
								textData[i] = questAnalyzeResult[i].lexText;
								pointData[i] = questAnalyzeResult[i].totalPoint;
								
								//tr.push('<tr>');
								//tr.push('<td>' + questAnalyzeResult[i].lexText + "</td>");
								//tr.push('<td>' + questAnalyzeResult[i].totalPoint + "</td>");
								//tr.push('</tr>');
							}
							//$table.append($(tr.join('')));
							
						var questResultChart = document.getElementById('questResultChart');
						document.getElementById('questResultChart').innerHTML =radarChart(questResultChart, textData, pointData);
						*/
						
						//for program 1
						
						var chartData_1 = new google.visualization.DataTable();
						chartData_1.addColumn('string', 'Lexicons');
						chartData_1.addColumn('number',"Points");
						//chartData.addColumn('number');
		    		  	
						chartData_1.addRows(questAnalyzeResult_1.length + 1);

							for(var i=0; i<questAnalyzeResult_1.length; i++){
								
								var sect = questAnalyzeResult_1[i].lexText;
								var point = questAnalyzeResult_1[i].totalPoint;
								
								
								chartData_1.setCell(i, 0, sect);
								chartData_1.setCell(i, 1, point);
								
							}
						
						var option =  {
			    	      		'title':"Overall Result", 
			    	            'width':400, 
			    	            'height':400,
			    	            isStacked:"true",
			    	            legend:"none" };
						
						new google.visualization.ColumnChart(compare_visualContainer_1).draw(chartData_1,option);
						
						
						//for program 2
						
						
						var chartData_2 = new google.visualization.DataTable();
						chartData_2.addColumn('string', 'Lexicons');
						chartData_2.addColumn('number',"Points");
						//chartData.addColumn('number');
		    		  	
						chartData_2.addRows(questAnalyzeResult_2.length + 1);

							for(var i=0; i<questAnalyzeResult_2.length; i++){

								var sect = questAnalyzeResult_2[i].lexText;
								var point = questAnalyzeResult_2[i].totalPoint;
								
								
								chartData_2.setCell(i, 0, sect);
								chartData_2.setCell(i, 1, point);
								
							}
						
						var option =  {
			    	      		'title':"Overall Result", 
			    	            'width':400, 
			    	            'height':400,
			    	            isStacked:"true",
			    	            legend:"none" };
						
						new google.visualization.ColumnChart(compare_visualContainer_2).draw(chartData_2,option);
						
							
					}else if(questAnalyzeResult_1.length !== 0 && questAnalyzeResult_2.length == 0){
						
						//program 1 not empty and program 2 is empty
						
						var chartData_1 = new google.visualization.DataTable();
						chartData_1.addColumn('string', 'Lexicons');
						chartData_1.addColumn('number',"Points");
						//chartData.addColumn('number');
		    		  	
						chartData_1.addRows(questAnalyzeResult_1.length + 1);

							for(var i=0; i<questAnalyzeResult_1.length; i++){
								
								var sect = questAnalyzeResult_1[i].lexText;
								var point = questAnalyzeResult_1[i].totalPoint;
								
								
								chartData_1.setCell(i, 0, sect);
								chartData_1.setCell(i, 1, point);
								
							}
						
						var option =  {
			    	      		'title':"Overall Result", 
			    	            'width':400, 
			    	            'height':400,
			    	            isStacked:"true",
			    	            legend:"none" };
						
						new google.visualization.ColumnChart(compare_visualContainer_1).draw(chartData_1,option);
						
						//$('#compare-visualization-2').empty();
						var para=[];
							
						para.push("No positive/negative value");
							
							
						$('.compare-noResult-2').append('<p>' + para[0] + '</p>');
						
					
					}else if(questAnalyzeResult_1.length == 0 && questAnalyzeResult_2.length ==! 0){
						
						//program 2 not empty and program 1 is empty
						
						var chartData_2 = new google.visualization.DataTable();
						chartData_2.addColumn('string', 'Lexicons');
						chartData_2.addColumn('number',"Points");
						//chartData.addColumn('number');
		    		  	
						chartData_2.addRows(questAnalyzeResult_2.length + 1);

							for(var i=0; i<questAnalyzeResult_2.length; i++){
								
								var sect = questAnalyzeResult_2[i].lexText;
								var point = questAnalyzeResult_2[i].totalPoint;
								
								
								chartData_2.setCell(i, 0, sect);
								chartData_2.setCell(i, 1, point);
								
							}
						
						var option =  {
			    	      		'title':"Overall Result", 
			    	            'width':400, 
			    	            'height':400,
			    	            isStacked:"true",
			    	            legend:"none" };
						
						new google.visualization.ColumnChart(compare_visualContainer_2).draw(chartData_2,option);
						
						//$('#compare-visualization-1').empty();
						//$('.compare-noResult-1').empty();
						
						var para=[];
							
						para.push("No positive/negative value");
							
							
						$('.compare-noResult-1').append('<p>' + para[0] + '</p>');
					
					}
					
				/*-------------NO POSITIVE/NEGATIVE value----------------*/	
				
					else{
						
						//for program 1
						

						//$('#compare-visualization-1').empty();
						//$('.compare-noResult-1').empty();
						var para=[];
							
						para.push("No positive/negative value");
							
							
						$('.compare-noResult-1').append('<p>' + para[0] + '</p>');
						
						
						//for program 2
						
						
						//$('#compare-visualization-2').empty();
						//$('.compare-noResult-2').empty();
						var para=[];
							
						para.push("No positive/negative value");
							
							
						$('.compare-noResult-2').append('<p>' + para[0] + '</p>');
							
					}
				}
			})	
		});
		
		
/************************************* RESULT *************************************/
		
		$('#result').click(function(){
		
			$.ajax({
				
				type:'GET',
				url:'Create',
				data:{
					action :"check program"
				},
				hearders:{
					Accept: "application/json; charset=utf-8",
					"Content-type" : "application/json; charset=utf-8"
				},
				success: function(result){
					
					var exist = $.parseJSON(result);
					
					if(exist == true){
						
						if(compareDiv.style.display == 'none'){			//display RESULT block
							
							homeDiv.style.display = 'none';
							progDiv.style.display = 'none';
							compareDiv.style.display = 'block';
							analyzeResultDiv.style.display = 'none';
							
						}
						
						
						programSelectionDiv.style.display = 'block';
						twoProgram.style.display = 'none';
						
						$('.compare-menuUl').empty();
						$('#compare-selections').empty();
						$('.compare-questOl').empty();
						
							
						$.ajax({
								
							type:'GET',
							url:'ProgList',
							hearders:{
								Accept: "application/json; charset=utf-8",
								"Content-type" : "application/json; charset=utf-8"
							},
							success: function(progName){
								
								var progName = $.parseJSON(progName);
								var list = [];
								
								$compareList = $('#selectCompareDiv');
								$compareList.empty();
								
								list.push('<div id="check-box">');
								list.push('<table>');
								
								for(var i=0; i<progName.length; i++){
									
									list.push('<tr>');
									list.push('<td><label><h4>'+progName[i].progName+ '</h4></label></td>');
									list.push('<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>')
									list.push('<td><input type="checkbox" class="progName" value="'+progName[i].progName+'"></td>');
									
									list.push('</tr>');
								}
								
								list.push('</table>');
								list.push('<p>&nbsp</p>');
								list.push('<input id="resultBtn" type="submit" value="Show result" />');
								list.push('</div>');
								$compareList.append($(list.join('')));
								
								$note = $('#noteText');
								$note.empty();
								
								var note=[];
								note.push('<a><strong>Note</strong> : To compare the data, it have to be a same program.</a>');
								$note.append($(note.join('')));
								
							}
						})
						
					}else{
						
						alert("You have no program to compare");
						
					}
					
					
				}
				
			})
		});
		
		$(document).on('click', '#resultBtn', function(){
		
			$('#finalResultDiv').empty();
			
			var allProgName = [];
		    
		    $('#selectCompareDiv :checked').each(function() {
		      	var value = $(this).val();
		      	
		      	allProgName.push(value);
		    	
		    });
		    
		    //console.log(allProgName);
		    
		    if(allProgName.length === 2){
		    	
		    	$.ajax({
					
					type:'GET',
					data:{
						progName: allProgName
						
					},
					url:'Result',				
					hearders:{
						Accept: "application/json; charset=utf-8",
						"Content-type" : "application/json; charset=utf-8"
					},
					success: function(data){
						
						var split = [];
						
						split = data.split("%");
						
						var result_1 = $.parseJSON(split[0]);
						var result_2 = $.parseJSON(split[1]);
						
						//console.log(result_2);
						
						$('#finalResultDiv').show();
						$('#analyzeResultDiv').hide();
						$('#compareDiv').hide();
						
						$('#finalResultDiv').append('<div id="final-result"></div>');
			
						
						
						var data = google.visualization.arrayToDataTable([
						                                                  ['Question', 'See You At Top - April 2016', 'See You At Top - October 2016'],
						                                                  ['Question 1',  result_1[0].average, result_2[0].average0],
						                                                  ['Question 2',  result_1[1].average, result_2[1].average],
						                                                  ['Question 3',  result_1[2].average, result_2[2].average],
						                                                  ['Question 4',  result_1[3].average, result_2[3].average],
						                                                  ['Question 5',  result_1[4].average, result_2[4].average]
						                                                ]);

						                                                var options = {
						                                                  title: 'Polarity of The Questions',
						                                                  hAxis: {title: 'Questions number',  titleTextStyle: {color: '#333'}},
						                                                  vAxis: {minValue: 0},
						                                                  height: 400,
						                                                  width: 700
						                                                };

						                                                var chart = new google.visualization.AreaChart(document.getElementById('final-result'));
						                                                chart.draw(data, options);
						                                            //console.log(options);
						                                                
						
					}
					
		    	})
		    	
		    	
		    }else{
		    	
		    	alert("You can only compare two program");
		    }
			
		})
		
		
		
		$(document).on('click', '#logout', function(){
		
			$.ajax({
				
				type:'POST',
				url:'Logout',
				success: function(){
					
					window.location = "LogIn.jsp";
				}
				
				
				
				
			})
		})
		
	
		
			}
		
		
		})
		
	
	});//close ready document
});//close google chart callback
