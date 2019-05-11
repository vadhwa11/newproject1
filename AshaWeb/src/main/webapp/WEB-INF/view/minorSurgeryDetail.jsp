 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="..//view/leftMenu.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Referral Patient Detail</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="..//view/commonJavaScript.jsp"%> 
</head>
<script type="text/javascript" language="javascript">
<%	String hospitalId = "1";
%>
var html="";
var combo="";
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
	
		getAnesthesiaList();
		var data = ${data};
		$j('#patient_name').val(data.patient_detail.patientName);
		$j('#age').val(data.patient_detail.age);
		$j('#serviceNo').val(data.patient_detail.serviceNo);		
		$j('#opdDate').val(data.patient_detail.opdDate);
		$j('#gender').val(data.patient_detail.gender);
		$j('#header_id').val(data.patient_detail.header_id);
		$j('#icd_diagnosis').val(data.patient_detail.working_diagnosis);
		
		html += '<tr>';		
			html += '<tr id="row"><input type="hidden" id="rowid" value="">';
			html += '<td><div><input class="form-control" id="procedure_name" type="text" value="" readonly></div></td>';
			html += '<td><div><input class="form-control" id="procedure_name" type="text" value=""></div></td>';
			html += '<td><div><input class="form-control" id="frequency" type="text" value="" ></div></td>';
			html += '<td><div><select id="anesthesia"></select></div></td>';			
			html += '<td><div><input class="form-control" id="final_status" type="text" value="" ></div></td>';
			html += '<td><div><button type="button" class="btn btn-primary" value="" onclick="addRow()">Add </button>&nbsp;<button type="button" class="btn btn-danger" value="" onclick="deleteRow(this)">Delete</button></td></div>';
			html += '</tr>';
			
			$j('#tblListofObesity').append(html);
			
		});
		
		function getAnesthesiaList(){
			
			jQuery.ajax({
        	 	crossOrigin: true,
        	    method: "POST",			    
        	    crossDomain:true,
        	    url: "getAnesthesiaList",
        	    data: JSON.stringify({}),
        	    contentType: "application/json; charset=utf-8",
        	    dataType: "json",
        	    success: function(result){
        	    	
        	    	combo = "<option value=\"\">Select</option>" ;
        	    	
        	    	for(var i=0;i<result.data.length;i++){	    		
        	    		combo += '<option value='+result.data[i].anesthesiaId+'>' +result.data[i].anesthesiaName+ '</option>';    		
        	    		
        	    	}
        	    	jQuery('#anesthesia').append(combo);
        	    }
        	    
        	});
		
		}
		
		function addRow(){	
				
			
			$j('#tblListofObesity').append(html);
		
		
				
		}
		
		function deleteRow(){
			
			alert("delete row clicked");
			 /* var parentRowIndex = currElement.parentNode.parentNode.rowIndex;
		     document.getElementById("row").deleteRow(parentRowIndex);  */ 
		    
			
	}
		
</script>

