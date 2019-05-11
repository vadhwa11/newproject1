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
	/* $j('#updateBtn').attr("disabled", true); */
	$j('#updateBtn').hide();
			
			GetAllRelation('ALL');
			
		});
function GetAllRelation(MODE){
	
	 var relationName= jQuery("#searchRelation").attr("checked", true).val().toUpperCase();
	 
	 var relationId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo, "relationName":""};			
		}else{
		var data = {"PN":nPageNo, "relationName":relationName};
		} 
	var url = "getAllRelation";		
	var bClickable = true;
	GetJsonData('tblListOfRelation',data,url,bClickable);	 
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
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].relationId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].relationCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].relationName+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].newRelationCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].newRelationName+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfRelation").html(htmlTable); 	
	
}

var relId;
var relCode;
var relName;
var newRelCode;
var newRelName;
var relStatus;

function executeClickEvent(relationId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(relationId == data.data[j].relationId){
			relId = data.data[j].relationId;
			relCode = data.data[j].relationCode;			
			relName = data.data[j].relationName;
			newRelCode = data.data[j].newRelationCode;
			newRelName = data.data[j].newRelationName;
			relStatus = data.data[j].status;
			
			
		}
	}
	rowClick(relId,relCode,relName,newRelCode, newRelName, relStatus);
}

function rowClick(relId,relCode,relName,newRelCode, newRelName, relStatus){	
		
	document.getElementById("relationCode").value = relCode;
	document.getElementById("relationName").value = relName;
	document.getElementById("newRelationCode").value = newRelCode;
	document.getElementById("newRelationName").value = newRelName;
		
	 
	if(relStatus == 'Y' || relStatus == 'y'){		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
	    $j("#updateBtn").show();
	    $j("#btnAddRelation").hide();
	}
	if(relStatus == 'N' || relStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j("#updateBtn").hide();
	}
	
	/* $j('#updateBtn').attr("disabled", false); */
	
	$j('#btnAddRelation').hide();
	/* $j('#relationCode').attr('readonly', true); */
	
}

function searchRelationList(){	
	if(document.getElementById('searchRelation').value == "" || document.getElementById('searchRelation') == null){
		 alert("Plese Enter the Relation Name");
		 return false;
	 }
		 	 
	 var relationName= jQuery("#searchRelation").attr("checked", true).val().toUpperCase();
		//alert("relationName :: "+relationName);
		var nPageNo=1;
		var url = "getAllRelation";
		var data =  {"PN":nPageNo, "relationName":relationName};
		var bClickable = true;
		GetJsonData('tblListOfRelation',data,url,bClickable);	
		ResetForm();
}


function updateRelationMaster(){	
	if(document.getElementById('relationCode') == null || document.getElementById('relationCode').value == ""){
		alert("Please Enter Relation Code");
		return false;
	}
	if(document.getElementById('relationName') == null || document.getElementById('relationName').value ==""){
		alert("Please Enter Relation Name");
		return false;
	}
	if(document.getElementById('newRelationCode') == null || document.getElementById('newRelationCode').value == ""){
		alert("Please Enter New Relation Code");
		return false;
	}
	if(document.getElementById('newRelationName') == null || document.getElementById('newRelationName').value ==""){
		alert("Please Enter New Relation Name");
		return false;
	}
	
	var params = {
			 'relationId':relId,
			 'relationCode':jQuery('#relationCode').val(),
			 'relationName':jQuery('#relationName').val(),			 
			 'newRelationCode':jQuery('#newRelationCode').val(),
			 'newRelationName':jQuery('#newRelationName').val()
	 } 
	
	var url="updateRelationDetails";
	SendJsonData(url,params);
	
	//alert("params: "+JSON.stringify(params)); 	
		/* jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateRelationDetails",
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
		    
		}); */
	$j('#updateBtn').attr("disabled", true);
	ResetForm();
}

