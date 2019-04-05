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
			
	GetDepartmentList();
	GetAllDepartment('ALL');
			
		});
function GetAllDepartment(MODE){
	 
	 var departmentId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo};			
		}else{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllDepartment";		
	var bClickable = true;
	GetJsonData('tblListOfDepartment',data,url,bClickable);	 
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
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].departmentId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].departmentCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].departmentName+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].departmentTypeName+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfDepartment").html(htmlTable); 	
	
}

var comboArray =[];
var departId;
var departCode;
var departName;
var departTypeName;
var departStatus;
var departTypeId

function executeClickEvent(departmentId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(departmentId == data.data[j].departmentId){
			departId = data.data[j].departmentId;			
			departCode = data.data[j].departmentCode;			
			departName = data.data[j].departmentName;
			departTypeName = data.data[j].departmentTypeName;
			departStatus = data.data[j].status;
			departTypeId = data.data[j].departmentTypeId
			
			
		}
	}
	rowClick(departId,departCode,departName,departTypeName,departStatus,departTypeId);
}

function rowClick(departId,departCode,departName,departTypeName,departStatus, departTypeId){	
		
	document.getElementById("departmentCode").value = departCode;
	document.getElementById("departmentName").value = departName;
	
	for(var j=0; j<comboArray.length;j++){				
		
		if(comboArray[j] == departTypeName){
			
			jQuery("#sDepartmentTypeName").val(departTypeId);
		}
	}
			 
	if(departStatus == 'Y' || departStatus == 'y'){		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').attr("disabled", false);
	}
	if(departStatus == 'N' || departStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j('#updateBtn').attr("disabled", true);
	}
	$j('#updateBtn').show();
	$j("#btnActive").attr("disabled", false);
	$j("#btnInActive").attr("disabled", false);
	/* $j('#updateBtn').attr("disabled", false); */
	$j('#btnAddDepartment').attr("disabled", true)
	
}
function GetDepartmentList(){	
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getDepartmentTypeList",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	    	//alert("success "+result.data.length);
	    	var combo = "<option value=\"\">Select</option>" ;
	    	
	    	for(var i=0;i<result.data.length;i++){
	    		comboArray[i] = result.data[i].departmentTypeName;
	    		combo += '<option value='+result.data[i].departmentTypeId+'>' +result.data[i].departmentTypeName+ '</option>';
	    		//alert("combo :: "+combo);
	    	}
	    	jQuery('#sDepartmentTypeName').append(combo);
	    }
	    
	});

 }


function searchDepartmentList(){	
	if(document.getElementById('searchDepartment').value == "" || document.getElementById('searchDepartment') == null){
		 alert("Plese Enter the Department Name");
		 return false;
	 }
		 	 
	 var departmentName= jQuery("#searchDepartment").attr("checked", true).val().toUpperCase();
		
		var nPageNo=1;
		var url = "getAllDepartment";
		var data =  {"PN":nPageNo, "departmentName":departmentName};
		var bClickable = true;
		GetJsonData('tblListOfDepartment',data,url,bClickable);	
		ResetForm();
}

function updateDepartment(){
	
	if(document.getElementById('departmentCode').value == null || document.getElementById('departmentCode').value == ""){
		alert("Please Enter Department Code");
		return false;
	}
	if(document.getElementById('departmentName').value == null || document.getElementById('departmentName').value ==""){
		alert("Please Enter Department Name");
		return false;
	}
	if(document.getElementById('sDepartmentTypeName').value == null || document.getElementById('sDepartmentTypeName').value ==""){
		alert("Please Enter Department Type Name");
		return false;
	}
		
	
	var params = {
			 'departmentId':departId,
			 'departmentCode':jQuery('#departmentCode').val(),
			 'departmentName':jQuery('#departmentName').val(),
			 'departmentTypeId':jQuery('#sDepartmentTypeName').find('option:selected').val()
			
	 } 
	
	//alert("params: "+JSON.stringify(params)); 	
		jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateDepartmentDetails",
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
		
		$j('#updateBtn').attr("disabled", true);
		ResetForm();
}

function updateDepartmentStatus(){
	
	if(document.getElementById('departmentCode').value == null || document.getElementById('departmentCode').value == ""){
		alert("Please Enter Department Code");
		return false;
	}
	if(document.getElementById('departmentName').value == null || document.getElementById('departmentName').value ==""){
		alert("Please Enter Department Name");
		return false;
	}
	if(document.getElementById('sDepartmentTypeName').value == null || document.getElementById('sDepartmentTypeName').value ==""){
		alert("Please Enter Department Type Name");
		return false;
	}
	
	var params = {
			  'departmentId':departId,
			 'departmentCode':jQuery('#departmentCode').val(),
			 'status':departStatus
			 
		} 
	//alert("params: "+JSON.stringify(params)); 
	 jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateDepartmentStatus",
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
	ResetForm();
}

