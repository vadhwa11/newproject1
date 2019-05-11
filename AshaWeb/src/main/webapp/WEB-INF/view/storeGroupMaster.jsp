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
	$j('#updateBtn').hide();	  
	$j('#groupCode').attr('readonly', false);		
		GetAllStoreGroup('ALL');			
		});
		
function GetAllStoreGroup(MODE){
	
	var groupName= jQuery("#searchStoreGroup").attr("checked", true).val().toUpperCase();
	
	document.getElementById('searchStoreGroup').value = "";
	 var groupId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo, "groupName":""};			
		}else{
		var data = {"PN":nPageNo, "groupName":groupName};
		} 
	var url = "getAllStoreGroup";		
	var bClickable = true;
	GetJsonData('tblListOfStoreGroup',data,url,bClickable);	 
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
					var Status='Inactive'
				} 		
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].groupId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].groupCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].groupName+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfStoreGroup").html(htmlTable); 	
	
}

var sgId;
var sgCode;
var sgName;
var sgStatus;

function executeClickEvent(groupId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(groupId == data.data[j].groupId){
			sgId = data.data[j].groupId;			
			sgCode = data.data[j].groupCode;			
			sgName = data.data[j].groupName;
			sgStatus = data.data[j].status;
			
			
		}
	}
	rowClick(sgId,sgCode,sgName,sgStatus);
}

function rowClick(sgId,sgCode,sgName,sgStatus){	
		
	document.getElementById("groupCode").value = sgCode;
	document.getElementById("groupName").value = sgName;
	
			 
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
	$j('#btnAddStoreGroup').hide();
	$j('#groupCode').attr('readonly', true);
	
}

function searchStoreGroupList(){	
	if(document.getElementById('searchStoreGroup').value == "" || document.getElementById('searchStoreGroup') == null){
		 alert("Plese Enter the Group Name");
		 return false;
	 }
		 	 
	 var groupName= jQuery("#searchStoreGroup").attr("checked", true).val().toUpperCase();
		
		var nPageNo=1;
		var url = "getAllStoreGroup";
		var data =  {"PN":nPageNo, "groupName":groupName};
		var bClickable = true;
		GetJsonData('tblListOfStoreGroup',data,url,bClickable);		
}

var success;
var error;

function addStoreGroup(){
	if(document.getElementById('groupCode').value == null || document.getElementById('groupCode').value == ""){
		alert("Please Enter Group Code");
		return false;
	}
	if(document.getElementById('groupName').value == null || document.getElementById('groupName').value ==""){
		alert("Please Enter Group Name");
		return false;
	}
	
	
	var params = {			 
			 'groupCode':jQuery('#groupCode').val(),
			 'groupName':jQuery('#groupName').val()			 			 
	 } 
	   var url = "addStoreGroup";
	   SendJsonData(url,params);
	    
}

function updateStoreGroup(){	
	if(document.getElementById('groupCode').value == null || document.getElementById('groupCode').value == ""){
		alert("Please Enter Group Code");
		return false;
	}
	if(document.getElementById('groupName').value == null || document.getElementById('groupName').value ==""){
		alert("Please Enter Group Name");
		return false;
	}
		
	
	var params = {
			 'groupId':sgId,
			 'groupCode':jQuery('#groupCode').val(),
			 'groupName':jQuery('#groupName').val()			 
			
	 } 
		    var url = "updateStoreGroupDetails";
		    SendJsonData(url,params);
		
		    $j('#updateBtn').attr("disabled", true);
			$j('#btnAddStoreGroup').attr("disabled", false);
			$j('#groupCode').attr('readonly', true);
			ResetFrom();
}

function updateStoreGroupStatus(){
	if(document.getElementById('groupCode').value == null || document.getElementById('groupCode').value == ""){
		alert("Please Enter Group Code");
		return false;
	}
	if(document.getElementById('groupName').value == null || document.getElementById('groupName').value ==""){
		alert("Please Enter Group Name");
		return false;
	}
	
	var params = {
			'groupId':sgId,
			 'groupCode':sgCode,
			 'status':sgStatus
			 
		} 
		    var url = "updateStoreGroupStatus";
	        SendJsonData(url,params);
	 
	     $j("#btnActive").attr("disabled", true);
	   	 $j("#btnInActive").attr("disabled", true);
	   	 $j('#btnAddStoreGroup').attr("disabled", false);
}

function Reset(){
	document.getElementById("addStoreGroupForm").reset();
	document.getElementById("searchStoreGroupForm").reset();
	document.getElementById('searchStoreGroup').value = "";
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddStoreGroup').attr("disabled", false);
	document.getElementById("messageId").innerHTML = "";
	$("#messageId").css("color", "black");
	$j('#groupCode').attr('readonly', false);
	showAll();
}



