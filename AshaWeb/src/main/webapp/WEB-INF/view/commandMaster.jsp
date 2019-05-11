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
			/* $j('#updateBtn').attr("disabled", true); */
			$j('#updateBtn').hide();
			$j('#commandCode').attr('readonly', false);
			GetCommandList('ALL');
			GetCommandTypeList();
			
		});
		
function GetCommandList(MODE)
{
	var cmdName= jQuery("#searchCommand").attr("checked", true).val().toUpperCase();
	
	var cmdId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo,"commandName":""};			
		}
	else
		{
		var data = {"PN":nPageNo, "commandName":cmdName};
		} 
	 
	var url = "getAllCommandDetails";		
	var bClickable = true;
	GetJsonData('tblListOfCommand',data,url,bClickable);
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
		 
		
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].cmdId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].cmdCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].cmdName+"</td>";			
			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].cmdType+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>";
			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   //alert('No Record Found');
		}			
	
	$j("#tblListOfCommand").html(htmlTable);	
	
	
}
var comboArray = [];
var commandId;
var cmdCode;
var cmdName;
var cmdStatus;
var cmdType;
var cmdtypeId;
function executeClickEvent(cmdId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(cmdId == data.data[j].cmdId){
			commandId = data.data[j].cmdId;
			cmdCode = data.data[j].cmdCode;
			cmdName = data.data[j].cmdName;
			cmdStatus = data.data[j].status;
			cmdType = data.data[j].cmdType;
			cmdtypeId = data.data[j].cmdTypeId;
			
		}
	}
	rowClick(commandId,cmdCode,cmdName,cmdStatus,cmdType,cmdtypeId);
}

/* function searchCommandList(){
	 if(document.getElementById('searchCommand').value == "" || document.getElementById('searchCommand') == null){
		 alert("Plese Enter the Command Name");
		 return false;
	 }
	 
	var cmdName= jQuery("#searchCommand").attr("checked", true).val().toUpperCase();
		
	var nPageNo=1;
	var url = "getAllCommandDetails";
	var data =  {"PN":nPageNo, "commandName":cmdName};//"PN="+nPageNo+"cmdName="+cmdName;
	var bClickable = true;
	GetJsonData('tblListOfCommand',data,url,bClickable);
	ResetForm();
} */

function addCmdDetails(){
	//return ValidateInputParamenters('commandCode','commandName','selectCmdType');
	
	 if(document.getElementById('commandCode').value=="" || document.getElementById('commandCode').value==null){
		alert("Please Enter the Region Code");
		return false;
	}
	if(document.getElementById('commandName').value=="" || document.getElementById('commandName').value==null){
		alert("Please Enter the Region Name");
		return false;
	}
	if(document.getElementById('selectCmdType').value=="" || document.getElementById('selectCmdType').value==0){
		alert("Please select the Region Type");
		return false;
	} 
	var params = {
			 
			 'commandCode':jQuery('#commandCode').val(),
			 'commandName':jQuery('#commandName').val(),
			 'commandtypeId':jQuery('#selectCmdType').find('option:selected').val()
	 } 	
	//alert("params: "+JSON.stringify(params));
	var url="addCommand";
	
	SendJsonData(url,params);
	
	ResetForm();	
	
}




function updateCmdDetails()
{	
	$j('#messageId').fadeIn();
	
	 if(document.getElementById('commandCode').value == "" ){
		alert("please enter the Region Code");
		return false;
	}
	if(document.getElementById('commandName').value == "" ){
		alert("please enter the Region Name");
		return false;
	}
	if(document.getElementById('selectCmdType').value == "" || document.getElementById('selectCmdType').value==0){
		alert("please select the Region Type");
		return false;
	}
		
	var params = {
			 'commandId':commandId,
			 'commandCode':jQuery('#commandCode').val(),
			 'commandName':jQuery('#commandName').val(),
			 /* 'cmdType':jQuery('#cmdType').val() */
			 'commandtypeId':jQuery('#selectCmdType').find('option:selected').val()
	 } 
	var url="updateCommandDetails";
	SendJsonData(url,params);	
 		 		
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddCommand').attr("disabled", false);
	ResetForm();
	
}

function updateStatus(){
	$j('#messageId').fadeIn();
	if(document.getElementById('commandCode').value == "" || document.getElementById('commandCode') == null ){
		alert("Please Select the Command");
		return false;
	}
		
	 var params = {
		 'commandId':commandId,
		 'commandCode':cmdCode,
		 'status':cmdStatus
		 
	}  
	 
	 var url="updateCommandStatus";
	 SendJsonData(url,params);	 
	 
	 $j("#btnActive").attr("disabled", true);
	 $j("#btnInActive").attr("disabled", true);
	 $j('#btnAddCommand').attr("disabled", false);
}



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
	    	var combo = "<option value='0'>Select</option>" ;
	    	
	    	for(var i=0;i<result.data.length;i++){
	    		comboArray[i] = result.data[i].commandtypeName;
	    		combo += '<option value='+result.data[i].commandtypeId+'>' +result.data[i].commandtypeName+ '</option>';
	    		//alert("combo :: "+comboArray[i]);
	    		
	    		
	    	}
	    	jQuery('#selectCmdType').append(combo);
	    }
	    
	});
}

