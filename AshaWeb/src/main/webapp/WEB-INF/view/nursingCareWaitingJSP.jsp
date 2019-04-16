<%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="static com.asha.icgweb.utils.RequestConstants.*"%>
<%@ page import="com.asha.icgweb.utils.HMSUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="../resources/js/jquery.js"></script>
<script type="text/javascript" src="../../resources/js/jquery.min.js"></script>
<script type="text/javascript" src="../../resources/js/jquery.common.js"></script>
<script type="text/javascript" src="../../resources/js/common.js"></script>
<script type="text/javascript" src="../../resources/js/ajax.js"></script>
<script type="text/javascript" src="../../resources/js/pops_global.js"></script>
<script type="text/javascript" src="../../resources/js/pops_menu.js"></script>
<script type="text/javascript" language="javascript"
	src="../resources/js/controls.js?n=1"></script>
<script type="text/javascript" src="../resources/js/hms.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../../resources/assets/css/bootstrap.min.css"	rel="stylesheet" />
<link href="../../resources/assets/css/animate.min.css" rel="stylesheet" />
<link	href="../../resources/assets/css/paper-dashboard.css"	rel="stylesheet" />
<link	href="../../resources/assets/css/demo.css"	rel="stylesheet" />
<link	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
<link href="../../resources/assets/css/themify-icons.css" rel="stylesheet">
<title>Referred Patient</title>
</head>
<script type="text/javascript" language="javascript">
<%	String hospitalId = "1";
%>

var nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
			getNursingWaitingList('ALL');
			
		});

 function getNursingWaitingList(MODE) { 	
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

 	var data = {"hospital_id": <%= hospitalId %>, "from_date":from_date,"to_date":to_date}; 
 		
 	var bClickable = true;
 	var url = "nursingCareWaitingList";
 	GetJsonData('tblListofObesity',data,url,bClickable);
 }
 
 function searchReferredWaitingList()
 {
 	$j('#tblListofObesity').empty();
 	var from_date = $j('#from_date').val();
 	var to_date = $j('#to_date').val();
 	if((from_date == undefined || from_date == '') && (to_date == undefined || to_date == '')){
 		alert("Atleast one of the search option must be entered");
 	}
 	var data = {"hospital_id": <%= hospitalId %>, "from_date":from_date,"to_date":to_date}; 
 	var bClickable = true;
 	var url = "nursingCareWaitingList";
 	GetJsonData('tblListofObesity',data,url,bClickable);
 	ResetForm();
 } 
 
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count;
 	
 	var pageSize = 5;
 	var dataList = jsonData.nursingCareWaitingList;
 	for(i=0;i<dataList.length;i++)
 		{	 		
 				
 			htmlTable = htmlTable+"<tr id='"+dataList[i].id+"' >";			
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].token_no+"</td>";
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].opd_date+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].patient_name+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].age+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].gender+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].doctor_name+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].status+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].priority+"</td>";
 			
 			htmlTable = htmlTable+"</tr>";
 			
 		}
 	if(dataList.length == 0)
 		{
 		htmlTable = htmlTable+"<tr ><td colspan='6'><h6>No Record Found</h6></td></tr>";
 		   //alert('No Record Found');
 		}			
 	
 	//alert("tblListOfCommand ::" +htmlTable)
 	$j("#tblListofObesity").html(htmlTable);	
 	
 	
 }
 
 
 function executeClickEvent(Id)
 {
	//alert(Id);
	window.location="nursingCareDetail?header_id="+Id+"";
	 
 }
 function ResetForm()
 {
 $j('#service_no').val('');
 $j('#mobile_no').val('');
 $j('#Patient_name').val('');
 }
</script>
<body>
	<div class="container">

		<form>
			<div class="form-row">
				<div class="form-group col-md-8">
					<h3>
						<label for="inputEmail3" class="col-md-10 col-form-label">Nursing Care Waiting List</label>
					</h3>
				</div>
			</div>
			<div class="form-row">
				<table class="tblSearchActions" cellspacing="0" cellpadding="0"
					border="0">
					<tr>
						<td><label for="Service No." class="col-sm-4 col-form-label">From Date</label>
							<div class="col-sm-8">
								<input type="date" class="border" id="from_date">
							</div></td>
							
						<td><label for="Service No." class="col-sm-4 col-form-label">To Date</label>
							<div class="col-sm-8">
								<input type="date" class="border" id="to_date">
							</div></td>
						
						<td>
							<button type="button" class="btn btn-success"
								onclick="searchReferredWaitingList()">Search</button>
						</td>
					</tr>
				</table>
			</div>
			<br>
			<br>
			<div class="form-row">
				<div class="tablediv">
				<table class="table table-bordered ">
					<thead bgcolor="00FFFF">
						<tr>
							<th id="th2">Token No.</th>
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
		</div>
		</form>
		</div>
</body>
</html>