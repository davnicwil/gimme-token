package com.davnicwil.gimmetoken.crypto.inject;

import com.davnicwil.gimmetoken.crypto.RandomStringGenerator;
import com.davnicwil.gimmetoken.crypto.impl.RandomStringGeneratorImpl;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class CryptoModule extends AbstractModule {

	private Integer tokenLength;

	public CryptoModule(Integer tokenLength) {
		this.tokenLength = tokenLength;
	}
	
	@Override
	protected void configure() {
		configureTokenLength();
		bind(RandomStringGenerator.class).to(RandomStringGeneratorImpl.class);
	}
	
	private void configureTokenLength() {
		bind(Integer.class).annotatedWith(Names.named("token-length")).toInstance(tokenLength);
	}
}