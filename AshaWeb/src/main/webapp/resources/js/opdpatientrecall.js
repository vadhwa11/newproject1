/**
 * 
 */
function patientExaminationDignosisData(){
	var visitId=$('#visitId').val();
	var data =	 {"visitId":visitId};
 	 $.ajax({
		
		type:"POST",
		contentType : "application/json",
		url : 'getExaminationDetail',
		
		data : JSON.stringify(data),
		dataType: "json",			
		//cache: false,
		
		success : function(data) {
			if(data!=undefined && data!=null){
			$('#pollar').val(data.data[0].pollor);
			$('#ordema').val(data.data[0].ordema);
			$('#cyanosis').val(data.data[0].cyanosis);
			
			
			$('#hairnail').val(data.data[0].hairnail);
			$('#jaundice').val(data.data[0].jaundice);
			$('#lymphnode').val(data.data[0].lymphnode);
			
			
			$('#clubbing').val(data.data[0].clubbing);
			$('#thyroid').val(data.data[0].thyroid);
			$('#tremors').val(data.data[0].tremors);
			 
			$('#others').val(data.data[0].others);
			$('#cns').val(data.data[0].cns);
			$('#chest').val(data.data[0].chest);
			
			$('#musculoskeletal').val(data.data[0].musculoskeletal);
			$('#cvs').val(data.data[0].cvs);
			$('#skin').val(data.data[0].skin);
			$('#gi').val(data.data[0].gi);
			$('#geneticurinary').val(data.data[0].geneticurinary);
			$('#OthersForSystem').val(data.data[0].OthersForSystem);
			$('#workingdiagnosis').val(data.data[0].workingdiagnosis);
			$('#snomeddiagnosis').val(data.data[0].snomeddiagnosis);
			} 
			
 	 	}
	 });  

	return false;
}
var investigationGridValue="investigationGrid";
var investigationData = '';
function patientInvestigationData(){
	var visitId=$('#visitId').val();
	var data =	 {"visitId":visitId};
 	 $.ajax({
		type:"POST",
		contentType : "application/json",
		url : 'getInvestigationDetail',
		data : JSON.stringify(data),
		dataType: "json",			
		//cache: false,
		
		success : function(res) {
			data = res.listObject;
			if(data!=null && data.length>0){
				$('#hinId').val(data.length+1);
				$('#departmentId').val(data[0].departId);
				//$('#hospitalId').val(data[0].hospitalId);
			}
			var count=1;
			var countins=1;
			var investigationfinalValue="";
		if(data!=null && data.length>0){
			for(var i=0;i<data.length;i++){
				
				investigationData +='<tr>';
				investigationData +='<td><div class="autocomplete">';
				investigationData +='<input type="text" value="'+data[i].investigationName+'['+data[i].investigationId+']" id="chargeCodeName'+count+'"';
				investigationData +=' class="form-control border-input" name="chargeCodeName"  onblur="populateChargeCode(this.value,'+countins+');putInvestigationValue(this)"/>';
				investigationData +='<input type="hidden" id="qty" tabindex="1" name="qty1"  maxlength="6"/>';
				investigationData +='<input type="hidden" tabindex="1" id="chargeCodeCode"';
				investigationData +='name="chargeCodeCode"  readonly />';
				investigationData +='<input type="hidden"  name="investigationIdValue" value="'+data[i].investigationId+'"  id="investigationIdValue'+data[i].investigationId+'"/>';
				
				investigationData +='<input type="hidden"  name="dgOrderDtIdValue" value='+data[i].dgOrderDtId+' id="dgOrderDtIdValue'+data[i].dgOrderDtId+'"/>';
				investigationData +='<input type="hidden"  name="dgOrderHdId" value='+data[i].orderHdId+' id="dgOrderHdId'+data[i].orderHdId+'"/>';
				
				//investigationData +='<input type="hidden"  name="marksAsLabValue" value="" id="marksAsLabValue" />';
				//investigationData +='<input type="hidden"  name="urgentValue" value="" id="urgentValue" />';
				investigationData+=	' </div></td>';
				
				investigationData +='<td><input type="date" id="investigationDate'+count+'"';
				investigationData +='name="investigationDate"    class="input_date form-control"';
				investigationData +='placeholder="DD/MM/YYYY" value="'+data[i].orderDate+'" maxlength="10" />';
				investigationData +='</td>';
				
				var checkedradioForI="";
				var checkedradioForO="";
				var checked="";
				if(data[i].labMark=="I"){
					checkedradioForI='checked';
				 }
				else{
					checkedradioForI='';
				}
				if(data[i].labMark=="O"){
					checkedradioForO='checked';
				}
				else{
					checkedradioForO='';
				}
				investigationData +='<td> <input type="radio"  '+checkedradioForI+' value="I" id="othAfLab1'+count+'" class="radioCheckCol2" style="margin-right: 15px;" ';
				investigationData +='name="othAfLab'+count+'"';
				investigationData +='onchange="#"/></td>';
				 
				
				investigationData +='<td> <input type="radio"  value="O" '+checkedradioForO+' id="othAfLab1'+count+'" class="radioCheckCol21" style="margin-right: 15px;" ';
				investigationData +='name="othAfLab'+count+'"';
				investigationData +='onchange="#"/></td>';
				var urgentValue="";
				if(data[i].urgent=="Y"){
					 checked='checked';
					 urgentValue="Y";
				 }
				else{
					checked='';
					urgentValue="N";
				}
				investigationData +='<td><input type="checkbox" '+checked+' name="urgent" id="uCheck1'+count+'"';
				investigationData +='	onchange="#" tabindex="1" class="radioAuto" value="'+urgentValue+'" /></td>';
		        
				investigationData +='<td><button name="Button" type="button"   class="buttonAdd btn btn-primary" value="" ';
				investigationData +='onclick="addRowForInvestigationFun();"';
				investigationData +='	tabindex="1" >Add </button></td>';
				
				investigationData +='<td><button type="button" name="delete" value="" id="deleteInves"';
				investigationData +='class="buttonDel btn btn-danger"';
				investigationData +='onclick="removeRowInvestigation(this,\''+investigationGridValue+'\','+data[i].dgOrderDtId+');"';
				investigationData +='	tabindex="1" >Delete</button></td>';
				investigationData+=	' </tr> ';
			 count++;
			}
			
			
			investigationData+=	'<input type="hidden" value='+count+' name="hiddenValue"';
			investigationData+=	'id="hiddenValue" />';
			//investigationData +='</table>';
			//$("#investigationGrid").html(investigationData);
			if(data[0]!=null && data[0].otherInvestigation!=null)
				$("#otherInvestigation").val(data[0].otherInvestigation);
			$("#dgInvetigationGrid").html(investigationData);
			if(data!=null && data.length>0){
				$('#templateOfInvest').hide();
			}
			else{
				$('#templateOfInvest').show();
			}
		}
			
 	 	}
	 });  

	return false;
}

 

