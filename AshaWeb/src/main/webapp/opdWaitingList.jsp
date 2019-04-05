<%@page import="java.io.InputStream"%>
<%@page import="java.net.URL"%>

<%-- <%@page import="jkt.hms.masters.business.MasUnit"%> --%>
<%-- <%@page import="jkt.hms.masters.business.MasRelation"%>
<%@page import="jkt.hms.masters.business.MasRank"%> --%> <!-- commented unused code by Babita Dangwal on 21-09-2017 -->
  <!-- Bootstrap core CSS     -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></link>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/resources/js/gridForPatient.js?n=1"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pops_global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pops_menu.js"></script>
<!-- <META HTTP-EQUIV="REFRESH" CONTENT="20"> -->
<script type="text/javascript">
	<!--
	var SESSIONURL = "s=cccfbaab0a70ed43fad9de8e3733112d&";
	var IMGDIR_MISC = "images/misc";
	var vb_disable_ajax = parseInt("0", 10);
	// -->
	</script>



<div class="titleBg">
<h2>Capture OPD Pre-Consulation Assements Details</h2>
</div>
<div class="clear"></div>


<form name="opdPatientList" method="post" action="">
<div class="clear"></div>

<div class="Block">

<label> Department</label> <input type="text" name="serviceNo"
	id="employeeId" value="1" value="" MAXLENGTH="30" validate="Service No,metachar,no" />
<label> Employee No.</label> <input type="text" name="serviceNo"
	value="" MAXLENGTH="30" validate="Service No,metachar,no" />
	<label>Patient Name</label> <input type="text" name="<%=""%>"></input>
<input type="button" name="search" value="Search"
	onClick="submitForm('opdPatientList','opd?method=showWaitingPatientListJsp')"
	class="button" /><input type="button" name="reset" value="Reset"
	onClick="submitForm('opdPatientList','opd?method=showWaitingPatientListJsp')"
	class="button" />
	<div class="clear"></div>
<div class="clear"></div>
</div>

</form>
<div class="clear"></div>
<div class="floatRight">


