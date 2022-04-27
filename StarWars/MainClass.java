package Java_OOP_Project.StarWars;

public class MainClass {
    public static void main(String[] args) {
        Controller con = new Controller();

        System.out.println(con.create_jedi("Duran", "Obi-wan", "MASTER", 38, "blue", 120));
        System.out.println(con.create_jedi("Patos", "Obi-wan", "MASTER", 38, "blue", 120));
        System.out.println(con.create_jedi("Duran", "Luke", "PADAWAN", 18, "blue", 40));
        System.out.println(con.removeJedi("Obi-wan", "Duran"));
        System.out.println(con.removeJedi("Obi-wan", "Duran"));
        System.out.println(con.removeJedi("Luke", "Patos"));
    }
}
