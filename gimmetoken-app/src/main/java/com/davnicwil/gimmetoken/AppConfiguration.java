package com.davnicwil.gimmetoken;

import com.yammer.dropwizard.config.Configuration;

public class AppConfiguration extends Configuration {

	private static final Long FIFTEEN_MINS_IN_MILLIS = 1000l * 60l * 15l;
	
	private Boolean adminEnabled = false;
	private Long tokenValueExpiryDurationMillis = FIFTEEN_MINS_IN_MILLIS;

	public Boolean isAdminEnabled() { return adminEnabled; }
	public void setAdminEnabled(Boolean adminEnabled) { this.adminEnabled = adminEnabled; }
	
	public Long getTokenValueExpiryDurationMillis() { return tokenValueExpiryDurationMillis; }
	public void setTokenValueExpiryDurationMillis(Long tokenValueExpiryDurationMillis) { this.tokenValueExpiryDurationMillis = tokenValueExpiryDurationMillis; }
}
