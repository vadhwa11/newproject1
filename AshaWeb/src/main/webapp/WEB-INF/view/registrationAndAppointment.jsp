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
    <title>Indian Cost Guard</title>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <%@include file="..//view/commonJavaScript.jsp"%>
    

<script>
        $(document).ready(function() {
            var brand = document.getElementById('logo-id');
            brand.className = 'attachment_upload';
            brand.onchange = function() {
                document.getElementById('fakeUploadLogo').value = this.value.substring(12);
            };

            // Source: http://stackoverflow.com/a/4459419/6396981
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function(e) {
                        $('.img-preview').attr('src', e.target.result);
                    };
                    reader.readAsDataURL(input.files[0]);
                }
            }
            $("#logo-id").change(function() {
                readURL(this);
            });
        });
    </script>  

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
                                <h4 class="page-title float-left">Registration And Appointment</h4>

                                <ol class="breadcrumb float-right">
                                    <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                     <li class="breadcrumb-item  active"><a href="#">Master</a></li>  
                                    <li class="breadcrumb-item active">Registration And Appointment</li>
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
                                                                     
                                        <div class="row">
            <div class="col-md-9">
                <h6>CREATE REGISTRATION & APPOINTMENT  OF OTHERS</h6>
            </div>

            <div class="col-md-3"> 
                <div class="form-group">
                    <div class="row" style="padding-bottom:10px;">
                        <div class="col-md-3">

                        </div>
                        <div class="col-md-6">
                            <div class="main-img-preview" style="width: 60%;    height: 60%;">
                                <img class="img-responsive thumbnail img-preview" src="${pageContext.request.contextPath}/resources/images/user.png" title="Preview Logo">
                            </div>
                        </div>
                        <div class="col-md-3">
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-1">

                        </div>
                        <div class="col-md-10">

                            <div class="input-group">
                                <input id="fakeUploadLogo" class="form-control fake-shadow" placeholder="Choose File" disabled="disabled">
                                <div class="input-group-btn">
                                    <div class="fileUpload btn btn-danger fake-shadow">
                                        <span><i class="glyphicon glyphicon-upload"></i> Upload Image</span>
                                        <input id="logo-id" name="logo" type="file" class="attachment_upload">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-1">
                        </div>

                    </div>
                </div>

            </div>
        </div>

        <!-- <h6 style="text-decoration:underline">Service Details</h6> -->
        <form>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
					   
					   
                             
                        <div class="col-md-4">
							<div class="form-check form-check-inline">
							  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
							  <label class="form-check-label" for="inlineRadio1">New Registration</label>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-check form-check-inline">
							  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
							  <label class="form-check-label" for="inlineRadio2">Already Registered</label>
							</div>						 
                        </div>
                        
                        
                        <div class="col-md-4">
                        
                        
                        </div>
                  <br> <br>
                        <div class="col-md-6">
                            <div class="form-group row">
                                <label class="col-md-4 col-form-label">Registration Type</label>
                                <div class="col-md-6">
                                    <select class="form-control">
                                        <option value="0" selected="selected">Select</option>
                                        <option value="1">Non Armed Forced</option>
                                        <option value="2">Other Service Personnel</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                        </div>

                        <div class="col-md-6">
                            <div class="form-group row">
                                <label class="col-md-4 col-form-label"><span style="color:red"><sup>&#9733;</sup></span>Name</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" id="name" placeholder="Name">
                                </div>
                                <div class="col-md-2">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group row">
                                <div class="col-md-2">
                                </div>
                                <label class="col-md-4 col-form-label"><span style="color:red"><sup>&#9733;</sup></span>Gender</label>
                                <div class="col-md-6">
                                    <select class="form-control">
                                        <option value="0" selected="selected">Select</option>
                                        <option value="1">Male</option>
                                        <option value="2">Female</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group row">
                                <label class="col-md-4 col-form-label"><span style="color:red"><sup>&#9733;</sup></span>DOB</label>
                                <div class="col-md-6">
                                    <input type="date" class="form-control" id="name" placeholder="DOB">
                                </div>
                                <div class="col-md-2">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group row">
                                <div class="col-md-2">
                                </div>
                                <label class="col-md-4 col-form-label"><span style="color:red"><sup>&#9733;</sup></span>Age</label>
                                <div class="col-md-6">
                                    <input type="number" class="form-control" id="firstname" placeholder="E/A">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group row">

                                <label class="col-md-4 col-form-label">ID Type</label>
                                <div class="col-md-6">
                                    <select class="form-control">
                                        <option value="0" selected="selected">Select</option>
                                        <option value="1">PAN No.</option>
                                        <option value="2">Aadhar No.</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                </div>

                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group row">
                                <div class="col-md-2">
                                </div>
                                <label class="col-md-4 col-form-label">Name</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" id="name" placeholder="Name">
                                </div>

                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group row">
                                <label class="col-md-4 col-form-label">Mobile Number</label>
                                <div class="col-md-6">
                                    <input type="number" class="form-control" id="name" placeholder="Mobile Number">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                        </div>

                    </div>
                </div>
            </div>
        </form>

        <h6 style="text-decoration:underline">Visit Details</h6>
        <form>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">

                        <div class="col-md-6">
                            <div class="form-group row">
                                <label class="col-md-4 col-form-label">Department</label>
                                <div class="col-md-6">
                                    <select class="form-control">
                                        <option value="0" selected="selected">Select</option>
                                        <option value="1">General OPD 1</option>
                                        <option value="2"> General OPD 2</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                </div>

                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group row">
                                <div class="col-md-2">
                                </div>
                                <label class="col-md-4 col-form-label">OPD Consultation</label>
                                <div class="col-md-6">
                                    <select class="form-control">
                                        <option value="0" selected="selected">Select</option>
                                        <option value="1">Lorem Ipsum 1</option>
                                        <option value="2">Lorem Ipsum 2</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group row">
                                <label class="col-md-4 col-form-label">Priority</label>
                                <div class="col-md-6">
                                    <select class="form-control">
                                        <option value="0" selected="selected">Select</option>
                                        <option value="1"> 1</option>
                                        <option value="2"> 2</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                </div>

                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group row">
                                <div class="col-md-2">
                                </div>
                                <div class="col-md-4">
                                    <button class="btn btn-primary  reception_registration_token" type="button">Show Token</button>
                                </div>

                                <div class="col-md-4">

                                    <div id="div1">Display Token
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </form>

        <br>

        <div class="row">
            <div class="col-md-12">

                <button class="btn btn-primary" type="button">Submit</button>

            </div>
        </div>

    </div>
                      
                      </div>                                    
									<br>	
                                    <div class="row">
                                        <div class="col-md-7">
                                        </div>
                                        <div class="col-md-5">
                                            <form>
                                                <div class="button-list">

                                                    <button id="btnAddHospital" type="button"  class="btn btn-primary btn-rounded w-md waves-effect waves-light" onclick="">Submit</button>
                                                    
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