package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaGrafica extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Liga liga;

    public VentanaGrafica() {
        liga = new Liga();
        setTitle("Modo Carrera - Juego de Fútbol");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton botonAgregarEquipo = new JButton("Agregar Equipo");
        JButton botonJugar = new JButton("Jugar Liga");
        JButton botonEstadisticas = new JButton("Mostrar Estadísticas");

        botonAgregarEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEquipo = JOptionPane.showInputDialog("Ingrese el nombre del equipo:");
                if (nombreEquipo != null && !nombreEquipo.trim().isEmpty()) {
                    Equipo equipo = new Equipo(nombreEquipo);
                    for (int i = 0; i < 11; i++) {
                        String nombreJugador = JOptionPane.showInputDialog("Ingrese el nombre del jugador " + (i + 1) + ":");
                        int habilidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la habilidad del jugador " + (i + 1) + ":"));
                        String posicion = JOptionPane.showInputDialog("Ingrese la posición del jugador " + (i + 1) + ":");
                        Jugador jugador = new Jugador(nombreJugador, habilidad, posicion);
                        equipo.agregarJugador(jugador);
                    }
                    liga.agregarEquipo(equipo);
                    textArea.append("Equipo " + nombreEquipo + " agregado.\n");
                }
            }
        });

        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Resultados de la liga:\n");
                liga.jugarLiga();
            }
        });

        botonEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Estadísticas de la liga:\n");
                liga.mostrarEstadisticas();
            }
        });

        panel.add(botonAgregarEquipo);
        panel.add(botonJugar);
        panel.add(botonEstadisticas);
        add(panel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaGrafica ventana = new VentanaGrafica();
            ventana.setVisible(true);
        });
    }
}