import java.util.Arrays;

public class Parser {
    String commandName;
    String[] args;
    private String userInput;
    public boolean parse(String input) {
        userInput = input;
        String[] parts = input.split(" ");
        if (parts.length > 0) {
            commandName = parts[0];
            args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);
        }

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
