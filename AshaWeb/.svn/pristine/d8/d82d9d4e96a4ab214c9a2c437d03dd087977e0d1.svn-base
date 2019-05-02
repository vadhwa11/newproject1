 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="..//view/leftMenu.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Patient Summary</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="..//view/commonJavaScript.jsp"%> 
</head>
<script type="text/javascript" language="javascript">
<%	

String hospitalId = "1";

if (session.getAttribute("hospital_id") !=null)
{
	hospitalId = session.getAttribute("hospital_id")+"";
}
%>
var $j = jQuery.noConflict();
var rowcount=0;
$j(document).ready(function()
		{
				var data = ${data};

			
		});
</script>
<body>
<div class="content-page">
		<!-- Start content -->
		<div class="">
			<div class="container-fluid">

				<div class="internal_Htext"> Referral Patient Detail</div>
				<!-- end row -->

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">


								<form>
								 <div class="row">                                       
                                     <h4  class="service_htext"> Patient Details</h4>
									<div class="row"> 
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">Service No.</label>
												<div class="col-sm-7">
													<input class="form-control  form-control-sm"  id="service_no" readonly>
													</input>
												</div>
											</div>
										</div> 
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">Patient Name</label>
												<div class="col-sm-7">
													<input class="form-control form-control-sm" type="text"
														placeholder=""  id="patient_name" readonly>
												</div>
											</div>
										</div> 
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">Age</label>
												<div class="col-sm-7">
													<input class="form-control form-control-sm" type="text"
														placeholder=""   id="age" readonly>
												</div>
											</div>
										</div> 
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">Gender</label>
												<div class="col-sm-7">
													<input class="form-control  form-control-sm"  id="gender" readonly>	
													</input>
												</div>
											</div>
										</div> 										
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">Rank </label>
												<div class="col-sm-7">
													<input class="form-control form-control-sm" type="text"
														placeholder="" id="rank" readonly>
												</div>
											</div>
										</div> 
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">Mobile No.</label>
												<div class="col-sm-7">
													<input class="form-control form-control-sm" type="text"
														placeholder=""   id="mobile_no" readonly>
												</div>
											</div>
										</div> 
										</div>
										</div>
										
										   <div style="float: left">

								</div>
								<h4  class="service_htext"> Referral Details</h4>
								<div style="float: right">
									<div id="resultnavigation"></div>
								</div>
												<table class="table table-bordered" id="tableId">
													<thead>
														<tr>
															<th>Referral Date</th>
															<th>Hospital Name</th>
															<th>Speciality</th>
															<th>Diagnosis</th>
															<th>Mark as Notifiable Desease</th>
															<th>Instruction</th>
															<th>Mark MB</th>
															<th>Mark as Admitted</th>
															<th>Close</th>
															<th>Final Note</th>
														</tr>
													</thead>
												</table>	
												<!-- <table class="table table-bordered table-responsive" id="tableId"> </table>  -->
										 
									    <div class="form-group row"> 
										    <div class="col-md-10"> 
											</div>   
											<div class="col-md-1"> 
														<button type="button" class="btn btn-primary" onclick="saveReferralDetail()"> Submit</button>  
											</div> 
											<div class="col-md-1"> 
														<button type="button" class="btn btn-primary" onclick="backToReferralWaitingList()"> Back</button> 
											</div>  
										</div>		
										
										<div class="row">
										<input type="hidden" class="form-control" id="header_id" value="">
											<input type="hidden" class="form-control" id="patient_id" value="">
											
											<input type="hidden" id="header_id">
										</div>	
									 
								</form> 
							</div>

						</div>
					</div>
				</div>
				
			</div>

		</div>

</body>
</html>