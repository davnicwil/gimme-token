package com.davnicwil.gimmetoken.api;

import com.davnicwil.gimmetoken.client.AdminEndpoint;
import com.davnicwil.gimmetoken.core.tokens.TokenRepo;
import com.google.inject.Inject;

import javax.ws.rs.core.Response;

public class AdminResource implements AdminEndpoint {

	private TokenRepo tokenRepo;
	
	@Inject
	public AdminResource(TokenRepo tokenRepo) {
		this.tokenRepo = tokenRepo;
	}

	public Response getTotalNumberOfIds() {
		return Responses.OK(tokenRepo.getNumberOfIds());
	}

	public Response getTotalNumberOfTokens() {
		return Responses.OK(tokenRepo.getNumberOfTokens());
	}

	public Response getNumberOfTokensFor(Long id) {
		return Responses.OK(tokenRepo.getNumberOfTokens(id));
	}
}
