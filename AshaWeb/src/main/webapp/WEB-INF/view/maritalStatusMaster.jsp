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
	$j('#maritalStatusCode').attr('readonly', false);		
		GetAllMaritalStatus('ALL');			
		});
		
function GetAllMaritalStatus(MODE){
	
	var maritalStatusName= jQuery("#searchMaritalStatus").attr("checked", true).val().toUpperCase();
	 var maritalStatusId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo, "maritalStatusName":""};			
		}else{
		var data = {"PN":nPageNo, "maritalStatusName":maritalStatusName};
		} 
	var url = "getAllMaritalStatus";		
	var bClickable = true;
	GetJsonData('tblListOfMaritalStatus',data,url,bClickable);	 
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
					var Status='Inactive'
				} 		
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].maritalStatusId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].maritalStatusCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].maritalStatusName+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>"; 			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   
		}			
	
	
	$j("#tblListOfMaritalStatus").html(htmlTable); 	
	
}

var mStaId;
var mStaCode;
var mStaName;
var mStaStatus;

function executeClickEvent(maritalStatusId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(maritalStatusId == data.data[j].maritalStatusId){
			mStaId = data.data[j].maritalStatusId;			
			mStaCode = data.data[j].maritalStatusCode;			
			mStaName = data.data[j].maritalStatusName;
			mStaStatus = data.data[j].status;
			
			
		}
	}
	rowClick(mStaId,mStaCode,mStaName,mStaStatus);
}

function rowClick(mStaId,mStaCode,mStaName,mStaStatus){	
		
	document.getElementById("maritalStatusCode").value = mStaCode;
	document.getElementById("maritalStatusName").value = mStaName;
	
			 
	if(mStaStatus == 'Y' || mStaStatus == 'y'){		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').show();
	}
	if(mStaStatus == 'N' || mStaStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j('#updateBtn').hide();
	}
	
	$j("#btnActive").attr("disabled", false);
	$j("#btnInActive").attr("disabled", false);
	$j('#updateBtn').attr("disabled", false);
	$j('#btnAddMaritalStatus').hide();
	$j('#maritalStatusCode').attr('readonly', true);
}

function searchMaritalStatusList(){	
	if(document.getElementById('searchMaritalStatus').value == "" ){
		 alert("Plese Enter the Marital Status Name");
		 return false;
	 }
		 	 
	 var maritalStatusName= jQuery("#searchMaritalStatus").attr("checked", true).val().toUpperCase();
		
		var nPageNo=1;
		var url = "getAllMaritalStatus";
		var data =  {"PN":nPageNo, "maritalStatusName":maritalStatusName};
		var bClickable = true;
		GetJsonData('tblListOfMaritalStatus',data,url,bClickable);		
}

function addMaritalStatusDetails(){
	if(document.getElementById('maritalStatusCode').value == null || document.getElementById('maritalStatusCode').value == ""){
		alert("Please Enter Marital Status Code");
		return false;
	}
	if(document.getElementById('maritalStatusName').value == null || document.getElementById('maritalStatusName').value ==""){
		alert("Please Enter Marital Status Name");
		return false;
	}
	
	
	var params = {			 
			 'maritalStatusCode':jQuery('#maritalStatusCode').val(),
			 'maritalStatusName':jQuery('#maritalStatusName').val()			 			 
	 } 
	
	var url="addMaritalStatus";
    SendJsonData(url,params);
}

var success;
var error;
function updateMaritalStatus(){	
	if(document.getElementById('maritalStatusCode').value == null || document.getElementById('maritalStatusCode').value == ""){
		alert("Please Enter Marital Status Code");
		return false;
	}
	if(document.getElementById('maritalStatusName').value == null || document.getElementById('maritalStatusName').value ==""){
		alert("Please Enter Marital Status Name");
		return false;
	}
		
	
	var params = {
			 'maritalStatusId':mStaId,
			 'maritalStatusName':jQuery('#maritalStatusName').val(),
			 'maritalStatusCode':jQuery('#maritalStatusCode').val()			 
			
	 } 
	
		    var url = "updateMaritalStatusDetails";
		    SendJsonData(url,params);	
		 		
			$j('#updateBtn').attr("disabled", true);
			$j('#btnAddMaritalStatus').attr("disabled", false);
			$j('#maritalStatusCode').attr('readonly', true);
			ResetFrom();
				
				$j('#updateBtn').attr("disabled", true);
		
		$j('#updateBtn').attr("disabled", true);
}

