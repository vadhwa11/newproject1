<%@page import="java.util.HashMap"%>
    <%@page import="java.util.Map"%>
    <%@ page import="com.asha.icgweb.utils.ProjectUtils"%>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

          
        <%--   <%@include file="..//view/leftMenu.jsp" %> --%>

                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                    <html>

                    <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                               
                   
                       
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/metismenu.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
  
   
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.datePicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>  
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/hms.js"></script>                
                            <title>OPD Previous Vital Record</title>
                 
	                 
                    
                    
                   
                    </head>
                    <body style="background:#ffff;">
                    
                    
   <%
	Map<String, Object> map = new HashMap<String, Object>();
	if (request.getAttribute("map") != null) {
		map = (Map) request.getAttribute("map");
	}
	Map<String, Object> utilMap = new HashMap<String, Object>();
	utilMap = (Map) ProjectUtils.getCurrentDateAndTime();
	String date = (String) utilMap.get("currentDate");
	String time = (String) utilMap.get("currentTime");

	String userName = "";
	if (session.getAttribute("userName") != null) {
		userName = (String) session.getAttribute("userName");
	}
	String message = "";
	if (map.get("message") != null) {
		message = (String) map.get("message");
		//out.println(message);
	}
	System.out.println("message==in jsp ===" + message);
	
	String patientId="";
	patientId= request.getParameter("patientId"); 
	
	
%>
<div class="container-fluid">
<div class="popupbg">

	<h4></h4>
	<div class="titleBg">
		<div class="internal_Htext">Previous Vital</div>
	</div>
	 
	<div class="Clear"></div>


	
	<form name="Previous Vitals Record" method="post" action="">

		
     <br>
		
		<div class="clear"></div>
		<div class="paddingTop15"></div>
		<div id="testDiv">
		<div style="float: right">
									<div id="resultnavigation"></div>
		</div>
			<table class="table table-hover table-bordered">
                                        <thead class="bg-primary" style="color:#fff;">
                                            <tr>
                                            <th>Visit Date</th>
											<th>Height</th>
											<th>Weight</th>
											<th>Ideal Weight</th>
											<th>Temperature</th>
											<th>BP</th>
											<th>BMI</th>
											<th>RR</th>
											<th>SpO2</th>
											<th>Varation</th>
											<!-- <th>Action</th>
											<th>Action</th> -->
										</tr>
                                        </thead>
                                         
                                        <tbody id="tblListofOpdPrevious">

                                        </tbody>
                                    </table>
		
</div>


		<div class="Clear"></div>
		<div class="division"></div>

			 <button	type="reset" name="Reset" id="reset" value="" class="button btn btn-danger btn-right-all"
			onclick="closeWindow();" accesskey="r" /u>Close</button>

		
		<div class="Clear"></div>
		<div class="division"></div>
		<div class="bottom">
		                            
		 

		</div>
	</form>
</div>

</div>
 <script type="text/javascript" language="javascript">

var nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
			getOpdPreConsultationList('ALL');
			
		});
		
		
 function searchOpdPreConsultationList()
{
	
	var nPageNo=1;	
	var url = "getOpdPreviousVital";
	var service_no = $j('#service_no').val();
	var patient_name = $j('#patient_name').val();
	if((service_no == undefined || service_no == '') && (patient_name == undefined || patient_name == '')){	
		alert("Atlease one option must be entered");
		return;
	}
	var data = {"patientId": <%=patientId%>};
	alert(data)
	var bClickable = true;
	
	GetJsonData('tblListofOpdPrevious',data,url,bClickable);
} 

 function getOpdPreConsultationList(MODE) { 	
 	
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
 	<%-- var data = {"hospitalId": <%= hospitalID %>, "serviceNo":service_no,"patient_name":patient_name,"pageNo":nPageNo}; --%>
 	 	var data = {"patientId": <%=patientId%>}; 
        console.log("data"+data); 
 	//var url = "getObesityWaitingList";
 		
 	var bClickable = true;
 	var url = "getOpdPreviousVital";
 	GetJsonData('tblListofOpdPrevious',data,url,bClickable);
 }
 
 
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count;
 	
 	var dataList = jsonData.data;
 		
 	for(i=0;i<dataList.length;i++)
 		{	 		
 				
 			htmlTable = htmlTable+"<tr >";	
 			htmlTable = htmlTable +"<td style='width: 100px; '>"+dataList[i].visitdate+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].height+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].weight+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].idealWeight+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].tempature+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].bp+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].bmi+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].rr+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].spo2+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].varation+"</td>";
 			htmlTable = htmlTable+"</tr>";
 			
 		}
 	if(dataList.length == 0)
 		{
 		htmlTable = htmlTable+"<tr ><td colspan='6'><h6>No Record Found</h6></td></tr>";
 		   //alert('No Record Found');
 		}			
 	
 	//alert("tblListOfCommand ::" +htmlTable)
 	$j("#tblListofOpdPrevious").html(htmlTable);	
 	
 	
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
 
 
</script>

</body>
</html>
