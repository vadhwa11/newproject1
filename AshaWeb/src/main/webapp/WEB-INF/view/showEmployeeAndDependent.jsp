<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.asha.icgweb.utils.HMSUtil"%>
<%@include file="..//view/leftMenu.jsp"%>
<%@include file="..//view/commonJavaScript.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>



<%long valueRegistrationTypeId = Long.parseLong(HMSUtil.getProperties("urlextension.properties", "REGISTRATION_TYPE_ICG_ID")); %>
<% int selfRelationId = Integer.parseInt(HMSUtil.getProperties("urlextension.properties", "SELF_RELATION_ID")); %>
<% String APPOINTMENTTYPE =HMSUtil.getProperties("urlextension.properties", "APPOINTMENT_TYPE"); %>
<% String visitFlagForReception =HMSUtil.getProperties("urlextension.properties", "VISIT_FLAG_FOR_RECEPTION"); %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PATIENT DETAILS LIST</title>

</head>
<body>
  <div id="wrapper">
		 
		 <div class="content-page">
				<div class="">
					<div class="container-fluid">
		        <div class="internal_Htext">Create Appointment of ICG Employee and	Dependent</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="card">
									<div class="card-body">
			<!-- Start tab coding  -->

									<ul class="nav nav-tabs" role="tablist">
										<li class="nav-item"><a class="nav-link active"
											data-toggle="tab" href="#empTab">Employee Details</a></li>
										<li class="nav-item"><a class="nav-link"
											data-toggle="tab" href="#patientTab">Patient Detail</a></li>
									</ul>
									
									<div class="tab-content">
										<div id="empTab" class="container tab-pane active">
											<div class="clearfix"></div>
											
											<form id="visitEmployee" name="visitEmployee" action="">
											<div class="row">
											
											<div class="col-md-4">
													<div class="form-group row">
														<label class="col-md-5 col-form-label">Service No.<span>*</span></label> 
														<div class="col-md-7">																			
															    <input	id="serviceNoId" class="auto  form-control" size="8" type="text"
																			name="serviceNo" value="" title="Enter Service No"
																			validate="Service No,string,yes" maxlength="10" />															
															
														</div>
													</div>
										   </div>
															 	
											
											<div class="col-md-1">
													<div class="form-group row">
														 <button type="button" class="btn  btn-primary" onclick="findEmployeeAndDependent()">Search</button>
													</div>
										   </div>
											
											<div class="col-md-7">
													 
										   </div>
                                         </div>
                                         
                                         
                                         
                                             
                                             <div class="row">
											<div class="col-md-12"><p align="Left" id="message"	style="color: green; font-weight: bold;"></p></div>
											</div>
                                             
											</form>
											

											<!-- Data Grid -->

											<div id="tblEmpAndDetails" style="display: none"
												class="right_col" role="main" style="padding:0.5% 1.8%;" >
												<div class="clearfix"></div>
												<h5 style="font-weight:600;">List of Details</h5>
												<table class="table table-striped table-hover  table-bordered  ">
													<thead>
														<tr>
															<th id="th1">Name</th>
															<th id="th2">Age</th>
															<th id="th3">Gender</th>
															<th id="th4">DOB</th>
															<th id="th5">Relation</th>
														</tr>
													</thead>
													<tbody id="tblListOfEmployeeAndDepenent">
													</tbody>
												</table>
											</div>
										</div>

										<div id="patientTab" class="container tab-pane fade">
		<!-- Start  form patient   -->
											
									 			<!-- panel-group -->
	
				<form id="patientDetailsForm" name="patientDetailsForm"
												action="" method="POST">										
												
												
					 
												
				<!-- -----  Service Detail  start here --------- -->	
				
						  <div class="adviceDivMain" id="button1" onclick="showhide(this.id)">
								<div class="titleBg" style="width: 520px; float: left;">
									<span>Service Detail  </span>
								</div>
								<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton1" value="+" onclick="showhide(this.id)" type="button">
							</div> 
						
						      <div class="hisDivHide" id="newpost1">
			 		 
												<div class="row">
													<div class="col-md-4">
														<div class="form-group row">
															<label for="service" class="col-md-5 col-form-label">Service
																No.</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="empService"
																	name="empService"  validate="Service No,string,yes">
																<input type="hidden" class="form-control" id="uhidNo"
																	name="uhidNo">
																<input type="hidden" class="form-control" id="registrationTypeId"
																	name="registrationTypeId"  value="<%=valueRegistrationTypeId%>">
																	
																<input type="hidden" class="form-control" id="visitFlag"
																	name="visitFlag"  value="<%=visitFlagForReception%>">
																	
																<input type="hidden" class="form-control" id="userId"
																	name="userId"  value="<%=session.getAttribute("user_id")%>">
																	
																<input type="hidden" class="form-control" id="hospitalId"
																	name="hospitalId"  value="<%=session.getAttribute("hospital_id")%>">
																
																<input type="hidden" class="form-control" id="visitDate"
																	name="visitDate"  value="">	
																	
															</div>
														</div>
														</div>
														
                                                     <div class="col-md-4">
														<div class="form-group row">
															<label for="EmpName" class="col-md-5 col-form-label">Name</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="empName"
																	name="empName" validate="Emp Name,string,yes">
																<input type="hidden" class="form-control" id="empId"
																	name="empId">
															</div>
														</div>
                                                      </div>

                                                    <div class="col-md-4">
														<div class="form-group row">
															<label for="region" class="col-md-5 col-form-label">Region/Command</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="region"
																	name="region" validate="Region/Command,string,yes">
																<input type="hidden" class="form-control" id="regionId"
																	name="regionId">
															</div>
														</div>														
												    </div>

                                                 <div class="col-md-4">
														<div class="form-group row">
															<label for="religion" class="col-md-5 col-form-label">Religion</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="religion"
																	name="religion" validate="Religion,string,yes">
																<input type="hidden" class="form-control"
																	id="religionId" name="religionId">
															</div>
														</div> 
													</div>
													
													
													<div class="col-md-4">
														<div class="form-group row">
															<label for="rank" class="col-md-5 col-form-label">Rank</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="rank"
																	name="rank" validate="Rank,string,yes"> <input
																	type="hidden" class="form-control" id="rankId"
																	name="rankId">
															</div>
														</div>
													</div>	
                                                    
                                                    <div class="col-md-4">
														<div class="form-group row">
															<label for="service" class="col-md-5 col-form-label">Total
																Service</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="totalservice" name="totalservice"
																	validate="Total Service,string,yes"> <input
																	type="hidden" class="form-control"
																	id="empServiceJoinDate" name="empServiceJoinDate">
															</div>
														</div>														
													</div>
                                                  
                                                  
                                                    <div class="col-md-4">
														<div class="form-group row">
															<label for="recordoffice" class="col-md-5 col-form-label">Record
																Office</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="recordoffice" name="recordoffice"
																	validate="Record Office,string,yes"> <input
																	type="hidden" class="form-control" id="recordofficeId"
																	name="recordofficeId">
															</div>
														</div> 
													</div>
													
													
													<div class="col-md-4">
														<div class="form-group row">
															<label for="trade_branch" class="col-md-5 col-form-label">Trade/Branch</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="trade"
																	name="trade" validate="Trade/Branch,string,yes">
																<input type="hidden" class="form-control" id="tradeId"
																	name="tradeId">
															</div>
														</div>
                                                     </div>

                                                    <div class="col-md-4">
														<div class="form-group row">
															<label for="unit" class="col-md-5 col-form-label">Unit</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="unit"
																	name="unit" validate="Unit,string,yes"> <input
																	type="hidden" class="form-control" id="unitId"
																	name="unitId">
															</div>
														</div> 
													</div>	

                                                      <div class="col-md-4">
														<div class="form-group row">
															<label for="marital status"
																class="col-md-5 col-form-label">Marital Status</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="maritalstarus" name="maritalstarus"
																	validate="Marital Status,string,yes"> <input
																	type="hidden" class="form-control" id="maritalstarusId"
																	name="maritalstarusId">
															</div>
														</div> 
														
													</div>
														
													
												</div>
										 
					 </div>
					 
					 
					 <!---------------------- Service Details end here --------------------------->
					 
					 
					 
					 
					<!---------------------- Patient Details start here --------------------------->
					
					  <div class="adviceDivMain" id="button2" onclick="showhide(this.id)">
						<div class="titleBg" style="width: 520px; float: left;">
							<span>Patient Detail  </span>
						</div>
						<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton2" value="-" onclick="showhide(this.id)" type="button">
					</div>	
				
				
				      <div class="hisDivHide" id="newpost2"  style="display:block;">
					
								<div class="row">
													<div class="col-md-4">
														<div class="form-group row">
															<label for="Name" class="col-md-5 col-form-label">Name</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="patientname"
																	name="patientname" validate="Patient Name,string,yes">
															</div>
														</div>														
												     </div>		
                                                     <div class="col-md-4">
														<div class="form-group row">
															<label for="DOB" class="col-md-5 col-form-label">DOB</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="patientDOB"
																	name="patientDOB" validate="Patient Name,string,yes">
															</div>
														</div>
												      </div>

                                                     <div class="col-md-4">
														<div class="form-group row">
															<label for="recordoffice" class="col-md-5 col-form-label">Address</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="patientAddress" name="patientAddress" validate="Patient Address,string,yes">
															</div>
														</div>
													 </div>
													 
                                                     <div class="col-md-4">
														<div class="form-group row">
															<label for="recordoffice" class="col-md-5 col-form-label">Pincode</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="patientPincode" name="patientPincode" validate="Patient Pincode,string,yes">
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label for="service" class="col-md-5 col-form-label">Relation</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="patientRelation" name="patientRelation" validate="Patient Relation ,string,yes"> <input
																	type="hidden" class="form-control"
																	id="patientRelationId" name="patientRelationId">
															</div>
														</div>
													</div>
                                                     <div class="col-md-4">
														<div class="form-group row">
															<label for="service" class="col-md-5 col-form-label">Age</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="patientAge"
																	name="patientAge" validate="Patient Age ,string,yes">
															</div>
														</div>
													</div>
                                                     <div class="col-md-4">
														<div class="form-group row">
															<label for="marital status"
																class="col-md-5 col-form-label">City</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="patientCity"
																	name="patientCity">
															</div>
														</div>
													</div>

                                                  <div class="col-md-4">
														<div class="form-group row">
															<label for="region" class="col-md-5 col-form-label">Email
																Address</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="patientEmail" name="patientEmail">
															</div>
														</div>
													</div>
													<div class="col-md-4">

														<div class="form-group row">
															<label class="col-md-5 col-form-label">Gender</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="gender"
																	name="gender" validate="Gender ,string,yes"> <input type="hidden"
																	class="form-control" id="genderId" name="genderId">
															</div>
														</div>

                                                      </div>
                                                      <div class="col-md-4">
														<div class="form-group row">
															<label for="unit" class="col-md-5 col-form-label">Mobile
																Number</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="patientMoblienumber" name="patientMoblienumber" validate="Patient Mobile No ,int,yes">
															</div>
														</div>
													</div>
														
														
                                                     <div class="col-md-4">
														<div class="form-group row">
															<label for="state" class="col-md-5 col-form-label">State</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="patientState" name="patientState" validate="Patient State ,string,yes"> <input
																	type="hidden" class="form-control" id="patientStateId"
																	name="patientStateId">
															</div>
														</div>
													</div>
												 
												
										</div>
					
					</div>
					<!---------------------- Patient Details end here --------------------------->
					
					
					
					<!----------------------   start here --------------------------->
					
					<div class="adviceDivMain" id="button3" onclick="showhide(this.id)">
						<div class="titleBg" style="width: 520px; float: left;">
							<span>NoK Detail 1 </span>
						</div>
						<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton3" value="+" onclick="showhide(this.id)" type="button">
					</div>	
				
				
				      <div class="hisDivHide" id="newpost3">
					
						       <div class="row">
													<div class="col-md-4">
														<div class="form-group row">
															<label for="First Name" class="col-md-5 col-form-label">Name</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="nok1Firstname" name="nok1Firstname">
															</div>
														</div>														
												   </div>
                                                        
                                                      <div class="col-md-4">
														<div class="form-group row">
															<label for="recordoffice" class="col-md-5 col-form-label">Address</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="nok1Address"
																	name="nok1Address">
															</div>
														</div>
                                                       </div>
                                                       
                                                       <div class="col-md-4">                                                       
														<div class="form-group row">
															<label for="unit" class="col-md-5 col-form-label">Mobile
																Number</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="nok1Moblienumber" name="nok1Moblienumber">
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label for="service" class="col-md-5 col-form-label">Relation</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="nok1Relation" name="nok1Relation"> <input
																	type="hidden" class="form-control" id="nok1RelationId"
																	name="nok1RelationId">
															</div>
														</div>
														</div>
														
												  <div class="col-md-4">
														<div class="form-group row">
															<label for="policestation"
																class="col-md-5 col-form-label">Police Station</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="nok1Policestation" name="nok1Policestation">
															</div>
														</div>
													</div>
													
													<div class="col-md-4">
														<div class="form-group row">
															<label for="region" class="col-md-5 col-form-label">Email
																Address</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="nok1Email"
																	name="nok1Email">
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label for="recordoffice" class="col-md-5 col-form-label">Pincode</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="nok1pincode"
																	name="nok1pincode">
															</div>
														</div>
												   </div>
												   <div class="col-md-4">

														<div class="form-group row">
															<label for="contactnumber"
																class="col-md-5 col-form-label">Contact Number</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="nok1Contactnumber" name="nok1Contactnumber">
															</div>
														</div>

													</div>
												</div>
											 
					
					</div>										
												
					<!----------------------  end here --------------------------->					
												
					 <!----------------------   start here --------------------------->
					
				 <div class="adviceDivMain" id="button4" onclick="showhide(this.id)">
						<div class="titleBg" style="width: 520px; float: left;">
							<span> NoK Detail 2 </span>
					</div>
						<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton4" value="+" onclick="showhide(this.id)" type="button">
					  	
				</div>	
				
				      <div class="hisDivHide" id="newpost4">
									
									<div class="row">
												
													<div class="col-md-4">
														<div class="form-group row">
															<label for="First Name" class="col-md-5 col-form-label">Name</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="nok2Firstname" name="nok2Firstname">
															</div>
														</div>
													</div>
													<div class="col-md-4">
          
														<div class="form-group row">
															<label for="recordoffice" class="col-md-5 col-form-label">Address</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="nok2Address"
																	name="nok2Address">
															</div>
														</div>
														</div>
														<div class="col-md-4">

														<div class="form-group row">
															<label for="unit" class="col-md-5 col-form-label">Mobile
																Number</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="nok2Moblienumber" name="nok2Moblienumber">
															</div>
														</div>

													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label for="service" class="col-md-5 col-form-label">Relation</label>
															<div class="col-md-7">
																<select id="nok2RelationId" name="nok2Relation"
																	class="form-control">
																	<option value="0" selected="selected">Select</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label for="policestation"
																class="col-md-5 col-form-label">Police Station</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="nok2Policestation" name="nok2Policestation">
															</div>
														</div>
													</div>
														
													<div class="col-md-4">
														<div class="form-group row">
															<label for="region" class="col-md-5 col-form-label">Email
																Address</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="nok2Email"
																	name="nok2Email">
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label for="recordoffice" class="col-md-5 col-form-label">Pincode</label>
															<div class="col-md-7">
																<input type="text" class="form-control" id="nok2pincode"
																	name="nok2pincode">
															</div>
														</div>
													</div>
													<div class="col-md-4">

														<div class="form-group row">
															<label for="contactnumber"
																class="col-md-5 col-form-label">Contact Number</label>
															<div class="col-md-7">
																<input type="text" class="form-control"
																	id="nok2Contactnumber" name="nok2Contactnumber">
															</div>
														</div>

													</div>
												</div>
					
					</div>										
												
					<!----------------------  end here --------------------------->		
					
					
						<!----------------------   start here --------------------------->
					
					<div class="adviceDivMain" id="button5" onclick="showhide(this.id)">
						<div class="titleBg" style="width: 520px; float: left;">
							<span> Medical Detail</span> 
						</div>
						<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton5" value="+" onclick="showhide(this.id)" type="button">
					</div>		
				
				      <div class="hisDivHide" id="newpost5">
										<div class="row">
													<div class="col-md-4">

														<div class="form-group row">
															<label for="Blood Group" class="col-md-5 col-form-label">Blood
																Group</label>
															<div class="col-md-7">
																<select id="bloodGroupId" name="bloodGroup"
																	class="form-control">
																	<option value="0" selected="selected">Select</option>
																</select>
															</div>
														</div>

													</div>
													<div class="col-md-4">

														<div class="form-group row">
															<label for="medicalCategory" id="lblmedicalCategory" class="col-md-5 col-form-label">Present
																Medical Category </label>
															<div class="col-md-7">
																<select id="medicalCategoryId" name="medicalCategory"
																	class="form-control">
																	<option value="0" selected="selected">Select</option>
																</select>
															</div>
														</div>

													</div>
													<div class="col-md-4">

														<div class="form-group row">
															<label for="date" id="lbldate" class="col-md-5 col-form-label">Date of Medical category</label>
															<div class="col-md-7">
															<div class="dateHolder">
																<input type="text" readonly class="calDate datePickerInput form-control" id="dateId" placeholder="DD/MM/YYYY"
																	name="date"  onkeyup="mask(this.value,this,'2,5','/');" onblur="validateExpDate(this,'dateId')" maxlength="10">
															</div>
															</div>
														</div>

													</div>
													<div class="col-md-4">

														<div class="form-group row">
															<label for="duration" id="lblduration" class="col-md-5 col-form-label">Duration of Medical category</label>
															<div class="col-md-7">
																<select class="form-control" id="durationId"
																	name="duration">
																	<option value="0" selected="selected">Select</option>
																	<option value="1">Year</option>
																	<option value="2">Month</option>
																</select>
															</div>
														</div>

													</div>
												</div>
					
					</div>										
												
					<!----------------------  end here --------------------------->			
					
					 <!----------------------  start here --------------------------->
					
					<div class="adviceDivMain" id="button6" onclick="showhide(this.id)">
						<div class="titleBg" style="width: 520px; float: left;">
							<span> Visit Detail</span>   
					    </div>
						<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton6" value="-" onclick="showhide(this.id)" type="button">
					</div>	
				
				
				      <div class="hisDivHide" id="newpost6"  style="display:block;">						
					
							<div id="departmentNSessionList">
													<div class="row" id="div">
														<!-- Visit details div start with department -->

														<div class="col-md-4">
															<div class="form-group row">
																<label for="department" class="col-md-5 col-form-label">Department
																</label>
																<div class="col-md-7">
																	<select id="departmentId" name="department" validate="Department,int,yes"
																		onchange="getAppointmentType()" class="form-control">
																		<option value="0" selected="selected">Select</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group row">
																<label for="Priority" class="col-md-5 col-form-label">Priority</label>
																<div class="col-md-7">
																	<select name="priority" id="priorityId"
																		class="form-control">
																		<option value="1">High</option>
																		<option value="2">Medium</option>
																		<option value="3" selected="selected">Low</option>
																	</select>
																</div>
															</div>
														</div>

														<div class="col-md-4">
															<table border="0" cellspacing="0" cellpadding="0"
																width="50%" id="appTypeTable"></table>
														</div>

														<div class="col-md-4">
															<div id="displayToken"  class="form-group row  token_value_manage"></div>
															<input type="hidden" class="form-control-plaintext" id="tokenCountValue"
																	name="tokenCountValue">
														</div>

													</div>
													<!-- Visit details div  with department -->
												</div>
					
					              </div>										
									
											<div class="clearfix"></div>
											
											<br>
									            <div class="row"> 
												      <div class="col-md-12">
															<div class="btn-left-all"> 
															</div> 
															<div class="btn-right-all">
																<button class="btn btn-primary" type="button" name="Print"
																	onClick="validateDivToken()">Show Token</button>
																<button class="btn btn-primary" type="button"
																	onclick="submitFormdata()">Submit</button>
															</div> 
													   </div>
												  </div>
									
												
					<!----------------------  end here --------------------------->											
												
												 
												
												
											</form>
											
											 
												 
												 
		<!-- End form patient   -->
										</div>
									</div> 


		<!-- End Tab Coding   -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</div>

