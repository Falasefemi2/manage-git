package com.gitcli.service;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GitHubServiceTest {

    private GitHubService gitHubService;

    @Mock
    private HttpURLConnection mockConnection;

    @BeforeEach
    public void setup() {
        gitHubService = new GitHubService();
    }

    @Test
    void testCreateRepository() {

    }

    @Test
    void testDeleteRepository() {

    }

    @Test
    void testGetProfileInfo() {

    }

    @Test
    void testGetToken() {

    }

    @Test
    void testIsLoggedIn() {

    }

    @Test
    void testListRepositories() {

    }

    @Test
    void testLogin() throws Exception {
        // Arrange
        String token = "validToken";

        // Use PowerMockito to mock URL class
        PowerMockito.mockStatic(URL.class);
        URL mockUrl = mock(URL.class);
        when(URL.class.getDeclaredConstructor(String.class).newInstance(anyString())).thenReturn(mockUrl);
        when(mockUrl.openConnection()).thenReturn(mockConnection);

        // Simulate a successful response (HTTP 200)
        when(mockConnection.getResponseCode()).thenReturn(200);

        // Act
        boolean result = gitHubService.login(token); // Call the method you want to test

        // Assert
        assertTrue(result, "Login should succeed with a valid token.");
        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).setRequestProperty("Authorization", "token " + token);

    }

    @Test
    void testLogout() {
    }

    @Test
    void testUpdateBio() {

    }
}
