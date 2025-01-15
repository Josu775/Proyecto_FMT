package gui2;

import domain2.Player;
import javax.swing.*;
import java.awt.*;

public class PlayerStatsWindow extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerStatsWindow(Player player) {
        setTitle("Estadísticas del jugador: " + player.getName());
        setSize(400, 300);
        setLayout(new BorderLayout());

        JTextArea statsArea = new JTextArea();
        statsArea.setEditable(false);

        StringBuilder statsText = new StringBuilder("Estadísticas de jugador:\n");
        statsText.append("Partidos jugados: ").append(player.getStats().getMatchesPlayed()).append("\n");
        statsText.append("Goles: ").append(player.getStats().getGoals()).append("\n");
        statsText.append("Asistencias: ").append(player.getStats().getAssists()).append("\n");
        statsText.append("Tarjetas amarillas: ").append(player.getStats().getYellowCards()).append("\n");
        statsText.append("Tarjetas rojas: ").append(player.getStats().getRedCards()).append("\n");

        statsArea.setText(statsText.toString());
        add(new JScrollPane(statsArea), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
