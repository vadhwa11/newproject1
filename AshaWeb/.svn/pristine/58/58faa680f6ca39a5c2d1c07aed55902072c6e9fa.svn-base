var $j = jQuery.noConflict();

function validateText(id, length){

	 
	if($j("#"+id).val().length >= length){
		 alert("Length of "+id+" should be less than "+length);	
		 
		var str=  $j("#"+id).val();
		 str=str.substring(0,length).trim();
		 
		 $j("#"+id).val(str);
		 return false;
	 }
	
}

function validateTextField(id,length){	
	if($j("#"+id).val().length >= length){
		 alert("Length of "+id+" should be less than "+length);
		 
		 var str=  $j("#"+id).val();
		 str=str.substring(0,length).trim();
		 
		 $j("#"+id).val(str);
		 return false;
	 }
}

function rowClick(appId,appName,appUrl,appstatus){	
	
	document.getElementById("applicationName").value = appName;
	document.getElementById("applicationUrl").value = appUrl;
		
	 
	if(appstatus == 'Y' || appstatus == 'y'){		
		$j("#btnInActive").show();
		$j("#btnActive").hide();
		$j('#updateBtn').attr("disabled", false);
	}
	if(appstatus == 'N' || appstatus == 'n'){
		$j("#btnActive").show();
		$j("#btnInActive").hide();
		$j('#updateBtn').attr("disabled", true);
	}
	
	
	$j('#btnAddApplication').attr("disabled", true);
	
}

function ValidateInputParamenters(codeId, nameId, selectId){
	if(document.getElementById(codeId)==null || document.getElementById(codeId).value ==""){
		alert("Please Enter the Value of "+codeId);
		return false;
	}
	
	if(document.getElementById(nameId)==null || document.getElementById(nameId).value ==""){
		alert("Please Enter the Value of "+nameId);
		return false;
	}
	
	if(document.getElementById(selectId)==null || document.getElementById(selectId).value ==""){
		alert("Please Enter the Value of "+selectId);
		return false;
	}
}

function enableAddButton(id){
	if(document.getElementById(id).value!=null || !document.getElementById(id).value==""){
		$j('#btnAddCommand').attr("disabled", false);
	}else if( document.getElementById(id).value!=null || !document.getElementById(id).value==""){
		$j('#btnAddCommand').attr("disabled", false);
	}else{
		$j('#btnAddCommand').attr("disabled", true);
	}
}