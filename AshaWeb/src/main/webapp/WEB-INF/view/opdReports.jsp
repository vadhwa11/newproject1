<%@page import="java.util.HashMap"%>
    <%@page import="java.util.Map"%>
    <%@ page import="com.asha.icgweb.utils.ProjectUtils"%>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

          
              <%@include file="..//view/leftMenu.jsp" %>

                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                    <html>

                    <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                        <%@include file="..//view/commonJavaScript.jsp" %>
                            <title>OPD Reports</title>
                      <!--   <link href="/AshaWeb/resources/css/stylecommon.css" rel="stylesheet" type="text/css" /> -->
                    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/newOpd.js"></script>
                   <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
                    
                   
                    </head>
                    <body>
                    
                    
   <%
	Map<String, Object> map = new HashMap<String, Object>();
	if (request.getAttribute("map") != null) {
		map = (Map) request.getAttribute("map");
	}
	Map<String, Object> utilMap = new HashMap<String, Object>();
	utilMap = (Map) ProjectUtils.getCurrentDateAndTime();
	String date = (String) utilMap.get("currentDate");
	String time = (String) utilMap.get("currentTime");

	String userName = "";
	if (session.getAttribute("userName") != null) {
		userName = (String) session.getAttribute("userName");
	}
	String message = "";
	if (map.get("message") != null) {
		message = (String) map.get("message");
		//out.println(message);
	}
	System.out.println("message==in jsp ===" + message);

	
