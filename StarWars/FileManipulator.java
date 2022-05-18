package Java_OOP_Project.StarWars;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FileManipulator {
    private String currFileName;
    private String path;
    private Document doc;

    public FileManipulator() {
        this.path = "D:/tu-varna/Семестър IV/ProjectOOP1/src/Java_OOP_Project/StarWars/Files/";
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String open(String fileName) throws IOException, SAXException, ParserConfigurationException {
        if (!Files.exists(Path.of(this.path + fileName))) {
            return "Can't find " + fileName;
        }

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = builder.parse(String.valueOf(new File(this.path + fileName).toURI()));
        doc.getDocumentElement().normalize();

        this.currFileName = fileName;

        return "Successfully opened " + fileName;
    }

    public String close() throws IOException {
        new FileReader(this.path + this.currFileName).close();
        return "Successfully closed " + this.currFileName;
    }

    public String save() {
        return "Successfully saved " + this.currFileName;
    }

    public String saveAs(String newPath) throws IOException {
        Path source = Paths.get(this.path + this.currFileName);
        Path destination = Paths.get(newPath + '/');
        Files.copy(source, destination);

        return "Successfully saved another " + this.currFileName;
    }
}
