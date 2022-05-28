package Java_OOP_Project.StarWars;

import java.io.Serializable;
import java.util.*;

public abstract class SpaceObject implements Serializable {
    private String name;
    private final List<Jedi> jedies;

    public SpaceObject(String name) throws InvalidDataException {
        setName(name);
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
        Map<String, Integer> jediesInfo = new HashMap<>();
        Map<String, Integer> jediesRanks = new HashMap<>();

        // fill map with ranks with id from 0 to 7
        for (int i = 0; i < JediRanks.values().length; ++i) {
            jediesRanks.put(JediRanks.values()[i].toString(), i);
        }

        for (Jedi j: this.jedies) {   // put in this map the equivalent of the rank id from map jediesRanks
            jediesInfo.put(j.getName(), jediesRanks.get(j.getRank()));
        }

        if (jediesInfo.isEmpty()) {   // check if there are jedies
            output.append("none\n");
        }
        else {
            List<Map.Entry<String, Integer>> jediesInfoList = new ArrayList<>(jediesInfo.entrySet());

            jediesInfoList.sort(Map.Entry.comparingByValue());  // sort by rank id then
            jediesInfoList.sort(Map.Entry.comparingByKey());    // sort by name

            for (Jedi j : this.jedies) {
                for (Map.Entry<String, Integer> var : jediesInfoList) {
                    if (var.getKey().equals(j.getName())) {
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
