package com.davnicwil.gimmetoken.api;

import javax.ws.rs.core.Response;

public class Responses {

    public static final Response OK = response(200);
    public static final Response OK(Object entity) { return response(200, entity); }
    public static final Response CREATED(Object entity) { return response(201, entity); }
    public static final Response UNAUTHORISED = response(401);
    public static final Response FORBIDDEN(String message) {  return response(403, message); }

    private static Response response(int status, Object entity) {
        return Response.status(status).entity(entity).build();
    }

    private static Response response(int status) {
        return Response.status(status).build();
    }
}
