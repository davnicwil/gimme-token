package com.davnicwil.gimmetoken.api;

import com.davnicwil.gimmetoken.client.AdminEndpoint;
import com.davnicwil.gimmetoken.core.tokens.TokenRepo;
import com.davnicwil.time.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminResourceTest {

    private AdminEndpoint testObj;

    @Mock TokenRepo tokenRepo;
    @Mock Clock clock;

    @Before
    public void setup() {
        testObj = new AdminResource(tokenRepo, clock);
    }

    @Test
    public void itShouldNotSucceedWithWipeIfNotRunWithinLastMinute() throws Exception {
        Long commandTime = 0l;
        mockCurrentTime(120000l);
        mockCommandTimeWithinLastMinute(commandTime, false);

        Response response = testObj.wipe(commandTime);
        assertForbiddenResponse(response);
        verifyWipeNotCalled();
        System.out.println("Test successful: response message: " + response.getEntity());
    }

    @Test
    public void itShouldSuceedWithWipeIfRunWithinLastMinute() throws Exception {
        Long commandTime = 0l;
        mockCurrentTime(0l);
        mockCommandTimeWithinLastMinute(commandTime, true);

        assertSuccessfulResponse(testObj.wipe(commandTime));
        verifyWipeCalledExactlyOnce();
    }

    private void assertSuccessfulResponse(Response response) {
        assertEquals(200, response.getStatus());
    }

    private void assertForbiddenResponse(Response response) {
        assertEquals(403, response.getStatus());
    }

    private void verifyWipeCalledExactlyOnce() {
        verify(tokenRepo, times(1)).wipe();
    }

    private void verifyWipeNotCalled() {
        verify(tokenRepo, times(0)).wipe();
    }

    private void mockCommandTimeWithinLastMinute(Long commandTime, Boolean value) {
        when(clock.withinLastMinute(commandTime)).thenReturn(value);
    }

    private void mockCurrentTime(Long time) {
        when(clock.now()).thenReturn(time);
    }
}