package com.gitcli.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class GitHubService {

    private String token;

    public boolean login(String token) {
        try {
            URL url = new URL("https://api.github.com/user");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Authorization", "token " + token);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = conn.getResponseCode();

            System.out.println("Response Code: " + responseCode);

            if (responseCode == 200) {
                this.token = token;
                return true;
            } else {
                Scanner errorScanner = new Scanner(conn.getErrorStream());
                while (errorScanner.hasNextLine()) {
                    System.out.println(errorScanner.nextLine());
                }
                errorScanner.close();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLoggedIn() {
        return token != null;
    }

    public void logout() {
        token = null;
    }

    public String getToken() {
        return token;
    }

    public void getProfileInfo() {
        if (token == null) {
            System.out.println("You must login first");
            return;
        }

        try {
            URL url = new URL("https://api.github.com/user");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Authorization", "token " + token);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();

                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                String jsonResponse = response.toString();
                JSONObject json = new JSONObject(jsonResponse);
                String username = json.optString("login");
                String name = json.optString("name");
                String email = json.optString("email");
                String bio = json.optString("bio");
                int publicRepos = json.optInt("public_repos");
                int followers = json.optInt("followers");
                int following = json.optInt("following");

                System.out.println("👤 Username: " + username);
                System.out.println("📛 Name: " + name);
                System.out.println("📧 Email: " + email);
                System.out.println("🧬 Bio: " + bio);
                System.out.println("📦 Public Repos: " + publicRepos);
                System.out.println("👥 Followers: " + followers);
                System.out.println("➡️ Following: " + following);

            } else {
                System.out.println("Failed to fetch profile info. HTTP Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int startIndex = json.indexOf(searchKey);

        if (startIndex == -1) {
            return "N/A";
        }

        startIndex += searchKey.length();

        // Handle string values
        if (json.charAt(startIndex) == '\"') {
            startIndex++;
            int endIndex = json.indexOf("\"", startIndex);
            return json.substring(startIndex, endIndex);
        } else {
            // Handle numeric or null values
            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = json.indexOf("}", startIndex);
            }
            return json.substring(startIndex, endIndex).trim();
        }
    }

    public void listRepositories() {
        if (token == null) {
            System.out.println("You must login first!");
            return;
        }

        try {
            URL url = new URL("https://api.github.com/user/repos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Authorization", "token " + token);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();

                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                String jsonResponse = response.toString();

                JSONArray repos = new JSONArray(jsonResponse);

                for (int i = 0; i < repos.length(); i++) {
                    JSONObject repo = repos.getJSONObject(i);

                    String name = repo.optString("name");
                    String description = repo.optString("description");
                    int stars = repo.optInt("stargazers_count");
                    int forks = repo.optInt("forks_count");
                    boolean isPrivate = repo.optBoolean("private");

                    System.out.println("📁 Repo Name: " + name);
                    System.out.println("📝 Description: " + description);
                    System.out.println("⭐ Stars: " + stars);
                    System.out.println("🍴 Forks: " + forks);
                    System.out.println("🔒 Private: " + isPrivate);
                    System.out.println("-----------------------------");
                }

            } else {
                System.out.println("Failed to fetch repositories. HTTP Code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createRepository(String repoName, String description, boolean isPrivate) {
        if (token == null) {
            System.out.println("You must login first!");
            return;
        }

        try {
            URL url = new URL("https://api.github.com/user/repos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "token " + token);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setDoOutput(true);

            String jsonInputString = String.format(
                    "{\"name\":\"%s\", \"description\":\"%s\", \"private\":%b}",
                    repoName, description, isPrivate);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == 201) {
                System.out.println("🎉 Repository created successfully!");
            } else {
                try (Scanner scanner = new Scanner(conn.getErrorStream())) {
                    while (scanner.hasNextLine()) {
                        System.out.println(scanner.nextLine());
                    }
                }
                System.out.println("❌ Failed to create repository. HTTP Code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRepository(String username, String repoName) {
        if (token == null) {
            System.out.println("You must login first!");
            return;
        }

        try {
            URL url = new URL("https://api.github.com/repos/" + username + "/" + repoName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            conn.setRequestProperty("Authorization", "token " + token);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");

            int responseCode = conn.getResponseCode();

            if (responseCode == 204) { // 204 = No Content = Success
                System.out.println("🗑 Repository deleted successfully!");
            } else {
                Scanner scanner = new Scanner(conn.getErrorStream());
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                scanner.close();
                System.out.println("❌ Failed to delete repository. HTTP Code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBio(String newBio) {
        if (token == null) {
            System.out.println("You must login first!");
            return;
        }

        try {
            URL url = new URL("https://api.github.com/user");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "token " + token);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            conn.setDoOutput(true);

            String jsonInputString = "{\"bio\":\"" + newBio + "\"}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                System.out.println("✅ Bio updated successfully!");
            } else {
                Scanner scanner = new Scanner(conn.getErrorStream());
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                scanner.close();
                System.out.println("❌ Failed to update bio. HTTP Code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
