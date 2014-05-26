package com.davnicwil.gimmetoken.time.impl;

import com.davnicwil.gimmetoken.time.CurrentTimeProvider;

public class CurrentTimeProviderImpl implements CurrentTimeProvider {

	public Long getCurrentTime() {
		return System.currentTimeMillis();
	}
}
