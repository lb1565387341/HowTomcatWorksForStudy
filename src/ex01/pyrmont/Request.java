package ex01.pyrmont;

import java.io.IOException;
import java.io.InputStream;

public class Request {
    private String uri;
    private InputStream input;
    private int BUFFER_SIZE = 2048;

    Request(InputStream input) {
        this.input = input;
    }

    public String getUri() {
        return uri;
    }

    public void parse() {
        StringBuffer sb = new StringBuffer(BUFFER_SIZE);
        byte[] temp = new byte[BUFFER_SIZE];
        try {
            int i = input.read(temp);
            while (i > -1 && i > BUFFER_SIZE) {
                for (int j = 0; j < i; j++) {
                    sb.append((char) temp[j]);
                }
                i = input.read(temp);
            }
            for (int j = 0; j < i; j++) {
                sb.append((char) temp[j]);
            }
            System.out.println(sb.toString());
            uri = parseUri(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseUri(String msg) {
        int index1 = msg.indexOf(" ");
        if (index1 > -1) {
            int index2 = msg.indexOf(" ", index1 + 1);
            if (index2 > -1)
                return msg.substring(index1 + 1, index2);
        }
        return "";
    }
}
