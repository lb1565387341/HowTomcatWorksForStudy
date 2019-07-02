package ex01.pyrmont;

import java.io.*;

public class Response {
    private OutputStream output;
    private Request request;
    private final int BUFFER_SIZE = 2048;

    public void setRequest(Request request) {
        this.request = request;
    }

    Response(OutputStream output) {
        this.output = output;
    }

    public void sendStaticResources() {
        String uri = request.getUri();
        File file = new File(HttpServer.WEB_ROOT + uri);
        FileReader reader = null;
        BufferedReader br = null;
        if (file.exists()) {
            try {
                reader = new FileReader(file);
                br = new BufferedReader(reader, BUFFER_SIZE);
                char[] temp = new char[BUFFER_SIZE];
                int i = br.read(temp, 0, BUFFER_SIZE);
                while (i != -1) {
                    output.write(temp.toString().getBytes(), 0, i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
        }
    }
}
