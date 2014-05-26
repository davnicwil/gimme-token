package com.davnicwil.gimmetoken.token.storage;

import java.util.Set;

import com.davnicwil.gimmetoken.token.management.exception.TokenValueDoesNotExistException;
import com.davnicwil.gimmetoken.token.storage.model.ExpiringTokenValue;
import com.google.common.base.Predicate;

public interface ExpiringTokenValueStore {

	public abstract void put(String key, ExpiringTokenValue value);

	public abstract ExpiringTokenValue get(String key)
			throws TokenValueDoesNotExistException;

	public abstract Set<String> getKeys();

	public abstract void delete(String key);

	public abstract Set<String> getKeys(Predicate<ExpiringTokenValue> filter);

}