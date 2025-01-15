package gui2;

import domain2.Player;
import domain2.Position;
import domain2.Team;
import domain2.Tournament;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public MainGUI() {
        setTitle("Juego de Fútbol - Modo Carrera");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        Team team1 = new Team(1, "Equipo A", 1000000);
        Team team2 = new Team(2, "Equipo B", 1200000);
        Team team3 = new Team(3, "Equipo C", 900000);

        Player player1 = new Player("Jugador 1", 25, 80, Position.FORWARD);
        Player player2 = new Player("Jugador 2", 27, 75, Position.MIDFIELDER);

        team1.addPlayer(player1);
        team2.addPlayer(player2);

        List<Team> teams = Arrays.asList(team1, team2, team3);
        Tournament tournament = new Tournament("Torneo Invierno", teams);

        JButton startTournamentButton = new JButton("Iniciar Torneo");
        startTournamentButton.addActionListener(e -> tournament.startRoundRobin());

        JButton viewPlayerStatsButton = new JButton("Ver Estadísticas de Jugador");
        viewPlayerStatsButton.addActionListener(e -> {
            PlayerStatsWindow playerStatsWindow = new PlayerStatsWindow(player1);
            playerStatsWindow.setVisible(true);
        });

        add(startTournamentButton);
        add(viewPlayerStatsButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI mainGUI = new MainGUI();
            mainGUI.setVisible(true);
        });
    }
}
