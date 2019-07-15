package ex02.pyrmont;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

public class Response implements ServletResponse {
    private OutputStream output;
    private Request request;
    private static final int BUFFER_SIZE = 2048;
    private PrintWriter pw;

    public void setRequest(Request request) {
        this.request = request;
    }

    Response(OutputStream output) {
        this.output = output;
    }

    public void sendStaticResources() {
        String uri = request.getUri();
        System.out.println("访问地址：" + Constants.WEB_ROOT + uri);
        File file = new File(Constants.WEB_ROOT, uri);
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


    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        pw = new PrintWriter(output, true); // true 自动发送 println 直接发送 print 不直接发送
        return pw;
    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
