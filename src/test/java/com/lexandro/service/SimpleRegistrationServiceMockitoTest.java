package com.lexandro.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.lexandro.entity.User;
import com.lexandro.persistence.RegistrationDao;

public class SimpleRegistrationServiceMockitoTest {

	private static final String EMAIL = "lexandro2000@g.......com";
	private static final String USER_NAME = "lexandro";
	@InjectMocks
	private SimpleRegistrationService registrationService;
	//
	@Mock
	private RegistrationDao registrationDao;
	//
	private User testUser;
	private User emptyUser;

	@Before
	public void setUp() throws Exception {
		registrationService = new SimpleRegistrationService();
		initMocks(this);
		//
		testUser = new UserBuilder().withName(USER_NAME).withPassword("123").withEmail(EMAIL).build();
		emptyUser = new UserBuilder().withEmpty(true).build();
		//
		when(registrationDao.findByName(USER_NAME)).thenReturn(emptyUser);
		when(registrationDao.findByEmail(EMAIL)).thenReturn(emptyUser);
	}

	@Test
	public void test() {
		// when
		registrationService.doRegister(testUser);
		// then
		verify(registrationDao).save(testUser);
	}

}
