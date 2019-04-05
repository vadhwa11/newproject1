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

<script type="text/javascript">
var nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
			GetStateList('ALL');
	
		});
function GetStateList(MODE)
{
	var cmdId=0;
	if(MODE == 'ALL'){
			var data = {"PN":nPageNo};
		}
	else{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllCommandDetails";		
	var bClickable = true;
	GetJsonData('tblListOfState',data,url,bClickable);
}

</script>

</head>
<body bgcolor="">

<div class="wrapper">
	<div class="sidebar" data-background-color="white" data-active-color="danger">
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
                    <a class="navbar-brand" href="#">State Master</a>
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
                                <h4 class="title">State Master</h4>
                            </div>
                            <div class="content">
    <form id="searchStateForm" name="searchStateForm" action="" method="POST"> 
                            
                             <!-- search div  -->
                                   <div class="row">
                                   <div class="row">
                                       <div class="col-md-2">  
                                              <div class="form-group">   
                                                <label>State Name</label>
                                               
                                                </div>
                                            </div> 
                                        
                                         <div class="col-md-4">
                                             <div class="form-group">
                                                
                                         <input name="searchState" id="searchState" type="text" class="form-control border-input" placeholder="state" value="">
                                            
                                            </div> 
                                        </div>
                                   <div class="col-md-2">  
                                    <div class="text-right">
                                     <button type="button" class="btn" id="button" onclick="">Search</button>                                    
                                     </div> 
                                     </div>
                                     
                                     <div class="col-md-2">
                                     <div class="text-right">
                                     <button type="button" class="btn" id="button" onclick="">Show ALL</button>                                    
                                     </div>
                                    </div>
                                        
                                </div>
                            
                            </div>
                            
                            </form>                        
                            
      <!-- Data Grid -->
<div class="right_col" role="main">
   <div class="">
         <div class="clearfix"></div>
              <div class="col-md-12 col-sm-12 col-xs-12, col-xs-12">
                <div class="x_panel">                 
                  <!-- <div class="x_content"> -->
                   <div class="content table-responsive table-full-width">
					
                   <!-- <table class="table table-striped table-hover jambo_table"> -->
                    <table class="table table-bordered ">
                    
                      <thead bgcolor="00FFFF">                        
                        <tr>                          
                          <th id="th1">State Code</th>
                          <th id="th2">State Name.</th>
                          <th id="th3">Status</th>
                          <th id="th3">Description</th>
                                                    
                        </tr>
                      </thead>
                      <tbody id="tblListOfState">
                       
                    
                      </tbody>
                    </table>

                  </div>
                </div>
              </div>

              
            </div>
          </div>                      
          <form id="addStateForm" name="addStateForm" action="<%=request.getContextPath()%>/v0.1/master/addStateDetails" method="POST"> 
              <div class="row">
               <div class="col-md-4">
   	            <div class="form-group">
                                  <label>State Code</label>
                                                <input name="stateCode" id="stateCode" type="text" class="form-control border-input" placeholder="State Code" value=""  />
                                            </div> 
                                        </div>
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>State Name</label>
                                                <input name="stateName" id="stateName" type="text" class="form-control border-input" placeholder="State Name" value="" >
                                            </div> 
                                        </div>
                                        
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Description</label>
                                                <input name="description" id="description" type="text" class="form-control border-input" placeholder="description" value="" >
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

</body>
</html>