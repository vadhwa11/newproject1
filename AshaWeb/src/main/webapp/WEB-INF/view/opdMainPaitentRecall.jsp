<%@page import="java.util.HashMap"%>
    <%@page import="java.util.Map"%>
    
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

          
          <%@include file="..//view/leftMenu.jsp" %>

                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
									action="${pageContext.request.contextPath}/v0.1/opd/submitPatientRecall" autocomplete="off">
									<input type="hidden" name="opdPatientDetailId" value=""
										id="opdPatientDetailId" />
									<input type="hidden" name="patientId" value="" id="patientId" />
									<input type="hidden"  name="marksAsLabValue" value="" id="marksAsLabValue" />
									<input type="hidden"  name="urgentValue" value="" id="urgentValue" />
									
									<input type="hidden"  name="obsitymarkvalue" value="" id="obsitymarkvalue" />
									<input type="hidden"  name="icdIdValue" value="" id="icdIdValue" />
									
									
									<!-- -----  Service Detail  start here --------- -->	
									
							  <div class="adviceDivMain" id="button1" onclick="showhide(this.id)">
									<div class="titleBg" style="width: 520px; float: left;">
										<span>Service Detail  </span>
									</div>
									<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton1" value="+" onclick="showhide(this.id)" type="button">
								</div> 
							
							      <div class="hisDivHide" id="newpost1" style="display:block">
							      
							      <div class="row">

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Service No.
													</label>
													<div class="col-md-7">
														<input name="service_no." id="service" type="text"
															class="form-control border-input"
															placeholder="" value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Rank </label>
													<div class="col-md-7">
														<input name="patients_name" id="rank" type="text"
															class="form-control border-input" placeholder=""
															value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Name </label>
													<div class="col-md-7">
														<input name="relation" id="name" type="text"
															class="form-control border-input" placeholder=" "
															readonly />
													</div>
												</div>
											</div>



											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Trade/Branch
													</label>
													<div class="col-md-7">
														<input name="Trade/Branch" id="trade" type="text"
															class="form-control border-input"
															placeholder=" " value="" readonly />
													</div>
												</div>
											</div>


											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Total
														Service </label>
													<div class="col-md-7">
														<input name="Total Service" id="totalservice" type="text"
															class="form-control border-input"
															placeholder="" value="" readonly />
													</div>
												</div>
											</div>


											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Unit </label>
													<div class="col-md-7">
														<input name="Unit" id="unit" type="text"
															class="form-control border-input" placeholder=" "
															value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">
														Region/Command </label>
													<div class="col-md-7">
														<input name="regioncommand" id="regioncommand" type="text"
															class="form-control border-input"
															placeholder=" " readonly/>
													</div>
												</div>
											</div>


											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> Record
														Office </label>
													<div class="col-md-7">
														<input name="recordoffice" id="recordoffice" type="text"
															class="form-control border-input"
															placeholder=" " readonly/>
													</div>
												</div>
											</div>



											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Medical
														Category </label>
													<div class="col-md-7">
														<input name="Medical Categories" id="medicalcategories"
															type="text" class="form-control border-input"
															placeholder="" value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> Duration </label>
													<div class="col-md-7">
														<input name="Duration" id="duration" type="text"
															class="form-control border-input" placeholder=""
															value="" readonly>
													</div>
												</div>
											</div>
										</div>
							      
							      
							      
										 
								</div>
								
								<!-- -----  Service Detail  end here --------- -->	
																
									 
								
							<!-- -----  Patient Detail  start here --------- -->
									
							       <div class="adviceDivMain" id="button2" onclick="showhide(this.id)">
										<div class="titleBg" style="width: 520px; float: left;">
											<span>Patient Detail  </span>
										</div>
										<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton2" value="+" onclick="showhide(this.id)" type="button">
									</div>	
								
								
								      <div class="hisDivHide" id="newpost2" style="display:block">
								      
								      	<div class="row">
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Name </label>
													<div class="col-md-7">
														<input name="empname" id="empname" type="text"
															class="form-control border-input" placeholder=""
															value="" readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> Relation </label>
													<div class="col-md-7">
														<input name="relation" id="relation" type="text"
															class="form-control border-input" placeholder=""
															value="" readonly/>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> Gender </label>
													<div class="col-md-7">
														<input name="Gender" id="Gender" type="text"
															class="form-control border-input" placeholder=""
															value="" readonly/>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">DOB </label>
													<div class="col-md-7">
														<input name="DOB" id="dob" type="text"
															class="form-control border-input" placeholder=" "
															value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> Age </label>
													<div class="col-md-7">
														<input name="Age" id="age" type="text"
															class="form-control border-input" placeholder=" "
															value="" readonly/>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Marital Status </label>
													<div class="col-md-7">
														<input name="Marrital Status" id="Marrital Status"
															type="text" class="form-control border-input"
															placeholder=" " value="" readonly/>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> Religion </label>
													<div class="col-md-7">
														<input name="Religion" id="religion" type="text"
															class="form-control border-input" placeholder=" "
															value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Address </label>
													<div class="col-md-7">
														<input name="Address" id="address" type="text"
															class="form-control border-input" placeholder=" "
															value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">City </label>
													<div class="col-md-7">
														<input name="City" id="city" type="text"
															class="form-control border-input" placeholder=" "
															value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> State </label>
													<div class="col-md-7">
														<input name="State" id="State" type="text"
															class="form-control border-input" placeholder=" "
															value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> Pin Code </label>
													<div class="col-md-7">
														<input name="Pin Code" id="pinCode" type="text"
															class="form-control border-input" placeholder=" "
															value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Mobile No. </label>
													<div class="col-md-7">
														<input name="Mobile No" id="mobileno" type="text"
															class="form-control border-input"
															placeholder=" " value="" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> E-mail
														Address </label>
													<div class="col-md-7">
														<input name="email" id="email" type="text"
															class="form-control border-input"
															placeholder=" " value="" readonly />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Allergy </label>
													<div class="col-md-7">
														<input name="Allergy" id="allergy" type="text"
															class="form-control border-input" placeholder=" "
															value=""/>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> </label>
													<div class="col-md-7"></div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> </label>
													<div class="col-md-7"></div>
												</div>
											</div>

										</div>
      
		
	                   </div>
	
	
<!-- -----  Patient Detail  end here --------- -->





