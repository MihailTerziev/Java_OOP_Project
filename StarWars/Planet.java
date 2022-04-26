package Java_OOP_Project.StarWars;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(name, planet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Planet: " + name + '\n');

        output.append("Jedies:\n");
        for (Jedi j: jedies) {
            output.append(j.toString()).append('\n');
        }

        output.append('\n').append('\n');
        return output.toString();
    }
}
