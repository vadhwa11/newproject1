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
	  
			
	GetAllDisposal('ALL');
			
		});
function GetAllDisposal(MODE){
	 
	 var disposalId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo};			
		}else{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllDisposal";		
	var bClickable = true;
	GetJsonData('tblListOfDisposal',data,url,bClickable);	 
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
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].disposalId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].disposalCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].disposalName+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].disposalType+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfDisposal").html(htmlTable); 	
	
}

var dispoId;
var dispoCode;
var dispoName;
var dispoStatus;
var dispoType;
function executeClickEvent(disposalId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(disposalId == data.data[j].disposalId){
			dispoId = data.data[j].disposalId;			
			dispoCode = data.data[j].disposalCode;			
			dispoName = data.data[j].disposalName;
			dispoType = data.data[j].disposalType;
			dispoStatus = data.data[j].status;
			
			
		}
	}
	rowClick(dispoId,dispoCode,dispoName,dispoType,dispoStatus);
}

function rowClick(dispoId,dispoCode,dispoName,dispoType,dispoStatus){	
		
	document.getElementById("disposalCode").value = dispoCode;
	document.getElementById("disposalName").value = dispoName;
	
		if(dispoType == 'OP'){
			
			jQuery("#disposalType").val(dispoType);
			
		}
		
		if(dispoType == 'IP'){
			
			jQuery("#disposalType").val(dispoType);
			
		}
	 
	if(dispoStatus == 'Y' || dispoStatus == 'y'){		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').attr("disabled", false);
	}
	if(dispoStatus == 'N' || dispoStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j('#updateBtn').attr("disabled", true);
	}
	
	$j("#btnActive").attr("disabled", false);
	$j("#btnInActive").attr("disabled", false);
	/* $j('#updateBtn').attr("disabled", false); */
	$j('#btnAddDisposal').attr("disabled", true);
	
}



function searchDisposalList(){	
	if(document.getElementById('searchDisposal').value == "" || document.getElementById('searchDisposal') == null){
		 alert("Plese Enter the Disposal Name");
		 return false;
	 }
		 	 
	 var disposalName= jQuery("#searchDisposal").attr("checked", true).val().toUpperCase();
		
		var nPageNo=1;
		var url = "getAllDisposal";
		var data =  {"PN":nPageNo, "disposalName":disposalName};
		var bClickable = true;
		GetJsonData('tblListOfDisposal',data,url,bClickable);	
		ResetForm();
}


