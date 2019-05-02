<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSessionListener"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>

<%@include file="..//view/leftMenu.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Indian Coast Guard</title>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <!-- App favicon -->
    <link rel="icon" type="images/favicon-32x32.png" sizes="32x32" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
   
    <script src="${pageContext.request.contextPath}/resources/js/canvasjs.min.js"></script>
	<%@include file="..//view/commonJavaScript.jsp"%>
</head>

<%
int hospitalId=0;
if(session.getAttribute("hospital_id")!=null)
{
hospitalId =(Integer)session.getAttribute("hospital_id"); 
}

int userId=0;
if(session.getAttribute("user_id")!=null)
{
	userId =(Integer)session.getAttribute("user_id"); 
}

SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 

Calendar c = Calendar.getInstance(); 
Date currentDate1 = c.getTime();
String currentDate=formatter.format(currentDate1); 

c.set(Calendar.DATE, 01);
Date startDate1 = c.getTime();
String startDate=formatter.format(startDate1); 
%>

<body>

    <!-- Begin page -->
    <div id="wrapper">

        <!-- Top Bar Start -->
        <div class="topbar">

            <!-- LOGO -->
            <div class="topbar-left">
                <a href="index.html" class="logo">
                    <span>
                        <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="" height="100%">
                    </span>
                </a>
            </div>

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
                                <h5 class="text-overflow"><small>Welcome ! Lorem Ipsum</small> </h5>
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
                                <h4 class="page-title float-left"></h4>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Dashboard</a></li>
                                   
                                </ol>

                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                    <!-- end row -->

                    <div class="row">
                        <div class="col-xl-3 col-md-6">
                            <div class="widget-panel widget-style-2 bg-pink">
                                <i class="ion-md-eye"></i>
                                <h2 class="m-0 text-white" data-plugin="counterup">225</h2>
                                <div>Today OPD</div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-md-6">
                            <div class="widget-panel widget-style-2 bg-purple">
                                <i class="ion-md-paper-plane"></i>
                                <h2 class="m-0 text-white" data-plugin="counterup">15</h2>
                                <div>Today Att'c</div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-md-6">
                            <div class="widget-panel widget-style-2 bg-info">
                                <i class="ion-ios-pricetag"></i>
                                <h2 class="m-0 text-white" data-plugin="counterup">25</h2>
                                <div>Today Medical Exam</div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-md-6">
                            <div class="widget-panel widget-style-2 bg-primary">
                                <i class="ion-md-contacts"></i>
                                <h2 class="m-0 text-white" data-plugin="counterup">30</h2>
                                <div>Today Medical Board</div>
                            </div>
                        </div>
                    </div>
                    <!-- end row -->

                    <div class="row dashboard_graph">
                      
							


<!-- <div class="row">

<div class="col-md-8"></div>

<div class="col-md-4">
<div class="form-group row">
<label class="col-sm-4 col-form-label">Gender</label>
<div class="col-sm-6">

</div>
</div>
</div>
</div> -->


<div class="col-md-12">
			<div class="row">
			<div class="col-md-4">
						  <div class="form-group row">
						          <label class="col-md-5">From Date</label>		  
								  <div class="col-md-6">
											<input  type="text" name="fromDate" id="fromDate" value="<%=currentDate%>" class="form-control">
										 
								</div>
						 </div>
				  </div>  
				  <div class="col-md-4">
						  <div class="form-group row">
						          <label class="col-md-4">To Date</label>		  
								  <div class="col-md-6">
										 <input  type="text" name="toDate" id="toDate" value="<%=startDate%>" class="form-control" >
								</div>
						 </div>
				  </div>
				   
			  
				  <div class="col-md-4">
						  <div class="form-group row">
						          <label class="col-md-4">Unit / Location</label>		  
								  <div class="col-md-6">
											<select class="form-control" id="unitId" name="unitId" onchange="birtReportOpen()">
											</select>
								</div>
						 </div>
				  </div>  
			</div>										

