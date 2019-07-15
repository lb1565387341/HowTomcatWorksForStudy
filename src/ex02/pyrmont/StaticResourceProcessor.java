package ex02.pyrmont;

public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        response.sendStaticResources();
    }
}
