
<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/hms.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/scriptaculous.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/unittest.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>

<div class="popupbg">
<form name="popPresentComp" method="post" action="">
<input type="hidden" name=" " value=" ">

<h2>Present Complaint & History Templates</h2>

<div class="smallWithHeight">
<div id="externalwindow"></div>
<table colspan="7" id="componentDetails" cellpadding="0"
	cellspacing="0" style="top: 7%;
    position: absolute;">

	<thead>
		<tr>
			<th scope="col"><input type="checkbox" name="checkall"
			class="radioCheck" value="Collected All" id="addbutton"
			onclick="CheckAll(this);" align="right" /></th>
			<th scope="col">Template Values</th>
		</tr>
	</thead>
	<tbody>

		<tr>
			<td>    
					<%-- <input id="checkId<%=i %>" name="checkedTemp" type="checkbox"  class="radioCheck" value="n"/> --%>
					<input type="hidden" name="rowLength" id="rowLength" value=" " readonly="readonly"/>
			</td>
			<%-- <td><input type="text"  name="presentComplain" id="presentComplain<%=i %>" readonly="readonly" size="40"/></td> --%>
		</tr>
		
	</tbody>
</table>
</div>

				
				<input type="hidden" value=" " name="rowVal" id="rowVal"/>	
	
	<input name="" type="button" class="button" value="OK" onclick="setPresentComplaintTempalte();" /> 
	<input name="" type="button" class="button"	value="Close" onclick="cancelForm();" /> 
	
	
		
</form>
</div>

<script>
 function cancelForm(){
   	   window.close();
  }
   
   function setPresentComplaintTempalte(){
	 
	   var name=[];
		for(var i =0;i<arrlen;i++){
			
			var cbcheck = document.getElementById('cb'+i+'');
			if(cbcheck.checked == true){
				
				name[i] = $('#text'+i+'').val();
				
				
			}
		}
		if (window.opener != null && !window.opener.closed) {
            var txtName = window.opener.document.getElementById("presentMedicalHistory");
            txtName.value = name;
        }
		
		  window.close(); 
		
  	}
   function CheckAll(chk){
	   var rowLength=document.getElementById('rowLength').value;
	   for (var i=0;i <document.popPresentComp.elements.length;i++)
	   	{
	   		var e = document.popPresentComp.elements[i];

	   		if (e.type == "checkbox")
	   		{
	   			e.checked = chk.checked;
	   			for(var j=0;j<rowLength;j++)
	   			{
	   				e.value="y";
	   			}
	   		}
	   	}
	   }
	    	
	</script>
	
	
<script type="text/javascript">

$( document ).ready(function() {
	
	 var data = ${data};
	 console.log("SUCCESS: ", data);
	 var dataarray = data.data;
		arrlen = dataarray.length;
		var addrowdata = "<table><tr>";
	
	  if (data.status == 1) {
	    	for(var i=0;i<dataarray.length;i++){
	    		var name = dataarray[i].patinetPresentComplaintname;
	    		addrowdata += '<td><input type="checkbox" id="cb'+i+'"></td><td><input type="text" value="'+name+'" id="text'+i+'"></td></tr><br>';				    		
	    		
	    	}
	    	addrowdata += '</table>'
	    	$('#externalwindow').append(addrowdata);
	
   }
	  else
		  {
		    alert("NO Record Found")
		  }
		
});

</script>	 
<!-- <script type="text/javascript">
var arrlen;
 $(document).ready(
		 
		function() {
			//alert("on load localStorage.visitId: ");
			var pathname = window.location.pathname;
			var accessGroup = pathname.split("/")[1];

			var url = window.location.protocol + "//"
					+ window.location.host + "/" + accessGroup
					+ "/v0.1/opd/getFamilyPatinetHistory";
    
			var data =localStorage.visitId;
			
						
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url,
				data : JSON.stringify({
					'employeeId' : "1"
				}),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					//alert(data.data.length);					
					var dataarray = data.data;
					arrlen = dataarray.length;
					var addrowdata = "<table><tr>";
				
				  if (data.status == 1) {
				    	for(var i=0;i<dataarray.length;i++){
				    		var name = dataarray[i].patinetPresentComplaintname;
				    		addrowdata += '<td><input type="checkbox" id="cb'+i+'"></td><td><input type="text" value="'+name+'" id="text'+i+'"></td></tr><br>';				    		
				    		
				    	}
				    	addrowdata += '</table>'
				    	$('#externalwindow').append(addrowdata);
				
	              }
				  else
					  {
					    alert("NO Record Found")
					  }
					
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
		 });
		
		
		
</script> -->
	