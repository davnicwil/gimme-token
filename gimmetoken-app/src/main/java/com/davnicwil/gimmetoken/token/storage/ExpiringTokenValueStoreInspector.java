package com.davnicwil.gimmetoken.token.storage;

import java.util.Set;


public interface ExpiringTokenValueStoreInspector {

	Set<String> getAllExpiredTokenKeys(ExpiringTokenValueStore expiringTokenValueStore);
	Set<String> getAllActiveTokenKeys(ExpiringTokenValueStore expiringTokenValueStore);
}
