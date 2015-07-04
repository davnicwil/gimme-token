package com.davnicwil.gimmetoken.client;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface TokenEndpoint {

	@POST
	@Path("/{id}")
	Response add(@PathParam("id") Long id);

	@GET
	@Path("/{id}/{token}")
	Response authorise(@PathParam("id") Long id, @PathParam("token") String token);

	@DELETE
	@Path("/{id}")
	Response remove(@PathParam("id") Long id);

	@DELETE
	@Path("/{id}/{token}")
	Response remove(@PathParam("id") Long id, @PathParam("token") String token);
}