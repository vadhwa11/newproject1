<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@include file="..//view/leftMenu.jsp" %>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Indian Coast Guard</title>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
     <link href="//resources/css/icons1.css" rel="stylesheet" type="text/css" />
    
<%@include file="..//view/commonJavaScript.jsp" %>
  
<script type="text/javascript" language="javascript">


var $j = jQuery.noConflict();

$j(document).ready(function()
		{	
	
		GetExportSyncTableList();
			
			
		});
		
function GetExportSyncTableList()
{
	
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getExportSyncTableList",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(jsonData){
	    	
	    	makeTable(jsonData); ;
	    }
	    
	});
	
	
	
}
function makeTable(jsonData)
{	
	var htmlTable = "";	
	var cmdId=0;
	var data = jsonData.count;
	
	var dataList = jsonData.data;
	
	for(i=0;i<dataList.length;i++)
		{ 		
			var j=i+1;	
			htmlTable = htmlTable+"<tr id='"+dataList[i].cmdId+"' >";	
			htmlTable = htmlTable +"<td style='width: 150px;'>"+j+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].tableName+"</td>";
			htmlTable = htmlTable +"<td style='width: 150px;'>"+dataList[i].syncDate+"</td>";			
			htmlTable = htmlTable +"<td style='width: 100px;'>"+dataList[i].fileName+"</td>";			
			htmlTable = htmlTable+"</tr>";
			
		}
				
	
	$j("#tblListOfExportTable").html(htmlTable);	
	
	
}

function genrateCSV(){
	alert("procedure call..");
	
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "genrateCSV",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(jsonData){
	    	if(jsonData.status=='error'){
	    	alert("Error occured during table export");
	    	}
	    	if(jsonData.status=='success'){
		    	alert("Successfully table exported");
		    	}
	    }
	    
	});
	
}
</script>
</head>

<body>

    <!-- Begin page -->
    <div id="wrapper">

        <!-- Top Bar Start -->
        <div class="topbar">

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
        <!-- Top Bar End -->

        <!-- ========== Left Sidebar Start ========== -->
        
        <!-- Left Sidebar End -->

        <!-- ============================================================== -->
        <!-- Start right Content here -->
        <!-- ============================================================== -->
        <div class="content-page">
            <!-- Start content -->
            <div class="">
                <div class="container-fluid">
                <div class="internal_Htext">Table Export</div>
                    
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                 <p align="center" id="messageId" style="font-weight: bold;" ></p>                     
                                    

                                   

					<!-- <table class="table table-striped table-hover jambo_table"> -->
					
					
					                  <div style="float:left">					           
		                                    <table class="tblSearchActions" cellspacing="0" cellpadding="0" border="0" >			<tr>
												
												<td>
												 <!-- <div id=resultnavigation></div> -->
												
												</td>
												</tr>
										</table>
		                                 </div>     
		                                    <div style="float:right">
				                                    <div id="resultnavigation">
				                                    </div> 
		                                    </div> 
        
                                    <table class="table table-hover table-bordered">
                                        <thead class="bg-success" style="color:#fff;">
                                            <tr>
                                                <th id="th2" class ="inner_md_htext">Sr. NO.</th>
                                                <th id="th3" class ="inner_md_htext">Table Name</th>
                                                <th id="th4" class ="inner_md_htext"> Sync Date </th>
                                                <th id="th5" class ="inner_md_htext">File Name</th>                                                
                                            </tr>
                                        </thead>
                                        <!--  <tbody id="tblListOfEmployeeAndDepenent">   </tbody>  --->
                                     <tbody id="tblListOfExportTable">
										 
                     				 </tbody>
                                    </table>          
                                  
									<br>	
                                    <div class="row">
                                        <div class="col-md-7">
                                        </div>
                                        <div class="col-md-5">
                                            <form>
                                                <div class="button-list">
                                                    <button type="button" id="btnAddCommand" class="btn btn-primary  " onclick="genrateCSV();">Ok</button>                                                   

                                                </div>
                                            </form>
                                        </div>

                                    </div>

                                    <!-- end row -->

                                </div>
                            </div>
                            <!-- end card -->
                        </div>
                        <!-- end col -->
                    </div>
                    <!-- end row -->
                    <!-- end row -->

                </div>
                <!-- container -->

            </div>
            <!-- content -->

            
        </div>

        <!-- ============================================================== -->
        <!-- End Right content here -->
        <!-- ============================================================== -->

    </div>
    <!-- END wrapper -->

    <!-- jQuery  -->
    

</body>

</html>