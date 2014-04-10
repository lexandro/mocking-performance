package com.lexandro.service;

import static org.easymock.EasyMock.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.lexandro.entity.User;
import com.lexandro.persistence.RegistrationDao;

public class SimpleRegistrationServiceMockitoTest {

	private static final String USER_NAME = "lexandro";

	private static final long NUMBER_OF_STEPS = 10000l;
	//
	private RegistrationDao registrationDao;
	//
	private User testUser = new User();;

	@Test
	public void shouldMeasureMockInteraction() throws Exception {
		long mockitoMockCreation = measureMockitoMockInteraction(NUMBER_OF_STEPS) / NUMBER_OF_STEPS;
		long easyMockCreation = measureEasyMockMockInteraction(NUMBER_OF_STEPS) / NUMBER_OF_STEPS;
		System.out.println("Avg. Mockito mock interaction time: " + mockitoMockCreation + " ns.");
		System.out.println("Avg. EasyMock mock interaction time: " + easyMockCreation + " ns.");
	}

	@Test
	public void shouldMeasureMockCreation() {
		long mockitoMockCreation = measureMockitoMockCreation(NUMBER_OF_STEPS) / NUMBER_OF_STEPS;
		long easyMockCreation = measureEasyMockMockCreation(NUMBER_OF_STEPS) / NUMBER_OF_STEPS;
		System.out.println("Avg. Mockito mock creation time: " + mockitoMockCreation + " ns.");
		System.out.println("Avg. EasyMock mock creation time: " + easyMockCreation + " ns.");
	}

	@Test
	public void shouldMeasureMockVerification() throws Exception {
		long mockitoMockVerification = measureMockitoMockVerification(NUMBER_OF_STEPS) / NUMBER_OF_STEPS;
		long easyMockVerification = measureEasyMockMockVerification(NUMBER_OF_STEPS) / NUMBER_OF_STEPS;
		System.out.println("Avg. Mockito mock verification time: " + mockitoMockVerification + " ns.");
		System.out.println("Avg. EasyMock mock verification time: " + easyMockVerification + " ns.");
	}

	private long measureMockitoMockVerification(long numberOfSteps) {
		registrationDao = createMockitoMock();
		registrationDao.findByName(USER_NAME);
		//
		long start = System.nanoTime();
		for (long i = 0; i < numberOfSteps; i++) {
			verify(registrationDao).findByName(USER_NAME);
		}
		long end = System.nanoTime();
		return end - start;
	}

	private long measureEasyMockMockVerification(long numberOfSteps) {
		registrationDao = createEasyMockMock();
		expect(registrationDao.findByName(USER_NAME)).andReturn(testUser);
		replay(registrationDao);
		registrationDao.findByName(USER_NAME);
		//
		long start = System.nanoTime();
		for (long i = 0; i < numberOfSteps; i++) {
			org.easymock.EasyMock.verify(registrationDao);
		}
		long end = System.nanoTime();
		return end - start;
	}

	private long measureMockitoMockCreation(long numberOfSteps) {
		long start = System.nanoTime();
		for (long i = 0; i < numberOfSteps; i++) {
			registrationDao = createMockitoMock();
		}
		long end = System.nanoTime();
		return end - start;
	}

	private long measureEasyMockMockCreation(long numberOfSteps) {
		long start = System.nanoTime();
		for (long i = 0; i < numberOfSteps; i++) {
//			registrationDao = createEasyMockMock();
			registrationDao = EasyMock.createMock(RegistrationDao.class);
		}
		long end = System.nanoTime();
		return end - start;
	}
	
	@SuppressWarnings("unused")
	private long measureMockitoMockInteraction(long numberOfSteps) {
		registrationDao = createMockitoMock();
		when(registrationDao.findByName(USER_NAME)).thenReturn(testUser);
		User result = null;
		//
		long start = System.nanoTime();
		for (long i = 0; i < numberOfSteps; i++) {
			result = registrationDao.findByName(USER_NAME);
		}
		long end = System.nanoTime();
		return end - start;

	}

	@SuppressWarnings("unused")
	private long measureEasyMockMockInteraction(long numberOfSteps) {
		registrationDao = createEasyMockMock();
		replay(registrationDao);
		User result = null;
		//
		long start = System.nanoTime();
		for (long i = 0; i < numberOfSteps; i++) {
			result = registrationDao.findByName(USER_NAME);
		}
		long end = System.nanoTime();
		return end - start;
	}

	private RegistrationDao createMockitoMock() {
		return mock(RegistrationDao.class);
	}

	private RegistrationDao createEasyMockMock() {
		return EasyMock.createNiceMock(RegistrationDao.class);
	}

}
