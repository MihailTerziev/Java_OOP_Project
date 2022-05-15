package Java_OOP_Project.StarWars;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws InvalidDataException  {
        Controller con = new Controller();
        FileManipulator operation = new FileManipulator();

        System.out.print("Star Wars > ");
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        while (true) {
            String[] argsArr = line.split(" ");
            String command = argsArr[0];

            switch (command) {
                case "open":
                    System.out.println(operation.open(argsArr[1]));
                    break;

                case "close":
                    System.out.println(operation.close(argsArr[1]));
                    break;

                case "save":
                    System.out.println(operation.save(argsArr[1]));
                    break;

                case "saveas":
                    System.out.println(operation.saveAs(argsArr[1]));
                    break;

                case "help":
                    System.out.println(operation.help());
                    break;

                case "exit":
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;

                case "add_planet":
                    System.out.println(con.addPlanet(argsArr[1]));
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
                    if (!argsArr[1].equals("+")) {
                        System.out.println("Incorrect command! Try again!");
                    }
            }

            if (argsArr.length == 3 && argsArr[1].equals("+")) {
                System.out.println(con.add(argsArr[0], argsArr[2]));
            }

            System.out.print("\nStar Wars > ");
            line = input.nextLine();
        }
    }
}