<div class="clear"></div>
<div id="test">
<!-- <div class="cmntable"> -->
<div id="searchresults" tabindex="2">
<div id="searchtable" tabindex="2" class="noBg"></div>
 <div class="right_col" role="main">
          <div class="">
            
            <div class="clearfix"></div>

            
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                 
                  <div class="x_content">

                    <table class="table table-striped table-hover jambo_table">
                      <thead>
                        
                        <tr>
                          
                          <th>Token No.</th>
                          <th>Service No.</th>
                          <th>Patient Name</th>
                          <th>Employee Name</th>
                          <th>Gender</th>
                          <th>Age</th>
                          <th>Doctor Name</th>
                          <th>Department Name</th>
                          <th>Status</th>
                          <th>Priority</th>

                          
                          
                        </tr>
                      </thead>
                      <tbody class="table-hover" id="responseData">
                       
                    
                      </tbody>
                    </table>

                  </div>
                </div>
              </div>

              
            </div>
          </div>
        </div>
        
  <div class="content">
            <div class="container-fluid">
                <div class="row">
                    
                    <div class="col-lg-12 col-md-7">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Patients Details</h4>
                            </div>
                            <div class="content">
                                <form action="<%=request.getContextPath()%>/v0.1/dashboard/addEmp" method="post">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Service No.</label>
                                                <input name="service_no." id="serviceno" type="text" class="form-control border-input" placeholder="Service No." value="" required />
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Patients Name</label>
                                                <input name="patients_name" id="patient_name" type="tel" min="10"  maxlength="10" class="form-control border-input" placeholder="Patients Name" value="" required />
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Relation</label>
                                                <input name="relation" id="relation" type="email" class="form-control border-input" placeholder="Relation" required />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Gender</label>
                                                <input name="gender" id="gender" type="text" class="form-control border-input" placeholder="gender" value="" required />
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Age</label>
                                                <input name="age" id="age" type="text" class="form-control border-input" placeholder="Age" value="" required />
                                            </div>
                                        </div>
                                    </div>
                                    <h4 class="title">Vitals</h4>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Height</label>
                                                <input name="height" id="height" type="text" class="form-control border-input" placeholder="Height" value="" required />
                                            </div>
                                        </div>
                                         <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Ideal Weight</label>
                                                <input name="ideal_weight" id="ideal_weight" type="text" class="form-control border-input" placeholder="Ideal Weight"/>
                                            </div> 
                                        </div>
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Weight</label>
                                                <input name="Weight" type="text" class="form-control border-input" placeholder="Weight"/>
                                            </div> 
                                        </div>
                                    </div>

                                    
                              
                                       
                                    
							<div class="row">
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Variant In Weight</label>
                                                <input name="variant_in_weight" id="variant_in_weight" type="text" class="form-control border-input" placeholder="Variant In Weight" value="" required />
                                            </div> 
                                        </div>
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Tempature</label>
                                                <input name="tempature" id="tempature" type="text" class="form-control border-input" placeholder="Tempature" value="" required>
                                            </div> 
                                     
                                    </div>
                                    <div class="col-md-4">
                                             <div class="form-group">
                                                <label>BP</label>
                                                <input name="bp" id="bp" type="text" class="form-control border-input" placeholder="bp" value="" required>
                                            </div> 
                                     
                                    </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Pulse</label>
                                                <input name="pulse" id="pulse" type="text" class="form-control border-input" placeholder="Pulse" value="" required />
                                            </div> 
                                        </div>
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Spo2</label>
                                                <input name="spo2" id="spo2" type="text" class="form-control border-input" placeholder="spo2" value="" required>
                                            </div> 
                                        </div>
                                         <div class="col-md-4">
                                             <div class="form-group">
                                                <label>BMI</label>
                                                <input name="bmi" id="bmi" type="text" class="form-control border-input" placeholder="BMI" value="" required>
                                            </div> 
                                        </div>
                                        </div>
                                         <div class="row">
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>RR</label>
                                                <input name="rr" id="rr" type="text" class="form-control border-input" placeholder="RR" value="" required>
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
                                        <div class="col-md-12">
                                           
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <input type="submit" id="clicked" class="btn btn-info btn-fill btn-wd" value="Submit" >
                                         <input type="submit" id="reset" class="btn btn-info btn-fill btn-wd" value="Reset" >
                                      
                                    </div>
                                  
                                
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
        
<script type="text/javascript" language="javascript">
$(document).ready(
		function() {
			var pathname = window.location.pathname;
			var accessGroup = pathname.split("/")[1];

			var url = window.location.protocol + "//"
					+ window.location.host + "/" + accessGroup
					+ "/v0.1/opd/getPreConsPatientWatingWeb";

			var data = $('employeeId').val();

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url,
				data : JSON.stringify({
					'employeeId' : "1"
				}),
				dataType : 'json',
				timeout : 100000,
				success : function(res) {
					console.log("SUCCESS: ", res);
					// var obj = JSON.parse(res);
					var data = res.VisitList;
					var html = '';
					$.each(data, function(key, value) {

						html += '<tr onclick="rowClick('+value.serviceNo+',\''+value.patientName+'\',\''+value.gender+'\')"><td>'
								+ value.tokenNo + '</td><td>'
								+ value.serviceNo + '</td><td>'
								+ value.patientName + '</td><td>'
								+ value.employeeName+ '</td><td>'
								+ value.gender + '</td><td>'
								+ value.dateOfBirth + '</td><td>'
								+ value.doctorname + '</td><td>'
								+ value.departmentName + '</td><td>'
								+ value.status + '</td><td>'
								+ value.priority + '</td></tr>'
								
					});
					$('#responseData').append(html);

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
		
		function rowClick(serviceno, patientName){
			//alert("pateintname "+patientName);gender
			document.getElementById("serviceno").value = serviceno;
			document.getElementById("patient_name").value = patientName;
			document.getElementById("gender").value = gender;
		}
</script>
</div>
<!-- </div> -->

<div id="edited"></div>
<label>&nbsp;</label>
<div id="statusMessage"></div>
</div>
</div>

		



