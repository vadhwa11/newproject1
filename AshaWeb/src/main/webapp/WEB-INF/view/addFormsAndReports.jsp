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
     
    
<%@include file="..//view/commonJavaScript.jsp" %>

<script type="text/javascript">
var $j = jQuery.noConflict();
$j(document).ready(function()
		{	
		
		GetApplicationAutoCompleteList();
			
		});


function GetApplicationAutoCompleteList(){
	/* var params = {
				'applicationName':'User'}  */
		
		$j.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getApplicationAutoComplete",
			data : JSON.stringify({}),
			dataType : "json",
			cache : false,
			success : function(result) {
				
				var maxAppId = result.max_app_id;
				var dataList = result.data;
				alert("dataList :: "+dataList);
				
				var maxId = parseInt(maxAppId.substring(1,5))+1;
				alert("maxAppId :: "+maxAppId);
				alert("maxId :: "+"A"+maxId);
				$j('#applicationId').val("A"+maxId);
				
				
				  for(i=0;i<dataList.length;i++){
					  alert("length:: "+dataList.length);
					  alert(dataList[0].applicationName)
					  $j("#applicationName").easyAutocomplete(dataList[i].applicationName);
					// $j('#applicationName').val(dataList[i].applicationName);
					
				}  
				//$j('#applicationName').val();
				//$j('#applicationUrl').val();
			console.log(result);
			}
		});
		
	}
	
function getAutoCompletion(length){
	
	alert(length);
}

</script>
</head>
<body>
    <!-- Begin page -->
    <div id="wrapper">
        <!-- Top Bar Start -->
        <div class="topbar">
            <!-- LOGO -->
           <%--  <div class="topbar-left">
                <a href="index.html" class="logo">
                    <span>
                            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="" height="18">
                        </span>
                    <i>
                            <img src="${pageContext.request.contextPath}/resources/images/logo_sm.png" alt="" height="22">
                        </i>
                </a>
            </div> --%>

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
                    <div class="row">
                        <div class="col-12">
                            <div class="page-title-box">
                                <h4 class="page-title float-left">ADD FORMS / REPORTS</h4>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                       <br>
                                    <div class="row">                                                                     
                                        <div class="col-md-8">
                                            <form class="form-horizontal" id="" name="" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Application Id</label>
                                                    <div class="col-3">
                                                      <input type="text" name="applicationId" id="applicationId" class="form-control" readonly="readonly">                                                            
                                                        
                                                    </div>
                                                     <label class="col-3 col-form-label inner_md_htext">Application Name</label>
                                                    <div class="col-3">
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
                                                      <input type="radio">
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
                                            <form>
                                                <div class="button-list">

                                                    <button type="button" id="btnAdd" class="btn btn-primary " onclick="addCmdDetails();">Add</button>

                                                </div>
                                            </form>
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