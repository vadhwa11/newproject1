<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<meta http-equiv="X-UA-Compatible" content="IE=8" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<!-- 
Code for Logout after session Expired
Code By Mukesh Narayan Singh
Date 19 Aug 2010
 -->

<link href="resources/css/styleicg.css" rel="stylesheet" type="text/css" />
<link href="resources/css/datepickericg.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="resources/js/commonicg.js?n=1"></script>
<script type="text/javascript" language="javascript" src="resources/js/hmsicg.js?n=1"></script>
<script type="text/javascript" language="javascript" src="resources/js/calendaricg.js?n=1"></script>


<!--[if IE 8]>
        <link href="/hms/jsp/css/ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!-- script for fixed header table starts -->
<!--[if IE 9]>
<link href="/hms/jsp/css/ie.css" rel="stylesheet" type="text/css" />
<![endif]-->

<!--[if IE]>
<style type="text/css">
/* IE Specific Style addition to constrain table from automatically growing in height */
div.TableContainer {
 height: auto; 
 overflow-x:hidden;
 overflow-y:auto;
}
</style>
<![endif]-->
<script>
// Function to scroll to top before sorting to fix an IE bug
// Which repositions the header off the top of the screen
// if you try to sort while scrolled to bottom.
function GoTop() {
 document.getElementById('TableContainer').scrollTop = 0;
}

// For those browsers that fully support the CSS :hover pseudo class the "table.scrollTable tr:hover" definition above 
// will work fine, but Internet Explorer 6 only supports "hover" for "<a>" tag elements, so we need to use the following 
// JavaScript to mimic support (at least until IE7 comes out, which does support "hover" for all elements)

// Create a JavaScript function that dynamically assigns mouseover and mouseout events to all 
// rows in a table which is assigned the "scrollTable" class name,  in order to simulate a "hover" 
// effect on each of the tables rows
HoverRow = function() {

 // If an IE browser
 if (document.all) {
  var table_rows = 0;
	
  // Find the table that uses the "scrollTable" classname
  var allTableTags=document.getElementsByTagName("table"); 
  for (i=0; i<allTableTags.length; i++) { 
   // If this table uses the "scrollTable" class then get a reference to its body and first row
   if (allTableTags[i].className=="scrollTable") { 
    table_body = allTableTags[i].getElementsByTagName("tbody");
    table_rows = table_body[0].getElementsByTagName("tr");
    i = allTableTags.length + 1; // Force an exit from the loop - only interested in first table match
   } 
  } 

  // For each row add a onmouseover and onmouseout event that adds, then remove the "hoverMe" class
  // (but leaving untouched all other class assignments) to the row in order to simulate a "hover"
  // effect on the entire row
  for (var i=0; i<table_rows.length; i++) {
   // ignore rows with the title and total class assigned to them
   if (table_rows[i].className != "title" && table_rows[i].className != "total") {
    table_rows[i].onmouseover=function() {this.className += " hoverMe";}
    table_rows[i].onmouseout=function() {this.className=this.className.replace(new RegExp(" hoverMe\\b"), "");}
   }
  } // End of for loop
  
 } // End of "If an IE browser"

}
// If this browser suports attaching events (IE) then make the HoverRow function run on page load
// Hote: HoverRow has to be re-run each time the table gets sorted
if (window.attachEvent) window.attachEvent("onload", HoverRow);

function ajaxFunctionForShowCalculator() 
{
  var xmlHttp;
  try {
    // Firefox, Opera 8.0+, Safari
    xmlHttp=new XMLHttpRequest();
  }catch (e){
    // Internet Explorer
    try{
      xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
    }catch (e){
    	alert(e)
      try{
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
      }catch (e){
        alert("Your browser does not support AJAX!");
        return false;
      }
     }
   }
   window.open('/hms/hms/login?method=showCalculator','mywindow','target="_blank", width=275,height=200');;
   
}

</script>

<!-- script for fixed header table ends -->
<script type="text/javascript">


function disableKey(event) {

  if (!event) event = window.event;

  if (!event) return;

  var keyCode = event.keyCode ? event.keyCode : event.charCode;

  //window.status = keyCode;

  // keyCode for F% on Opera is 57349 ?!  

  if (keyCode == 116) {

   window.status = "F5 key detected! Attempting to disabling default response.";

   window.setTimeout("window.status='';", 2000);

   // Standard DOM (Mozilla):

   if (event.preventDefault) event.preventDefault();

   //IE (exclude Opera with !event.preventDefault):

   if (document.all && window.event && !event.preventDefault) {

     event.cancelBubble = true;

     event.returnValue = false;

     event.keyCode = 0;

   }

   return false;

  }

  if (event.keyCode == 27) return false; 

}

 

document.onkeydown = disableKey; // register listener function  

