<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="..//view/leftMenu.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>New Admission</title>
<%@include file="..//view/commonJavaScript.jsp"%>
</head>
<script type="text/javascript" language="javascript">
	
	<%			
		String hospitalId = "1";
		if (session.getAttribute("hospital_id") !=null)
		{
			hospitalId = session.getAttribute("hospital_id")+"";
		}
	%>
	var $j = jQuery.noConflict();
	$j(document).ready(function() {
		
	});
	var result = '';
	function getPatientList(){
	
		var params = {
				"service_no": $j('#service_no').val()
		}
		 $j.ajax({
				type:"POST",
				contentType : "application/json",
				url: 'getServiceWisePatientList',
				data : JSON.stringify(params),
				dataType: "json",			
				cache: false,
				success: function(data)
				{  
					result = data;
					var data_length = data.patient_list.length;
					if(data_length == 0){
						alert("Service No. does not exist");
						return;
					}
					$j('#patient_list').empty();
					var make_patient_List_combo = '<option value="">Select</option>';
					for(var i=0;i<data_length;i++){
						make_patient_List_combo += '<option value="'+data.patient_list[i].patient_id+'">'+data.patient_list[i].patient_name+'</option>'
					}
					$j('#patient_list').append(make_patient_List_combo);
					
				},
				
				error: function(data)
				{					
					
					alert("An error has occurred while contacting the server");
					
				}
		}); 
	}
	
/* 	$j('#patient_list').change(function () {
		alert("called");
        var selectedText = $j(this).find("option:selected").text();
        var selectedValue = $j(this).val();
        alert("Selected Text: " + selectedText + " Value: " + selectedValue);
    }); */
	
 	function getPatientDetail(id){
		 for(var j = 0;j<result.patient_list.length;j++){
			if(result.patient_list[j].patient_id == id){
				
				$j('#patient_name').val(result.patient_list[j].patient_name);
				$j('#service_no2').val(result.patient_list[j].service_no);
				$j('#gender').val(result.patient_list[j].gender);
				$j('#age').val(result.patient_list[j].age);
				$j('#rank').val(result.patient_list[j].rank);
				$j('#mobile_no').val(result.patient_list[j].mobile_no);
				$j('#emp_name').val(result.patient_list[j].emp_name);
				$j('#relation').val(result.patient_list[j].relation);
				$j('#patient_id').val(result.patient_list[j].patient_id);
			}
		} 
		 var disposal_combo = '';
				for(var k=0;k<result.disposal_list.length;k++){
					disposal_combo += '<option value="'+result.disposal_list[k].id+'">'+result.disposal_list[k].disposal_name+'</option>';
				}
				 $j('#disposal_combo').append(disposal_combo);
	} 
    
    function saveAdmissionDetail(){
    	/*validate="Ward,String,yes"
    	var value=validateFields('newAdmission');
    	 if(value!=true){
    		alert(value);	
    	}else{
    		alert("hello");
    	} */
    	var admission_date = $j('#admission_date').val();
    	var ward = $j('#ward').val();
    	var admission_no = $j('#admission_no').val();
    	if($j('#service_no').val() == '' || $j('#service_no').val() == undefined){
    		window.alert("Please Enter Service No.");
    		return;
    	}else if($j('#patient_list').find('option:selected').text() == '' || $j('#patient_list').find('option:selected').text() == undefined || $j('#patient_list').find('option:selected').text() == 'Select'){
    		window.alert("Patient must be selected");
    		return;
    	}else if(admission_date == '' || admission_date == undefined){
    		alert("Admission Date must be selected");
    		return;
    	} else if(ward == '' || ward == undefined){
    		alert("Please enter Ward Name");
    		return;
    	}else if(admission_no == '' || admission_no == undefined){
    		alert("Admission No. must be entered");
    		return;
    	}    	
    	
		var params = {
				"patient_id": $j('#patient_id').val(),
				"admission_date":$j('#admission_date').val(),
				"ward":$j('#ward').val(),
				"admission_no":$j('#admission_no').val(),
				"remarks":$j('#remark').val(),
				"hospital_id": <%= hospitalId %>
		}
		 $j.ajax({
				type:"POST",
				contentType : "application/json",
				url: 'saveNewAdmission',
				data : JSON.stringify(params),
				dataType: "json",			
				cache: false,
				success: function(data)
				{  
					alert(data.msg);
					window.location = "admissionPending";
				},
				
				error: function(data)
				{					
					
					alert("An error has occurred while contacting the server");
					
				}
		}); 
    }
    
    function resetDetail(){
    	$j('#admission_date').val('');
    	$j('#ward').val('');
    	$j('#remark').val('');
    	$j('#admission_no').val('');
    	$j('#discharge_date').val('');
    }
    
    function closeScreen(){
    	window.location = 'admissionPending';
    }
    
	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}
