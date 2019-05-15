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
    <title>Indian Cost Gaurd</title>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    
<%--     <link href="${pageContext.request.contextPath}/resources/css/sweetalert2.min.css" rel="stylesheet"> --%>  
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/metismenu.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/app.css" rel="stylesheet" type="text/css" />    
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
    
    <script src="${pageContext.request.contextPath}/resources/js/modernizr.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/metisMenu.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/waves.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.scrollTo.min.js"></script>    
	 <script src="${pageContext.request.contextPath}/resources/js/jquery.core.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.app.js"></script>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
  

</head>

<body>

    <!-- Begin page -->
    <div id="wrapper">
 
        <div class="content-page">
            <!-- Start content -->
            <div class="">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <div class="page-title-box">
                                <h4 class="page-title float-left">ICD Master</h4>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">ICD Master</li>
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
                                            <form class="form-horizontal" id="searchHospitalForm" name="searchHospitalForm" method="" role="form">
                                                <div class="form-group row">
                                                    <label class="col-3 col-form-label inner_md_htext">ICD Name</label>
                                                    <div class="col-5">
                                                        <div class="col-auto">
                                                            <label class="sr-only" for="inlineFormInputGroup">ICD Name</label>
                                                            <div class="input-group mb-2">
                                                                <!-- <div class="input-group-prepend">
                                                                    <div class="input-group-text">&#128269;</div>
                                                                </div> -->
                                                                <input type="text" name="searchICD" id="searchICD" class="form-control" id="inlineFormInputGroup" placeholder="ICD">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn  btn-primary" onclick="searchICDList();">Search</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                        
                                        
                                        
                                        
                                        <div class="col-md-4">
                                            <form>
                                                <div class="button-list">
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" onclick="showAll('ALL');">Show All</button>
                                                    <button type="button" class="btn  btn-primary btn-rounded w-md waves-effect waves-light" >Reports</button>

                                                </div>
                                            </form>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination float-right">
                                                    <li class="page-item">
                                                        <a href="#" aria-label="Previous" class="page-link">
                                                            <i class="fa fa-angle-left"></i>
                                                        </a>
                                                    </li>
                                                    
                                                    <li class="page-item">
                                                        <a href="#" aria-label="Next" class="page-link">
                                                            <i class="fa fa-angle-right"></i>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </nav>
                                        </div>
                                    </div>

					<!-- <table class="table table-striped table-hover jambo_table"> -->
                   <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >			<tr>
				<td class="SearchStatus" style="font-size: 15px;" align="left">Search Results</td>
				<td>
				<div id=resultnavigation></div>
				
				</td>
				</tr>
			</table>

                                    <table class="table table-hover table-bordered">
                                        <thead class="bg-success" style="color:#fff;">
                                            <tr>
                                                <th id="th2" class ="inner_md_htext">ICD Code</th>
                                                <th id="th3" class ="inner_md_htext">ICD Name</th>
                                                <th id="th4" class ="inner_md_htext"> ICD SubCategory</th>
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
                                                            <label for="Command Code" class=" col-form-label inner_md_htext" >ICD Code</label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="ICDCode" id="ICDCode" class="form-control" placeholder="ICD Code" onkeypress="enableAddButton(); validTextBox();">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">ICD Name</label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="ICDName" id="ICDName" class="form-control" placeholder="ICD Name" onkeypress="enableAddButton(); validTextBox();">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group row">
                                                        <div class="col-sm-5">
                                                            <label for="service" class="col-form-label inner_md_htext">ICD SubCategory</label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <input type="text" name="ICDSubCategory" id="ICDSubCategory" class="form-control" placeholder="ICD SubCategory" onkeypress="enableAddButton(); validTextBox();">
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

                                                    <button id="btnAddHospital" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="addHospitalDetails();">Add</button>
                                                    <button id ="updateBtn" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateHospitalMaster();">Update</button>
                                                    <button id="btnActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateHospitalStatus();">Active</button>
                                      				<button id="btnInActive" type="button" class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="updateHospitalStatus();">InActive</button>
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

            <footer class="footer">
                ICD Master
            </footer>

        </div>

        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

    </div>
    <!-- END wrapper -->

    <!-- jQuery  -->
    

</body>

</html>