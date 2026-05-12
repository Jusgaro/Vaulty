package com.example.vaulty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordStorage {

    private static String getFileName(String username) {
        return "passwords_" + username + ".txt";
    }

    public static void save(List<PasswordEntry> passwords, String username, String masterPassword) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(username)));
            for (PasswordEntry entry : passwords) {
                String line = entry.getName() + "," + entry.getUsername() + "," + entry.getPassword();
                String encrypted = CryptoUtil.encrypt(line, masterPassword);
                writer.write(encrypted);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Chyba pri ukladaní: " + e.getMessage());
        }
    }

    public static List<PasswordEntry> load(String username, String masterPassword) {
        List<PasswordEntry> passwords = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getFileName(username)));
            String line;
            while ((line = reader.readLine()) != null) {
                String decrypted = CryptoUtil.decrypt(line, masterPassword);
                String[] parts = decrypted.split(",");
                if (parts.length == 3) {
                    passwords.add(new PasswordEntry(parts[0], parts[1], parts[2]));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Súbor nenájdený, začíname s prázdnym zoznamom.");
        }
        return passwords;
    }
}