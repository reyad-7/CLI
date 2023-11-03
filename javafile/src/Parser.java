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
        else if (commandName.equals("cat")) {
            return true;
        }
        else if (commandName.equals("touch")) {
            return true;
        }
        else if (commandName.equals("rm")) {
            return true;
        }
        else if (commandName.equals("rmdir")) {
            return true;
        }
        else if (commandName.equals("mkdir")) {
            return true;
        }
        else if (commandName.equals("ls")) {
            return true;
        }
        else if (commandName.equals("ls-r")) {
            return true;
        }
        else if (commandName.equals("cp")) {
            return true;
        }
        else if (commandName.equals(">")) {
            return true;
        }
        else if (commandName.equals("cp-r")) {
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
