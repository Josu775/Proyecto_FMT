package gui2;

import domain2.MatchResult;
import domain2.MatchSimulator;
import domain2.Team;

import javax.swing.*;
import java.awt.*;

public class MatchSimulationWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel team1Label, team2Label;
    private JLabel scoreLabel;

    // Constructor principal
    public MatchSimulationWindow(Team team1, Team team2) {
        setTitle("Simulación de Partido");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new GridLayout(3, 1));

        // Equipos
        team1Label = new JLabel(team1.getName(), SwingConstants.CENTER);
        team1Label.setFont(new Font("Arial", Font.BOLD, 18));

        team2Label = new JLabel(team2.getName(), SwingConstants.CENTER);
        team2Label.setFont(new Font("Arial", Font.BOLD, 18));

        // Marcador
        scoreLabel = new JLabel("0 - 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(team1Label);
        add(scoreLabel);
        add(team2Label);

        // Simulación del partido
        JButton simulateButton = new JButton("Simular Partido");
        simulateButton.addActionListener(e -> simulateMatch(team1, team2));
        add(simulateButton);
    }

    // Constructor adicional (sin argumentos)
    public MatchSimulationWindow() {
        this(new Team(1, "Equipo A", 100000), new Team(2, "Equipo B", 100000));
    }

    private void simulateMatch(Team team1, Team team2) {
        MatchSimulator simulator = new MatchSimulator();
        MatchResult result = simulator.simulateMatch(team1, team2);

        // Actualizar marcador
        scoreLabel.setText(result.getTeam1Score() + " - " + result.getTeam2Score());

        // Mostrar mensaje de resultado
        JOptionPane.showMessageDialog(this, result.getResultSummary());
    }
}

