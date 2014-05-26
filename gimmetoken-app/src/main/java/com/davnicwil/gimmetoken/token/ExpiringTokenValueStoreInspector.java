package com.davnicwil.gimmetoken.token;

import java.util.Set;

import com.davnicwil.gimmetoken.model.ExpiringTokenValueStore;

public interface ExpiringTokenValueStoreInspector {

	Set<String> getAllExpiredTokenKeys(ExpiringTokenValueStore expiringTokenValueStore);
	Set<String> getAllActiveTokenKeys(ExpiringTokenValueStore expiringTokenValueStore);
}
