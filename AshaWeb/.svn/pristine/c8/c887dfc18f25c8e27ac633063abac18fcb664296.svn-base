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
<link href="../../resources/assets/css/bootstrap.min.css"	rel="stylesheet" />
<link href="../../resources/assets/css/animate.min.css" rel="stylesheet" />
<link	href="../../resources/assets/css/paper-dashboard.css"	rel="stylesheet" />
<link	href="../../resources/assets/css/demo.css"	rel="stylesheet" />
<link	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
<link href="../../resources/assets/css/themify-icons.css" rel="stylesheet">
<title>Referred Patient</title>
</head>
<script type="text/javascript" language="javascript">
<%	String hospitalId = "1";
%>

var $j = jQuery.noConflict();

$j(document).ready(function()
		{
		var data = ${data};
		$j('#patient_name').val(data.patient_detail.patientName);
		$j('#age').val(data.patient_detail.age);
		$j('#gender').val(data.patient_detail.gender);
		//$j('#diagnosis').val(data.patient_detail.diagnosis);
		
		var html= '<tr>';
		for(var i=0;i<data.nursingDetailList.length;i++){
			html += '<tr><input type="hidden" id="rowid" value="'+data.nursingDetailList[i].id+'">';
			html += '<td><div><input class="form-control" id="procedure_name" type="text" value="'+data.nursingDetailList[i].procedureName+'" readonly></div></td>';
			html += '<td><div><input class="form-control" id="frequency" type="text" value="'+data.nursingDetailList[i].frequency+'" readonly></div></td>';
			html += '<td><div><input class="form-control" id="no_of_days" type="text" value="'+data.nursingDetailList[i].noOfDays+'" readonly></div></td>';
			html += '<td><div><input class="form-control" id="final_status" type="text" value="'+data.nursingDetailList[i].finalStatus+'" readonly></div></td>';
			html += '<td><div><button type="button" class="btn btn-success"	style="margin-top: 30px" onclick="saveAdmissionDetail()">View And Submit</button></div></td>';
		}
		html += '</tr>'
			$j('#tblListofObesity').append(html);
		});
</script>		
		
<body>
	<div class="container">
		<form>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Patient Name</label>
					<div class="col-sm-10">
						<input type="text" id="patient_name" name="patient_name">
					</div>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Age</label>
					<div class="col-sm-10">
						<input type="text" id="age" name="age">
					</div>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Gender</label>
					<div class="col-sm-10">
						<input type="text" id="gender" name="gender">
					</div>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="staticEmail" class="col-sm-2 col-form-label">Diagnosis</label>
					<div class="col-sm-10">
						<input type="text" id="diagnosis" name="diagnosis">
					</div>
				</div>
			</div>
			<div class="form-row">
				<div class="tablediv">
				<table class="table table-bordered ">
					<tbody id="tblListofObesity">

					</tbody>
				</table>
			</div>
		</div>
		</form>
		</div>
</body>
</html>