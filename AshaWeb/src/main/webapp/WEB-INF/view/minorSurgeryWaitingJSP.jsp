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

<title>Minor Surgery Waiting List</title>
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
			getMinorSurgeryWaitingList('ALL');
			
		});

 function getMinorSurgeryWaitingList(MODE) {
	
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
 	var url = "minorSurgeryWaitingList";
 	GetJsonData('tblListofObesity',data,url,bClickable);
 }
 
 function searchWaitingList()
 {
 	$j('#tblListofObesity').empty();
 	var from_date = $j('#from_date').val();
 	var to_date = $j('#to_date').val();
 	var serviceNo=$j('#srvNo').val();
 	 var flag = true;
 	if((from_date == undefined || from_date == '') && (to_date == undefined || to_date == '') && (serviceNo == undefined || serviceNo == '') ){
 		
 		alert("At least one of the search option must be entered") ;
 		flag=false;
 		return flag;
 		
 	}
 	var data = {"hospital_id": <%= hospitalId %>, "from_date":from_date,"to_date":to_date,"pageNo":1,"serviceNo":serviceNo}; 
 	var bClickable = true;
 	var url = "minorSurgeryWaitingList";
 	GetJsonData('tblListofObesity',data,url,bClickable);
 	ResetForm();
 } 
 
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count;
 	
 	var pageSize = 5;
 	var dataList = jsonData.minorSurgeryWaitingList;
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
 			htmlTable = htmlTable+"<tr id='"+dataList[i].id+"' >";			
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].serviceNo+"</td>";
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
 	$j("#tblListofObesity").html(htmlTable);	
 	
 	
 }
 
 
 function executeClickEvent(Id)
 {
	//alert(Id);
	window.location="minorSurgeryDetail?header_id="+Id+"";
	 
 }
 function ResetForm()
 {
 $j('#service_no').val('');
 $j('#mobile_no').val('');
 $j('#Patient_name').val('');
 }
 
 function showResultPage(pageNo)
 { 	
 	
 	nPageNo = pageNo;	
 	getMinorSurgeryWaitingList('FILTER');
 	
 }
</script>
<body>
	<div class="content-page">
		<div class="">
			<div class="container-fluid">
				<div class="internal_Htext">Minor Surgery Waiting List</div>
				<!-- end row -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<form>
									<div class="row">
									 <div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">Service No</label>
												<div class="col-sm-8">
													<input type="text" class="border form-control"
														id="srvNo" placeholder="service no">
												</div>
											</div>
										</div> 
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-4 col-form-label">From Date</label>
												<div class="col-sm-8">
													<input type="date" class="border form-control"
														id="from_date">
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
										<div class="col-md-3">
											<div class="form-group row">
												<button type="button" class="btn btn-primary"
													onclick="searchWaitingList()">Search</button>
											</div>
										</div>
										<div class="col-md-3"></div>
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


										<table class="table table-hover table-bordered">
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