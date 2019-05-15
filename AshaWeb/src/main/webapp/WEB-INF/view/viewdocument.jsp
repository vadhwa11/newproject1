<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<%@page import="com.asha.icgweb.entity.UploadDocument"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>


<% 
 	Map<String,Object> map = new HashMap<String,Object>();
	String fileExtn="";
	UploadDocument doc = new UploadDocument();
	
		if(request.getAttribute("map") != null){
			map = (Map)request.getAttribute("map");
		}
		
		if(map.get("fileExt")!=null){
			fileExtn = (String)map.get("fileExt");
		}
		String image = "";
		if(map.get("document")!=null){
			doc = (UploadDocument)map.get("document");
			image = doc.getBase64Image();	
		}
		
		
   %> 
   
<html>
<body>
<div align="center">
  <%if(fileExtn.equalsIgnoreCase("jpg") || fileExtn.equalsIgnoreCase("gif") || fileExtn.equalsIgnoreCase("jpeg") || fileExtn.equalsIgnoreCase("png")) {%>
  <iframe src="data:image/png;base64,<%=image %>" width="800" height="600"></iframe> 
  <%}else if(fileExtn.equalsIgnoreCase("pdf")){ %>
 <iframe  src="data:application/pdf;base64,<%=image %>" width="800" height="600"></iframe> 
  <%} %>
</div>
</body>
</html>