package com.witek.model;

import java.io.Serializable;
import java.time.Month;
import java.time.Year;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AttendanceList implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long attendanceListId;

//private Month month;
private Months months;
private String year;
private double workedHours;
private double monthlyWages;
@ManyToOne(fetch = FetchType.EAGER,targetEntity = Employee.class,cascade = {CascadeType.PERSIST,CascadeType.MERGE}) 
private Employee employee;

public AttendanceList(Long  id,Months months, String year, double workedHours, double monthlyWages) {
	this.attendanceListId=id;
	this.months=months;
	this.year=year;
	this.workedHours=workedHours;
	this.monthlyWages=monthlyWages;
}
public AttendanceList() {}

@Override
public String toString() {
	return "AttendanceList [attendanceListId=" + attendanceListId + ", month=" + months + ", year=" + getYear()
			+ ", workedHours=" + workedHours + ", monthlyWages=" + monthlyWages + ", employee=" + employee + "]";
}
public Long getAttendanceListId() {
	return attendanceListId;
}
public void setAttendanceListId(Long attendanceListId) {
	this.attendanceListId = attendanceListId;
}

public double getWorkedHours() {
	return workedHours;
}
public void setWorkedHours(double workedHours) {
	this.workedHours = workedHours;
}
public double getMonthlyWages() {
	return monthlyWages;
}
public void setMonthlyWages(double monthlyWages) {
	this.monthlyWages = monthlyWages;
}
public Employee getEmployee() {
	return employee;
}
public void setEmployee(Employee employee) {
	this.employee = employee;
}

public Months getMonths() {
	return months;
}
public void setMonths(Months months) {
	this.months = months;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
}