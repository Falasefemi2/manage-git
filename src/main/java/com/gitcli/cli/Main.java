package com.gitcli.cli;

import java.util.Scanner;

import com.gitcli.service.GitHubService;

public class Main {

    private static GitHubService gitHubService = new GitHubService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("==== Welcome to Git Account Manager ====");

        while (!exit) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Login");
            System.out.println("2. View Profile");
            System.out.println("3. List Repositories");
            System.out.println("4. Create Repository");
            System.out.println("5. Delete Repository");
            System.out.println("6. Update Profile Bio");
            System.out.println("7. Logout");
            System.out.println("0. Exit");

            System.out.println("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Logging in...");
                    if (gitHubService.isLoggedIn()) {
                        System.out.println("Already logged in.");
                    } else {
                        System.out.print("Enter your GitHub Personal Access Token: ");
                        String tokenInput = scanner.nextLine();
                        boolean success = gitHubService.login(tokenInput);

                        if (success) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Login failed. Please check your token.");
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Viewing profile...");
                }
                case 3 -> {
                    System.out.println("Listing repositories...");
                }
                case 4 -> {
                    System.out.println("Creating repository...");
                }
                case 5 -> {
                    System.out.println("Deleting repository...");
                }
                case 6 -> {
                    System.out.println("Updating bio...");
                }
                case 7 -> {
                    System.out.println("Logging out...");
                }
                case 0 -> {
                    exit = true;
                    System.out.println("Exiting. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
