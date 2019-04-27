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
     <link href="//resources/css/icons1.css" rel="stylesheet" type="text/css" />
    
<%@include file="..//view/commonJavaScript.jsp" %>
  
<script type="text/javascript" language="javascript">
var nPageNo=1;
var Status;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{	
			$j("#btnActive").hide();
			$j("#btnInActive").hide();
			$j('#updateBtn').attr("disabled", true);
			$j('#rangeFlag').attr('readonly', false);
			 $j('#selectGender').attr('readonly', false);
			GetRangeList('ALL');
			GetGenderList();
			
		});

		
function GetRangeList(MODE)
{
	var rangeId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo};			
		}
	else
		{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllRange";
		
	var bClickable = true;
	GetJsonData('tblListOfRange',data,url,bClickable);
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
		 
			htmlTable = htmlTable+"<tr id='"+dataList[i].rangeId+"' >";	
			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].administrativeSexName+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].fromRange+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].toRange+"</td>";			
			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].rangeFlag+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>";			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   //alert('No Record Found');
		}			
	
	$j("#tblListOfRange").html(htmlTable);	
	
	
}
var comboArray = [];
var rId;
var rFromRange;
var rToRange;
var rStatus;
var rRangeFlag
var gName;
var gId;
function executeClickEvent(rangeId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(rangeId == data.data[j].rangeId){
			rId = data.data[j].rangeId;
			rFromRange = data.data[j].fromRange;
			rToRange = data.data[j].toRange;
			rStatus = data.data[j].status;
			rRangeFlag = data.data[j].rangeFlag;
			gName = data.data[j].administrativeSexName;
			gId = data.data[j].administrativeSexId;
			
		}
	}
	rowClick(rId,rFromRange,rToRange,rStatus,rRangeFlag,gName,gId);
}

function searchRangeList(){	
	if(document.getElementById('sGender').value == "" || document.getElementById('sGender') == null){
		 alert("Plese Select Gender");
		 return false;
	 }
		 	 
	 var genderId= jQuery("#sGender").val();
	 		
		var nPageNo=1;
		var url = "getAllRange";
		var data =  {"PN":nPageNo, "administrativeSexId":genderId};
		var bClickable = true;
		GetJsonData('tblListOfRange',data,url,bClickable);		
}

function addRangeDetails(){
	if(document.getElementById('selectGender').value=="" || document.getElementById('selectGender').value==null){
		alert("Please Enter the Gender");
		return false;
	} 
	 if(document.getElementById('fromRange').value=="" || document.getElementById('fromRange').value==null){
		alert("Please Enter the From Range");
		return false;
	}
	 
	if(document.getElementById('toRange').value=="" || document.getElementById('toRange').value==null){
		alert("Please Enter the To Range");
		return false;
	}
	if(document.getElementById('rangeFlag').value=="" || document.getElementById('rangeFlag').value==null){
		alert("Please Enter the Range Type");
		return false;
	} 
	
	var params = {
			'administrativeSexId':jQuery('#selectGender').find('option:selected').val(),
			 'fromRange':jQuery('#fromRange').val(),
			 'toRange':jQuery('#toRange').val(),
			 'rangeFlag':jQuery('#rangeFlag').find('option:selected').val()
	 } 	
	var url="addRange";
	
	SendJsonData(url,params);	
	
}




function updateRangeDetails()
{	
	$j('#messageId').fadeIn();
	
	 if(document.getElementById('fromRange').value == "" || document.getElementById('fromRange').value == null ){
		alert("please enter the From Range");
		return false;
	}
	if(document.getElementById('toRange').value == "" || document.getElementById('toRange').value == null ){
		alert("please enter the To Range");
		return false;
	}
		
	var params = {
			 'rangeId':rId,
			 'fromRange':jQuery('#fromRange').val(),
			 'toRange':jQuery('#toRange').val(),
	 } 
	var url="updateRangeDetails";
	SendJsonData(url,params);	
 		 		
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddRange').attr("disabled", false);
	ResetForm();
	
}

function updateStatus(){
	$j('#messageId').fadeIn();
	if(document.getElementById('fromRange').value == "" || document.getElementById('fromRange') == null ){
		alert("Please Select the From Range");
		return false;
	}
		
	 var params = {
		 'rangeId':rId,
		 'fromRange':rFromRange,
		 'status':rStatus
		 
	}  
	 
	 var url="updateRangeStatus";
	 SendJsonData(url,params);	 
	 
	 $j("#btnActive").attr("disabled", true);
	 $j("#btnInActive").attr("disabled", true);
	 $j('#btnAddRange').attr("disabled", false);
}



