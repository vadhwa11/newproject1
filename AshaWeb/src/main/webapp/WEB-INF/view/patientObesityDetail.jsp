<%@page import="org.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@include file="..//view/leftMenu.jsp" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
                        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Masters</title>

   <%@include file="..//view/commonJavaScript.jsp" %>


	<%			
		String hospitalId = "1";
		if (session.getAttribute("hospital_id") !=null)
		{
			System.out.println("hospital id is "+session.getAttribute("hospital_id"));
			hospitalId = session.getAttribute("hospital_id")+"";
		}
	%>

</head>

<script type="text/javascript" language="javascript">

$(document).ready(function()
		{
			var i = 0;
			var height = '';
			var dictionary = ${data};
			//if(dictionary.status == 1){
				for (item in dictionary) {
					i++;
					if(i == 1){
				  		for (subItem in dictionary[item]) {
					
							$('#service').val(dictionary[item][subItem].serviceNo);
							$('#patient').val(dictionary[item][subItem].patientName);
							$('#age').val(dictionary[item][subItem].age);
							$('#gender').val(dictionary[item][subItem].gender);		
							$('#header_id').val(dictionary[item][subItem].header_id);
							$('#gender_id').val(dictionary[item][subItem].gender_id);
				  		}
				   }else if(i == 2){					
						var tableData = "<tbody>";
						height = dictionary[item][subItem].height;
						for (subItem in dictionary[item]) {
							tableData += '<tr><td>'+dictionary[item][subItem].date+'</td><td>'+dictionary[item][subItem].month+'</td><td>'+dictionary[item][subItem].height+'</td><td>'+dictionary[item][subItem].weight+'</td><td>'+dictionary[item][subItem].idealWeight+'</td><td>'+dictionary[item][subItem].variation+'</td><td>'+dictionary[item][subItem].bmi+'</td></tr>';						
					  	}
						tableData += '</tbody>';					
						$('#tableId').append(tableData);
						var addNewRow = '<tr><td><div><input class="form-control" id="createdDate" type="text" value="'+formatDate()+'" readonly></div></td><td><select id="months"></select></td><td><div><input class="form-control" id="height" type="text" value="" readonly></div></td><td><div ><input class="form-control" id="weight" type="text" onblur="getVariation()"></div></td><td><div><input class="form-control" id="idealWeight" type="text" readonly></div></td><td><div><input class="form-control"  id="variant_in_weight" type="text" readonly></div></td><td><div><input class="form-control" id="bmi" type="text" readonly></div></td></tr>';
						$('#tableId').append(addNewRow);
						$j('#height').val(height);
						//months in select option
						var optionsValue = '';
						var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ];
						for(var i=0;i<monthNames.length;i++){
							optionsValue += '<option>'+monthNames[i]+'</option>'	
						}
						$('#months').append(optionsValue);
					
					}
				
				}	
/* 		}else{
			var addNewRow = '<tr><td><div><input class="form-control" id="createdDate" type="text" value="'+formatDate()+'"></div></td><td><select id="months"></select></td><td><div><input class="form-control" id="height" type="text" onblur="getIdealWeight()" required></div></td><td><div ><input class="form-control" id="weight" type="text" onblur="getVariation()" required></div></td><td><div><input class="form-control" id="idealWeight" type="text" required></div></td><td><div><input class="form-control"  id="variant_in_weight" type="text" required></div></td><td><div><input class="form-control" id="bmi" type="text" required></div></td></tr>';
			$('#tableId').append(addNewRow);
			//months in select option
			var optionsValue = '';
			var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ];
			for(var i=0;i<monthNames.length;i++){
				optionsValue += '<option>'+monthNames[i]+'</option>'	
			}
			$('#months').append(optionsValue);
		} */
			
		});
		
