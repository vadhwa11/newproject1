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
</head>
 <body>
 <div id="wrapper">
	
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
	    	  htmlTable = htmlTable + "<td style='width: 150px;'><a style='text-decoration:underline; color:blue;'  href='#' onclick='downloadFile("+dataList[count].Id+")'</a>"+dataList[count].fileName+"</td>";
	    	  htmlTable = htmlTable + "<td style='width: 150px;'>" + remarks + "</td>";
	    	  htmlTable = htmlTable + "<td style='width: 150px;'><input type='button' id='deletebtn' name= 'deletebtn' class='btn  btn-danger' value='Delete'  onclick='deleteFile("+dataList[count].Id+")'</td>";
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
		
		validation("fileUploadForm");
		
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
	              	$("#btnAdd").prop("disabled", false);
	                changePatientName();
	                resetFields();
	            },
	            error: function (e) {
	                alert(e.responseText);
	               $("#btnAdd").prop("disabled", false);

	            }
	        });
			
	}else{
		alert("Service No. can not be blank.");
		return false;
	}
	
}	
	
	function downloadFile(fileId){
		  window.open('${pageContext.request.contextPath}/registration/viewUploadDocuments?fileId='+fileId+'',"_blank");
	}

	
	function deleteFile(fileId){
		var params={
				"fileId": fileId
		}
	       $.ajax({
	    	   type : "POST",
				contentType : "application/json",
				url : 'deleteUploadDocument',
				data : JSON.stringify(params),
				dataType : "json",
				cache : false,
	            success: function (data) {
	            	console.log(data);
	               if(data.data.status=="1"){
	            	   alert(data.data.msg);
	            	   changePatientName();
		               resetFields();
	               }else{
	            	   alert(data.data.msg); 
	               }
	            },
	            error: function (e) {
	              	alert(e.responseText);

	            }
	        });
	}
	
	
	function resetFields(){
		$('#fileUploadId').val("");
		$('#remarks').val("");
		
	}
	
	
	function validation(thisform)
	{
	var file =$('#fileUploadId').val();
	   with(thisform)
	   {
	      if(validateFileExtension(file, "valid_msg", "Only pdf/image files are allowed !",
	      new Array("jpg","pdf","jpeg","gif","png")) == false)
	      {
	         return false;
	      }
	   }
	}
	
	
	function validateFileExtension(component,msg_id,msg,extns)
	{
	   var flag=0;
	   with(component)
	   {
	      var ext=component.substring(component.lastIndexOf('.')+1);
	      for(i=0;i<extns.length;i++)
	      {
	         if(ext==extns[i])
	         {
	            flag=0;
	            break;
	         }
	         else
	         {
	            flag=1;
	         }
	      }
	      if(flag!=0)
	      {
	         alert(msg);
	         component.value="";
	         component.style.backgroundColor="#eab1b1";
	         component.style.border="thin solid #000000";
	         component.focus();
	         return false;
	      }
	      else
	      {
	         return true;
	      }
	   }
	}
	
	
	
</script>
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
															<th id="th3">Action</th>
														</tr>
													</thead>
													<tbody id="tblUploadDocument">
													<tr>
   														 <td><label>File</label>
   														  <input type="file" name="uploadFile" class="file" id="fileUploadId"></td>
   														  <td><textarea  class="form-control" type="textarea" name="remarks" id="remarks" value=""  MAXLENGTH="480" tabindex=1></textarea></td>
   														 <td><input name="btnAdd" id="btnAdd" type="button" class="btn  btn-primary" value="Upload" onClick="uploadDocument();"/></td> 
 													 </tr>
													</tbody>
												</table>
										</div>	
									 <div class="row">
									 
									<table class="table table-striped table-hover  table-bordered  ">
											<thead>
												<tr>
													<th style=" width:17%;" >Sr No.</th>
													<th style=" width:40%;" >View Doc</th>
													<th>Remarks</th>
													<th>Action</th>
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
	 
 </div>

</body>
</html>
