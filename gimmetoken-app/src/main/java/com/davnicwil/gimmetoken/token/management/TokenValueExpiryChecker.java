package com.davnicwil.gimmetoken.token.management;

import com.davnicwil.gimmetoken.token.storage.model.ExpiringTokenValue;

public interface TokenValueExpiryChecker {

	Boolean isExpired(ExpiringTokenValue expiringTokenValue);
}
