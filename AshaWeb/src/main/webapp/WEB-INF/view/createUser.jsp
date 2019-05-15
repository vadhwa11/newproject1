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
  
<script type="text/javascript" language="javascript">
var nPageNo=1;
var Status;
var sTypeId;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{	
			$j("#btnActive").hide();
			$j("#btnInActive").hide();
			$j('#updateBtn').attr("disabled", true);
			$j('#btnAddServiceType').attr("disabled", false);
			$j('#serviceTypeCode').attr('readonly', false);
			GetServiceTypeList('ALL');
			GetCommandTypeList();
			
		});
		
function GetCommandTypeList(){
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getCommandTypeList",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	    	//alert("success "+result.data.length);
	    	var combo = "<option value=\"\">Select</option>" ;
	    	
	    	for(var i=0;i<result.data.length;i++){
	    		comboArray[i] = result.data[i].commandtypeName;
	    		combo += '<option value='+result.data[i].commandtypeId+'>' +result.data[i].commandtypeName+ '</option>';
	    		//alert("combo :: "+comboArray[i]);
	    		
	    		
	    	}
	    	jQuery('#selectCmdType').append(combo);
	    }
	    
	});
}	
		
		
		
function GetServiceTypeList(MODE)
{
	var cmdId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo};			
		}
	else
		{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllServiceTypeDetails";
		
	var bClickable = true;
	GetJsonData('tblListOfServiceType',data,url,bClickable);
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
					Status='Active'
						
				}
			else
				{
					Status='Inactive'
				}				
			htmlTable = htmlTable+"<tr id='"+dataList[i].serviceTypeId+"' >";	
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].serviceTypeCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].serviceTypeName+"</td>";	
			htmlTable = htmlTable +"<td style='width: 150px;'></td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>";			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   //alert('No Record Found');
		}			
	
	$j("#tblListOfServiceType").html(htmlTable);	
	
	
}

var serviceTypeId;
var serviceTypeName;
var serviceTypeCode;
var serviceTypeStatus;
function executeClickEvent(serviceTypeId,data)
{
	sTypeId=serviceTypeId;
			
	for(j=0;j<data.data.length;j++){
		if(serviceTypeId == data.data[j].serviceTypeId){
			serviceTypeId = data.data[j].serviceTypeId;
			serviceTypeName = data.data[j].serviceTypeName;
			serviceTypeCode = data.data[j].serviceTypeCode;
			serviceTypeStatus = data.data[j].status;
			
		}
	}
	rowClick(serviceTypeId,serviceTypeName,serviceTypeCode,serviceTypeStatus);
	$j('#serviceTypeCode').attr('readonly', 'true');
	
}

function searchServiceTypeList(){
		 if(document.getElementById('searchServiceTypeName').value == "" || document.getElementById('searchServiceTypeName') == null){
		 alert("Plese Enter the Service Type Name");
		 return false;
	 }
	var nPageNo=1;
	var serviceTypeName=document.getElementById('searchServiceTypeName').value;
	var url = "getAllServiceTypeDetails";
	var data =  {"PN":nPageNo, "serviceTypeName":serviceTypeName};
	var bClickable = true;
	GetJsonData('tblListOfServiceType',data,url,bClickable);
	ResetForm();
}

function addSeviceTypeDetails(){
	if(document.getElementById('serviceTypeCode').value=="" || document.getElementById('serviceTypeCode').value==null){
		alert("Please Enter the Service Type Code");
		return false;
	}
	if(document.getElementById('serviceTypeName').value=="" || document.getElementById('serviceTypeName').value==null){
		alert("Please Enter the Service Type Name");
		return false;
	}
	var url="addServiceType";
	var data = {
			 
			 'serviceTypeCode':jQuery('#serviceTypeCode').val(),
			 'serviceTypeName':jQuery('#serviceTypeName').val(),
			 
	 } 	
	
	SendJsonData(url,data);	
		
}

function updateServiceTypeDetails()
{	
	
	if(document.getElementById('serviceTypeName').value == "" || document.getElementById('serviceTypeName').value == null ){
		alert("please enter the Service Type Name");
		return false;
	}	
	var url="updateServiceTypeDetails";
	var data = {
			'serviceTypeId':sTypeId,
			 //'serviceTypeCode':jQuery('#serviceTypeCode').val(),
			 'serviceTypeName':jQuery('#serviceTypeName').val()			 
	 } 
	SendJsonData(url,data);		 
 		
	$j('#updateBtn').attr("disabled", true);
	ResetForm();
	
}

