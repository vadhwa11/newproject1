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
		 alert("Length of "+id+" should be less than 30");
		 
		 var str=  $j("#"+id).val();
		 str=str.substring(0,length).trim();
		 
		 $j("#"+id).val(str);
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