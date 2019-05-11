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
	$j('#hospitalCode').attr('readonly', false);
			GetUnitTypeList();
			GetAllHospital('ALL');
			
		});
		
function GetAllHospital(MODE){
	 var hospitalName= jQuery("#searchHospital").attr("checked", true).val().toUpperCase();
		
	
	 var hospitalId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo, "hospitalName":""};			
		}else{
		var data = {"PN":nPageNo, "hospitalName":hospitalName};
		} 
	var url = "getAllHospital";		
	var bClickable = true;
	GetJsonData('tblListOfHospital',data,url,bClickable);	 
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
					var Status='Inactive'
				} 		
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].hospitalId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].hospitalCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].hospitalName+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].unitName+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfHospital").html(htmlTable); 	
	
}

var comboArray = [];
var hId;
var hospitalCode;
var hospitalName;
var hospitalStatus;
var unitId;
function executeClickEvent(hospitalId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(hospitalId == data.data[j].hospitalId){
			hId = data.data[j].hospitalId;			
			hospitalCode = data.data[j].hospitalCode;
			hospitalName = data.data[j].hospitalName;
			hospitalStatus = data.data[j].status;
			unitName = data.data[j].unitName;
			unitId = data.data[j].unitId;
			
			
		}
	}
	rowClick(hId,hospitalCode,hospitalName,hospitalStatus, unitName, unitId);
}

function rowClick(hId,hospitalCode,hospitalName,hospitalStatus, unitName){	
		
	document.getElementById("hospitalCode").value = hospitalCode;
	document.getElementById("hospitalName").value = hospitalName;
		
	 for(var j=0; j<comboArray.length;j++){		
		
		if(comboArray[j] == unitName){
			
			jQuery("#selectUnitList").val(unitId);
		}
	}
	 
	if(hospitalStatus == 'Y' || hospitalStatus == 'y'){
		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
	    $j("#updateBtn").show();
	    $j("#btnAddHospital").hide();
		
	}
	if(hospitalStatus == 'N' || hospitalStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j("#updateBtn").hide();
	}
	$j("#btnActive").attr("disabled", false);
	$j("#btnInActive").attr("disabled", false);
	$j('#updateBtn').attr("disabled", false);
	$j('#btnAddHospital').hide();
	
	$j('#hospitalCode').attr('readonly', true);
}


/* function searchHospitalList(){
	
	if(document.getElementById('searchHospital').value == "" || document.getElementById('searchHospital') == null){
		 alert("Plese Enter the Hospital Name");
		 return false;
	 }
		 	 
	 var hospitalName= jQuery("#searchHospital").attr("checked", true).val().toUpperCase();
		
		var nPageNo=1;
		var url = "getAllHospital";
		var data =  {"PN":nPageNo, "hospitalName":hospitalName};
		var bClickable = true;
		GetJsonData('tblListOfHospital',data,url,bClickable);	
		
		ResetForm();
} */


function GetUnitTypeList(){
	 
	 jQuery.ajax({
		 	crossOrigin: true,
		    method: "POST",			    
		    crossDomain:true,
		    url: "getUnitNameList",
		    data: JSON.stringify({}),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){
		    	//alert("success "+result.data.length);
		    	var combo = "<option value=\"\">Select</option>" ;
		    	
		    	for(var i=0;i<result.data.length;i++){
		    		comboArray[i] = result.data[i].unitName;
		    		combo += '<option value='+result.data[i].unitId+'>' +result.data[i].unitName+ '</option>';
		    		//alert("combo :: "+combo);
		    	}
		    	jQuery('#selectUnitList').append(combo);
		    }
		    
		});
}

