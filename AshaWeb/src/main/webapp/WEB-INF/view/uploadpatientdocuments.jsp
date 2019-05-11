<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.asha.icgweb.utils.ProjectUtils"%>
<%@page import="java.util.*"%>
<%@page import="com.asha.icgweb.entity.UploadDocument"%>
<%@include file="..//view/leftMenu.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="..//view/commonJavaScript.jsp"%>
<title>Upload patient documents</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/newOpd.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
</head>
<body>
	
<div class="Clear"></div>

<script >
	function getPatientList() {
		var serviceNo = $j('#serviceNo').val();
		$j('#patientId').val("0");
		document.getElementById("patientName").options.length = 1;
		if(serviceNo){
			var params = {
					"serviceNo":serviceNo
			}
			$j.ajax({
				
				type : "POST",
				contentType : "application/json",
				url : 'getPatientListFromServiceNo',
				data : JSON.stringify(params),
				dataType : "json",
				cache : false,
				success : function(response) {
					if(response.status=='1'){
						var patientValue = "";
						var patientList = response.data;
						 for(count in patientList){
							 patientValue += '<option value='+patientList[count].patientId+'>'
											+ patientList[count].patientName
											+ '</option>';
						 }
						 $j('#patientName').append(patientValue); 	  	
						
					}else{
						$j('#patientId').val("0");
						document.getElementById("patientName").options.length = 1;
						alert(response.msg);
					}
				}
			});
		}else{
			alert("Service No. can not be blank.");
			return false;
		}
		
	}
	
/* $(document).delegate("#patientName","change", function() {
	$j('#patientId').val("0");
	var patientId = $j('#patientName').find('option:selected').val();
	$j('#patientId').val(patientId);
	
	var params = {
				 "patientId":patientId 
				}
	
			 var data = params;
   			 var url = 'getDocumentListForPatient';
    		 var bClickable = true;
            GetJsonData('resultUploadDocTable', data, url, bClickable);
	
	}); */
	
	
function changePatientName(){
		$j('#patientId').val("0");
		var patientId = $j('#patientName').find('option:selected').val();
		$j('#patientId').val(patientId);
		
		var params = {
					 "patientId":patientId 
					}
		
				 var data = params;
	   			 var url = 'getDocumentListForPatient';
	    		 var bClickable = true;
	            GetJsonData('resultUploadDocTable', data, url, bClickable);
		
	}	

function makeTable(jsonData) {
    var htmlTable = "";
    if(jsonData.status==1){
   var dataList = jsonData.data;
		 for(count in dataList){
			 var a =parseInt(count)+1;
			 var remarks="";
			 if(dataList[count].remarks != undefined)
				 {
				 remarks= dataList[count].remarks
				 }
	    	  htmlTable = htmlTable + "<tr id='" + dataList[count].Id + "'>";
	    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + a + "</td>";
	    	  htmlTable = htmlTable + "<td style='width: 150px;'><a href='#' onclick='downloadFile("+dataList[count].Id+")'</a>"+dataList[count].fileName+"</td>";
	    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + remarks + "</td>";
	      }
		
	      $j("#resultUploadDocTable").html(htmlTable);
	}else{
		 $j("#resultUploadDocTable").empty();
	}
 }


