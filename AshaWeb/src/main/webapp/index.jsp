<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
       <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Indian Coast Guard</title>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
   <link rel="icon" type="images/favicon-32x32.png" sizes="32x32" href="images/favicon.ico">
    <link rel="stylesheet" href="../plugins/morris/morris.css">

    <link href="../plugins/sweet-alert2/sweetalert2.min.css" rel="stylesheet">
    <link href="css/icons.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />   
    <link href="${pageContext.request.contextPath}/resources/css/metismenu.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/app.css" rel="stylesheet" type="text/css" />    
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
    
    <script src="${pageContext.request.contextPath}/resources/js/modernizr.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/metisMenu.min.js"></script>
   <%--  <script src="${pageContext.request.contextPath}/resources/js/waves.js"></script> --%>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.scrollTo.min.js"></script>    
	 <script src="${pageContext.request.contextPath}/resources/js/jquery.core.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.app.js"></script>
	
	<script type="text/javascript">
	function getLogin(){
		/* document.form.name="loginForm";
		document.action.name="/login/loginPage";
		document.method.name="POST" */
		
			document.getElementById("loginForm").submit();
	}
	
	</script>

    </head>
     <body class="pb-0">
        <div class="wrapper-page">
            <div class="account-pages">
                <div class="account-box">
                    <div class="account-logo-box bg-primary p-4">
                        <h3 class="m-0 text-center text-white">INDIAN COAST GAURD</h3>
                    </div>
                    <div class="account-content">
                        <form name="loginForm" id="loginForm" action="${pageContext.request.contextPath}/v0.1/dashboard/dashboard" method="GET" class="form-horizontal" action="#">

                            <div class="form-group  mb-4 row">
                                <div class="col-12">
                                    <label for="emailaddress">Email Address :</label>
                                    <input class="form-control" type="email" id="emailaddress" placeholder="Enter email id">
                                </div>
                            </div>

                            <div class="form-group row mb-4">
                                <div class="col-12">
                                    <label for="password">Password :</label>
                                    <input class="form-control" type="password" id="password" placeholder="Enter your password">
                                </div>
                            </div>

                            <div class="form-group row mb-4">
                                <div class="col-12">
                                    <div class="checkbox checkbox-success">
                                        <input id="remember" type="checkbox" checked="">
                                        <label for="remember">
                                            Remember me
                                        </label>
                                        <a href="pages-recoverpw.html" class="text-muted float-right">Forgot your password?</a>
                                    </div>

                                </div>
                            </div>

                            <div class="form-group row text-center m-t-10">
                                <div class="col-12">
                                    <button class="btn btn-md btn-block btn-primary waves-effect waves-light" type="submit" onsubmit="getLogin();">Sign In</button>
                                </div>
                            </div>

                        </form>

                        <div class="row mt-4">
                            <div class="col-sm-12 text-center">
                                <p class="text-muted">Don't have an account? <a href="pages-register.html" class="text-dark m-l-5"><b>Sign Up</b></a></p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <!-- end account-box-->
        </div> 
    </body>
</html>