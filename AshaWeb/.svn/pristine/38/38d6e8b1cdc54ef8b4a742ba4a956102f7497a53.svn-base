<%@page import="java.io.InputStream"%>
<%@page import="java.net.URL"%>

<!-- Bootstrap core CSS     -->
<link href="${pageContext.request.contextPath}/resources/css/stylecommon.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/datepickericg.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" />

<link href="${pageContext.request.contextPath}/resources/assets/css/animate.min.css" rel="stylesheet" />

<!--  Paper Dashboard core CSS    -->
<link href="${pageContext.request.contextPath}/resources/assets/css/paper-dashboard.css" rel="stylesheet" />

<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="${pageContext.request.contextPath}/resources/assets/css/demo.css" rel="stylesheet" />

<!--  Fonts and icons     -->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
<link href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css" rel="stylesheet">

<!--    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></link> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/resources/js/gridForPatient.js?n=1"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pops_global.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pops_menu.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/newOpd.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/opd.js"></script>


<div class="clear"></div>
<%
	int i = 1;
%>
<form name="opdPatientList" method="post" action="">
	<form name="header" method="post">
		<!--header Starts-->
		<div class="header">

			<div class="hdText">
				<!-- <img src="/hms/jsp/images/careIsLogo.gif" class="floatRight" alt="careIs Logo" /> -->

				<div class="dateTimeDiv">

					<span> <script type="text/javascript">
						function getNoticeData() {
							alert("hi");
							var xmlHttp;
							try {
								// Firefox, Opera 8.0+, Safari
								xmlHttp = new XMLHttpRequest();
							} catch (e) {
								// Internet Explorer
								try {
									xmlHttp = new ActiveXObject(
											"Msxml2.XMLHTTP");
								} catch (e) {
									alert(e)
									try {
										xmlHttp = new ActiveXObject(
												"Microsoft.XMLHTTP");
									} catch (e) {
										alert("Your browser does not support AJAX!");
										return false;
									}
								}
							}

							xmlHttp.onreadystatechange = function() {
								if (xmlHttp.readyState == 4) {

									var notice = xmlHttp.responseXML
											.getElementsByTagName('notice')[0];
									var desc = notice
											.getElementsByTagName("desc")[0];

									//alert("notice value::::"+desc.childNodes[0].nodeValue);
									if (desc.childNodes[0].nodeValue != 'nodesc') {
										document.getElementById('noticeLabel').value = desc.childNodes[0].nodeValue;
									} else {
										document.getElementById('noticeLabel').value = '';
									}
									//document.getElementById('noticeLabel').value = desc.childNodes[0].nodeValue; 
									//document.getElementById("noticeDiv").innerHTML = '<marquee>'+noticeData+'</marquee>' ;

								}
							}
							//alert('hello');
							var url = '/hms/hms/login?method=getNoticeData';

							xmlHttp.open("GET", url, true);
							xmlHttp
									.setRequestHeader("Content-Type",
											"text/xml");
							xmlHttp.send(null);
						}

						/*
						 var currentDate = new Date()
						 var day = currentDate.getDate()
						 var month = currentDate.getMonth()
						 var year = currentDate.getFullYear()
						 document.write(day + "/" + month + "/" + year)	
						 */
						function getCalendarDate() {
							var months = new Array(13);
							months[0] = "January";
							months[1] = "February";
							months[2] = "March";
							months[3] = "April";
							months[4] = "May";
							months[5] = "June";
							months[6] = "July";
							months[7] = "August";
							months[8] = "September";
							months[9] = "October";
							months[10] = "November";
							months[11] = "December";

							var monthname = 'January';
							var monthday = '13';
							var year = '2019';

							if (year < 2000) {
								year = year + 1900;
							}
							var dateString = monthname + ' ' + monthday + ', '
									+ year;
							return dateString;
						}

						var calendarDate = getCalendarDate();
						document.write(calendarDate);
					</script>
					</span> <span> <script type="text/javascript">
						document
								.write('18' + ":" + '43' + " " + 'PM')
					//-->
					</script>
					</span>
				</div>
				<div class="hdTextFix">
					Super User <br /> Dept : <span
						style="color: #fff; font-size: 12px;">CHILDREN WARD</span>

				</div>
				<div class="homeLoginDiv">
					<span> <a href="#"
						onclick="submitForm('header','/hms/hms/login?method=showHomeJsp')"
						class="home-icon"></a>
					</span>
					<!-- <a href="http://www.maintguru.iaf.in/forum" target="_blank">Forum</a> | -->
					<!-- <a	href="#" target="blank">Contact</a> -->
					<a href="#" name="logout"
						onclick="submitForm('header','/hms/hms/login?method=logout')"
						class="logout-icon"></a>

				</div>
			</div>
			<!---header text ends--->

			<div class="hName">OPD CONSULTATION</div>
			<!-- 
<input type="button" value="Calculator" name="Button" onclick="ajaxFunctionForShowCalculator();"/>
 -->
			<div class="clear"></div>
			<input type="hidden" id="notice" name="notice" value="" />
			<!--  <div>
  <marquee direction="left">
<input type="text" readonly="readonly" id="noticeLabel" value=""  /></marquee>

