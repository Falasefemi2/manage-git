<!-- @format -->

# Project Git Account Manager (CLI) in Java

## What is this project?

- I built a Command Line Interface (CLI) Tool that allows users to manage their GitHub account without needing GitHub Desktop or browser.

- The Cli will help users:

| Feature                                       | Description                                                          |
| --------------------------------------------- | -------------------------------------------------------------------- |
| Login to GitHub (using Personal Access Token) | Authenticate securely                                                |
| View Profile Details                          | Fetch and display user profile info (name, bio, followers, repos...) |
| List Repositories                             | List all repositories in the user's account                          |
| Delete a Repository                           | Delete a repo directly                                               |
| Update Profile Bio                            | Delete a repo directly                                               |
| Update Profile Bio                            | Update user's GitHub bio                                             |
| Logout                                        | Clear token/session                                                  |
| Unit Testing                                  | Write tests for important services                                   |
| Clean Project Structure                       | Follow good package and class design                                 |
| JSON Parsing                                  | Parse GitHub API responses (with minimal libraries)                  |

## Technologies I Will Use

1. Java 24
2. HTTP Requests (using HttpURLConnection)
3. JSON Parsing (using minimal libraries like org.json or manual parsing)
4. JUnit 5 for Unit Testing
5. Maven (for dependency management)
6. Good OOP Design (Interfaces, Classes, Packages)

## Basic Structure / Packages

| Package            | Description                                |
| ------------------ | ------------------------------------------ |
| com.gitcli.cli     | For main CLI classes                       |
| com.gitcli.service | For all GitHub API logic                   |
| com.gitcli.model   | For creating Java objects from GitHub data |
| com.gitcli.utils   | For helpers (HTTP helpers, parsers...)     |
| com.gitcli.test    | For unit testing                           |

## Flow of the Application

1. User opens CLI
2. User Logs in (provides GitHub Personal Access Token)
3. CLI shows options (View profile, List repos, Create repo, etc.)
4. User selects an action
5. CLI interacts with GitHub API
6. Response shown to user
7. Repeat until user logs out
