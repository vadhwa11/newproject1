<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@include file="..//view/leftMenu.jsp" %>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Indian Cost Gaurd</title>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    
<%--     <link href="${pageContext.request.contextPath}/resources/css/sweetalert2.min.css" rel="stylesheet"> --%>  
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/metismenu.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/app.css" rel="stylesheet" type="text/css" />    
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
    
    <script src="${pageContext.request.contextPath}/resources/js/modernizr.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/metisMenu.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/waves.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.scrollTo.min.js"></script>    
	 <script src="${pageContext.request.contextPath}/resources/js/jquery.core.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.app.js"></script>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>

<% int hospitalID = 1; %>

</head>

<script type="text/javascript" language="javascript">

$(document).ready(function()
		{
			var i = 0;
			var dictionary = ${data};
			
			for (item in dictionary) {
				i++;
				if(i == 1){
				  for (subItem in dictionary[item]) {
					
					$('#service').val(dictionary[item][subItem].serviceNo);
					$('#patient').val(dictionary[item][subItem].patientName);
					$('#age').val(dictionary[item][subItem].age);
					$('#gender').val(dictionary[item][subItem].gender);		
					$('#header_id').val(dictionary[item][subItem].header_id);
				    
				  }
				}else if(i == 2){					
					var tableData = "<tbody>";
					for (subItem in dictionary[item]) {
						tableData += '<tr><td>'+dictionary[item][subItem].date+'</td><td>'+dictionary[item][subItem].month+'</td><td>'+dictionary[item][subItem].height+'</td><td>'+dictionary[item][subItem].weight+'</td><td>'+dictionary[item][subItem].idealWeight+'</td><td>'+dictionary[item][subItem].variation+'</td><td>'+dictionary[item][subItem].bmi+'</td></tr>';						
					  }
					tableData += '</tbody>';					
					$('#tableId').append(tableData);
					var addNewRow = '<tr><td><div><input class="form-control" id="createdDate" type="text" value="'+formatDate()+'"></div></td><td><select id="months"></select></td><td><div><input class="form-control" id="height" type="text" onblur="getIdealWeight()"></div></td><td><div ><input class="form-control" id="weight" type="text" onblur="getVariation()"></div></td><td><div><input class="form-control" id="idealWeight" type="text" readonly></div></td><td><div><input class="form-control"  id="variant_in_weight" type="text"></div></td><td><div><input class="form-control" id="bmi" type="text"></div></td></tr>';
					$('#tableId').append(addNewRow);
					
					
					
					//months in select option
					var optionsValue = '';
					var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ];
					for(var i=0;i<monthNames.length;i++){
						optionsValue += '<option>'+monthNames[i]+'</option>'	
					}
					$('#months').append(optionsValue);
					
					
				}
				
				}				
			
		});
		
function getIdealWeight(){
	
  	var params = {
			"age" : $('#age').val(),
			"height" : $('#height').val()
	}
  	
	 $j.ajax({
			type:"POST",
			contentType : "application/json",
			url: 'getIdealWeight',
			data : JSON.stringify(params),
			dataType: "json",			
			cache: false,
			success: function(data)
			{  
				//alert("success "+data.data.idealWeight);
				
				$('#idealWeight').val(data.data[0].idealWeight);				
				
			},			
			error: function(data)
			{							
				
				alert("An error has occurred while contacting the server");
				
			}
	});   
	
}

	function getVariation() {
		var a = document.getElementById("idealWeight").value;
		var b = document.getElementById("weight").value;
		if (a > b) {
			var sub = a - b;
			var cal = sub * 100 / a
			var n = cal.toFixed(2);
			$('#variant_in_weight').val("-" + n);
		} else {
			var eadd = b - a;
			var cal1 = eadd * 100 / b
			var n1 = cal1.toFixed(2);
			$('#variant_in_weight').val("+" + n1);
		}
	}

	function getBMI() {
		alert("get bmi");
	}

	function formatDate() {
		var d = new Date(new Date()), month = '' + (d.getMonth() + 1), day = ''
				+ d.getDate(), year = d.getFullYear();

		if (month.length < 2)
			month = '0' + month;
		if (day.length < 2)
			day = '0' + day;

		return [day, month, year].join('-');
	}
	
	function saveObesityDetails(){
		
		var obesity_date = $('#createdDate').val();
		var month_name = $('#months').find('option:selected').text();
		var height = +($('#height').val());
		var weight = +($('#weight').val());
		var ideal_weight = +($('#idealWeight').val());
		var variation = $('#variant_in_weight').val();
		var bmi = +($('#bmi').val());
		var header_id = +($('#header_id').val());
		var obesity_check = document.getElementById('obesity_check');
		if(obesity_check.checked){
			obesity_check = "y";
		}
		
		var params = {
				"obesity_date" : obesity_date,
				"month_name" : month_name,
				"height" : height,
				"weight" : weight,
				"ideal_weight" : ideal_weight,
				"variation" : variation,
				"bmi" : bmi,
				"header_id": header_id,
				"obesity_check":obesity_check
			}
		
		 $j.ajax({
				type:"POST",
				contentType : "application/json",
				url: 'saveObesityDetails',
				data : JSON.stringify(params),
				dataType: "json",			
				cache: false,
				success: function(data)
				{  
					
					alert(data.msg);		
					
				},			
				error: function(data)
				{							
					
					alert("An error has occurred while contacting the server");
					
				}
		}); 
		
	}
	
	function getoObesityList(){
		window.location = "obesityWaitingJsp";
	}
	
