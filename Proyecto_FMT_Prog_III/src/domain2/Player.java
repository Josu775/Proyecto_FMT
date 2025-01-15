package domain2;

public class Player {
    private int id;
    private String name;
    private int age;
    private int skill;
    private double marketValue;
    private Position position; // Usando el enum Position
    private PlayerStats stats; // Estadísticas del jugador

    // Constructor principal
    public Player(int id, String name, int age, int skill, double marketValue, Position position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.skill = skill;
        this.marketValue = marketValue;
        this.position = position;
        this.stats = new PlayerStats();
    }

    // Constructor alternativo (sin ID)
    public Player(String name, int age, int skill, Position position) {
        this(0, name, age, skill, 0.0, position);
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSkill() {
        return skill;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PlayerStats getStats() {
        return stats;
    }

    // Métodos adicionales
    public void improveSkill(int amount) {
        this.skill += amount;
    }

    public void recordMatchStats(int goals, int assists, int yellowCards, int redCards) {
        stats.recordMatch(goals, assists, yellowCards, redCards);
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", Skill: " + skill + ", Position: " + position + ", Value: $" + marketValue + ")";
    }
}