function updateMaritalStatusStatus(){
	if(document.getElementById('maritalStatusCode').value == ""){
		alert("Please Enter Marital Status Code");
		return false;
	}
	if(document.getElementById('maritalStatusName').value ==""){
		alert("Please Enter Marital Status Name");
		return false;
	}
	
	var params = {
			'maritalStatusId':mStaId,
			 'maritalStatusCode':mStaCode,
			 'status':mStaStatus
			 
		} 
		    var url = "updateMaritalStatusStatus";
			SendJsonData(url,params);
    
     $j("#btnActive").attr("disabled", true);
	 $j("#btnInActive").attr("disabled", true);
	 $j('#btnAddMaritalStatus').attr("disabled", false);
}


function Reset(){
	document.getElementById("addMaritalStatusForm").reset();
	document.getElementById("searchMaritalStatusForm").reset();
	document.getElementById('searchMaritalStatus').value = "";
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').hide();
	$j('#btnAddMaritalStatus').show();
	document.getElementById("messageId").innerHTML = "";
	$("#messageId").css("color", "black");
	$j('#maritalStatusCode').attr('readonly', false);
	showAll();
}



function ResetForm()
{	
	$j('#maritalStatusCode').val('');
	$j('#maritalStatusName').val('');
	$j('#searchMaritalStatus').val('');
	
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetAllMaritalStatus('ALL');
	
}

 function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetAllMaritalStatus('FILTER');
	
} 

 function search()
 {
 	if(document.getElementById('searchMaritalStatus').value == ""){
 		alert("Please Enter Marital Status Name");
 		return false;
 	}
 	nPageNo=1;
 	GetAllMaritalStatus('Filter');
 }
 
 function generateReport()
 {
 	document.searchMaritalStatusForm.action="${pageContext.request.contextPath}/report/generateMaritalStatusReport";
 	document.searchMaritalStatusForm.method="POST";
 	document.searchMaritalStatusForm.submit(); 
 	
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
                <div class="internal_Htext">Marital Status Master</div>
                    
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                       <br>
                                       <div class="row">
                                            <div class="col-md-8">
											<form class="form-horizontal" id="searchMaritalStatusForm"
												name="searchMaritalStatusForm" method="" role="form">
												<div class="form-group row">
													<label class="col-3 col-form-label">MaritalStatus Name <span
														style="color: red">*</span></label>
													<div class="col-4">

														<input type="text" name="searchMaritalStatus" id="searchMaritalStatus"
															class="form-control" id="inlineFormInputGroup"
															placeholder="Marital Status Name">

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
                                    <%-- <div class="row">
                                                                     
                                        <div class="col-md-8">
                                            <form class="form-horizontal" id="searchMaritalStatusForm" name="searchMaritalStatusForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">MaritalStatus Name <span style="color:red">*</span> </label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">MaritalStatus Name</label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchMaritalStatus" id="searchMaritalStatus" class="form-control" id="inlineFormInputGroup" placeholder="Marital Status Name">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn  btn-primary" onclick="GetAllMaritalStatus('FILTER');">Search</button>
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

                                    </div> --%>


					<!-- <table class="table table-striped table-hover jambo_table"> -->
           <!--         <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >			<tr>
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
                                                <th id="th2" class ="inner_md_htext">MaritalStatus Code</th>
                                                <th id="th3" class ="inner_md_htext">MaritalStatus Name</th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                        <!--  <tbody id="tblListOfEmployeeAndDepenent">   </tbody>  --->
                                     <tbody id="tblListOfMaritalStatus">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addMaritalStatusForm" name="addMaritalStatusForm" method="">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="MaritalStatus Code" class=" col-form-label inner_md_htext" >Marital Status Code <span style="color:red">*</span> </label>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="maritalStatusCode" name="maritalStatusCode" placeholder="Marital Status Code" onkeypress=" return validateText('maritalStatusCode',7,'Marital Status Code');">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            <label for="service" class="col-form-label inner_md_htext">Marital Status Name <span style="color:red">*</span> </label>
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="maritalStatusName" name="maritalStatusName" placeholder="Marital Status Name" onkeypress="return validateTextField('maritalStatusName',30,'Marital Status Name');" >
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
												
														<button type="button" id="btnAddMaritalStatus"
															class="btn  btn-primary " onclick="addMaritalStatusDetails();">Add</button>
														<button type="button" id="updateBtn"
															class="btn  btn-primary " onclick="updateMaritalStatus();">Update</button>
														<button id="btnActive" type="button"
															class="btn  btn-primary " onclick="updateMaritalStatusStatus();">Activate</button>
														<button id="btnInActive" type="button"
															class="btn btn-primary  " onclick="updateMaritalStatusStatus();">Deactivate</button>
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

                                                    <button type="button" id="btnAddMaritalStatus" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addMaritalStatusDetails();">Add</button>
                                                    <button type="button" id ="updateBtn" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateMaritalStatus();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateMaritalStatusStatus();">Activate</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateMaritalStatusStatus();">Deactivate</button>
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