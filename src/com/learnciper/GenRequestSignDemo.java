package com.learnciper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
// import com.google.common.hash.Hashing;

public class GenRequestSignDemo {

    public static String genRequestSign(Map<String, Object> params, String psk) {
        TreeMap<String, Object> data = new TreeMap<>(params);
        data.remove("sign");

        StringBuilder buffer = new StringBuilder();
        String lastKey = data.lastEntry().getKey();
        for (String k : data.keySet()) {
            Object v = data.get(k);
            if (v != null) {
                buffer.append(k).append("=").append(v.toString());
            } else {
                buffer.append(k).append("=");
            }
            if (!lastKey.equals(k)) {
                buffer.append("&");
            }
        }

        buffer.append(psk);

        return buffer.toString();
    }

    public static String MD5(String input) throws NoSuchAlgorithmException {
        //获取MD5机密实例
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] byteArr = md5.digest(input.getBytes(StandardCharsets.UTF_8));
        return byte2hex(byteArr);
    }

    private static String byte2hex(byte[] b) {
        StringBuilder hs= new StringBuilder();
        String stmp="";
        for (byte value : b) {
            //为了保证二进制机器数不变，这里需要& 0XFF
            stmp = (Integer.toHexString(value & 0XFF));
            //如果只有一位，需要在前面补上0凑足两位
            if(stmp.length() == 1) {
                hs.append("0").append(stmp);
            }else {
                hs.append(stmp);
            }
        }
        return hs.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Map<String, Object> params = new HashMap<>();
        params.put("request_no", "230621175012994051431130267648serial");
        params.put("company_sign", "aaa");
        String psk = "37y4uxXZXeWtCDRq3z14dEhtCCawb2tM";

        String reqStr = GenRequestSignDemo.genRequestSign(params, psk);
        System.out.println(reqStr);
        System.out.println(MD5(reqStr));
    }
}
