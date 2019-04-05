<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>Add Emp</title>

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

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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
                   <a href="${pageContext.request.contextPath}/v0.1/dashboard/manageRegistration">
                        <i class="ti-panel"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li class="active">
					<a href="#" data-target="#item1" data-toggle="collapse" data-parent="#stacked-menu"> <i class="ti-user"></i>
                        <p>LOGS<span class="caret arrow"></span></p>
                    </a>
						<ul class="nav child_menu nav-stacked collapse left-submenu" id="item1">
							<li><a href="${pageContext.request.contextPath}/v0.1/dashboard/user" style="font-weight:900;margin-left: 50px"><span></span>Add New Emp</a></li>
							<li><a href="${pageContext.request.contextPath}/v0.1/dashboard/managemerchant" style="font-weight:900;margin-left: 50px">Logs</a></li>
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
                        <p>Know Emp ID</p>
                    </a>
                </li>
                <li>
                  <a href="${pageContext.request.contextPath}/v0.1/dashboard/empRegistration">
                        <i class="ti-text"></i>
                        <p>Track A Emp</p>
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
                    <a class="navbar-brand" href="#">Add Emp</a>
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

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    
                    <div class="col-lg-12 col-md-7">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Add New Employee</h4>
                            </div>
                            <div class="content">
                                <form action="<%=request.getContextPath()%>/v0.1/dashboard/addEmp" method="post">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Emp Name*</label>
                                                <input name="emp_name" id="empName" type="text" class="form-control border-input" placeholder="emp_name" value="" required />
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Mobile No.*</label>
                                                <input name="mobile_no" id="mobile_no" type="tel" min="10"  maxlength="10" class="form-control border-input" placeholder="Mobile No." value="" required />
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Email address*</label>
                                                <input name="email_id" id="email" type="email" class="form-control border-input" placeholder="Email" required />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>User Name*</label>
                                                <input name="user_name" id="userName" type="text" class="form-control border-input" placeholder="User Name" value="" required />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Password*</label>
                                                <input name="password" id="password" type="text" class="form-control border-input" placeholder="password" value="" required />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Address*</label>
                                                <input name="address" id="address" type="text" class="form-control border-input" placeholder="Address" value="" required />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Ip(s)*</label>
                                                <input name="ip" id="ip" type="text" class="form-control border-input" placeholder="192.XXX.4.148,192.168.14.XXX"/>
                                            </div> 
                                        </div>
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Role*</label>
                                                <input name="role" type="text" class="form-control border-input" placeholder="Role"/>
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
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>State*</label>
                                                <input name="state" id="state" type="text" class="form-control border-input" placeholder="state*" value="" required />
                                            </div> 
                                        </div>
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>country*</label>
                                                <input name="country" id="country" type="text" class="form-control border-input" placeholder="country*" value="" required>
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
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Value1</label>
                                                <input name="value1" id="value1" type="text" class="form-control border-input" placeholder="value for test" value="" required />
                                            </div> 
                                        </div>
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Value2</label>
                                                <input name="value2" id="value2" type="text" class="form-control border-input" placeholder="value for test" value="" required>
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
                                        <input type="submit" id="clicked" class="btn btn-info btn-fill btn-wd" value="Add Employee" >
                                      
                                    </div>
                                
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

 
     
        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>

                        <li>
                            <a href="https://www.filegstnow.com/">
                                JK Technosoft Limited.
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
                    &copy; <script>document.write(new Date().getFullYear())</script>, made with <i class="fa fa-heart heart"></i> by <a href="https://www.jkt.com/"> JK Technosoft Limited.</a>
                </div>
            </div>
        </footer>

    </div>
</div>

</body>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></link>

<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
</script>

<!--     <script>  
$('#clicked').click(function() {
	
  	var dataJSON={
  			     'emp_name':$('#empName').val(),
      			'user_name':$('#userName').val(),
      			'password':$('#password').val(),
      			'email_id':$('#email').val(),
      			'mobile_no':$('#mobile_no').val(),
      			'address':$('#address').val(),
      			'state':$('#state').val(),
      			'country':$('#country').val(),
  	}
      
      $.ajax({
    	  crossOrigin: true,
          url: "http://localhost:8081/HMSWebServices/v0.1/dashboard/addEmp",
         //url: "http://192.168.10.59:7003/CallingRest/RestService_2/empREG",
          method: "POST",
          headers: {
              'Access-Control-Allow-Origin': '*'
          },
          crossDomain: true,
          data: JSON.stringify(dataJSON),
          dataType: 'json',
          contentType: "application/json",
          success: function(result){
        	   alert(result.status)
        	   if (result.status == 1) {
        		   show_msg({'msg':' emp registered successfully ','status':'1'});
        		   location.reload();
        	   }
        		
        	   else{
        		  alert(result.status)
        	   }
           },
           error: function (jqXHR, exception) {
               var msg = '';
               if (jqXHR.status === 0) {
                   msg = 'Not connect.\n Verify Network.';
               } else if (jqXHR.status == 404) {
                   msg = 'Requested page not found. [404]';
               } else if (jqXHR.status == 500) {
                   msg = 'Internal Server Error [500].';
               } else if (exception === 'parsererror') {
                   msg = 'Requested JSON parse failed.';
               } else if (exception === 'timeout') {
                   msg = 'Time out error.';
               } else if (exception === 'abort') {
                   msg = 'Ajax request aborted.';
               } else {
                   msg = 'Uncaught Error.\n' + jqXHR.responseText;
               }
               alert(msg);
           }
      });
      
 });
</script>  

 
<script>  

$('#btnadad').on('click',function (){

	   alert("ok");
	   $.ajax({
	   type:'GET',
	   dataType: 'json',
	   url :"/tournament",
	   success: function(data) {
	        console.log('success',data);

	   },
	   error:function(jqXHR,textStatus,errorThrown ){
	      alert('Exception:'+errorThrown );
	   }
	}); 
	});
 </script> -->
    

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
