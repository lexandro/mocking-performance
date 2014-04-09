package com.lexandro.service;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

import com.lexandro.entity.RegistrationResult;
import com.lexandro.entity.User;
import com.lexandro.persistence.RegistrationDao;

public class SimpleRegistrationService implements RegistrationService {

	private RegistrationDao registrationDao;

	@Override
	public RegistrationResult doRegister(User user) {
		if (isValidUserEntity(user)) {
			if (!isExists(user)) {				
				registrationDao.save(user);
			}
		}
		return null;
	}

	private boolean isValidUserEntity(User user) {
		return user != null && !isBlankOrNull(user.getUserName()) && !isBlankOrNull(user.getPassword());
	}

	private boolean isExists(User user) {		
		return userNameExists(user.getUserName()) || userEmailExists(user.getEmail());
	}

	private boolean userEmailExists(String email) {
		return !registrationDao.findByEmail(email).isEmpty();
	}

	private boolean userNameExists(String userName) {
		return !registrationDao.findByName(userName).isEmpty();
	}
}
