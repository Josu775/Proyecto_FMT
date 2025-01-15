package gui2;

import domain2.Player;
import domain2.Team;

import javax.swing.*;
import java.awt.*;

public class StatsWindow extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatsWindow(Team team) {
        setTitle("Estadísticas del equipo: " + team.getName());
        setSize(400, 300);
        setLayout(new BorderLayout());

        JTextArea statsArea = new JTextArea();
        statsArea.setEditable(false);

        StringBuilder statsText = new StringBuilder("Estadísticas de equipo:\n");
        statsText.append(team.getStats()).append("\n\n");

        statsText.append("Estadísticas de jugadores:\n");
        for (Player player : team.getPlayers()) {
            statsText.append(player.getName()).append(": ").append(player.getStats()).append("\n");
        }

        statsArea.setText(statsText.toString());
        add(new JScrollPane(statsArea), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
