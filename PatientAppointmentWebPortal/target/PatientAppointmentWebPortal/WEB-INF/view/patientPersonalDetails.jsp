<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@include file="..//view/leftMenu.jsp" %>
     <%@include file="..//view/commonJavaScript.jsp" %>
<!DOCTYPE html>
<html>
<head>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <meta charset="utf-8">

<meta charset="ISO-8859-1">
<title>Personal Information</title>
</head>
<body>
  <div class="content-page">    
<!-- Start content -->
		<div class="">
			<div class="container-fluid">

				<div class="internal_Htext"> Personal Information</div>

				<!-- <div class="row">
					<div class="col-12">
						<div class="page-title-box">
							<ol class="breadcrumb float-right">
								<li class="breadcrumb-item active"><a href="#">Home</a></li>
								<li class="breadcrumb-item  active"><a href="#">Master</a></li>
								<li class="breadcrumb-item active">Doctor Roaster</li>
							</ol>

							<div class="clearfix"></div>
						</div>
					</div>
				</div> -->
				<!-- end row -->

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">									
										
										 <form id="patientdetails" name="patientdetailsForm" method="post" modelAttribute="patientDetail" > 
									
									
										<!-- -------------  Patient Appointment end here -->
										 <input type="hidden" id="priorityId" name="priorityId" value="">
										 <input type="hidden" id="appointmentTypeId" name="appointmentTypeId" value="">
										 <input type="hidden" id="empService" name="empService" value="">
										 <input type="hidden"  id="tokenIds"name="tokenIds" value="">
										 <input type="hidden" id="registrationTypeId" name="registrationTypeId" value="">
										 <input type="hidden" id="visitFlag" name="visitFlag" value="">
										 <input type="hidden" id="region" name="region" value="">
		                                 <input type="hidden" id="regionId" name="regionId" value="">
										 <input type="hidden" id="religion" name="religion" value="">
		                                 <input type="hidden" id="religionId" name="religionId" value="">
										 <input type="hidden"  id="rankId" name="rankId" value="">
										 <input type="hidden" id="empServiceJoinDate" name="empServiceJoinDate" value="">
		
		                                 <input type="hidden" id="recordoffice" name="recordoffice" value="">
		                                 <input type="hidden" id="totalservice" name="totalservice" value="">
										 <input type="hidden" id="recordofficeId" name="recordofficeId" value="">
		
		                                 <input type="hidden" id="trade" name="trade" value="">
		                                 <input type="hidden" id="tradeId" name="tradeId" value="">
										
		                                  <input type="hidden" id="unitId" name="unitId" value="">
		                                 <input type="hidden" id="maritalstarus" name="maritalstarus" value="">
										 <input type="hidden" id="maritalstarusId" name="maritalstarusId" value="">
		
		
		                                 <input type="hidden" id="patientname" name="patientname" value="">
		                                 <input type="hidden" id="patientDOB" name="patientDOB" value="">
										 <input type="hidden" id="patientAddress" name="patientAddress" value="">
		
	
	                                     <input type="hidden" id="patientPincode" name="patientPincode" value="">
		                                 <input type="hidden"  id="patientRelation" name="patientRelation" value="">
										 <input type="hidden" id="patientRelationId" name="patientRelationId" value="">
		
		
		                                 <input type="hidden" id="patientAge" name="patientAge" value="">
		                                 <input type="hidden" id="patientCity" name="patientCity" value="">
										 <input type="hidden" id="patientEmail" name="patientEmail" value="">
		
		
		                                 <input type="hidden" id="patientMoblienumber" name="patientMoblienumber" value="">
		                                 <input type="hidden" id="patientState" name="patientState" value="">
										 <input type="hidden" id="patientStateId" name="patientStateId" value="">
		
		                                  <input type="hidden" id="nok1Firstname" name="nok1Firstname" value="">
		                                 <input type="hidden" id=nok1Address name="nok1Address" value="">
										 <input type="hidden" id="nok1Moblienumber" name="nok1Moblienumber" value="">
		
		                                 <input type="hidden" id="nok1Relation" name="nok1Relation" value="">
		                                 <input type="hidden" id="nok1RelationId" name="nok1RelationId" value="">
										 <input type="hidden" id="nok1Policestation" name="nok1Policestation" value="">
		
		
		                                 <input type="hidden" id="nok1Email" name="nok1Email" value="">
		                                 <input type="hidden" id="nok1pincode" name="nok1pincode" value="">
										 <input type="hidden" id="nok1Contactnumber" name="nok1Contactnumber" value="">
		
		
		                                 <input type="hidden" id="nok2Firstname" name="nok2Firstname" value="">
		                                 <input type="hidden" id="nok2Address" name="nok2Address" value="">
										 <input type="hidden" id="nok2Moblienumber" name="nok2Moblienumber" value="">
		                                 <input type="hidden" id="nok2Relation" name="nok2Relation" value="">
		                                 
		                                 <input type="hidden" id="nok2Policestation" name="nok2Policestation" value="">
										 <input type="hidden" id="nok2Email" name="nok2Email" value="">
		
	                                 	<input type="hidden" id="nok2Firstname" name="nok2Firstname" value="">
		                                 <input type="hidden" id="nok2pincode" name="nok2pincode" value="">
										 <input type="hidden" id="nok2Contactnumber" name="nok2Contactnumber" value="">
		                                 <input type="hidden" id="bloodGroup" name="bloodGroup" value="">
		                                 <input type="hidden" id="medicalCategory" name="medicalCategory" value="">
										 <input type="hidden" id="date" name="date" value="">
										 
										 <input type="hidden" id="duration" name="duration" value="">
										 <input type="hidden" id="priority" name="priority" value="">
		                                 <input type="hidden" id="checkDiv" name="checkDiv" value="">
		                                 <input type="hidden" id="tokenNo" name="tokenNo" value="">
		                                  <input type="hidden" id="uhidNo" name="uhidNo" value="">
		                                    <input type="hidden" id="empId" name="empId" value="">
		                                     <input type="hidden" id="genderId" name="genderId" value="">
		                                     <input type="hidden" id="empName" name="empName" value="">
		                                     <input type="hidden" id="gender" name="gender" value="">
									
									<div class="row">
									
									 <div class="col-md-12">
									 
									 
									 
									     
												   <div id="empDetails"> 
												   
												   
												   <div class="row">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-5 col-form-label">Service No</label>
																<div class="col-sm-7">
																	<input class="border-hide " type="text" id="sNo" name="serviceNo" value="" readonly>
																</div>
															</div>
														</div>										
														 <div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-5 col-form-label">Full Name </label>
																<div class="col-sm-7">
																	<input class="border-hide "  type="text" id="empName1" name= "empName1" value="" readonly>
																</div>
															</div>
														</div>										 
				                                         <div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-5 col-form-label">Unit/Location </label>
																<div class="col-sm-7">
																	<input class="border-hide " type="text" id="unit" value="" readonly>
																</div>
															</div>
														</div>	
														
														
														
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-5 col-form-label">Age </label>
																<div class="col-sm-7">
																<input class="border-hide "  type="text" id="age" value="" readonly>
																</div>
															</div>
														</div>	
														
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-5 col-form-label">Department </label>
																<div class="col-sm-7">
																	<input class="border-hide "  type="text" id="department" value="" readonly>
																</div>
															</div>
														</div>	
														
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-5 col-form-label">Gender </label>
																<div class="col-sm-7">
																	<input class="border-hide "  type="text" id="gender1" value="" readonly>
																</div>
															</div>
														</div>	
														
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-5 col-form-label">Rank</label>
																<div class="col-sm-7">
																	<input class="border-hide "  type="text" id="rank" value="" readonly>
																</div>
															</div>
														</div>																	 
													</div>
													
													
													
												<!-- <div class="row">
													<div class="col-md-10"> 
													</div>														
													<div class="col-md-2">
														<div class="form-group row">															 
															<button class="btn btn-primary" id="appointmentHistory" type="submit"  onclick="return  checkValidation()">Appointment History</button>
														</div>
													</div> 
												</div> -->
															   
												 
												   <!--  Service No.: <input type="text" id="sNo" name="serviceNo" value="" readonly><br>
												    Full Name: <input type="text" id="empName" name= "empName" value="" readonly>
												    Unit/Location.: <input type="text" id="unit" value="" readonly><br>
												    Age: <input type="text" id="age" value="" readonly>
												    Department:<input type="text" id="department" value="" readonly><br>
												    Gender: <input type="text" id="gender" value="" readonly>
												    Rank: <input type="text" id="rank" value="" readonly><br>
												    Mobile Number:<input type="text" id="mobile" value="" readonly><br><br> -->
												    
												    
												 </div>
												  <div id="tblEmpAndDetails"  style="display:none" >
												 
												  
                                                        
                                                            <table class="table table-bordered  ">
                                                                            <thead>
                                                                                <tr>
                                                                                    <th id="th1">Select</th>
                                                                                    <th id="th2">Name</th>
                                                                                    <th id="th3">Relation</th>
                                                                                     <th id="th4">DOB</th>
                                                                                    <th id="th5">Age</th>
                                                                                    <th id="th6">Gender</th>
                                                                                   
                                                                                    
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody id="tblListOfEmployeeAndDepenent">
                                                                            </tbody>
                                                                        </table>
                                                   			
                                                   			  
                                                   			  
                                                   			  
                                                   			  
                                                   			  
                                                   			  
                                                   			  
                                                   			  
                                                   			  
                                                   			  
                                                   			  
                                                   			  <div class="row">
													<div class="col-md-10"> 
													</div>														
													<div class="col-md-2">
														<div class="form-group row">															 
															 <button class="btn btn-primary" id="bookappointment" type="submit" onclick="return  checkbookappointmentValidation();" >Book Appointment</button>
														</div>
													</div> 
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
</body>

