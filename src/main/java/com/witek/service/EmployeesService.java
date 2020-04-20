
package com.witek.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sun.istack.NotNull;
import com.witek.dao.EmployeesDao;
import com.witek.dao.EmployerDao;
import com.witek.model.AttendanceList;
import com.witek.model.Employee;
import com.witek.model.Employer;
import com.witek.model.PositionEnum;
import com.witek.repo.EmployeesRepo;

@Service

public class EmployeesService {

	private EmployeesDao employeeDao;
	private EmployeesRepo employeesRepo;
	private List<Employee> lista;
	private EmployerDao employerDao;

	@Autowired
	public EmployeesService(EmployeesDao employeeDao, EmployeesRepo employeesRepo,EmployerDao employerDao) {
		this.employeeDao = employeeDao;
		this.employeesRepo = employeesRepo;
		this.employerDao=employerDao;
	}

	public Employee getOneById(Long id) {
		return employeeDao.getOne(id);
	}

	public List<Employee> getListOfAllEmployees() {
		lista = employeeDao.findAll();

		// lista = jobsRepo.generateEmployeesList();
		// employeeDao.saveAll(lista);

		return lista;
	}

	public void saveNewEmployee(Employee employee) {
		employeeDao.save(employee);
	}

	public void editEmployee(Employee employee) {
		System.out.println("Editing : "  + employee);
		Employee fromDb = employeeDao.findById(employee.getEmployeeId()).get();
		if (employee.getName()!="") {
			fromDb.setName(employee.getName());
		}
		if (employee.getSurname()!="") {
			fromDb.setSurname(employee.getSurname());
		}
		if (employee.getCity()!="") {
			fromDb.setCity(employee.getCity());
		}
		if (employee.getCountry()!="") {
			fromDb.setCountry(employee.getCountry());
		}
		if (employee.getDateOfBirth()!=null) {
			fromDb.setDateOfBirth(employee.getDateOfBirth());
		}
		if (employee.getPosition()!=null) {
			fromDb.setPosition(employee.getPosition());
		}
		if (employee.getEmployer()!=null) {
			fromDb.setEmployer(employee.getEmployer());
		}
		if (employee.getWage()!=0) {
			fromDb.setWage(employee.getWage());
		}
		employeeDao.save(fromDb);
		
	}

	public void removeEmployeeById(Long id) {
		Employee empToDelete = employeeDao.findById(id).get();
		Employer employer = empToDelete.getEmployer();
		employer.getListOfEmployees().remove(empToDelete);
		employerDao.save(employer);
		
		employeeDao.delete(empToDelete);
	}

	public List<Employee> findEmployeeBySurname(String surname) {
		List<Employee> list = employeeDao.findBySurname(surname);
		return list;
	}

	public List<Employee> findEmployeeByCountry(String country) {
		List<Employee> list = employeeDao.findByCountry(country);
		return list;
	}

	public List<Employee> findEmployeeByCity(String city) {
		List<Employee> list = employeeDao.findByCity(city);
		return list;
	}

	public List<Employee> findEmployeeByEmployer(String employer) {
		List<Employee> list = employeeDao.findByEmployerName(employer);
		return list;
	}

	public List<Employee> findEmployeeByPosition(String position) {
		PositionEnum positionEnum = PositionEnum.valueOf(position);
		System.out.println("lookam za : " + position);
		List<Employee> list = employeeDao.findByPosition(positionEnum);
		System.out.println(list);
		return list;
	}

	public void riseUpForEveryone(double howMuch) {
		lista = employeeDao.findAll();
		lista = lista.stream().peek(x -> x.setWage(x.getWage() + howMuch)).collect(Collectors.toList());
		employeeDao.saveAll(lista);
	}

	public List<Employee> sortListBy(String criteria) {
		lista = employeeDao.findAll(Sort.by(Sort.Direction.ASC, criteria));
		return lista;
	}
}
