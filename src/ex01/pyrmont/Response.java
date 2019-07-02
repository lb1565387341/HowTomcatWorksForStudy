package ex01.pyrmont;


import java.io.*;

public class Response {
    private OutputStream output;
    private Request request;
    private static final int BUFFER_SIZE = 2048;

    public void setRequest(Request request) {
        this.request = request;
    }

    Response(OutputStream output) {
        this.output = output;
    }

    public void sendStaticResources() {
        String uri = request.getUri();
        System.out.println("访问地址：" + HttpServer.WEB_ROOT + uri);
        File file = new File(HttpServer.WEB_ROOT, uri);
        FileInputStream fis = null;
        BufferedInputStream br = null;
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                br = new BufferedInputStream(fis, BUFFER_SIZE);
                byte[] temp = new byte[BUFFER_SIZE];
                int i = br.read(temp, 0, BUFFER_SIZE);
                while (i != -1) {
                    output.write(temp, 0, i);
                    i = br.read(temp, 0, BUFFER_SIZE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            String errorMsg = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            try {
                output.write(errorMsg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