</script>
<script language=JavaScript>


//Disable right mouse click Script
//By Maximus (maximus@nsimail.com) w/ mods by DynamicDrive
//For full source code, visit http://www.dynamicdrive.com

var message="Function Disabled!";

///////////////////////////////////
function clickIE4(){
if (event.button==2){
alert(message);
return false;
}
}

function clickNS4(e){
if (document.layers||document.getElementById&&!document.all){
if (e.which==2||e.which==3){
alert(message);
return false;
}
}
}

if (document.layers){
document.captureEvents(Event.MOUSEDOWN);
document.onmousedown=clickNS4;
}
else if (document.all&&!document.getElementById){
document.onmousedown=clickIE4;
}

//document.oncontextmenu=new Function("alert(message);return false")


</script>
</head>
<body>
<div id="body">
<form name="header" method="post"><!--header Starts-->
<div class="header">

<div class="hdText">
<!-- <img src="/hms/jsp/images/careIsLogo.gif" class="floatRight" alt="careIs Logo" /> -->

<div class="dateTimeDiv">

<span>

<script type="text/javascript">

function getNoticeData(){
//alert("hi");
	var xmlHttp;
  	try {
    	// Firefox, Opera 8.0+, Safari
    	xmlHttp=new XMLHttpRequest();
  	}catch (e){
	    // Internet Explorer
    	try{
      	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
    }catch (e){
    	alert(e)
      try{
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
      }catch (e){
        alert("Your browser does not support AJAX!");
        return false;
      }
     }
   }

	xmlHttp.onreadystatechange=function()
    {
    	if(xmlHttp.readyState==4)
    	{
    	  
    		var notice =xmlHttp.responseXML.getElementsByTagName('notice')[0];
    		  var desc  = notice.getElementsByTagName("desc")[0];
    		
    		
    		//alert("notice value::::"+desc.childNodes[0].nodeValue);
    		if(desc.childNodes[0].nodeValue!='nodesc'){
    			document.getElementById('noticeLabel').value = desc.childNodes[0].nodeValue;
    		}else{
    			document.getElementById('noticeLabel').value = '';
    		}
    		//document.getElementById('noticeLabel').value = desc.childNodes[0].nodeValue; 
	 		//document.getElementById("noticeDiv").innerHTML = '<marquee>'+noticeData+'</marquee>' ;
	 		  
	 	}
    }
	//alert('hello');
   	 var url='/hms/hms/login?method=getNoticeData';
     
    xmlHttp.open("GET",url,true);
    xmlHttp.setRequestHeader("Content-Type", "text/xml");
    xmlHttp.send(null);
}


/*
var currentDate = new Date()
var day = currentDate.getDate()
var month = currentDate.getMonth()
var year = currentDate.getFullYear()
document.write(day + "/" + month + "/" + year)	
*/
function getCalendarDate()
{
var months = new Array(13);
months[0]  = "January";
months[1]  = "February";
months[2]  = "March";
months[3]  = "April";
months[4]  = "May";
months[5]  = "June";
months[6]  = "July";
months[7]  = "August";
months[8]  = "September";
months[9]  = "October";
months[10] = "November";
months[11] = "December";


var monthname   = 'January';
var monthday    = '13';
var year        = '2019';

if(year < 2000) { year = year + 1900; }
var dateString = monthname + ' ' + monthday + ', ' + year;
return dateString;
}						

var calendarDate = getCalendarDate();
document.write(calendarDate);	
</script> </span>
<span>  <script type="text/javascript">


document.write('18' + ":" + '43' + " " + 'PM')
//-->
</script> </span>
</div>
<div class="hdTextFix"> Super  User <br /> Dept : <span style="color:#fff; font-size:12px;">CHILDREN WARD</span> 


</div>
<div class="homeLoginDiv">
<span>   <a href="#" onclick="submitForm('header','/hms/hms/login?method=showHomeJsp')" class="home-icon"></a>  </span>
<!-- <a href="http://www.maintguru.iaf.in/forum" target="_blank">Forum</a> | -->
	<!-- <a	href="#" target="blank">Contact</a> -->
	 <a href="#" name="logout"
	onclick="submitForm('header','/hms/hms/login?method=logout')" class="logout-icon"></a>

</div>
</div>
<!---header text ends--->



 <div class="hName">ICG Hospital Management System
</div>
 <!-- 
<input type="button" value="Calculator" name="Button" onclick="ajaxFunctionForShowCalculator();"/>
 -->
<div class="clear"></div>
<input type="hidden" id="notice" name="notice" value="" />
<!--  <div>
  <marquee direction="left">
<input type="text" readonly="readonly" id="noticeLabel" value=""  /></marquee>

</div>-->
</div>
</div>

