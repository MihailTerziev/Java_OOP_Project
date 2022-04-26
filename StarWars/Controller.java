package Java_OOP_Project.StarWars;

import java.util.*;

public class Controller {
    private Set<Planet> planets;
    private List<Jedi> jedies;

    public Controller() {
        this.planets = new HashSet<>();
        this.jedies = new ArrayList<>();
    }

    public void add_planet(String planetName) {
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
}
