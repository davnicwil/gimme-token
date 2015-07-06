package com.davnicwil.gimmetoken.client;

import com.davnicwil.gimmetoken.model.TokenBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TokenEndpoint {

	@POST
	@Path("/{id}")
	Response add(@PathParam("id") Long id);

	@POST
	@Path("/authorise/{id}")
	Response authorise(@PathParam("id") Long id, TokenBody tokenBody);

	@POST
	@Path("/remove-all/{id}")
	Response removeAll(@PathParam("id") Long id);

	@POST
	@Path("/remove/{id}")
	Response remove(@PathParam("id") Long id, TokenBody payload);
}