function addDepartmentDetails(){
	
	if(document.getElementById('departmentCode').value == null || document.getElementById('departmentCode').value == ""){
		alert("Please Enter Department Code");
		return false;
	}
	if(document.getElementById('departmentName').value == null || document.getElementById('departmentName').value ==""){
		alert("Please Enter Department Name");
		return false;
	}
	if(document.getElementById('sDepartmentTypeName').value == null || document.getElementById('sDepartmentTypeName').value ==""){
		alert("Please Enter Department Type Name");
		return false;
	}
	
	var params = {
			 
			 'departmentCode':jQuery('#departmentCode').val(),
			 'departmentName':jQuery('#departmentName').val(),
			 'departmentTypeId':jQuery('#sDepartmentTypeName').find('option:selected').val()
			 			 
	 } 
	
	//alert("params: "+JSON.stringify(params)); 
	
		jQuery.ajax({
	 crossOrigin: true,
	    method: "POST",
	    header:{
	    	'Access-Control-Allow-Origin': '*'
	    	},
	    	crossDomain:true,
	    url: "addDepartment",
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
	
}
function changeDepartType(value){
	
	var departTypeId= jQuery('#sDepartmentTypeName').find('option:selected').val()
	
	 if(value == departTypeId){
		$j('#updateBtn').attr("disabled", false);
	}	
	 
}

function Reset(){
	document.getElementById("searchDepartmentForm").reset();
	document.getElementById("addDepartmentForm").reset();
	
	 $j("#btnActive").hide();
		$j("#btnInActive").hide();
		$j('#updateBtn').attr("disabled", true);
		$j('#btnAddDepartment').attr("disabled", false);
		
	
}	

 function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetAllDepartment('FILTER');
	
} 
 
   
  function isValidLengthDeptCode(){
		
	 if(departmentCode.value.length >= 20){
		 alert("Please Enter Valid Department Code");
		 document.getElementById('departmentCode').value="";
		 return false;
	 }
}
  
  function enableAddButton(){
		if(document.getElementById("departmentCode").value!=null || !document.getElementById("departmentCode").value==""){
			$j('#btnAddDepartment').attr("disabled", false);
		}else if( document.getElementById("departmentName").value!=null || !document.getElementById("departmentName").value==""){
			$j('#btnAddDepartment').attr("disabled", false);
		}else{
			$j('#btnAddDepartment').attr("disabled", true);
		}
	}

	function validTextBox(){
		if($j('#departmentCode').val().length > 10){
			 alert("Department Code should be less than 10");
			 document.getElementById('departmentCode').value="";
			 return false;
		 }
		if($j('#departmentName').val().length > 30){
			 alert("Department Name should be less than 30");
			 document.getElementById('departmentName').value="";
			 return false;
		 }
	}

	function ResetForm()
	{
		$j('#departmentCode').val('');
		$j('#departmentName').val('');
		$j('#sDepartmentTypeName').val('');
		$j('#searchDepartment').val('');
	}

	function showAll()
	{
		ResetForm();
		nPageNo = 1;	
		GetAllDepartment('ALL');
		
	}
 
</script> 

</head>

<body>

    <!-- Begin page -->
    <div id="wrapper">

        <!-- Top Bar Start -->
        <div class="topbar">

            <!-- LOGO -->
            <%-- <div class="topbar-left">
                <a href="index.html" class="logo">
                    <span>
                            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="" height="18">
                        </span>
                    <i>
                            <img src="${pageContext.request.contextPath}/resources/images/logo_sm.png" alt="" height="22">
                        </i>
                </a>
            </div> --%>

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
                                <h5 class="text-overflow"><small>Welcome ! Department Master</small> </h5>
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
                <div class="internal_Htext">Department Master</div>
                    <div class="row">
                    
                        <!-- <div class="col-12">
                            <div class="page-title-box">
                                <div class="internal_Htext">Department Master</div>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">Department Master</li>
                                </ol>

                                <div class="clearfix"></div>
                            </div>
                        </div> -->
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
                                            <form class="form-horizontal" name="searchDepartmentForm" id="searchDepartmentForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Department Name<span style="color:red">*</span></label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Department Name</label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchDepartment" id="searchDepartment" class="form-control" id="inlineFormInputGroup" placeholder="Department Name" onkeypress="return validateTextField('searchDepartment',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button id="searchBtn" type="button" class="btn  btn-primary" onclick="searchDepartmentList();">Search</button>
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
                                                <th id="th2" class ="inner_md_htext">Department Code</th>
                                                <th id="th3" class ="inner_md_htext">Department Name</th>
                                                <th id="th4" class ="inner_md_htext">Department Type Name</th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                     <tbody id="tblListOfDepartment">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addDepartmentForm" name="addDepartmentForm" action="" method="POST">
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Department Code" class=" col-form-label inner_md_htext" >Department Code <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="departmentCode" id="departmentCode" class="form-control" placeholder="Department Code" onkeypress="return validateText('departmentCode',7);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Department Name <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="departmentName" id="departmentName" class="form-control" placeholder="Department Name" onkeypress="return validateTextField('departmentName',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <div class="form-group row">                                                        
                                                            <label for="recordoffice" class="col-sm-5 col-form-label inner_md_htext">Department Type Name <span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-6">
                                                                <select class="form-control" id="sDepartmentTypeName" onchange="changeDepartType(this.value);">
                                                                    
                                                                </select>
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

                                                    <button id="btnAddDepartment" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addDepartmentDetails();">Add</button>
                                                    <button id ="updateBtn" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateDepartment();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateDepartmentStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateDepartmentStatus();">DeActivate</button>
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

        </div>

        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

    </div>
    <!-- END wrapper -->

    <!-- jQuery  -->
    

</body>

</html>