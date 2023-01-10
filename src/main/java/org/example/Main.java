package org.example;

import java.io.IOException;

public class Main {
    private static final int PORT = 9999;
    private static final int THREADPOOL_SIZE = 64;

    public static void main(String[] args) {
        final var server = new Server(PORT, THREADPOOL_SIZE);

        server.addHandler("GET", "/message", (request, responseStream) -> {
            try {
                responseStream.write(("Processing GET request \r\n" +
                        "Параметры запроса: " + request.getQueryParams() + "\r\n").getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        server.addHandler("POST", "/message", (request, responseStream) -> {
            try {
                responseStream.write(("Значение параметра name : " + request.getQueryParam("id", request.getQueryParams()) + "\r\n").getBytes());
                System.out.println("Processing POST request");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        server.addHandler("GET", "/", ((request, outputStream) -> server.defaultHandler(outputStream, "index.html")));

        server.start();
    }
}