function addHospitalDetails(){	
	if(document.getElementById('hospitalCode').value == null || document.getElementById('hospitalCode').value == ""){
		alert("Please Enter Hospital Code");
		return false;
	}
	if(document.getElementById('hospitalName').value == null || document.getElementById('hospitalName').value ==""){
		alert("Please Enter Hospital Name");
		return false;
	}
	if(document.getElementById('selectUnitList').value == null || document.getElementById('selectUnitList').value ==""){
		alert("Please Select Unit");
		return false;
	}
	
	var params = {
			 
			 'hospitalCode':jQuery('#hospitalCode').val(),
			 'hospitalName':jQuery('#hospitalName').val(),
			 'unitId':jQuery('#selectUnitList').find('option:selected').val()
	 } 
	var url="addHospital";
	//alert("params: "+JSON.stringify(params)); 
	SendJsonData(url,params);
	
		/* jQuery.ajax({
	 crossOrigin: true,
	    method: "POST",
	    header:{
	    	'Access-Control-Allow-Origin': '*'
	    	},
	    	crossDomain:true,
	    url: "addHospital",
	    data: JSON.stringify(params),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	        //alert("result" +result);
	         console.log(result);
	        if(result.status==1){     	
	        		
	        	showAll('ALL');
	        	document.getElementById("messageId").innerHTML = result.msg;
	        	$j('#messageId').toggle(5000);   	        	
	        }
	        if(result.status==2){	        	
	        	GetAllHospital('ALL')
	        	document.getElementById("messageId").innerHTML = result.msg;
	        	$j('#messageId').toggle(5000);
	        }
	        if(result.status==0){
	        	document.getElementById("messageId").innerHTML = result.msg;
	        	$j('#messageId').toggle(5000);
	        }
	    }
	    
	    
	});	 */
	
}


function updateHospitalMaster(){	
	var params = {
			 'hospitalId':hId,
			 'hospitalCode':jQuery('#hospitalCode').val(),
			 'hospitalName':jQuery('#hospitalName').val(),			 
			 'unitId':jQuery('#selectUnitList').find('option:selected').val()
	 } 
	var url="updateMasHospitalDetails";
	SendJsonData(url,params);
	
	//alert("params: "+JSON.stringify(params)); 	
		/* jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateMasHospitalDetails",
		    data: JSON.stringify(params),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){
		        //alert("result" +result);
		        console.log(result);
		        if(result.status==1){
		        		        	
		        	showAll('ALL');
		        	document.getElementById("messageId").innerHTML = result.msg;
		        	$j('#messageId').toggle(5000);
		        }
		        if(result.status==0){		        	
		        	showAll('ALL');
		        	document.getElementById("messageId").innerHTML = result.msg;
		        	$j('#messageId').toggle(5000);
		        }
		    }
		    
		    
		}); */
		$j('#updateBtn').attr("disabled", true);				
		$j('#hospitalCode').val('');
		$j('#hospitalName').val('');
		$j('#selectUnitList').val('')
		
}


function updateHospitalStatus(){
	
	if(document.getElementById('hospitalCode').value == null || document.getElementById('hospitalCode').value == ""){
		alert("Please Select the Hospital");
		return false;
	}
	if(document.getElementById('hospitalName').value == null || document.getElementById('hospitalName').value ==""){
		alert("Please Select the Hospital");
		return false;
	}
	if(document.getElementById('selectUnitList').value == null || document.getElementById('selectUnitList').value ==""){
		alert("Please Select the Hospital");
		return false;
	}
	
	var params = {
			'hospitalId':hId,
			'hospitalCode':jQuery('#hospitalCode').val(),
			 'status':hospitalStatus
			 
		}
	var url="updateMasHospitalStatus";
	SendJsonData(url,params);
	//alert("params: "+JSON.stringify(params)); 
	 /* jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateMasHospitalStatus",
		    data: JSON.stringify(params),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){
		        //alert("result" +result);
		        console.log(result);
		        if(result.status==1){
		        	
		        	showAll('ALL');
		        	document.getElementById("messageId").innerHTML = result.msg;
		        	$j('#messageId').toggle(5000);
		        }
		        if(result.status==0){
		        	
		        	showAll('ALL');
		        	document.getElementById("messageId").innerHTML = result.msg;
		        	$j('#messageId').toggle(5000);
		        }
		    }
		    		    
		}); */
	 
	$j("#btnActive").attr("disabled", true);
	$j("#btnInActive").attr("disabled", true);
	$j('#updateBtn').attr("disabled", true);				
	ResetForm();
}

function Reset(){
	document.getElementById("searchHospitalForm").reset();
	document.getElementById("addHospitalForm").reset();
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').hide();
	$j('#btnAddHospital').show();
	$j('#hospitalCode').attr('readonly', false);
	showAll();
}	

function showResultPage(pageNo)
{
	
	nPageNo = pageNo;	
	GetAllHospital('FILTER');
	
}

