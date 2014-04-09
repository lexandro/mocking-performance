package com.lexandro.service;

import org.apache.commons.validator.GenericValidator;

import com.lexandro.entity.User;

public class UserBuilder {

	private String userName;
	private String password;
	private String email;
	private Boolean empty;

	public UserBuilder() {

	}

	public UserBuilder withName(String userName) {
		this.userName = userName;
		return this;

	}

	public UserBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public UserBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder withEmpty(boolean empty) {
		this.empty = empty;
		return this;
	}

	public User build() {
		if (null != empty && empty) {
			return new User();
		} else if (!GenericValidator.isBlankOrNull(userName) && !GenericValidator.isBlankOrNull(password) && !GenericValidator.isBlankOrNull(email)) {
			return new User(userName, password, email);

		}
		throw new IllegalArgumentException("User name, password or email missing!");
	}
}
