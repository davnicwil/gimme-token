package com.davnicwil.gimmetoken.model.builder.impl;

import com.davnicwil.gimmetoken.crypto.RandomStringGenerator;
import com.davnicwil.gimmetoken.model.ExpiringTokenValue;
import com.davnicwil.gimmetoken.model.builder.ExpiringTokenValueBuilder;
import com.davnicwil.gimmetoken.time.Clock;
import com.google.inject.name.Named;

public class ExpiringTokenValueBuilderImpl implements ExpiringTokenValueBuilder {

	private RandomStringGenerator randomStringGenerator;
	private Clock clock;
	private Long tokenExpiryMillis;
	
	public ExpiringTokenValueBuilderImpl(RandomStringGenerator randomStringGenerator, Clock clock, @Named("token-expiry-millis") Long tokenExpiryMillis) {
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