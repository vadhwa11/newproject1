<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="..//view/leftMenu.jsp"%>
<%@include file="..//view/commonJavaScript.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="ISO-8859-1">
<title>OPD History</title>
<meta charset="utf-8">


</head>
<body>
	<div class="content-page">
		<!-- Start content -->
		<div class="">
			<div class="container-fluid">
				<div class="internal_Htext">OPD History</div>

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">

								<form name="frm">

									<div class="row">
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label"> From Date</label>
												<div class="col-sm-9">
													<input type="date" name="fromDate" id="fromDate">
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">To Date:</label>
												<div class="col-sm-6">
													<input type="date" name="toDate" id="toDate">
												</div>
											</div>
										</div>
										<div class="col-md-1">
											<button class="btn btn-primary" id="searhAppointment"
												type="button" onclick="searchAppointment()">Search</button>

										</div>


										<div class="col-md-3"></div>

									</div>

								 <input type="hidden" id="serviceNo" name="serviceNo"
										value="${serviceNo}">
						
								   <div style="float:left">					           
		                                    <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >	
		                                    
		                             		<tr>
												<td class="SearchStatus" style="font-size: 15px;" align="left">Search Results</td>
												<td>
												 <!-- <div id=resultnavigation></div> -->
												
												</td>
												</tr>
										</table>
		                                 </div>     
		                                    <div style="float:right">
				                                    <div id="resultnavigation">
				                                    </div> 
		                                    </div> 
                                    
					
		

                                    <table class="table table-hover table-bordered">
                                        <thead class="bg-success" style="color:#fff;">

											<tr>
													<th id="th1">Patient Name</th>
													<th id="th2">Relation</th>
													<th id="th3">Age</th>
													<th id="th4">Gender</th>
													<th id="th5">Department Name</th>

													<th id="th6">Doctor Name</th>
													<th id="th8">Visit Date</th>
													<th id="th9">Status</th>
													<th style="width: 21%;" id="th10">Action</th>
												</tr>
											</thead>
											<tbody id="tblListOfAppointments">
											</tbody>
										</table>
											</form>
									</div>


                    </div>
                 
						</div>
						
					</div>
				
				</div>
	</div>
	</div>
<script type="text/javascript" language="javascript">
	var nPageNo=1;
	var Status;
	var $j = jQuery.noConflict();

 $j(document).ready(function()
		{	
	
	 GetCommandList('ALL');
			
			
		});
		
function GetCommandList(MODE)
{
	var serviceNo=$j('#serviceNo').val();
	var cmdId=0;
	 if(MODE == 'ALL'){
		 var data = {"PN":nPageNo,"serviceNo":serviceNo,"flag":"c"};		
		}
	else
		{
		var data = {"PN":nPageNo,"serviceNo":serviceNo,"flag":"c"};
		} 
	var url = "getAllOpdList";
		
	var bClickable = true;
	GetJsonData('tblListOfAppointments',data,url,bClickable);
} 

function makeTable(jsonData)
{	
	var htmlTable = "";	
	var data = jsonData.count;
	
	
	var pageSize = 5;

	
	var dataList = jsonData.data;
	var dept;
	 for(item in dataList){
			
		 if(dataList[item].visitStatus.toUpperCase()==="c".toUpperCase()){
			 if(dataList[item].departmentName==null)
					dept="";
				else
					dept= dataList[item].departmentName;
				 var date= new Date(dataList[item].visitDate ).toLocaleDateString("en-US");
					
					 var datearray = date.split("/");

					 visitDate = datearray[1] + '/' + datearray[0] + '/' + datearray[2];
					  // htmlTable += '<tr id="'+dataList[item].Id+'" onclick="hello('+dataList+')">';
					  htmlTable = htmlTable + "<tr id='" + dataList[item].Id + "'>";
					  htmlTable = htmlTable + "<td >" + dataList[item].patientName + "</td>";
			    	  htmlTable = htmlTable + "<td >" + dataList[item].relation + "</td>";
			    	  htmlTable = htmlTable + "<td >" + dataList[item].age + "</td>";
			    	  htmlTable = htmlTable + "<td >" + dataList[item].gender + "</td>";
			    	  htmlTable = htmlTable + "<td >" + dept + "</td>";
			    	  htmlTable = htmlTable + "<td ></td>";
			    	  htmlTable = htmlTable + "<td >" + visitDate + "</td>";
			    	 htmlTable = htmlTable + "<td > Completed</td>";
			    	 htmlTable = htmlTable + "<td >"
			    	  htmlTable=  htmlTable + "&nbsp<button id ='printBtn' type='button'  class='btn btn-primary' onclick='opdCasesheet("+dataList[item].visitId+");'>OPD Casesheet</button>&nbsp</td>";
			    	 
			    	htmlTable = htmlTable + "</tr>";
			      }
   	htmlTable = htmlTable + "</tr>";
   	 
	    	
	      }
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   //alert('No Record Found');
		}			
	
	$j("#tblListOfAppointments").html(htmlTable);	
	
	
}
var comboArray = [];
var commandId;
var cmdCode;
var cmdName;
var cmdStatus;
var cmdType;
var cmdtypeId;
function executeClickEvent(cmdId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(cmdId == data.data[j].cmdId){
			commandId = data.data[j].cmdId;
			cmdCode = data.data[j].cmdCode;
			cmdName = data.data[j].cmdName;
			cmdStatus = data.data[j].status;
			cmdType = data.data[j].cmdType;
			cmdtypeId = data.data[j].cmdTypeId;
			
		}
	}
	rowClick(commandId,cmdCode,cmdName,cmdStatus,cmdType,cmdtypeId);
}


function showResultPage(pageNo)
{
	
	
	nPageNo = pageNo;	
	GetCommandList('FILTER');
	
}


function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetCommandList('ALL');
	
}


function searchAppointment() {
	if(document.getElementById('fromDate').value == "" || document.getElementById('fromDate') == null){
		 alert("Please enter fromDate.");
		 return false;
	 }
	if(document.getElementById('toDate').value == "" || document.getElementById('toDate') == null){
		 alert("Please enter toDate.");
		 return false;
	 }
	var serviceNo=$j('#serviceNo').val();
	var visitDate;
	 var fromDate = $j('#fromDate').val();
	  var toDate = $j('#toDate').val();
     var date = new Date(fromDate);
     
	var varDate = new Date(toDate); //dd-mm-YYYY
	var today = new Date();

	 if(toDate < fromDate) {
	alert("toDate date can not smaller than fromDate!");
	return false;
	} 
	
	
	var nPageNo=1;
	var url = "searchAppointment";
	var data =   {
			   "PN":nPageNo,
			 'fromDate':jQuery('#fromDate').val(),
			 'toDate':jQuery('#toDate').val(),
			 "flag":"c",
			 'serviceNo':serviceNo
	 } 
	var bClickable = true;
	GetJsonData('tblListOfAppointments',data,url,bClickable);
	ResetForm();
}

function opdCasesheet(visitId) {
	//window.location.href="PrintToken?visitId="+visitId;
	document.frm.action=url+"/report/printCaseSheet?visit_id="+visitId;	                  
	document.frm.method="POST";
	document.frm.submit(); 

}

</script>
</body>
</html>