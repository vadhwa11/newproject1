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
                <a href="index.html" class="logo">
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
                            <a href="javascript: void(0);"><i class="ion-md-home"></i> <span>Reception</span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded=false>
								<li><a href="${pageContext.request.contextPath}/registration/showemployeeanddependent">Registration and Appointment ICG</a></li>
								<li><a href="${pageContext.request.contextPath}/registration/registrationandappointmentothers">Registration and Appointment Others</a></li>
										
								<li><a href="${pageContext.request.contextPath}/registration/uploadPatientDocument">Upload Patient Document</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript: void(0);"><i class="fa fa-address-card-o" aria-hidden="true"></i>
                                <span>OPD</span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/preOpdWaitingList">Pre-Consultation Waiting List</a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/opdWaitingList">OPD Waiting List</a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/obesityWaitingJsp">Obesity Waiting List</a></li>    
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/referralWaitingList">Referral Waiting List</a></li>   
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/admissionDischargePending">Pending Admission List</a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/dischargePending">Pending Discharge List</a></li>                                  
                                <li><a href="${pageContext.request.contextPath}/v0.1/opd/opdPrescriptionReports">Reports</a></li>
                                <li><a href="#">Reports Case stylesheet</a></li>
                                <li><a href="#">OP Nursing Care</a></li>
                                
                                
                            </ul>
                        </li>

						 <li>
                            <a href="javascript: void(0);"><i class="ion-ios-list"></i><span> Medical Exam </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="${pageContext.request.contextPath}/exam/medicalExam">Medical Exam</a></li>                                
                                <li><a href="#">Reports </a></li>                           
                            </ul>
                        </li>
                         <li>
                            <a href="javascript: void(0);"><i class="ion-ios-list"></i><span> Medical Board </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Department Return </a></li>
                                <li><a href="#">Opening Balance Entry </a></li>
                                <li><a href="#">Pending Prescription </a></li>
                                <li><a href="#">MR Receive </a></li>
                                <li><a href="#">Reports </a></li>                           
                            </ul>
                        </li>
                        
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-mail"></i>  <span> Laboratory </span><span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Inbox</a></li>
                                <li><a href="#">Compose Mail</a></li>
                                <li><a href="#">View Mail</a></li>
                                <li><a href="#">Email Templates</a></li>
                            </ul>
                        </li>
						
						<li>
                            <a href="javascript: void(0);"><i class="ion-md-map"></i> <span> Radiology </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#"> Google Map</a></li>
                                <li><a href="#"> Vector Map</a></li>
                            </ul>
                        </li>
                        
                        <li>
                            <a href="javascript: void(0);"><i class="ion-ios-list"></i><span> Dispensary </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Department Return </a></li>
                                <li><a href="#">Opening Balance Entry </a></li>
                                <li><a href="#">Pending Prescription </a></li>
                                <li><a href="#">MR Receive </a></li>
                                <li><a href="#">Reports </a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-pie"></i><span> Stores </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <!-- <li><a href="#">Morris Chart</a></li>
                                    <li><a href="#">Chartjs</a></li>
                                    <li><a href="#">Flot Chart</a></li>
                                    <li><a href="#">Rickshaw Chart</a></li>
                                    <li><a href="#">Peity Chart</a></li>
                                    <li><a href="#">C3 Chart</a></li>
                                    <li><a href="#">Other Chart</a></li> -->
                            </ul>
                        </li>

                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Ward </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Nursing Station    </a></li>
                                <li><a href="#">Reports </a></li>
                                <li><a href="#">IP Admission </a></li>
                           </ul>
                        </li>

 						<li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> MI Administration </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Nursing Station    </a></li>
                                <li><a href="#">Reports </a></li>
                                <li><a href="#">IP Admission </a></li>
                           </ul>
                        </li>
                        
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> FWC </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Nursing Station    </a></li>
                                <li><a href="#">Reports </a></li>
                                <li><a href="#">IP Admission </a></li>
                           </ul>
                        </li>
                        
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> SHO </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="#">Nursing Station    </a></li>
                                <li><a href="#">Reports </a></li>
                                <li><a href="#">IP Admission </a></li>
                           </ul>
                        </li>
                        
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> User Management </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                            <li><a href="${pageContext.request.contextPath}/addFormsAndReports">Add Form / Reports</a></li>
                                <li><a href="${pageContext.request.contextPath}/manageUserApplication">Manage User Application</a></li>
                                <li><a href="${pageContext.request.contextPath}/manageTemplate">Manage Template</a></li>
                                <li><a href="${pageContext.request.contextPath}/assignApplicationToTemplate">Assign Application To Template</a></li>
                                <li><a href="${pageContext.request.contextPath}/assignTemplateToRole">Assign Template To Role</a></li>
                                <li><a href="${pageContext.request.contextPath}/manageRole">Manage Role</a></li>
                                
                            </ul>
                        </li>
                        
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> Masters </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/regionMaster">Command Master </a></li>
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
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/itemUnitMaster">Item Unit Master </a></li>
                                <%-- <li><a href="${pageContext.request.contextPath}/v0.1/master/usersMaster">Users Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/mainChargeCodeMaster">MainChargeCode Master </a></li>
                                --%> <li><a href="${pageContext.request.contextPath}/v0.1/master/disposalMaster">Disposal Type Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/idealWeightMaster">Ideal Weight Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/frequencyMaster">Frequency Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/appointmentTypeMaster">Appointment Type </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/empanelledHospitalMaster">Empanelled Hospital Master </a></li>                                
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/physiotherapyMaster">Physiotherapy Master </a></li>
                                <li><a href="${pageContext.request.contextPath}/v0.1/master/serviceTypeMaster">Service Type</a></li>                                
                            </ul>
                        </li>
                        
                        <li>
                            <a href="javascript: void(0);"><i class="ion-md-copy"></i> <span> ADMIN </span> <span class="menu-arrow"></span></a>
                            <ul class="nav-second-level" aria-expanded="false">
                                                         
                             <li><a href="${pageContext.request.contextPath}/appointment/showappointmentsetup">Appointment Setup</a></li>
                                <li><a href="${pageContext.request.contextPath}/appointment/showappointmentsession">Appointment Session</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/doctorRoaster">Doctor Roster</a></li>
                                                              
                            </ul>
                        </li>
                                                
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

    

</body>

</html>