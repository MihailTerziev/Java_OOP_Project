package Java_OOP_Project.StarWars.Space;

public class Moon extends SpaceObject {
    private String planet;  // orbiting around a planet

    public Moon(String name) throws InvalidDataException {
        super(name);
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    @Override
    public String toString() {
        return "Moon: " + super.getName() + "\nIn orbit: " + planet +"\nJedies:\n" + super.toString();
    }
}
