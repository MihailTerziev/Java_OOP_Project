package Java_OOP_Project.StarWars.Controllers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManipulator {
    private static String path;
    private String currFileName;
    private boolean fileAvailable; // this will control access to the operations

    public FileManipulator() {
        path = "D:\\tu-varna\\Семестър_IV\\ProjectOOP1\\src\\Java_OOP_Project\\StarWars\\Files\\";
        this.fileAvailable = false;
    }

    public void setCurrFileName(String currFileName) {
        this.currFileName = currFileName;
    }

    public void setPath(String path) {
        FileManipulator.path = path;
    }

    public Controller open(String fileName) throws IOException, ClassNotFoundException {
        this.fileAvailable = true; // there is opened file, so now it can be closed, saved, saved as ...

        if (!Files.exists(Paths.get(path + fileName))) {
            new FileWriter(path + fileName).close();
        }

        BufferedReader br = new BufferedReader(new FileReader(path + fileName));
        Controller controller;
        this.currFileName = fileName;

        if (!(br.readLine() == null)) {
            FileInputStream inFile = new FileInputStream(path + fileName);
            ObjectInputStream inObj = new ObjectInputStream(inFile);

            controller = (Controller) inObj.readObject();
            inFile.close();
            inObj.close();
        }
        else {
            controller = new Controller();
        }

        return controller;
    }

    public String close() throws IOException {
        if (!this.fileAvailable) {   // we can't close a file if it is not opened first
            return "No opened file!";
        }

        this.fileAvailable = false;  // if there is opened file it will be closed, and we can't use the other methods again
        new FileReader(path + this.currFileName).close();
        return "Successfully closed " + this.currFileName;
    }

    public String save(Controller con) throws IOException {
        if (!this.fileAvailable) {  // we can't save changes if file is not opened
            return "Please open a file to use this operation!";
        }

        FileOutputStream outFile = new FileOutputStream(path + this.currFileName);
        ObjectOutputStream outObj = new ObjectOutputStream(outFile);

        outObj.writeObject(con);
        outObj.close();
        outFile.close();

        return "Successfully saved " + this.currFileName;
    }

    public String saveAs(String newPath, String fileName, Controller con) throws IOException {
        if (!this.fileAvailable) {  // can't copy file on another location if not opened
            return "Please open a file and make changes to use this operation!";
        }

        String currFileNameCopy = this.currFileName;  // make copies to set original path back
        String pathCopy = path;

        setCurrFileName(fileName);  // set new path and file name
        setPath(newPath + '\\');
        save(con);                  // save copy of the file at other location

        setCurrFileName(currFileNameCopy);  // set back to original path and name
        setPath(pathCopy);                  // this will prevent saving files to unknown location by mistake

        return "Successfully saved another " + currFileNameCopy;
    }
}
