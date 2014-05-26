package com.davnicwil.gimmetoken.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.davnicwil.gimmetoken.model.ExpiringTokenValue;

public class ExpiringTokenValueUTest {

	private static final Long TIME_X = 100l;
	
	@Test
	public void itShouldReportEqualToEqualValue() {
		ExpiringTokenValue testObj = new ExpiringTokenValue("same-value", TIME_X);
		assertTrue(testObj.isEqualTo("same-value"));
	}
	
	@Test
	public void itShouldReportNotEqualToNotEqualValue() {
		ExpiringTokenValue testObj = new ExpiringTokenValue("same-value", TIME_X);
		assertFalse(testObj.isEqualTo("different-value"));
	}
}