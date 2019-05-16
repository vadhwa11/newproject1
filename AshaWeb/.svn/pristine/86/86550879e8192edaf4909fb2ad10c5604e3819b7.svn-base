<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
 <link href="resources/css/stylecommon.css" rel="stylesheet" type="text/css" />
  <link href="resources/css/datepickericg.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />

<link href="<%=request.getContextPath()%>/resources/css/dataTables.bootstrap.min.css" rel="stylesheet" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/dataTables.bootstrap.min.js"></script>


</head>

<body>
<form name="opdPatientList" method="post" action="">
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



 <div class="hName">Capture OPD Pre-Consulation Assements</div>

<div >


<label> Department</label> <input type="text" name="serviceNo"
	id="employeeId" value="1" value="" MAXLENGTH="30" validate="Service No,metachar,no" />

<div class="clear"></div>
</div>

<div>
<table class="table table-striped table-bordered" style="width:100%" id ="tableid">
                      <thead>
                        
                        <tr>
                          
                          <th>Token No.</th>
                          <th>Service No.</th>
                          <th>Patient Name</th>
                          <th>Name</th>
                          <th>Gender</th>
                          <th>Age</th>
                          <th>Relation</th>
                          <th>Doctor Name</th>
                          <th>Department Name</th>
                          <th>Status</th>
                          <th>Priority</th>
                          <th style=display:none; >VisitId</th>  
                          <th style=display:none; >PatientId</th>
                          

                          
                          
                        </tr>
                      </thead>
                      <tbody  id="responseData">
                       
                    
                      </tbody>
                    </table>
 <script>               
 $(document).ready(
		function() {
			var pathname = window.location.pathname;
			var accessGroup = pathname.split("/")[1];

			var url = window.location.protocol + "//"
					+ window.location.host + "/" + accessGroup
					+ "/v0.1/opd/getPreConsPatientWatingWeb";

			//var data = $('employeeId').val();

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url,
				data : JSON.stringify({
					'employeeId' : "1"
				}),
				dataType : 'json',
				timeout : 100000,
				success : function(res) {
					console.log("SUCCESS: ", res);
					// var obj = JSON.parse(res);
					var data = res.data;
					var html = '';
					$.each(data, function(key, value) {

						html += '<tr ><td>'
								+ value.tokenNo+ '</td><td>'
								+ value.serviceNo + '</td><td>'
								+ value.patientName + '</td><td>'
								+ value.employeeName+ '</td><td>'
								+ value.gender + '</td><td>'
								+ value.age + '</td><td>'
								+ value.relation + '</td><td>'
								+ value.doctorname + '</td><td>'
								+ value.departmentName + '</td><td>'
								+ value.status + '</td><td>'
								+ value.priority + '</td><td style=display:none; >' //style=display:none;
								+ value.visitId+ '</td><td style=display:none;>'
								+ value.patientId + '</td></tr >'
									
								
								
					});
					$('#responseData').append(html);
					  $('#tableid').DataTable();

				},
				error : function(e) {

					console.log("ERROR: ", e);

				},
				done : function(e) {
					console.log("DONE");
					alert("success");
					var datas = e.data;
				}
			});
			
			$("#tableid")
			.on(
					'click',
					'tr',
					function() {
						alert("localStorage.visitId: "+ localStorage.visitId);
						var visitId =   $(this).closest('tr').find('td:eq(11)').text();
						var patientId =  $(this).closest('tr').find('td:eq(12)').text();
						var serviceNo =  $(this).closest('tr').find('td:eq(1)').text();
						var patientName =  $(this).closest('tr').find('td:eq(2)').text();
						var relation   =  $(this).closest('tr').find('td:eq(6)').text();
						var gender =  $(this).closest('tr').find('td:eq(4)').text();
						var age =  $(this).closest('tr').find('td:eq(5)').text();
						localStorage.visitId=visitId;
						localStorage.patientId=patientId;
						localStorage.serviceNo = serviceNo;
						localStorage.patientName=patientName;
						localStorage.relation=relation;
						localStorage.gender=gender;
						localStorage.age=age;
						window.location.href = "addVitalRecords"
					
						
					});
			

		});
		
		
		
</script>    		
    </div>
</body>
</html>