package com.nce.project.gojiiv1.security;

import java.security.MessageDigest;
import java.util.Arrays;
import android.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TripleKeyDES {

    private static final Logger logger = Logger.getLogger(TripleKeyDES.class.getName());

    private String key;

    public TripleKeyDES(String myEncryptionKey) {
        key = myEncryptionKey;
    }

    /**
     * Method to encrypt the string
     */
    public String harden(String unencryptedString) {
        if (unencryptedString == null) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            for (int j = 0, k = 16; j < 8; ) {
                keyBytes[k++] = keyBytes[j++];
            }

            SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] plainTextBytes = unencryptedString.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            String base64EncryptedString = Base64.encodeToString(buf,Base64.DEFAULT);

            return base64EncryptedString;
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to decrypt an encrypted string
     *
     * @param encryptedString
     */
    public String soften(String encryptedString) {
        if (encryptedString == null) {
            return "";
        }
        try {

            byte [] message = Base64.decode(encryptedString.getBytes("utf-8"),Base64.DEFAULT);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            for (int j = 0, k = 16; j < 8; ) {
                keyBytes[k++] = keyBytes[j++];
            }

            SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            decipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] plainText = decipher.doFinal(message);

            return new String(plainText, "UTF-8");
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
            throw new RuntimeException(e);
        }
    }
}


