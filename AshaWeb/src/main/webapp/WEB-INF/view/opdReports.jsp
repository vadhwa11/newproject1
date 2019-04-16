<%@page import="java.util.HashMap"%>
    <%@page import="java.util.Map"%>
    <%@ page import="com.asha.icgweb.utils.ProjectUtils"%>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

          
              <%@include file="..//view/leftMenu.jsp" %>

                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                    <html>

                    <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                        <%@include file="..//view/commonJavaScript.jsp" %>
                            <title>OPD Reports</title>
                      <!--   <link href="/AshaWeb/resources/css/stylecommon.css" rel="stylesheet" type="text/css" /> -->
                    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/newOpd.js"></script>
                   <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
                    
                   
                    </head>
                    <body>
                    
                    
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

	
%>

<div class="popupbg">

	<h4></h4>
	<div class="titleBg">
		<div class="internal_Htext">OPD Prescription Reports </div>
	</div>
	 
	<div class="Clear"></div>

	
	<script type="text/javascript" language="javascript">
	
	function getPatientId() {
      
		var pathname = window.location.pathname;
		var accessGroup = pathname.split("/")[1];
		/* var url = window.location.protocol + "//"
		+ window.location.host + "/" + accessGroup
		+ "/v0.1/opd/getTemplateName"; */
		
		var empNo=document.getElementById('employeeNo').value;
		var url="http://localhost:7002/AshaServices/v0.1/opd/getOpdReportsDetailsbyServiceNo";
		
		$
				.ajax({
					url : url,
					dataType : "json",
					data : JSON.stringify({
						'serviceNo' : empNo
					}),
					contentType : "application/json",
					type : "POST",
					success : function(response) {
						  if (response.status == 1) {
						var datas = response.data;
						var trHTML = '<option value=""><strong>Choose...</strong></option>';
						 for (var i = 0; i < datas.length; i++) {
                             var patientId = datas[i].patientId;
                             var patientName = datas[i].patinetName;
                            
                             var a = patientName + "[" + patientId + "]"
                             trHTML += '<option value="' + patientId + '" >'
								+ a + '</option>';
                            
                         }
						
						$('#patientName').html(trHTML);
					}
				    else
					{
					  alert("No Record Found");		  
					}
					},
					error : function(e) {

						console.log("ERROR: ", e);

					},
					done : function(e) {
						console.log("DONE");
						alert("success");
						var datas = e.data;

					}
				});

	}
	
	$(document).delegate("#patientName","change",
			function() {

		var pathname = window.location.pathname;
		var accessGroup = pathname.split("/")[1];
		var url = "http://localhost:7002/AshaServices/v0.1/opd/getOpdReportsDetailsbyPatinetId";
		
		$
				.ajax({
					url : url,
					dataType : "json",
					data : JSON.stringify({
						'employeeId' : '1',
						'patientId':$('#patientName').val()
					}),
					contentType : "application/json",
					type : "POST",
					success : function(response) {
						console.log(response);
					   if (response.status == 1) {
						var datas = response.data;
						var trHTML = '<option value=""><strong>Choose...</strong></option>';
						 for (var i = 0; i < datas.length; i++) {
                            var visitId = datas[i].visitNo;
                            var visitDate = datas[i].visitDate;
                            var deptName = datas[i].departmentNo;
                           
                            var a = visitId + "[" + visitDate + "]" + "[" + deptName + "]"
                            trHTML += '<option value="' + visitId + '" >'
								+ a + '</option>';
                           
                        }
						
						$('#service').html(trHTML);


					}
					   else
						{
						  alert("No Record Found")
						  
						}
					}
				   ,
					error : function(e) {

						console.log("ERROR: ", e);

					},
					done : function(e) {
						console.log("DONE");
						alert("success");
						var datas = e.data;

					}
				});

	});



function submitWindow()
{
var code=document.getElementById('code').value;
var flag =true;
if(validateMetaCharacters(code)){

}

}

