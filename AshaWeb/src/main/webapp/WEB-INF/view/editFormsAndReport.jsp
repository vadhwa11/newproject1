<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@include file="..//view/leftMenu.jsp" %>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Indian Coast Guard</title>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
     
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/newOpd.js"></script>    
<%@include file="..//view/commonJavaScript.jsp" %>

<script type="text/javascript">
var $j = jQuery.noConflict();
$j(document).ready(function()
		{		
		GetApplicationNameAutoCompleteList();
		});
	
var applicationArry = new Array();
var dataList;
var appId;
var appUrl;
var applicationName;
var parentId;
var applicationStatus;
function GetApplicationNameAutoCompleteList(){
	$j.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getApplicationNameFormsAndReport",
		data : JSON.stringify({}),
		dataType : "json",
		
		success : function(result) {				
			 dataList = result.data;	
			  for(i=0;i<dataList.length;i++){
				  appId = dataList[i].applicationId;	
				  applicationStatus = dataList[i].applicationStatus;				 
				  appUrl = dataList[i].applicationUrl;
				  applicationName = dataList[i].applicationName;				  
				  parentId = dataList[i].parentId;				 								  
				  applicationArry.push(applicationName);
				  
			}  
			  autocomplete(document.getElementById("searchApplicationName"), applicationArry);  
				
		}
	});
}
var	appStatus='';
function searchApplication(){
	if(document.getElementById('searchApplicationName').value ==""){
		alert("Please Enter the Application Name");
		return false;
	}
	var applicationNameVal = $j('#searchApplicationName').val();
	
	$j.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getApplicationNameFormsAndReport",
		data : JSON.stringify({}),
		dataType : "json",
		
		success : function(result) {				
			var dataList1 = result.data;	
			
			  for(i=0;i<dataList1.length;i++){
				  	
				  if(dataList1[i].applicationName == applicationNameVal){
					  
						
						$j('#applicationId').val(dataList1[i].applicationId);
						$j('#applicationName').val(dataList1[i].applicationName);
						$j('#parentId').val(dataList1[i].parentId);
						$j('#applicationUrl').val(dataList1[i].applicationUrl);
						appStatus = dataList1[i].applicationStatus;
						  //alert("appStatus :: "+appStatus);
					}
						
			  }
			 }
	})
	
}

function updateFormAndReportsDetails(){
	if($('#applicationId').val()==""){
		alert("Please Enter the Application Id");
		return false;
	}
	
	if($('#applicationName').val()==""){
		alert("Please Enter the Application Name");
		return false;
	}
	if($('#parentId').val()==""){
		alert("Please Enter the Parent Id");
		return false;
	}
	if($('#applicationUrl').val()==""){
		alert("Please Enter the Url");
		return false;
	}
	//alert("status ::"+appStatus);
	var params = {
			
		    'applicationId': $('#applicationId').val(),
		    'applicationName': $('#applicationName').val(),
		    'parentId': $('#parentId').val(),
		    'applicationUrl': $('#applicationUrl').val(),
		    'applicationStatus': appStatus
	}
	//alert(JSON.stringify(params));
	 $j.ajax({
			type : "POST",
			contentType : "application/json",
			url : "updateAddFormsAndReport",
			data : JSON.stringify(params),
			dataType : "json",
			cache : false,
			success : function(result){
				if(result.status==1){	
		        	document.getElementById("messageId").innerHTML = result.msg;
		        	$j('#messageId').toggle(5000); 
		    		
		        }	        
		    	else if(result.status==0){	        	
		        	if(result.msg != undefined){
			        		document.getElementById("messageId").innerHTML = result.msg;
			        		$j("#messageId").css("color", "red");			        	
		        		}
		        	if(result.err_mssg != undefined){
		        		document.getElementById("messageId").innerHTML = result.err_mssg;
		        		$j("#messageId").css("color", "red");		        	
	        		}	        	
		        }
		    	else{	        	
		        	if(result.msg != undefined){
			        		document.getElementById("messageId").innerHTML = result.msg;
			        		$j("#messageId").css("color", "red");			        	
		        		}
		        	if(result.err_mssg != undefined){
		        		document.getElementById("messageId").innerHTML = result.err_mssg;
		        		$j("#messageId").css("color", "red");		        	
	        		}	        	
		        }
		    },
			error: function(result){			
				alert("An error has occurred while contacting the server"+ result);			
			   }
			
		})
		ResetForm();
	 
}

function backToaddFormAndReports(){
	
	window.location.href = "${pageContext.request.contextPath}/user/addFormsAndReports";
	/* document.editformsAndReportForm.name='editformsAndReportForm';
	document.editformsAndReportForm.method='POST',
	document.editformsAndReportForm.action="${pageContext.request.contextPath}/user/addFormsAndReports";
	document.editformsAndReportForm.submit(); */
	
}

