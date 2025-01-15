package db;

import domain2.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private static final String CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS Players (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            age INTEGER NOT NULL,
            skill INTEGER NOT NULL,
            marketValue REAL NOT NULL
        );
    """;

    private static final String INSERT_PLAYER = """
        INSERT INTO Players (name, age, skill, marketValue)
        VALUES (?, ?, ?, ?);
    """;

    private static final String SELECT_ALL_PLAYERS = """
        SELECT * FROM Players;
    """;

    public PlayerDAO() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE);
        } catch (SQLException e) {
            System.out.println("Error creando la tabla de jugadores: " + e.getMessage());
        }
    }

    public void insertPlayer(Player player) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_PLAYER)) {
            pstmt.setString(1, player.getName());
            pstmt.setInt(2, player.getAge());
            pstmt.setInt(3, player.getSkill());
            pstmt.setDouble(4, player.getMarketValue());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar jugador: " + e.getMessage());
        }
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_PLAYERS)) {
            while (rs.next()) {
                Player player = new Player(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getInt("skill"),
                    rs.getDouble("marketValue"), null
                );
                players.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener jugadores: " + e.getMessage());
        }
        return players;
    }
}
