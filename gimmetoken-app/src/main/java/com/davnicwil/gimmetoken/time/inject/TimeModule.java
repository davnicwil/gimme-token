package com.davnicwil.gimmetoken.time.inject;

import com.davnicwil.gimmetoken.time.Clock;
import com.davnicwil.gimmetoken.time.CurrentTimeProvider;
import com.davnicwil.gimmetoken.time.impl.ClockImpl;
import com.davnicwil.gimmetoken.time.impl.CurrentTimeProviderImpl;
import com.google.inject.AbstractModule;

public class TimeModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Clock.class).to(ClockImpl.class);
		bind(CurrentTimeProvider.class).to(CurrentTimeProviderImpl.class);
	}
}