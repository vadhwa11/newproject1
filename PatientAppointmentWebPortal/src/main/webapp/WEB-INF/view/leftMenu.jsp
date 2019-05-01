<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Indian Coast Guard</title>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    
        
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/metismenu.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/app.css" rel="stylesheet" type="text/css" />
   	<%-- <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" /> --%>
    
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/metisMenu.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/waves.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.scrollTo.min.js"></script>    
    <script src="${pageContext.request.contextPath}/resources/js/jquery.core.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.app.js"></script>    
    <script src="${pageContext.request.contextPath}/resources/js/modernizr.min.js"></script>
    
   

    
</head>

<body>

    <!-- Begin page -->
    <div id="wrapper">

        <!-- Top Bar Start -->
        <div class="topbar">

            <!-- LOGO -->
            <div class="topbar-left">
                <a href="#" class="logo">
                    <span>
                            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="" height="18">
                        </span>
                    <i>
                            <img src="${pageContext.request.contextPath}/resources/images/logo_sm.png" alt="" height="22">
                        </i>
                </a>
            </div>

            <nav class="navbar-custom">
                <ul class="list-inline float-right mb-0">
                    
                   

                    <li class="list-inline-item dropdown notification-list">
                       <%--  <a class="nav-link dropdown-toggle nav-user" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                            <i class="noti-icon"><img src="${pageContext.request.contextPath}/resources/images/users/avatar-1.jpg" alt="user" class="img-fluid rounded-circle"></i>
                            <span class="profile-username ml-2 text-dark">Username </span> <span class="mdi mdi-menu-down text-dark"></span>
                        </a> --%>
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
        <div class="left side-menu">
            <div class="slimscroll-menu" id="remove-scroll">
                <!--- Sidemenu -->
                <div id="sidebar-menu">
                    <!-- Left Menu Start -->
                    <ul class="metismenu" id="side-menu">
                        <li class="menu-title">Menu</li>
                        <li>						
                            <a href="javascript: void(0);"><i class="ion-md-home"></i> <span>Personal Information</span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded=false>
                               <%--  <li><a href="${pageContext.request.contextPath}/appointment/showappointmentsetup">Appointment Setup</a></li>
                                <li><a href="${pageContext.request.contextPath}/appointment/showappointmentsession">Appointment Session</a></li>
								<li><a href="${pageContext.request.contextPath}/registration/showemployeeanddependent">Registration and Appointment ICG</a></li>
								<li><a href="${pageContext.request.contextPath}/registration/registrationandappointmentothers">Registration and Appointment Others</a></li> --%>
							 <li><a href="${pageContext.request.contextPath}/appointment/getServiceDetails?serviceNumber=${serviceNumber}">Personal Information </a></li> 
								
                            </ul>
                        </li>
                        <li>
                            <a href="javascript: void(0);"><i class="ion-ios-list"></i>
                                <span>Appointment History</span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                               <%--  
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/preOpdWaitingList">Pre-Consultation Waiting List</a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/opdWaitingList">OPD Waiting List</a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/obesityWaitingJsp">Obesity Waiting List</a></li>    
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/referralWaitingList">Referral Waiting List</a></li>   
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/admissionDischargePending">Pending Admission List</a></li>                                  
                                <li><a href="#">Reports</a></li>
                                <li><a href="#">Reports Case stylesheet</a></li>
                                <li><a href="#">OP Nursing Care</a></li> --%>
                                 <li><a href="${pageContext.request.contextPath}/appointment/getAppointmentHistory?serviceNo=${serviceNumber}">Appointment History</a></li> 
							
                                
                            </ul>
                        </li>

						 <li>
                            <a href="javascript: void(0);"><i class="ion-ios-list"></i><span> OPD History</span> <span class="menu-arrow"></span></a>
                             <ul class="nav-second-level" aria-expanded="false">
                            <li> <a href="${pageContext.request.contextPath}/appointment/getOpdHistory?serviceNo=${serviceNumber} ">OPD History</a></li>
													
                                 </ul>
                             <!--
                                <!--<li><a href="#">Department Return </a></li>
                                <li><a href="#">Opening Balance Entry </a></li>
                                <li><a href="#">Pending Prescription </a></li>
                                <li><a href="#">MR Receive </a></li>
                                <li><a href="#">Reports </a></li> 
                                     
                                ${pageContext.request.contextPath}/appointment/getOpdHistory?serviceNo=${serviceNumber}                     
                            </ul> -->
                            
                        </li>
                         <li>
                            <a href="javascript: void(0);"><i class="ion-ios-list"></i><span> ME History </span> <span class="menu-arrow"></span></a>
                           <!--  <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Department Return </a></li>
                                <li><a href="#">Opening Balance Entry </a></li>
                                <li><a href="#">Pending Prescription </a></li>
                                <li><a href="#">MR Receive </a></li>
                                <li><a href="#">Reports </a></li>                           
                            </ul> -->
                            
                        </li>
                        
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-mail"></i>  <span> MB </span><span class="menu-arrow"></span></a>
                           <!--  <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Inbox</a></li>
                                <li><a href="#">Compose Mail</a></li>
                                <li><a href="#">View Mail</a></li>
                                <li><a href="#">Email Templates</a></li>
                            </ul> -->
                        </li>
						
						<!-- <li>
                            <a href="javascript: void(0);"><i class="ion-md-map"></i> <span> Radiology </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#"> Google Map</a></li>
                                <li><a href="#"> Vector Map</a></li>
                            </ul>
                        </li> -->
                        
                       <!--  <li>
                            <a href="javascript: void(0);"><i class="ion-ios-list"></i><span> Dispensary </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Department Return </a></li>
                                <li><a href="#">Opening Balance Entry </a></li>
                                <li><a href="#">Pending Prescription </a></li>
                                <li><a href="#">MR Receive </a></li>
                                <li><a href="#">Reports </a></li>
                            </ul>
                        </li>
 -->
                       <!--  <li>
                            <a href="javascript: void(0);"><i class="ion-md-pie"></i><span> Stores </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Morris Chart</a></li>
                                    <li><a href="#">Chartjs</a></li>
                                    <li><a href="#">Flot Chart</a></li>
                                    <li><a href="#">Rickshaw Chart</a></li>
                                    <li><a href="#">Peity Chart</a></li>
                                    <li><a href="#">C3 Chart</a></li>
                                    <li><a href="#">Other Chart</a></li>
                            </ul>
                        </li> -->

                       <!--  <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Ward </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Nursing Station    </a></li>
                                <li><a href="#">Reports </a></li>
                                <li><a href="#">IP Admission </a></li>
                           </ul>
                        </li> -->

 						<!-- <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> MI Administration </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Nursing Station    </a></li>
                                <li><a href="#">Reports </a></li>
                                <li><a href="#">IP Admission </a></li>
                           </ul>
                        </li> -->
                        
                       <!--  <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> FWC </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Nursing Station    </a></li>
                                <li><a href="#">Reports </a></li>
                                <li><a href="#">IP Admission </a></li>
                           </ul>
                        </li> -->
                      <!--   
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> SHO </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Nursing Station    </a></li>
                                <li><a href="#">Reports </a></li>
                                <li><a href="#">IP Admission </a></li>
                           </ul>
                        </li> -->
                        
                       <%--  <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Masters </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/commandMaster">Command Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/unitMaster">Unit Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/hospitalMaster">Hospital Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/departmentMaster">Department Master</a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/relationMaster">Relation Master </a></li>
                                 <li><a href="${pageContext.request.contextPath}/v0.1/master/rankMaster">Rank Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/tradeMaster">Trade Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/religionMaster">Religion Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/maritalStatusMaster">Marital Status Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/employeeCategoryMaster">Employee Category Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/genderMaster">Gender Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/medicalCategoryMaster">Medical Category Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/bloodGroupMaster">Blood Group Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/sampleContainerMaster">Sample Container Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/uomMaster">Unit of Measurement Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/disposalMaster">Disposal Type Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/idealWeightMaster">Ideal Weight Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/frequencyMaster">Frequency Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/appointmentTypeMaster">Appointment Type </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/empanelledHospitalMaster">Empanelled Hospital Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/immunizationMaster">Immunization Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/physiotherapyMaster">Physiotherapy Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/investigationMaster">Investigation Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/subInvestigationMaster">Sub-Investigation Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/itemMaster">Item Master ( PVMS/NIV)</a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/nurshingCareMaster">Nursing Care </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/ICDMaster">ICD Master</a></li>
                            </ul>
                        </li>
                         --%>
                        <%-- <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> ADMIN </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="${pageContext.request.contextPath}/admin/doctorRoaster">Doctor Roaster</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/manageUserApplication">Manage User Application</a></li>
                                <li><a href="#">User Application </a></li>
                                <li><a href="#">Template </a></li>
                                <li><a href="#">Assign Application to Template </a></li>
                                <li><a href="#">Security Reports </a></li>
                                <li><a href="#">User Rights </a></li>
                                <li><a href="#">Add Forms/Reports </a></li>
                                <li><a href="#">User/Employee Department </a></li>
                                <li><a href="#">Create User </a></li>
                                <li><a href="#">Reports </a></li>                                
                            </ul>
                        </li> --%>
                        
                        <%-- <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> OT</span> <span class="menu-arrow"></span></a>

                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#"> PAC Clearance List</a></li>
                                <li><a href="#">OT Booking List </a></li>
                                <li><a href="#">Pre Op Surgery </a></li>
                                <li><a href="#">Post Anaesthesia </a></li>
                                <li><a href="#">Post bOP Notes(Surgery) </a></li>
                                <li><a href="#">OT Reports </a></li>
                                <li><a href="#">Surgery Enquiry</a></li>
                                <li><a href="#">Surgery Status </a></li>
                                <li><a href="#">Surgery Wait list-group-lg </a></li>
                                <li><a href="#">OT Dashboard </a></li>
                                <li><a href="#">Pre Anesthesia </a></li>
                                
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Labor Room</span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Transfer Patient </a></li>
                                <li><a href="#">Labor Room Waiting List </a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Facility Mgmt </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Equipment Detail </a></li>
                                <li><a href="#">Request </a></li>
                                <li><a href="#">Approval </a></li>
                                <li><a href="#">Service </a></li>
                                <li><a href="#">Preventive </a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Referral </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Waiting List of Referral </a></li>
                                <li><a href="#">Submission of Invoice for Division </a></li>
                                <li><a href="#">Approval of HR Division </a></li>
                                <li><a href="#">Consolidations of Bill by Bills Payable </a></li>
                                <li><a href="#">Invoice Status </a></li>
                                <li><a href="#">Waiting List of Extension </a></li>
                                
                            </ul>

                        </li>
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Billing </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">IP Billing </a></li>
                                <li><a href="#">Billing of Other Patient </a></li>
                                <li><a href="#">Billing for Investigations </a></li>
                            </ul>
                        </li>
                        
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Admin </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="${pageContext.request.contextPath}/admin/doctorRoaster">Doctor Roaster</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/manageUserApplication">Manage User Application</a></li>
                                <li><a href="#">User Application </a></li>
                                <li><a href="#">Template </a></li>
                                <li><a href="#">Assign Application to Template </a></li>
                                <li><a href="#">Security Reports </a></li>
                                <li><a href="#">User Rights </a></li>
                                <li><a href="#">Add Forms/Reports </a></li>
                                <li><a href="#">User/Employee Department </a></li>
                                <li><a href="#">Create User </a></li>
                                <li><a href="#">Reports </a></li>
                                
                            </ul>

                        </li>
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Ward Pharmacy </span> <span class="menu-arrow"></span></a>
                        </li>
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Accounts </span> <span class="menu-arrow"></span></a>
                        </li>
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Payroll </span> <span class="menu-arrow"></span></a>
                        </li> --%>

                    </ul>

                </div>
                <!-- Sidebar -->
                <div class="clearfix"></div>

            </div>
            <!-- Sidebar -left -->

        </div>
        <!-- Left Sidebar End -->

        <!-- ============================================================== -->
        <!-- Start right Content here -->
        <!-- ============================================================== -->
        <div class="content-page">
            <!-- Start content -->
            <div class="content">
                <div class="container-fluid">

                    <!-- <div class="row">
                        <div class="col-12">
                            <div class="page-title-box">
                                <h4 class="page-title float-left">Welcome ! Lorem Ipsum</h4>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                    <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Dashboard 1</li>
                                </ol>

                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div> -->
                    <!-- end row -->

 











                    
                    <!-- end row -->
                    <!-- end row -->

                </div>
                <!-- container -->

            </div>
            <!-- content -->

          <!--   <footer class="footer">
                Lorem Ipsum
            </footer> -->

        </div>

        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

    </div>
    <!-- END wrapper -->

    

</body>

</html>