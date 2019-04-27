<%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="..//view/leftMenu.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="..//view/commonJavaScript.jsp"%>
<title>Doctor Roster</title>

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
		makeCombo();

	});

	function makeCombo() {

		var params = {
			"hospitalID" : "<%= hospitalId %>"
		}

		$j.ajax({
					type : "POST",
					contentType : "application/json",
					url : 'getDepartmentList',
					data : JSON.stringify(params),
					dataType : "json",
					cache : false,
					success : function(msg) {
						if (msg.status == '1') {

							var comboval = "<option value=\"\">Select</option>";
							for (var i = 0; i < msg.departmentList.length; i++) {

								comboval += '<option value=' + msg.departmentList[i].departmentId + '>'
										+ msg.departmentList[i].departmentname
										+ '</option>';

							}
							$j('#com').append(comboval);

						}

					},

					error : function(msg) {

						alert("An error has occurred while contacting the server");

					}
				});
	}

	var checkboxLength;

	function getDoctorRoaster() {
		var fromDate = $j('#from_date').val();
		var deptName = $j('#com').find('option:selected').text();
		var deptID = $j('#com').find('option:selected').val();
		if(deptID == '' || deptID == undefined){
			alert("Please Select Department");
			return;
		}else if(fromDate == '' || fromDate == undefined){
			alert("Please Select Date");
			return;
		}
		
		var params = {
			"fromDate" : fromDate,
			"departmentID" : deptID,
			"hospital_id": "<%= hospitalId %>"
		}

		$j.ajax({
					type : "POST",
					contentType : "application/json",
					url : 'getDoctorRoasterDetail',
					data : JSON.stringify(params),
					dataType : "json",
					cache : false,
					success : function(data) {
						var jspdate = nextDate(fromDate);
						$j('#roastertable').empty();
						var currentdate = makeDateinHyphenformat(new Date());
						var fdate = new Date(fromDate);
						fdate.setHours(0, 0, 0, 0);
						var today = new Date();
						today.setHours(0, 0, 0, 0);
						//alert((new Date(fdate) - new Date(today)) == 0);

						var tablebody = '<tr id="row1"><th id="">Department</th>';
						for (var m = 0; m < jspdate.length; m++) {
							tablebody += '<th id="th' + m + '">' + jspdate[m]
									+ '</th>';
						}
						tablebody += '</tr>';
						var checkboxorder = [];
						var labelId = [];
						var jsondate = [];
						var roastervalue = [];
						var idlist = [];
						var idandType = [];
						checkboxLength = data.checkboxConfigurationList.length;
						for (var a = 0; a < checkboxLength; a++) {
							checkboxorder[a] = data.checkboxConfigurationList[a].appintment_Type_Name;
							labelId[a] = data.checkboxConfigurationList[a].id;
							idandType[a] = checkboxorder[a] + '@' + labelId[a];
							//alert("idandType[a] "+idandType[a]);
						}
						if (data.status == "1") {
							for (var j = 0; j < data.doctorRoasterList.length; j++) {

								//jsondate[j] = makeDate(data.doctorRoasterDetail[j].roasterDate);//2018-11-29
								jsondate[j] = dateFormat(data.doctorRoasterList[j].roasterDate);
								roastervalue[j] = data.doctorRoasterList[j].roasterValue;
								idlist[j] = data.doctorRoasterList[j].id;

							}
							tablebody += '<tr id="row2"><td id="">' + deptName
									+ '</td>';
							for (var k = 0; k < jspdate.length; k++) {
								var checkbox = '';
								//tablebody += '<th>';
								var flag = true;
								for (var l = 0; l < jsondate.length; l++) {
									//checkbox = '';

									if (jspdate[k] === jsondate[l]) {

										if (new Date(compareDate(jsondate[l])) < new Date(
												compareDate(currentdate))) {
											tablebody += '<td bgcolor="#00FF00"><input type="hidden" id="td' + k + '" value="' + idlist[l] + '">';
											var roastervalueindexwise = roastervalue[l];
											var roaster_value = roastervalue[l]
													.split(","); //{y@1,n@2,y@3}
											for (var p = 0; p < checkboxLength; p++) {
												//var checkBoxIdandValue = idandType[p].split("@");
												var checkbox_name = '';
												var checkbox_id = '';
												var roaster_ordered_value = roaster_value[p]
														.split("@");
												for (var i = 0; i < idandType.length; i++) {
													var checkBoxIdandValue = idandType[i]
															.split("@");
													if (checkBoxIdandValue[1] == roaster_ordered_value[1]) {
														checkbox_name = checkBoxIdandValue[0];
														checkbox_id = checkBoxIdandValue[1];
													}
												}

												if (roaster_ordered_value[0] == 'y') {

													checkbox += '<input type="checkbox" id="td' + k + 'cb' + k + '' + p + '" checked disabled><span  id = "td' + k + 'cb' + k + '' + p + 'lb">'
															+ checkbox_name
															+ '</span><input type="hidden" id = "td' + k + 'cb' + k + '' + p + 'lbid" value="' + checkbox_id + '"><br>';
												} else {

													checkbox += '<input type="checkbox" id="td' + k + 'cb' + k + '' + p + '" disabled><span  id = "td' + k + 'cb' + k + '' + p + 'lb">'
															+ checkbox_name
															+ '</span><input type="hidden" id = "td' + k + 'cb' + k + '' + p + 'lbid" value="' + checkbox_id + '"><br>';
												}

											}
											flag = false;
											break;
										} else {
											tablebody += '<td bgcolor="#00FF00"><input type="hidden" id="td' + k + '" value="' + idlist[l] + '">';
											var roastervalueindexwise = roastervalue[l];
											var roaster_value = roastervalue[l]
													.split(","); //{y@1,n@2,y@3}

											for (var p = 0; p < checkboxLength; p++) {

												//alert(roastervalueindexwise[p]);
												var checkbox_name = '';
												var checkbox_id = '';
												var roaster_ordered_value = roaster_value[p]
														.split("@");
												for (var i = 0; i < idandType.length; i++) {
													var checkBoxIdandValue = idandType[i]
															.split("@");
													if (checkBoxIdandValue[1] == roaster_ordered_value[1]) {
														checkbox_name = checkBoxIdandValue[0];
														checkbox_id = checkBoxIdandValue[1];
													}
												}

												if (roaster_ordered_value[0] == 'y') {

													checkbox += '<input type="checkbox" id="td' + k + 'cb' + k + '' + p + '" checked><span  id = "td' + k + 'cb' + k + '' + p + 'lb">'
															+ checkbox_name
															+ '</span><input type="hidden" id = "td' + k + 'cb' + k + '' + p + 'lbid" value="' + checkbox_id + '"><br>';
												} else {

													checkbox += '<input type="checkbox" id="td' + k + 'cb' + k + '' + p + '"><span  id = "td' + k + 'cb' + k + '' + p + 'lb">'
															+ checkbox_name
															+ '</span><input type="hidden" id = "td' + k + 'cb' + k + '' + p + 'lbid" value="' + checkbox_id + '"><br>';
												}
												//checkbox += '<input type="checkbox">';
											}
											flag = false;
											break;
										}

									}
								}
								if (flag) {

									/* var roaster_value = [];
									for(var m=0;m<roastervalue.length;m++){								
										roaster_value = roastervalue[m].split(",");  //{y@1,n@2,y@3}
									} */
									if (new Date(compareDate(jspdate[k])) < new Date(
											compareDate(currentdate))) {
										tablebody += '<td id="td' + k + '" value="0"><input type="hidden" id="td' + k + '" value="0">';
										//var roastervalueofzeroindex = roastervalue[0];									

										for (var p = 0; p < checkboxLength; p++) {

											var checkBoxIdandValue = idandType[p]
													.split("@");
											//alert("c "+checkBoxIdandValue[0]);
											var checkbox_name = '';
											var checkbox_id = '';
											//var checkBoxIdandValue = idandType[p].split("@");
											checkbox_name = checkBoxIdandValue[0];
											checkbox_id = checkBoxIdandValue[1];
											checkbox += '<input type="checkbox" checked id="td' + k + 'cb' + k + '' + p + '" disabled><span  id = "td' + k + 'cb' + k + '' + p + 'lb">'
													+ checkbox_name
													+ '</span><input type="hidden" id = "td' + k + 'cb' + k + '' + p + 'lbid" value="' + checkbox_id + '"><br>';
										}
									} else {
										tablebody += '<td id="td' + k + '" value="0"><input type="hidden" id="td' + k + '" value="0">';
										//var roastervalueofzeroindex = roastervalue[0];
										for (var p = 0; p < checkboxLength; p++) {

											var checkBoxIdandValue = idandType[p]
													.split("@");
											var checkbox_name = '';
											var checkbox_id = '';
											//var checkBoxIdandValue = idandType[i].split("@");
											checkbox_name = checkBoxIdandValue[0];
											checkbox_id = checkBoxIdandValue[1];
											checkbox += '<input type="checkbox" checked id="td' + k + 'cb' + k + '' + p + '"><span  id = "td' + k + 'cb' + k + '' + p + 'lb">'
													+ checkbox_name
													+ '</span><input type="hidden" id = "td' + k + 'cb' + k + '' + p + 'lbid" value="' + checkbox_id + '"><br>';
										}
									}

								}
								tablebody += checkbox + '</td>';
							}
							tablebody += '</tr>';
							$j('#roastertable').append(tablebody);
							var subbutton = '<button type="button" class="btn btn-primary" style="margin-top: 30px" onclick="saveDetail()">Submit</button>';
							$j('#btn_submit').empty();
							$j('#btn_submit').append(subbutton);
							tablebody = '';
						} else {

							tablebody += '<tr><td>' + deptName + '</td>';
							for (var x = 0; x < jspdate.length; x++) {
								if (new Date(compareDate(jspdate[x])) < new Date(
										compareDate(currentdate))) {
									tablebody += '<td>';
									for (var y = 0; y < checkboxLength; y++) {
										//tablebody += '<td><input type="checkbox" id = "td'+x+'cb'+x+'0" checked disabled><input type="checkbox" id = "td'+x+'cb'+x+'1" checked disabled></td><input type="hidden" id="td'+x+'" value="0">';
										var checkBoxIdandValue = idandType[y]
												.split("@");

										tablebody += '<input type="checkbox" id = "td' + x + 'cb' + x + '' + y + '" checked disabled><span  id = "td' + x + 'cb' + x + '' + y + 'lb">'
												+ checkBoxIdandValue[0]
												+ '</span><input type="hidden" id = "td' + x + 'cb' + x + '' + y + 'lbid" value="' + checkBoxIdandValue[1] + '"><br>';
									}
									tablebody += '<input type="hidden" id="td' + x + '" value="0"></td>';

								} else {
									//tablebody += '<td><input type="checkbox" id = "td'+x+'cb'+x+'0" checked><input type="checkbox" id = "td'+x+'cb'+x+'1" checked></td><input type="hidden" id="td'+x+'" value="0">';
									tablebody += '<td>';
									for (var z = 0; z < checkboxLength; z++) {
										//tablebody += '<td><input type="checkbox" id = "td'+x+'cb'+x+'0" checked disabled><input type="checkbox" id = "td'+x+'cb'+x+'1" checked disabled></td><input type="hidden" id="td'+x+'" value="0">';
										var checkBoxIdandValue = idandType[z]
												.split("@");

										tablebody += '<input type="checkbox" id = "td' + x + 'cb' + x + '' + z + '" checked><span  id = "td' + x + 'cb' + x + '' + z + 'lb">'
												+ checkBoxIdandValue[0]
												+ '</span><input type="hidden" id = "td' + x + 'cb' + x + '' + z + 'lbid" value="' + checkBoxIdandValue[1] + '"><br>';
									}
									tablebody += '<input type="hidden" id="td' + x + '" value="0"></td>';
								}

							}
							$j('#roastertable').append(tablebody);
							var subbutton = '<button type="button" class="btn btn-primary" style="margin-top: 30px" onclick="saveDetail()">Submit</button>';
							$j('#btn_submit').empty();
							$j('#btn_submit').append(subbutton);
							tablebody = '';
						}

					},
					error : function(msg) {
						alert("An error has occurred while contacting the server");
					}
				});

	}

	function dateFormat(inputdate) {

		var daynmonth = inputdate.split("-");
		var formated_date = daynmonth[2] + '-' + daynmonth[1] + '-'
				+ daynmonth[0];
		return formated_date;
	}

	function compareDate(inputdate) {
		var parts = inputdate.split("-");
		var formated_date = parts[2] + '-' + parts[1] + '-' + parts[0];
		return formated_date;
	}

	function nextDate(inputdate) {

		var datearray = [];
		for (var i = 0; i <= 6; i++) {

			var nextday = new Date(inputdate);
			nextday.setDate(nextday.getDate() + i);
			//var nextdate = nextday.getFullYear()+'-'+("0" + (nextday.getMonth() + 1)).slice(-2)+'-'+("0" + nextday.getDate()).slice(-2);
			var nextdate = ("0" + nextday.getDate()).slice(-2) + '-'
					+ ("0" + (nextday.getMonth() + 1)).slice(-2) + '-'
					+ nextday.getFullYear();
			datearray[i] = nextdate;
		}

		return datearray;

	}

	function makeDateinHyphenformat(inputdate) {
		var firstdate = new Date(inputdate);
		//var date1 = firstdate.getFullYear()+'-'+(firstdate.getMonth()+1)+'-'+firstdate.getDate();
		var date1 = ("0" + firstdate.getDate()).slice(-2) + '-'
				+ ("0" + (firstdate.getMonth() + 1)).slice(-2) + '-'
				+ firstdate.getFullYear();
		return date1;
	}

	function saveDetail() {

		var th0 = $j('#th0').text();
		var th1 = $j('#th1').text();
		var th2 = $j('#th2').text();
		var th3 = $j('#th3').text();
		var th4 = $j('#th4').text();
		var th5 = $j('#th5').text();
		var th6 = $j('#th6').text();

		//id in hidden field
		var td0 = $j('#td0').val();
		var td1 = $j('#td1').val();
		var td2 = $j('#td2').val();
		var td3 = $j('#td3').val();
		var td4 = $j('#td4').val();
		var td5 = $j('#td5').val();
		var td6 = $j('#td6').val();

		//dynamically get value
		var rValue = [];
		for (var i = 0; i <= 6; i++) {
			var checkboxvalue = '';
			for (var j = 0; j < checkboxLength; j++) {
				var ch = 'td' + i + 'cb' + i + '' + j + '';
				var ch1 = document.getElementById(ch).checked;
				if (ch1) {
					checkboxvalue += 'y';
				} else {
					checkboxvalue += 'n';
				}
				var lbid = 'td' + i + 'cb' + i + '' + j + 'lbid';
				// alert("document.getElementById(lbid) "+document.getElementById(lbid).value);
				checkboxvalue += '@' + document.getElementById(lbid).value
						+ ',';

			}
			checkboxvalue = checkboxvalue.slice(0, checkboxvalue.length - 1);
			rValue[i] = checkboxvalue;

		}

		//row value
		var row1 = td0 + '#' + th0 + '#' + rValue[0];
		var row2 = td1 + '#' + th1 + '#' + rValue[1];
		var row3 = td2 + '#' + th2 + '#' + rValue[2];
		var row4 = td3 + '#' + th3 + '#' + rValue[3];
		var row5 = td4 + '#' + th4 + '#' + rValue[4];
		var row6 = td5 + '#' + th5 + '#' + rValue[5];
		var row7 = td6 + '#' + th6 + '#' + rValue[6];

		var deptID = $j('#com').find('option:selected').val();
		var currentdate = makeDateinHyphenformat(new Date());
		var datentime = new Date();
		var currenttime = datentime.getHours() + ":" + datentime.getMinutes();

		var params = {
			"row1" : row1,
			"row2" : row2,
			"row3" : row3,
			"row4" : row4,
			"row5" : row5,
			"row6" : row6,
			"row7" : row7,
			"current_date" : currentdate,
			"change_time" : currenttime,
			"change_by" : "2",
			"deptID" : deptID,
			"hospital_id" : "<%= hospitalId %>"
		}

		$j.ajax({
			type : "POST",
			contentType : "application/json",
			url : 'saveDoctorRoaster',
			data : JSON.stringify(params),
			dataType : "json",
			cache : false,
			success : function(data) {

				var msg = data.Msg;
				alert(msg);

			},

			error : function(data) {

				alert("An error has occurred while contacting the server");

			}
		});

	}
