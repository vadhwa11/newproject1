<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="..//view/leftMenu.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Indian Coast Guard</title>
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<%@include file="..//view/commonJavaScript.jsp"%>
<script type="text/javascript">
	nPageNo = 1;
	var $j = jQuery.noConflict();

	$j(document).ready(function() {
		$j("#btnActive").hide();
		$j("#btnInActive").hide();
		$j('#updateBtn').attr("disabled", true);

		GetAllTemplate('ALL');
	});

	function GetAllTemplate(MODE) {

		var id = 0;
		if (MODE == 'ALL') {
			var data = {
				"PN" : nPageNo
			};
		} else {
			var data = {
				"PN" : nPageNo
			};
		}
		var url = "getAllTemplate";
		var bClickable = true;
		GetJsonData('tblListOfTemplate', data, url, bClickable);
	}

	function makeTable(jsonData) {
		var htmlTable = "";
		var data = jsonData.count;

		var pageSize = 5;
		var dataList = jsonData.data;

		for (i = 0; i < dataList.length; i++) {

			if (dataList[i].status == 'Y' || dataList[i].status == 'y') {
				var Status = 'Active'
			} else {
				var Status = 'InActive'
			}

			htmlTable = htmlTable + "<tr id='"+dataList[i].templateId+"' >";
			htmlTable = htmlTable + "<td style='width: 150px;'>"
					+ dataList[i].templateCode + "</td>";
			htmlTable = htmlTable + "<td style='width: 150px;'>"
					+ dataList[i].templateName + "</td>";
			htmlTable = htmlTable + "<td style='width: 100px;'>" + Status
					+ "</td>";
			htmlTable = htmlTable + "</tr>";

		}
		if (dataList.length == 0) {
			htmlTable = htmlTable
					+ "<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";

		}

		$j("#tblListOfTemplate").html(htmlTable);

	}

	var appId;
	var appName;
	var appUrl;
	var appstatus;

	function executeClickEvent(id, data) {

		for (j = 0; j < data.data.length; j++) {
			if (id == data.data[j].id) {
				appId = data.data[j].id;
				appName = data.data[j].appName;
				appUrl = data.data[j].url;
				appstatus = data.data[j].status;

			}
		}
		rowClick(appId, appName, appUrl, appstatus);
	}

	function searchApplicationList() {
		if (document.getElementById('searchTemplate').value == ""
				|| document.getElementById('searchTemplate') == null) {
			alert("Plese Enter the Application Name");
			return false;
		}

		var templateName = jQuery("#searchTemplate")
				.attr("checked", true).val().toUpperCase();

		var nPageNo = 1;
		var url = "getAllTemplate";
		var data = {
			"PN" : nPageNo,
			"templateName" : templateName
		};
		var bClickable = true;
		GetJsonData('tblListOfTemplate', data, url, bClickable);
		ResetForm();
	}
	
	function updateUserApplication(status){
				
		var params = {
				 'id':appId,
				 'appName':jQuery('#applicationName').val(),
				 'url':jQuery('#applicationUrl').val(),
				 'status':status
				 
			} 
		alert("params: "+JSON.stringify(params)); 
		 jQuery.ajax({
			 crossOrigin: true,
			    method: "POST",
			    header:{
			    	'Access-Control-Allow-Origin': '*'
			    	},
			    	crossDomain:true,
			    url: "updateUserApplication",
			    data: JSON.stringify(params),
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(result){
			        //alert("result" +result);
			        console.log(result);
			        if(result.status==1){
			        	
			        	showAll('ALL')
			        	document.getElementById("messageId").innerHTML = result.msg;	        	
			        }
			        if(result.status==0){
			        	
			        	showAll('ALL')
			        	document.getElementById("messageId").innerHTML = result.msg;
			        }
			    }
			    
			    
			});
		$j("#btnActive").attr("disabled", true);
		$j("#btnInActive").attr("disabled", true);
		$j('#updateBtn').attr("disabled", true);
		ResetForm();
	}

	function ResetForm() {
		$j('#appName').val('');
		$j('#url').val('');
		$j('#searchTemplate').val('');

	}
	function showAll() {
		ResetForm();
		nPageNo = 1;
		GetAllTemplate('ALL');

	}

	function showResultPage(pageNo) {
		nPageNo = pageNo;
		GetAllTemplate('FILTER');

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
				<a href="index.html" class="logo"> <span> <img
						src="${pageContext.request.contextPath}/resources/images/logo.png"
						alt="" height="18">
				</span> <i> <img
						src="${pageContext.request.contextPath}/resources/images/logo_sm.png"
						alt="" height="22">
				</i>
				</a>
			</div>

			<nav class="navbar-custom">
				<ul class="list-inline float-right mb-0">

					<li class="list-inline-item dropdown notification-list"><a
						class="nav-link dropdown-toggle nav-user" data-toggle="dropdown"
						href="#" role="button" aria-haspopup="false" aria-expanded="false">
							<i class="noti-icon"><img
								src="${pageContext.request.contextPath}/resources/images/users/avatar-1.jpg"
								alt="user" class="img-fluid rounded-circle"></i> <span
							class="profile-username ml-2 text-dark">Username </span> <span
							class="mdi mdi-menu-down text-dark"></span>
					</a>
						<div
							class="dropdown-menu dropdown-menu-animated dropdown-menu-right profile-dropdown ">
							<!-- item-->
							<div class="dropdown-item noti-title">
								<h5 class="text-overflow">
									<small>Manage Template</small>
								</h5>
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

						</div></li>

				</ul>

				<ul class="list-inline menu-left mb-0">
					<li class="float-left">
						<button
							class="button-menu-mobile open-left waves-light waves-effect">
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
								<h4 class="page-title float-left">Manage Template</h4>

								<ol class="breadcrumb float-right">
									<li class="breadcrumb-item active"><a href="#">Home</a></li>
									<li class="breadcrumb-item  active"><a href="#">Master</a></li>
									<li class="breadcrumb-item active">Manage Template</li>
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
									<p align="center" id="messageId"
										style="color: green; font-weight: bold;"></p>
									<br>
									<div class="row">

										<div class="col-md-8">
											<form class="form-horizontal" id="searchTemplateForm"
												name="searchTemplateForm" method="" role="form">
												<div class="form-group row">
													<label class="col-3 col-form-label inner_md_htext">Template
														Name<span style="color: red">*</span>
													</label>
													<div class="col-5">
														<div class="col-auto">

															<div class="input-group mb-2">

																<input type="text" name="searchTemplate"
																	id="searchTemplate" class="form-control"
																	id="inlineFormInputGroup"
																	placeholder="Template Name">
															</div>
														</div>
													</div>
													<div class="col-2">
														<button id="searchBtn" type="button"
															class="btn  btn-primary"
															onclick="searchTemplateList();">Search</button>
													</div>
												</div>
											</form>

										</div>




										<div class="col-md-4">
											<form>
												<div class="button-list">
													<button type="button"
														class="btn  btn-primary btn-rounded w-md waves-effect waves-light"
														onclick="showAll('ALL');">Show All</button>

												</div>
											</form>
										</div>

									</div>

									 <div class="row">
										<div class="col-sm-12">
											<nav aria-label="Page navigation example">
												<!-- <ul class="pagination float-right">
													<li class="page-item"><a href="#"
														aria-label="Previous" class="page-link"> <i
															class="fa fa-angle-left"></i>
													</a></li>

													<li class="page-item"><a href="#" aria-label="Next"
														class="page-link"> <i class="fa fa-angle-right"></i>
													</a></li>
												</ul> -->
											</nav>
										</div>
									</div> 

									<!-- <table class="table table-striped table-hover jambo_table"> -->
									<table class="tblSearchActions" cellspacing="0" cellpadding="0"
										border="0">
										<tr>
											<td class="SearchStatus" style="font-size: 15px;"
												align="left">Search Results</td>
											<td>
												<div id=resultnavigation></div>

											</td>
										</tr>
									</table>

									<table class="table table-hover table-bordered">
										<thead class="bg-success" style="color: #fff;">
											<tr>
												<th id="th2" class="inner_md_htext">Template Code</th>
												<th id="th3" class="inner_md_htext">Template Name</th>
												<th id="th4" class="inner_md_htext">Status</th>
											</tr>
										</thead>
										<tbody id="tblListOfTemplate">

										</tbody>
									</table>

									<div class="row">
										<div class="col-md-12">
											<form id="addTemplateForm" name="addTemplateForm"
												action="" method="POST">
												<div class="row">
													<div class="col-md-4">
														<div class="form-group row">
															<div class="col-sm-5">
																<label for=""
																	class=" col-form-label inner_md_htext">Template 
																	Code</label>
															</div>
															<div class="col-sm-7">
																<input type="text" name="templateCode"
																	id="templateCode" class="form-control"
																	placeholder="Template Code"
																	onkeypress="return validateText('templateCode', 7);">
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group row">
															<div class="col-sm-3">
																<label for="service"
																	class="col-form-label inner_md_htext">Template Name</label>
															</div>
															<div class="col-sm-9">
																<input type="text" name="templateName"
																	id="templateName" class="form-control"
																	placeholder="Template Name"
																	onkeypress="return validateTextField('templateName', 30)">
															</div>
														</div>
													</div>

												</div>
											</form>
										</div>

									</div>
									<br>
									<div class="row">
										<div class="col-md-7"></div>
										<div class="col-md-5">
											<form>
												<div class="button-list">

													<button id="btnAddTemplate" type="button"
														class="btn btn-primary btn-rounded w-md waves-effect waves-light"
														onclick="addTemplate();">Add</button>
													<button id="updateBtn" type="button"
														class="btn btn-primary btn-rounded w-md waves-effect waves-light"
														onclick="updateTemplate('update');">Update</button>
													<button id="btnActive" type="button"
														class="btn btn-primary btn-rounded w-md waves-effect waves-light"
														onclick="updateTemplate('active');">Active</button>
													<button id="btnInActive" type="button"
														class="btn btn-primary btn-rounded w-md waves-effect waves-light"
														onclick="updateTemplate('inactive');">De-Active</button>
													<button type="button"
														class="btn btn-danger btn-rounded w-md waves-effect waves-light"
														onclick="Reset();">Reset</button>

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