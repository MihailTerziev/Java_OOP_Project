package Java_OOP_Project.StarWars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        StringBuilder output = new StringBuilder("Planet: " + getName() + '\n' + "Jedies:\n");

        Map<String, String> jediesInfo = new HashMap<>();

        for (Jedi j: getJedies()) {   // if jedi has the wanted rank, count the saber color
            jediesInfo.put(j.getRank(), j.getName());
        }

        List<Map.Entry<String, String>> jediesInfoList = new ArrayList<>(jediesInfo.entrySet());
        jediesInfoList.sort(Map.Entry.comparingByKey());
        jediesInfoList.sort(Map.Entry.comparingByValue());

        for (Jedi j: getJedies()) {
            for (Map.Entry<String, String> var: jediesInfoList) {
                if (var.getValue().equals(j.getName())) {
                    output.append(j.toString()).append('\n');
                    break;
                }
            }
        }

        return output.toString();
    }
}
