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
	$j('#employeeCategoryCode').attr('readonly', false);		
		GetAllEmployeeCategory('ALL');			
		});
		
function GetAllEmployeeCategory(MODE){
	document.getElementById('searchEmployeeCategory').value = "";
	 var employeeCategoryId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo};			
		}else{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllEmployeeCategory";		
	var bClickable = true;
	GetJsonData('tblListOfEmployeeCategory',data,url,bClickable);	 
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
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].employeeCategoryId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].employeeCategoryCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].employeeCategoryName+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfEmployeeCategory").html(htmlTable); 	
	
}

var eCatId;
var eCatCode;
var eCatName;
var eCatStatus;

function executeClickEvent(employeeCategoryId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(employeeCategoryId == data.data[j].employeeCategoryId){
			eCatId = data.data[j].employeeCategoryId;			
			eCatCode = data.data[j].employeeCategoryCode;			
			eCatName = data.data[j].employeeCategoryName;
			eCatStatus = data.data[j].status;
			
			
		}
	}
	rowClick(eCatId,eCatCode,eCatName,eCatStatus);
}

function rowClick(eCatId,eCatCode,eCatName,eCatStatus){	
		
	document.getElementById("employeeCategoryCode").value = eCatCode;
	document.getElementById("employeeCategoryName").value = eCatName;
	
			 
	if(eCatStatus == 'Y' || eCatStatus == 'y'){		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').show();
	}
	if(eCatStatus == 'N' || eCatStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j('#updateBtn').hide();
	}
	
	$j("#btnActive").attr("disabled", false);
	$j("#btnInActive").attr("disabled", false);
	$j('#updateBtn').attr("disabled", false);
	$j('#btnAddEmployeeCategory').attr("disabled", true);
	$j('#employeeCategoryCode').attr('readonly', true);
	
}

function searchEmployeeCategoryList(){	
	if(document.getElementById('searchEmployeeCategory').value == "" || document.getElementById('searchEmployeeCategory') == null){
		 alert("Plese Enter the EmployeeCategory Name");
		 return false;
	 }
		 	 
	 var employeeCategoryName= jQuery("#searchEmployeeCategory").attr("checked", true).val().toUpperCase();
		
		var nPageNo=1;
		var url = "getAllEmployeeCategory";
		var data =  {"PN":nPageNo, "employeeCategoryName":employeeCategoryName};
		var bClickable = true;
		GetJsonData('tblListOfEmployeeCategory',data,url,bClickable);		
}

var success;
var error;

function addEmployeeCategoryDetails(){
	if(document.getElementById('employeeCategoryCode').value == null || document.getElementById('employeeCategoryCode').value == ""){
		alert("Please Enter EmployeeCategory Code");
		return false;
	}
	if(document.getElementById('employeeCategoryName').value == null || document.getElementById('employeeCategoryName').value ==""){
		alert("Please Enter EmployeeCategory Name");
		return false;
	}
	
	
	var params = {			 
			 'employeeCategoryCode':jQuery('#employeeCategoryCode').val(),
			 'employeeCategoryName':jQuery('#employeeCategoryName').val()			 			 
	 } 
	   var url = "addEmployeeCategory";
	   SendJsonData(url,params);
	    
}

function updateEmployeeCategory(){	
	if(document.getElementById('employeeCategoryCode').value == null || document.getElementById('employeeCategoryCode').value == ""){
		alert("Please Enter EmployeeCategory Code");
		return false;
	}
	if(document.getElementById('employeeCategoryName').value == null || document.getElementById('employeeCategoryName').value ==""){
		alert("Please Enter EmployeeCategory Name");
		return false;
	}
		
	
	var params = {
			 'employeeCategoryId':eCatId,
			 'employeeCategoryCode':jQuery('#employeeCategoryCode').val(),
			 'employeeCategoryName':jQuery('#employeeCategoryName').val()			 
			
	 } 
		    var url = "updateEmployeeCategoryDetails";
		    SendJsonData(url,params);
		
		    $j('#updateBtn').attr("disabled", true);
			$j('#btnAddEmployeeCategory').attr("disabled", false);
			$j('#employeeCategoryCode').attr('readonly', true);
			ResetFrom();
}

