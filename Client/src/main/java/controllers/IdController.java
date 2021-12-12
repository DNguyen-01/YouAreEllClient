package controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.http.*;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;


public class IdController {

    private HashMap<String, Id> allIds;
    private ObjectMapper objectMapper = new ObjectMapper();
    Id myId;

    public ArrayList<Id> getIds() {
        //implement the client request and response
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://zipcode.rocks:8085/ids"))
//                    .header("Content-Type", "application/json") might not be needed
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Id> idList = objectMapper.readValue(response.body(), new TypeReference<List<Id>>() {
            });
//            System.out.println(response.statusCode());
            System.out.println(response.body());
            System.out.println(idList.get(0).getGithub());
        }
        catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public Id postId(Id id) {
//        HttpClient httpClient = (HttpClient) HttpClient.newBuilder();
//        String json = new StringBuilder()
//                .append("{")
//                .append("\"name\":\"mkyong\",")
//                .append("\"notes\":\"hello\"")
//                .append("}").toString();
//
//        try {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .POST(HttpRequest.BodyPublishers.ofString(json))
//                    .uri(URI.create("https://httpbin.org/post"))
//                    .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
//                    .header("Content-Type", "application/json")
//                    .build();
//
//            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//            List<Id> idList = objectMapper.readValue(response.body(), new TypeReference<List<Id>>() {
//            });
//
//        }
//        catch(Exception e) {
//
//            // create json from id
//            // call server, get json result Or error
//            // result json to Id obj
//        }
        return null;
    }



    public Id putId(Id id) {
        return null;
    }
 
}