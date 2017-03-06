//---------------------Target Selection Table Display----------
function check(ele){
	
	$("#loading").show();
	var check_list = document.getElementsByClassName("kpi");
	var co=0;
	for(var i =0; check_list[i]; i++){
		if(check_list[i].checked===true)
		{
			co++;
			if(co<=3){				
				document.getElementById("remainingkpi").innerHTML=3-co;
				document.getElementById("kpi "+co+" label").innerHTML=check_list[i].value;
				document.getElementById("input start "+co).value = check_list[i].getAttribute("start");
				document.getElementById("input weight "+co).value = check_list[i].getAttribute("weightage");
				displaytable();
			}
			else{
				ele.checked=false;
				alert("Can't select more than 3 KPI");
			}
		}
	}
	if(co===0)
	{
		document.getElementById("remainingkpi").innerHTML=3;
		document.getElementById("kpi 1 label").innerHTML="";
		displaytable();	
	}
}

//----------------Table Style Change-----------------------
function displaytable(){
	document.getElementById("disp_segment").innerHTML="";	
	for(i=1;i<=3;i++){
		var tableid = "kpi "+i+" tabel";
		document.getElementById(tableid).style.display = "none";
	}
	var co =3-document.getElementById("remainingkpi").innerHTML;
	if(co>0)
	{
		for(i=1;i<=co;i++){
			var tableid = "kpi "+i+" tabel";
			document.getElementById(tableid).style.display = "block";
		}
		disp_segment_target();
	}
	$("#loading").fadeOut(500);
}

//--------------------Target Selection Kpi List----------------
function segment(ele){
	$("#loading").show();
	if(ele!=""){
		document.getElementById("kpi_list").style.visibility="visible"
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("checklist").innerHTML=(xhttp.responseText);
			var check_list = document.getElementsByClassName("kpi");
			check(check_list[0]);
			$("#loading").fadeOut(500);
			$('.ui.checkbox').checkbox();
		}
	};
	xhttp.open("GET", "http://localhost:9000/xoportal/config/disp/"+ele, true);
	xhttp.send();
}
segment("A1");

function validate(evt) {
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode( key );
	var regex = /[0-9]|\./;
	if( !regex.test(key) ) {
		theEvent.returnValue = false;
		if(theEvent.preventDefault) theEvent.preventDefault();
	}
}

//-------------KPI Selection Page - Selected List-----------------
function selectlist(ele){
	$("#loading").show();
	if(ele.checked==false){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var content = xhttp.responseText;
				if(content.indexOf("null")===-1){
					document.getElementById("kpi_selection_segment").innerHTML=content;
					var retval = confirm("Kpi used in some segment Do you want to remove ?");
					if(!(retval)){
						ele.checked=true;
						selectedlist();				
					}
					else{
						document.getElementById("kpi_selection_segment").innerHTML="";				
					}
				}
				else{
					document.getElementById("kpi_selection_segment").innerHTML="";				
				}
			}
		};
		xhttp.open("GET", "http://localhost:9000/xoportal/config/get_target/\""+ele.value+"\"", true);
		xhttp.send();
	}
	selectedlist();
}
function selectedlist(){
	var temp = document.getElementsByClassName("select_kpi");	
	var oSelField = document.getElementById("kpi selected");
	oSelField.style.visibility="visible";
	oSelField.innerHTML="";
	var co=0;
	for(var i=0; temp[i]; i++)
	{
		if(temp[i].checked===true)
		{
			co++;
		}
	}
	if(co===0){
		oSelField.style.visibility="hidden";
	}
	oSelField.size=co+1;
	for(var i=0; temp[i]; i++)
	{
		if(temp[i].checked===true)
		{
			var oOption = document.createElement("OPTION");
			oSelField.options.add(oOption);
			oOption.text = temp[i].value;
			oOption.value = temp[i].value;
			oOption.setAttribute("class","kpi_select_update");
			oOption.setAttribute("onclick","showsegment(this.value);")
		}
	}
	$("#loading").fadeOut(500);
}
var temp = document.getElementsByClassName("select_kpi");
selectlist(temp[0]);

