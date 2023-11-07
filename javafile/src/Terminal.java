import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Vector;
import static java.nio.file.StandardCopyOption.*;
public class Terminal {
    Parser parser;
    public static String currentDirectory = System.getProperty("user.dir");
    public static final String homeDirectory = System.getProperty("user.dir");

    Terminal(Parser parser) {
        this.parser = parser;
    }
    Terminal() {

    }
    //getter for the current path
    public String getTheCurrentPath() {
        return currentDirectory;
    }


/////////////////////////////////////////////////////////////////////////


    public void echo(String[] args) {
        if (args.length == 1) {
            System.out.println(args[0]);
        } else {
            System.out.println("error only 1 argument should be entered ");
        }
    }


////////////////////////// second function  /////////////////////////////
    public void pwd() {
        System.out.println(currentDirectory);
    }

/////////////////////////////////////////////////////////////////

    public String cd(String[] args) {
        String result = "";
        if (args.length == 0 ) {
            currentDirectory=homeDirectory;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("..")) {
                try {
                    File currentDir = new File(currentDirectory);
                    String previousPath = currentDir.getParent();
                    if (previousPath != null) {
                        currentDirectory = previousPath;
                    }
                    else {
                        result += "Already at the root directory.";
                    }
                } catch (Exception e) {
                    result += "An error occurred while navigating up the directory.\n";
                }
            } else {
                File newDir;
                newDir = new File(args[0] );
                currentDirectory=args[0];
                if (newDir.isDirectory()) {
                    currentDirectory = newDir.getPath();
                } else {
                    result += "Directory not found: " + args[0] + '\n';
                }
            }
        }
        result += currentDirectory ;
        return result;
    }


