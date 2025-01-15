package juego;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Equipo> equipos = new ArrayList<>();
    private static JTextArea jugadoresArea;
    private static JTextArea resultadosArea;
    private static JComboBox<Equipo> equipoComboBox1;
    private static JComboBox<Equipo> equipoComboBox2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Equipos y Partidos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(3, 1));

        // Panel para agregar jugadores
        JPanel panelAgregarJugador = new JPanel(new GridLayout(5, 2));
        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();
        JLabel habilidadLabel = new JLabel("Habilidad:");
        JTextField habilidadField = new JTextField();
        JLabel posicionLabel = new JLabel("Posición:");
        JTextField posicionField = new JTextField();
        JLabel equipoLabel = new JLabel("Seleccionar Equipo:");
        equipoComboBox1 = new JComboBox<>();
        equipoComboBox2 = new JComboBox<>();
        JButton agregarButton = new JButton("Agregar Jugador");
        JButton crearEquipoButton = new JButton("Crear Equipo");
        JButton jugarPartidoButton = new JButton("Jugar Partido");
        
        jugadoresArea = new JTextArea();
        jugadoresArea.setEditable(false);
        JScrollPane scrollJugadores = new JScrollPane(jugadoresArea);
        
        // Panel para mostrar resultados
        resultadosArea = new JTextArea();
        resultadosArea.setEditable(false);
        JScrollPane scrollResultados = new JScrollPane(resultadosArea);
        scrollResultados.setPreferredSize(new Dimension(300, 100));

        // Agregar paneles al marco
        panelAgregarJugador.add(nombreLabel);
        panelAgregarJugador.add(nombreField);
        panelAgregarJugador.add(habilidadLabel);
        panelAgregarJugador.add(habilidadField);
        panelAgregarJugador.add(posicionLabel);
        panelAgregarJugador.add(posicionField);
        panelAgregarJugador.add(equipoLabel);
        panelAgregarJugador.add(equipoComboBox1);
        panelAgregarJugador.add(agregarButton);
        panelAgregarJugador.add(crearEquipoButton);
        panelAgregarJugador.add(jugarPartidoButton);

        frame.add(panelAgregarJugador);
        frame.add(scrollJugadores);
        frame.add(scrollResultados);

        // Acción para agregar jugador
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                int habilidad = Integer.parseInt(habilidadField.getText());
                String posicion = posicionField.getText();
                Equipo equipoSeleccionado = (Equipo) equipoComboBox1.getSelectedItem();

                if (equipoSeleccionado != null) {
                    Jugador nuevoJugador = new Jugador(nombre, habilidad, posicion);
                    equipoSeleccionado.agregarJugador(nuevoJugador);
                    jugadoresArea.setText(equipoSeleccionado.mostrarJugadores());
                }

                nombreField.setText("");
                habilidadField.setText("");
                posicionField.setText("");
            }
        });

        // Acción para crear equipo
        crearEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEquipo = JOptionPane.showInputDialog(frame, "Nombre del equipo:");
                if (nombreEquipo != null && !nombreEquipo.trim().isEmpty()) {
                    Equipo nuevoEquipo = new Equipo(nombreEquipo);
                    equipos.add(nuevoEquipo);
                    equipoComboBox1.addItem(nuevoEquipo);
                    equipoComboBox2.addItem(nuevoEquipo);
                    jugadoresArea.setText(nuevoEquipo.mostrarJugadores());
                }
            }
        });

        // Acción para jugar partido
        jugarPartidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (equipos.size() < 2) {
                    JOptionPane.showMessageDialog(frame, "Se necesitan al menos dos equipos para jugar un partido.");
                    return;
                }
                Equipo equipoSeleccionado1 = (Equipo) equipoComboBox1.getSelectedItem();
                Equipo equipoSeleccionado2 = (Equipo) equipoComboBox2.getSelectedItem();

                if (equipoSeleccionado1 != null && equipoSeleccionado2 != null && !equipoSeleccionado1.equals(equipoSeleccionado2)) {
                    Partido partido = new Partido(equipoSeleccionado1, equipoSeleccionado2);
                    String resultado = partido.jugar();
                    resultadosArea.setText(resultado);
                } else {
                    JOptionPane.showMessageDialog(frame, "Por favor, selecciona dos equipos diferentes para jugar.");
                }
            }
        });

        frame.setVisible(true);
    }
}