function ResetForm()
{	
	$j('#groupCode').val('');
	$j('#groupName').val('');
	$j('#searchStoreGroup').val('');
	
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetAllStoreGroup('ALL');
	
}

 function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetAllStoreGroup('FILTER');
	
} 

 function search()
 {
 	if(document.getElementById('searchStoreGroup').value == ""){
 		alert("Please Enter Group Name");
 		return false;
 	}
 	nPageNo=1;
 	GetAllStoreGroup('Filter');
 }
 
 /* function generateReport()
 {
 	document.searchStoreGroupForm.action="${pageContext.request.contextPath}/report/generateEmployeeCategoryReport";
 	document.searchStoreGroupForm.method="POST";
 	document.searchStoreGroupForm.submit(); 
 	
 } */
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
                <%-- <ul class="list-inline float-right mb-0">
                
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

                </ul> --%>

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
                <div class="internal_Htext">Store Group Master</div>
                    
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                       <br>
                                       <div class="row">
                                            <div class="col-md-8">
											<form class="form-horizontal" id="searchStoreGroupForm"
												name="searchStoreGroupForm" method="" role="form">
												<div class="form-group row">
													<label class="col-3 col-form-label">Group Name<span
														style="color: red">*</span></label>
													<div class="col-4">

														<input type="text" name="searchStoreGroup" id="searchStoreGroup"
															class="form-control" id="inlineFormInputGroup"
															placeholder="Group Name">

													</div>
													<div class="form-group row">

														<div class="col-md-12">
															<button type="button" class="btn  btn-primary "
																onclick="search()">Search</button>
														</div>
													</div>
												</div>
											</form>

										</div>
										<div class="col-md-4">
											<div class="btn-right-all">
												<button type="button" class="btn  btn-primary "
													onclick="showAll('ALL');">Show All</button>
												<button type="button" class="btn  btn-primary "
													onclick="generateReport()">Reports</button>
											</div>
										</div>
									</div>
                                    <%-- <div class="row">
                                                                     
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
                                                        <button type="button" class="btn  btn-primary" onclick="GetAllEmployeeCategory('FILTER');">Search</button>
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

                                    </div> --%>

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

                                    <table class="table table-striped table-hover table-bordered ">
                                        <thead class="bg-success" style="color:#fff;">
                                            <tr>
                                                <th id="th2" class ="inner_md_htext">Group Code</th>
                                                <th id="th3" class ="inner_md_htext">Group Name</th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                        <!--  <tbody id="tblListOfEmployeeAndDepenent">   </tbody>  --->
                                     <tbody id="tblListOfStoreGroup">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addStoreGroupForm" name="addStoreGroupForm" method="">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Group Code" class=" col-form-label inner_md_htext" >Group Code <span style="color:red">*</span> </label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" id="groupCode" name="groupCode" placeholder="Group Code" onkeypress=" return validateText('groupCode',3);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Group Name <span style="color:red">*</span> </label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" id="groupName" name="groupName" placeholder="Group Name" onkeypress="return validateTextField('groupName',30);" >
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                    </div>
									<br>
									
									<div class="clearfix"></div>
         							<br>
									<div class="row">

										<div class="col-md-12">
											<div class="btn-left-all"></div>
											<div class="btn-right-all">
												
														<button type="button" id="btnAddStoreGroup"
															class="btn  btn-primary " onclick="addStoreGroup();">Add</button>
														<button type="button" id="updateBtn"
															class="btn  btn-primary " onclick="updateStoreGroup();">Update</button>
														<button id="btnActive" type="button"
															class="btn  btn-primary " onclick="updateStoreGroupStatus();">Activate</button>
														<button id="btnInActive" type="button"
															class="btn btn-primary  " onclick="updateStoreGroupStatus();">Deactivate</button>
														<button type="button" class="btn btn-danger "
															onclick="Reset();">Reset</button>
													
											</div>
										</div>
									</div>
										
                                    <%-- <div class="row">
                                        <div class="col-md-7">
                                        </div>
                                        <div class="col-md-5">
                                            <form>
                                                <div class="button-list">

                                                    <button type="button" id="btnAddEmployeeCategory" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addEmployeeCategoryDetails();">Add</button>
                                                    <button type="button" id ="updateBtn" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateEmployeeCategory();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateEmployeeCategoryStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateEmployeeCategoryStatus();">Deactivate</button>
                                                    <button type="button" class="btn btn-danger btn-rounded w-md waves-effect waves-light" onclick="Reset();">Reset</button>

                                                </div>
                                            </form>
                                        </div>

                                    </div> --%>

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

           

        </div>

        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

    </div>
    <!-- END wrapper -->

    <!-- jQuery  -->
    

</body>

</html>