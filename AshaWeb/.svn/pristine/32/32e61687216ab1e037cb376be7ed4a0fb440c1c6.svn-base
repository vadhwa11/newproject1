<%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="static com.asha.icgweb.utils.RequestConstants.*"%>
<%@ page import="com.asha.icgweb.utils.HMSUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="../resources/js/jquery.js"></script>
<script type="text/javascript" src="../../resources/js/jquery.min.js"></script>
<script type="text/javascript" src="../../resources/js/jquery.common.js"></script>
<script type="text/javascript" src="../../resources/js/common.js"></script>
<script type="text/javascript" src="../../resources/js/ajax.js"></script>
<script type="text/javascript" src="../../resources/js/pops_global.js"></script>
<script type="text/javascript" src="../../resources/js/pops_menu.js"></script>
<script type="text/javascript" language="javascript"
	src="../resources/js/controls.js?n=1"></script>
<script type="text/javascript" src="../resources/js/hms.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../../resources/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="../../resources/assets/css/animate.min.css" rel="stylesheet" />
<link href="../../resources/assets/css/paper-dashboard.css"
	rel="stylesheet" />
<link href="../../resources/assets/css/demo.css" rel="stylesheet" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300'
	rel='stylesheet' type='text/css'>
<link href="../../resources/assets/css/themify-icons.css"
	rel="stylesheet">
<title>Referred Patient</title>
</head>
<script type="text/javascript" language="javascript">
	
<%String hospitalId = "1";%>
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
    	var admission_date = $j('#admission_date').val();
    	var ward = $j('#ward').val();
    	var admission_no = $j('#admission_no').val();
    	if(admission_date == '' || admission_date == undefined){
    		alert("Admission Date must be selected");
    		return;
    	}else if(ward == '' || ward == undefined){
    		alert("Ward No. must be selected");
    		return;
    	}else if(admission_no == '' || admission_no == undefined){
    		alert("Admission No. must be selected");
    		return;
    	}
		var params = {
				"patient_id": $j('#patient_id').val(),
				"admission_date":$j('#admission_date').val(),
				"disposal": $j('#disposal_combo').find('option:selected').val(),
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
					window.location = "admissionDischargePending";
				},
				
				error: function(data)
				{					
					
					alert("An error has occurred while contacting the server");
					
				}
		}); 
    }
	
</script>
<body>
	<div class="container">

		<form>			

			<div class="form-row">
			<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Service No.</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="service_no" value="" onchange="getPatientList()">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Patient List</label>
					<div class="col-sm-10">
						<select id="patient_list" onchange="getPatientDetail(this.value)"></select>
					</div>
				</div>
			</div><br><br>	
			<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Service No.</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="service_no2" value="">
					</div>
				</div>
				
			<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Patient Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="patient_name" value="">
					</div>
				</div>
				
			<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Age</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="age" value="">
					</div>
				</div>
				
			<div class="form-row" style="margin-top:20px">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Gender</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="gender" value="">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Rank</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="rank" value="">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Mobile No.</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="mobile_no" value="">
					</div>
				</div>

			</div>

			<div class="form-row" style="margin-top:20px">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="emp_name" value="">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Relation</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="relation" value="">
					</div>
				</div>

			</div>

			<div class="form-row" style="margin-top:20px">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Date of Admission</label>
					<div class="col-sm-10">
						<input type="date" id="admission_date" name="admission_date">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Ward</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="ward" value="">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Date of Discharge</label>
					<div class="col-sm-10">
						<input type="date" id="discharge_date" name="fromDate">
					</div>
				</div>

			</div>

			<div class="form-row" style="margin-top:20px">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Disposal</label>
					<div class="col-sm-10">
						<select id="disposal_combo"></select>
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Remark</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="remark" value="">
					</div>
				</div>

			</div>

			<div class="form-row" style="margin-top:20px">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Admission No.</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="admission_no" value="" required>
					</div>
				</div>
			</div>
			<div class="form-row" style="margin-top: 20px">								
				<input type="hidden" id="patient_id" value="">						
			</div>


			<div class="form-row" style="margin-top:20px">
				<div class="form-group col-md-8">
					<button type="button" class="btn btn-success"
						style="margin-top: 30px" onclick="saveAdmissionDetail()">Submit</button>
					<button type="button" class="btn btn-success"
						style="margin-top: 30px" onclick="resetDetail()">Reset</button>
					<button type="button" class="btn btn-success"
						style="margin-top: 30px" onclick="close()">Close</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>