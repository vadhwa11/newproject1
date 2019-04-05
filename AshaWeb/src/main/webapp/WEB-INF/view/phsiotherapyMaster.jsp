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
				GetAllPhysiotherapy('ALL');
			
		});
		
function GetAllPhysiotherapy(MODE){
	 
	 var nursingId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo};			
		}else{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllPhysiotherapy";		
	var bClickable = true;
	GetJsonData('tblListOfPhsiotherapy',data,url,bClickable);	 
}
function makeTable(jsonData)
{	
	var htmlTable = "";	
	var data = jsonData.count; 
	//alert("data :: "+data);
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
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].nursingId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].nursingCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].nursingName+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].nursingType+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfPhsiotherapy").html(htmlTable); 	
	
}

var comboArray = [];
var nId;
var nursingCode;
var nursingName;
var nursingType;
var nursingStatus;
function executeClickEvent(nursingId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(nursingId == data.data[j].nursingId){
			nId = data.data[j].nursingId;			
			nursingCode = data.data[j].nursingCode;
			nursingName = data.data[j].nursingName;
			nursingStatus = data.data[j].status;
			nursingType = data.data[j].nursingType;
			//unitId = data.data[j].unitId;
			
			
		}
	}
	rowClick(nId,nursingCode,nursingName,nursingType,nursingStatus);
}

function rowClick(nId,nursingCode,nursingName,nursingType,nursingStatus){	
	document.getElementById("nursingCode").value = nursingCode;
	document.getElementById("nursingName").value = nursingName;
	document.getElementById("nursingType").value = nursingType;	
	
	 
	if(nursingStatus == 'Y' || nursingStatus == 'y'){
		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').attr("disabled", false);
	}
	if(nursingStatus == 'N' || nursingStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j('#updateBtn').attr("disabled", true);
	}
	
	
	$j("#btnActive").attr("disabled", false);
	$j("#btnInActive").attr("disabled", false);
	$j('#btnAddPhysiotherapy').attr("disabled", true);
}


function searchPhysioList(){
	
	if(document.getElementById('searchTherapy').value == "" || document.getElementById('searchTherapy') == null){
		 alert("Please Enter the Physiotherapy Name.");
		 return false;
	 }
		 	 
	 var nursingName= jQuery("#searchTherapy").attr("checked", true).val().toUpperCase();
		//alert(nursingName);
		var nPageNo=1;
		var url = "getAllPhysiotherapy";
		var data =  {"PN":nPageNo, "nursingName":nursingName};
		var bClickable = true;
		GetJsonData('tblListOfPhsiotherapy',data,url,bClickable);		
}


