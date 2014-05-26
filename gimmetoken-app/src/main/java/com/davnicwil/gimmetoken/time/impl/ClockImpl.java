package com.davnicwil.gimmetoken.time.impl;

import com.davnicwil.gimmetoken.time.Clock;
import com.davnicwil.gimmetoken.time.CurrentTimeProvider;

public class ClockImpl implements Clock {

	private CurrentTimeProvider currentTimeProvider;
	
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