<!--header Ends-->
<div class="clear"></div>
<script type="text/javascript">
	window.setInterval('getNoticeData()',5000000);
</script></form>

<div class="clear"></div>






<script type="text/javascript" language="javascript"src="/hms/jsp/js/common.js"></script>
<form name="defaultMenu"  action="" method="post">
<div id="main">
<div id="menuIcon" >


<a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showReceptionDefaultJsp&csrfToken=903a720b-52d0-4bac-8817-31d8ceebc976')" class="menuIconBg" > 
<!-- <a href="#" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true"  htmlEscape="true" onclick="submitForm('default','/hms/hms/login?method=showModuleDefaultJsp&moduleName=Reception&appId=A3')" >-->
<img src="resources/imagessss/reception.png" height="80" class="fade" />
<br />Reception
</a>

<a href="#"  onclick="location.reload();location.href='opdWaitingList.jsp'" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="resources/imagessss/opd.png" height="80" class="fade" />
<br />OPD
</a>

<!-- <a href="#"  onclick="submitForm('defaultMenu','/hms/hms/login?method=showDispensaryDefaultJsp','checkDeptDisp')" --> 
	<a href="${pageContext.request.contextPath}/dispensary/showDispensary" )"
	class="menuIconBg"	onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" 
	onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="resources/imagessss/dispensary.png" height="80" class="fade" />
<br />Dispensary
</a>

<!-- <a href="#" onClick="submitForm('defaultMenu','/hms/hms/login?method=showStoresDefaultJsp','checkDeptStores')" --> 
	<a href="${pageContext.request.contextPath}/stores/showStores" )"
	class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" 
	onMouseout="JavaScript:window.status=''; return true" htmlEscape="true"/>
<img src="resources/imagessss/med-store.png" height="80" class="fade" />
<br />Stores
</a>



<a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showLabDefaultJsp&csrfToken=903a720b-52d0-4bac-8817-31d8ceebc976','checkDeptLab');" class="menuIconBg"   onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true"/>
<img src="resources/imagessss/laboratory.png" height="80" class="fade" />
<br />Laboratory
</a>

<br />
<div class="Clear"></div>

<a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showRadioDefaultJsp','checkDeptRadio')" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="resources/imagessss/radiology.png" height="80" class="fade" />
<br />Radiology
</a>



<a href="#" onClick="submitForm('defaultMenu','/hms/hms/login?method=showWardDefaultJsp')" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">



<img src="resources/imagessss/ward.png" height="80" />
<br />Ward
</a>



<a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showOTDefaultJsp')" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="resources/imagessss/ot.png" height="80" class="fade" />
<br />OT
</a>



<a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showLaborDefaultJsp')" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="resources/imagessss/labor-room.png" height="80" class="fade" />
<br />Labor Room
</a>


<a href="#" class="menuIconBgDisable">
<img src="resources/imagessss/ficility-mgmtDisb.png" height="80" />
<br />Facility Mgmt
</a>

<div class="Clear"></div>
<br />

<a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showReferralDefaultJsp')" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="resources/imagessss/referral.png" height="80" class="fade" />
<br />Referral
</a> 




<a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showBillingDefaultJsp')" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="resources/imagessss/billing.png" height="80" class="fade" />
<br />Billing
</a> 



<!-- <a href="#"  onclick="submitForm('defaultMenu','/hms/hms/login?method=showMastersDefaultJsp')" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="resources/imagessss/masters.png" height="80" class="fade"/>
<br />Masters
</a> -->

<!-- code change by rajdeo -->
<!-- onclick="location.reload();location.href='opdWaitingList.jsp'" -->
<!-- <a href="#" onclick="location.reload();location.href='showCmd.jsp'")" -->
<a href="${pageContext.request.contextPath}/v0.1/master/masterModule" )"
	class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" 
	onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="resources/imagessss/masters.png" height="80" class="fade"/>
<br />Masters
</a>



 <a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showAdminDefaultJsp')" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true"> 
<img src="resources/imagessss/admin.png" height="80" />
<br />Admin
</a> 



<!-- <a href="#" class="menuIconBgDisable">
<img src="/hms/jsp/images/menuIcons/aviation_medicineDisb.png" height="80" />
<br />Aviation Medicine
</a> -->



<!-- <a href="#" class="menuIconBgDisable">
<img src="/hms/jsp/images/menuIcons/dentalDisb.png" height="80" />
<br />Dental
</a> -->



<!-- <a href="#" class="menuIconBgDisable">
<img src="/hms/jsp/images/menuIcons/educationsDisb.png" height="80" />
<br />SHO
</a> -->



<!-- <a href="#" class="menuIconBgDisable">
<img src="/hms/jsp/images/menuIcons/familyDisb.png" height="80" />
<br />Family Welfare Centre
</a> -->




