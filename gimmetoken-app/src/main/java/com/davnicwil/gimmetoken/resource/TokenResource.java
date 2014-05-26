package com.davnicwil.gimmetoken.resource;

import javax.ws.rs.core.Response;

import com.davnicwil.gimmetoken.client.TokenEndpoint;
import com.davnicwil.gimmetoken.model.Token;
import com.davnicwil.gimmetoken.token.management.TokenManager;
import com.davnicwil.gimmetoken.token.management.exception.TokenValueDoesNotExistException;
import com.davnicwil.gimmetoken.token.management.exception.TokenValueExpiredException;
import com.google.inject.Inject;

public class TokenResource implements TokenEndpoint {
	
	private static final Response AUTHORISED = Response.status(200).build();
	private static final Response UNAUTHORISED = Response.status(401).build();
	
	private TokenManager tokenManager;
	
	@Inject
	public TokenResource(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

	public Token create(String key) {
		return tokenManager.createNew(key);
	}

	public Response authorise(Token token) {
		try {
			return tokenManager.isValid(token) ? AUTHORISED : UNAUTHORISED;
		} catch (TokenValueDoesNotExistException ex) {
			return UNAUTHORISED;
		} catch (TokenValueExpiredException e) {
			return UNAUTHORISED;
		}
	}
}