</script>

<body>

	<div class="content-page">
		<!-- Start content -->
		<div class="">
			<div class="container-fluid">

				<div class="internal_Htext">Doctor Roster</div>

				<div class="row">
					<div class="col-12">
					</div>
				</div>
				<!-- end row -->

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">


								<form>
									<div class="row">

										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-5 col-form-label">Department<sup><span>&#9733;</span></sup></label>
												<div class="col-sm-7">
													<select class="form-control" id="com">
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-1"></div>

										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-5 col-form-label">Date<sup><span>&#9733;</span></sup></label>
												<div class="col-sm-7">
													<input class="form-control form-control-sm" type="date"
														placeholder="" id="from_date">
												</div>
											</div>
										</div>

										<div class="col-md-1"></div>

										<div class="col-md-2">
											<div class="form-group row">
												<div class="col-sm-12">
													<button type="button" class="btn btn-primary"
														onclick="getDoctorRoaster()">Search</button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<table class="table table-bordered table-striped" border="2" cellspacing="0" cellpadding="0"
											id="roastertable"></table>
									</div>
									
									
									<div class="row"> 
												      <div class="col-md-12">
															<div class="btn-left-all">
																 
															</div>
															
															<div class="btn-right-all">
																
									                        <div id="btn_submit"></div>
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


</body>

</html>