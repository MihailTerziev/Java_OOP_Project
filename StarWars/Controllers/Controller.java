package Java_OOP_Project.StarWars.Controllers;

import Java_OOP_Project.StarWars.JediInfo.Jedi;
import Java_OOP_Project.StarWars.JediInfo.JediRanks;
import Java_OOP_Project.StarWars.Space.InvalidDataException;
import Java_OOP_Project.StarWars.Space.Moon;
import Java_OOP_Project.StarWars.Space.Planet;
import Java_OOP_Project.StarWars.Space.SpaceObject;

import java.io.Serializable;
import java.util.*;

public class Controller implements Serializable {
    private final List<SpaceObject> spaceObjects;
    private final List<Jedi> jedies;

    public Controller() {
        this.spaceObjects = new ArrayList<>();
        this.jedies = new ArrayList<>();
    }

    public String addPlanet(String planetName) throws InvalidDataException {
        for (SpaceObject sp: spaceObjects) {
            if (sp instanceof Planet && sp.getName().equals(planetName)) {   // check if planet exists
                return "Planet " + planetName + " is already added!";
            }
        }

        spaceObjects.add(new Planet(planetName));   // add new planet
        return "Added planet " + planetName + '.';
    }

    public String addMoon(String moonName, String planetName) throws InvalidDataException {
        for (SpaceObject sp: spaceObjects) {
            if (sp instanceof Planet && sp.getName().equals(planetName)) {   // check if planet exists
                Moon moon = new Moon(moonName);
                ((Planet) sp).addMoon(moon);          // add moon to planet's system
                moon.setPlanet(sp.getName());
                spaceObjects.add(moon);
                return "Discovered new moon " + moonName + " orbiting planet " + planetName + '.';
            }
        }

        return "No such planet " + planetName + '.';
    }

    private String addJediToLocation(SpaceObject spaceObject, Jedi jedi) throws InvalidDataException {
        for (Jedi j: spaceObject.getJedies()) {
            if (j.getName().equals(jedi.getName())) {   // check if jedi is already on planet
                return "Jedi " + jedi.getName() + " is already on planet " + spaceObject.getName() + ".";
            }
        }

        jedi.setLocation(spaceObject.getName());  // set location
        spaceObject.addJedi(jedi);                // create and add jedi
        jedies.add(jedi);
        return "Jedi " + jedi.getName() + " landed on " + spaceObject.getName() + '.';
    }

    public String createJedi(String spaceObjName, String jediName, String jediRank,
                              int jediAge, String saberColor, double jediStrength) throws InvalidDataException {
        for (Jedi j: jedies) {
            if (j.getName().equalsIgnoreCase(jediName)) {   // check if jedi exists
                return "Jedi " + jediName + " already exists.";
            }
        }

        Jedi jedi = new Jedi(jediName, jediRank, jediAge, saberColor, jediStrength);

        for (SpaceObject sp: spaceObjects) {
            if (sp.getName().equalsIgnoreCase(spaceObjName)) {
                return addJediToLocation(sp, jedi);
            }
        }

        return "No such planet or moon as " + spaceObjName + '.';  // if nothing is returned above, there is no such planet
    }

    public String removeJedi(String jediName, String spaceObjName) {
        boolean spaceObjExists = false;
        boolean jediExists = false;

        for (SpaceObject sp: spaceObjects) {
            if (sp.getName().equalsIgnoreCase(spaceObjName)) {
                spaceObjExists = true;

                for (Jedi j: sp.getJedies()) {
                    if (j.getName().equalsIgnoreCase(jediName)) {
                        jediExists = true;
                        sp.removeJedi(j);    // remove jedi if he/she is on the planet
                        break;
                    }
                }
            }

            if (spaceObjExists) {    // check if planet exists, then check if the jedi is on it
                if (jediExists) {
                    return  "Jedi " + jediName + " has left " + spaceObjName + ".";
                }
                else {
                    return "No such jedi as " + jediName + " on " + spaceObjName + ".";
                }
            }
        }

        return "No such planet or moon as " + spaceObjName + '.';
    }

