package org.example;

public class Main {
    private static final int PORT = 9999;
    private static final int THREADPOOL_SIZE = 64;

    public static void main(String[] args) {
        final var server = new Server(PORT, THREADPOOL_SIZE);
        server.start();
    }
}


