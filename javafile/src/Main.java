import java.io.IOException;
import java.util.*;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

//import org.apache.commons.io.FileUtils;

//import java.awt.event.TextEvent;

public class Main {
    public static void main(String[] args) throws IOException {
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
            }
            else {
                if (parser.parse(userInput)) {
                    terminal.chooseCommandAction(parser.commandName, parser.getArgs());
                } else {
                    System.out.println("wrong command , please try again ");
                }
            }
        }
    }
}