<script type="text/javascript" language="javascript">

$j(document).ready(function(){
	
	var deptValues = "";
	var bloodValues = "";
	var categoryValues = "";
	var relationValues = "";
	 	
		 var dictionary = ${data};
		 var deptList=dictionary.departmentList;
		 var bloodGroupList=dictionary.bloodGroupList;
		 var medicalCategoryList=dictionary.medicalCategoryList;
		 var masRelationList=dictionary.masRelationList;
		 
		 for(dept in deptList){
				deptValues += '<option value='+deptList[dept].departmentId+'>'
							+ deptList[dept].departmentName
							+ '</option>';
		 }
		 $j('#departmentId').append(deptValues); 	  
	  
		 
		 for(blood in bloodGroupList){
			 bloodValues += '<option value='+bloodGroupList[blood].bloodGroupId+'>'
							+ bloodGroupList[blood].bloodGroupName
							+ '</option>';
		 }
		 $j('#bloodGroupId').append(bloodValues); 	  
	  
		 
		 for(category in medicalCategoryList){
			 categoryValues += '<option value='+medicalCategoryList[category].medicalCategoryId+'>'
							+ medicalCategoryList[category].medicalCategoryName
							+ '</option>';
		 }
		 $j('#medicalCategoryId').append(categoryValues); 
		 
		 
		 
		 for(relation in masRelationList){
			 relationValues += '<option value='+masRelationList[relation].relationId+'>'
							+ masRelationList[relation].relationName
							+ '</option>';
		 }
		 $j('#nok2RelationId').append(relationValues); 
		 
	  
		 //activaTab('empTab');
	  
		 
	  $j('#serviceNoId').val(""); 
		  
	  
	});



 function findEmployeeAndDependent() {
	 $j('#message').html("");
	 var serviceNo =$j('#serviceNoId').val();
         if (serviceNo) {
             var params = {
                "serviceNo": serviceNo
                   }
               var data = params;
               var url = 'getEmployeeAndDependentlist';
               var bClickable = true;
                 GetJsonData('tblListOfEmployeeAndDepenent', data, url, bClickable);
                 }else{
                 alert("Please enter the service number");
                  return false;
                  }
                 }

 function makeTable(jsonData) {
	
     var htmlTable = "";
     if(jsonData.status==1){
    var data = jsonData.count;
    var dataList = jsonData.data;
		 for(item in dataList){
	    	  htmlTable = htmlTable + "<tr id='" + dataList[item].Id + "'>";
	    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].name + "</td>";
	    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].age + "</td>";
	    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].gender + "</td>";
	    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].dateOfBirth + "</td>";
	    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + dataList[item].relation + "</td>";
	    	  
	      }
		  $j('#message').html('');
	      $j("#tblListOfEmployeeAndDepenent").html(htmlTable);
		  $j('#tblEmpAndDetails').show();
	}else{
		
		 $j('#tblEmpAndDetails').hide();
		 $j("#tblListOfEmployeeAndDepenent").empty();
		 $j('#message').html(jsonData.msg);
        
	}
  }
 
 

