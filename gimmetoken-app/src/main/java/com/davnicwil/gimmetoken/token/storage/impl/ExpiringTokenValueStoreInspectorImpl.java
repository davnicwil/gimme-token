package com.davnicwil.gimmetoken.token.storage.impl;

import java.util.Set;

import com.davnicwil.gimmetoken.token.management.TokenValueExpiryChecker;
import com.davnicwil.gimmetoken.token.storage.ExpiringTokenValueStore;
import com.davnicwil.gimmetoken.token.storage.ExpiringTokenValueStoreInspector;
import com.davnicwil.gimmetoken.token.storage.model.ExpiringTokenValue;
import com.google.common.base.Predicate;
import com.google.inject.Inject;

public class ExpiringTokenValueStoreInspectorImpl implements ExpiringTokenValueStoreInspector {

	private Predicate<ExpiringTokenValue> isExpired;
	private Predicate<ExpiringTokenValue> isActive;
	
	@Inject
	public ExpiringTokenValueStoreInspectorImpl(TokenValueExpiryChecker tokenValueExpiryChecker) {
		this.isExpired = buildIsExpiredPredicate(true, tokenValueExpiryChecker);
		this.isActive = buildIsExpiredPredicate(false, tokenValueExpiryChecker);
	}
	
	public Set<String> getAllExpiredTokenKeys(ExpiringTokenValueStore expiringTokenValueStore) {
		return expiringTokenValueStore.getKeys(isExpired);
	}

	public Set<String> getAllActiveTokenKeys(ExpiringTokenValueStore expiringTokenValueStore) {
		return expiringTokenValueStore.getKeys(isActive);
	}
	
	private Predicate<ExpiringTokenValue> buildIsExpiredPredicate(final boolean isExpired, final TokenValueExpiryChecker tokenValueExpiryChecker) {
		return new Predicate<ExpiringTokenValue>() {
			public boolean apply(ExpiringTokenValue input) {
				return tokenValueExpiryChecker.isExpired(input) == isExpired;
			}
		};
	}
}
