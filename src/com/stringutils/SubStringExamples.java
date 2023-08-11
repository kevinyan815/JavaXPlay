package com.stringutils;

public class SubStringExamples {
    public static void main(String[] args) {
        String bankCardNumber = "1234567890123456";
        String lastFourDigits = getLastFourDigits(bankCardNumber);
        System.out.println("银行卡号的后四位：" + lastFourDigits);
    }


    public static String getLastFourDigits(String bankCardNumber) {
        if (bankCardNumber.length() >= 4) {
            return bankCardNumber.substring(bankCardNumber.length() - 4);
        } else {
            // 处理银行卡号长度不足四位的情况
            return bankCardNumber;
        }
    }
}
