package com.davnicwil.gimmetoken.time.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import com.davnicwil.gimmetoken.time.CurrentTimeProvider;

@RunWith(MockitoJUnitRunner.class)
public class ClockImplUTest {

	private static final Long NOW = 0l;
	
	@Mock private CurrentTimeProvider currentTimeProvider;
	
	private ClockImpl testObj;
	
	@Before
	public void setup() {
		testObj = new ClockImpl(currentTimeProvider);
		when(currentTimeProvider.getCurrentTime()).thenReturn(NOW);
	}
	
	@Test
	public void givenANumberOfMillisItShouldReturnTheTimestampThatNumberOfMillisInTheFuture() {
		assertEquals(new Long(1000l), testObj.getTimeXMillisInFuture(1000l));
		assertEquals(new Long(2000l), testObj.getTimeXMillisInFuture(2000l));
		assertEquals(new Long(10000l), testObj.getTimeXMillisInFuture(10000l));
	}
}