    private String getUpperRank(String currentRank) {
        for (int i = 0; i < JediRanks.values().length; ++i) {
            if (JediRanks.values()[i].toString().equals(currentRank)) {
                return JediRanks.values()[i+1].toString();  // find and return next rank
            }
        }
        return "";
    }

    public String promoteJedi(String jediName, double multiplier) throws InvalidDataException {
        if (multiplier <= 0) {
            return "Multiplier must be positive!";
        }

        String newRank;  // these values will hold the new information after the promotion
        double newForce = 0;

        for (Jedi j: jedies) {
            if (j.getName().equalsIgnoreCase(jediName)) {

                // jedi can't be the highest rank and get promoted
                if (j.getRank().equalsIgnoreCase("GRAND_MASTER")) {
                    return "Jedi " + jediName + " is highest rank. Can't be promoted.";
                }

                newRank = getUpperRank(j.getRank());  // get higher rank and promote the jedi
                if (!newRank.isEmpty()) {
                    j.setRank(newRank);
                }

                newForce = j.getForce() + (j.getForce() * multiplier);  // increase force
                newForce = Math.round(newForce * 100.00) / 100.00;    // round it to 2 after decimal
                j.setForce(newForce);

                // after jedi is promoted, don't check the rest jedies
                return "Jedi " + jediName + " has been promoted to " + newRank + ". His/her force is now " + newForce;
            }
        }
        return "No jedi named " + jediName + "!";
    }

    private String getLowerRank(String currentRank) {
        for (int i = 0; i < JediRanks.values().length; ++i) {
            if (JediRanks.values()[i].toString().equals(currentRank)) {
                return JediRanks.values()[i-1].toString();  // find and return lower rank
            }
        }
        return "";
    }

    public String demoteJedi(String jediName, double multiplier) throws InvalidDataException {
        if (multiplier <= 0) {
            return "Multiplier must be positive!";
        }

        String newRank;
        double newForce = 0;

        for (Jedi j: jedies) {
            if (j.getName().equalsIgnoreCase(jediName)) {

                // if jedi is lowest rank, can't get demoted
                if (j.getRank().equalsIgnoreCase("YOUNGLING")) {
                    return "Jedi " + jediName + " is lowes rank. Can't be demoted.";
                }

                newRank = getLowerRank(j.getRank());
                if (!newRank.isEmpty()) {
                    j.setRank(newRank);
                }                                // set lower rank and decrease power

                newForce = j.getForce() - (j.getForce() * multiplier);
                newForce = Math.round(newForce * 100.00) / 100.00;
                j.setForce(newForce);

                // after jedi is demoted, don't check the rest jedies
                return "Jedi " + jediName + " has been demoted to " + newRank + ". His/her force is now " + newForce;
            }
        }
        return "No jedi named " + jediName + "!";
    }

    public String getStrongestJedi(String planetName) {
        Jedi strongestJedi = null;

        for (SpaceObject sp: spaceObjects) {
            if (sp.getName().equalsIgnoreCase(planetName)) {
                double maxForce = 0;

                for (Jedi j: sp.getJedies()) {
                    if (j.getForce() > maxForce) {
                        maxForce = j.getForce();
                        strongestJedi = j;
                    }
                }
            }
        }

        if (strongestJedi != null) {   // the planet may not have jedies, so we check for any jedi
            return "Strongest jedi: " + strongestJedi;
        }

        return "Planet " + planetName + " has no jedies.";
    }

    public String getYoungestJedi(String planetName, String jediRank) {
        Map<Jedi, Integer> jedies = new HashMap<>();

        for (SpaceObject sp: spaceObjects) {
            if (sp.getName().equalsIgnoreCase(planetName)) {

                for (Jedi j: sp.getJedies()) {
                    if (j.getRank().equalsIgnoreCase(jediRank)) {   // if jedi has the wanted rank, add him with his age
                        jedies.put(j, j.getAge());
                    }
                }

                break;   // when all jedies on the planet are checked, break from loop
            }
        }

        if (jedies.isEmpty()) {
            return "There are no jedies of that rank on planet " + planetName + ".";
        }

        List<Map.Entry<Jedi, Integer>> jediesList = new ArrayList<>(jedies.entrySet());
        jediesList.sort(Map.Entry.comparingByValue());  // convert map to list and sort

        List<Jedi> sameAgeJedies = new ArrayList<>();
        int smallestAge = jediesList.get(0).getValue();

        for(Map.Entry<Jedi, Integer> current : jediesList) {
            if(current.getValue() == smallestAge) {
                sameAgeJedies.add(current.getKey());
            }
        }

        sameAgeJedies.sort(Comparator.comparing(Jedi::getName));   // sort jedies alphabetically by name
        return "Youngest jedi: " + sameAgeJedies.get(0).toString();
    }

