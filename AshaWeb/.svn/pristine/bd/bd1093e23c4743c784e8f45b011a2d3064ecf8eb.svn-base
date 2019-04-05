

<%@ page import="com.asha.icgweb.utils.ProjectUtils"%>
<%@ page import="java.util.*"%>

<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">

<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/gridForPatient.js"></script>
<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/hms.js"></script>
<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pops_global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pops_menu.js"></script>

<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/proto.js"></script>
<%--For AutoComplete Through Ajax--%>

<script src="${pageContext.request.contextPath}/resources/js/ajax.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/prototype.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/divideprototype.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/opd.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/hms.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/scriptaculous.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/unittest.js" type="text/javascript"></script>

<%
Map<String,Object> map = new HashMap<String,Object>();
if(request.getAttribute("map") != null){
	map = (Map) request.getAttribute("map");
}
Map<String,Object> utilMap = new HashMap<String,Object>();
utilMap = (Map)ProjectUtils.getCurrentDateAndTime();
String date = (String)utilMap.get("currentDate");	 
String time = (String)utilMap.get("currentTime");


String userName = "";
String deptName="";
int deptId = 0;
/* if(session.getAttribute("userName") != null){
	userName = (String)session.getAttribute("userName");
}
if(map.get("message") != null){
	   String message = (String)map.get("message");
	   out.println(message);
	  }
if(session.getAttribute("deptId") != null){
	deptId = (Integer)session.getAttribute("deptId");
}

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
}
	List templateList= new ArrayList();
	if(map.get("templateList") != null){
	templateList=(List)map.get("templateList");
	}

	String templateType= "";
	if(map.get("templateType") != null){
		templateType=(String)map.get("templateType");
	} */
%>

<div class="titleBg"><h2>Opd Template</h2></div>
<div class="clear"></div>
<h4><%=deptName %></h4>
<div class="Clear"></div>
<script type="text/javascript">
	   var icdArray=new Array();
	     var icdArrayForInstruction=new Array();
	     var itemClassCodeArray = new Array();
	</script> 
	
	<script type="text/javascript" language="javascript">
	
