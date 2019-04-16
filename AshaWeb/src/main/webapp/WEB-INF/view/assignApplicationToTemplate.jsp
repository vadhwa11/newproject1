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
		
		GetTemplateList();
		$j('#selectModuleTemllateWiseId').hide();
	});
	
	var moduleList='';
	var comboArray=[];
	function GetTemplateList(){
		jQuery.ajax({
		 	crossOrigin: true,
		    method: "POST",			    
		    crossDomain:true,
		    url: "getTemplateList",
		    data: JSON.stringify({}),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){
		    	//alert("success "+result.data.length);
		    	var combo = "<option value='0'>Select Template</option>" ;
		    	
		    	for(var i=0;i<result.data.length;i++){
		    		comboArray[i] = result.data[i].templateName;
		    		combo += '<option value='+result.data[i].templateId+'>' +result.data[i].templateName+ '</option>';
		    		
		    	}		    	
		    	jQuery('#selectTemplate').append(combo);
		    }
		    
		});
	}
	
	function searchApplication(){
		var htmlTable = "";	
		var tempId = $j('#selectTemplate').find('option:selected').val();
		
		param={"templateId":tempId}
		jQuery.ajax({
			method:"POST",
			url:"getModuleNameTemplateWise",
			data: JSON.stringify(param),
			contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){
		    	var rownum=1;
		    	var dataList = result.data;
		    	
		    	for(i=0;i<dataList.length;i++)
		 		{	  		
		    		htmlTable = htmlTable+"<tr id='"+dataList[i].tempappId+"' >";			
		 			htmlTable = htmlTable +"<td style='width: 150px;'>"+rownum+"</td>";
		 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].name+"</td>";
		 			/* htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].templateId+"</td>"; */
		 			htmlTable = htmlTable+"</tr>";
		 			rownum++;
		 		}
		    	if(dataList.length == 0)
		 		{
		 		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		 		   
		 		}
		    	$j("#tblListOfApplication").html(htmlTable);
		    	console.log(result);		    		
		    	
		    }
		})
		
	}
	
	function displayApplication(){
		$j('#selectModuleTemllateWiseId').show();
		
		jQuery.ajax({
			method:"POST",
			url:"getModuleNameTemplateWise",
			data: JSON.stringify(param),
			contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){		    	
			var combo = "<option value='0'>Select Template</option>" ;		    	
		    	for(var i=0;i<result.listObjModule.length;i++){
		    		comboArray[i] = result.listObjModule[i].applicationId;		    		
		    		combo += '<option value='+result.listObjModule[i].applicationId+'>' +result.listObjModule[i].applicationName+ '</option>';		    		    		
		    	}		    	    	
				$j('#selectModuleTemplateWise').append(combo); 
		    }
		})
		
	}
	
	function populateApplicationAndTemplates(parentId){
		//alert(parentId);
		var tempId = $j('#selectTemplate').find('option:selected').val();
		//alert("tempId :: "+tempId);
		params={"parentId":parentId,
				"templateId":tempId}
		
		var htmlTable = "";
			jQuery.ajax({
				method:"POST",
				data: JSON.stringify(params),
				url:"getAllApplicationAndTemplates",				
				contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(result){
			    	
			    	var rownum=1;
			    	var dataList = result.data;
			    	var tempList = result.tempList;
			    	var checkboxvalue='';
			    //	alert(tempList);
			    	for(i=0;i<dataList.length;i++)
			 		{	  	
			    		var checkbox_name = '';
			    		var checkbox = '';
			    		var parentId = dataList[i].parentId;
			    		//alert(parentId);
			    		htmlTable = htmlTable+"<input type='hidden' name='applicationId' value="+dataList[i].applicationId+" id='applicationId"+i+"'/>";
			    		htmlTable = htmlTable+"<tr id='"+dataList[i].applicationId+"' >";			
			 			htmlTable = htmlTable +"<td style='width: 150px;'>"+rownum+"</td>";
			 			if(parentId!=0){
			 			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].applicationName2+"&nbsp;"+"&gt;"+"&gt;"+"&nbsp"+dataList[i].applicationName+"</td>";
			 			}
			 			else{
			 				htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].applicationName+"</td>";
			 				
			 			}
			 			
			 			var appId=dataList[i].applicationId;
			 			
			 			for(j=0;j<tempList.length;j++){
			 				//alert("appId :: "+appId);
			 				//alert("tempList[i].appId :: "+tempList[i].appId);
			 				var apppp=tempList[i].appId;
			 				if(appId==apppp || parentId == '0'){
				 				checkboxvalue='checked=checked';
				 				break;
				 			}
			 				 
				 			else {
				 				checkboxvalue='';
				 			}
			 				
			 			}
			 			//alert("checkboxvalue>>>"+checkboxvalue);
			 			
			 			htmlTable =htmlTable +"<td style='width: 150px;'><input type='checkbox' name='checkBoxTemp' "+checkboxvalue+" id='checkBoxTemp"+i+"'></td>"; 
			 			/*  $.each(tempList, function(ij, item) {
			 			if(appId == item.appId){
			 				checkboxvalue="checked";
			 				break;
			 			}
			 			else{
			 				checkboxvalue="";
			 			}
			 			
			 			 
			 			}); 
			 			htmlTable =htmlTable +"<td style='width: 150px;'><input type='checkbox' name='checkBoxTemp' '"+checkboxvalue+"' id='checkBoxTemp"+i+"'></td>"; */
			 			//htmlTable = htmlTable +"<td style='width: 150px;'>"+checkbox+"</td>";
			 			htmlTable = htmlTable+"</tr>";
			 			rownum++;
			 		}
			    	if(dataList.length == 0)
			 		{
			 		htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
			 		   
			 		}
			    	
			    	$j("#tblListOfApplicationTemplateWise").html(htmlTable);
			    	console.log(result);
			    }
			})
	}
	
	var urgentValuearray=[];
	var applicationIdAarray=[];
	function addTemplateApplication(){
		var tempId = $j('#selectTemplate').find('option:selected').val();
		
		var tbl = document.getElementById('tblListOfApplicationTemplateWise');
		lastRow = tbl.rows.length;
		var labMarkValue = ''; 
		 for(var j=0;j<lastRow;j++){
		    var chkf = 'checkBoxTemp'+j+'';
		    var applicationId = 'applicationId'+j+'';
		if(document.getElementById(chkf).checked == true){
		  var yurgent='Y';
		  urgent=yurgent;
		  }
		  else
		  {
		      var nUrgent='N';
		      urgent=nUrgent;
		  }
		    urgentValuearray.push(urgent);
		   var applicattttt= document.getElementById(applicationId).value;
		    applicationIdAarray.push(applicattttt);
		    
		}
		 // alert("applicattttt"+applicationIdAarray);
		 
		 //$('#tempCheckValue').val(urgentValuearray);
		 var params={'urgentValuearray':urgentValuearray,
				 'applicationId':applicationIdAarray,
				 'templateId':tempId}
		 
		 jQuery.ajax({
				method:"POST",
				data: JSON.stringify(params),
				url:"addTemplateApplication",				
				contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(result){
			    	
			    	console.log(result)
			    }
			    });
	}
	</script>

