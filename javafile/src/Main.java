import java.io.IOException;
import java.util.*;
import java.nio.file.*;
//import java.awt.event.TextEvent;



class Parser {
    String commandName;
    String[] args;
    public void parse(String input) {
        String[] userInput = input.split(" ");
        commandName = userInput[0];
        args = Arrays.copyOfRange(userInput, 1, userInput.length);
    }

    public String getCommandName(){
        return commandName;
    }
    public String[] getArgs(){
        return args ;
    }
}

class Terminal {
    Parser parser;
    private Path theCurrentPath = FileSystems.getDefault().getPath("C:\\Users\\Mohamed reyad");
    Terminal(Parser parser) {
        this.parser = parser;
    }
    Terminal() {

    }

    public void echo(String[] args) {
        if (args.length == 1) {
            System.out.println(args[0]);
        } else {
            System.out.println("error only 1 argument should be entered ");
        }
    }


    ////////////////////////// second function  /////////////////////////////
    public void pwd(){
        System.out.println(theCurrentPath.toString());
    }

}


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}