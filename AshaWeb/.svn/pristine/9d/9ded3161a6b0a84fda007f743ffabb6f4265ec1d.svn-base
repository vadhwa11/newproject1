<!DOCTYPE html>

<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"  src="resources/js/proto.js?n=1"></script>
<script type="text/javascript" language="javascript"  src="resources/js/jquery.js?n=1"></script>

<script type="text/javascript">
var jQuery=$.noConflict();
jQuery(document).ready(function(){
	jQuery('#MainMenu li a').click(function(){
	  jQuery('li a').removeClass("active");
	  jQuery(this).addClass("active");
});
});

jQuery(document).ready(function(){
jQuery('#MainMenu li ul li a').click(function(){
	jQuery('li a').removeClass("active");
	jQuery(this).parent().parent().parent().children().get(0).setAttribute("class", "active");
});
});
</script>

<div class="clear"></div>
<form name="navigation" method="post" >

</ul>
<div class="clear"></div>


</div>           
  <!-- 	makeMainMenu(); -->           	

<%--  <input type="hidden" name="selectedAppId" id="selectedAppId" value="<%=reqappId%>"/>
       <input type="hidden" name="parentId" id="parentId" value="<%=parentId%>"/> --%>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>

<!--</div>
</div>-->
<div class="clear"></div>
<%-- <div class="indicationMainDiv">
<%
	if(!appName.equals("")){
		screenPath = parentName+" > "+appName;
%>
<div class="indication-nav"><%=screenPath %></div>
<%} %>
<div class="clear"></div>
</div> --%>

<div class="clear"></div>
<div class="clear"></div>
<div id="mainIn">

