package Java_OOP_Project.StarWars.Controllers;

import Java_OOP_Project.StarWars.Space.InvalidDataException;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public void startMenu() throws InvalidDataException, IOException, ClassNotFoundException {
        Controller con = new Controller();
        FileManipulator operation = new FileManipulator();

        System.out.print("\nYou are granted access, Welcome!\n\nStar_Wars > ");
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        while (true) {
            String[] argsArr = line.split(" ");
            String command = argsArr[0];

            if (argsArr.length == 3 && argsArr[1].equals("+")) {
                System.out.println(con.add(argsArr[0], argsArr[2]));
            }
            else {
                switch (command) {
                    case "open":
                        con = operation.open(argsArr[1]);
                        System.out.println("Successfully opened " + argsArr[1]);
                        break;

                    case "close":
                        System.out.println(operation.close());
                        break;

                    case "save":
                        System.out.println(operation.save(con));
                        break;

                    case "saveas":
                        System.out.println(operation.saveAs(argsArr[1], argsArr[2], con));
                        break;

                    case "help":
                        System.out.println(
                                "\nThe following commands are supported:\n" +
                                "open <file>                                   opens <file>\n" +
                                "close                                         closes currently opened file\n" +
                                "save                                          saves the currently open file\n" +
                                "saveas <file>                                 saves the currently open file in <file>\n" +
                                "help                                          prints this information\n" +
                                "exit                                          exists the program\n" +
                                "add_planet <name>                             creates planet\n" +
                                "add_moon <name> <planet>                      creates moon and adds it to planet\n" +
                                "create_jedi <planet/moon> <name> <rank> <age> <saber_color> <force>    creates jedi\n" +
                                "remove_jedi <name> <planet/moon>              removes jedi from planet or moon\n" +
                                "promote_jedi <name> <multiplier>              gives upper rank and increases strength of jedi\n" +
                                "demote_jedi <name> <multiplier>               gives lower rank and decreases strength of jedi\n" +
                                "get_strongest_jedi <planet>                   gives info about strongest jedi on a planet\n" +
                                "get_youngest_jedi <planet> <rank>             gives info about youngest jedi by certain rank on a planet\n" +
                                "get_most_used_saber_color <planet> <rank>     prints most used color of certain rank jedies on a planet\n" +
                                "get_most_used_saber_color <planet>            prints most used color of GRAND_MASTER rank jedies on a planet\n" +
                                "print <name>                                  prints info about the name, no matter if it's jedi or planet/moon\n" +
                                "<planet> + <planet>                           prints combined info about two planets\n"
                        );
                        break;

                    case "exit":
                        System.out.println("Exiting the program...");
                        System.exit(0);
                        break;

                    case "add_planet":
                        System.out.println(con.addPlanet(argsArr[1]));
                        break;

                    case "add_moon":
                        System.out.println(con.addMoon(argsArr[1], argsArr[2]));
                        break;

                    case "create_jedi":
                        String planetName = argsArr[1];
                        String jediName = argsArr[2];
                        String jediRank = argsArr[3];
                        int jediAge = Integer.parseInt(argsArr[4]);
                        String saberColor = argsArr[5];
                        double jediStrength = Double.parseDouble(argsArr[6]);

                        System.out.println(con.createJedi(planetName, jediName,
                                jediRank, jediAge, saberColor, jediStrength));
                        break;

                    case "remove_jedi":
                        System.out.println(con.removeJedi(argsArr[1], argsArr[2]));
                        break;

                    case "promote_jedi":
                        jediName = argsArr[1];
                        double multiplier = Double.parseDouble(argsArr[2]);

                        System.out.println(con.promoteJedi(jediName, multiplier));
                        break;

                    case "demote_jedi":
                        jediName = argsArr[1];
                        multiplier = Double.parseDouble(argsArr[2]);

                        System.out.println(con.demoteJedi(jediName, multiplier));
                        break;

                    case "get_strongest_jedi":
                        System.out.println(con.getStrongestJedi(argsArr[1]));
                        break;

                    case "get_youngest_jedi":
                        System.out.println(con.getYoungestJedi(argsArr[1], argsArr[2]));
                        break;

                    case "get_most_used_saber_color":
                        if (argsArr.length == 2) {
                            System.out.println(con.getMostUsedSaberColor(argsArr[1]));
                        }
                        else {
                            System.out.println(con.getMostUsedSaberColor(argsArr[1], argsArr[2]));
                        }
                        break;

                    case "print":
                        System.out.println(con.print(argsArr[1]));
                        break;

                    default:
                        System.out.println("Incorrect command! Try again!");
                }
            }

            System.out.print("\nStar_Wars > ");
            line = input.nextLine();
        }
    }
}
