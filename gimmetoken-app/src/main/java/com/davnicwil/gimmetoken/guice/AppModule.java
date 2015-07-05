package com.davnicwil.gimmetoken.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class AppModule extends AbstractModule {

    private Integer tokenLength;

    public AppModule(Integer tokenLength) {
        this.tokenLength = tokenLength;
    }

    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("tokenLength")).toInstance(tokenLength);
    }
}