<body>
	<div class="content-page">
		<!-- Start content -->
		<div class="">
			<div class="container-fluid">

				<div class="internal_Htext">Minor Surgery Waiting List</div>
				<!-- end row -->

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">

								<form>
									<div class="row">
										<h4 class="service_htext">Patient Details</h4>
										<div class="col-md-4">
											<div class="form-group row">
												<label for="staticEmail" class="col-sm-4 col-form-label">Patient
													Name</label>
												<div class="col-sm-7">
													<input type="text" class="form-control  form-control-sm"
														id="patient_name" name="patient_name" readonly>
												</div>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group row">
												<label for="staticEmail" class="col-sm-4 col-form-label">Age</label>
												<div class="col-sm-7">
													<input type="text" class="form-control  form-control-sm"
														id="age" name="age" readonly>
												</div>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group row">
												<label for="staticEmail" class="col-sm-4 col-form-label">Gender</label>
												<div class="col-sm-7">
													<input type="text" class="form-control  form-control-sm"
														id="gender" name="gender" readonly>
												</div>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group row">
												<label for="staticEmail" class="col-sm-4 col-form-label">Service 
													No</label>
												<div class="col-sm-7">
													<input type="text" class="form-control  form-control-sm"
														id="serviceNo" name="serviceNo" readonly>
												</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group row">
												<label for="staticEmail" class="col-sm-4 col-form-label">OPD 
													Date</label>
												<div class="col-sm-7">
													<input type="text" class="form-control  form-control-sm"
														id="opdDate" name="opdDate" readonly>
												</div>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group row">
												<label for="staticEmail" class="col-sm-4 col-form-label">
													Diagnosis</label>
												<div class="col-sm-7">
													<input type="text" class="form-control  form-control-sm"
														id="icd_diagnosis" name="icd_diagnosis" >
												</div>
											</div>
										</div>
									</div>
									
									
									<div class="row">
										<h4 class="service_htext">Vitals</h4>
										
										<div class="col-md-4">
                                                                            <div class="form-group row">
                                                                             
                                                                                  <label   class="col-sm-6 col-form-label">Height</label>
                                                                               
                                                                                <div class="col-sm-6">
                                                                                   <input name="height" id="height" onblur="idealWeight();checkBMI();"
											type="text" class="form-control border-input"
											placeholder="Height" value="" required />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                              
                                                                                    <label   class="col-sm-6 col-form-label">Ideal Weight</label>
                                                                              
                                                                                <div class="col-sm-6">
                                                                                    <input name="ideal_weight"
											id="ideal_weight" type="text" onblur="checkVaration()"
											class="form-control border-input" placeholder="Ideal Weight" />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                    <label   class="col-sm-6 col-form-label">Weight</label>
                                                                             
                                                                                <div class="col-sm-6">
                                                                                    <input name="Weight" id="weight"
											type="text" class="form-control border-input" onblur="checkVaration();checkBMI();"
											placeholder="Weight" />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                  <label   class="col-sm-6 col-form-label">Variation in Weight</label>
                                                                               
                                                                                <div class="col-sm-6">
                                                                                   <input
											name="Variation in Weight" id="variant_in_weight" type="text"
											class="form-control border-input"
											placeholder="Variation in Weight" value="" readonly />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        
                                                                         <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                    <label   class="col-sm-6 col-form-label">Temperature</label>
                                                                                
                                                                                <div class="col-sm-6">
                                                                                    <input name="tempature"
											id="tempature" type="text" class="form-control border-input"
											placeholder="Temperature" value="" required>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                    <label   class="col-sm-6 col-form-label">BP</label>
                                                                              
                                                                                <div class="col-sm-6">
                                                                                    <input name="bp" id="bp" type="text"
											class="form-control border-input" placeholder="bp" value=""
											required>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                  <label   class="col-sm-6 col-form-label">Pulse</label>
                                                                              
                                                                                <div class="col-sm-6">
                                                                                    <input name="pulse" id="pulse"
											type="text" class="form-control border-input"
											placeholder="Pulse" value="" required />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                         <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                    <label   class="col-sm-6 col-form-label">SpO2</label>
                                                                                
                                                                                <div class="col-sm-6">
                                                                                   <input name="spo2" id="spo2" type="text"
											class="form-control border-input" placeholder="SpO2" value=""
											required>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                  <label   class="col-sm-6 col-form-label">BMI</label>
                                                                               
                                                                                <div class="col-sm-6">
                                                                                    <input name="bmi" id="bmi" type="text"
											class="form-control border-input" placeholder="BMI" value=""
											readonly >
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                    <label   class="col-sm-6 col-form-label">RR</label>
                                                                                
                                                                                <div class="col-sm-6">
                                                                                    <input name="rr" id="rr" type="text"
											class="form-control border-input" placeholder="RR" value=""
											required>
                                                                                </div>
                                                                            </div>
                                                                        </div>
										
						
									</div>
									
									
									<h4 class="service_htext">Minor Surgery Details</h4>
									<input type="hidden" id="header_id">
									
										<div class="tablediv">
											<table class="table table-bordered " id="myTable">
												<tr>
												<th style="width: 80px;">SI. No</th>
													<th>Minor Surgery Name</th>
													<th>Prescribed By</th>
													<th>Type of Anesthesia</th>
													<th>Minor Surgery Remarks</th>
													<th></th>
													
												</tr>
												<tbody id="tblListofObesity">

												</tbody>
											</table>
										</div>
								</form>
								
								<div class="row">
                                        <div class="col-md-7">
                                        </div>
                                        <div class="col-md-5">
                                            <form>
                                                <div class="button-list">

                                                    <button type="button" id="btnSubmit" class="btn btn-primary  " onclick="adMsurgery();">Submit</button>
                                                <button type="button" class="btn btn-danger " onclick="Reset();">Reset</button>

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