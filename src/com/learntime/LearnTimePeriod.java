package com.learntime;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class LearnTimePeriod {
    public static void main(String[] args) {
        // 使用Period.between 计算间隔天数
        // 2022-10-10 与 2022-10-11 比较返回 1
        // 2022-10-11 与 2022-10-10 比较返回 -1
        // 2022-10-11 与 2022-10-11 比较返回 0
        // 2023-10-10 与 2022-10-11 比较返回 0
        // 所以Period.between 返回的不是两个日期间的绝对间隔天数
        Period period = Period.between(LocalDate.of(2023, 10, 10), LocalDate.of(2022, 10, 10));
        System.out.println("Period.between计算间隔天数: " + period.getDays());
        // 返回两个日期的绝对间隔天数，使用ChronoUnit.DAYS.between
        // 2023-10-10 与 2022-10-11 比较返回 -365
        // 2022-10-11 与 2022-10-11 比较返回 0
        // 2022-10-11 与 2022-10-10 比较返回 -1
        // 2022-10-11 与 2022-10-12 比较返回 1
        Long days = ChronoUnit.DAYS.between(LocalDate.of(2022, 10, 9), LocalDate.of(2022, 10, 10));
        System.out.println("间隔天数: " + days);
    }
}
