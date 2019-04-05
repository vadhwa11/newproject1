<%--
 * Copyright 2019 JK Technosoft Ltd. All rights reserved.
 * Use is subject to license terms.
 * opdInvestigationTemplate.jsp  
 * Purpose of the JSP -  This is for All OpdTemplate Master.
 * @author  krishna Thakur
 * Create Date: 2 March,2019
 * Revision Date:      
 * Revision By: 
 * @version 1.8
--%>
<%@ page import="java.util.*"%>

<%@ page import="com.asha.icgweb.utils.ProjectUtils"%>
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/resources/js/gridForPatient.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/pops_global.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/pops_menu.js"></script>
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/resources/js/proto.js"></script>
<%--For AutoComplete Through Ajax--%>
<script src="/hms/jsp/js/ajax.js" type="text/javascript"></script>

<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/resources/js/hms.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.js?n=1"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/phase2/animatedcollapse.js"></script>
<%--For AutoComplete Through Ajax--%>

<script
	src="${pageContext.request.contextPath}/resources/js/unittest.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/newOpd.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.common.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>

<%
	Map<String, Object> map = new HashMap<String, Object>();
	if (request.getAttribute("map") != null) {
		map = (Map) request.getAttribute("map");
	}
	Map<String, Object> utilMap = new HashMap<String, Object>();
	utilMap = (Map) ProjectUtils.getCurrentDateAndTime();
	String date = (String) utilMap.get("currentDate");
	String time = (String) utilMap.get("currentTime");

	String userName = "";
	if (session.getAttribute("userName") != null) {
		userName = (String) session.getAttribute("userName");
	}
	String message = "";
	if (map.get("message") != null) {
		message = (String) map.get("message");
		//out.println(message);
	}
	System.out.println("message==in jsp ===" + message);

	/* int deptId = 0;
	if(session.getAttribute("deptId") != null){
		deptId = (Integer)session.getAttribute("deptId");
	}
	String deptName="";
	if(session.getAttribute("deptName") != null){
		deptName = (String)session.getAttribute("deptName");
	}
	List<MasFrequency> frequencyList= new ArrayList<MasFrequency>();
	if(map.get("masFrequencyList") != null){
	frequencyList=(List)map.get("masFrequencyList");
	
	}
	List<OpdInstructionTreatment>opdInstructionTreatmentList=new ArrayList<OpdInstructionTreatment>();
	if(map.get("opdInstructionTreatmentList")!=null){
		opdInstructionTreatmentList=(List<OpdInstructionTreatment>)map.get("opdInstructionTreatmentList");
	} */
%>

<div class="popupbg">

	<h4></h4>
	<div class="titleBg">
		<h2>Investigation Template</h2>
	</div>
	<h4></h4>
	<div class="Clear"></div>

	<script type="text/javascript">
      var templateCode='';
	   var icdArray=new Array();
	     var icdArrayForInstruction=new Array();
	   
	</script>
	<script type="text/javascript" language="javascript">


function removeRow()
{
  var tbl = document.getElementById('grid');
  var lastRow = tbl.rows.length;
  if (lastRow > 2) tbl.deleteRow(lastRow - 1);
}
function submitFormForOPD(formName,action,extraFunction,extraFunction2,extraFunction3){
errorMsg="";
	ob1 = true;
	ob2 = true;
	ob3 = true;
	obj = eval('document.'+formName)
	if(eval("window."+extraFunction))
    	ob1 =  eval(extraFunction+"()")
        
		if(eval("window."+extraFunction2))
        	ob2 = eval(extraFunction2+"()")
        
		if(eval("window."+extraFunction3))
        	ob3 = eval(extraFunction3+"()")        	
    	
		if(validateFields(formName)== true & ob1 & ob2 &ob3){
        if(formName=="admissionByHin"){
		
		obj.Submit11.disabled=true
		}
		
		
        	obj.action = action;
			obj.submit();
    		return true;
		}else{
    	   	
			if(errorMsg != ""){
				alert(errorMsg);
	       		return false;	
	       	}
	       	return true;
    	}
}


function submitWindow()
{
var code=document.getElementById('code').value;
var flag =true;
if(validateMetaCharacters(code)){

}

}

function closeWindow()
{
  window.close();
}
var col_length;

