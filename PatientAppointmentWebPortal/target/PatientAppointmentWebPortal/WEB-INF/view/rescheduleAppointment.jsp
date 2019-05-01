<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="..//view/leftMenu.jsp"%>
<%@include file="..//view/commonJavaScript.jsp"%>

<!DOCTYPE html>
<html>

<head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="ISO-8859-1">
<title>Patient Appointment</title>
</head>

<body>
	${message}

	<!--START  -->
	<div class="content-page">
		<!-- Start content -->
		<div class="">
			<div class="container-fluid">
				<div class="internal_Htext">Reschedule Appointment</div>

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<form:form id="bookAppointmentform"
									name="rescheduleAppointmentform" method="POST"
									modelAttribute="patientDetails">
									<!-- -------------  Patient Appointment end here -->

									<div class="row">

										<div class="col-md-12">


											<div id="empDetails">

												<div class="row">
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Service
																No</label>
															<div class="col-sm-7">
																<input class="border-hide " type="text" id="sNo"
																	name="serviceNo" value="${patientDetail.serviceNo}"
																	readonly>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Full Name
															</label>
															<div class="col-sm-7">
																<input class="border-hide " type="text" id="empName"
																	name="fullName" value="${patientDetail.fullName}"
																	readonly>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Relation</label>
															<div class="col-sm-7">
																<input class="border-hide " type="text" id="relation"
																	name="relation" value="${patientDetail.relation}"
																	readonly>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Date Of
																Birth</label>
															<div class="col-sm-7">
																<input class="border-hide " type="text" id="dob"
																	name="dateOfBirth" value="${patientDetail.dateOfBirth}"
																	readonly>
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Gender</label>
															<div class="col-sm-7">
																<input class="border-hide " type="text" id="gender"
																	name="gender" value="${patientDetail.gender}" readonly>
															</div>
														</div>
													</div>
                                                    <input type="hidden" name="visitId"
														value="${patientDetail.visitId}"> 
													<input type="hidden" name="genderId"
														value="${patientDetail.genderId}"> <input
														type="hidden" name="uhidNo"
														value="${patientDetail.uhidNo}"> <input
														type="hidden" name="relationId"
														value="${patientDetail.relationId}"> <input
														type="hidden" name="empId" value="${patientDetail.empId}">
													<input type="hidden" id="tokenNo" name="tokenNumber"
														value="">

												</div>

												<div class="row">

													<div class="col-md-4">
														<div class="form-group row">
															<label for="service" class="col-sm-5 col-form-label">Hospital</label>
															<div class="col-sm-7">
																<select id="hospitalId" name="hospitalId"
																	onchange="geAppointmentType();" class="form-control">
																	<option value="${patientDetails.hospitalId}"
																		selected="selected" readonly>${patientDetails.hospital}</option>
																</select>
															</div>
														</div>

													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label for="service" class="col-sm-5 col-form-label">Department</label>
															<div class="col-sm-7">
																<select id="departmentId" name="departmentId"
																	onchange="geAppointmentType();" class="form-control">
																	<option value="${patientDetails.departmentId}"
																		selected="selected">${patientDetails.department}</option>
																</select>

															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label for="service" class="col-sm-5 col-form-label">
																Appointment Type</label>
															<div class="col-sm-7">
																<select id="appTypeTable" name="appTypeId"
																	class="form-control">
																	<option value="${patientDetails.appointmentId}"
																		selected="selected">${patientDetails.appointmentType}</option>
																</select>

															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label for="service" class="col-sm-5 col-form-label">Appointment
																Date</label>
															<div class="col-sm-5">
																<input type="date" name="appointmentDate"
																	id="appointmentDate">
															</div>
														</div>
													</div>

												</div>
												<div class="row">



													<div class="col-md-4">
														<button class="btn btn-primary" id="appointment"
															type="button" onclick="getTokens();">Show Token</button>

													</div>

												</div>
												<!-- <div class="row">

													<div class="col-md-4" id="showToken" style="display: none;">
														<div class="form-group row">
															<label for="service" class="col-sm-4 col-form-label"
																style="background: #1a2942; color: #fff;">
																Token.</label>
															  <div >
                                                                            <div id="displayToken" ></div>
                                                                        </div> 

														</div>
													</div>
													<div class="col-md-8"></div>

												</div> -->

												<div class="row">
													<div id="displayToken"></div>

												</div>
											</div>

											<div class="row">

												<div class="col-md-9"></div>

												<div class="col-md-3">
													<button class="btn btn-primary" id="bookAppointment"
														type="submit"
														onclick="return  checkRescheduleappointmentValidation();">Reschedule
														Appointment</button>

												</div>

											</div>

										</div>


									</div>
							</div>
							</form:form>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
	</div>

	<!--End  -->

	<script>
	/*  $j(document).ready(function() {
			var now = new Date();
			now.setDate(now.getDate() + 1);
			var day = ("0" + now.getDate()).slice(-2);
			var month = ("0" + (now.getMonth() + 1)).slice(-2);

			var today = now.getFullYear()+"-"+(month)+"-"+(day) ;

			$('#appointmentDate').val(today);
		   });
 */


	  function getTokens() { 
	 var appointmentDate = $j("#appointmentDate").val();
     var tokenNo = $j('#tokenNo').val();
     var hospitalId = $j("#hospitalId").val();
     var departmentId = $j('#departmentId').val();
     var appTypeTable = $j('#appTypeTable').val();
     
     if (hospitalId == 0 || hospitalId == "") {
         alert("Please select hospital.");
         return false;
     }
     if (departmentId == 0 || departmentId == "") {
         alert("Please select department.");
         return false;
     }
     if (appTypeTable == 0 || appTypeTable == "") {
         alert("Please select Appointment Type.");
         return false;
     }
     if (appointmentDate == null || appointmentDate == "") {
         alert("Please select Appointment Date.");
         return false;
     }

   
	var appointmentDate = $j('#appointmentDate').val();
	var date= new Date(appointmentDate);
	var varDate = new Date(date); //dd-mm-YYYY
	var today = new Date();

    if(varDate < today) {
	alert("Appointment date can not smaller than current date!");
	return false;
	}  
	 var arrParam=[];
	 var deptId = $j('#departmentId').find('option:selected').val();
	 var appointmentTypeId=[];
	// var appointmentTypeId = $j('#appTypeTable').find('option:selected').val();
	 var appId=$j('#appTypeTable').find('option:selected').val();
     appointmentTypeId.push(appId);
	 //appointmentTypeId=["1"];

	 
	appointmentDate=(( date.getDate() + '-' + (date.getMonth()+1) )  + '-' +  date.getFullYear());
	 var hospitalId = $j('#hospitalId').find('option:selected').val();
	 var params = {
			"deptId":deptId,
			"appointmentTypeId":appointmentTypeId,
			"hospitalId" : hospitalId,
			"visitFlag" :"P",
			"visitDate":appointmentDate
		}
		arrParam.push(params);


 var resuestData ={
		"arrParam":arrParam
		}

 		$j.ajax({
			type : "POST",
			contentType : "application/json",
			url : 'getAppointmentTokens',
			data : JSON.stringify(resuestData),
			dataType : "json",
			cache : false,
			success : function(msg) {
				var displayToken = '';
				var dataMain = JSON.parse(JSON.stringify(msg));
				jQuery("#showToken").show();
                 for (var i=0; i<dataMain.jsonList.length; i++) {
                	    var counter = dataMain.jsonList[i];
                	    if (typeof counter.tokenMsg[0].tokenValue !== 'undefined' || !counter.tokenMsg[0].tokenValue instanceof String){
                      	  
                	    for (var j=0; j<counter.tokenMsg.length; j++) {
                	    	//displayToken += '<input readonly onclick="setVal('+counter.tokenMsg[j].tokenValue+','+counter.tokenMsg[j].tokenValue+')" type="text" name="tokenNo" id="tokenNoId'+counter.tokenMsg[j].tokenValue+'" value="token No '+counter.tokenMsg[j].tokenValue+"-"+counter.tokenMsg[j].tokenStartTime+'" style="left-margin:50px">'; 
                	    	 if (counter.tokenMsg[j].tokenStatus == "available")
                                 displayToken += '<div id="d_button" class=" sad abc'+j+'" onclick="setVal('+ j + ',' + counter.tokenMsg[j].tokenValue + ',' + counter.tokenMsg[j].tokenValue + ')"> Token No ' + counter.tokenMsg[j].tokenValue + "-" + counter.tokenMsg[j].tokenStartTime + " to " + counter.tokenMsg[j].tokenEndTime + '</div>';
                             else
                                 displayToken += '<div id="r_button"> Token No ' + counter.tokenMsg[j].tokenValue + "-" + counter.tokenMsg[j].tokenStartTime + " to " + counter.tokenMsg[j].tokenEndTime + '</div>';

                	    }
                	} else{
                          	 alert(counter.tokenMsg);
                           }
                 }
				
				$j('#displayToken').html(displayToken);
                
			},
			error : function(msg) {
				alert("An error has occurred while contacting the server");
			}
		});
}


function setVal(count,tokenno, a) {
	$j('.sad').css({
        'background-color': 'green'
        
    });
	$j('.abc'+count).css({
        'background-color': 'blue'
        
    });

    $j('#tokenNo').val(tokenno);

}

function checkRescheduleappointmentValidation() {
	
	 var appointmentDate = $j("#appointmentDate").val();
	 var tokenNo=$j('#tokenNo').val();
   if (appointmentDate == null || appointmentDate == "")
	{
	   alert("Please select Appointment Date.");
	    return false;
	} 
  if (tokenNo == null || tokenNo == "")
	{
	   alert("Please select token.");
	    return false;
	}  
	document.rescheduleAppointmentform.action="${pageContext.request.contextPath}/appointment/submitRescheduleAppointment";
}
//for enter key press then search

/* $j('#INPUTID').keypress(function (e) {
	var key = e.which;
	if(key == 13)  // the enter key code
	  {
	findEmployeeAndDependent();
	return false;
	  }
	}); */
</script>
</body>
</html>




