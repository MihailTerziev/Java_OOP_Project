package Java_OOP_Project.StarWars;

import java.util.Objects;

public class Jedi {
    private final String name;
    private String rank;
    private int age;
    private String saberColor;
    private double force;

    public Jedi(String name, String rank, int age, String saberColor, double force) {
        this.name = name;
        this.rank = rank;
        this.age = age;
        this.saberColor = saberColor;
        this.force = force;
    }

    public String getName() {
        return name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSaberColor() {
        return saberColor;
    }

    public void setSaberColor(String saberColor) {
        this.saberColor = saberColor;
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jedi jedi = (Jedi) o;
        return age == jedi.age && Double.compare(jedi.force, force) == 0 &&
                Objects.equals(name, jedi.name) && Objects.equals(rank, jedi.rank) &&
                Objects.equals(saberColor, jedi.saberColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rank, age, saberColor, force);
    }

    @Override
    public String toString() {
        return "Jedi " + name + '\'' +
                ", rank: " + rank + '\'' +
                ", age: " + age +
                ", Saber color: " + saberColor + '\'' +
                ", Force power: " + force;
    }
}
