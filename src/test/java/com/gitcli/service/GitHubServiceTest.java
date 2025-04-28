package com.gitcli.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class GitHubServiceTest {

    @Test
    void testCreateRepository() {
        GitHubService service = new GitHubService();
        service.createRepository("Test Repo", "Testing", false);
        assertNull(service.getToken(), "Token should be null when not logged in");

    }

    @Test
    void testDeleteRepository() {
        GitHubService service = new GitHubService();
        service.deleteRepository("Testuser", "Test Repo");
        assertNull(service.getToken(), "Token should be null when deleteting repo");

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
    void testLogin() {
        GitHubService service = new GitHubService();
        service.login("fake_token");

        assertEquals("fake_token_success", service.getToken());

    }

    @Test
    void testLogout() {
        GitHubService service = new GitHubService();
        service.login("fake_token");
        service.logout();

        assertNull(service.getToken());
    }

    @Test
    void testUpdateBio() {
        GitHubService service = new GitHubService();
        service.updateBio("New Bio");
        assertNull(service.getToken(), "Token should be null when updating bio");

    }
}
