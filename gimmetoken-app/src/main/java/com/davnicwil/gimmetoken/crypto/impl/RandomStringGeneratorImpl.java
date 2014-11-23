package com.davnicwil.gimmetoken.crypto.impl;

import java.security.SecureRandom;
import java.util.Random;

import com.davnicwil.gimmetoken.crypto.RandomStringGenerator;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class RandomStringGeneratorImpl implements RandomStringGenerator {

	private static final char[] ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();
	
	private Integer length;
	private SecureRandom secureRandom;
	
	@Inject
	public RandomStringGeneratorImpl(@Named("token-length") Integer length) {
		this.length = length;
		secureRandom = new SecureRandom();
	}
	
	public String generate() {
		return generateSecureRandomString(length);
	}
	
	private String generateSecureRandomString(int length) {
		Random random = new Random();
	    char[] accessTokenChars = new char[length];
	    for (int i = 0; i < length; i++) {
	    	if ((i % 10) == 0) {
	    		random.setSeed(secureRandom.nextLong());
	    	}
	    	accessTokenChars[i] = ALLOWED_CHARS[random.nextInt(ALLOWED_CHARS.length)];
	    }
	    return new String(accessTokenChars);
	}
}