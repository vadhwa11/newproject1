
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="..//view/leftMenu.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Pending Discharge List</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="..//view/commonJavaScript.jsp"%>

</head>
<script type="text/javascript" language="javascript">
<%	String hospitalId = "1";
%>

var nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
			getPendingDischargeList('ALL');
			
		});

 function getPendingDischargeList(MODE) { 	
 	//alert("inside getReferredList");
 	var cmdId=0; 	

 	 if(MODE == 'ALL')
 		{
 			var data = {"PN":nPageNo};
 			
 		}
 	else
 		{
 			var data = {"PN":nPageNo};
 		} 
 	 
 	 var patient_name = $j('patient_name').val();
 	 var service_no = $j('service_no').val();
 	 //var mobile_no = $j('mobile_no').val();
 	 
 	if (patient_name === undefined) {
 		patient_name = "";
 	}
	if (service_no === undefined) {
		service_no = "";
 	}
	
 	var data = {"hospital_id": <%= hospitalId %>, "service_no":service_no,"patient_name":patient_name,"pageNo":nPageNo}; 
 		
 	var bClickable = true;
 	var url = "getDischargePendingList";
 	GetJsonData('tblListofObesity',data,url,bClickable);
 }
 
 function searchPendingDischargeList()
 {
 	//var nPageNo=1; 
 	$j('#tblListofObesity').empty();
 	var url = "getPendingDischargeList";
 	var service_no = $j('#service_no').val();
 	var patient_name = $j('#patient_name').val();
 	if((service_no == undefined || service_no == '') && (patient_name == undefined || patient_name == '')){
 		alert("Atleast one of the search option must be entered");
 	}
 	var data = {"hospital_id": <%= hospitalId %>, "service_no":service_no,"patient_name":patient_name,"pageNo":1}; 
 	var bClickable = true;
 	
 	GetJsonData('tblListofObesity',data,url,bClickable);
 	ResetForm();
 } 
 
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count;
 	
 	var pageSize = 5;	
 	
    var dischargePendingList = jsonData.dischargePendingList;
 	var html = "";	 	
  	var pageSize = 5;
 	for(i=0;i<dischargePendingList.length;i++)
 		{	 		
 				
 			html = html+'<tr id="'+dischargePendingList[i].id+'">';			
 			html = html +"<td style='width: 150px;'>"+dischargePendingList[i].service_no+"</td>";
 			html = html +"<td style='width: 150px;'>"+dischargePendingList[i].patient_name+"</td>";
 			html = html +"<td style='width: 100px;'>"+dischargePendingList[i].age+"</td>";
 			html = html +"<td style='width: 100px;'>"+dischargePendingList[i].gender+"</td>";
 			html = html +"<td style='width: 100px;'>"+dischargePendingList[i].rank+"</td>";
 			html = html +"<td style='width: 100px;'>"+dischargePendingList[i].date_of_admission+"</td>";
 			html = html +"<td style='width: 100px;'>"+dischargePendingList[i].ward+"</td>";
 			html = html +"<td style='width: 100px;'>"+dischargePendingList[i].disposal+"</td>";
 			
 			html = html+"</tr>";
 			
 		}
 	
 	if(dischargePendingList.length == 0)
 		{
 			htmlTable = htmlTable+"<tr ><td colspan='6'><h6>No Record Found !!</h6></td></tr>";
 		} 			
 	
 	$j("#tblListofObesity").html(html); 	
 	
 }
 
 function executeClickEvent(Id)
 {
	window.location = "admissionAndDischarge?id="+Id+"";	
	 
 }

 function ResetForm()
 {
 	$j('#service_no').val('');
 	$j('#patient_name').val('');
 }
 
 function showResultPage(pageNo) 	
 {
 	
 	nPageNo = pageNo;	
 	getPendingDischargeList('FILTER');
 	
 }
</script>
<body>


	<div class="content-page">
		<!-- Start content -->
		<div class="">
			<div class="container-fluid">
				<div class="internal_Htext">Pending for Discharge</div>

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">

								<form>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">Service No.</label>
												<div class="col-sm-7">
													<input class="form-control  form-control-sm"
														id="service_no"> </input>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-5 col-form-label">Patient Name</label>
												<div class="col-sm-7">
													<input class="form-control form-control-sm" type="text"
														placeholder="" id="patient_name">
												</div>
											</div>
										</div>

										<div class="col-md-1"></div>


										<div class="col-md-1">
											<div class="form-group row">
												<div class="col-sm-12">
													<button type="button" class="btn btn-primary"
														onclick="searchPendingDischargeList()">Search</button>
												</div>
											</div>
										</div>
									</div>
									<div class="form-row">
										<div style="float: left">

											<table class="tblSearchActions" cellspacing="0"
												cellpadding="0" border="0">
												<tr>
													<td class="SearchStatus" style="font-size: 15px;"
														align="left">Search Results</td>
													<td>
														<!-- <div id=resultnavigation></div> -->
													</td>
												</tr>
											</table>
										</div>
										<div style="float: right">
											<div id="resultnavigation"></div>
										</div>
										<table class="table table-bordered ">
											<thead bgcolor="00FFFF">
												<tr>
													<th id="th1">Service No.</th>
													<th id="th2">Patient Name</th>
													<th id="th3">Age</th>
													<th id="th4">Gender</th>
													<th id="th5">Rank</th>
													<th id="th6">Date of Admission</th>
													<th id="th7">Ward</th>
													<th id="th7">Disposal</th>
												</tr>
											</thead>
											<tbody id="tblListofObesity">
											</tbody>
										</table>
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