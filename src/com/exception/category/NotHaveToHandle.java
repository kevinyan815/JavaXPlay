package com.exception.category;

import java.util.Locale;

public class NotHaveToHandle {
    // 继承自RuntimeException的异常不强制要求在编写程序时处理
    public static void main(String[] args) throws NullPointerException {
        String str = null;
        str.toLowerCase();
    }
}
