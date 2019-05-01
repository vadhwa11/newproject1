<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@include file="..//view/leftMenu.jsp" %>
        <%@include file="..//view/commonJavaScript.jsp" %>

            <!DOCTYPE html>
            <html>

            <head>
                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

                        <meta charset="ISO-8859-1">
                        <title>Patient Appointment</title>
                        
                     
            </head>

            <body>

                <!--START  -->
                <div class="content-page">
                    <!-- Start content -->
                    <div class="">
                        <div class="container-fluid">
                            <div class="internal_Htext"> Personal Information</div>

                            <div class="row">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-body">
                                        <div class="form-group row">	
                                       												 
															
                                             <form:form id="bookAppointmentform" name="bookAppointmentform" method="POST" modelAttribute="patientDetail">
                                            <!-- -------------  Patient Appointment end here -->

                                            <div class="row">

                                                <div class="col-md-12">

                                                    
                                                        <div id="empDetails">

                                                            <div class="row">
                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label class="col-sm-5 col-form-label">Service No</label>
                                                                        <div class="col-sm-7">
                                                                       <input class="border-hide "  type="text" id="sNo" name="serviceNo" value="${patientDetail.serviceNo}" readonly>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label class="col-sm-5 col-form-label">Full Name </label>
                                                                        <div class="col-sm-7">
                                                                           <input class="border-hide "  type="text" id="empName" name="fullName" value="${patientDetail.fullName}" readonly>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label class="col-sm-5 col-form-label">Relation</label>
                                                                        <div class="col-sm-7">
                                                                            <input class="border-hide "  type="text" id="relation" name="relation" value="${patientDetail.relation}" readonly>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label class="col-sm-5 col-form-label">Date Of Birth </label>
                                                                        <div class="col-sm-7">
                                                                            <input class="border-hide "  type="text" id="dob" name="dateOfBirth" value="${patientDetail.dateOfBirth}" readonly>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label class="col-sm-5 col-form-label">Gender</label>
                                                                        <div class="col-sm-7">
                                                                           <input class="border-hide "  type="text" id="gender" name="gender" value="${patientDetail.gender}" readonly>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <input type="hidden" name="genderId" value="${patientDetail.genderId}">
                                                                <input type="hidden" id="uhidNo" name="uhidNo" value="${patientDetail.uhidNo}">
                                                                <input type="hidden" name="relationId" value="${patientDetail.relationId}">
                                                                <input type="hidden" name="empId" value="${patientDetail.empId}">
                                                                <input type="hidden" id="tokenNo" name="tokenIds" value="">
                                                                
                                                                	<!-- -------------  Patient Appointment end here -->
										 <input type="hidden" id="priorityId" name="priorityId" value="3">
										 <input type="hidden" id="empService" name="empService" value="${patientDetail.empService}">
										 <input type="hidden" id="registrationTypeId" name="registrationTypeId" value="1">
										 <input type="hidden" id="visitFlag" name="visitFlag" value="P">
										  <input type="hidden" id="empName" name="empName" value="${patientDetail.empName}">
										 <input type="hidden" id="region" name="region" value="${patientDetail.region}">
		                                 <input type="hidden" id="regionId" name="regionId" value="${patientDetail.regionId}">
										 <input type="hidden" id="religion" name="religion" value="${patientDetail.religion}">
		                                 <input type="hidden" id="religionId" name="religionId" value="${patientDetail.religionId}">
										 <input type="hidden" id="rank" name="rank" value="${patientDetail.rank}">
		                                 <input type="hidden"  id="rankId" name="rankId" value="${patientDetail.rankId}">
										 <input type="hidden" id="empServiceJoinDate" name="empServiceJoinDate" value="${patientDetail.empServiceJoinDate}">
		
		                                 <input type="hidden" id="recordoffice" name="recordoffice" value="${patientDetail.recordoffice}">
		                                 <input type="hidden" id="totalservice" name="totalservice" value="${patientDetail.totalservice}">
										 <input type="hidden" id="recordofficeId" name="recordofficeId" value="${patientDetail.recordofficeId}">
		
		                                 <input type="hidden" id="trade" name="trade" value="${patientDetail.trade}">
		                                 <input type="hidden" id="tradeId" name="tradeId" value="${patientDetail.tradeId}">
										 <input type="hidden" id="unit" name="unit" value="${patientDetail.unit}">
		
		                                  <input type="hidden" id="unitId" name="unitId" value="${patientDetail.unitId}">
		                                 <input type="hidden" id="maritalstarus" name="maritalstarus" value="${patientDetail.maritalstarus}">
										 <input type="hidden" id="maritalstarusId" name="maritalstarusId" value="${patientDetail.maritalstarusId}">
		
		
		                                 <input type="hidden" id="patientname" name="patientname" value="${patientDetail.patientname}">
		                                 <input type="hidden" id="patientDOB" name="patientDOB" value="${patientDetail.patientDOB}">
										 <input type="hidden" id="patientAddress" name="patientAddress" value="${patientDetail.patientAddress}">
		
	
	                                     <input type="hidden" id="patientPincode" name="patientPincode" value="${patientDetail.patientPincode}">
		                                 <input type="hidden"  id="patientRelation" name="patientRelation" value="${patientDetail.patientRelation}">
										 <input type="hidden" id="patientRelationId" name="patientRelationId" value="${patientDetail.patientRelationId}">
		
		
		                                 <input type="hidden" id="patientAge" name="patientAge" value="${patientDetail.patientAge}">
		                                 <input type="hidden" id="patientCity" name="patientCity" value="${patientDetail.patientCity}">
										 <input type="hidden" id="patientEmail" name="patientEmail" value="${patientDetail.patientEmail}">
		
		
		                                 <input type="hidden" id="patientMoblienumber" name="patientMoblienumber" value="${patientDetail.patientMoblienumber}">
		                                 <input type="hidden" id="patientState" name="patientState" value="${patientDetail.patientState}">
										 <input type="hidden" id="patientStateId" name="patientStateId" value="${patientDetail.patientStateId}">
		
		                                  <input type="hidden" id="nok1Firstname" name="nok1Firstname" value="${patientDetail.nok1Firstname}">
		                                 <input type="hidden" id=nok1Address name="nok1Address" value="${patientDetail.nok1Address}">
										 <input type="hidden" id="nok1Moblienumber" name="nok1Moblienumber" value="${patientDetail.nok1Moblienumber}">
		
		                                 <input type="hidden" id="nok1Relation" name="nok1Relation" value="${patientDetail.nok1Relation}">
		                                 <input type="hidden" id="nok1RelationId" name="nok1RelationId" value="${patientDetail.nok1RelationId}">
										 <input type="hidden" id="nok1Policestation" name="nok1Policestation" value="${patientDetail.nok1Policestation}">
		
		
		                                 <input type="hidden" id="nok1Email" name="nok1Email" value="${patientDetail.nok1Email}">
		                                 <input type="hidden" id="nok1pincode" name="nok1pincode" value="${patientDetail.nok1pincode}">
										 <input type="hidden" id="nok1Contactnumber" name="nok1Contactnumber" value="${patientDetail.nok1Contactnumber}">
		
		
		                                 <input type="hidden" id="nok2Firstname" name="nok2Firstname" value="${patientDetail.nok2Firstname}">
		                                 <input type="hidden" id="nok2Address" name="nok2Address" value="${patientDetail.nok2Address}">
										 <input type="hidden" id="nok2Moblienumber" name="nok2Moblienumber" value="${patientDetail.nok2Moblienumber}">
		                                 <input type="hidden" id="nok2Relation" name="nok2Relation" value="${patientDetail.nok2Relation}">
		                                 <input type="hidden" id="nok2Policestation" name="nok2Policestation" value="${patientDetail.nok2Policestation}">
										 <input type="hidden" id="nok2Email" name="nok2Email" value="${patientDetail.nok2Email}">
		
	                                 	<input type="hidden" id="nok2Firstname" name="nok2Firstname" value="${patientDetail.nok2Firstname}">
		                                 <input type="hidden" id="nok2pincode" name="nok2pincode" value="${patientDetail.nok2pincode}">
										 <input type="hidden" id="nok2Contactnumber" name="nok2Contactnumber" value="${patientDetail.nok2Contactnumber}">
		                                 <input type="hidden" id="bloodGroup" name="bloodGroup" value="${patientDetail.bloodGroup}">
		                                 <input type="hidden" id="medicalCategory" name="medicalCategory" value="${patientDetail.medicalCategory}">
										 <input type="hidden" id="date" name="date" value="${patientDetail.date}">
										 
										 <input type="hidden" id="duration" name="duration" value="${patientDetail.duration}">
										 <input type="hidden" id="priority" name="priority" value="3">
		                                 <input type="hidden" id="checkDiv" name="checkDiv" value="${patientDetail.checkDiv}">
		                                 <input type="hidden" id="tokenNo" name="tokenNo" value="${patientDetail.tokenNo}">
		                                  
                                                            </div>
                                                            
                                                            
                                                            
                                                            
                                                            
                                                            
                                                            
                                                            
                                                            

                                                            <div class="row">

                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label for="service" class="col-md-5 col-form-label">Hospital</label>
                                                                        <div class="col-md-7">
                                                                            <select id="hospitalId" name="hospital" class="form-control">
                                                                                <option value="0" selected="selected">Select</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>

                                                                </div>

                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label for="service" class="col-md-5 col-form-label">Department</label>
                                                                        <div class="col-md-7">
                                                                            <select id="departmentId" name="departmentId" onchange="geAppointmentType();" class="form-control">
                                                                                <option value="0" selected="selected">Select</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label for="service" class="col-md-5 col-form-label"> Appointment Type</label>
                                                                        <div class="col-md-7">
                                                                            <select id="appTypeTable" name="appointmentTypeId" class="form-control">
                                                                                <option value="0" selected="selected">Select</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-4">
                                                                    <div class="form-group row">
                                                                        <label for="service" class="col-md-5 col-form-label">Appointment Date</label>
                                                                        <div class="col-md-7">
                                                                            <input type="date" name="appointmentDate" id="appointmentDate">
                                                                             
                                                                   
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                             <div class="row">

                                                               

                                                                <div class="col-md-4">
                                                                    <button class="btn btn-primary" id="appointment" type="button" onclick="showTokens();">Show Token</button>

                                                                </div>

                                                            </div>
                                                            
                                                            <div class="row">

                                                                <!-- <div class="col-md-4" id="showToken" style="display:none;">
                                                                    <div class="form-group row">
                                                                        <label for="service" class="col-md-4 col-form-label"  style="background: #1a2942;    color: #fff;"> Token.</label>
                                                                       <div >
                                                                            <div id="displayToken" ></div>
                                                                        </div> 
                                                                        
                                                                    </div>
                                                                </div> -->
                                                                <div class="col-md-8">

                                                                </div>

                                                            </div>
                                                            
                                                                 <div class="row">
                                                          <div id="displayToken">   
                                                          
                                                          </div>
                                                          
  </div>
                                                      <div class="row">

                                                                <div class="col-md-10">

                                                                </div>

                                                                <div class="col-md-2">
                                                                    <button class="btn btn-primary" id="bookAppointment" type="submit" onclick="return checkbookappointmentValidation();">Book Appointment</button>

                                                                </div>

                                                            </div>
                                                     
                                                     
                                                      </div>
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                     
                                                   
                                                </div>

                                            </div>
 </form:form>
                                        </div>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <!--End  -->

                <script>
                    $j(document).ready(function() {
                    	// $j("#appointment").hide();
                    	var now = new Date();
                    	now.setDate(now.getDate() + 1);
                    	var day = ("0" + now.getDate()).slice(-2);
                    	var month = ("0" + (now.getMonth() + 1)).slice(-2);

                    	var today = now.getFullYear()+"-"+(month)+"-"+(day) ;

                    	$('#appointmentDate').val(today);
                    	
                    	
                        var deptValues = "";
                        var bloodValues = "";
                        var categoryValues = "";

                        var dictionary = ${departmentList}
                        var deptList = dictionary.departmentList;
                        var bloodGroupList = dictionary.bloodGroupList;
                        var medicalCategoryList = dictionary.medicalCategoryList;

                        for (dept in deptList) {
                            deptValues += '<option value=' + deptList[dept].departmentId + '>' + deptList[dept].departmentName + '</option>';
                        }
                        $j('#departmentId').append(deptValues);

                    });

                    //Get Hospitallist

                    var hospitalValues = "";
                    var hospitalDict = ${hospitalList}
                    for (hospital in hospitalDict) {

                        hospitalValues += '<option name="hospitalid" value=' + hospital + '>' + hospitalDict[hospital] + '</option>';
                    }
                    $j('#hospitalId').append(hospitalValues);

                    function geAppointmentType() {

                        var deptId = $j('#departmentId').find('option:selected').val();
                        var hospitalId = $j("#hospitalId").val();
                       var params = {
                            "deptId": deptId,
                            "hospitalId":hospitalId
                        }
                        $j.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: '${pageContext.request.contextPath}/appointment/getAppointmentType',
                            data: JSON.stringify(params),
                            dataType: "json",
                            cache: false,
                            success: function(data) {
                                var appTypeValues = "";
                                if (data.status == '1') {
                                    var dataList = data.appointmentTypeList;
                                    var dataMain = JSON.parse(JSON.stringify(data));

                                    if (dataMain.appointmentTypeList.length > 0) {
                                        for (var i = 0; i < dataMain.appointmentTypeList.length; i++) {
                                            var counter = dataMain.appointmentTypeList[i];
                                            appTypeValues += '<option name="hospitalid" value=' + counter.appointmentTypeId + '>' + counter.appointmentTypeName + '</option>';
                                        }

                                        $j('#appTypeTable').append(appTypeValues);

                                    }
                                } else {
                                    $j('#appTypeTable').empty();
                                }
                            },
                            error: function(msg) {
                                alert("An error has occurred while contacting the server");
                            }
                        });

                    }

                    function getTokens() { 
                    	
                    	var uhidNo = $j("#uhidNo").val();
                    	
                    	 var appointmentDate = $j("#appointmentDate").val();
                         var tokenNo = $j('#tokenNo').val();
                         var hospitalId = $j("#hospitalId").val();
                         var departmentId = $j('#departmentId').val();
                         var appTypeTable = $j('#appTypeTable').val();
                         if (hospitalId == 0 || hospitalId == "") {
                             alert("Please select hospital.");
                             return false;
                         }
                         if (departmentId == 0 || departmentId == "") {
                             alert("Please select department.");
                             return false;
                         }
                         if (appTypeTable == 0 || appTypeTable == "") {
                             alert("Please select Appointment Type.");
                             return false;
                         }
                         if (appointmentDate == null || appointmentDate == "") {
                             alert("Please select Appointment Date.");
                             return false;
                         }
        	            var appointmentDate = $j('#appointmentDate').val();
                         var date = new Date(appointmentDate);
                         appointmentDate = ((date.getDate() + '-' + (date.getMonth() + 1)) + '-' + date.getFullYear());
                         
                    	var varDate = new Date(date); //dd-mm-YYYY
                    	var today = new Date();

                    	  if(varDate < today) {
                    	alert("Appointment date can not smaller than current date!");
                    	return false;
                    	}  
                        var arrParam = [];
                        var deptId = $j('#departmentId').find('option:selected').val();
                        var appointmentTypeId = [];
                        var appId=$j('#appTypeTable').find('option:selected').val();
                        appointmentTypeId.push(appId);
                      // appointmentTypeId = ["1"];
                       
                        var hospitalId = $j('#hospitalId').find('option:selected').val();
                        
                       
                        var params = {
                            "deptId": deptId,
                            "appointmentTypeId": appointmentTypeId,
                            "hospitalId": hospitalId,
                            "visitFlag": "P",
                            "visitDate": appointmentDate
                        }
                        arrParam.push(params);

                        var resuestData = {
                            "arrParam": arrParam
                        }

                      
                        $j.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: 'getAppointmentTokens',
                            data: JSON.stringify(resuestData),
                            dataType: "json",
                            cache: false,
                            success: function(msg) {
                                var displayToken = '';
                                var dataMain = JSON.parse(JSON.stringify(msg));
                                jQuery("#showToken").show();
                                 // if(dataMain.jsonList.length>0){
                                for (var i = 0; i < dataMain.jsonList.length; i++) {
                                    var counter = dataMain.jsonList[i];
                                    //alert("d"+counter.tokenMsg[0].tokenValue);
                                    if (typeof counter.tokenMsg[0].tokenValue !== 'undefined' || !counter.tokenMsg[0].tokenValue instanceof String) {
                                        for (var j = 0; j < counter.tokenMsg.length; j++) {
                                            //displayToken += '<input readonly onclick="setVal('+counter.tokenMsg[j].tokenValue+','+counter.tokenMsg[j].tokenValue+')" type="text" name="tokenNo" id="tokenNoId'+counter.tokenMsg[j].tokenValue+'" value="token No '+counter.tokenMsg[j].tokenValue+"-"+counter.tokenMsg[j].tokenStartTime+'" style="left-margin:50px">'; 
                                            if (counter.tokenMsg[j].tokenStatus == "available")
                                            	  displayToken += '<div id="d_button" class=" sad abc'+j+'" onclick="setVal('+ j + ',' + counter.tokenMsg[j].tokenValue + ',' + counter.tokenMsg[j].tokenValue + ')"> Token No ' + counter.tokenMsg[j].tokenValue + "-" + counter.tokenMsg[j].tokenStartTime + " to " + counter.tokenMsg[j].tokenEndTime + '</div>';
                                            else
                                                displayToken += '<div id="r_button"> Token No ' + counter.tokenMsg[j].tokenValue + "-" + counter.tokenMsg[j].tokenStartTime + " to " + counter.tokenMsg[j].tokenEndTime + '</div>';

                                        }
                                        $j('#displayToken').html(displayToken);
                                    } else {
                                        alert(counter.tokenMsg);
                                    }

                                }
                             

                            },
                            error: function(msg) {
                                alert("An error has occurred while contacting the server");
                            }
                        });
                    }

                    function setVal(count,tokenno, a) {
                    	$j('.sad').css({
                            'background-color': 'green'
                            
                        });
                    	$j('.abc'+count).css({
                            'background-color': 'blue'
                            
                        });
                    
                        $j('#tokenNo').val(tokenno);

                    }

                    function checkbookappointmentValidation() {
                        var appointmentDate = $j("#appointmentDate").val();
                        var tokenNo = $j('#tokenNo').val();
                        var hospitalId = $j("#hospitalId").val();
                        var departmentId = $j('#departmentId').val();
                        var appTypeTable = $j('#appTypeTable').val();
                        if (hospitalId == 0 || hospitalId == "") {
                            alert("Please select hospital.");
                            return false;
                        }
                        if (departmentId == 0 || departmentId == "") {
                            alert("Please select department.");
                            return false;
                        }
                        if (appTypeTable == 0 || appTypeTable == "") {
                            alert("Please select Appointment Type.");
                            return false;
                        }
                        if (appointmentDate == null || appointmentDate == "") {
                            alert("Please select Appointment Date.");
                            return false;
                        }

                        if (tokenNo == null || tokenNo == "") {
                            alert("Please select token.");
                            return false;
                        }
                        document.bookAppointmentform.action = "${pageContext.request.contextPath}/appointment/submitBookAppointment";

                    }
 ///Appointment blur                   
   //   $j("#appointmentDate").blur(function(){
	       function showTokens() { 
                    var uhidNo = $j("#uhidNo").val();
                	
               	 var appointmentDate = $j("#appointmentDate").val();
                    var tokenNo = $j('#tokenNo').val();
                    var hospitalId = $j("#hospitalId").val();
                    var departmentId = $j('#departmentId').val();
                    var appTypeTable = $j('#appTypeTable').val();
                    if (hospitalId == 0 || hospitalId == "") {
                        alert("Please select hospital.");
                        return false;
                    }
                    if (departmentId == 0 || departmentId == "") {
                        alert("Please select department.");
                        return false;
                    }
                    if (appTypeTable == 0 || appTypeTable == "") {
                        alert("Please select Appointment Type.");
                        return false;
                    }
                    if (appointmentDate == null || appointmentDate == "") {
                        alert("Please select Appointment Date.");
                        return false;
                    }
   	            var appointmentDate = $j('#appointmentDate').val();
                    var date = new Date(appointmentDate);
                    appointmentDate = ((date.getDate() + '-' + (date.getMonth() + 1)) + '-' + date.getFullYear());
                    
               	var varDate = new Date(date); //dd-mm-YYYY
               	var today = new Date();
            	  if(varDate < today) {
               	alert("Appointment date can not smaller than current date!");
               	return false;
               	}  
                   var arrParam = [];
                   var deptId = $j('#departmentId').find('option:selected').val();
                   var appointmentTypeId = [];
                   var appId=$j('#appTypeTable').find('option:selected').val();
                   appointmentTypeId.push(appId);
                 // appointmentTypeId = ["1"];
                  
                   var hospitalId = $j('#hospitalId').find('option:selected').val();
                     ///check visit is exist for same date or not
                    if(uhidNo!=0){
                    var visitparam = {
                        "deptId": deptId,
                        "appointmentTypeId": appId,
                        "hospitalId": hospitalId,
                        "visitFlag": "P",
                        "visitDate": appointmentDate,
                        "uhidNo":uhidNo
                    }
                      $j.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: 'checkVisitOnSameDate',
                        data: JSON.stringify(visitparam),
                        dataType: "json",
                        cache: false,
                        success: function(msg) {
                        	// $j('#displayToken').empty();
                      	 if(msg.status!=0){
                        	   alert("One Appointment  already booked for this Date");
                        	 //  $j("#appointment").hide();
                        	  
                           }else
                        	   {
                        	   getTokens();
                        	   }
                         

                        },
                        error: function(msg) {
                            alert("An error has occurred while contacting the server");
                        }
                    });
                  
                    }else{
                    	getTokens();
                    }
                    ///end visit exist
      }
                    //});
                </script>
            </body>

            </html>