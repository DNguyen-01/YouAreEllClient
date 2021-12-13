package controllers;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;

import com.fasterxml.jackson.core.type.TypeReference;
import models.Id;
import models.Message;
import models.SupportedHttpMethod;

public class MessageController {

    private HashSet<Message> messagesSeen;
    // why a HashSet?? - immediate constant time look-up w/o worrying about order
    //as soon as you receive the message, you'll just save it to the set
    //URL: /messages

    public ArrayList<Message> getMessages() {
        //using the method from HTTPController
        try {
            HttpResponse<String> response = HTTPController.sendRequest("/messages", SupportedHttpMethod.GET, null);
            ArrayList<Message> messageList = HTTPController.objectMapper.readValue(response.body(), new TypeReference<ArrayList<Message>>() {
            });
            System.out.println(messageList);
            return messageList;

        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        return null;
    }
    //URL: /ids/:githubname/messages
    public ArrayList<Message> getMessagesForId(Id id) {
        try {
            HttpResponse<String> response = HTTPController.sendRequest("/ids/" + id.getGithub() +"/messages", SupportedHttpMethod.GET, null);
            ArrayList<Message> messageList = HTTPController.objectMapper.readValue(response.body(), new TypeReference<ArrayList<Message>>() {
            });
            System.out.println(messageList);
            return messageList;

        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        return null;
    }
    //URL: /ids/:githubname/messages/:sequence
    public Message getMessageForSequence(Id id, String seq) {

        try {
            HttpResponse<String> response = HTTPController.sendRequest("/ids/" + id.getGithub() +"/messages/" + seq, SupportedHttpMethod.GET, null);
            Message message = HTTPController.objectMapper.readValue(response.body(), new TypeReference<Message>(){
            });
            System.out.println(message);
            return message;

        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        return null;
    }
    //URL: /ids/:githubname/from/:friendId
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        try {
            HttpResponse<String> response = HTTPController.sendRequest("/ids/" + myId.getGithub() + "/from/" + friendId.getGithub(), SupportedHttpMethod.GET, null);
            ArrayList<Message> messageList = HTTPController.objectMapper.readValue(response.body(), new TypeReference<ArrayList<Message>>() {
            });
            System.out.println(messageList);
            return messageList;

        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        return null;
    }
    //URL:  /ids/:toId/messages
    public Message postMessage(Message msg) {

        try {
            HttpResponse<String> response = HTTPController.sendRequest("/ids/" + msg.getToid() + "/messages", SupportedHttpMethod.POST, HTTPController.objectMapper.writeValueAsString(msg));
            Message message = HTTPController.objectMapper.readValue(response.body(), new TypeReference<Message>(){
            });
            System.out.println(message);
            return message;

        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        return null;
    }
 
}