<!-- ----- Previous visits & Chief		Complaint start here --------- -->		

    <div class="adviceDivMain" id="button3" onclick="showhide(this.id)">
		<div class="titleBg" style="width: 520px; float: left;">
			<span>Previous visits & Chief		Complaint  </span>
		</div>
		<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton3" value="+" onclick="showhide(this.id)" type="button">
	</div>	


      <div class="hisDivHide" id="newpost3">
      
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
																	<div class="col-md-1"></div>
																	<label class="col-md-3 col-form-label">Chief
																		Complaint </label> 
																	<div class="col-md-6">
																		<textarea class="textNew" name="chiefCompliant"
																			id="chiefCompliant" cols="0" rows="0" value=""
																			tabindex="1"></textarea>
																	</div>
																	<!-- <div class="col-md-1">
																		<input type="button" class="button" tabindex="3"
																			name="" value="+" onclick="SelectName()" />
																	</div> -->
																	<div class="col-md-2"></div>
																</div>
															</div>

															<div class="col-md-12">
																<div class="form-group row">
																	<div class="col-md-1"></div>
																	<label class="col-md-3 col-form-label">History
																		Of Present illness </label>
																	<div class="col-md-6">
																		<textarea class="textNew" name="historyPresentIllness"
																			id="presentIllnessHistory" cols="0" rows="0" value=""
																			tabindex="1"></textarea>

																	</div>
																	<!-- <div class="col-md-1">
																		<input type="button" class="button" tabindex="3"
																			name="" value="+" onclick="SelectName()" />
																	</div> -->
																	<div class="col-md-1"></div>

																</div>
															</div>

															<div class="col-md-12">
																<div class="form-group row">
																	<div class="col-md-1"></div>
																	<label class="col-md-3 col-form-label">Past
																		Medical History </label>
																	<div class="col-md-6">
																		<textarea class="textNew" name="pastMedicalHistory"
																			id="pastMedicalHistory" cols="0" rows="0" value=""
																			tabindex="1"></textarea>
																	</div>
																	<!-- <div class="col-md-1">
																		<input type="button" class="button" tabindex="3"
																			name="" value="+"
																			onclick="getPresentTemplate('','pastMedicalHistory');" />
																	</div> -->

																	<div class="col-md-1"></div>

																</div>
															</div>

															<div class="col-md-12">
																<div class="form-group row">
																	<div class="col-md-1"></div>
																	<label class="col-md-3 col-form-label">Past Surgeries</label>
																	<div class="col-md-6">
																		<textarea class="textNew" name="surgicalHistory"
																			id="surgicalHistory" cols="0" rows="0" value=""
																			tabindex="1"></textarea>
																	</div>
																	<!-- <div class="col-md-1">
																		<input type="button" class="button" tabindex="3"
																			name="" value="+" onclick="SelectName()" />
																	</div> -->
																	<div class="col-md-1"></div>

																</div>
															</div>

															<div class="col-md-12">
																<div class="form-group row">
																	<div class="col-md-1"></div>
																	<label class="col-md-3 col-form-label">Medication
																		History </label>
																	<div class="col-md-6">
																		<textarea class="textNew" name="medicationHistory"
																			id="medicationHistory" cols="0" rows="0" value=""
																			tabindex="1"></textarea>
																	</div>
																	<!-- <div class="col-md-1">
																		<input type="button" class="button" tabindex="3"
																			name="" value="+"
																			onclick="getPresentTemplate('','pastMedicalHistory');" />
																	</div> -->
																	<div class="col-md-1"></div>

																</div>
															</div>

															<div class="col-md-12">
																<div class="form-group row">
																	<div class="col-md-1"></div>
																	<label class="col-md-3 col-form-label">Personal
																		History </label>
																	<div class="col-md-6">
																		<textarea class="textNew" name="personalHistory"
																			id="personalHistory" cols="0" rows="0" value=""
																			tabindex="1"></textarea>
																	</div>
																	<!-- <div class="col-md-1">
																		<input type="button" class="button" tabindex="3"
																			name="" value="+"
																			onclick="getPresentTemplate('','pastMedicalHistory');" />

																	</div> -->
																	<div class="col-md-1"></div>

																</div>
															</div>
															<div class="col-md-12">
																<div class="form-group row">
																	<div class="col-md-1"></div>
																	<label class="col-md-3 col-form-label">Social
																		History </label>
																	<div class="col-md-6">
																		<textarea class="textNew" name="socialHistory"
																			id="socialHistory" cols="0" rows="0" value=""
																			tabindex="1"></textarea>
																	</div>
																	<!-- <div class="col-md-1">
																		<input type="button" class="button" tabindex="3"
																			name="" value="+"
																			onclick="getPresentTemplate('','pastMedicalHistory');" />
																	</div> -->
																	<div class="col-md-1"></div>

																</div>
															</div>
															<div class="col-md-12">
																<div class="form-group row">
																	<div class="col-md-1"></div>
																	<label class="col-md-3 col-form-label">Family
																		History </label>
																	<div class="col-md-6">
																		<textarea class="textNew" name="familyHistory"
																			id="familyHistory" cols="0" rows="0" value=""
																			tabindex="1"></textarea>
																	</div>
																	<!-- <div class="col-md-1">
																		<input type="button" class="button" tabindex="3"
																			name="" value="+"
																			onclick="getPresentTemplate('','pastMedicalHistory');" />
																	</div> -->
																	<div class="col-md-1"></div>

																</div>
															</div>
															<div class="col-md-12">
																<div class="form-group row">
																	<div class="col-md-1"></div>
																	<label class="col-md-3 col-form-label">Allergy
																		History </label>
																	<div class="col-md-6">
																		<textarea class="textNew" name="allergyHistory"
																			id="allergyHistory" cols="0" rows="0" value=""
																			tabindex="1"></textarea>
																	</div>
																	<!-- <div class="col-md-1">
																		<input type="button" class="button" tabindex="3"
																			name="" value="+"
																			onclick="getPresentTemplate('','pastMedicalHistory');" />
																	</div> -->
																	<div class="col-md-1"></div>

																</div>
															</div>
															<div class="col-md-12">
																<div class="form-group row">
																	<div class="col-md-1"></div>
																	<label class="col-md-3 col-form-label">Implant
																		History </label>
																	<div class="col-md-6">
																		<textarea class="textNew" name="implantHistory"
																			id="implantHistory" cols="0" rows="0" value=""
																			tabindex="1"></textarea>
																	</div>
																	<!-- <div class="col-md-1">
																		<input type="button" class="button" tabindex="3"
																			name="" value="+"
																			onclick="getPresentTemplate('','pastMedicalHistory');" />

																	</div> -->
																	<div class="col-md-1"></div>

																</div>
															</div>

														</div>

														<%-- </form> --%>

													</div>

												</div>
											</div>

										</div>
      
      
			
	</div>


