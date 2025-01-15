package db;

import domain2.MatchResult;
import domain2.Team;
import domain2.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentDAO {

    public void saveTournament(Tournament tournament) {
        String insertTournament = "INSERT INTO Tournament (name) VALUES (?)";
        String insertMatch = """
            INSERT INTO Match (tournamentId, team1Id, team2Id, team1Score, team2Score)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection connection = SQLiteConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement tournamentStmt = connection.prepareStatement(insertTournament, Statement.RETURN_GENERATED_KEYS)) {
                tournamentStmt.setString(1, tournament.getName());
                tournamentStmt.executeUpdate();

                ResultSet rs = tournamentStmt.getGeneratedKeys();
                if (rs.next()) {
                    int tournamentId = rs.getInt(1);

                    try (PreparedStatement matchStmt = connection.prepareStatement(insertMatch)) {
                        for (MatchResult match : tournament.getMatches()) {
                            matchStmt.setInt(1, tournamentId);
                            matchStmt.setInt(2, match.getTeam1().getId());
                            matchStmt.setInt(3, match.getTeam2().getId());
                            matchStmt.setInt(4, match.getTeam1Score());
                            matchStmt.setInt(5, match.getTeam2Score());
                            matchStmt.addBatch();
                        }
                        matchStmt.executeBatch();
                    }
                }
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tournament> loadAllTournaments(List<Team> allTeams) {
        List<Tournament> tournaments = new ArrayList<>();

        String selectTournaments = "SELECT * FROM Tournament";
        String selectMatches = "SELECT * FROM Match WHERE tournamentId = ?";

        try (Connection connection = SQLiteConnection.getConnection();
             PreparedStatement tournamentStmt = connection.prepareStatement(selectTournaments);
             PreparedStatement matchStmt = connection.prepareStatement(selectMatches)) {

            ResultSet tournamentRs = tournamentStmt.executeQuery();
            while (tournamentRs.next()) {
                int tournamentId = tournamentRs.getInt("id");
                String name = tournamentRs.getString("name");

                Tournament tournament = new Tournament(name);

                matchStmt.setInt(1, tournamentId);
                ResultSet matchRs = matchStmt.executeQuery();
                while (matchRs.next()) {
                    Team team1 = findTeamById(allTeams, matchRs.getInt("team1Id"));
                    Team team2 = findTeamById(allTeams, matchRs.getInt("team2Id"));

                    if (team1 != null && team2 != null) {
                        MatchResult match = new MatchResult(
                            team1,
                            team2,
                            matchRs.getInt("team1Score"),
                            matchRs.getInt("team2Score")
                        );
                        tournament.addMatch(match);
                    }
                }

                tournaments.add(tournament);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tournaments;
    }

    private Team findTeamById(List<Team> teams, int id) {
        return teams.stream().filter(team -> team.getId() == id).findFirst().orElse(null);
    }
}

