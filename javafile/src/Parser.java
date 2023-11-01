import java.util.Arrays;

public class Parser {
    String commandName;
    String[] args;
    public boolean parse(String input) {
        String[] userInput = input.split(" ");
        commandName = userInput[0];
        args = Arrays.copyOfRange(userInput, 1, userInput.length);
        if (commandName.equals("echo")){
            return true;
        }
        else if (commandName.equals("cd")) {
            return true;
        }
        else if (commandName.equals("pwd")) {
            return true;
        }
        else if (commandName.equals("help")) {
            return true;
        }
        return false;
    }

    public String getCommandName(){
        return commandName;
    }
    public String[] getArgs(){
        return args ;
    }
}