<script type="text/javascript">
jQuery(document).ready(function() {
	//jQuery("#empDetails").hide();
	
	
	var jsonData=${patientData}
		  if(jsonData.status==1){
		  var htmlTable = "";
		  var dateOfBirth;
		    var data = jsonData.count;
		    var dataList = jsonData.data;
		    jQuery('#sNo').val(dataList[1].serviceNo);
		    jQuery('#empName1').val(dataList[1].empName);
		    jQuery('#unit').val(dataList[1].empUnitName);
		    jQuery('#age').val(dataList[1].empAge);//need to chk
		    jQuery('#department').val(dataList[1].empDepartmentName);
		    jQuery('#gender1').val(dataList[1].gender);
		    jQuery('#mobile').val(dataList[1].mobileNumber);
		    jQuery('#rank').val(dataList[1].empRank);
				 for(item in dataList){
					  // htmlTable += '<tr id="'+dataList[item].Id+'" onclick="hello('+dataList+')">';
					   dateOfBirth= new Date(dataList[item].dateOfBirth).toLocaleDateString("en-US");
					   //alert("dateOfBirth"+dataList[item].dateOfBirth);
					  htmlTable = htmlTable + "<tr id='" + dataList[item].Id + "'>";
					  htmlTable = htmlTable + "<td style='width: 150px;' name='ename'><input type='radio' onclick='getValue("+dataList[item].Id+","+JSON.stringify(jsonData)+");' id='patient' name='patient' value='"+dataList[item].name+","+dataList[item].relation+","+dataList[item].dateOfBirth+","+dataList[item].age+","+dataList[item].genderId+","+dataList[item].uhidNo+","+dataList[item].employeeId+","+dataList[item].serviceNo+","+dataList[item].relationId+"'></td>";
			    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].name + "</td>";
			    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].relation + "</td>";
			    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].dateOfBirth + "</td>";
			    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].age + "</td>";
			    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].gender + "</td>";
			    	 
			    	
			      }
				 jQuery("#empDetails").show();
			      $j("#tblListOfEmployeeAndDepenent").html(htmlTable);
				  $j('#tblEmpAndDetails').show();
			}else{
				alert(jsonData.msg);
				 $j('#message').html(jsonData.msg);
				 $j('#tblEmpAndDetails').empty();
				 $j("#tblListOfEmployeeAndDepenent").empty();
				 /* $j(document).ready(function(){
		             $j('#message').fadeOut(5000);
		             }); */
		        
			}
});


	
function getServiceDetails(){
	var serviceNumber;
	serviceNumber=document.getElementById('serviceNumber').value;
	if(document.getElementById('serviceNumber').value == null || document.getElementById('serviceNumber').value == ""){
		alert("Please Enter service Number .");
		return false;
	}
	
	
	var params = {
			 'serviceNo':serviceNumber
	 } 
	
	//alert("params: "+JSON.stringify(params)); 	
		jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "getServiceDetails",
		    data: JSON.stringify(params),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(jsonData){
		    	//console.log(jsonData);
		    	jQuery("#empDetails").show();
		    	  if(jsonData.status==1){
		    		
		    		  var htmlTable = "";
		    		    var data = jsonData.count;
		    		    var dataList = jsonData.data;
		    		    jQuery('#sNo').val(serviceNumber);
		    		    jQuery('#empName').val(dataList[1].empName);
		    		    jQuery('#unit').val(dataList[1].empUnitName);
		    		    jQuery('#age').val(dataList[1].empAge);//need to chk
		    		    jQuery('#department').val(dataList[1].empDepartmentName);
		    		    jQuery('#gender').val(dataList[1].gender);
		    		    jQuery('#mobile').val(dataList[1].mobileNumber);
		    		    jQuery('#rank').val(dataList[1].empRank);
		    				 for(item in dataList){
		    					  // htmlTable += '<tr id="'+dataList[item].Id+'" onclick="hello('+dataList+')">';
		    					  htmlTable = htmlTable + "<tr id='" + dataList[item].Id + "'>";
		    					  htmlTable = htmlTable + "<td style='width: 150px;' name='ename'><input type='radio' onclick='getValue("+dataList[item].Id+","+JSON.stringify(jsonData)+");' id='patient' name='patient' value='"+dataList[item].name+","+dataList[item].relation+","+dataList[item].dateOfBirth+","+dataList[item].age+","+dataList[item].genderId+","+dataList[item].uhidNo+","+dataList[item].employeeId+","+dataList[item].serviceNo+","+dataList[item].relationId+"'></td>";
		    			    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].name + "</td>";
		    			    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].relation + "</td>";
		    			    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].dateOfBirth + "</td>";
		    			    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].age + "</td>";
		    			    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].gender + "</td>";
		    			    	 
		    			    	
		    			      }
		    			      $j("#tblListOfEmployeeAndDepenent").html(htmlTable);
		    				  $j('#tblEmpAndDetails').show();
		    			}else{
		    				 $j('#message').html(jsonData.msg);
		    				 $j('#tblEmpAndDetails').empty();
		    				 $j("#tblListOfEmployeeAndDepenent").empty();
		    				 /* $j(document).ready(function(){
		    		             $j('#message').fadeOut(5000);
		    		             }); */
		    		        
		    			}
		       
		        
		    }
		    
		    
		});
	
}
function checkValidation() {
	
	/* var str = $j("input:radio[name=patient]:checked").val();
	var myarray = str.split(',');
     if(myarray[5]==0)
	{
	alert("No Appointment history");
	return false;
	}  */
	document.patientdetailsForm.action="${pageContext.request.contextPath}/appointment/getAppointmentHistory";
	
	}

