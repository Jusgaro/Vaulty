# Vaulty

A desktop password manager built with Java and JavaFX. Passwords are encrypted locally — nothing leaves your machine.

---

## Features

- User registration and login
- AES-encrypted password storage
- Each user has their own encrypted file
- Add and delete saved passwords
- Clean, minimal UI

## Tech Stack

- Java 17+
- JavaFX
- AES encryption (javax.crypto)
- SHA-256 password hashing

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Run

```bash
git clone https://github.com/jusgaro/Vaulty.git
cd Vaulty
mvn javafx:run
```

## How It Works

- Passwords are encrypted using AES before being written to disk
- The encryption key is derived from the user's master password via SHA-256
- Each user's passwords are stored in a separate file: `passwords_<username>.txt`
- Master passwords are hashed with SHA-256 + salt and stored in `users.txt`

## Project Structure

```
src/main/java/com/example/vaulty/
├── App.java                 # JavaFX entry point
├── Launcher.java            # Main launcher
├── CryptoUtil.java          # AES encryption & SHA-256 hashing
├── UserStorage.java         # User registration & login
├── PasswordStorage.java     # Save & load encrypted passwords
├── PasswordEntry.java       # Password data model
├── LoginController.java     # Login screen logic
├── RegisterController.java  # Registration screen logic
├── MainController.java      # Main app screen logic
└── VaultyIcons.java         # Custom drawn icons
```

## Notes

This project was built as a personal learning exercise. It is not intended for production use — the encryption implementation has not been audited.

---

Made by [Jusgaro](https://github.com/jusgaro)
