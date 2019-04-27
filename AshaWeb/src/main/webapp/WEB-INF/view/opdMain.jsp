<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@include file="..//view/leftMenu.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<%
	String hospitalId = "1";
	if (session.getAttribute("hospital_id") != null) {
		hospitalId = session.getAttribute("hospital_id") + "";
	}
	String userId = "1";
	if (session.getAttribute("user_id") != null) {
		userId = session.getAttribute("user_id") + "";
	}
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="..//view/commonJavaScript.jsp"%>
<title>OPD Main</title>
<!--   <link href="/AshaWeb/resources/css/stylecommon.css" rel="stylesheet" type="text/css" /> -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/newOpd.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/opd.js"></script>

</head>

<%
	int i = 1;
%>

<body>

	<div class="content-page">
		<div class="">
			<div class="container-fluid">
				<div class="internal_Htext">OPD Consultation</div>

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">

								<form>

									<!-- Service Detail Start Here -->
									<div class="opdMain_detail_area">
										<h4 class="service_htext">Service Detail</h4>
										<div class="row">

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Service No.
													</label>
													<div class="col-sm-7">
														<input name="service_no." id="service" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Rank </label>
													<div class="col-sm-7">
														<input name="patients_name" id="rank" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Name </label>
													<div class="col-sm-7">
														<input name="relation" id="name" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Trade/Branch
													</label>
													<div class="col-sm-7">
														<input name="Trade/Branch" id="trade" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Total
														Service </label>
													<div class="col-sm-7">
														<input name="Total Service" id="totalservice" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Unit </label>
													<div class="col-sm-7">
														<input name="Unit" id="unit" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">
														Region/Command </label>
													<div class="col-sm-7">
														<input name="regioncommand" id="regioncommand" type="text"
															class="form-control border-input" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Record
														Office </label>
													<div class="col-sm-7">
														<input name="recordoffice" id="recordoffice" type="text"
															class="form-control border-input" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Medical
														Category </label>
													<div class="col-sm-7">
														<input name="Medical Category" id="medicalcategories"
															type="text" class="form-control border-input" value=""
															readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Duration </label>
													<div class="col-sm-7">
														<input name="Duration" id="duration" type="text"
															class="form-control border-input" value="" readonly>
													</div>
												</div>
											</div>

										</div>

									</div>
									<br>
									<!-- Service Detail end here -->

									<!-- Patient Detail Start Here -->
									<div class="opdMain_detail_area">

										<h4 class="service_htext">Patient Detail</h4>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Name </label>
													<div class="col-sm-7">
														<input name="empname" id="empname" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Relation</label>
													<div class="col-sm-7">
														<input name="relation" id="relation" type="text"
															class="form-control border-input" value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Gender </label>
													<div class="col-sm-7">
														<input name="Gender" id="gender" type="text"
															class="form-control border-input" value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">DOB </label>
													<div class="col-sm-7">
														<input name="DOB" id="dob" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Age </label>
													<div class="col-sm-7">
														<input name="Age" id="age" type="text"
															class="form-control border-input" value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Marital
														Status </label>
													<div class="col-sm-7">
														<input name="Marrital Status" id="Marrital Status"
															type="text" class="form-control border-input" value=""
															readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Religion </label>
													<div class="col-sm-7">
														<input name="Religion" id="religion" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Address </label>
													<div class="col-sm-7">
														<input name="Address" id="address" type="text"
															class="form-control border-input" value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">City </label>
													<div class="col-sm-7">
														<input name="City" id="city" type="text"
															class="form-control border-input" value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> State </label>
													<div class="col-sm-7">
														<input name="State" id="State" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Pincode </label>
													<div class="col-sm-7">
														<input name="Pin Code" id="pinCode" type="text"
															class="form-control border-input" value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Mobile No. </label>
													<div class="col-sm-7">
														<input name="Mobile No" id="mobileno" type="text"
															class="form-control border-input" value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> E-mail
														Address </label>
													<div class="col-sm-7">
														<input name="email" id="email" type="text"
															class="form-control border-input" value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Allergy </label>
													<div class="col-sm-7">
														<input name="Allergy" id="allergy" type="text"
															class="form-control border-input" placeholder="Allergy"
															value="" required />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> </label>
													<div class="col-sm-7"></div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> </label>
													<div class="col-sm-7"></div>
												</div>
											</div>

										</div>
									</div>
									<!-- Patient Detail end here -->
									<br>
									<!-- Previous visits & Chief Complaint  start here -->

									<div class="opdMain_detail_area">

										<h4 class="service_htext">Previous visits & Chief
											Complaint</h4>
										<div class="row">
											<div class="col-md-3">
												<div class="arrowlistmenu">
													<ul class="categoryitems">

														<li><a href="#" onclick=" "> Previous Visits </a>
														<li><a href="#" onclick=" ">Previous
																Hospitalization </a></li>
														<li><a href="#" onclick="">Previous Lab
																Investigation </a></li>
														<li><a href="#" onclick="">Previous Radio </a></li>
														<li><a href="#">Previous Medical Exam</a></li>

														<li><a href="#">Previous Vitals</a></li>
														<li><a href="#">EHR</a></li>
														<li><a href="#">Previous Medical Board</a></li>
														<li><a href="#">Upload View Document</a></li>
														<li><a href="#">Allergy</a></li>
														<li><a href="#">Immuzation History</a></li>
													</ul>
												</div>
											</div>

											<div class="col-md-9">

												<div class="opdArea">
													<div id=hospidataId style="display: none">
														<label>Hospital Name</label> <input type="text"
															name="hospName" tabindex="1" size="100" value=""
															maxlength="150" /> <input type="text" name="hospName"
															tabindex="1" size="100" value="" maxlength="150" /> <label
															class="auto">DOA</label> <input type="text" name="doa"
															class="date" id="doa" MAXLENGTH="30"
															validate="Pick a from date,date,no" value=""
															readonly="readonly"
															onblur="checkDate1(this.value,this.id)" /> <input
															type="text" name="doa" class="date" id="doa"
															MAXLENGTH="30" validate="Pick a from date,date,no"
															value="" readonly="readonly"
															onblur="checkDate1(this.value,this.id)" /> <img
															src="${pageContext.request.contextPath}/resources/images/cal.gif"
															width="16" height="16" border="0"
															onclick="javascript:setdate('doa',document.opdMain.doa, event)"
															validate="Pick a date" /> <label class="auto">DOD</label>
														<input type="text" name="dod" value="" class="date"
															id="dod" MAXLENGTH="30"
															validate="Pick a from date,date,no" readonly="readonly" />
														<input type="text" name="dod" value="" class="date"
															id="dod" MAXLENGTH="30"
															validate="Pick a from date,date,no" readonly="readonly"
															onblur="checkDate1(this.value,this.id)" /> <img
															src="${pageContext.request.contextPath}/resources/images/cal.gif"
															width="16" height="16" border="0"
															onclick="javascript:setdate('dod',document.opdMain.dod, event)"
															validate="Pick a date"
															onblur="checkDate1(this.value,this.id)" />
														<div class="clear"></div>
														<label>Diagnosis</label> <input type="text" class="auto"
															size="48" id="pastDiagnosis" tabindex="1" value=""
															name="pastDiagnosis" maxlength="100" />
														<div class="clear"></div>
														<label>Advise on Discharge</label>

														<textarea name="adviceOnDischarge" cols="0" rows="0"
															maxlength="300" value="" tabindex="1"
															onkeyup="return ismaxlength(this)"></textarea>

														<textarea name="adviceOnDischarge" cols="0" rows="0"
															maxlength="300" value="" tabindex="1"
															onkeyup="return ismaxlength(this)"></textarea>

													</div>

													<div class="floatLeft">

														<form>
															<div class="row">

																<div class="col-md-12">
																	<div class="form-group row">
																		<div class="col-sm-1"></div>
																		<label class="col-sm-3 col-form-label">Chief
																			Complaint </label>
																		<div class="col-sm-6">
																			<textarea class="textNew" name="chiefCompliant"
																				id="chiefCompliant" cols="0" rows="0" value=""
																				tabindex="1"></textarea>
																		</div>
																		<div class="col-sm-1">
																			<input type="button" class="button" tabindex="3"
																				name="" value="+" onclick="SelectName()" />
																		</div>
																		<div class="col-sm-1"></div>
																	</div>
																</div>

																<div class="col-md-12">
																	<div class="form-group row">
																		<div class="col-sm-1"></div>
																		<label class="col-sm-3 col-form-label">History
																			Of Present Illness </label>
																		<div class="col-sm-6">
																			<textarea class="textNew"
																				name="historyPresentIllness"
																				id="presentIllnessHistory" cols="0" rows="0"
																				value="" tabindex="1"></textarea>

																		</div>
																		<div class="col-sm-1">
																			<input type="button" class="button" tabindex="3"
																				name="" value="+" onclick="SelectName()" />
																		</div>
																		<div class="col-sm-1"></div>

																	</div>
																</div>

																<div class="col-md-12">
																	<div class="form-group row">
																		<div class="col-sm-1"></div>
																		<label class="col-sm-3 col-form-label">Past
																			Medical History </label>
																		<div class="col-sm-6">
																			<textarea class="textNew" name="pastMedicalHistory"
																				id="pastMedicalHistory" cols="0" rows="0" value=""
																				tabindex="1"></textarea>
																		</div>
																		<div class="col-sm-1">
																			<input type="button" class="button" tabindex="3"
																				name="" value="+"
																				onclick="getPresentTemplate('','pastMedicalHistory');" />
																		</div>

																		<div class="col-sm-1"></div>

																	</div>
																</div>

																<div class="col-md-12">
																	<div class="form-group row">
																		<div class="col-sm-1"></div>
																		<label class="col-sm-3 col-form-label">Present
																			Surgical History </label>
																		<div class="col-sm-6">
																			<textarea class="textNew" name="surgicalHistory"
																				id="surgicalHistory" cols="0" rows="0" value=""
																				tabindex="1"></textarea>
																		</div>
																		<div class="col-sm-1">
																			<input type="button" class="button" tabindex="3"
																				name="" value="+" onclick="SelectName()" />
																		</div>
																		<div class="col-sm-1"></div>

																	</div>
																</div>

																<div class="col-md-12">
																	<div class="form-group row">
																		<div class="col-sm-1"></div>
																		<label class="col-sm-3 col-form-label">Medication
																			History </label>
																		<div class="col-sm-6">
																			<textarea class="textNew" name="medicationHistory"
																				id="medicationHistory" cols="0" rows="0" value=""
																				tabindex="1"></textarea>
																		</div>
																		<div class="col-sm-1">
																			<input type="button" class="button" tabindex="3"
																				name="" value="+"
																				onclick="getPresentTemplate('','pastMedicalHistory');" />
																		</div>
																		<div class="col-sm-1"></div>

																	</div>
																</div>

																<div class="col-md-12">
																	<div class="form-group row">
																		<div class="col-sm-1"></div>
																		<label class="col-sm-3 col-form-label">Personal
																			History </label>
																		<div class="col-sm-6">
																			<textarea class="textNew" name="personalHistory"
																				id="personalHistory" cols="0" rows="0" value=""
																				tabindex="1"></textarea>
																		</div>
																		<div class="col-sm-1">
																			<input type="button" class="button" tabindex="3"
																				name="" value="+"
																				onclick="getPresentTemplate('','pastMedicalHistory');" />

																		</div>
																		<div class="col-sm-1"></div>

																	</div>
																</div>
																<div class="col-md-12">
																	<div class="form-group row">
																		<div class="col-sm-1"></div>
																		<label class="col-sm-3 col-form-label">Social
																			History </label>
																		<div class="col-sm-6">
																			<textarea class="textNew" name="socialHistory"
																				id="socialHistory" cols="0" rows="0" value=""
																				tabindex="1"></textarea>
																		</div>
																		<div class="col-sm-1">
																			<input type="button" class="button" tabindex="3"
																				name="" value="+"
																				onclick="getPresentTemplate('','pastMedicalHistory');" />
																		</div>
																		<div class="col-sm-1"></div>

																	</div>
																</div>
																<div class="col-md-12">
																	<div class="form-group row">
																		<div class="col-sm-1"></div>
																		<label class="col-sm-3 col-form-label">Family
																			History </label>
																		<div class="col-sm-6">
																			<textarea class="textNew" name="familyHistory"
																				id="familyHistory" cols="0" rows="0" value=""
																				tabindex="1"></textarea>
																		</div>
																		<div class="col-sm-1">
																			<input type="button" class="button" tabindex="3"
																				name="" value="+"
																				onclick="getPresentTemplate('','pastMedicalHistory');" />
																		</div>
																		<div class="col-sm-1"></div>

																	</div>
																</div>
																<div class="col-md-12">
																	<div class="form-group row">
																		<div class="col-sm-1"></div>
																		<label class="col-sm-3 col-form-label">Allergy
																			History </label>
																		<div class="col-sm-6">
																			<textarea class="textNew" name="allergyHistory"
																				id="allergyHistory" cols="0" rows="0" value=""
																				tabindex="1"></textarea>
																		</div>
																		<div class="col-sm-1">
																			<input type="button" class="button" tabindex="3"
																				name="" value="+"
																				onclick="getPresentTemplate('','pastMedicalHistory');" />
																		</div>
																		<div class="col-sm-1"></div>

																	</div>
																</div>
																<div class="col-md-12">
																	<div class="form-group row">
																		<div class="col-sm-1"></div>
																		<label class="col-sm-3 col-form-label">Implant
																			History </label>
																		<div class="col-sm-6">
																			<textarea class="textNew" name="implantHistory"
																				id="implantHistory" cols="0" rows="0" value=""
																				tabindex="1"></textarea>
																		</div>
																		<div class="col-sm-1">
																			<input type="button" class="button" tabindex="3"
																				name="" value="+"
																				onclick="getPresentTemplate('','pastMedicalHistory');" />

																		</div>
																		<div class="col-sm-1"></div>

																	</div>
																</div>

															</div>

														</form>

													</div>

												</div>
											</div>

										</div>
									</div>
									<!-- Previous visits & Chief Complaint  end here -->
									<br>
									<!-- Vitals  start here -->
									<div class="opdMain_detail_area">
										<h4 class="service_htext">Vital Detail</h4>
										<div class="row">

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Height (in cm) </label>
													<div class="col-sm-7">
														<input name="height" id="height" type="text"
															class="form-control border-input" onblur="idealWeight();checkBMI();" placeholder="Height"
															value="" required />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Ideal Weight
													</label>
													<div class="col-sm-7">
														<input name="ideal_weight" id="ideal_weight" onblur="checkVaration()" type="text"
															class="form-control border-input"
															placeholder="Ideal Weight" />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Weight (in kg) </label>
													<div class="col-sm-7">
														<input name="Weight" id="weight" type="text"
															class="form-control border-input" onblur="checkVaration();checkBMI();" placeholder="Weight" />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Variation in
														Weight </label>
													<div class="col-sm-7">
														<input name="variant_in_weight" id="variant_in_weight"
															type="text" class="form-control border-input"
															placeholder="Variation in Weight" value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Temperature (in �C)</label>
													<div class="col-sm-7">
														<input name="tempature" id="tempature" type="text"
															class="form-control border-input"
															placeholder="Temperature" value="" required>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">BP </label>
													<div class="col-sm-7">
														<input name="bp" id="bp" type="text"
															class="form-control border-input" placeholder="bp"
															value="" required>
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Pulse (in bpm) </label>
													<div class="col-sm-7">
														<input name="pulse" id="pulse" type="text"
															class="form-control border-input" placeholder="Pulse"
															value="" required />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">SpO2 (in %) </label>
													<div class="col-sm-7">
														<input name="spo2" id="spo2" type="text"
															class="form-control border-input" placeholder="SpO2"
															value="" required>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">BMI </label>
													<div class="col-sm-7">
														<input name="bmi" id="bmi" type="text"
															class="form-control border-input" placeholder="BMI"
															value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">RR (in bpm) </label>
													<div class="col-sm-7">
														<input name="rr" id="rr" type="text"
															class="form-control border-input" placeholder="RR"
															value="" required>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<input type="hidden" id="patientId" name="PatientID"
														value="">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<input type="hidden" id="visitId" name="VisitID" value="">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<input type="hidden" id="genderId" name="genderId" value="">
												</div>
											</div>
											<div class="col-md-4">
												<span class="checkmark"></span> <label class="container">Obesity
													Mark <input type="checkbox" id="obsistyMark"
													onclick="obsistyFunction()">
												</label>
											</div>

										</div>
									</div>
									<!-- Vitals  end here -->

									<!-- Examination  start here -->
									<div class="opdMain_detail_area">
										<h4 class="service_htext">Examination</h4>
										<div class="row">
											<div class="col-md-12">

												<!-- General Examination  start here -->
												<h5>General Examination</h5>
												<div class="row">
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Pallor </label>
															<div class="col-sm-7">
																<input name="Pollar" id="pollar" type="text"
																	class="form-control border-input" placeholder="Pallor"
																	value="" required />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Edema </label>
															<div class="col-sm-7">
																<input name="Ordema" id="ordema" type="text"
																	class="form-control border-input" placeholder="Edema" />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Cyanosis </label>
															<div class="col-sm-7">
																<input name="cyanosis" id="cyanosis" type="text"
																	class="form-control border-input"
																	placeholder="Cyanosis" />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Hair/ Nail
															</label>
															<div class="col-sm-7">
																<input name="hairnail" id="hairnail" type="text"
																	class="form-control border-input"
																	placeholder="Hair/ Nail" value="" required />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Jaundice </label>
															<div class="col-sm-7">
																<input name="Jaundice" id="jaundice" type="text"
																	class="form-control border-input"
																	placeholder="Jaundice" value="" required>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Lymph node</label>
															<div class="col-sm-7">
																<input name="Lymph node" id="lymphnode" type="text"
																	class="form-control border-input"
																	placeholder="Lymph node" value="" required>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Clubbing </label>
															<div class="col-sm-7">
																<input name="Clubbing" id="clubbing" type="text"
																	class="form-control border-input"
																	placeholder="Clubbing" value="" required />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Thyroid </label>
															<div class="col-sm-7">
																<input name="Thyroid" id="thyroid" type="text"
																	class="form-control border-input" placeholder="Thyroid"
																	value="" required>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Tremors </label>
															<div class="col-sm-7">
																<input name="Tremors" id="tremors" type="text"
																	class="form-control border-input" placeholder="Tremors"
																	value="" required>
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Others </label>
															<div class="col-sm-7">
																<input name="Others" id="others" type="text"
																	class="form-control border-input" placeholder="Others"
																	value="" required>
															</div>
														</div>
													</div>

												</div>

												<!-- General Examination  end here -->

												<!-- System Examination  start here -->

												<h5>System Examination</h5>
												<div class="row">
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">CNS </label>
															<div class="col-sm-7">
																<input name="CNS" id="cns" type="text"
																	class="form-control border-input" placeholder="CNS"
																	value="" required />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Chest/
																Resp </label>
															<div class="col-sm-7">
																<input name="Chest" id="chest" type="text"
																	class="form-control border-input"
																	placeholder="Chest/ Resp" />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Musculoskeletal
															</label>
															<div class="col-sm-7">
																<input name="Musculoskeletal" id="musculoskeletal"
																	type="text" class="form-control border-input"
																	placeholder="Musculoskeletal" />
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">CVS </label>
															<div class="col-sm-7">
																<input name="CVS" id="cvs" type="text"
																	class="form-control border-input" placeholder="CVS"
																	value="" required />
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Skin </label>
															<div class="col-sm-7">
																<input name="Skin" id="skin" type="text"
																	class="form-control border-input" placeholder="Skin"
																	value="" required>
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">GI </label>
															<div class="col-sm-7">
																<input name="GI" id="gi" type="text"
																	class="form-control border-input" placeholder="GI"
																	value="" required>
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Geneto
																Urinary</label>
															<div class="col-sm-7">
																<input name="geneticurinary" id="geneticurinary"
																	type="text" class="form-control border-input"
																	placeholder="Geneto Urinary" value="" required />
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Others </label>
															<div class="col-sm-7">
																<input name="Others" id="others1" type="text"
																	class="form-control border-input" placeholder="Others"
																	value="" required>
															</div>
														</div>
													</div>

												</div>

												<!-- System Examination  end here -->

											</div>
										</div>
									</div>
									<!-- -- Examination End Here -->
									<br>
									<!-- DIAGNOSIS  start here -->

									<div class="opdMain_detail_area">
										<h4 class="service_htext">Diagonsis</h4>
										<div class="row">

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Working
														Diagnosis </label>
													<div class="col-sm-7">
														<input name="workingdiagnosis" id="workingdiagnosis"
															type="text" class="form-control border-input"
															placeholder="Working Diagnosis" value="" required />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">SNOMED
														Diagnosis </label>
													<div class="col-sm-7">
														<input name="snomeddiagnosis" id="snomeddiagnosis"
															type="text" class="form-control border-input"
															placeholder="SNOMED diagnosis" value="" required>
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> </label>
													<div class="col-sm-7"></div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row  autocomplete">

													<label class="col-sm-5 col-form-label">ICD
														Diagnosis </label>
													<div class="col-sm-7">
														<input name="icddiagnosis" id="icd" type="text"
															class="form-control border-input"
															placeholder="ICD Diagnosis" value="" required
															onblur="fillDiagnosisCombo(this.value);" />
													</div>

												</div>
											</div>

											<div class="col-md-8">
												<select name="diagnosisId" multiple="4" size="5"
													tabindex="1" id="diagnosisId" class="listBig"
													validate="ICD Daignosis,string,yes">
												</select>
												<button type="button" class="buttonDel btn  btn-danger"
													value="" onclick="deleteDgItems(this,'diagnosisId');"
													align="right" />
												Delete
												</button>

												<input type="checkbox" onclick="addNAIcd('"
													style="margin-top: 0px; margin-right: 5px;"> Not
												available
											</div>

										</div>
									</div>
									<!-- DIAGNOSIS  end here -->

									<br>
									<!-- Investigation  start here -->

									<div class="opdMain_detail_area">
										<h4 class="service_htext">Investigation</h4>

										<div class="Block">
											<div class="row"
												style="border: 1px solid #d6d6d6; margin-bottom: 10px; padding-top: 9px; padding-left: 4px;">

												<div class="col-md-4">
													<div class="form-group row">
														<label class="col-sm-6 col-form-label"> Template </label>
														<div class="col-sm-6">
															<div id="investigationDiv">
																<select name="investigationTemplateId"
																	id="dgInvestigationTemplateId" tabindex="1">
																</select>
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-2">
													<div class="form-group row">
														<div class="col-sm-12">
															<div id="createInvestigationDivToShowHide">
																<input name="investigationTemplate" type="button"
																	value="Create Template" tabindex="1" class="buttonBig"
																	onclick="showCreateInvestigationTemplate();" />
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-2">
													<div class="form-group row">
														<div class="col-sm-12">
															<div>
																<input name="createupdateTemplate" tabindex="1"
																	type="button" value="Update Template" class="buttonBig"
																	onclick="showUpdateOpdTempate('I');" />
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-2">
													<div class="form-group row">
														<div class="col-sm-12">
															<div id="investigationImportButton1">
																<input name="investigationImportButton1" tabindex="1"
																	type="button" value="Import New" class="button"
																	onclick="testPrescriptionData()" />
															</div>
														</div>
													</div>
												</div>


												<!--       <div class="col-md-2">
                                                                    <div class="form-group row">
                                                                          <label class="col-sm-5 col-form-label"> Urgent  </label>
                                                                        <div class="col-sm-7">
                                                                            <input type="checkbox" name="urgent" tabindex="1" class="radioAuto" value="1" />  
                                                                        </div>
                                                                    </div>
                                                                </div> -->


											</div>




											<div class="row">
												<div class="col-md-1">
													<div class="form-group row">

														<div class="col-sm-1">
															<input type="radio" value="1" class="radioCheckCol2"
																name="labradiologyCheck" checked="checked"
																onchange="changeRadio(this.value)" />
														</div>
														<label
															style="font: bold 12px/16px Arial, tahoma; color: #000000; background: none; text-align: left; back padding: 0px 9px;"
															class="col-sm-5 col-form-label labRadiologyDivfixed">Lab</label>
													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group row">

														<div class="col-sm-1">
															<input type="radio" value="2" class="radioCheckCol2"
																name="labradiologyCheck"
																onchange="changeRadio(this.value)" />

														</div>
														<label
															style="font: bold 12px/16px Arial, tahoma; color: #000000; background: none; text-align: left; padding: 0px 9px;"
															class="col-sm-5 col-form-label labRadiologyDivfixed">Imaging</label>
													</div>
												</div>
												<div class="col-md-8">
													<div class="form-group row"></div>
												</div>


											</div>


											<div class="Block1">









												<!--  <div class="floatleft">
                                                                                    <span style="float:left;">
                                                                                    <input type="radio" value="1" class="radioCheckCol2" style="float:left;margin-right: 6px;" name="labradiologyCheck" checked="checked" onchange="changeRadio(this.value)" />
                                                                                    <div class="labRadiologyDivfixed" style="float:right">LAB</div>
                                                                                    </span>
                                                                                     <span>
                                                                                    <input type="radio" value="2" class="radioCheckCol2"  style="margin-left:12px;margin-right: 6px;" name="labradiologyCheck" onchange="changeRadio(this.value)" />
                                                                                    <span class="labRadiologyDivfixed">Radiology</span>
                                                                                     </span>
                                                                                    <input type="hidden" name="investigationCategory" id="investigationCategory" />
                                                                                    <div class="clear"></div>
                                                                                </div>
                                                                                
                                                                                
                                                                                 <br> -->
												<div id="gridview">
													<table class="table table-bordered" border="0"
														align="center" cellpadding="0" cellspacing="0"
														id="investigationGrid">
														<thead>
															<tr class="table-primary">
																<th>Investigation</th>

																<th>Date</th>
																<th>Mark as Other AF Lab</th>
																<th>Mark as Outside AF Lab</th>
																<th>Urgent</th>
																<th>Add</th>
																<th>Delete</th>
															</tr>
														</thead>

														<tbody id="dgInvetigationGrid">
															<tr>

																<td>
																	<div class="autocomplete">
																		<input type="text" value="" id="chargeCodeName"
																			class="form-control border-input"
																			name="chargeCodeName"
																			onblur="populateChargeCode(this.value);putInvestigationValue(this)" />
																		<input type="hidden" id="qty" tabindex="1" name="qty1"
																			size="10" maxlength="6" validate="Qty,num,no" /> <input
																			type="hidden" tabindex="1" id="chargeCodeCode"
																			name="chargeCodeCode" size="10" readonly />
																	</div>
																</td>

																<%-- <td><input type="checkbox" name="referToMh<%=inc %>" tabindex="1" id="referToMhId
                                                                                <%=inc%>" value="y" class="radio" validate="Refer to MH,string,no" /></td> --%>
																<td><input type="Date" id="investigationDate1"
																	name="investigationDate" class="input_date"
																	placeholder="DD/MM/YYYY" value="" maxlength="10" /></td>
																<td>
																	<div class="labRadiologyDivfixed"></div> <input
																	type="radio" value="I" id="othAfLab10"
																	class="radioCheckCol2" style="margin-right: 15px;"
																	name="labradiologyCheck1" />
																</td>
																<td>
																	<div class="labRadiologyDivfixed"></div> <input
																	type="radio" value="O" id="othAfLab10"
																	class="radioCheckCol2" style="margin-right: 15px;"
																	name="labradiologyCheck1" " />
																</td>
																<td><input type="checkbox" name="urgent"
																	id="uCheck" tabindex="1" class="radioAuto" value="1" />
																</td>
																<td style="display: none";><input type="hidden"
																	value="" tabindex="1" id="inestigationIdval" size="77"
																	name="inestigationIdval" /></td>

																 <td>
                                                                  <button type="button" type="button" class="btn btn-primary buttonAdd" value="" tabindex="1" onclick="addRowForInvestigation();">Add</button>
                                                                  </td>
																<td>
																	<button type="button" name="delete" value=""
																		class="buttonDel btn btn-danger" tabindex="1"
																		onclick="removeRow('investigationGrid','hiddenValue',this);">
																		Delete</button>
																</td>

															</tr>
														</tbody>
														<input type="hidden" value="1" name="hiddenValue"
															id="hiddenValue" />

													</table>
													<br> <label>Other Investigation</label>
													<textarea name="otherInvestigation" cols="50" rows="0"
														maxlength="500" tabindex="1"
														onkeyup="return ismaxlength(this)" class="auto"></textarea>
													<div class="clear paddingTop15"></div>
													<table border="0" align="center" cellpadding="0"
														cellspacing="0" id="investigationGrid1">
													</table>

												</div>
											</div>


										</div>
									</div>

									<!-- Investigation  end here -->

									<br>

									<!-- Treatment  start here -->
									<div class="opdMain_detail_area">
										<h4 class="service_htext">Treatment</h4>
										<div class="Block">

											<div class="row"
												style="border: 1px solid #d6d6d6; margin-bottom: 10px; padding-top: 9px; padding-left: 4px;">

												<div class="col-md-5">
													<div class="form-group row">
														<label class="col-sm-6 col-form-label"> Template </label>
														<div class="col-sm-6">
															<div id="investigationDiv">
																<select name="investigationTemplateId"
																	id="investigationTemplateId" tabindex="1">
																	<%-- <select name="investigationTemplateId"	tabindex="1" onchange="showHideInvestigationTemplateCombo(this.value);">--%>
																	<option value="0">Select</option>
																</select>
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-2">
													<div class="form-group row">
														<div id="createInvestigationDivToShowHide">
															<input name="investigationTemplate" type="button"
																value="Create Template" tabindex="1" class="buttonBig"
																onclick="createTreatmentTemplate();" />
														</div>
													</div>
												</div>

												<div class="col-md-2">
													<div class="form-group row">
														<div>
															<input name="createupdateTemplate" tabindex="1"
																type="button" value="Update Template" class="buttonBig"
																onclick="showUpdateOpdTempate('I');" />
														</div>
													</div>
												</div>



												<div class="col-md-2">
													<div class="form-group row">
														<div id="investigationImportButton1">
															<input name="investigationImportButton1" tabindex="1"
																type="button" value="Import New" class="button"
																onclick="referalJson()" ;" />
														</div>
													</div>
												</div>





											</div>

										</div>
										<h6>Current Medication</h6>

										<table class="table table-bordered" align="center"
											cellpadding="0" cellspacing="0">
											<tr>
												<th>Nomenclature/Material Code</th>
												<th scope="col">Disp. Unit</th>
												<th scope="col">Dosage<span>*</span></th>
												<th scope="col">Frequency<span>*</span></th>
												<th scope="col">Days<span>*</span></th>
												<th scope="col">Total<span>*</span></th>
												<th scope="col">Instructions</th>
												<th scope="col">Stock</th>

												<th>Add</th>
												<th>Delete</th>

											</tr>
											<tbody id="nomenclatureGrid">
												<tr>

													<td>
														<div class="form-group row  autocomplete">
															<input type="text" value="" tabindex="1"
																id="nomenclature1" name="nomenclature1"
																class="form-control border-input"
																onblur="populatePVMS(this.value,'1');putPvmsValue(this);" />

														</div>
													</td>

													<td><input type="text" name="dispensingUnit1"
														tabindex="1" id="dispensingUnit1" size="6"
														validate="AU,string,no" readonly="readonly" /></td>

													<td><input type="text" name="dosage1" tabindex="1"
														value="" id="dosage1" size="5" maxlength="5" /></td>

													<td><select name="frequency1" id="frequency1"
														class="medium">

													</select></td>

													<td><input type="text" name="noOfDays1" tabindex="1"
														id="noOfDays1" onblur="fillValueNomenclature('1')"
														size="5" maxlength="3" validate="No of Days,num,no" /></td>

													<td><input type="text" name="total1" tabindex="1"
														id="total1" size="5" validate="total,num,no"
														onblur="treatmentTotalAlert(this.value,1)" /></td>

													<td><input type="text" name="remarks1" tabindex="1"
														id="remarks1" size="10" maxlength="15" placeholder="1-1-1" />
													</td>

													<td><input type="text" name="closingStock1"
														tabindex="1" value="" id="closingStock1" size="3"
														validate="closingStock,string,no" /></td>
													<td style="display: none;"><input type="hidden"
														value="" tabindex="1" id="itemIdNom" size="77"
														name="itemIdNom" /></td>
													<td>

														<button type="button" class="btn btn-primary buttonAdd"
															name="button" type="button" value=""
															onclick="addNomenclatureRow();" tabindex="1">Add</button>

													</td>
													<td>


														<button type="button" class="buttonDel  btn btn-danger"
															name="button" type="button" value=""
															onclick="removeTreatmentRow(this);""
															tabindex="1">Delete</button>

													</td>
													<td style="display: none;"><input type="hidden"
														name="pvmsNo1" tabindex="1" id="pvmsNo1" size="10"
														readonly="readonly" /></td>
												</tr>

											</tbody>
											<tr>
										</table>


										<!-- <div class="clear"></div>
								<div class="paddingTop40"></div>
								<div class="clear"></div>
								<div class="paddingTop40"></div>
								<div class="clear"></div> -->
										<!-- <br><br><br>	<br><br><br>	<br><br><br> -->



									</div>



									<!-- ------   Treatment end here-- -->


									<br>


									<!-- ------   Procedure Care start here-- -->



									<div class="opdMain_detail_area">




										<h4 class="service_htext">Procedure</h4>



										<div class="Block">








											<div class="row">


												<div class="col-md-1"></div>

												<div class="col-md-3">
													<div class="form-group row">
														<div class="col-sm-1">
															<input class="form-check-input" value="N" type="radio"
																checked="checked" name="procedureRadioName"
																id="procedureRadio"
																onchange="changeProcedureRadio(this.value)">
														</div>
														<label class="col-sm-5 col-form-label">Nursing
															Care</label>

													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group row">
														<div class="col-sm-1">
															<input class="form-check-input" value="P" type="radio"
																name="procedureRadioName" id="procedureRadio"
																onchange="changeProcedureRadio(this.value)">
														</div>
														<label class="col-sm-5 col-form-label">Physiotherapy
														</label>

													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group row">
														<div class="col-sm-1">
															<input class="form-check-input" value="M" type="radio"
																name="procedureRadioName" id="procedureRadio"
																onchange="changeProcedureRadio(this.value)">
														</div>
														<label class="col-sm-5 col-form-label"> Minor
															Surgery</label>

													</div>
												</div>

												<div class="col-md-2"></div>

											</div>

											<div id="divTemplet">

												<table class="table table-bordered" align="center"
													cellpadding="0" cellspacing="0">
													<tr>
														<th>Name</th>
														<th>Frequency<span>*</span></th>
														<th>No.Of Days<span>*</span></th>
														<th>Remarks</th>
														<th>Add</th>
														<th>Delete</th>
														<!-- <th>Alert Me</th> -->
													</tr>
													<tbody id="gridNursing">
														<tr>
															<td>
																<div class="form-group row  autocomplete">
																	<input type="text" class="form-control border-input"
																		value="" id="procedureNameNursing" size="55"
																		name="procedureNameNursing"
																		onblur="populateNursing(this.value,'1');calucateNursingValue(this);" />
																</div>
															</td>

															<td>
																<%-- onchange="populateNoOfDaysForNursingProcedure(this.value, '')" --%>
																<select name="frequency_nursing" id="frequency_nursing"
																tabindex="36">
																	<option value="">Select</option>

															</select>

															</td>

															<td><input type="text" name="noOfDays_nursing"
																id="noOfDays_nursing" value=""
																class="opdTextBoxTSmall textYellow" size="5"
																validate="No. of days,num,no" /></td>
															<td><input value="" type="text"
																name="remark_nursing" id="remark_nursing"
																class="largTextBoxOpd textYellow" maxlength="100" /></td>
															<td style="display: none;"><input type="hidden"
																value="" id="procedureNameNursingId"
																name="procedureNameNursingId" /></td>
															<td style="display: none;"><input type="hidden"
																class="form-control border-input" value=""
																id="procedureNameNursing" size="55"
																name="procedureNameNursing" />
															<td style="display: none;"><input type="hidden"
																class="form-control border-input" value=""
																id="procedureNameNursingCare" size="55"
																name="procedureNameNursingCare" /></td>
															<td>
																<button type="button" class="buttonAdd btn btn-primary"
																	alt="Add" tabindex="4" value=""
																	onclick="addRowTreatmentNursingCare();">Add</button>
															</td>
															<td>
																<button type="button" class="buttonDel btn btn-danger"
																	tabindex="3" alt="Delete" value=""
																	onclick="removeProcedureRow(this);">Delete</button>
															</td>

															<td style="display: none;"><input type="hidden"
																id="procedureHeaderId" name="procedureHeaderId" value="" />
															</td>
															<td style="display: none;"><input type="hidden"
																name="nursinghdb" value="" id="nursinghdb" /></td>
														</tr>
													</tbody>
												</table>
												<label>Additional Advice</label>
												<textarea name="additionalNote"
													validate="referralNote,string,no" id="additionalNote"
													cols="0" rows="0" maxlength="500" tabindex="5"
													onkeyup="return checkLength(this)" style="width: 477px;"></textarea>


											</div>

											<!-- ------   Treatment end here-- -->



											<br>



											<!-- Referral  start here -->
											<div class="opdMain_detail_area">

												<div id="referalDiv">
													<h4 class="service_htext">REFERRAL</h4>
													<div class="row">
														<div class="col-md-4">
															<div class="form-group row">

																<label class="col-sm-5 col-form-label">Referral</label>

																<div class="col-md-7">
																	<select id="referral" name="referral" class="midium">
																		<option value="0">No</option>
																		<option value="1">Yes</option>
																	</select>


																</div>

															</div>

														</div>



														<div class="col-md-4"></div>
														<div class="col-md-4"></div>


													</div>

													<!-- <label>Referral </label>
												 <select  style=" width: 10%;" id="referral" name="referral" class="midium">
													<option value="0">No</option>
													<option value="1">Yes</option>
												</select>
                                                  <br><br><br> -->
													<div id="referDiv" class="col collaps"
														style="display: block;">
														<label>Refer To</label> <label class="autoSpace">
															<input type="radio" class="radioCheckCol2" name="referTo"
															id="referExternal" value="E"
															onclick="checkReferTO('referExternal');"
															style="margin: 1px 5px 0px 0px;" checked="checked">External
														</label> <label class="autoSpace"> <input type="radio"
															class="radioCheckCol2" name="referTo" id="referInternal"
															value="I" onclick="checkReferTO('referInternal');"
															style="margin: 1px 5px 0px 0px;">Internal
														</label>


														<!--  <label
											class="autoSpace"><input type="radio"
											class="radioCheckCol2" name="referTo" id="referBoth"
											value="Both" onclick="checkReferTO('Both');"
											style="margin: 1px 5px 0px 0px;" />Both
											</label> -->

														<div class="clear"></div>
														<label>Referral Date: </label> <input type="Date"
															name="referVisitDate" id="referVisitDate"
															placeholder="DD/MM/YYYY" value="">

														<div class="clear"></div>
														<!-- onblur="checkAdmte()" -->
														<!-- <label id="priorityLabelId" style="display: block;">Current Proirity No.</label>
													 <select id="priorityId" name="priorityName" tabindex="1" style="display: block;">
														<option value="3">3</option>
														<option value="2">2</option>
														<option value="1">1</option>
													</select> -->
														<br>
														<!-- <div class="clear"></div> -->
														<div id="referDepartmentDiv" style="display: block;">
															<div class="clear"></div>
															<table id="referGrid" class="table table-bordered">
																<tbody>
																	<tr>
																		<th>S.No.</th>
																		<th>Hospital</th>
																		<th>Department</th>
																		<th>Diganosis</th>
																		<th>Instruction</th>
																		<th scope="col">Add</th>
																		<th scope="col">Delete</th>
																	</tr>
																</tbody>
																<tbody id="referalGrid">

																</tbody>
															</table>

															<input type="hidden" value="1" name="hiddenValueRefer"
																id="hiddenValueRefer">
														</div>
														<label id="referhospitalLabel" style="display: none;">Hospital
															<span>*</span>
														</label> <select id="referhospital" name="referhospital"
															onchange="fnGetHospitalDepartment(this.value);"
															style="display: none;" validate="">
															<option value="0">Select</option>

															<option value=""></option>

														</select> <label id="referdayslLabel" style="display: none;">No.
															of Days</label> <input id="referdays" name="referdays"
															type="text" maxlength="2" style="display: none;">

														<div class="clear"></div>


														<div class="clear"></div>
														<label style="display: none">Patient Advise</label>
														<textarea name="patientAdvise"
															validate="patientAdvise,string,no" id="patientAdvise"
															cols="0" rows="0" maxlength="500" tabindex="5"
															onkeyup="return checkLength(this)" style="display: none"></textarea>
														<!-- <input type="button" class="buttonAuto-buttn" value="+"
										onclick="" /> -->
														<label id="referral_treatment_type_label"
															style="display: none">Treatment Type <span>*</span></label>
														<select id="referral_treatment_type"
															name="referral_treatment_type" style="display: none">
															<option value="1" selected="true">OPD</option>
															<option value="2">Admission</option>
														</select> <label id="referredForLabel" style="display: none">Referred
															For<span>*</span>
														</label> <input id="referredFor" name="referredFor" type="text"
															maxlength="300" validate="" style="display: none">
														<div class="clear"></div>
														<label>Referral Notes</label>
														<textarea name="referralNote"
															validate="referralNote,string,no" id="referralNote"
															cols="0" rows="0" maxlength="500" tabindex="5"
															onkeyup="return checkLength(this)" style="width: 477px;"></textarea>
														<!-- <input type="button" class="buttonAuto-buttn" value="+"
										onclick="" /> -->
													</div>

												</div>


											</div>
											<!-- Referral  end here -->

											<br>

											<!-- Disposal start here -->
											<div class="opdMain_detail_area">
												<h4 class="service_htext">Disposal</h4>

												<div class="row">
													<div class="col-md-5">
														<div class="form-group row">

															<label class="col-sm-5 col-form-label">Disposal
																Type</label>

															<div class="col-sm-5">
																<select name="disposalId" id="disposalId" class="medium">

																</select>
															</div>


														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">

															<label class="col-sm-3 col-form-label">Days</label>

															<div class="col-sm-7">
																<input type="text" lass="form-control border-input"
																	name="disposalDays" tabindex="1" value=""
																	id="disposalDays" size="5" maxlength="5" />
															</div>
														</div>
													</div>



													<div class="col-md-3"></div>



												</div>
												<div class="row">
													<div class="col-md-9"></div>

													<div class="col-md-3">
														<input type="submit" id="clicked" class="btn btn-info"
															value="Submit"> <input type="submit" id="reset"
															class="btn btn-info btn-fill btn-wd" value="Reset">
													</div>

												</div>
											</div>
										</div>

										<!-- Disposal end here -->
								</form>

							</div>

						</div>
					</div>
				</div>

			</div>
		</div>
	</div>


	<script>

