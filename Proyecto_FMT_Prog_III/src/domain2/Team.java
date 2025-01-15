package domain2;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int id;
    private String name;
    private double budget;
    private List<Player> players;
    private TeamStats stats;

    public Team(int id, String name, double budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.players = new ArrayList<>();
        this.stats = new TeamStats();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public TeamStats getStats() {
        return stats;
    }

    public void addPlayer(Player player) {
        if (player.getMarketValue() <= budget) {
            players.add(player);
            budget -= player.getMarketValue();
        } else {
            System.out.println("No hay suficiente presupuesto para fichar a " + player.getName());
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
        budget += player.getMarketValue();
    }

    public void trainPlayers() {
        for (Player player : players) {
            player.improveSkill((int) (Math.random() * 5));
        }
    }

    @Override
    public String toString() {
        return name + " (Budget: $" + budget + ", Players: " + players.size() + ")";
    }

	public void train(Training training) {
		// TODO Auto-generated method stub
		
	}
}

