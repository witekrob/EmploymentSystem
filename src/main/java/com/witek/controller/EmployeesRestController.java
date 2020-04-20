package com.witek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.witek.model.Employee;
import com.witek.service.EmployeesService;

@RestController
@RequestMapping("/api/")

public class EmployeesRestController {

		private EmployeesService empService;
		private List<Employee> employeesList;

		@Autowired
		public EmployeesRestController(EmployeesService empService) {
			this.empService = empService;
		}

		@GetMapping("/employee/{id}")
		public ResponseEntity<Employee> getObeById(@PathVariable Long id) {
			Employee emp = empService.getOneById(id);
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}

		@GetMapping("/employee")
		public ResponseEntity<List<Employee>> getALlEmployees() {
			employeesList = empService.getListOfAllEmployees();
			return new ResponseEntity<List<Employee>>(employeesList, HttpStatus.OK);
		}
		
	}