function getIdealWeight(){
  	var params = {
			"age" : $('#age').val(),
			"height" : $('#height').val(),
			"genderId": $('#gender_id').val()
	}

	 $j.ajax({
			type:"POST",
			contentType : "application/json",
			url: 'getIdealWeight',
			data : JSON.stringify(params),
			dataType: "json",			
			cache: false,
			success: function(data)
			{  
				//alert("success "+data.data.idealWeight);				
				$('#idealWeight').val(data.data[0].idealWeight);				
				
			},			
			error: function(data)
			{							
				
				alert("An error has occurred while contacting the server");
				
			}
	});   
	
}

	function getVariation() {
		var a = document.getElementById("idealWeight").value;
		var b = document.getElementById("weight").value;
		if(b == ''){
			return;
		}
		if (a > b) {
			var sub = a - b;
			var cal = sub * 100 / a
			var n = cal.toFixed(2);
			$('#variant_in_weight').val("-" + n);
		} else {
			var eadd = b - a;
			var cal1 = eadd * 100 / b
			var n1 = cal1.toFixed(2);
			$('#variant_in_weight').val("+" + n1);
		}
	}

	function getBMI() {
		alert("get bmi");
	}

	function formatDate() {
		var d = new Date(new Date()), month = '' + (d.getMonth() + 1), day = ''
				+ d.getDate(), year = d.getFullYear();

		if (month.length < 2)
			month = '0' + month;
		if (day.length < 2)
			day = '0' + day;

		return [day, month, year].join('-');
	}
	
	function saveObesityDetails(){
		
		var obesity_date = $('#createdDate').val();
		var month_name = $('#months').find('option:selected').text();
		var height = +($('#height').val());
		var weight = +($('#weight').val());
		var ideal_weight = +($('#idealWeight').val());
		var variation = $('#variant_in_weight').val();
		var bmi = $('#bmi').val();
		var header_id = +($('#header_id').val());
		var obesity_check = document.getElementById('obesity_check');
		if(obesity_check.checked){
			obesity_check = "y";
		}
		//alert("height "+height+" weight "+weight+" ideal_weight "+ideal_weight+" variation "+variation+" bmi "+bmi);
		if(height == 0 || height == undefined || height == ''){
			alert("Please enter valid Height");
			return;
		}else if(weight == 0 || weight == undefined || weight == ''){
			alert("Please enter valid Weight");
			return;
		}else if(ideal_weight == 0 || ideal_weight == undefined || ideal_weight ==''){
			alert("Ideal Weight must be entered");
			return;
		}
		var params = {
				"obesity_date" : obesity_date,
				"month_name" : month_name,
				"height" : height,
				"weight" : weight,
				"ideal_weight" : ideal_weight,
				"variation" : variation,
				"bmi" : bmi,
				"header_id": header_id,
				"obesity_check":obesity_check
			}
		
		 $j.ajax({
				type:"POST",
				contentType : "application/json",
				url: 'saveObesityDetails',
				data : JSON.stringify(params),
				dataType: "json",			
				cache: false,
				success: function(data)
				{  
					
					alert(data.msg);		
					window.location = "obesityWaitingJsp";
				},			
				error: function(data)
				{							
					
					alert("An error has occurred while contacting the server");
					
				}
		}); 
		
	}
	
	function getoObesityList(){
		window.location = "obesityWaitingJsp";
	}
	
