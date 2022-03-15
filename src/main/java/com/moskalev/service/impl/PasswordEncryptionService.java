package com.moskalev.service.impl;

import com.moskalev.exception.CustomException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 10.03.22
 * Class for Encryption password
 */
@Service
public class PasswordEncryptionService {

    /**
     * @param hashMe -password that person entered in service
     * @param salt   -key for coding password
     * @return hash value
     * newString-Here append symbol from bit shift
     */
    public String hashToHex(String hashMe, Optional<String> salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bytes = hash(hashMe, salt);

        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            newString.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return newString.toString().toUpperCase();
    }

    /**
     * @param hashMe -password that person entered in service
     * @param salt   -key for coding password
     * @return mass of byte for next coding
     * The Java MessageDigest class represents a cryptographic hash function that can compute a message digest from binary data.
     */
    public byte[] hash(String hashMe, Optional<String> salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

        messageDigest.update(hashMe.getBytes("UTF-8"));
        salt.ifPresent(s -> {
            try {
                messageDigest.update(s.getBytes("UTF-8"));
            } catch (Exception exception) {
                throw new CustomException(exception.getMessage());
            }
        });
        return messageDigest.digest();
    }
}

