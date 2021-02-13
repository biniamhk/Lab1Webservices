package org.JavaEnthusiast;

import org.JavaEnthusiast.FileUtils.FileReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            //TCP/IP
            ServerSocket serverSocket = new ServerSocket(5050);
            System.out.println(Thread.currentThread());

            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(() -> handleConnection(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket socket) {
        System.out.println(Thread.currentThread());
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String url = readHeaders(input);

            var output = new PrintWriter(socket.getOutputStream());

            File file = new File("web" + File.separator + url);
            byte[] page = FileReader.readFromFile(file);

            String contentType = Files.probeContentType(file.toPath());

            output.println("HTTP/1.1 200 OK");
            output.println("Content-Length:" + page.length);
            output.println("Content-Type:" + contentType);  //application/json
            output.println("");
            output.flush();

            var dataOut = new BufferedOutputStream(socket.getOutputStream());
            dataOut.write(page);
            dataOut.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readHeaders(BufferedReader input) throws IOException {
        String requestedUrl = "";
        String requestType = "";
        while (true) {
            String headerLine = input.readLine();

            if (headerLine.startsWith("GET") || headerLine.startsWith("POST")){
                requestType = headerLine.split(" ")[0];
                requestedUrl = headerLine.split(" ")[1];
            }
            else if(headerLine.startsWith("Content-Type: ")){

            }
            else if(headerLine.startsWith("Content-Length: ")){

            }
            System.out.println(headerLine);
            if (headerLine.isEmpty())
                break;
        }
        return requestedUrl;
    }

    private static void createJsonResponse() {
        DataCall dataCall = new DataCall();

        JsonConverter converter = new JsonConverter();

        var json = converter.convertToJson(dataCall.getAll());
        System.out.println(json);

    }
}