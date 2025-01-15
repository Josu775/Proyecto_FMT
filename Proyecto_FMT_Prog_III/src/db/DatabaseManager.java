package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:resources/db/career_mode.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Conexión a la base de datos establecida.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
    
    public static void createTables() {
        try (Connection connection = SQLiteConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Crear tabla de equipos
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Team (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    strength INTEGER NOT NULL
                )
            """);

            // Crear tabla de torneos
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Tournament (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL
                )
            """);

            // Crear tabla de partidos
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS Match (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    tournamentId INTEGER NOT NULL,
                    team1Id INTEGER NOT NULL,
                    team2Id INTEGER NOT NULL,
                    team1Score INTEGER NOT NULL,
                    team2Score INTEGER NOT NULL,
                    FOREIGN KEY (tournamentId) REFERENCES Tournament(id),
                    FOREIGN KEY (team1Id) REFERENCES Team(id),
                    FOREIGN KEY (team2Id) REFERENCES Team(id)
                )
            """);

            System.out.println("Tablas creadas exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
