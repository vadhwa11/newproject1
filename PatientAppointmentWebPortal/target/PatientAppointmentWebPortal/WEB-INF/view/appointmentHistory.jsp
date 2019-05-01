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
<title>Appointment History</title>
<meta charset="utf-8">

<meta charset="ISO-8859-1">
<title>Personal Information</title>
</head>
<body>
<form name="frm">
	<div class="content-page">
		<!-- Start content -->
		<div id="seccessmsg" style="color:green; align:center;">
										${message}
								</div>
		<div class="">
		
			<div class="container-fluid">
				<div class="internal_Htext">Appointment History</div>

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								

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
												<label class="col-sm-3 col-form-label">To Date</label>
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
													<th id="th7">Token Number</th>
													<th id="th8">Appointment Date</th>
													<th id="th9">Status</th>
													<th style="width: 21%;" id="th10">Action</th>
												</tr>
											</thead>
											<tbody id="tblListOfAppointments">
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


	<script>
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
		 var data = {"PN":nPageNo,"serviceNo":serviceNo,"flag":"w"};		
		}
	else
		{
		var data = {"PN":nPageNo,"serviceNo":serviceNo,"flag":"w"};
		} 
	var url = "getAllAppointmentList";
		
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
			if(dataList[item].departmentName==null)
				dept="";
			else
				dept= dataList[item].departmentName;
			 var date= new Date(dataList[item].visitDate ).toLocaleDateString("en-US");
			
			 var datearray = date.split("/");

			 visitDate = datearray[1] + '/' + datearray[0] + '/' + datearray[2];
			  // htmlTable += '<tr id="'+dataList[item].Id+'" onclick="hello('+dataList+')">';
			  htmlTable = htmlTable + "<tr id='name'>";
			  htmlTable = htmlTable + "<td >" + dataList[item].patientName + "</td>";
	    	  htmlTable = htmlTable + "<td >" + dataList[item].relation + "</td>";
	    	  htmlTable = htmlTable + "<td >" + dataList[item].age + "</td>";
	    	  htmlTable = htmlTable + "<td >" + dataList[item].gender + "</td>";
	    	  htmlTable = htmlTable + "<td >" + dept + "</td>";
	    	  htmlTable = htmlTable + "<td ></td>";
	    	  htmlTable = htmlTable + "<td >" + dataList[item].tokenNo + "</td>";
	    	
	    	  htmlTable = htmlTable + "<td >" + visitDate + "</td>";
	    	 if(dataList[item].visitStatus.toUpperCase()==="w".toUpperCase()){
		    	  htmlTable = htmlTable + "<td > Waiting </td>";
		    	  htmlTable = htmlTable + "<td >"
		    	  htmlTable= htmlTable + "&nbsp<button id ='printBtn' type='button'  class='btn btn-primary' onclick='printToken("+dataList[item].visitId+");'>Print</button>&nbsp";
		    	 htmlTable= htmlTable + "&nbsp<button id ='rescheduleBtn' type='button'  class='btn btn-primary' onclick='appointmentReschedule(\""+dataList[item].visitId+"\",\""+dataList[item].serviceNo+"\",\""+dataList[item].patientName+"\",\""+dataList[item].relation+"\",\""+dataList[item].dateOfBirth+"\",\""+dataList[item].gender+"\",\""+dataList[item].hospitalName+"\",\""+dataList[item].hospitalId+"\",\""+dataList[item].departmentName+"\",\""+dataList[item].departmentId+"\",\""+dataList[item].appointmentType+"\",\""+dataList[item].appointmentTypeId+"\");'>Reschedule</button>&nbsp";
		    	  htmlTable= htmlTable + "&nbsp<button id ='cancelBtn' type='button'  class='btn btn-primary' onclick='cancelAppointment("+dataList[item].visitId+");'>Cancel</button>&nbsp</td>";
		    	  }else if(dataList[item].visitStatus.toUpperCase()==="n".toUpperCase()){
			    	  htmlTable = htmlTable + "<td > Cancelled</td>";
			    	  htmlTable = htmlTable + "<td ></td>";
			    	 
		    	  }else if(dataList[item].visitStatus.toUpperCase()==="c".toUpperCase()){
 	   			    	  htmlTable = htmlTable + "<td > Completed</td>";
 	   			    	  htmlTable = htmlTable + "<td ></td>";
 	   			    	 
 			    	 
		    	  }else if(dataList[item].visitStatus.toUpperCase()==="z".toUpperCase()){
 	   			    	  htmlTable = htmlTable + "<td > Close</td>";
 	   			    	  htmlTable = htmlTable + "<td ></td>";
 	   			    	 
 			    	 
		    	  }else if(dataList[item].visitStatus.toUpperCase()==="r".toUpperCase()){
 	   			    	  htmlTable = htmlTable + "<td > Refer</td>";
 	   			    	  htmlTable = htmlTable + "<td ></td>";
 	   			    	 
 			    	 
		    	  }else if(dataList[item].visitStatus.toUpperCase()==="p".toUpperCase()){
 	   			    	  htmlTable = htmlTable + "<td >Pre Consultation</td>";
 	   			    	  htmlTable = htmlTable + "<td ></td>";
 	   			    	 
 			    	 
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

function cancelAppointment(visitId){
	  $j('#messageCancel').show();
	  $j('#visitIdValue').val(visitId);
}


function cancelAppointment1() {
	var visitId= $j('#visitIdValue').val();
	/* if(document.getElementById('fromDate').value == "" || document.getElementById('fromDate') == null){
		 alert("Please enter fromDate Date.");
		 return false;
	 }
	if(document.getElementById('toDate').value == "" || document.getElementById('toDate') == null){
		 alert("Please enter toDate Date.");
		 return false;
	 }  */
	var serviceNo=$j('#serviceNo').val();
	var visitDate;
	
	var fromDate=jQuery('#fromDate').val();
	var toDate=jQuery('#toDate').val();
	if((fromDate!="" && fromDate!=null) ||(toDate!="" && fromDate!=toDate)){
   var fromDate= new Date(fromDate);
   var fromDate= new Date(fromDate);
   var toDate= new Date(toDate);
   fromDate=(( fromDate.getDate() + '-' + (fromDate.getMonth()+1) )  + '-' +  fromDate.getFullYear());
   toDate=(( toDate.getDate() + '-' + (toDate.getMonth()+1) )  + '-' +  toDate.getFullYear());
}
		
	
		var nPageNo=1;
		var url = "searchAppointment";
		var data =   {
				     'PN':nPageNo,
				     'visitId':visitId,
					 'fromDate':fromDate,
					 'toDate':toDate,
					 'flag':'w',
					 'serviceNo':serviceNo
		 }
	
		var bClickable = true;
		GetJsonData('tblListOfAppointments',data,url,bClickable);
		closeButton();
		alert("Appointment has been Cancelled.");
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

function printToken(visitId) {
	//window.location.href="PrintToken?visitId="+visitId;
	document.frm.action=url+"/report/printVisitTokenReport?visit_id="+visitId;
	document.frm.method="POST";
	document.frm.submit(); 

}

function appointmentReschedule(visitId,serviceNo,patientname,relation,dateOfBirth,gender,hospitalName,hospitalId,departmentName,depatmentId,appointmentType,appointmentId) {

	var visitId=visitId;
	var serviceNo=serviceNo;
	var patientname=patientname;
	var dateOfBirth=dateOfBirth;
	var gender=gender;
	var hospitalName=hospitalName;
	var departmentName=departmentName;
	var hospitalId=hospitalId;
	var depatmentId=depatmentId;
	var appointmentType=appointmentType;
	var appointmentId=appointmentId;
	var relation=relation;
	var params = {
			
			 "visitId":visitId,
			 "serviceNo":serviceNo,
			 "patientname":patientname,
			 "dateOfBirth":dateOfBirth,
			 "gender":gender,
			 "hospitalName":hospitalName,
			 "departmentName":departmentName,
			 "hospitalId":hospitalId,
			 "depatmentId":depatmentId,
			 "appointmentType":appointmentType,
			 "appointmentId":appointmentId,
			 "relation":relation
	 } 
	
	jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "rescheduleAppointmentDataGet",
		    data: JSON.stringify(params),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(jsonData){
		    	 
		    	window.location.href="rescheduleAppointment?appointmentData="+visitId+","+serviceNo+","+patientname+","+dateOfBirth+","+gender+","+hospitalName+","+departmentName+","+hospitalId+","+depatmentId+","+appointmentType+","+appointmentId+","+relation+"";
		    },
	        error : function(jsonData) {
		        alert("An error has occurred while contacting the server");
	       }
		    });

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
	
	  var fromDate = $j('#fromDate').val();
	  var toDate = $j('#toDate').val();
      var date = new Date(fromDate);
      
 	var varDate = new Date(toDate); //dd-mm-YYYY
 	var today = new Date();

 	 if(toDate < fromDate) {
 	alert("toDate date can not smaller than fromDate!");
 	return false;
 	} 
	var serviceNo=$j('#serviceNo').val();
	var visitDate;
	var nPageNo=1;
	var url = "searchAppointment";
	var data =   {
			     'PN':nPageNo,
			     'fromDate':jQuery('#fromDate').val(),
				 'toDate':jQuery('#toDate').val(),
			     'flag':'w',
				 'serviceNo':serviceNo

	 } 
	var bClickable = true;
	GetJsonData('tblListOfAppointments',data,url,bClickable);
}
</script>
<script>
function closeButton(){
	$j('#messageCancel').hide();
}
</script>
	


	<div class="modal hide z-index5000" id="messageCancel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static">
		<input type="hidden" name="visitIdValue" id="visitIdValue"/>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<!-- <button type="button" class="close btnclose" data-dismiss="modal" aria-hidden="true"  id="btncross"></button> -->


					<span class="Message_htext">Indian coast Guard</span>

					<button type="button"  onClick="closeButton();" class="close"  data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>

				</div>
				<div class="modal-body">
					<div class="control-group">
						<div class="">
							 
							<p class="message_text">Are you sure want to cancel Appointment !</p>
						</div>
					</div>
				</div>
				<div class="modal-footer">
				<button class="btn btn-primary" data-dismiss="modal"
						onClick="cancelAppointment1();" aria-hidden="true">OK</button>
					<button class="btn btn-primary" data-dismiss="modal"
						onClick="closeButton();" aria-hidden="true">Close</button>
				</div>
			</div>
		</div>
	</div>




</body>
</html>