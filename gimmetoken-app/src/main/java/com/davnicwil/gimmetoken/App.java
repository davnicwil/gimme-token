package com.davnicwil.gimmetoken;

import com.davnicwil.gimmetoken.api.AdminResource;
import com.davnicwil.gimmetoken.api.TokenResource;
import com.davnicwil.gimmetoken.guice.Builder;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration> {

	public static final void main(String[] args) throws Exception { new App().run(args); }
	public void initialize(Bootstrap<AppConfiguration> bootstrap) {}

	public void run(AppConfiguration configuration, Environment environment) throws Exception {
		Builder resourceBuilder = new Builder(configuration);
		environment.jersey().register(resourceBuilder.build(TokenResource.class));
		environment.jersey().register(resourceBuilder.build(AdminResource.class));
	}
}