</div>
								  

			                      
			                     
			                      <div  style="height:20px;width:100%;border:none;"> </div>
			                      
			                      <div class="col-md-2"></div>
			                      <div class="col-md-8">
			                      		<div id="graphDiv"></div> 
			                      </div>
			                      <div class="col-md-2"></div> 
			</div>
						
						 
                    <!-- End row -->

                    <!-- end row -->
                    <!-- end row -->

                </div>
                <!-- container -->

            </div>
            <!-- content -->
            <script type="text/javascript">
										window.onload = function()
										{
										executeDbProcedure();
										
										
										}
										
									/* Execute Db Procedure */
										
										function executeDbProcedure()
										{
										
									
											var hospitalId=<%=hospitalId%>;
											var userId=<%=userId%>;
											var params = {
													"hospitalId":hospitalId,
													"userId":userId
								
											}
											$j.ajax({
												type : "POST",
												contentType : "application/json",
												url : '${pageContext.request.contextPath}/v0.1/dashboard/executeDbProcedure',
												data : JSON.stringify(params),
												dataType : "json",
												cache : false,
												success : function(response) {
													console.log(response)
													
													alert(response)
													if (response.status == '1') {
														birtReportOpen();
													}
													var unitValues="";
													 var respData = response.data 
													 for(count in respData){
														 unitValues += '<option value='+respData[count].hospitalId+'>'
																		+ respData[count].name
																		+ '</option>';
													 }
													 $j('#unitId').append(unitValues); 
												  	
														
												},
												error : function(msg) {
													alert("An error has occurred while contacting the server");
												}
											});
										}
										
											 
											
										
									/* Ended	 */
										
							function birtReportOpen()
							{
								 // var $j = jQuery.noConflict();
								  var fromDate=document.getElementById('fromDate').value;
								  var toDate=document.getElementById('toDate').value; 
								  var hospitalId=0;
								  var sHospitalId=<%=hospitalId%>;
								  if(document.getElementById('unitId').value){
								  hospitalId=document.getElementById('unitId').value;
								  if(hospitalId==sHospitalId)
									  var hospitalId=0;  
								  }
								  
								  var pathname = window.location.pathname;
							      var accessGroup = pathname.split("/")[1];
							
							      var url = window.location.protocol + "//" + window.location.host + "/" + accessGroup + "/v0.1/dashboard/openBirtReport"; 
								  
								 $j("#graphDiv").empty();
								  
							        $j.ajax(
							        {
							            
							             type: "POST",
							             contentType: "application/json",
							           	 url:url,
							             data: JSON.stringify({"fileName":"hospitalwise_opd_statistics","hospitalId":hospitalId,"fromDate":fromDate,"toDate":toDate}),
							             dataType: "html",
							            beforeSend : function()
							            {
							             
							            },
							            success : function(data)
							            {
							            	
							                var dom = $j.parseHTML(data);
							               
							                $j("#graphDiv").append(dom);
							                
							              
							            }
							        });
								  }
							</script>     
            
            
            
            

            <footer class="footer">
                Dashboard
            </footer>

        </div>

        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

    </div>
    <!-- END wrapper -->

    <!-- jQuery  -->
    <!-- <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/metisMenu.min.js"></script>
    <script src="js/waves.js"></script>
    <script src="js/jquery.slimscroll.js"></script>

    <script src="js/jquery.scrollTo.min.js"></script>

    Counter js 
    <script src="../plugins/waypoints/jquery.waypoints.min.js"></script>
    <script src="../plugins/counterup/jquery.counterup.min.js"></script>

    sparkline
    <script src="../plugins/jquery-sparkline/jquery.sparkline.min.js"></script>

    sweet alerts
    <script src="../plugins/sweet-alert2/sweetalert2.min.js"></script>

    Morris Chart
    <script src="../plugins/morris/morris.min.js"></script>
    <script src="../plugins/raphael/raphael.min.js"></script>

    Chat
    <script src="../plugins/moment/moment.js"></script>
    <script src="../pages/jquery.chat.js"></script>

    Dashboard
    <script src="../pages/jquery.dashboard.js"></script>

    Todoapp
    <script src="../pages/jquery.todo.js"></script>

    App js
    <script src="js/jquery.core.js"></script>
    <script src="js/jquery.app.js"></script> -->

</body>

</html>