function addRow(){
  var tbl = document.getElementById('grid');
  var lastRow = tbl.rows.length;
  col_length = tbl.rows.length;
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow+1;
  var row = tbl.insertRow(lastRow);
  var hdb = document.getElementById('hdb');
  hdb.value=iteration
  
  
 
  
  var cellRight1 = row.insertCell(0);
  var e1 = document.createElement('input');
  e1.type = 'text';
 
  					  
  e1.name = 'chargeCodeName' + iteration;
  e1.id = 'chargeCodeName' + iteration;
  
  e1.size = '45';
  cellRight1.appendChild(e1);
  if(i == ""){
	  autocomplete(document.getElementById("chargeCodeName"+iteration), arry);
  }else{
	  autocomplete(document.getElementById("chargeCodeName"+iteration), arrayData);
  }
 
  e1.onblur=function(){populateChargeCode(this.value,iteration)};
  e1.focus();

/*   Map m = new LinkedHashMap(4); 
  JSONArray ja = new JSONArray(); 
  m = new LinkedHashMap(); 
  ja.put("cargeCodeId",col_length); */
  
  //new Ajax.Autocompleter('chargeCodeName'+iteration,'ac2update1','opd?method=getInvestigationListForAutoComplete',{callback: function(element, entry) { return entry + '&labradiologyCheck=' + document.getElementById('investigationCategory').value;},parameters:'requiredField=chargeCodeName'+iteration});
  var sel = document.createElement('input');
  sel.type = 'hidden';
  sel.name='chargeCodeCode'+iteration;
  sel.id='chargeCodeCode'+iteration
  sel.size = '20'
  cellRight1.appendChild(sel);
  

var cellRight2 = row.insertCell(1);
var e4 = document.createElement('input');
e4.type = 'button';
e4.value="Add";
e4.className = 'button';
e4.onclick = function(){addRow();};
 
cellRight2.appendChild(e4); 

var cellRight3 = row.insertCell(2);
var e5 = document.createElement('input');
e5.type = 'button';
e5.value="Delete";
e5.className = 'button';
e5.onclick = function(){removeRow();};
cellRight3.appendChild(e5);



/* for(var i=0;i<col_length;i++){
	var id = 'chargeCodeName'+col_length+'';
	autocomplete(document.getElementById('chargeCodeName'+col_length+''), arry);
} */


}


function createTemplateCode(){
	
var templateName=document.getElementById("templateName").value;
if(validateMetaCharacters(templateName)){
if(templateName.length>5){

	templateCode=templateName.substring(0,5);
document.investigationTemplate.code.value=templateCode;
//alert("tempalte code" + templateCode);
}
else{
document.investigationTemplate.code.value=templateName;
}
}
}
	</script>

	<%
		int i = 1;
	%>
	<form name="investigationTemplate" method="post" action="">

		<input type="hidden" name=" " value="OpdTemplate" /> <input
			type="hidden" name=" " value="TemplateName" /> <input type="hidden"
			name="title" value="OpdTemplate" /> <input type="hidden" name=" "
			value="opdTemplate" /> <input type="hidden" name="hiddenValueCharge"
			id="hiddenValueCharge" /> <input type="hidden"
			name="pojoPropertyCode" value="TemplateCode" />

		<div class="Block">
			<!--  <label>Template Code </label>-->

			<input type="hidden" name="code" id="code" value="" disabled="true"
				readonly="readonly" validate="Template Code,metachar,yes"
				MAXLENGTH="8" tabindex=1 /> <label><span>*</span> Template
				Name</label> <input type="text" name=" " id="templateName" value=""
				onblur="createTemplateCode();" validate="Template Name,metachar,yes"
				MAXLENGTH="30" tabindex=1 />

		</div>
		<div class="clear"></div>
		<div class="paddingTop15"></div>
		<div id="testDiv">
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				id="gridHeader">
				<tr>
					<td colspan="3">
						<div>
							<input type="radio" onchange="changeRadio(this.value)"
								checked="checked" name="labradiologyCheck"
								class="radioCheckCol2" value="1">LAB <input type="radio"
								onchange="changeRadio(this.value)" name="labradiologyCheck"
								class="radioCheckCol2" value="2">Radiology <input
								type="hidden" id="investigationCategory"
								name="investigationCategory" value="Lab">
							<div class="clear"></div>
						</div>

					</td>
				</tr>
				<tr id="th">

					<th>Investigation</th>
					<th>Add</th>
					<th>Delete</th>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				id="grid">
				<tr id="tableInv" name="tableInv">
					<td><input type="hidden" size="40" value="0" class="smcaption"
						name="chargeCodeId1" id="chargeCodeId1" /> 
						<input type="hidden"
						name="chargeCodeCode<%=i%>" id="chargeCodeCode1"
						validate="Test Code And Description,metachar,yes" readonly /> 
						<input
						type="text" value="" validate=" Test Description,string,no"
						size="45" tabindex="1" id="chargeCodeName<%=i%>"
						name="chargeCodeName1"
						onblur="populateChargeCode(this.value,<%=i%>);" /></td>


					<td><input type="hidden" name="hdb" value="1" id="hdb"
						validate="hdb,metachar,yes" /> <input type="Button" tabindex="1"
						class="button" onclick="addRow();" value="Add" /></td>
					<td><input type="button" name="delete" tabindex="1"
						class="button" value="Delete" onclick="removeRow();" /></td>
				</tr>
			</table>
			<div class="Clear"></div>
		</div>


		<div class="Clear"></div>
		<div class="division"></div>

		<input type="button" name="add" id="addbutton" value="Save"
			class="button" onClick="submitWindow();" accesskey="a" tabindex=1 />
		<input type="reset" name="Reset" id="reset" value="Reset"
			class="button" onclick="resetCheck();" accesskey="r" /> <input
			type="reset" name="Reset" id="reset" value="Close" class="button"
			onclick="closeWindow();" accesskey="r" />

		<%-- <input type="button" name="close" id="addbutton" value="close" class="button"	onClick="closeWindow();" accesskey="a" tabindex=1 />--%>
		<div class="Clear"></div>
		<div class="division"></div>
		<div class="bottom">

			<label>Changed By</label> <label class="value"> </label> <label>Changed
				Date</label> <label class="value"><%=date%> </label> <label>Changed
				Time</label> <label class="value"><%=time%></label> <input type="hidden"
				name=" " value=" " /> <input type="hidden" name=" " value=" " /> <input
				type="hidden" name=" " value=" " />
			<div class="Clear"></div>
			<div id="edited"></div>

		</div>
	</form>

