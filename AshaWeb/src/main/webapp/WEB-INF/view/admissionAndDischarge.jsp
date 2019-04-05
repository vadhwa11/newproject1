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
		var data = ${data}; 
			$j('#service_no').val(data.referralList[0].service_no);
			$j('#patient_name').val(data.referralList[0].patient_name);
			$j('#age').val(data.referralList[0].age);
			$j('#gender').val(data.referralList[0].gender);
			$j('#rank').val(data.referralList[0].rank);
			$j('#mobile_no').val(data.referralList[0].mobile_no);
			$j('#referral_date').val(data.referralList[0].referral_date);
			$j('#hospital_name').val(data.referralList[0].hospital_name);
			$j('#speciality').val(data.referralList[0].speciality);
			$j('#diagnosis').val(data.referralList[0].diagnosis);
			$j('#instructions').val(data.referralList[0].instructions);
			$j('#header_id').val(data.referralList[0].header_id);
			$j('#patient_id').val(data.referralList[0].patient_id);
			$j('#admission_id').val(data.referralList[0].admission_id);
			var date = data.referralList[0].admission_date.split("-");
			var date_of_admission = date[2]+"-"+date[1]+"-"+date[0];
			$j('#admission_date').val(date_of_admission);
			$j('#ward').val(data.referralList[0].ward);
			$j('#no_of_days').val(data.referralList[0].no_of_days);
			$j('#remark').val(data.referralList[0].remarks);
			$j('#admission_no').val(data.referralList[0].admission_no);
			$j('#disposal_combo').val(data.referralList[0].disposal);
	var comboList = '';
	for(var j=0;j<data.disposalList.length;j++){
		comboList += '<option value="'+data.disposalList[j].id+'">'+data.disposalList[j].disposal_name+'</option>';
	}
	//alert("comboList "+comboList);
	$j('#disposal_combo').append(comboList);

	});
	
	function saveAdmissionDetail(){
		var admission_date = $j('#admission_date').val();
		var ward = $j('#ward').val();
		var no_of_days = $j('#no_of_days').val();
		var admission_no = $j('#admission_id').val();
		if(admission_date == '' || admission_date == undefined){
			alert("Admission date must be selected");
			return;
		}else if(ward == '' || ward == undefined){
			alert("Ward No. must be entered");
			return;
		}else if(no_of_days == '' || no_of_days == undefined){
			alert("No. of Days must be entered");
			return;
		}else if(admission_no == '' || admission_no == undefined){
			alert("Admission No. must be entered");
			return;
		}
		
	 var params = {
				"header_id": $j('#header_id').val(),
				"patient_id": $j('#patient_id').val(),
				"admission_date": $j('#admission_date').val(),
				"ward": $j('#ward').val(),
				"disposal":$j('#disposal_combo').find('option:selected').val(),
				"no_of_days":$j('#no_of_days').val(),
				"remark":$j('#remark').val(),
				"admission_no": $j('#admission_no').val(),
				"admission_id" : $j('#admission_id').val(),
				"discharge_date": $j('#discharge_date').val(),
				"hospital_id":<%= hospitalId %>
		}
	 
	 $j.ajax({
			type:"POST",
			contentType : "application/json",
			url: 'savePatientAdmission',
			data : JSON.stringify(params),
			dataType: "json",			
			cache: false,
			success: function(data)
			{  
				
				var msg = data.msg;
				alert(msg);
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
						<input type="text" class="form-control-plaintext" id="service_no" value="">
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

			</div><br><br>	
			
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
					<label for="staticEmail" class="col-sm-2 col-form-label">Referral Date</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="referral_date" value="">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Hospital Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="hospital_name" value="">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Speciality</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="speciality" value="">
					</div>
				</div>

			</div>

			<div class="form-row" style="margin-top:30px">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Diagnosis</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="diagnosis" value="">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Instructions</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="instructions" value="">
					</div>
				</div>

			</div>

			<div class="form-row" style="margin-top:20px">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Date of Admission</label>
					<div class="col-sm-10">
						<input type="date" id="admission_date" name="fromDate">
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
					<label for="staticEmail" class="col-sm-2 col-form-label">No. of Days</label>
					<div class="col-sm-10">
						<input type="text" class="form-control-plaintext" id="no_of_days" value="">
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
						<input type="text" class="form-control-plaintext" id="admission_no" value="">
					</div>
				</div>
			</div>
			<div class="form-row" style="margin-top: 20px">								
				<input type="hidden" id="header_id" value="">
				<input type="hidden" id="patient_id" value="">	
				<input type="hidden" id="admission_id" value="">					
			</div>


			<div class="form-row" style="margin-top:20px">
				<div class="form-group col-md-8">
					<button type="button" class="btn btn-success"
						style="margin-top: 30px" onclick="saveAdmissionDetail()">Submit</button>
					<button type="button" class="btn btn-success"
						style="margin-top: 30px" onclick="close()">Close</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>