package com.witek.repo;

import java.util.ArrayList;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Repository;

import com.witek.model.Employer;

@Repository
public class EmployerRepo {

	
	private List<Employer> listOfEmployers;

	public List<Employer> generateEmployersList() {
		DataFactory df = new DataFactory();
		listOfEmployers = new ArrayList<Employer>();
		Employer [] array = new  Employer [10] ;
		for (int i = 0; i < 10; i++) {
			
			array[i] = new Employer();
			Employer emp = array[i];
			
			emp.setName(df.getFirstName());
			emp.setCity(df.getCity());
			listOfEmployers.add(emp);
			System.out.println(emp);
		}
		return listOfEmployers;
	}
}	