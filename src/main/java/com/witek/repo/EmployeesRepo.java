package com.witek.repo;

import java.util.ArrayList;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Repository;

import com.witek.model.Employee;

@Repository
public class EmployeesRepo {

	private List<Employee> listOfEmployees;

	public List<Employee> generateEmployeesList() {
		DataFactory df = new DataFactory();
		listOfEmployees = new ArrayList<Employee>();
		Employee [] array = new  Employee [100] ;
		for (int i = 0; i < 50; i++) {
			
			array[i] = new Employee();
			Employee e = array[i];
			
			e.setName(df.getFirstName());
			e.setSurname(df.getLastName());
			e.setDateOfBirth(df.getBirthDate());
			e.setCity(df.getCity());
			listOfEmployees.add(e);
		}

		return listOfEmployees;
	}
}