function patientTreatementDetail(){
	var visitId=$('#visitId').val();
	var data =	 {"visitId":visitId};
 	 $.ajax({
		
		type:"POST",
		contentType : "application/json",
		url : 'getTreatmentPatientDetail',
		
		data : JSON.stringify(data),
		dataType: "json",			
		//cache: false,
		success : function(res) {
			
			data = res.listObject;
			var frequencyList = res.MasFrequencyList;
			var treatementData = '';
			
			treatementData +='<div class="autocomplete">';
			var count=1;
			var countNo=1;
			if(data==null || data.length==0){
			return false;	
			}
			investigationGridValue="nomenclatureGrid";
			for(var i=0;i<data.length;i++){
				treatementData +='<tr>';
				treatementData +='<td>';
					treatementData +='<div class="autocomplete">';
					treatementData +='<input type="text"  tabindex="1" size="77" value="'+data[i].nomenclature+'['+data[i].PVMSno+']" ';
					treatementData +='id="nomenclature'+countNo+'"  name="nomenclature1"';
					treatementData +='class="form-control border-input"';
					treatementData +=' onblur="populatePVMS(this.value,'+count+');putPvmsValue(this);" />';
					treatementData +='<input type="hidden"  name="itemId" value='+data[i].itemId+' id="nomenclatureId'+data[i].itemId+'"/>';
					treatementData += '<input type="hidden"  name="prescriptionHdId" value='+data[i].precryptionHdId+' id="prescriptionHdId'+data[i].precryptionHdId+'"/>';
					treatementData += '<input type="hidden"  name="prescriptionDtId" value='+data[i].precriptionDtId+' id="precriptionDtId'+data[i].precriptionDtId+'"/>';
					
					treatementData +='</div>';
					
				treatementData +='</td>';
				
				treatementData +='<td><input type="text" value="'+data[i].dispStock+'" name="dispensingUnit1" ';
				treatementData +=' id="dispensingUnit1" size="6"';
				treatementData +='validate="AU,string,no"   class="form-control"/>';
				treatementData +='</td>';
				
				treatementData +='<td><input type="text" class="form-control" size="5" name="dosage1" tabindex="1"';
				treatementData +='value='+data[i].dosage+' id="dosage1"  maxlength="5" />';
				treatementData +='</td>';
				

				treatementData +='<td><select name="frequencyTre" class="form-control" id="frequencyTre'+i+'"';
				treatementData +='class="medium">';
				
				var frequencyIdValue=data[i].frequencyId;
				treatementData +='<option value=""><strong>Select</strong></option>';
				
				var selectFre="";
				$.each(frequencyList, function(ij, item) {
				
					if(frequencyIdValue == item.frequencyId){
						selectFre="selected";
					}
					else{
						selectFre="";
					}
					treatementData += '<option '+selectFre+' value="'+item.frequencyId +'">' + item.frequencyName+'</option>';
				});
				treatementData +='</select>';
				treatementData +='</td>';
				
				
				treatementData +='	<td><input type="text" class="form-control" value="'+data[i].noOfDays+'" name="noOfDays1" tabindex="1"';
				treatementData +='	id="noOfDays1" onblur="fillValueNomenclature('+count+')" size="5"';
				treatementData +='	maxlength="3" validate="No of Days,num,no" /></td>';
				
				treatementData +='<td><input type="text" class="form-control" value="'+data[i].total+'" name="total1" tabindex="1"';
				treatementData +='id="total1" size="5" validate="total,num,no"';
				treatementData +='onblur="treatmentTotalAlert(this.value,1)" /></td>';
				
				treatementData +='<td><input type="text" class="form-control" tabindex="1" value="'+data[i].instruction+'" name="remarks1" ';
				treatementData +='id="remarks1" size="10" maxlength="15" placeholder="1-1-1" />';
				treatementData +='</td>';
				
				
				treatementData +='	<td><input type="text" class="form-control" name="closingStock1" tabindex="1"  value="'+data[i].storeStoke+'"';
				treatementData +='id="closingStock1" size="3"';
				treatementData +='validate="closingStock,string,no" /></td>';
				
				treatementData +='<td style="display:none"><input  type="hidden" value="" tabindex="1"';
				treatementData +='	id="itemIdNom" size="77" name="itemIdNom" /></td>';
				
				treatementData +='<td><button name="Button" type="button"';
				treatementData +='	class="buttonAdd btn btn-primary" value="" onclick="addNomenclatureRowRecall();"';
				treatementData +='	tabindex="1" >Add </button></td>';
				
				treatementData +='<td><button type="button" name="delete" id="treatementId" value=""';
				treatementData +='class="buttonDel btn btn-danger"';
				treatementData +='	onclick="removeRowInvestigation(this,\''+investigationGridValue+'\','+data[i].precriptionDtId+');"';
				treatementData +='	tabindex="1">Delete</button></td>';

				treatementData +='<input type="hidden" name="pvmsNo1" tabindex="1"';
				treatementData +='	id="pvmsNo1" size="10" readonly="readonly" />';
				
				
				treatementData +='</tr>';
				countNo++;
			}
			 
			treatementData+=	'</div">';
			$("#nomenclatureGrid").html(treatementData);
			if(data!=null && data.length>0)
			$("#recommendedMedicalAdvice").val(data[0].otherTreatement);
			if(data!=null && data.length>0){
				$('#templateTreatment').hide();
			}
			else{
				$('#templateTreatment').show();
			}
 	 	}
	 });  

	return false;
}


