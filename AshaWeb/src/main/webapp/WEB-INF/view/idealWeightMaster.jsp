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
  
<script type="text/javascript">
nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
	$j("#btnActive").hide();
	$j("#btnInActive").hide();		
	$j('#updateBtn').attr("disabled", true);
	$j('#selectAgeDiv').hide();
	$j('#selectHeightDiv').hide();
			
	GetAge();
	GetAllIdealWeight('ALL');
	GetAllAdministrativeSex();		
		});
function GetAllIdealWeight(MODE){	 
	 var idealWeightId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo};			
		}else{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllIdealWeight";		
	var bClickable = true;
	GetJsonData('tblListOfIdealWeight',data,url,bClickable);	 
}


function makeTable(jsonData)
{	
	var Gender ="";
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
		 
		 if(dataList[i].genderId==1 )
			{
				 Gender='Male'
			}
			else if(dataList[i].genderId == 2)
			{
				 Gender='FeMale'
			} 
			else{
				 Gender='Transender'
			}
		 
		 if(dataList[i].toHeight == "" ){
			 var toHeight = "";
		 }else{
			 var toHeight = dataList[i].toHeight;
		 }
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].idealWeightId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+Gender+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].fromAge+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].toAge+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].fromHeight+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+toHeight+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].weight+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfIdealWeight").html(htmlTable); 	
	
}

var comboArray = [];
var idealWtId;
var idealWtFromHeight;
var idealWtToHeight;
var idealWtFromAge;
var idealWtToAge;
var idealWtWeight;
var idealWtStatus;
var genderId;
function executeClickEvent(idealWeightId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(idealWeightId == data.data[j].idealWeightId){
			idealWtId = data.data[j].idealWeightId;
			genderId = data.data[j].genderId;
			alert(idealWtId);
			idealWtFromAge = data.data[j].fromAge;			
			idealWtToAge = data.data[j].toAge;
			idealWtFromHeight = data.data[j].fromHeight;
			idealWtToHeight = data.data[j].toHeight;
			idealWtWeight = data.data[j].weight;
			idealWtStatus = data.data[j].status;
			
			
		}
	}
	rowClick(idealWtId,genderId,idealWtFromAge,idealWtToAge,idealWtFromHeight,idealWtToHeight,idealWtWeight,idealWtStatus);
}

function rowClick(idealWtId,genderId,idealWtFromAge,idealWtToAge,idealWtFromHeight,idealWtToHeight,idealWtWeight,idealWtStatus){	
	document.getElementById("ideaWeight").value = idealWtWeight;
	document.getElementById("toHeight").value = idealWtToHeight;
	document.getElementById("fromHeight").value = idealWtFromHeight;
	document.getElementById("selectGender").value = genderId;
	document.getElementById("selectAge").value = age;	 
	
	for(var j=0; j<comboArray.length;j++){		
		if(comboArray[j] == idealWtId){
			
			jQuery("#selectAge").val(idealWtId);
		}
	}
	
	if(idealWtStatus == 'Y' || idealWtStatus == 'y'){		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
	}
	if(idealWtStatus == 'N' || idealWtStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
	}
	
	$j('#updateBtn').attr("disabled", false);
	$j('#addIdealWeight').attr("disabled", true);
	
	 $j("#btnActive").attr("disabled", false);
	 $j("#btnInActive").attr("disabled", false);
	
}

function GetAge(){
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getAgeList",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	    	
	    	var combo = "<option value=\"\">Select</option>" ;	    	
	    	for(var i=0;i<result.data.length;i++){	    		
	    		comboArray[i] = result.data[i].age;	    		
	    		combo += '<option value='+result.data[i].age+'>' +result.data[i].age+ '</option>';	    			    		
	    	}
	    	jQuery('#selectAge').append(combo);
	    }
	    
	});
	
}

function GetAllAdministrativeSex(){
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getAllAdministrativeSex",
	    data: JSON.stringify({"PN":1}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){	    	
	    	var combo = "<option value=\"\">Select</option>" ;	    	
	    	for(var i=0;i<result.data.length;i++){	    		
	    		comboArray[i] = result.data[i].administrativeSexName;	    		
	    		combo += '<option value='+result.data[i].administrativeSexId+'>' +result.data[i].administrativeSexName+ '</option>';	    			    		
	    	}
	    	jQuery('#selectGender').append(combo);
	    }	    
	});
}

var adminSexId='';
function changeGenderId(){
	adminSexId = $j('#selectGender').find('option:selected').val();
	
	if(adminSexId == 1 || adminSexId == 2 || adminSexId == 3){
		getAgeListOfMasRange();
		
	}
}

