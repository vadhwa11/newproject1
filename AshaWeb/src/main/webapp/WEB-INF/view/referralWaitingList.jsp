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
 
<title>Referred Patient</title>
<%@include file="..//view/commonJavaScript.jsp" %>
</head>
<script type="text/javascript" language="javascript">
<%	String hospitalId = "1";
%>

var nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
			getReferredList('ALL');
			
		});

 function getReferredList(MODE) { 	
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
	if (mobile_no === undefined) {
		mobile_no = "";
 	}
 	var data = {"hospital_id": <%= hospitalId %>, "serviceNo":service_no,"patient_name":patient_name,"pageNo":nPageNo,"mobile_no":mobile_no}; 
 		
 	var bClickable = true;
 	var url = "referredPatientList";
 	GetJsonData('tblListofObesity',data,url,bClickable);
 }
 
 function searchReferredWaitingList()
 {
 	//var nPageNo=1; 
 	$j('#tblListofObesity').empty();
 	var url = "referredPatientList";
 	var service_no = $j('#service_no').val();
 	var patient_name = $j('#Patient_name').val();
 	var mobile_no = $j('#mobile_no').val();
 	if((service_no == undefined || service_no == '') && (patient_name == undefined || patient_name == '') && (mobile_no == undefined || mobile_no == '')){
 		alert("Atleast one of the search option must be entered");
 	}
 	<%-- var data = {"hospitalId": <%= hospitalId %>, "service_no":service_no,"patient_name":patient_name,"pageNo":"1"}; --%>
 	var data = {"hospital_id": <%= hospitalId %>, "serviceNo":service_no,"patient_name":patient_name,"pageNo":1,"mobile_no":mobile_no};
 	var bClickable = true;
 	
 	GetJsonData('tblListofObesity',data,url,bClickable);
 	ResetForm();
 } 
 
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count;
 	
 	var pageSize = 5;
 	var dataList = jsonData.referral_list;
 	for(i=0;i<dataList.length;i++)
 		{	 		
 				
 			htmlTable = htmlTable+"<tr id='"+dataList[i].id+"' >";			
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].service_no+"</td>";
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].patient_name+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].age+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].gender+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].rank+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].mobile_no+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].referred_hospital+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].referral_date+"</td>";
 			
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
	window.location="referredPatientDetail?Id="+Id+"";
	 
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
 	getReferredList('FILTER');
 	
 }
</script>
<body>

 <div class="content-page">
    <div class="">
       <div class="container-fluid"> 
	  <div class="internal_Htext">Referral Waiting List</div>
	 
                    <!-- end row -->
                    
                    
                    
                     <div class="row">
                                            <div class="col-12">
                                                <div class="card">
                                                    <div class="card-body">
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    <form>
			
			
			
			
			
			
			
			
			                                      <div class="row"> 
														   
													          
													          
													          <div class="col-md-3"> 
																    
																		 <div class="form-group row">
																	    <label class="col-sm-4 col-form-label">Service	No.</label>
																	    <div class="col-sm-8">
																	        <input class="form-control form-control-sm" type="text" placeholder="" id="service_no">
																	    </div>
																	  </div> 
													          </div>	 
													          
													          <div class="col-md-3"> 
																    
																		 <div class="form-group row">
																	    <label class="col-sm-4 col-form-label">Patient 	Name</label>
																	    <div class="col-sm-8">
																	        <input class="form-control form-control-sm" type="text" placeholder="" id="Patient_name" >
																	         
																	    </div>
																	  </div> 
													          </div> 
													          
													          <div class="col-md-3"> 
																    
																		 <div class="form-group row">
																	    <label class="col-sm-4 col-form-label">Mobile	No.</label>
																	    <div class="col-sm-8">
																	        <input class="form-control form-control-sm" type="text" placeholder="" id="mobile_no" >
																	    </div>
																	  </div> 
													          </div> 
													          
													          
													           <div class="col-md-3"> 
																   
																		 <div class="form-group row">
																	     
																	    <div class="col-sm-12">
																	        <button type="button" class="btn btn-primary"
																					onclick="searchReferredWaitingList()">Search</button>
																	    </div>
																	  </div>
															 
													          </div>	 
													          
													          
													</div>
			
			
			
			
	
			<br>
			 <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >			<tr>
				<td class="SearchStatus" style="font-size: 15px;" align="left">Search Results</td>
				<td>
				<div id=resultnavigation></div>
				
				</td>
				</tr>
			</table>
				<div class="tablediv">
							<table class="table table-bordered ">
								<thead bgcolor="00FFFF">
									<tr>
										<th id="th2">Service No.</th>
										<th id="th3">Patient Name</th>
										<th id="th4">Age</th>
										<th id="th5">Gender</th>
										<th id="th6">Rank</th>
										<th id="th7">Mobile No.</th>
										<th id="th7">Referred Hospital</th>
										<th id="th7">Referred Date</th>
			
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



















	<%-- <div class="container">

		<form>
			<div class="form-row">
				<div class="form-group col-md-8">
					<h3>
						<label for="inputEmail3" class="col-md-10 col-form-label">Referral
							Waiting List</label>
					</h3>
				</div>
			</div>
			<div class="form-row">
				<table class="tblSearchActions" cellspacing="0" cellpadding="0"
					border="0">
					<tr>
						<td class="SearchStatus" style="font-size: 13px;" align="left">Search
							Results</td>
						<td><label for="Service No." class="col-sm-4 col-form-label">Service
								No.</label>
							<div class="col-sm-8">
								<input type="text" class="border" id="service_no">
							</div></td>
						<td><label for="Patient Name" class="col-sm-4 col-form-label">Patient
								Name</label>
							<div class="col-sm-8">
								<input type="text" class="border" id="Patient_name"
									style="margin-right: 10px">
							</div></td>
						<td><label for="Patient Name" class="col-sm-4 col-form-label">Mobile
								No.</label>
							<div class="col-sm-8">
								<input type="text" class="border" id="mobile_no"
									style="margin-right: 10px">
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
							<th id="th2">Service No.</th>
							<th id="th3">Patient Name</th>
							<th id="th4">Age</th>
							<th id="th5">Gender</th>
							<th id="th6">Rank</th>
							<th id="th7">Mobile No.</th>
							<th id="th7">Referred Hospital</th>
							<th id="th7">Referred Date</th>

						</tr>
					</thead>
					<tbody id="tblListofObesity">

					</tbody>
				</table>
			</div>
		</div>
		</form>
		</div> --%>
</body>
</html>