function patientHistoryData(){
	var visitId=$('#visitId').val();
	var data =	 {"visitId":visitId};
 	 $.ajax({
		
		type:"POST",
		contentType : "application/json",
		url : 'getPatientHistoryDetail',
		data : JSON.stringify(data),
		dataType: "json",			
		//cache: false,
		success : function(res) {
			data = res.listOpdPatientHistory;
			for(var i=0;i<data.length;i++){
			$('#chiefCompliant').val(data[i].chiefComplain);
			$('#presentIllnessHistory').val(data[i].presentIllnessHistory);
			$('#pastMedicalHistory').val(data[i].pastMedicalHistory);
			
			
			$('#surgicalHistory').val(data[i].surgicalHistory);
			$('#medicationHistory').val(data[i].medicationHistory);
			$('#personalHistory').val(data[i].personalHistory);
			
			
			$('#socialHistory').val(data[i].socialHistory);
			$('#familyHistory').val(data[i].familyHistory);
			$('#allergyHistory').val(data[i].allergyHistory);
			$('#implantHistory').val(data[i].implantHistory);
			}
 	 	}
	 });  

	return false;
}
var globalDataForReferal="";
var globalReferalDatHtml="";
function getPatientReferalDetail(){
	var opdPatientDetailId=$('#opdPatientDetailId').val();
	var patientId=$('#patientId').val();
	var visitId=$('#visitId').val();
	var masIcdList="";
	var data =	 {"opdPatientDetailId":opdPatientDetailId,"patientId":patientId,"visitId":visitId};
 	 $.ajax({
		
		type:"POST",
		contentType : "application/json",
		url : 'getPatientReferalDetail',
		data : JSON.stringify(data),
		dataType: "json",			
		//cache: false,
		success : function(res) {
			data = res.listReferralPatientDt;
			masEmpanaledList=res.masEmpanelledHospitalList;
			masIcdList=res.listMasIcd;
			
			globalDataForReferal=res.listReferralPatientDt;
			var referrDtData="";
			var count=1;
			investigationGridValue="referrDtData";
			var diagnosisValue="";
			var diagonisisIdValue="";
			$("#referalGridNew tr").remove(); 
			var referalPatientDtIds="";
			var referalNotes="";
			var referVisitDates="";
			if(data!= undefined && data.length!=0){
			for(var i=0;i<data.length;i++){
				referrDtData +='<tr ">';
				referrDtData +='<td>'+count+'</td>';
				referrDtData +='<td><select class="form-control" id="referHospitalList'+i+'" name="referHospitalList"';
				referrDtData +='class="medium">';
				var masEmpanalId=data[i].masEmpanalId;
				referrDtData +='<option value=""><strong>Select</strong></option>';
				var selectMasEmpalHos="";
				
				$.each(masEmpanaledList, function(ij, empanal) {
					if(masEmpanalId == empanal.empanelledHospitalId){
						selectMasEmpalHos='selected';
					}
					else{
						selectMasEmpalHos="";
					}
					referrDtData += '<option '+selectMasEmpalHos+' value="'+ empanal.empanelledHospitalId + '@' + empanal.empanelledHospitalCode + '" >' + empanal.empanelledHospitalName
							+ '</option>';
				});
				referrDtData +='</select>';
				referrDtData +='</td>';
				 
			 
				referrDtData +='<td><input type="text" class="form-control" id="departmentValue'+i+'" name="departmentValue" value='+data[i].exDepartmentValue+' />';
				referrDtData +='<input type="hidden" id="diagonsisId'+i+'" name="diagonsisId" value="'+data[i].diagonisId+'"/>';
				referrDtData += '<input type="hidden"  name="masEmpanalHospitalId" value='+data[i].masEmpanalId+' id="masEmpanalHospitalId"/>';
				referrDtData += '<input type="hidden"  name="masDepatId" value='+data[i].masDepatId+' id="masDepatId"/>';
				
				referrDtData += '<input type="hidden"  name="referalPatientDt" value='+data[i].referalPatientDt+' id="referalPatientDt"/>';
				referrDtData += '<input type="hidden"  name="referalPatientHd" value='+data[0].referalPatientHd+' id="referalPatientHd"/>';
			
				referrDtData += '</td>';
				referrDtData +='<div class="autocomplete">';
				referrDtData +='<td><input type="text" class="form-control" id="diagonsisText'+i+'" name="diagonsisText" value="'+data[i].daiganosisName+'['+data[i].masCode+']'+'" /></td>'
				referrDtData +='<div>';
				referrDtData +='<td><input type="text" class="form-control" id="hos" name="hos" value='+data[i].instruction+' /></td>';
				
			 
				referrDtData +='<td><button name="Button" type="button" class="buttonAdd btn btn-primary" id="referalDtIdAdd" value="" tabindex="1" onclick="addRowForReferalPatient();">Add </button></td>';
				referrDtData +='<td><button type="button" name="delete" value="" id="referalDtIdDel"  class="buttonDel btn btn-danger" tabindex="1" onclick="removeRowInvestigationReferal(this,\''+investigationGridValue+'\','+data[i].referalPatientDt+');" >Delete</button></td>';
				referrDtData +='</tr>';
				count++;
				diagnosisValue+=data[i].daiganosisName+"["+data[i].masCode+"]"+"##";
				diagonisisIdValue+=data[i].diagonisId+"&&";
				referalPatientDtIds+=data[i].referalPatientDt+"&&";
			}
			if(count>1){
				document.getElementById("option2").selected = true;
				$('input:radio[name=referTo]')[0].checked = true;
				
				var diagonisIDValuennn="";
				diagonisIDValuennn+='<select name="diagnosisId" multiple="4" size="5"';
				diagonisIDValuennn+='	tabindex="1" id="diagnosisId" class="listBig"';
				diagonisIDValuennn+='	validate="ICD Daignosis,string,yes">';
				var res11  = diagnosisValue.split("##");
				var resId  = diagonisisIdValue.split("&&");
				var referalPatientDtIdsValue=referalPatientDtIds.split("&&");
				for(var i=0;i<res11.length-1;i++){
					diagonisIDValuennn += '<option   value="'+resId[i]+'&&&'+referalPatientDtIdsValue[i]+'">' + res11[i]+ '</option>';
					 total_icd_value += res11[i]+",";
				}
				diagonisIDValuennn+='</select>';
				$("#diagnosisId").html(diagonisIDValuennn);
				
				
				globalReferalDatHtml=referrDtData;
				
				
				$('#referVisitDate').val(data[0].referalDate);
				$('#referralNote').val(data[0].referalNotes);
				
				$("#referalGridNew").html(referrDtData);
				var mcdIdValuee="";
				for(var i=0;i<masIcdList.length;i++){
					mcdIdValuee+=masIcdList[i].icdId+",";
				}
				$('#icdIdValue').val(mcdIdValuee);
				$("#referDiv").show();
				
			}
			}
			else{
				if(count==1){
				var diagonisIDValuennn="";
				diagonisIDValuennn+='<select name="diagnosisId" multiple="4" size="5"';
				diagonisIDValuennn+='	tabindex="1" id="diagnosisId" class="listBig"';
				diagonisIDValuennn+='	validate="ICD Daignosis,string,yes">';
				
				var mcdIdValuee="";
				for(var i=0;i<masIcdList.length;i++){
					diagonisIDValuennn += '<option   value="'+masIcdList[i].icdId+'&&&'+0+'">' +masIcdList[i].icdName+"["+masIcdList[i].icdCode+"]"+ '</option>';
					mcdIdValuee+=masIcdList[i].icdId+",";
					total_icd_value += masIcdList[i].icdName+"["+masIcdList[i].icdCode+"]"+",";
				}
				diagonisIDValuennn+='</select>';
				$("#diagnosisId").html(diagonisIDValuennn);
				document.getElementById("option1").selected = true;
				$('#icdIdValue').val(mcdIdValuee);
				$("#referDiv").hide();
				}
			}
 	 	}
	 });  
 	
	return false;
}
function deleteInvestigatRow(flag,valueForDelete,visitId,opdPatientDetailId,patientId){
	var data =	 {"valueForDelete":valueForDelete,"flag":flag,"visitId":visitId,"opdPatientDetailId":opdPatientDetailId,"patientId":patientId};
 	 $.ajax({
		type:"POST",
		contentType : "application/json",
		url : "deleteGridRow",
		data : JSON.stringify(data),
		dataType: "json",			
		cache: false,
		success : function(res) { 
			  getPatientReferalDetail();
			return "2";
 	 	}
	 });  
 	
	return "1";
}


 

function getFrequencyDetailTre() {

	var pathname = window.location.pathname;
	var accessGroup = pathname.split("/")[1];
	var url = window.location.protocol + "//"
	+ window.location.host + "/" + accessGroup
	+ "/opd/getMasFrequency";
	//var url = "http://localhost:8082/AshaServices/opdmaster/getMasFrequency";
	$
			.ajax({
				url : url,
				dataType : "json",
				data : JSON.stringify({
					'employeeId' : '1'
				}),
				contentType : "application/json",
				type : "POST",
				success : function(response) {
					//console.log(response);
					var datas = response.MasFrequencyList;
					
					var trHTML="";
					trHTML+= '<option value=""><strong>Select</strong></option>';
					$.each(datas, function(i, item) {
						trHTML += '<option value="'+ item.frequencyId + '" >' + item.frequencyName
								+ '</option>';
					});
					$('#frequencyTre').html(trHTML);

				},
				error : function(e) {

					//console.log("ERROR: ", e);

				},
				done : function(e) {
					//console.log("DONE");
					alert("success");
					var datas = e.data;

				}
			});

}
function addRowForReferalPatient() {
	var val = parseInt($('#referalGridNew>tr:last').find("td:eq(0)").text());
	var aClone = $('#referalGridNew>tr:last').clone(true)
	aClone.find("td:eq(0)").text(++val);
	aClone.find(":input").val("");
	aClone.find("option[selected]").removeAttr("selected")
	aClone.clone(true).appendTo('#referalGridNew');
	$('#referalGridNew>tr:last').find("td:eq(6)").find("button:eq(0)").attr("id","newIdRef");
	
}


