package com.davnicwil.gimmetoken.api;

import com.davnicwil.gimmetoken.client.AdminEndpoint;
import com.davnicwil.gimmetoken.core.tokens.TokenRepo;
import com.davnicwil.time.Clock;
import com.google.inject.Inject;

import javax.ws.rs.core.Response;

public class AdminResource implements AdminEndpoint {

	private static final String FORBIDDEN_HELP_MESSAGE = "as a security measure, this request is only actioned when it is sent within a minute of current server time. You sent the request at %s - current server time is %s";

	private TokenRepo tokenRepo;
	private Clock clock;
	
	@Inject
	public AdminResource(TokenRepo tokenRepo, Clock clock) {
		this.tokenRepo = tokenRepo;
		this.clock = clock;
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

	public Response wipe(Long commandTime) {
		if(!clock.withinLastMinute(commandTime)) return Responses.FORBIDDEN(String.format(FORBIDDEN_HELP_MESSAGE, commandTime, clock.now()));
		tokenRepo.wipe();
		return Responses.OK;
	}
}
