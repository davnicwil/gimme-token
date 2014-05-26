package com.davnicwil.gimmetoken.token.impl;

import com.davnicwil.gimmetoken.model.ExpiringTokenValue;
import com.davnicwil.gimmetoken.time.Clock;
import com.davnicwil.gimmetoken.token.TokenValueExpiryChecker;

public class TokenValueExpiryCheckerImpl implements TokenValueExpiryChecker {

	private Clock clock;
	
	public TokenValueExpiryCheckerImpl(Clock clock) {
		this.clock = clock;
	}

	public Boolean isExpired(ExpiringTokenValue expiringTokenValue) {
		return clock.now() >= expiringTokenValue.getExpiryTimestamp();
	}
}