function addPhsiotherapyDetails(){	
	if(document.getElementById('nursingCode').value == null || document.getElementById('nursingCode').value == ""){
		alert("Please Enter Nursing Code.");
		return false;
	}
	if(document.getElementById('nursingName').value == null || document.getElementById('nursingName').value ==""){
		alert("Please Enter Nursing Name.");
		return false;
	}
	if(document.getElementById('nursingType').value == null || document.getElementById('nursingType').value ==""){
		alert("Please Enter Nursing Type.");
		return false;
	}
	
	var params = {
			 
			 'nursingCode':jQuery('#nursingCode').val(),
			 'nursingName':jQuery('#nursingName').val(),
			 'nursingType':jQuery('#nursingType').val()
	 } 
	
	//alert("params: "+JSON.stringify(params)); 
	
		jQuery.ajax({
	 crossOrigin: true,
	    method: "POST",
	    header:{
	    	'Access-Control-Allow-Origin': '*'
	    	},
	    	crossDomain:true,
	    url: "addPhsiotherapy",
	    data: JSON.stringify(params),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	        //alert("result" +result);
	         console.log(result);
	        if(result.status==1){     	
	        		
	        	GetAllPhysiotherapy('ALL')
	        	document.getElementById("messageId").innerHTML = result.msg;
	        	$j('#messageId').toggle(5000);   	        	
	        }
	        if(result.status==2){	        	
	        	GetAllPhysiotherapy('ALL')
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


function updatePhsiotherapyMaster(status){
	//alert("status :: "+status);
	if(document.getElementById('nursingCode').value == null || document.getElementById('nursingCode').value == ""){
		alert("Please Enter Nursing Code.");
		return false;
	}
	if(document.getElementById('nursingName').value == null || document.getElementById('nursingName').value ==""){
		alert("Please Enter Nursing Name.");
		return false;
	}
	if(document.getElementById('nursingType').value == null || document.getElementById('nursingType').value ==""){
		alert("Please Enter Nursing Type.");
		return false;
	}
	var params = {
			 'nursingId':nId,
			 'nursingCode':jQuery('#nursingCode').val(),
			 'nursingName':jQuery('#nursingName').val(),			 
			 'nursingType':jQuery('#nursingType').val(),
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
		    url: "updatePhsiotherapyDetails",
		    data: JSON.stringify(params),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){
		        //alert("result" +result);
		        console.log(result);
		        if(result.status==1){
		        		        	
		        	GetAllPhysiotherapy('ALL')
		        	document.getElementById("messageId").innerHTML = result.msg;
		        	$j('#messageId').toggle(5000);
		        }
		        if(result.status==0){
		        	
		        	GetAllPhysiotherapy('ALL')
		        	document.getElementById("messageId").innerHTML = result.msg;
		        	$j('#messageId').toggle(5000);
		        }
		    }
		    
		    
		});
	
		$j("#btnActive").attr("disabled", true);
		$j("#btnInActive").attr("disabled", true);
		$j('#updateBtn').attr("disabled", true);
}

function Reset(){
	document.getElementById("searchTherapyForm").reset();
	document.getElementById("addPhsiotherapyForm").reset();
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddPhysiotherapy').attr("disabled", false);
	
}	

function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetAllPhysiotherapy('FILTER');
	
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
                                <h5 class="text-overflow"><small>Welcome ! Physiotherapy/Procedurer</small> </h5>
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
                                <h4 class="page-title float-left">Physiotherapy/Procedure Master</h4>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">Physiotherapy/Procedure Master</li>
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
                                            <form class="form-horizontal" id="searchTherapyForm" name="searchTherapyForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Physiotherapy/Procedure Name*</label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Physiotherapy/Procedure Name*</label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchTherapy" id="searchTherapy" class="form-control" id="inlineFormInputGroup" placeholder="Physiotherapy/Procedure Name">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn  btn-primary" onclick="searchPhysioList();">Search</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                        
                                        
                                        
                                        
                                        <div class="col-md-4">
                                            <form>
                                                <div class="button-list">
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" onclick="GetAllPhysiotherapy('ALL');">Show All</button>
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
                                                <th id="th2" class ="inner_md_htext">Physiotherapy/Procedure Code</th>
                                                <th id="th3" class ="inner_md_htext">Physiotherapy/Procedure Name</th>
                                                <th id="th4" class ="inner_md_htext"> Physiotherapy/Procedure Type</th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                     <tbody id="tblListOfPhsiotherapy">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addPhsiotherapyForm" name="addPhsiotherapyForm" action="" method="POST">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Command Code" class=" col-form-label inner_md_htext" >Physiotherapy/Procedure Code</label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="nursingCode" id="nursingCode" class="form-control" placeholder="Physiotherapy/Procedure Code">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Physiotherapy/Procedure Name</label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="nursingName" id="nursingName" class="form-control" placeholder="Physiotherapy/Procedure Name">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-5 col-form-label inner_md_htext">Physiotherapy/Procedure Type</label>
                                                            
                                                            <div class="col-sm-7">
                                                              <input type="text" name="nursingType" id="nursingType" class="form-control" placeholder="Physiotherapy/Procedure Type">
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

                                                    <button id="btnAddPhysiotherapy" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addPhsiotherapyDetails();">Add</button>
                                                    <button id ="updateBtn" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updatePhsiotherapyMaster('update');">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updatePhsiotherapyMaster('active');">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updatePhsiotherapyMaster('inactive');">DeActivate</button>
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