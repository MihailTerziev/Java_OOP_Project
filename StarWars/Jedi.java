package Java_OOP_Project.StarWars;

import java.io.Serializable;

public class Jedi implements Serializable {
    private String name;
    private String rank;
    private int age;
    private String saberColor;
    private double force;
    private String location;

    public Jedi(String name, String rank, int age, String saberColor, double force) throws InvalidDataException {
        setName(name);
        setRank(rank);
        setAge(age);
        setSaberColor(saberColor);
        setForce(force);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidDataException {
        if (name.isEmpty()) {
            throw new InvalidDataException("Name of jedi cannot be empty!");
        }
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) throws InvalidDataException {
        boolean correctRank = false;

        if (!rank.isEmpty()) {
            for (JediRanks var: JediRanks.values()) {
                if (var.toString().equals(rank)) {
                    this.rank = rank;
                    correctRank = true;
                }
            }
        }

        if(!correctRank) {
            throw new InvalidDataException(
                    "\nJedi rank must be one of the titles:\n" +
                    "YOUNGLING, INITIATE, PADAWAN, KNIGHT_ASPIRANT,\n" +
                    "KNIGHT, MASTER, BATTLE_MASTER и GRAND_MASTER\n"
            );
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws InvalidDataException {
        if (age < 5) {
            throw new InvalidDataException("Apprentice must be at least 5 years old to start jedi training!");
        }
        this.age = age;
    }

    public String getSaberColor() {
        return saberColor;
    }

    public void setSaberColor(String saberColor) throws InvalidDataException {
        if (!saberColor.isEmpty() && (saberColor.equalsIgnoreCase("blue") ||
                saberColor.equalsIgnoreCase("green") ||
                saberColor.equalsIgnoreCase("yellow") ||
                saberColor.equalsIgnoreCase("purple") ||
                saberColor.equalsIgnoreCase("none")))
        {
            this.saberColor = saberColor;
        }
        else {
            throw new InvalidDataException("Saber color must be accurate!");
        }
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) throws InvalidDataException {
        if (force < 0) {
            throw new InvalidDataException("Force power must be positive number!");
        }
        this.force = force;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) throws InvalidDataException {
        if (location.isEmpty()) {
            throw new InvalidDataException("Jedi must have location!");
        }
        this.location = location;
    }

    @Override
    public String toString() {
        return "Jedi " + name + ", rank: " + rank +
                ", age: " + age + ", Saber color: " + saberColor +
                ", Force power: " + force + ", Location: " + location;
    }
}
