 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="..//view/leftMenu.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<title>Minor Surgery Detail</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="..//view/commonJavaScript.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/newOpd.js"></script>
</head>
<script type="text/javascript" language="javascript">
<%	String hospitalId = "1";
String userId = "1";
if (session.getAttribute("user_id") != null) {
	userId = session.getAttribute("user_id") + "";
}
%>
//var html="";
var combo="";
var t=0;
var $j = jQuery.noConflict();
var j;
var popup ;
var patientId;
var defaultProcedureValue='M';
$j(document).ready(function()
		{
	    
	
		var data = ${data};			
		data.patient_detail.patientId
		$j('#patient_name').val(data.patient_detail.patientName);
		$j('#age').val(data.patient_detail.age);
		$j('#serviceNo').val(data.patient_detail.serviceNo);		
		$j('#opdDate').val(data.patient_detail.opdDate);
		$j('#gender').val(data.patient_detail.gender);
		$j('#header_id').val(data.patient_detail.header_id);		
		$j('#icd_diagnosis').val(data.patient_detail.working_diagnosis);
		patientId=data.patient_detail.patientId;      	
		/* $j('#height').val(data.vitalDetailst.height);
		
		
		
		
    	$j('#ideal_weight').val(data.vitalDetailst.idealWeight);
    	$j('#weight').val(data.vitalDetailst.weight);
    	$j('#variant_in_weight').val(data.vitalDetailst.varation);
    	$j('#tempature').val(data.vitalDetailst.tempature);
    	$j('#bp').val(data.vitalDetailst.bp);
    	$j('#pulse').val(data.vitalDetailst.pulse);
    	$j('#spo2').val(data.vitalDetailst.spo2);
    	$j('#bmi').val(data.vitalDetailst.bmi);
    	$j('#rr').val(data.vitalDetailst.rr); */
		
		
		
		getAnesthesiaList();	
		var html= '';
		for(var i=0;i<data.nursingDetailList.length;i++){
			 j=i+1;
			html += '<tr><input type="hidden" class="mid" id="rowid'+j+'" value="'+data.nursingDetailList[i].id+'">';
			html += '<td><div><input class="form-control" id="siNO" type="text" value="'+j+'" readonly></div></td>';
			html += '<td><div><input class="form-control" id="procedure_name" type="text" value="'+data.nursingDetailList[i].minorSurgryName+'" readonly></div></td>';
			html += '<td><div><input class="form-control" id="" type="hidden" value="'+data.nursingDetailList[i].prescribedBy+'" readonly>'+data.nursingDetailList[i].userName+'</div></td>';
			html += '<td><div><select class="form-control" id="anesthesia"></select></div></td>';
			html += '<td><div><textarea class="form-control" id="minor_surgery_remarks" type="text" value="" rows="2" cols="3"></textarea></div></td>';
			html += '<td style="display:none"><div><button type="button" class="btn btn-primary" value="" onclick="addRow()" disabled>Add </button>&nbsp;<button type="button" class="btn btn-danger" value="'+data.nursingDetailList[i].id+'" onclick="deleteRow(this)" disabled>Delete</button></div></td>';
			html += '<td style="display:none"><div><input class="form-control" id="minorSurgeyVal" type="hidden" value="'+data.nursingDetailList[i].id+'" readonly ></div></td>';
			html += '</tr>';
		}
			
			
			$j('#gridMinorSurgery').append(html);
			
			
			
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
        	    	jQuery('#anesthesia').empty();
        	    	jQuery('#anesthesia').append(combo);
        	    }
        	    
        	});
		
		}
		
		var arryProcedureCare= new Array();
		var autoNursingNo = '';
		var nursingItemId = '';
		var nursingCode="";
		 var nursingName="";
		 var nursingType="";
		 var nursingId="";
		 var dataNursing='';
		
		function addRow(){	
			t=t+1;
			j=j+1;
			
			var htmlnew= '';
				htmlnew += '<tr><input type="hidden" id="rowid" value="">';
				htmlnew += '<td><div><input class="form-control" id="siNO" type="text" value="'+j+'" readonly ></div></td>';
				htmlnew += '<td><div class="autocomplete"><input class="form-control" id="minor'+t+'" onblur="populateNursing(this.value,1);calucateNursingValue(this);" name="minor" type="text" value="'+nursingId+'" ></div></td>';
				htmlnew += '<td><div><input class="form-control" id="prescribed_by" type="text" value="'+<%=userId%>+'"></div></td>';
				htmlnew += '<td><div><select class="form-control" id="anesthesia'+t+'"> </select></div></td>';
				htmlnew += '<td><div><textarea class="form-control" id="minor_surgery_remarks" type="text" value="" rows="2" cols="3"></textarea></div></td>';
				htmlnew += '<td style="display:none"><div><button type="button" class="btn btn-primary" value="" onclick="addRow()">Add </button>&nbsp;<button type="button" class="btn btn-danger" value="" onclick="deleteRow(this)" >Delete</button></div></td>';
				htmlnew += '<td style="display:none"><div><input class="form-control" id="minorSurgeyVal" type="hidden" value="'+j+'" readonly ></div></td>';
				htmlnew += '</tr>';
				getAnesthesiaList();
				
			$j('#gridMinorSurgery').append(htmlnew);
			$j("#anesthesia"+t).empty();
			$j("#anesthesia"+t).append(combo);
			
			 
				   var pathname = window.location.pathname;
					var accessGroup = pathname.split("/")[1];

					var url = window.location.protocol + "//"
							+ window.location.host + "/" + accessGroup
							+ "/v0.1/opd/getMasNursingCare";
					defaultProcedureValue = "M";
					$j.ajax({
						type : "POST",
						contentType : "application/json",
						url : url,
						data : JSON.stringify({
							'nursingType' : defaultProcedureValue
						}),
						dataType : 'json',
						timeout : 100000,
						
						success : function(res)
						
						{
							dataNursing = res.data;
							autoNursingNo=res.data;
							
							for(var i=0;i<dataNursing.length;i++){
								nursingCode= dataNursing[i].nursingCode;
								nursingName=dataNursing[i].nursingName;
								nursingType = dataNursing[i].nursingType;
								nursingId = dataNursing[i].nursingId;
								var aProcedure=nursingName+"["+nursingCode +"]";
							
								//alert("a "+a);
								arryProcedureCare.push(aProcedure);
								console.log('MasProcedureCare :',arryProcedureCare);
								autocomplete(document.getElementById("minor"+t), arryProcedureCare);
								
							}
							
						
				           },
				           error: function (jqXHR, exception) {
				               var msg = '';
				               if (jqXHR.status === 0) {
				                   msg = 'Not connect.\n Verify Network.';
				               } else if (jqXHR.status == 404) {
				                   msg = 'Requested page not found. [404]';
				               } else if (jqXHR.status == 500) {
				                   msg = 'Internal Server Error [500].';
				               } else if (exception === 'parsererror') {
				                   msg = 'Requested JSON parse failed.';
				               } else if (exception === 'timeout') {
				                   msg = 'Time out error.';
				               } else if (exception === 'abort') {
				                   msg = 'Ajax request aborted.';
				               } else {
				                   msg = 'Uncaught Error.\n' + jqXHR.responseText;
				               }
				               alert(msg);
				           }
					});
			
			autocomplete(document.getElementById("minor"+t), arryProcedureCare)
			
			
			
		}
		
		var nursingNo = '';
		function populateNursing(val, inc) {
		    
			if (val != "") {
				var index1 = val.lastIndexOf("[");
				var indexForBrandName = index1;
				var index2 = val.lastIndexOf("]");
				index1++;
				nursingNo = val.substring(index1, index2);
				var indexForBrandName = indexForBrandName--;
				var brandName = val.substring(0, indexForBrandName);
				// alert("pvms no--"+pvmsNo)
				
				if (nursingNo == "") {
					// alert("pvms no1111--"+pvmsNo)
					// document.getElementById('nomenclature' + inc).value = "";
					// document.getElementById('pvmsNo' + inc).value = "";
					return false;
				} else {
					document.getElementById('procedureNameNursingCare').value = nursingNo
					//alert(nursingNo);
					return true;

				}

			} else {
				return false;
			}
		}
		
		var nursingItemIdValue= '';
		 var nursingValue = '';
		 var nursingType = '';
		 function calucateNursingValue(nursingItem){
			  //alert("autovalue"+autoNursingNo.length);
			  
			  for(var i=0;i<autoNursingNo.length;i++){
				  
				  var nursingC = dataNursing[i].nursingCode;
				  if(nursingC == nursingNo){
					  nursingValue = dataNursing[i].nursingId;
					  //alert(nursingValue)
					  nursingType = dataNursing[i].nursingType;
					  //itemIds = itemIdPrescription;
					 // alert("item id is " + nursingType )
				  }
			  }
			 // console.log("item is "+item);
			  $(nursingItem).closest('tr').find("td:eq(6)").find(":input").val(nursingValue);
			  //$(nursingItem).closest('tr').find("td:eq(7)").find(":input").val(nursingType)
			  
		  }
		
		
		
		function deleteRow(val){
			
			 if(j>1){
			j=j-1;
		}  
			 var param={procedureDtId:val.value};
			 
			 
			 
			//alert("id"+JSON.stringify(param));
		   
		if (confirm("are you sure want to delete ?")){
			
			/* if(val.value !=''){
			jQuery.ajax({
        	 	crossOrigin: true,
        	    method: "POST",			    
        	    crossDomain:true,
        	    url: "deleteMinorSurgery",
        	    data: JSON.stringify(param),
        	    contentType: "application/json; charset=utf-8",
        	    dataType: "json",
        	    success: function(result){
        	    	if(result.status==1){
        	    		alert(result.msg);
        	    	}
        	    	
        	    }
        	    
        	});
			
			} */
			
			
		   if($j('#gridMinorSurgery tr').length > 1) {
			   $j(val).closest('tr').remove()
			 }
		   
			}
		}
		
		function showPreveiousVital() { 
			
            popup = window.open("showPreveiousVital?patientId="+patientId+"", "popUpWindow", "height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes");
            popup.focus();
			
        }
		
		function closeWindow()
		{
			
		  window.close();
		}
		
		function saveMsDetails(){			
			
               	
        	var listofMinorDT =[];
        	
        	var dataMinorgDT='';
        	
        	/* if(document.getElementById('procedureNameNursing').value!= '' && document.getElementById('procedureNameNursing').value != undefined)
	    	{ */
        	
        	
	    	//alert("totalRowCount "+totalRowCount);
        	$j('#gridMinorSurgery tr').each(function(i, el) {        		
        		
        	    var $tds = $(this).find('td');
        	    var surgeryNameId = $tds.eq(1).find(":input").val();
        	    var prescribedBy = $tds.eq(2).find(":input").val();
        	    var typeofAnesthesia = $tds.eq(3).find(":input").val();
        	    var remarks = $tds.eq(4).find(":input").val();        	    
        		var minorVal= $tds.eq(6).find(":input").val();
        	    dataMinorgDT={
        				'msId' : minorVal,
        				'prescribedById' : prescribedBy,        				
        				'anethesiaType':typeofAnesthesia,
        				'remarks':remarks,
        				'headerId':$j('#header_id').val(),
        				'userId': <%=userId%>
        				    }
       	  
        	   listofMinorDT.push(dataMinorgDT);
       	
        	});
			
	    	var param={"listofMinorDT":listofMinorDT};
	    		  //alert(JSON.stringify(param)); 	
	    	jQuery.ajax({
        	 	crossOrigin: true,
        	    method: "POST",			    
        	    crossDomain:true,
        	    url: "saveMinorSurgery",
        	    data: JSON.stringify(param),
        	    contentType: "application/json; charset=utf-8",
        	    dataType: "json",
        	    success: function(result){
        	    	if(result.status==1){
        	    	alert(result.msg);
        	    	}
        	    }
        	    
        	});
			
		}
		
		
