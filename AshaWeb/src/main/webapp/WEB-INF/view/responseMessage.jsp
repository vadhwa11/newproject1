<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="..//view/leftMenu.jsp"%>
<%@include file="..//view/commonJavaScript.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message</title>

</head>

<script type="text/javascript">

<%

String visit_id= request.getParameter("visitId");

String flag="ICG";
if(request.getParameter("flag") !=null)
{
	flag="Others";
}
%>


function GoToHomePage()
{
  window.history.back();   
}


function printTokenReport()
{
	var flag="<% out.print(flag);%>";
		
	if(flag=='ICG')
	document.frm.action="${pageContext.request.contextPath}/report/printVisitTokenReport";
	if(flag=='Others')
		document.frm.action="${pageContext.request.contextPath}/report/printVisitTokenReportforOthers";
	document.frm.method="POST";
	document.frm.submit(); 
	
	
 
}

</script>
<body>
<div id="wrapper">
	<div class="content-page">
		<div class="">
			<div class="container-fluid">
				<div class="internal_Htext">Token Card </div>

								<div class="row">
									<div class="col-12">
										<div class="card">
											<div class="card-body">
											<p align="Left" id="message"
													style="color: black; font-weight: bold;"></p>
								
									<form name="frm">
										<div class="row">
											<div class="col-md-4">
											
											<input type="hidden" id="visit_id" name="visit_id" value="<%out.print(visit_id);%>">
												<button class="btn btn-primary" type="button" name="Print Token Card"
													id="backBtn" onClick="printTokenReport()">Print Token</button>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-primary" type="button" name="backBtn"
													id="backBtn" onClick="GoToHomePage()">Back</button>
											</div>
											<div class="col-md-4">
												<!-- <button class="btn btn-primary" type="button" name="backBtn"
													id="backBtn" onClick="GoToHomePage()">Back</button> -->
											</div>
										<div class="col-md-4">
												
										</div>
										</div>
				                     </form>
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