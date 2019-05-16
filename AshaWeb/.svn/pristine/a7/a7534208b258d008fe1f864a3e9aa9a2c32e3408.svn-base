<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pops_global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pops_menu.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/resources/js/controls.js"></script>





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Masters</title>

<!-- Bootstrap core CSS     -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    --> <!-- left menu dashboard -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css" rel="stylesheet">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


</head>

<body bgcolor="">

<div class="wrapper">
	<div class="sidebar" data-background-color="white" data-active-color="danger">

    <!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->
	
    <div class="sidebar-wrapper">
            <div class="logo">
                <a href="#" class="simple-text">
                    MASTERS
                </a>
            </div>

            <ul class="nav">
                <li>
                   <a href="${pageContext.request.contextPath}/v0.1/dashboard/manageRegistration">
                        <i class="ti-panel"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
					<a href="${pageContext.request.contextPath}/v0.1/master/showCommand" data-target="#item1" data-toggle="collapse" data-parent="#stacked-menu"> 
					<i class="ti-user"></i>
                        <p>Command Master<span class="caret arrow"></span></p>
                    </a>
						
					</li>
                
                
				
				<li>
                    <a href="${pageContext.request.contextPath}/v0.1/master/tradeMaster">
                        <i class="fa fa-user-plus" aria-hidden="true"></i>
                        <p>Trade Master</p>
                    </a>
                </li>
                <li>
                  <a href="${pageContext.request.contextPath}/v0.1/master/stateMaster">
                        <i class="ti-text"></i>
                        <p>State Master</p>
                    </a>
                </li>
                 <li>
                  <a href="${pageContext.request.contextPath}/v0.1/master/uomMaster">
                        <i class="ti-text"></i>
                        <p>UOM Master</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/v0.1/master/unitMaster">
                        <i class="ti-text"></i>
                        <p>UNIT Master</p>
                    </a>
                </li>
                
            </ul>
    	</div>	
    </div>
<!-- main div starts i.e Body rajdeo -->
    <div class="main-panel">
		<nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    <a class="navbar-brand" href="#">Unit of Measurement Master</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                          
						
                    </ul>

                </div>
            </div>
        </nav>
	
	
        <div class="content">
        
            <div class="container-fluid">            
                <div class="row">                    
                    <div class="col-lg-12 col-md-7">
                        <div class="card">
                            <div class="header">
                                
                            </div>
                            <div class="content">
                            <form id="searchUOMForm" name="searchUOMForm" action="" method="POST"> 
                             <!-- search UOM div  -->
                            <div class="row"> 
                                   <div class="row">                                   
                                        <div class="col-md-1">                                         
                                             <!-- <div class="form-group">
                                                <label>UOM Code</label>
                                                <input type="radio" id="searchUOMRadio"/>
                                           </div> -->
                                           </div>
                                              <div class="col-md-2">  
                                              <div class="form-group">   
                                                <label>UOM Name</label>
                                                <input type="radio" id="searchUOMRadio"/>
                                                </div>
                                            </div> 
                                         <div class="col-md-4">
                                             <div class="form-group">                                                
                                                <input name="searchUOM" id="searchUOM" type="text" class="form-control border-input" placeholder="UOM" value="">                                            
                                            </div> 
                                        </div>
                                     <div class="col-md-2">  
                                    <div class="text-right">
                                        <button type="button" class="btn" onclick="searchUOMList();">Search</button>
                                     </div>                                     
                                    </div> 
                                     <div class="col-md-2">  
                                    <div class="text-right">
                                        <button type="button" class="btn" id="button" onclick="GetUOMList('ALL');">Show ALL</button> 
                                     </div>                                     
                                    </div>                                        
                                </div>                            
                            </div> 
                            </form>
                <!-- START Data Grid -->            
                <div class="right_col" role="main">
   <div class="">
         <div class="clearfix"></div>
              <div class="col-md-12 col-sm-12 col-xs-12, col-xs-12">
                <div class="x_panel">                 
                  <!-- <div class="x_content"> -->
                   <div class="content table-responsive table-full-width">
					
                   <!-- <table class="table table-striped table-hover jambo_table"> -->
                    <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >
			<tr>
				<td class="SearchStatus" style="font-size: 13px;" align="left">Search Results</td>
				<td>
				<div id=resultnavigation></div>
				
				</td>
				</tr>
		</table>
                    <table class="table table-bordered ">
                    
                      <thead bgcolor="00FFFF">                        
                        <tr>                          
                          <th id="th1">UOM Code</th>
                          <th id="th2">UOM Name.</th>                          
                          <th id="th3">Status</th>
                          <!-- <th id="th3">Description</th> -->                       
                                                    
                        </tr>
                      </thead>
                      <tbody id="tblListOfUOM">
                       
                    
                      </tbody>
                    </table>

                  </div>
                </div>
              </div>

              
            </div>
          </div>           
                <!-- END Data Grid -->                                       
                            
                                <form id="addUOMForm" name="addUOMForm" action="<%=request.getContextPath()%>/v0.1/master/addUOM" method="POST"> 
                                   <div class="row">                                   
                                        <div class="col-md-4">                                         
                                             <div class="form-group">
                                                <label>UOM Code</label>
                                                <input name="UOMCode" id="UOMCode" type="text" class="form-control border-input" placeholder="UOMCode" value=""  required/>
                                            </div> 
                                        </div>
                                        <div class="col-md-4">
                                             <div class="form-group">
                                                <label>UOM Name</label>
                                                <input name="UOMName" id="UOMName" type="text" class="form-control border-input" placeholder="UOMName" value="" required>
                                            </div> 
                                        </div>                                        
                                        <!-- <div class="col-md-4">
                                             <div class="form-group">
                                                <label>Description</label>
                                                <input name="UOMDesc" id="UOMDesc" type="text" class="form-control border-input" placeholder="description" value="" required>
                                            </div> 
                                        </div>  -->                                       
                                    </div>							
                                    <div class="row">
                                        <div class="col-md-12">                                           
                                        </div>
                                    </div>
                                    <div class="text-right">
                                        <input type="submit" id="clicked" class="btn btn-info btn-fill btn-wd" value="Add" > 
                                        <button type="button" class="btn" onclick="updateUOM();">Update</button>
                                        <button type="button" class="btn" onclick="activeUOM();">Active</button> 
                                        <button type="button" class="btn" onclick="Reset();">Reset</button>                                    
                                    </div>                                
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>
<script type="text/javascript">
var nPageNo=1;
var $j = jQuery.noConflict();

