package Java_OOP_Project.StarWars;

import java.util.*;

public class Controller {
    private List<Planet> planets;
    private List<Jedi> jedies;

    public Controller() {
        this.planets = new ArrayList<>();
        this.jedies = new ArrayList<>();
    }

    public void add_planet(String planetName) {
        for (Planet p: planets) {
            if (p.getName().equals(planetName)) {
                return;
            }
        }

        planets.add(new Planet(planetName));
    }

    public String create_jedi(String planetName, String jediName, String jediRank,
                              int jediAge , String saberColor, double jediStrength)
    {
        for (Jedi j: jedies) {
            if (j.getName().equals(jediName)) {
                return "Jedi " + jediName + " already exists.";
            }
        }

        boolean flag = false;
        for (Planet p: planets) {
            if (p.getName().equals(planetName)) {

                for (Jedi j: p.getJedies()) {
                    if (j.getName().equals(jediName)) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    return "Jedi " + jediName + " is already on planet " + planetName + ".";
                }
                else {
                    Jedi jedi = new Jedi(jediName, jediRank, jediAge, saberColor, jediStrength);
                    p.addJedi(jedi);
                    jedies.add(jedi);
                    return "Jedi " + jediName + " landed on planet " + planetName + ".";
                }
            }
        }

        return "No such planet as " + planetName + '.';
    }

    public String removeJedi(String jediName, String planetName) {
        boolean planetExists = false;
        boolean jediExists = false;

        for (Planet p: planets) {
            if (p.getName().equals(planetName)) {
                planetExists = true;
                if (p.getJedies().removeIf(jedi -> jedi.getName().equals(jediName))) {
                    jediExists = true;
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
        else {
            return "No such planet as " + planetName + '.';
        }
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
            throw new InvalidDataException("Multiplier must be positive!");
        }

        String newRank = null;
        double newForce = 0;

        for (Jedi j: jedies) {
            if (j.getName().equals(jediName)) {
                if (!j.getRank().equals("GRAND_MASTER")) {
                    return "Jedi " + jediName + " is highest rank. Can't be promoted.";
                }
                newRank = getUpperRank(j.getRank());
                j.setRank(newRank);

                newForce = j.getForce() + (j.getForce() * multiplier);
                j.setForce(newForce);
            }

            break;  // after jedi is promoted, don't check the rest jedies
        }

        return "Jedi " + jediName + " has been promoted to " + newRank + ". His/her force is now " + newForce + ".";
    }

    public String demoteJedi(String jediName, double multiplier) {
        return "";
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

        if (strongestJedi != null) {
            return strongestJedi.toString();
        }

        return "No such planet as " + planetName + '.';
    }

    public String getYoungestJedi(String planetName, String jediRank) {
        return "";
    }

    public String getMostUsedSaberColor(String planetName, String jediRank) {
//        boolean correctRank = false;
//
//        for (JediRanks var: JediRanks.values()) {   // check if rank exists
//            if (var.toString().equals(jediRank)) {
//                correctRank = true;
//                break;
//            }
//        }
//
//        if (!correctRank) {   // if rank is incorrect, return
//            return "Incorrect rank!";
//        }

        Map<String, Integer> colors = new HashMap<>();
//        boolean planetExists = false;

        for (Planet p: planets) {   // count saber colors
            if (p.getName().equals(planetName)) {
//                planetExists = true;

                for (Jedi j: p.getJedies()) {
                    if (j.getRank().equals(jediRank)) {   // if jedi has the wanted rank, count the saber color
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

//        if (!planetExists) {
//            return "No such planet as " + planetName + '.';
//        }

        List<Map.Entry<String, Integer>> colorsList = new ArrayList<>(colors.entrySet());
        colorsList.sort(Map.Entry.comparingByValue());  // convert map to list and sort

        return colorsList.get(colorsList.size()-1).getKey();  // return most used saber color
    }

    public String getMostUsedSaberColor(String planetName) {
        Map<String, Integer> colors = new HashMap<>();

        for (Planet p: planets) {
            if (p.getName().equals(planetName)) {
                for (Jedi j: p.getJedies()) {
                    if (j.getRank().equals("GRAND_MASTER")) {
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

        List<Map.Entry<String, Integer>> colorsList = new ArrayList<>(colors.entrySet());
        colorsList.sort(Map.Entry.comparingByValue());  // convert map to list and sort

        return colorsList.get(colorsList.size()-1).getKey();  // return most used saber color
    }
}
