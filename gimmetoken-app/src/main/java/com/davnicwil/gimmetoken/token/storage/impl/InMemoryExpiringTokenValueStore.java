package com.davnicwil.gimmetoken.token.storage.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.davnicwil.gimmetoken.token.management.exception.TokenValueDoesNotExistException;
import com.davnicwil.gimmetoken.token.storage.ExpiringTokenValueStore;
import com.davnicwil.gimmetoken.token.storage.model.ExpiringTokenValue;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.inject.Singleton;

@Singleton
public class InMemoryExpiringTokenValueStore implements ExpiringTokenValueStore {

	private Map<String, ExpiringTokenValue> tokens;
	
	public InMemoryExpiringTokenValueStore() {
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
		return clone(Maps.filterValues(tokens, filter).keySet());
	}
	
	private Set<String> clone(Set<String> strings) {
		Set<String> clone = new HashSet<String>();
		for(String string: strings) {
			clone.add(string);
		}
		return clone;
	}
}