<!-- ----- Previous visits & Chief	Complaint end here --------- -->	





  <!-- ----- Vital Detail start here --------- -->

  <!-- <div class="adviceDivMain" id="button4" onclick="showhide(this.id)">
		<div class="titleBg" style="width: 520px; float: left;">
			<span>  Vital Detail </span>
		<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton4" value="+" onclick="showhide(this.id)" type="button">
	  </div>	
</div>	 -->

   <div class="adviceDivMain" id="button4" onclick="showhide(this.id)">
		<div class="titleBg" style="width: 520px; float: left;">
			<span>Vital Detail  </span>
		</div>
		<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton4" value="+" onclick="showhide(this.id)" type="button">
	</div>	





      <div class="hisDivHide" id="newpost4">
      
      
      	<div class="row">

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Height (in cm) </label>
													<div class="col-md-7">
														<input name="height" id="height" type="text" maxlength="10"
															class="form-control border-input" onblur="idealWeight();checkBMI();" placeholder="Height"
															value="" onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;" />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Ideal Weight
													</label>
													<div class="col-md-7">
														<input name="ideal_weight" id="ideal_weight" maxlength="10" onblur="checkVaration()" type="text"
															class="form-control border-input"
															placeholder="Ideal Weight" onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;" />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Weight (in kg) </label>
													<div class="col-md-7">
														<input name="Weight" id="weight" type="text"
															class="form-control border-input" maxlength="10" onblur="checkVaration();checkBMI();" placeholder="Weight" onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;" />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Variation in
														Weight </label>
													<div class="col-md-7">
														<input name="variant_in_weight" maxlength="10" id="variant_in_weight"
															type="text" class="form-control border-input"
															placeholder="Variation in Weight" value=""  readonly />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Temperature (in �C)</label>
													<div class="col-md-7">
														<input name="tempature" id="tempature" type="text" maxlength="12"
															class="form-control border-input"
															placeholder="Temperature" value="" onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;" />
													</div>
												</div>
											</div>
										<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">BP (in mm/Hg)</label>
													<div class="col-md-3">
														<input name="bp" id="bp" type="text"
															class="form-control border-input" placeholder="Diastolic"
															value="" onkeypress="if (isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;"/>
														 
													</div>
													
													
													<div class="col-md-1">
														/
														 
													</div>
													
													<div class="col-md-3">
														<input name="bp1" id="bp1" type="text"
															class="form-control border-input" placeholder="Systolic"
															value="" onkeypress="if (isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;"/>
														 
													</div>
													
													
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Pulse (in bpm) </label>
													<div class="col-md-7">
														<input name="pulse" id="pulse" type="text" maxlength="10"
															class="form-control border-input" placeholder="Pulse"
															value="" onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;" />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">SpO2 (in %) </label>
													<div class="col-md-7">
														<input name="spo2" id="spo2" type="text" maxlength="25"
															class="form-control border-input" onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;" placeholder="SpO2"
															value=""/>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">BMI (in kg/m2) </label>
													<div class="col-md-7">
														<input name="bmi" id="bmi" type="text" 
															class="form-control border-input" placeholder="BMI"
															value="" onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">RR (in bpm) </label>
													<div class="col-md-7">
														<input name="rr" id="rr" type="text" maxlength="3"
															class="form-control border-input" onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;" placeholder="RR"
															value=""/>
													</div>
												</div>
											</div>
											<!-- <div class="col-md-4" style="display:none;">
												<div class="form-group row">
													<input type="hidden" id="patientId" name="PatientID"
														value="">
												</div>
											</div> -->
											<div class="col-md-4" style="display:none;">
												<div class="form-group row">
													<input type="hidden" id="visitId" name="VisitID" value="">
												</div>
											</div>
											<div class="col-md-4" style="display:none;">
												<div class="form-group row">
													<input type="hidden" id="genderId" name="genderId" value="">
												</div>
											</div>
											 
											
												<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Mark Obesity
													</label>
													<div class="col-md-7">
														<input type="checkbox" id="obsistyMark" style="  position: relative; top: 10px;"
													onclick="obsistyFunction()">
													</div>
												</div>
											</div>

										</div>
      
      
			 
	</div>
<!-- ----- Vital Detail end here --------- -->




								<!-- ----- General Examination  start here --------- --> 

										   <div class="adviceDivMain" id="button5" onclick="showhide(this.id)">
												<div class="titleBg" style="width: 520px; float: left;">
													<span> General Examination</span> 
												</div>
												<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton5" value="+" onclick="showhide(this.id)" type="button">
											</div>	
										
										
										      <div class="hisDivHide" id="newpost5">
										      
										      
										      <div class="row">
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Pallor </label>
															<div class="col-md-7">
																<input name="Pollar" id="pollar" type="text"
																	class="form-control border-input" placeholder="Pallor"
																	value="" />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Edema </label>
															<div class="col-md-7">
																<input name="Ordema" id="ordema" type="text"
																	class="form-control border-input" placeholder="Edema" />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Cyanosis </label>
															<div class="col-md-7">
																<input name="cyanosis" id="cyanosis" type="text"
																	class="form-control border-input"
																	placeholder="Cyanosis" />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Hair/ Nail
															</label>
															<div class="col-md-7">
																<input name="hairnail" id="hairnail" type="text"
																	class="form-control border-input"
																	placeholder="" value="" />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Jaundice </label>
															<div class="col-md-7">
																<input name="Jaundice" id="jaundice" type="text"
																	class="form-control border-input"
																	placeholder=" " value=""/>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Lymph node
																 </label>
															<div class="col-md-7">
																<input name="Lymph node" id="lymphnode" type="text"
																	class="form-control border-input"
																	placeholder=" " value=""/>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Clubbing </label>
															<div class="col-md-7">
																<input name="Clubbing" id="clubbing" type="text"
																	class="form-control border-input"
																	placeholder=" " value="" />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Thyroid </label>
															<div class="col-md-7">
																<input name="Thyroid" id="thyroid" type="text"
																	class="form-control border-input" placeholder=" "
																	value="" />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Tremors </label>
															<div class="col-md-7">
																<input name="Tremors" id="tremors" type="text"
																	class="form-control border-input" placeholder=" "
																	value="" />
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Others </label>
															<div class="col-md-7">
																<input name="Others" id="others" type="text"
																	class="form-control border-input" placeholder=" "
																	value="" />
															</div>
														</div>
													</div>

												</div>
      
      
      
      
			 
	 </div>
	
