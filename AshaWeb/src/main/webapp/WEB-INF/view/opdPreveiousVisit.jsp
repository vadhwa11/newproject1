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
                            <title>OPD Main</title>
                 
	                 
                    
                    
                   
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

<div class="popupbg">

	<h4></h4>
	<div class="titleBg">
		<div class="internal_Htext">Previous Visit</div>
	</div>
	 
	<div class="Clear"></div>


	
	<form name="PreviousVisitRecord" method="post" action="">

		
     
		<input type="text" name="visit_id" id="visit_id" value="">
									<div id="resultnavigation"></div>
		
			<table class="table table-hover table-bordered">
                                        <thead class="bg-primary" style="color:#fff;">
                                            <tr>
                                             <th style=display:none; >VisitId</th>
											<th style="display: none;">PatientId</th>
											<th>Visit Date</th>
											<th>Working/Snomed Diagnosis</th>
											<th>Doctor</th>
											<th>Speciality</th>
											<th>Chief Complaint</th>
											<th>History Of Present illness</th>
											<th>ICD Diagnosis</th>
											<th>Action</th>
											
											<!-- <th>Action</th>
											<th>Action</th> -->
										</tr>
                                        </thead>
                                         
                                        <tbody id="tblListofOpdPrevious">

                                        </tbody>
                                    </table>
		
</div>


		

			

		

		            <div class="clearfix"></div>								
								         <br>
										<div class="row">		 
										 <div class="col-md-12">
															<div class="btn-left-all">																 
															</div> 
															<div class="btn-right-all">
															 <button	type="reset" name="Reset" id="reset" value="" class="button btn btn-danger"
															onclick="closeWindow();" accesskey="r" /u>Close</button>
																
															
															</div> 
													   </div> 
										</div>
                    
		 
		

			<%-- <label>Changed By</label> 
			<label class="value"></label>
			 <label>Changed	Date</label> 
				<label class="value"><%=date%></label>
				 <label>Changed	Time</label>
				  <label class="value"><%=time%></label>
				   <input type="hidden"	name=" " value=" " />
				    <input type="hidden" name=" " value=" " /> 
				    <input	type="hidden" name=" " value=" " />
			<div class="Clear"></div>
			<div id="edited"></div> --%>

		</div>
	</form>

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
	var url = "getOpdPreviousVisitRecord";
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
 
 	var bClickable = true;
 	var url = "getOpdPreviousVisitRecord";
 	GetJsonData('tblListofOpdPrevious',data,url,bClickable);
 }
 
 function closeWindow()
 {
  //if(getTemplateDetail()){
   window.close();
  //}
 }
 
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count;
 	
 	var dataList = jsonData.data;
 		
 	for(i=0;i<dataList.length;i++)
 		{	 		
 				
 			htmlTable = htmlTable+"<tr id='"+dataList[i].patientId+"' >";	
 			htmlTable = htmlTable +"<td style='width: 100px; '>"+dataList[i].visistDate+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].workingDiagnosis+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].specialisty+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].diagnosis+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].chiefComplaint+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].presentHistoryIllness+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].icdDiagnosis+"</td>";
 			htmlTable= htmlTable + "<td><button id ='printBtn' type='button'  class='btn btn-primary' onclick='printToken("+dataList[i].visitId+");'>Case Sheet</button></td>";
 			/* htmlTable = htmlTable +"<td><input type='submit' value='Release' id='released' />" +"</td>";
 			htmlTable = htmlTable +"<td><input type='submit' value='Close' id='closed' />" +"</td>"; */
 			
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
 
 function printToken(visitId)
 {
	 
	 document.getElementById('visit_id').value=visitId;
	 
		document.PreviousVisitRecord.action="${pageContext.request.contextPath}/report/printCaseSheet";
		document.PreviousVisitRecord.method="POST";
		document.PreviousVisitRecord.submit(); 
	 
	 
 }
 
 
</script>

</body>
</html>
