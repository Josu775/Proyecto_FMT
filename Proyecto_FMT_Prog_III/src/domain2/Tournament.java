package domain2;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private String name;
    private List<Team> teams;
    private List<MatchResult> matches;

    public Tournament(String name, List<Team> teams) {
        this.name = name;
        this.teams = teams;
        this.matches = new ArrayList<>();
    }

    public Tournament(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<MatchResult> getMatches() {
        return matches;
    }

    public void addMatch(MatchResult match) {
        matches.add(match);
    }

    public void startTournament() {
        MatchSimulator simulator = new MatchSimulator();
        List<Team> currentRound = new ArrayList<>(teams);

        while (currentRound.size() > 1) {
            List<Team> nextRound = new ArrayList<>();
            for (int i = 0; i < currentRound.size() - 1; i += 2) {
                Team team1 = currentRound.get(i);
                Team team2 = currentRound.get(i + 1);

                MatchResult result = simulator.simulateMatch(team1, team2);
                matches.add(result);
                nextRound.add(result.getTeam1Score() > result.getTeam2Score() ? team1 : team2);

                System.out.println(result.getResultSummary());
            }

            if (currentRound.size() % 2 != 0) {
                Team loneTeam = currentRound.get(currentRound.size() - 1);
                System.out.println(loneTeam.getName() + " avanza automáticamente a la siguiente ronda.");
                nextRound.add(loneTeam);
            }

            currentRound = nextRound;
        }

        if (!currentRound.isEmpty()) {
            System.out.println("¡Ganador del torneo: " + currentRound.get(0).getName() + "!");
        }
    }

    public void startRoundRobin() {
        System.out.println("Iniciando torneo formato liga: " + name);
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Team homeTeam = teams.get(i);
                Team awayTeam = teams.get(j);
                Match match = new Match(homeTeam, awayTeam);
                match.simulateMatch();
            }
        }
    }
}


