package com.lexandro.persistence;

import com.lexandro.entity.User;

public interface RegistrationDao {

	void save(User user);

	User findByEmail(String email);

	User findByName(String userName);

}
