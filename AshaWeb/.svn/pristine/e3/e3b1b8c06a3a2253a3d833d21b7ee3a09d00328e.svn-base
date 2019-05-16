function autocomplete(inp, arr) {
  /*the autocomplete function takes two arguments,
  the text field element and an array of possible autocompleted values:*/
  var currentFocus;
  /*execute a function when someone writes in the text field:*/
  inp.addEventListener("input", function(e) {
      var a, b, i, val = this.value;
      /*close any already open lists of autocompleted values*/
      closeAllLists();
      if (!val) { return false;}
      currentFocus = -1;
      /*create a DIV element that will contain the items (values):*/
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      /*append the DIV element as a child of the autocomplete container:*/
      this.parentNode.appendChild(a);
      /*for each item in the array...*/
      for (i = 0; i < arr.length; i++) {
        /*check if the item starts with the same letters as the text field value:*/
        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
          /*create a DIV element for each matching element:*/
          b = document.createElement("DIV");
          /*make the matching letters bold:*/
          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
          b.innerHTML += arr[i].substr(val.length);
          /*insert a input field that will hold the current array item's value:*/
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
          /*execute a function when someone clicks on the item value (DIV element):*/
          b.addEventListener("click", function(e) {
        	  
        	 
              /*insert the value for the autocomplete text field:*/
              inp.value = this.getElementsByTagName("input")[0].value;
              inp.focus();
              /*close the list of autocompleted values,
              (or any other open lists of autocompleted values:*/
              closeAllLists();
          });
          a.appendChild(b);
        }
      }
  });
  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function(e) {
      var x = document.getElementById(this.id + "autocomplete-list");
      if (x) x = x.getElementsByTagName("div");
      if (e.keyCode == 40) {
        /*If the arrow DOWN key is pressed,
        increase the currentFocus variable:*/
        currentFocus++;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 38) { //up
        /*If the arrow UP key is pressed,
        decrease the currentFocus variable:*/
        currentFocus--;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 13) {
        /*If the ENTER key is pressed, prevent the form from being submitted,*/
        e.preventDefault();
        if (currentFocus > -1) {
          /*and simulate a click on the "active" item:*/
          if (x) x[currentFocus].click();
        }
      }
  });
  function addActive(x) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /*add class "autocomplete-active":*/
    x[currentFocus].classList.add("autocomplete-active");
  }
  function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }
  /*execute a function when someone clicks in the document:*/
  document.addEventListener("click", function (e) {
      closeAllLists(e.target);
  });
}



