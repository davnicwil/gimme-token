package com.davnicwil.gimmetoken.api;

import com.davnicwil.crypto.RandomStringGenerator;
import com.davnicwil.gimmetoken.client.TokenEndpoint;
import com.davnicwil.gimmetoken.core.tokens.TokenRepo;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.ws.rs.core.Response;

public class TokenResource implements TokenEndpoint {

	private TokenRepo tokenRepo;
	private RandomStringGenerator randomStringGenerator;
	private Integer tokenLength;

	@Inject
	public TokenResource(TokenRepo tokenRepo, RandomStringGenerator randomStringGenerator, @Named("tokenLength") Integer tokenLength) {
		this.tokenRepo = tokenRepo;
		this.randomStringGenerator = randomStringGenerator;
		this.tokenLength = tokenLength;
	}

	public Response add(Long id) {
		String newToken = randomStringGenerator.generate(tokenLength);
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