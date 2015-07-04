package com.davnicwil.gimmetoken.core.tokens.guice;

import com.davnicwil.gimmetoken.core.tokens.TokenRepo;
import com.davnicwil.gimmetoken.core.tokens.impl.TokenRepoImpl;
import com.google.inject.AbstractModule;

public class GimmeTokenCoreModule extends AbstractModule {

    protected void configure() {
        bind(TokenRepo.class).to(TokenRepoImpl.class);
    }
}
