<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@include file="..//view/leftMenu.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>


<%
	String hospitalId = "1";
	if (session.getAttribute("hospital_id") != null) {
		hospitalId = session.getAttribute("hospital_id") + "";
	}
	String userId = "1";
	if (session.getAttribute("user_id") != null) {
		userId = session.getAttribute("user_id") + "";
	}
	
	String departmentId="5";
	
%>
<%@include file="..//view/commonJavaScript.jsp" %>
</head>
 
<script type="text/javascript" language="javascript">

var nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
			getOpdPreConsultationList('ALL');
			getDepartment();
			
		});
	
	
function searchOpdPreConsultationList()
{
		
	var nPageNo=1;	
	 var service_no = $j('#service_no').val();
	 	var patient_name = $j('#patient_name').val();
	if((service_no == undefined || service_no == '') && (patient_name == undefined || patient_name == '')){	
		alert("At least one option must be entered");
		return;
	}
	getOpdPreConsultationList('FILTER');
	ResetForm();
} 

function getOpdPreConsultationList(MODE) { 	
 	
	     var cmdId=0;
	     var service_no = $j('#service_no').val();
	 	var patient_name = $j('#patient_name').val();
    var cmdId=0;
	 if(MODE == 'ALL'){
		 var data = {"hospitalId": <%=hospitalId%>,"employeeId": <%=userId%>,"pageNo":nPageNo,"opdPre":"opdWait","serviceNo":"","patientName":""};				
		}
	else
		{
		var data = {"hospitalId": <%=hospitalId%>,"employeeId": <%=userId%>,"pageNo":nPageNo,"opdPre":"opdWait","serviceNo":service_no,"patientName":patient_name};
		} 
	 
	var url = "getOpdWaitingList";		
	var bClickable = true;
	GetJsonData('tblListofOpdP',data,url,bClickable);
}
 
 
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count;
 	
 	var dataList = jsonData.data;
 	if(dataList!=undefined && dataList!="" && dataList.length >= 0)	
 	{	
 	for(i=0;i<dataList.length;i++)
 		{	 		
 				
 			htmlTable = htmlTable+"<tr id='"+dataList[i].visitId+"' >";	
 			htmlTable = htmlTable +"<td  style=display:none;'>"+dataList[i].visitId+"</td>";
 			htmlTable = htmlTable +"<td  style=display:none;'>"+dataList[i].patientId+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px; '>"+dataList[i].tokenNo+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].serviceNo+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].patinetname+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].relation+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].rankName+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].empName+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].age+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].gender+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].departmentName+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].mobileNumber+"</td>";
 	
 			
 			htmlTable = htmlTable+"</tr>";
 		}	
 		}
 	   if(jsonData.status == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='10'><h6>No Record Found</h6></td></tr>";
		   //alert('No Record Found');
		}			
 	
 	//alert("tblListOfCommand ::" +htmlTable)
 	$j("#tblListofOpdP").html(htmlTable);	
 	
 	
 }
 
 function executeClickEvent(Id)
 {
	 //alert(Id)
	 window.location="getOpdPatientModel?visitId="+Id+"";
	
 }
 
 function showResultPage(pageNo) 	
 {
 	
 	nPageNo = pageNo;	
 	getOpdPreConsultationList('FILTER');
 	
 }
 
 function ResetForm()
 {	
 	 $j('#service_no').val('');
 	 $j('#patient_name').val('');
 }
 
 function showAll()
 {
	 ResetForm();
 	nPageNo = 1;	
 	getOpdPreConsultationList('ALL');
 	
 }
 
 
 function getDepartmentWaitingList()
 {
 	
	var departId=$('#com').val(); 
	var nPageNo=1;	
 	 	
 	var data = {"hospitalId": <%= hospitalId %>,"employeeId": <%=userId%>,"departmentId":departId,"opdPre":"opdWait", "serviceNo":"","patientName":"","pageNo":"1"};
 	var bClickable = true;
 	var url = "getOpdWaitingList";
 	GetJsonData('tblListofOpdP',data,url,bClickable);
 }
 
 
	function getDepartment() {

		var pathname = window.location.pathname;
        var accessGroup = pathname.split("/")[1];

        var url = window.location.protocol + "//" + window.location.host + "/" + accessGroup + "/admin/getDepartmentList";
       
		
		var params = {
			"hospitalID" : "<%= hospitalId %>"
		}

		$j.ajax({
					type : "POST",
					contentType : "application/json",
					url : url,
					data : JSON.stringify(params),
					dataType : "json",
					cache : false,
					success : function(msg) {
						if (msg.status == '1') {

							var comboval = "<option value=\"\">Select</option>";
							for (var i = 0; i < msg.departmentList.length; i++) {

								comboval += '<option value=' + msg.departmentList[i].departmentId + '>'
										+ msg.departmentList[i].departmentname
										+ '</option>';

							}
							$j('#com').append(comboval);

						}

					},

					error : function(msg) {

						alert("An error has occurred while contacting the server");

					}
				});
	}
 
 
