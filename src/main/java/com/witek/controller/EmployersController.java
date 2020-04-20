package com.witek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.witek.model.Employer;
import com.witek.model.Employee;
import com.witek.service.EmployerService;


@Controller
public class EmployersController {
	private EmployerService empService;
	private List<Employer> employersList;
	@Autowired
	public EmployersController(EmployerService empService) {
		this.empService=empService;
//		employersList = empService.getListOfAllEmployers();
	}

	@GetMapping("/employersManager")
	public String goToEmployersManager(Model model) {
		employersList = empService.getListOfAllEmployers();
		model.addAttribute("employersList", employersList);
		return "employersManager";
	}

	@GetMapping("/showInfoAboutEmployer")
	public String showOneEmployer(Long employerId, Model model) {
		if (employerId == null) {
			employerId = 1L;
		}
		Employer employer = empService.getOneById(employerId);
		model.addAttribute("employer", employer);
		double wagesTotal = employer.getListOfEmployees().stream().mapToDouble(o -> o.getWage()).sum();
		model.addAttribute("wagesTotal", wagesTotal);
		return "employerPage";
	}

	@PostMapping("/createNewEmployerForm")
	public String createNewEmployerForm(@ModelAttribute Employer employer,Model model) {
		empService.saveNewEmployer(employer);
		model.addAttribute("employer", employer);
		return "employerPage";
		}

	@GetMapping("/addNewEmployer")
	public String getNewEmployerForm() {
		return "createNewEmployerForm";
	}

	@PostMapping("/showInfoAboutEmployer")
	public String editEmployerForm(@ModelAttribute Employer employer) {
		System.out.println(employer);
		empService.editEmployer(employer);
		return "employerPage";
	}

	@PostMapping("/removeEmployer/{id}")
	public String removeEmployer(@PathVariable Long id, Model model) {
		System.out.println(id);
		empService.removeEmployerById(id);
		employersList = empService.getListOfAllEmployers();
		model.addAttribute("employersList", employersList);
		//return "index";
		return "redirect:/employersManager";
	}
	@GetMapping("/employersRest")
	public String showEmployersFromRest() {
		return "employersRest";
}
}
