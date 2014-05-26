package com.davnicwil.gimmetoken.token;

import java.util.Set;

import com.davnicwil.gimmetoken.model.Token;
import com.davnicwil.gimmetoken.token.exception.TokenValueDoesNotExistException;
import com.davnicwil.gimmetoken.token.exception.TokenValueExpiredException;

public interface TokenManager {

	Token createNew(String key);
	Boolean isValid(Token token) throws TokenValueDoesNotExistException, TokenValueExpiredException;
	Set<String> getAllTokenKeys();
	Set<String> getAllExpiredTokenKeys();
	Set<String> getAllActiveTokenKeys();
	void purgeAllExpiredTokens();
}
