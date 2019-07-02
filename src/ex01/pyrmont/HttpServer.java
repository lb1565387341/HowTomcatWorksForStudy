package ex01.pyrmont;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean SHUTDOWN = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        try {
            ServerSocket serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
            System.out.println("----- web 服务器启动完成 -------");
            while (!server.SHUTDOWN) {
                Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResources();

                socket.close();
                if (request.getUri().equals(SHUTDOWN_COMMAND)) {
                    server.SHUTDOWN = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
