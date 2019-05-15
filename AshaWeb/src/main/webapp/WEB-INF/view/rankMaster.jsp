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
			$j('#rankCode').attr('readonly', false);
			GetRankList('ALL');
			GetEmployeeCategoryList();
		});
		
function GetRankList(MODE)
{
	var rankName= jQuery("#searchRank").attr("checked", true).val().toUpperCase();
		
	var rankId=0;
	 if(MODE == 'ALL'){
			var data = {"PN":nPageNo, "rankName":""};			
		}
	else
		{
		var data = {"PN":nPageNo,"rankName":rankName};
		} 
	var url = "getAllRankDetails";
		
	var bClickable = true;
	GetJsonData('tblListOfRank',data,url,bClickable);
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
		 
		
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].rankId+"' >";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].rankCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].rankName+"</td>";			
			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].employeeCategoryName+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>";
			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		   //alert('No Record Found');
		}			
	
	$j("#tblListOfRank").html(htmlTable);	
	
	
}
var comboArray = [];
var rnkId;
var rnkCode;
var rnkName;
var rnkStatus;
var empCatId;
var empCatName;
function executeClickEvent(rankId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(rankId == data.data[j].rankId){
			rnkId = data.data[j].rankId;
			rnkCode = data.data[j].rankCode;
			rnkName = data.data[j].rankName;
			rnkStatus = data.data[j].status;
			empCatId = data.data[j].employeeCategoryId;
			empCatName = data.data[j].employeeCategoryName;
			
		}
	}
	rowClick(rnkId,rnkCode,rnkName,rnkStatus,empCatId,empCatName);
}

/* function searchRankList(){
	 if(document.getElementById('searchRank').value == "" || document.getElementById('searchRank') == null){
		 alert("Plese Enter the Rank Name");
		 return false;
	 }
	 
	var rankName= jQuery("#searchRank").attr("checked", true).val().toUpperCase();
	var nPageNo=1;
	var url = "getAllRankDetails";
	var data =  {"PN":nPageNo, "rankName":rankName};
	var bClickable = true;
	GetJsonData('tblListOfRank',data,url,bClickable);
	ResetFrom();
} */
var success;
var error;

function addRankDetails(){
	if(document.getElementById('rankCode').value==""){
		alert("Please Enter the Rank Code");
		return false;
	}
	if(document.getElementById('rankName').value==""){
		alert("Please Enter the Rank Name");
		return false;
	}
	if(document.getElementById('selectEmpCat').value==""){
		alert("Please Select Employee Category");
		return false;
	}
	var params = {
			 
			 'rankCode':jQuery('#rankCode').val(),
			 'rankName':jQuery('#rankName').val(),
			 'employeeCategoryId':jQuery('#selectEmpCat').find('option:selected').val()
	 } 	
	
	var url="addRank";
	    SendJsonData(url,params);
}

function updateRankDetails()
{	
	if(document.getElementById('rankCode').value == "" || document.getElementById('rankCode').value == null ){
		alert("please enter the Rank Code");
		return false;
	}
	if(document.getElementById('rankName').value == "" || document.getElementById('rankName').value == null ){
		alert("please enter the Rank Name");
		return false;
	}
	
	if(document.getElementById('selectEmpCat').value == "" || document.getElementById('selectEmpCat').value == null ){
		alert("please select Employee Category");
		return false;
	}
		
	var params = {
			 'rankId':rnkId,
			 'rankCode':jQuery('#rankCode').val(),
			 'rankName':jQuery('#rankName').val(),
			 'employeeCategoryId':jQuery('#selectEmpCat').find('option:selected').val()
	 } 
	var url="updateRankDetails";
	SendJsonData(url,params);	
	GetRankList();	 		
	$j('#updateBtn').attr("disabled", true);
	$j('#btnAddRank').attr("disabled", false);
	$j('#rankCode').attr('readonly', true);
	ResetFrom();
	
}

function updateStatus(){
	if(document.getElementById('rankCode').value == "" || document.getElementById('rankCode').value == null ){
		alert("Please Select the Rank");
		return false;
	}
	 var params = {
		 'rankId':rnkId,
		 'rankCode':rnkCode,
		 'status':rnkStatus
		 
	}  
	 var url= "updateRankStatus";
		    SendJsonData(url,params);
		    
		    $j("#btnActive").attr("disabled", true);
			 $j("#btnInActive").attr("disabled", true);
			 $j('#btnAddRank').attr("disabled", false);
}



function GetEmployeeCategoryList(){
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getEmployeeCategoryList",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	    	//alert("success "+result.data.length);
	    	var combo = "<option value=\"\">Select</option>" ;
	    	
	    	for(var i=0;i<result.data.length;i++){
	    		comboArray[i] = result.data[i].employeeCategoryName;
	    		combo += '<option value='+result.data[i].employeeCategoryId+'>' +result.data[i].employeeCategoryName+ '</option>';
	    		//alert("combo :: "+comboArray[i]);
	    		
	    		
	    	}
	    	jQuery('#selectEmpCat').append(combo);
	    }
	    
	});
}