%>



	
	<script type="text/javascript" language="javascript">
	
	function getPatientId() {
      
		var pathname = window.location.pathname;
		var accessGroup = pathname.split("/")[1];
		/* var url = window.location.protocol + "//"
		+ window.location.host + "/" + accessGroup
		+ "/v0.1/opd/getTemplateName"; */
		
		var empNo=document.getElementById('employeeNo').value;
		var url=window.location.protocol + "//"
		+ window.location.host + "/" + accessGroup
		+ "/v0.1/opd/getOpdReportsDetailsbyServiceNo";
		
		$
				.ajax({
					url : url,
					dataType : "json",
					data : JSON.stringify({
						'serviceNo' : empNo
					}),
					contentType : "application/json",
					type : "POST",
					success : function(response) {
						  if (response.status == 1) {
						var datas = response.data;
						var trHTML = '<option value=""><strong>Select...</strong></option>';
						 for (var i = 0; i < datas.length; i++) {
                             var patientId = datas[i].patientId;
                             var patientName = datas[i].patinetName;
                            
                             var a = patientName + "[" + patientId + "]"
                             trHTML += '<option value="' + patientId + '" >'
								+ a + '</option>';
                            
                         }
						
						$('#patientName').html(trHTML);
					}
				    else
					{
					  alert("No Record Found");		  
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
	
	$(document).delegate("#patientName","change",
			function() {

		var pathname = window.location.pathname;
		var accessGroup = pathname.split("/")[1];
		var url = window.location.protocol + "//"
		+ window.location.host + "/" + accessGroup
		+ "/v0.1/opd/getOpdReportsDetailsbyPatinetId";
		
		$
				.ajax({
					url : url,
					dataType : "json",
					data : JSON.stringify({
						'employeeId' : '1',
						'patientId':$('#patientName').val()
					}),
					contentType : "application/json",
					type : "POST",
					success : function(response) {
						console.log(response);
					   if (response.status == 1) {
						var datas = response.data;
						var trHTML = '<option value=""><strong>Select...</strong></option>';
						 for (var i = 0; i < datas.length; i++) {
                            var visitId = datas[i].visitNo;
                            var visitDate = datas[i].visitDate;
                            var deptName = datas[i].departmentNo;
                           
                            var a = visitId + "[" + visitDate + "]" + "[" + deptName + "]"
                            trHTML += '<option value="' + visitId + '" >'
								+ a + '</option>';
                           
                        }
						
						$('#visitDetails').html(trHTML);


					}
					   else
						{
						  alert("No Record Found")
						  
						}
					}
				   ,
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
	
	$(document).delegate("#visitDetails","change",
			function() {
        
		var visitIdVal=$('#visitDetails').val()
		
		document.getElementById('visit_id').value =visitIdVal; 
	

	});

	function printReport(flag) {
		 if(document.getElementById('visit_id').value == "")
		 {
		   alert("Please enter and select above details")
		   return false;
		  }
		//alert("flag : "+flag)
		if(flag=='C')
		document.frm.action="${pageContext.request.contextPath}/report/printCaseSheet";
		if(flag=='I')
		document.frm.action="${pageContext.request.contextPath}/report/printInvestigationSlip";
		if(flag=='P')
		document.frm.action="${pageContext.request.contextPath}/report/printPrescriptionSlip";
		document.frm.method="POST";
		document.frm.submit(); 
	}	

function validateData()
{
	 if(document.getElementById('visit_id').value == "")
	 {
	   if(document.getElementById('employeeNo').value == "")
	   {
	   alert("Please enter service no.")
	   return false;
	   }
	   if(document.getElementById('patientName').value == "")
		{
		   alert("Please select patient name")
    	   return false; 
		}
	   if(document.getElementById('visitDetails').value == "")
		   {
		   alert("Please select visit no.")
   	       return false; 
		   }
	   
	 }  	
}
	

function submitWindow()
{
var code=document.getElementById('code').value;
var flag =true;
if(validateMetaCharacters(code)){

}

}

function closeWindow()
{
  window.close();
}

	</script>
	
	
	<div class="content-page">
		<!-- Start content -->
		<div class="">
			<div class="container-fluid">

				<div class="internal_Htext"> OPD Reports</div>

			 
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body"> 
								<form name="frm" >
									<div class="row">

                                        <div class="col-md-4">
                                                        <div class="form-group row">
                                                            <label class="col-sm-5 col-form-label">Service No. </label>
                                                            <div class="col-sm-7">
                                                                <input name="employee_no" id="employeeNo" type="text" class="form-control border-input" placeholder="Service No." value="" onblur="getPatientId()" />
                                                            </div>
                                                        </div>
                                          </div>
										<div class="col-md-4">
											<div class="form-group row">
                                                 <label class="col-sm-5 col-form-label">Patient Name </label>
                                                 <div class="col-sm-7">
                                                     <select name="patient_name" id="patientName" type="text" class="form-control border-input" placeholder="Employee No." value=""></select>
                                                 </div>
                                             </div>
										</div> 
										
										
										
										<div class="col-md-4"> 
										 <div class="form-group row">
                                                 <label class="col-sm-5 col-form-label">Visit No. For Prescription</label>
                                                 <div class="col-sm-7">
                                                     <select name="visitDetails" id="visitDetails" type="text" class="form-control border-input" placeholder="Employee No." value=""></select>
                                                 </div>
                                             </div>
	                                            </div> 
										</div>
										
									
                    <div class="row">
                        <div class="col-12">
                         
                                
                               
                                     <div class="row">
										
												<!-- <button class=" btn btn-primary opd_submit_btn" onclick="printReport('C');">Case Sheet</button> 
												 <button class="btn btn-primary opd_submit_btn" onclick="printReport('I');">Investigation Slip</button> 
												 <button class="btn btn-primary opd_submit_btn" onclick="printReport('P');">Prescription Slip</button>  -->
									
										
										<!-- <div class="col-md-2"> 
												 <button class="btn btn-primary opd_submit_btn" onclick="printReport('R');">Referral Letter</button> 
										</div> -->
										
                                    </div>
                              
                            </div>
                            <!-- end card -->
                        </div>
                        <!-- end col -->
                    </div>
									
									
									<div class="row"> 
										 <div class="col-md-12">
												<div class="btn-left-all">
													</div>
															<div class="btn-right-all">
																
									                       
												<button class=" btn btn-primary opd_submit_btn" onclick="printReport('C');">Case Sheet</button> 
												 <button class="btn btn-primary opd_submit_btn" onclick="printReport('I');">Investigation Slip</button> 
												 <button class="btn btn-primary opd_submit_btn" onclick="printReport('P');">Prescription Slip</button>
												 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
										
															</div>
															
															
													   </div>
												  </div>
									
									
									
									    <div class="row">                         
                                                            
															  <div class="col-md-4">																  
															 </div> 
														 </div>
                                                           
									
											<div class="col-md-4">
											<div class="form-group">
												<input type="hidden" id="visit_id" name="visit_id"
													value=""/>
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
</html>
