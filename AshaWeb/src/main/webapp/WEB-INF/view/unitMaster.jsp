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
var comboArray = [];
var $j = jQuery.noConflict();
$j(document).ready(function()
		{
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').attr("disabled", true);
	$j('#updateBtn').hide();
	
			GetAllUnit('ALL');
			GetCommandList();
			GetUnitTypeList();
			
		});
		
function GetCommandList(){		
	
		 jQuery.ajax({
			 	crossOrigin: true,
			    method: "POST",			    
			    crossDomain:true,
			    url: "getCmdList",
			    data: JSON.stringify({}),
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(result){
			    	//alert("success "+result.data.length);
			    	var combo = "<option value=\"\">Select</option>" ;
			    	
			    	for(var i=0;i<result.data.length;i++){
			    		//alert(result.data[i].cmdId);
			    		//alert(result.data[i].cmdName);
			    		comboArray[i] = result.data[i].commandName;
			    		combo += '<option value='+result.data[i].commandId+'>' +result.data[i].commandName+ '</option>';
			    		//alert("combo :: "+combo);
			    	}
			    	jQuery('#selectListOfCommand').append(combo);
			    }
			    
			});
	 
		 }
		 
 function GetUnitTypeList(){
	 
	 jQuery.ajax({
		 	crossOrigin: true,
		    method: "POST",			    
		    crossDomain:true,
		    url: "getUnitTypeList",
		    data: JSON.stringify({}),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){
		    	//alert("success "+result.data.length);
		    	var combo = "<option value=\"\">Select</option>" ;
		    	
		    	for(var i=0;i<result.data.length;i++){
		    		comboArray[i] = result.data[i].unitTypeName;
		    		combo += '<option value='+result.data[i].unitTypeId+'>' +result.data[i].unitTypeName+ '</option>';
		    		//alert("combo :: "+combo);
		    	}
		    	jQuery('#sUnitTypList').append(combo);
		    }
		    
		});
 }
 
 function GetAllUnit(MODE){
	 
	 var unitId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo};			
		}else{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllUnit";		
	var bClickable = true;
	GetJsonData('tblListOfUnit',data,url,bClickable);	 
 }
 function makeTable(jsonData)
 {	
 	var htmlTable = "";	
 	var data = jsonData.count; 
 	
 	var pageSize = 5; 	
 	var dataList = jsonData.data; 
 	//alert("dataList:: "+dataList.length)
 	
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
 				
 			htmlTable = htmlTable+"<tr id='"+dataList[i].unitId+"' >";			
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].unitName+"</td>";
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].commandName+"</td>";
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].unitType+"</td>";
 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].unitAddress+"</td>";
 			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
 			htmlTable = htmlTable+"</tr>";
 			
 		}
 	if(dataList.length == 0)
 		{
 		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
 		   
 		}			
 	
 	
 	$j("#tblListOfUnit").html(htmlTable); 	
 	
 }
 
 
 var uId;
 var unitName;
 var unitAddress;
 var unitStatus;
 function executeClickEvent(unitId,data)
 {
 	
 	for(j=0;j<data.data.length;j++){
 		if(unitId == data.data[j].unitId){
 			uId = data.data[j].unitId; 			
 			unitName = data.data[j].unitName;
 			 unitStatus = data.data[j].status;
 			commandId = data.data[j].cmdId;
 			commandName = data.data[j].commandName;
 			unitType = data.data[j].unitType;
 			unitTypeId = data.data[j].unitTypeId; 			
 			unitAddress = data.data[j].unitAddress;
 			
 		}
 	}
 	rowClick(unitId,unitName, unitStatus, commandName, unitType, unitAddress, unitTypeId);
 }
 
 function rowClick(uId,unitName, unitStatus, commandName, unitType, unitAddress, unitTypeId){
		document.getElementById("unitName").value = unitName;	
		document.getElementById("unitAddress").value = unitAddress;
		
		for(var j=0; j<comboArray.length;j++){				
			
			if(comboArray[j] == commandName){
				
				jQuery("#selectListOfCommand").val(commandId);
			}
		}
		
		for(var k=0; k<comboArray.length;k++){
			
			if(comboArray[k] == unitType){
				
				jQuery("#sUnitTypList").val(unitTypeId);
			}
		}
		
		if(unitStatus == 'Y' || unitStatus == 'y'){
			
			$j("#btnInActive").show();
			$j("#btnActive").hide();
		    $j('#updateBtn').attr("disabled", false);
		}
		if(unitStatus == 'N' || unitStatus == 'n'){
			
			$j("#btnActive").show();
			$j("#btnInActive").hide();
			$j('#updateBtn').attr("disabled", true);
		}
		
		$j('#updateBtn').show();
		$j('#addBtnUnit').attr("disabled", true);
		 $j("#btnActive").attr("disabled", false);
		 $j("#btnInActive").attr("disabled", false);
		
}
 function updateUnitMaster(){
	 
	 if(document.getElementById('unitName').value=="" || document.getElementById('unitName').value== null){
		 alert("Please Enter the Unit Name")
		 return false;
	 }
	 if(document.getElementById('unitAddress').value=="" || document.getElementById('unitAddress').value== null){
		 alert("Please Enter the unit Address")
		 return false;
	 }
	 if(document.getElementById('selectListOfCommand').value=="" || document.getElementById('selectListOfCommand').value== null){
		 alert("Please Select the Command")
		 return false;
	 }
	 if(document.getElementById('sUnitTypList').value=="" || document.getElementById('sUnitTypList').value== null){
		 alert("Please Select the Unit Type")
		 return false;
	 }
	 
		params = {
				'unitId':uId,
				'unitName':jQuery('#unitName').val(),
				'unitAddress':jQuery('#unitAddress').val(),
				'cmdId':jQuery('#selectListOfCommand').find('option:selected').val(),
				'unitTypeId':jQuery('#sUnitTypList').find('option:selected').val()
		}
		//alert("params: "+JSON.stringify(params));
		
		jQuery.ajax({
			 crossOrigin: true,
			    method: "POST",
			    header:{
			    	'Access-Control-Allow-Origin': '*'
			    	},
			    	crossDomain:true,
			    url: "updateUnit",
			    data: JSON.stringify(params),
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(result){
			        //alert("result" +result);
			        console.log(result);
			        if(result.status==1){			        	
			        	showAll('ALL')
			        	document.getElementById("messageId").innerHTML = result.msg;
			        }
			        if(result.status==0){
			        	document.getElementById("messageId").innerHTML = result.msg;
			        }
			    }
			});
		
		
	}
 
 function updateStatus(){
	 if(document.getElementById('unitName').value=="" || document.getElementById('unitName').value== null){
		 alert("Please Enter the Unit Name")
		 return false;
	 }
	 if(document.getElementById('unitAddress').value=="" || document.getElementById('unitAddress').value== null){
		 alert("Please Enter the unit Address")
		 return false;
	 }
	 if(document.getElementById('selectListOfCommand').value=="" || document.getElementById('selectListOfCommand').value== null){
		 alert("Please Select the Command")
		 return false;
	 }
	 if(document.getElementById('sUnitTypList').value=="" || document.getElementById('sUnitTypList').value== null){
		 alert("Please Select the Unit Type")
		 return false;
	 }
	 
	 params = {
				'unitId':uId,
				'unitName':jQuery('#unitName').val(),
				'status':unitStatus
		}
		//alert("params: "+JSON.stringify(params));
		
		jQuery.ajax({
			 crossOrigin: true,
			    method: "POST",
			    header:{
			    	'Access-Control-Allow-Origin': '*'
			    	},
			    	crossDomain:true,
			    url: "updateUnitStatus",
			    data: JSON.stringify(params),
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(result){
			        //alert("result" +result);
			        console.log(result);
			        if(result.status==1){
			        	
			        	showAll('ALL')
			        	document.getElementById("messageId").innerHTML = result.msg;
			        }
			        if(result.status==0){
			        	document.getElementById("messageId").innerHTML = result.msg;
			        }
			    }
			}); 
 }
 
 function searchUnitList(){
	 if(document.getElementById('searchUnit').value == "" || document.getElementById('searchUnit') == null){
		 alert("Plese Enter the Unit Name");
		 return false;
	 }
	/*  if (jQuery('#searchUnit').val() == ""){		 
         alert('Please Enter the Unit');
     } */
		 	 
	 var unitName= jQuery("#searchUnit").attr("checked", true).val().toUpperCase();
		
		var nPageNo=1;
		
		var data =  {"PN":nPageNo, "unitName":unitName};
		var url = "getAllUnit";
		var bClickable = true;
		GetJsonData('tblListOfUnit',data,url,bClickable);	
		ResetForm();
 }
 
 function addUnit(){
	 if(document.getElementById('unitName').value=="" || document.getElementById('unitName').value== null){
		 alert("Please Enter the Unit Name")
		 return false;
	 }
	 if(document.getElementById('unitAddress').value=="" || document.getElementById('unitAddress').value== null){
		 alert("Please Enter the unit Address")
		 return false;
	 }
	 if(document.getElementById('selectListOfCommand').value=="" || document.getElementById('selectListOfCommand').value== null){
		 alert("Please Select the Command")
		 return false;
	 }
	 if(document.getElementById('sUnitTypList').value=="" || document.getElementById('sUnitTypList').value== null){
		 alert("Please Select the Unit Type")
		 return false;
	 }
	 
	 
	 
	var cmdId =  jQuery('#selectListOfCommand').find('option:selected').val();
	var unitTypId = jQuery('#sUnitTypList').find('option:selected').val();		 
	  var params = {
			
			 'unitName':jQuery('#unitName').val(),
			 'unitAddress':jQuery('#unitAddress').val(),
			 'cmdId':cmdId,
			 'unitTypId':unitTypId
			 
	 }	 
	//alert("params: "+JSON.stringify(params));  
	  jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "addUnit",
		    data: JSON.stringify(params),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){
		       // alert("result" +result);
		        console.log(result);
		        if(result.status==1){		        	
		        	showAll('ALL')
		        	document.getElementById("messageId").innerHTML = result.msg;
		        }
		        if(result.status==2){
		        	document.getElementById("messageId").innerHTML = result.msg;
		        }
		        if(result.status==0){
		        	document.getElementById("messageId").innerHTML = result.msg;
		        }
		    }		    
		}); 
	  ResetForm();
 }
 	
 
 function Reset(){
	 document.getElementById('searchUnitForm').reset();
	 document.getElementById('addUnitForm').reset();
	 
	 $j("#btnActive").hide();
		$j("#btnInActive").hide();
		$j('#updateBtn').attr("disabled", true);
		$j('#addBtnUnit').attr("disabled", false);
		$j('#updateBtn').hide();
		
	   
 }
 
 function showResultPage(pageNo)
 {
 	nPageNo = pageNo;	
 	GetAllUnit('FILTER');
 	
 }
 
 function onChgcommandName(value){
	 
	 var commandId= jQuery('#selectListOfCommand').find('option:selected').val()
		
	 if(value == commandId){
		$j('#updateBtn').attr("disabled", false);
	}	 
 }
 
