package com.davnicwil.gimmetoken.time.impl;

import com.davnicwil.gimmetoken.time.Clock;
import com.davnicwil.gimmetoken.time.CurrentTimeProvider;
import com.google.inject.Inject;

public class ClockImpl implements Clock {

	private CurrentTimeProvider currentTimeProvider;
	
	@Inject
	public ClockImpl(CurrentTimeProvider currentTimeProvider) {
		this.currentTimeProvider = currentTimeProvider;
	}

	public Long now() {
		return currentTimeProvider.getCurrentTime();
	}

	public Long getTimeXMillisInFuture(Long millis) {
		return now() + millis;
	}
}