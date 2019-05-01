<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@include file="..//view/leftMenu.jsp" %>
        <%@include file="..//view/commonJavaScript.jsp" %>

            <!DOCTYPE html>
            <html>

            <head>
                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

                        <meta charset="ISO-8859-1">
                        <title>Patient Appointment</title>
            </head>
<body>

 <div class="content-page">
                    <!-- Start content -->
                    <div class="">
                        <div class="container-fluid">
                            <div class="internal_Htext">Patient Appointment</div>

                            <div class="row">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-body">
                                        

  <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                         ${message}
                                                                    </div>
                                                                </div>
 <%-- <div class="col-md-2">
   <a class="btn btn-info" role="button" href="${pageContext.request.contextPath}/appointment/getAppointmentHistory?visitId=${visitId}">Print Token</a>
		</div> --%>
		<div class="col-md-2">
			   <a class="btn btn-primary" role="button" href="${pageContext.request.contextPath}/appointment/getServiceDetails?serviceNumber=${serviceNumber}">Home</a>
      </div> 
		</div>
		</div>
		</div>
		</div>
		</div>
		</div>
		</div>
		

	</body>
</html>