function removeRowInvestigation(val, investigationGrid, investigationData) {

	var tbl = document.getElementById('dgInvetigationGrid');
	var lastRow = tbl.rows.length;
	if(confirm("are you sure want to delete ?")){
	//var i = val.parentNode.parentNode.rowIndex;
	if (investigationGrid == "investigationGrid" && lastRow == '1') {
		$("#messageDelete").show();
		return false;
	}
	var tb2 = document.getElementById('nomenclatureGrid');
	var lastRow2 = tb2.rows.length;
	
	if (investigationGrid == "nomenclatureGrid" && lastRow2 == '1') {
		$("#messageDelete").show();
		return false;
	}

	
	/*var tb3 = document.getElementById('referrDtData');
	var lastRow3 = tb3.rows.length;
	
	if (investigationGrid == "referrDtData" && lastRow3 == '1') {
		$("#messageDelete").show();
		return false;
	}*/

	
	$(val).closest('tr').remove();
	var flag = 0;
	if ((val.id != "newIdInv" && investigationGrid == "investigationGrid")
			|| (val.id != "newIdTre" && investigationGrid == "nomenclatureGrid")
			|| (val.id != "newIdRef" && investigationGrid == "referrDtData")) {
		if (investigationGrid == "investigationGrid" && investigationData != "") {
			flag = 1;
			deleteInvestigatRow(flag, investigationData,"","","");
		}
		if (investigationGrid == "nomenclatureGrid" && investigationData != "") {
			flag = 2;
			deleteInvestigatRow(flag, investigationData,"","","");
		}
		if (investigationGrid == "referrDtData" && investigationData != "") {
			flag = 3;
			deleteInvestigatRow(flag, investigationData,"","","");
		}
	}	}
}

function removeRowInvestigationReferal(val, investigationGrid, investigationData) {
	if (confirm("are you sure want to delete ?")) {
	var tb3 = document.getElementById('referalGridNew');
	var lastRow3 = tb3.rows.length;
	if (investigationGrid == "referrDtData" && lastRow3 == '1') {
		$("#messageDelete").show();
		return false;
	}

	
	$(val).closest('tr').remove();
	var flag = 0;
	if (val.id != "newIdRef" && investigationGrid == "referrDtData") {
	 
		if (investigationGrid == "referrDtData" && investigationData != "") {
			flag = 3;
			deleteInvestigatRow(flag, investigationData,"","","");
		}
	}
}
}

function closeDelete()
{
	$("#messageDelete").hide();
 
	} 
function closeSubmit()
{
	$("#messageSubmit").hide();
 
}


var labMarkArray=[];
var urgentValuearray=[];


function radioInvest(){
	$('#dgInvetigationGrid tr').each(function(i, el){
		
	       var id= $(this).find("td:eq(2)").find("input:eq(0)").attr("id")
	         if(document.getElementById(id).checked == true){
	         var iinLab='I';
	         labMarkValue=iinLab;
	         
	        }
	        else
	         {
	         var outLab='O';
	         labMarkValue=outLab;
	         }
	             
	       labMarkArray.push(labMarkValue);
	       
	       
	       var $tds = $(this).find('td')
		    
		    if ($tds.eq(4).find(":input").is(":checked")){
				  var yurgent='Y';
				  urgent=yurgent;
				  }
				  else
				  {
				      var nUrgent='N';
				      urgent=nUrgent;
				  }
		    
		    urgentValuearray.push(urgent);
	       
	    });
	
	/*$('#dgInvetigationGrid tr').each(function(i, el) {
	    var $tds = $(this).find('td')
	    
	    if ($tds.eq(4).find(":input").is(":checked")){
			  var yurgent='Y';
			  urgent=yurgent;
			  }
			  else
			  {
			      var nUrgent='N';
			      urgent=nUrgent;
			  }
	    
	    urgentValuearray.push(urgent);
	});*/
 //$('#dgInvetigationGrid>tr').closest('tr').find("td:eq(0)").find("input:eq(6)").val(labMarkArray);
 //$('#dgInvetigationGrid>tr').closest('tr').find("td:eq(0)").find("input:eq(7)").val(urgentValuearray);
 $('#marksAsLabValue').val(labMarkArray);
 $('#urgentValue').val(urgentValuearray);

}

