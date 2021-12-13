package controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.http.*;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.SupportedHttpMethod;


public class IdController {

    private HashMap<String, Id> allIds;
    private ObjectMapper objectMapper = new ObjectMapper();
    Id myId;

    public ArrayList<Id> getIds() {
        //implement the client request and response
        try {
            HttpResponse<String> response = HTTPController.sendRequest("/ids", SupportedHttpMethod.GET, null);
            ArrayList<Id> idList = objectMapper.readValue(response.body(), new TypeReference<ArrayList<Id>>() {
            });
////            System.out.println(response.statusCode());
//            System.out.println(response.body());
//            System.out.println(idList.get(0).getGithub());
            return idList;
        }
        catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }


    public Id postId(Id id) { //adding to the collection, requesting the server to accept the entity

        try {
            String json = HTTPController.objectMapper.writeValueAsString(id);

            HttpResponse<String> response = HTTPController.sendRequest("/ids", SupportedHttpMethod.POST, json);
            Id responseId = objectMapper.readValue(response.body(), new TypeReference<Id>() {
            });
            System.out.println(responseId);

            return  responseId;
        }
        catch(Exception e) {

            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }



    public Id putId(Id id) { //method call when you have to modify a single resource, which is already a part of the receiver collection

        try {
            String json = HTTPController.objectMapper.writeValueAsString(id);

            HttpResponse<String> response = HTTPController.sendRequest("/ids", SupportedHttpMethod.PUT, json);
            Id responseId = objectMapper.readValue(response.body(), new TypeReference<Id>() {
            });
            System.out.println(responseId);

            return  responseId;
        }
        catch(Exception e) {

            System.out.println("Exception: " + e.getMessage());
        }

        return null;
    }
 
}