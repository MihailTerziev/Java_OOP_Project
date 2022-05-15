package Java_OOP_Project.StarWars;

import java.io.File;

public class FileManipulator {
    File file;

    public FileManipulator() {}

    public String open(String path) {
        return "Successfully opened ";
    }

    public String close(String path) {
        return "Successfully closed ";
    }

    public String save(String path) {
        return "Successfully saved ";
    }

    public String saveAs(String path) {
        return "Successfully saved another";
    }

    public String help() {
        return """
                The following commands are supported:
                open <file>     opens <file>
                close           closes currently opened file
                save            saves the currently open file
                saveas <file>   saves the currently open file in <file>
                help            prints this information
                exit            exists the program""";
    }
}