</div>-->
		</div>
		</div>

		<div class="clear"></div>

	</form>

	<div class="content">
		<div class="container-fluid">
			<div class="row">

				<div class="col-lg-12 col-md-7">
					<div class="card">
						<div class="header">
							<h4 class="title">Service Details</h4>
						</div>
						<div class="content">
							<%--   <form action="<%=request.getContextPath()%>/v0.1/dashboard/saveVitailsPatientdetails" method="post"> --%>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Service No.</label> <input name="service_no."
											id="service" type="text" class="form-control border-input"
											placeholder="Service No." value="" required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Rank</label> <input name="patients_name" id="rank"
											type="text" class="form-control border-input"
											placeholder="Rank" value="" required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Name</label> <input name="relation" id="name"
											type="text" class="form-control border-input"
											placeholder="Name" required />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Trade/Branch</label> <input name="Trade/Branch"
											id="trade" type="text" class="form-control border-input"
											placeholder="Trade/Branch" value="" required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Total Service</label> <input name="Total Service"
											id="totalservice" type="text"
											class="form-control border-input" placeholder="Total Service"
											value="" required />
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Unit</label> <input name="Unit" id="unit" type="text"
												class="form-control border-input" placeholder="Unit"
												value="" required />
										</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label>Region/Command</label> <input name="regioncommand"
											id="regioncommand" type="text"
											class="form-control border-input" placeholder="regioncommand" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Record Office</label> <input name="recordoffice"
											id="recordoffice" type="text"
											class="form-control border-input" placeholder="Record Office" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Medical Categories</label> <input
											name="Medical Categories" id="medicalcategories" type="text"
											class="form-control border-input"
											placeholder="Medical Categories" value="" required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Duration</label> <input name="Duration" id="duration"
											type="text" class="form-control border-input"
											placeholder="Duration" value="" required>
									</div>

								</div>

							</div>

							<div class="row">
								<div class="col-md-4">
									<h4 class="title">Patient Details</h4>
									<div class="form-group">
										<label>Name</label> <input name="empname" id="empname"
											type="text" class="form-control border-input"
											placeholder="Name" value="" required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Region</label> <input name="region" id="region"
											type="text" class="form-control border-input"
											placeholder="Region" value="" required>
									</div>

								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>gender</label> <input name="Gender" id="Gender"
											type="text" class="form-control border-input"
											placeholder="Gender" value="" required>
									</div>

								</div>

							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>DOB</label> <input name="DOB" id="dob" type="text"
											class="form-control border-input" placeholder="DOB" value=""
											required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Age</label> <input name="Age" id="age" type="text"
											class="form-control border-input" placeholder="Age" value=""
											required>
									</div>

								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Marrital Status</label> <input name="Marrital Status"
											id="Marrital Status" type="text"
											class="form-control border-input"
											placeholder="marritalstatus" value="" required>
									</div>

								</div>

							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Religion</label> <input name="Religion" id="religion"
											type="text" class="form-control border-input"
											placeholder="Religion" value="" required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Address</label> <input name="Address" id="address"
											type="text" class="form-control border-input"
											placeholder="Address" value="" required>
									</div>

								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>City</label> <input name="City" id="city" type="text"
											class="form-control border-input" placeholder="City" value=""
											required>
									</div>

								</div>

							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>State</label> <input name="State" id="State"
											type="text" class="form-control border-input"
											placeholder="State" value="" required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Pin Code</label> <input name="Pin Code" id="pinCode"
											type="text" class="form-control border-input"
											placeholder="Pin Code" value="" required>
									</div>

								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Mobile No.</label> <input name="Mobile No"
											id="mobileno" type="text" class="form-control border-input"
											placeholder="Mobile No." value="" required>
									</div>

								</div>

							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>E-mail Address</label> <input name="email" id="email"
											type="text" class="form-control border-input"
											placeholder="E-mail Address" value="" required />
									</div>
								</div>

							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Allergy</label> <input name="Allergy" id="allergy"
											type="text" class="form-control border-input"
											placeholder="Allergy" value="" required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="container">New <input type="checkbox">
											<span class="checkmark"></span>
										</label> <label class="container">Follow Up <input
											type="checkbox"> <span class="checkmark"></span>
										</label>
									</div>

								</div>

								<div class="clearfix"></div>
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

								<div class="opdArea">
									
									<div class="clear"></div>

									<div id=hospidataId style="display: none">
										<label>Hospital Name</label> <input type="text"
											name="hospName" tabindex="1" size="100" value=" "
											maxlength="150" /> <input type="text" name="hospName"
											tabindex="1" size="100" value="" maxlength="150" /> <label
											class="auto">DOA</label> <input type="text" name="doa"
											class="date" id="doa" MAXLENGTH="30"
											validate="Pick a from date,date,no" value=""
											readonly="readonly" onblur="checkDate1(this.value,this.id)" />

										<input type="text" name="doa" class="date" id="doa"
											MAXLENGTH="30" validate="Pick a from date,date,no" value=""
											readonly="readonly" onblur="checkDate1(this.value,this.id)" />

										<img
											src="${pageContext.request.contextPath}/resources/images/cal.gif"
											width="16" height="16" border="0"
											onclick="javascript:setdate('doa',document.opdMain.doa, event)"
											validate="Pick a date" /> <label class="auto">DOD</label> <input
											type="text" name="dod" value=" " class="date" id="dod"
											MAXLENGTH="30" validate="Pick a from date,date,no"
											readonly="readonly" /> <input type="text" name="dod"
											value="" class="date" id="dod" MAXLENGTH="30"
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

										<div class="clear"></div>

										<%-- <label class="">Disposal</label>
<select name="pastdisposal" size="0" tabindex="1" class="med">
	<option value="">select</option>
	<% 

		for(MasDisposal masDisposalType : disposalTypeList){
			if(opdDetailsForFollowup != null){
			if(opdDetailsForFollowup.getDisposal() != null){
			if(opdDetailsForFollowup.getDisposal().equals(masDisposalType.getDisposalName())){
		%>
                                                                                <option value="<%=masDisposalType.getDisposalName() %>" selected="selected">
                                                                                    <%=masDisposalType.getDisposalName() %>
                                                                                </option>
                                                                                <%}}}else{%>
                                                                                    <option value="<%=masDisposalType.getDisposalName() %>">
                                                                                        <%=masDisposalType.getDisposalName() %>
                                                                                    </option>
                                                                                    <%}} %>
                                                                                        </select>
                                                                                        --%>
										<!-- <label>Days</label>
<input name="disposalDays" type="text" tabindex="1" maxlength="2" id="disposalDays" size="20" validate='Days,int,no'  />

 -->
										<%-- 	
<select name="pastdisposal" size="0" tabindex="1" class="med">
	<option value="0">select</option>
	<option value="ED">ED</option>
	<option value="MD">MD</option>
	<option value="LD">LD</option>
