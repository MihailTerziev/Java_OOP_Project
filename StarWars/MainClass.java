package Java_OOP_Project.StarWars;

public class MainClass {
    public static void main(String[] args) throws InvalidDataException {
        Controller con = new Controller();

        con.add_planet("tu_varna");
        con.add_planet("Duran");
        con.add_planet("Patos");

        System.out.println(con.create_jedi("Duran", "Obi-wan", "MASTER", 38, "blue", 120));
        System.out.println(con.create_jedi("Patos", "Yoda", "GRAND_MASTER", 300, "green", 250));
        System.out.println(con.create_jedi("Duran", "Luke", "PADAWAN", 18, "blue", 50));
        System.out.println(con.create_jedi("Duran", "Sasha", "YOUNGLING", 15, "blue", 20));
        System.out.println(con.create_jedi("Duran", "Name1", "PADAWAN", 17, "blue", 60.5));
        System.out.println(con.create_jedi("Duran", "Name2", "PADAWAN", 16, "blue", 34));
        System.out.println(con.create_jedi("Duran", "Name3", "KNIGHT_ASPIRANT", 20, "blue", 180));
        System.out.println(con.create_jedi("Duran", "Name4", "KNIGHT", 18, "blue", 200));
        System.out.println(con.create_jedi("Duran", "Name5", "PADAWAN", 19, "blue", 46.8));
        System.out.println(con.create_jedi("Duran", "Name6", "PADAWAN", 14, "blue", 57.9));

        System.out.println(con.getMostUsedSaberColor("Duran"));
        System.out.println(con.getMostUsedSaberColor("Duran", "PADAWAN"));
        System.out.println(con.getMostUsedSaberColor("Duran", "dsfs"));

        System.out.println(con.getYoungestJedi("Duran", "PADAWAN"));
        System.out.println(con.getStrongestJedi("Duran"));
        System.out.println(con.getStrongestJedi("Patos"));

        System.out.println(con.promoteJedi("Name4", 1.25));
        System.out.println(con.promoteJedi("Yoda", 1.20));
        System.out.println(con.demoteJedi("Sasha", 1.25));
        System.out.println(con.promoteJedi("Sasha", 2));
        System.out.println(con.demoteJedi("Obi-wan", 0.2));

        System.out.println(con.removeJedi("Name5", "tu_varna"));
        System.out.println(con.removeJedi("Name5", "Duran"));
    }
}
