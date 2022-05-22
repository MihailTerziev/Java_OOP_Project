package Java_OOP_Project.StarWars;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManipulator {
    private String currFileName;
    private static String path = "D:\\tu-varna\\Семестър_IV\\ProjectOOP1\\src\\Java_OOP_Project\\StarWars\\Files\\";

    public FileManipulator() {}

    public void setCurrFileName(String currFileName) {
        this.currFileName = currFileName;
    }

    public void setPath(String path) {
        FileManipulator.path = path;
    }

    public Controller open(String fileName) throws IOException, ClassNotFoundException {
        if (!Files.exists(Path.of(path + fileName))) {
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
        new FileReader(path + this.currFileName).close();
        return "Successfully closed " + this.currFileName;
    }

    public String save(Controller con) throws IOException {
        FileOutputStream outFile = new FileOutputStream(path + this.currFileName);
        ObjectOutputStream outObj = new ObjectOutputStream(outFile);

        outObj.writeObject(con);
        outObj.close();
        outFile.close();

        return "Successfully saved " + this.currFileName;
    }

    public String saveAs(String newPath, String fileName, Controller con) throws IOException {
        if (this.currFileName == null) {
            this.currFileName = fileName;
        }

        String currFileNameCopy = this.currFileName;
        String pathCopy = path;

        setCurrFileName(fileName);
        setPath(newPath + '\\');
        save(con);

        setCurrFileName(currFileNameCopy);
        setPath(pathCopy);

        return "Successfully saved another " + currFileNameCopy;
    }
}
