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
    <title>Indian Coast Guard</title>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    
<%@include file="..//view/commonJavaScript.jsp" %>

<script type="text/javascript">
nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
	$j("#btnActive").hide();
	$j("#btnInActive").hide();		
	$j('#updateBtn').attr("disabled", true);
	$j('#updateBtn').hide();
			
	GetAllEmpanelledHospital('ALL');
			
		});
function GetAllEmpanelledHospital(MODE){
	 
	 var empanelledHospitalId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo};			
		}else{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllEmpanelledHospital";		
	var bClickable = true;
	GetJsonData('tblListOfEmpanelledHospital',data,url,bClickable);	 
}

function makeTable(jsonData)
{	
	var htmlTable = "";	
	var data = jsonData.count; 
	
	var pageSize = 5; 	
	var dataList = jsonData.data; 
	
	
	for(i=0;i<dataList.length;i++)
		{		
		
		 if(dataList[i].status == 'Y' || dataList[i].status == 'y')
				{
					var Status='Active'
				}
			else
				{
					var Status='InActive'
				} 		
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].empanelledHospitalId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].empanelledHospitalCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].empanelledHospitalName+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].empanelledHospitalAddress+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfEmpanelledHospital").html(htmlTable); 	
	
}


var empanelledHospId;
var empanelledHospCode;
var empanelledHospName;
var empanelledHospAddress;
var empanelledHospStatus;

function executeClickEvent(empanelledHospitalId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(empanelledHospitalId == data.data[j].empanelledHospitalId){
			empanelledHospId = data.data[j].empanelledHospitalId;			
			empanelledHospCode = data.data[j].empanelledHospitalCode;			
			empanelledHospName = data.data[j].empanelledHospitalName;
			empanelledHospAddress = data.data[j].empanelledHospitalAddress;
			empanelledHospStatus = data.data[j].status;
			
			
		}
	}
	rowClick(empanelledHospId,empanelledHospCode,empanelledHospName,empanelledHospAddress,empanelledHospStatus);
}

function rowClick(empanelledHospId,empanelledHospCode,empanelledHospName,empanelledHospAddress,empanelledHospStatus){	
		
	document.getElementById("empanelledHospitalCode").value = empanelledHospCode;
	document.getElementById("empanelledHospitalName").value = empanelledHospName;
	document.getElementById("empanelledHospitalAddress").value = empanelledHospAddress;
			 
	if(empanelledHospStatus == 'Y' || empanelledHospStatus == 'y'){		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').attr("disabled", false);
	}
	if(empanelledHospStatus == 'N' || empanelledHospStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j('#updateBtn').attr("disabled", true);
	}
	
	$j('#updateBtn').show();
	$j("#btnActive").attr("disabled", false);
	$j("#btnInActive").attr("disabled", false);
	$j('#btnAddEmpanelledHospital').attr("disabled", true);
	
}


function searchEmpanelledHospitalList(){	
	if(document.getElementById('searchEmpanelledHospital').value == "" || document.getElementById('searchEmpanelledHospital') == null){
		 alert("Plese Enter the Hospital Name");
		 return false;
	 }
		 	 
	 var empHospName= jQuery("#searchEmpanelledHospital").attr("checked", true).val().toUpperCase();
		
		var nPageNo=1;
		var url = "getAllEmpanelledHospital";
		var data =  {"PN":nPageNo, "empanelledHospitalName":empHospName};
		var bClickable = true;
		GetJsonData('tblListOfEmpanelledHospital',data,url,bClickable);	
		ResetForm();
}