function ResetForm()
{	
	$j('#parentId').val('');
	$j('#applicationName').val('');
	$j('#applicationUrl').val('');
	$j('#searchApplicationName').val('');
	$j('#applicationId').val('');
	
	
	
}
</script>
<!-- <script type="text/javascript">
     var auto = setInterval(    function ()
     {
          $j('#cardBodyId').load('editFormsAndReport.jsp').fadeIn("slow");
     }, 2000); // refresh every 5000 milliseconds
</script> -->
</head>
<body>
    <!-- Begin page -->
    <div id="wrapper">
        <!-- Top Bar Start -->
        <div class="topbar">
             <nav class="navbar-custom">
                <ul class="list-inline float-right mb-0">                
                    <li class="list-inline-item dropdown notification-list">
                        <a class="nav-link dropdown-toggle nav-user" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                            <i class="noti-icon"><img src="${pageContext.request.contextPath}/resources/images/users/avatar-1.jpg" alt="user" class="img-fluid rounded-circle"></i>
                            <span class="profile-username ml-2 text-dark">Username </span> <span class="mdi mdi-menu-down text-dark"></span>
                        </a>                        
                    </li>
                </ul>
                <ul class="list-inline menu-left mb-0">
                    <li class="float-left">
                        <button class="button-menu-mobile open-left waves-light waves-effect">
                            <i class="mdi mdi-menu"></i>
                        </button>
                    </li>                   
                </ul>
            </nav>
        </div>
       
        <!-- ============================================================== -->
        <!-- Start right Content here -->
        <!-- ============================================================== -->
        <div class="content-page">
            <!-- Start content -->
            <div class="">
                <div class="container-fluid">
                <div class="internal_Htext">EDIT FORMS / REPORTS</div>
                    <div class="row">
                    </div>                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body" id="cardBodyId">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                  <br>
                                  <div class="form-group row">
                                   <label class="col-2 col-form-label inner_md_htext">Application Name</label>
                                    <div class="autocomplete">
                                    <input type="text" name="searchApplicationName" id="searchApplicationName" class="form-control">
                                    </div>
                                     <div class="col-3">
                                    <button id="searchApplication" name="searchApplication" class="btn btn-primary" onclick="searchApplication();">Search</button>
                                    </div>
                                    </div>
                                    <div class="row">                                                                     
                                        <div class="col-md-8">
                                            <form class="form-horizontal" id="editformsAndReportForm" name="editformsAndReportForm">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Application Id</label>
                                                    <div class="col-3">
                                                      <input type="text" name="applicationId" id="applicationId" class="form-control" readonly="readonly">                                                            
                                                        
                                                    </div>
                                                     <label class="col-3 col-form-label inner_md_htext">Application Name</label>
                                                    <div class="autocomplete"">
                                                      <input type="text" name="applicationName" id="applicationName" class="form-control">
                                                    </div>
                                                    
                                                </div>
                                                <div class="form-group row">
                                                <label class="col-3 col-form-label inner_md_htext">Parent Id</label>
                                                    <div class="col-3">
                                                      <input type="text" name="parentId" id="parentId" class="form-control" placeholder="">                                                            
                                                        
                                                    </div>
                                                     <label class="col-3 col-form-label inner_md_htext">Sub Parent Id</label>
                                                    <div class="col-3">
                                                      <select class="form-control" name="subparentId" id="subparentId">
                                                      
                                                      </select>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                <label class="col-3 col-form-label inner_md_htext">Url</label>
                                                    <div class="col-3">
                                                      <input type="text" name="applicationUrl" id="applicationUrl" class="form-control" placeholder="">                                                            
                                                        
                                                    </div>
                                                     <label class="col-3 col-form-label inner_md_htext">Status</label>
                                                    <div class="col-3">
                                                    <label class="col-form-label inner_md_htext">Active</label>
                                                      <input type="radio" checked="checked" name="rd">
                                                      <label class="col-form-label inner_md_htext">Inactive</label>
                                                      <input type="radio" name="rd">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
									<br>	
                                    <div class="row">
                                        <div class="col-md-7">
                                        </div>
                                        <div class="col-md-5">
                                            
                                                <div class="button-list">
                                                    <button type="button" id="btnUpdate" class="btn btn-primary" onclick="updateFormAndReportsDetails();">Update</button>
                                                    <button type="submit" id="btnBack" class="btn btn-primary" onclick="backToaddFormAndReports();">Back</button>
                                                </div>
                                           
                                        </div>

                                    </div>

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
            <!-- content -->

            <footer class="footer">
                
            </footer>

        </div>

        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

    </div>
    <!-- END wrapper -->

    <!-- jQuery  -->
    

</body>

</html>