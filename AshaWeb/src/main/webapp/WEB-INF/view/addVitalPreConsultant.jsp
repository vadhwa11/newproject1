<%@page import="java.io.InputStream"%>
<%@page import="java.net.URL"%>


<!-- Bootstrap core CSS     -->
<link href="resources/css/stylecommon.css" rel="stylesheet"
	type="text/css" />
<link href="resources/css/datepickericg.css" rel="stylesheet"
	type="text/css" />

<link
	href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css"
	rel="stylesheet" />

<!-- Animation library for notifications   -->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/animate.min.css"
	rel="stylesheet" />

<!--  Paper Dashboard core CSS    -->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/paper-dashboard.css"
	rel="stylesheet" />

<!--  CSS for Demo Purpose, don't include it in your project     -->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/demo.css"
	rel="stylesheet" />

<!--  Fonts and icons     -->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300'
	rel='stylesheet' type='text/css'>
<link
	href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></link>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/resources/js/gridForPatient.js?n=1"></script>
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/pops_global.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/pops_menu.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<!-- <META HTTP-EQUIV="REFRESH" CONTENT="20"> -->
<script type="text/javascript">
<!--
	var SESSIONURL = "s=cccfbaab0a70ed43fad9de8e3733112d&";
	var IMGDIR_MISC = "images/misc";
	var vb_disable_ajax = parseInt("0", 10);
// -->
</script>




<div class="clear"></div>


<form name="opdPatientList" method="post" action="">
	<form name="header" method="post">
		<!--header Starts-->
		<div class="header">

			<div class="hdText">
				<!-- <img src="/hms/jsp/images/careIsLogo.gif" class="floatRight" alt="careIs Logo" /> -->

				<div class="dateTimeDiv">

					<span> <script type="text/javascript">
						function getNoticeData() {
							//alert("hi");
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



			<div class="hName">Capture OPD Pre-Consulation Assements</div>
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
							<h4 class="title">Patients Details</h4>
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
										<label>Patients Name</label> <input name="patients_name"
											id="patient_name" type="text"
											class="form-control border-input" placeholder="Patients Name"
											value="" required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Relation</label> <input name="relation" id="relation"
											type="text" class="form-control border-input"
											placeholder="Relation" required />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Gender</label> <input name="gender" id="gender"
											type="text" class="form-control border-input"
											placeholder="gender" value="" required />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Age</label> <input name="age" id="age" type="text"
											class="form-control border-input" placeholder="Age" value=""
											required />
									</div>
								</div>
							</div>
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
											class="form-control border-input" placeholder="Ideal Weight" />
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
											id="tempature" type="text" class="form-control border-input"
											placeholder="Tempature" value="" required>
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
											class="form-control border-input" placeholder="spo2" value=""
											required>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>BMI</label> <input name="bmi" id="bmi" type="text"
											class="form-control border-input" placeholder="BMI" value=""
											required>
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
										  <input type="hidden" id="patiendID" name="PatientID" value="">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										 <input type="hidden" id="visitId" name="VisitID" value="">
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
                       
                    		<div class="row">
							<div class="col-md-12"></div>
						</div>
						<div class="text-center">
							<input type="submit" id="clicked"
								class="btn btn-info btn-fill btn-wd" value="Submit"> <input
								type="submit" id="reset" class="btn btn-info btn-fill btn-wd"
								value="Reset">

						</div>
                           

						<div class="clearfix"></div>
</form>
</div>
</div>
</div>


</div>
</div>
</div>


<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>    

<script type="text/javascript" language="javascript">
	
</script>


 <script type="text/javascript" language="javascript">
 $('#clicked').click(function() {
			
			var pathname = window.location.pathname;
			var accessGroup = pathname.split("/")[1];

			var url = window.location.protocol + "//"
					+ window.location.host + "/" + accessGroup
					+ "/v0.1/opd/saveVitailsPatientdetails";
			
			var dataJSON={
	  			     
	      			 'visitId':$('#visitId').val(),
	      			'patientId':$('#patiendID').val(), 
	      			'idealWeight':$('#ideal_weight').val(),
	      			'height':$('#height').val(),
	      			'weight':$('#weight').val(),
	      			'varation':$('#variant_in_weight').val(),
	      			'temperature':$('#tempature').val(),
	      			'bp':$('#bp').val(),
	      			'pulse':$('#pulse').val(),
	      			'spo2':$('#spo2').val(),
	      			'bmi':$('#bmi').val(),
	      			'rr':$('#rr').val(),
	      		}

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url,
				data: JSON.stringify(dataJSON),
				dataType : 'json',
				timeout : 100000,
				success : function(msg)
				{
				  if (msg.status == 1)
				   	   {
		        		   alert("Vitals Details Insert successfully");
		        		   //show_msg({'msg':' Vitals Details Insert successfully ','status':'1'});
		        		   location.reload();
		        		   window.location.href = "preOpdWaitingList"
		        	   }
		        		
		        	  else
		        	  {
		        		  alert(msg.status)
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
		
		
		
</script>  

<script>


/* $(window)
		.bind(
				"load",
				function() {
					function getdata(){
						alert("localStorage.datas: "+ localStorage.datas);
						document.getElementById('service').innerHTML.value =localStorage.datas;
					}
				}); */
	'""'
				var $j= jQuery.noConflict();
				$j(document).ready(function(){
					alert("localStorage.visitId: "+ localStorage.visitId);
					document.getElementById('visitId').value =localStorage.visitId;
					document.getElementById('patiendID').value =localStorage.patientId;
					document.getElementById('service').value =localStorage.serviceNo;
					document.getElementById('patient_name').value =localStorage.patientName;
					document.getElementById('relation').value =localStorage.relation;
					document.getElementById('gender').value =localStorage.gender;
					document.getElementById('age').value =localStorage.age;
					//document.getElementById('service').value =localStorage.datas;
				});
</script>

<!-- <script type="text/javascript">
   $(document).ready(function () {
       $('#height').keyup(function () { alert('test'); });
   });
</script> -->



<script type="text/javascript">

 $(document).ready(function () {
		 $('#height').change(
		function() {
			var pathname = window.location.pathname;
			var accessGroup = pathname.split("/")[1];

			var url = window.location.protocol + "//"
					+ window.location.host + "/" + accessGroup
					+ "/v0.1/opd/getIdealWeight";
    
			var dataJSON={
					 
					  'height':$('#height').val(),
		      		  'age':$('#age').val(),
					
					
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
					alert("value is "+data.data[0].idealWeight);
					$('#ideal_weight').val(data.data[0].idealWeight);
	               
					
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
		 });
		});
		
		
</script>

<script type="text/javascript">

 $(document).ready(function () {
		 $('#weight').change(
		function() {
			
			 var a=document.getElementById("ideal_weight").value;
		     var b=document.getElementById("weight").value;
			
			if(a>b)
			{
				var sub=a-b;
				var cal=sub*100/a
				var n = cal.toFixed(2);
				$('#variant_in_weight').val("+"+n);
				
			}
			else
				{
				var eadd=b-a;
				var cal1=eadd*100/b
				var n1 = cal1.toFixed(2);
				$('#variant_in_weight').val("-"+n1);
				}
			
			
	});
 });

 </script>
</div>
<!-- </div> -->

<div id="edited"></div>
<label>&nbsp;</label>
<div id="statusMessage"></div>
</div>
</div>





