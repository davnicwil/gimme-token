package com.davnicwil.gimmetoken;

import com.yammer.dropwizard.config.Configuration;

public class AppConfiguration extends Configuration {

	private static final Integer DEFAULT_LENGTH_32 = 32;
	private static final Long DEFAULT_15_MIN_EXPIRY = 1000l * 60l * 15l;
	
	private Boolean tokenAdminEnabled = false;
	private Long tokenValueExpiryDurationMillis = DEFAULT_15_MIN_EXPIRY;
	private Integer tokenLength = DEFAULT_LENGTH_32;

	public Boolean isTokenAdminEnabled() { return tokenAdminEnabled; }
	public void setTokenAdminEnabled(Boolean tokenAdminEnabled) { this.tokenAdminEnabled = tokenAdminEnabled; }
	
	public Long getTokenValueExpiryDurationMillis() { return tokenValueExpiryDurationMillis; }
	public void setTokenValueExpiryDurationMillis(Long tokenValueExpiryDurationMillis) { this.tokenValueExpiryDurationMillis = tokenValueExpiryDurationMillis; }
	
	public Integer getTokenLength() { return tokenLength; }
	public void setTokenLength(Integer tokenLength) { this.tokenLength = tokenLength; }
}
