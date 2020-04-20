package com.witek.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Employer implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employerId;
	private String city;
	private String name;
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	//@JsonIgnore
	@OneToMany(mappedBy = "employer",fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST})//,CascadeType.REMOVE })
	private List<Employee> listOfEmployees;

	public Employer() {
	}

	public Employer(Long employerId, String city, String name,List<Employee>listOfEmployees) {
		this.employerId = employerId;
		this.name = name;
		this.city = city;
		this.listOfEmployees=listOfEmployees;
	}

	@Override
	public String toString() {
		return "Employer [employerId=" + employerId + ", city=" + city + ", name=" + name + "]";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((employerId == null) ? 0 : employerId.hashCode());
		result = prime * result + ((listOfEmployees == null) ? 0 : listOfEmployees.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employer other = (Employer) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (employerId == null) {
			if (other.employerId != null)
				return false;
		} else if (!employerId.equals(other.employerId))
			return false;
		if (listOfEmployees == null) {
			if (other.listOfEmployees != null)
				return false;
		} else if (!listOfEmployees.equals(other.listOfEmployees))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Long getEmployerId() {
		return employerId;
	}

	public void setEmployerId(Long employerId) {
		this.employerId = employerId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getListOfEmployees() {
		return listOfEmployees;
	}

	public void setListOfEmployees(List<Employee> listOfEmployees) {
		this.listOfEmployees = listOfEmployees;
	}
}
