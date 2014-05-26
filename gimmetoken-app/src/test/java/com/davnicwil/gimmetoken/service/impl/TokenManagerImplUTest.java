package com.davnicwil.gimmetoken.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.davnicwil.gimmetoken.model.ExpiringTokenValue;
import com.davnicwil.gimmetoken.model.ExpiringTokenValueStore;
import com.davnicwil.gimmetoken.model.Token;
import com.davnicwil.gimmetoken.model.builder.ExpiringTokenValueBuilder;
import com.davnicwil.gimmetoken.token.ExpiringTokenValueStoreInspector;
import com.davnicwil.gimmetoken.token.TokenValueExpiryChecker;
import com.davnicwil.gimmetoken.token.exception.TokenValueDoesNotExistException;
import com.davnicwil.gimmetoken.token.exception.TokenValueExpiredException;
import com.davnicwil.gimmetoken.token.impl.TokenManagerImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TokenManagerImplUTest {

	private static final Long TIME_X = 1000l;
	private static final ExpiringTokenValue BUILT_TOKEN_VALUE = new ExpiringTokenValue("built", TIME_X);
	private static final ExpiringTokenValue ACTIVE_TOKEN_VALUE = new ExpiringTokenValue("active", TIME_X);
	private static final ExpiringTokenValue EXPIRED_TOKEN_VALUE = new ExpiringTokenValue("expired", TIME_X);
	
	@Mock private ExpiringTokenValueStore expiringTokenValueStore;
	@Mock private ExpiringTokenValueBuilder expiringTokenValueBuilder;
	@Mock private TokenValueExpiryChecker tokenValueExpiryChecker;
	@Mock private ExpiringTokenValueStoreInspector tokenStoreInspector;
	
	private TokenManagerImpl testObj;
	
	@Before
	public void setup() {
		testObj = new TokenManagerImpl(expiringTokenValueStore, expiringTokenValueBuilder, tokenValueExpiryChecker, tokenStoreInspector);
	}
	
	@Test
	public void givenItIsInstructedToCreateANewTokenItShouldReturnTheCreatedToken() {
		when(expiringTokenValueBuilder.build()).thenReturn(BUILT_TOKEN_VALUE);
		
		Token createdToken = testObj.createNew("new-key");
		
		assertEquals("new-key", createdToken.getKey());
		assertEquals(BUILT_TOKEN_VALUE.getValue(), createdToken.getValue());
	}
	
	@Test(expected=TokenValueDoesNotExistException.class)
	public void givenANonExistantTokenIsValidatedItShouldThrowTheAppropriateException() throws TokenValueDoesNotExistException, TokenValueExpiredException {
		when(expiringTokenValueStore.get("does-not-exist")).thenThrow(new TokenValueDoesNotExistException());
		
		testObj.isValid(new Token("does-not-exist", "value-does-not-matter-it-does-not-exist"));
	}
	
	@Test(expected=TokenValueExpiredException.class)
	public void givenAnExpiredTokenValueIsValidatedItShouldThrowTheAppropriateException() throws TokenValueDoesNotExistException, TokenValueExpiredException {
		when(expiringTokenValueStore.get("expired-token")).thenReturn(EXPIRED_TOKEN_VALUE);
		when(tokenValueExpiryChecker.isExpired(EXPIRED_TOKEN_VALUE)).thenReturn(true);
		
		testObj.isValid(new Token("expired-token", EXPIRED_TOKEN_VALUE.getValue()));
	}
	
	@Test
	public void givenAValidTokenValueItShouldReportItIsValid() throws TokenValueDoesNotExistException, TokenValueExpiredException {
		when(expiringTokenValueStore.get("active-token")).thenReturn(ACTIVE_TOKEN_VALUE);
		when(tokenValueExpiryChecker.isExpired(ACTIVE_TOKEN_VALUE)).thenReturn(false);
		
		assertTrue(testObj.isValid(new Token("active-token", ACTIVE_TOKEN_VALUE.getValue())));
	}
	
	@Test
	public void givenAnInvalidTokenValueItShouldReportItIsNotValid() throws TokenValueDoesNotExistException, TokenValueExpiredException {
		when(expiringTokenValueStore.get("active-token")).thenReturn(ACTIVE_TOKEN_VALUE);
		when(tokenValueExpiryChecker.isExpired(ACTIVE_TOKEN_VALUE)).thenReturn(false);
		
		assertFalse(testObj.isValid(new Token("active-token", "some-incorrect-value")));
	}
	
	
}