package com.davnicwil.gimmetoken.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.davnicwil.gimmetoken.model.ExpiringTokenValue;
import com.davnicwil.gimmetoken.time.Clock;
import com.davnicwil.gimmetoken.token.impl.TokenValueExpiryCheckerImpl;

@RunWith(MockitoJUnitRunner.class)
public class TokenValueExpiryCheckerImplUTest {

	private static final Long NOW = 1000l;
	private static final Long EXPIRY_TIME_IN_PAST = NOW - 1;
	private static final Long EXPIRY_TIME_IN_FUTURE = NOW + 1;
	private static final ExpiringTokenValue EXPIRED_TOKEN = new ExpiringTokenValue("expired", EXPIRY_TIME_IN_PAST);
	private static final ExpiringTokenValue ACTIVE_TOKEN = new ExpiringTokenValue("active", EXPIRY_TIME_IN_FUTURE);
	
	@Mock private Clock clock;
	
	@Before
	public void setup() {
		when(clock.now()).thenReturn(NOW);
	}
	
	@Test
	public void givenTokenValueIsExpiredItShouldTellMeItIsExpired() {
		TokenValueExpiryCheckerImpl testObj = new TokenValueExpiryCheckerImpl(clock);
		assertTrue(testObj.isExpired(EXPIRED_TOKEN));
	}
	
	@Test
	public void givenTokenValueIsActiveItShouldTellMeItIsNotExpired() {
		TokenValueExpiryCheckerImpl testObj = new TokenValueExpiryCheckerImpl(clock);
		assertFalse(testObj.isExpired(ACTIVE_TOKEN));
	}
}