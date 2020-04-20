package com.witek.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.witek.model.AttendanceList;
import com.witek.model.Employee;
import com.witek.model.Months;

@Repository
public interface AttendanceListDao extends JpaRepository<AttendanceList,Long> {

AttendanceList findByMonthsAndYearAndEmployee(Months months, String year, Employee employee) ;
}