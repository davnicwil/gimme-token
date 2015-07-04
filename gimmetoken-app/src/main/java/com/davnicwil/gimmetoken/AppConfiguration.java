package com.davnicwil.gimmetoken;

import io.dropwizard.Configuration;

public class AppConfiguration extends Configuration {

	public Integer tokenLength = 64; // default token length to 64
}