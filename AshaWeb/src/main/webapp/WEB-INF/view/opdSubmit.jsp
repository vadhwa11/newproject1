<%@page import="java.util.HashMap"%>
    <%@page import="java.util.Map"%>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

          
          <%@include file="..//view/leftMenu.jsp" %>

                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                    <html>

                    <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                        <%@include file="..//view/commonJavaScript.jsp" %>
                            <title>OPD Main</title>
                      <!--   <link href="/AshaWeb/resources/css/stylecommon.css" rel="stylesheet" type="text/css" /> -->
                    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/newOpd.js"></script>
                    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/opd.js"></script>
                   
                    </head>

                    <%
	int i = 1;
%>






<style>
.block {
  display: block;
  width: 100%;
  border: none;
  background-color: #4CAF50;
  color: white;
  padding: 14px 28px;
  font-size: 16px;
  cursor: pointer;
  text-align: center;
}

.block:hover {
  background-color: #ddd;
  color: black;
}
</style>
<body>


<div class="content-page">
                                <!-- Start content -->
 <div class="">
  <div class="container-fluid">
	 
	  <div class="internal_Htext">OPD </div>
	  

                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                                <h3>OPD Submitted Successfully</h3>
                                     <div class="row">
										<div class="col-md-3">
											<div class="form-group row">
											<button class=" btn btn-primary">CASE Sheet</button>
												 
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group row">
												 <button class="btn btn-primary">Investigation Slip</button>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group row">
												 <button class="btn btn-primary">Prescription Slip</button>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group row">
												<button class="btn btn-primary" input type="submit" id="clicked">Back</button> 
											</div>
										</div>
                                      

                                    </div>
 
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
                  </div>


 



<!---header text ends--->

											<div class="col-md-4">
											<div class="form-group">
												<input type="hidden" id="visitId" name="PatientID"
													value="">
											</div>
										</div>


  


  
  
  
  

 

<script type="text/javascript" language="javascript">
            $('#clicked').click(function() {

                           // alert("hello")
                           window.location.href ="opdWaitingList";
                      
            });
            
            
            
 </script>           
<script type="text/javascript" language="javascript">
var visitId=$('#visitId').val();
          $('#clicked').click(function() {
            
        	  alert("hello" :visitId );
            }

</script>
</body>
</html>