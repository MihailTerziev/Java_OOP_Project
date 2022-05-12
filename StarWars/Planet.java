package Java_OOP_Project.StarWars;

import java.util.ArrayList;
import java.util.List;

public class Planet extends SpaceObject {
    private List<Moon> moons;

    public Planet(String name) {
        super(name);
        this.moons = new ArrayList<>();
    }

    public List<Moon> getMoons() {
        return moons;
    }

    public void addMoon(Moon moon) {
        moons.add(moon);
    }

    public void removeMoon(Moon moon) {
        moons.remove(moon);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Planet: " + super.getName() + "\nJedies:\n" + super.toString());
        output.append('\n').append("Moons of ").append(super.getName()).append(":\n");

        if (moons.isEmpty()) {
            output.append("none\n");
        }
        else {
            for (Moon m: moons) {
                output.append(m).append('\n');
            }
        }

        return output.toString();
    }
}