<!-- ----- General Examination  end here --------- -->
								
								
								
								
								<!-- ----- System Examination  start here --------- -->


									 <div class="adviceDivMain" id="button6" onclick="showhide(this.id)">
											<div class="titleBg" style="width: 520px; float: left;">
												<span> System Examination</span>   
												
										</div>		
											<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton6" value="+" onclick="showhide(this.id)" type="button">
										</div>	
									
									
									      <div class="hisDivHide" id="newpost6">
									      
									      <div class="row">
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">CNS </label>
															<div class="col-md-7">
																<input name="CNS" id="cns" type="text"
																	class="form-control border-input" placeholder=" "
																	value="" />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Chest/
																Resp </label>
															<div class="col-md-7">
																<input name="Chest" id="chest" type="text"
																	class="form-control border-input"
																	placeholder=" " />
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Musculoskeletal
															</label>
															<div class="col-md-7">
																<input name="Musculoskeletal" id="musculoskeletal"
																	type="text" class="form-control border-input"
																	placeholder=" " />
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">CVS </label>
															<div class="col-md-7">
																<input name="CVS" id="cvs" type="text"
																	class="form-control border-input" placeholder=" "
																	value="" />
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Skin </label>
															<div class="col-md-7">
																<input name="Skin" id="skin" type="text"
																	class="form-control border-input" placeholder=" "
																	value="" />
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">GI </label>
															<div class="col-md-7">
																<input name="GI" id="gi" type="text"
																	class="form-control border-input" placeholder=" "
																	value="" />
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Genito Urinary
															</label>
															<div class="col-md-7">
																<input name="geneticurinary" id="geneticurinary"
																	type="text" class="form-control border-input"
																	placeholder=" " value="" />
															</div>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group row">
															<label class="col-md-5 col-form-label">Others </label>
															<div class="col-md-7">
																<input name="OthersForSystem" id="OthersForSystem" type="text"
																	class="form-control border-input" placeholder=" "
																	value=""/>
															</div>
														</div>
													</div>

												</div>
      
      
      
			 
	</div>
	<!-- ----- System Examination  end here --------- -->
								
								
								
								<!-- ----- Diagnosis  start here --------- -->
	
								 <div class="adviceDivMain" id="button7" onclick="showhide(this.id)">
									<div class="titleBg" style="width: 520px; float: left;">
										<span> Diagnosis </span>
								     </div>		
									<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton7" value="+" onclick="showhide(this.id)" type="button">
								</div>	
							
							
							      <div class="hisDivHide" id="newpost7">
							      
							      
							      <div class="row">

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">Working
														Diagnosis </label>
													<div class="col-md-7">
														<input name="workingdiagnosis" id="workingdiagnosis"
															type="text" class="form-control border-input"
															placeholder=" " value="" />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label">SNOMED
														Diagnosis </label>
													<div class="col-md-7">
														<input name="snomeddiagnosis" id="snomeddiagnosis"
															type="text" class="form-control border-input"
															placeholder=" " value="" />
													</div>
												</div>
											</div>

											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-md-5 col-form-label"> </label>
													<div class="col-md-7"></div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row  autocomplete">

													<label class="col-md-5 col-form-label">ICD
														Diagnosis </label> 
													<div class="col-md-7">
														<input name="icddiagnosis" id="icd" type="text"
															class="form-control border-input"
															placeholder=" " value="" 
															onblur="fillDiagnosisCombo(this.value);" />
													</div>

												</div>
											</div>

											<div class="col-md-8" id="diagnosisValueUpdate" style="margin-bottom:6px;">
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
					<!-- -----Diagnosis   end here --------- -->
								
								
								
								<!-- -----Investigation   start here --------- -->


      <div class="adviceDivMain" id="button8" onclick="showhide(this.id)">
		<div class="titleBg" style="width: 520px; float: left;">
			<span> Investigation</span>
		</div>
		<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton8" value="+" onclick="showhide(this.id)" type="button">
	</div>	


      <div class="hisDivHide" id="newpost8">
      
      
      	<div class="Block">
											<div class="row">
												<div class="col-md-4">
													<div class="form-group row">
														<label class="col-md-5 col-form-label"  style="padding-left: 10px;"> Template </label>
														<div class="col-md-7">
															<div id="investigationDivInvest">
																<select name="dgInvestigationTemplateIdInvest" class="form-control"
																	id="dgInvestigationTemplateIdInvest" tabindex="1">
																	<option value="0">Select</option>
																</select> 
															</div>
														</div>
													</div>
												</div> 
												<div class="col-md-2">
													<div class="form-group row">
														<div class="col-md-12">
															<div id="createInvestigationDivToShowHide"  class="opd_invetigation_createtemplate">
																<input name="investigationTemplate" type="button"
																	value="Create Template" tabindex="1" class="buttonBig"
																	onclick="showCreateInvestigationTemplate();" />
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-2">
													<div class="form-group row">
														<div class="col-md-12">
															<div  class="opd_invetigation_createtemplate  opd_invetigation_recall_import2">
																<input name="createupdateTemplate" tabindex="1"
																	type="button" value="Update Template" class="buttonBig"
																	onclick="showUpdateOpdTempate('I');" />
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-2">
													<div class="form-group row">
														<div class="col-md-12">
															<div id="investigationImportButton1" class="opd_invetigation_recall_import1">
																<input name="investigationImportButton1" tabindex="1"
																	type="button" value="Import New" class="button"
																	onclick="testPrescriptionData()" />
															</div>
														</div>
													</div>
												</div>


												<!-- <div class="col-md-2">
													<div class="form-group row">
														<label class="col-md-5 col-form-label"> Urgent </label>
														<div class="col-md-7">
															<input type="checkbox" name="urgent" tabindex="1"
																class="radioAuto" value="1" />
														</div>
													</div>
												</div> -->


											</div>
											
											<div class="row">
												<div class="col-md-1">
													<div class="form-group row">

														<div class="col-md-1">
															<input type="radio" value="1" class="radioCheckCol2"
																name="labradiologyCheck" checked="checked"
																onchange="changeRadio(this.value)" />
														</div>
														<label
															style="font: bold 12px/16px Arial, tahoma; color: #000000; background: none; text-align: left; back padding: 0px 9px;"
															class="col-md-5 col-form-label labRadiologyDivfixed">Lab</label>
													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group row">

														<div class="col-md-1">
															<input type="radio" value="2" class="radioCheckCol2"
																name="labradiologyCheck"
																onchange="changeRadio(this.value)" />

														</div>
														<label
															style="font: bold 12px/16px Arial, tahoma; color: #000000; background: none; text-align: left; padding: 0px 9px;"
															class="col-md-5 col-form-label labRadiologyDivfixed">Imaging</label>
													</div>
												</div>
												<div class="col-md-8">
													<div class="form-group row"></div>
												</div>


											</div>
											
											
											<div class="Block1">
												<!-- <div class="floatleft">
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
												</div> -->
												<br>





												<div id="gridview">
													<table class="table table-bordered" border="0"
														align="center" cellpadding="0" cellspacing="0"
														id="investigationGrid">
														<thead>
															<tr class="table-primary">
																<th style="width:30%;">Investigation</th>
																<th style="width:10%;">Date</th>
																<th style="width:20%;">Mark as Other AF Lab</th>
																<th style="width:20%;">Mark as Outside AF Lab</th>
																<th style="width:5%;">Urgent</th>
																<th style="width:7%;">Add</th>
																<th style="width:8%;">Delete</th> 
															</tr>
														</thead>

														<tbody id="dgInvetigationGrid">
															<tr>

																<td>
																	<div class="autocomplete">
																		<input type="text" value="" id="chargeCodeName"
																			class="form-control border-input"
																			name="chargeCodeName" size="44"
																			onblur="populateChargeCode(this.value,'1');putInvestigationValue(this)" />
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
															<!-- 	<td>
																<input type="Date" id="investigationDate1"
																	name="investigationDate" class="input_date"
																	placeholder="DD/MM/YYYY" value="" maxlength="10" />
																	</td> -->
																	
																	
																	<td><input type="date" id="investigationDate" name="investigationDate" class="input_date  form-control" placeholder="DD/MM/YYYY" value="" maxlength="10"> </td>
																	
																	
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
													<br> 
													
													
												<!-- 	
													<label>Other Investigation</label>
													<textarea name="otherInvestigation" id="otherInvestigation" cols="50" rows="0"
														maxlength="500" tabindex="1"
														onkeyup="return ismaxlength(this)" class="auto"></textarea>
													<div class="clear paddingTop15"></div> -->
													
													
											<div class="row">
												 <div class="col-md-6">
													<div class="form-group row">
														<label class="col-md-4 col-form-label" style="  padding-left: 10px;"> Other Investigation </label>
														<div class="col-md-7">
															<textarea name="otherInvestigation" id="otherInvestigation" cols="0" rows="0"
													        	maxlength="500" tabindex="1" onkeyup="return ismaxlength(this)" class="auto form-control">
														    </textarea>													 
													    </div>
												    </div>
												  </div>  
												<div class="col-md-6">
													 
												
												</div>
											</div> 
											 
													
													
													
													
													
													
													
													
													<table border="0" align="center" cellpadding="0"
														cellspacing="0" id="investigationGrid1">
													</table>

												</div>
											</div>


										</div>
       
	</div>
	<!-- -----Investigation   end here --------- -->
								
								
								
					<!-- -----Treatment   start here --------- -->
						
					      <div class="adviceDivMain" id="button9" onclick="showhide(this.id)">
							<div class="titleBg" style="width: 520px; float: left;">
								<span> Treatment</span>
							</div>
							<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton9" value="+" onclick="showhide(this.id)" type="button">
						</div>	
					
					
					      <div class="hisDivHide" id="newpost9">
					      
					      		<div class="Block">

											<div class="row">
												<div class="col-md-4">
													<div class="form-group row">
														<label class="col-md-6 col-form-label"> Template </label>
														<div class="col-md-6">
															<div id="investigationDivRecallTre">
																<select name="investigationTemplateIdTre" class="form-control"
																	id="investigationTemplateIdTre" tabindex="1">
																	<%-- <select name="investigationTemplateId"	tabindex="1" onchange="showHideInvestigationTemplateCombo(this.value);">--%>
																	<option value="0">Select</option>
																</select>
															</div>
														</div>
													</div>
												</div>

												<div class="col-md-2">
													<div class="form-group row">
													<div class="col-md-12">
														<div id="createInvestigationDivToShowHide"  class="opd_invetigation_createtemplate">
															<input name="investigationTemplate" type="button"
																value="Create Template" tabindex="1" class="buttonBig"
																onclick="createTreatmentTemplate();" />
														</div>
													</div>
													</div>
												</div>

												<div class="col-md-2">
													<div class="form-group row">
														<div class="opd_invetigation_recall_import">
															<input name="createupdateTemplate" tabindex="1"
																type="button" value="Update Template" class="buttonBig"
																onclick="showUpdateOpdTempate('I');" />
														</div>
													</div>
												</div>



												<div class="col-md-2">
													<div class="form-group row">
														<div id="investigationImportButton1" class="opd_invetigation_recall_import3">
															<input name="investigationImportButton1" tabindex="1"
																type="button" value="Import New" class="button"
																onclick="referalJson()" ;" />
														</div>
													</div>
												</div>

													<div class="col-md-2"></div>



											</div>

										</div>
										<!-- <h6>Current Medication</h6> -->
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
															validate="AU,string,no" readonly="readonly" class="form-control"/></td>

														<td><input type="text" name="dosage1" tabindex="1"
															value="" id="dosage1" size="5" maxlength="5"   class="form-control"/></td>

														<td><select name="frequencyTre" id="frequencyTre"
															class="medium form-control">

														</select></td>

														<td><input type="text"  class="form-control" name="noOfDays1" tabindex="1"
															id="noOfDays1" onblur="fillValueNomenclature('1')"
															size="5" maxlength="3" validate="No of Days,num,no" /></td>

														<td><input  class="form-control"  type="text" name="total1" tabindex="1"
															id="total1" size="5" validate="total,num,no"
															onblur="treatmentTotalAlert(this.value,1)" /></td>

														<td><input  class="form-control" type="text" name="remarks1" tabindex="1"
															id="remarks1" size="10" maxlength="15"
															placeholder="1-1-1" /></td>

														<td><input  class="form-control" type="text" name="closingStock1"
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
						
				<!-- -----Treatment   end here --------- -->
				
				
				
							<!-- -----Procedure   start here --------- -->
	
							 <div class="adviceDivMain" id="button10" onclick="showhide(this.id)">
								<div class="titleBg" style="width: 520px; float: left;">
									<span>Procedure  </span>
									
								</div>
								<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton10" value="+" onclick="showhide(this.id)" type="button">
							</div> 
						     <div class="hisDivHide" id="newpost10">
						     
						     
						     	<div class="Block">

											<div class="row">


												<div class="col-md-1"></div>

												<div class="col-md-3">
													<div class="form-group row">
														<div class="col-md-1">
															<input class="form-check-input" value="N" type="radio"
																checked="checked" name="procedureRadioName"
																id="procedureRadio"
																onchange="changeProcedureRadio(this.value)">
														</div>
														<label class="col-md-5 col-form-label">Nursing
															Care</label>

													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group row">
														<div class="col-md-1">
															<input class="form-check-input" value="P" type="radio"
																name="procedureRadioName" id="procedureRadio"
																onchange="changeProcedureRadio(this.value)">
														</div>
														<label class="col-md-5 col-form-label">Physiotherapy
														</label>

													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group row">
														<div class="col-md-1">
															<input class="form-check-input" value="M" type="radio"
																name="procedureRadioName" id="procedureRadio"
																onchange="changeProcedureRadio(this.value)">
														</div>
														<label class="col-md-5 col-form-label"> Minor
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
														<th>No. Of Days<span>*</span></th>
														<th>Remarks</th>
														<th>Add</th>
														<th>Delete</th>
														<!-- <th>Alert Me</th> -->
													</tr>
													<tbody id="gridNursing">
														<tr>
															<td>
																<div class="form-group autocomplete">
																	<input type="text" class="form-control border-input"
																		value="" id="procedureNameNursing" size="42"
																		name="procedureNameNursing"
																		onblur="populateNursing(this.value,'1');calucateNursingValue(this);" />
																</div>
															</td>

															<td>
																<%-- onchange="populateNoOfDaysForNursingProcedure(this.value, '')" --%>
																<select name="frequency_nursing" class="form-control" id="frequency_nursing"
																tabindex="36">
																	<option value="">Select</option>

															</select>

															</td>

															<td>
															<input type="text" name="noOfDays_nursing"
																id="noOfDays_nursing" value=""
																class="opdTextBoxTSmall textYellow form-control" size="5"
																validate="No. of days,num,no" />
															</td>
															<td>
															<input value="" type="text"
																name="remark_nursing" id="remark_nursing"
																class="largTextBoxOpd textYellow form-control" maxlength="100" />
															</td>
															<td style="display: none;">
															<input type="hidden"
																value="" id="procedureNameNursingId"
																name="procedureNameNursingId" />
															</td>
															<td style="display: none;">
															<input type="hidden"
																class="form-control border-input" value=""
																id="procedureNameNursing" size="55"
																name="procedureNameNursing" />
															</td>
															<td style="display: none;"><input type="hidden"
																class="form-control border-input" value=""
																id="procedureNameNursingCare" size="55"
																name="procedureNameNursingCare" />
															</td>
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
													<div class="clearfix"></div>
												</table>
											 <div class="clearfix"></div>
											 <br>
									 
											<div class="row">
												 <div class="col-md-6">
													<div class="form-group row">
														<label class="col-md-4 col-form-label" style="  padding-left: 10px;"> Additional Advice </label>
														<div class="col-md-7">
															 <textarea name="additionalNote"   class="form-control"
																validate="referralNote,string,no" id="additionalNote"
																cols="0" rows="0" maxlength="500" tabindex="5"
																onkeyup="return checkLength(this)">
															</textarea>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													 
												</div>
											</div> 
											 

											</div> 
									</div>
						      
							</div>
							
							<!-- ----- Procedure  end here --------- -->	
								
								
								
																		<!-- -----Referral   start here --------- -->
										 <div class="adviceDivMain" id="button11" onclick="showhide(this.id)">
											<div class="titleBg" style="width: 520px; float: left;">
												<span>Referral </span>
											</div>	
											<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton11" value="+" onclick="showhide(this.id)" type="button">
										</div>	
									
									
									      <div class="hisDivHide" id="newpost11">
									      
									               <div id="referalDiv">
											
													<div class="row" style="padding-left: 9px;">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-md-5 col-form-label">Referral</label>
																
																<div class="col-md-7">
																	<select  id="referralForNew" name="referralForNew" class="midium form-control"  onchange="getReferalDataForAdd();" >
																		<option  id="option1" value="0">No</option>
																		<option id="option2" value="1">Yes</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="col-md-4"></div>
														<div class="col-md-4"></div>
													</div>
													
													
													
												 

													<div id="referDiv" class="col collaps"
														style="display: block;">
														<label>Refer To</label> <label class="autoSpace" style="margin-left:12px;">
															<input type="radio" class="radioCheckCol2" name="referTo"
															id="referExternal" value="E"
															onclick="checkReferTO('referExternal');"
															style="margin: 1px 5px 0px 85px;" checked="checked">External
														</label> <label class="autoSpace" > <input type="radio"
															class="radioCheckCol2" name="referTo" id="referInternal"
															value="I" onclick="checkReferTO('referInternal');"
															style="margin: 1px 5px 0px 32px;">Internal
														</label>
														
														


													<!--  <label
											class="autoSpace"><input type="radio"
											class="radioCheckCol2" name="referTo" id="referBoth"
											value="Both" onclick="checkReferTO('Both');"
											style="margin: 1px 5px 0px 0px;" />Both
											</label> -->
											
											
											
													<div class="row">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-md-5 col-form-label">Referral Date</label>
																
																<div class="col-md-7">
																	 <input type="date"  class="form-control"  name="referVisitDate" id="referVisitDate" placeholder="DD/MM/YYYY" value="">
																</div>
															</div>
														</div>
														<div class="col-md-4"></div>
														<div class="col-md-4"></div>
													</div>
											 
									 
												<!-- 	<label>Referral Date:</label> <input type="Date" name="referVisitDate" id="referVisitDate" placeholder="DD/MM/YYYY" value=""> -->

 
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
																	<th style="width:26%;">Diganosis</th>
																	<th style="width:16%;">Instruction</th>
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
													
													
													
											<div class="row">
												 <div class="col-md-6">
													<div class="form-group row">
														<label class="col-md-4 col-form-label" style="  padding-left: 10px;"> Referral Notes</label>
														<div class="col-md-7">
															 <textarea name="referralNote" class="form-control" validate="referralNote,string,no" id="referralNote" cols="0" rows="0" maxlength="500" tabindex="5" onkeyup="return checkLength(this)" ></textarea> 
														</div>
													</div>
												</div>
												<div class="col-md-6">													 
												</div>
											</div>  
											 
										</div>
							</div>        
	         </div>
	
	<!-- ----- Referral  end here --------- -->
								
								
								<!-- -----Disposal   start here --------- -->
								
							 <div class="adviceDivMain" id="button12" onclick="showhide(this.id)">
								<div class="titleBg" style="width: 520px; float: left;">
									<span>  Disposal </span>
							   </div>
								<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton12" value="+" onclick="showhide(this.id)" type="button">
							</div>	
						
						
						      <div class="hisDivHide" id="newpost12">
						      
						      
										<div class="row">
											<div class="col-md-4">
												<div class="form-group row">

													<label class="col-md-5 col-form-label">Disposal Type</label>

													<div class="col-md-7">
														<select name="disposalId" id="disposalId" class="medium form-control">

														</select>
													</div>


												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">

													<label class="col-md-3 col-form-label">Days</label>

													<div class="col-md-7">
														<input type="text" class="form-control border-input"
															name="disposalDays" tabindex="1" value=""
															id="disposalDays" size="5" maxlength="5" />
													</div>
												</div>
											</div> 
											<div class="col-md-4"></div>

										</div>
						      
						      
									 
							</div>
							
							<!-- ----- Disposal  end here --------- --> 
								
								<div class="clearfix"></div>	
								
								<br>
										<div class="row">
										
										
										 <div class="col-md-12">
																<div class="btn-left-all">																 
															</div> 
															<div class="btn-right-all">
															<input type="submit" id="clicked111" class="btn btn-primary" onclick="return submitForm();"
															value="Submit"/>
															<button id="reset" class="btn  btn-danger"/> Reset </button>
																
															
															</div> 
													   </div> 
																										
													   </div>
										 

										</div>
								
						 