function getAgeListOfMasRange(){
	jQuery('#selectAge').html("");
	jQuery('#selectHeight').html("");
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getMasRange",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){	    	
	    	var combo = "<option value=\"\">Select</option>";	
	    	var combo1 = "<option value=\"\">Select</option>";	
	    	for(var i=0;i<result.data.length;i++){  
	    		//alert("height :: "+result.data[i].fromHeightToHeight);
	    		comboArray[i] = result.data[i].rangeId;	
	    		
	    		if(result.data[i].rangeFlag == 'A' && result.data[i].genderId == adminSexId){
	    			combo += '<option value='+result.data[i].rangeId+'>' +result.data[i].fromAgeToAge+ '</option>';
	    		}else if(result.data[i].rangeFlag == 'H' && result.data[i].genderId == adminSexId){
	    			//alert("adminSexId :: "+adminSexId +"height :: "+result.data[i].fromHeightToHeight);
	    			
	    			combo1 += '<option value='+result.data[i].rangeId+'>' +result.data[i].fromHeightToHeight+ '</option>';
	    			
	    			
	    		}/* else if(result.data[i].rangeFlag == 'A' && result.data[i].genderId == adminSexId){
	    			combo += '<option value='+result.data[i].rangeId+'>' +result.data[i].fromAgeToAge+ '</option>';
	    			combo1 += '<option value='+result.data[i].rangeId+'>' +result.data[i].fromHeightToHeight+ '</option>';
	    		}	 */    		
	    	}
	    	jQuery('#selectAge').append(combo);
	    	jQuery('#selectHeight').append(combo1);
	    }
	    
	});
	$j('#selectAgeDiv').show();
	$j('#selectHeightDiv').show();
}

function getHeightListOfMasRange(){
	jQuery('#selectAge').html("");
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getMasRange",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){	    	
	    	var combo = "<option value=\"\">Select</option>" ;	    	
	    	for(var i=0;i<result.data.length;i++){    		
	    		comboArray[i] = result.data[i].rangeId;	
	    		if(result.data[i].rangeFlag == 'H' && result.data[i].genderId == 1){
	    		combo += '<option value='+result.data[i].rangeId+'>' +result.data[i].fromHeightToHeight+ '</option>';	    			    		
	    		}	    		
	    	}
	    	jQuery('#selectHeight').append(combo);
	    	
	    }
	    
	});
	$j('#selectAgeDiv').show();
	$j('#selectHeightDiv').show();
}
function searchIdealWeightList(){	
	if(document.getElementById('sGenderList').value == "" || document.getElementById('sGenderList') == null){
		 alert("Plese Enter the Hospital Name");
		 return false;
	 }
		 	 
	 var genderId= jQuery("#sGenderList").val();
	 		
		var nPageNo=1;
		var url = "getAllIdealWeight";
		var data =  {"PN":nPageNo, "genderId":genderId};
		var bClickable = true;
		GetJsonData('tblListOfIdealWeight',data,url,bClickable);		
}

function addIdealWeightDetails(){
		
	if(document.getElementById('selectGender').value=="" || document.getElementById('selectGender')==null){
		alert("Please Select Gender");
		return false;
	}
	 
	if(document.getElementById('selectHeight').value=="" || document.getElementById('selectHeight')==null){
		alert("Please Select the Height");
		return false;
	}
	
	if(document.getElementById('selectAge').value=="" || document.getElementById('selectAge')==null){
		alert("Please Enter the Age");
		return false;
	} 
	
	if(document.getElementById('ideaWeight').value=="" || document.getElementById('ideaWeight')==null){
		alert("Please Enter the Weight");
		return false;
	}
	
	var params = {			 
			 'administrativeSexId':jQuery('#selectGender').find('option:selected').val(),// jQuery('#ideaWeight').val(),
			 'masRange1':jQuery('#selectHeight').find('option:selected').val(),
			 'masRange2':jQuery('#selectAge').find('option:selected').val(),
			 'weight':jQuery('#ideaWeight').val()
	 } 	
	var url="addIdealWeight";
	SendJsonData(url,params);
	}
	