var checkBox='';

function obsistyFunction() {
	
		 checkBox = document.getElementById("obsistyMark");
		  var text = document.getElementById("text");
		  if (checkBox.checked == true){
		    text.style.display = "block";
		  } else {
		     text.style.display = "none";
		  }
		
	
}
</script>

	<script>
            /*An array containing all the country names in the world:*/
            //var countries = ["Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla","Antigua & Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia & Herzegovina","Botswana","Brazil","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands","Central Arfrican Republic","Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica","Cote D Ivoire","Croatia","Cuba","Curacao","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kiribati","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Myanmar","Namibia","Nauro","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","North Korea","Norway","Oman","Pakistan","Palau","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre & Miquelon","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Korea","South Sudan","Spain","Sri Lanka","St Kitts & Nevis","St Lucia","St Vincent","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad & Tobago","Tunisia","Turkey","Turkmenistan","Turks & Caicos","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States of America","Uruguay","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam","Virgin Islands (US)","Yemen","Zambia","Zimbabwe"];
            var arry = new Array();
            var icdArry = new Array();
            
            autocomplete(document.getElementById("icd"), arry);

                   
            var arryInvestigation = new Array();
            var inVal=$('#dgInvetigationGrid').children('tr:first').children('td:eq(0)').find(':input')[0]
            autocomplete(inVal, arryInvestigation);
            
             var arryNomenclature= new Array();
             var val=$('#nomenclatureGrid').children('tr:first').children('td:eq(0)').find(':input')[0]
             autocomplete(val, arryNomenclature);
             
            var arryProcedureCare= new Array();
            var valProcedure=$('#gridNursing').children('tr:first').children('td:eq(0)').find(':input')[0]
            autocomplete(valProcedure, arryProcedureCare); 
             
            // autocomplete(document.getElementById("procedureNameNursing"), arryProcedureCare);
             
                 
        </script>

	<script type="text/javascript">
            var popup;

            function SelectName() {
                popup = window.open("getFamilyPatinetHistory?employeeId="+<%=userId%>+"", "popUpWindow", "height=500,width=400,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
                popup.focus();
            }
        </script>

	<script type="text/javascript">
            var popup;

            function showCreateInvestigationTemplate() {
            	
                popup = window.open("showCreateInvestigationTemplate?employeeId="+<%= userId %>+"", "popUpWindow", "height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
                popup.focus();
            }
            
            function createTreatmentTemplate() {
                popup = window.open("createTreatmentTemplate?employeeId="+<%= userId %>+"", "popUpWindow", "height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
                popup.focus();
            }

            function showUpdateInvestigationTemplate() {
                popup = window.open("showUpdateInvestigationTemplate?employeeId="+<%= userId %>+"", "popUpWindow", "height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
                popup.focus();
            }
        </script>


	<script type="text/javascript" language="javascript">
            $(document).ready(
            	
            		
                function() {
                	
                    var pathname = window.location.pathname;
                    var accessGroup = pathname.split("/")[1];

                    var url = window.location.protocol + "//" + window.location.host + "/" + accessGroup + "/v0.1/opd/getIcdList";

                    //var data = $('employeeId').val();

                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: url,
                        data: JSON.stringify({
                            'employeeId': <%= userId %>
                        }),
                        dataType: 'json',
                        timeout: 100000,

                        success: function(res)

                        {
                        	icdData = res.ICDList;
                        	autoIcdCode=res.ICDList;
                            //alert(data.length);
                            //console.log('data :',data);
                            var autoIcdList = [];
                            for (var i = 0; i < icdData.length; i++) {
                                var icdId = icdData[i].icdId;
                                var icdCode = icdData[i].icdCode;
                                var icdName = icdData[i].icdName;
                                var a = icdName + "[" + icdCode + "]"

                                var icdId = icdName + "[" + icdId + "]"
                                autoIcdList[i] = a;
                                    //alert("a "+a);
                                arry.push(a);
                                icdArry.push(icdId);
                                //console.log('data :', a, icdId);
                            }
                           
                            //putIcdIdValue(autoIcdList, icdData);

                        },
                        error: function(jqXHR, exception) {
                            var msg = '';
                            if (jqXHR.status === 0) {
                                msg = 'Not connect.\n Verify Network.';
                            } else if (jqXHR.status == 404) {
                                msg = 'Requested page not found. [404]';
                            } else if (jqXHR.status == 500) {
                                msg = 'Internal Server Error [500].';
                            } else if (exception === 'parsererror') {
                                msg = 'Requested JSON parse failed.';
                            } else if (exception === 'timeout') {
                                msg = 'Time out error.';
                            } else if (exception === 'abort') {
                                msg = 'Ajax request aborted.';
                            } else {
                                msg = 'Uncaught Error.\n' + jqXHR.responseText;
                            }
                            alert(msg);
                        }
                    });

                });
            
            var autoIcdCode = '';
           var icdData= '';	 
            var idIcdNo = '';
            var icdValue = '';
            var multiIcdValue=[];
          /*  function putIcdIdValue(item){
           	 //  alert(pvmsNo);
           	 
           	  for(var i=0;i<autoIcdCode.length;i++){
           		  
           		  var icdNo1 = icdData[i].icdCode;
           		 
           		  if(icdNo1 == idIcdNo){
           			icdValue = icdData[i].icdId;
           			alert("Icditem id is " + icdValue )
           		  }
           	  }
           	  
           	// $('#diagnosisId').append('<option value=' + icdValue + '>' + val + '</option>');
           	         	  
             }  */
         var total_icd_value = '';
            function fillDiagnosisCombo(val) {
            	  
                var index1 = val.lastIndexOf("[");
                var index2 = val.lastIndexOf("]");
                index1++;
                idIcdNo = val.substring(index1, index2);
                if (idIcdNo == "") {
                    return;
                } else {
                    obj = document.getElementById('diagnosisId');
                    total_icd_value += val+",";
              	    
                    obj.length = document.getElementById('diagnosisId').length;
                    var b = "false";
                    for (var i = 1; i < obj.length; i++) {
						
                        var val1 = obj.options[i].value;
                        
                        if (idIcdNo == val1) {
                        	alert("ICD  Already taken");
                            document.getElementById('icd').value = ""
                            b = true;
                        }

                    }
                  
                    for(var i=0;i<autoIcdCode.length;i++){
                 		  
                 		  var icdNo1 = icdData[i].icdCode;
                 		 
                 		  if(icdNo1 == idIcdNo){
                 			icdValue = icdData[i].icdId;
                 			multiIcdValue.push(icdValue);
                 		  }
                 	  }
                    if (b == "false") {
                        $('#diagnosisId').append('<option value=' + icdValue + '>' + val + '</option>');
                        document.getElementById('icd').value = ""

                    }
                }
            }

         
        </script>

	<!-- <script type="text/javascript" language="javascript">
            
            '""'
            var $j = jQuery.noConflict();
            $j(document).ready(function() {
                alert("localStorage.patient: " + localStorage.patientId);
                document.getElementById('visitId').value = localStorage.visitId;
                document.getElementById('patient_Id').value = localStorage.patientId;

            });
        </script> -->

	<!-- <script type="text/javascript">
   $(document).ready(function () {
       $('#height').keyup(function () { alert('test'); });
   });
</script> -->

	<script type="text/javascript">
            $(document).ready(function() {
                /* if (typeof element !== "undefined" && element.value == '') {
                } */
                var data = ${
                    data
                };
                if (data.data[0].serviceNo != null) {
                    document.getElementById('service').value = data.data[0].serviceNo;
                }
                if (data.data[0].visitId != null) {
                    document.getElementById('visitId').value = data.data[0].visitId;
                }
                if (data.data[0].genderId != null) {
                    document.getElementById('genderId').value = data.data[0].genderId;
                }
                if (data.data[0].patientId != null) {
                    document.getElementById('patientId').value = data.data[0].patientId;
                }
                if (data.data[0].rank != null) {
                    document.getElementById('rank').value = data.data[0].rank;
                }
                if (data.data[0].empName != null) {
                    document.getElementById('name').value = data.data[0].empName;
                }
                if (data.data[0].tradeBranch != null) {
                    document.getElementById('trade').value = data.data[0].tradeBranch;
                }
                if (data.data[0].relation != null) {
                    document.getElementById('relation').value = data.data[0].relation;
                }
                if (data.data[0].gender != null) {
                    document.getElementById('gender').value = data.data[0].gender;
                }
                if (data.data[0].totalService != null) {
                    document.getElementById('totalservice').value = data.data[0].totalService;
                }
                if (data.data[0].unit != null) {
                    document.getElementById('unit').value = data.data[0].unit;
                }
                if (data.data[0].religionCommand != null) {
                    document.getElementById('regioncommand').value = data.data[0].religionCommand;
                }
                if (data.data[0].recordOfficeAddress != null) {
                    document.getElementById('recordoffice').value = data.data[0].recordOfficeAddress;
                }
                if (data.data[0].medicalCategory != null) {
                    document.getElementById('medicalcategories').value = data.data[0].medicalCategory;
                }
                if (data.data[0].patientName != null) {
                    document.getElementById('empname').value = data.data[0].patientName;
                }
                if (data.data[0].dob != null) {
                    document.getElementById('dob').value = data.data[0].dob;
                }
                if (data.data[0].age != null) {
                    document.getElementById('age').value = data.data[0].age;
                }
                if (data.data[0].marritalStatus != null) {
                    document.getElementById('Marrital Status').value = data.data[0].marritalStatus;
                }
                if (data.data[0].religionCommand != null) {
                    document.getElementById('religion').value = data.data[0].religionCommand;
                }
                if (data.data[0].address != null) {
                    document.getElementById('address').value = data.data[0].address;
                }
                if (data.data[0].city != null) {
                    document.getElementById('city').value = data.data[0].city;
                }
                if (data.data[0].state != null) {
                    document.getElementById('State').value = data.data[0].state;
                }
                if (data.data[0].pincode != null) {
                    document.getElementById('pinCode').value = data.data[0].pincode;
                }
                if (data.data[0].mobileno != null) {
                    document.getElementById('mobileno').value = data.data[0].mobileno;
                }
                if (data.data[0].email != null) {
                    document.getElementById('email').value = data.data[0].email;
                }
                if (data.data[0].height != null) {
                    document.getElementById('height').value = data.data[0].height;
                }
                if (data.data[0].idealWeight != null) {
                    document.getElementById('ideal_weight').value = data.data[0].idealWeight;
                }
                if (data.data[0].weight != null) {
                    document.getElementById('weight').value = data.data[0].weight;
                }
                if (data.data[0].varation != null) {
                    document.getElementById('variant_in_weight').value = data.data[0].varation;
                }
                if (data.data[0].tempature != null) {
                    document.getElementById('tempature').value = data.data[0].tempature;
                }
                if (data.data[0].bp != null) {
                    document.getElementById('bp').value = data.data[0].bp;
                }
                if (data.data[0].pulse != null) {
                    document.getElementById('pulse').value = data.data[0].pulse;
                }
                if (data.data[0].spo2 != null) {
                    document.getElementById('spo2').value = data.data[0].spo2;
                }
                if (data.data[0].bmi != null) {
                    document.getElementById('bmi').value = data.data[0].bmi;
                }
                if (data.data[0].rr != null) {
                    document.getElementById('rr').value = data.data[0].rr;
                }

            });
        </script>

	<script type="text/javascript">
	function idealWeight() {
		var pathname = window.location.pathname;
		var accessGroup = pathname.split("/")[1];

		var url = window.location.protocol + "//"
				+ window.location.host + "/" + accessGroup
				+ "/v0.1/opd/getIdealWeight";
	   
		var dataJSON={
				 
				  'height':$('#height').val(),
	      		  'age':$('#age').val(),
				  'genderId':$('#genderId').val(),
				
		}
		
		
	   
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : url,
			data : JSON.stringify(dataJSON),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				
			   	   	   if (data.status == 1) {
				//var data = data;
				//alert("value is "+data.data[0].idealWeight);
				$('#ideal_weight').val(data.data[0].idealWeight);
				$('#ideal_weight').attr("disabled","disabled");
	           
				
			    }
			   	   	   else
			   	   		   {
			   	   		   	alert("Ideal Weight Not Configured")
			   	   		   	$('#ideal_weight').val();
							$('#ideal_weight').removeAttr("disabled");
			   	   		   }
				
				

			},
			error : function(e) {

				console.log("ERROR: ", e);

			},
			done : function(e) {
				console.log("DONE");
				alert("success");
				var datas = e.data;
			}
		});

	 }
	
	function checkBMI() {
		var a = document.getElementById("weight").value;
		var b = document.getElementById("height").value;
		var c=b/100;
		var d=c*c;
		var sub = a/d;
		$('#bmi').val(sub);
		
	}
	
	function checkObsistyMark() {
		
			if(document.getElementById('variant_in_weight').value== '' && document.getElementById('variant_in_weight').value== undefined)
			{
				alert("Please enter Height and Varation and Weight");
			}
		    
		
		
		
	}
	
  </script>

	<script type="text/javascript">
            
                    function checkVaration()  {

                        var a = document.getElementById("ideal_weight").value;
                        var b = document.getElementById("weight").value;

                        if (a > b) {
                            var sub = a - b;
                            var cal = sub * 100 / a
                            var n = cal.toFixed(2);
                            $('#variant_in_weight').val("-" +n);

                        } else {
                            var eadd = b - a;
                            var cal1 = eadd * 100 / b
                            var n1 = cal1.toFixed(2);
                            $('#variant_in_weight').val(n1);
                        }

                    
				}
        </script>

	<script type="text/javascript" language="javascript">
            $('#clicked').click(function() {
                var pathname = window.location.pathname;
                var accessGroup = pathname.split("/")[1];

                var url = window.location.protocol + "//" + window.location.host + "/" + accessGroup + "/v0.1/opd/saveOpdPatientdetails";
               
                if(document.getElementById('chiefCompliant').value == "") {
                    alert("Please enter a Chief Complaint Value");
                    return false;
                  }
                
                
                  if(total_icd_value=="") {
                    alert("Please enter at least one ICD Diagnosis");
                    return false;
                  }
                
            	if (checkBox.checked == true)
    			{
    			    var obsistyMark='true';
    			} 
				 
            	if(document.getElementById('chargeCodeName').value!= '' && document.getElementById('chargeCodeName').value != undefined)
		    	{
				    var tableDataInvestigation = [];  
			    	var dataInvestigation='';
			    	var labMarkValue = '';   	
			    	var labInvestgationId=[];
					var urgent='';
			    	 for(var j=0;j<lastRow;j++){
						  var f = 'othAfLab1'+j+'';
						    if(document.getElementById(f).checked == true){
								  var iinLab='I';
								  labMarkValue=iinLab;
								  
							  }
							  else
								  {
								  var outLab='O';
								  labMarkValue=outLab;
								  }
						        
							  labInvestgationId.push(labMarkValue);
								}
					         
					         
			    	
			    	    var count=0;
			    		$('#dgInvetigationGrid tr').each(function(i, el) {
					    var $tds = $(this).find('td')
					  		
						var itemIdInvestigation = $tds.eq(5).find(":input").val();
						var investigationDate = $tds.eq(1).find(":input").val();
						if ($tds.eq(4).find(":input").is(":checked")) 
						{
							var yurgent='Y';
							urgent=yurgent;
						}
						else
						{
						    var nUrgent='N';
						    urgent=nUrgent;
						 }
						var labinvastigation="";
						 for (var i = count; i <labInvestgationId.length; i++) {
							 if(i==count){
								 labinvastigation=labInvestgationId[i];
								 break;
							 }
							 
						 }
						 count++;
						dataInvestigation={
			    				'investigationId' : itemIdInvestigation,
			    				'orderDate' : investigationDate,
			    				'labMark' : labinvastigation,
			    				'urgent' : urgent,
			    				
						}
				
						tableDataInvestigation.push(dataInvestigation);
					
			    	});
		    	}
				 
			    		    var icdArryVal = [];
					    	for (var i = 0; i < multiIcdValue.length; i++) {
					    	   // console.log("chargeCodeId" + ": " + multipleChargeCode[i]);
					    	    var param = {'icdId' : multiIcdValue[i],
					    	    		      'visitId':  $('#visitId').val(),
					    	    		      'patientId':$('#patientId').val()
					    	                 }
					    	    icdArryVal.push(param)
					    	  
					    	} 
			     	    
			    	
			    	
			    	var tableDataPrescrption = [];  
			    	var dataPresecption='';
			    	   	
			    	if(document.getElementById('nomenclature1').value!= '' && document.getElementById('nomenclature1').value != undefined)
			    	{
			    		$('#nomenclatureGrid tr').each(function(i, el) {
	    			    var $tds = $(this).find('td')
	    			  		
	    				var itemIdPresc = $tds.eq(8).find(":input").val();
	    				var dosage = $tds.eq(2).find(":input").val();
	    				var frequency = $tds.eq(3).find(":input").val();
	    				var splitFrequency=frequency.split("@");
	    				var noofdays = $tds.eq(4).find(":input").val();
	    				var total = $tds.eq(5).find(":input").val();
	    				var instruction = $tds.eq(6).find(":input").val();
	    				
	    				dataPresecption={
	    	    				'itemId' : itemIdPresc,
	    	    				'dosage' : dosage,
	    	    				'frequencyId' : splitFrequency[1],
	    	    				'noOfDays' : noofdays,
	    	    				'total' : total,
	    	    				'instruction' : instruction,
	    	    			}
	    				tableDataPrescrption.push(dataPresecption)
	    				
			    	});
			    	}
			    	
			    	var listofReferallHD =[];
	            	var listofReferalDT =[];
	            	var dataReferalHD='';
	            	var dataReferalDT='';
	            	
	            	$('#referalGrid tr').each(function(i, el) {
	            	    var $tds = $(this).find('td')
	            	  		
	            		var extHospitalId = $tds.eq(1).find(":input").val();
	            	    var splitHospital=extHospitalId.split("@");
	            		var department = $tds.eq(2).find(":input").val();
	            		var diagonsis = $tds.eq(3).find(":input").val();
	            		var instruction = $tds.eq(4).find(":input").val();
	            		var diagonsisId = $tds.eq(7).find(":input").val();
	            		var referalNote= document.getElementById('referralNote').value;
	            		var extReferalDate=document.getElementById('referVisitDate').value;
	            		
	            		/* dataReferalDT={'diagnosisId' : diagonsisId,
	            				       'extDepartment' : department,
	            				       'instruction' : instruction,} */
	            		//listofReferalDT.push(dataReferalDT);
	            		
	            		dataReferalHD={
	            				'extHospitalId' : splitHospital[0],
	            				'extReferalDate':extReferalDate,
	            				'patientId' : $('#patientId').val(),
	            				'hospitalId' : <%=hospitalId%>,
	            				'referralNote' : referalNote,
	            				'listofReferalDT':[{ 'diagnosisId' : diagonsisId,
	            				      				'extDepartment' : department,
	            				      				'instruction' : instruction,}]
	            		}
	            		listofReferallHD.push(dataReferalHD);
	            	});
	            	
	            	var nrusingM=[];
	            	var nrusingP=[];
	            	var nrusingN=[];
	            	
	            	var listofNursingHD =[];
	            	var listofNursingDT =[];
	            	var dataNursingHD='';
	            	var dataNursingDT='';
	            	if(document.getElementById('procedureNameNursing').value!= '' && document.getElementById('procedureNameNursing').value != undefined)
			    	{
	            		
	            	$('#gridNursing tr').each(function(i, el) {
	            	    var $tds = $(this).find('td')
	            	    var nursingType = $tds.eq(5).find(":input").val();
	            	  
	            	    var nursingValue = $tds.eq(4).find(":input").val();
	            		var frequencyType = $tds.eq(1).find(":input").val();
	            	    var splitFrequency=frequencyType.split("@");
	            		var noOfDays = $tds.eq(2).find(":input").val();
	            		var remarks = $tds.eq(3).find(":input").val();
	            		
	            		//var extReferalDate=document.getElementById('referVisitDate').value;
	            	   dataNursingHD={
	            				'patientId' : $('#patientId').val(),
	            				'visitId' : $('#visitId').val(),
	            				'hospitalId':<%=hospitalId%>,
	            				'procedureType':nursingType ,
	            				'listofNursingDT':listofNursingDT,
	            				    }
	            	   
	            	   dataNursingDT={'nursingId' : nursingValue,
        				       'frequencyId' : splitFrequency[1],
        				       'noOfDays' : noOfDays,
        				       'remarks' : remarks,
        				       'nursingType':nursingType,}
	            	   
        		       //listofNursingDT.push(dataNursingDT);
	            	   
	            		if (nursingType=='M')
	            	  	{
	            			nrusingM.push(dataNursingDT);
	            			
	            	  	}
	            	   if (nursingType=='P')
	            	  	{
	            		   nrusingP.push(dataNursingDT);
	            	  	}
	            	   if (nursingType=='N')
	            	  	{
	            		   nrusingN.push(dataNursingDT);
	            	  	}
	            		
	            	   //listofNursingHD.push(listofNursingHD);
	            		
	            		
	            	
	            	});
	            		
	            	var nursingNJson={
	            			'patientId' : $('#patientId').val(),
            				'visitId' : $('#visitId').val(),
            				'hospitalId':<%=hospitalId%>,
            				'procedureType':'N',
            				'listofNursingDT':nrusingN
            		}
	            	
            		var nursingPJson={
	            			'patientId' : $('#patientId').val(),
            				'visitId' : $('#visitId').val(),
            				'hospitalId':<%=hospitalId%>,
            				'procedureType':'P',
            				'listofNursingDT':nrusingP
            		}
            		var nursingMJson={
	            			'patientId' : $('#patientId').val(),
            				'visitId' : $('#visitId').val(),
            				'hospitalId':<%=hospitalId%>,
            				'procedureType':'M',
            				'listofNursingDT':nrusingM
            		}
	            	if(nrusingN!="" && nrusingN!='undefined')
	            	{
	            	listofNursingDT.push(nursingNJson);
	            	}
	            	if(nrusingP!="" && nrusingP!='undefined')
	            	{
	            	listofNursingDT.push(nursingPJson);
	            	}
	            	if(nrusingM!="" && nrusingM!='undefined')
	            	{
	            	listofNursingDT.push(nursingMJson);
	            	}
	            	  
	            	}
			  
	            	
	            total_icd_value = total_icd_value.substring(0,total_icd_value.length-1);	
	            var disposalValue = document.getElementById('disposalId').value;
	          								
                var dataJSON = {

                    'visitId': $('#visitId').val(),
                    'patientId': $('#patientId').val(),
                    'pallar': $('#pollar').val(),
                    'edema': $('#ordema').val(),
                    'cyanosis': $('#cyanosis').val(),
                    'jauindice': $('#jaundice').val(),
                    'lymphNode': $('#lymphnode').val(),
                    'clubbing': $('#clubbing').val(),
                    'thyroid': $('#thyroid').val(),
                    'tremors': $('#tremors').val(),
                    'generalOther': $('#others').val(),
                    //'general':$('#ordema').val(),
                    'cns': $('#cns').val(),
                    'chestresp': $('#chest').val(),
                    'musculoskeletal': $('#musculoskeletal').val(),
                    'cvs': $('#cvs').val(),
                    'skin': $('#skin').val(),
                    'gi': $('#gi').val(),
                    'systemother': $('#others1').val(),
                    'genitourinary': $('#geneticurinary').val(),
                    'idealWeight': $('#ideal_weight').val(),
                    'height': $('#height').val(),
                    'weight': $('#weight').val(),
                    'varation': $('#variant_in_weight').val(),
                    'temperature': $('#tempature').val(),
                    'bp': $('#bp').val(),
                    'pulse': $('#pulse').val(),
                    'spo2': $('#spo2').val(),
                    'bmi': $('#bmi').val(),
                    'rr': $('#rr').val(),
                    'disposal1Id': disposalValue,
                    'disposalDays': $('#disposalDays').val(),
                    'obsistyMark':obsistyMark,
                    'icdValue':icdArryVal,
	      			'hospitalId':<%=hospitalId%>,
                    'chiefComplain': $('#chiefCompliant').val(),
                    'presentIllnessHistory': $('#presentIllnessHistory').val(),
                    'pastMedicalHistory': $('#pastMedicalHistory').val(),
                    'pastSurgicalHistory': $('#surgicalHistory').val(),
                    'medicationHistory': $('#medicationHistory').val(),
                    'personalHistory': $('#personalHistory').val(),
                    'socialHistory': $('#socialHistory').val(),
                    'familyHistory': $('#familyHistory').val(),
                    'allergyHistory': $('#allergyHistory').val(),
                    'implantHistory': $('#implantHistory').val(),
                    'icdDiagnosis' : total_icd_value,
                    'workingDiagnosis' : $('#workingdiagnosis').val(),
                    'snomedDiagnosis' : $('#snomeddiagnosis').val(),
                    "departmentId"  :  "1",
                    "orderStatus"   :  "y",
                    "listofInvestigation" : tableDataInvestigation,
                    "prescriptionStatus" :"y",
                    "injectionStatus" : "N",
                    "nipStatus" : "N",
                    "listofPrescription" : tableDataPrescrption,
                    "listofNursingCareHD" : listofNursingDT,
                    "listofReferallHD" : listofReferallHD
                }

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: url,
                    data: JSON.stringify(dataJSON),
                    dataType: 'json',
                    timeout: 100000,
                    success: function(msg) {
                        if (msg.status == 1) {
                            //alert("OPD Patient Details Insert successfully");
                            //show_msg({'msg':' Vitals Details Insert successfully ','status':'1'});
                            //location.reload();
                           var Id= $('#visitId').val()
                           window.location.href ="opdSubmit?visitId="+Id+"";
                        } else {
                            alert(msg.status)
                        }
                    },
                    error: function(jqXHR, exception) {
                        var msg = '';
                        if (jqXHR.status === 0) {
                            msg = 'Not connect.\n Verify Network.';
                        } else if (jqXHR.status == 404) {
                            msg = 'Requested page not found. [404]';
                        } else if (jqXHR.status == 500) {
                            msg = 'Internal Server Error [500].';
                        } else if (exception === 'parsererror') {
                            msg = 'Requested JSON parse failed.';
                        } else if (exception === 'timeout') {
                            msg = 'Time out error.';
                        } else if (exception === 'abort') {
                            msg = 'Ajax request aborted.';
                        } else {
                            msg = 'Uncaught Error.\n' + jqXHR.responseText;
                        }
                        alert("Response msg is "+msg);
                    }
                });

            });

            ///////////// Code by  02/02/2019 ////////////////////////

            function deleteDgItems(value) {

                if (confirm("are you sure want to delete ?")) {

                    if (document.getElementById('diagnosisId').options[document.getElementById('diagnosisId').selectedIndex].value != null)
                        document.getElementById('diagnosisId').remove(document.getElementById('diagnosisId').selectedIndex)

                }
            }

            /*  $('.buttonAdd').click(function(){

	 html='<tr><td><input type="text" size="100"/><div id="ac2update1" style="display: none;"class="autocomplete"></div> <input type="hidden" id="qty" tabindex="1" name="qty1" size="10"maxlength="6" validate="Qty,num,no" /> <input	type="hidden" tabindex="1" id="chargeCode1" name="chargeCode1" size="10" readonly /> <!-- 	<input type="text"  name="chargeCodeId" id="chargeCodeId" value=""/> --></td><td><input type="text" class="calDate"	maxlength="10" style="width: 120px" /></td><td><input name="Button" type="button"class="buttonAdd" value="" tabindex="1"onclick="addRowForInvestigation();" /></td><td><input type="button" name="delete" value=""class="buttonDel" tabindex="1" onclick="removeRow(this);" /></td></tr>';
	 $('#investigationGrid').append(html);

 })  */

            /* $('.buttonAdd').click(function(){

	  alert("hello")
	 html='<tr><td>><input type="text" size="100"/><div id="ac2update1" style="display: none;"class="autocomplete"></div><input type="text" value="" tabindex="1" id="nomenclature1" size="77" name="nomenclature1" onblur="checkForAlreadyIssuedPrescription(this.value,'1',document.getElementById('hinId').value);populatePVMS(this.value,' ');checkItem(1);displayAu(this.value,' ','');checkForPurchase(this.value,' ');" /> <input type="hidden" name="itemId1" id="itemId1" value="" /> <input type="hidden" name="itemIdClassificationId1" id="itemIdClassificationId1" value="" /> <div id="ac2updateTreatment1" style="display: none;" class="autocomplete"></div> </td> <td><input type="text" name="dispensingUnit1" tabindex="1" id="dispensingUnit1" size="6" validate="AU,string,no" readonly="readonly" /> <input type="hidden" name="actualDispensingQty1" tabindex="1" id="actualDispensingQty1" value="" size="6" validate="AU,string,no" /> <input type="hidden" tabindex="1" id="itemClassCode1" name="itemClassCode1" validate="itemClassCode,string,no" value="" /> <input type="hidden" tabindex="1" id="highValueMedicine1" name="highValueMedicine1" validate="highValue,string,no" value="" /></td> <td><input type="hidden" name="pvmsNo1" tabindex="1" id="pvmsNo1" size="10" readonly="readonly" /> <input	type="text" name="dosage1" tabindex="1" value="" id="dosage1" size="5" maxlength="5" onblur="checkDosageValidation(this.value,' ');fillValue(' ')" /></td><td><select name="frequency1" id="frequency1" tabindex="1" class="medium"  onchange="getFrequencyValue(this.value,' ');fillValueFromFrequency(this.value,' ');displaySOSQty(this.value,' ');fillValue(' ')"> <option value="0">Select</option> <option value=""></option>	</select> <input type="hidden" name="frequencyValue1" id="frequencyValue1" value=""> <input type="text" name="sosQty1" tabindex="1" id="sosQty1" style="display: none;" size="3" onblur="fillValue('')"  maxlength="3" validate="Sos Qty,num,no" /></td> <td><input type="text" name="noOfDays1" tabindex="1" id="noOfDays1" onblur="fillValue(' ')" size="5" maxlength="3" validate="No of Days,num,no" /></td> <td><input type="text" name="total1" tabindex="1" id="total1" size="5" validate="total,num,no" onblur="treatmentTotalAlert(this.value,1)" /></td> <td><input type="text" name="remarks1" tabindex="1" id="remarks1" size="10" maxlength="15" placeholder="1-1-1" /></td> <td><input type="text" name="closingStock1" tabindex="1" value="" id="closingStock1" size="3" validate="closingStock,string,no" /></td> <td><input name="Button" type="button" class="buttonAdd" value="" onclick="addNomenclatureRow();" tabindex="1" /></td> <td><input type="button" name="delete" value="" class="buttonDel" onclick="removeRow('','',this);" tabindex="1" /></td></tr>';
	 $('#grid').append(html);

 }) */

            /* function removeRow(val){

            	 $(val).parents("tr").remove();
             } */

            function removeRow(idName, countId, obj) {
                var tbl = document.getElementById(idName);
                var lastRow = tbl.rows.length;
                if (lastRow > 2) {
                    //	tbl.deleteRow(lastRow - 1);
                    var i = obj.parentNode.parentNode.rowIndex;
                    tbl.deleteRow(i);
                }
            }

            function populateClinicalNotes(obj) {
                var objValue = obj.value;
                if (obj.id == 'initialDiagnosis') {
                    document.getElementById('clinicalNotes').value = objValue;
                }
            }
            
                       
        </script>

	<script type="text/javascript" language="javascript">
        
        $(document).ready(
        		 
                function getDisposalDetail() {

               		var pathname = window.location.pathname;
                	var accessGroup = pathname.split("/")[1];
                	var url = window.location.protocol + "//"
        			+ window.location.host + "/" + accessGroup
        			+ "/v0.1/opd/getMasDisposalList";
                	
                	$
                			.ajax({
                				url : url,
                				dataType : "json",
                				data : JSON.stringify({
                					'employeeId' : <%= userId %>
                				}),
                				contentType : "application/json",
                				type : "POST",
                				success : function(response) {
                					console.log(response);
                					var datas = response.MasDisposal;
                					var trHTML = '<option value=""><strong>Choose...</strong></option>';
                					$.each(datas, function(i, item) {
                						trHTML += '<option value="' + item.disposalId + '" >'
                								+ item.disposalName + '</option>';
                					});

                					$('#disposalId').html(trHTML);

                				},
                				error : function(e) {

                					console.log("ERROR: ", e);

                				},
                				done : function(e) {
                					console.log("DONE");
                					alert("success");
                					var datas = e.data;

                				}
                			});

                });
        </script>

	<script type="text/javascript" language="javascript">
        
        $(document).ready(
        		function() {
        			var radioValue = 1; 
        			
        	var pathname = window.location.pathname;
        	var accessGroup = pathname.split("/")[1];

        	var url = window.location.protocol + "//"
        			+ window.location.host + "/" + accessGroup
        			+ "/v0.1/opd/getIinvestigationList";

        	//var data = $('employeeId').val();
           // alert("radioValue" +radioValue);
        	$.ajax({
        		type : "POST",
        		contentType : "application/json",
        		url : url,
        		data : JSON.stringify({
        			'employeeId' : <%= userId %>,
        			'mainChargeCode':radioValue,
        		}),
        		dataType : 'json',
        		timeout : 100000,
        		
        		success : function(res)
        		
        		{
        			var data = res.InvestigationList;
        			
        			for(var i=0;i<data.length;i++){
        				var investigationId= data[i].investigationId;
        				var investigationName = data[i].investigationName;
        				//var icdName = data[i].icdName;
        				var a=investigationName+"["+investigationId +"]"
        				//alert("a "+a);
        				 arryInvestigation.push(a);
        				//console.log('data :',a);
        			}

        		
                   },
                   error: function (jqXHR, exception) {
                       var msg = '';
                       if (jqXHR.status === 0) {
                           msg = 'Not connect.\n Verify Network.';
                       } else if (jqXHR.status == 404) {
                           msg = 'Requested page not found. [404]';
                       } else if (jqXHR.status == 500) {
                           msg = 'Internal Server Error [500].';
                       } else if (exception === 'parsererror') {
                           msg = 'Requested JSON parse failed.';
                       } else if (exception === 'timeout') {
                           msg = 'Time out error.';
                       } else if (exception === 'abort') {
                           msg = 'Ajax request aborted.';
                       } else {
                           msg = 'Uncaught Error.\n' + jqXHR.responseText;
                       }
                       alert(msg);
                   }
        	});
        	

        }); 
        
        var arrayData = [];
        var i = "";
        function changeRadio(radioValue){
        	i++;
        	/* var radioValue = '';
        	 $('input[name=labradiologyCheck]').change(function(){
        		 radioValue = $( 'input[name=labradiologyCheck]:checked' ).val();
         	   
         	}); */ 
        	// alert(radioValue);
        	var pathname = window.location.pathname;
        	var accessGroup = pathname.split("/")[1];

        	var url = window.location.protocol + "//"
        			+ window.location.host + "/" + accessGroup
        			+ "/v0.1/opd/getIinvestigationList";

        	//var data = $('employeeId').val();
           // alert("radioValue" +radioValue);
        	$.ajax({
        		type : "POST",
        		contentType : "application/json",
        		url : url,
        		data : JSON.stringify({
        			'employeeId' : <%= userId %>,
        			'mainChargeCode':radioValue,
        		}),
        		dataType : 'json',
        		timeout : 100000,
        		
        		success : function(res)
        		
        		{
        			var data = res.InvestigationList;
        			
        			for(var i=0;i<data.length;i++){
        				var investigationId= data[i].investigationId;
        				var investigationName = data[i].investigationName;
        				//var icdName = data[i].icdName;
        				var a=investigationName+"["+investigationId +"]"
        				//alert("a "+a);
        				arrayData.push(a);
        				 
        				// autocomplete(document.getElementById("chargeCodeName1"), "");
        				 autocomplete(document.getElementById("chargeCodeName"), arrayData);
        				//console.log('data :',a);
        			}

        		
                   },
                   error: function (jqXHR, exception) {
                       var msg = '';
                       if (jqXHR.status === 0) {
                           msg = 'Not connect.\n Verify Network.';
                       } else if (jqXHR.status == 404) {
                           msg = 'Requested page not found. [404]';
                       } else if (jqXHR.status == 500) {
                           msg = 'Internal Server Error [500].';
                       } else if (exception === 'parsererror') {
                           msg = 'Requested JSON parse failed.';
                       } else if (exception === 'timeout') {
                           msg = 'Time out error.';
                       } else if (exception === 'abort') {
                           msg = 'Ajax request aborted.';
                       } else {
                           msg = 'Uncaught Error.\n' + jqXHR.responseText;
                       }
                       alert(msg);
                   }
        	});
        }
        var charge_code_array = [];
        var ChargeCode='';
        var multipleChargeCode = new Array();
        function populateChargeCode(val,count) {
        	
        	//alert(count);
        	if(validateMetaCharacters(val)){
        		if(val != "")
        		{
        			
        		    var index1 = val.lastIndexOf("[");
        		    var indexForChargeCode=index1;
        		    var index2 = val.lastIndexOf("]");
        		    index1++;
        		    ChargeCode = val.substring(index1,index2);
        		    
        		    var indexForChargeCode=indexForChargeCode--;
        		   //alert("value is fun "+ChargeCode);
               
        		if(ChargeCode == "")
        		{
        		document.getElementById('chargeCodeName'+count).value="";
        		document.getElementById('chargeCodeCode'+count).value="";
        		return;
        		}
        		else{
        			        			
        			document.getElementById('chargeCodeCode').value=ChargeCode
        			//alert(ChargeCode);
        			//multipleChargeCode[count-1]=ChargeCode;
        			$(val).closest('tr').find("td:eq(5)").find(":input").val(ChargeCode)
        			//console.log(multipleChargeCode);
        		}
        		}
        		}	
        		}
       
  		function putInvestigationValue(item){
        	
        	$(item).closest('tr').find("td:eq(5)").find(":input").val(ChargeCode)
       	 
       	  }
  		
  		 function changeInvRadio(item){
  			var labMarkValue = ''; 
  			for(var j=0;j<lastRow;j++){
				  var f = 'othAfLab1'+j+'';
				    if(document.getElementById(f).checked == true){
						  var iinLab='I';
						  labMarkValue=iinLab;
						  //alert("aaaaaaa " +labMarkValue)
				    }
					  else
						  {
						  var outLab='O';
						  //alert("out lab "+outLab);
						  labMarkValue=outLab;
						  //alert("bbbbbb " +labMarkValue)
						  }
				        
				    $(item).closest('tr').find("td:eq(6)").find(":input").val(labMarkValue)
						}
  			 
  		 }
  		
  			
        	
       var lastRow;
       var i=0;
      function addRowForInvestigation(){
    	 // var newIndex = $('.autocomplete').length;
    	  i++
    	    var aClone = $('#dgInvetigationGrid>tr:last').clone(true)
    		aClone.find(":input").val("");
    		aClone.find("option[selected]").removeAttr("selected")
    		aClone.find('input[type="radio"]').prop('name', 'labradiologyCheck1'+i+'');
    		aClone.find('input[type="radio"]').prop('id', 'othAfLab1'+i+'');
    		aClone.clone(true).appendTo('#dgInvetigationGrid');
    		var val = $('#dgInvetigationGrid>tr:last').find("td:eq(0)").find(":input")[0];
    		autocomplete(val, arryInvestigation);
    		  var tbl = document.getElementById('dgInvetigationGrid');
          	  lastRow = tbl.rows.length;
    }
