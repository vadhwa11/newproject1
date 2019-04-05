
<%@ page import="com.asha.icgweb.utils.ProjectUtils"%>
<%@ page import="java.util.*"%>
<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<link href="/AshaWeb/resources/css/stylecommon.css" rel="stylesheet" type="text/css">

<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/gridForPatient.js"></script>
<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/hms.js"></script>
<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pops_global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pops_menu.js"></script>

<script type="text/javascript" language="javascript"src="${pageContext.request.contextPath}/resources/js/proto.js"></script>
<%--For AutoComplete Through Ajax--%>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/prototype.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/divideprototype.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/opd.js" type="text/javascript"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/hms.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scriptaculous.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/unittest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.common.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<!-- <script type="text/javascript" src="/hms/jsp/js/phase2/ddaccordion.js">
/***********************************************
* Accordion Content script- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
* Visit http://www.dynamicDrive.com for hundreds of DHTML scripts
* This notice must stay intact for legal use
***********************************************/
</script>-->

<!-- <script type="text/javascript">
animatedcollapse.addDiv('slide0', 'fade=0,speed=400,group=pets')
animatedcollapse.addDiv('slide1', 'fade=0,speed=400,group=pets')
animatedcollapse.addDiv('slide2', 'fade=0,speed=400,group=pets,persist=1,hide=1')
animatedcollapse.addDiv('slide3', 'fade=0,speed=400,group=pets,persist=1,hide=1')
animatedcollapse.init()

</script> -->

<%
Map<String,Object> map = new HashMap<String,Object>();
if(request.getAttribute("map") != null){
	map = (Map) request.getAttribute("map");
}
Map<String,Object> utilMap = new HashMap<String,Object>();
utilMap = (Map)ProjectUtils.getCurrentDateAndTime();
String date = (String)utilMap.get("currentDate");	 
String time = (String)utilMap.get("currentTime");


/* String userName = "";
String deptName="";
int deptId = 0;
if(session.getAttribute("userName") != null){
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
} */
%>

<div class="popupbg">

<div class="titleBg"><h2>Opd Template</h2></div>
<div class="clear"></div>
<h4></h4>
<div class="Clear"></div>
<script type="text/javascript">
	   var icdArray=new Array();
	     var icdArrayForInstruction=new Array();
	     var itemClassCodeArray = new Array();
	</script> 
	
<script type="text/javascript" language="javascript">