</div>

<script>

var arry=new Array();
autocomplete(document.getElementById("chargeCodeName1"), arry);

</script>


<script type="text/javascript" language="javascript">


$(document).ready(
		function() {
			var radioValue = 1; 
			
	var pathname = window.location.pathname;
	var accessGroup = pathname.split("/")[1];

	var url = window.location.protocol + "//"
			+ window.location.host + "/" + accessGroup
			+ "/v0.1/opd/getIinvestigationList";

	//var data = $('employeeId').val();
   // alert("radioValue" +radioValue);
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : JSON.stringify({
			'employeeId' : "1",
			'mainChargeCode':radioValue,
		}),
		dataType : 'json',
		timeout : 100000,
		
		success : function(res)
		
		{
			var data = res.InvestigationList;
			
			for(var i=0;i<data.length;i++){
				var investigationId= data[i].investigationId;
				var investigationName = data[i].investigationName;
				//var icdName = data[i].icdName;
				var a=investigationName+"["+investigationId +"]"
				//alert("a "+a);
				 arry.push(a);
				console.log('data :',a);
			}

		
           },
           error: function (jqXHR, exception) {
               var msg = '';
               if (jqXHR.status === 0) {
                   msg = 'Not connect.\n Verify Network.';
               } else if (jqXHR.status == 404) {
                   msg = 'Requested page not found. [404]';
               } else if (jqXHR.status == 500) {
                   msg = 'Internal Server Error [500].';
               } else if (exception === 'parsererror') {
                   msg = 'Requested JSON parse failed.';
               } else if (exception === 'timeout') {
                   msg = 'Time out error.';
               } else if (exception === 'abort') {
                   msg = 'Ajax request aborted.';
               } else {
                   msg = 'Uncaught Error.\n' + jqXHR.responseText;
               }
               alert(msg);
           }
	});
	

}); 
var arrayData = [];
var i = "";
function changeRadio(radioValue){
	i++;
	/* var radioValue = '';
	 $('input[name=labradiologyCheck]').change(function(){
		 radioValue = $( 'input[name=labradiologyCheck]:checked' ).val();
 	   
 	}); */ 
	 alert(radioValue);
	var pathname = window.location.pathname;
	var accessGroup = pathname.split("/")[1];

	var url = window.location.protocol + "//"
			+ window.location.host + "/" + accessGroup
			+ "/v0.1/opd/getIinvestigationList";

	//var data = $('employeeId').val();
   // alert("radioValue" +radioValue);
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : JSON.stringify({
			'employeeId' : "1",
			'mainChargeCode':radioValue,
		}),
		dataType : 'json',
		timeout : 100000,
		
		success : function(res)
		
		{
			var data = res.InvestigationList;
			
			for(var i=0;i<data.length;i++){
				var investigationId= data[i].investigationId;
				var investigationName = data[i].investigationName;
				//var icdName = data[i].icdName;
				var a=investigationName+"["+investigationId +"]"
				//alert("a "+a);
				arrayData.push(a);
				 
				// autocomplete(document.getElementById("chargeCodeName1"), "");
				 autocomplete(document.getElementById("chargeCodeName1"), arrayData);
				//console.log('data :',a);
			}

		
           },
           error: function (jqXHR, exception) {
               var msg = '';
               if (jqXHR.status === 0) {
                   msg = 'Not connect.\n Verify Network.';
               } else if (jqXHR.status == 404) {
                   msg = 'Requested page not found. [404]';
               } else if (jqXHR.status == 500) {
                   msg = 'Internal Server Error [500].';
               } else if (exception === 'parsererror') {
                   msg = 'Requested JSON parse failed.';
               } else if (exception === 'timeout') {
                   msg = 'Time out error.';
               } else if (exception === 'abort') {
                   msg = 'Ajax request aborted.';
               } else {
                   msg = 'Uncaught Error.\n' + jqXHR.responseText;
               }
               alert(msg);
           }
	});
}
var charge_code_array = [];
var ChargeCode='';
var multipleChargeCode = new Array();
function populateChargeCode(val,count) {
	
	
	if(validateMetaCharacters(val)){
		if(val != "")
		{
			
		    var index1 = val.lastIndexOf("[");
		    var indexForChargeCode=index1;
		    var index2 = val.lastIndexOf("]");
		    index1++;
		    ChargeCode = val.substring(index1,index2);
		    
		    var indexForChargeCode=indexForChargeCode--;
		   // alert("value is fun"+ChargeCode);
       
		if(ChargeCode == "")
		{
		document.getElementById('chargeCodeName'+count).value="";
		document.getElementById('chargeCodeCode'+count).value="";
		return;
		}
		else
			ChargeCode= document.getElementById('chargeCodeCode'+count).value=ChargeCode
			multipleChargeCode[count-1]=ChargeCode;
			
		}
		}	
		}
	