function checkbookappointmentValidation() {
	
	 var str = $j("input:radio[name=patient]:checked").val();
	
  if (typeof str === 'undefined' || str instanceof String)
	{
	alert("Please select radio button");
	return false;
	} 
	document.patientdetailsForm.action="${pageContext.request.contextPath}/appointment/bookAppointment";
}


function getValue(rowId,jsonData) {
	
		var dataList=jsonData.data;
		 for(item in dataList){
			 if(dataList[item].Id === parseInt(rowId)){
					$j('#religionId').val(dataList[item].empReligionId);


			 $j('#uhidNo').val(dataList[item].uhidNo); 
			 $j('#empId').val(dataList[item].employeeId);
			 $j('#empService').val(dataList[item].serviceNo); 
			 $j('#rank').val(dataList[item].empRank); 
			 $j('#rankId').val(dataList[item].empRankId); 
			 $j('#trade').val(dataList[item].empTradeName); 
			 $j('#tradeId').val(dataList[item].empTradeId); 
			 $j('#empName').val(dataList[item].empName); 
			 $j('#totalservice').val(dataList[item].empTotalService); 
			 $j('#empServiceJoinDate').val(dataList[item].empServiceJoinDate); 
			 $j('#unit').val(dataList[item].empUnitName); 
			 $j('#unitId').val(dataList[item].empUnitId); 
			 $j('#region').val(dataList[item].empCommandName); 
			 $j('#regionId').val(dataList[item].empCommandId);
			 $j('#recordoffice').val(dataList[item].empRecordOfficeName); 
			 $j('#recordofficeId').val(dataList[item].empRecordOfficeId); 
			 $j('#maritalstarus').val(dataList[item].empMaritalStatusName); 
			 $j('#maritalstarusId').val(dataList[item].empMaritalStatusId); 
			 $j('#religion').val(dataList[item].empReligion); 
			 $j('#religionId').val(dataList[item].empReligionId); 
			 
			 $j('#patientname').val(dataList[item].name);
			 $j('#patientRelation').val(dataList[item].relation); 
			 $j('#patientRelationId').val(dataList[item].relationId); 
			 $j('#patientAge').val(dataList[item].age); 
			 $j('#patientAddress').val(dataList[item].patientAddress); 
			 $j('#patientPincode').val(dataList[item].patientPincode); 
			 $j('#gender').val(dataList[item].gender);
			 $j('#genderId').val(dataList[item].genderId);
			 $j('#patientMoblienumber').val(dataList[item].mobileNumber); 
			 $j('#patientCity').val(dataList[item].city); 
			 $j('#patientDOB').val(dataList[item].dateOfBirth); 
			 $j('#patientEmail').val(dataList[item].patientEmailId); 
			 $j('#patientState').val(dataList[item].stateName);
			 $j('#patientStateId').val(dataList[item].stateId);
			 


			 $j('#nok1Firstname').val(dataList[item].nok1Name); 
			 $j('#nok1Address').val(dataList[item].Nok1Address); 
			 $j('#nok1Moblienumber').val(dataList[item].nok1MobileNo); 
			 $j('#nok1Relation').val(dataList[item].nok1Relation); 
			 $j('#nok1RelationId').val(dataList[item].nok1RelationId);
			 $j('#nok1Policestation').val(dataList[item].nok1Policestation); 
			 $j('#nok1Email').val(dataList[item].nok1EamilId); 
			 $j('#nok1pincode').val(dataList[item].nok1Pincode); 
			 $j('#nok1Contactnumber').val(dataList[item].nok1ContactNo); 
			 
			 
			 $j('#nok2Firstname').val(dataList[item].nok2Name); 
			 $j('#nok2Address').val(dataList[item].nok2Address); 
			 $j('#nok2Moblienumber').val(dataList[item].nok2MobileNo); 
			// $j('#nok2Relation').val((dataList[item].nok2Relation!=0?dataList[item].nok2Relation:0)); 
			 $j('#nok2Relation').val(dataList[item].nok2RelationId); 
			 $j('#nok2Policestation').val(dataList[item].nok2Policestation); 
			 $j('#nok2Email').val(dataList[item].nok2EamilId); 
			 $j('#nok2pincode').val(dataList[item].nok2Pincode); 
			 $j('#nok2Contactnumber').val(dataList[item].nok2ContactNo); 
			 
			 $j('#bloodGroup').val(dataList[item].patientBloodGroupId); 
			 $j('#medicalCategory').val(dataList[item].empMedicalCategoryId); 
			 $j('#date').val(dataList[item].dateME); 
			  
			 }	 
	
		 }
}
</script>


</html>