package com.davnicwil.gimmetoken.token.management.impl;

import com.davnicwil.gimmetoken.time.Clock;
import com.davnicwil.gimmetoken.token.management.TokenValueExpiryChecker;
import com.davnicwil.gimmetoken.token.storage.model.ExpiringTokenValue;

public class TokenValueExpiryCheckerImpl implements TokenValueExpiryChecker {

	private Clock clock;
	
	public TokenValueExpiryCheckerImpl(Clock clock) {
		this.clock = clock;
	}

	public Boolean isExpired(ExpiringTokenValue expiringTokenValue) {
		return clock.now() >= expiringTokenValue.getExpiryTimestamp();
	}
}