function executeClickEvent(rowId,jsonData) {
	
	var dataList=jsonData.data;
	 for(item in dataList){
		 if(dataList[item].Id===parseInt(rowId)){
			
		$j('#medicalCategoryId').val("0"); 
		$j('#dateId').val(""); 
		$j('#durationId').val("0"); 
		$j('#departmentId').val("0"); 
		$j('#appTypeTable').empty();
		$j('#displayToken').empty();
		
		$j('#tokenCountValue').val("");  // new added
		
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
		 $j('#nok2Relation').val((dataList[item].nok2Relation!=0?dataList[item].nok2Relation:0)); 
		 $j('#nok2Policestation').val(dataList[item].nok2Policestation); 
		 $j('#nok2Email').val(dataList[item].nok2EamilId); 
		 $j('#nok2pincode').val(dataList[item].nok2Pincode); 
		 $j('#nok2Contactnumber').val(dataList[item].nok2ContactNo); 
		 
		 
		  $j('[name=bloodGroup] option').filter(function() { 
			    return ($j(this).text()==dataList[item].patientBloodGroup); 
			}).prop('selected', true); 
		
		
		 if(dataList[item].relationId==<%=selfRelationId%>){
			 $j("#lblmedicalCategory").show();
			 $j("#medicalCategoryId").show();
			 $j("#lbldate").show();
			 $j("#dateId").show();
			 $j("#lblduration").show();
			 $j("#durationId").show();
			 $j('[name=medicalCategory] option').filter(function() { 
				    return ($j(this).text()==dataList[item].empMedicalCategory); 
				}).prop('selected', true); 
			 
			 $j('#dateId').val(dataList[item].dateME);  
		 }else{
			 $j("#medicalCategoryId").val($j("#medicalCategoryId option:first").val());
			 $j("#lblmedicalCategory").hide();
			 $j("#medicalCategoryId").hide();
			 $j("#lbldate").hide();
			 $j("#dateId").hide();
			 $j("#lblduration").hide();
			 $j("#durationId").hide();
		 }
		 makeFieldsReadonly();	 
		 activaTab('patientTab');
		 }
	}
 }
 
 
  function activaTab(tabId){
	 $j('.nav-tabs a[href="#' + tabId + '"]').tab('show');
 } 
 
 function makeFieldsReadonly(){
	 	 $j("#empService").prop("readonly", true); 
		 $j('#empService').prop("readonly", true);
		 $j('#rank').prop("readonly", true);
		 $j('#trade').prop("readonly", true);
		 $j('#empName').prop("readonly", true);
		 $j('#totalservice').prop("readonly", true);
		 $j('#unit').prop("readonly", true);
		 $j('#region').prop("readonly", true);
		 $j('#recordoffice').prop("readonly", true);
		 $j('#maritalstarus').prop("readonly", true);
		 $j('#religion').prop("readonly", true);
		 
		 $j('#patientname').prop("readonly", true);
		 $j('#patientRelation').prop("readonly", true);
		 $j('#patientAge').prop("readonly", true);
		 $j('#patientAddress').prop("readonly", true);
		 $j('#patientPincode').prop("readonly", true);
		 $j('#gender').prop("readonly", true); 
		 $j('#patientMoblienumber').prop("readonly", true);
		 $j('#patientCity').prop("readonly", true);
		 $j('#patientDOB').prop("readonly", true);
		 $j('#patientEmail').prop("readonly", true);
		 $j('#patientState').prop("readonly", true);
		 


		 $j('#nok1Firstname').prop("readonly", true);
		 $j('#nok1Address').prop("readonly", true);
		 $j('#nok1Moblienumber').prop("readonly", true);
		 $j('#nok1Relation').prop("readonly", true);
		 $j('#nok1Policestation').prop("readonly", true);
		 $j('#nok1Email').prop("readonly", true);
		 $j('#nok1pincode').prop("readonly", true);
		 $j('#nok1Contactnumber').prop("readonly", true);
 }
 
 
 
 function getAppointmentType(){
		 $j('#appTypeTable').empty();
		 $j('#displayToken').empty();
		 $j('#tokenCountValue').val("");
		var deptId = $j('#departmentId').find('option:selected').val();
		var hospitalId = $j('#hospitalId').val();
		var relationId=$j('#patientRelationId').val();
		if(deptId!=0){
			var params = {
					"deptId":deptId,
					"hospitalId":hospitalId
			}
			$j.ajax({
				type : "POST",
				contentType : "application/json",
				url : '${pageContext.request.contextPath}/appointment/getLocationWiseAppointmentType',
				data : JSON.stringify(params),
				dataType : "json",
				cache : false,
				success : function(data) {
					if (data.status == '1') {
						var checkboxorder = [];
						var labelId = [];
						var idandType = [];
						var checkboxLength = data.appointmentTypeList.length;
						for(var a = 0;a< checkboxLength;a++){
							checkboxorder[a] = data.appointmentTypeList[a].appointmentTypeName;
							labelId[a] = data.appointmentTypeList[a].appointmentTypeId;
							idandType[a] = checkboxorder[a]+'@'+ labelId[a];
						}
						var tablebody = '<tr>';
						var tokenDisplay = '';
						$j('#appTypeTable').empty();
						$j('#displayToken').empty();
						$j('#tokenCountValue').val("");
						
						for(var z=0;z<checkboxLength;z++){
							tablebody += '<td style="position: relative;top: 13px;">';
							var checkBoxIdandValue = idandType[z].split("@");
							if(relationId==<%=selfRelationId%>){
								if(checkBoxIdandValue[1]=="<%=APPOINTMENTTYPE%>"){
									tablebody += '<input type="checkbox"  id ="td'+checkBoxIdandValue[0]+'" name="checkDiv" value="'+checkBoxIdandValue[1]+'"><span id="td'+z+'">'+checkBoxIdandValue[0]+'</span></br>';
								}else{
									tablebody += '<input type="checkbox" disabled id ="td'+checkBoxIdandValue[0]+'" name="checkDiv" value="'+checkBoxIdandValue[1]+'"><span id="td'+z+'">'+checkBoxIdandValue[0]+'</span></br>';
								}
							}else{
								if(checkBoxIdandValue[1]=="<%=APPOINTMENTTYPE%>"){
									tablebody += '<input type="checkbox"  id ="td'+checkBoxIdandValue[0]+'" name="checkDiv" value="'+checkBoxIdandValue[1]+'"><span id="td'+z+'">'+checkBoxIdandValue[0]+'</span></br>';	
								}else{
									//alert("Appointment Type OPD is not available.")
								}
							}
						}
						$j('#appTypeTable').append(tablebody);	
						
					}else{
						alert("Appointment Type is not available");
						$j('#appTypeTable').empty();	
					}
				},
				error : function(msg) {
					alert("An error has occurred while contacting the server");
				}
			});
		}else{
			$j('#appTypeTable').empty();
			$j('#displayToken').empty();
			$j('#tokenCountValue').val("");
			
		}
		
		
	}

 
 function validateDivToken(){
	 if(validateDeptAndAppointment()){
		 var arrParam=[];
		 var deptId = $j('#departmentId').find('option:selected').val();
		 var hospitalId = $j('#hospitalId').val(); //Hospiatl id will fetch from session
		 var visitFlag=$j('#visitFlag').val(); // It is fetching from hidden field
		 var visitDate="";
		 var appointmentTypeId=[];
		 $j.each($j("input[name=checkDiv]:checked"), function(){  
			  appointmentTypeId.push($j(this).val());
         });
		
	var params = {
					"deptId" : deptId,
					"appointmentTypeId" : appointmentTypeId,
					"hospitalId" : hospitalId,
					"visitFlag" : visitFlag,
					"visitDate" : visitDate
			}
			arrParam.push(params);
	
	// The uper code will comes with in loop if we will go for multi specility 
	
	 var resuestData ={
			"arrParam":arrParam
			}
	 
			$j.ajax({
				type : "POST",
				contentType : "application/json",
				url : 'tokenNoForDepartmentMultiVisit',
				data : JSON.stringify(resuestData),
				dataType : "json",
				cache : false,
				success : function(msg) {
					var displayToken = '';
					var tokenCount = '';
					$j('#tokenCountValue').val("");
					
					for(count in msg.jsonList){
						if( msg.jsonList[count].status=='1'){
							displayToken += 'Token No. &nbsp '+'<input readonly class="border_none" style="color:green;font-weight:600" type="text" name="tokenNo" id="tokenNoId'+count +'" value="'+msg.jsonList[count].tokenMsg+'"   validate="Token No,string,yes">';
							tokenCount=count;
						}else{
							$j('#displayToken').html(msg.jsonList[count].tokenMsg);
							//alert(msg.jsonList[count].tokenMsg);
							return;
						}
					}
					$j('#tokenCountValue').val(tokenCount);
					$j('#displayToken').html(displayToken);
					
				},
				error : function(msg) {
					alert("An error has occurred while contacting the server");
				}
			}); 
	 }
 }
 