function updateDisposalMaster(){	
	if(document.getElementById('disposalCode').value == null || document.getElementById('disposalCode').value == ""){
		alert("Please Enter Disposal Code");
		return false;
	}
	if(document.getElementById('disposalName').value == null || document.getElementById('disposalName').value ==""){
		alert("Please Enter Disposal Name");
		return false;
	}
	if(document.getElementById('disposalType').value == null || document.getElementById('disposalType').value == ""){
		alert("Please Enter New disposal Type");
		return false;
	}
	
	
	var params = {
			 'disposalId':dispoId,
			 'disposalCode':jQuery('#disposalCode').val(),
			 'disposalName':jQuery('#disposalName').val(),			 
			 'disposalType':jQuery('#disposalType').val()
			
	 } 
	
	//alert("params: "+JSON.stringify(params)); 	
		jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateDisposalDetails",
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

function updateDisposalStatus(){
	
	if(document.getElementById('disposalCode').value == null || document.getElementById('disposalCode').value == ""){
		alert("Please Enter Disposal Code");
		return false;
	}
	if(document.getElementById('disposalName').value == null || document.getElementById('disposalName').value ==""){
		alert("Please Enter Disposal Name");
		return false;
	}
	if(document.getElementById('disposalType').value == null || document.getElementById('disposalType').value == ""){
		alert("Please Enter New disposal Type");
		return false;
	}
	
	var params = {
			'disposalId':dispoId,
			 'disposalCode':jQuery('#disposalCode').val(),
			 'status':dispoStatus
			 
		} 
	//alert("params: "+JSON.stringify(params)); 
	 jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateDisposalStatus",
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

function addDisposalDetails(){
	
	if(document.getElementById('disposalCode').value == null || document.getElementById('disposalCode').value == ""){
		alert("Please Enter Disposal Code");
		return false;
	}
	if(document.getElementById('disposalName').value == null || document.getElementById('disposalName').value ==""){
		alert("Please Enter Disposal Name");
		return false;
	}
	if(document.getElementById('disposalType').value == null || document.getElementById('disposalType').value == ""){
		alert("Please Enter New disposal Type");
		return false;
	}
	
	var params = {
			 
			 'disposalCode':jQuery('#disposalCode').val(),
			 'disposalName':jQuery('#disposalName').val(),
			 'disposalType':jQuery('#disposalType').val()
			 			 
	 } 
	
	//alert("params: "+JSON.stringify(params)); 
	
		jQuery.ajax({
	 crossOrigin: true,
	    method: "POST",
	    header:{
	    	'Access-Control-Allow-Origin': '*'
	    	},
	    	crossDomain:true,
	    url: "addDisposal",
	    data: JSON.stringify(params),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	        //alert("result" +result);
	         console.log(result);
	        if(result.status==1){	        	
	        	       	
	        	GetAllDisposal('ALL')
	        	document.getElementById("messageId").innerHTML = result.msg;
	        	$j('#messageId').toggle(5000);   	        	
	        }
	        if(result.status == 2){
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
function changeDisposalType(value){
		
	if(value == 'OP'){
		$j('#updateBtn').attr("disabled", false);
	}	
	if(value == 'IP'){
		$j('#updateBtn').attr("disabled", false);
	}
}

function Reset(){
	document.getElementById("searchDisposalForm").reset();
	document.getElementById("addDisposalForm").reset();
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddDisposal').attr("disabled", false);
	
}	

function enableAddButton(){
	if(document.getElementById("disposalCode").value!=null || !document.getElementById("disposalCode").value==""){
		$j('#btnAddDisposal').attr("disabled", false);
	}else if( document.getElementById("disposalName").value!=null || !document.getElementById("disposalName").value==""){
		$j('#btnAddDisposal').attr("disabled", false);
	}else{
		$j('#btnAddDisposal').attr("disabled", true);
	}
}

function validTextBox(){
	if($j('#disposalCode').val().length >= 10){
		 alert("Relation Code should be less or equal to 2");
		 document.getElementById('disposalCode').value="";
		 return false;
	 }
	if($j('#disposalName').val().length >=2){
		 alert("Disposal Name should be less or equal to 2");
		 document.getElementById('disposalName').value="";
		 return false;
	 }
}

function ResetForm()
{
	$j('#disposalCode').val('');
	$j('#disposalName').val('');
	$j('#disposalType').val('');
	$j('#searchDisposal').val('');
	
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetAllDisposal('ALL');
	
}

 function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetAllDisposal('FILTER');
	
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
                                <h5 class="text-overflow"><small>Welcome ! Disposal Master</small> </h5>
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
                                <h4 class="page-title float-left">Disposal Master</h4>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">Disposal Master</li>
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
                                            <form class="form-horizontal" id="searchDisposalForm" name="searchDisposalForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Disposal Name <span style="color:red">*</span></label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Disposal Name <span style="color:red">*</span></label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchDisposal" id="searchDisposal" class="form-control" id="inlineFormInputGroup" placeholder="Disposal Name" onkeypress="return validateTextField('searchDisposal',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button id="searchBtn" type="button" class="btn  btn-primary" onclick="searchDisposalList();">Search</button>
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
                                                <th id="th2" class ="inner_md_htext">Disposal Code</th>
                                                <th id="th3" class ="inner_md_htext">Disposal Name</th>
                                                <th id="th4" class ="inner_md_htext">Disposal Type</th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                     <tbody id="tblListOfDisposal">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addDisposalForm" name="addDisposalForm" action="" method="POST">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Command Code" class=" col-form-label inner_md_htext" >Disposal Code <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="disposalCode" id="disposalCode" class="form-control" placeholder="Disposal Code" onkeypress="return validateText('disposalCode',7);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Disposal Name <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="disposalName" id="disposalName" class="form-control" placeholder="Disposal Name" onkeypress="return validateTextField('disposalName',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-5 col-form-label inner_md_htext">Disposal Type <span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-6">
                                                                <select class="form-control" id="disposalType" onchange="changeDisposalType(this.value);">
                                                                    <option value="">Select</option>
				                                                	<option value="OP">OP</option>
				                                                	<option value="IP">IP</option>
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

                                                    <button id="btnAddDisposal" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addDisposalDetails();">Add</button>
                                                    <button id ="updateBtn" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateDisposalMaster();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateDisposalStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateDisposalStatus();">DeActivate</button>
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
                Disposal Master
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