</script>
<body>

    <!-- Begin page -->
    <div id="wrapper">

        <!-- Top Bar Start -->
        <div class="topbar">

            <!-- LOGO -->
            <div class="topbar-left">
                <a href="index.html" class="logo">
                    <span>
                            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="" height="18">
                        </span>
                    <i>
                            <img src="${pageContext.request.contextPath}/resources/images/logo_sm.png" alt="" height="22">
                        </i>
                </a>
            </div>

            <nav class="navbar-custom">
                <ul class="list-inline float-right mb-0">
                
                    <li class="list-inline-item dropdown notification-list">
                        <a class="nav-link dropdown-toggle nav-user" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                            <i class="noti-icon"><img src="${pageContext.request.contextPath}/resources/images/users/avatar-1.jpg" alt="user" class="img-fluid rounded-circle"></i>
                            <span class="profile-username ml-2 text-dark">Username </span> <span class="mdi mdi-menu-down text-dark"></span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-animated dropdown-menu-right profile-dropdown ">
                            <!-- item-->
                            <div class="dropdown-item noti-title">
                                <h5 class="text-overflow"><small>Patient Obesity Details</small> </h5>
                            </div>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-account-circle"></i> <span>Profile</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-settings"></i> <span>Settings</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-lock-open"></i> <span>Lock Screen</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-power"></i> <span>Logout</span>
                            </a>

                        </div>
                    </li>

                </ul>

                <ul class="list-inline menu-left mb-0">
                    <li class="float-left">
                        <button class="button-menu-mobile open-left waves-light waves-effect">
                            <i class="mdi mdi-menu"></i>
                        </button>
                    </li>
                    
                </ul>

            </nav>

        </div>
        <!-- Top Bar End -->

        <!-- ========== Left Sidebar Start ========== -->
        
        <!-- Left Sidebar End -->

        <!-- ============================================================== -->
        <!-- Start right Content here -->
        <!-- ============================================================== -->
        <div class="content-page">
            <!-- Start content -->
            <div class="">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <div class="page-title-box">
                                <h4 class="page-title float-left">Patient Obesity Details</h4>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">Patient Obesity Details</li>
                                </ol>

                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                       <br>
                                    <div class="row">
                                                                     
                                        <div class="col-md-8">
                                            <form class="form-horizontal" name="searchRelationForm" id="searchRelationForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Service No</label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Service No</label>
                                                            <div class="input-group mb-2">
                                                                
                                                                <input type="text" name="service" id="service" class="form-control" id="inlineFormInputGroup" placeholder="Service No">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Patient</label>
                                                            <div class="input-group mb-2">
                                                                
                                                                <input type="text" name="patient" id="patient" class="form-control" id="inlineFormInputGroup" placeholder="Patient">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Age</label>
                                                            <div class="input-group mb-2">
                                                                
                                                                <input type="text" name="age" id="age" class="form-control" id="inlineFormInputGroup" placeholder="Age">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Gender</label>
                                                            <div class="input-group mb-2">
                                                                
                                                                <input type="text" name="gender" id="gender" class="form-control" id="inlineFormInputGroup" placeholder="Gender">
                                                                <input type="hidden" id="header_id">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button id="searchBtn" type="button" class="btn  btn-primary" onclick="searchRelationList();">Search</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                       

                                    </div>

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination float-right">
                                                    <li class="page-item">
                                                        <a href="#" aria-label="Previous" class="page-link">
                                                            <i class="fa fa-angle-left"></i>
                                                        </a>
                                                    </li>
                                                    
                                                    <li class="page-item">
                                                        <a href="#" aria-label="Next" class="page-link">
                                                            <i class="fa fa-angle-right"></i>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </nav>
                                        </div>
                                    </div>

					<!-- <table class="table table-striped table-hover jambo_table"> -->
                   <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >			<tr>
				<td class="SearchStatus" style="font-size: 15px;" align="left">Search Results</td>
				<td>
				<div id=resultnavigation></div>
				
				</td>
				</tr>
		</table>

                                    <table class="table table-hover table-bordered">
                                        <thead class="bg-success" style="color:#fff;">
                                            <tr>
                                                <th id="th2" class ="inner_md_htext">Date</th>
                                                <th id="th3" class ="inner_md_htext">Month</th>
                                                <th id="th4" class ="inner_md_htext"> Height</th>
                                                <th id="th5" class ="inner_md_htext"> Weight</th>
                                                <th id="th6" class ="inner_md_htext">Ideal Weight</th>
                                                <th id="th7" class ="inner_md_htext">Variation in weight</th>
                                                <th id="th8" class ="inner_md_htext">BMI</th>
                                            </tr>
                                        </thead>
                                     
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            
                                        </div>

                                    </div>
									<br>	
                                    <div class="row">
                                        <div class="col-md-7">
                                        </div>
                                        <div class="col-md-5">
                                            <form>
                                                <div class="button-list">
										<input type="checkbox" id='obesity_check'> Obesity monitoring not required<br>
                                                    <button id="" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="saveObesityDetails();">Submit</button>
                                                    <button id ="" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="getoObesityList();">Back To Obesity List</button>
                                                    
                                                </div>
                                            </form>
                                        </div>

                                    </div>

                                    <!-- end row -->

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
            <!-- content -->

            <footer class="footer">
                Patient Obesity Details
            </footer>

        </div>

        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

    </div>
    <!-- END wrapper -->

    <!-- jQuery  -->
    

</body>

</html>