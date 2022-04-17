package frontend.Fetchers;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

public class CommonFetchers {
    public HttpRequest getPostRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(BodyPublishers.ofString(""))
                .build();
    }

}