</script>
 <body onload="getIdealWeight()">
                         <div class="content-page">
                                <!-- Start content -->
                                <div class="">
                                    <div class="container-fluid">

	                                        <div class="internal_Htext">Patient Obesity Detail</div>

                                        <!-- end row -->
                                            <div class="row">
                                            <div class="col-12">
                                                <div class="card">
                                                    <div class="card-body">
                                                        

                                                      <form>
                                                      
                                                      
                                                       <div class="row"> 
														    <div class="col-md-3"> 
																   
																		 <div class="form-group row">
																	    <label   class="col-sm-5 col-form-label">Service No.</label>
																	    <div class="col-sm-7">
																	        <input class="form-control form-control-sm" type="text" placeholder="" id="service" readonly>
																	    </div>
																	  </div>
															 
													          </div>	 
													          
													          
													          <div class="col-md-3"> 
																    
																		 <div class="form-group row">
																	    <label   class="col-sm-4 col-form-label">Patient Name</label>
																	    <div class="col-sm-8">
																	        <input class="form-control form-control-sm" type="text" placeholder="" id="patient" readonly>
																	    </div>
																	  </div> 
													          </div>	 
													          
													          
													          
													          <div class="col-md-3"> 
																    
																		 <div class="form-group row">
																	    <label   class="col-sm-4 col-form-label">Age</label>
																	    <div class="col-sm-8">
																	        <input class="form-control form-control-sm" type="text" placeholder="" id="age" readonly>
																	    </div>
																	  </div> 
													          </div> 
													          
													          <div class="col-md-3"> 
																    
																		 <div class="form-group row">
																	    <label   class="col-sm-4 col-form-label">Gender</label>
																	    <div class="col-sm-8">
																	        <input class="form-control form-control-sm" type="text" placeholder="" id="gender" readonly>
																	        <input type="hidden" id="header_id">
																	        <input type="hidden" id="gender_id">
																	    </div>
																	  </div> 
													          </div>
													          
													          
													          
													</div>
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                                      
                                 <!--                      <div class="form-row">

														<div class="form-group col-md-6">
															<label for="inputEmail3" class="col-sm-4 col-form-label">Service No.</label>
															<div class="col-sm-8">
																<input type="text" class="border" id="service" readonly>
															</div>
														</div>
														<div class="form-group col-md-6">
															<label for="inputEmail3" class="col-sm-4 col-form-label">Patient</label>
															<div class="col-sm-8">
																<input type="text" class="border" id="patient" readonly>
															</div>
														</div>
										
														<div class="form-group col-md-6">
															<label for="age" class="col-sm-4 col-form-label">Age</label>
															<div class="col-sm-8">
																<input type="text" class="border" id="age" readonly>
															</div>
														</div>
														<div class="form-group col-md-6">
															<label for="gender" class="col-sm-4 col-form-label">Gender</label>
															<div class="col-sm-8">
																<input type="text" class="border" id="gender" readonly>
																<input type="hidden" id="header_id">
															</div>
														</div>
										
													</div> -->
													
													
													<div class="form-row">
										
														<table class="table table-bordered" id="tableId">
															<thead>
																<tr>
																	<th>Date</th>
																	<th>Month</th>
																	<th>Height</th>
																	<th>Weight</th>
																	<th>Ideal Weight</th>
																	<th>Variation in weight</th>
																	<th>BMI</th>
																</tr>
															</thead>
														</table>
														<br>
														<br>
													</div>
													
													
													
												 <div class="row"> 
														    <div class="col-md-8"> 
																   <div class="form-group row">
																		
																		<div class="col-sm-12">
																			<input type="checkbox" id='obesity_check'> &nbsp &nbsp  Obesity monitoring not required
																		</div>
																	</div>
													          </div>	 
													          
													          
													          <div class="col-md-1"> 
																   <div class="form-group row">
																		
																		<div class="col-sm-12">
																			<button type="button" class="btn btn-primary" style="margin-top: 30px" onclick="saveObesityDetails()">Submit</button>
																		</div>
																	</div>
													          </div>	 
													          
													          
													          
													          <div class="col-md-3"> 
																  <div class="form-group row">
																			
																			<div class="col-sm-12">
																			<button type="button" class="btn btn-primary" style="margin-top: 30px" onclick="getoObesityList()">Back To Obesity List</button>
																			</div>
																		</div>
															  </div>	 
													</div>
													
													
													
													
													
													
														   
														</form>

                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
 
 
 
                                        </div>
                                      </div>
                                   </div>
 
 
 
 
 
 
 
 
 
 
 
 
 <%-- <div class="container">
		<form>
			<div class="form-row">

				<div class="form-group col-md-6">
					<label for="inputEmail3" class="col-sm-4 col-form-label">Service No.</label>
					<div class="col-sm-8">
						<input type="text" class="border" id="service" readonly>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label for="inputEmail3" class="col-sm-4 col-form-label">Patient</label>
					<div class="col-sm-8">
						<input type="text" class="border" id="patient" readonly>
					</div>
				</div>

				<div class="form-group col-md-6">
					<label for="age" class="col-sm-4 col-form-label">Age</label>
					<div class="col-sm-8">
						<input type="text" class="border" id="age" readonly>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label for="gender" class="col-sm-4 col-form-label">Gender</label>
					<div class="col-sm-8">
						<input type="text" class="border" id="gender" readonly>
						<input type="hidden" id="header_id">
					</div>
				</div>

			</div>
			<div class="form-row">

				<table class="table table-bordered" id="tableId">
					<thead>
						<tr>
							<th>Date</th>
							<th>Month</th>
							<th>Height</th>
							<th>Weight</th>
							<th>Ideal Weight</th>
							<th>Variation in weight</th>
							<th>BMI</th>
						</tr>
					</thead>
				</table>
				<br>
				<br>
			</div>
			<div class="form-row">
				<input type="checkbox" id='obesity_check'> Obesity monitoring not required<br>
				<button type="button" class="btn btn-success" style="margin-top: 30px" onclick="saveObesityDetails()">Submit</button>
				<button type="button" class="btn btn-success" style="margin-top: 30px" onclick="getoObesityList()">Back To Obesity List</button>
			</div>
		</form>
	</div> --%>
	
	
	
</body>
</html>