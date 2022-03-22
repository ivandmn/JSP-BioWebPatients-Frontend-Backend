/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.model;

/**
 *
 * @author alumne
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author roger
 */
public class EncryptAndDecryptSHA1 {
       
    public String getKey() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 100;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    /**
     * generate the key for encrypt en decrypt
     *
     * @param key
     * @return sha-1 key
     */
    public SecretKeySpec createKey(String key) {
        try {
            byte[] listByte = key.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            listByte = md.digest(listByte);
            listByte = Arrays.copyOf(listByte, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(listByte, "AES");
            return secretKeySpec;
        } catch (UnsupportedEncodingException ex) {
            return null;
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    /**
     * Method that encrypt using the AES algorithm.
     *
     * @param content // content that will encrypting
     * @param key // key for encrypt and decrypt
     * @return String encryption
     */
    public String encrypt(String content, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec sks = createKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, sks);

        byte[] bytes_content = content.getBytes("UTF-8");
        byte[] bytes_encryption = cipher.doFinal(bytes_content);

        String encryption = Base64.getEncoder().encodeToString(bytes_encryption);
        return encryption;
    }

    public String decrypt(String encrypt, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec sks = createKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sks);
        byte[] bytes_encrypt = Base64.getDecoder().decode(encrypt);
        byte[] bytes_decryption = cipher.doFinal(bytes_encrypt);
        String decryption = new String(bytes_decryption);
        return decryption;

    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        // Lets encrypt the text admin.
        EncryptAndDecryptSHA1 ead = new EncryptAndDecryptSHA1();
        
        String CLAVE_PRIVADA = "claveprivada";
        String encryptAdmin = ead.encrypt
            ("admin", CLAVE_PRIVADA);
        System.out.println("The encrypted Password for Admin is ");
        System.out.println(encryptAdmin);
        // Lets encrypt the text 123456.
    }
    
}