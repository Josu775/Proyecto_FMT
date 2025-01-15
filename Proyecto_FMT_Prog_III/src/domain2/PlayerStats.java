package domain2;

public class PlayerStats {
    private int matchesPlayed;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;

    public PlayerStats() {
        this.matchesPlayed = 0;
        this.goals = 0;
        this.assists = 0;
        this.yellowCards = 0;
        this.redCards = 0;
    }

    public void recordMatch(int goals, int assists, int yellowCards, int redCards) {
        this.matchesPlayed++;
        this.goals += goals;
        this.assists += assists;
        this.yellowCards += yellowCards;
        this.redCards += redCards;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "matchesPlayed=" + matchesPlayed +
                ", goals=" + goals +
                ", assists=" + assists +
                ", yellowCards=" + yellowCards +
                ", redCards=" + redCards +
                '}';
    }
}
