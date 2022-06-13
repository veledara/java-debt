package org.homework.server;

import org.homework.methods.Methods;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Server extends Thread {
    public static Set<String> nicknames = new HashSet<>();
    public static ArrayList<Server> users = new ArrayList<>();
    public static ArrayList<Server> servers = new ArrayList<>();
    public String nickname;
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;

    public Server(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String message;
        boolean isNicknameCorrect = false;
        while (!isNicknameCorrect) {
            try {
                message = in.readLine();
                if (nicknames.contains(message)) {
                    out.write("bad\n");
                    out.write("Это имя недоступно. Попробуйте другое:\n");
                    out.flush();
                } else {
                    nicknames.add(message);
                    users.add(this);
                    nickname = message;
                    out.write("good\n");
                    out.write("Добро пожаловать, " + message + "! Чтобы выйти из чата, напишите '/exit'\n");
                    out.flush();
                    isNicknameCorrect = true;
                }
            } catch (IOException e) {
                close();
            }
        }
        try {
            try {
                while (true) {
                    message = in.readLine();
                    if (message.equals("/exit")) {
                        users.remove(this);
                        servers.remove(this);
                        nicknames.remove(nickname);
                        break;
                    }
                    System.out.println(message);
                    for (Server server : servers) {
                        server.send(message);
                    }
                }
            } catch (NullPointerException e) {
                close();
            }
        } catch (IOException e) {
            close();
        }
    }

    private void send(String message) throws IOException {
        out.write(message + "\n");
        out.flush();
    }

    private void close() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (Server server : servers) {
                    if (server.equals(this)) server.interrupt();
                    servers.remove(this);
                }
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при закрытии сокета.");
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Введите порт сервера:");
        int port = Methods.isPortCorrect();
        while (port > 65535 || port < 0) {
            System.out.println("Введенный порт не находится в диапозоне [0, 65535]. Попробуйте еще раз.");
            port = Methods.isPortCorrect();
        }
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Сервер запущен!");
            while (true) {
                Socket socket = server.accept();
                try {
                    servers.add(new Server(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        }
    }
}
