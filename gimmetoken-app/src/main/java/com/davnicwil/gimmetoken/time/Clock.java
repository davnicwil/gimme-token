package com.davnicwil.gimmetoken.time;

public interface Clock {

	Long now();
	Long getTimeXMillisInFuture(Long millis);
}
