<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" language="javascript" src="/resources/js/ajax.js"></script>
<script type="text/javascript" src="/resources/js/pops_global.js"></script>
<script type="text/javascript" src="/resources/js/pops_menu.js"></script>
<script type="text/javascript" language="javascript" src="/resources/js/controls.js"></script>

</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Masters</title>

<!-- Bootstrap core CSS     -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    --> <!-- left menu dashboard -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css" rel="stylesheet">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<% int hospitalID = 1; %>
</head>
 
<script type="text/javascript" language="javascript">

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
	var patient_name = $j('#Patient_name').val();
	if(service_no != undefined && service_no != '' && patient_name != undefined && patient_name != ''){
		alert("Atlease one option must be entered");
	}
	var data = {"hospitalId": <%= hospitalID %>, "service_no":service_no,"patient_name":patient_name,"pageNo":"1"};
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
 	 
 	 var patient_name = $j('patient_name').val();
 	 var service_no = $j('service_no').val();
 	if (patient_name === undefined) {
 		patient_name = "";
 	}
	if (service_no === undefined) {
		service_no = "";
 	}
 	<%-- var data = {"hospitalId": <%= hospitalID %>,"pageNo":"1"}; --%>
 	var data = {"hospitalId": <%= hospitalID %>, "serviceNo":service_no,"patient_name":patient_name,"pageNo":1}; 
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
</script>
 <body>
 
    <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >
			<tr>
				<td class="SearchStatus" style="font-size: 13px;" align="left">Search Results</td>
				<td>
				<label for="Service No." class="col-sm-4 col-form-label">Service No.</label>
					<div class="col-sm-8">
						<input type="text" class="border" id="service_no">
					</div>
				</td>
				<td>
				<label for="Patient Name" class="col-sm-4 col-form-label">Patient Name</label>
					<div class="col-sm-8">
						<input type="text" class="border" id="Patient_name"  style="margin-right: 10px">
					</div>
				</td>
				<td>
					<button type="button" class="btn btn-success"  onclick="searchObesityWaitingList()">Search</button>
				</td>
				</tr>
		</table><br><br>
<div id = 'obesity_table'>
 <table class="table table-bordered ">
                    
                      <thead bgcolor="00FFFF">                        
                        <tr>                          
                         
                          <th id="th2">Service No.</th>
                          <th id="th3">Patient Name</th>
                          <th id="th4">Age</th>
                          <th id="th5">Gender</th>
                          <th id="th6">Department</th>
                          <th id="th7">Variation in weight</th>
                                                    
                        </tr>
                      </thead>
                      <tbody id="tblListofObesity">                      
                    
                      </tbody>
                    </table>
    </div>
</body>
</html>