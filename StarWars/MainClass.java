package Java_OOP_Project.StarWars;

import Java_OOP_Project.StarWars.Controllers.Menu;
import Java_OOP_Project.StarWars.Space.InvalidDataException;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws InvalidDataException, IOException, ClassNotFoundException {
        new Menu().startMenu();
    }
}