</script>

<body>


 <!-- Begin page -->
    <div id="wrapper">

	<div class="content-page">
	
		<!-- Start content -->
		<div class="">
			<div class="container-fluid">

				<div class="internal_Htext">Minor Surgery Details</div>
				<!-- end row -->

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">

								<form>
								
								 <!-- Patient Detail start here--------------->
								 
								 
								    <div class="adviceDivMain" id="button1" onclick="showhide(this.id)">
										<div class="titleBg" style="width: 520px; float: left;">
											<span>Patient Details </span>
										</div>
										<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton1" value="+" onclick="showhide(this.id)" type="button">
									</div> 
											
									   <div class="hisDivHide" id="newpost1">						      
									      	<div class="row">										 
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
																	id="icd_diagnosis" name="icd_diagnosis" readonly>
															</div>
														</div>
													</div>
												
												
										    </div> 
			                           </div>
								
								 <!-- Patient Detail end here--------------->
								
								
								
									
								<!-- -----  Vital start here --------- -->
	
									  <div class="adviceDivMain" id="button2" onclick="showhide(this.id)">
										<div class="titleBg" style="width: 520px; float: left;">
											<span>Vitals &nbsp;&nbsp; <a href="#" style="text-decoration:underline;color:blue;" onclick="showPreveiousVital()">Previous Vitals</a> </span>
										</div>
										<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton2" value="-" onclick="showhide(this.id)" type="button">
									</div>	
								
								
								      <div class="hisDivHide" id="newpost2" style="display:block;">  
																	
														 <div class="row">						 
										
									                               	   <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                             
                                                                                  <label   class="col-sm-4 col-form-label">Height (in cm)</label>
                                                                               
                                                                                <div class="col-sm-7">
                                                                                   <input name="height" id="height" onblur="idealWeight();checkBMI();"
																							type="text" class="form-control border-input"
																							placeholder="Height" value="" required />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                              
                                                                                    <label   class="col-sm-4 col-form-label">Ideal Weight</label>
                                                                              
                                                                                <div class="col-sm-7">
                                                                                    <input name="ideal_weight"
																							id="ideal_weight" type="text" onblur="checkVaration()"
																							class="form-control border-input" placeholder="Ideal Weight" />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                    <label   class="col-sm-4 col-form-label">Weight (in kg)</label>
                                                                             
                                                                                <div class="col-sm-7">
                                                                                    <input name="Weight" id="weight"
																						type="text" class="form-control border-input" onblur="checkVaration();checkBMI();"
																						placeholder="Weight" />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                  <label   class="col-sm-4 col-form-label">Variation in Weight</label>
                                                                               
                                                                                <div class="col-sm-7">
                                                                                   <input
																						name="Variation in Weight" id="variant_in_weight" type="text"
																						class="form-control border-input"
																						placeholder="Variation in Weight" value="" readonly />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        
                                                                         <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                    <label   class="col-sm-4 col-form-label">Temperature (in �C)</label>
                                                                                
                                                                                <div class="col-sm-7">
                                                                                    <input name="tempature"
																						id="tempature" type="text" class="form-control border-input"
																						placeholder="Temperature" value="" required>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                    <label   class="col-sm-4 col-form-label">BP (in mm/Hg)</label>
                                                                              
                                                                                <div class="col-sm-7">
                                                                                    <input name="bp" id="bp" type="text"
																						class="form-control border-input" placeholder="bp" value=""
																						required>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                  <label   class="col-sm-4 col-form-label">Pulse (in bpm)</label>
                                                                              
                                                                                <div class="col-sm-7">
                                                                                    <input name="pulse" id="pulse"
																							type="text" class="form-control border-input"
																							placeholder="Pulse" value="" required />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                         
                                                                         <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                    <label   class="col-sm-4 col-form-label">SpO2 (in %) </label>
                                                                                
                                                                                <div class="col-sm-7">
                                                                                   <input name="spo2" id="spo2" type="text"
																					class="form-control border-input" placeholder="SpO2" value=""
																					required>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                  <label   class="col-sm-4 col-form-label">BMI (in kg/m2)</label>
                                                                               
                                                                                <div class="col-sm-7">
                                                                                    <input name="bmi" id="bmi" type="text"
																					class="form-control border-input" placeholder="BMI" value=""
																					readonly >
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                    <label   class="col-sm-4 col-form-label">RR (in bpm)</label>
                                                                                
                                                                                <div class="col-sm-7">
                                                                                    <input name="rr" id="rr" type="text"
																						class="form-control border-input" placeholder="RR" value=""
																						required>
                                                                                </div>
                                                                            </div>
                                                                        </div>
										
						
									</div> 
							 </div>
							 
													
									
									
							  <div class="adviceDivMain" id="button6" onclick="showhide(this.id)">
								<div class="titleBg" style="width: 520px; float: left;">
									<span> Minor Surgery Details</span>   
							    </div>
								<input class="buttonPlusMinus" tabindex="1" name="" id="realtedbutton6" value="-" onclick="showhide(this.id)" type="button">
							</div>	


                             <div class="hisDivHide" id="newpost6" style="display:block;">  
                                 <input type="hidden" id="header_id">
									<input type="hidden" class="form-control border-input" value="" id="procedureNameNursingCare" size="55" name="procedureNameNursingCare" />
									<input type="hidden" value="" id="procedureNameNursingId" name="procedureNameNursingId" />
									
										<div class="tablediv">
											<table class="table table-bordered " align="center" cellpadding="0" cellspacing="0">
												<tr>
												<th style="width: 80px;">SI. No</th>
													<th>Minor Surgery Name</th>
													<th>Prescribed By</th>
													<th>Type of Anesthesia</th>
													<th>Minor Surgery Remarks</th>
													<th></th>
													
											 	</tr>
											 	
												<tbody id="gridMinorSurgery">
												
												</tbody>
											</table>
										</div> 
										
										
										<div class="row">		 
														 <div class="col-md-12">
															
															<div class="btn-right-all">
															<button type="button" id="minorSubmit" class="btn btn-primary  " onclick="saveMsDetails();">Submit</button>
                                                             <button type="button" class="btn btn-danger " onclick="Reset();">Reset</button>
																
															
															</div> 
													   </div> 
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

			
<!-- ----------------Accordian  end here---------- -->

