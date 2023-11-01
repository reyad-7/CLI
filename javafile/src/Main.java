import java.io.IOException;
import java.util.*;
import java.nio.file.*;
//import java.awt.event.TextEvent;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Terminal terminal = new Terminal(parser);
        String userInput;
        boolean KeepGoing = true;
        Scanner in = new Scanner(System.in);
        while (KeepGoing) {
            System.out.print("-->");
            userInput = in.nextLine();

            if (userInput.equals("exit")) {
                KeepGoing = false;
                break;
            } else {
                parser.parse(userInput);
                terminal.chooseCommandAction(parser.commandName,parser.getArgs());
            }
        }
    }
}