$j(document).ready(function()
		{
			GetUOMList('ALL');
	
		});


function searchUOMList()
{
	/* if(document.getElementById('searchUOM').value=="" || document.getElementById('searchUOM').value==null){
		alert("Please Enter UOM Name.");
		return false;
	} */
	 if (jQuery('#searchUOM').val() == ""){		 
         alert('Please Enter the UOM');
     }
	/* jQuery('#searchUOM').val(''); */
	
	var uomName= jQuery("#searchUOM").attr("checked", true).val().toUpperCase();
	
	var nPageNo=1;
	var url = "getAllUOM";
	var data =  {"PN":nPageNo, "UOMName":uomName};
	var bClickable = true;
	GetJsonData('tblListOfUOM',data,url,bClickable);
}

function GetUOMList(MODE)
{	
	var UOMId=0;
	
	 if(MODE == 'ALL')
		{
			var data = {"PN":nPageNo};			
		}
	else
		{
		var data = {"PN":nPageNo};
		} 
	var url = "getAllUOM";
		
	var bClickable = true;
	GetJsonData('tblListOfUOM',data,url,bClickable);
}

function makeTable(jsonData)
{	
	var htmlTable = "";	
	var data = jsonData.count;
	//alert("data :: "+data)
	var dataList = jsonData.data;
	//alert("dataList :: "+dataList)
	for(i=0;i<dataList.length;i++)
		{		
		
		 if(dataList[i].UOMStatus == 'Y')
				{
					var Status='Active'
				}
			else
				{
					var Status='InActive'
				}
		 
		 if(dataList[i].cmdType == '2')
			{
				var cmdType='Noida'
			}
				else
			{
				var cmdType='Delhi HQ'
			}
				
			htmlTable = htmlTable+"<tr id='"+dataList[i].UOMId+"' >";			
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].UOMCode+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].UOMName+"</td>";
			htmlTable = htmlTable +"<td style='width: 100px;'>"+Status+"</td>";
			/* htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].UOMDesc+"</td>"; */
			
			
			htmlTable = htmlTable+"</tr>";
			
		}
	if(dataList.length == 0)
		{
		   htmlTable = htmlTable+"<tr ><td colspan='4'><h6>No Record Found</h6></td></tr>";
		}			
	
	//alert("tblListOfCommand ::" +htmlTable)
	$j("#tblListOfUOM").html(htmlTable);	
	
	
}

var uomId;
var uomCode;
var uomName;
var uomStatus;
var uomDesc;
function executeClickEvent(UOMId,data)
{
	
	for(j=0;j<data.data.length;j++){
		if(UOMId == data.data[j].UOMId){
			uomId = data.data[j].UOMId;
			uomCode = data.data[j].UOMCode;
			uomName = data.data[j].UOMName;
			uomStatus = data.data[j].UOMStatus;
			uomDesc = data.data[j].UOMDesc
			
		}
	}
	rowClick(uomId,uomCode,uomName, uomDesc);
}

function rowClick(uomId,uomCode,uomName, uomDesc){	
	document.getElementById("UOMCode").value = uomCode;
	document.getElementById("UOMName").value = uomName;
	/* document.getElementById("UOMDesc").value = uomDesc; */
	
	
}

//update updateUOM
function updateUOM()
{
	if(document.getElementById('UOMCode').value == null || document.getElementById('UOMCode').value == ""){
		alert('Please Enter the UOM Code');
		return false;
	}
	if(document.getElementById('UOMName').value == null || document.getElementById('UOMName').value == ""){
		alert('Please Enter the UOM Name');
		return false;
	}
	/* if(document.getElementById('UOMDesc').value == null || document.getElementById('UOMDesc').value == ""){
		alert('Please Enter the UOM Desc');
		return false;
	} */
	//alert("uomId: " +uomId+ "UOMCode" +jQuery('#UOMCode').val()+" UOMName "+jQuery('#UOMName').val()); 	
	var params = {
			 'UOMId':uomId,
			 'UOMCode':jQuery('#UOMCode').val(),
			 'UOMName':jQuery('#UOMName').val()
			 /* 'UOMDesc':jQuery('#UOMDesc').val() */
	 } 
	//alert("params: "+JSON.stringify(params)); 	
 		jQuery.ajax({
		 crossOrigin: true,
		    method: "POST",
		    header:{
		    	'Access-Control-Allow-Origin': '*'
		    	},
		    	crossDomain:true,
		    url: "updateUOMDetails",
		    data: JSON.stringify(params),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(result){
		        alert("result" +result);
		        console.log(result);
		        if(result.status==1){
		        	GetUOMList('ALL');
		        	//location.reload();
		        }else{
		        	alert(result.status)
		        }
		    }
		    
		    
		});	  
	
}

function Reset(){
	document.getElementById('addUOMForm').reset();
	document.getElementById('searchUOMForm').reset();
	
}
function showResultPage(pageNo)
{
	nPageNo = pageNo;	
	GetUOMList('FILTER');
	
}
</script>	
	
</body>
</html>