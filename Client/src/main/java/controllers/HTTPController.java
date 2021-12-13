package controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import models.SupportedHttpMethod;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPController {

    public static ObjectMapper objectMapper = new ObjectMapper();
    private static HttpClient client = HttpClient.newBuilder().build();
    private static final String baseUrl = "http://zipcode.rocks:8085";

    public static HttpResponse<String> sendRequest(String path, SupportedHttpMethod httpMethod, String body) {

        //helper class/method to make call/receive data from the client and translate it into
        //into the defined models -> streamline method calls

        //base to be built our post
        try {
            //header to identify the request - typically aren't used in the Get
            Builder builder = HttpRequest.newBuilder().uri(URI.create(baseUrl + path)).header("Content-Type", "application/json");
            switch (httpMethod) {
                case GET:
                    builder = builder.GET();
                    break;
                case POST:
                    builder = builder.POST(HttpRequest.BodyPublishers.ofString(body));
                    break;
                case PUT:
                    builder = builder.PUT(HttpRequest.BodyPublishers.ofString(body));
                    break;
            }
            HttpRequest request = builder.build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (Exception e){

            System.out.println("Exception: " + e.getMessage());

            return null;
        }
    }




}
