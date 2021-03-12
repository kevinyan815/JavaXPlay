package com.learnsocket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.learnsocket.SimpleServer.SERVER_PORT;

public class SimpleClient {
    public static void main(String[] args) {
        commWithServer();
    }

    private static void commWithServer() {
        try (
                Socket socket = new Socket("localhost", SERVER_PORT)
        ) {
            Chat chat = new Chat("服务端", null, socket);
            chat.chatting();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