function uploadDocument(){
	var patientId =$j('#patientId').val();
	if(patientId!=0){
		if (document.getElementById('fileUploadId').value == ""){
			alert('Please select a file to Upload');
			return;
			}
			 // Get form
	        var form = $('#fileUploadForm')[0];
			// Create an FormData object 
	        var data = new FormData(form);
			
			// disabled the submit button
	        $("#btnAdd").prop("disabled", true);

	        $.ajax({
	            type: 'POST',
	            enctype: 'multipart/form-data',
	            url: 'uploadDocumentForPatient',
	            data: data,
	            processData: false,
	            contentType: false,
	            cache: false,
	            timeout: 600000,
	            success: function (data) {
	                $("#result").text(data.message);
	                console.log("SUCCESS : ", data);
	              	$("#btnAdd").prop("disabled", false);
	                changePatientName();
	            },
	            error: function (e) {
	                $("#result").text(e.responseText);
	                console.log("ERROR : ", e);
	              //$("#btnAdd").prop("disabled", false);

	            }
	        });
			
	}else{
		alert("Service No. can not be blank.");
		return false;
	}
	
}	
	
	function downloadFile(fileId){
		$('#fileId').val(fileId);
		document.fileUploadForm.action="${pageContext.request.contextPath}/registration/viewUploadDocuments";
		document.fileUploadForm.method="POST";
		document.fileUploadForm.submit(); 
		
		
	/* 	var params = {
				"fileId":fileId
		}
		$j.ajax({
			
			type : "POST",
			contentType : "application/json",
			url : 'viewUploadDocuments',
			data : JSON.stringify(params),
			dataType : "json",
			cache : false,
			success : function(response) {
			console.log(response);
			}
		}); */
		
	}


</script>
		<%-- <form method="post" action="uploadDocumentForPatient" name="fileUploadForm" id="fileUploadForm" enctype="multipart/form-data">  --%>
		<form method="post" name="fileUploadForm" id="fileUploadForm"> 
			<div class="content-page">
				<div class="">
					<div class="container-fluid">
						<div class="internal_Htext">Upload Patient Documents</div>

						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-body">

										<!-- Patient Detail Start Here -->
										 
											<div class="row">
												<div class="col-md-4">
													<div class="form-group row">
														<label class="col-md-3 col-form-label">Service No.
														</label>
														<div class="col-sm-7">
															<input name="serviceNo" id="serviceNo" type="text"
																class="form-control border-input"
																placeholder="Service No." value=""
																onblur="getPatientList()" />
														</div>
													</div>
												</div>
											 
												<div class="col-md-4">
													<div class="form-group row">
														<label class="col-sm-5 col-form-label">Patient 	Name </label>
														<div class="col-sm-6">
															<select id="patientName" name="patientName"
																	class="form-control" onchange="changePatientName();">
																	<option value="0" selected="selected">Select</option>
																</select>
														</div>
														 <input type="hidden" name="patientId" id="patientId" value=""/>
														 <input type="hidden" name="fileId" id="fileId" value=""/>
													</div>
												</div>
											
										          	<div class="col-md-3">  									
															   <label class="radio-inline">
															      General Docs &nbsp &nbsp
															    <input  style="  position: relative; top: 3px; "  checked="checked" type="radio" id="gendocRadio" name="btnradio">
															    </label>	 
										             </div>
											</div>
										<div class="row">
										
										<table class="table table-striped table-hover  table-bordered  ">
													<thead>
														<tr>
															<th style=" width: 34%;" id="th1">Document</th>
															<th id="th2">Remarks</th>
															<th id="th3"></th>
														</tr>
													</thead>
													<tbody id="tblUploadDocument">
													<tr>
   														 <td><label>File</label>
   														  <input type="file" name="uploadFile" class="file" id="fileUploadId"></td>
   														  <td><textarea  class="form-control" type="textarea" name="remarks" id="remarks" value=""  MAXLENGTH="480" tabindex=1></textarea></td>
   														 <td><input name="btnAdd" id="btnAdd" type="button" class="button" value="Upload" onClick="uploadDocument();"/></td> 
   														 <!-- <td><input name="btnAdd" id="btnAdd" type="submit" class="button" value="Upload" /></td> -->
 													 </tr>
													</tbody>
												</table>
										</div>	
									 <div class="row">
									 
									<table class="table table-striped table-hover  table-bordered  ">
											<thead>
												<tr>
													<th style=" width: 34%;" >Sr No.</th>
													<th>View Doc</th>
													<th>Remarks</th>
												</tr>
											</thead>
													<tbody id="resultUploadDocTable">
													</tbody>
								  </table>
									 
									 
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>

</body>
</html>
