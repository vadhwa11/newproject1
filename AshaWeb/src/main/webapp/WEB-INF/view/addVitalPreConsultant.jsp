<%@page import="java.util.HashMap"%>
    <%@page import="java.util.Map"%>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

            <%@include file="..//view/leftMenu.jsp" %>
             <%@include file="..//view/commonJavaScript.jsp" %>

                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Add Vitals Record</title>
</head>




 <script type="text/javascript">
 function saveAddvitalDetails() {
			
			var pathname = window.location.pathname;
			var accessGroup = pathname.split("/")[1];

			var url = window.location.protocol + "//"
					+ window.location.host + "/" + accessGroup
					+ "/v0.1/opd/saveVitailsPatientdetails";
			
			var dataJSON={
	  			     
	      			 'visitId':$('#visitId').val(),
	      			'patientId':$('#patientId').val(), 
	      			'idealWeight':$('#ideal_weight').val(),
	      			'height':$('#height').val(),
	      			'weight':$('#weight').val(),
	      			'varation':$('#variant_in_weight').val(),
	      			'temperature':$('#tempature').val(),
	      			'bp':$('#bp').val(),
	      			'pulse':$('#pulse').val(),
	      			'spo2':$('#spo2').val(),
	      			'bmi':$('#bmi').val(),
	      			'rr':$('#rr').val(),
	      		}

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url,
				data: JSON.stringify(dataJSON),
				dataType : 'json',
				timeout : 100000,
				success : function(msg)
				{
				  if (msg.status == 1)
				   	   {
		        		   alert("Vitals Details Insert successfully");
		        		   //show_msg({'msg':' Vitals Details Insert successfully ','status':'1'});
		        		   location.reload();
		        		   window.location.href = "preOpdWaitingList"
		        	   }
		        		
		        	  else
		        	  {
		        		  alert(msg.status)
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

 }
		
		
		
</script>  
<script type="text/javascript">
            $(document).ready(function() {
                /* if (typeof element !== "undefined" && element.value == '') {
                } */
                var data = ${
                    data
                };
                if (data.data[0].visitId != null) {
                    document.getElementById('visitId').value = data.data[0].visitId;
                }
               
                if (data.data[0].patientId != null) {
                    document.getElementById('patientId').value = data.data[0].patientId;
                }
                if (data.data[0].serviceNo != null) {
                    document.getElementById('service').value = data.data[0].serviceNo;
                }
                if (data.data[0].patientName != null) {
                    document.getElementById('patient_name').value = data.data[0].patientName;
                }
                if (data.data[0].relation != null) {
                    document.getElementById('relation').value = data.data[0].relation;
                }
                if (data.data[0].gender != null) {
                    document.getElementById('gender').value = data.data[0].gender;
                }
                if (data.data[0].age != null) {
                    document.getElementById('age').value = data.data[0].age;
                }
            });
</script>




<!-- <script>


/* $(window)
		.bind(
				"load",
				function() {
					function getdata(){
						alert("localStorage.datas: "+ localStorage.datas);
						document.getElementById('service').innerHTML.value =localStorage.datas;
					}
				}); */
	'""'
				var $j= jQuery.noConflict();
				$j(document).ready(function(){
					alert("localStorage.visitId: "+ localStorage.visitId);
					document.getElementById('visitId').value =localStorage.visitId;
					document.getElementById('patiendID').value =localStorage.patientId;
					document.getElementById('service').value =localStorage.serviceNo;
					document.getElementById('patient_name').value =localStorage.patientName;
					document.getElementById('relation').value =localStorage.relation;
					document.getElementById('gender').value =localStorage.gender;
					document.getElementById('age').value =localStorage.age;
					//document.getElementById('service').value =localStorage.datas;
				});
</script> -->

<!-- <script type="text/javascript">
   $(document).ready(function () {
       $('#height').keyup(function () { alert('test'); });
   });
</script> -->



<script type="text/javascript">

 $(document).ready(function () {
		 $('#height').change(
		function() {
			var pathname = window.location.pathname;
			var accessGroup = pathname.split("/")[1];

			var url = window.location.protocol + "//"
					+ window.location.host + "/" + accessGroup
					+ "/v0.1/opd/getIdealWeight";
    
			var dataJSON={
					 
					  'height':$('#height').val(),
		      		  'age':$('#age').val(),
					
					
			}
			
			
           
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url,
				data : JSON.stringify(dataJSON),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					
				   	   	   if (data.status == 1) {
					//var data = data;
					//alert("value is "+data.data[0].idealWeight);
					$('#ideal_weight').val(data.data[0].idealWeight);
	               
					
				    }
					
					

				},
				error : function(e) {

					console.log("ERROR: ", e);

				},
				done : function(e) {
					console.log("DONE");
					alert("success");
					var datas = e.data;
				}
			});
		 });
		});
	
 function cancelForm(){
	 alert("hello")
 	   window.close();
} 
		
