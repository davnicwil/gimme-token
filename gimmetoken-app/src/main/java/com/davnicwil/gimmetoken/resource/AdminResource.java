package com.davnicwil.gimmetoken.resource;

import java.util.Set;

import com.davnicwil.gimmetoken.client.AdminEndpoint;
import com.davnicwil.gimmetoken.token.TokenManager;
import com.google.inject.Inject;

public class AdminResource implements AdminEndpoint {

	private TokenManager tokenManager;
	
	@Inject
	public AdminResource(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}
	
	public Set<String> getAllTokenKeys() {
		return tokenManager.getAllTokenKeys();
	}
	
	public Set<String> getAllExpiredTokens() {
		return tokenManager.getAllExpiredTokenKeys();
	}
	
	public Set<String> getAllNonExpiredTokens() {
		return tokenManager.getAllActiveTokenKeys();
	}
	
	public void purgeExpiredTokens() {
		tokenManager.purgeAllExpiredTokens();
	}
}