//$("#referralForNew").change(

		function getReferalDataForAdd() {
			
			document.getElementById("referExternal").checked = true;
			investigationGridValue="referrDtData";
			
			//$("#referalGridNew tr").remove(); 
			var flagBydefault=false;
			if ($("#referralForNew").val() == 1) {
				checkReferTO('referExternal');

				$("#admDiv").hide();
				$("#admissionAdvised").attr("checked", false);
				$("#admissionAdvised").attr('disabled', true);
				// $("#referInternal").attr('checked', true);
				$("#referExternal").attr('checked', 'checked');
				var j=1;
				if(globalDataForReferal==null || globalDataForReferal.length==0){
					$("#referalGridNew tr").remove(); 
				}
				else{
					$("#referalGridNew tr").remove();
					$('#referalGridNew').append(globalReferalDatHtml);
				}
				var pathname = window.location.pathname;
				var accessGroup = pathname.split("/")[1];
				var url = window.location.protocol + "//"
				+ window.location.host + "/" + accessGroup
				+ "/opd/getEmpanelledHospital";
				 			$.ajax({
							url : url,
							dataType : "json",
							data : JSON.stringify({
								'employeeId' : '1'
							}),
							contentType : "application/json",
							type : "POST",
							success : function(response) {
								//console.log(response);
								var datas = response.masEmpanelledHospital;
								var trHTML = '<option value=""><strong>Select</strong></option>';
								$.each(datas, function(i, item) {
									trHTML += '<option value="' + item.empanelledHospitalId + '@'
											+ item.empanelledHospitalCode + '" >' + item.empanelledHospitalName
											+ '</option>';
								});
								
								var tb11 = document.getElementById('referalGridNew');
								var lastRow=0;
								if(tb11!=null){
								  lastRow = tb11.rows.length;removeRowInvestigation
									j=lastRow+1;
								}
								
								var count=0;
								 
								$("#diagnosisId > option").each(
										function() {
											var flagForcheck=true;
											var diagonsisText=this.text;
											var diagonsisValue=this.value;
											var gobalReferIdId=0;
											var finalValueDiagonosisId=diagonsisValue;
											
											if(finalValueDiagonosisId!=null && finalValueDiagonosisId.includes("&&&")){
												finalValueDiagonosisId=finalValueDiagonosisId.split("&&&");
												diagonsisValue=finalValueDiagonosisId[0];
											}
											
											if(globalDataForReferal!=null && globalDataForReferal.length!=0){
											
											for(var i=count;i<globalDataForReferal.length;i++){
												gobalReferIdId=globalDataForReferal[i].diagonisId;
												if(gobalReferIdId == diagonsisValue){
													flagForcheck = false;
												}
											}
											}
											
											 
									
										if(flagForcheck==true){
											var html='<tr><td>'+(j++)+'</td><td><select class="form-control" id="referHospitalList'+j+'" name="referHospitalList">';
											
											//html+=trHTML+'</select></td><td><input type="text" id="departmentValue" name="departmentValue" /></td><td><input type="text" id="diagonsisText" name="diagonsisText" value="'+diagonsisText+'"/></td><td><input type="text" id="hos" name="hos" /></td><td><button name="button" type="button" class="btn btn-primary buttonAdd" value="" tabindex="1" onclick="addRowForRefer();">Add</button></td><td><button type="button" name="delete" value="" class="buttonDel btn btn-danger" tabindex="1" onclick="removeRowReferal(this);">Delete</button></td><td><input type="hidden" id="diagonsisId" name="diagonsisId" value="'+diagonsisValue+'"/></td></tr>'
											html+=trHTML+'</select></td><td><input type="text" class="form-control" id="departmentValue'+j+'" name="departmentValue" />';
											
											html +='<input type="hidden" id="diagonsisId'+j+'" name="diagonsisId" value="'+diagonsisValue+'"/>';
											html += '<input type="hidden"  name="masEmpanalHospitalId" value="" id="masEmpanalHospitalId"/>';
											html += '<input type="hidden"  name="masDepatId" value="" id="masDepatId"/>';
											
											html += '<input type="hidden"  name="referalPatientDt" value="" id="referalPatientDt"/>';
											html += '<input type="hidden"  name="referalPatientHd" value="" id="referalPatientHd"/>';
										
											html+='</td>';
											html+='<td><input type="text" class="form-control" id="diagonsisText'+j+'" name="diagonsisText" value="'+diagonsisText+'"/></td><td><input type="text" id="hos" name="hos" /></td>';
											html+='<td><button name="Button" type="button" class="buttonAdd btn btn-primary" value="" tabindex="1" onclick="addRowForReferalPatient();" >Add</button></td>';
											html+='<td><button type="button" name="delete" value="" class="buttonDel btn btn-danger" id="newIdRef" tabindex="1" onclick="removeRowInvestigationReferal(this,\''+investigationGridValue+'\',0);" >Delete</button></td></tr>'
											$('#referalGridNew').append(html);
											var val = parseInt($('#referalGridNew>tr:last').find("td:eq(1)").text());
											}
											
											count++;
										});
							},
							error : function(e) {

								//console.log("ERROR: ", e);

							},
							done : function(e) {
								//console.log("DONE");
								alert("success");
								var datas = e.data;

							}
						});
				
				 			$("#referDiv").show();
				 			//flagBydefault=true;
				 			
			} else {
				if(globalDataForReferal!=null && globalDataForReferal.length>1){
					if (confirm("You have already referred this Patient. Are you sure want to  delete this referral.")) {
				document.getElementById('referhospital').setAttribute(
						"validate", " ");
				document.getElementById('referredFor').setAttribute(
						"validate", " ");
				$("#admissionAdvised").attr('disabled', false);
				$("#referalGrid tr").remove(); 
				$("#referDiv").hide();
					var visitId=$('#visitId').val();
					var opdPatientDetailId=$('#opdPatientDetailId').val();
					var patientId=$('#patientId').val();
					deleteInvestigatRow(4,"",visitId,opdPatientDetailId,patientId);
				}
				else{
						document.getElementById("option2").selected = true;
					}	
				}
				if($("#referralForNew").val() != 1){
					document.getElementById('referhospital').setAttribute(
							"validate", " ");
					document.getElementById('referredFor').setAttribute(
							"validate", " ");
					$("#admissionAdvised").attr('disabled', false);
					$("#referalGrid tr").remove(); 
					$("#referDiv").hide();
				}
				
			}
			
		}



function checkReferTO(id) {
	if (document.getElementById('referInternal').checked == true) {
		document.getElementById('referhospital').setAttribute("validate", "Hospital,String,yes");
		document.getElementById('referral_treatment_type').setAttribute("validate", "Referral Type,String,yes");
		document.getElementById('referredFor').setAttribute("validate", "Referred for,String,yes");

		document.getElementById('referhospitalLabel').style.display='block'
		document.getElementById('referhospital').style.display='block';
		//document.getElementById('priorityLabelId').style.display='none';
		//document.getElementById('priorityId').style.display='none';
	
		document.getElementById('referdayslLabel').style.display='block';
		document.getElementById('referdays').style.display='block';
		document.getElementById('referral_treatment_type_label').style.display='block';
		document.getElementById('referral_treatment_type').style.display='block';
		document.getElementById('referredFor').style.display='block';
		document.getElementById('referredForLabel').style.display='block';
		document.getElementById('referDepartmentDiv').style.display='none';
			
	} else if (document.getElementById('referExternal').checked == true) {
		
		document.getElementById('referhospital').setAttribute("validate", " ");
		document.getElementById('referredFor').setAttribute("validate", " ");
		
		//document.getElementById('priorityLabelId').style.display = 'block';
		//document.getElementById('priorityId').style.display = 'block';
		
		document.getElementById('referhospitalLabel').style.display = 'none';
		document.getElementById('referhospital').style.display = 'none';
		document.getElementById('referdayslLabel').style.display = 'none';
		document.getElementById('referdays').style.display = 'none';
		document.getElementById('referral_treatment_type_label').style.display = 'none';
		document.getElementById('referral_treatment_type').style.display = 'none';
		document.getElementById('referredFor').style.display = 'none';
		document.getElementById('referredForLabel').style.display = 'none';
		document.getElementById('referDepartmentDiv').style.display = 'block';
	} else {
		document.getElementById('referhospital').setAttribute("validate",
				"Hospital,String,yes");
		document.getElementById('referral_treatment_type').setAttribute(
				"validate", "Referral Type,String,yes");
		document.getElementById('referredFor').setAttribute("validate",
				"Referred for,String,yes");
		document.getElementById('referhospitalLabel').style.display = 'block'
		document.getElementById('referhospital').style.display = 'block';
		;
		document.getElementById('referdayslLabel').style.display = 'block';
		document.getElementById('referdays').style.display = 'block';
		document.getElementById('referral_treatment_type_label').style.display = 'block';
		document.getElementById('referral_treatment_type').style.display = 'block';
		document.getElementById('referredFor').style.display = 'block';
		document.getElementById('referredForLabel').style.display = 'block';

		//document.getElementById('priorityLabelId').style.display = 'block';
		//document.getElementById('priorityId').style.display = 'block';
		document.getElementById('referDepartmentDiv').style.display = 'block';
	}
}
/*function addRowForRefer() {

	var val = parseInt($('#referalGrid>tr:last').find("td:eq(0)").text());
	var aClone = $('#referalGrid>tr:last').clone(true)
	aClone.find("td:eq(0)").text(++val);
	aClone.find(":input").val("");
	aClone.find("option[selected]").removeAttr("selected")
	aClone.clone(true).appendTo('#referalGrid');
	// autocomplete(val, arryNomenclature);

}*/
 

