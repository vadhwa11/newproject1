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
var checkStatus="";

$j(document).ready(function()
		{
	
			getRoleRightsList();
			getTemplateNameList();
			
		});


function getRoleRightsList(){
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getRoleRightsList",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	    	
	    	var combo = "<option value=\"\">Select</option>" ;
	    	
	    	for(var i=0;i<result.data.length;i++){	    		
	    		combo += '<option value='+result.data[i].roleId+'>' +result.data[i].roleName+ '</option>';    		
	    		
	    	}
	    	jQuery('#rolelist').append(combo);
	    }
	    
	});
}

function getTemplateNameList(){
	
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,
	    url: "getTemplateNameList",
	    data: JSON.stringify({}),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	    	
	    	makeTableAll(result); 
	    }
	    
	});
}

function makeTableAll(result) {		
	var tempid=0;	
	var htmlTable = "";	
	var dataList = result.data;		
	for(i=0;i<dataList.length;i++)	{		
			htmlTable = htmlTable+'<tr>';		
			htmlTable = htmlTable +'<td style="display:none">'+dataList[i].templateId+'</td>';						
			htmlTable = htmlTable +'<td style="">'+dataList[i].templateName+'</td>';			 
			htmlTable = htmlTable +"<td style=''><input id='chk"+dataList[i].templateId+"' type='checkbox'   value='"+dataList[i].templateId+"'></td>";	 
			htmlTable = htmlTable+'</tr>';			    
			
		}	
	
	
	$j("#tblListOTemplate").html(htmlTable);	
	
}



function makeTable(result,role) {	
	
	var tempid=0;	
	var htmlTable = "";	
	var dataList = result.data;	
	//var dataCommon = result.data;
	
	for(i=0;i<dataList.length;i++)	{	
			
		var tempLateIdd=dataList[i].templateId;
		
			
		
			htmlTable = htmlTable+'<tr>';		
			htmlTable = htmlTable +'<td style="width: 150px; display:none">'+dataList[i].templateId+'</td>';						
			htmlTable = htmlTable +'<td style="width: 150px;">'+dataList[i].templateName+'</td>';			 
			htmlTable = htmlTable +"<td style='width: 150px;'><input class='chk' id='chk"+dataList[i].templateId+"' type='checkbox'   value='"+dataList[i].templateId+"'></td>";		 
			htmlTable = htmlTable+'</tr>';			    
			
		}	
		
	 
	
	$j("#tblListOTemplate").html(htmlTable);	
	
}	


function getAssingedTemplateNameList(value){	
	
	var data = {"roleId":value};
	jQuery.ajax({
	 	crossOrigin: true,
	    method: "POST",			    
	    crossDomain:true,	    	
	    url: "getAssingedTemplateNameList",
	    data: JSON.stringify(data),
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(result){
	    	var dataList = result.data;	
	    	
	    	
	    	if(dataList.length ==0)
	    		{
	    		$j('input:checkbox').removeAttr("checked");
	    		}
	    	for(i=0;i<dataList.length;i++){
	    		
	    		var tempId= $j("#chk"+dataList[i].id).val();
	    		if(dataList[i].id==tempId && dataList[i].status=='y' ){
	    			
	    			$j("#chk"+dataList[i].id).attr("checked","checked");
	    			
	    		}
	    		else
	    			{
	    				$j("#chk"+dataList[i].id).removeAttr("checked");
	    			}
	    		
	    		
	    	}
	    	
	    }
	    
	});
}

	
	function Reset() {	
		document.getElementById("messageId").innerHTML = "";
		$j( 'input[type="checkbox"]' ).prop('checked', false);
		$j('#rolelist').val('');
	}

	function saveRolesRight() {

		
		if (jQuery('#rolelist').find('option:selected').val() == '') {
			alert("Please select role");
			return false;
		}
		var roleId = jQuery('#rolelist').find('option:selected').val()
		//alert("role id" + roleId);

		var valCheckBox = new Array();

		$j('input:checkbox:checked').each(function(j) {
			
			var params = {
				"tid" : $j(this).val(),
				"status" : 'y',

			};
			valCheckBox.push(params);

		});

		$j("input:checkbox:not(:checked)").each(function(k) {
			
			var params = {
				"tid" : $j(this).val(),
				"status" : 'n'
			};
			valCheckBox.push(params);

		});

		
		var data = {
			"templateid" : valCheckBox,
			"roleId" : roleId
		};

		//alert(JSON.stringify(data));		
		
		$j('#messageId').fadeIn();
		
		$j.ajax({
			 crossOrigin: true,
			    method: "POST",
			    header:{
			    	'Access-Control-Allow-Origin': '*'
			    	},
			    	crossDomain:true,
			    url: "saveRolesRight",
			    data: JSON.stringify(data),
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(result)
			    {
			    	if(result.status==1){			        	
			    					    			        	      	
			        	document.getElementById("messageId").innerHTML = result.msg;
			    		$j("#messageId").css("color", "green");
			        	
			        }
			        
			    	else if(result.status==0)
			    	{
			        	
			        	if(result.msg != undefined)
			        		{
				        		document.getElementById("messageId").innerHTML = result.msg;
				        		$j("#messageId").css("color", "red");
					        	
			        		}
			        	if(result.err_mssg != undefined)
		        		{
			        		document.getElementById("messageId").innerHTML = result.err_mssg;
			        		$j("#messageId").css("color", "red");
				        	
		        		}
			        	
			        }
			    	
			    	else
			    	{
			        	
			        	if(result.msg != undefined)
			        		{
				        		document.getElementById("messageId").innerHTML = result.msg;
				        		$j("#messageId").css("color", "red");
					        	
			        		}
			        	if(result.err_mssg != undefined)
		        		{
			        		document.getElementById("messageId").innerHTML = result.err_mssg;
			        		$j("#messageId").css("color", "red");
				        	
		        		}
			        	
			        }
			    }
			
			
		});
		
		
		

	}

	function assignRoleTemplate(value) {

		getAssingedTemplateNameList(value);
		//alert("assign template" + value);
	}
