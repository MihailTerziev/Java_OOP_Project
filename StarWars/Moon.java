package Java_OOP_Project.StarWars;

public class Moon extends SpaceObject{
    public Moon(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Moon: " + super.getName() + "\nJedies:\n" + super.toString();
    }
}
