package com.davnicwil.gimmetoken.client;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public interface AdminEndpoint {
	
	@GET
	Set<String> getAllTokenKeys();
	
	@GET
	@Path("/expired")
	Set<String> getAllExpiredTokenKeys();
	
	@GET
	@Path("/active")
	Set<String> getAllNonExpiredTokenKeys();
	
	@GET
	@Path("/purge-expired")
	Response purgeExpiredTokens();
}