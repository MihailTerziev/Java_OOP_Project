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
}
