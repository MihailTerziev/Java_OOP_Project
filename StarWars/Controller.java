package Java_OOP_Project.StarWars;

import java.io.Serializable;
import java.util.*;

public class Controller implements Serializable {
    private List<Planet> planets;
    private List<Jedi> jedies;

    public Controller() {
        this.planets = new ArrayList<>();
        this.jedies = new ArrayList<>();
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public List<Jedi> getJedies() {
        return jedies;
    }

    public void setJedies(List<Jedi> jedies) {
        this.jedies = jedies;
    }

    public String addPlanet(String planetName) {
        for (Planet p: planets) {
            if (p.getName().equals(planetName)) {   // check if planet exists
                return "Planet " + planetName + " is already added!";
            }
        }

        planets.add(new Planet(planetName));   // add new planet
        return "Added planet " + planetName + '.';
    }

    public String createJedi(String planetName, String jediName, String jediRank,
                              int jediAge, String saberColor, double jediStrength) throws InvalidDataException {
        for (Jedi j: jedies) {
            if (j.getName().equals(jediName)) {   // check if jedi exists
                return "Jedi " + jediName + " already exists.";
            }
        }

        for (Planet p: planets) {
            if (p.getName().equals(planetName)) {

                for (Jedi j: p.getJedies()) {
                    if (j.getName().equals(jediName)) {   // check if jedi is already on planet
                        return "Jedi " + jediName + " is already on planet " + planetName + ".";
                    }
                }

                Jedi jedi = new Jedi(jediName, jediRank, jediAge, saberColor, jediStrength);
                jedi.setLocation(p.getName());  // set location
                p.addJedi(jedi);              // create and add jedi
                jedies.add(jedi);
                return "Jedi " + jediName + " has landed on planet " + planetName + ".";
            }
        }

        return "No such planet as " + planetName + '.';  // if nothing is returned above, there is no such planet
    }

    public String removeJedi(String jediName, String planetName) {
        boolean planetExists = false;
        boolean jediExists = false;

        for (Planet p: planets) {
            if (p.getName().equals(planetName)) {
                planetExists = true;

                for (Jedi j: p.getJedies()) {
                    if (j.getName().equals(jediName)) {
                        jediExists = true;
                        p.removeJedi(j);    // remove jedi if he/she is on the planet
                        break;
                    }
                }
            }

            if (planetExists) {
                if (jediExists) {
                    return  "Jedi " + jediName + " has left planet " + planetName + ".";
                }
                else {
                    return "No such jedi as " + jediName + " on planet " + planetName + ".";
                }
            }
        }

        return "No such planet as " + planetName + '.';
    }

    private String getUpperRank(String currentRank) {
        for (int i = 0; i < JediRanks.values().length; ++i) {
            if (JediRanks.values()[i].toString().equalsIgnoreCase(currentRank)) {
                return JediRanks.values()[i+1].toString();  // find and return next rank
            }
        }
        return "";
    }

    public String promoteJedi(String jediName, double multiplier) throws InvalidDataException {
        if (multiplier <= 0) {
            throw new InvalidDataException("Multiplier must be positive!");
        }

        String newRank = null;
        double newForce = 0;

        for (Jedi j: jedies) {
            if (j.getName().equals(jediName)) {
                if (j.getRank().equalsIgnoreCase("GRAND_MASTER")) {
                    return "Jedi " + jediName + " is highest rank. Can't be promoted.";
                }

                newRank = getUpperRank(j.getRank());
                if (!newRank.isEmpty()) {
                    j.setRank(newRank);
                }

                newForce = j.getForce() + (j.getForce() * multiplier);
                j.setForce(newForce);

                break;  // after jedi is promoted, don't check the rest jedies
            }
        }

        return "Jedi " + jediName + " has been promoted to " + newRank + ". His/her force is now " + newForce + ".";
    }

    private String getLowerRank(String currentRank) {
        for (int i = 0; i < JediRanks.values().length; ++i) {
            if (JediRanks.values()[i].toString().equalsIgnoreCase(currentRank)) {
                return JediRanks.values()[i - 1].toString();  // find and return lower rank
            }
        }
        return "";
    }

    public String demoteJedi(String jediName, double multiplier) throws InvalidDataException {
        if (multiplier <= 0) {
            throw new InvalidDataException("Multiplier must be positive!");
        }

        String newRank = null;
        double newForce = 0;

        for (Jedi j: jedies) {
            if (j.getName().equals(jediName)) {
                if (j.getRank().equalsIgnoreCase("YOUNGLING")) {
                    return "Jedi " + jediName + " is lowes rank. Can't be demoted.";
                }

                newRank = getLowerRank(j.getRank());
                if (!newRank.isEmpty()) {
                    j.setRank(newRank);
                }

                newForce = j.getForce() - (j.getForce() * multiplier);
                j.setForce(newForce);

                break;  // after jedi is demoted, don't check the rest jedies
            }
        }

        return "Jedi " + jediName + " has been demoted to " + newRank + ". His/her force is now " + newForce + ".";
    }

    public String getStrongestJedi(String planetName) {
        Jedi strongestJedi = null;

        for (Planet p: planets) {
            if (p.getName().equals(planetName)) {
                double maxForce = 0;

                for (Jedi j: p.getJedies()) {
                    if (j.getForce() > maxForce) {
                        maxForce = j.getForce();
                        strongestJedi = j;
                    }
                }
            }
        }

        if (strongestJedi != null) {   // the planet may not have jedies, so we check for any jedi
            return "Strongest jedi: " + strongestJedi.toString();
        }

        return "Planet " + planetName + " has no jedies.";
    }

    public String getYoungestJedi(String planetName, String jediRank) {
        Map<Jedi, Integer> jedies = new HashMap<>();

        for (Planet p: planets) {
            if (p.getName().equals(planetName)) {

                for (Jedi j: p.getJedies()) {
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

        for (Planet p: planets) {   // count saber colors
            if (p.getName().equals(planetName)) {
                planetExists = true;

                for (Jedi j: p.getJedies()) {
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
            return "There are no " + jediRank + " jedies on planet" + planetName + ".";
        }

        List<Map.Entry<String, Integer>> colorsList = new ArrayList<>(colors.entrySet());
        colorsList.sort(Map.Entry.comparingByValue());  // convert map to list and sort

        return colorsList.get(colorsList.size()-1).getKey();  // return most used saber color
    }

    public String getMostUsedSaberColor(String planetName) {
        return getMostUsedSaberColor(planetName, "GRAND_MASTER");
    }

    public String print(String name) {
        String output = null;
        boolean flag = false;

        for (Jedi j: jedies) {
            if (j.getName().equals(name)) {
                output = j.toString();
                flag = true;
                break;
            }
        }

        if (!flag) {
            for (Planet p: planets) {
                if (p.getName().equals(name)) {
                    output = p.toString();
                    break;
                }
            }
        }

        return output;
    }

    private String sortAlpha(List<Jedi> jedies) {
        StringBuilder output = new StringBuilder();
        jedies.sort(Comparator.comparing(Jedi::getName));

        for (Jedi j: jedies) {
            output.append(j).append('\n');
        }

        return output.toString();
    }

    public String add(String planetOne, String planetTwo) {
        StringBuilder output = new StringBuilder();

        for (Planet p: planets) {
            if (p.getName().equals(planetOne)) {
                output.append("Planet: ").append(p.getName()).append("\nJedies:\n").append(sortAlpha(p.getJedies()));
            }
            if (p.getName().equals(planetTwo)) {
                output.append("Planet: ").append(p.getName()).append("\nJedies:\n").append(sortAlpha(p.getJedies()));
            }
        }

        return output.toString();
    }
}
