package com.example.vaulty;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {

    private static final String FILE_NAME = "users.txt";

    public static void saveUser(String username, String password) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            writer.write(username + "," + CryptoUtil.hashPassword(password));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Chyba pri ukladaní používateľa: " + e.getMessage());
        }
    }

    public static boolean userExists(String username) {
        return loadUsers().containsKey(username);
    }

    public static boolean verifyPassword(String username, String password) {
        Map<String, String> users = loadUsers();
        String storedHash = users.get(username);
        if (storedHash == null) return false;
        return storedHash.equals(CryptoUtil.hashPassword(password));
    }

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
            System.out.println("Súbor nenájdený.");
        }
        return users;
    }
}