function submitFormdata(){
	var value="";
	var tokenValue=""
	value=validateFields('patientDetailsForm');
	tokenValue =checkTokenValue();
	if(value==true && tokenValue){
		var departmentId = $j('#departmentId').find('option:selected').val();
		var priorityId = $j('#priorityId').val();
		var appointmentTypeId=[];
		 $j.each($j("input[name=checkDiv]:checked"), function(){  
			  appointmentTypeId.push($j(this).val());
	   	 });
		 
		 // var tokenLength =$j('input[name*="tokenNo"]').length; 
		 var tokenIds=[];
		 $j("input[name='tokenNo']").each(function() {
			 tokenIds.push($j(this).val());
			});
		 var hospitalId=$j('#hospitalId').val(); //Hospiatl id will fetch from session
		 var params = {
					 "departmentId":departmentId,
					 "priorityId":priorityId,
					 "appointmentTypeId":appointmentTypeId,
					 "tokenIds":tokenIds,
					 "hospitalId":hospitalId,
					 "patientDetailsForm":$j('#patientDetailsForm').serializeObject() 
					}
		
		 
	     $j.ajax({
	   	  type : "POST",
			  contentType : "application/json",
			  url : 'submitPatientDetails',
			  data : JSON.stringify(params),
			  dataType : "json",
			  cache : false,
	        success : function(response) {
	        	if(response.status=="1"){
	        		var visitList = response.visitList;
		        	// This need to update
		        	window.location.href="showVisitTokenList?visitId="+visitList+"";
	        	}else if(response.status=="0"){
	        		alert(response.msg);
	        		return;
	        	}else{
	        		alert(response.errorMessage);
	        	}
	        	
	        },
	     error: function(msg){					
				alert("An error has occurred while contacting the server");
			}
	     }); 
	}else{
		if(value!=true)
		alert(value);
	}
}