function updateIdealWeight()
{	
	if(document.getElementById('selectGender').value=="" || document.getElementById('selectGender')==null){
		alert("Please Select Gender");
		return false;
	}
	 
	if(document.getElementById('selectHeight').value=="" || document.getElementById('toHeight')==null){
		alert("Please Select the Height");
		return false;
	}
	
	if(document.getElementById('selectAge').value=="" || document.getElementById('selectAge')==null){
		alert("Please Enter the Age");
		return false;
	} 
	
	if(document.getElementById('ideaWeight').value=="" || document.getElementById('ideaWeight')==null){
		alert("Please Enter the Weight");
		return false;
	}
		
	var params = {
			 'idealWeightId':idealWtId,
			 'weight':jQuery('#ideaWeight').val(),
			 'toHeight':jQuery('#toHeight').val(),
			 'fromHeight':jQuery('#fromHeight').val(),
			 'genderId':jQuery('#selectGender').find('option:selected').val(),
			 'selectAge':jQuery('#selectAge').find('option:selected').text(),
			 'status':idealWtStatus
	 } 
	var url="updateIdealWeight";
	SendJsonData(url,params);	
 		 		
	$j('#updateBtn').attr("disabled", true);
	$j('#addIdealWeight').attr("disabled", false);
	ResetForm();
}


 function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetAllIdealWeight('FILTER');
	
} 
 function Reset(){
		document.getElementById("addIdealWeightForm").reset();
		document.getElementById("idealWeightForm").reset();
		document.getElementById('sGenderList').value = "";
		
		$j("#btnActive").hide();
		$j("#btnInActive").hide();
		$j('#updateBtn').attr("disabled", true);
		$j('#btnAddUsers').attr("disabled", false);
		document.getElementById("messageId").innerHTML = "";
		$("#messageId").css("color", "black");
		
	}



	function ResetForm()
	{	
		$j('#ideaWeight').val('');
		$j('#selectHeight').val('');
		$j('#selectAge').val('');
		$j('#selectGender').val('');
		$j('#sGenderList').val('');
		
	}

	function showAll()
	{
		ResetForm();
		nPageNo = 1;	
		GetAllIdealWeight('ALL');
		
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
                                <h5 class="text-overflow"><small>Welcome ! Ideal Weight Master</small> </h5>
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
                <div class="internal_Htext">Ideal Weight Master</div>
                    <div class="row">
                      
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
                                            <form class="form-horizontal" id="idealWeightForm" name="idealWeightForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Gender <span style="color:red">*</span></label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Gender <span style="color:red">*</span></label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <div class="col-md-6">
                                                                <select class="form-control" id="sGenderList"  onchange="">
                                                                    <option value="0">Select</option>
                                                                    <option value="1">Male</option>
                                                                    <option value="2">FeMale</option>
                                                                    <option value="3">Transgender</option>
                                                                </select>
                                                            </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn  btn-primary" onclick="searchIdealWeightList();">Search</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                        
                                        
                                        
                                        
                                        <div class="col-md-4">
                                            <form>
                                                <div class="button-list">
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" onclick="GetAllIdealWeight('ALL');">Show All</button>
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
                                                <th id="th2" class ="inner_md_htext">Gender</th>
                                                <th id="th3" class ="inner_md_htext">From Age</th>
                                                <th id="th3" class ="inner_md_htext">To Age</th>
                                                <th id="th4" class ="inner_md_htext">From Height</th>
                                                <th id="th4" class ="inner_md_htext">To Height</th>
                                                <th id="th5" class ="inner_md_htext">Ideal Weight</th>
                                                <th id="th6" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                     <tbody id="tblListOfIdealWeight">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addIdealWeightForm" name="addIdealWeightForm" action="" method="POST">
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="" class=" col-form-label inner_md_htext" >Gender <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-md-7">
                                                                <select class="form-control" id="selectGender" name="selectGender" onchange="changeGenderId();">
                                                                   
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                       <div class="form-group row" id="selectAgeDiv">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Age <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-md-7">
                                                                <select class="form-control" id="selectAge" name="selectAge">
                                                                    
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <div class="form-group row" id="selectHeightDiv">                                                        
                                                            <label for="recordoffice" class="col-sm-5 col-form-label inner_md_htext"> Height <span style="color:red">*</span></label>  
                                                             <div class="col-md-7">                                                          
                                                             <select class="form-control" id="selectHeight" name="selectHeight">
                                                                    
                                                                </select>
                                                            </div>
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="col-md-3">
                                                        <div class="form-group row">                                                        
                                                            <label for="recordoffice" class="col-sm-4 col-form-label inner_md_htext">Ideal Weight <span style="color:red">*</span></label>                                                            
                                                            <div class="col-md-6">
                                                                 <input type="text" class="form-control" id="ideaWeight" name="ideaWeight" placeholder="Ideal Weight" onkeypress="return validateText('ideaWeight',3);"/>
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

                                                    <button id="addIdealWeight" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addIdealWeightDetails();">Add</button>
                                                    <button id ="updateBtn" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateIdealWeight();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateIdealWeight('a');">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateIdealWeight('i');">DeActivate</button>
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