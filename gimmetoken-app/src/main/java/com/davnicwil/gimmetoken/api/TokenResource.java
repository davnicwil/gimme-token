package com.davnicwil.gimmetoken.api;

import com.davnicwil.crypto.RandomStringGenerator;
import com.davnicwil.gimmetoken.client.TokenEndpoint;
import com.davnicwil.gimmetoken.core.tokens.TokenRepo;
import com.google.inject.Inject;

import javax.ws.rs.core.Response;

public class TokenResource implements TokenEndpoint {

	private TokenRepo tokenRepo;
	private final RandomStringGenerator randomStringGenerator;

	@Inject
	public TokenResource(TokenRepo tokenRepo, RandomStringGenerator randomStringGenerator) {
		this.tokenRepo = tokenRepo;
		this.randomStringGenerator = randomStringGenerator;
	}

	public Response add(Long id) {
		String newToken = randomStringGenerator.generate();
		tokenRepo.add(id, newToken);
		return Responses.CREATED(newToken);
	}

	public Response authorise(Long id, String token) {
		return tokenRepo.exists(id, token) ? Responses.OK : Responses.UNAUTHORISED;
	}

	public Response removeAll(Long id) {
		tokenRepo.removeAll(id);
		return Responses.OK;
	}

	public Response remove(Long id, String token) {
		tokenRepo.remove(id, token);
		return Responses.OK;
	}
}