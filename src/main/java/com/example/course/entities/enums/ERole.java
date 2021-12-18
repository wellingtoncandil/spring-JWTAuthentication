package com.example.course.entities.enums;

public enum ERole {

	ROLE_USER(1),
	ROLE_MODERATOR(2),
	ROLE_ADMIN(3);

	private int code;

	ERole(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ERole valueOf(int code) {
		for (ERole role : ERole.values()) {
			if (role.getCode() == code) {
				return role;
			}
		}
		throw new IllegalArgumentException("Status invalido");
	}
}
