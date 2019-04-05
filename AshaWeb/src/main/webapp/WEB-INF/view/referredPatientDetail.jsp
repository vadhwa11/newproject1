<%@page import="org.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
</head>


<script type="text/javascript" language="javascript">
var $j = jQuery.noConflict();
var rowcount=0;
$j(document).ready(function()
		{
				var data = ${data};
				var i = 0;
				var j = 0;
				for(item in data){
					i++;
					
					if(i == 1){						
						for(subitem in data[item]){
							
							$j('#service_no').val(data[item][subitem].service_no);
							$j('#patient_name').val(data[item][subitem].patient_name);
							$j('#age').val(data[item][subitem].age);
							$j('#gender').val(data[item][subitem].gender);
							$j('#rank').val(data[item][subitem].rank);
							$j('#mobile_no').val(data[item][subitem].mobile_no);
							$j('#header_id').val(data[item][subitem].id);
							$j('#patient_id').val(data[item][subitem].patient_id);
							
						}
					}else if(i == 2){						
						var html = '';						
						for(subitem in data[item]){
							var id = data[item][subitem].id;
							var disease = data[item][subitem].notifiable_desease;
							var mark_mb = data[item][subitem].mark_mb;
							var mark_admitted = data[item][subitem].mark_admitted;
							var close = data[item][subitem].close;
							var final_note = data[item][subitem].final_note;
							if(disease == 'N'){
								var disease_checkbox = '<input type="checkbox" id="notifiable_disease'+rowcount+'" value="N">';
							}else if(disease == 'Y'){
								var disease_checkbox = '<input type="checkbox" id="notifiable_disease'+rowcount+'" value="Y" checked>';
							}
							
							if(mark_mb == 'N'){
								var mark_mb_checkbox = '<input type="checkbox" id="mark_mb'+rowcount+'" value="N">';
							}else if(mark_mb == 'Y'){
								var mark_mb_checkbox = '<input type="checkbox" id="mark_mb'+rowcount+'" value="Y" checked>';
							}
							
							if(mark_admitted == 'N'){
								var mark_admitted_checkbox = '<input type="checkbox" id="mark_admitted'+rowcount+'" value="N">';
							}else if(mark_admitted == 'Y'){
								var mark_admitted_checkbox = '<input type="checkbox" id="mark_admitted'+rowcount+'" value="Y" checked>';
							}
							
							if(close == 'N'){
								var close_checkbox = '<input type="checkbox" id="close'+rowcount+'" value="N">';
							}else if(close == 'Y'){
								var close_checkbox = '<input type="checkbox" id="close'+rowcount+'" value="Y" checked>';
							}
							if(final_note == undefined){
								final_note ='';
							}
							
							html += '<tr><input type="hidden" id="rowid'+rowcount+'" value="'+id+'"><td><div><input class="form-control" id="referral_date" type="text" value="'+data[item][subitem].referral_date+'" readonly></div></td><td><div><input class="form-control" id="hospital_name" type="text" value="'+data[item][subitem].hospital_name+'" readonly></div></td><td><div><input class="form-control" id="speciality" type="text" value="'+data[item][subitem].department_name+'" readonly></div></td><td><div><input class="form-control" id="diagnosis" type="text" value="'+data[item][subitem].diagnosis_name+'" readonly></div></td><td>'+disease_checkbox+'</td><td><div><input class="form-control" id="instructions'+rowcount+'" type="text" value="'+data[item][subitem].instructions+'" readonly></div></td><td>'+mark_mb_checkbox+'</td><td>'+mark_admitted_checkbox+'</td><td>'+close_checkbox+'</td><td><div><input class="form-control" id="final_note'+rowcount+'" type="text" value="'+final_note+'"><input class="form-control" id="final_hidden'+rowcount+'" type="hidden" value="'+final_note+'"></div></td></tr>';
							rowcount++;
						}
						$j('#tableId').append(html);
						
					}				
				
				}
			
		});
		

	function saveReferralDetail() {
		var form_validation = validateReferralDetail();
		if(form_validation == false){
			alert("Please Select one option atleast");
			return;
		} 
		var row_array = [];
		for (var i = 0; i < rowcount; i++) {
			
			var header_id = $j('#header_id').val();
			var id = $j('#rowid'+i+'').val();
			var final_note = $j('#final_note'+i+'').val();
			var notifiable_disease = '';
			var mark_mb = '';
			var mark_admitted = '';
			var close = '';
			var instructions = '';
			var notifiable_check = document.getElementById('notifiable_disease'+ i).checked;
			var mb_check = document.getElementById('mark_mb' + i).checked;
			var admitted_check = document.getElementById('mark_admitted' + i).checked;
			var close_check = document.getElementById('close' + i).checked;
			if (notifiable_check == true) {
				notifiable_disease = 'Y';
			} else {
				notifiable_disease = 'N';
			}

			if (mb_check == true) {
				mark_mb = 'Y';
			} else {
				mark_mb = 'N';
			}
			if (admitted_check == true) {
				mark_admitted = 'Y';
			} else {
				mark_admitted = 'N';
			}
			if (close_check == true) {				
				close = 'Y';
				if(final_note == '' || final_note == 'undefined'){
					return alert("Final Note is Required");
				}
			} else {
				close = 'N';
			}
			
			var params = {
					'id':id,
					'final_note':final_note,
					'notifiable_disease':notifiable_disease,
					'mark_mb':mark_mb,
					'mark_admitted':mark_admitted,
					'close':close					
			}
			row_array.push(params);
			
		}
		var patient_id = $j('#patient_id').val();
		var data = {
				"detail_list":row_array,
				"header_id": header_id,
				"patient_id": patient_id
		}
		
		 $j.ajax({
				type:"POST",
				contentType : "application/json",
				url: 'updateReferralDetail',
				data : JSON.stringify(data),
				dataType: "json",			
				cache: false,
				success: function(msg)
				{  
					 alert(msg.msg);  
					 window.location.href="referralWaitingList";
					
				},
				
				error: function(msg)
				{					
					
					alert("An error has occurred while contacting the server");
					
				}
		});
	}
	
	function validateReferralDetail(){
		var flag = true;
		for(var i=0;i<rowcount;i++){
			var row1before = '';
			//var notifiable_before = document.getElementById('notifiable_disease'+i+'').value;
			var mb_before = document.getElementById('mark_mb'+i+'').value;
			var admitted_before = document.getElementById('mark_admitted'+i+'').value;
			var close_before = document.getElementById('close'+i+'').value;
			//var hidden_note = $j('#final_hidden'+i+'').val();
			var old_row_data = mb_before+admitted_before+close_before;
			
			//var notifiable_after = document.getElementById('notifiable_disease'+i+'').checked;
			var mb_after = document.getElementById('mark_mb'+i+'').checked;
			var admitted_after = document.getElementById('mark_admitted'+i+'').checked;
			var close_after = document.getElementById('close'+i+'').checked;
			
			if(mb_after == true){
				mb_after = 'Y';
			}else{
				mb_after = 'N';
			}
			if(admitted_after == true){
				admitted_after = 'Y';
			}else{
				admitted_after = 'N'
			}
			if(close_after == true){
				close_after = 'Y';
			}else{
				close_after = 'N';
			}
			var new_row_data = mb_after+admitted_after+close_after;
			if(old_row_data === new_row_data){
				flag =  false;
				break;
			}
			
		}
		return flag;
	}
	
