package com.davnicwil.gimmetoken.model;

public class TokenBody {

    public String authToken;

    public TokenBody() {} // for Jackson

    public TokenBody(String authToken) {
        this.authToken = authToken;
    }
}