<%-- 	function getFrequencyValue(feqValue,inc){
		var feqQty;
	<%
	if(frequencyList.size()>0){	
		for(MasFrequency masFrequency :frequencyList){
	%>
	 if(feqValue == '<%=masFrequency.getId()%>'){
		 feqQty = '<%=masFrequency.getFeq()%>'
		
	  }

	<%}
	}%>
	 document.getElementById('frequencyValue'+inc).value = feqQty;
	} --%>
	function getFrequencyValue(feqValue,inc){
		feqValue =  document.getElementById('frequency'+inc).value;
		var feqQty;
	<%-- <%
	if(frequencyList.size()>0){	
		for(MasFrequency masFrequency1 :frequencyList){
	%>
	 if(feqValue == '<%=masFrequency1.getId()%>'){
		 feqQty = '<%=masFrequency1.getFeq()%>'
		
	  }

	<%}
	}%> --%>
	// document.getElementById('frequencyValue'+inc).value = '';
	}
	 function  fillValueFromFrequency(value,inc){
	   	  var dosage = document.getElementById('dosage'+inc).value
		  var noOfDays=document.getElementById('noOfDays'+inc).value
		  var freq=document.getElementById('frequencyValue'+inc).value
		  document.getElementById('total'+inc).value=noOfDays*freq*dosage
		  var dispenseQty = document.getElementById('actualDispensingQty'+inc).value;
	   	  var sosQty = document.getElementById('sosQty'+inc).value;
		  var total = freq*noOfDays*dosage;
		  var finalQty;
		  if(document.getElementById('frequency'+inc).value != 13 ){
		  if(document.getElementById('actualDispensingQty'+inc).value != 0){
			  var totalQty = (parseFloat(total)/parseFloat(dispenseQty)).toFixed(2)
			  if(totalQty != '0.00'){
				  finalQty = Math.ceil(totalQty);
			  }
			  document.getElementById('total'+inc).value=finalQty;
		
			 }else{
				 
				  document.getElementById('total'+inc).value=noOfDays*freq*dosage
			  }
		 // document.getElementById('noOfDays'+inc).readOnly = false;
		 // document.getElementById('sosQty'+inc).readOnly = true;
		  }else{
			  if(document.getElementById('actualDispensingQty'+inc).value != 0){
				  var totalQty = (parseFloat(freq*sosQty*dosage)/parseFloat(dispenseQty)).toFixed(2)
				  if(totalQty != '0.00'){
					  finalQty = Math.ceil(totalQty);
				  }
				  document.getElementById('total'+inc).value=finalQty;
			
				 }else{
					  document.getElementById('total'+inc).value=sosQty*freq*dosage
				  
			//  alert(document.getElementById('noOfDays'+inc).readOnly);
			 // document.getElementById('noOfDays'+inc).readOnly = true;
			 // document.getElementById('sosQty'+inc).readOnly = false;
		  }
		 }
	
/*  	function removeRow()
		{
		  var tbl = document.getElementById('grid');
		  var lastRow = tbl.rows.length;
		  if (lastRow > 2) tbl.deleteRow(lastRow - 1);
		}  */

 	
 	function removeRow(idName,countId,obj)
 	{
 	  var tbl = document.getElementById(idName);
 	  var lastRow = tbl.rows.length;
 	  if (lastRow > 3){
 	  //	tbl.deleteRow(lastRow - 1);
 	    var i=obj.parentNode.parentNode.rowIndex;
 	    tbl.deleteRow(i);
 	  }
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
		var code=document.getElementById("code").value; 
		var flag = true;
		flag = submitFormForOPD('prescriptionTemplate','opd?method=updateTreatmentTemplate&='+code+'');
		//alert("flag="+flag);
		// if(flag == true)
		//window.close();
		// if (window.opener.progressWindow)
		//	 {
		 //   	window.opener.progressWindow.close()
		//  	 } 
		//  window.close();
	}
	
	function deleteTemplate()
	{
		if(confirm("Do You want to delete the treatment template!?")){
		var templateId=document.getElementById("templateId").value; 
		var flag = true;
		flag = submitFormForOPD('prescriptionTemplate','opd?method=deleteTemplate&templateId='+templateId);
	
		}
	}
	function closeWindow()
	{
		  window.close();
	}
	
	function addRowForInvestigation(){
		
		  var tbl = document.getElementById('investigationGrid');
		  var lastRow = tbl.rows.length;

		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
		  var hdb = document.getElementById('hiddenValue');
		  var iteration = parseInt(hdb.value)+1;
		  hdb.value=iteration
		  // alert("iteration row--"+iteration)
	   
		  var cellRight0 = row.insertCell(0);
		  var e0 = document.createElement('input');
		  e0.type = 'text';
		 // e0.innerHTML = iteration+':'
		  e0.onblur=function(){
		  						if(validateInvestigationAutoComplete(this.value,iteration)){checkForChargeCode(this.value,iteration,'chargeCodeVal'+iteration);}
		  					  };
		   var newdiv1 = document.createElement('div');
		  newdiv1.id='ac2update'+iteration;
		  newdiv1.className='autocomplete';
		  newdiv1.style.display = 'none';
		  					
		  e0.name = 'chargeCodeName' + iteration;
		  e0.id = 'chargeCodeName' + iteration;
		  e0.setAttribute('tabindex','1');
		  //alert("name--"+e0.name)
		  e0.size = '100'
		  cellRight0.appendChild(newdiv1);
		  
		  cellRight0.appendChild(e0);
		  e0.focus();
		
		  new Ajax.Autocompleter('chargeCodeName'+iteration,'ac2update'+iteration,'opd?method=getInvestigationListForAutoComplete',{callback: function(element, entry) { return entry + '&labradiologyCheck=' + document.getElementById('investigationCategory').value;},parameters:'requiredField=chargeCodeName'+iteration});	  var sel = document.createElement('input');
		  																																								
		  sel.type = 'hidden';
		  sel.name='chargeCode'+iteration;
		  sel.id='chargeCode'+iteration
		  sel.size = '10';
		  sel.setAttribute('tabindex','1');
		  cellRight0.appendChild(sel);

		  var e2 = document.createElement('input');
		  e2.type = 'hidden';
		  e2.name='qty'+iteration;
		  e2.id='qty'+iteration
		  e2.size='10';
		  e2.setAttribute('tabindex','1');
		  cellRight0.appendChild(e2);
		
		 var cellRight2 = row.insertCell(1);
		  var e4 = document.createElement('input');
		  e4.type = 'button';
		  e4.className = 'buttonAdd';
		  e4.value = "";
		  e4.name='Button';
		  e4.setAttribute('tabindex','1');
		  //e4.setAttribute('onClick','addRowForInvestigation();');
		  e4.onclick = function(){addRowForInvestigation();}; 
		  cellRight2.appendChild(e4);

		  var cellRight3 = row.insertCell(2);
		  var e5 = document.createElement('input');
		  e5.type = 'button';
		  e5.className = 'buttonDel';
		  e5.value = ""
		  e5.name='delete';
		  e5.setAttribute('tabindex','1');
		  e5.onclick = function(){removeRow("investigationGrid","hdb",this);};  
		  cellRight3.appendChild(e5);
		}
	
	function createTemplateCode(){
	var templateName=document.getElementById(" ").value;
	
	if(templateName.length>5){
	
	var templateCode=templateName.substring(0,5);
	document.prescriptionTemplate.value=templateCode; 
	}
	else{
	document.prescriptionTemplate.value=templateName;
	}
	}
	
	
	</script>

<div class="Block">
<form name="prescriptionTemplate" method="post" action="">
<input type="hidden" name="templateType" value=" ">
		<label>Template</label>
			<div id="treatmentDiv">
				<select name="templateId" id="templateId" tabindex="1" onclick="submitProtoAjaxWithDivName('prescriptionTemplate','/hms/hms/opd?method=showTreatmentListByTemplate&templateType=','templateList');">
				
					<option value="0">Select</option>
					<%-- <%
					System.out.println("templateList "+templateList.size());
	   Iterator itr=templateList.iterator();
	   while(itr.hasNext())
	   {
		   OpdTemplate opdTemplate=(OpdTemplate)itr.next();
		   //String templateType=opdTemplate.getTemplateType();
		  /*  if(templateType.equalsIgnoreCase("p"))
		   { */
	%>
					<option value="<%=opdTemplate.getId()%>"><%=opdTemplate.getTemplateName()%></option>
					<%
		  // }
		  }%> --%>
				</select>
			</div>


<div id="templateList">


</div>
</form>
</div>

<div class="Clear"></div>
<div class="division"></div>
<div class="Clear"></div>