function updateRelationStatus(){	
	if(document.getElementById('relationCode') == null || document.getElementById('relationCode').value == ""){
		alert("Please Enter Relation Code");
		return false;
	}
	if(document.getElementById('relationName') == null || document.getElementById('relationName').value ==""){
		alert("Please Enter Relation Name");
		return false;
	}
	if(document.getElementById('newRelationCode') == null || document.getElementById('newRelationCode').value == ""){
		alert("Please Enter New Relation Code");
		return false;
	}
	if(document.getElementById('newRelationName') == null || document.getElementById('newRelationName').value ==""){
		alert("Please Enter New Relation Name");
		return false;
	}
	
	var params = {
			'relationId':relId,
			'relationCode':jQuery('#relationCode').val(),
			 'status':relStatus
			 
		} 
	var url="updateRelationStatus";
	SendJsonData(url,params);
	//alert("params: "+JSON.stringify(params)); 
	 /* jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateRelationStatus",
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
		    
		    
		}); */
	 
	$j("#btnActive").attr("disabled", true);
	$j("#btnInActive").attr("disabled", true);
	ResetForm();
}

function addRelationDetails(){
	
	if(document.getElementById('relationCode') == null || document.getElementById('relationCode').value == ""){
		alert("Please Enter Relation Code");
		return false;
	}
	if(document.getElementById('relationName') == null || document.getElementById('relationName').value ==""){
		alert("Please Enter Relation Name");
		return false;
	}
	if(document.getElementById('newRelationCode') == null || document.getElementById('newRelationCode').value == ""){
		alert("Please Enter New Relation Code");
		return false;
	}
	if(document.getElementById('newRelationName') == null || document.getElementById('newRelationName').value ==""){
		alert("Please Enter New Relation Name");
		return false;
	}
	
	var params = {
			 
			 'relationCode':jQuery('#relationCode').val(),
			 'relationName':jQuery('#relationName').val(),
			 'newRelationCode':jQuery('#newRelationCode').val(),
			 'newRelationName':jQuery('#newRelationName').val(),
			 
	 } 
	var url="addRelation";
	SendJsonData(url,params);
	//alert("params: "+JSON.stringify(params)); 
	
		/* jQuery.ajax({
	 crossOrigin: true,
	    method: "POST",
	    header:{
	    	'Access-Control-Allow-Origin': '*'
	    	},
	    	crossDomain:true,
	    url: "addRelation",
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
	    }
	    
	    
	});	 */
	
}

function Reset(){
	document.getElementById("searchRelationForm").reset();
	document.getElementById("addRelationForm").reset();
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').hide();
	$j("#btnAddRelation").show();
	showAll();
}	

function enableAddButton(){
	if(document.getElementById("relationCode").value!=null || !document.getElementById("relationCode").value==""){
		$j('#btnAddRelation').attr("disabled", false);
	}else if( document.getElementById("relationName").value!=null || !document.getElementById("relationName").value==""){
		$j('#btnAddRelation').attr("disabled", false);
	}else{
		$j('#btnAddRelation').attr("disabled", true);
	}
}

function validTextBox(){
	if($j('#relationCode').val().length >= 2){
		 alert("Relation Code should be less or equal to 2");
		 document.getElementById('relationCode').value="";
		 return false;
	 }
	if($j('#newRelationCode').val().length >=2){
		 alert("Relation Name should be less or equal to 2");
		 document.getElementById('newRelationCode').value="";
		 return false;
	 }
}

function ResetForm()
{
	$j('#relationCode').val('');
	$j('#relationName').val('');
	$j('#newRelationCode').val('');
	$j('#newRelationName').val('');
	$j('#searchRelation').val('');
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetAllRelation('ALL');
	
}

function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetAllRelation('FILTER');
	
}
function search()
{
	if(document.getElementById('searchRelation').value == ""){
		alert("Please Enter Relation Name");
		return false;
	}
	nPageNo=1;
	GetAllRelation('Filter');
}

