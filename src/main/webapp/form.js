document.write('<div class="center" id="formFilter"><h2 id="titleForm"></h2>');
document.write('<table align="center" >');
document.write('<tr><td align="left">Nome:</td><td><input type="text" id="name"></td></tr>');
document.write('<tr><td align="left">Cognome:</td><td><input type="text" id="surname"></td></tr>');
document.write('<tr id="tr1"><td align="left">Data di nascita (da):</td><td align="left"><input type="date" id="dateFrom"></td></tr>');
document.write('<tr id="tr2"><td align="left">Data di nascita (a):</td><td align="left"><input type="date" id="dateTo"></td></tr>');
document.write('<tr id="tr3"><td align="left">Numero di record:</td><td align="left"><input type="number" id="record" value="5" min="1" max="250"></td></tr>');
document.write('<tr id="tr4"><td align="left">Chiave:</td><td align="left"><input type="text" id="key"></td></tr>');
document.write('<tr id="tr5"><td align="left">Data di nascita:</td><td align="left"><input type="date" id="birth"></td></tr>');
document.write('<tr></tr>');
document.write('<tr><td align="left">');
document.write('<input type="button" class="button" id="find" value="Cerca">');
document.write('</td><td align="left">');
document.write('<input type="button" class="button" id="add" value="Aggiungi" onclick="addPerson()">');
document.write('</td>');
document.write('</tr><tr>');
document.write('<td align="left">');
document.write('<input type="button" class="button" id="update" value="Modifica">');
document.write('</td><td align="left">');
document.write('<input type="button" class="button" id="back" value="Indietro">');
document.write('</td>');
document.write('<td align="left">');
document.write('<input type="button" class="button" id="save" value="Salva">');
document.write('</td>');
document.write('</table>');
document.write('</div>');

setInitialForm();

$("#back").click(function() {
	setInitialForm();
});

function setInitialForm(){
	$('#formFilter').show();
	$('#titleForm').html('Form di ricerca');
	$('#tr1').show();
	$('#tr2').show();
	$('#tr3').show();
	$('#add').show();
	$('#find').show();	
	$('#tr4').hide();
	$('#tr5').hide();
	$('#update').hide();
	$('#save').hide();
	$('#back').hide();	
	$('#myTable').hide();
	$('#findDiv').hide();
}

$("#find").click(function() {
	var name = $("#name").val();
	var surname = $("#surname").val();
	var date1 = $("#dateFrom").val();
	var date2 = $("#dateTo").val();
	var recordNum = $("#record").val();
	var filterInput = {
		"name" : name,
		"surname" : surname,
		"date1" : date1,
		"date2" : date2,
		"recordNum" : recordNum
	};
	var settings = {
		"url" : "http://localhost:8080/webapp/people/list",
		"method" : "POST",
		"timeout" : 0,
		"headers" : {
			"Content-Type" : "application/json"
		},
		"data" : JSON.stringify(filterInput),
	};
	$.ajax(settings).done(function(response) {
		writeTable(response);
	});
});

$("#save").click(function() {
	var name = $("#name").val();
	var surname = $("#surname").val();
	var key = $("#key").val();
	var date = $("#birth").val();
	var person = {
		"key" : key,
		"name" : name,
		"surname" : surname,
		"date" : date
	};
	var settings = {
		"url" : "http://localhost:8080/webapp/people/person",
		"method" : "POST",
		"timeout" : 0,
		"headers" : {
			"Content-Type" : "application/json"
		},
		"data" : JSON.stringify(person),
	};
	$.ajax(settings).done(function(response) {
		alert("La persona è stata inserita!");
		location.reload();
	});
});

$("#update").click(function() {
	var name = $("#name").val();
	var surname = $("#surname").val();
	var key = $("#key").val();
	var date = $("#birth").val();
	var person = {
		"key" : key,
		"name" : name,
		"surname" : surname,
		"date" : date
	};
	var settings = {
		"url" : "http://localhost:8080/webapp/people/person/" + key,
		"method" : "PUT",
		"timeout" : 0,
		"headers" : {
			"Content-Type" : "application/json"
		},
		"data" : JSON.stringify(person),
	};
	$.ajax(settings).done(function(response) {
		alert("La persona selezionata è stata modificata!");
		//location.reload();
	});
});

function writeTable(data) {
	$('#formFilter').hide();
	for (var i = 0; i < data.length; i++) {
		var person = data[i];
		var trPerson = "<tr><td>"
				+ person.key
				+ "</td><td>"
				+ person.name
				+ "</td><td>"
				+ person.surname
				+ "</td><td><input type='button' id='update' value='Modifica' onclick='updatePerson("
				+ person.key
				+ ")'></td><td><input type='button' id='delete' value='Elimina' onclick='deletePerson("
				+ person.key + ")'></td></tr>";
		$('#bodyTable').append(trPerson);
	}
	$('#myTable').show();
	$('#findDiv').show();
	$('#myTable').DataTable();
}

$(document).ready(function() {
	$('#myTable').hide();
	$('#findDiv').hide();
});

function addPerson(key) {
	$('#findDiv').hide();
	$('#formFilter').show();
	$('#titleForm').html('Aggiungi persona');
	$('#tr4').show();
	$('#tr5').show();
	$('#tr1').hide();
	$('#tr2').hide();
	$('#tr3').hide();
	$('#add').hide();
	$('#update').hide();
	$('#find').hide();
	$('#save').show();
	$('#back').show();	
}

function updatePerson(key) {
	$('#findDiv').hide();
	$('#formFilter').show();
	$('#tr4').show();
	$('#tr5').show();
	$('#tr1').hide();
	$('#tr2').hide();
	$('#tr3').hide();
	$('#update').show();
	$('#find').hide();
	$('#add').hide();
	$('#save').hide();
	$('#key').val(key);
	$('#key').attr("disabled", true);
}


function deletePerson(key) {
	var settings = {
		"url" : "http://localhost:8080/webapp/people/person/" + key,
		"method" : "DELETE",
		"timeout" : 0,
	};
	$.ajax(settings).done(function(response) {
		alert("La persona con chiave " + key + " è stata eliminata!");
		location.reload();
	});
}