</select>
 --%>
										<div class="clear"></div>

									</div>

									<div class="floatLeft">

										<div class="clear"></div>
										<label>Chief Complaint</label>
										<textarea name="chiefCompliant" id="chiefCompliant" cols="0"
											rows="0" maxlength="5000" value="" tabindex="1"
											onkeyup="return ismaxlength(this)"></textarea>
										<input type="button" class="button" tabindex="3" name=""
											value="+" onclick="SelectName()" />
										<div class="clear"></div>

										<div class="clear"></div>
										<label>History Of Present Illness </label>
										<textarea name="historyPresentIllness"
											id="presentIllnessHistory" cols="0" rows="0" maxlength="5000"
											value="" tabindex="1" onkeyup="return ismaxlength(this)"></textarea>
										<input type="button" class="button" tabindex="3" name=""
											value="+" onclick="SelectName()" />
										<div class="clear"></div>

										<div class="clear"></div>
										<label>Past Medical History</label>
										<textarea name="pastMedicalHistory" id="pastMedicalHistory"
											cols="0" rows="0" maxlength="5000" value="" tabindex="1"
											onkeyup="return ismaxlength(this)"></textarea>
										<input type="button" class="button" tabindex="3" name=""
											value="+"
											onclick="getPresentTemplate('','pastMedicalHistory');" />
										<div class="clear"></div>

										<div class="clear"></div>
										<label>Present Surgical History</label>
										<textarea name="surgicalHistory" id="surgicalHistory" cols="0"
											rows="0" maxlength="5000" value="" tabindex="1"
											onkeyup="return ismaxlength(this)"></textarea>
										<input type="button" class="button" tabindex="3" name=""
											value="+" onclick="SelectName()" />

										<div class="clear"></div>

										<div class="clear"></div>
										<label>Medication History</label>
										<textarea name="medicationHistory" id="medicationHistory"
											cols="0" rows="0" maxlength="5000" value="" tabindex="1"
											onkeyup="return ismaxlength(this)"></textarea>
										<input type="button" class="button" tabindex="3" name=""
											value="+"
											onclick="getPresentTemplate('','pastMedicalHistory');" />
										<div class="clear"></div>

										<div class="clear"></div>
										<label>Personal History</label>
										<textarea name="personalHistory" id="personalHistory" cols="0"
											rows="0" maxlength="5000" value="" tabindex="1"
											onkeyup="return ismaxlength(this)"></textarea>
										<input type="button" class="button" tabindex="3" name=""
											value="+"
											onclick="getPresentTemplate('','pastMedicalHistory');" />
										<div class="clear"></div>

										<div class="clear"></div>
										<label>Social History</label>
										<textarea name="socialHistory" id="socialHistory" cols="0"
											rows="0" maxlength="5000" value="" tabindex="1"
											onkeyup="return ismaxlength(this)"></textarea>
										<input type="button" class="button" tabindex="3" name=""
											value="+"
											onclick="getPresentTemplate('','pastMedicalHistory');" />
										<div class="clear"></div>

										<div class="clear"></div>
										<label>Family History</label>
										<textarea name="familyHistory" id="familyHistory" cols="0"
											rows="0" maxlength="5000" value="" tabindex="1"
											onkeyup="return ismaxlength(this)"></textarea>
										<input type="button" class="button" tabindex="3" name=""
											value="+"
											onclick="getPresentTemplate('','pastMedicalHistory');" />
										<div class="clear"></div>

										<div class="clear"></div>
										<label>Allergy History</label>
										<textarea name="allergyHistory" id="allergyHistory" cols="0"
											rows="0" maxlength="5000" value="" tabindex="1"
											onkeyup="return ismaxlength(this)"></textarea>
										<input type="button" class="button" tabindex="3" name=""
											value="+"
											onclick="getPresentTemplate('','pastMedicalHistory');" />
										<div class="clear"></div>

										<div class="clear"></div>
										<label>Implant History</label>
										<textarea name="implantHistory" id="implantHistory" cols="0"
											rows="0" maxlength="5000" value="" tabindex="1"
											onkeyup="return ismaxlength(this)"></textarea>
										<input type="button" class="button" tabindex="3" name=""
											value="+"
											onclick="getPresentTemplate('','pastMedicalHistory');" />
										<div class="clear"></div>

									</div>

								</div>
								<div>
									<h4 class="title">Vitals</h4>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Height</label> <input name="height" id="height"
													type="text" class="form-control border-input"
													placeholder="Height" value="" required />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Ideal Weight</label> <input name="ideal_weight"
													id="ideal_weight" type="text"
													class="form-control border-input"
													placeholder="Ideal Weight" />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Weight</label> <input name="Weight" id="weight"
													type="text" class="form-control border-input"
													placeholder="Weight" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Variant In Weight</label> <input
													name="variant_in_weight" id="variant_in_weight" type="text"
													class="form-control border-input"
													placeholder="Variant In Weight" value="" required />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Tempature</label> <input name="tempature"
													id="tempature" type="text"
													class="form-control border-input" placeholder="Tempature"
													value="" required>
											</div>

										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>BP</label> <input name="bp" id="bp" type="text"
													class="form-control border-input" placeholder="bp" value=""
													required>
											</div>

										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Pulse</label> <input name="pulse" id="pulse"
													type="text" class="form-control border-input"
													placeholder="Pulse" value="" required />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Spo2</label> <input name="spo2" id="spo2" type="text"
													class="form-control border-input" placeholder="spo2"
													value="" required>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>BMI</label> <input name="bmi" id="bmi" type="text"
													class="form-control border-input" placeholder="BMI"
													value="" required>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>RR</label> <input name="rr" id="rr" type="text"
													class="form-control border-input" placeholder="RR" value=""
													required>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group">
												<input type="hidden" id="patient_Id" name="PatientID"
													value="">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<input type="hidden" id="visitId" name="VisitID" value="">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12"></div>
										<div class="col-md-4">
											<div class="form-group">
												<span class="checkmark"></span> <label class="container">Obesity
													Mark <input type="checkbox" id="obsistyMark"
													onclick="obsistyFunction()">
												</label>
												<p id="text" style="display: none">ObsistyMark is
													CHECKED!</p>
											</div>

										</div>
									</div>
									<!-- <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Postal Code</label>
                                                <input type="number" class="form-control border-input" placeholder="ZIP Code">
                                            </div>
                                        </div> -->
								</div>
								<div>
									<h4 class="title">Eximanation</h4>
									<h6 class="title">General Eximanation</h6>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Pollar</label> <input name="Pollar" id="pollar"
													type="text" class="form-control border-input"
													placeholder="Pollar" value="" required />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Ordema</label> <input name="Ordema" id="ordema"
													type="text" class="form-control border-input"
													placeholder="Ordema" />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Cyanosis</label> <input name="cyanosis" id="cyanosis"
													type="text" class="form-control border-input"
													placeholder="Cyanosis" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Hair/ Nail</label> <input name="hairnail"
													id="hairnail" type="text" class="form-control border-input"
													placeholder="Hair/ Nail" value="" required />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Jaundice</label> <input name="Jaundice" id="jaundice"
													type="text" class="form-control border-input"
													placeholder="Jaundice" value="" required>
											</div>

										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Lymph node</label> <input name="Lymph node"
													id="lymphnode" type="text"
													class="form-control border-input" placeholder="Lymph node"
													value="" required>
											</div>

										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Clubbing</label> <input name="Clubbing" id="clubbing"
													type="text" class="form-control border-input"
													placeholder="Clubbing" value="" required />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Thyroid</label> <input name="Thyroid" id="thyroid"
													type="text" class="form-control border-input"
													placeholder="spo2" value="" required>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Tremors</label> <input name="Tremors" id="tremors"
													type="text" class="form-control border-input"
													placeholder="Tremors" value="" required>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Others</label> <input name="Others" id="others"
													type="text" class="form-control border-input"
													placeholder="Others" value="" required>
											</div>
										</div>

									</div>

									<h6 class="title">System Eximanation</h6>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>CNS</label> <input name="CNS" id="cns" type="text"
													class="form-control border-input" placeholder="CNS"
													value="" required />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Chest/ Resp</label> <input name="Chest" id="chest"
													type="text" class="form-control border-input"
													placeholder="Chest/ Resp" />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Musculoskeletal</label> <input name="Musculoskeletal"
													id="musculoskeletal" type="text"
													class="form-control border-input"
													placeholder="Musculoskeletal" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>CVS</label> <input name="CVS" id="cvs" type="text"
													class="form-control border-input" placeholder="CVS"
													value="" required />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Skin</label> <input name="Skin" id="skin" type="text"
													class="form-control border-input" placeholder="Skin"
													value="" required>
											</div>

										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>GI</label> <input name="GI" id="gi" type="text"
													class="form-control border-input" placeholder="GI" value=""
													required>
											</div>

										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Genetic urinary</label> <input name="geneticurinary"
													id="geneticurinary" type="text"
													class="form-control border-input"
													placeholder="Genetic urinary" value="" required />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Others</label> <input name="Others" id="others1"
													type="text" class="form-control border-input"
													placeholder="Others" value="" required>
											</div>
										</div>

									</div>

									<h4 class="title">DIGNOSIS</h4>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>Working Diagnosis</label> <input
													name="workingdiagnosis" id="workingdiagnosis" type="text"
													class="form-control border-input"
													placeholder="Working Diagnosis" value="" required />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>SNOMED diagnosis</label> <input
													name="snomeddiagnosis" id="snomeddiagnosis" type="text"
													class="form-control border-input"
													placeholder="SNOMED diagnosis" value="" required>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<div class="autocomplete" style="width: 300px;">
													<!-- <div id="ac2update" style="display: none;" class="autocomplete"> </div> -->
													<label>ICD Diagnosis</label> <input name="icddiagnosis"
														id="icd" type="text" class="form-control border-input"
														placeholder="ICD Diagnosis" value="" required
														onblur="fillDiagnosisCombo(this.value);" />
												</div>
											</div>
										</div>
										<select name="diagnosisId" multiple="4" size="5" tabindex="1"
											id="diagnosisId" class="listBig"
											validate="ICD Daignosis,string,yes">
										</select> <input type="button" class="buttonDel" value=""
											onclick="deleteDgItems(this,'diagnosisId');" align="right" />
										<input type="checkbox" onclick="addNAIcd(' "
											style="margin-top: 0px; margin-right: 5px;"> Not
										available
									</div>

									<div class="clear"></div>


									<!-- fayaz added -->
									<h4>Investigation</h4>

									<div class="Block">
										<label>Template</label>
										<div id="investigationDiv">
											<select name="investigationTemplateId"
												id="investigationTemplateId" tabindex="1">
											</select>
										</div>
										<div id="createInvestigationDivToShowHide">
											<input name="investigationTemplate" type="button"
												value="Create Template" tabindex="1" class="buttonBig"
												onclick="showCreateInvestigationTemplate();" />
										</div>
										<div>
											<input name="createupdateTemplate" tabindex="1" type="button"
												value="Update Template" class="buttonBig"
												onclick="showUpdateInvestigationTemplate();" />
										</div>
										<div id="copyPrevInvestigationTemplateDiv"
											style="display: none">
											<input name="copyPrevInvestigationTemplate" tabindex="1"
												type="button" value="Copy Previous" class="buttonBig"
												onclick=" " />
										</div>
										<div id="investigationImportButton1">
											<input name="investigationImportButton1" tabindex="1"
												type="button" value="Import New" class="button"
												onclick="testPrescriptionData()" />
										</div>
										<label>Urgent</label> <input type="checkbox" name="urgent"
											tabindex="1" class="radioAuto" value="1" />
									</div>

								</div>


								<div class="clear"></div>
								<div class="Block">
									<div id="gridview">
										<table class="table table-responsive" border="0"
											align="center" cellpadding="0" cellspacing="0"
											id="investigationGrid">
											<tr>
												<td colspan="8">
													<div class="floatleft">
														<input type="radio" value="1" class="radioCheckCol2"
															style="margin-right: 5px;" name="labradiologyCheck"
															checked="checked" onchange="changeRadio(this.value)" />
														<div class="labRadiologyDivfixed">LAB</div>
														<input type="radio" value="2" class="radioCheckCol2"
															style="margin-right: 5px;" name="labradiologyCheck"
															onchange="changeRadio(this.value)" />
														<div class="labRadiologyDivfixed">Radiology</div>
														<input type="hidden" name="investigationCategory"
															id="investigationCategory" />
														<div class="clear"></div>
													</div>
													<div class="clear"></div> <input id="visitId"
													name="visitId" type="hidden" value=" " /> <input
													id="visitId1" name=" " type="hidden" value=" " /> <input
													name="hinId" id="hinId" type="hidden" value=" " /> <input
													name="departmentId" type="hidden" value=" " /> <input
													name="hospitalId" type="hidden" id="hospitalId" value="" />
												</td>
											</tr>
											<tr>
												<th>Investigation</th>
												<!-- <th scope="col">Refer to MH</th> -->

												<th>Date</th>
												<th>Mark as Other AF Lab</th>
												<th>Mark as Outside AF Lab</th>
												<th>Urgent</th>
												<th>Add</th>
												<th>Delete</th>
											</tr>

											<tr>

												<td>
													<div class="autocomplete">
														<input type="text" value="" id="chargeCodeName<%=i%>"
															class="form-control border-input" name="chargeCodeName"
															size="100"
															onblur="populateChargeCode(this.value,<%=i%>);" /> <input
															type="hidden" id="qty" tabindex="1" name="qty1" size="10"
															maxlength="6" validate="Qty,num,no" /> <input
															type="hidden" tabindex="1" id="chargeCodeCode<%=i%>"
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
													type="radio" value="I" id="othAfLab1"
													class="radioCheckCol2" style="margin-right: 15px;"
													name="labradiologyCheck1"
													onchange="labCheckRadio(this.value)" />
												</td>
												<td>
													<div class="labRadiologyDivfixed"></div> <input
													type="radio" value="O" id="othAfLab1"
													class="radioCheckCol2" style="margin-right: 15px;"
													name="labradiologyCheck1"
													onchange="labCheckRadio(this.value)" />
												</td>
												<td><input type="checkbox" name="urgent" id="uCheck"
													tabindex="1" class="radioAuto" value="1" /></td>
												<td><input name="Button" type="button"
													class="buttonAdd" value="" tabindex="1"
													onclick="addRowForInvestigation();" /></td>
												<td><input type="button" name="delete" value=""
													class="buttonDel" tabindex="1"
													onclick="removeRow('investigationGrid','hiddenValue',this);" />
												</td>

											</tr>



											<input type="hidden" value="1" name="hiddenValue"
												id="hiddenValue" />

										</table>

										<label>Other Investigation</label>
										<textarea name="otherInvestigation" cols="50" rows="0"
											maxlength="500" tabindex="1"
											onkeyup="return ismaxlength(this)" class="auto"></textarea>
										<div class="clear paddingTop15"></div>
										<table border="0" align="center" cellpadding="0"
											cellspacing="0" id="investigationGrid1">
										</table>


									</div>
								</div>

								<div class="clear paddingTop5"></div>
								<h4>Treatment</h4>
								<div class="Block">
									<label>Template</label>
									<div id="investigationDiv">
										<select name="investigationTemplateId"
											id="investigationTemplateId" tabindex="1">
											<%-- <select name="investigationTemplateId"	tabindex="1" onchange="showHideInvestigationTemplateCombo(this.value);">--%>
											<option value="0">Select</option>
										</select>
									</div>
									<div id="createInvestigationDivToShowHide">
										<input name="investigationTemplate" type="button"
											value="Create Template" tabindex="1" class="buttonBig"
											onclick="createTreatmentTemplate();" />
									</div>
									<div>
										<input name="createupdateTemplate" tabindex="1" type="button"
											value="Update Template" class="buttonBig"
											onclick="showUpdateOpdTempate('I');" />
									</div>
									<div id="copyPrevInvestigationTemplateDiv"
										style="display: none">
										<input name="copyPrevInvestigationTemplate" tabindex="1"
											type="button" value="Copy Previous" class="buttonBig"
											onclick="copyPrevInvestigationTempate('');" />
									</div>
									<div id="investigationImportButton1">
										<input name="investigationImportButton1" tabindex="1"
											type="button" value="Import New" class="button"
											onclick="referalJson()" ;" />
									</div>

								</div>
								<h6>Current Medication</h6>
								<div class="cmntable">
									<table border="0" align="center" cellpadding="0"
										cellspacing="0">
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
														<!-- <input
													type="hidden" name="itemId1" id="itemId1" value="" /> <input
													type="hidden" name="itemIdClassificationId1"
													id="itemIdClassificationId1" value="" /> -->
													</div>
												</td>

												<td><input type="text" name="dispensingUnit1"
													tabindex="1" id="dispensingUnit1" size="6"
													validate="AU,string,no" readonly="readonly" /> <!-- <input
													type="hidden" name="actualDispensingQty1" tabindex="1"
													id="actualDispensingQty1" value="" size="6"
													validate="AU,string,no" /> <input type="hidden"
													tabindex="1" id="itemClassCode1" name="itemClassCode1"
													validate="itemClassCode,string,no" value="" /> <input
													type="hidden" tabindex="1" id="highValueMedicine1"
													name="highValueMedicine1" validate="highValue,string,no"
													value="" /> --></td>

												<td><input type="text" name="dosage1" tabindex="1"
													value="" id="dosage1" size="5" maxlength="5" />
													<!-- onblur="checkDosageValidation(this.value,'1');fillValue('1')"  -->
												</td>

												<td><select name="frequency1" id="frequency1"
													class="medium">

												</select> <!-- <input type="hidden" name="frequencyValue1"
													id="frequencyValue1" value=""> <input type="text"
													name="sosQty1" tabindex="1" id="sosQty1"
													style="display: none;" size="3" onblur="fillValueNomenclature('1')"
													maxlength="3" validate="Sos Qty,num,no" /> --></td>

												<td><input type="text" name="noOfDays1" tabindex="1"
													id="noOfDays1" onblur="fillValueNomenclature('1')" size="5"
													maxlength="3" validate="No of Days,num,no" /></td>

												<td><input type="text" name="total1" tabindex="1"
													id="total1" size="5" validate="total,num,no"
													onblur="treatmentTotalAlert(this.value,1)" /></td>

												<td><input type="text" name="remarks1" tabindex="1"
													id="remarks1" size="10" maxlength="15" placeholder="1-1-1" />
												</td>

												<td><input type="text" name="closingStock1"
													tabindex="1" value="" id="closingStock1" size="3"
													validate="closingStock,string,no" /></td>
												<td><input type="hidden" value="" tabindex="1"
													id="itemIdNom" size="77" name="itemIdNom" /></td>
												<td><input name="Button" type="button"
													class="buttonAdd" value="" onclick="addNomenclatureRow();"
													tabindex="1" /></td>
												<td><input type="button" name="delete" value=""
													class="buttonDel"
													onclick="removeRow('nomenclatureGrid','nomenclaturehdb',this);"
													tabindex="1" /></td>

												<input type="hidden" name="pvmsNo1" tabindex="1"
													id="pvmsNo1" size="10" readonly="readonly" />
											</tr>

										</tbody>
										<tr>
									</table>

								</div>
								<div class="clear"></div>
								<div class="paddingTop40"></div>
								<div class="clear"></div>
								<div class="paddingTop40"></div>
								<div class="clear"></div>
								<div class="Block">

									<label>Procedure Care</label>
									<div class="clear"></div>
									<div class="clear"></div>
									<div class="clear"></div>
									<div class="floatLeft" style="width: 350px;">
										<input type="button" class="buttonAdd" alt="Add" tabindex="4"
											value="" onclick="addRowTreatmentNursingCare();" align="left" />
										<input type="button" class="buttonDel" tabindex="3"
											alt="Delete" value=""
											onclick="removeRowTreatmentNursingCare();" align="right" />
										<input type="radio" value=" " class="radioCheckCol2"
											id="procedureCheck" style="margin-right: 5px;"
											name="procedureCheck" checked="checked" onchange="">
										<div class="labRadiologyDivfixed">Procedure</div>
										<input type="hidden" id="nurCodePhy" value=" "> <input
											type="radio" value=" " class="radioCheckCol2"
											id="procedureCheck" style="margin-right: 5px;"
											name="procedureCheck" onchange="">
										<div class="labRadiologyDivfixed">Physiotherapy</div>
										<input type="hidden" name="nursingCategory"
											id="nursingCategory">

									</div>
									<div class="clear"></div>
									<div id="divTemplet" style="width: 700px;">
										<table border="0" align="center" cellpadding="0"
											cellspacing="0" id="gridNursing">

											<tr>
												<th>&nbsp;</th>
												<th>Nursing Care Name</th>
												<th>Frequency<span>*</span></th>
												<th>No.Of Days<span>*</span></th>
												<th>Remarks</th>
												<!-- <th>Alert Me</th> -->
											</tr>
											<tr>
												<td><input type="checkbox" disabled="disabled"
													class="radioCheck" id="nursingRadio" name="nursingRadio" />
												</td>

												<input type="checkbox" class="radioCheck" id="nursingRadio"
													name="nursingRadio">
												</td>

												<input type="hidden" value="" name="procedureDetailId"
													id="procedureDetailId" />
												<td><input readonly="readonly" type="text"
													class="opdTextBoxSmall textYellow" value=" "
													id="procedureName_nursing" size="35"
													name="procedureName_nursing" /> <input
													type="text" class="opdTextBoxSmall textYellow" value=""
													id="procedureName_nursing" size="35"
													name="procedureName_nursing"
													onblur="validateNursingCare(this.value, )" <%-- onkeypress="selectSNOMEDCT('ACTIVE','PROCEDURE','ALL',returnlimit_IN,callbck_index,'procedureName_nursing');" --%> 
										  />

													<input type="hidden" name="procedureType"
													id="procedureType" value="" />
													<div id="ac2updates_nursing" style="display: none;"
														class="autocomplete"></div> <script type="text/javascript"
														language="javascript" charset="utf-8">
											  new Ajax.Autocompleter('procedureName_nursing','ac2updates_nursing','registration?method=getProcedureForAutoComplete',{
											  callback: function(element, entry) {
								              return entry + '&minor_major=1'+'&procedureType=' + document.getElementById('nursingCategory').value;
								        }, parameters:'requiredField=procedureName_nursing'});
									</script></td>
												<td>
													<%-- onchange="populateNoOfDaysForNursingProcedure(this.value,'')" --%>
													<select name="frequency_nursing" id="frequency_nursing"
													tabindex="36">
														<option value="">Select</option>

												</select> </script>
												</td>
												<td id="nf"><input type="text" name="noOfDays_nursing"
													id="noOfDays_nursing" value=""
													class="opdTextBoxTSmall textYellow" size="5"
													validate="No. of days,num,no" /></td>
												<td><input readonly="readonly" value=" " type="text"
													name="remark_nursing" id="remark_nursing"
													class="largTextBoxOpd textYellow" maxlength="100" /> <input
													type="text" value="" name="remark_nursing"
													id="remark_nursing" class="largTextBoxOpd textYellow"
													maxlength="100" /></td>
										</table>
										<input type="hidden" id="procedureHeaderId"
											name="procedureHeaderId" value="" />
										</td> <input type="hidden" name="nursinghdb" value=" "
											id="nursinghdb" />
									</div>
								</div>


								<td><textarea size="20" class="large"
										id="remark_therapy<%--  --%>" name="remark_therapy<%--  --%>"
										validate="Remarks,string,no" value="" tabindex="1"
										style="width: 250px; height: 50px;" maxlength="1000"></textarea>
								</td>


								</table>
								
					
						<div id="referalDiv">
												<h4>REFERAL</h4>

												<label>Referral </label> <select id="referral"
													name="referral" class="midium">
													<option value="0">No</option>
													<option value="1">Yes</option>
												</select>

												<div id="referDiv" class="col collaps">
													<label>Refer To</label> <label class="autoSpace"><input
														type="radio" class="radioCheckCol2" name="referTo"
														id="referExternal" value="E"
														onclick="checkReferTO('referExternal');"
														style="margin: 1px 5px 0px 0px;" />External </label> <label
														class="autoSpace"> <input type="radio"
														class="radioCheckCol2" name="referTo" id="referInternal"
														value="I" onclick="checkReferTO('referInternal');"
														style="margin: 1px 5px 0px 0px;" />Internal
													</label>


													<!--  <label
											class="autoSpace"><input type="radio"
											class="radioCheckCol2" name="referTo" id="referBoth"
											value="Both" onclick="checkReferTO('Both');"
											style="margin: 1px 5px 0px 0px;" />Both
											</label> -->

													<div class="clear"></div>
													<label>Refer Date: </label> <input type="Date"
														name="referVisitDate" id="referVisitDate"
														placeholder="DD/MM/YYYY" value=" " />

													<div class="clear"></div>
													<!-- onblur="checkAdmte()" -->
													<label id="priorityLabelId">Current Proirity No.</label> <select
														id="priorityId" name="priorityName" tabindex="1">
														<option value="3">3</option>
														<option value="2">2</option>
														<option value="1">1</option>
													</select>

													<!-- <div class="clear"></div> -->
													<div id="referDepartmentDiv">
														<div class="clear"></div>
														<table id="referGrid">
															<tr>
																<th>S.No.</th>
																<th>Hospital</th>
																<th>Department</th>
																<th>Diganosis</th>
																<th>Instaruction</th>
																<th scope="col">Add</th>
																<th scope="col">Delete</th>
															</tr>
															<tbody id="referalGrid">

															</tbody>
														</table>
														<input type="hidden" value="1" name="hiddenValueRefer"
															id="hiddenValueRefer" />
													</div>
													<label id="referhospitalLabel" style="display: none;">Hospital
														<span>*</span>
													</label> <select id="referhospital" name="referhospital"
														onchange="fnGetHospitalDepartment(this.value);"
														style="display: none;">
														<option value="0">Select</option>

														<option value=" "></option>

													</select> <label id=referdayslLabel style="display: none;">No.
														of Days</label> <input id="referdays" name="referdays" type="text"
														maxlength="2" style="display: none;" />

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
														maxlength="300" validate="" style="display: none" />
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
								</div>
				<div class="row">
					<div class="col-md-12"></div>
				</div>
				<div class="text-center">
					<input type="submit" id="clicked"
						class="btn btn-info btn-fill btn-wd" value="Submit"> <input
						type="submit" id="reset" class="btn btn-info btn-fill btn-wd"
						value="Reset">
					</div>
				</div>
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
            autocomplete(document.getElementById("chargeCodeName1"), arryInvestigation);
            
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
                           
                            putIcdIdValue(autoIcdList, icdData);

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
         
            function fillDiagnosisCombo(val) {

                var index1 = val.lastIndexOf("[");
                var index2 = val.lastIndexOf("]");
                index1++;
                idIcdNo = val.substring(index1, index2);
                if (idIcdNo == "") {
                    return;
                } else {
                    obj = document.getElementById('diagnosisId');
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

<script type="text/javascript" language="javascript">
            
            '""'
            var $j = jQuery.noConflict();
            $j(document).ready(function() {
               // alert("localStorage.patient: " + localStorage.patientId);
                document.getElementById('visitId').value = localStorage.visitId;
                document.getElementById('patient_Id').value = localStorage.patientId;

            });
        </script>

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
            $('#clicked').click(function() {

                var pathname = window.location.pathname;
                var accessGroup = pathname.split("/")[1];

                var url = window.location.protocol + "//" + window.location.host + "/" + accessGroup + "/v0.1/opd/saveOpdPatientdetails";
				
            	if (checkBox.checked == true)
    			{
    			    var obsistyMark='true';
    			} 
				 
				 
				// var today = new Date();
				/*  var dd = today.getDate();
				 var mm = today.getMonth() + 1; //January is 0!

				 var yyyy = today.getFullYear();
				 if (dd < 10) {
				   dd = '0' + dd;
				 } 
				 if (mm < 10) {
				   mm = '0' + mm;
				 } */
				 
				    var today= document.getElementById('investigationDate1').value 
					alert(today);
				
				var row_array = [];
				var investigationDate=[];	
				for(var j=1;j<lastRow;j++){
										var nameRadio = 'othAfLab1'+j+'';
										alert(nameRadio)
										var labmark = $('input[name='+nameRadio+']:checked').val();
										row_array[j-1] = labmark;
										
					/* console.log("data is "+row_array);
					console.log("row array is "+row_array.length); */
					
				}
				
				
				 var arryChargeCode = [];
			    	for (var i = 0; i < multipleChargeCode.length; i++) {
			    	   // console.log("chargeCodeId" + ": " + multipleChargeCode[i]);
			    	    var param = {'investigationId' : multipleChargeCode[i],
			    	    		      /* 'labMark' : row_array[i], */
			    	    		      'urgent' :'y'}
			    	    arryChargeCode.push(param)
			    	   //console.log("data array chargeCode"+arryChargeCode);
			    	} 
			    	
			    	var tableDataPrescrption = [];  
			    	var dataPresecption='';
			    	
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
	            				'patientId' : '1',
	            				'hospitalId' : '1',
	            				'referralNote' : referalNote,
	            				'instruction' : instruction,
	            				'listofReferalDT':listofReferalDT,
	            		       			}
	            		listofReferallHD.push(dataReferalHD);
	            	});
	            		
	            		
	            		
				
				
                var dataJSON = {

                    'visitId': $('#visitId').val(),
                    'patientId': $('#patient_Id').val(),
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
                    "departmentId"  :  "1",
                    "orderStatus"   :  "y",
                    "listofInvestigation" : arryChargeCode,
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
                           window.location.href ="opdSubmit";
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
        		   //alert("value is fun "+ChargeCode);
               
        		if(ChargeCode == "")
        		{
        		document.getElementById('chargeCodeName'+count).value="";
        		document.getElementById('chargeCodeCode'+count).value="";
        		return;
        		}
        		else{
        			/*alert("first:" +ChargeCode);
        			//ChargeCode= document.getElementById('chargeCodeCode'+count+'').value=ChargeCode
        			ChargeCode = $('#chargeCodeCode'+count).val(ChargeCode);
        			alert(count-1);
        			alert(ChargeCode);
        			multipleChargeCode[count-1]=ChargeCode;
        			//alert("chargeCode"+multipleChargeCode);
        			console.log("multiplearray: "+multipleChargeCode);*/
        			
        			document.getElementById('chargeCodeCode'+count).value=ChargeCode
        			multipleChargeCode[count-1]=ChargeCode;
        			//console.log(multipleChargeCode);
        		}
        		}
        		}	
        		}
        	
        var lastRow;
      //var iteration='';
      function addRowForInvestigation(){
      	
      	 var hinId =  document.getElementById('hinId').value;
      	  var tbl = document.getElementById('investigationGrid');
      	  lastRow = tbl.rows.length;
      	  //alert(lastRow)
             // if there's no header row in the table, then iteration = lastRow + 1
      	  var iteration = lastRow;
      	  var row = tbl.insertRow(lastRow);
      	  var hdb = document.getElementById('hiddenValue');
      	  iteration = parseInt(hdb.value)+1;
      	  hdb.value=iteration
      	  // alert("iteration row--"+iteration)
        
      	  var cellRight0 = row.insertCell(0);
      	  var e0 = document.createElement('input');
      	  e0.type = 'text';
      	 // e0.innerHTML = iteration+':'
      	  e0.onblur=function(){
      		  populateChargeCode(this.value,iteration);
      	  						//if(validateInvestigationAutoComplete(this.value,iteration)){checkForChargeCode(this.value,iteration,'chargeCodeVal'+iteration);}
      	  					  };
      	   var newdiv1 = document.createElement('div');
      	  newdiv1.id='ac2updateInv'+iteration;
      	  //newdiv1.className='form-control border-input';
      	  newdiv1.style.display = 'none';
      	  newdiv1.className='form-control border-input';					
      	  e0.name = 'chargeCodeName' + iteration;
      	  e0.id = 'chargeCodeName' + iteration;
      	  e0.setAttribute('tabindex','1');
      	  //alert("name--"+e0.name)
      	  e0.size = '100'
      	  cellRight0.appendChild(newdiv1);
      	  
      	  cellRight0.appendChild(e0);
      	  e0.focus();
      	  if(i == ""){
      		  autocomplete(document.getElementById("chargeCodeName"+iteration), arryInvestigation);
      	  }else{
      		  autocomplete(document.getElementById("chargeCodeName"+iteration), arrayData);
      	  }
      	 // new Ajax.Autocompleter('chargeCodeName'+iteration,'ac2updateInv'+iteration,'opd?method=getInvestigationListForAutoComplete',{callback: function(element, entry) { return entry + '&labradiologyCheck=' + document.getElementById('investigationCategory').value;},parameters:'requiredField=chargeCodeName'+iteration});	  var sel = document.createElement('input');
      	  var sel = document.createElement('input');																																								
      	  sel.type = 'hidden';
      	  sel.name='chargeCodeCode'+iteration;
      	  sel.id='chargeCodeCode'+iteration
      	  sel.size = '10';
      	  sel.setAttribute('tabindex','1');
      	  cellRight0.appendChild(sel);

      	  var e2 = document.createElement('input');
      	  e2.type = 'hidden';
      	  e2.name='qty'+iteration;
      	  e2.id='qty'+iteration
      	  e2.size='10';
      	  e2.setAttribute('tabindex','1');
      	  cellRight0.appendChild(e2);
      	
      	  var cellRight2 = row.insertCell(1);
      	  var e3 = document.createElement('input');
      	  e3.type = "date";
      	  e3.name='investigationDate'+iteration;
      	  e3.placeholder="DD/MM/YYYY";
      	  //e3.value=document.getElementById("consultationDate").value;;
      	  e3.className='calDate';
      	  e3.id='investigationDate'+iteration;
      		 e3.setAttribute("onblur", "validateExpDate(this,'investigationDate"+iteration+"')");
      		 e3.setAttribute("onkeyup", "mask(this.value,this,'2,5','/')");
      		  e3.onchange=function(){checkForChargeCode(this.value,iteration,'chargeCodeVal'+iteration);
      				checkForAlreadyPrescribedInvestigation(this.value,iteration,visitId);
      	 };
      	 cellRight2.appendChild(e3);
      		 
      		  var cellRight3 = row.insertCell(2);
      		  var e4 = document.createElement('input');
      		  e4.type = 'radio';
      		  e4.name='labradiologyCheck'+iteration;
      		  e4.id='othAfLab'+iteration
      		  e4.value = "I";
      		  e4.name='radio';
      		  e4.setAttribute('name','labradiologyCheck'+iteration);
      		  //e4.setAttribute('onClick','addRowForInvestigation();');
      		 // e4.onclick = function(){changeRadio();}; 
      		  cellRight3.appendChild(e4);
      		  
      		  var cellRight4 = row.insertCell(3);
      		  var e5 = document.createElement('input');
      		  e5.type = 'radio';
      		  e5.name='labradiologyCheck'+iteration;
      		  e5.id='othAfLab'+iteration
      		  e5.value = "O";
      		  e5.name='radio';
      		  e5.setAttribute('name','labradiologyCheck'+iteration);
      		  //e4.setAttribute('onClick','addRowForInvestigation();');
      		  //e5.onclick = function(){changeRadio();}; 
      		  cellRight4.appendChild(e5);
      		  
      		  var cellRight5 = row.insertCell(4);
      		  var eCheck = document.createElement('input');
      		  eCheck.type = 'checkbox';
      		  eCheck.id='uCheck'+iteration;
      		  eCheck.name='radioAuto'+iteration;
      		  eCheck.value = "O";
      		  eCheck.name='checkbox';
      		  eCheck.setAttribute('name','radioAuto'+iteration);
      		  //alert(eCheck.setAttribute('name','radioAuto'+iteration))
      		  //eCheck.setAttribute('onClick','addRowForInvestigation();');
      		  //e5.onclick = function(){addRowForInvestigation();}; 
      		  cellRight5.appendChild(eCheck);
      		 
      	 var cellRight6 = row.insertCell(5);
      	  var e6 = document.createElement('input');
      	  e6.type = 'button';
      	  e6.className = 'buttonAdd';
      	  e6.value = "";
      	  e6.name='Button';
      	  e6.setAttribute('tabindex','1');
      	  //e4.setAttribute('onClick','addRowForInvestigation();');
      	  e6.onclick = function(){addRowForInvestigation();}; 
      	  cellRight6.appendChild(e6);

      	  var cellRight7 = row.insertCell(6);
      	  var e7 = document.createElement('input');
      	  e7.type = 'button';
      	  e7.className = 'buttonDel';
      	  e7.value = ""
      	  e7.name='delete';
      	  e7.setAttribute('tabindex','1');
      	  e7.onclick = function(){removeRow("investigationGrid","hdb",this);}; 
      	  cellRight7.appendChild(e7);
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
    	  
    	  $(item).closest('tr').find("td:eq(1)").find(":input").val(pvmsValue)
    	  $(item).closest('tr').find("td:eq(8)").find(":input").val(itemIds)
    	  
      }
	    	 

        </script>
 </div>
<!-- </div> -->

<div id="edited"></div>
<label>&nbsp;</label>
<div id="statusMessage"></div>
</div>
</div>