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
var $j = jQuery.noConflict();

$j(document).ready(function()
		{	
			$j("#btnActive").hide();
			$j("#btnInActive").hide();
			$j('#updateBtn').hide();
			GetTradeList('ALL');
			GetServiceTypeList();
		});
		
function GetTradeList(MODE)
{
	//document.getElementById('searchTrade').value = "";
	var tradeName= jQuery("#searchTrade").attr("checked", true).val().toUpperCase();
		
	var tradeId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo, "tradeName":""};			
		}
	else
		{
		var data = {"PN":nPageNo, "tradeName":tradeName};
		} 
	var url = "getAllTradeDetails";
		
	var bClickable = true;
	GetJsonData('tblListOfTrade',data,url,bClickable);
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
		
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].tradeId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].tradeName+"</td>";			
			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].serviceTypeName+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>";
			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
	{
	htmlTable = htmlTable+"<tr ><td colspan='3'><h6>No Record Found</h6></td></tr>";
	   //alert('No Record Found');
	}	
	
	$j("#tblListOfTrade").html(htmlTable);	
	
	
}
var comboArray = [];
var trdeId;
var tradeName;
var tradeStatus;
var serviceTypeId;
var serviceTypeName;
function executeClickEvent(tradeId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(tradeId == data.data[j].tradeId){
			trdeId = data.data[j].tradeId;
			tradeName = data.data[j].tradeName;
			tradeStatus = data.data[j].status;
			serviceTypeName = data.data[j].serviceTypeName;
			serviceTypeId = data.data[j].serviceTypeId;
			
		}
	}
	rowClick(trdeId,tradeName,tradeStatus,serviceTypeName,serviceTypeId);
}
var success;
var error;

/* function searchTradeList(){
	 if(document.getElementById('searchTrade').value == "" || document.getElementById('searchTrade') == null){
		 alert("Plese Enter the Trade Name");
		 return false;
	 }
	 
	var tradeName= jQuery("#searchTrade").attr("checked", true).val().toUpperCase();
		
	var nPageNo=1;
	var url = "getAllTradeDetails";
	var data =  {"PN":nPageNo, "tradeName":tradeName};//"PN="+nPageNo+"cmdName="+cmdName;
	var bClickable = true;
	GetJsonData('tblListOfTrade',data,url,bClickable);
	ResetForm();
} */

function addTradeDetails(){
	
	if(document.getElementById('tradeName').value=="" || document.getElementById('tradeName').value==null){
		alert("Please Enter the Trade Name");
		return false;
	}
	if(document.getElementById('selectServiceType').value=="" || document.getElementById('selectServiceType').value==null){
		alert("Please Enter the Service Type");
		return false;
	}
	var params = {
			 'tradeName':jQuery('#tradeName').val(),
			 'serviceTypeId':jQuery('#selectServiceType').find('option:selected').val()
	 } 	
	var url="addTrade";
    SendJsonData(url,params);
	
}




function updateTradeDetails()
{	
	if(document.getElementById('tradeName').value == "" || document.getElementById('tradeName').value == null ){
		alert("please enter the tradeName");
		return false;
	}
	var params = {
			 'tradeId':trdeId,
			 'tradeName':jQuery('#tradeName').val(),
			 'serviceTypeId':jQuery('#selectServiceType').find('option:selected').val()
	 } 
	var url="updateTradeDetails";
	SendJsonData(url,params);	
 		 		
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddTrade').attr("disabled", false);
	ResetForm();
}

function updateStatus(){
	if(document.getElementById('tradeName').value == "" || document.getElementById('tradeName').value == null ){
		alert("Please Select the Trade");
		return false;
	}
	
	 var params = {
		 'tradeId':trdeId,
		 'tradeName':tradeName,
		 'status':tradeStatus
	}  
	
	 var url="updateTradeStatus";
	 SendJsonData(url,params);	 
	 
	 $j("#btnActive").attr("disabled", true);
	 $j("#btnInActive").attr("disabled", true);
	 $j('#btnAddTrade').attr("disabled", false);
}

function GetServiceTypeList(){
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getServiceTypeList",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	    	//alert("success "+result.data.length);
	    	var combo = "<option value=\"\">Select</option>" ;
	    	
	    	for(var i=0;i<result.data.length;i++){
	    		comboArray[i] = result.data[i].serviceTypeName;
	    		combo += '<option value='+result.data[i].serviceTypeId+'>' +result.data[i].serviceTypeName+ '</option>';
	    		//alert("combo :: "+comboArray[i]);
	    		
	    		
	    	}
	    	jQuery('#selectServiceType').append(combo);
	    }
	    
	});
}

function rowClick(trdeId,tradeName,tradeStatus,serviceTypeName,serviceTypeId){	
	
	document.getElementById("tradeName").value = tradeName;
	
	
	for(var j=0; j<comboArray.length;j++){		
		
		if(comboArray[j] == serviceTypeName){
			jQuery("#selectServiceType").val(serviceTypeId);
		}
	}
	
	if(tradeStatus == 'Y' || tradeStatus == 'y'){
		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
	    $j("#updateBtn").show();
	    $j("#btnAddTrade").hide();
	}
	if(tradeStatus == 'N' || tradeStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j("#updateBtn").hide();
	}
	
	/* $j('#updateBtn').attr("disabled", false); */
	$j('#btnAddTrade').hide();
	
	 $j("#btnActive").attr("disabled", false);
	 $j("#btnInActive").attr("disabled", false);
}

