package com.balarawool.vtworkshop.simple;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ServerUtil {
    /**
     * Calls a specific endpoint of the server with sample SpringBoot app running locally.
     * @param service the endpoint to be called.
     * @param params the path params to be used.
     * @return HttpResponse
     */
    public static HttpResponse<String> call(String service, Map<String, String> params) {
        var sb = new StringBuilder();
        params.forEach((k, v) -> {
            var pre = sb.length() == 0 ? "?" : "&";
            sb.append(pre).append(k).append("=").append(v);
        });
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8001"+service+ sb)).build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (ConnectException e) {
            throw new RuntimeException("ServerApplication is not running or unreachable. Please start server, if not running, and try again!", e);
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpResponse<String> call(String service) {
        return call(service, new HashMap<>());
    }
}