function generateReport()
{
	document.searchRelationForm.action="${pageContext.request.contextPath}/report/generateRelationReport";
	document.searchRelationForm.method="POST";
	document.searchRelationForm.submit(); 
	
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
                <%-- <ul class="list-inline float-right mb-0">
                
                    <li class="list-inline-item dropdown notification-list">
                        <a class="nav-link dropdown-toggle nav-user" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                            <i class="noti-icon"><img src="${pageContext.request.contextPath}/resources/images/users/avatar-1.jpg" alt="user" class="img-fluid rounded-circle"></i>
                            <span class="profile-username ml-2 text-dark">Username </span> <span class="mdi mdi-menu-down text-dark"></span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-animated dropdown-menu-right profile-dropdown ">
                            <!-- item-->
                            <div class="dropdown-item noti-title">
                                <h5 class="text-overflow"><small>Welcome ! Relation Master</small> </h5>
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
                <div class="internal_Htext">Relation Master</div>
                    
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                       <br>
                                       <div class="row">
                                            <div class="col-md-8">
											<form class="form-horizontal" id="searchRelationForm"
												name="searchRelationForm" method="" role="form">
												<div class="form-group row">
													<label class="col-3 col-form-label">Relation Name <span
														style="color: red">*</span></label>
													<div class="col-4">

														<input type="text" name="searchRelation" id="searchRelation"
															class="form-control" id="inlineFormInputGroup"
															placeholder="Relation Name" onkeypress="return validateTextField('searchRelation',30);">

													</div>
													<div class="form-group row">

														<div class="col-md-12">
															<button type="button" class="btn  btn-primary obesityWait-search"
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
                                            <form class="form-horizontal" name="searchRelationForm" id="searchRelationForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Relation Name<span style="color:red">*</span></label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Relation Name</label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchRelation" id="searchRelation" class="form-control" id="inlineFormInputGroup" placeholder="Relation Name" onkeypress="return validateTextField('searchRelation',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button id="searchBtn" type="button" class="btn  btn-primary" onclick="searchRelationList();">Search</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                        
                                        
                                        
                                        
                                        <div class="col-md-4">
                                            <form>
                                                <div class="button-list">
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" onclick="showAll('ALL');">Show All</button>
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" onclick="generateReport()">Reports</button>

                                                </div>
                                            </form>
                                        </div>

                                    </div> --%>

                                    

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

                                    <table class="table table-striped table-hover table-bordered ">
                                        <thead class="bg-success" style="color:#fff;">
                                            <tr>
                                                <th id="th2" class ="inner_md_htext">Relation Code</th>
                                                <th id="th3" class ="inner_md_htext">Relation Name</th>
                                                <th id="th4" class ="inner_md_htext"> New Relation Code</th>
                                                <th id="th5" class ="inner_md_htext"> New Relation Name</th>
                                                <th id="th6" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                     <tbody id="tblListOfRelation">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addRelationForm" name="addRelationForm" action="" method="POST">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Relation Code" class=" col-form-label inner_md_htext" >Relation Code <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="relationCode" id="relationCode" class="form-control" placeholder="Relation Code" onkeypress="return validateText('relationCode',2);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Relation Name <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="relationName" id="relationName" class="form-control" placeholder="Relation Name" onkeypress="return validateTextField('relationName',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-5 col-form-label inner_md_htext">New Relation Code <span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-7">
                                                                <input type="text" name="newRelationCode" id="newRelationCode" class="form-control" placeholder="New Relation code" onkeypress="return validateText('newRelationCode',2);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-5 col-form-label inner_md_htext">New Relation Name <span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-7">
                                                                <input type="text" name="newRelationName" id="newRelationName" class="form-control" placeholder="New Relation Name" onkeypress="return validateTextField('newRelationName',30);">
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
												
														<button type="button" id="btnAddRelation"
															class="btn  btn-primary " onclick="addRelationDetails();">Add</button>
														<button type="button" id="updateBtn"
															class="btn  btn-primary " onclick="updateRelationMaster();">Update</button>
														<button id="btnActive" type="button"
															class="btn  btn-primary " onclick="updateRelationStatus();">Activate</button>
														<button id="btnInActive" type="button"
															class="btn btn-primary  " onclick="updateRelationStatus();">Deactivate</button>
														<button type="button" class="btn btn-danger "
															onclick="Reset();">Reset</button>
												
											</div>
										</div>
									</div>
                                   <%--  <div class="row">
                                        <div class="col-md-7">
                                        </div>
                                        <div class="col-md-5">
                                            <form>
                                                <div class="button-list">

                                                    <button id="btnAddRelation" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addRelationDetails();">Add</button>
                                                    <button id ="updateBtn" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateRelationMaster();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateRelationStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateRelationStatus();">DeActivate</button>
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