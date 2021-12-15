package views;

import controllers.IdController;
import controllers.MessageController;
import models.Id;
import models.Message;
import youareell.YouAreEll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Simple Shell is a Console view for youareell.YouAreEll.


public class SimpleShell {


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {

        YouAreEll webber = new YouAreEll(new MessageController(), new IdController());
        Map<String, Id> gitHubNameId = new HashMap<>();
        
        String commandLine;
        BufferedReader console = new BufferedReader
                //this console reads the input
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }


                // Specific Commands.

                // ids - returning us the list of ids
                if (list.contains("ids"))  {
                    if (list.size() == 1) {
                        String results = webber.get_ids();
                        SimpleShell.prettyPrint(results);
                    }else if(list.size() == 3){
                        String name = list.get(1), gitHubId = list.get(2), results = null;
                        Id id = new Id();
                        if(gitHubNameId.get(gitHubId) == null) {
                            id.setName(name);
                            id.setGithub(gitHubId);
                            id = webber.getIdController().postId(id);
                            results = id.toString();
                        }else {
                            id = gitHubNameId.get(gitHubId);
                            id.setName(name);
                            System.out.println(id);
                            results = webber.put_id(id);
                        }
                        SimpleShell.prettyPrint(results);
                        gitHubNameId.put(gitHubId,id);
                    }
                    else {
                        SimpleShell.prettyPrint("Invalid Use of Ids!");
                    }
                    continue;
                }

                // messages
                if (list.contains("messages")) {
                    if(list.size() == 1){
                        String results = webber.get_messages();
                        SimpleShell.prettyPrint(results);
                    }else if(list.size() == 2){
                        Id id = new Id("", list.get(1));
                        String results = webber.get_messages_by_githubId(id);
                        SimpleShell.prettyPrint(results);
                    }else{
                        SimpleShell.prettyPrint("Invalid Use of Messages!");
                    }
                    continue;
                }

                //sending messages
                if(list.size() >= 1 && list.get(0).equals("send")){
                    int singleQuoteStartIndex = commandLine.indexOf('\''); //indexof -- searches from right to left
                    int singleQuoteEndIndex = commandLine.lastIndexOf('\''); //lastIndexof -- searches from right to left
                    if(singleQuoteStartIndex == singleQuoteEndIndex){ //checks if there are no index, parse the entire string
                        singleQuoteStartIndex = commandLine.length()-1;
                        singleQuoteEndIndex = commandLine.length()-1;
                    }
                    list = new ArrayList<>();
                    String firstPart = commandLine.substring(0, singleQuoteStartIndex);
                    String lastPart = commandLine.substring(singleQuoteEndIndex+1); //give me a substring that has an index w/ an outbound, returns an empty string if there is no part after the single quote
                    String[] beforeMessage = firstPart.split(" ");
                    for(String word : beforeMessage){
                        list.add(word);
                    }
                    if(singleQuoteStartIndex != singleQuoteEndIndex){
                        list.add(commandLine.substring(singleQuoteStartIndex+1, singleQuoteEndIndex));
                        String[] afterMessage = lastPart.split(" ");
                        for (String word : afterMessage) {
                            if (!word.equals("")) {
                                list.add(word);
                            }
                        }
                    }
                    if(list.size() == 3){
                        Message message = new Message();
                        message.setMessage(list.get(2));
                        message.setToid(list.get(1));
                        SimpleShell.prettyPrint(webber.post_message(message));
                    }else if (list.size() == 5 && list.get(3).equals("to")){
                        Message message = new Message();
                        message.setMessage(list.get(2));
                        message.setToid(list.get(4));
                        message.setFromid(list.get(1));
                        SimpleShell.prettyPrint(webber.post_message(message));
                    }else {
                        SimpleShell.prettyPrint("Invalid Use of Send!");
                    }
                    continue;
                }



                /* TODO: complete the send message function */

                // you need to add a bunch more.

                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // // wait, wait, what curiousness is this?
                 Process process = pb.start();

                // //obtain the input stream
                 InputStream is = process.getInputStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr);

                 //read output of the process
                 String line;
                 while ((line = br.readLine()) != null)
                     System.out.println(line);
                 br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (IOException e) {
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

}