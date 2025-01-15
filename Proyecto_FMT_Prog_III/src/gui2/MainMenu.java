package gui2;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainMenu() {
        setTitle("FIFA Modo Carrera");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botones del menú
        JButton manageTeamsButton = new JButton("Gestionar Equipos");
        JButton managePlayersButton = new JButton("Gestionar Jugadores");
        JButton playMatchButton = new JButton("Simular Partido");
        JButton viewTournamentsButton = new JButton("Ver Torneos");
        JButton exitButton = new JButton("Salir");

        // Agregar botones al panel
        mainPanel.add(manageTeamsButton);
        mainPanel.add(managePlayersButton);
        mainPanel.add(playMatchButton);
        mainPanel.add(viewTournamentsButton);
        mainPanel.add(exitButton);

        // Acciones de los botones
        manageTeamsButton.addActionListener(e -> new TeamManagementWindow().setVisible(true));
        managePlayersButton.addActionListener(e -> new PlayerManagementWindow().setVisible(true));
        playMatchButton.addActionListener(e -> new MatchSimulationWindow().setVisible(true));
        exitButton.addActionListener(e -> System.exit(0));

        // Agregar panel al frame
        add(mainPanel);
    }
}
