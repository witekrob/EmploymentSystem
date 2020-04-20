package com.witek.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.witek.dao.EmployerDao;
import com.witek.model.Employer;
import com.witek.model.Employee;
import com.witek.repo.EmployerRepo;

@Service
public class EmployerService {

	private EmployerDao employerDao;
	private List<Employer> lista;
	private EmployerRepo empRepo;
	private EmployeesService emploService;
	@Autowired
	public EmployerService(EmployerDao employerDao, EmployerRepo empRepo, EmployeesService emploService) {
		this.employerDao = employerDao;
		this.empRepo = empRepo;
		this.emploService=emploService;
	}

	public Employer getOneById(Long id) {
		return employerDao.getOne(id);
	}

	public List<Employer> getListOfAllEmployers() {
	//	lista = empRepo.generateEmployersList();
		//employerDao.saveAll(lista);
		
		lista = employerDao.findAll();
		return lista;
	}

	public void createNewEmployer(Employer employer) {
		employerDao.save(employer);

	}
	@Transactional
	public void removeEmployerById(Long id) {
		Employer toBeRemoved = employerDao.findById(id).get();
		for (Employee e:toBeRemoved.getListOfEmployees()) {
			e.setEmployer(null);
		emploService.editEmployee(e);
		}
		
		
		
		toBeRemoved.setListOfEmployees(null);
		employerDao.save(toBeRemoved);
		System.out.println(toBeRemoved.getListOfEmployees());
		employerDao.delete(toBeRemoved);
		System.out.println(employerDao.existsById(id));	
	}
	
	public void editEmployer(Employer employer) {

		Employer fromDb = employerDao.findById(employer.getEmployerId()).get();
		if (employer.getName() == "") {
			employer.setName(fromDb.getName());
		}
		if (employer.getCity() == "") {
			employer.setCity(fromDb.getCity());
		}
		//List<Employee> listOfEmployees = fromDb.getListOfEmployees();
		//employer.setListOfEmployees(listOfEmployees);
		employerDao.save(employer);
	}

	public void saveNewEmployer(Employer employer) {
		employerDao.save(employer);
	}
}
