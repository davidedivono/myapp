document.write('<div class="container-fluid" id="findDiv">');   
document.write('<table class="datatable display table table-hover table-bordered" id="myTable">');
document.write('<thead>');
document.write('<tr>');
document.write('<th>Key</th>');
document.write('<th>Name</th>');
document.write('<th>Surname</th>');
document.write('<th></th>');
document.write('<th></th>');
document.write('</tr>');
document.write('</thead>');
document.write('<tbody id="bodyTable">');
document.write('</tbody>');
document.write('</table>');
document.write('<div class="center"><input type="button" class="button" id="newFind" value="Nuova Ricerca"></div>');
document.write('</div>');

$("#newFind").click(function() {
	location.reload();
});