    public String getMostUsedSaberColor(String planetName, String jediRank) {
        boolean correctRank = false;

        for (JediRanks var: JediRanks.values()) {   // check if rank exists
            if (var.toString().equalsIgnoreCase(jediRank)) {
                correctRank = true;
                break;
            }
        }

        if (!correctRank) {   // if rank is incorrect, return
            return "Incorrect rank! " + jediRank;
        }

        Map<String, Integer> colors = new HashMap<>();
        boolean planetExists = false;

        for (SpaceObject sp: spaceObjects) {   // count saber colors
            if (sp instanceof Planet && sp.getName().equals(planetName)) {
                planetExists = true;

                for (Jedi j: sp.getJedies()) {
                    if (j.getRank().equalsIgnoreCase(jediRank)) {   // if jedi has the wanted rank, count the saber color
                        if (colors.containsKey(j.getSaberColor())) {
                            int counter = colors.get(j.getSaberColor());
                            colors.replace(j.getSaberColor(), counter, counter + 1);
                        }
                        else {
                            colors.put(j.getSaberColor(), 0);
                        }
                    }
                }

                break;   // when all jedies on the planet are checked, break from loop
            }
        }

        if (!planetExists) {
            return "No such planet as " + planetName + '.';
        }

        if (colors.isEmpty()) {
            return "There are no " + jediRank + " jedies on planet " + planetName + ".";
        }

        List<Map.Entry<String, Integer>> colorsList = new ArrayList<>(colors.entrySet());
        colorsList.sort(Map.Entry.comparingByValue());  // convert map to list and sort

        return colorsList.get(colorsList.size()-1).getKey();  // return most used saber color
    }

    public String getMostUsedSaberColor(String planetName) {
        // it is needed the strongest jedi among the strongest rank
        return getMostUsedSaberColor(planetName, "GRAND_MASTER");
    }

    public String print(String name) {
        String output = null;
        boolean flag = false;

        for (Jedi j: jedies) {  // if the parameter is jedi name, the information is printed
            if (j.getName().equalsIgnoreCase(name)) {
                output = j.toString();
                flag = true;
                break;
            }
        }

        if (!flag) {    // if it is not jedi name it should be planet name, so check and print
            for (SpaceObject sp: spaceObjects) {
                if (sp.getName().equalsIgnoreCase(name)) {
                    output = sp.toString();
                    flag = true;
                    break;
                }
            }
        }

        if (!flag) {   // finally, if the name is not of a planet or a jedi, there is no such name
            output = "There is no recorded information about " + name + ".";
        }

        return output;
    }

    private String sortAlpha(List<Jedi> jedies) {  // sort alphabetically list of jedies by their names
        StringBuilder output = new StringBuilder();
        jedies.sort(Comparator.comparing(Jedi::getName));

        for (Jedi j: jedies) {
            output.append(j).append('\n');
        }

        return output.toString();  // returns the sorted list as string
    }

    public String add(String planetOne, String planetTwo) {  // prints combined info about two planets
        StringBuilder output = new StringBuilder();          // method is called when: planet_name1 + planet_name2

        for (SpaceObject sp: spaceObjects) {
            if (sp instanceof Planet && sp.getName().equalsIgnoreCase(planetOne)) {
                output.append("\nPlanet: ").append(sp.getName()).append("\nJedies:\n").append(sortAlpha(sp.getJedies()));
            }
            if (sp instanceof Planet && sp.getName().equalsIgnoreCase(planetTwo)) {
                output.append("\nPlanet: ").append(sp.getName()).append("\nJedies:\n").append(sortAlpha(sp.getJedies()));
            }
        }

        return output.toString();
    }
}
