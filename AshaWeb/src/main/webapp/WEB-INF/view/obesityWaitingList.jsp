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
<title>Masters</title>

<%@include file="..//view/commonJavaScript.jsp" %>
</head>
 
<script type="text/javascript" language="javascript">
<%			
	String hospitalId = "1";
	if (session.getAttribute("hospital_id") !=null)
	{
		hospitalId = session.getAttribute("hospital_id")+"";
		System.out.println("hospital id is "+session.getAttribute("hospital_id"));
	}
%>

var nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
			getObesityList('ALL');
			
		});
		
		
 function searchObesityWaitingList()
{
	
	var nPageNo=1;	
	var url = "obesityWaitingList";
	var service_no = $j('#service_no').val();
	var patient_name = $j('#patient_name').val();
	if((service_no == undefined || service_no == '') && (patient_name == undefined || patient_name == '')){	
		alert("Atlease one option must be entered");
		return;
	}
	var data = {"hospitalId": <%= hospitalId %>, "service_no":service_no,"patient_name":patient_name,"pageNo":"1"};
	var bClickable = true;
	
	GetJsonData('tblListofObesity',data,url,bClickable);
} 

 function getObesityList(MODE) { 	
 	
 	var cmdId=0; 	

 	 if(MODE == 'ALL')
 		{
 			var data = {"PN":nPageNo};
 			
 		}
 	else
 		{
 			var data = {"PN":nPageNo};
 		} 
 	 
 	 var patient_name = $j('#patient_name').val();
 	 var service_no = $j('#service_no').val();
 	if (patient_name === undefined) {
 		patient_name = "";
 	}
	if (service_no === undefined) {
		service_no = "";
 	}
 	<%-- var data = {"hospitalId": <%= hospitalID %>,"pageNo":"1"}; --%>
 	var data = {"hospitalId": <%= hospitalId %>, "serviceNo":service_no,"patient_name":patient_name,"pageNo":nPageNo};
 	//var url = "getObesityWaitingList";
 		
 	var bClickable = true;
 	var url = "obesityWaitingList";
 	GetJsonData('tblListofObesity',data,url,bClickable);
 }
 
 
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count;
 	var pageSize = 5;
 	var dataList = jsonData.patientObesityList;
 		
 	for(i=0;i<dataList.length;i++)
 		{	 		
 				
 			htmlTable = htmlTable+"<tr id='"+dataList[i].Id+"' >";			
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].serviceNo+"</td>";
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].patientName+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].age+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].gender+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].DeptName+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].variation+"</td>";
 			
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
	 window.location="patientObesityDetailjsp?Id="+Id+"";
	 
 }
 
 function showResultPage(pageNo) 	
 {
 	
 	nPageNo = pageNo;	
 	getObesityList('FILTER');
 	
 }
</script>
 <body>
 
 <div class="content-page">
                                <!-- Start content -->
 <div class="">
  <div class="container-fluid">
	 
	  <div class="internal_Htext">Obesity Waiting List</div>
	 
                    <!-- end row -->

                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">

                                     <div class="row">
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
												
												<div class="col-sm-10">
													<button type="button" class="btn btn-primary  obesityWait-search" onclick="searchObesityWaitingList()">Search</button>
												</div>
											</div>
										</div>
										
										<div class="col-md-3">
											 
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


								<table class="table table-hover table-striped table-bordered">
                                        <thead class="bg-primary" style="color:#fff;">
                                            <tr>
                                                <th id="th1">Service No.</th>
                                                <th id="th2">Patient Name </th>
                                                <th id="th3"> Age</th>
                                                <th id="th4">Gender</th>
												 <th id="th5">Department/Doctor</th> 
												 <th id="th6">Variation in weight</th>
                                            </tr>
                                        </thead>
                                         
                                        <tbody id="tblListofObesity">

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
</body>
</html>