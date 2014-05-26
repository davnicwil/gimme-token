package com.davnicwil.gimmetoken.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.davnicwil.gimmetoken.model.Token;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TokenEndpoint {

	@Path("/token")
	@POST
	public Token create(String key);
	
	@Path("/token/authorise")
	@POST
	public Response authorise(Token token);
}