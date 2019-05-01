<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

 <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
 <body class="pb-0" style="   background-color: #edf0f0;">
        <div class="wrapper-page">
            <div class="account-pages">
                <div class="account-box">
                    <div class="account-logo-box bg-primary p-4">
                       <h6 class="m-0 text-center text-white">INDIAN COAST GUARD</h6>
                    </div>
                    <div class="account-content">
                     <form id="patientdetails" class="form-horizontal" name="patientdetailsForm" method="post" modelAttribute="patientDetail"  > 
                            <div class="form-group  mb-4 row">
                                <div class="col-12">
                                    <label for="emailaddress">Service Number </label>
                                    <input class="form-control" type="text" name="serviceNumber" id="serviceNumber"  required="" placeholder="Enter Service Number">
                                </div>
                            </div>

                          <!--   <div class="form-group row mb-4">
                                <div class="col-12">
                                    <label for="password">Password :</label>
                                    <input class="form-control" type="password" required="" id="password" placeholder="Enter your password">
                                </div>
                            </div> -->

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
                                    <button class="btn btn-md btn-block btn-primary waves-effect waves-light" type="submit" onclick="return  checkServiceNumberValidation();">Search</button>
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
        <script type="text/javascript">

function checkServiceNumberValidation() {
	 var serviceNumber = $("#serviceNumber").val();
	 if(document.getElementById('serviceNumber').value == null || document.getElementById('serviceNumber').value == ""){
			alert("Please Enter service Number .");
			return false;
		}
	document.patientdetailsForm.action="${pageContext.request.contextPath}/appointment/getServiceDetails";
}
 </script>
    </body>

</html>