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
<body>

        <%
  
 
  
   response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
   response.setDateHeader("Expires", 0);
  /*  Cookie[] cookies = request.getCookies(); 
   String JsessionId = null;
    
    if(cookies !=null)
    {
     for(Cookie cookie : cookies)
     {
	if(cookie.getName().equals("JSESSIONID")) 
        JsessionId = cookie.getValue();
     }
    } */
    
   // String userName = request.getHeader("oam_userid");
    //String username = (String)request.getAttribute("un");
    
    
    String ServiceNo="123457";

    
    session.setAttribute("serviceNumber", ServiceNo);
  
    
    %>
  <script type="text/javascript" language="javascript">
	
 
  var serviceNumber = "<% out.print(ServiceNo);%>";
	       window.location.href="appointment/getServiceDetails?serviceNumber="+serviceNumber;
     
		
 </script>

</body>
</html>