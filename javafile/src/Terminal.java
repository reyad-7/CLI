import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

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
            if (args[0].equalsIgnoreCase("..")) {  // second check to change the current directory to the previous directory.
                try {
                    File currentDir = new File(currentDirectory);
                    String previousPath = currentDir.getParent();
                    if (previousPath != null) {
                        currentDirectory = previousPath;
                    } else {
                        result += "Already at the root directory.";
                    }
                } catch (Exception e) {
                    result += "An error occurred while navigating up the directory.\n";
                }
            } else {// third check to change the current path to that path
                File newDir;
                newDir = new File(currentDirectory, args[0].toString());
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

    public void ls(String [] args){

    }

    //////////////////////////////////////////

    public void mkdir(String [] args){

    }

    //////////////////////////////////////////

     public void rmdir(String [] args) {
        String dir = args[0];
        try {
            if (dir.equals("*")) {
                File theDir = currentDirectory;
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

    public void chooseCommandAction(String command, String[] args) {
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
//             System.out.println(ls(args));
                break;

            case "mkdir" :
//                mkdir(args);
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
        }
    }




}
