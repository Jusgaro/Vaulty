package com.example.vaulty;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {

    private static final String FILE_NAME = "users.txt";

    /**
     * Saves a new user by appending their username and hashed password to the storage file.
     */
    public static void saveUser(String username, String password) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            writer.write(username + "," + CryptoUtil.hashPassword(password));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving user: " + e.getMessage());
        }
    }

    /**
     * Checks if a username already exists in the local storage.
     */
    public static boolean userExists(String username) {
        return loadUsers().containsKey(username);
    }

    /**
     * Compares a provided password hash against the stored hash for a given user.
     */
    public static boolean verifyPassword(String username, String password) {
        Map<String, String> users = loadUsers();
        String storedHash = users.get(username);
        if (storedHash == null) return false;
        return storedHash.equals(CryptoUtil.hashPassword(password));
    }

    /**
     * Loads all users from the file into a HashMap for quick lookup.
     */
    private static Map<String, String> loadUsers() {
        Map<String, String> users = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
            reader.close();
        } catch (IOException e) {
            // Usually indicates the file hasn't been created yet
            System.out.println("Storage file not found.");
        }
        return users;
    }
}
