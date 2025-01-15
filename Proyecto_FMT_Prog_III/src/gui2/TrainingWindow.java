package gui2;

import domain2.Team;
import domain2.Training;
import db.TrainingDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TrainingWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<Team> teamComboBox;
    private JTextField typeField;
    private JSlider intensitySlider;
    private JTextField durationField;
    private JTextArea logArea;

    private TrainingDAO trainingDAO;

    public TrainingWindow(List<Team> teams) {
        trainingDAO = new TrainingDAO();
        setTitle("Gestión de Entrenamientos");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Equipo:"));
        teamComboBox = new JComboBox<>(teams.toArray(new Team[0]));
        inputPanel.add(teamComboBox);

        inputPanel.add(new JLabel("Tipo de entrenamiento:"));
        typeField = new JTextField();
        inputPanel.add(typeField);

        inputPanel.add(new JLabel("Intensidad:"));
        intensitySlider = new JSlider(1, 10);
        intensitySlider.setMajorTickSpacing(1);
        intensitySlider.setPaintTicks(true);
        intensitySlider.setPaintLabels(true);
        inputPanel.add(intensitySlider);

        inputPanel.add(new JLabel("Duración (minutos):"));
        durationField = new JTextField();
        inputPanel.add(durationField);

        add(inputPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton trainButton = new JButton("Aplicar Entrenamiento");
        trainButton.addActionListener(this::applyTraining);
        buttonPanel.add(trainButton);

        JButton loadButton = new JButton("Cargar Entrenamientos");
        loadButton.addActionListener(this::loadTrainings);
        buttonPanel.add(loadButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Área de log
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBorder(BorderFactory.createTitledBorder("Log de Entrenamientos"));
        add(new JScrollPane(logArea), BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void applyTraining(ActionEvent e) {
        Team team = (Team) teamComboBox.getSelectedItem();
        if (team == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo.");
            return;
        }

        String type = typeField.getText();
        int intensity = intensitySlider.getValue();
        int duration;
        try {
            duration = Integer.parseInt(durationField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Duración inválida.");
            return;
        }

        Training training = new Training(type, intensity, duration);
        team.train(training);
        trainingDAO.saveTraining(training, team);
        logArea.append("Entrenamiento aplicado: " + training + "\n");
    }

    private void loadTrainings(ActionEvent e) {
        Team team = (Team) teamComboBox.getSelectedItem();
        if (team == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo.");
            return;
        }

        List<Training> trainings = trainingDAO.loadTrainings(team);
        logArea.setText("Entrenamientos cargados para " + team.getName() + ":\n");
        for (Training training : trainings) {
            logArea.append(training + "\n");
        }
    }
}

