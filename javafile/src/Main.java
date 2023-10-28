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
}


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}