</script>

<body>
	<div class="content-page">
		<div class="">
			<div class="container-fluid">
				<div class="internal_Htext">New Admission</div>

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">


								<div class="row">
									<div class="col-md-12">
										<form id="newAdmission" name="newAdmission">
										<div class="row">
										<h4  class="service_htext">Patient Details</h4>
										</div>
											<div class="row">

												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class=" col-form-label  ">Service No. </label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="service_no"
																name="service_no" onchange="getPatientList()">
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Patient List</label>
														</div>
														<div class="col-sm-7">
															<select id="patient_list" onchange="getPatientDetail(this.value)">
															
															</select>
														</div>
													</div>
												</div>
												</div>
												<div class="row">
												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Service No.</label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="service_no2" value="" readonly>
														</div>
													</div>
												</div>


												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class=" col-form-label  ">Patient Name </label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="patient_name" value="" readonly>
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Age</label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="age"
																name="age" readonly>
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Gender</label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="gender"
																name="gender" readonly>
														</div>
													</div>
												</div>


												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class=" col-form-label  ">Rank </label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control"
																id="rank" name="rank" readonly>
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Mobile No.</label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control"
																id="mobile_no" readonly>
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Name</label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="emp_name" readonly>
														</div>
													</div>
												</div>


												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class=" col-form-label  ">Relation</label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="relation" readonly>
														</div>
													</div>
												</div>
												</div>
<!-- 												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Date of Admission</label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="instructions">
														</div>
													</div>
												</div> -->
												<div class="row">
													<h4  class="service_htext">Admission Details</h4>
												</div>
												<div class="row">
												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Date of Admission</label>
														</div>
														<div class="col-sm-7">
															<input type="date" class="form-control"
																id="admission_date" name="admission_date">
														</div>
													</div>
												</div>





												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class=" col-form-label  ">Ward Name</label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="ward">
														</div>
													</div>
												</div>
												<!-- <div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Date of Discharge</label>
														</div>
														<div class="col-sm-7">
															<input type="date" class="form-control"
																id="discharge_date">
														</div>
													</div>
												</div> -->
												<!-- <div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Disposal</label>
														</div>
														<div class="col-sm-7">
															<select class="form-control" id="disposal_combo">
															</select>
														</div>
													</div>
												</div> -->



												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class=" col-form-label  ">Remark</label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="remark">
														</div>
													</div>
												</div>
												
												<div class="col-md-4">
													<div class="form-group row">
														<div class="col-sm-5">
															<label class="col-form-label  ">Admission No.</label>
														</div>
														<div class="col-sm-7">
															<input type="text" class="form-control" id="admission_no">
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group row"></div>
												</div>

												<div class="form-row">
													<input type="hidden" id="header_id" value=""> <input
														type="hidden" id="patient_id" value=""> <input
														type="hidden" id="admission_id" value="">
												</div>



												<div class="col-md-8">
													<div class="form-group row"></div>
												</div>
												
														<div class="col-md-1">  
														<button type="button" class="btn btn-primary"
						 onclick="saveAdmissionDetail()">Submit</button>
						</div>
					<div class="col-md-1">  
					<button type="button" class="btn btn-primary"
						onclick="resetDetail()">Reset</button>
						</div>
					<div class="col-md-1">  	
					<button type="button" class="btn btn-primary"
						onclick="closeScreen()">Close</button>
					</div>

											</div>
										</form>
									</div>
								</div>


							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>