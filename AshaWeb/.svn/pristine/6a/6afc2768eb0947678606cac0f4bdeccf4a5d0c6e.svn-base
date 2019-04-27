<%@page import="java.util.HashMap"%>
    <%@page import="java.util.Map"%>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

          
          <%@include file="..//view/leftMenu.jsp" %>

                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                    <html>

                    <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                        <%@include file="..//view/commonJavaScript.jsp" %>
                            <title>OPD Recall Main</title>
                      <!--   <link href="/AshaWeb/resources/css/stylecommon.css" rel="stylesheet" type="text/css" /> -->
                    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/newOpd.js"></script>
                    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/opd.js"></script>
					<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/opdpatientrecall.js"></script>

</head>
<%
	int i = 1;
%>

<body>
	<div class="content-page">
		<div class="">
			<div class="container-fluid">
				<div class="internal_Htext">OPD Recall Main</div>

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<form:form name="submitPatientRecall" id="submitPatientRecall" method="post"
									action="${pageContext.request.contextPath}/v0.1/opd/submitPatientRecall">
									<input type="hidden" name="opdPatientDetailId" value=""
										id="opdPatientDetailId" />
									<input type="hidden" name="patientId" value="" id="patientId" />
									<input type="hidden"  name="marksAsLabValue" value="" id="marksAsLabValue" />
									<input type="hidden"  name="urgentValue" value="" id="urgentValue" />
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
															class="form-control border-input"
															placeholder="Service No." value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Rank </label>
													<div class="col-sm-7">
														<input name="patients_name" id="rank" type="text"
															class="form-control border-input" placeholder="Rank"
															value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Name </label>
													<div class="col-sm-7">
														<input name="relation" id="name" type="text"
															class="form-control border-input" placeholder="Name"
															readonly />
													</div>
												</div>
											</div>



											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Trade/Branch
													</label>
													<div class="col-sm-7">
														<input name="Trade/Branch" id="trade" type="text"
															class="form-control border-input"
															placeholder="Trade/Branch" value="" readonly />
													</div>
												</div>
											</div>


											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Total
														Service </label>
													<div class="col-sm-7">
														<input name="Total Service" id="totalservice" type="text"
															class="form-control border-input"
															placeholder="Total Service" value="" readonly />
													</div>
												</div>
											</div>


											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Unit </label>
													<div class="col-sm-7">
														<input name="Unit" id="unit" type="text"
															class="form-control border-input" placeholder="Unit"
															value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">
														Region/Command </label>
													<div class="col-sm-7">
														<input name="regioncommand" id="regioncommand" type="text"
															class="form-control border-input"
															placeholder="regioncommand" readonly/>
													</div>
												</div>
											</div>


											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Record
														Office </label>
													<div class="col-sm-7">
														<input name="recordoffice" id="recordoffice" type="text"
															class="form-control border-input"
															placeholder="Record Office" readonly/>
													</div>
												</div>
											</div>



											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Medical
														Categories </label>
													<div class="col-sm-7">
														<input name="Medical Categories" id="medicalcategories"
															type="text" class="form-control border-input"
															placeholder="Medical Categories" value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Duration </label>
													<div class="col-sm-7">
														<input name="Duration" id="duration" type="text"
															class="form-control border-input" placeholder="Duration"
															value="" readonly>
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
															class="form-control border-input" placeholder="Name"
															value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Region </label>
													<div class="col-sm-7">
														<input name="region" id="region" type="text"
															class="form-control border-input" placeholder="Region"
															value="" readonly/>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Gender </label>
													<div class="col-sm-7">
														<input name="Gender" id="Gender" type="text"
															class="form-control border-input" placeholder="Gender"
															value="" readonly/>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">DOB </label>
													<div class="col-sm-7">
														<input name="DOB" id="dob" type="text"
															class="form-control border-input" placeholder="DOB"
															value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Age </label>
													<div class="col-sm-7">
														<input name="Age" id="age" type="text"
															class="form-control border-input" placeholder="Age"
															value="" readonly/>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Marrital
														Status </label>
													<div class="col-sm-7">
														<input name="Marrital Status" id="Marrital Status"
															type="text" class="form-control border-input"
															placeholder="marritalstatus" value="" readonly/>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Religion </label>
													<div class="col-sm-7">
														<input name="Religion" id="religion" type="text"
															class="form-control border-input" placeholder="Religion"
															value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Address </label>
													<div class="col-sm-7">
														<input name="Address" id="address" type="text"
															class="form-control border-input" placeholder="Address"
															value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">City </label>
													<div class="col-sm-7">
														<input name="City" id="city" type="text"
															class="form-control border-input" placeholder="City"
															value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> State </label>
													<div class="col-sm-7">
														<input name="State" id="State" type="text"
															class="form-control border-input" placeholder="State"
															value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> Pin Code </label>
													<div class="col-sm-7">
														<input name="Pin Code" id="pinCode" type="text"
															class="form-control border-input" placeholder="Pin Code"
															value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Mobile No. </label>
													<div class="col-sm-7">
														<input name="Mobile No" id="mobileno" type="text"
															class="form-control border-input"
															placeholder="Mobile No." value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label"> E-mail
														Address </label>
													<div class="col-sm-7">
														<input name="email" id="email" type="text"
															class="form-control border-input"
															placeholder="E-mail Address" value="" readonly />
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

														<li><a href="#"
															onclick="openWindow('/hms/hms/opd?method=showPatientPreviousVisitForViewScreen&visitId=>&backFlag=OPD')">
																Previous Visits </a>
														<li><a href="#"
															onclick="openWindow('/hms/hms/opd?method=viewAllPrevoiusSurgery&hinId=<>')">Previous
																Hospitalization </a></li>
														<li><a href="#"
															onclick="openWindow('/hms/hms/opd?method=showPsychiatristQuestionnaireJsp&hinId=')">Previous
																Lab Investigation </a></li>
														<li><a href="#"
															onclick="openWindow('/hms/hms/opd?method=viewAllPrevoiusSession&hinId=')">Previous
																Radio </a></li>
														<li><a
															href="javascript:popwindowImmunization('opd?method=showVaccineDetailJsp&visitId=<');">Previous
																Medical Exam</a></li>

														<li><a
															href="javascript:popwindowImmunization('opd?method=showVaccineDetailJsp&visitId=<');">Previous
																Vitals</a></li>
														<li><a
															href="javascript:popwindowImmunization('opd?method=showVaccineDetailJsp&visitId=<');">EHR</a></li>
														<li><a
															href="javascript:popwindowImmunization('opd?method=showVaccineDetailJsp&visitId=<');">Previous
																Medical Board</a></li>
														<li><a
															href="javascript:popwindowImmunization('opd?method=showVaccineDetailJsp&visitId=<');">Upload
																View Document</a></li>
														<li><a
															href="javascript:popwindowImmunization('opd?method=showVaccineDetailJsp&visitId=<');">Allergy</a></li>
														<li><a
															href="javascript:popwindowImmunization('opd?method=showVaccineDetailJsp&visitId=<');">Immuzation
																History</a></li>
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

														<%-- <form> --%>
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
																		<textarea class="textNew" name="historyPresentIllness"
																			id="presentIllnessHistory" cols="0" rows="0" value=""
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

														<%-- </form> --%>

													</div>

												</div>
											</div>

										</div>
									</div>




									<!-- Previous visits & Chief Complaint  end here -->
									<br>

									<!-- Previous visits & Chief Complaint  end here -->
									<br>
									<!-- Vitals  start here -->
									<div class="opdMain_detail_area">
										<h4 class="service_htext">Vital Detail</h4>
										<div class="row">

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Height </label>
													<div class="col-sm-7">
														<input name="height" id="height" type="text"
															class="form-control border-input" placeholder="Height"
															value="" required />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Ideal Weight
													</label>
													<div class="col-sm-7">
														<input name="ideal_weight" id="ideal_weight" type="text"
															class="form-control border-input"
															placeholder="Ideal Weight" />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Weight </label>
													<div class="col-sm-7">
														<input name="Weight" id="weight" type="text"
															class="form-control border-input" placeholder="Weight" />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Variation in weight </label>
													<div class="col-sm-7">
														<input name="variant_in_weight" id="variant_in_weight"
															type="text" class="form-control border-input"
															placeholder="Variation in weight" value="" required />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Tempature </label>
													<div class="col-sm-7">
														<input name="tempature" id="tempature" type="text"
															class="form-control border-input" placeholder="Tempature"
															value="" required>
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
													<label class="col-sm-5 col-form-label">Pulse </label>
													<div class="col-sm-7">
														<input name="pulse" id="pulse" type="text"
															class="form-control border-input" placeholder="Pulse"
															value="" required />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">Spo2 </label>
													<div class="col-sm-7">
														<input name="spo2" id="spo2" type="text"
															class="form-control border-input" placeholder="spo2"
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
															value="" required>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-5 col-form-label">RR </label>
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
															<label class="col-sm-5 col-form-label">Lymph node
																 </label>
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
															<label class="col-sm-5 col-form-label">Musculoskeletol
															</label>
															<div class="col-sm-7">
																<input name="Musculoskeletal" id="musculoskeletal"
																	type="text" class="form-control border-input"
																	placeholder="Musculoskeletol" />
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
															<label class="col-sm-5 col-form-label">Genetic
																urinary </label>
															<div class="col-sm-7">
																<input name="geneticurinary" id="geneticurinary"
																	type="text" class="form-control border-input"
																	placeholder="Genetic urinary" value="" required />
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-sm-5 col-form-label">Others </label>
															<div class="col-sm-7">
																<input name="OthersForSystem" id="OthersForSystem" type="text"
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

											<div class="col-md-8" id="diagnosisValueUpdate">
												<select name="diagnosisId" multiple="4" size="5"
													tabindex="1" id="diagnosisId" class="listBig"
													validate="ICD Daignosis,string,yes">
												</select>
												<button type="button" class="buttonDel btn btn-danger" value=""
													onclick="deleteDgItems(this,'diagnosisId');" align="right" />
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


												<div class="col-md-2">
													<div class="form-group row">
														<label class="col-sm-5 col-form-label"> Urgent </label>
														<div class="col-sm-7">
															<input type="checkbox" name="urgent" tabindex="1"
																class="radioAuto" value="1" />
														</div>
													</div>
												</div>


											</div>

											<div class="Block1">
												<div class="floatleft">
													<span style="float: left;"> <input type="radio"
														value="1" class="radioCheckCol2"
														style="float: left; margin-right: 6px;"
														name="labradiologyCheck" checked="checked"
														onchange="changeRadio(this.value)" />
														<div class="labRadiologyDivfixed" style="float: right">LAB</div>
													</span> <span> <input type="radio" value="2"
														class="radioCheckCol2"
														style="margin-left: 12px; margin-right: 6px;"
														name="labradiologyCheck"
														onchange="changeRadio(this.value)" /> <span
														class="labRadiologyDivfixed">Radiology</span>
													</span> <input type="hidden" name="investigationCategory"
														id="investigationCategory" /> <input id="visitId"
														name="visitId" type="hidden" value=" " /> <input
														id="visitId1" name=" " type="hidden" value=" " /> <input
														name="hinId" id="hinId" type="hidden" value=" " /> <input
														name="departmentId" id="departmentId" type="hidden" value="" /> <input
														name="hospitalId" type="hidden" id="hospitalId" value="" />


													<div class="clear"></div>
												</div>
												<br>


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
																			name="chargeCodeCode" size="10" readonly /> <input
																			type="hidden" name="investigationIdValue" value=""
																			id="investigationIdValue" /> <input type="hidden"
																			name="dgOrderDtIdValue" value=""
																			id="dgOrderDtIdValue" /> <input type="hidden"
																			name="dgOrderHdId" value="" id="dgOrderHdId " /> 
																			<%--<input type="hidden" name="marksAsLabValue" value=""
																			id="marksAsLabValue" /> <input type="hidden"
																			name="urgentValue" value="" id="urgentValue" />--%>

																	</div>
																</td>

																<%-- <td><input type="checkbox" name="referToMh<%=inc %>" tabindex="1" id="referToMhId
                                                                                <%=inc%>" value="y" class="radio" validate="Refer to MH,string,no" /></td> --%>
																<td><input type="Date" id="investigationDate1"
																	name="investigationDate" class="input_date"
																	placeholder="DD/MM/YYYY" value="" maxlength="10" /></td>
																<td>
																	<div class="labRadiologyDivfixed"></div> <input
																	type="radio" value="I" id="othAfLab11"
																	class="radioCheckCol2" style="margin-right: 15px;"
																	name="labradiologyCheck1" name="othAfLab1"
																	onchange="#" />
																</td>
																<td>
																	<div class="labRadiologyDivfixed"></div> <input
																	type="radio" value="O" id="othAfLab11"
																	class="radioCheckCol2" style="margin-right: 15px;"
																	name="labradiologyCheck1" name="othAfLab1"
																	onchange="#" />
																</td>
																<td><input type="checkbox" name="urgent"
																	id="uCheck11" tabindex="1" class="radioAuto"
																	onchange="#" value="N" /></td>
																<!-- <td style="display: none";><input type="hidden"
																	value="" tabindex="1" id="inestigationRadioval"
																	size="77" name="inestigationRadioval" /></td> -->

																<td><button name="Button" type="button"
																	class="buttonAdd btn btn-primary" value="" tabindex="1"
																	onclick="addRowForInvestigationFun();">Add</button></td>
																<td><button type="button" id="deleteInves" name="delete" value=""
																	class="buttonDel btn btn-danger" tabindex="1"
																	onclick="removeRowInvestigation(this,'investigationGrid','');">Delete</button>
																</td>

															</tr>
														</tbody>
														<input type="hidden" value="1" name="hiddenValue"
															id="hiddenValue"/>

													</table>
													<br> <label>Other Investigation</label>
													<textarea name="otherInvestigation" id="otherInvestigation" cols="50" rows="0"
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
										<div class="">
											<table class="table table-bordered" align="center"
												cellpadding="0" cellspacing="0">
												<tr>
													<th>Nomenclature/Material Code</th>
													<th scope="col">Disp. Unit</th>
													<th scope="col">Dosage<span>*</span></th>
													<th scope="col">Frequency<span>*</span></th>
													<th scope="col">Days<span>*</span></th>
													<th scope="col">Total<span>*</span></th>
													<th scope="col">Instruction</th>
													<th scope="col">Stock</th>

													<th>Add</th>
													<th>Delete</th>

												</tr>
												<tbody id="nomenclatureGrid">
													<tr>

														<td>
															<div class="autocomplete">
																<input type="text" value="" tabindex="1"
																	id="nomenclature1" size="77" name="nomenclature1"
																	class="form-control border-input"
																	onblur="populatePVMS(this.value,'1');putPvmsValue(this);" />
																<input type="hidden" name="itemId" value=""
																	id="nomenclatureId" /> <input type="hidden"
																	name="prescriptionHdId" value="" id="prescriptionHdId" />
																<input type="hidden" name="prescriptionDtId" value=""
																	id="precriptionDtId" />
															</div>
														</td>

														<td><input type="text" name="dispensingUnit1"
															tabindex="1" id="dispensingUnit1" size="6"
															validate="AU,string,no" readonly="readonly" /></td>

														<td><input type="text" name="dosage1" tabindex="1"
															value="" id="dosage1" size="5" maxlength="5" /></td>

														<td><select name="frequencyTre" id="frequencyTre"
															class="medium">

														</select></td>

														<td><input type="text" name="noOfDays1" tabindex="1"
															id="noOfDays1" onblur="fillValueNomenclature('1')"
															size="5" maxlength="3" validate="No of Days,num,no" /></td>

														<td><input type="text" name="total1" tabindex="1"
															id="total1" size="5" validate="total,num,no"
															onblur="treatmentTotalAlert(this.value,1)" /></td>

														<td><input type="text" name="remarks1" tabindex="1"
															id="remarks1" size="10" maxlength="15"
															placeholder="1-1-1" /></td>

														<td><input type="text" name="closingStock1"
															tabindex="1" value="" id="closingStock1" size="3"
															validate="closingStock,string,no" /></td>
														<td style="display: none;"><input type="hidden"
															value="" tabindex="1" id="itemIdNom" size="77"
															name="itemIdNom" /></td>
														<td>

															<button type="button" class="btn btn-primary buttonAdd"
																name="button" type="button" value=""
																onclick="addNomenclatureRowRecall();" tabindex="1">Add</button>

														</td>
														<td>


															<button type="button" class="buttonDel  btn btn-danger"
																name="button" type="button" value="" tabindex="1"
																onclick="removeRowInvestigation(this,'nomenclatureGrid', '');">Delete</button>

														</td>
														<td style="display: none;"><input type="hidden"
															name="pvmsNo1" tabindex="1" id="pvmsNo1" size="10"
															readonly="readonly" /></td>
													</tr>

												</tbody>
												<tr>
											</table>

										</div>

									</div>



									<!-- ------   Treatment end here-- -->



									<!-- ------   Procedure Care start here-- -->

									<div class="opdMain_detail_area">
										<h4 class="service_htext">Procedure Care</h4>
										<div class="Block">

											<div class="row">
												<div class="col-md-1"></div>

												<div class="col-md-3">
													<div class="form-group row">
														<div class="col-sm-1">
															<input class="form-check-input" type="radio"
																name="exampleRadios" id="exampleRadios2" value="option2">
														</div>
														<label class="col-sm-5 col-form-label">Procedure</label>

													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group row">
														<div class="col-sm-1">
															<input class="form-check-input" type="radio"
																name="exampleRadios" id="exampleRadios2" value="option2">
														</div>
														<label class="col-sm-5 col-form-label">Physiotherapy
														</label>

													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group row">
														<div class="col-sm-1">
															<input class="form-check-input" type="radio"
																name="exampleRadios" id="exampleRadios2" value="option2">
														</div>
														<label class="col-sm-5 col-form-label"> Minor
															Surgery</label>

													</div>
												</div>

												<div class="col-md-2"></div>

											</div>


											<!-- 	<label>Procedure Care</label>
									<div class="clear"></div>
									<div class="clear"></div>
									<div class="clear"></div>
									<div class="floatLeft" style="width: 350px;">
										<button type="button" class="buttonAdd btn btn-primary" alt="" tabindex="4"
											value="" onclick="addRowTreatmentNursingCare();" align="left" >Add</button>
										<input type="button" class="buttonDel" tabindex="3"	alt="Delete" value=""
											onclick="removeRowTreatmentNursingCare();" align="right" />
										<input type="radio"	value="N"	class="radioCheckCol2" id="procedureCheck"
											style="margin-right: 5px;" name="procedureCheck"	checked="checked" onchange="">
											
										<div class="labRadiologyDivfixed">Procedure</div>
										<input  style="display:none;" type="hidden" id="nurCodePhy"	value="">
										<input type="radio" value="P"
											class="radioCheckCol2" id="procedureCheck"
											style="margin-right: 5px;" name="procedureCheck" onchange="">
										<div class="labRadiologyDivfixed">Physiotherapy</div>
										<input type="hidden" name="nursingCategory"
											id="nursingCategory">

									    <input type="hidden" id="minorSurgery"
											value="">
										<input type="radio" value="M"
											class="radioCheckCol2" id="minorSurgery"
											style="margin-right: 5px;" name="procedureCheck" onchange="">
										<div class="labRadiologyDivfixed">Minor Surgery</div>
										<input type="hidden" name="minorSurgery"
											id="minorSurgery">		

									</div>
									<div class="clear"></div> -->
											<div id="divTemplet">

												<table class="table table-bordered" align="center"
													cellpadding="0" cellspacing="0" id="gridNursing">
													<tr>
														<th>&nbsp;</th>
														<th>Nursing Care Name</th>
														<th>Frequency<span>*</span></th>
														<th>No.Of Days<span>*</span></th>
														<th>Remarks</th>
														<th>Add</th>
														<th>Delete</th>
														<!-- <th>Alert Me</th> -->
													</tr>
													<tbody id="gridNursing">
														<tr>
															<td><input type="checkbox" disabled="disabled"
																class="radioCheck" id="nursingRadio" name="nursingRadio" />
															</td>

															<input type="checkbox" class="radioCheck"
																id="nursingRadio" name="nursingRadio">
															</td>

															<td><input type="text"
																class="opdTextBoxSmall textYellow" value=""
																id="procedureName_nursing" size="35"
																name="procedureName_nursing"
																onblur="validateNursingCare(this.value, )" /> <input
																style="display: none;" type="hidden"
																name="procedureType" id="procedureType" value="" />
																<div id="ac2updates_nursing" style="display: none;"
																	class="autocomplete"></div></td>
															<td>
																<%-- onchange="populateNoOfDaysForNursingProcedure(this.value, '')" --%>
																<select name="frequency_nursing" id="frequency_nursing"
																tabindex="36">
																	<option value="">Select</option>

															</select> </script>
															</td>

															<td id="nf"><input type="text"
																name="noOfDays_nursing" id="noOfDays_nursing" value=""
																class="opdTextBoxTSmall textYellow" size="5"
																validate="No. of days,num,no" /></td>
															<td><input readonly="readonly" value="" type="text"
																name="remark_nursing" id="remark_nursing"
																class="largTextBoxOpd textYellow" maxlength="100" /></td>
															<td><button type="button" class="buttonAdd btn btn-primary" alt=""
																tabindex="4" value=""
																onclick="addRowTreatmentNursingCare();">Add</button>
															</td>
															<td><button type="button" class="buttonDel btn btn-danger"
																tabindex="3" alt="Delete" value=""
																onclick="removeRowTreatmentNursingCare();" align="right">Delete</button>
															</td>
													</tbody>
												</table>
												<input style="display: none;" type="hidden"
													id="procedureHeaderId" name="procedureHeaderId" value="" />
												</td> <input style="display: none;" type="hidden"
													name="nursinghdb" value="" id="nursinghdb" />
											</div>
										</div>

										<td><textarea size="20" class="large"
												id="remark_therapy<%-- --%>"
												name="remark_therapy
                                                                    <%--  --%>"
												validate="Remarks,string,no" value="" tabindex="1"
												style="width: 250px; height: 50px;" maxlength="1000"></textarea>
										</td>

										</table>

									</div>

									<!-- ------   Treatment end here-- -->


									                     
                                                   <!-- Referral  start here -->             
                                     <div class="opdMain_detail_area">           
                                                            
                                               <div id="referalDiv">
												<h4  class="service_htext">REFERAL</h4>

												<!-- <label>Referral </label> -->
												 <select  style=" width: 10%;" id="referralForNew" name="referralForNew" class="midium" onchange="getReferalDataForAdd();">
													<option id="option1" value="0">No</option>
													<option id="option2" value="1">Yes</option>
												</select>
                                                  <br><br><br>
												<div id="referDiv" class="col collaps" style="display: block;">
													<label>Refer To</label> <label class="autoSpace">
													    <input type="radio" class="radioCheckCol2" name="referTo" id="referExternal" value="E" onclick="checkReferTO('referExternal');" style="margin: 1px 5px 0px 0px;" checked="checked">External </label>
														<label class="autoSpace">
														<input type="radio" class="radioCheckCol2" name="referTo" id="referInternal" value="I" onclick="checkReferTO('referInternal');" style="margin: 1px 5px 0px 0px;">Internal
													</label>


													<!--  <label
											class="autoSpace"><input type="radio"
											class="radioCheckCol2" name="referTo" id="referBoth"
											value="Both" onclick="checkReferTO('Both');"
											style="margin: 1px 5px 0px 0px;" />Both
											</label> -->

													<div class="clear"></div>
													<!-- <label>Refer Date: </label> <input type="Date" name="referVisitDate" id="referVisitDate" placeholder="DD/MM/YYYY" value=""> -->

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
																	<th>Instaruction</th>
																	<th scope="col">Add</th>
																	<th scope="col">Delete</th>
																</tr>
															</tbody>
															<tbody id="referalGridNew">

															</tbody>
														</table>
														
														<input type="hidden" value="1" name="hiddenValueRefer" id="hiddenValueRefer">
													</div>
													<label id="referhospitalLabel" style="display: none;">Hospital
														<span>*</span>
													</label> <select id="referhospital" name="referhospital" 
													onchange="fnGetHospitalDepartment(this.value);" style="display: none;" validate="">
														<option value="0">Select</option>

														<option value=""></option>

													</select> <label id="referdayslLabel" style="display: none;">No.
														of Days</label> <input id="referdays" name="referdays" type="text" maxlength="2" style="display: none;">

													<div class="clear"></div>


													<div class="clear"></div>
													<label style="display: none">Patient Advise</label>
													<textarea name="patientAdvise" validate="patientAdvise,string,no" id="patientAdvise" cols="0" rows="0" maxlength="500" tabindex="5" onkeyup="return checkLength(this)" style="display: none"></textarea>
													<!-- <input type="button" class="buttonAuto-buttn" value="+"
										onclick="" /> -->
													<label id="referral_treatment_type_label" style="display: none">Treatment Type <span>*</span></label>
													<select id="referral_treatment_type" name="referral_treatment_type" style="display: none">
														<option value="1" selected="true">OPD</option>
														<option value="2">Admission</option>
													</select> <label id="referredForLabel" style="display: none">Referred
														For<span>*</span>
													</label> <input id="referredFor" name="referredFor" type="text" maxlength="300" validate="" style="display: none">
													<div class="clear"></div>
													<!-- <label>Referral Notes</label>
													<textarea name="referralNote" validate="referralNote,string,no" id="referralNote" cols="0" rows="0" maxlength="500" tabindex="5" onkeyup="return checkLength(this)" style="width: 477px;"></textarea> -->
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

													<label class="col-sm-5 col-form-label">Disposal Type</label>

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
											<div class="col-md-10"></div>

											<div class="col-md-2">
												<input type="button" id="clicked111" onclick="return submitForm();" class="btn btn-info"
													value="Submit"/> <input type="submit" id="reset"
													class="btn btn-info btn-fill btn-wd" value="Reset">
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

	<!--  For AutoComplete Method -->

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

         
        </script>

	<script type="text/javascript">
            var popup;

            function SelectName() {
                popup = window.open("getFamilyPatinetHistory?employeeId=1", "popUpWindow", "height=500,width=400,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
                popup.focus();
            }
        </script>

	<script type="text/javascript">
            var popup;

            function showCreateInvestigationTemplate() {
                popup = window.open("showCreateInvestigationTemplate?employeeId=1", "popUpWindow", "height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
                popup.focus();
            }
            
            function createTreatmentTemplate() {
                popup = window.open("createTreatmentTemplate?employeeId=1", "popUpWindow", "height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
                popup.focus();
            }

            function showUpdateInvestigationTemplate() {
                popup = window.open("showUpdateInvestigationTemplate?employeeId=1", "popUpWindow", "height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
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
                            'employeeId': "1"
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
                           
                           // putIcdIdValue(autoIcdList, icdData);

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
                 			
                 		  }
                 	  }
                    if (b == "false") {
                        $('#diagnosisId').append('<option value=' + icdValue + '>' + val + '</option>');
                        document.getElementById('icd').value = ""

                    }
                }
            }

         
        </script>



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
                
                if(data.data[0].visitId!=null){ 
                  	 document.getElementById('visitId').value=data.data[0].visitId;
                  }
                   
                   if(data.data[0].opdPatientDetailId!=null){
                     	 document.getElementById('opdPatientDetailId').value=data.data[0].opdPatientDetailId;
                     }
                   if(data.data[0].patientId!=null){
                    	 document.getElementById('patientId').value=data.data[0].patientId;
                    }
                   if(data.data[0].duration!=null){
                  	 document.getElementById('duration').value=data.data[0].duration;
                  }
                   if(data.data[0].gender!=null){
                    	 document.getElementById('Gender').value=data.data[0].gender;
                    } 
                   
   					patientExaminationDignosisData();
   					patientHistoryData();
                   	patientInvestigationData();
                   	patientTreatementDetail();
                   	getPatientReferalDetail();
                   	getFrequencyDetailTre()

            });
        </script>

	<script type="text/javascript">
            $(document).ready(function() {
                $('#height').change(
                    function() {

                        var pathname = window.location.pathname;
                        var accessGroup = pathname.split("/")[1];

                        var url = window.location.protocol + "//" + window.location.host + "/" + accessGroup + "/v0.1/opd/getIdealWeight";

                        var dataJSON = {

                            'height': $('#height').val(),
                            'age': $('#age').val(),

                        }

                        $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: url,
                            data: JSON.stringify(dataJSON),
                            dataType: 'json',
                            timeout: 100000,
                            success: function(data) {
                                console.log("SUCCESS: ", data);

                                if (data.status == 1) {
                                    //var data = data;

                                    $('#ideal_weight').val(data.data[0].idealWeight);

                                }

                            },
                            error: function(e) {

                                console.log("ERROR: ", e);

                            },
                            done: function(e) {
                                console.log("DONE");
                                alert("success");
                                var datas = e.data;
                            }
                        });
                    });
            });
        </script>

	<script type="text/javascript">
            $(document).ready(function() {
                $('#weight').change(
                    function() {

                        var a = document.getElementById("ideal_weight").value;
                        var b = document.getElementById("weight").value;

                        if (a > b) {
                            var sub = a - b;
                            var cal = sub * 100 / a
                            var n = cal.toFixed(2);
                            $('#variant_in_weight').val(n);

                        } else {
                            var eadd = b - a;
                            var cal1 = eadd * 100 / b
                            var n1 = cal1.toFixed(2);
                            $('#variant_in_weight').val("-" + n1);
                        }

                    });
            });
        </script>

	<script type="text/javascript" language="javascript">
           /*  $('#clicked').click(function() {

                var pathname = window.location.pathname;
                var accessGroup = pathname.split("/")[1];

                var url = window.location.protocol + "//" + window.location.host + "/" + accessGroup + "/v0.1/opd/saveOpdPatientdetails";
				
            	if (checkBox.checked == true)
    			{
    			    var obsistyMark='true';
    			} 
				 
				    var today= document.getElementById('investigationDate1').value 
				    var tableDataInvestigation = [];  
			    	var dataInvestigation='';
			    	var labMarkValue = '';   	
			    	var labInvestgationId=[];
					var rawArrayInvestigation=[];
			    	 for(var j=0;j<lastRow;j++){
						  var f = 'othAfLab1'+j+'';
						    if(document.getElementById(f).checked == true){
								  var iinLab='I';
								  labMarkValue=iinLab;
								  alert("in lab "+iinLab);
							  }
							  else
								  {
								  var outLab='O';
								  alert("out lab "+outLab);
								  labMarkValue=outLab;
								  }
						        
							  labInvestgationId.push(labMarkValue);
								}
					         
					         
			    	
			    	var count=0;
			    		$('#dgInvetigationGrid tr').each(function(i, el) {
					    var $tds = $(this).find('td')
					  		
						var itemIdInvestigation = $tds.eq(5).find(":input").val();
						var investigationDate = $tds.eq(1).find(":input").val();
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
			    				
						}
				
						tableDataInvestigation.push(dataInvestigation);
						//console.log("aaaaaaaaaIIIII " +tableDataInvestigation );
			    	});
				
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
	            		
	            		dataReferalDT={'diagnosisId' : diagonsisId}
	            		listofReferalDT.push(dataReferalDT);
	            		
	            		dataReferalHD={
	            				'extHospitalId' : splitHospital[0],
	            				'department' : department,
	            				'patientId' : $('#patientId').val(),
	            				'hospitalId' : '1',
	            				'referralNote' : referalNote,
	            				'instruction' : instruction,
	            				'listofReferalDT':listofReferalDT,
	            		       			}
	            		listofReferallHD.push(dataReferalHD);
	            	});
	            		
	            	
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
	      			'hospitalId':'1',
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
                 
            }); */

            ///////////// Code by  02/02/2019 ////////////////////////

         /*    function deleteDgItems(value) {
					alert("value"+document.getElementById('diagnosisId').options[document.getElementById('diagnosisId').selectedIndex].value );
				var referPatientDtOrDiagnosis=	document.getElementById('diagnosisId').options[document.getElementById('diagnosisId').selectedIndex].value;
				if(referPatientDtOrDiagnosis.includes("&&&");){
					referPatientDtOrDiagnosis=referPatientDtOrDiagnosis.split("&&&");
					
				}
                if (confirm("are you sure want to delete ?")) {

                    if (document.getElementById('diagnosisId').options[document.getElementById('diagnosisId').selectedIndex].value != null)
                        document.getElementById('diagnosisId').remove(document.getElementById('diagnosisId').selectedIndex)

                }
            } */

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
                	
                	$.ajax({
                				url : url,
                				dataType : "json",
                				data : JSON.stringify({
                					'employeeId' : '1'
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
        			'employeeId' : "1",
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
        			'employeeId' : "1",
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
        		    
        		if(ChargeCode == "")
        		{
        		//document.getElementById('chargeCodeName'+count).value="";
        		//document.getElementById('chargeCodeCode'+count).value="";
        		return;
        		}
        		else{
        		          	
        			 
        		}           
        		}
        		}	
        		}
       
  		function putInvestigationValue(item){
        	$(item).closest('tr').find("td:eq(0)").find("input:eq(3)").val(ChargeCode);
        	
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
  		
  		
  		function testPrescriptionData()
  		{
  			var tableDataInvestigation = [];  
	    	var dataInvestigation='';
	    	var labMarkValue = '';   	
	    	var labInvestgationId=[];
			var rawArrayInvestigation=[];
	    	 for(var j=0;j<lastRow;j++){
				  var f = 'othAfLab1'+j+'';
				    if(document.getElementById(f).checked == true){
						  var iinLab='I';
						  labMarkValue=iinLab;
						  alert("in lab "+iinLab);
					  }
					  else
						  {
						  var outLab='O';
						  alert("out lab "+outLab);
						  labMarkValue=outLab;
						  }
				        
					  labInvestgationId.push(labMarkValue);
						}
	    	
	    	var count=0;
	    		$('#dgInvetigationGrid tr').each(function(i, el) {
			    var $tds = $(this).find('td')
			  		
				var itemIdInvestigation = $tds.eq(5).find(":input").val();
				var investigationDate = $tds.eq(1).find(":input").val();
				var labinvastigation="";
				 for (var i = count; i <labInvestgationId.length; i++) {
					 if(i==count){
						 labinvastigation=labInvestgationId[i];
						 break;
					 }
					 
				 }
				 count++;
				dataInvestigation={
	    				'investigationItemId' : itemIdInvestigation,
	    				'orderDate' : investigationDate,
	    				'labMark' : labinvastigation,
	    				
				}
		
				tableDataInvestigation.push(dataInvestigation);
				console.log("aaaaaaaaaIIIII " +tableDataInvestigation );
	    	}); 
	    
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
    				'employeeId' : "1",
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
    	               } else if (jqXHR.status == 404) {REFERAL
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
    	  $(item).closest('tr').find("td:eq(0)").find("input:eq(1)").val(itemIds)
      }
	    	 

     
     var lastRow;
    
     var i=0;
      function addRowForInvestigationFun(){
     	  var tbl = document.getElementById('dgInvetigationGrid');
        	lastRow = tbl.rows.length;
        	i =lastRow;
        	i++;
     	  var aClone = $('#dgInvetigationGrid>tr:last').clone(true)
     		aClone.find(":input").val("");
     		aClone.find("option[selected]").removeAttr("selected")
     		aClone.find('input[type="radio"]').prop('name', 'othAfLab1'+i+'');
     		aClone.find('input[type="radio"]').prop('id', 'othAfLab1'+i+'');
     		aClone.find('input[type="checkbox"]').prop('id','uCheck1'+i+'');

     		aClone.clone(true).appendTo('#dgInvetigationGrid');
     		var val = $('#dgInvetigationGrid>tr:last').find("td:eq(0)").find(":input")[0];
     		autocomplete(val, arryInvestigation);
           //alert($('#dgInvetigationGrid>tr:last').find('td:eq(6)').find('button:eq(0)').attr('id'));
           //dynamic create id for button
         	$('#dgInvetigationGrid>tr:last').find("td:eq(6)").find("button:eq(0)").attr("id","newIdInv");
           
         	//alert($('#dgInvetigationGrid>tr').closest('tr').find("td:eq(4)").find('input:eq(0)').attr('id'));
           //dynamic create id for check box
         	//$('#dgInvetigationGrid>tr').closest('tr').find("td:eq(4)").find('input:eq(0)').attr('id','uCheck1'+i+'');
     	} 

      function addNomenclatureRowRecall() {
     		var aClone = $('#nomenclatureGrid>tr:last').clone(true)
     		aClone.find(":input").val("");
     		aClone.find("option[selected]").removeAttr("selected")
     		aClone.clone(true).appendTo('#nomenclatureGrid');
     		var val = $('#nomenclatureGrid>tr:last').find("td:eq(0)").find(":input")[0];
     		autocomplete(val, arryNomenclature);
     		$('#nomenclatureGrid>tr:last').find("td:eq(10)").find('button:eq(0)').attr("id","newIdTre");
     	}
      

    
   
     
      var labMarkArray=[]; 
      var urgentValuearray=[];
       function labCheckRadio(item) {
    	   var tbl = document.getElementById('dgInvetigationGrid');
       			lastRow = tbl.rows.length;
    		var labMarkValue = ''; 
      	  for(var j=1;j<=lastRow;j++){
      		  var f = 'othAfLab1'+j+'';
      		 if(document.getElementById(f).checked == true){
      		  var iinLab='I';
      		  labMarkValue=iinLab;
      		  //break;
      		  }
      		  else
      		  {
      		  var outLab='O';
      		  labMarkValue=outLab;
      			//break;
      		  }
      		        
      		    labMarkArray.push(labMarkValue);
      		}
      	   var count=0;
      	  
      	  
      	  
      	  $(item).closest('tr').find("td:eq(0)").find("input:eq(6)").val(labMarkValue);
      	 
       		}
       
       
       function onCheckedUrgent(item){
    	   $('#dgInvetigationGrid tr').each(function(i, el) {
         	    var $tds = $(this).find('td')
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
         	    urgentValuearray.push(urgent);
         	   });
    	   $(item).closest('tr').find("td:eq(0)").find("input:eq(7)").val(urgent);
       }
     
        </script>






<!-- <div class="modal" id="messageDelete"  tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div> -->







	<div class="modal hide z-index5000" id="messageDelete" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<!-- 	<button type="button" class="close btnclose" data-dismiss="modal" aria-hidden="true"  id="btncross"></button> -->


					<span class="Message_htext">Indian coast Guard</span>

					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>

				</div>
				<div class="modal-body">
					<div class="control-group">
						<div class="">
							<!-- <spring:message code="lblWarningMessageJSI" /> -->
							<p class="message_text">Please add first another
								Investigation.</p>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal"
						onClick="closeDelete();" aria-hidden="true">Close</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>