package com.witek.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.witek.model.Employee;
import com.witek.model.PositionEnum;

@Repository
public interface EmployeesDao extends JpaRepository<Employee, Long> {

List <Employee> findBySurname(String surname);
List <Employee> findByCountry(String country);
List <Employee> findByCity(String city);
List<Employee> findByEmployer(String employer);
List<Employee> findByEmployerName(String employer);
List<Employee> findByPosition(PositionEnum position);
List<Employee> findAllByOrderByWageDesc();
	
}
