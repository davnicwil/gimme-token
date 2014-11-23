package com.davnicwil.gimmetoken.inject;

import com.davnicwil.gimmetoken.AppConfiguration;
import com.davnicwil.gimmetoken.crypto.inject.CryptoModule;
import com.davnicwil.gimmetoken.time.inject.TimeModule;
import com.davnicwil.gimmetoken.token.management.inject.TokenManagementModule;
import com.davnicwil.gimmetoken.token.storage.inject.TokenStorageModule;
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
		return Guice.createInjector(new CryptoModule(configuration.getTokenLength()),
									new TokenManagementModule(),
									new TokenStorageModule(configuration.getTokenValueExpiryDurationMillis()),
									new TimeModule());
	}
}