function enableAddButton(){
	if(document.getElementById("hospitalCode").value!=null || !document.getElementById("hospitalCode").value==""){
		$j('#btnAddHospital').attr("disabled", false);
	}else if( document.getElementById("hospitalName").value!=null || !document.getElementById("hospitalName").value==""){
		$j('#btnAddHospital').attr("disabled", false);
	}else{
		$j('#btnAddHospital').attr("disabled", true);
	}
}

function validTextBox(){
	if($j('#hospitalCode').val().length > 10){
		 alert("Hospital Code should be less than 10");
		 document.getElementById('hospitalCode').value="";
		 return false;
	 }
	if($j('#hospitalName').val().length > 30){
		 alert("Hospital Name should be less than 30");
		 document.getElementById('hospitalName').value="";
		 return false;
	 }
}

function ResetForm()
{
	$j('#hospitalCode').val('');
	$j('#hospitalName').val('');
	$j('#selectUnitList').val('');
	$j('#searchHospital').val('');
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetAllHospital('ALL');
	
}

function changeUnit(value){
	
	var unitId = jQuery('#selectUnitList').find('option:selected').val()
	if(value == unitId){
		$j('#updateBtn').attr("disabled", false);
	}
}


function generateReport()
{
	document.searchHospitalForm.action="${pageContext.request.contextPath}/report/generateHospitalReport";
	document.searchHospitalForm.method="POST";
	document.searchHospitalForm.submit(); 
	
}

function search()
{
	if(document.getElementById('searchHospital').value == ""){
		alert("Please Enter Hospital Name");
		return false;
	}
	nPageNo=1;
	GetAllHospital('Filter');
}
</script>
</head>

<body>

    <!-- Begin page -->
    <div id="wrapper">

        <!-- Top Bar Start -->
        <div class="topbar">

            <nav class="navbar-custom">
                

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
                 <div class="internal_Htext">Hospital Master</div>
                    <!-- <div class="row">
                        <div class="col-12">
                            <div class="page-title-box">
                               

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">Hospital Master</li>
                                </ol>

                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div> -->
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                       <br>
                                       <div class="row">
										<div class="col-md-8">
											<form class="form-horizontal" id="searchHospitalForm"
												name="searchHospitalForm" method="" role="form">
												<div class="form-group row">
													<label class="col-3 col-form-label">Hospital Name <span
														style="color: red">*</span></label>
													<div class="col-4">

														<input type="text" name="searchHospital" id="searchHospital"
															class="form-control" id="inlineFormInputGroup"
															placeholder="Hospital Name" onkeypress="return validateTextField('searchHospital',30);">

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
                                                <th id="th2" class ="inner_md_htext">Hospital Code</th>
                                                <th id="th3" class ="inner_md_htext">Hospital Name</th>
                                                <th id="th4" class ="inner_md_htext"> Unit Name</th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                     <tbody id="tblListOfHospital">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addHospitalForm" name="addHospitalForm" action="" method="POST">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Command Code" class=" col-form-label inner_md_htext" >Hospital Code <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="hospitalCode" id="hospitalCode" class="form-control" placeholder="Hospital Code" onkeypress="return validateText('hospitalCode',7);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Hospital Name <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="hospitalName" id="hospitalName" class="form-control" placeholder="Hospital Name" onkeypress="return validateTextField('hospitalName',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-5 col-form-label inner_md_htext">Unit Name<span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-7">
                                                                <select class="form-control" id="selectUnitList" onchange="changeUnit(this.value);">
                                                                    
                                                                </select>
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
												
														<button type="button" id="btnAddHospital"
															class="btn  btn-primary " onclick="addHospitalDetails();">Add</button>
														<button type="button" id="updateBtn"
															class="btn  btn-primary " onclick="updateHospitalMaster();">Update</button>
														<button id="btnActive" type="button"
															class="btn  btn-primary " onclick="updateHospitalStatus();">Activate</button>
														<button id="btnInActive" type="button"
															class="btn btn-primary  " onclick="updateHospitalStatus();">Deactivate</button>
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

                                                    <button id="btnAddHospital" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addHospitalDetails();">Add</button>
                                                    <button id ="updateBtn" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateHospitalMaster();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateHospitalStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateHospitalStatus();">Deactivate</button>
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