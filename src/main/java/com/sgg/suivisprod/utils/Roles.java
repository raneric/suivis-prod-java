package com.sgg.suivisprod.utils;

public enum Roles {
	ADMIN("ADMIN"), USER("USER"),;

	private String roleType;
	
	Roles(String type) {
		this.roleType = type;
	}
	
	public String getValue() {
		return roleType;
	}
}