</script>

	<script type="text/javascript" language="javascript">
      var autoVsPvmsNo = '';
      var data='';
      var itemIds = '';
      $(document).ready(
        function getMastStoreItem(){
    	  var pathname = window.location.pathname;
    		var accessGroup = pathname.split("/")[1];

    		var url = window.location.protocol + "//"
    				+ window.location.host + "/" + accessGroup
    				+ "/v0.1/opd/getMasStoreItemList";
             
    		//var data = $('employeeId').val();
    	   // alert("radioValue" +radioValue);
    		$.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url : url,
    			data : JSON.stringify({
    				'employeeId' : <%= userId %>,
    			}),
    			dataType : 'json',
    			timeout : 100000,
    			
    			success : function(res)
    			
    			{
    				data = res.MasStoreItemList;
    				autoVsPvmsNo = res.MasStoreItemList;
    				var autoList = [];
    				for(var i=0;i<data.length;i++){
    					var masItemId= data[i].itemId;
    					var masNomenclature=data[i].nomenclature;
    					var masPvmsNo = data[i].pvmsNo;
    					var masDispUnit = data[i].dispUnit;
    					//var icdName = data[i].icdName;
    					//var a=investigationName+"["+investigationId +"]"
    					var aNom=masNomenclature+"["+masPvmsNo +"]";
    					autoList[i] = aNom;
    					//alert("a "+a);
    					arryNomenclature.push(aNom);
    					console.log('MasStoredata :',aNom);
    					
    					
    				}
    				putPvmsValue(autoList, data);
    			
    	           },
    	           error: function (jqXHR, exception) {
    	               var msg = '';
    	               if (jqXHR.status === 0) {
    	                   msg = 'Not connect.\n Verify Network.';
    	               } else if (jqXHR.status == 404) {
    	                   msg = 'Requested page not found. [404]';
    	               } else if (jqXHR.status == 500) {
    	                   msg = 'Internal Server Error [500].';
    	               } else if (exception === 'parsererror') {
    	                   msg = 'Requested JSON parse failed.';
    	               } else if (exception === 'timeout') {
    	                   msg = 'Time out error.';
    	               } else if (exception === 'abort') {
    	                   msg = 'Ajax request aborted.';
    	               } else {
    	                   msg = 'Uncaught Error.\n' + jqXHR.responseText;
    	               }
    	               alert(msg);
    	           }
    		});
    		
        });
    
      var itemIdPrescription= '';
     function putPvmsValue(item){
    	 //  alert(pvmsNo);
    	  var pvmsValue = '';
    	  for(var i=0;i<autoVsPvmsNo.length;i++){
    		  var pvmsNo1 = data[i].pvmsNo;
    		  if(pvmsNo1 == pvmsNo){
    			  pvmsValue = data[i].dispUnit;
    			  itemIdPrescription = data[i].itemId;
    			  itemIds = itemIdPrescription;
    			  //alert("item id is " + itemIdPrescription )
    		  }
    	  }
    	  console.log("item is "+item);
    	  $(item).closest('tr').find("td:eq(1)").find(":input").val(pvmsValue)
    	  $(item).closest('tr').find("td:eq(8)").find(":input").val(itemIds)
    	  
      }
	    	 

        </script>

	<script type="text/javascript" language="javascript">
 var autoNursingNo = '';
 var nursingItemId = '';
 $(document).ready(
   function getMastNursingCareItem(){
	   var pathname = window.location.pathname;
		var accessGroup = pathname.split("/")[1];

		var url = window.location.protocol + "//"
				+ window.location.host + "/" + accessGroup
				+ "/v0.1/opd/getMasNursingCare";
		var defaultProcedureValue = "N";
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : url,
			data : JSON.stringify({
				'nursingType' : defaultProcedureValue
			}),
			dataType : 'json',
			timeout : 100000,
			
			success : function(res)
			
			{
				data = res.data;
				autoNursingNo=res.data;
				for(var i=0;i<data.length;i++){
					var nursingCode= data[i].nursingCode;
					var nursingName=data[i].nursingName;
					var nursingType = data[i].nursingType;
					var nursingId = data[i].nursingId;
					var aProcedure=nursingName+"["+nursingCode +"]";
				
					//alert("a "+a);
					arryProcedureCare.push(aProcedure);
					//console.log('MasProcedureCare :',arryProcedureCare);
					
					
				}
				
			
	           },
	           error: function (jqXHR, exception) {
	               var msg = '';
	               if (jqXHR.status === 0) {
	                   msg = 'Not connect.\n Verify Network.';
	               } else if (jqXHR.status == 404) {
	                   msg = 'Requested page not found. [404]';
	               } else if (jqXHR.status == 500) {
	                   msg = 'Internal Server Error [500].';
	               } else if (exception === 'parsererror') {
	                   msg = 'Requested JSON parse failed.';
	               } else if (exception === 'timeout') {
	                   msg = 'Time out error.';
	               } else if (exception === 'abort') {
	                   msg = 'Ajax request aborted.';
	               } else {
	                   msg = 'Uncaught Error.\n' + jqXHR.responseText;
	               }
	               alert(msg);
	           }
		});
		
   });
 
 

 var procedureArrayData = [];
 var p = "";
 function changeProcedureRadio(defaultProcedureValue){
 	p++;
 	var pathname = window.location.pathname;
	var accessGroup = pathname.split("/")[1];
   // alert("procedureChangeValue" + defaultProcedureValue);
	var url = window.location.protocol + "//"
			+ window.location.host + "/" + accessGroup
			+ "/v0.1/opd/getMasNursingCare";
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : JSON.stringify({
			'nursingType' : defaultProcedureValue
		}),
		dataType : 'json',
		timeout : 100000,
		
		success : function(res)
		
		{
			data = res.data;
			autoNursingNo=res.data;
			var nursingCode="";
			var nursingName="";
			var nursingType="";
			var nursingId="";
			var aProcedure="";
			var valProcedureCare="";
			procedureArrayData=[];
			arryProcedureCare=[];
			for(var i=0;i<data.length;i++){
				  nursingCode= data[i].nursingCode;
				  nursingName=data[i].nursingName;
				  nursingType = data[i].nursingType;
				  nursingId = data[i].nursingId;
				  aProcedure=nursingName+"["+nursingCode +"]";
			
				procedureArrayData.push(aProcedure);
				$j('#ChangeMasProcedureCare').val('');
				   valProcedureCare=$('#gridNursing').children('tr:last').children('td:eq(0)').find(':input')[0]
		         autocomplete(valProcedureCare, procedureArrayData); 
				//console.log('ChangeMasProcedureCare :',procedureArrayData);
				
				
				
			}

 		
            },
            error: function (jqXHR, exception) {
                var msg = '';
                if (jqXHR.status === 0) {
                    msg = 'Not connect.\n Verify Network.';
                } else if (jqXHR.status == 404) {
                    msg = 'Requested page not found. [404]';
                } else if (jqXHR.status == 500) {
                    msg = 'Internal Server Error [500].';
                } else if (exception === 'parsererror') {
                    msg = 'Requested JSON parse failed.';
                } else if (exception === 'timeout') {
                    msg = 'Time out error.';
                } else if (exception === 'abort') {
                    msg = 'Ajax request aborted.';
                } else {
                    msg = 'Uncaught Error.\n' + jqXHR.responseText;
                }
                alert(msg);
            }
 	});
 }
 
 var nursingItemIdValue= '';
 var nursingValue = '';
 var nursingType = '';
 function calucateNursingValue(nursingItem){
	  // alert(nursingNo);
	  
	  for(var i=0;i<autoNursingNo.length;i++){
		  var nursingC = data[i].nursingCode;
		  if(nursingC == nursingNo){
			  nursingValue = data[i].nursingId;
			  nursingType = data[i].nursingType;
			  //itemIds = itemIdPrescription;
			 // alert("item id is " + nursingType )
		  }
	  }
	 // console.log("item is "+item);
	  $(nursingItem).closest('tr').find("td:eq(4)").find(":input").val(nursingValue)
	  $(nursingItem).closest('tr').find("td:eq(5)").find(":input").val(nursingType)
	  
  }
    		
</script>
























</body>

</html>