function updateEmployeeCategoryStatus(){
	if(document.getElementById('employeeCategoryCode').value == null || document.getElementById('employeeCategoryCode').value == ""){
		alert("Please Enter EmployeeCategory Code");
		return false;
	}
	if(document.getElementById('employeeCategoryName').value == null || document.getElementById('employeeCategoryName').value ==""){
		alert("Please Enter EmployeeCategory Name");
		return false;
	}
	
	var params = {
			'employeeCategoryId':eCatId,
			 'employeeCategoryCode':eCatCode,
			 'status':eCatStatus
			 
		} 
		    var url = "updateEmployeeCategoryStatus";
	        SendJsonData(url,params);
	 
	     $j("#btnActive").attr("disabled", true);
	   	 $j("#btnInActive").attr("disabled", true);
	   	 $j('#btnAddEmployeeCategory').attr("disabled", false);
}

function Reset(){
	document.getElementById("addEmployeeCategoryForm").reset();
	document.getElementById("searchEmployeeCategoryForm").reset();
	document.getElementById('searchEmployeeCategory').value = "";
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddEmployeeCategory').attr("disabled", false);
	document.getElementById("messageId").innerHTML = "";
	$("#messageId").css("color", "black");
	
}



function ResetForm()
{	
	$j('#employeeCategoryCode').val('');
	$j('#employeeCategoryName').val('');
	$j('#searchEmployeeCategory').val('');
	
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetAllEmployeeCategory('ALL');
	
}

 function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetAllEmployeeCategory('FILTER');
	
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
                                <h5 class="text-overflow"><small>Welcome ! EmployeeCategory Master</small> </h5>
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
                                <h4 class="page-title float-left">Employee Category Master</h4>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">Laboratory Related</li>
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
                                            <form class="form-horizontal" id="searchEmployeeCategoryForm" name="searchEmployeeCategoryForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Employee Category Name <span style="color:red">*</span> </label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Employee Category Name</label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchEmployeeCategory" id="searchEmployeeCategory" class="form-control" id="inlineFormInputGroup" placeholder="Employee Category Name">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn  btn-primary" onclick="searchEmployeeCategoryList();">Search</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                        
                                        
                                        
                                        
                                        <div class="col-md-4">
                                            <form>
                                                <div class="button-list">
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" onclick="showAll('ALL');">Show All</button>
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light">Reports</button>

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
                   <!-- <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >			<tr>
				<td class="SearchStatus" style="font-size: 15px;" align="left">Search Results</td>
				<td>
				<div id=resultnavigation></div>
				
				</td>
				</tr>
		</table> -->
		
			<div style="float:left">
               
                                     <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >   <tr>
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
                                                <th id="th2" class ="inner_md_htext">Employee Category Code</th>
                                                <th id="th3" class ="inner_md_htext">Employee Category Name</th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                        <!--  <tbody id="tblListOfEmployeeAndDepenent">   </tbody>  --->
                                     <tbody id="tblListOfEmployeeCategory">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addEmployeeCategoryForm" name="addEmployeeCategoryForm" method="">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Employee Category Code" class=" col-form-label inner_md_htext" >Employee Category Code <span style="color:red">*</span> </label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" id="employeeCategoryCode" name="employeeCategoryCode" placeholder="Employee Category Code" onkeypress=" return validateText('employeeCategoryCode',3);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Employee Category Name <span style="color:red">*</span> </label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" id="employeeCategoryName" name="employeeCategoryName" placeholder="Employee Category Name" onkeypress="return validateTextField('employeeCategoryName',30);" >
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

                                                    <button type="button" id="btnAddEmployeeCategory" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addEmployeeCategoryDetails();">Add</button>
                                                    <button type="button" id ="updateBtn" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateEmployeeCategory();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateEmployeeCategoryStatus();">Active</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateEmployeeCategoryStatus();">InActive</button>
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
               ROLE MASTER
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