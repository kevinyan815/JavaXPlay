package com.practice;

import com.practice.ai.AI;

import java.util.Scanner;

public class AiTalkAppMain {
    public static void main(String[] args) {
        AI ai = new AI();
        Scanner in = new Scanner(System.in);
        System.out.println("请输入你的问题：");
        while (true) {
            String input = in.next();
            if ("exit".equals(input)) {
                System.out.println("再见！");
                break;
            }
            String answer = ai.answer(input);
            System.out.println(answer);
        }
    }
}