//-------------KPI Selection - Segment with KPI--------
function showsegment(kpi_selected){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var content = xhttp.responseText;
			if(content.indexOf("null")===-1){
				document.getElementById("kpi_selection_segment").innerHTML=content;
				return true;
			}
			else{
				document.getElementById("kpi_selection_segment").innerHTML="";				
				return false;
			}
		}
	};
	xhttp.open("GET", "http://localhost:9000/xoportal/config/get_target/\""+kpi_selected+"\"", true);
	xhttp.send();
}

//--------------------------KPI-selection Update--------------
function kpi_select_update()
{
	$("#loading").show(500);
	var kpi_selected = document.getElementsByClassName("kpi_select_update");
	var selected_kpi=null;
	for(var i=0;i<kpi_selected.length;i++){
		if(selected_kpi===null)
		{
			selected_kpi=kpi_selected[i].value;
		}
		else
		{
			selected_kpi=selected_kpi+","+kpi_selected[i].value;
		}
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			alert("Data Updated");
			segment(document.getElementById("segment").value);
		}
	};
	xhttp.open("GET", "http://localhost:9000/xoportal/config/update/"+selected_kpi, true);
	xhttp.send();
}

//--------------------------Target-selection Update--------------
function update_target(){
	$("#loading").show();
	var kpi_selected_name=null;
	var kpi_start_value = null;
	var kpi_weight_value = null;	
	var co= 0;
	for(i=1;i<=3;i++){
		var tableid = "kpi "+i+" tabel";
		var di = document.getElementById(tableid).style.display;
		if(di=="block"){
			co=co+1;
		}
	}

	for(var i=1;i<=co;i++){
		var kpi_selected = document.getElementById("kpi "+i+" label").innerHTML;
		var starting_value = document.getElementById("input start "+i).value;
		var weightage_value = document.getElementById("input weight "+i).value;		
		if(kpi_selected!=="")
		{
			if(kpi_selected_name===null){
				kpi_selected_name = kpi_selected;
			}
			else{
				kpi_selected_name=kpi_selected_name+","+kpi_selected;
			}
		
			if(starting_value==="" || weightage_value===""){
				alert("Enter all field");
				return;
			}

			if(kpi_start_value===null){
				kpi_start_value=starting_value;
				kpi_weight_value = weightage_value;
			}
			else{
				kpi_start_value=kpi_start_value+","+starting_value;
				kpi_weight_value =kpi_weight_value+","+weightage_value;				
			}
		}
	}

	var segment_name = document.getElementById("segment").value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			$("#loading").fadeOut(500);
			alert("Data Updated");
			disp_segment_target();
		}
	};
	xhttp.open("GET", "/config/update_target/"+segment_name+"/"+kpi_selected_name+"/"+kpi_start_value+"/"+kpi_weight_value, true);
	xhttp.send();
}

function disp_segment_target(){
	$("#loading").show();	
	document.getElementById("disp_segment").innerHTML="";	
	var kpi_selected=null;
	var co =3-document.getElementById("remainingkpi").innerHTML;
	if(co>0)
	{
		for(i=1;i<=co;i++){
			var labelid = "kpi "+i+" label";
			if(kpi_selected===null){
				kpi_selected="\""+document.getElementById(labelid).innerHTML+"\"";
			}
			else{
				kpi_selected=kpi_selected+",\""+document.getElementById(labelid).innerHTML+"\"";
			}
		}
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var content = xhttp.responseText;
			if(content.indexOf("null")===-1){
				document.getElementById("disp_segment").innerHTML=content;
			}
			else{
				document.getElementById("disp_segment").innerHTML="";				
			}
			$("#loading").fadeOut(500);
		}
	};
	xhttp.open("GET", "http://localhost:9000/xoportal/config/get_target/"+kpi_selected, true);
	xhttp.send();
}
$('.menu .item').tab();

$('.ui.dropdown').dropdown();

$('.ui.checkbox').checkbox();