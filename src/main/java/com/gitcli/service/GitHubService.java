package com.gitcli.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

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

}
