package com.davnicwil.gimmetoken.core.tokens.impl;

import com.davnicwil.gimmetoken.core.tokens.TokenRepo;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;


public class TokenRepoImpl implements TokenRepo {

    private SetMultimap<Long, String> store;

    public TokenRepoImpl() {
        store = Multimaps.synchronizedSetMultimap(HashMultimap.create());
    }

    public Boolean exists(Long id, String tokenValue) {
        return store.get(id)
                    .stream()
                    .filter(tokenValue::equals)
                    .findAny()
                    .isPresent();
    }

    public void add(Long id, String token) { store.put(id, token); }
    public void remove(Long id, String token) { store.remove(id, token); }
    public void removeAll(Long id) { store.removeAll(id); }
    public Integer getNumberOfIds() { return store.keySet().size(); }
    public Integer getNumberOfTokens() { return store.values().size(); }
    public Integer getNumberOfTokens(Long id) { return store.get(id).size(); }
}
