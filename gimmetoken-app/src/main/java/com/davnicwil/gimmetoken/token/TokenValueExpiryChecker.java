package com.davnicwil.gimmetoken.token;

import com.davnicwil.gimmetoken.model.ExpiringTokenValue;

public interface TokenValueExpiryChecker {

	Boolean isExpired(ExpiringTokenValue expiringTokenValue);
}
