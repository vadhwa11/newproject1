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
								<h4 class="page-title float-left">Assign Application To Template</h4>

								<ol class="breadcrumb float-right">
									<li class="breadcrumb-item active"><a href="#">Home</a></li>
									<li class="breadcrumb-item  active"><a href="#">User</a></li>
									<li class="breadcrumb-item active">Assign Application To Template</li>
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
															 <div class="col-md-6">
															<select class="form-control" id="selectTemplate">
																<option value="0">Ward Pharmacy</option>
																<option value="1">OPD</option>
															</select>																
															</div>
														</div>
													</div>
													<div class="col-2">
														<button id="searchBtn" type="button"
															class="btn  btn-primary"
															onclick="searchApplication();">Application</button>
													</div>
												</div>
												<div class="form-group row">
												<label class="col-3 col-form-label inner_md_htext">Module
														Name<span style="color: red">*</span>
													</label>
													<div class="col-5">
														<div class="col-auto">
															<div class="input-group mb-2">
															<select class="form-control" id="selectModuleTemplateWise" >
																<option value="0">Dispensary</option>
																<option value="1">Store</option>
															</select>																
															</div>
														</div>
													</div>
												</div>
											</form>

										</div>

									</div>

									<div class="row">
										<div class="col-sm-12">
											<nav aria-label="Page navigation example">
												<ul class="pagination float-right">
													<li class="page-item"><a href="#"
														aria-label="Previous" class="page-link"> <i
															class="fa fa-angle-left"></i>
													</a></li>

													<li class="page-item"><a href="#" aria-label="Next"
														class="page-link"> <i class="fa fa-angle-right"></i>
													</a></li>
												</ul>
											</nav>
										</div>
									</div>

									<table class="tblSearchActions" cellspacing="0" cellpadding="0"	border="0">
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
												<th id="th2" class="inner_md_htext">Serial No</th>
												<th id="th3" class="inner_md_htext">Assigned Module</th>
											</tr>
										</thead>
										<tbody id="tblListOfApplication">

										</tbody>
									</table>

									
									<br>
									<div class="row">
										<div class="col-md-7"></div>
										<div class="col-md-5">
											<form>
												<div class="button-list">

													<button id="btnTemplateAssignment" type="button"
														class="btn btn-primary btn-rounded w-md waves-effect waves-light"
														onclick="saveTemplateAssignment();">Save</button>
													<!-- <button id="updateBtn" type="button"
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
														onclick="Reset();">Reset</button> -->

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

			<footer class="footer"> Assign Application To Template </footer>

		</div>

		<!-- ============================================================== -->
		<!-- End Right content here -->
		<!-- ============================================================== -->

	</div>
	<!-- END wrapper -->

	<!-- jQuery  -->


</body>

</html>