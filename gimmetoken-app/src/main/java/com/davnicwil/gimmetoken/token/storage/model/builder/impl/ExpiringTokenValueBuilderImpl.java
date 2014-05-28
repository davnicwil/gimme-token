package com.davnicwil.gimmetoken.token.storage.model.builder.impl;

import com.davnicwil.gimmetoken.crypto.RandomStringGenerator;
import com.davnicwil.gimmetoken.time.Clock;
import com.davnicwil.gimmetoken.token.storage.model.ExpiringTokenValue;
import com.davnicwil.gimmetoken.token.storage.model.builder.ExpiringTokenValueBuilder;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ExpiringTokenValueBuilderImpl implements ExpiringTokenValueBuilder {

	private RandomStringGenerator randomStringGenerator;
	private Clock clock;
	private Long tokenExpiryMillis;
	
	@Inject
	public ExpiringTokenValueBuilderImpl(RandomStringGenerator randomStringGenerator, 
											Clock clock, 
											@Named("token-value-expiry-duration-millis") Long tokenExpiryMillis) {
		this.randomStringGenerator = randomStringGenerator;
		this.clock = clock;
		this.tokenExpiryMillis = tokenExpiryMillis;
	}

	public ExpiringTokenValue build() {
		return new ExpiringTokenValue(randomStringGenerator.generate(), getNewExpiryTimestamp());
	}

	private Long getNewExpiryTimestamp() {
		return clock.getTimeXMillisInFuture(tokenExpiryMillis);
	}
}