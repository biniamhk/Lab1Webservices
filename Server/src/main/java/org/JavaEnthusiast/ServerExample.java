package org.JavaEnthusiast;

import org.JavaEnthusiast.FileUtils.FileReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

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

        DataCall dataCall = new DataCall();

        System.out.println(Thread.currentThread());

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String url = readHeaders(input);

            var output = new PrintWriter(socket.getOutputStream());

            if (url.equals("/contacts")) {

                var jsonResponse = createJsonResponse();

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + jsonResponse.getBytes().length);
                output.println("Content-Type:" + "application/json");  //application/json
                output.println("");

                output.println(jsonResponse);
                output.flush();
            }
            else if(url.equals("/upload")){

                System.out.println(input);

                StringBuilder jb = new StringBuilder();
                while (input.ready()){
                    jb.append((char) input.read());
                }

                    JSONObject jsonObject = new JSONObject(jb.toString());

                    String firstName = jsonObject.getString("FirstName");
                    String lastName = jsonObject.getString("LastName");

                    dataCall.addContacts(0, firstName, lastName);
            }
            else if(url.equals("/index.html") || url.equals("/cat.png") || url.equals("/Laboration1.pdf")){

                File file = new File("web" + File.separator + url);
                byte[] page = FileReader.readFromFile(file);

                String contentType = Files.probeContentType(file.toPath());

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type:" + contentType);
                //output.println("Content-type::" + "contacts.json");
                output.println("");
                output.flush();

                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                dataOut.write(page);
                dataOut.flush();
            }
            else{
                var output2 = new PrintWriter(socket.getOutputStream());
            String page = """
                    <html>
                    <head>
                        <title>404-Not Found</title>
                    </head>
                    <body>
                    <h1>File Not Found</h1>
                    <div>Possible cause: Invalid url</div>
                    </body>
                    </html>""";
                output2.println("HTTP/1.1 404 Error");
                output2.println("Content-Length:" + page.getBytes().length);
                output2.println("Error");
                output2.println("");
                output2.print(page);

                output2.flush();
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readHeaders(BufferedReader input) throws IOException {
        String requestedUrl = "";
        while (true) {
            String headerLine = input.readLine();
            if (headerLine.startsWith("GET") || headerLine.startsWith("POST") || headerLine.startsWith("HEAD")){
                requestedUrl = headerLine.split(" ")[1];
            }
            System.out.println(headerLine);
            if (headerLine.isEmpty())
                break;
        }
        return requestedUrl;
    }

    private static String createJsonResponse() {

        DataCall dataCall = new DataCall();

        JsonConverter converter = new JsonConverter();

        var Json = converter.convertToJson(dataCall.getAll());

        System.out.println(Json);

        return Json;
    }
}