function updateStatus(){
	var url="updateServiceTypeStatus";
	var data = {
		 'serviceTypeId':sTypeId,
		 'serviceTypeCode':serviceTypeCode,
		 'status':serviceTypeStatus
		 
	}  
	
	SendJsonData(url,data);		 
	 
	 $j("#btnActive").attr("disabled", true);
	 $j("#btnInActive").attr("disabled", true);
}

function rowClick(serviceTypeId,serviceTypeName,serviceTypeCode,serviceTypeStatus){	
	sTypeId=serviceTypeId;
	document.getElementById("serviceTypeCode").value = serviceTypeCode;
	document.getElementById("serviceTypeName").value = serviceTypeName;
	if(serviceTypeStatus == 'Y' || serviceTypeStatus == 'y'){
		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').show();
		
	}
	if(serviceTypeStatus == 'N' || serviceTypeStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j('#updateBtn').hide();
		$j('#btnAddServiceType').hide();
		
	}
	
	 $j('#updateBtn').attr("disabled", false); 
	$j('#btnAddServiceType').attr("disabled", true);
	
	 $j("#btnActive").attr("disabled", false);
	 $j("#btnInActive").attr("disabled", false);
	
}

function showResultPage(pageNo)
{	
	nPageNo = pageNo;		
	GetServiceTypeList('FILTER');
	
}

function Reset(){
	document.getElementById("searchSeriviceTypeForm").reset();
	document.getElementById("addServiceTypeForm").reset();
	document.getElementById('searchServiceTypeName').value = "";	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddServiceType').attr("disabled", false);
	
}

function ResetForm()
{
	$j('#serviceTypeCode').val('');
	$j('#serviceTypeName').val('');
	$j('#searchServiceType').val('');
}

function showAll()
{
	ResetForm();
	nPageNo = 1;
	GetServiceTypeList('ALL');		
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
               <%--  <a href="index.html" class="logo">
                    <span>
                            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="" height="18">
                        </span>
                    <i>
                            <img src="${pageContext.request.contextPath}/resources/images/logo_sm.png" alt="" height="22">
                        </i>
                </a> --%>
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
                                <h5 class="text-overflow"><small>Welcome ! User Registration</small> </h5>
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
                                <h4 class="page-title float-left">User Registration</h4>

                               

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
                                            <form class="form-horizontal" id="searchSeriviceTypeForm" name="searchSeriviceTypeForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Login Name <span style="color:red">*</span></label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Login Name</label>
                                                            <div class="input-group mb-2">
                                                                <input type="text" name="searchServiceTypeName" id="searchServiceTypeName" class="form-control" id="inlineFormInputGroup" placeholder="Login Name" onkeypress="return validateTextField('searchServieType',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn  btn-primary" onclick="searchServiceTypeList();">Search</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>        
                                      
                                        <div class="col-md-4">
                                            <form>
                                                <div class="button-list">
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" onclick="showAll('ALL');">Show All</button>
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light">Report</button>

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
		<div id=resultnavigation></div>
		</div>

                                    <table class="table table-hover table-bordered">
                                        <thead class="bg-success" style="color:#fff;">
                                            <tr>
                                                <th id="th2" class ="inner_md_htext">User Name</th>
                                                <th id="th2" class ="inner_md_htext"> Employee Name</th>
                                                <th id="th3" class ="inner_md_htext">Unit/Hospital</th>
                                                <th id="th4" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                        <!--  <tbody id="tblListOfEmployeeAndDepenent">   </tbody>  --->
                                     <tbody id="tblListOfServiceType">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addServiceTypeForm" name="addServiceTypeForm" method="">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row" id="hidestype">
                                                        <div class="col-sm-5">
                                                            <label for="Service Type" class=" col-form-label inner_md_htext" >Service no. <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" id="serviceTypeCode" name="Service no" placeholder="Service Type Code" onkeypress="return validateText('serviceTypeCode',5);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext"> Password <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="password" class="form-control" id="serviceTypeName" name="serviceTypeName" placeholder="password" onkeypress="return validateTextField('serviceTypeName',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-4 col-form-label inner_md_htext">Hospital<span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-6">
                                                                <select class="form-control" id="selectCmdType" onchange="changeCommandType(this.value);">
                                                                    
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

                                                    <button type="button" id="btnAddServiceType" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addSeviceTypeDetails();">Add</button>
                                                    <button type="button" id ="updateBtn" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateServiceTypeDetails();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateStatus();">Deactivate</button>
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
               <!--  Lorem Ipsum -->
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