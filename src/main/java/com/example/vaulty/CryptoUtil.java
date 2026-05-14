package com.example.vaulty;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CryptoUtil {

    private static final String SALT = "VaultySalt123";

    /**
     * Hashes the master password using SHA-256 for secure storage verification.
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(SALT.getBytes());
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error during hashing: " + e.getMessage());
        }
    }

    /**
     * Encrypts plain text data using the provided password and AES algorithm.
     */
    public static String encrypt(String data, String password) {
        try {
            SecretKeySpec key = generateKey(password);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error during encryption: " + e.getMessage());
        }
    }

    /**
     * Decrypts AES-encrypted data back into plain text.
     */
    public static String decrypt(String encryptedData, String password) {
        try {
            SecretKeySpec key = generateKey(password);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error during decryption: " + e.getMessage());
        }
    }

    /**
     * Generates a 256-bit AES key from the user's password.
     */
    private static SecretKeySpec generateKey(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] key = md.digest(password.getBytes());
        return new SecretKeySpec(key, "AES");
    }
}
