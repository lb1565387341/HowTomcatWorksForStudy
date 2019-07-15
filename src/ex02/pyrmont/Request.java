package ex02.pyrmont;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public class Request implements ServletRequest {
    private String uri;
    private InputStream input;
    private static final int BUFFER_SIZE = 2048;

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
        return null;
    }


    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Enumeration getAttributeNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getParameter(String s) {
        return null;
    }

    @Override
    public Enumeration getParameterNames() {
        return null;
    }

    @Override
    public String[] getParameterValues(String s) {
        return new String[0];
    }

    @Override
    public Map getParameterMap() {
        return null;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }
}
