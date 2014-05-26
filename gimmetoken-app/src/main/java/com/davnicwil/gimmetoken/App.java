package com.davnicwil.gimmetoken;

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
		// TODO Auto-generated method stub
	}
}