function closeWindow()
{
  window.close();
}

	</script>

	
	<form name="investigationTemplate" method="post" action="">

       <div class="content-page">
                                <div class="">
                                    <div class="container-fluid">
                                        <div class="internal_Htext"> OPD Reports</div>

                                        <div class="row">
                                            <div class="col-12">
                                                <div class="card">
                                                    <div class="card-body">

                                                        <form>

                                                            <!-- Service Detail Start Here -->
                                                             <div class="opdMain_detail_area">
                                                            <h4  class="service_htext"> OPD Reports Section</h4>
                                                            <div class="row">

                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label class="col-sm-5 col-form-label">Employee No. </label>
                                                                        <div class="col-sm-7">
                                                                            <input name="employee_no" id="employeeNo" type="text" class="form-control border-input" placeholder="Employee No." value="" onblur="getPatientId()" />
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                           </div>
                                                             <div class="row">

                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label class="col-sm-5 col-form-label">Patinet Name </label>
                                                                        <div class="col-sm-7">
                                                                            <select name="patient_name" id="patientName" type="text" class="form-control border-input" placeholder="Employee No." value=""></select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                           </div>
                                                           <div class="row">
                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label class="col-sm-5 col-form-label">Visit No. For Prescription</label>
                                                                        <div class="col-sm-7">
                                                                            <select name="employee_no" id="service" type="text" class="form-control border-input" placeholder="Employee No." value=""></select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                           </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <div class="form-group row">                                                                       
                                                                        <div class="col-sm-12">
                                                                             <button type="button" class="buttonDel btn  btn-primary" value="" onclick="deleteDgItems(this,'diagnosisId');" align="right" />  OK  </button>
                                                                               <button type="button" class="buttonDel btn btn-danger" value="" onclick="deleteDgItems(this,'diagnosisId');" align="right" /> CANCEL </button>
                                                                        </div>
                                                                    </div>
                                                                </div>                                                                
                                                                <div class="col-md-6">                                                                     
                                                                </div>
                                                           </div>
                                                            
                                                           
                                                           
                                                           <div class="row">                         
                                                             <div class="col-md-4">
																  <div class="form-group row">
																       <label class="col-sm-2">Date </label>
																        <div class="col-sm-6">
                                                                            <div class="value investigationPop"><%=date%></div>
                                                                        </div>
																 </div>
															 </div>
                                                            <div class="col-md-4">
																  <div class="form-group row">
																 <label class="col-sm-2">Time </label>
																 <div class="col-sm-6">
                                                                            <div class="value investigationPop"><%=time%></div> 
                                                                        </div>
																 </div>
															 </div>	
															  <div class="col-md-4">																  
															 </div> 
														 </div>
                                                           
                                                           
                                                           
                                                           
                                                            </div>
                                                           
                                                           
                                                           
                                                           
                                                           
                                                           
                                                           
                                                           </form>
                                      
		
                                                          
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

<script>

var arry=new Array();
autocomplete(document.getElementById("chargeCodeName1"), arry);

</script>


<script type="text/javascript" language="javascript">


