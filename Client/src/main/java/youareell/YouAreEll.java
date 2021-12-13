package youareell;

import controllers.*;
import models.Id;
import models.Message;

public class YouAreEll {

    MessageController messageController;
    IdController idController;
    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
        this.messageController = t.getMsgCtrl();
        this.idController = t.getIdCtrl();
    }

    public YouAreEll(MessageController messageController, IdController idController) {
        this.idController = idController;
        this.messageController = messageController;
    }


    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
//        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
//        urlhandler.idController.getIds(); //had the urlHandler to get the id
//        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
//        Id id = new Id();
//        id.setUserid("18bfd91e6235cea3cfd12a1a938a1977b01380da");
//        id.setName("David KeystoneFirst");
//        id.setGithub("DNguyen-01");
//        urlhandler.idController.putId(id);
//        urlhandler.messageController.getMessages();
        Id id = new Id();
        Id id2 = new Id();
        id.setGithub("keerthana-java");
        id2.setGithub("temp-java");
        Message message = new Message();
        message.setToid("keerthana-java");
        message.setMessage("Hello");
        message.setFromid("DNguyen-01");
        urlhandler.messageController.postMessage(message);



        /*TODO: pass in an invalid sequence, githubname, id, and see what happens?!
        * rerun the test from MessageController
         */
    }

//    private String MakeURLCall(String s, String get, String s1) {
//        return "";
//    }

    public String get_ids() {

        return idController.getIds().toString();
    }

    public String get_messages() {

        return messageController.getMessages().toString();
    }


    public String post_id(Id id){

        return idController.postId(id).toString();
    }

}
