package com.learnfile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.SortedMap;

public class WriteToFilesAppMain {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        File targetFile = createFile();

        writeToFile(targetFile);

        System.out.println("程序执行结束");

    }

    private static void writeToFile(File targetFile) throws IOException {
        // TODO 使用 try with resource 自动回收打开的资源
        try (
                // TODO 创建一个outputstream 建立一个从程序到文件的byte数据传输流
                FileOutputStream fos = new FileOutputStream(targetFile);
                // TODO 创建一个可以使用outputstream的Writer，并制定字符集，这样程序就能一个一个字符地写入
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                // TODO 使用PrintWriter, 可以方便的写入一行字符
                PrintWriter pw = new PrintWriter(osw);
         ) {
            System.out.println("输入的内容会实时写入文件，如果输入空行则结束");
            while (true) {
                String lineToWrite = in.nextLine().trim();
                System.out.println("输入内容为：" + lineToWrite);
                if (lineToWrite.trim().isBlank()) {
                    System.out.println("输入结束");
                    break;
                } else {
                    pw.println(lineToWrite);
                    // 真正用的时候不要写一行就flush() 这里只是演示
                    pw.flush();
                }
            }
            // 平时用的时候放在外面 flush
            // pw.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static File createFile() throws IOException {
        System.out.println("请输入文件名：");
        String fileName = in.nextLine().trim();
        File f = new File("." + File.separator + fileName +".txt");
        if (f.isFile()) {
            System.out.println("目标文件存在，删除：" + f.delete());
        }
        System.out.println(f.createNewFile());
        return f;
    }
}
