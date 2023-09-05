package com.learnciper;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/**
 * ChatGPT 生成的AES加密Demo,
 * 生成后有手动调整, 去掉了do.Final中对明文的填充逻辑
 */
public class ChatGptGenEncryptDemo {
    // 生成免登陆的用户信息加密串(用固定的时间生成--测试验证结果用)
    public static String genUserLoginedSecretTest(long userId) throws Exception {
        String encryptKey = "9wjyxqDPNyrd8QrhxTycRMU3dFN2lCm6";
        LocalDateTime locaTime = LocalDateTime.parse("2023-09-05 01:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long timestamp = locaTime.toInstant(ZoneOffset.ofHours(0)).toEpochMilli();
        String plain = String.format("{\"%d\": %d}", userId, timestamp);
        System.out.println(plain);
        String secret = aesEncrypt(encryptKey, plain);
        return secret;
    }

    // 生成免登陆的用户信息加密串
    public static String genUserLoginedSecret(long userId) throws Exception {
        String encryptKey = "9wjyxqDPNyrd8QrhxTycRMU3dFN2lCm6";
        LocalDateTime locaTime = LocalDateTime.now().plusDays(1);
        long timestamp = locaTime.toInstant(ZoneOffset.ofHours(0)).toEpochMilli();

        String plain = String.format("{\"%d\": %d}", userId, timestamp);
        String secret = aesEncrypt(encryptKey, plain);
        return secret;
    }

    // AES 加密
    private static String aesEncrypt(String key, String plain) throws Exception {
        byte[] keyBytes = GetKeyBytes(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] ivBytes = keyBytes;
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        byte[] encryptedBytes = cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8));
        String encryptedString = Base64.getUrlEncoder().withoutPadding().encodeToString(encryptedBytes);
        return encryptedString;
    }

    private static byte[] GetKeyBytes(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        switch (keyBytes.length) {
            case 16:
                return keyBytes;
            case 0:
                return new byte[16];
            default:
                byte[] newKeyBytes = new byte[16];
                System.arraycopy(keyBytes, 0, newKeyBytes, 0, Math.min(keyBytes.length, newKeyBytes.length));
                return newKeyBytes;
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println(ChatGptGenEncryptDemo.genUserLoginedSecretTest(111111));
    }
}