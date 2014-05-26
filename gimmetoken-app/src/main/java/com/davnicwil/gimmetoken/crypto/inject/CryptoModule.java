package com.davnicwil.gimmetoken.crypto.inject;

import com.davnicwil.gimmetoken.crypto.RandomStringGenerator;
import com.davnicwil.gimmetoken.crypto.impl.RandomStringGeneratorImpl;
import com.google.inject.AbstractModule;

public class CryptoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RandomStringGenerator.class).to(RandomStringGeneratorImpl.class);
	}
}