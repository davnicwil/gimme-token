package com.davnicwil.gimmetoken.client;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/admin")
public interface AdminEndpoint {
	
	@GET
	Set<String> getAllTokenKeys();
	
	@GET
	@Path("/expired")
	Set<String> getAllExpiredTokens();
	
	@GET
	@Path("/active")
	Set<String> getAllNonExpiredTokens();
	
	@POST
	@Path("/purge-expired")
	void purgeExpiredTokens();
}