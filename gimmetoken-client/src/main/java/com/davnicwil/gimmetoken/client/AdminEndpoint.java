package com.davnicwil.gimmetoken.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public interface AdminEndpoint {
	
	@GET
	@Path("/number-of-ids")
	Response getTotalNumberOfIds();
	
	@GET
	@Path("/number-of-tokens")
	Response getTotalNumberOfTokens();
	
	@GET
	@Path("/number-of-tokens/{id}")
	Response getNumberOfTokensFor(@PathParam("id") Long id);
}