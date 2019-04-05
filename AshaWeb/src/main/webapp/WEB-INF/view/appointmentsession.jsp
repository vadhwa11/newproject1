<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@include file="..//view/leftMenu.jsp" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    
    <title>APPOINTMENT  SESSION</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/checkout/">
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" >
 
    
    <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script> --%>
    <%@include file="..//view/commonJavaScript.jsp" %>
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        
        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
   

</head>

<!-- <body class="bg-light"> -->

<body>

<div class="content-page">
            <!-- Start content -->
            <div class="">
                <div class="container-fluid">
                <div class="internal_Htext">APPOINTMENT  SESSION</div>
                    
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                      
                                   
                                   <div style="float:left">
					           
		                                    <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0">			<tbody><tr>
												<td class="SearchStatus" style="font-size: 15px; color: green;" align="left"></td>
												<td>
												 <!-- <div id=resultnavigation></div> -->
												
												</td>
												</tr>
										</tbody></table>
		                                 </div>
		                                 <div style="float:right">
				                                    <div id="resultnavigation"></div> 
		                                    </div>
                                   
				                                    <table class="table table-hover table-bordered">
												           <thead class="bg-success" style="color:#fff;">
												                   <tr>
												                    <th id="th2" class ="inner_md_htext">Hospital Name</th>
												                      <th id="th3" class ="inner_md_htext">Department Name</th>
												                      <th id="th4" class ="inner_md_htext">Appointment Type </th>
												                      <th id="th5" class ="inner_md_htext">Start Time</th>
												                      <th id="th5" class ="inner_md_htext">End Time</th>
												                      <th id="th5" class ="inner_md_htext">Status</th>
												                   </tr>
												               </thead>
												           
												          <tbody id="tblListOfAppointmentSession">
														 </tbody>
												       </table>
                                      
                                      
                                     <div class="row">  
								            <div class="col-md-4">
											 <div class="form-group row">											 
												<label   class="col-sm-5 col-form-label">Hospital</label>
												<div class="col-sm-7">
												 <select class="form-control" id="hospitalId">
												  <option value="0" selected="selected" >Select</option>
												</select>
												</div>
											  </div>
								                
								            </div>
								
								            <div class="col-md-4"> 
												<div class="form-group row">
													<label   class="col-sm-5 col-form-label">Department</label>
													<div class="col-sm-7">
													 <select class="form-control" id="departmentId">
													  <option value="0" selected="selected">Select</option>
													</select>
													</div>
												</div> 
								            </div>
											
											<div class="col-md-4">
											
											    <div class="form-group row">
													<label for="inputEmail3" class="col-sm-5 col-form-label">Appointment type</label>
													<div class="col-sm-7">
													 <select class="form-control" id="appointmentTypeId">
													  <option value="0" selected="selected">Select</option>
													</select>
													</div>
												</div> 
								                
								            </div>
								            
								            <div class="col-md-4">
							                    <div class="form-group row">
							                        <label for="StartTime" class="col-sm-5 col-form-label">Start Time </label>
							                        <div class="col-sm-7">
							
							                            <input type="time" class="form-control" id="startTime" placeholder="Enterable">
							                        </div>
							                    </div>
							            </div>
							            <div class="col-md-4">
							                    <div class="form-group row">
							                        <label for="EndTime" class="col-sm-5 col-form-label">End Time </label>
							                        <div class="col-sm-7">
							                            <input type="time" class="form-control" id="endTime" placeholder="Enterable">
							                        </div>
							                    </div>
							            </div>
										<div class="col-md-4">
							            </div>
								            
								  </div>

                                   
                                     
												       
												       <div class="row">
												       
												       <div class="col-md-9">
												       
												       
												       </div>
												       <div class="col-md-3">
												       <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off" onclick="submitAppointmentSession();">
												                SUBMIT
												        </button>
												        <button type="reset" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
												                    RESET
												        </button>
												       
												       </div>
												       
												       </div>
                                    
                                   
                                 

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
            <!-- content -->

           

        </div>