</script>

<script type="text/javascript" language="javascript">


 $('#addbutton').click(function() {
	    
	       var arry = [];
	    	for (var i = 0; i < multipleChargeCode.length; i++) {
	    	   // console.log("chargeCodeId" + ": " + multipleChargeCode[i]);
	    	    var param = {'investigationId' : multipleChargeCode[i],}
	   		    arry.push(param)
	    	   console.log("data array"+arry);
	    	} 
	    	
	    
	    		
	
	 var pathname = window.location.pathname;
		var accessGroup = pathname.split("/")[1];

		var url = window.location.protocol + "//"
				+ window.location.host + "/" + accessGroup
				+ "/v0.1/opd/saveOpdTemplates";

	 var dataJSON = {
	 'templateCode' : templateCode,
	 'templateName' : $('#templateName').val(),
	 'templateType' : "I",
	 'departmentId' : "1",
	 'doctorId' : "1",
	 'hospitalId' : "1",
	  "listofInvestigationTemplate" : arry
	 }
	 $.ajax({
	 type : "POST",
	 contentType : "application/json",
	 url : url,
	 data : JSON.stringify(dataJSON),
	 dataType : 'json',
	 timeout : 100000,
	 success : function(msg) {
	 console.log("SUCCESS: ", msg);
	 if (msg.status == 1)
 	   {
		   alert("OPD Template Details Insert successfully");
		   location.reload();
		  
	   }
		
	  else
	  {
		  alert(msg.status)
	   }
	 },
	 error: function (jqXHR, exception) {
         var msg = '';
         if (jqXHR.status === 0) {
             msg = 'Not connect.\n Verify Network.';
         } else if (jqXHR.status == 404) {
             msg = 'Requested page not found. [404]';
         } else if (jqXHR.status == 500) {
             msg = 'Internal Server Error [500].';
         } else if (exception === 'parsererror') {
             msg = 'Requested JSON parse failed.';
         } else if (exception === 'timeout') {
             msg = 'Time out error.';
         } else if (exception === 'abort') {
             msg = 'Ajax request aborted.';
         } else {
             msg = 'Uncaught Error.\n' + jqXHR.responseText;
         }
         alert(msg);
     },
	 });
	 		});
	  
 
 </script>

