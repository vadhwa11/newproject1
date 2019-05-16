<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
 <link href="<%=request.getContextPath()%>/resources/css/stylecommon.css" rel="stylesheet" type="text/css" />
  <link href="<%=request.getContextPath()%>/resources/css/datepickericg.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />

<link href="<%=request.getContextPath()%>/resources/css/dataTables.bootstrap.min.css" rel="stylesheet" />

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/dataTables.bootstrap.min.js"></script>

<style>
.block {
  display: block;
  width: 100%;
  border: none;
  background-color: #4CAF50;
  color: white;
  padding: 14px 28px;
  font-size: 16px;
  cursor: pointer;
  text-align: center;
}

.block:hover {
  background-color: #ddd;
  color: black;
}
</style>
</head>

<body>

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

</div>
<!---header text ends--->



 <div class="hName"><h4>OPD Submit</h4></div>


  
  <p><button class="block">CASE Sheet</button></p>

  
  <p><button class="block">Investigation Slip</button></p>
  
  <p><button class="block">Prescription Slip</button></p>
  
  <p><button class="block" input type="submit" id="clicked">Back</button></p>
  
  

</div>

<script type="text/javascript" language="javascript">
            $('#clicked').click(function() {

                           // alert("hello")
                           window.location.href ="opdWaitingList";
                      
            });
 </script>           

</body>
</html>