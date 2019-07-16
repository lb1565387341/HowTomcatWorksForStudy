package ex02.pyrmont;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer2 {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean SHUTDOWN = false;

    public static void main(String[] args) {
        HttpServer2 server = new HttpServer2();
        server.await();
    }

    private void await() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
            System.out.println("----- web 服务器启动完成 -------");
            while (!SHUTDOWN) {
                Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                // response.sendStaticResources();
                if (request.getUri().indexOf("/servlet/") > -1) {
                    ServletProcessor2 processor = new ServletProcessor2();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                socket.close();
                if (request.getUri().equals(SHUTDOWN_COMMAND)) {
                    SHUTDOWN = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