function rowClick(cmdId,cmdCode, cmdName, cmdStatus, cmdType, cmdtypeId){	
		
	document.getElementById("commandCode").value = cmdCode;
	document.getElementById("commandName").value = cmdName;
	
	for(var j=0; j<comboArray.length;j++){		
		
		if(comboArray[j] == cmdType){
			
			
			jQuery("#selectCmdType").val(cmdtypeId);
		}
		else
			{
			
				$j("#selectCmdType option[value=0]").attr("selected","selected");
			}
		
		//$j("#selectCmdType option [value="cmdtypeId")
		
	}
	
	if(cmdStatus == 'Y' || cmdStatus == 'y'){
		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').attr("disabled", false);
		$j('#updateBtn').show();
		$j('#btnAddCommand').hide();
	}
	if(cmdStatus == 'N' || cmdStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j("#updateBtn").hide();
		
	}
	
	 
	$j('#btnAddCommand').hide();
	 $j("#btnActive").attr("disabled", false);
	 $j("#btnInActive").attr("disabled", false);
	 $j('#commandCode').attr('readonly', true);
}

function changeCommandType(value){
	
	var commandTypeId = jQuery('#selectCmdType').find('option:selected').val();
	
	if(value == commandTypeId){
		$j('#updateBtn').attr("disabled", false);
	}
	
}
function showResultPage(pageNo)
{
	
	
	nPageNo = pageNo;	
	GetCommandList('FILTER');
	
}



function Reset(){
	document.getElementById("addCommandForm").reset();
	document.getElementById("searchCommandForm").reset();
	document.getElementById('searchCommand').value = "";
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').hide();
	$j('#btnAddCommand').show();
	document.getElementById("messageId").innerHTML = "";
	$("#messageId").css("color", "black");
	$j('#commandCode').attr('readonly', false);
	showAll();
}



function ResetForm()
{	
	$j('#commandCode').val('');
	$j('#commandName').val('');
	$j('#selectCmdType').val('');
	$j('#searchCommand').val('');
	
	
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetCommandList('ALL');
	
}

function search()
{
	if(document.getElementById('searchCommand').value == ""){
		alert("Please Enter Region Name");
		return false;
	}
	nPageNo=1;
	GetCommandList('Filter');
}

function generateReport()
{
	document.searchCommandForm.action="${pageContext.request.contextPath}/report/generateRegionMasterReport";
	document.searchCommandForm.method="POST";
	document.searchCommandForm.submit(); 
	
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
                <div class="internal_Htext">Region Master</div>
                    
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="font-weight: bold;" ></p>
                                       <br>


									<div class="row">
										<div class="col-md-8">
											<form class="form-horizontal" id="searchCommandForm"
												name="searchCommandForm" method="" role="form">
												<div class="form-group row">
													<label class="col-3 col-form-label">Region Name <span
														style="color: red">*</span></label>
													<div class="col-4">

														<input type="text" name="searchCommand" id="searchCommand"
															class="form-control" id="inlineFormInputGroup"
															placeholder="Region Name"
															onkeypress="return validateTextField('searchCommand',30);">

													</div>
													<div class="form-group row">

														<div class="col-md-12">
															<button type="button" class="btn  btn-primary "
																onclick="search();">Search</button>
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
                                                <th id="th2" class ="inner_md_htext">Region Code</th>
                                                <th id="th3" class ="inner_md_htext">Region Name</th>
                                                <th id="th4" class ="inner_md_htext"> Region Type </th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                        <!--  <tbody id="tblListOfEmployeeAndDepenent">   </tbody>  --->
                                     <tbody id="tblListOfCommand">
										 
                     				 </tbody>
                                    </table>
                                    
                                   
                                    
                                    

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addCommandForm" name="addCommandForm" method="">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-md-5">
                                                            <label for="Region Code" class=" col-form-label inner_md_htext" >Region Code <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-md-7">
                                                                <input type="text" class="form-control" id="commandCode" name="commandCode" placeholder="Region Code" onkeypress="return validateText('commandCode',7);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-md-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Region Name <span style="color:red">*</span></label>
                                                            </div>
                                                            <div class="col-md-7">
                                                                <input type="text" class="form-control" id="commandName" name="commandName" placeholder="Region Name" onkeypress="return validateTextField('commandName',30);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-md-5 col-form-label inner_md_htext">Region Type<span style="color:red">*</span></label>
                                                            
                                                            <div class="col-md-7">
                                                                <select class="form-control" id="selectCmdType" name="selectCmdType">
                                                                
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
												 
														<button type="button" id="btnAddCommand"
															class="btn  btn-primary " onclick="addCmdDetails();">Add</button>
														<button type="button" id="updateBtn"
															class="btn  btn-primary " onclick="updateCmdDetails();">Update</button>
														<button id="btnActive" type="button"
															class="btn  btn-primary " onclick="updateStatus();">Activate</button>
														<button id="btnInActive" type="button"
															class="btn btn-primary  " onclick="updateStatus();">Deactivate</button>
														<button type="button" class="btn btn-danger "
															onclick="Reset();">Reset</button>
													 
											</div>
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