function addRow(){
	
	  var tbl = document.getElementById('grid');
	  var lastRow = tbl.rows.length;
	 
	  // if there's no header row in the table, then iteration = lastRow + 1
	  var iteration = lastRow;
	  var row = tbl.insertRow(lastRow);
	  var hdb = document.getElementById('hdb');
	  hdb.value=iteration
 
	  var cellRight0 = row.insertCell(0);
	  var e0 = document.createElement('input');
	  e0.type = 'text';
	 // e0.innerHTML = iteration+':'
	 /*  e0.onblur=function(){
	                       var val=e0.value
	                       if(val != "")
							{
					    	
						    var index1 = val.lastIndexOf("[");
						    var indexForPvms=index1;
						    var index2 = val.lastIndexOf("]");
						    index1++;
						    var pvmsNo = val.substring(index1,index2);
						    var indexForPvms=indexForPvms--;
						    var nomenclature=val.substring(0,indexForPvms);
						   	 if(pvmsNo =="")
						    {
						    		document.getElementById('nomenclature'+iteration).value="";
	   								document.getElementById('pvmsNo'+iteration).value="";
						     return;
						    }
						    else */
	      						//document.getElementById('pvmsNo'+iteration).value=pvmsNo
	      						
	      						
	      						//populatePVMS(this.value,iteration);displayAu(this.value,iteration,'0');checkForPurchase(this.value,iteration);
	      						
	      						
	      				
	  e0.name = 'nomenclature' + iteration;
	  e0.id = 'nomenclature' + iteration;
	
	  e0.size = '43';
	  e0.setAttribute('validate','Nomenclature,String,yes');
	  cellRight0.appendChild(e0);
	  
		var e01 = document.createElement('input');
		  e01.type = 'hidden';
		  e01.name = 'itemId' + iteration;
		  e01.id = 'itemId' + iteration;
		  e0.focus();
		  cellRight0.appendChild(e01);
		  
	 // new Ajax.Autocompleter('nomenclature'+iteration,'ac2update1','opd?method=getItemListForAutoCompleteItem',{parameters:'requiredField=nomenclature'+iteration});
	  
	  
	  var cellRightSel = row.insertCell(1);
	  var sel = document.createElement('input');
	  sel.type = 'text';
	  sel.name='pvmsNo'+iteration;
	  sel.id='pvmsNo'+iteration
	  sel.size = '10'
	  sel.setAttribute('validate','Material Code,String,yes');
	  cellRightSel.appendChild(sel);
	 
	  var e15 = document.createElement('input');
	  e15.type = 'hidden';
	  e15.name='highValueMedicine'+iteration;
	  e15.id='highValueMedicine'+iteration
	  e15.size='1';
	  e15.setAttribute('tabindex','1');
	  cellRightSel.appendChild(e15);
	  
	  
	  var e016 = document.createElement('input');
	  e016.type = 'hidden';
	  e016.name='itemClassCode'+iteration;
	  e016.id='itemClassCode'+iteration
	  e016.size='6';
	  e016.setAttribute('tabindex','1');
	  cellRightSel.appendChild(e016);
	  
	  var cellRight2 = row.insertCell(2);
	  var e2 = document.createElement('input');
	  e2.type = 'text';
	  e2.name='dosage'+iteration;
	  e2.id='dosage'+iteration
	  e2.size='5'
      e2.setAttribute('maxlength', 5); 
	  e2.setAttribute('validate','Dosage,int,yes');
	  e2.onblur = function(){checkDosageValidation(this.value,iteration);fillValue(iteration)};
	  cellRight2.appendChild(e2); 
	  
	  var cellRight3 = row.insertCell(3);
	  var e3 = document.createElement('Select');
	  e3.name='frequency'+iteration;
	  e3.id='frequency'+iteration;
	  e3.classname='smalllabel'
	  e3.setAttribute('validate','Frequency,String,yes');
	  e3.onblur=function(){getFrequencyValue(this.value,iteration);fillValueFromFrequency(this.value,iteration);displaySOSQty(this.value,iteration);fillValue(iteration)};
 
	   e3.options[0] = new Option('Select', '0');
	   for(var i = 0;i<icdArray.length;i++ ){
	      e3.options[i+1] = new Option(icdArray[i][1],icdArray[i][0]);
	   }
	  cellRight3.appendChild(e3); 
	  
	  var e31 = document.createElement('input');
	  e31.type = 'hidden';
	  e31.name='actualDispensingQty'+iteration;
	  e31.id='actualDispensingQty'+iteration
	  e31.size='6';
	  e31.setAttribute('tabindex','1');
	  cellRight3.appendChild(e31); 
	  
	  
	  var e32 = document.createElement('input');
	  e32.type = 'hidden';
	  e32.name='frequencyValue'+iteration;
	  e32.id='frequencyValue'+iteration;
	  e32.size='5';
	  e32.setAttribute('tabindex','1');
	  cellRight3.appendChild(e32);
	  
	  var e33 = document.createElement('input');
		e33.type = 'text';
		e33.name='sosQty'+iteration;
		e33.id='sosQty'+iteration;
		e33.tabIndex='1';
		e33.size='3';
		e33.style.display='none';
		e33.setAttribute('maxlength', 3); 
	    e33.onblur=function(){fillValue(iteration)};
		cellRight3.appendChild(e33);
	  
		 var e34 = document.createElement('input');
		  e34.type = 'hidden';
		  e34.name='frequencyValue'+iteration;
		  e34.id='frequencyValue'+iteration;
		  e34.size='5';
		  e34.setAttribute('tabindex','1');
		  cellRight3.appendChild(e34);
	  
	  
	   var cellRight4 = row.insertCell(4);
	  var e4 = document.createElement('input');
	  e4.type = 'text';
	  e4.name='instruction'+iteration;
	  e4.id='instruction'+iteration
	  e4.size='10';
	  e4.setAttribute('placeholder','1-1-1');
	  e4.setAttribute('maxlength', 15); 
	  e4.setAttribute('tabindex','1');
	  cellRight4.appendChild(e4);
	  
	  var cellRight5 = row.insertCell(5);
	  var e5 = document.createElement('input');
	  e5.type = 'text';
	  e5.name='noOfDays'+iteration;
	  e5.id='noOfDays'+iteration;
	  e5.size='7'
	  e5.setAttribute('maxlength', 3); 
	  e5.setAttribute('validate','No Of Days,int,yes');
	  
	  e5.onblur=function(){
	  							
	  							fillValue(iteration);
	  						}
	  cellRight5.appendChild(e5);
	  
	  var cellRight6 = row.insertCell(6);
	  var e7 = document.createElement('input');
	  e7.type = 'text';
	  e7.name='total'+iteration;
	  e7.id='total'+iteration;
	  e7.size='3'
	  e7.setAttribute('validate','Total,num,no');
	  cellRight6.appendChild(e7);
	  

	  var cellRight8 = row.insertCell(7);
	  var e8 = document.createElement('input');
	  e8.type = 'button';
	  e8.className = 'buttonAdd';
	  e8.value='',
	  //e8.name='remark';
	  //e8.id='';
	  //e8.size='10'
	  e8.setAttribute('tabindex', 1);
	  e8.setAttribute('onClick','addRow();'); 
	  cellRight8.appendChild(e8);

	  
	  var cellRight9 = row.insertCell(8);
	  var e9 = document.createElement('input');
	  e9.type = 'button';
	  e9.value='',
	  e9.className = 'buttonDel';
	  e9.setAttribute('tabindex', 1);
	  e9.setAttribute('onClick','removeRow();');
	
	  cellRight9.appendChild(e9); 
	  
}
	                       
