package com.davnicwil.gimmetoken.token.management;

import java.util.Set;

import com.davnicwil.gimmetoken.model.Token;
import com.davnicwil.gimmetoken.token.management.exception.TokenValueDoesNotExistException;
import com.davnicwil.gimmetoken.token.management.exception.TokenValueExpiredException;

public interface TokenManager {

	Token createNew(String key);
	Boolean isValid(Token token) throws TokenValueDoesNotExistException, TokenValueExpiredException;
	Set<String> getAllTokenKeys();
	Set<String> getAllExpiredTokenKeys();
	Set<String> getAllActiveTokenKeys();
	void purgeAllExpiredTokens();
}
