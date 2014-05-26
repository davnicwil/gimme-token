package com.davnicwil.gimmetoken.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.davnicwil.gimmetoken.token.management.TokenValueExpiryChecker;
import com.davnicwil.gimmetoken.token.storage.ExpiringTokenValueStore;
import com.davnicwil.gimmetoken.token.storage.impl.ExpiringTokenValueStoreInspectorImpl;
import com.davnicwil.gimmetoken.token.storage.impl.InMemoryExpiringTokenValueStore;
import com.davnicwil.gimmetoken.token.storage.model.ExpiringTokenValue;

@RunWith(MockitoJUnitRunner.class)
public class ExpiringTokenStoreInspectorImplUTest {

	private ExpiringTokenValue ACTIVE_TOKEN_VALUE_1 = new ExpiringTokenValue("active-one", 100l);
	private ExpiringTokenValue ACTIVE_TOKEN_VALUE_2 = new ExpiringTokenValue("active-two", 100l);
	private ExpiringTokenValue EXPIRED_TOKEN_VALUE_1 = new ExpiringTokenValue("expired-one", 0l);
	private ExpiringTokenValue EXPIRED_TOKEN_VALUE_2 = new ExpiringTokenValue("expired-two", 0l);
	
	@Mock private TokenValueExpiryChecker tokenValueExpiryChecker;
	
	@Before
	public void setup() {
		when(tokenValueExpiryChecker.isExpired(ACTIVE_TOKEN_VALUE_1)).thenReturn(false);
		when(tokenValueExpiryChecker.isExpired(ACTIVE_TOKEN_VALUE_2)).thenReturn(false);
		when(tokenValueExpiryChecker.isExpired(EXPIRED_TOKEN_VALUE_1)).thenReturn(true);
		when(tokenValueExpiryChecker.isExpired(EXPIRED_TOKEN_VALUE_2)).thenReturn(true);
	}
	
	@Test
	public void givenOnlyExpiredTokensItShouldGetTheExpiredTokens() {
		ExpiringTokenValueStoreInspectorImpl testObj = new ExpiringTokenValueStoreInspectorImpl(tokenValueExpiryChecker);
		ExpiringTokenValueStore expiringTokenValueStore = new InMemoryExpiringTokenValueStore();
		expiringTokenValueStore.put("one", EXPIRED_TOKEN_VALUE_1);
		expiringTokenValueStore.put("two", EXPIRED_TOKEN_VALUE_2);
		
		Set<String> expiredKeys = testObj.getAllExpiredTokenKeys(expiringTokenValueStore);
		
		assertEquals(2, expiredKeys.size());
		assertTrue(expiredKeys.contains("one"));
		assertTrue(expiredKeys.contains("two"));
	}
	
	@Test
	public void givenNoExpiredTokensItShouldGetNoExpiredTokens() {
		ExpiringTokenValueStoreInspectorImpl testObj = new ExpiringTokenValueStoreInspectorImpl(tokenValueExpiryChecker);
		ExpiringTokenValueStore expiringTokenValueStore = new InMemoryExpiringTokenValueStore();
		expiringTokenValueStore.put("one", ACTIVE_TOKEN_VALUE_1);
		
		Set<String> expiredTokenKeys = testObj.getAllExpiredTokenKeys(expiringTokenValueStore);
		
		assertTrue(expiredTokenKeys.isEmpty());
	}
	
	@Test
	public void givenAMixOfExpiredAndActiveTokensItShouldGetTheExpiredTokens() {
		ExpiringTokenValueStoreInspectorImpl testObj = new ExpiringTokenValueStoreInspectorImpl(tokenValueExpiryChecker);
		ExpiringTokenValueStore expiringTokenValueStore = new InMemoryExpiringTokenValueStore();
		expiringTokenValueStore.put("one", ACTIVE_TOKEN_VALUE_1);
		expiringTokenValueStore.put("two", ACTIVE_TOKEN_VALUE_2);
		expiringTokenValueStore.put("three", EXPIRED_TOKEN_VALUE_1);
		expiringTokenValueStore.put("four", EXPIRED_TOKEN_VALUE_2);
		
		Set<String> expiredTokenKeys = testObj.getAllExpiredTokenKeys(expiringTokenValueStore);
		
		assertEquals(2, expiredTokenKeys.size());
		assertTrue(expiredTokenKeys.contains("three"));
		assertTrue(expiredTokenKeys.contains("four"));
	}
	
	@Test
	public void givenOnlyActiveTokensItShouldGetTheActiveTokens() {
		ExpiringTokenValueStoreInspectorImpl testObj = new ExpiringTokenValueStoreInspectorImpl(tokenValueExpiryChecker);
		ExpiringTokenValueStore expiringTokenValueStore = new InMemoryExpiringTokenValueStore();
		expiringTokenValueStore.put("one", ACTIVE_TOKEN_VALUE_1);
		expiringTokenValueStore.put("two", ACTIVE_TOKEN_VALUE_2);
		
		Set<String> activeTokenKeys = testObj.getAllActiveTokenKeys(expiringTokenValueStore);
		
		assertEquals(2, activeTokenKeys.size());
		assertTrue(activeTokenKeys.contains("one"));
		assertTrue(activeTokenKeys.contains("two"));
	}
	
	@Test
	public void givenNoActiveTokensItShouldGetNoActiveTokens() {
		ExpiringTokenValueStoreInspectorImpl testObj = new ExpiringTokenValueStoreInspectorImpl(tokenValueExpiryChecker);
		ExpiringTokenValueStore expiringTokenValueStore = new InMemoryExpiringTokenValueStore();
		expiringTokenValueStore.put("one", EXPIRED_TOKEN_VALUE_1);
		
		Set<String> activeTokenKeys = testObj.getAllActiveTokenKeys(expiringTokenValueStore);
		
		assertTrue(activeTokenKeys.isEmpty());
	}
	
	@Test
	public void givenAMixOfExpiredAndActiveTokensItShouldGetTheActiveTokens() {
		ExpiringTokenValueStoreInspectorImpl testObj = new ExpiringTokenValueStoreInspectorImpl(tokenValueExpiryChecker);
		ExpiringTokenValueStore expiringTokenValueStore = new InMemoryExpiringTokenValueStore();
		expiringTokenValueStore.put("one", ACTIVE_TOKEN_VALUE_1);
		expiringTokenValueStore.put("two", ACTIVE_TOKEN_VALUE_2);
		expiringTokenValueStore.put("three", EXPIRED_TOKEN_VALUE_1);
		expiringTokenValueStore.put("four", EXPIRED_TOKEN_VALUE_2);
		
		Set<String> activeTokenKeys = testObj.getAllActiveTokenKeys(expiringTokenValueStore);
		
		assertEquals(2, activeTokenKeys.size());
		assertTrue(activeTokenKeys.contains("one"));
		assertTrue(activeTokenKeys.contains("two"));
	}
}