function validateDataOpdPatientRecall(){
	 if(document.getElementById('chiefCompliant').value == "") {
         alert("Please enter a Chief Complaint Value");
         document.getElementById('chiefCompliant').focus(); 
         return false;
       }
     
     var count=0
	 $("#diagnosisId > option").each(
				function() {
				count++;	
					
				});
	 
       if(count=="") {
     	alert("Please enter atleast one ICD Diagnosis");
     	document.getElementById('icd').focus();
         return false;
       }
       
       
       var flag = true;
       
    /////////////////// referral Validation ///////////////////////////////   
       if(document.getElementById('referralForNew').value == "1")
       {	  
       if(document.getElementById('referVisitDate').value == "")
     	{
     		alert("Please select referral date");
     		document.getElementById('referVisitDate').focus();
     		return false;
     	}
       }
       
      /* $('#referalGridNew tr').each(function(i, el) {
     	    var $tds = $(this).find('td')
     	    var extHospitalId = $tds.eq(1).find(":input").val();
     	   var departmentValue = $tds.eq(2).find(":input").val();
     	  var instruction = $tds.eq(4).find(":input").val();
     	 if(extHospitalId== "")
   	    {
       		alert("Please select hospital for referral");
       		flag = false;
       		    	
   		}
     	 if(departmentValue== "")
    	    {
        		alert("Please entered  department for referral");
        		flag = false;
    		} 
     	 if(instruction== "")
 	    {
     		alert("Please entered  instruction for referral");
     		flag = false;
 		} 
       });*/
     
       //////////////////////////////////////for treatmentCheck//////////////////////////////////////
       
     /*  $('#dgInvetigationGrid tr').each(function(i, el) {
    	    var $tds = $(this).find('td')
    	    var investigationIdValue=$($tds).closest('tr').find("td:eq(0)").find("input:eq(3)").val();
    	    var investigationDate = $tds.eq(2).find(":input").val();
    	 if(investigationIdValue== "")
  	    {
      		alert("Please entered the Investigation.");
      		flag = false;
      		    	
  		}
    	 if(investigationDate== "")
   	    {
       		alert("Please entered  investigation Date.");
       		flag = false;
   		} 
     
      });*/
       
     ///////////////////////////////////////////////////////////////////////////////////////////////
       
    /*   $('#nomenclatureGrid tr').each(function(i, el) {
   	    var $tds = $(this).find('td')
   	  var treatementValue=$($tds).closest('tr').find("td:eq(0)").find("input:eq(1)").val( );
   	 var dispUnit = $tds.eq(2).find(":input").val();
   	    var dosageValue = $tds.eq(3).find(":input").val();
   	 var frequencyValue = $tds.eq(4).find(":input").val();
   	 var daysValue = $tds.eq(5).find(":input").val();
   	 if(treatementValue== "")
 	    {
     		alert("Please entered the Nomenclature.");
     		flag = false;
     		    	
 		}
   	 if(dispUnit== "")
  	    {
      		alert("Dispt Unit can not blank.");
      		flag = false;
  		} 
   	 if(dosageValue== "")
	    {
   		alert("Please entered the Dosage.");
   		flag = false;
		}
   	 
   	 if(frequencyValue== "")
	    {
		alert("Please select the frequency.");
		flag = false;
		}
   	if(daysValue== "")
    {
	alert("Please entered days.");
	flag = false;
	}
     });*/
      ////////////////////////////////////////////////////////////////////////////// 
       
       if(flag == false){
      	  return;
        }	  	
        
       
     /////////////////// onsisty mark validation ////////////////////////////  
     var obsistyMark='';
 	if (checkBox.checked == true)
		{
		    obsistyMark='true';
		} 
		
 	if(checkBox.checked == true)
 	{
 		if(document.getElementById('height').value == "")
 		{	
 		alert("please enter height")
 		return false;
 		}
 		if(document.getElementById('weight').value == "")
 		{	
 		alert("please enter weight")
 		document.getElementById('weight').focus();
 		return false;
 		}
 	}
 	var freProcedureValue="";
 	var noOfDaysValue="";
 	 $('#gridNursing tr').each(function(i, el) {
  	    var $tds = $(this).find('td')
  	    var select = $tds.eq(1).find(":input").val();
  	 // var valueSelect=select.options[select.selectedIndex].value;
  	//alert("valueSelect"+valueSelect);
  	  freProcedureValue+=select+",";
  	  var  noOfDays  = $tds.eq(2).find(":input").val();
  	  noOfDaysValue+=noOfDays+",";
 	});
 	
	 
	$('#freProcedure').val(freProcedureValue); 	 
	$('#noOfDaysPro').val(noOfDaysValue); 
 	return true;
}



function submitForm(){
	var validateData=validateDataOpdPatientRecall();
	
	if(validateData==true){
	radioInvest();
	$("#submitPatientRecall").submit();
	return true;
	}
	else{
		return false;
	}
}


function deleteDgItems(value) {
var referPatientDtOrDiagnosis=document.getElementById('diagnosisId').options[document.getElementById('diagnosisId').selectedIndex].value;

if(referPatientDtOrDiagnosis.includes("&&&")){
	var checkForReferPatient=referPatientDtOrDiagnosis.split("&&&");
	var visitId=$('#visitId').val();
	var opdPatientDetailId=$('#opdPatientDetailId').val();
	var patientId=$('#patientId').val();
	if(checkForReferPatient[1]=="0"){
		var status=deleteInvestigatRow(3,referPatientDtOrDiagnosis,visitId,opdPatientDetailId,patientId);
	}
	else{
		alert("Referal Header already generated for this diaganosis.");
		return true;
	}
}
if (confirm("are you sure want to delete ?")) {
    if (document.getElementById('diagnosisId').options[document.getElementById('diagnosisId').selectedIndex].value != null)
        document.getElementById('diagnosisId').remove(document.getElementById('diagnosisId').selectedIndex)
}
	
}



