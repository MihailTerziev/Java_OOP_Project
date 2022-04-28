package Java_OOP_Project.StarWars;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class SpaceObject {
    private String name;
    private List<Jedi> jedies;

    public SpaceObject(String name) {
        this.name = name;
        this.jedies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidDataException {
        if (name.isEmpty()) {
            throw new InvalidDataException("Name of space object cannot be empty!");
        }
        this.name = name;
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
        SpaceObject that = (SpaceObject) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