function backToReferralWaitingList(){
	window.location.href="referralWaitingList";
}
</script>
<body>
	<div class="container">
	
		<form>
			<div class="form-row">
				<div class="form-group col-md-8">
					<h3><label for="inputEmail3" class="col-md-10 col-form-label">Patient Detail</label></h3>
				</div>
			</div>
			<div class="form-row">

				<div class="form-group col-md-4">
					<label for="inputEmail3" class="col-sm-4 col-form-label">Service No.</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="service_no" readonly>
						
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="inputEmail3" class="col-sm-4 col-form-label">Patient</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="patient_name" readonly>
					</div>
				</div>

				<div class="form-group col-md-4">
					<label for="age" class="col-sm-4 col-form-label">Age</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="age" readonly>
					</div>
				</div>
				
				</div>
				
				<div class="form-row">
				<div class="form-group col-md-4">
					<label for="gender" class="col-sm-4 col-form-label">Gender</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="gender" readonly>						
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="gender" class="col-sm-4 col-form-label">Rank</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="rank" readonly>
						
					</div>
				</div>
				<div class="form-group col-md-4">
					<label for="gender" class="col-sm-4 col-form-label">Mobile No.</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="mobile_no" readonly>
					</div>
				</div>
				
				<input type="hidden" class="form-control" id="header_id" value="">
				<input type="hidden" class="form-control" id="patient_id" value="">
				
				<input type="hidden" id="header_id">
			</div>
			<div class="form-row">
				<div class="form-group col-md-8">
					<h3><label for="inputEmail3" class="col-md-10 col-form-label">Referral Detail</label></h3>
				</div>
			</div>
			<div class="form-row">

				<!-- <table class="table table-bordered table-responsive" id="tableId"> -->
				<table class="table table-bordered" id="tableId">
					<thead>
						<tr>
							<th>Referral Date</th>
							<th>Hospital Name</th>
							<th>Speciality</th>
							<th>Diagnosis</th>
							<th>Mark as Notifiable Desease</th>
							<th>Instruction</th>
							<th>Mark MB</th>
							<th>Mark as Admitted</th>
							<th>Close</th>
							<th>Final Note</th>
						</tr>
					</thead>
				</table>				
			</div>
			<div class="form-row">
				
				<button type="button" class="btn btn-success" style="margin-top: 30px" onclick="saveReferralDetail()">Submit</button>
				<button type="button" class="btn btn-success" style="margin-top: 30px" onclick="backToReferralWaitingList()">Back</button>
			</div>
		</form>
	</div>

</body>
</html>