function addEmpanelledHospitalDetails(){
	
	if(document.getElementById('empanelledHospitalCode').value == null || document.getElementById('empanelledHospitalCode').value == ""){
		alert("Please Enter Hospital Code");
		return false;
	}
	if(document.getElementById('empanelledHospitalName').value == null || document.getElementById('empanelledHospitalName').value ==""){
		alert("Please Enter Hospital Name");
		return false;
	}
	if(document.getElementById('empanelledHospitalAddress').value == null || document.getElementById('empanelledHospitalAddress').value ==""){
		alert("Please Enter Address");
		return false;
	}
	
	var params = {			 
			 'empanelledHospitalCode':jQuery('#empanelledHospitalCode').val(),
			 'empanelledHospitalName':jQuery('#empanelledHospitalName').val(),
			 'empanelledHospitalAddress':jQuery('#empanelledHospitalAddress').val()
	 } 
	
	//alert("params: "+JSON.stringify(params)); 
	
		jQuery.ajax({
	 crossOrigin: true,
	    method: "POST",
	    header:{
	    	'Access-Control-Allow-Origin': '*'
	    	},
	    	crossDomain:true,
	    url: "addEmpanelledHospital",
	    data: JSON.stringify(params),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	        //alert("result" +result);
	         console.log(result);
	        if(result.status==1){	        	
	        	   	
	        	showAll('ALL')	        	
	        	document.getElementById("messageId").innerHTML = result.msg;
	        	$j('#messageId').toggle(5000);
	        }
	        if(result.status==2){
	        	
	        	showAll('ALL')
	        	document.getElementById("messageId").innerHTML = result.msg;
	        	$j('#messageId').toggle(5000);
	        }
	        if(result.status == 0){
	        	document.getElementById("messageId").innerHTML = result.msg;
	        	$j('#messageId').toggle(5000);
	        }
	    }
	    
	    
	});	
	
}

function updateEmpanelledHospital(status){
	//alert("status :: "+status)
	if(document.getElementById('empanelledHospitalCode').value == null || document.getElementById('empanelledHospitalCode').value == ""){
		alert("Please Enter Hospital Code");
		return false;
	}
	if(document.getElementById('empanelledHospitalName').value == null || document.getElementById('empanelledHospitalName').value ==""){
		alert("Please Enter Hospital Name");
		return false;
	}
	if(document.getElementById('empanelledHospitalAddress').value == null || document.getElementById('empanelledHospitalAddress').value ==""){
		alert("Please Enter Address");
		return false;
	}
	
	var params = {
			 'empanelledHospitalId':empanelledHospId,
			 'empanelledHospitalCode':jQuery('#empanelledHospitalCode').val(),
			 'empanelledHospitalName':jQuery('#empanelledHospitalName').val(),
			 'empanelledHospitalAddress':jQuery('#empanelledHospitalAddress').val(),
			 'status':status
			 
		} 
	//alert("params: "+JSON.stringify(params)); 
	 jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateEmpanelledHospital",
		    data: JSON.stringify(params),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){
		        //alert("result" +result);
		        console.log(result);
		        if(result.status==1){
		        	
		        	showAll('ALL')
		        	document.getElementById("messageId").innerHTML = result.msg;
		        	$j('#messageId').toggle(5000);
		        }
		        if(result.status==0){
		        	
		        	showAll('ALL')
		        	document.getElementById("messageId").innerHTML = result.msg;
		        	$j('#messageId').toggle(5000);
		        }
		    }
		    
		    
		});
	$j("#btnActive").attr("disabled", true);
	$j("#btnInActive").attr("disabled", true);
	$j('#updateBtn').attr("disabled", true);
	ResetForm();
}


function Reset(){
	document.getElementById("searchEmpanelledHospitalForm").reset();
	document.getElementById("addEmpanelledHospitaForm").reset();
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddEmpanelledHospital').attr("disabled", false);
	
}	


function ResetForm()
{
	$j('#empanelledHospitalCode').val('');
	$j('#empanelledHospitalName').val('');
	$j('#empanelledHospitalAddress').val('');
	$j('#searchEmpanelledHospital').val('');
	
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetAllEmpanelledHospital('ALL');
	
}

 function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetAllEmpanelledHospital('FILTER');
	
} 
 
 

</script>   

