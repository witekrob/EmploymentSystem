package com.witek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witek.dao.AttendanceListDao;
import com.witek.model.AttendanceList;

@Service
public class AttendanceListService {
	private AttendanceListDao attenDao;

	@Autowired
	public AttendanceListService(AttendanceListDao attenDao) {
		this.attenDao = attenDao;
	}

	public void saveList(AttendanceList attendanceList) {
		attenDao.save(attendanceList);
	}
	public AttendanceList findAttendanceListById(Long attendanceListId) {
		AttendanceList list = attenDao.findById(attendanceListId).get();
		return list;
	}
	public void removeAttendanceListById(Long attendanceListId) {
		AttendanceList listToRemove = attenDao.findById(attendanceListId).get();
		System.out.println("removing att.list : " +listToRemove);
		attenDao.deleteById(attendanceListId);
	}

	public AttendanceList checkIfExist(AttendanceList attendanceList) {
		AttendanceList check = new AttendanceList();
		check = attenDao.findByMonthsAndYearAndEmployee
		(attendanceList.getMonths(), attendanceList.getYear(), attendanceList.getEmployee());
		if (check==null) {
			check=attendanceList;
		}
		else {
		check.setEmployee(attendanceList.getEmployee());
		check.setMonths(attendanceList.getMonths());
		check.setYear(attendanceList.getYear());
		check.setWorkedHours(attendanceList.getWorkedHours());
		}
		return check;	
	}
}
