package db;

import domain2.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private static final String CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS Teams (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            budget REAL NOT NULL
        );
    """;

    private static final String INSERT_TEAM = """
        INSERT INTO Teams (name, budget)
        VALUES (?, ?);
    """;

    private static final String SELECT_ALL_TEAMS = """
        SELECT * FROM Teams;
    """;

    public TeamDAO() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE);
        } catch (SQLException e) {
            System.out.println("Error creando la tabla de equipos: " + e.getMessage());
        }
    }

    public void insertTeam(Team team) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_TEAM)) {
            pstmt.setString(1, team.getName());
            pstmt.setDouble(2, team.getBudget());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar equipo: " + e.getMessage());
        }
    }

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_TEAMS)) {
            while (rs.next()) {
                Team team = new Team(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("budget")
                );
                teams.add(team);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener equipos: " + e.getMessage());
        }
        return teams;
    }
}
