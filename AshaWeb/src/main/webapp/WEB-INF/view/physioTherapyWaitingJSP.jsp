<%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
     <%@include file="..//view/leftMenu.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Physiotherapy Waiting List</title>
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

var nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
			getPhisioTherapyWaitingList('ALL');
			
		});

 function getPhisioTherapyWaitingList(MODE) { 	
 	var cmdId=0; 	
 	 if(MODE == 'ALL')
 		{
 			var data = {"PN":nPageNo}; 			
 		}
 	else
 		{
 			var data = {"PN":nPageNo};
 		} 
 	 
 	 var from_date = $j('from_date').val();
 	 var to_date = $j('to_date').val();
 	 
 	if (from_date === undefined) {
 		from_date = "";
 	}
	if (to_date === undefined) {
		to_date = "";
 	}

 	var data = {"hospital_id": <%= hospitalId %>, "from_date":from_date,"to_date":to_date,"pageNo":nPageNo}; 
 		
 	var bClickable = true;
 	var url = "physioTherapyWaitingList";
 	GetJsonData('tblListofPhisio',data,url,bClickable);
 }
 
 function searchReferredWaitingList()
 {
 	$j('#tblListofPhisio').empty();
 	var from_date = $j('#from_date').val();
 	var to_date = $j('#to_date').val();
 	if((from_date == undefined || from_date == '') && (to_date == undefined || to_date == '')){
 		alert("Atleast one of the search option must be entered");
 	}
 	var data = {"hospital_id": <%= hospitalId %>, "from_date":from_date,"to_date":to_date,"pageNo":1}; 
 	var bClickable = true;
 	var url = "physioTherapyWaitingList";
 	GetJsonData('tblListofPhisio',data,url,bClickable);
 	ResetForm();
 } 
 
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count;
 	
 	var pageSize = 5;
 	var dataList = jsonData.physioTherapyWaitingList;
 	for(i=0;i<dataList.length;i++)
 		{	 		
 			var status = '';
 			if(dataList[i].status == 'Y'){
 				status = 'Completed';
 			}else if(dataList[i].status == 'N'){
 				status = 'Pending';
 			}
 			var priority = '';
 			if(dataList[i].priority == 1){
 				priority = 'High';
 			}else if(dataList[i].priority == 2){
 				priority = 'Medium';
 			}else if(dataList[i].priority == 3){
 				priority = 'Low';
 			}
 			htmlTable = htmlTable +"<tr id='"+dataList[i].id+"' >";			
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].service_no+"</td>";
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].opd_date+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].patient_name+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].age+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].gender+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].doctor_name+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+status+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+priority+"</td>";
 			
 			htmlTable = htmlTable+"</tr>";
 			
 		}
 	if(dataList.length == 0)
 		{
 		htmlTable = htmlTable+"<tr ><td colspan='8'><h6>No Record Found</h6></td></tr>";
 		   //alert('No Record Found');
 		}			
 	
 	//alert("tblListOfCommand ::" +htmlTable)
 	$j("#tblListofPhisio").html(htmlTable);	
 	
 	
 }
 
 
 function executeClickEvent(Id)
 {
	//alert(Id);
	window.location="getphysioTherapyDetail?header_id="+Id+"";
	 
 }
 function showResultPage(pageNo)
 { 	 	
 	nPageNo = pageNo;	
 	getPhisioTherapyWaitingList('FILTER');
 	
 }
/*  function ResetForm()
 {
 	$j('#service_no').val('');
 	$j('#mobile_no').val('');
 	$j('#Patient_name').val('');
 } */
</script>
<body>
	<div class="content-page">
		<div class="">
			<div class="container-fluid">
				<div class="internal_Htext">Physiotherapy Waiting List</div>
				<!-- end row -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<form>
									<div class="row">
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">Service No.</label>
												<div class="col-sm-8">
													<input class="form-control form-control-sm" type="text"
														placeholder="" id="service_no">
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">From Date</label>
												<div class="col-sm-8">
													<input type="date" class="border form-control" id="from_date">
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">To Date</label>
												<div class="col-sm-8">
													<input type="date" class="border form-control" id="to_date">
												</div>
											</div>
										</div>
										<div class="col-md-1">
											<div class="form-group row">
												<button type="button" class="btn btn-primary"
												onclick="searchReferredWaitingList()">Search</button>			
											</div>
										</div>										
										
										<div class="col-md-2">											 
										</div>												
										
									</div>									
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
									 
										<div class="tablediv">
											<table class="table table-bordered ">
												<thead bgcolor="00FFFF">
													<tr>
														<th id="th2">Service No.</th>
														<th id="th3">OPD Date</th>
														<th id="th4">Patient Name</th>
														<th id="th5">Age</th>
														<th id="th5">Gender</th>
														<th id="th6">Department/Doctor</th>
														<th id="th7">Status</th>
														<th id="th7">Priority</th>
													</tr>
												</thead>
												<tbody id="tblListofPhisio">

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