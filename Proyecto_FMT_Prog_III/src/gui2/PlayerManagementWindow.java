package gui2;

import db.PlayerDAO;
import domain2.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerManagementWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable playerTable;
    private PlayerDAO playerDAO;

    public PlayerManagementWindow() {
        setTitle("Gestión de Jugadores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        playerDAO = new PlayerDAO();
        List<Player> players = playerDAO.getAllPlayers();

        // Tabla de jugadores
        String[] columnNames = {"ID", "Nombre", "Edad", "Habilidad", "Valor de mercado"};
        Object[][] data = new Object[players.size()][5];
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            data[i][0] = player.getId();
            data[i][1] = player.getName();
            data[i][2] = player.getAge();
            data[i][3] = player.getSkill();
            data[i][4] = player.getMarketValue();
        }

        playerTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(playerTable);

        // Panel para botones
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Añadir Jugador");
        JButton editButton = new JButton("Editar Jugador");
        JButton deleteButton = new JButton("Eliminar Jugador");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Agregar componentes al frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Acciones de los botones
        addButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función por implementar: Añadir Jugador"));
        editButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función por implementar: Editar Jugador"));
        deleteButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función por implementar: Eliminar Jugador"));
    }
}