</script>

 <body>
  <!-- Begin page -->
    <div id="wrapper">
 
 <div class="content-page">
                                <!-- Start content -->
 <div class="">
  <div class="container-fluid">
	 
	  <div class="internal_Htext">OPD Waiting List</div>
	 
                    <div class="row">
                        <div class="col-12">
                            <div class="page-title-box">
                                

                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                    <!-- end row -->

                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">

                                     <div class="row">
                                    <!--  <div class="form-group row">
												<label class="col-sm-7 col-form-label">Department<sup><span>&#9733;</span></sup></label>
												<div class="col-sm-5">
													<select class="form-control" id="com" onchange="getDepartmentWaitingList()">
													</select>
												  </div>
											     </div> -->
										<div class="col-md-4">
											<div class="form-group row">
											    	
												<label class="col-sm-5 col-form-label">Service No.</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="service_no" placeholder="">
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-5 col-form-label">Patient Name</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="patient_name" placeholder="">
												</div>
											</div>
										</div>
										<div class="col-md-1">
											<div class="form-group row">
												
												<div class="col-sm-4">
													<button type="button" class="btn btn-primary" onclick="searchOpdPreConsultationList()">Search</button>
												
												</div>
												
											</div>
										</div>
										
										
										
										
										<div class="col-md-3">
										<div class="btn-right-all">
												<button type="button" class="btn  btn-primary "
													onclick="showAll('ALL');">Show All</button>
												</div>
											 
										</div>
                                      

                                    </div>

                                     <div classs="row">
                                     
                                     <div class="col-md-4">
                                     </div>
                                     
                                     </div>

								<div style="float: left">

									<table class="tblSearchActions" cellspacing="0" cellpadding="0"
										border="0">
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


								<table class="table table-hover table-bordered">
                                        <thead class="bg-primary" style="color:#fff;">
                                            <tr>
                                             <th style=display:none; >VisitId</th>
											<th style="display: none;">PatientId</th>
											<th>Token No.</th>
											<th>Service No.</th>
											<th>Patient Name</th>
											<th>Relation</th>
											<th>Rank</th>
											<th>Emp Name</th>
											<th>Age</th>
											<th>Gender</th>
											<th>Department</th>
											<th>Mobile No</th>
											<!-- <th>Action</th>
											<th>Action</th> -->
										</tr>
                                        </thead>
                                         
                                        <tbody id="tblListofOpdP">

                                        </tbody>
                                    </table>
						
                                    <!-- end row -->

                                </div>
                            </div>
                            <!-- end card -->
                        </div>
                        <!-- end col -->
                    </div>
                    <!-- end row -->
                    <!-- end row -->

                </div>
                <!-- container -->
                 </div>
                  </div>

</div>

</body>
</html>