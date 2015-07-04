package com.davnicwil.gimmetoken.core.tokens.impl;

import com.davnicwil.gimmetoken.core.tokens.TokenRepo;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TokenRepoImplUTest {

    private TokenRepo testObj;

    @Before
    public void setup() {
        testObj = new TokenRepoImpl();
    }

    @Test
    public void itShouldAddASingleTokenToTheRepoForAGivenId() throws Exception {
        itShouldAddASingleTokenToTheRepoForAGivenId(1l);
    }

    @Test
    public void itShouldAddASingleTokenToTheRepoForMultipleDifferentIds() throws Exception {
        LongStream.range(0, 100).forEach(id -> itShouldAddASingleTokenToTheRepoForAGivenId(id));
    }

    @Test
    public void itShouldAddMultipleTokensToTheRepoForAGivenId() throws Exception {
        itShouldAddMultipleTokensToTheRepoForAGivenId(1l);
    }

    @Test
    public void itShouldAddMultipleTokensToTheRepoForMultipleDifferentIds() throws Exception {
        LongStream.range(0, 100).forEach(id -> itShouldAddMultipleTokensToTheRepoForAGivenId(id));
    }

    private void itShouldAddASingleTokenToTheRepoForAGivenId(Long id) {
        String token = generateRandomToken();
        testObj.add(id, token);
        assertTrue(testObj.exists(id, token));
    }

    private void itShouldAddMultipleTokensToTheRepoForAGivenId(Long id){
        // create 100 tokens
        List<String> tokens = Stream.generate(() -> generateRandomToken()).limit(100).collect(Collectors.toList());

        // add them all for the id
        tokens.stream().forEach(token -> testObj.add(id, token));

        // test they are all there
        tokens.stream().forEach(token -> assertTrue(testObj.exists(id, token)));
    }

    @Test
    public void itShouldRemoveASingleTokenForAGivenId() {
        String token = generateRandomToken();

        testObj.add(1l, token);
        assertTrue(testObj.exists(1l, token));

        testObj.remove(1l, token);
        assertFalse(testObj.exists(1l, token));
    }

    @Test
    public void itShouldRemoveASingleTokenForMultipleDifferentIds() {
        // create 100 tokens
        List<String> tokens = Stream.generate(() -> generateRandomToken()).limit(100).collect(Collectors.toList());

        // add them for 100 different ids
        IntStream.range(0 , 100).forEach(i -> testObj.add(new Long(i), tokens.get(i)));

        // check they're all there
        IntStream.range(0 , 100).forEach(i -> assertTrue(testObj.exists(new Long(i), tokens.get(i))));

        // remove each one individually
        IntStream.range(0 , 100).forEach(i -> testObj.remove(new Long(i), tokens.get(i)));

        // check they're all removed
        IntStream.range(0 , 100).forEach(i -> assertFalse(testObj.exists(new Long(i), tokens.get(i))));
    }

    @Test
    public void itShouldRemoveAllTokensForAGivenId() throws Exception {
        // create 100 tokens
        List<String> tokens = Stream.generate(() -> generateRandomToken()).limit(100).collect(Collectors.toList());

        // add them all to one id
        IntStream.range(0 , 100).forEach(i -> testObj.add(1l, tokens.get(i)));

        // check they're all there
        IntStream.range(0 , 100).forEach(i -> assertTrue(testObj.exists(1l, tokens.get(i))));

        // remove them all
        testObj.wipe(1l);

        // check they're all removed
        IntStream.range(0 , 100).forEach(i -> assertFalse(testObj.exists(1l, tokens.get(i))));
    }

    @Test
    public void itShouldRemoveAllTokensForMultipleDifferentIds() throws Exception {

        // create 100 tokens
        List<List<String>> tokens = new ArrayList<>();

        IntStream.range(0, 100).forEach(i -> {
            // for each of 100 ids
            Long thisId = new Long(i);

            // create 100 tokens
            tokens.add(Stream.generate(() -> generateRandomToken()).limit(100).collect(Collectors.toList()));

            // add them all to this id
            IntStream.range(0 , 100).forEach(j -> testObj.add(thisId, tokens.get(i).get(j)));

            // check they're all there
            IntStream.range(0 , 100).forEach(j -> assertTrue(testObj.exists(thisId, tokens.get(i).get(j))));
        });


        IntStream.range(0, 100).forEach(i -> {
            // now they're all added to the repo, for each of the 100 ids
            Long thisId = new Long(i);

            // remove them all
            testObj.wipe(thisId);

            // check they're all removed
            IntStream.range(0, 100).forEach(j -> assertFalse(testObj.exists(thisId, tokens.get(i).get(j))));
        });
    }

    @Test
    public void itShouldReportTheCorrectTotalNumberOfTokensStored() throws Exception {
        // check count is 0 for empty repo
        assertEquals(new Integer(0), testObj.getNumberOfTokens());

        // create 100 tokens
        List<String> tokens = Stream.generate(() -> generateRandomToken()).limit(100).collect(Collectors.toList());

        // add them all for a single id
        tokens.stream().forEach(token -> testObj.add(1l, token));

        // total should be 100
        assertEquals(new Integer(100), testObj.getNumberOfTokens());

        // remove half the tokens
        IntStream.range(0, 50).forEach(i -> testObj.remove(1l, tokens.get(i)));

        // total should be 500
        assertEquals(new Integer(50), testObj.getNumberOfTokens());

        // add various new tokens to new ids and check count increments
        testObj.add(2l, generateRandomToken());
        testObj.add(2l, generateRandomToken());
        testObj.add(2l, generateRandomToken());
        assertEquals(new Integer(53), testObj.getNumberOfTokens());

        testObj.add(3l, generateRandomToken());
        testObj.add(3l, generateRandomToken());
        assertEquals(new Integer(55), testObj.getNumberOfTokens());

        testObj.add(8l, generateRandomToken());
        testObj.add(8l, generateRandomToken());
        testObj.add(8l, generateRandomToken());
        testObj.add(8l, generateRandomToken());
        testObj.add(8l, generateRandomToken());
        assertEquals(new Integer(60), testObj.getNumberOfTokens());

        // remove all from an id and check count reduces appropriately
        testObj.wipe(2l);
        assertEquals(new Integer(57), testObj.getNumberOfTokens());
    }

    @Test
    public void itShouldReportTheCorrectTotalNumberOfIdsWhichHaveTokensStored() throws Exception {
        // check count is 0 for empty repo
        assertEquals(new Integer(0), testObj.getNumberOfIds());

        // add various ids, check the count increments
        testObj.add(1l, generateRandomToken());
        testObj.add(2l, generateRandomToken());
        testObj.add(3l, generateRandomToken());
        testObj.add(4l, generateRandomToken());
        testObj.add(5l, generateRandomToken());
        assertEquals(new Integer(5), testObj.getNumberOfIds());

        // add new tokens to same ids, check count stays the same
        testObj.add(1l, generateRandomToken());
        testObj.add(2l, generateRandomToken());
        testObj.add(3l, generateRandomToken());
        testObj.add(4l, generateRandomToken());
        testObj.add(5l, generateRandomToken());
        assertEquals(new Integer(5), testObj.getNumberOfIds());

        // remove all for one id, check count decreases by 1
        testObj.wipe(1l);
        assertEquals(new Integer(4), testObj.getNumberOfIds());

        // add a single token, then remove it, for a new id. Check the count increases then decreases by 1
        testObj.add(6l, "testtoken");
        assertEquals(new Integer(5), testObj.getNumberOfIds());
        testObj.remove(6l, "testtoken");
        assertEquals(new Integer(4), testObj.getNumberOfIds());

        // now remove all for a non existant id, check count remains the same
        testObj.wipe(10l);
        assertEquals(new Integer(4), testObj.getNumberOfIds());

        // now remove a non-existant token, check count remains the same
        testObj.add(7l, "testtoken");
        assertEquals(new Integer(5), testObj.getNumberOfIds());
        testObj.remove(7l, "nonexistant");
        assertEquals(new Integer(5), testObj.getNumberOfIds());
    }

    @Test
    public void itShouldReportTheCorrectNumberOfTokensForAnIdWithTokens() throws Exception {
        // add some, check the count
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        assertEquals(new Integer(5), testObj.getNumberOfTokens(1l));

        // add more, check the count increases
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        assertEquals(new Integer(10), testObj.getNumberOfTokens(1l));

        // add some to a different Id, check the count for both
        testObj.add(2l, generateRandomToken());
        testObj.add(2l, generateRandomToken());
        testObj.add(2l, generateRandomToken());
        assertEquals(new Integer(10), testObj.getNumberOfTokens(1l));
        assertEquals(new Integer(3), testObj.getNumberOfTokens(2l));

        // add one then remove it, check the count adjusts accordingly
        testObj.add(2l, "testtoken");
        assertEquals(new Integer(4), testObj.getNumberOfTokens(2l));
        testObj.remove(2l, "testtoken");
        assertEquals(new Integer(3), testObj.getNumberOfTokens(2l));

        // remove a non-existant token, check the count doesn't change
        testObj.add(3l, "testtoken");
        assertEquals(new Integer(1), testObj.getNumberOfTokens(3l));
        testObj.remove(3l, "nonexistant");
        assertEquals(new Integer(1), testObj.getNumberOfTokens(3l));

        // remove all, check the count goes to 0
        testObj.wipe(1l);
        assertEquals(new Integer(0), testObj.getNumberOfTokens(1l));
    }

    @Test
    public void itShouldReportZeroTokensForANonExistantId() throws Exception {
        assertEquals(new Integer(0), testObj.getNumberOfTokens(1l));
    }

    @Test
    public void itShouldRemoveEverythingWhenTheCommandIsGiven() {
        // add some tokens for different ids, check the counts
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        testObj.add(1l, generateRandomToken());
        assertEquals(new Integer(1), testObj.getNumberOfIds());
        assertEquals(new Integer(5), testObj.getNumberOfTokens());
        assertEquals(new Integer(5), testObj.getNumberOfTokens(1l));

        testObj.add(2l, generateRandomToken());
        testObj.add(2l, generateRandomToken());
        testObj.add(2l, generateRandomToken());
        assertEquals(new Integer(2), testObj.getNumberOfIds());
        assertEquals(new Integer(8), testObj.getNumberOfTokens());
        assertEquals(new Integer(3), testObj.getNumberOfTokens(2l));

        testObj.add(3l, generateRandomToken());
        testObj.add(3l, generateRandomToken());
        testObj.add(3l, generateRandomToken());
        testObj.add(3l, generateRandomToken());
        testObj.add(3l, generateRandomToken());
        testObj.add(3l, generateRandomToken());
        assertEquals(new Integer(3), testObj.getNumberOfIds());
        assertEquals(new Integer(14), testObj.getNumberOfTokens());
        assertEquals(new Integer(6), testObj.getNumberOfTokens(3l));

        // delete everything! nothing should remain
        testObj.wipe();
        assertEquals(new Integer(0), testObj.getNumberOfIds());
        assertEquals(new Integer(0), testObj.getNumberOfTokens());
        assertEquals(new Integer(0), testObj.getNumberOfTokens(1l));
        assertEquals(new Integer(0), testObj.getNumberOfTokens(2l));
        assertEquals(new Integer(0), testObj.getNumberOfTokens(3l));
    }

    private String generateRandomToken() {
        return new BigInteger(130, new Random()).toString();
    }
}