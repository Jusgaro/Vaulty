package com.example.vaulty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordStorage {

    /**
     * Generates a unique filename for each user's vault.
     */
    private static String getFileName(String username) {
        return "passwords_" + username + ".txt";
    }

    /**
     * Encrypts and saves the list of passwords to a user-specific file.
     */
    public static void save(List<PasswordEntry> passwords, String username, String masterPassword) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(username)));
            for (PasswordEntry entry : passwords) {
                // Combine fields into a CSV format before encryption
                String line = entry.getName() + "," + entry.getUsername() + "," + entry.getPassword();
                String encrypted = CryptoUtil.encrypt(line, masterPassword);
                writer.write(encrypted);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving: " + e.getMessage());
        }
    }

    /**
     * Loads and decrypts passwords from the user's storage file.
     */
    public static List<PasswordEntry> load(String username, String masterPassword) {
        List<PasswordEntry> passwords = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getFileName(username)));
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String decrypted = CryptoUtil.decrypt(line, masterPassword);
                    String[] parts = decrypted.split(",");
                    if (parts.length == 3) {
                        passwords.add(new PasswordEntry(parts[0], parts[1], parts[2]));
                    }
                } catch (RuntimeException e) {
                    // This handles cases where the master password might be wrong
                    System.out.println("Failed to decrypt a line: " + e.getMessage());
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found, starting with an empty list.");
        }
        return passwords;
    }
}