</head>

<body>

	<!-- Begin page -->
	<div id="wrapper">

		<!-- Top Bar Start -->
		<div class="topbar">

			<!-- LOGO -->
			
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
				<div class="internal_Htext">Assign Application To Template</div>
					<div class="row">
						
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
												<!-- <input type="hidden" name="tempCheckValue" id="tempCheckValue"/> -->
												<div class="form-group row">
													<label class="col-3 col-form-label inner_md_htext">Template
														Name<span style="color: red">*</span>
													</label>
													<div class="col-5">
														<div class="col-auto">
															 <div class="col-md-14">
															<select class="form-control" id="selectTemplate" name="selectTemplate" onchange="searchApplication();">
																
															</select>																
															</div>
														</div>
													</div>
													<div class="col-2">
														<button id="searchBtn" type="button"
															class="btn  btn-primary"
															onclick="displayApplication();">Application</button>
													</div>
												</div>
												<div class="form-group row" id="selectModuleTemllateWiseId">
												<label class="col-3 col-form-label inner_md_htext">Module
														Name<span style="color: red">*</span>
													</label>
													<div class="col-5">
														<div class="col-auto">
															<div class="input-group mb-2" >
															<select class="form-control" id="selectModuleTemplateWise" name="selectModuleTemplateWise" onchange="populateApplicationAndTemplates(this.value);">
																
															</select>																
															</div>
														</div>
													</div>
													
													
													
												</div>
											</form>

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
									
									<table class="table table-hover table-bordered">
										<thead class="bg-success" style="color: #fff;">											
										</thead>
										<tbody id="tblListOfApplicationTemplateWise">

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
														onclick="addTemplateApplication();">Save</button>
													

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
			
		</div>

		<!-- ============================================================== -->
		<!-- End Right content here -->
		<!-- ============================================================== -->

	</div>
	<!-- END wrapper -->

	<!-- jQuery  -->


</body>

</html>