///////////////////////////////////////////

    public void ls(){
        try {
            File f = new File(currentDirectory);
            String []arr = f.list();
            Arrays.sort(arr);
            for (String s: arr ) {
                System.out.println(s);
            }
        }
        catch (Exception e) {
            System.err.println("Error listing contents of the directory: " + e.getMessage());
        }
    }

    //////////////////////////////////////////

    public void mkdir(String [] args){
        for (int i =0;i<args.length; i++)
        {
            File f = new File(currentDirectory,args[i]);
            f.mkdir();

        }
    }

    ////////////////////////////////////////
    public void ls_r(){
        try {
            File f = new File(currentDirectory);
            String []arr = f.list();
            Arrays.sort(arr);
            for (int i = arr.length-1;i>=0;i--)
            {
                System.out.println(arr[i]);
            }
        }catch (Exception e) {
            System.err.println("Error listing contents of the directory: " + e.getMessage());
        }
    }

    //////////////////////////////////////////

     public void rmdir(String [] args) {
        String dir = args[0];
        try {
            if (dir.equals("*")) {
                File theDir = new File(args[0]);
                File[] tmp = theDir.listFiles();
                for (int i = 0; i < tmp.length; i++) {
                    File file = tmp[i];
                    if (!file.isFile()) {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } else {
                File theDir;
                if (dir.contains(":")) {
                    theDir = new File(dir);
                } else {
                    theDir = new File(System.getProperty("user.dir") + "\\" + dir);
                }
                if (theDir.listFiles().length == 0) {
                    theDir.delete();
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /////////////////////////////////////////

      public void touch(String [] args) {
        File file;
        if (args[0].contains(":")) {
            file = new File(args[0]);
        } else {
            file = new File(currentDirectory + "\\" + args[0]);
        }

        try {
            if (!new File(args[0]).exists())
                file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /////////////////////////////////////////

    public void rm(String fileName) throws IOException, NoSuchFileException {
        File file = new File(currentDirectory,fileName);

        if(!file.exists())
            throw new NoSuchFileException(fileName,null,"No such file.");
        else if(file.isDirectory())
            throw new IOException("Cannot delete directory.");
        else if (!file.delete())
            throw  new IOException("Cannot delete file.");
    }

    ////////////////////////////////////////



   public void cat(String [] args) throws IOException {
        for (int i = 0 ; i< args.length;i++){
            File file = new File(currentDirectory , args[i]);
            Scanner fileReader = new Scanner(file);
            StringBuilder text= new StringBuilder();
            if (file.exists() && file.isFile())
            {
                while (fileReader.hasNextLine())
                {
                    text.append(fileReader.nextLine()+'\n');
                }
                if (text.length()>0){System.out.println(text); }
            }
            else{
                throw new IOException("No such file.");
            }
        }
    }
    //////////////////////////////////////////
    public void cp(String [] args){
        try {
            String First = args[0];
            String Last = args[1];
            if(First.substring(First.indexOf(".")).trim().equals(Last.substring(Last.indexOf(".")).trim())){
                try {
                    File ReadedFile = new File(First);
                    Scanner Reader = new Scanner(ReadedFile);
                    String ReturnedText = "";
                    while (Reader.hasNextLine()){
                        ReturnedText += Reader.nextLine();
                    }
                    Reader.close();
                    try{
                        FileWriter Writer = new FileWriter(Last);
                        Writer.write(ReturnedText);
                        Writer.close();
                        System.out.println("Text Copied Successfully.");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }catch (ArrayIndexOutOfBoundsException ex)
        {
            System.out.println(ex.toString());
        }

}
/////////////////////////////////////////////

    public static void cpr(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid syntax. Usage: cpr <sourceDirectory> <destinationDirectory>");
            return;
        }

        String sourcePath = currentDirectory + File.separator + args[0];
        String destinationPath = currentDirectory + File.separator + args[1];

        File sourceDir = new File(sourcePath);
        File destinationDir = new File(destinationPath);

        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            System.out.println("Source directory does not exist or is not a directory.");
            return;
        }

        if (!destinationDir.exists()) {
            if (!destinationDir.mkdirs()) {
                System.out.println("Failed to create the destination directory.");
                return;
            }
        }

        try {
            Files.walkFileTree(sourceDir.toPath(), EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path targetFile = destinationDir.toPath().resolve(sourceDir.toPath().relativize(file));
                    Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = destinationDir.toPath().resolve(sourceDir.toPath().relativize(dir));
                    if (!Files.exists(targetDir)) {
                        Files.createDirectory(targetDir);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("Data Copied Successfully");
        } catch (IOException e) {
            System.out.println("Error copying files: " + e.getMessage());
        }
    }



public static void insert(String [] args){
    try{
        FileWriter Obj1 = new FileWriter(currentDirectory+args[0]);
        Obj1.write(args[1]);
        Obj1.close();
        System.out.println("File Is Updated by Data");
    }catch (IOException e){
        System.out.println(e.getMessage());
    }
    finally {
        System.out.println("Command Ended");
}
}

    //////////////////////////////////////////


    public void help(){
        System.out.println("echo   -> takes 1 argument and prints it..");
        System.out.println("pwd    -> return an absolute (full) path.");
        System.out.println("cd     -> change the directory.");
        System.out.println("ls     -> lists the contents of the current directory sorted alphabetically");
        System.out.println("ls -r  -> lists the contents of the current directory in reverse order.");
        System.out.println("mkdir  -> make a new directory.");
        System.out.println("rmdir  -> delete a directory.");
        System.out.println("touch  -> create a file ");
        System.out.println("rm     -> delete directories and the contents within them.");
        System.out.println("cat    -> Prints all contents in files.");
        System.out.println("help   -> display all command to help you.");
        System.out.println("clear   -> clears the screen.");
        System.out.println("exit   -> stop program.");

    }

    public void clear(){
        for(int i = 0;i < 100;i++){
            System.out.println();
        }
    }

    public void chooseCommandAction(String command, String[] args) throws IOException {
        switch (command){
            case "echo" :
                echo(args);
                break;
            case "pwd" :
                pwd();
                break;
            case "cd" :
                System.out.println(cd(args));
                break;
            case "ls" :
                ls();
                break;
            case "ls-r" :
                ls_r();
                break;
            case "mkdir" :
                mkdir(args);
                break;
            case "rmdir" :
                rmdir(args);
                break;
            case "touch":
                touch(args);
                break;
            case "help":
                help();
                break;
            case "cat":
                cat(args);
                break;
            case "rm":
                rm(args[0]);
                break;
            case "clear" :
                clear();
                break;
            case "cp":
                cp(args);
                break;
            case ">":
                insert(args);
                break;
            case "cp-r":
                cpr(args);
                break;

        }
    }
}
