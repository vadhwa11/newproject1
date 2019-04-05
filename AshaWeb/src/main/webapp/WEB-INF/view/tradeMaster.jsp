<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<%-- <%@include file="/WEB-INF/view/masterHeader.jsp" %> --%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Masters</title>

<!-- Bootstrap core CSS     -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    --> <!-- left menu dashboard -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css" rel="stylesheet">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script type="text/javascript" language="javascript">
$(document).ready(
		function() {
			var pathname = window.location.pathname;
			var accessGroup = pathname.split("/")[1];
			
			var url = window.location.protocol + "//"
					+ window.location.host + "/" + accessGroup
					+ "/v0.1/master/getTradeDetails"

			/* var data = $('cmdCode').val(); */

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url,
				data : JSON.stringify({
					'tradeName' : "DMT"
				}),
				dataType : 'json',
				timeout : 100000,
				success : function(res) {
					console.log("SUCCESS: ", res);
					// var obj = JSON.parse(res);
					var data = res.masTradeLst;
					var html = '';
					$.each(data, function(key, value) {

						html += '<tr onclick="rowClick('+value.tradeId+',\''+value.tradeName+'\')"><td>'
						+ value.tradeId + '</td><td>'
						+ value.tradeName + '</td><td>'
						+ value.status + '</td><td>'
								
					});
					$('#responseData').append(html);

				},
				error : function(e) {

					console.log("ERROR: ", e);

				},
				done : function(e) {
					console.log("DONE");
					alert("success");
					var datas = e.data;
				}
			});

		});

function rowClick(cmdCode, cmdName){
	//alert("pateintname "+patientName);gender
	document.getElementById("tradeId").value = tradeId;
	document.getElementById("tradeName").value = tradeName;
}
		
</script>

</head>
<%-- <%
	Map map = new HashMap();
	map.get("map");
%> --%>
<body bgcolor="">

<div class="wrapper">
	<div class="sidebar" data-background-color="white" data-active-color="danger">

    <!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->
	
    <div class="sidebar-wrapper">
            <div class="logo">
                <a href="#" class="simple-text">
                    MASTERS
                </a>
            </div>

            <ul class="nav">
                <li>
                   <a href="${pageContext.request.contextPath}/v0.1/dashboard/manageRegistration">
                        <i class="ti-panel"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
					<a href="${pageContext.request.contextPath}/v0.1/master/showCommand" data-target="#item1" data-toggle="collapse" data-parent="#stacked-menu"> 
					<i class="ti-user"></i>
                        <p>Command Master<span class="caret arrow"></span></p>
                    </a>
						<%-- <ul class="nav child_menu nav-stacked collapse left-submenu" id="item1">
							<li><a href="${pageContext.request.contextPath}/v0.1/dashboard/user" style="font-weight:900;margin-left: 50px"><span></span>Add New Emp</a></li>
							<li><a href="${pageContext.request.contextPath}/v0.1/dashboard/managemerchant" style="font-weight:900;margin-left: 50px">Logs</a></li>
						</ul> --%>
					</li>
                
                <%-- <li>
					<a href="#" data-target="#item2" data-toggle="collapse" data-parent="#stacked-menu"> <i class="ti-view-list-alt"></i>
							<p>Transactions Logs <span class="caret arrow"></span></p>
					</a>
					<ul class="nav child_menu nav-stacked collapse left-submenu" id="item2">
							<li><a href="${pageContext.request.contextPath}/v0.1/dashboard/authlogs" style="font-weight:900;margin-left: 50px"><span></span>Authentication</a></li>
							<li><a href="#" style="font-weight:900;margin-left: 50px">Ekyc</a></li>
						</ul>
					
					</li> --%>
				
				<li>
                    <a href="${pageContext.request.contextPath}/v0.1/master/tradeMaster">
                        <i class="fa fa-user-plus" aria-hidden="true"></i>
                        <p>Trade Master</p>
                    </a>
                </li>
                <li>
                  <a href="${pageContext.request.contextPath}/v0.1/master/stateMaster">
                        <i class="ti-text"></i>
                        <p>State Master</p>
                    </a>
                </li>
                 <li>
                  <a href="${pageContext.request.contextPath}/v0.1/master/unitMaster">
                        <i class="ti-text"></i>
                        <p>Unit Master</p>
                    </a>
                </li>
                
            </ul>
    	</div>	
    </div>
<!-- main div starts i.e Body rajdeo -->
    <div class="main-panel">
		<nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    <a class="navbar-brand" href="#">Trade Master</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                          
						
                    </ul>

                </div>
            </div>
        </nav>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    
                    <div class="col-lg-12 col-md-7">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Trade Master</h4>
                            </div>
                            <div class="content">
                            <form id="searchTradeForm" name="searchTradeForm" action="<%=request.getContextPath()%>/v0.1/master/getTradeDetails" method="POST"> 
                            <div class="row">
                             <!-- searchtrade div  -->
                                   
                                   <div class="row">
                                         <div class="col-md-4">
                                             <div class="form-group">
                                                
                                                <input name="searchTrade" id="searchTrade" type="text" class="form-control border-input" placeholder="searchTrade" value="">
                                            
                                            </div> 
                                        </div>
                                       
                                    <div class="text-right">
                                        <input type="submit" id="clicked" class="btn btn-info btn-fill btn-wd" value="Search" >
                                      
                                    </div>
                                        
                                </div>
                            
                            </div>
                            
                            </form>
                                
                <!-- Data Grid -->
                            <div class="right_col" role="main">
          <div class="">
            
            <div class="clearfix"></div>

            
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                 
                  <div class="x_content">

                    <table class="table table-striped table-hover jambo_table">
                      <thead>
                        
                        <tr>
                          
                          <th>Trade ID</th>
                          <th>Trade Name.</th>
                          <th>Status</th>
                                                    
                        </tr>
                      </thead>
                      <tbody class="table-hover" id="responseData">
                       
                    
                      </tbody>
                    </table>

                  </div>
                </div>
              </div>

              
            </div>
          </div>                
                                
                                <form id="addTradeForm" name="addTradeForm" action="<%=request.getContextPath()%>/v0.1/master/addCommandDetails" method="POST"> 
                                   <div class="row">
                                   
                                        <div class="col-md-4">
                                         
                                             <div class="form-group">
                                                <label>Trade Name</label>
                                                <input name="tradeName" id="tradeName" type="text" class="form-control border-input" placeholder="Trade Name" value="" required />
                                            </div> 
                                        </div>
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Service Type</label>
                                                <input name="serviceType" id="serviceType" type="text" class="form-control border-input" placeholder="Service Type" value="" required>
                                            </div> 
                                        </div>
                                        
                                    </div>
							
							
                                    <div class="row">
                                        <div class="col-md-12">
                                           
                                        </div>
                                    </div>
                                    <div class="text-right">
                                        <input type="submit" id="clicked" class="btn btn-info btn-fill btn-wd" value="Add" >
                                      
                                    </div>
                                
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>


    </div>
</div>
		
</form> 
	
</body>
</html>