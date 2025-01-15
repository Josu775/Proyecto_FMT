package domain2;

import java.util.HashMap;
import java.util.Map;

public class TeamStats {
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int losses;
    private int goalsScored;
    private int goalsConceded;

    public TeamStats() {
        this.matchesPlayed = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.goalsScored = 0;
        this.goalsConceded = 0;
    }

    /**
     * Registra los resultados de un partido en las estadísticas del equipo.
     *
     * @param goalsScored  Goles anotados por el equipo.
     * @param goalsConceded Goles recibidos por el equipo.
     */
    public void recordMatch(int goalsScored, int goalsConceded) {
        this.matchesPlayed++;
        this.goalsScored += goalsScored;
        this.goalsConceded += goalsConceded;

        if (goalsScored > goalsConceded) {
            this.wins++;
        } else if (goalsScored == goalsConceded) {
            this.draws++;
        } else {
            this.losses++;
        }
    }

    /**
     * Devuelve un mapa con las estadísticas del equipo.
     *
     * @return Mapa con las estadísticas clave.
     */
    public Map<String, Integer> getStats() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Matches Played", matchesPlayed);
        stats.put("Wins", wins);
        stats.put("Draws", draws);
        stats.put("Losses", losses);
        stats.put("Goals Scored", goalsScored);
        stats.put("Goals Conceded", goalsConceded);
        return stats;
    }

    /**
     * Obtiene el número de victorias del equipo.
     *
     * @return Número de victorias.
     */
    public int getWins() {
        return wins;
    }

    /**
     * Obtiene el número de derrotas del equipo.
     *
     * @return Número de derrotas.
     */
    public int getLosses() {
        return losses;
    }

    @Override
    public String toString() {
        return "TeamStats{" +
                "matchesPlayed=" + matchesPlayed +
                ", wins=" + wins +
                ", draws=" + draws +
                ", losses=" + losses +
                ", goalsScored=" + goalsScored +
                ", goalsConceded=" + goalsConceded +
                '}';
    }
}
