package org.example;

import java.io.IOException;

public class Main {
    private static final int PORT = 9999;
    private static final int THREADPOOL_SIZE = 64;

    public static void main(String[] args) {
        final var server = new Server(PORT, THREADPOOL_SIZE);

        server.addHandler("GET", "/messages", (request, responseStream) -> {
            try {
                server.responseWithoutContent(responseStream, "404", "Not Found");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        server.addHandler("POST", "/messages", (request, responseStream) -> server.responseWithoutContent(responseStream, "503", "Service Unavailable"));

        server.addHandler("GET", "/", ((request, outputStream) -> server.defaultHandler(outputStream, "index.html")));

        server.start();
    }
}


