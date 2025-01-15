package db;

import domain2.Player;
import domain2.TeamStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatsDAO {

    public void savePlayerStats(Player player) {
        String query = """
            INSERT INTO PlayerStats (playerId, matchesPlayed, goals, assists, yellowCards, redCards)
            VALUES (?, ?, ?, ?, ?, ?)
            ON CONFLICT(playerId) DO UPDATE SET
            matchesPlayed = excluded.matchesPlayed,
            goals = excluded.goals,
            assists = excluded.assists,
            yellowCards = excluded.yellowCards,
            redCards = excluded.redCards;
        """;

        try (Connection conn = SQLiteConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, player.getId());
            stmt.setInt(2, player.getStats().getMatchesPlayed());
            stmt.setInt(3, player.getStats().getGoals());
            stmt.setInt(4, player.getStats().getAssists());
            stmt.setInt(5, player.getStats().getYellowCards());
            stmt.setInt(6, player.getStats().getRedCards());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveTeamStats(TeamStats stats, int teamId) {
        String query = """
            INSERT INTO TeamStats (teamId, matchesPlayed, wins, draws, losses, goalsScored, goalsConceded)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            ON CONFLICT(teamId) DO UPDATE SET
            matchesPlayed = excluded.matchesPlayed,
            wins = excluded.wins,
            draws = excluded.draws,
            losses = excluded.losses,
            goalsScored = excluded.goalsScored,
            goalsConceded = excluded.goalsConceded;
        """;

        try (Connection conn = SQLiteConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teamId);
            stmt.setInt(2, stats.getStats().get("Matches Played"));
            stmt.setInt(3, stats.getStats().get("Wins"));
            stmt.setInt(4, stats.getStats().get("Draws"));
            stmt.setInt(5, stats.getStats().get("Losses"));
            stmt.setInt(6, stats.getStats().get("Goals Scored"));
            stmt.setInt(7, stats.getStats().get("Goals Conceded"));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
