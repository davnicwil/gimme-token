package com.davnicwil.gimmetoken.model;

public class ExpiringTokenValue {

	String value;
	Long expiryTimestamp;
	
	public ExpiringTokenValue(String value, Long expiryTimestamp) {
		this.value = value;
		this.expiryTimestamp = expiryTimestamp;
	}
	
	public String getValue() {
		return value;
	}
	
	public Long getExpiryTimestamp() {
		return expiryTimestamp;
	}
	
	public Boolean isEqualTo(String otherValue) {
		return value.equals(otherValue);
	}
}