package com.davnicwil.gimmetoken.resource;

import java.util.Set;

import javax.ws.rs.core.Response;

import com.davnicwil.gimmetoken.client.AdminEndpoint;
import com.davnicwil.gimmetoken.token.management.TokenManager;
import com.google.inject.Inject;

public class AdminResource implements AdminEndpoint {

	private static final Response SUCCESS_NO_CONTENT = Response.status(204).build();
	
	private TokenManager tokenManager;
	
	@Inject
	public AdminResource(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}
	
	public Set<String> getAllTokenKeys() {
		return tokenManager.getAllTokenKeys();
	}
	
	public Set<String> getAllExpiredTokenKeys() {
		return tokenManager.getAllExpiredTokenKeys();
	}
	
	public Set<String> getAllNonExpiredTokenKeys() {
		return tokenManager.getAllActiveTokenKeys();
	}
	
	public Response purgeExpiredTokens() {
		tokenManager.purgeAllExpiredTokens();
		return SUCCESS_NO_CONTENT;
	}
}
