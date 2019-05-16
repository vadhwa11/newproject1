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
    <title>Indian Cost Guard</title>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    
<%--     <link href="${pageContext.request.contextPath}/resources/css/sweetalert2.min.css" rel="stylesheet"> --%>  
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/metismenu.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/app.css" rel="stylesheet" type="text/css" />    
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
    
    <script src="${pageContext.request.contextPath}/resources/js/modernizr.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/metisMenu.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/waves.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.scrollTo.min.js"></script>    
	 <script src="${pageContext.request.contextPath}/resources/js/jquery.core.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.app.js"></script>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>

<script>


<%			
String hospitalId = "1";
if (session.getAttribute("hospital_id") !=null)
{
	hospitalId = session.getAttribute("hospital_id")+"";
}
String userId = "1";
if (session.getAttribute("user_id") != null) {
	userId = session.getAttribute("user_id") + "";
}
%>
var $j = jQuery.noConflict();
        $(document).ready(function() {   	

        	makeCombo();
        	getDisposalDetail();
        	getAppointmentTypeList();
           
      	
        });
        
       
        function makeCombo() {

    		var params = {
    			"hospitalID" : "<%= hospitalId %>"
    		}

    		$j.ajax({
    					type : "POST",
    					contentType : "application/json",
    					url : '${pageContext.request.contextPath}/admin/getDepartmentList',
    					data : JSON.stringify(params),
    					dataType : "json",
    					cache : false,
    					success : function(msg) {
    						if (msg.status == '1') {

    							var comboval = "<option value='0'>Select</option>";
    							for (var i = 0; i < msg.departmentList.length; i++) {

    								comboval += '<option value=' + msg.departmentList[i].departmentId + '>'
    										+ msg.departmentList[i].departmentname
    										+ '</option>';

    							}
    							$j('#department').append(comboval);

    						}

    					},

    					error : function(msg) {

    						alert("An error has occurred while contacting the server");

    					}
    				});
    	}
        
 
        function getDisposalDetail() {

       		var pathname = window.location.pathname;
        	var accessGroup = pathname.split("/")[1];
        	var url = window.location.protocol + "//"
			+ window.location.host + "/" + accessGroup
			+ "/opd/getMasDisposalList";
        	
        	
        	
        	$j.ajax({
        				url : url,
        				dataType : "json",
        				data : JSON.stringify({
        					'employeeId' : <%= userId %>
        				}),
        				contentType : "application/json",
        				type : "POST",
        				success : function(response) {
        					console.log(response);
        					var datas = response.MasDisposal;
        					var trHTML = "<option value='0'>Select</option>";
        					$j.each(datas, function(i, item) {
        						trHTML += '<option value="' + item.disposalId + '" >'
        								+ item.disposalName + '</option>';
        					});

        					$j('#disposalId').html(trHTML);

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
        function getAppointmentTypeList() {

        	jQuery.ajax({
        	 	crossOrigin: true,
        	    method: "POST",			    
        	    crossDomain:true,
        	    url: "getAppointmentTypeList",
        	    data: JSON.stringify({}),
        	    contentType: "application/json; charset=utf-8",
        	    dataType: "json",
        	    success: function(result){
        	    	
        	    	var combo = "<option value='0'>Select</option>" ;
        	    	
        	    	for(var i=0;i<result.data.length;i++){	    		
        	    		combo += '<option value='+result.data[i].id+'>' +result.data[i].appointmentType+ '</option>';    		
        	    		
        	    	}
        	    	jQuery('#appointmentTypeId').append(combo);
        	    }
        	    
        	});
    	}
        
        
        function generateReport()
        {
        	  if($j("#fromdate").val() == "")
        	  {
        		  	alert("Please enter from date");
        		  	retun ;
        		  	
        		  	
        	  }
        	  
        	  if($j("#todate").val() == "")
        	  {
        		  	alert("Please enter to date");
        		  	retun ;
        		  	
        		  	
        	  }
        		document.frm.action="${pageContext.request.contextPath}/report/printMIReport";
        		document.frm.method="POST";
        		document.frm.submit(); 
        }
        
    </script>  

</head>

<body>

    <!-- Begin page -->
    <div id="wrapper">

        <!-- Top Bar Start -->
        <div class="topbar">

            <!-- LOGO -->
            <div class="topbar-left">
               <%--  <a href="index.html" class="logo">
                    <span>
                            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="" height="18">
                        </span>
                    <i>
                            <img src="${pageContext.request.contextPath}/resources/images/logo_sm.png" alt="" height="22">
                        </i>
                </a> --%>
            </div>

            <nav class="navbar-custom">
               

                <ul class="list-inline menu-left mb-0">
                    <li class="float-left">
                        <button class="button-menu-mobile open-left waves-light waves-effect">
                            <i class="mdi mdi-menu"></i>
                        </button>
                    </li>
                    <!-- <li class="hide-phone app-search"> -->
                        <!-- <form role="search" class=""> -->
                            <!-- <input type="text" placeholder="Search..." class="form-control"> -->
                            <!-- <a href="#"><i class="fa fa-search"></i></a> -->
                        <!-- </form> -->
                    <!-- </li> -->
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
                    
                    
                    <div class="internal_Htext">Generate MI Appointment Report</div>
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                               
        
						              <form name="frm">						                          
						                    <div class="row">
							                        <div class="col-md-4">
							                            <div class="form-group row">
							                                <label class="col-md-5 col-form-label">From Date<span style="color:red"><sup>&#9733;</sup></span></label>
							                                <div class="col-md-7">
							                                    <input type="date" class="form-control" id="fromdate" name="fromdate" placeholder="frmDate">
							                                </div>							                                
							                            </div>
							                        </div>
						                    
								                    <div class="col-md-4">
								                            <div class="form-group row">
								                                <label class="col-md-5 col-form-label">To Date<span style="color:red"><sup>&#9733;</sup></span></label>
								                                <div class="col-md-7">
								                                    <input type="date" class="form-control" id="todate" name="todate" placeholder="toDate">
								                                </div>								                                
								                            </div>
								                      </div>
						                                              
						                       
							                         <div class="col-md-4">
							                            <div class="form-group row">							                                
							                                <label class="col-md-5 col-form-label">Appointment Type</label>
							                                <div class="col-md-7">
							                                    
							                                    <select class="form-control" id="appointmentTypeId" name="appointmentTypeId">												  
													</select>
							                                </div>
							                            </div>
							                        </div>
						                        
							                        <div class="col-md-4">
							                            <div class="form-group row">							                                 
							                                <label class="col-md-5 col-form-label">Disposal Type</label>
							                                <div class="col-md-7">
							                                    <select name="disposalId" id="disposalId" class="medium form-control"></select>																</select> 
							                                </div>
							                                
							                                
							                            </div>
							                        </div>
							                        <div class="col-md-4">
							                            <div class="form-group row">
							                                <label class="col-md-5 col-form-label">Department</label>
							                                <div class="col-md-7">
							                                    <select class="form-control" id="department" name="department"></select>
							                                </div>
							                                 
							
							                            </div>
							                        </div> 
							                           
								            </div>								            
								             <div class="clearfix"></div>								
								        
								            <div class="row">
	            
								                    <div class="col-md-3">
							                            <div class="form-group row">
							                            <div class="col-md-12">
							                                <div class="form-check form-check-inline">
							                                   <label class="form-check-label">Total Count &nbsp &nbsp &nbsp &nbsp</label>
																<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="C">																
														    </div> 
														    
														    
														    <div class="form-check form-check-inline">
							                                   <label class="form-check-label">Detailed &nbsp &nbsp &nbsp &nbsp</label>
																<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="D" checked>																
														    </div> 
												       </div>						                                
							                            </div>
							                        </div> 
							                        
							                       <div class="col-md-2">
							                          
							                          <button type="button" 
															class="btn btn-primary reception_mi_reports" onclick="generateReport();"> Generate Report</button>
							                            
							                        </div> 
							                         
							                        
							                           <div class="col-md-7">
							                            
							                           </div> 
							                        
								            </div>
								            
								            
								        </form>

                   
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
 
        </div>

        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

     
    <!-- END wrapper -->

    <!-- jQuery  -->
    

</body>

</html>