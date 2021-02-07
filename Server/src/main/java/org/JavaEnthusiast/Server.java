package org.JavaEnthusiast;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        File file = new File("web\\index.html");
        new Server().readFromFile(file);

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            System.out.println(Thread.currentThread());

            while (true){
                Socket socket = serverSocket.accept();

                executorService.execute(() -> handleConnections(socket));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void handleConnections(Socket socket) {
        System.out.println(Thread.currentThread());

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                String headerLine = input.readLine();
                System.out.println(headerLine);
                if (headerLine.isEmpty())
                    break;
            }

            var output = new PrintWriter(socket.getOutputStream());
            String page = """
                    <html>
                    <head>
                    <title>Hello World!</title>
                    </head>
                    <body>
                    <h1>Hello There!</h1>
                    <div>First Page</div>
                    </body>
                    </html>""";

            output.println("HTTP/1.1 200 OK");
            output.println("Content-Length:" + page.getBytes().length);
            output.println("Content-Type:text/html");
            output.println("");
            output.println(page);

            output.flush();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private static void Json(){
//
//
//        JsonConverter converter = new JsonConverter();
//
//        var json = converter.ConvertToJson();
//        System.out.println(json);
//    }

    private byte[] readFromFile(File file){
        byte[] content = new byte[0];
        System.out.println("Does file exists: " + file.exists());
        if(file.exists() && file.canRead()){
            try (FileInputStream fileInputStream = new FileInputStream(file)){
                content = new byte[(int) file.length()];
                int count = fileInputStream.read(content);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}