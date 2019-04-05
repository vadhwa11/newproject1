
  <%-- <jsp:forward page="/v0.1/dashboard/login"></jsp:forward>  --%>
  
  <%--  <jsp:forward page="/v0.1/user/adhaarpage"></jsp:forward>  --%>

<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="style.css">
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
   <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
   
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script type="text/javascript">
	window.history.forward();
	function preventBack() {
		window.history.forward(1);
	}
</script>

</head>

<body onload="preventBack();" onpageshow="if (event.persisted) preventBack();" onunload="">
	<section class="login-block" style="">
		<div class="container">
			<div class="row">
				<div class="col-md-4 login-sec">
					<h2 class="text-center">ICG Login WebServices</h2>
					<%-- <form class="login-form" method="POST" action="${pageContext.request.contextPath}/v0.1/dashboard/home"> --%>
						<div class="form-group">
							<label for="exampleInputEmail1" id="user_name" class="text-uppercase">Username</label>

							<input type="text" class="form-control" placeholder=""
								name="username" required>


						</div>
						<div class="form-group">
							<label for="exampleInputPassword1" class="text-uppercase">Password</label>

							<input type="password" id="password" class="form-control" placeholder=""
								name="password" required>

						</div>

						
						<div class="form-check">
							<!-- <label class="form-check-label">
				  <input type="checkbox" class="form-check-input">
				  <small>Remember Me</small>
				</label> -->
			
							<button type="submit" class="btn btn-login float-right" onclick="openPage('registration.jsp')">Submit</button>
						</div>
						<c:choose>
							<c:when test="${indexvalue=='0'}">
								<p style="text-align: left; color: red;">Incorrect Username/Password Combination !</p>
							</c:when>
							<c:otherwise>
								<p style="text-align: left; color: red;"></p>
							</c:otherwise>
						</c:choose>

					</form>
					
					<div class="copy-text">Created<i class=""></i> by <a>JK Technosoft Ltd.</a>
					</div>
				
				</div>


				<div id="slider" class="col-md-8 banner-sec">

					<div id="slider">
						<figure>
							<img
								src="${pageContext.request.contextPath}/resources/images/slider1.jpg"
								alt>
							<img
								src="${pageContext.request.contextPath}/resources/images/slider2.jpg"
								alt>
							<img
								src="${pageContext.request.contextPath}/resources/images/slider1.jpg"
								alt>
							<img
								src="${pageContext.request.contextPath}/resources/images/slider2.jpg"
								alt>
						</figure>

					</div>
					<!--end id slider-->
					
				</div>
				
			</div>
		</div>
		
	</section>

</body>
<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
</script>
</html>