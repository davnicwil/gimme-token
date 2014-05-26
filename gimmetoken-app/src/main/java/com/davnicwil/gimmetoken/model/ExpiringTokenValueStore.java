package com.davnicwil.gimmetoken.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.davnicwil.gimmetoken.token.exception.TokenValueDoesNotExistException;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

public class ExpiringTokenValueStore {

	private Map<String, ExpiringTokenValue> tokens;
	
	public ExpiringTokenValueStore() {
		tokens = new HashMap<String, ExpiringTokenValue>();
	}
	
	public void put(String key, ExpiringTokenValue value) {
		tokens.put(key, value);
	}

	public ExpiringTokenValue get(String key)throws TokenValueDoesNotExistException {
		ExpiringTokenValue value = tokens.get(key);
		if(value == null) {
			throw new TokenValueDoesNotExistException();
		}
		return value;
	}

	public Set<String> getKeys() {
		return tokens.keySet();
	}

	public void delete(String key) {
		tokens.remove(key);
	}

	public Set<String> getKeys(Predicate<ExpiringTokenValue> filter) {
		return Maps.filterValues(tokens, filter).keySet();
	}
}