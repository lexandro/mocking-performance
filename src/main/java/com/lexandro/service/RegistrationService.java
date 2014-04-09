package com.lexandro.service;

import com.lexandro.entity.RegistrationResult;
import com.lexandro.entity.User;

public interface RegistrationService {
	
	RegistrationResult doRegister(User user);

}
