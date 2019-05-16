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
			getAdmissionDischargeList('ALL');
			
		});

 function getAdmissionDischargeList(MODE) { 	
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
 	 var mobile_no = $j('mobile_no').val();
 	 
 	if (patient_name === undefined) {
 		patient_name = "";
 	}
	if (service_no === undefined) {
		service_no = "";
 	}
	
 	var data = {"hospital_id": <%= hospitalId %>, "service_no":service_no,"patient_name":patient_name,"pageNo":1}; 
 		
 	var bClickable = true;
 	var url = "getAdmissionDischargeList";
 	GetJsonData('tblListofObesity',data,url,bClickable);
 }
 
 function searchAdmissionDischargeList()
 {
 	//var nPageNo=1; 
 	$j('#tblListofObesity').empty();
 	var url = "getAdmissionDischargeList";
 	var service_no = $j('#service_no').val();
 	var patient_name = $j('#Patient_name').val();
 	//var mobile_no = $j('mobile_no').val();
 	if((service_no == undefined || service_no == '') && (patient_name == undefined || patient_name == '')){
 		alert("Atleast one of the search option must be entered");
 	}
 	var data = {"hospital_id": <%= hospitalId %>, "service_no":service_no,"patient_name":patient_name,"pageNo":1}; 
 	var bClickable = true;
 	
 	GetJsonData('tblListofObesity',data,url,bClickable);
 	ResetForm();
 } 
 
 function rowClickable(data){
	 var data = 'discharge_table';
	  $j("#"+data+" tr[id!='0']").hover(
		      function () {										          
		          $j(this).css("background-color", "#EDF4DA");			          
		        	 
		        	  	$j(this).css("cursor","pointer");
		        	  
		        }, 
		        function () {
		        
		          $j(this).css("background-color", "");
		        }
			)
			.click(
				function(){
					
							rowClick($j(this).attr("id"),data);						
				}
			);
 }
 
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count;
 	
 	var pageSize = 5;
 	var dataList = jsonData.admissionPendingList;
 	for(i=0;i<dataList.length;i++)
 		{	 		
 				
 			htmlTable = htmlTable+"<tr id='"+dataList[i].id+"' >";			
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].service_no+"</td>";
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].patient_name+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].age+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].gender+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].rank+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].mobile_no+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].hospital_name+"</td>";
 			
 			htmlTable = htmlTable+"</tr>";
 			
 		}
 	
  	if(dataList.length == 0)
 		{
 			htmlTable = htmlTable+"<tr ><td colspan='6'><h6>No Record Found !!</h6></td></tr>";
 		} 			
 	
 	$j("#tblListofObesity").html(htmlTable); 	
 	
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
 	
 	$j("#discharge_table").html(html); 	
 	rowClickable(jsonData.dischargePendingList);
 	
 }
 
 function executeClickEvent(Id)
 {
	window.location = "admissionAndDischarge?id="+Id+"";	
	 
 }
 
 function rowClick(Id){
	 window.location = "admissionAndDischarge?id="+Id+"";
 }
 function ResetForm()
 {
 	$j('#service_no').val('');
 	$j('#Patient_name').val('');
 }
 function newAdmission(){
	 window.location = "newAdmission";
 }
</script>
<body>
	<div class="container">

		<form>
			<div class="form-row">
				<div class="form-group col-md-8">
					<h3>
						<label for="inputEmail3" class="col-md-10 col-form-label">Pending Admission List</label>
					</h3>
				</div>
			</div>
			<div class="form-row">
				<table class="tblSearchActions" cellspacing="0" cellpadding="0"	border="0">
					<tr>
						<td class="SearchStatus" style="font-size: 13px;" align="left">Search Results</td>
						<td><label for="Service No." class="col-sm-4 col-form-label">Service No.</label>
							<div class="col-sm-8">
								<input type="text" class="border" id="service_no">
							</div></td>
						<td><label for="Patient Name" class="col-sm-4 col-form-label">Patient Name</label>
							<div class="col-sm-8">
								<input type="text" class="border" id="Patient_name"	style="margin-right: 10px">
							</div></td>
						<td>
							<button type="button" class="btn btn-success"
								onclick="searchAdmissionDischargeList()">Search</button>
						</td>
					</tr>
				</table>
			</div>
			<br>
			<br>
			<div class="form-row">
			<button type="button" class="btn btn-success"
						style="margin-top: 30px" onclick="newAdmission()">New Admission</button>
			</div><br><br>
			<div class="form-row">
				<div class="tablediv">
				<table class="table table-bordered ">
					<thead bgcolor="00FFFF">
						<tr>
							<th id="th1">Service No.</th>
							<th id="th2">Patient Name</th>
							<th id="th3">Age</th>
							<th id="th4">Gender</th>
							<th id="th5">Rank</th>
							<th id="th6">Mobile No.</th>
							<th id="th7">Hospital Name</th>
						</tr>
					</thead>
					<tbody id="tblListofObesity">

					</tbody>
				</table>
			</div>
		</div>
		<div class="form-row">
				<div class="tablediv">
				<table class="table table-bordered ">
					<thead bgcolor="00FFFF">
						<tr class="clickable-row">
							<th id="th1">Service No.</th>
							<th id="th2">Patient Name</th>
							<th id="th3">Age</th>
							<th id="th4">Gender</th>
							<th id="th5">Rank</th>
							<th id="th6">Date of Admission</th>
							<th id="th7">Ward</th>
							<th id="th8">Disposal</th>
						</tr>
					</thead>
					<tbody id="discharge_table">

					</tbody>
				</table>
			</div>
		</div>
		</form>
		</div>
</body>
</html>