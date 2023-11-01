import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Terminal {
    Parser parser;
    private Path CurrentPath = FileSystems.getDefault().getPath(System.getProperty("user.dir"));
    private Path previousPath;

    Terminal(Parser parser) {
        this.parser = parser;
    }
    Terminal() {

    }
    //getter for the current path
    public Path getTheCurrentPath() {
        return CurrentPath;
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
    public void pwd(){
        System.out.println(CurrentPath.toString());
    }

/////////////////////////////////////////////////////////////////

    public void cd(String [] args){
        try {
            if (args.length == 0) {
                CurrentPath=FileSystems.getDefault().getPath("C:\\Users\\Mohamed reyad");
                System.out.println("Current Directory: " + CurrentPath.toString());
            }
            else if (args.length == 1) {
                if (args[0].equals("..")) {  // second check to change the current directory to the previous directory.
                    if (CurrentPath.getParent() != null) {  //first check if there is a parent directory
                        previousPath = CurrentPath;
                        CurrentPath = CurrentPath.getParent();
                    }
                    else {
                        System.out.println("Already at the root directory.");
                    }

                }

                else {                          // third check to change the current path to that path
                    previousPath = CurrentPath;
                    CurrentPath = FileSystems.getDefault().getPath(args[0]);
                }
                System.out.println("Current Directory: " + CurrentPath.toString());
            }
        }
        catch (InvalidPathException e) {
            System.out.println("Invalid path: " + e.getMessage());
        }
        catch (Exception e ){
            System.out.println("an error occurred " +e.getMessage());
        }

    }


///////////////////////////////////////////

    public void ls(String [] args){

    }

    //////////////////////////////////////////

    public void mkdir(String [] args){

    }

    //////////////////////////////////////////

    public void rmdir(String [] args){

    }

    /////////////////////////////////////////

    public void touch(String [] args){

    }

    /////////////////////////////////////////

    public void rm(String FileName){

    }

    ////////////////////////////////////////



    public void cat(String [] args){

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
                cd(args);
                break;
            case "ls" :
//             System.out.println(ls(args));
                break;

            case "mkdir" :
//                mkdir(args);
                break;
            case "rmdir" :
//                rmdir(args);
                break;
            case "touch":
//                touch(args);
                break;
            case "help":
                help();
                break;
            case "cat":
//                cat(args);
                break;
            case "rm":
//                rm(args[0]);
                break;
            case "clear" :
//                clear();
                break;
        }
    }




}
