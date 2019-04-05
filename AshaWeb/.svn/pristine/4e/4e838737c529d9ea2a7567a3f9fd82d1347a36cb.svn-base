<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>View Registration</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></link>

<script type="text/javascript">
	window.history.forward();
	function preventBack() {
		window.history.forward(1);
	}
</script>
</head>
<body>

<div class="wrapper">
	<div class="sidebar" data-background-color="white" data-active-color="danger">

    <!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->

    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="#" class="simple-text">
                    ADMIN
                </a>
            </div>

            <ul class="nav">
                <li >
                    <a href="${pageContext.request.contextPath}/v0.1/dashboard/home">
                        <i class="ti-panel"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li class="active">
					<a href="#" data-target="#item1" data-toggle="collapse" data-parent="#stacked-menu"> <i class="ti-user"></i>
                        <p>MERCHANTS <span class="caret arrow"></span></p>
                    </a>
						<ul class="nav child_menu nav-stacked collapse left-submenu" id="item1">
							<li><a href="${pageContext.request.contextPath}/v0.1/dashboard/user" style="font-weight:900;margin-left: 50px"><span></span>Add New merchants</a></li>
							<li><a href="${pageContext.request.contextPath}/v0.1/dashboard/manageRegistration" style="font-weight:900;margin-left: 50px">Manage merchants</a></li>
						</ul>
					</li>
                
                <%-- <li>
					<a href="#" data-target="#item2" data-toggle="collapse" data-parent="#stacked-menu"> <i class="ti-view-list-alt"></i>
							<p>Transactions Logs <span class="caret arrow"></span></p>
					</a>
					<ul class="nav child_menu nav-stacked collapse left-submenu" id="item2">
							<li><a href="${pageContext.request.contextPath}/v0.1/dashboard/authlogs" style="font-weight:900;margin-left: 50px"><span></span>Authentication</a></li>
							<li><a href="#" style="font-weight:900;margin-left: 50px">Ekyc</a></li>
						</ul>
					
					</li> --%>
				<li>
                    <a href="${pageContext.request.contextPath}/v0.1/dashboard/getref">
                        <i class="fa fa-user-plus" aria-hidden="true"></i>
                        <p>Know Aadhaar-Ref. Key</p>
                    </a>
                </li>	
                <li>
                    <a href="#">
                        <i class="ti-text"></i>
                        <p>Track A Transaction</p>
                    </a>
                </li>
                <!-- <li>
                    <a href="icons.html">
                        <i class="ti-pencil-alt2"></i>
                        <p>Icons</p>
                    </a>
                </li>
                <li>
                    <a href="maps.html">
                        <i class="ti-map"></i>
                        <p>Maps</p>
                    </a>
                </li>
                <li>
                    <a href="notifications.html">
                        <i class="ti-bell"></i>
                        <p>Notifications</p>
                    </a>
                </li>
				<li class="active-pro">
                    <a href="upgrade.html">
                        <i class="ti-export"></i>
                        <p>Upgrade to PRO</p>
                    </a>
                </li> -->
            </ul>
    	</div>
    </div>

    <div class="main-panel">
		<nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    <a class="navbar-brand" href="#">View/Modify Merchant</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                           <!--  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="ti-panel"></i>
								<p>Stats</p>
                            </a> -->
                        </li>
                        <!-- <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="ti-bell"></i>
                                    <p class="notification">5</p>
									<p>Notifications</p>
									<b class="caret"></b>
                              </a>
                              <ul class="dropdown-menu">
                                <li><a href="#">Notification 1</a></li>
                                <li><a href="#">Notification 2</a></li>
                                <li><a href="#">Notification 3</a></li>
                                <li><a href="#">Notification 4</a></li>
                                <li><a href="#">Another notification</a></li>
                              </ul>
                        </li> -->
						<li>
                            <a href="${pageContext.request.contextPath}/" class="btn btn-info btn-lg">
									<span class="glyphicon glyphicon-log-out"></span> Log out
							</a>
							</li>
                    </ul>

                </div>
            </div>
        </nav>

			<c:choose>
				<c:when test="${empty merchantInfo}">
					<div class="content">
						<div class="container-fluid">
							<div class="row">

								<div class="col-lg-12 col-md-7">
									<div class="card">
										<div class="header">
											<h4 class="title" align="center">Unable to get merchant details try again !</h4>
											<br>

										</div>
										<div class="content" style="text-align: center;">
											<a
												href="${pageContext.request.contextPath}/v0.1/dashboard/home"><u>back<u></u></a>
										</div>
									</div>
								</div>


							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="content">
            <div class="container-fluid">
                <div class="row">
                    
                    <div class="col-lg-12 col-md-7">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">View/Modify Merchant</h4>
                            </div>
                            <div class="content">
                                <form action="${pageContext.request.contextPath}/v0.1/dashboard/updatemerchantInfo/${merchantInfo.merchantId}">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>UserId*</label>
                                                <input type="text" class="form-control border-input" disabled placeholder="UserId" value="${merchantInfo.userId}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Password*</label>
                                                <input type="text" class="form-control border-input" disabled placeholder="Password" value="${merchantInfo.userpassword}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label >MerchantId*</label>
                                                <input type="text" class="form-control border-input" placeholder="MerchantId" disabled value="${merchantInfo.merchantId}">
                                            </div>
                                        </div>
                                    </div>
		
									<div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label >MerchantName*</label>
                                                <input type="text" class="form-control border-input" name = "MerchantName" placeholder="MerchantName" value="${merchantInfo.merchantname}">
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Status*</label>
                                                <input type="text" class="form-control border-input" enabled name ="Status" placeholder="Status" value="${merchantInfo.status}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Enabled*</label>
                                                <input type="text" class="form-control border-input" name="Enabled" placeholder="Enabled"  enabled value="${merchantInfo.enabled}">
                                            </div>
                                        </div>
                                    </div>
		
							<div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>credentialsNonExpired*</label>
                                                <input type="text" class="form-control border-input" name="credentialsNonExpired"  placeholder="credentialsNonExpired" enabled value="${merchantInfo.credentialsNonExpired}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>accountNonLocked*</label>
                                                <input type="text" class="form-control border-input" name="accountNonLocked" placeholder="accountNonLocked" enabled value="${merchantInfo.accountNonLocked}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label >apikey*</label>
                                                <input type="text" class="form-control border-input" placeholder="apikey" disabled value="${merchantInfo.apikey}">
                                            </div>
                                        </div>
                                    </div>
		
		
						<div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>apikeyhash*</label>
                                                <input type="text" class="form-control border-input"  placeholder="apikeyhash" disabled value="${merchantInfo.apikeyhash}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label >accountNonExpired*</label>
                                                <input type="text" class="form-control border-input" name="accountNonExpired" placeholder="accountNonExpired" enabled value="${merchantInfo.accountNonExpired}">
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>timestamp*</label>
                                                <input type="text" class="form-control border-input" placeholder="timestamp" disabled value="${merchantInfo.timestamp}">
                                            </div>
                                        </div>
                                        
                                    </div>
		
	
									<div class="row">
                                        
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Mobile No.*</label>
                                                <input type="text" class="form-control border-input" name="mobile" placeholder="Mobile No." enabled value="${merchantInfo.mobileno}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Email address*</label>
                                                <input type="email" class="form-control border-input" name="Email" placeholder="Email" enabled value="${merchantInfo.email}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>ip(s)*</label>
                                                <input type="text" class="form-control border-input"  name="ip" placeholder="ip(s)*" enabled value="${merchantInfo.ip}">
                                            </div>
                                        </div>
                                        
                                        
                                    </div>
	
	
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Company Name*</label>
                                                <input type="text" class="form-control border-input" name="compyname" placeholder="Company Name*" enabled value="${merchantInfo.companyname}">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Owner Name*</label>
                                                <input type="text" class="form-control border-input" name="ownername" placeholder="Owner Name" enabled value="${merchantInfo.ownername}">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Address*</label>
                                                <input type="text" class="form-control border-input" name="address" placeholder="Home Address" enabled value="${merchantInfo.address}">
                                            </div>
                                        </div>
                                    </div>

                                    
							<div class="row">
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Monthly Transaction Volume*</label>
                                                <input type="text" class="form-control border-input" name="monthlytrans" placeholder="Monthly Transaction Volume*" enabled value="${merchantInfo.monthly_transaction}">
                                            </div> 
                                        </div>
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>eKYC or Authentication Purpose(s)*</label>
                                                <input type="text" class="form-control border-input" name="purpose" placeholder="eKYC or Authentication Purpose(s)*" enabled value="${merchantInfo.purpose}">
                                            </div> 
                                        </div>
                                        <!-- <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Postal Code</label>
                                                <input type="number" class="form-control border-input" placeholder="ZIP Code">
                                            </div>
                                        </div> -->
                                    </div>
							
							
                                    <div class="row">
                                        <div class="col-md-12">
                                            <!-- <div class="form-group">
                                                <label>About Me</label>
                                                <textarea rows="5" class="form-control border-input" placeholder="Here can be your description" value="Mike">Oh so, your weak rhyme
												You doubt I'll bother, reading into it
												I'll probably won't, left to my own devices
												But that's the difference in our opinions.</textarea>
                                            </div> -->
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-info btn-fill btn-wd" >Update Merchant</button>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
				</c:otherwise>
			</c:choose>
			
			
			


        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>

                        <li>
                            <a href="https://www.filegstnow.com/">
                                JKTechnosoft Ltd.
                            </a>
                        </li>
                        <!-- <li>
                            <a href="http://blog.creative-tim.com">
                               Blog
                            </a>
                        </li>
                        <li>
                            <a href="http://www.creative-tim.com/license">
                                Licenses
                            </a>
                        </li> -->
                    </ul>
                </nav>
                <div class="copyright pull-right">
                    &copy; <script>document.write(new Date().getFullYear())</script>, made with <i class="fa fa-heart heart"></i> by <a href="https://www.filegstnow.com/"> Skill lotto sol pvt Ltd.</a>
                </div>
            </div>
        </footer>

    </div>
</div>


</body>



    <!--   Core JS Files   -->
    <script src="${pageContext.request.contextPath}/resources/assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-checkbox-radio.js"></script>

	<!--  Charts Plugin -->
	<script src="${pageContext.request.contextPath}/resources/assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
	<script src="${pageContext.request.contextPath}/resources/assets/js/paper-dashboard.js"></script>

	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="${pageContext.request.contextPath}/resources/assets/js/demo.js"></script>

</html>
