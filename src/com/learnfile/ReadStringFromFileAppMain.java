package com.learnfile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public class ReadStringFromFileAppMain {
    private static final String SOURCE_FILE_NAME = "file1.txt";

    public static void main(String[] args) {

        File sourceFile = new File("." + File.separator + SOURCE_FILE_NAME);

        classicWay(sourceFile);

        coolerWay(sourceFile);
    }
    private static void classicWay(File sourceFile) {
        System.out.println("-------------经典的处理方式------------");
        try (
            // TODO 建立从文件到程序的数据输入(input)流
            FileInputStream fis = new FileInputStream(sourceFile);
            // TODO 用 InputStreamReader 装饰 byte流，并指定字符编码，让它能够将读出的byte转为字符
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            // TODO 增加缓冲功能，输入输出效率更高，并且可以一次读一行
            BufferedReader reader = new BufferedReader(isr)
        ) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line.trim().toUpperCase());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void coolerWay(File sourceFile) {
        System.out.println("-------666---------");

        try (
            FileInputStream fis = new FileInputStream(sourceFile);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr)
        ) {
            reader.lines().map(String::trim).map(String::toUpperCase).forEach(System.out::println);
        } catch (Exception ex) {

        }
    }
}
