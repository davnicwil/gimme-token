package com.davnicwil.gimmetoken.token.management.impl;

import java.util.Set;

import com.davnicwil.gimmetoken.model.Token;
import com.davnicwil.gimmetoken.token.management.TokenManager;
import com.davnicwil.gimmetoken.token.management.TokenValueExpiryChecker;
import com.davnicwil.gimmetoken.token.management.exception.TokenValueDoesNotExistException;
import com.davnicwil.gimmetoken.token.management.exception.TokenValueExpiredException;
import com.davnicwil.gimmetoken.token.storage.ExpiringTokenValueStore;
import com.davnicwil.gimmetoken.token.storage.ExpiringTokenValueStoreInspector;
import com.davnicwil.gimmetoken.token.storage.model.ExpiringTokenValue;
import com.davnicwil.gimmetoken.token.storage.model.builder.ExpiringTokenValueBuilder;
import com.google.inject.Inject;

public class TokenManagerImpl implements TokenManager {

	private ExpiringTokenValueStore expiringTokenValueStore;
	private ExpiringTokenValueBuilder expiringTokenValueBuilder;
	private TokenValueExpiryChecker tokenValueExpiryChecker;
	private ExpiringTokenValueStoreInspector expiringTokenValueStoreInspector;

	@Inject
	public TokenManagerImpl(ExpiringTokenValueStore expiringTokenValueStore, 
							ExpiringTokenValueBuilder expiringTokenValueBuilder, 
							TokenValueExpiryChecker tokenValueExpiryChecker, 
							ExpiringTokenValueStoreInspector tokenStoreInspector) {
		this.expiringTokenValueStore = expiringTokenValueStore;
		this.expiringTokenValueBuilder = expiringTokenValueBuilder;
		this.tokenValueExpiryChecker = tokenValueExpiryChecker;
		this.expiringTokenValueStoreInspector = tokenStoreInspector;
	}

	public Token createNew(String key) {
		ExpiringTokenValue expiringTokenValue = expiringTokenValueBuilder.build();
		expiringTokenValueStore.put(key, expiringTokenValue);
		return new Token(key, expiringTokenValue.getValue());
	}

	public Boolean isValid(Token candidate) throws TokenValueDoesNotExistException, TokenValueExpiredException {
		Token token = getToken(candidate.getKey());
		return candidate.equals(token);
	}
	
	private Token getToken(String key) throws TokenValueDoesNotExistException, TokenValueExpiredException {
		ExpiringTokenValue expiringTokenValue = expiringTokenValueStore.get(key);
		if(tokenValueExpiryChecker.isExpired(expiringTokenValue)) {
			throw new TokenValueExpiredException();
		}
		return new Token(key, expiringTokenValue.getValue());
	}

	public void purgeAllExpiredTokens() {
		for(String expiredTokenKey: getAllExpiredTokenKeys()) {
			expiringTokenValueStore.delete(expiredTokenKey);
		}
	}

	public Set<String> getAllTokenKeys() {
		return expiringTokenValueStore.getKeys();
	}

	public Set<String> getAllExpiredTokenKeys() {
		return expiringTokenValueStoreInspector.getAllExpiredTokenKeys(expiringTokenValueStore);
	}

	public Set<String> getAllActiveTokenKeys() {
		return expiringTokenValueStoreInspector.getAllActiveTokenKeys(expiringTokenValueStore);
	}
}