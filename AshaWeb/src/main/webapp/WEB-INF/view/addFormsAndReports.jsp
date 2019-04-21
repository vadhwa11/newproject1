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
		GetApplicationAutoCompleteList();
		GetParentId();
		});


var applicationArry = new Array();
var appId;
var dataList;
var Id='';
var appUrl='';
var applicationId;
var appNameList='';
var applicationName='';
function GetApplicationAutoCompleteList(){
		$j.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getApplicationAutoComplete",
			data : JSON.stringify({}),
			dataType : "json",
			cache : false,
			success : function(result) {				
				var maxAppId = result.max_app_id;
				 dataList = result.data;	
				 if(maxAppId !=undefined)
					 {
					 	var maxId = parseInt(maxAppId.substring(1,maxAppId.length))+1;				
						$j('#applicationId').val("A"+maxId);	
					 }
				 else
					 {
						 $j('#applicationId').val("A1");
					 }
											
				  for(i=0;i<dataList.length;i++){
					  appId = dataList[i].id;					  
					  var status = dataList[i].status;
					  if(status == 'y' || status == 'Y'){
					//Id = parseInt(appId.substring(1,appId.length));					  
					  appNameList = dataList[i].appName;
					  var url = dataList[i].url;
					  var applicationName = appNameList+"["+appId+"]";					  
					  applicationArry.push(applicationName);
					  }
				}  
				  autocomplete(document.getElementById("applicationName"), applicationArry);  
					
			}
		});
		
	}
	
	 function fillDataUrl(value){    	   	  
    	  for(var i=0;i<dataList.length;i++){
    		  var applicationId1 = dataList[i].id;    		  
    		  //Id = parseInt(applicationId1.substring(1,applicationId1.length));    		  
    		  if(applicationId1 == applicationId){    			 
    			  appUrl = dataList[i].url;    			  
    		  }
    	  }    	  
    	 $j('#applicationUrl').val(appUrl);    	  
      }
	
function changeApplication(value){	
	var index1 = value.lastIndexOf("[");
    var index2 = value.lastIndexOf("]");
    index1++;
    applicationId = value.substring(index1, index2);
                
}

var appName='';
var appPId='';
var appArray = new Array();
function GetParentId(){
	
	$j.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getApplicationAutoComplete",
		data : JSON.stringify({}),
		dataType : "json",
		cache : false,
		success : function(result) {			
			var dataList = result.listObjModule;
			for(var i=0;i<dataList.length;i++){
				 appPId = dataList[i].applicationId;
				 //alert("appPId :: "+appPId);
				 var appName1 = dataList[i].applicationName;
				 appName =  appName1+"["+appPId+"]";
				 //alert(appName);
				 appArray.push(appName);
			}
			 autocomplete(document.getElementById("parentId"), appArray);
		}
	})
}

function addFormAndReportsDetails(){
	
	if(document.getElementById('applicationName').value==""){
		alert("Please Enter the Application Name");
		return false;
	}
	if(document.getElementById('parentId').value==""){
		alert("Please Enter the ParentId");
		return false;
	}
	if(document.getElementById('applicationUrl').value==""){
		alert("Please Enter the Url");
		return false;
	}
	
	 var url = $j('#applicationUrl').val();
	 var parentidd = document.getElementById('parentId').value;
	 //alert("appPId :: "+appPId);
	 //alert("parentidd :: "+parentidd);
	params = {"applicationId":$j('#applicationId').val(),
			"applicationName":$j('#applicationName').val(),
			"parentId":parentidd,
			"url":url
			}
	//alert(JSON.stringify(params));
	/* var url="addFormAndReports";
	SendJsonData(url,params);  */
	  $j.ajax({
		type : "POST",
		contentType : "application/json",
		url : "addFormAndReports",
		data : JSON.stringify(params),
		dataType : "json",
		cache : false,
		success : function(result){
			if(result.status==1){	
	        	document.getElementById("messageId").innerHTML = result.msg;
	    		$j("#messageId").css("color", "green");
	    		
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
	
	//window.location.reload();
}
 function editFormAndReports(){
	document.formsAndReportForm.name='formsAndReportForm';
	document.formsAndReportForm.method='POST';
	document.formsAndReportForm.action="${pageContext.request.contextPath}/user/editFormsAndReport";
	document.formsAndReportForm.submit(); 
} 

function ResetForm()
{	
	$j('#parentId').val('');
	$j('#applicationName').val('');
	$j('#applicationUrl').val('');
	
}


</script>



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
                <div class="internal_Htext">ADD FORMS / REPORTS</div>
                    <div class="row">
                    </div>                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                  <br>
                                    <div class="row">                                                                     
                                        <div class="col-md-8">
                                            <form class="form-horizontal" id="formsAndReportForm" name="formsAndReportForm">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Application Id</label>
                                                    <div class="col-3">
                                                      <input type="text" name="applicationId" id="applicationId" class="form-control" readonly="readonly">                                                            
                                                        
                                                    </div>
                                                     <label class="col-3 col-form-label inner_md_htext">Application Name</label>
                                                    <div class="autocomplete"">
                                                      <input type="text" name="applicationName" id="applicationName" class="form-control" onblur="changeApplication(this.value);fillDataUrl(this);">
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
                                                      <input type="radio" checked="checked">
                                                      <label class="col-form-label inner_md_htext">InActive</label>
                                                      <input type="radio">
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
                                                    <button type="button" id="btnAdd" class="btn btn-primary " onclick="addFormAndReportsDetails();">Add</button>
                                                    <button type="submit" id="btnEdit" class="btn btn-primary" onclick="editFormAndReports();">Edit</button>
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