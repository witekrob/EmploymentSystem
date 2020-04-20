package com.witek.controller;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.witek.model.AttendanceList;
import com.witek.model.Employee;
import com.witek.model.Employer;
import com.witek.model.PositionEnum;
import com.witek.service.AttendanceListService;
import com.witek.service.EmployeesService;

@Controller
public class AttendanceListController {
private EmployeesService empService;
private AttendanceListService attendanceService;

@Autowired
public AttendanceListController(EmployeesService empService, AttendanceListService attendanceService) {
	this.empService=empService;
	this.attendanceService=attendanceService;
}
	
@PostMapping("/addNewAttendanceList")	
public String addNewAttendanceList(AttendanceList attendanceList, Long employeeId,double wage, double workedHours,Model model) {
	Employee employee = empService.getOneById(employeeId);
	
	attendanceList.setEmployee(employee);
	
	attendanceList = attendanceService.checkIfExist(attendanceList);
	System.out.println(attendanceList);
	
	attendanceList.setMonthlyWages(wage*workedHours);
	employee.getAttendanceLists().add(attendanceList);
	empService.saveNewEmployee(employee);
	//attendanceService.saveList(attendanceList);
	
	model.addAttribute("employee", employee);
	PositionEnum [] positions = PositionEnum.values();
	model.addAttribute("positions", positions);
	return "redirect:/showInfoAboutEmployee?employeeId="+employee.getEmployeeId();
	//return "employeePage";	
}
@PostMapping("/editAttendanceList")
public String editAttendanceList (Long attendanceListId, double newWorkedHours,Model model) {
	System.out.println(newWorkedHours);
	System.out.println(attendanceListId);
	AttendanceList listToEdit = attendanceService.findAttendanceListById(attendanceListId);
	Employee employee = listToEdit.getEmployee();
	System.out.println("Edytujemy listę " + listToEdit.getAttendanceListId());
	listToEdit.setWorkedHours(newWorkedHours);
	double newMonthlyWages = newWorkedHours*employee.getWage(); 
	listToEdit.setMonthlyWages(newMonthlyWages);
	attendanceService.saveList(listToEdit);
	
	model.addAttribute("employee", employee);
	return "redirect:/showInfoAboutEmployee?employeeId="+employee.getEmployeeId();
	//return "employeePage";
	}
@PostMapping("/removeAttendanceList")
public String removeAttendanceList(Long attendanceListId, Model model) {
AttendanceList listToBeRemoved = attendanceService.findAttendanceListById(attendanceListId);
Employee emp = listToBeRemoved.getEmployee();
emp.getAttendanceLists().remove(listToBeRemoved);
empService.saveNewEmployee(emp);
attendanceService.removeAttendanceListById(attendanceListId);
model.addAttribute("employee", emp);
return "redirect:/showInfoAboutEmployee?employeeId="+emp.getEmployeeId();
//return "employeePage";

}
}
