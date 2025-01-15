package gui2;

import domain2.MatchResult;
import domain2.Team;
import domain2.Tournament;
import db.TournamentDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TournamentWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField tournamentNameField;
    private JList<Team> teamList;
    private DefaultListModel<Team> teamListModel;
    private JTextArea logArea;
    private Tournament currentTournament; // Variable para almacenar el torneo actual
    private TournamentDAO tournamentDAO;
    private List<Team> allTeams;

    public TournamentWindow(List<Team> allTeams) {
        this.allTeams = allTeams;
        this.tournamentDAO = new TournamentDAO();

        setTitle("Gestión de Torneos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel de creación de torneos
        JPanel createPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        createPanel.setBorder(BorderFactory.createTitledBorder("Crear Torneo"));

        JLabel nameLabel = new JLabel("Nombre del Torneo:");
        tournamentNameField = new JTextField();

        JLabel teamLabel = new JLabel("Selecciona Equipos:");
        teamListModel = new DefaultListModel<>();
        allTeams.forEach(teamListModel::addElement);

        teamList = new JList<>(teamListModel);
        teamList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane teamScrollPane = new JScrollPane(teamList);

        JButton startButton = new JButton("Iniciar Torneo");
        startButton.addActionListener(e -> startTournament());

        createPanel.add(nameLabel);
        createPanel.add(tournamentNameField);
        createPanel.add(teamLabel);
        createPanel.add(teamScrollPane);
        createPanel.add(startButton);

        // Área de log para mostrar los resultados
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder("Log del Torneo"));

        // Botones para guardar y cargar torneos
        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton saveButton = new JButton("Guardar Torneo");
        saveButton.addActionListener(e -> saveTournament());
        JButton loadButton = new JButton("Cargar Torneos");
        loadButton.addActionListener(e -> loadTournaments());

        actionPanel.add(saveButton);
        actionPanel.add(loadButton);

        // Agregar paneles
        mainPanel.add(createPanel, BorderLayout.NORTH);
        mainPanel.add(logScrollPane, BorderLayout.CENTER);
        mainPanel.add(actionPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void startTournament() {
        String tournamentName = tournamentNameField.getText();
        List<Team> selectedTeams = teamList.getSelectedValuesList();

        if (tournamentName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un nombre para el torneo.");
            return;
        }

        if (selectedTeams.size() < 2) {
            JOptionPane.showMessageDialog(this, "Se necesitan al menos 2 equipos para iniciar un torneo.");
            return;
        }

        // Crear y simular torneo
        currentTournament = new Tournament(tournamentName, selectedTeams);
        currentTournament.startTournament();

        // Mostrar resultados en el log
        logArea.setText("");
        for (MatchResult match : currentTournament.getMatches()) {
            logArea.append(match.getResultSummary() + "\n");
        }

        // Obtener el ganador y mostrarlo
        MatchResult finalMatch = currentTournament.getMatches().get(currentTournament.getMatches().size() - 1);
        Team winningTeam = finalMatch.getWinningTeam();
        if (winningTeam != null) {
            logArea.append("\n¡Ganador: " + winningTeam.getName() + "!\n");
        } else {
            logArea.append("\nEl torneo terminó en empate.\n");
        }
    }

    private void saveTournament() {
        if (currentTournament == null) {
            JOptionPane.showMessageDialog(this, "No hay un torneo activo para guardar.");
            return;
        }

        tournamentDAO.saveTournament(currentTournament);
        JOptionPane.showMessageDialog(this, "Torneo guardado exitosamente.");
    }

    private void loadTournaments() {
        List<Tournament> tournaments = tournamentDAO.loadAllTournaments(allTeams);
        logArea.setText("Torneos cargados:\n");
        for (Tournament t : tournaments) {
            logArea.append("- " + t.getName() + "\n");
        }
    }
}

