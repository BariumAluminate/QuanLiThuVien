package com.example.springapi.api.reader.basicclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class CSRFTokenUtil {

    @Autowired
    private SecretKeyProvider secretKeyProvider;

    private static final String ALGO = "AES";

    public String encrypt(String data) throws Exception {
        String secretKey = secretKeyProvider.getSecretKey();  // ✅ Lấy tại đây
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), ALGO);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encryptedData) throws Exception {
        String secretKey = secretKeyProvider.getSecretKey();  // ✅ Lấy tại đây
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), ALGO);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(original);
    }
}
