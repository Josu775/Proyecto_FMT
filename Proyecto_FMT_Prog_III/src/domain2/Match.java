package domain2;

import java.util.Random;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
    private Random rand; // Añadido como atributo de la clase

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.rand = new Random(); // Inicializar el generador aleatorio
    }

    public void simulateMatch() {
        // Simulación del marcador con base en las estadísticas de los equipos
        int homeAdvantage = homeTeam.getStats().getWins() - awayTeam.getStats().getLosses();
        int homeGoals = rand.nextInt(4) + homeAdvantage; // Aumentamos la probabilidad de goles en casa
        int awayGoals = rand.nextInt(4);

        // Asignamos los goles a los equipos
        homeScore = homeGoals;
        awayScore = awayGoals;

        // Actualizamos las estadísticas de los jugadores y los equipos
        homeTeam.getStats().recordMatch(homeGoals, awayGoals);
        awayTeam.getStats().recordMatch(awayGoals, homeGoals);

        // Registrar estadísticas individuales de los jugadores
        recordPlayerStats(homeTeam, homeGoals, awayGoals);
        recordPlayerStats(awayTeam, awayGoals, homeGoals);

        // Anunciar el resultado
        System.out.println(homeTeam.getName() + " " + homeScore + " - " + awayScore + " " + awayTeam.getName());
    }

    private void recordPlayerStats(Team team, int goalsScored, int goalsConceded) {
        for (Player player : team.getPlayers()) {
            // Por simplicidad, asignamos estadísticas al azar
            int goals = (goalsScored > goalsConceded) ? 1 : 0;
            int assists = rand.nextInt(2);
            int yellowCards = rand.nextInt(2);
            int redCards = rand.nextInt(1);
            player.recordMatchStats(goals, assists, yellowCards, redCards);
        }
    }
}

