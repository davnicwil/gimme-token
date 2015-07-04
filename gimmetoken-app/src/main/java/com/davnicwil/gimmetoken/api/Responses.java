package com.davnicwil.gimmetoken.api;

import javax.ws.rs.core.Response;

public class Responses {

    public static final Response OK = Response.status(200).build();
    public static final Response OK(Object entity) { return response(200, entity); }
    public static final Response CREATED(Object entity) { return response(201, entity); }
    public static final Response UNAUTHORISED = Response.status(401).build();

    private static Response response(int status, Object entity) {
        return Response.status(status).entity(entity).build();
    }
}