<!-- <a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showECGDefaultJsp','checkDeptECG')" class="menuIconBg" onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="/hms/jsp/images/menuIcons/ecg-room.png" height="80"   />
<br />ECG Room
</a> -->



 <a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showWardPharmacyDefaultJsp','checkDeptWard')" class="menuIconBg" 
 	onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="resources/imagessss/dispensary.png" height="80"   />
<!-- <a href="#" class="menuIconBgDisable">
<img src="/hms/jsp/images/menuIcons/dispensaryDisb.png" height="80" /> -->
<br />Ward Pharmacy
</a>

<a href="#" class="menuIconBgDisable">
<img src="resources/imagessss/accountsDisb.png" height="80" />
<br />Accounts
</a> 


<a href="#" class="menuIconBgDisable">
<img src="resources/imagessss/payrollDisb.png" height="80" />
<br />Payroll
</a> 

<!-- <a href="#" onclick="submitForm('defaultMenu','/hms/hms/login?method=showModuleDefaultJsp&moduleName=Non Expendable&appId=A218')"  class="menuIconBg"   onMouseover="JavaScript:window.status='Status Bar Message goes here'; return true" onMouseout="JavaScript:window.status=''; return true" htmlEscape="true">
<img src="/hms/jsp/images/menuIcons/noe-exp.png" height="80" />
<br />Non Expendable
</a> -->

<div class="clear"></div>
</div>
<div class="clear"></div>
</div>
</form>
<script>
function checkDeptLab(){
		var loginDeptType='WARD';
		if(loginDeptType == 'DIAG'){
		return true;
	}else{
		alert("Unauthorized user department. Please change the department.");
		return false;
	}
}

function checkDeptRadio(){
		var loginDeptType='WARD';
	if(loginDeptType == 'RADIO'){		
		return true;
	}else{
		alert("Unauthorized user department. Please change the department.");
		return false;
	}
}

function checkDeptDisp(){
	var loginDeptCode='WD';
if(loginDeptCode == 'Disp'){		
	return true;
}else{
	alert("Unauthorized user department. Please change the department.");
	return false;
}
}


function checkDeptWard(){
	var loginDeptCode='WD';
if(loginDeptCode == 'wp'){		
	return true;
}else{
	alert("Unauthorized user department. Please change the department.");
	return false;
}
}

function checkDeptStores(){
	var loginDeptCode='WD';
if(loginDeptCode == 'ExpStr'){		
	return true;
}else{
	alert("Unauthorized user department. Please change the department.");
	return false;
}
}

function checkDeptECG(){
	var loginDeptType='WARD';
	if(loginDeptType == 'ECG'){
		return true;
	}else{
		alert("Unauthorized user department. Please change the department.");
		return false;
	}
}

function checkDeptAccounts(){
	var loginDeptType='WARD';
	if(loginDeptType == 'Accounts'){
		return true;
	}else{
		alert("Unauthorized user department. Please change the department.");
		return false;
	}
}

function checkDeptPayroll(){
	var loginDeptType='WARD';
	if(loginDeptType == 'Payroll'){
		return true;
	}else{
		alert("Unauthorized user department. Please change the department.");
		return false;
	}
}

</script>

<script type="text/javascript">
var formlist = document.getElementsByTagName("form");
	if(formlist.length > 0)
	{
		for(var i=0; i<formlist.length; i++)
		{
			var ele = document.createElement("input");
   		ele.setAttribute("id","csrfToken");
   		ele.setAttribute("name","csrfToken");
   		ele.setAttribute("type","hidden");
   		ele.setAttribute("value","903a720b-52d0-4bac-8817-31d8ceebc976");
   		
			formlist[i].appendChild(ele);
   	}
}
//var prev_handler = window.onload;
window.onload = function(){
	//if (prev_handler) {
       // prev_handler();
    //}
   	var formlist = document.getElementsByTagName("form");
   	if(formlist.length > 0)
   	{
   		for(var i=0; i<formlist.length; i++)
   		{
   			var ele = document.createElement("input");
       		ele.setAttribute("id","csrfToken");
       		ele.setAttribute("name","csrfToken");
       		ele.setAttribute("type","hidden");
       		ele.setAttribute("value","903a720b-52d0-4bac-8817-31d8ceebc976");
       		
   			formlist[i].appendChild(ele);
       	}
    }
}
</script>
<div class="clear"></div>
</div>
<div class="clear"></div>


<div class="clear paddingTop15"></div>



<div id="loginFooterWrapper">Disclaimer:This site is best viewed with a resolution of 1024 x 768 (or higher) and supports Microsoft IE9, Chrome 23, Firefox 16.</div>


</div>
<!-- body div closes -->
</body>


</html>
