package com.davnicwil.gimmetoken.inject;

import com.davnicwil.gimmetoken.AppConfiguration;
import com.davnicwil.time.guice.TimeModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Builder {

	private Injector injector;
	
	public Builder(AppConfiguration configuration) {
		this.injector = buildInjector(configuration);
	}
	
	public <T> T build(Class<T> resourceType) {
		return injector.getInstance(resourceType);
	}
	
	private Injector buildInjector(AppConfiguration configuration) {
		return Guice.createInjector(new AppModule(configuration.tokenLength), new TimeModule());
	}
}
