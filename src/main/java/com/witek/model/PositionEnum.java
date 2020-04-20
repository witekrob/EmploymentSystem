package com.witek.model;

public enum PositionEnum {
	
	JUNIOR ("Junior"),
	REGULAR ("Regular"),
	SENIOR ("Senior"),
	MANAGER ("Manager");
	
	private String desc;
	
	PositionEnum(String desc) {
		this.desc=desc;
	}
}