function populatePVMS(val,inc){
	if(val != "")
	{
	    var index1 = val.lastIndexOf("[");
	    var indexForBrandName=index1;
	    var index2 = val.lastIndexOf("]");
	    index1++;
	    var pvmsNo = val.substring(index1,index2);
	    var indexForBrandName=indexForBrandName--;
	    var brandName=val.substring(0,indexForBrandName);
 
  if(pvmsNo == "")
  {
   	document.getElementById('nomenclature'+inc).value="";
    document.getElementById('pvmsNo'+inc).value="";
   return;
   }
   else
      document.getElementById('pvmsNo'+inc).value=pvmsNo
 }
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

function removeRow()
{
  var tbl = document.getElementById('grid');
  var lastRow = tbl.rows.length;
  if (lastRow > 2) tbl.deleteRow(lastRow - 1);
}	
	
</script>


<form name="prescriptionTemplate" method="post" action="">
<input type="hidden" name=" " value="OpdTemplate">
<input type="hidden" name=" " value="TemplateName">
<input type="hidden" name="title" value="OpdTemplate">
<input type="hidden" name=" " value="opdTemplate">
<input type="hidden" name="pojoPropertyCode" value="TemplateCode">
<div class="Block">
<label> Template Code </label>
 <input type="text"	name=" " id="code" value="" disabled="true"	readonly="readonly" validate="Template Code,string,yes" MAXLENGTH="8"	tabindex=1 />
 <label><span>*</span> Template Name</label>
 <input type="text" name=" " id=" " value=""onblur="createTemplateCode();"  validate="Template Name,string,yes" MAXLENGTH="30" tabindex=1 />
   <script>
	document.prescriptionTemplate.focus();
</script>
</div>
<div id="testDiv">

<div class="Clear"></div>

	

<table border="0" align="center" cellpadding="0" cellspacing="0" id="grid">
	<tr>
		<th scope="col">Nomenclature</th>
		<th scope="col">Material Code</th>
		<th scope="col">Dosage</th>
		<th scope="col">Frequency</th>
		<th scope="col">Instruction</th>
		<th scope="col">No. of Days</th>
			<th scope="col">Total</th>
		<!-- <th scope="col">Remark</th> -->
		<th>Add</th>
		<th>Delete</th>
	</tr>
	<tr>
	<td>
	 <input type="text" value="" tabindex="1" id="nomenclature1"size="43" validate="Nomenclature,string,yes" 
			name="nomenclature1" onblur="populatePVMS(this.value,'1');displayAu(this.value,'1','0');checkForPurchase(this.value,'1');" tabindex="1"/>
			<input type="hidden" name="itemId1" id="itemId1" value="" />
		
			
		<td><input type="text" name="pvmsNo1" tabindex="1" id="pvmsNo1"	validate="MET No.,string,yes" size="10" readonly="readonly" />
		<input type="hidden"
						name="highValueMedicine1" tabindex="1"
						id="highValueMedicine1" value="" size="1"
						validate="High value Medicine,string,no" />
						
	<input type="hidden" tabindex="1" id="itemClassCode1" name="itemClassCode1" validate="itemClassCode,string,no" value="" />
							
							
		</td>
		<td><input type="text" name="dosage1" tabindex="1" id="dosage1"	validate="Dosage,string,yes" size="5" onblur="fillValue('1')" maxlength="5" /></td>
		<td><select name="frequency1" id="frequency1" validate="Frequency,string,yes"
			onchange="getFrequencyValue(this.value,'1');fillValueFromFrequency(this.value,'1');displaySOSQty(this.value,'1');fillValue('1')" tabindex="1">
			<option value="0">Select</option>
			
		<%-- 	<option value="> </option>
		
		</select>  
		
		<td><input type="text" name="noOfDays1" tabindex="1" id="noOfDays1" onblur="fillValue(1)" size="7" maxlength="3" validate="No Of Days,int,yes" /></td>
		<td><input type="text" name="total1" tabindex="1" id="total1" size="3" validate="total,num,no"/><input type="hidden" name="hdb" value="1" id="hdb" /></td>
		
		<!-- <td><input type="text" name="remarks1" tabindex="1" id="remarks1" size="10" maxlength="40"/>
		</td> -->
			<td><input type="button" tabindex="1" class="buttonAdd" alt="Add" onclick="addRow();" value="" align="right" /></td>
			<td><input type="button" tabindex="1"	class="buttonDel" alt="Delete" value="" onclick="removeRow();" /></td>
	</tr>
</table>
</div>
<div class="Clear"></div>
<div class="division"></div>
<div class="Clear"></div>

<input type="button" name="add" id="addbutton" value="Save"	class="button" onClick="submitWindow();" accesskey="a" tabindex=1 />
<input	type="reset" name="Reset" id="reset" value="Reset" class="button"	onclick="resetCheck();" accesskey="r" />
<input	type="reset" name="Reset" id="reset" value="Close" class="button"	onclick="closeWindow();" accesskey="r" />
<!--  <input type="button" name="close" id="addbutton" value="close" class="button"	onClick="closeWindow();" accesskey="a" tabindex=1 />
-->
<div class="Clear"></div>
<div class="division"></div>
<div class="Clear"></div>
<div class="bottom">


<label>Changed Date</label>
<label class="value"><%=date%></label>
<label>Changed Time</label>
<label class="value"><%=time%></label>
<input type="hidden" name=" " value=" " />
<input type="hidden" name=" " value=" " />
<input type="hidden" name=" " value=" " />
<div class="Clear"></div>
<div id="edited"></div>
</div>
</form>
</div>


<style>
.Block, .division{width:988px!important;}
.Block input {height:24px;}
</style> --%>