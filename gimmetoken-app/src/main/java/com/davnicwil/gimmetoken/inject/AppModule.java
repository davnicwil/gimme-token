package com.davnicwil.gimmetoken.inject;

import com.davnicwil.crypto.RandomStringGenerator;
import com.davnicwil.crypto.impl.RandomStringGeneratorImpl;
import com.davnicwil.gimmetoken.core.tokens.TokenRepo;
import com.davnicwil.gimmetoken.core.tokens.impl.TokenRepoImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {

    private Integer tokenLength;

    public AppModule(Integer tokenLength) {
        this.tokenLength = tokenLength;
    }

    protected void configure() {
        bind(TokenRepo.class).to(TokenRepoImpl.class);
    }

    @Provides @Singleton
    public RandomStringGenerator bindRandomStringGenerator() { return new RandomStringGeneratorImpl(tokenLength); }
}
