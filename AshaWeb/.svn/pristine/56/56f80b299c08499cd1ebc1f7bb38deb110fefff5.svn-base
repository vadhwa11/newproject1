<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@include file="..//view/leftMenu.jsp" %>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.autocomplete.min.js" />"></script>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

  
<script type="text/javascript" language="javascript">


var $j = jQuery.noConflict();

$j(document).ready(function() {
	
	 $j('#icdname').autocomplete({
		 
		serviceUrl: '${pageContext.request.contextPath}/utility/getICDNameSearch',
		paramName: "icdName",
		type:'post',
		delimiter: ",",
	    transformResult: function(response) {
	    	
	        return {	        	
	            suggestions: $j.map($j.parseJSON(response), function(item) {
	            	
	                return { value: item.icdName, data: item.icdId };
	            })
	            
	        };
	         
	    }
	    
	});
	 
	 
	 $j('#icdname1').autocomplete({
		 
			serviceUrl: '${pageContext.request.contextPath}/utility/getICDNameSearch',
			paramName: "icdName",
			type:'post',
			delimiter: ",",
		    transformResult: function(response) {
		    	
		        return {	        	
		            suggestions: $j.map($j.parseJSON(response), function(item) {
		            	
		                return { value: item.icdName, data: item.icdId };
		            })
		            
		        };
		         
		    }
		    
		});
	
});

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
                        <div class="dropdown-menu dropdown-menu-animated dropdown-menu-right profile-dropdown ">
                            <!-- item-->
                            <div class="dropdown-item noti-title">
                                <h5 class="text-overflow"><small>Welcome ! Role Rghts</small> </h5>
                            </div>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-account-circle"></i> <span>Profile</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-settings"></i> <span>Settings</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-lock-open"></i> <span>Lock Screen</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-power"></i> <span>Logout</span>
                            </a>

                        </div>
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
        <!-- Top Bar End -->

        <!-- ========== Left Sidebar Start ========== -->
        
        <!-- Left Sidebar End -->

        <!-- ============================================================== -->
        <!-- Start right Content here -->
        <!-- ============================================================== -->
        <div class="content-page">
            <!-- Start content -->
            <div class="">
                <div class="container-fluid">
                <div class="internal_Htext">ICD</div>
                                                  
                    
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                  <form>
                                
                                  <div class="row">                                        
                                        
                                         <div class="col-md-8">
                                             ICD Name: <input id="icdname" type="text" value="" style='width:100%'>
                                              </div> 
                                           <div class="col-md-8">
                                             ICD Name: <input id="icdname1" type="text" value="" style='width:100%'>
                                              </div>     
                                                                           
 		         					 <div style="float:right">
					                     <div id="resultnavigation">
					                     </div> 
		                              </div>                                  
					   				<table class="table table-hover table-bordered">
                                        <thead class="bg-success" style="color:#fff;">                                           
                                        </thead>                                        
	                                     <tbody id="tblListOTemplate">											 
	                     				 </tbody>
                                    </table>                                 
                                                                  
      			                   </div>	                          

                                    <!-- end row -->
                                     </div>	 
                                    
                                    </form>
                                    
                                    

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

        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

    </div>
    <!-- END wrapper -->

    <!-- jQuery  -->
    
    ICD :<input id="field1" type="text" />
         
        
    
    

</body>

</html>