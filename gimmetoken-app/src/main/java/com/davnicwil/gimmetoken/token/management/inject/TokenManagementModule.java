package com.davnicwil.gimmetoken.token.management.inject;

import com.davnicwil.gimmetoken.token.management.TokenManager;
import com.davnicwil.gimmetoken.token.management.TokenValueExpiryChecker;
import com.davnicwil.gimmetoken.token.management.impl.TokenManagerImpl;
import com.davnicwil.gimmetoken.token.management.impl.TokenValueExpiryCheckerImpl;
import com.google.inject.AbstractModule;

public class TokenManagementModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TokenValueExpiryChecker.class).to(TokenValueExpiryCheckerImpl.class);
		bind(TokenManager.class).to(TokenManagerImpl.class);
	}
}