</script>

<script type="text/javascript">

 $(document).ready(function () {
		 $('#weight').change(
		function() {
			
			 var a=document.getElementById("ideal_weight").value;
		     var b=document.getElementById("weight").value;
			
			if(a>b)
			{
				var sub=a-b;
				var cal=sub*100/a
				var n = cal.toFixed(2);
				$('#variant_in_weight').val("-"+n);
				
			}
			else
				{
				var eadd=b-a;
				var cal1=eadd*100/b
				var n1 = cal1.toFixed(2);
				$('#variant_in_weight').val("+"+n1);
				}
			
			
	});
 });

 </script>
<body>
                         <div class="content-page">                               
                                <div class="">
                                    <div class="container-fluid">
                                    <div class="internal_Htext">OPD Pre- Consultation</div>
                                       <!--  <div class="row">
                                            <div class="col-12">
                                                <div class="page-title-box">
                                                    
                                                    <ol class="breadcrumb float-right">
                                                        <li class="breadcrumb-item active"><a href="#">Home</a></li>
                                                        <li class="breadcrumb-item  active"><a href="#">OPD</a></li>
                                                        <li class="breadcrumb-item active">Command Master</li>
                                                    </ol>

                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>
                                        </div> -->
                                    
                                        
                                          <div class="row">
                                            <div class="col-12">
                                                <div class="card">
                                                    <div class="card-body"> 
                                                        
 
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <form>
                                                                    <div class="row">
                                                                    
                                                                    
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                    <label   class="col-sm-6 col-form-label">Service No. </label>
                                                                               
                                                                                <div class="col-sm-6">
                                                                                    <input name="service_no."
																						id="service" type="text" class="form-control border-input"
																						placeholder="Service No." value="" required />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                    <label   class="col-sm-6 col-form-label">Patient Name</label>
                                                                              
                                                                                <div class="col-sm-6">
                                                                                     <input name="patients_name"
																						id="patient_name" type="text"
																						class="form-control border-input" placeholder="Patients Name"
																						value="" required />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                  <label   class="col-sm-6 col-form-label">Relation</label>
                                                                           
                                                                                <div class="col-sm-6">
                                                                                   <input name="relation" id="relation"
																						type="text" class="form-control border-input"
																						placeholder="Relation" required />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                   <label   class="col-sm-6 col-form-label">Gender </label>
                                                                              
                                                                                <div class="col-sm-6">
                                                                                   <input name="gender" id="gender"
																						type="text" class="form-control border-input"
																						placeholder="gender" value="" required />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                              
                                                                                  <label   class="col-sm-6 col-form-label">Age</label>
                                                                             
                                                                                <div class="col-sm-6">
                                                                                    <input name="age" id="age" type="text"
											class="form-control border-input" placeholder="Age" value=""
											required />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        </div>
                                                                        <br>
                                                                        
                                                                        <div class="row">
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                             
                                                                                  <label   class="col-sm-6 col-form-label">Height</label>
                                                                               
                                                                                <div class="col-sm-6">
                                                                                   <input name="height" id="height"
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
											id="ideal_weight" type="text"
											class="form-control border-input" placeholder="Ideal Weight" />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                    <label   class="col-sm-6 col-form-label">Weight</label>
                                                                             
                                                                                <div class="col-sm-6">
                                                                                    <input name="Weight" id="weight"
											type="text" class="form-control border-input"
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
											placeholder="Variation in Weight" value="" required />
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
											required>
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
                                                                        
                                                                        
                                                                        
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                                
                                                                                
                                                                                <div class="col-sm-6">
                                                                                    <input type="hidden" id="patientId" name="PatientID12" value="">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                               
                                                                                                                                                             
                                                                                <div class="col-sm-6">
                                                                                   <input type="hidden" id="visitId" name="VisitID" value="">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        
                                                                         
                                                                        
                                                                        
                                                                        
                                                                           <div class="col-md-4">
                                                                            <div class="form-group row">
                                                                                
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            <div class="form-group row">
                                                                                 
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-1">
                                                                            <div class="form-group row">
                                                                            
                                                                                  <button type="button" class="btn btn-primary"
																				  onclick="saveAddvitalDetails()">Submit</button>
																			 
                                                                                
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        <div class="col-md-1">
                                                                            <div class="form-group row">
                                                                           
																			<button type="button" class="btn btn-primary"
																				  onclick="history.back()">Close</button>
                                                                                
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
                               </div>
 
</body>
</html>