package Java_OOP_Project.StarWars;

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
}