function changeServiceType(value){
	
	var serviceTypeId = jQuery('#selectServiceType').find('option:selected').val();
	
	if(value == serviceTypeId){
		$j('#updateBtn').attr("disabled", false);
	}
	
}

function Reset(){
	document.getElementById("addTradeForm").reset();
	document.getElementById("searchTradeForm").reset();
	document.getElementById('searchTrade').value = "";
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').hide();
	$j('#btnAddTrade').show();
	document.getElementById("messageId").innerHTML = "";
	$("#messageId").css("color", "black");
	showAll();
}



function ResetForm()
{	
	$j('#tradeCode').val('');
	$j('#tradeName').val('');
	$j('#selectServiceType').val('');
	$j('#searchTrade').val('');
	
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetTradeList('ALL');
	
}

function showResultPage(pageNo)
{
	
	
	nPageNo = pageNo;	
	GetTradeList('FILTER');
	
}

function search()
{
	if(document.getElementById('searchTrade').value == ""){
		alert("Please Enter Trade Name");
		return false;
	}
	nPageNo=1;
	GetTradeList('Filter');
}

function generateReport()
{
	document.searchTradeForm.action="${pageContext.request.contextPath}/report/generateTradeMasterReport";
	document.searchTradeForm.method="POST";
	document.searchTradeForm.submit(); 
	
}

</script>
</head>

<body>

    <!-- Begin page -->
    <div id="wrapper">

        
        <div class="content-page">
            <!-- Start content -->
            <div class="">
                <div class="container-fluid">
                <div class="internal_Htext">Trade Master</div>
                    
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                       <br>
                                       <div class="row">
                                            <div class="col-md-8">
											<form class="form-horizontal" id="searchTradeForm"
												name="searchTradeForm" method="" role="form">
												<div class="form-group row">
													<label class="col-3 col-form-label">Trade Name <span
														style="color: red">*</span></label>
													<div class="col-4">

														<input type="text" name="searchTrade" id="searchTrade"
															class="form-control" id="inlineFormInputGroup"
															placeholder="Trade Name" onkeypress="return validateTextField('tradeName',30,'Trade Name')">

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
                                            <form class="form-horizontal" id="searchTradeForm" name="searchTradeForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Trade Name <span style="color:red">*</span> </label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Trade Name</label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchTrade" id="searchTrade" class="form-control" id="inlineFormInputGroup" placeholder="Trade Name">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn  btn-primary" onclick="GetTradeList('FILTER');">Search</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                        
                                        
                                        
                                        
                                        <div class="col-md-4">
                                            <form>
                                                <div class="button-list">
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" onclick="GetTradeList('ALL');">Show All</button>
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light">Reports</button>

                                                </div>
                                            </form>
                                        </div>

                                    </div> --%>


					<!-- <table class="table table-striped table-hover jambo_table"> -->
                  <!--  <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >			<tr>
				<td class="SearchStatus" style="font-size: 15px;" align="left">Search Results</td>
				<td>
				<div id=resultnavigation></div>
				
				</td>
				</tr>
		</table> -->
		
			<div style="float:left">
               
                                     <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >   <tr>
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
                                                <th id="th3" class ="inner_md_htext">Trade Name</th>
                                                <th id="th4" class ="inner_md_htext"> Service Type </th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                        <!--  <tbody id="tblListOfEmployeeAndDepenent">   </tbody>  --->
                                     <tbody id="tblListOfTrade">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addTradeForm" name="addTradeForm" method="">
                                                <div class="row">
                                                    <!-- <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Rank Code" class=" col-form-label inner_md_htext" >Rank Code</label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" id="rankCode" name="rankCode" placeholder="Rank Code">
                                                            </div>
                                                        </div>
                                                    </div> -->
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Trade Name <span style="color:red">*</span> </label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" id="tradeName" name="tradeName" placeholder="Trade Name" onkeypress="return validateTextField('tradeName',30,'Trade Name')">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-4 col-form-label inner_md_htext">Service Type <span style="color:red">*</span> </label>
                                                            
                                                            <div class="col-md-6">
                                                                <select class="form-control" id="selectServiceType" onchange="changeServiceType(this.value);">
                                                                    
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
												
														<button type="button" id="btnAddTrade"
															class="btn  btn-primary " onclick="addTradeDetails();">Add</button>
														<button type="button" id="updateBtn"
															class="btn  btn-primary " onclick="updateTradeDetails();">Update</button>
														<button id="btnActive" type="button"
															class="btn  btn-primary " onclick="updateStatus();">Activate</button>
														<button id="btnInActive" type="button"
															class="btn btn-primary  " onclick="updateStatus();">Deactivate</button>
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

                                                    <button type="button" id="btnAddTrade" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addTradeDetails();">Add</button>
                                                    <button type="button" id ="updateBtn" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateTradeDetails();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateStatus();">Deactivate</button>
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