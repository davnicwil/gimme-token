package com.davnicwil.gimmetoken.core.tokens;

import java.util.Optional;

public interface TokenRepo {

    void add(Long id, String token);
    Boolean exists(Long id, String tokenValue);
    void remove(Long id, String tokenValue);
    void removeAll(Long id);
    Integer getNumberOfIds();
    Integer getNumberOfTokens();
    Integer getNumberOfTokens(Long id);
}
