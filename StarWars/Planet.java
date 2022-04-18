package Java_OOP_Project.StarWars;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private final String name;
    private List<Jedi> jedies;

    public Planet(String name) {
        this.name = name;
        this.jedies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Jedi> getJedies() {
        return jedies;
    }

    public void addJedi(Jedi jedi) {
        jedies.add(jedi);
    }

    public void removeJedi(Jedi jedi) {
        jedies.remove(jedi);
    }
}
