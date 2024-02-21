package com.learnfile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownloadWebImageToLocalApp {
    public static void main(String[] args) {
        String remoteImgUrl =  "https://dalleprodsec.blob.core.windows.net/private/images/25882aa5-cf05-4d70-bbec-fa0a116d48f3/generated_00.png?se=2024-02-22T09%3A41%3A30Z&sig=GR9fSBlzZRvv8WvHWBI5UXOVjjl4t5LlB35G3qPeZ44%3D&ske=2024-02-28T07%3A25%3A17Z&skoid=e52d5ed7-0657-4f62-bc12-7e5dbb260a96&sks=b&skt=2024-02-21T07%3A25%3A17Z&sktid=33e01921-4d64-4f8c-a055-5bdaffd5e33d&skv=2020-10-02&sp=r&spr=https&sr=b&sv=2020-10-02";
        try {
            URL url = new URL(remoteImgUrl);
            URLConnection connection = url.openConnection();
            String fileName = "gptImgtest.png";
            try (
                    InputStream inputStream = connection.getInputStream();
                    BufferedOutputStream fos = new BufferedOutputStream(Files.newOutputStream(Paths.get("." + File.separator + fileName)));
            ) {

                int len = 0;
                byte[] readChunk = new byte[1024];
                while ((len = inputStream.read(readChunk)) != -1) {
                    fos.write(readChunk, 0, len);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
