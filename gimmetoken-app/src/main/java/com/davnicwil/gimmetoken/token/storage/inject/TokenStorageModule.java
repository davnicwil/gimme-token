package com.davnicwil.gimmetoken.token.storage.inject;

import com.davnicwil.gimmetoken.token.storage.ExpiringTokenValueStore;
import com.davnicwil.gimmetoken.token.storage.ExpiringTokenValueStoreInspector;
import com.davnicwil.gimmetoken.token.storage.impl.ExpiringTokenValueStoreInspectorImpl;
import com.davnicwil.gimmetoken.token.storage.impl.InMemoryExpiringTokenValueStore;
import com.davnicwil.gimmetoken.token.storage.model.builder.ExpiringTokenValueBuilder;
import com.davnicwil.gimmetoken.token.storage.model.builder.impl.ExpiringTokenValueBuilderImpl;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class TokenStorageModule extends AbstractModule {

	private final Long tokenValueExpiryDurationMillis;
	
	public TokenStorageModule(Long tokenValueExpiryDurationMillis) {
		this.tokenValueExpiryDurationMillis = tokenValueExpiryDurationMillis;
	}
	
	@Override
	protected void configure() {
		configureTokenValueExpiryDurationMillis();
		
		bind(ExpiringTokenValueStore.class).to(getExpiringTokenValueStoreImplementationClass());
		bind(ExpiringTokenValueStoreInspector.class).to(ExpiringTokenValueStoreInspectorImpl.class);
		bind(ExpiringTokenValueBuilder.class).to(ExpiringTokenValueBuilderImpl.class);
	}
	
	private void configureTokenValueExpiryDurationMillis() {
		bind(Long.class).annotatedWith(Names.named("token-value-expiry-duration-millis")).toInstance(tokenValueExpiryDurationMillis);
	}
	
	// Change the token storage implementation here
	private Class<? extends ExpiringTokenValueStore> getExpiringTokenValueStoreImplementationClass() {
		return InMemoryExpiringTokenValueStore.class;
	}
}