function rowClick(rnkId,rnkCode,rnkName,rnkStatus,empCatId,empCatName){	
	document.getElementById("rankCode").value = rnkCode;
	document.getElementById("rankName").value = rnkName;
	for(var j=0; j<comboArray.length;j++){		
		
		if(comboArray[j] == empCatName){
			
			jQuery("#selectEmpCat").val(empCatId);
		}
	}
	
if(rnkStatus == 'Y' || rnkStatus == 'y'){
		
	$j("#btnInActive").show();
	$j("#btnActive").hide();
    $j("#updateBtn").show();
    $j("#btnAddRank").hide();
	}
	if(rnkStatus == 'N' || rnkStatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j("#updateBtn").hide();
	}
	
	/* $j('#updateBtn').attr("disabled", false); */
	$j('#btnAddRank').hide();
	
	 $j("#btnActive").attr("disabled", false);
	 $j("#btnInActive").attr("disabled", false);
	 $j('#rankCode').attr('readonly', true);
	
}

function changeEmployeeCategory(value){
	
	var employeeCategoryId = jQuery('#selectEmpCat').find('option:selected').val();
	
	if(value == employeeCategoryId){
		$j('#updateBtn').attr("disabled", false);
	}
	
}
function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetRankList('FILTER');
	
}

function Reset(){
	document.getElementById("addRankForm").reset();
	document.getElementById("searchRankForm").reset();
	document.getElementById('searchRank').value = "";
	
	$j("#btnActive").hide();
	$j("#btnInActive").hide();
	$j('#updateBtn').hide();
	$j('#btnAddRank').show();
	document.getElementById("messageId").innerHTML = "";
	$("#messageId").css("color", "black");
	$j('#rankCode').attr('readonly', false);
	showAll();
}


function ResetForm()
{	
	$j('#rankCode').val('');
	$j('#rankName').val('');
	$j('#selectEmpCat').val('');
	$j('#searchRank').val('');
	
}

function showAll()
{
	ResetForm();
	nPageNo = 1;	
	GetRankList('ALL');
	
}

function enableAddButton(){
	if(document.getElementById("rankCode").value!=null || !document.getElementById("rankCode").value==""){
		$j('#btnAddRank').attr("disabled", false);
	}else if( document.getElementById("rankName").value!=null || !document.getElementById("rankName").value==""){
		$j('#btnAddRank').attr("disabled", false);
	}else{
		$j('#btnAddRank').attr("disabled", true);
	}
}

function search()
{
	if(document.getElementById('searchRank').value == ""){
		alert("Please Enter Rank Name");
		return false;
	}
	nPageNo=1;
	GetRankList('Filter');
}

function generateReport()
{
	document.searchRankForm.action="${pageContext.request.contextPath}/report/generateRankMasterReport";
	document.searchRankForm.method="POST";
	document.searchRankForm.submit(); 
	
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
                <div class="internal_Htext">Rank Master</div>
                    
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="color:green; font-weight: bold;" ></p>
                                       <br>
                                       <div class="row">
                                            <div class="col-md-8">
											<form class="form-horizontal" id="searchRankForm"
												name="searchRankForm" method="" role="form">
												<div class="form-group row">
													<label class="col-3 col-form-label">Rank Name <span
														style="color: red">*</span></label>
													<div class="col-4">

														<input type="text" name="searchRank" id="searchRank"
															class="form-control" id="inlineFormInputGroup"
															placeholder="Rank Name" onkeypress="return validateTextField('searchRelation',30,'Rank Name');">

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
                                            <form class="form-horizontal" id="searchRankForm" name="searchRankForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">Rank Name <span style="color:red">*</span> </label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">Rank Name</label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchRank" id="searchRank" class="form-control" id="inlineFormInputGroup" placeholder="Rank Name">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn  btn-primary" onclick="GetRankList('FILTER');">Search</button>
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
                   <!-- <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >
                   <tr>
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
                                                <th id="th2" class ="inner_md_htext">Rank Code</th>
                                                <th id="th3" class ="inner_md_htext">Rank Name</th>
                                                <th id="th4" class ="inner_md_htext"> Employee Category </th>
                                                <th id="th5" class ="inner_md_htext">Status</th>
                                            </tr>
                                        </thead>
                                        <!--  <tbody id="tblListOfEmployeeAndDepenent">   </tbody>  --->
                                     <tbody id="tblListOfRank">
										 
                     				 </tbody>
                                    </table>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="addRankForm" name="addRankForm" method="">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="Rank Code" class=" col-form-label inner_md_htext" >Rank Code <span style="color:red">*</span> </label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" id="rankCode" name="rankCode" placeholder="Rank Code" onkeypress=" return validateText('rankCode',7,'Rank Code');">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">Rank Name <span style="color:red">*</span> </label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" id="rankName" name="rankName" placeholder="Rank Name" onkeypress="return validateTextField('rankName',30,'Rank Name')">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        
                                                            <label for="recordoffice" class="col-sm-5 col-form-label inner_md_htext">Employee Category <span style="color:red">*</span> </label>
                                                            
                                                            <div class="col-md-7">
                                                                <select class="form-control" id="selectEmpCat" onchange="changeEmployeeCategory(this.value);">
                                                                    
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
												
														<button type="button" id="btnAddRank"
															class="btn  btn-primary " onclick="addRankDetails();">Add</button>
														<button type="button" id="updateBtn"
															class="btn  btn-primary " onclick="updateRankDetails();">Update</button>
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

                                                    <button type="button" id="btnAddRank" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addRankDetails();">Add</button>
                                                    <button type="button" id ="updateBtn" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateRankDetails();">Update</button>
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