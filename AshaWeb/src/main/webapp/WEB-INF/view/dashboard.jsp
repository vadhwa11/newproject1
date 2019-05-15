<%@page import="java.util.Date"%>
    <%@page import="java.util.Calendar"%>
        <%@page import="java.text.SimpleDateFormat"%>
            <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
                <%@include file="..//view/leftMenu.jsp"%>
                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="utf-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Indian Coast Guard</title>
                        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
                        <meta content="Coderthemes" name="author" />
                        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                        <%-- <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" /> --%>
                            <!-- App favicon -->
                            <link rel="icon" type="images/favicon-32x32.png" sizes="32x32" href="${pageContext.request.contextPath}/resources/images/favicon.ico">

                            <script src="${pageContext.request.contextPath}/resources/js/canvasjs.min.js"></script>

                            <%@include file="..//view/commonJavaScript.jsp"%>

                    </head>
                    <%
int hospitalId=0;
if(session.getAttribute("hospital_id")!=null)
{
hospitalId =(Integer)session.getAttribute("hospital_id"); 
}

int userId=0;
if(session.getAttribute("user_id")!=null)
{
	userId =(Integer)session.getAttribute("user_id"); 
}

SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 

Calendar c = Calendar.getInstance(); 
Date currentDate1 = c.getTime();
String currentDate=formatter.format(currentDate1); 

c.set(Calendar.DATE, 01);
Date startDate1 = c.getTime();

c.set(Calendar.DATE, 30);
Date enddate1 = c.getTime();
String startDate=formatter.format(startDate1); 
String enddate=formatter.format(enddate1); 

