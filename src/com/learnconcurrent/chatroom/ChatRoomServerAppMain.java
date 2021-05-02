package com.learnconcurrent.chatroom;

import com.learnconcurrent.chatroom.server.ChatRoomServer;

import java.io.IOException;

import static com.learnconcurrent.chatroom.common.Constants.SERVER_PORT;

public class ChatRoomServerAppMain {
    public static void main(String[] args) throws IOException {
        ChatRoomServer server = new ChatRoomServer(SERVER_PORT);
        server.start();
    }
}