<!-- *************************************************************************************** -->
  <script type="text/javascript">
  var nPageNo=1;
  var Status;
  var $j = jQuery.noConflict();

  $j(document).ready(function()
  		{	
  			GetAppointmentSession('ALL');
  			
  		});
  		
  function GetAppointmentSession(MODE)
  {
  	var cmdId=0;
  	 if(MODE == 'ALL'){
  			var data = {"PN":nPageNo};			
  		}
  	else
  		{
  		var data = {"PN":nPageNo};
  		} 
  	var url = "getAllAppointmentSession";
  		
  	var bClickable = true;
  	GetJsonData('tblListOfAppointmentSession',data,url,bClickable);
  }
  function makeTable(jsonData)
  {	
  	var htmlTable = "";	
  	var data = jsonData.count;
  	var pageSize = 5;
  	var dataList = jsonData.data;
  	
  	for(i=0;i<dataList.length;i++)
  		{		
  		
  		 if(dataList[i].status == 'Y' || dataList[i].status == 'y')
  				{
  					Status='Active'
  						
  				}
  			else
  				{
  					Status='InActive'
  				}
  		 
  		
  				
  			htmlTable = htmlTable+"<tr id='"+dataList[i].id+"' >";			
  			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].hospitalName+"</td>";
  			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].departmentName+"</td>";			
  			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].apppointmentType+"</td>";
  			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].startTime+"</td>";
  			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].endTime+"</td>";
  			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>";
  			
  			htmlTable = htmlTable+"</tr>";
  			
  		}
  	if(dataList.length == 0)
  		{
  		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
  		   //alert('No Record Found');
  		}			
  	
  	$j("#tblListOfAppointmentSession").html(htmlTable);	
  	
  	
  }  
  </script>
  
        
  <script>
    
    $(document).ready(function(){
    	var dictionary = ${data};
    	var hospitalList = dictionary.hospitalList;
    	var appointmentTypeList=dictionary.appointmentTypeList;
    	var departmentList=	dictionary.departmentList;
    
    	
    var hospitalValues="";
    	for(hospital in hospitalList){
    		hospitalValues += '<option value='+hospitalList[hospital].hospitalId+'>'
 							+ hospitalList[hospital].hospitalName
 							+ '</option>';
 		 }
 		 $('#hospitalId').append(hospitalValues); 	
    	
    var appointmentTypeValues="";
   	    for(appointmentType in appointmentTypeList){
   	    	appointmentTypeValues += '<option value='+appointmentTypeList[appointmentType].appointmentTypeId+'>'
							+ appointmentTypeList[appointmentType].appointmentTypeName
							+ '</option>';
		 }
		 $('#appointmentTypeId').append(appointmentTypeValues); 	
		 
    var deptValues="";
        for(dept in departmentList){
				deptValues += '<option value='+departmentList[dept].departmentId+'>'
							+ departmentList[dept].departmentName
							+ '</option>';
		 }
		 $('#departmentId').append(deptValues); 	  
	  
    });
    
   function submitAppointmentSession(){

		if($('#hospitalId').val()=="0"){
			alert("Please Select the Hospital");
			return false;
		}
		else if($('#appointmentTypeId').val()=="0"){
			alert("Please Select the Appointment Type");
			return false;
		}
		else if($('#departmentId').val()=="0" ){
			alert("Please Select the Department");
			return false;
		}
		
		else if($('#startTime').val()=="" ){
			alert("Please Emter the Start Time");
			return false;
		}
		
		else if($('#endTime').val()=="" ){
			alert("Please Enter the End Time");
			return false;
		}
		 var departmentId = $('#departmentId').find('option:selected').val();	 
		 var appointmentTypeId = $('#appointmentTypeId').find('option:selected').val();	 
		 var hospitalId = $('#hospitalId').find('option:selected').val();	 
		 var startTime = $('#startTime').val();
		 var endTime =  $('#endTime').val();
		 
		var params = {
			"departmentId":departmentId,
			"appointmentTypeId":appointmentTypeId,
			"hospitalId":hospitalId,
			"startTime":startTime,
			"endTime":endTime
		 } 	
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : 'submitappointmentsession',
				data : JSON.stringify(params),
				dataType : "json",
				cache : false,
				success : function(response) {
				console.log(response)
					
				},
				error : function(msg) {
					alert("An error has occurred while contacting the server");
				}
		    
		});	
	   
   } 
    
   function showResultPage(pageNo)
   {
   	
   	
   	nPageNo = pageNo;	
   	GetAppointmentSession('FILTER');
   	
   }
    </script>
</body>
</html>