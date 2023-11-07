package com.learntime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class GetRandomDateTimeOfDate {
    public static void main(String[] args) {
        // 创建一个 LocalDate 对象表示日期
        LocalDate date = LocalDate.now();

        // 生成随机秒数
        int randomSeconds = generateRandomSeconds();

        // 使用 LocalTime 对象表示随机秒数的时间部分
        LocalTime time = LocalTime.ofSecondOfDay(randomSeconds);

        // 将 LocalDate 和 LocalTime 组合成 LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        System.out.println("Original LocalDate: " + date);
        System.out.println("Random Seconds: " + randomSeconds + " seconds");
        System.out.println("LocalDateTime with Random Seconds: " + dateTime);
    }

    // 生成随机秒数的示例方法
    private static int generateRandomSeconds() {
        Random random = new Random();
        int randomSeconds = random.nextInt(86400); // 一天的秒数
        return randomSeconds;
    }
}