/*var dynamicDataValue="";

function getDynamicMenu(){
	//var data = sessionStorage.getItem('dynamicDataValue');
	//alert("data"+data);
	//if(data==null || data==""){
	var serviceNo="123457";
	var roles = ["RECEPTIONIST","DOCTOR","MEDICAL OFFICER"];
	var params = {
			"serviceNo" : serviceNo,
			"roles":roles
	}
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : '/AshaWeb/dashboard/showApplicationsOnRoleBaseNew',
		data : JSON.stringify(params),
		dataType : "json",
		cache : false,
		 
		 
		success : function(response) {
			var map1 =response.listOfKeyyy;
			 var keys = [];
			 var templateValue="";
			 var finaltemplate="";
			 
				for(var k in map1){ 
						keys.push(k);
				}
			for(var i = 0; i < keys.length; i++){	
				templateValue="";
				  
				 if(keys[i]=="A1"){
					   templateValue="";
				 	      for(var j=0;j<map1.A1.length;j++){
					    	templateValue+=("<li><a href='"+map1.A5[j].url+"'>"+map1.A5[j].appName+"</a></li>");
						}
				 	    
					    $('#dynamicValue').show().append("<li class='active'>");
					     $('#dynamicValue').show().append("<a href='#homeSubmenu1' data-toggle='collapse' aria-expanded='false' class='dropdown-toggle'><i class='ion-md-home'></i>Reception</span><span class='menu-arrow'></span></a>");
					     $('#dynamicValue').show().append("<ul class='nav-second-level collapse aria-expanded=false' id='homeSubmenu1'>"+templateValue+"</ul></li>");
				   }
				
				
				   if(keys[i]=="A5"){
					   templateValue="";
				 	      for(var j=0;j<map1.A5.length;j++){
					    	templateValue+=("<li><a href='"+map1.A5[j].url+"'>"+map1.A5[j].appName+"</a></li>");
						}
				 	    
					    $('#dynamicValue').show().append("<li class='active'>");
					     $('#dynamicValue').show().append("<a href='#homeSubmenu5' data-toggle='collapse' aria-expanded='false' class='dropdown-toggle'><i class='ion-md-home'></i>Reception</span><span class='menu-arrow'></span></a>");
					     $('#dynamicValue').show().append("<ul class='nav-second-level collapse aria-expanded=false' id='homeSubmenu5'>"+templateValue+"</ul></li>");
					     
					   
				   }
				   if(keys[i]=="A8"){
						 templateValue="";
				 	      for(var j=0;j<map1.A8.length;j++){
					    	templateValue+=("<li><a href='"+map1.A8[j].url+"'>"+map1.A8[j].appName+"</a></li>");
						}
					    $('#dynamicValue').show().append("<li class='active'>");
					     $('#dynamicValue').show().append("<a href='#homeSubmenu8' data-toggle='collapse' aria-expanded='false' class='dropdown-toggle'><i class='ion-md-home'></i>OPD</span><span class='menu-arrow'></span></a>");
					     $('#dynamicValue').show().append("<ul class='nav-second-level collapse aria-expanded=false' id='homeSubmenu8'>"+templateValue+"</ul></li>");
				   }
			   }
			 
			  
			}, 
		error : function(response) {
			alert("An error has occurred while contacting the server");
		}
	});
}*/ 




