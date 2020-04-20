package com.witek.model;

public enum Months {

	JANUARY ("JANUARY"),
	FEBRUARY ("FEBRUARY"),
	MARCH ("MARCH"),
	APRIL ("APRIL"),
	MAY ("MAY"),
	JUNE ("JUNE"),
	JULY ("JULY"),
	AUGUST ("AUGUST"),
	SEPTEMBER ("SEPTEMBER"),
	OCTOBER ("OCTOBER"),
	NOVEMBER ("NOVEMBER"),
	DECEMBER ("DECEMBER");
	
	private String desc;
	
	Months(String desc) {
		this.desc=desc;
	}
}