$j.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $j.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


function checkTokenValue(){
    var count = $j('#tokenCountValue').val();
    for(var i=0;i<=count;i++){
    	if($j('#tokenNoId'+i).val()){
    		if(isNaN($j('#tokenNoId'+i).val())){
        		return false;
        		alert($j('#tokenNoId'+i).val());
        	}else{
        		return true;
        	}
    	}else{
    		alert("Please check the token number");
    		return false;
    	}
    }
}


function validateDeptAndAppointment(){
	var deptId = document.getElementById("departmentId").value;
	if(deptId == 0){
		alert("Please select Department");
	}else{
		if(!$j("input[name='checkDiv']:checked").is(':checked')){
			alert("Please select Appointment Type");
			return false;
		}
		return true;
	}
}    
 
 
$j('#serviceNoId').keypress(function (e) {
	 var key = e.which;
	 if(key == 13)  // the enter key code
	  {
		 findEmployeeAndDependent();
		 return false;
	  }
	});
</script>
<!-- ----------------Accordian  start here---------- -->

<script>
    function showhide(buttonId)
     {
		 if(buttonId=="button1"){
					test('realted'+buttonId,"newpost1");					
		 }else if(buttonId=="button2"){
					test('realted'+buttonId,"newpost2");
		 }else if(buttonId=="button3"){
					test('realted'+buttonId,"newpost3");
		 }else if(buttonId=="button4"){
					test('realted'+buttonId,"newpost4");
		 }else if(buttonId=="button5"){
					test('realted'+buttonId,"newpost5");
		 }else if(buttonId=="button6"){
					test('realted'+buttonId,"newpost6");
		 }else if(buttonId=="button7"){
					test('realted'+buttonId,"newpost7");
		 }else if(buttonId=="button8"){
					test('realted'+buttonId,"newpost8");
		 }else if(buttonId=="button9"){
					test('realted'+buttonId,"newpost9");
		 }else if(buttonId=="button10"){
					test('realted'+buttonId,"newpost10");
		 }else if(buttonId=="button11"){
					test('realted'+buttonId,"newpost11");
		 }else if(buttonId=="button12"){
					test('realted'+buttonId,"newpost12");
		 } 
	 }
    
	function test(buttonId,newpost){
			 var x = document.getElementById(newpost);
				if (x.style.display != "block") {
					x.style.display = "block";
					document.getElementById(buttonId).value="-";
				}else {
					x.style.display = "none";
					document.getElementById(buttonId).value="+";
				}	 
	}
	      
  </script>

<!-- ----------------Accordian  end here---------- -->
</body>
</html>
