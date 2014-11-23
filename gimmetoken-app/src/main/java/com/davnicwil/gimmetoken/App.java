package com.davnicwil.gimmetoken;

import com.davnicwil.gimmetoken.inject.Builder;
import com.davnicwil.gimmetoken.resource.AdminResource;
import com.davnicwil.gimmetoken.resource.TokenResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class App extends Service<AppConfiguration> {

	public static final void main(String[] args) throws Exception {
		new App().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<AppConfiguration> bootstrap) {
		bootstrap.setName("gimmetoken-app");
	}

	@Override
	public void run(AppConfiguration configuration, Environment environment) throws Exception {
		setupResources(configuration, environment);
	}

	private void setupResources(AppConfiguration configuration, Environment environment) {
		Builder builder = new Builder(configuration);
		environment.addResource(builder.build(TokenResource.class));
		if(configuration.isTokenAdminEnabled()) {
			environment.addResource(builder.build(AdminResource.class));
		}
	}
}
