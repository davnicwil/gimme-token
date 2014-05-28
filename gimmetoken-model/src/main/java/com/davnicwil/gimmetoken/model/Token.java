package com.davnicwil.gimmetoken.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {

	private String key;
	private String value;

	@JsonCreator
	public Token(@JsonProperty("key") String key, @JsonProperty("value") String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {return key;}
	
	public String getValue() {return value;}
	
	public boolean equals(Object other) {
		if(other instanceof Token) {
			Token token = (Token) other;
			return token.getKey().equals(key) &&
					token.getValue().equals(value);
		}
		return false;
	}
}