function chgUnitType(value){
	var unitTypeId = jQuery('#sUnitTypList').find('option:selected').val()
	if(value == unitTypeId){
		$j('#updateBtn').attr("disabled", false);
	}
} 
 
function enableAddButton(){
	if(document.getElementById("unitName").value!=null || !document.getElementById("unitName").value==""){
		$j('#addBtnUnit').attr("disabled", false);
	}else if( document.getElementById("unitAddress").value!=null || !document.getElementById("unitAddress").value==""){
		$j('#addBtnUnit').attr("disabled", false);
	}else{
		$j('#addBtnUnit').attr("disabled", true);
	}
}

function validTextBox(){
	
	if($j('#unitName').val().length > 30){
		 alert("Unit Name should be less than 30");
		 document.getElementById('unitName').value="";
		 return false;
	 }
	
}
function ResetForm()
{
	$j('#unitName').val('');
	$j('#unitAddress').val('');
	$j('#selectListOfCommand').val('');
	$j('#sUnitTypList').val('');
	$j('#searchUnit').val('');
	
}
function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetAllUnit('ALL');
	
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
                                <h5 class="text-overflow"><small>Welcome ! Unit Master</small> </h5>
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
                <div class="internal_Htext">Unit Master</div>
                    <div class="row">
                        <!-- <div class="col-12">
                            <div class="page-title-box">
                                

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">Unit Master</li>
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
                                            <form class="form-horizontal" id="searchUnitForm" name="searchUnitForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Unit Name <span style="color:red">*</span></label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Unit Name <span style="color:red">*</span></label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchUnit" id="searchUnit" class="form-control" id="inlineFormInputGroup" placeholder="Unit Name" onkeypress="return validateTextField('searchUnit',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn  btn-primary" onclick="searchUnitList();">Search</button>
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
                                                <th id="th2" class ="inner_md_htext">Unit Name</th>
                                                <th id="th3" class ="inner_md_htext">Region Name</th>
                                                <th id="th4" class ="inner_md_htext"> Unit Type</th>
                                                <th id="th5" class ="inner_md_htext"> Address</th>
                                                <th id="th6" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                        <!--  <tbody id="tblListOfEmployeeAndDepenent">   </tbody>  --->
                                     <tbody id="tblListOfUnit">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addUnitForm" name="addUnitForm" action="" method="POST">
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Command Code" class=" col-form-label inner_md_htext" >Unit Name <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="unitName" id="unitName" class="form-control" placeholder="Unit Name" onkeypress="return validateTextField('unitName',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Address <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="unitAddress" id="unitAddress" class="form-control" placeholder="Unit Address" onkeypress="return validateTextField('unitAddress',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-5 col-form-label inner_md_htext">Region Name <span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-6">
                                                                <select class="form-control" id="selectListOfCommand" onchange="onChgcommandName(this.value);">
                                                                    
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-4 col-form-label inner_md_htext">Unit Type <span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-6">
                                                                <select class="form-control" id="sUnitTypList" onchange="chgUnitType(this.value);">
                                                                    
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

                                                    <button id="addBtnUnit" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addUnit();">Add</button>
                                                    <button id ="updateBtn" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateUnitMaster();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateStatus();">DeActivate</button>
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