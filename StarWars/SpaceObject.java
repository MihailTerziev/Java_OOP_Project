package Java_OOP_Project.StarWars;

import java.io.Serializable;
import java.util.*;

public abstract class SpaceObject implements Serializable {
    private String name;
    private final List<Jedi> jedies;

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

    protected void addJedi(Jedi jedi) {
        jedies.add(jedi);
    }

    protected void removeJedi(Jedi jedi) {
        jedies.remove(jedi);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        Map<String, String> jediesInfo = new HashMap<>();

        for (Jedi j: this.jedies) {   // if jedi has the wanted rank, count the saber color
            jediesInfo.put(j.getRank(), j.getName());
        }

        if (jediesInfo.isEmpty()) {   // check if there are jedies
            output.append("none\n");
        }
        else {
            List<Map.Entry<String, String>> jediesInfoList = new ArrayList<>(jediesInfo.entrySet());
            jediesInfoList.sort(Map.Entry.comparingByValue());

            for (Jedi j : this.jedies) {
                for (Map.Entry<String, String> var : jediesInfoList) {
                    if (var.getValue().equals(j.getName())) {
                        output.append(j).append('\n');
                        break;
                    }
                }
            }
        }

        output.deleteCharAt(output.length()-1);
        return output.toString();
    }
}