</head>

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
                                <h5 class="text-overflow"><small>Welcome ! Empanelled Hospital Master</small> </h5>
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
                    <!-- <li class="hide-phone app-search"> -->
                        <!-- <form role="search" class=""> -->
                            <!-- <input type="text" placeholder="Search..." class="form-control"> -->
                            <!-- <a href="#"><i class="fa fa-search"></i></a> -->
                        <!-- </form> -->
                    <!-- </li> -->
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
                                <h4 class="page-title float-left">Empanelled Hospital Master</h4>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">Empanelled Hospital Master</li>
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
                                            <form class="form-horizontal" id="searchEmpanelledHospitalForm" name="searchEmpanelledHospitalForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Empanelled Hospital Name<span style="color:red">*</span></label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Empanelled Hospital Name<span style="color:red">*</span></label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchEmpanelledHospital" id="searchEmpanelledHospital" class="form-control" id="inlineFormInputGroup" placeholder="Empanelled Hospital Name" onkeypress="return validateTextField('searchEmpanelledHospital',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn  btn-primary" onclick="searchEmpanelledHospitalList();">Search</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                        
                                        <div class="col-md-4">
                                            <form>
                                                <div class="button-list">
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" onclick="GetAllEmpanelledHospital('ALL');">Show All</button>
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" onclick="generateEmpanelledHospitalReport();">Reports</button>

                                                </div>
                                            </form>
                                        </div>

                                    </div>

                                    

					<!-- <table class="table table-striped table-hover jambo_table"> -->
                    <div style="float:left">					           
		                                    <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >			<tr>
												<td class="SearchStatus" style="font-size: 15px;" align="left">Search Results</td>
												<td>
												 <!-- <div id=resultnavigation></div> -->
												
												</td>
												</tr>
										</table>
		                                 </div>     
		                                    <div style="float:right">
				                                    <div id="resultnavigation">
				                                    </div> 
		                                    </div> 

                                    <table class="table table-hover table-bordered">
                                        <thead class="bg-success" style="color:#fff;">
                                            <tr>
                                                <th id="th2" class ="inner_md_htext">Empanelled Hospital Code</th>
                                                <th id="th3" class ="inner_md_htext">Empanelled Hospital Name</th>
                                                <th id="th4" class ="inner_md_htext">Address</th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                     <tbody id="tblListOfEmpanelledHospital">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addEmpanelledHospitaForm" name="addEmpanelledHospitaForm" action="" method="POST">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            <label for="Empanelled Hospital Code" class=" col-form-label inner_md_htext" >Empanelled Hospital Code<span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <input type="text" name="empanelledHospitalCode" id="empanelledHospitalCode" class="form-control" placeholder="Empanelled Hospital Code" onkeypress="return validateTextField('empanelledHospitalCode',7);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            <label for="service" class="col-form-label inner_md_htext">Empanelled Hospital Name <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <input type="text" name="empanelledHospitalName" id="empanelledHospitalName" class="form-control" placeholder="Empanelled Hospital Name" onkeypress="return validateTextField('empanelledHospitalName',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-4">
                                                            <label for="service" class="col-form-label inner_md_htext">Address <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-8">
                                                                <input type="text" name="empanelledHospitalAddress" id="empanelledHospitalAddress" class="form-control" placeholder="Address" onkeypress="return validateTextField('empanelledHospitalAddress',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                   
                                                </div>
                                            </form>
                                        </div>

                                    </div>
									<br>	
                                    <div class="row">
                                        <div class="col-md-7">
                                        </div>
                                        <div class="col-md-5">
                                            <form>
                                                <div class="button-list">

                                                    <button id="btnAddEmpanelledHospital" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addEmpanelledHospitalDetails();">Add</button>
                                                    <button id ="updateBtn" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateEmpanelledHospital('update');">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateEmpanelledHospital('active');">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateEmpanelledHospital('inactive');">DeActivate</button>
                                                    <button type="button" class="btn btn-danger btn-rounded w-md waves-effect waves-light" onclick="Reset();">Reset</button>

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
                Empanelled Hospital Master
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