function GetGenderList(){
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getGenderList",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	    	//alert("success "+result.data.length);
	    	var combo = "<option value=\"\">Select</option>" ;
	    	
	    	for(var i=0;i<result.data.length;i++){
	    		comboArray[i] = result.data[i].administrativeSexName;
	    		combo += '<option value='+result.data[i].administrativeSexId+'>' +result.data[i].administrativeSexName+ '</option>';
	    		//alert("combo :: "+comboArray[i]);
	    		
	    		
	    	}
	    	jQuery('#selectGender').append(combo);
	    	jQuery('#sGender').append(combo);
	    }
	    
	});
}

function rowClick(rId,rFromRange,rToRange,rStatus,rRangeFlag,gName,gId){	
		
	document.getElementById("fromRange").value = rFromRange;
	document.getElementById("toRange").value = rToRange;
	
	for(var j=0; j<comboArray.length;j++){		
		if(comboArray[j] == gName){
			
			jQuery("#selectGender").val(gId);
		}
	}
	
	if(rStatus == 'Y' || rStatus == 'y'){
		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').show();
	}
	if(rStatus == 'N' || rStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j('#updateBtn').hide();
	}
	$j('#updateBtn').attr("disabled", false);
	$j('#btnAddRange').attr("disabled", true);
	
	 $j("#btnActive").attr("disabled", false);
	 $j("#btnInActive").attr("disabled", false);
	 $j('#rangeFlag').attr('readonly', true);
	 $j('#selectGender').attr('readonly', true);
	
}

function changeGender(value){
	
	var administrativeSexId = jQuery('#selectGender').find('option:selected').val();
	
	if(value == administrativeSexId){
		$j('#updateBtn').attr("disabled", false);
	}
	
}
function showResultPage(pageNo)
{
	
	
	nPageNo = pageNo;	
	GetRangeList('FILTER');
	
}



function Reset(){
	document.getElementById("addRangeForm").reset();
	document.getElementById("searchRangeForm").reset();
	document.getElementById('searchRange').value = "";
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddRange').attr("disabled", false);
	document.getElementById("messageId").innerHTML = "";
	$("#messageId").css("color", "black");
	
}



function ResetForm()
{	
	$j('#fromRange').val('');
	$j('#toRange').val('');
	$j('#selectGender').val('');
	$j('#searchRange').val('');
	
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetRangeList('ALL');
	
}

</script>
</head>

<body>

    <!-- Begin page -->
    <div id="wrapper">

        <!-- Top Bar Start -->
        <div class="topbar">

            <!-- LOGO -->
           <%--  <div class="topbar-left">
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
                                <h5 class="text-overflow"><small>Welcome ! Range Master</small> </h5>
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
                <div class="internal_Htext">Range Master</div>
                    
                    <!-- end row -->
                   
                  <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                       <br>
                                    <div class="row">
                                                                     
                                        <div class="col-md-8">
                                            <form class="form-horizontal" id="rangeForm" name="rangeForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Gender <span style="color:red">*</span></label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Gender <span style="color:red">*</span></label>
                                                            <div class="input-group mb-2">
                                                                <div class="col-md-6">
                                                                <select class="form-control" id="sGender" onchange="">
                                                                    
                                                                </select>
                                                            </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="searchRangeList();">Search</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                        
                                        
                                        
                                        
                                        <div class="col-md-4">
                                            <form>
                                                <div class="button-list">
                                                    <button type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="showAll('ALL');">Show All</button>
                                                    <button type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light">Reports</button>

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
                                                <th id="th2" class ="inner_md_htext">Gender</th>
                                                <th id="th3" class ="inner_md_htext">From Range</th>
                                                <th id="th4" class ="inner_md_htext">To Range</th>
                                                <th id="th4" class ="inner_md_htext">Range Type</th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                        <!--  <tbody id="tblListOfEmployeeAndDepenent">   </tbody>  --->
                                     <tbody id="tblListOfRange">
										 
                     				 </tbody>
                                    </table>
                                    
                                   
                                    
                                    

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addRangeForm" name="addRangeForm" method="">
                                                <div class="row">
                                                <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-4 col-form-label inner_md_htext">Gender Type<span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-6">
                                                                <select class="form-control" id="selectGender" onchange="changeGender(this.value);">
                                                                    
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-4">
                                                            <label for="From Range" class=" col-form-label inner_md_htext" >From Range <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="fromRange" name="fromRange" placeholder="From Range" onkeypress="return validateText('fromRange',10);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-4">
                                                            <label for="Name" class="col-form-label inner_md_htext">To Range <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="toRange" name="toRange" placeholder="To Range" onkeypress="return validateTextField('toRange',10);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-4 col-form-label inner_md_htext">Range Type<span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-6">
                                                               <select class="form-control" id="rangeFlag"  onchange="">
                                                                    <option value="0">Select</option>
                                                                    <option value="A">Age</option>
                                                                    <option value="H">Height</option>
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

                                                    <button type="button" id="btnAddRange" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addRangeDetails();">Add</button>
                                                    <button type="button" id ="updateBtn" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateRangeDetails();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateStatus();">Deactivate</button>
                                                    <button type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="Reset();">Reset</button>

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