$(document).ready(
		function() {
			var radioValue = 1; 
			
	var pathname = window.location.pathname;
	var accessGroup = pathname.split("/")[1];

	var url = window.location.protocol + "//"
			+ window.location.host + "/" + accessGroup
			+ "/v0.1/opd/getIinvestigationList";

	//var data = $('employeeId').val();
   // alert("radioValue" +radioValue);
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : JSON.stringify({
			'employeeId' : "1",
			'mainChargeCode':radioValue,
		}),
		dataType : 'json',
		timeout : 100000,
		
		success : function(res)
		
		{
			var data = res.InvestigationList;
			
			for(var i=0;i<data.length;i++){
				var investigationId= data[i].investigationId;
				var investigationName = data[i].investigationName;
				//var icdName = data[i].icdName;
				var a=investigationName+"["+investigationId +"]"
				//alert("a "+a);
				 arry.push(a);
				console.log('data :',a);
			}

		
           },
           error: function (jqXHR, exception) {
               var msg = '';
               if (jqXHR.status === 0) {
                   msg = 'Not connect.\n Verify Network.';
               } else if (jqXHR.status == 404) {
                   msg = 'Requested page not found. [404]';
               } else if (jqXHR.status == 500) {
                   msg = 'Internal Server Error [500].';
               } else if (exception === 'parsererror') {
                   msg = 'Requested JSON parse failed.';
               } else if (exception === 'timeout') {
                   msg = 'Time out error.';
               } else if (exception === 'abort') {
                   msg = 'Ajax request aborted.';
               } else {
                   msg = 'Uncaught Error.\n' + jqXHR.responseText;
               }
               alert(msg);
           }
	});
	

}); 
var arrayData = [];
var i = "";
function changeRadio(radioValue){
	i++;
	/* var radioValue = '';
	 $('input[name=labradiologyCheck]').change(function(){
		 radioValue = $( 'input[name=labradiologyCheck]:checked' ).val();
 	   
 	}); */ 
	 alert(radioValue);
	var pathname = window.location.pathname;
	var accessGroup = pathname.split("/")[1];

	var url = window.location.protocol + "//"
			+ window.location.host + "/" + accessGroup
			+ "/v0.1/opd/getIinvestigationList";

	//var data = $('employeeId').val();
   // alert("radioValue" +radioValue);
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : JSON.stringify({
			'employeeId' : "1",
			'mainChargeCode':radioValue,
		}),
		dataType : 'json',
		timeout : 100000,
		
		success : function(res)
		
		{
			var data = res.InvestigationList;
			
			for(var i=0;i<data.length;i++){
				var investigationId= data[i].investigationId;
				var investigationName = data[i].investigationName;
				//var icdName = data[i].icdName;
				var a=investigationName+"["+investigationId +"]"
				//alert("a "+a);
				arrayData.push(a);
				 
				// autocomplete(document.getElementById("chargeCodeName1"), "");
				 autocomplete(document.getElementById("chargeCodeName1"), arrayData);
				//console.log('data :',a);
			}

		
           },
           error: function (jqXHR, exception) {
               var msg = '';
               if (jqXHR.status === 0) {
                   msg = 'Not connect.\n Verify Network.';
               } else if (jqXHR.status == 404) {
                   msg = 'Requested page not found. [404]';
               } else if (jqXHR.status == 500) {
                   msg = 'Internal Server Error [500].';
               } else if (exception === 'parsererror') {
                   msg = 'Requested JSON parse failed.';
               } else if (exception === 'timeout') {
                   msg = 'Time out error.';
               } else if (exception === 'abort') {
                   msg = 'Ajax request aborted.';
               } else {
                   msg = 'Uncaught Error.\n' + jqXHR.responseText;
               }
               alert(msg);
           }
	});
}
var charge_code_array = [];
var ChargeCode='';
var multipleChargeCode = new Array();
function populateChargeCode(val,count) {
	
	
	if(validateMetaCharacters(val)){
		if(val != "")
		{
			
		    var index1 = val.lastIndexOf("[");
		    var indexForChargeCode=index1;
		    var index2 = val.lastIndexOf("]");
		    index1++;
		    ChargeCode = val.substring(index1,index2);
		    
		    var indexForChargeCode=indexForChargeCode--;
		   // alert("value is fun"+ChargeCode);
       
		if(ChargeCode == "")
		{
		document.getElementById('chargeCodeName'+count).value="";
		document.getElementById('chargeCodeCode'+count).value="";
		return;
		}
		else
			ChargeCode= document.getElementById('chargeCodeCode'+count).value=ChargeCode
			multipleChargeCode[count-1]=ChargeCode;
			
		}
		}	
		}
	



</script>

<script type="text/javascript" language="javascript">


 $('#addbutton').click(function() {
	    
	       var arry = [];
	    	for (var i = 0; i < multipleChargeCode.length; i++) {
	    	   // console.log("chargeCodeId" + ": " + multipleChargeCode[i]);
	    	    var param = {'investigationId' : multipleChargeCode[i],}
	   		    arry.push(param)
	    	   console.log("data array"+arry);
	    	} 
	    	
	    
	    		
	
	 var pathname = window.location.pathname;
		var accessGroup = pathname.split("/")[1];

		var url = window.location.protocol + "//"
				+ window.location.host + "/" + accessGroup
				+ "/v0.1/opd/saveOpdTemplates";

	 var dataJSON = {
	 'templateCode' : templateCode,
	 'templateName' : $('#templateName').val(),
	 'templateType' : "I",
	 'departmentId' : "1",
	 'doctorId' : "1",
	 'hospitalId' : "1",
	  "listofInvestigationTemplate" : arry
	 }
	 $.ajax({
	 type : "POST",
	 contentType : "application/json",
	 url : url,
	 data : JSON.stringify(dataJSON),
	 dataType : 'json',
	 timeout : 100000,
	 success : function(msg) {
	 console.log("SUCCESS: ", msg);
	 if (msg.status == 1)
 	   {
		   alert("OPD Template Details Insert successfully");
		   location.reload();
		  
	   }
		
	  else
	  {
		  alert(msg.status)
	   }
	 },
	 error: function (jqXHR, exception) {
         var msg = '';
         if (jqXHR.status === 0) {
             msg = 'Not connect.\n Verify Network.';
         } else if (jqXHR.status == 404) {
             msg = 'Requested page not found. [404]';
         } else if (jqXHR.status == 500) {
             msg = 'Internal Server Error [500].';
         } else if (exception === 'parsererror') {
             msg = 'Requested JSON parse failed.';
         } else if (exception === 'timeout') {
             msg = 'Time out error.';
         } else if (exception === 'abort') {
             msg = 'Ajax request aborted.';
         } else {
             msg = 'Uncaught Error.\n' + jqXHR.responseText;
         }
         alert(msg);
     },
	 });
	 		});
	  
 
 </script>
 
</body>
</html>