<!-- 
									Disposal start here
									<div class="opdMain_detail_area">
										<h4 class="service_htext">Disposal</h4>

										 
										
										
									</div>
 -->
								 


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

function obsistyFunctionRecall() {
  checkBox = document.getElementById("obsistyMark");
 // var text = document.getElementById("text");
  if (checkBox.checked == true){
   // text.style.display = "block";
   $('#obsitymarkvalue').val(1);
  } else {
	  $('#obsitymarkvalue').val(0);
  }
  //alert($('#obsitymarkvalue').val());
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
                popup = window.open("getFamilyPatinetHistory?employeeId="+<%=userId%>+"", "popUpWindow", "height=500,width=400,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
                popup.focus();
            }
        </script>

	<script type="text/javascript">
            var popup;

            function showCreateInvestigationTemplate() {
                popup = window.open("showCreateInvestigationTemplate?employeeId="+<%=userId%>+"", "popUpWindow", "height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
                popup.focus();
            }
            
            function createTreatmentTemplate() {
                popup = window.open("createTreatmentTemplate?employeeId="+<%=userId%>+"", "popUpWindow", "height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
                popup.focus();
            }

            function showUpdateInvestigationTemplate() {
                popup = window.open("showUpdateInvestigationTemplate?employeeId="+<%=userId%>+"", "popUpWindow", "height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
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
             var multiIcdValue=[];
            
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
                $('#icdIdValue').val(multiIcdValue);
            }
            
        /*  var total_icd_value = '';
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
            } */

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
                if (data.data[0].bp1 != null) {
                    document.getElementById('bp1').value = data.data[0].bp1;
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
                   if (data.data[0].relation != null) {
                       document.getElementById('relation').value = data.data[0].relation;
                   }
                   if(data.data[0].gender!=null){
                    	 document.getElementById('Gender').value=data.data[0].gender;
                    } 
                   
                   if(data.data[0].disposalDays!=null){
                  	 document.getElementById('disposalDays').value=data.data[0].disposalDays;
                  }
                if(data.data[0].disposalIdValue!=null){
                	  	disposalValueId  =data.data[0].disposalIdValue;
                }
				 
                if(data.data[0].obesityHdId!=null && data.data[0].relation!=null && data.data[0].relation=='SELF'){
                	var x = document.getElementById("obsistyMark");
                	  x.checked = true;
                	  $('#obsitymarkvalue').val(1);
                	  $('#markObesityId').show();
                	}
                else{
                	$('#obsitymarkvalue').val(0);
                	$('#markObesityId').hide();
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
	 var disposalValueId ="";
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
                					'employeeId' : <%= userId %>
                				}),
                				contentType : "application/json",
                				type : "POST",
                				success : function(response) {
                					console.log(response);
                					var datas = response.MasDisposal;
                					var trHTML = '<option value=""><strong>Choose...</strong></option>';
                					
                					$.each(datas, function(i, item) {
                						
                					var selectDisposal="";
                					if(disposalValueId!=""){	
                					if(disposalValueId == item.disposalId){
                							selectDisposal="selected";
                					}
                					else{
                						selectDisposal="";
                					}
                					}
                						trHTML += '<option '+selectDisposal+' value="' + item.disposalId + '" >'
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
        			var radioValue = 2; 
        			invesRadio=radioValue;
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
        var invesRadio="";
        function changeRadio(radioValue){
        	invesRadio=radioValue;
        	i++;
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
        			arryInvestigation=[];
        			arrayData=[];
        			var data = res.InvestigationList;
        			
        			for(var i=0;i<data.length;i++){
        				var investigationId= data[i].investigationId;
        				var investigationName = data[i].investigationName;
        				var a=investigationName+"["+investigationId +"]"
        				arrayData.push(a);
        				
        				var inChangeVal=$('#dgInvetigationGrid').children('tr:first').children('td:eq(0)').find(':input')[0]
        			       autocomplete(inChangeVal, arrayData);
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
        	//if(validateMetaCharacters(val)){
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
        	 
        		//$(val).closest('tr').find("td:eq(0)").find("input:eq(3)").val(ChargeCode);
        		return;
        		}
        		else{
        		          	
        			 
        		}           
        		}
        		}	
        	//}
       
  		function putInvestigationValue(item){
  			
  			$(item).closest('tr').find("td:eq(0)").find("input:eq(3)").val(ChargeCode)
  			   /*  $('#dgInvetigationGrid tr').each(function(i, el) {
	    		   var $tds = $(this).find('td')
	    		   var chargeCodeId=  $($tds).closest('tr').find("td:eq(0)").find("input:eq(3)").attr("id");
	    		   var investigationIDss=  $($tds).closest('tr').find("td:eq(0)").find("input:eq(0)").attr("id");
	    		    var chargeCodeIdValue=$('#'+chargeCodeId).val();
	    		    
	    		    var idddd=$(item).closest('tr').find("td:eq(0)").find("input:eq(3)").attr("id");
    		    	var currentidddd=$(item).closest('tr').find("td:eq(0)").find("input:eq(0)").attr("id");
    		    	
	    		    if(ChargeCode!="" &&  chargeCodeIdValue!="" && currentidddd!=investigationIDss){
	    		    if(ChargeCode==chargeCodeIdValue){
	    		    	$('#'+idddd).val("");
	    		    	$('#'+currentidddd).val("");
	    		    	alert("Plese select another Investigation.Investigation already selected.");
	    		    	return false;
	    		    }
	    		   
	    		    }
	    	});   */
        	
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
      var countinves="";
     function putPvmsValue(item){
    	 
    	  var pvmsValue = '';
    	 // var itemsIds="";
    	 // var itemsIdValue="";
		  // var nomerculid="";
    	  for(var i=0;i<autoVsPvmsNo.length;i++){
    		  var pvmsNo1 = data[i].pvmsNo;
    		  if(pvmsNo1 == pvmsNo){
    			  pvmsValue = data[i].dispUnit;
    			  itemIdPrescription = data[i].itemId;
    			  itemIds = itemIdPrescription;
    			  
    		  }
    	  }
    	  console.log("item is "+pvmsValue);
    	  $(item).closest('tr').find("td:eq(1)").find(":input").val(pvmsValue)
    	  $(item).closest('tr').find("td:eq(8)").find(":input").val(itemIds)
    	  $(item).closest('tr').find("td:eq(0)").find("input:eq(1)").val(itemIds)
    	  
    	      /*  $('#nomenclatureGrid tr').each(function(i, el) {
    	    		   var $tds = $(this).find('td')
    	    		   var itemsIds=  $($tds).closest('tr').find("td:eq(0)").find("input:eq(1)").attr("id");
    	    		   var itemsIdValue=$('#'+itemsIds).val();
    	    		   var nomerculid=  $($tds).closest('tr').find("td:eq(0)").find("input:eq(0)").attr("id");
    	    		   
    	    		    var nomerculIdItem=  $(item).closest('tr').find("td:eq(0)").find("input:eq(1)").attr("id");
    	    		    if(itemsIdValue!="" && itemIds!="" && itemsIdValue!='undefined' && itemIds!='undefined'
    	    		    		&& item.id!='undefined' && nomerculid!='undefined' && nomerculid!=item.id){
    	    		    	
    	    		    if(itemsIdValue==itemIds){
    	    		    	//alert("itemsIdValuesadadsa"+itemsIdValue);
    	    		    	//alert("itemIds>>>>>>>>>>>"+itemIds);
    	    		    	$('#'+item.id).val("");
    	    		    	$('#'+nomerculIdItem).val("");
    	    		    	alert("Plese select another Material Code.Material Code already selected.");
    	    		    	return false;
    	    		    }
    	    		     
    	    		    }
    	    		      
    	    		    
    	    	});  */
    	   
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
     		//aClone.find('input[type="text"]').prop('id', 'chargeCodeCode'+i+'');
     		aClone.find("td:eq(0)").find("input:eq(0)").prop('id', 'chargeCodeName'+i+'');
     		aClone.find("td:eq(0)").find("input:eq(3)").prop('id', 'investigationIdValue'+i+'');
     		aClone.find("td:eq(1)").find("input:eq(0)").prop('id', 'investigationDate'+i+'');
     		aClone.find("option[selected]").removeAttr("selected")
     		aClone.find('input[type="radio"]').prop('name', 'othAfLab1'+i+'');
     		aClone.find('input[type="radio"]').prop('id', 'othAfLab1'+i+'');
     		aClone.find('input[type="checkbox"]').prop('id','uCheck1'+i+'');

     		aClone.clone(true).appendTo('#dgInvetigationGrid');
     		var valInvestigation = $('#dgInvetigationGrid>tr:last').find("td:eq(0)").find(":input")[0];
     		
     		if(arryInvestigation!=null &&  (invesRadio!=null && invesRadio!="" && invesRadio=="2"))
    		{
    			arrayData=[];
    			autocomplete(valInvestigation, arryInvestigation);
    		}
    		else
    		{
    			arryInvestigation=[];
    			autocomplete(valInvestigation, arrayData);
    		}
     		//autocomplete(val, arryInvestigation);
           //alert($('#dgInvetigationGrid>tr:last').find('td:eq(6)').find('button:eq(0)').attr('id'));
           //dynamic create id for button
         	$('#dgInvetigationGrid>tr:last').find("td:eq(6)").find("button:eq(0)").attr("id","newIdInv");
           
         	//alert($('#dgInvetigationGrid>tr').closest('tr').find("td:eq(4)").find('input:eq(0)').attr('id'));
           //dynamic create id for check box
         	//$('#dgInvetigationGrid>tr').closest('tr').find("td:eq(4)").find('input:eq(0)').attr('id','uCheck1'+i+'');
     	} 
		var countRecall=1;
      function addNomenclatureRowRecall() {
    	  var tbl = document.getElementById('nomenclatureGrid');
  			var lastRow = tbl.rows.length;
  			countRecall=lastRow+1;
     		var aClone = $('#nomenclatureGrid>tr:last').clone(true)
     		aClone.find(":input").val("");
     		//aClone.find('input[type="text"]').prop('id', 'nomenclature'+countRecall+'');
     		aClone.find("td:eq(0)").find("input:eq(0)").prop('id', 'nomenclature'+countRecall+'');
     		aClone.find("td:eq(0)").find("input:eq(1)").prop('id', 'nomenclatureId'+countRecall+'');
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
	
	
		
<!-- ----------------Accordian  end here---------- -->

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
		$('#bmi').val(parseFloat(Math.round(sub * 100) / 100).toFixed(2));
		
	}
	
	function checkObsistyMark() {
		
			if(document.getElementById('variant_in_weight').value== '' && document.getElementById('variant_in_weight').value== undefined)
			{
				alert("Please enter Height and Varation and Weight");
			}
		    
		
		
		
	}
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
	

</body>
</html>