<script>
    function showhide(buttonId)
     {
		 if(buttonId=="button1"){
					test('realted'+buttonId,"newpost1");					
		 }else if(buttonId=="button2"){
					test('realted'+buttonId,"newpost2");
		 }else if(buttonId=="button3"){
					test('realted'+buttonId,"newpost3");
		 }else if(buttonId=="button4"){
					test('realted'+buttonId,"newpost4");
		 }else if(buttonId=="button5"){
					test('realted'+buttonId,"newpost5");
		 }else if(buttonId=="button6"){
					test('realted'+buttonId,"newpost6");
		 }else if(buttonId=="button7"){
					test('realted'+buttonId,"newpost7");
		 }else if(buttonId=="button8"){
					test('realted'+buttonId,"newpost8");
		 }else if(buttonId=="button9"){
					test('realted'+buttonId,"newpost9");
		 }else if(buttonId=="button10"){
					test('realted'+buttonId,"newpost10");
		 }else if(buttonId=="button11"){
					test('realted'+buttonId,"newpost11");
		 }else if(buttonId=="button12"){
					test('realted'+buttonId,"newpost12");
		 } 
	 }
    
	function test(buttonId,newpost){
			 var x = document.getElementById(newpost);
				if (x.style.display != "block") {
					x.style.display = "block";
					document.getElementById(buttonId).value="-";
				}else {
					x.style.display = "none";
					document.getElementById(buttonId).value="+";
				}	 
	}
	      
  </script>



</div>


</body>
</html>