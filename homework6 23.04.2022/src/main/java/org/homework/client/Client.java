package org.homework.client;

import org.homework.methods.Methods;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Client {
    public static Scanner scanner = new Scanner(System.in);
    public static String ip = "localhost";
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private String nickname;

    public Client(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
        } catch (IOException e) {
            System.err.println("Ошибка при создании сокета.");
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.nicknameEntering();
            new ReadMessage().start();
            new WriteMessage().start();
        } catch (IOException e) {
            Client.this.close();
        }
    }

    private void nicknameEntering() {
        System.out.print("Введите имя пользователя: ");
        String consoleInput;
        String message = "";
        try {
            while (!message.equals("good")) {
                consoleInput = scanner.nextLine();
                out.write(consoleInput + "\n");
                out.flush();
                message = in.readLine();
                if (message.equals("bad")) {
                    message = in.readLine();
                    System.out.println(message);
                } else if (message.equals("good")) {
                    message = in.readLine();
                    System.out.println(message);
                    nickname = consoleInput;
                    break;
                }
            }
        } catch (IOException e) {
            close();
        }
    }

    private class ReadMessage extends Thread {
        @Override
        public void run() {
            String message;
            try {
                while (true) {
                    message = in.readLine();
                    if (message.equals("/exit")) {
                        Client.this.close();
                        break;
                    }
                    System.out.println(message);
                }
            } catch (IOException e) {
                close();
            }
        }
    }

    public class WriteMessage extends Thread {
        @Override
        public void run() {
            while (true) {
                String message;
                try {
                    Date time = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    String date = dateFormat.format(time);
                    message = inputUser.readLine();
                    if (message.equals("/exit")) {
                        out.write("/exit" + "\n");
                        System.out.println("Вы вышли из чата.");
                        close();
                        break;
                    } else {
                        out.write("[" + date + "] " + nickname + ": " + message + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                    close();
                }
            }
        }
    }

    private void close() {
        try {
            if (!socket.isBound()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при закрытии сокета.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Введите порт сервера:");
        int port = Methods.isPortCorrect();
        while (port > 65535 || port < 0) {
            System.out.println("Введенный порт не находится в диапозоне [0, 65535]. Попробуйте еще раз.");
            port = Methods.isPortCorrect();
        }
        new Client(ip, port);
    }
}
