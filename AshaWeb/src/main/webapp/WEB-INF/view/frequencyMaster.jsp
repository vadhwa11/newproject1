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
		GetAllOPDFrequency('ALL');			
		});
		
function GetAllOPDFrequency(MODE){
	 
	 var frequencyId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo};			
		}else{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllOpdFrequency";		
	var bClickable = true;
	GetJsonData('tblListOfFrequency',data,url,bClickable);	 
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
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].frequencyId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].frequencyCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].frequencyName+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfFrequency").html(htmlTable); 	
	
}

var freqId;
var freqCode;
var freqName;
var freqStatus;

function executeClickEvent(frequencyId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(frequencyId == data.data[j].frequencyId){
			freqId = data.data[j].frequencyId;			
			freqCode = data.data[j].frequencyCode;			
			freqName = data.data[j].frequencyName;
			freqStatus = data.data[j].status;
			
			
		}
	}
	rowClick(freqId,freqCode,freqName,freqStatus);
}

function rowClick(freqId,freqCode,freqName,freqStatus){	
		
	document.getElementById("frequencyCode").value = freqCode;
	document.getElementById("frequencyName").value = freqName;
	
			 
	if(freqStatus == 'Y' || freqStatus == 'y'){		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').attr("disabled", false);
	}
	if(freqStatus == 'N' || freqStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j('#updateBtn').attr("disabled", true);
	}
	$j('#updateBtn').show();
	$j("#btnActive").attr("disabled", false);
	$j("#btnInActive").attr("disabled", false);
	/* $j('#updateBtn').attr("disabled", false); */
	$j('#btnAddFrequency').attr("disabled", true);
	
}

function searchFrequencyList(){	
	if(document.getElementById('searchFrequency').value == "" || document.getElementById('searchFrequency') == null){
		 alert("Plese Enter the Frequency Name");
		 return false;
	 }
		 	 
	 var frequencyName= jQuery("#searchFrequency").attr("checked", true).val().toUpperCase();
		
		var nPageNo=1;
		var url = "getAllOpdFrequency";
		var data =  {"PN":nPageNo, "frequencyName":frequencyName};
		var bClickable = true;
		GetJsonData('tblListOfFrequency',data,url,bClickable);
		ResetForm();
}

function updateFrequency(){	
	if(document.getElementById('frequencyCode').value == null || document.getElementById('frequencyCode').value == ""){
		alert("Please Enter Frequency Code");
		return false;
	}
	if(document.getElementById('frequencyName').value == null || document.getElementById('frequencyName').value ==""){
		alert("Please Enter Frequency Name");
		return false;
	}
		
	
	var params = {
			 'frequencyId':freqId,
			 'frequencyCode':jQuery('#frequencyCode').val(),
			 'frequencyName':jQuery('#frequencyName').val()			 
			
	 } 
	
	//alert("params: "+JSON.stringify(params)); 	
		jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateOpdFrequencyDetails",
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

function updateFrequencyStatus(){
	if(document.getElementById('frequencyCode').value == null || document.getElementById('frequencyCode').value == ""){
		alert("Please Enter Frequency Code");
		return false;
	}
	if(document.getElementById('frequencyName').value == null || document.getElementById('frequencyName').value ==""){
		alert("Please Enter Frequency Name");
		return false;
	}
	
	var params = {
			'frequencyId':freqId,
			 'frequencyCode':jQuery('#frequencyCode').val(),
			 'status':freqStatus
			 
		} 
	//alert("params: "+JSON.stringify(params)); 
	 jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateOpdFrequencyStatus",
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

function addFrequencyDetails(){
	if(document.getElementById('frequencyCode').value == null || document.getElementById('frequencyCode').value == ""){
		alert("Please Enter Frequency Code");
		return false;
	}
	if(document.getElementById('frequencyName').value == null || document.getElementById('frequencyName').value ==""){
		alert("Please Enter Frequency Name");
		return false;
	}
	
	
	var params = {			 
			 'frequencyCode':jQuery('#frequencyCode').val(),
			 'frequencyName':jQuery('#frequencyName').val()			 			 
	 } 
	
	//alert("params: "+JSON.stringify(params)); 
	
		jQuery.ajax({
	 crossOrigin: true,
	    method: "POST",
	    header:{
	    	'Access-Control-Allow-Origin': '*'
	    	},
	    	crossDomain:true,
	    url: "addOpdFrequency",
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
	        	document.getElementById("messageId").innerHTML = result.msg;
	        	$j('#messageId').toggle(5000);
	        }
	        if(result.status==0){
	        	document.getElementById("messageId").innerHTML = result.msg;
	        	$j('#messageId').toggle(5000);
	        }
	    }
	    
	    
	});	
	
}

