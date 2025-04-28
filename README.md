<!-- @format -->

# 🚀 Git Account Manager CLI (Java)

## 📖 Project Overview

This project is a **Command Line Interface (CLI) Tool** that allows users to **manage their GitHub account** directly from the terminal — without needing GitHub Desktop or a browser.

With this tool, users can perform common GitHub operations easily and securely from their terminal.

---

## ✨ Features

| Feature                                       | Description                                                          |
| --------------------------------------------- | -------------------------------------------------------------------- |
| Login to GitHub (using Personal Access Token) | Authenticate securely using GitHub Tokens                            |
| View Profile Details                          | Fetch and display user profile info (name, bio, followers, repos...) |
| List Repositories                             | List all repositories in the user's GitHub account                   |
| Create a Repository                           | Create a new repository                                              |
| Delete a Repository                           | Delete an existing repository                                        |
| Update Profile Bio                            | Update the user's GitHub bio                                         |
| Logout                                        | Clear stored token/session                                           |
| Unit Testing                                  | Write tests for important services                                   |
| Clean Project Structure                       | Follow clean package and class design                                |
| JSON Parsing                                  | Parse GitHub API responses with minimal libraries                    |

---

## 🛠️ Technologies Used

- **Java 24**
- **HTTP Requests** (using `HttpURLConnection`)
- **JSON Parsing** (manual parsing or with minimal libraries like `org.json`)
- **JUnit 5** (for Unit Testing)
- **Maven** (for dependency management)
- **Good OOP Design** (Interfaces, Classes, Packages)

---

## 📁 Project Structure

| Package              | Description                               |
| -------------------- | ----------------------------------------- |
| `com.gitcli.cli`     | Main CLI classes                          |
| `com.gitcli.service` | GitHub API communication and logic        |
| `com.gitcli.model`   | Java models for GitHub data               |
| `com.gitcli.utils`   | Helper classes (HTTP helpers, parsers...) |
| `com.gitcli.test`    | Unit testing classes                      |

---

## 🔄 Application Flow

1. User opens the CLI application.
2. User logs in using a GitHub Personal Access Token (PAT).
3. CLI displays available actions (View profile, List repos, Create repo, Delete repo, Update bio, etc.)
4. User selects an action.
5. CLI sends requests to GitHub API and displays results.
6. User continues or logs out.

---

## 🖥️ Setup & Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/manage-git.git
   ```