</script>
</head>

<body>

    <!-- Begin page -->
    <div id="wrapper">

        <!-- Top Bar Start -->
        <div class="topbar">

            <nav class="navbar-custom">
                <ul class="list-inline float-right mb-0">
                
                    <li class="list-inline-item dropdown notification-list">
                        <a class="nav-link dropdown-toggle nav-user" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                            <i class="noti-icon"><img src="${pageContext.request.contextPath}/resources/images/users/avatar-1.jpg" alt="user" class="img-fluid rounded-circle"></i>
                            <span class="profile-username ml-2 text-dark">Username </span> <span class="mdi mdi-menu-down text-dark"></span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-animated dropdown-menu-right profile-dropdown ">
                            <!-- item-->
                            <div class="dropdown-item noti-title">
                                <h5 class="text-overflow"><small>Welcome ! Role Rghts</small> </h5>
                            </div>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-account-circle"></i> <span>Profile</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-settings"></i> <span>Settings</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-lock-open"></i> <span>Lock Screen</span>
                            </a>

                            <!-- item-->
                            <a href="javascript:void(0);" class="dropdown-item notify-item">
                                <i class="mdi mdi-power"></i> <span>Logout</span>
                            </a>

                        </div>
                    </li>

                </ul>

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
                <div class="internal_Htext">Role Rights</div>
                                                  
                    
                    <!-- end row -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                  <form>
                                
                                  <div class="row"> 
                                     <div class="col-md-4">
                                            <div class="form-group row">
                                            
                                                <label for="recordoffice" class="col-sm-3 col-form-label inner_md_htext">Role<span style="color:red">*</span></label>
                                                
                                                <div class="col-md-6">
                                                    <select class="form-control" id="rolelist" onchange="assignRoleTemplate(this.value);">                                                                    
                                                    </select>
                                                </div>
                                               
                                            </div>
                                        </div> 
                                        
                                        
                                        
                                         <div class="col-md-8">
                                             
                                        </div> 
                                  
                                   
                                
                                 <p align="center" id="messageId" style="font-weight: bold;" ></p><br>                           
							          <div style="float:right">
					                     <div id="resultnavigation">
					                     </div> 
		                              </div>                                  
					   				<table class="table table-hover table-bordered">
                                        <thead class="bg-success" style="color:#fff;">                                           
                                        </thead>                                        
	                                     <tbody id="tblListOTemplate">											 
	                     				 </tbody>
                                    </table>                                 
                                                                  
      						      
                                        <div class="col-md-12">
                                            <form id="addCommandForm" name="roleRight" method="">
                                                <div class="row">   
		                                                <div class="col-md-10"> 
		                                                </div>
                                                      <div class="col-md-2"> 
                                                            <button type="button" id="btnSubmit" class="btn btn-primary  " onclick="saveRolesRight()">Save</button>                                                             
                                                   
                                                            <button type="button" class="btn btn-danger" onclick="Reset()">Reset</button> 
                                                    </div> 
                                                </div>
                                            </form>
                                        </div>
                                    </div>	                          

                                    <!-- end row -->
                                     </div>	 
                                    
                                    </form>
                                    
                                    

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