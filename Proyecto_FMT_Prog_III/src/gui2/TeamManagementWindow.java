package gui2;

import db.TeamDAO;
import domain2.Team;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TeamManagementWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable teamTable;
    private TeamDAO teamDAO;

    public TeamManagementWindow() {
        setTitle("Gestión de Equipos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        teamDAO = new TeamDAO();
        List<Team> teams = teamDAO.getAllTeams();

        // Tabla de equipos
        String[] columnNames = {"ID", "Nombre", "Presupuesto"};
        Object[][] data = new Object[teams.size()][3];
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            data[i][0] = team.getId();
            data[i][1] = team.getName();
            data[i][2] = team.getBudget();
        }

        teamTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(teamTable);

        // Panel para botones
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Añadir Equipo");
        JButton editButton = new JButton("Editar Equipo");
        JButton deleteButton = new JButton("Eliminar Equipo");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Agregar componentes al frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Acciones de los botones
        addButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función por implementar: Añadir Equipo"));
        editButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función por implementar: Editar Equipo"));
        deleteButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función por implementar: Eliminar Equipo"));
    }
}