$(document).delegate("#dgInvestigationTemplateIdInvest","change",
		function() {
	var pathname = window.location.pathname;
	var accessGroup = pathname.split("/")[1];
	var url = window.location.protocol + "//"
	+ window.location.host + "/" + accessGroup
	+ "/opd/getTemplateInvestigation";
	// var url =
	// "http://localhost:8181/AshaServices/opdmaster/getTemplateInvestData";
	//alert("$('#dgInvestigationTemplateId')"+$('#dgInvestigationTemplateIdInvest').val());
	$
			.ajax({
				url : url,
				dataType : "json",
				data : JSON.stringify({
					'employeeId' : '1',
					'templateId':$('#dgInvestigationTemplateIdInvest').val()
				}),
				contentType : "application/json",
				type : "POST",
				success : function(response) {
					//console.log(response);
				   if (response.status == 1) {
					//$("#dgInvetigationGrid tr").remove(); 
					var datas = response.data;
					var trHTML = '';
					var count=0;
					$.each(datas, function(ij, item) {
								var flagForcheck1=true;
								var investigationValue=item.investigationName;
									var investigationId= item.templateInvestgationId;
									
									 
									  $('#dgInvetigationGrid tr').each(function(ihh, el) {
							    		   var $tds = $(this).find('td')
							    		   var chargeCodeId=  $($tds).closest('tr').find("td:eq(0)").find("input:eq(3)").attr("id");
									    var investigationIdValu= $('#'+chargeCodeId).val();
									    if(investigationId == investigationIdValu){
									    	 
											flagForcheck1 = false;
										}
									    //j++;
									});
									/*if(glopbalInvestigationList!=null && glopbalInvestigationList.length!=0){
									for(var j=i;j<glopbalInvestigationList.length;j++){
												if(investigationId == glopbalInvestigationList[i].investigationId){
													flagForcheck = false;
												}
											}
									}*/
									if(flagForcheck1==true){
										
										//alert("000000");
									trHTML +='<tr>';
									trHTML +='<td><div class="autocomplete">';
									trHTML +='<input type="text" value="'+investigationValue+'['+investigationId+']" id="chargeCodeName'+i+'"';
									trHTML +=' class="form-control border-input" name="chargeCodeName"  onblur="populateChargeCode(this.value,1);putInvestigationValue(this)"/>';
									trHTML +='<input type="hidden" id="qty" tabindex="1" name="qty1"  maxlength="6"/>';
									trHTML +='<input type="hidden" tabindex="1" id="chargeCodeCode'+i+'"';
									trHTML +='name="chargeCodeCode"  readonly />';
									trHTML +='<input type="hidden"  name="investigationIdValue" value="'+investigationId+'"  id="investigationIdValue'+i+'"/>';
									
									trHTML +='<input type="hidden"  name="dgOrderDtIdValue" value="" id="dgOrderDtIdValue'+i+'"/>';
									trHTML +='<input type="hidden"  name="dgOrderHdId" value="" id="dgOrderHdId'+i+'"/>';
									
									//investigationData +='<input type="hidden"  name="marksAsLabValue" value="" id="marksAsLabValue" />';
									//investigationData +='<input type="hidden"  name="urgentValue" value="" id="urgentValue" />';
									trHTML+=	' </div></td>';
									
									trHTML +='<td><input type="date" id="investigationDate'+i+'"';
									trHTML +='name="investigationDate"    class="input_date form-control"';
									trHTML +='placeholder="DD/MM/YYYY" value="" maxlength="10" />';
									trHTML +='</td>';
									trHTML +='<td> <input type="radio"    value="I" id="othAfLab1'+i+'" class="radioCheckCol2" style="margin-right: 15px;" ';
									trHTML +='name="othAfLab'+i+'"';
									trHTML +='onchange="#"/></td>';
									 
									
									trHTML +='<td> <input type="radio"  value="O"   id="othAfLab1'+i+'" class="radioCheckCol21" style="margin-right: 15px;" ';
									trHTML +='name="othAfLab'+i+'"';
									trHTML +='onchange="#"/></td>';
									 
									 
									trHTML +='<td><input type="checkbox"   name="urgent" id="uCheck1'+i+'"';
									trHTML +='	onchange="#" tabindex="1" class="radioAuto" value="" /></td>';
							        
									trHTML +='<td><button name="Button" type="button"   class="buttonAdd btn btn-primary" value="" ';
									trHTML +='onclick="addRowForInvestigationFun();"';
									trHTML +='	tabindex="1" >Add </button></td>';
									
									trHTML +='<td><button type="button" name="delete" value="" id="deleteInves"';
									trHTML +='class="buttonDel btn btn-danger"';
									trHTML +='onclick="removeRowInvestigation(this,\''+investigationGridValue+'\',);"';
									trHTML +='	tabindex="1" >Delete</button></td>';
									trHTML+=' </tr> ';
									}
									count++;
									
					});
					$('#dgInvetigationGrid').append(trHTML);
					//$('#dgInvetigationGrid').append(trHTML);
					// $('#investigationGrid').html(trHTML);
				/*	var dgInvestigationTemplate=$('#dgInvestigationTemplateIdInvest').val();
					alert(dgInvestigationTemplate);
					if(dgInvestigationTemplate.){
						
					}*/
				}
				   else
					{
					  // $("#investigationGrid tr").remove(); 
					  
					}
				},
				error : function(e) {

					//console.log("ERROR: ", e);

				},
				done : function(e) {
					//console.log("DONE");
					alert("success");
					var datas = e.data;

				}
			});

});



 
function getPocedureDetailData(){
	var visitId=$('#visitId').val();
	var opdPatientDetailId=$('#opdPatientDetailId').val();
	var data =	 {"visitId":visitId,"opdPatientDetailId":opdPatientDetailId};
 	 $.ajax({
		type:"POST",
		contentType : "application/json",
		url : 'getPocedureDetailRecall',
		data : JSON.stringify(data),
		dataType: "json",			
		cache: false,
		success : function(res) {
			var data = res.listOfProcedure;
			var masFrequency=res.masFrequency;
			if(data!=null && data.length>0){
				
				var nursingData="";
				investigationGridValue='gridNursing';
				var count=1;
				for(var i=0;i<data.length;i++){
					
					nursingData +='<tr>';
					nursingData +='<td><div class="form-group autocomplete">';
					nursingData +='<input type="text" value="'+data[i].nursingName+'['+data[i].nursingId+']" id="procedureNameNursing'+count+'"';
					nursingData +=' class="form-control border-input" name="procedureNameNursing"  onblur="populateNursingRecall(this.value,1);calucateNursingValue(this)"/>';
					nursingData +='<input type="hidden"  name="procedureNameNursingId" value="'+data[i].nursingId+'"  id="procedureNameNursingId'+data[i].nursingId+'"/>';
					nursingData +='<input type="hidden"  name="procedureDtIdValue" value="'+data[i].procedureDtId+'" id="procedureDtIdValue'+data[i].procedureDtId+'"/>';
					nursingData +='<input type="hidden"  name="procedureHdId" value="'+data[i].procedureHdId+'" id="procedureHdId'+data[i].procedureHdId+'"/>';
					nursingData+=	' </div></td>';
					
					
					
					nursingData +='<td><select name="frequencyNursing" class="form-control" id="frequency_nursing'+i+'"';
					nursingData +='class="form-control">';
					
					var frequencyIdValue=data[i].frequencyId;
					nursingData +='<option value=""><strong>Select</strong></option>';
					
					var selectFre="";
					$.each(masFrequency, function(ij, item) {
					
						if(frequencyIdValue == item.frequencyId){
							selectFre="selected";
						}
						else{
							selectFre="";
						}
						
					/*	trHTMLFrequncy += '<option value="' + item.feq + '@'
						+ item.frequencyId + '" >' + item.frequencyName
						+ '</option>';*/
						nursingData += '<option '+selectFre+' value="' + item.feq + '@'
						+ item.frequencyId + '">' + item.frequencyName+'</option>';
					});
					nursingData +='</select>';
					nursingData +='</td>';
					
					var noOfDaysValue="";
			 		if(data[i].noOfDays!=null && data[i].noOfDays!=0){
			 			noOfDaysValue=data[i].noOfDays;
			 		}
			 		else{
			 			noOfDaysValue="";
			 		}
					nursingData +='<td>';
					nursingData +='<input type="text" name="noOfDays_nursing"';
					nursingData +='id="noOfDays_nursing'+count+'" value="'+noOfDaysValue+'"';
					nursingData +='	class="opdTextBoxTSmall textYellow form-control" size="5"';
					nursingData +='	validate="No. of days,num,no" />';
					nursingData +='</td>';
					
					/*var procedureTypeValue="";
					if(data[i].proceduretype!=null && data[i].proceduretype=="M"){
						procedureTypeValue="Minor Surgery"
			 		}
					
					if(data[i].proceduretype!=null && data[i].proceduretype=="N"){
						procedureTypeValue="Nursing Care"
			 		}
					
					if(data[i].proceduretype!=null && data[i].proceduretype=="P"){
						procedureTypeValue="Physiotherapy"
			 		}*/
					
					
					nursingData +='<td>';
					nursingData +='<input type="text" name="procedureType" id="procedureType'+count+'" value="'+data[i].proceduretype+'"';
					nursingData +='class="largTextBoxOpd textYellow form-control" maxlength="5" readonly />';
					nursingData +='</td>';					
					
					
					nursingData +='<td>';
					nursingData +='<input value="'+data[i].remarks+'" type="text"';
					nursingData +='name="remark_nursing" id="remark_nursing'+count+'"';
					nursingData +='class="largTextBoxOpd textYellow form-control" maxlength="100" />';
					nursingData +='</td>';					
					
					
					/*nursingData +='<td style="display: none;">';
					nursingData +='<input type="hidden"';
					nursingData +='	value="" id="procedureNameNursingId"';
					nursingData +='	name="procedureNameNursingId" />';
					nursingData +='	</td>';
					nursingData +='<td style="display: none;">';
					nursingData +='<input type="hidden"';
					nursingData +='		class="form-control border-input" value=""';
					nursingData +='	id="procedureNameNursing" size="55"';
					nursingData +='	name="procedureNameNursing" />';
					nursingData +='</td>';
					nursingData +='<td style="display: none;"><input type="hidden"';
					nursingData +='	class="form-control border-input" value=""';
					nursingData +='	id="procedureNameNursingCare" size="55"';
					nursingData +='	name="procedureNameNursingCare" />';
					nursingData +='</td>';*/
					
					
 					nursingData +='<td><button name="Button" type="button"   class="buttonAdd btn btn-primary" value="" ';
					nursingData +='onclick="addRowTreatmentNursingCareRecall();"';
					nursingData +='	tabindex="1" >Add </button></td>';
					
					nursingData +='<td><button type="button" name="delete" value="" id="deleteProcure"';
					nursingData +='class="buttonDel btn btn-danger"';
					nursingData +='onclick="removeProcedureRow(this,\''+investigationGridValue+'\','+data[i].procedureDtId+');"';
					nursingData +='	tabindex="1" >Delete</button></td>';
					nursingData+=	' </tr> ';
				 count++;
				}
				
				$("#gridNursing").html(nursingData);
			
			}
		}
 	 });
}
 	 
 	 
var nursingNo = '';
function populateNursingRecall(val, inc) {
    
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
			//document.getElementById('procedureNameNursingCare').value = nursingNo
			// alert(nursingNo)
			return true;

		}

	} else {
		return false;
	}
}

var k=0;
function addRowTreatmentNursingCareRecall() {
	var tbl = document.getElementById('gridNursing');
	lastRow = tbl.rows.length;
	k =lastRow;
	k++;
	var aClone = $('#gridNursing>tr:last').clone(true)
	aClone.find(":input").val("");
	aClone.find("td:eq(0)").find("input:eq(0)").prop('id', 'procedureNameNursing'+k+'');
	
	aClone.find("option[selected]").removeAttr("selected")
	aClone.find('select').prop('id', 'frequency_nursing'+k+'');
	aClone.find("td:eq(2)").find(":input").prop('id', 'noOfDays_nursing'+k+'');
	
	aClone.find("td:eq(3)").find("input:eq(0)").prop('id', 'procedureType'+k+'');
	aClone.find("td:eq(4)").find("input:eq(0)").prop('id', 'remark_nursing'+k+'');
	aClone.clone(true).appendTo('#gridNursing');
	var val = $('#gridNursing>tr:last').find("td:eq(0)").find(":input")[0];
	if(arryProcedureCare!=null && arryProcedureCare!="" )
	{
	autocomplete(val, arryProcedureCare);
	}
	else
	{
	autocomplete(val, procedureArrayData);
	}
	
}
