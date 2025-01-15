package db;

import domain2.Team;
import domain2.Training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingDAO {

    public void saveTraining(Training training, Team team) {
        String insertTraining = """
            INSERT INTO Training (teamId, type, intensity, duration, impact)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection connection = SQLiteConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertTraining)) {

            stmt.setInt(1, team.getId());
            stmt.setString(2, training.getType());
            stmt.setInt(3, training.getIntensity());
            stmt.setInt(4, training.getDuration());
            stmt.setInt(5, training.calculateImpact());
            stmt.executeUpdate();

            System.out.println("Entrenamiento guardado exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Training> loadTrainings(Team team) {
        String selectTrainings = "SELECT * FROM Training WHERE teamId = ?";
        List<Training> trainings = new ArrayList<>();

        try (Connection connection = SQLiteConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(selectTrainings)) {

            stmt.setInt(1, team.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                int intensity = rs.getInt("intensity");
                int duration = rs.getInt("duration");

                Training training = new Training(type, intensity, duration);
                trainings.add(training);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trainings;
    }
}