function Reset(){
	document.getElementById("searchFrequencyForm").reset();
	document.getElementById("addFrequencyForm").reset();
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddFrequency').attr("disabled", false);
	
}	


function enableAddButton(){
	if(document.getElementById("frequencyCode").value!=null || !document.getElementById("frequencyCode").value==""){
		$j('#btnAddFrequency').attr("disabled", false);
	}else if( document.getElementById("frequencyName").value!=null || !document.getElementById("frequencyName").value==""){
		$j('#btnAddFrequency').attr("disabled", false);
	}else{
		$j('#btnAddFrequency').attr("disabled", true);
	}
}

function validTextBox(){
	if($j('#frequencyCode').val().length >= 10){
		 alert("Frequency Code should be equal to 10");
		 document.getElementById('frequencyCode').value="";
		 return false;
	 }
	if($j('#frequencyName').val().length >=20){
		 alert("Frequency Name should be equal to 10");
		 document.getElementById('frequencyName').value="";
		 return false;
	 }
}

function ResetForm()
{
	$j('#frequencyCode').val('');
	$j('#frequencyName').val('');
	$j('#searchFrequency').val('');
	
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetAllOPDFrequency('ALL');
	
}

 function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetAllOPDFrequency('FILTER');
	
} 

 function isValidLengthOfFreqCode(){
	
		 if(frequencyCode.value.length >= 7){
			 alert("Please Enter Valid Length of Frequency");
			 return false;
		 }
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
                                <h5 class="text-overflow"><small>Welcome ! Frequency Master</small> </h5>
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
                                <h4 class="page-title float-left">Frequency Master</h4>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">Frequency Master</li>
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
                                            <form class="form-horizontal" id="searchFrequencyForm" name="searchFrequencyForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Frequency Name <span style="color:red">*</span></label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Frequency Name <span style="color:red">*</span></label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchFrequency" id="searchFrequency" class="form-control" id="inlineFormInputGroup" placeholder="Frequency Name" onkeypress="return validateTextField('searchFrequency',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button id="searchBtn" type="button" class="btn  btn-primary" onclick="searchFrequencyList();">Search</button>
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
		</table>

                                    <table class="table table-hover table-bordered">
                                        <thead class="bg-success" style="color:#fff;">
                                            <tr>
                                                <th id="th2" class ="inner_md_htext">Frequency Code</th>
                                                <th id="th3" class ="inner_md_htext">Frequency Name</th>
                                                <th id="th4" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                     <tbody id="tblListOfFrequency">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addFrequencyForm" name="addFrequencyForm" action="" method="POST">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Command Code" class=" col-form-label inner_md_htext" >Frequency Code <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="frequencyCode" id="frequencyCode" class="form-control" placeholder="Frequency Code" onkeypress="return validateText('frequencyCode',7);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Frequency Name <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="frequencyName" id="frequencyName" class="form-control" placeholder="Frequency Name" onkeypress="return validateTextField('frequencyName',30);">
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

                                                    <button id="btnAddFrequency" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addFrequencyDetails();">Add</button>
                                                    <button id ="updateBtn" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateFrequency();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateFrequencyStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateFrequencyStatus();">DeActivate</button>
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