angular.module('app', ['ngResource'])
.controller('Controller', function($http, $resource) {
	var vm = this;
	var Employer = $resource('api/employer/:EmployerId');
	vm.Employer = new Employer();
	
	function refreshData($http) {
		vm.Employers = Employer.query(
				function success(data, headers) {
					console.log('Pobrano dane: ' +  data);
					console.log(vm.Employer.__proto__);
					console.log(headers('Content-Type'));
				},
				function error(response) {
					console.log(response.status); //np. 404
				});
	}
	vm.loadData = function(employerId) {
		vm.details = Employer.get({employerId: employerId});
	}
	vm.addEmployer = function(Employer) {
		console.log(vm.Employer.__proto__);
		vm.Employer.$save(function(data) {
			refreshData();
			vm.Employer = new Employer();
		});
	}
	
	vm.loadData = function(id) {
		vm.details = Employer.get({EmployerId: id});
	}
	
	vm.appName = 'Employer Manager';
	refreshData();
});





function showEmployerEditForm() {
	var div = document.getElementById('changeEmployerDetailsForm');	
			div.style.display="block";	
	}
function closeEmployerEditForm(){
	var div = document.getElementById('changeEmployerDetailsForm');	
	div.style.display="none";
}

function showEmployeesList() {
	var div = document.getElementById("employeesList");	
			div.style.display="block";	
	}
function closeEmployeesList(){
	var div = document.getElementById('employeesList');	
	div.style.display="none";
}

function sortTable(n) {
var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;

 table = document.getElementsByClassName("steelBlueCols")[0];
 switching = true;
 dir = "asc";
 while (switching) {
 switching = false;
 rows = table.getElementsByTagName("TR");
 for (i = 1; i < (rows.length - 1); i++) {
 shouldSwitch = false;
 x = rows[i].getElementsByTagName("TD")[n];
 y = rows[i + 1].getElementsByTagName("TD")[n];
 var xContent = (isNaN(x.innerHTML))
 ? (x.innerHTML.toLowerCase() === '-')
 ? 0 : x.innerHTML.toLowerCase()
 : parseFloat(x.innerHTML);
 var yContent = (isNaN(y.innerHTML))
 ? (y.innerHTML.toLowerCase() === '-')
 ? 0 : y.innerHTML.toLowerCase()
 : parseFloat(y.innerHTML);
 if (dir == "asc") {
 if (xContent > yContent) {
 shouldSwitch= true;
 break;
 }
 } else if (dir == "desc") {
 if (xContent < yContent) {
 shouldSwitch= true;
 break;
 }
 }
 }
 if (shouldSwitch) {
 rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
 switching = true;
 switchcount ++;
 } else {
 if (switchcount == 0 && dir == "asc") {
 dir = "desc";
 switching = true;
 }
 }
 }

 }