/*var lastRow;
//var iteration='';
function addRowForInvestigation(){
	
	 var hinId =  document.getElementById('hinId').value;
	  var tbl = document.getElementById('investigationGrid');
	  lastRow = tbl.rows.length;
	  //alert(lastRow)
       // if there's no header row in the table, then iteration = lastRow + 1
	  var iteration = lastRow;
	  var row = tbl.insertRow(lastRow);
	  var hdb = document.getElementById('hiddenValue');
	  iteration = parseInt(hdb.value)+1;
	  hdb.value=iteration
	  // alert("iteration row--"+iteration)
  
	  var cellRight0 = row.insertCell(0);
	  var e0 = document.createElement('input');
	  e0.type = 'text';
	 // e0.innerHTML = iteration+':'
	  e0.onblur=function(){
		  populateChargeCode(this.value,iteration,hinId);
	  						//if(validateInvestigationAutoComplete(this.value,iteration)){checkForChargeCode(this.value,iteration,'chargeCodeVal'+iteration);}
	  					  };
	   var newdiv1 = document.createElement('div');
	  newdiv1.id='ac2updateInv'+iteration;
	  newdiv1.className='autocomplete';
	  newdiv1.style.display = 'none';
	  					
	  e0.name = 'chargeCodeName' + iteration;
	  e0.id = 'chargeCodeName' + iteration;
	  e0.setAttribute('tabindex','1');
	  //alert("name--"+e0.name)
	  e0.size = '100'
	  cellRight0.appendChild(newdiv1);
	  
	  cellRight0.appendChild(e0);
	  e0.focus();
	  if(i == ""){
		  autocomplete(document.getElementById("chargeCodeName"+iteration), arry);
	  }else{
		  autocomplete(document.getElementById("chargeCodeName"+iteration), arrayData);
	  }
	 // new Ajax.Autocompleter('chargeCodeName'+iteration,'ac2updateInv'+iteration,'opd?method=getInvestigationListForAutoComplete',{callback: function(element, entry) { return entry + '&labradiologyCheck=' + document.getElementById('investigationCategory').value;},parameters:'requiredField=chargeCodeName'+iteration});	  var sel = document.createElement('input');
	  var sel = document.createElement('input');																																								
	  sel.type = 'hidden';
	  sel.name='chargeCode'+iteration;
	  sel.id='chargeCode'+iteration
	  sel.size = '10';
	  sel.setAttribute('tabindex','1');
	  cellRight0.appendChild(sel);

	  var e2 = document.createElement('input');
	  e2.type = 'hidden';
	  e2.name='qty'+iteration;
	  e2.id='qty'+iteration
	  e2.size='10';
	  e2.setAttribute('tabindex','1');
	  cellRight0.appendChild(e2);
	
	  var cellRight2 = row.insertCell(1);
	  var e3 = document.createElement('input');
	  e3.type = "date";
	  e3.name='investigationDate'+iteration;
	  e3.placeholder="DD/MM/YYYY";
	  //e3.value=document.getElementById("consultationDate").value;;
	  e3.className='calDate';
	  e3.id='investigationDate'+iteration;
		 e3.setAttribute("onblur", "validateExpDate(this,'investigationDate"+iteration+"')");
		 e3.setAttribute("onkeyup", "mask(this.value,this,'2,5','/')");
		  e3.onchange=function(){checkForChargeCode(this.value,iteration,'chargeCodeVal'+iteration);
				checkForAlreadyPrescribedInvestigation(this.value,iteration,visitId);
	 };
	 cellRight2.appendChild(e3);
		 
		  var cellRight3 = row.insertCell(2);
		  var e4 = document.createElement('input');
		  e4.type = 'radio';
		  e4.name='labradiologyCheck'+iteration;
		  e4.id='othAfLab'+iteration
		  e4.value = "I";
		  e4.name='radio';
		  e4.setAttribute('name','labradiologyCheck'+iteration);
		  //e4.setAttribute('onClick','addRowForInvestigation();');
		  e4.onclick = function(){changeRadio();}; 
		  cellRight3.appendChild(e4);
		  
		  var cellRight4 = row.insertCell(3);
		  var e5 = document.createElement('input');
		  e5.type = 'radio';
		  e5.name='labradiologyCheck'+iteration;
		  e5.id='othAfLab'+iteration
		  e5.value = "O";
		  e5.name='radio';
		  e5.setAttribute('name','labradiologyCheck'+iteration);
		  //e4.setAttribute('onClick','addRowForInvestigation();');
		  e5.onclick = function(){changeRadio();}; 
		  cellRight4.appendChild(e5);
		  
		  var cellRight5 = row.insertCell(4);
		  var eCheck = document.createElement('input');
		  eCheck.type = 'checkbox';
		  eCheck.id='uCheck'+iteration;
		  eCheck.name='radioAuto'+iteration;
		  eCheck.value = "O";
		  eCheck.name='checkbox';
		  eCheck.setAttribute('name','radioAuto'+iteration);
		  //alert(eCheck.setAttribute('name','radioAuto'+iteration))
		  //eCheck.setAttribute('onClick','addRowForInvestigation();');
		  //e5.onclick = function(){addRowForInvestigation();}; 
		  cellRight5.appendChild(eCheck);
		 
	 var cellRight6 = row.insertCell(5);
	  var e6 = document.createElement('input');
	  e6.type = 'button';
	  e6.className = 'buttonAdd';
	  e6.value = "";
	  e6.name='Button';
	  e6.setAttribute('tabindex','1');
	  //e4.setAttribute('onClick','addRowForInvestigation();');
	  e6.onclick = function(){addRowForInvestigation();}; 
	  cellRight6.appendChild(e6);

	  var cellRight7 = row.insertCell(6);
	  var e7 = document.createElement('input');
	  e7.type = 'button';
	  e7.className = 'buttonDel';
	  e7.value = ""
		  e7.name='delete';
	  e7.setAttribute('tabindex','1');
	  e7.onclick = function(){removeRow("investigationGrid","hdb",this);}; 
	  cellRight7.appendChild(e7);
	}

function checkForAlreadyPrescribedInvestigation(val,inc,hinId){
	//
	//var value1=document.getElementsByName('nomenclature'+inc).value;
	//alert(val+"<<<-------val======inc------>>"+value1);
	
	//var visitId=document.getElementById("visitId").value;
	var investigationDate;
	if(document.getElementById("investigationDate"+inc)!=null)
	investigationDate = document.getElementById("investigationDate"+inc).value;
	else
	  investigationDate = document.getElementById("consultationDate").value;
	
	
	var id;
	if(val!=""){

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
		  if(xmlHttp.readyState==4){
			  var b="false";
		  	var items =xmlHttp.responseXML.getElementsByTagName('items')[0];
		  	for (loop = 0; loop < items.childNodes.length; loop++) {
		   	    var item = items.childNodes[loop];
		        var dupl  = item.getElementsByTagName('alreadyIssued1')[0];
		        //alert("icdString"+icdString);
		         b =dupl.childNodes[0].nodeValue
		     // alert("b-->>"+b);
		       
		        // var val=document.getElementById('icd').value;
		          var index1 = val.lastIndexOf("[");
				    var index2 = val.lastIndexOf("]");
				    index1++;
				    id = val.substring(index1,index2);
				    //alert("id------>>>"+id);
				    if(id ==""){
					  return;
					}
				   
		         
		      
				    if(b=='true'){
						   alert("Already Prescribed to Patient!!");
						   document.getElementById('chargeCodeName'+inc).value="";
						
					   }
				    }
					
		  }
		  }
		//var url="/hms/hms/opd?method=getIcdWithIcdCode&icdCode="+encodeURIComponent(icdCode)
		  	
		 //var url="/hms/hms/opd?method=checkForAlreadyIssuedPrescribtion&val="+val+"&visitId="+visitId+"&"+csrfTokenName+"="+csrfTokenValue;
				var url="/hms/hms/opd?method=checkForAlreadyPrescibedInvestigation&val="+encodeURIComponent(val)+"&hinId="+hinId+"&investigationDate="+investigationDate;
				
		xmlHttp.open("GET",url,true);
		xmlHttp.setRequestHeader("Content-Type", "text/xml");
		xmlHttp.send(null);
		

			
	}
	else
		{
		 //document.getElementById('chargeCodeName'+inc).value="";
		}
	
	
}

var arrayData = [];
var i = "";
function changeRadio(radioValue){
	i++;
	 var radioValue = '';
	 $('input[name=labradiologyCheck]').change(function(){
		 radioValue = $( 'input[name=labradiologyCheck]:checked' ).val();
 	   
 	});  
	 alert(radioValue);
	var pathname = window.location.pathname;
	var accessGroup = pathname.split("/")[1];

	var url = window.location.protocol + "//"
			+ window.location.host + "/" + accessGroup
			+ "/v0.1/opd/getIinvestigationList";

	//var data = $('employeeId').val();
   // alert("radioValue" +radioValue);
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : JSON.stringify({
			'employeeId' : "1",
			'mainChargeCode':radioValue,
		}),
		dataType : 'json',
		timeout : 100000,
		
		success : function(res)
		
		{
			var data = res.InvestigationList;
			
			for(var i=0;i<data.length;i++){
				var investigationId= data[i].investigationId;
				var investigationName = data[i].investigationName;
				//var icdName = data[i].icdName;
				var a=investigationName+"["+investigationId +"]"
				//alert("a "+a);
				arrayData.push(a);
				 
				// autocomplete(document.getElementById("chargeCodeName1"), "");
				 //autocomplete(document.getElementById("chargeCodeName1"), arrayData);
				//console.log('data :',a);
			}

		
           },
           error: function (jqXHR, exception) {
               var msg = '';
               if (jqXHR.status === 0) {
                   msg = 'Not connect.\n Verify Network.';
               } else if (jqXHR.status == 404) {
                   msg = 'Requested page not found. [404]';
               } else if (jqXHR.status == 500) {
                   msg = 'Internal Server Error [500].';
               } else if (exception === 'parsererror') {
                   msg = 'Requested JSON parse failed.';
               } else if (exception === 'timeout') {
                   msg = 'Time out error.';
               } else if (exception === 'abort') {
                   msg = 'Ajax request aborted.';
               } else {
                   msg = 'Uncaught Error.\n' + jqXHR.responseText;
               }
               alert(msg);
           }
	});
}

var charge_code_array = [];
var ChargeCode='';
var multipleChargeCode = new Array();
function populateChargeCode(val,count) {
	
	
	if(validateMetaCharacters(val)){
		if(val != "")
		{
			
		    var index1 = val.lastIndexOf("[");
		    var indexForChargeCode=index1;
		    var index2 = val.lastIndexOf("]");
		    index1++;
		    ChargeCode = val.substring(index1,index2);
		    
		    var indexForChargeCode=indexForChargeCode--;
		   // alert("value is fun"+ChargeCode);
       
		if(ChargeCode == "")
		{
		//document.getElementById('chargeCodeName'+count).value="";
		//document.getElementById('chargeCodeCode'+count).value="";
		return;
		}
		else
			ChargeCode= document.getElementById('chargeCodeCode'+count).value=ChargeCode
			multipleChargeCode[count-1]=ChargeCode;
			
		}
		}	
		}

function checkDate1(val, Id) {
	if (Id == "doa" || Id == "dod") {
		 curr = new Date(serverdate);
		 dob = new Date(val);
		 
		curr = new Date()
		dob = new Date(val.substring(6), (val.substring(3, 5) - 1), val
				.substring(0, 2));

		if (dob > curr) {
			alert("Date should be less or Equal Current Date " + serverdate
					+ ".");
			document.getElementById(Id).value = "";
			return false;
		}
	}

	if (Id == "dod") {
		var doa = document.getElementById('doa').value;
		  curr1 = new Date(doa);
		 dob1 = new Date(val);
		if (doa != '') {
			curr1 = new Date(doa.substring(6), (doa.substring(3, 5) - 1), doa
					.substring(0, 2))
			dob1 = new Date(val.substring(6), (val.substring(3, 5) - 1), val
					.substring(0, 2));
			if (curr1 > dob1) {
				// alert("DOA can not be gretter than DOD.");
				document.getElementById(Id).value = "";
				return false;
			}
		}

	}
	if (Id == "doa") {

		var dod = document.getElementById('dod').value;
		    curr1 = new Date(passportExpiryDate);
		 dob1 = new Date(val); 
		if (dod != '') {
			curr1 = new Date(dod.substring(6), (dod.substring(3, 5) - 1), dod
					.substring(0, 2))
			dob1 = new Date(val.substring(6), (val.substring(3, 5) - 1), val
					.substring(0, 2));
			if (dob1 > curr1 && curr1 != "") {
				alert("DOA can not be gretter than DOD.");
				document.getElementById(Id).value = "";
				return false;
			}
		}
	}

}
*/