<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="..//view/leftMenu.jsp"%>
<%@include file="..//view/commonJavaScript.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
</head>

<script type="text/javascript">


$(document).ready(function(){
	var dictionary = ${result};
	var visitMsg ="Visit has been created sucessfully.";
	
	 $j('#visit_id').val(dictionary)
	 $j('#message').html(visitMsg);
});


function GoToHomePage()
{
  window.history.back();   
}


function printTokenReport()
{
	
	 document.frm.action="${pageContext.request.contextPath}/report/printVisitTokenReport";
	document.frm.method="POST";
	document.frm.submit(); 
	
	 /* var visitId = $j('#visitId').val();	 
	 var params = {
			 "visitId":visitId
	       }
		 $j.ajax({
				type : "POST",
				contentType : "application/json",
				url : '${pageContext.request.contextPath}/report/printCaseSheet',
				data : JSON.stringify(params),
				dataType : "json",
				cache : false,
				success : function(response) {
				console.log(response.msg)
			},
				error : function(msg) {
					alert("An error has occurred while contacting the server");
				}
		    
		}); */
 
}

</script>
<body>
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
						<!-- <div class="row">
							<div class="col-md-12">
								<p align="Left" id="message"
									style="color: black; font-weight: bold;"></p>
							</div>
						</div> -->
<form name="frm">
						<div class="row">
							<div class="col-md-4">
							
							<input type="hidden" id="visit_id" name="visit_id" value="">
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
		<br><br><br><br><br>
		
		
		
	</div>
	
	</div>
	</div>

</body>
</html>