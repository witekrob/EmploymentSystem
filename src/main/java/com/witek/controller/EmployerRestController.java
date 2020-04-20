package com.witek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.witek.model.Employer;
import com.witek.service.EmployerService;

@RestController
@RequestMapping("/api/")
public class EmployerRestController {
	private EmployerService empService;
	private List<Employer> employersList;
	@Autowired
	public EmployerRestController(EmployerService empService) {
		this.empService=empService;
	}

	@GetMapping ("/employer/{id}")
	public ResponseEntity<Employer> getObeById(@PathVariable Long id){
	Employer emp = empService.getOneById(id);	
	return new ResponseEntity<Employer>(emp,HttpStatus.OK);
	}
	@GetMapping("/employer")
	public ResponseEntity<List<Employer>> getALlEmployers(){
	employersList = empService.getListOfAllEmployers();
	return new ResponseEntity<List<Employer>>(employersList,HttpStatus.OK);
	}
	}