%>
                        <script type="text/javascript">
                            var comboArray = [];
                            var hospitalCode;
                            var fdate;
                            var tdate;

                            window.onload = function() {

                                //executeProcedureForDashBoard();
                            }

                            function executeProcedureForDashBoard() {

                                fdate = $j("#fromDate").val();
                                tdate = $j("#toDate").val();
                                $j('#unitId').html("");
                                var hospitalId = <%=hospitalId%>;
                                var userId = <%=userId%>;
                                /* alert($j("#unitId").val()) */
                                var combohospitalId = 0;

                                //alert(document.getElementById('unitId').value);
                                /* alert(fdate)
	     alert(tdate) */
                                var params = {
                                    "hospitalId": hospitalId,
                                    "tUserHospitalId": combohospitalId,
                                    "fromDate": fdate,
                                    "toDate": tdate

                                }

                                $j.ajax({
                                    type: "POST",
                                    contentType: "application/json",
                                    url: '${pageContext.request.contextPath}/dashboard/executeProcedureForDashBoard',
                                    data: JSON.stringify(params),
                                    dataType: "json",
                                    cache: false,
                                    success: function(result) {
                                        console.log(result)

                                        for (var i = 0; i < result.dashboardData.ref_cursor3.length; i++) {

                                            //	alert(result.dashboardData.ref_cursor3[0])
                                            if (result.dashboardData.ref_cursor3[0] != "") {

                                                if (result.dashboardData.ref_cursor3[i] != undefined) {
                                                    opdDataCount = result.dashboardData.ref_cursor3[i].TOTAL_OPD;

                                                    $j("#totalOPD").html(result.dashboardData.ref_cursor3[i].TOTAL_OPD);
                                                }
                                                if (result.dashboardData.ref_cursor3[i] != undefined) {
                                                    $j("#totalAttc").html(result.dashboardData.ref_cursor3[i].ATT_C);
                                                }

                                                if (result.dashboardData.ref_cursor3[i] != undefined) {
                                                    $j("#totalMedicalExam").html(result.dashboardData.ref_cursor3[i].TOTAL_ME);
                                                }

                                                if (result.dashboardData.ref_cursor3[i] != undefined) {
                                                    $j("#totalMedicalBoard").html(result.dashboardData.ref_cursor3[i].TOTAL_MB);
                                                }
                                                /* if(result.dashboardData.ref_cursor3[i]!=undefined){																	
                                                	$j("#fromDate").val(result.dashboardData.ref_cursor3[i].MTH_STDT);
                                                }
                                                if(result.dashboardData.ref_cursor3[i]!=undefined){																	
                                                	$j("#toDate").val(result.dashboardData.ref_cursor3[i].MTH_ENDDT);
                                                } */

                                            } else {
                                                $j("#totalOPD").html("0");
                                                $j("#totalAttc").html("0");
                                                $j("#totalMedicalExam").html("0");
                                                $j("#totalMedicalBoard").html("0");
                                            }
                                        }

                                        for (var i = 0; i < result.dashboardData.ref_cursor2.length; i++) {
                                            /* alert("length :: "+result.dashboardData.ref_cursor2.length); */

                                            if (result.dashboardData.ref_cursor2[i] != undefined) {
                                                if (result.dashboardData.ref_cursor2[i].A == 'Total OPD') {

                                                    var opdVal = result.dashboardData.ref_cursor2[i].B;

                                                }
                                            }
                                            if (result.dashboardData.ref_cursor2[i] != undefined) {
                                                if (result.dashboardData.ref_cursor2[i].A == 'ATT C') {
                                                    var attcVal = result.dashboardData.ref_cursor2[i].B;

                                                }
                                            }
                                            if (result.dashboardData.ref_cursor2[i] != undefined) {
                                                if (result.dashboardData.ref_cursor2[i].A == 'Referral') {
                                                    var reffVal = result.dashboardData.ref_cursor2[i].B;

                                                }
                                            }

                                            if (result.dashboardData.ref_cursor2[i] != undefined) {
                                                if (result.dashboardData.ref_cursor2[i].A == 'Others') {
                                                    var othersVal = result.dashboardData.ref_cursor2[i].B;

                                                }
                                            }
                                        }
                                        var unitValues = "";

                                        var respData = result.dashboardData.ref_cursor1;

                                        for (count in respData) {

                                            unitValues += '<option value=' + respData[count].HOSPITAL_ID + '>' + respData[count].HOSPITAL_NAME + '</option>';
                                        }

                                        $j('#unitId').append(unitValues);
                                        openChartReport(opdVal, attcVal, reffVal, othersVal)

                                    }
                                });

                            }

                            function executeEvent() {
                                fdate = $j("#fromDate").val();
                                tdate = $j("#toDate").val();
                                /* alert(fdate)
										     alert(tdate) */
                                var hospitalId = <%=hospitalId%>;
                                var userId = <%=userId%>;
                                /* alert($j("#unitId").val()) */

                                var temId = $j("#unitId").val();

                                if ($j("#unitId").val() == hospitalId) {
                                    var combohospitalId = 0
                                } else {
                                    var combohospitalId = $j("#unitId").val();
                                }

                                $j('#unitId').html("");

                                //alert(document.getElementById('unitId').value);

                                var params = {
                                    "hospitalId": hospitalId,
                                    "tUserHospitalId": combohospitalId,
                                    "fromDate": fdate,
                                    "toDate": tdate

                                }

                                $j.ajax({
                                    type: "POST",
                                    contentType: "application/json",
                                    url: '${pageContext.request.contextPath}/dashboard/executeProcedureForDashBoard',
                                    data: JSON.stringify(params),
                                    dataType: "json",
                                    cache: false,
                                    success: function(result) {
                                        console.log(result)

                                        for (var i = 0; i < result.dashboardData.ref_cursor3.length; i++) {

                                            if (result.dashboardData.ref_cursor3[0] != undefined) {

                                                if (result.dashboardData.ref_cursor3[i].TOTAL_OPD != undefined) {
                                                    $j("#totalOPD").html(result.dashboardData.ref_cursor3[i].TOTAL_OPD);

                                                } else {
                                                    $j("#totalOPD").html("0");
                                                }
                                                if (result.dashboardData.ref_cursor3[i].ATT_C != undefined) {
                                                    $j("#totalAttc").html(result.dashboardData.ref_cursor3[i].ATT_C);
                                                } else {
                                                    $j("#totalAttc").html("0");
                                                }

                                                if (result.dashboardData.ref_cursor3[i].TOTAL_ME != undefined) {
                                                    $j("#totalMedicalExam").html(result.dashboardData.ref_cursor3[i].TOTAL_ME);
                                                } else {
                                                    $j("#totalMedicalExam").html("0");
                                                }

                                                if (result.dashboardData.ref_cursor3[i].TOTAL_MB != undefined) {
                                                    $j("#totalMedicalBoard").html(result.dashboardData.ref_cursor3[i].TOTAL_MB);
                                                } else {
                                                    $j("#totalMedicalBoard").html("0");
                                                }
                                            }

                                        }

                                        for (var i = 0; i < result.dashboardData.ref_cursor2.length; i++) {
                                            /* alert("length :: "+result.dashboardData.ref_cursor2.length); */

                                            if (result.dashboardData.ref_cursor2[i] != undefined) {
                                                if (result.dashboardData.ref_cursor2[i].A == 'Total OPD') {

                                                    var opdVal = result.dashboardData.ref_cursor2[i].B;

                                                }
                                            }
                                            if (result.dashboardData.ref_cursor2[i] != undefined) {
                                                if (result.dashboardData.ref_cursor2[i].A == 'ATT C') {
                                                    var attcVal = result.dashboardData.ref_cursor2[i].B;

                                                }
                                            }
                                            if (result.dashboardData.ref_cursor2[i] != undefined) {
                                                if (result.dashboardData.ref_cursor2[i].A == 'Referral') {
                                                    var reffVal = result.dashboardData.ref_cursor2[i].B;

                                                }
                                            }

                                            if (result.dashboardData.ref_cursor2[i] != undefined) {
                                                if (result.dashboardData.ref_cursor2[i].A == 'Others') {
                                                    var othersVal = result.dashboardData.ref_cursor2[i].B;

                                                }
                                            }
                                        }
                                        var unitValues = "";

                                        var respData = result.dashboardData.ref_cursor1;
                                        //alert(respData)
                                        for (count in respData) {

                                            unitValues += '<option value=' + respData[count].HOSPITAL_ID + '>' + respData[count].HOSPITAL_NAME + '</option>';
                                        }

                                        $j('#unitId').append(unitValues);

                                        $j("#unitId").val(temId);
                                        openChartReport(opdVal, attcVal, reffVal, othersVal)

                                    }
                                });

                            }
                        </script>

                        <body>

                            <!-- Begin page -->
                            <div id="wrapper">

                                <!-- Top Bar Start -->
                                <div class="topbar">

                                    <!-- LOGO -->
                                    <div class="topbar-left">
                                        <a href="index.html" class="logo">
                                            <span>
                        <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="" height="100%">
                    </span>
                                        </a>
                                    </div>

                                    <nav class="navbar-custom">

                                        <ul class="list-inline menu-left mb-0">
                                            <li class="float-left">
                                                <button class="button-menu-mobile open-left waves-light waves-effect">
                                                    <i class="mdi mdi-menu"></i>
                                                </button>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>

                                <!-- ============================================================== -->
                                <!-- Start right Content here -->
                                <!-- ============================================================== -->
                                <form id="dashboardForm" name="dashboardForm">
                                    <div class="content-page">
                                        <!-- Start content -->
                                        <input type="hidden" id="opdDataCount1" name="opdDataCount1" value="" />
                                        <div class="">
                                            <div class="container-fluid">

                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="page-title-box">
                                                            <h4 class="page-title float-left"></h4>

                                                            <div class="clearfix"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end row -->

                                                <div class="row">
                                                    <div class="col-xl-3 col-md-6">
                                                        <div class="widget-panel widget-style-2 bg-pink">
                                                            <i class="ion-md-eye"></i>
                                                            <h2 class="m-0 text-white" data-plugin="counterup" id="totalOPD">22</h2>
                                                            <div>Today OPD</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-xl-3 col-md-6">
                                                        <div class="widget-panel widget-style-2 bg-purple">
                                                            <i class="ion-md-paper-plane"></i>
                                                            <h2 class="m-0 text-white" data-plugin="counterup" id="totalAttc">10</h2>
                                                            <div>Today Att'c</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-xl-3 col-md-6">
                                                        <div class="widget-panel widget-style-2 bg-info">
                                                            <i class="ion-ios-pricetag"></i>
                                                            <h2 class="m-0 text-white" data-plugin="counterup" id="totalMedicalExam">20</h2>
                                                            <div>Today Medical Exam</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-xl-3 col-md-6">
                                                        <div class="widget-panel widget-style-2 bg-primary">
                                                            <i class="ion-md-contacts"></i>
                                                            <h2 class="m-0 text-white" data-plugin="counterup" id="totalMedicalBoard">35</h2>
                                                            <div>Today Medical Board</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end row -->

                                                <%--        <div class="row dashboard_graph"> 
									<div class="col-md-12">
												<div class="row">
												      <div class="col-md-4">
															  <div class="form-group row">
															          <label class="col-md-5">From Date</label>		  
																	  <div class="col-md-4"> 
																				<input  type="text" class="calDate"  id="fromDate" name="fromDate" placeholder="DD/MM/YYYY" validate="From Date,string,yes" value="<% out.print(startDate); %>" onkeyup="mask(this.value,this,'2,5','/');"onblur="validateExpDate(this,'fromDate'); executeEvent();" maxlength="10" style="width: 120px"/>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <div class="form-group row">
                                            <label class="col-md-4">To Date</label>
                                            <div class="col-md-4">

                                                <input type="text" class="calDate" id="toDate" name="toDate" placeholder="DD/MM/YYYY" validate="To Date,string,yes" value="<% out.print(enddate); %>" onkeyup="mask(this.value,this,'2,5','/');" onblur="validateExpDate(this,'toDate'); executeEvent();" maxlength="10" style="width: 120px" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <div class="form-group row">
                                            <label class="col-md-4">Unit / Location</label>
                                            <div class="col-md-6">
                                                <select class="form-control" id="unitId" name="unitId" onchange="executeEvent();">

                                                </select>

                                            </div>
                                        </div>
                                    </div>
                            </div>

                            </div>

                            <div style="height:20px;width:100%;border:none;"> </div>

                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <div id="graphDiv"></div>
                            </div>
                            <div class="col-md-2"></div>
                            </div> --%>

                            <!--  -->
                            <!--          <div class="row"> 
                        <div class="col-xl-12 col-sm-12">
                            <div class="portlet">
                                <div id="portlet1" class="panel-collapse collapse show">
                                    <div class="portlet-body">
                                        <script type="text/javascript">
                                       function openChartReport(opdVal,attcVal,reffVal,othersVal) {

											CanvasJS.addColorSet("greenShades",
															[//colorSet Array

															"#fd5621",
															"#7441bc",
															"#2095f2",
															"#01957e",
															"#90EE90"                
															]);

                                                var chart = new CanvasJS.Chart("chartContainer", {
												 colorSet: "greenShades",
                                                    animationEnabled: true,
													dataPointWidth: 40,
                                                    title: {
                                                        text: "Generate Report for OPD Statistics"
                                                    },
                                                    axisY: {
                                                        title: "No. of Patient",
                                                        titleFontColor: "#4F81BC",
                                                        lineColor: "#4F81BC",
                                                        labelFontColor: "#4F81BC",
                                                        tickColor: "#4F81BC"
                                                    },

                                                    toolTip: {
                                                        shared: true
                                                    },
                                                    legend: {
                                                        cursor: "pointer",
                                                        itemclick: toggleDataSeries
                                                    },
                                                    data: [{
                                                        type: "column",
                                                        name: "OPD",
                                                        legendText: "OPD",
                                                        showInLegend: true,
                                                        dataPoints: [{
                                                                label: "",
                                                                y: opdVal
                                                            }/* , {
                                                                label: "C2",
                                                                y: 202.25
                                                            }, {
                                                                label: "C3",
                                                                y: 187.20
                                                            }, {
                                                                label: "C4",
                                                                y: 148.77
                                                            }, */

                                                        ]
                                                    }, {
                                                        type: "column",
                                                        name: "ATT C",
                                                        legendText: "ATT C",
                                                        axisYType: "secondary",
                                                        showInLegend: true,
                                                        dataPoints: [{
                                                                label: "C1",
                                                                y: attcVal
                                                            }

                                                        ]
                                                    }, {
                                                        type: "column",
                                                        name: "Referral",
                                                        legendText: "Referral",
                                                        axisYType: "secondary",
                                                        showInLegend: true,
                                                        dataPoints: [{
                                                                label: "C1",
                                                                y: reffVal
                                                            }

                                                        ]
                                                    }, 							 

													{
                                                        type: "column",
                                                        name: "Others",
                                                        legendText: "Others",
                                                        axisYType: "secondary",
                                                        showInLegend: true,
                                                        dataPoints: [{
                                                                label: "C1",
                                                                y: othersVal
                                                            }

                                                        ]
                                                    }]
                                                });
                                                chart.render();

                                                function toggleDataSeries(e) {
                                                    if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                                                        e.dataSeries.visible = false;
                                                    } else {
                                                        e.dataSeries.visible = true;
                                                    }
                                                    chart.render();
                                                }

                                            }
                                        </script>

                                        <div id="chartContainer" style="height: 370px; max-width: 920px; margin: 0px auto;"></div>

                                    </div>
                                </div>

                            </div>
                            /Portlet

                        </div>

						</div>
						 -->

                            <!-- BAR Chart -->
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="portlet">
                                        <!-- /primary heading -->
                                        <div class="portlet-heading">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="row">
                                                        <div class="col-md-9">
                                                            <h3 class="portlet-title text-uppercase">
										                                    OPD Statistics
										                                        </h3>
                                                        </div>
                                                        <div class="col-md-3 btn-right-all">
                                                            <div class="form-group row">
                                                                <label class="col-md-3 col-form-label">Select</label>
                                                                </label>
                                                                <div class="col-md-9">
                                                                    <select class="form-control">
                                                                        <option value="0">Day</option>
                                                                        <option value="1">Monthly</option>
                                                                        <option value="1">Yearly</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>
                                        </div>
                                        <div id="bg-default" class="panel-collapse collapse show">
                                            <div class="portlet-body">
                                                <div id="morris-bar-example" class="morris-charts" style="height: 300px;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Portlet -->
                                </div>
                                <!-- col -->
                            </div>
                            <!-- End row-->

                            <div class="row">
                                <!-- Area Chart -->
                                <div class="col-lg-6">
                                    <div class="portlet">
                                        <!-- /primary heading -->
                                        <div class="portlet-heading">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="row">
                                                        <div class="col-md-8">
                                                            <div class="form-group row">
                                                                <label class="col-md-6 col-form-label"><b>Medical Board</b></label>
                                                              
                                                                <div class="col-md-6">
                                                                    <div class="form-group row"> 
			                                                                 <div class="dropdown">
			                                                                    <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                                                                        Select
			                                                                    </button>
			                                                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			                                                                        <a class="dropdown-item" href="#">Officers</a>
			                                                                        <a class="dropdown-item" href="#">Sailors</a>
			                                                                        <a class="dropdown-item" href="#">All</a>
			                                                                    </div>
			                                                                </div> 
			                                                          </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4 btn-right-all">
                                                            <div class="form-group row">
                                                                <label class="col-md-3 col-form-label">Select</label>
                                                                </label>
                                                                <div class="col-md-9">
                                                                    <select class="form-control">
                                                                        <option value="0">2019</option>
                                                                        <option value="1">2018</option>
                                                                        <option value="1">2017</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div id="portlet3" class="panel-collapse collapse show">
                                            <div class="portlet-body">
                                                <div id="morris-donut-example1" class="morris-charts" style="height: 300px;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Portlet -->
                                </div>

                                <div class="col-lg-6">
                                    <div class="portlet">
                                        <!-- /primary heading -->
                                        <div class="portlet-heading">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="row">
                                                        <div class="col-md-8">
                                                            <div class="form-group row">
                                                             <label class="col-md-6 col-form-label"><b>Medical Exam</b></label>
                                                                
                                                                 <div class="dropdown">
                                                                    <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                                        Select
                                                                    </button>
                                                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                                        <a class="dropdown-item" href="#">Officers</a>
                                                                        <a class="dropdown-item" href="#">Sailors</a>
                                                                        <a class="dropdown-item" href="#">All</a>
                                                                    </div>
                                                                </div> 
                                                          </div>
                                                        </div>
                                                        <div class="col-md-4"> 
                                                            <div class="form-group row btn-right-all">   
                                                                    <div class="dropdown">
                                                                    <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                                        Select
                                                                    </button>
                                                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                                        <a class="dropdown-item" href="#">2019</a>
                                                                        <a class="dropdown-item" href="#">2018</a>
                                                                        <a class="dropdown-item" href="#">2017</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div id="portlet3" class="panel-collapse collapse show">
                                            <div class="portlet-body">
                                                <div id="morris-donut-example" class="morris-charts" style="height: 300px;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Portlet -->
                                </div>

                            </div>
                            <!-- End row-->

                            <!--      <div class="row">
                             Line Chart
                            <div class="col-sm-12">
                                <div class="portlet">/primary heading
                                    <div class="portlet-heading">
                                        <h3 class="portlet-title text-uppercase">
                                            Line Chart
                                        </h3>
                                        <div class="portlet-widgets">

                                      <div class="row">

                                        <div class="col-md-12">
												<div class="form-group row  btn-right-all">
													<label class="col-md- col-form-label">Service No.
													</label>
													<div class="col-md-7">
														<select class="form-control">
																		<option value="0">No</option>
																		<option value="1">Yes</option>
																	</select>
													</div>
												</div>
											</div>

											</div>

                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div id="portlet4" class="panel-collapse collapse show">
                                        <div class="portlet-body">
                                            <div id="morris-line-example" class="morris-charts" style="height: 300px;"></div>
                                        </div>
                                    </div>
                                </div> /Portlet
                            </div>
                        </div> End row
  -->

                            </div>
                            <!-- container -->

                            </div>
                            <!-- content -->

                            </div>
                            </form>
                            <!-- ============================================================== -->
                            <!-- End Right content here -->
                            <!-- ============================================================== -->

                            </div>

                            <script src="${pageContext.request.contextPath}/resources/js/pages/morris.min.js"></script>
                            <script src="${pageContext.request.contextPath}/resources/js/pages/raphael.min.js"></script>
                            <script src="${pageContext.request.contextPath}/resources/js/pages/morris.init.js"></script>

                        </body>

                    </html>