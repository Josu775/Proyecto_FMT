package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VentanaMenuPrincipal extends JFrame {

    private boolean pantallaCompleta = false;  // Estado de pantalla completa
    private GraphicsDevice dispositivo;

    // Paneles
    private JPanel panelInicio;  // Panel para el menú principal
    private JPanel panelLigas;   // Panel para la selección de ligas
    private JPanel panelEquipos; // Panel para la selección de equipos

    public VentanaMenuPrincipal() {
        setTitle("Football Manager Team");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        dispositivo = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // Inicializar los paneles
        panelInicio = crearPanelInicio();
        panelLigas = crearPanelLigas();
        panelEquipos = crearPanelEquipos();

        // Añadir los paneles al JFrame
        setLayout(new CardLayout()); // Usar CardLayout para manejar las transiciones entre paneles
        add(panelInicio, "Inicio");
        add(panelLigas, "Ligas");
        add(panelEquipos, "Equipos");

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para crear el panel de inicio
    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Crear botones
        JButton nuevaPartida = new JButton("Empezar Nueva Partida");
        JButton cargarPartida = new JButton("Cargar Partida");
        JButton botonOpciones = new JButton("Opciones");
        JButton botonSalir = new JButton("Salir");

        Dimension buttonSize = new Dimension(200, 50);
        
        nuevaPartida.setMaximumSize(buttonSize);
        cargarPartida.setMaximumSize(buttonSize);
        botonOpciones.setMaximumSize(buttonSize);
        botonSalir.setMaximumSize(buttonSize);

        // Acción del botón "Empezar Nueva Partida"
        nuevaPartida.addActionListener(e -> mostrarPanelLigas());

        // Acción del botón "Opciones"
        botonOpciones.addActionListener(e -> mostrarPantallaDeOpciones());

        // Acción del botón "Salir"
        botonSalir.addActionListener(e -> System.exit(0));

        // Alineación de los botones y separación entre ellos
        nuevaPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
        cargarPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonOpciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir los botones con una separación de 50 píxeles entre ellos
        panel.add(Box.createVerticalGlue());  // Espacio flexible para centrar verticalmente
        panel.add(nuevaPartida);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));  // Espacio de 50 píxeles
        panel.add(cargarPartida);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));  // Espacio de 50 píxeles
        panel.add(botonOpciones);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));  // Espacio de 50 píxeles
        panel.add(botonSalir);
        panel.add(Box.createVerticalGlue());  // Espacio flexible para centrar verticalmente

        // Añadir un borde vacío alrededor del panel para darle márgenes
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        return panel;
    }

    // Método para crear el panel de ligas
    private JPanel crearPanelLigas() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));  // 5 filas, 1 columna + botón de retroceso

        // Lista de ligas
        String[] ligas = {"LaLiga", "Premier League", "Serie A", "Bundesliga", "Ligue 1"};

        // Añadir botones para cada liga
        for (String liga : ligas) {
            JButton ligaButton = new JButton(liga);
            ligaButton.addActionListener(e -> mostrarEquipos(liga)); // Mostrar equipos al seleccionar la liga
            panel.add(ligaButton);
        }

        // Botón para regresar a la pantalla de inicio
        JButton regresarButton = new JButton("Regresar");
        regresarButton.addActionListener(e -> mostrarPanelInicio());
        panel.add(regresarButton);

        return panel;
    }

    // Método para crear el panel de equipos
    private JPanel crearPanelEquipos() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    // Método para mostrar equipos de la liga seleccionada
    private void mostrarEquipos(String liga) {
        // Limpiar el panel de equipos
        panelEquipos.removeAll();

        // Crear y agregar el botón de regresar
        JButton regresarButton = new JButton("Regresar a Ligas");
        regresarButton.addActionListener(e -> mostrarPanelLigas());
        panelEquipos.add(regresarButton);
        
        // Cargar los equipos y añadir botones para cada uno
        ArrayList<String> equipos = cargarEquipos(liga);
        for (String equipo : equipos) {
            JButton equipoButton = new JButton(equipo);
            panelEquipos.add(equipoButton);
            panelEquipos.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio de 10 píxeles entre botones
        }

        panelEquipos.revalidate(); // Actualizar el panel de equipos
        panelEquipos.repaint(); // Redibujar el panel de equipos

        // Cambiar a la vista de equipos
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "Equipos");
    }

    // Método para cargar equipos desde un archivo
    private ArrayList<String> cargarEquipos(String liga) {
        ArrayList<String> equipos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/data/equipos_ligas.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2 && parts[1].equalsIgnoreCase(liga)) {
                    equipos.add(parts[0]); // Agregar el nombre del equipo
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    // Método para abrir la pantalla de opciones
    public void mostrarPantallaDeOpciones() {
        this.setVisible(false); // Ocultar la ventana principal
        new OpcionesPantalla(this);  // Crear la ventana de opciones (un JFrame independiente)
    }

    // Método para mostrar el panel de inicio
    public void mostrarPanelInicio() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "Inicio"); // Mostrar el panel de inicio
    }

    // Método para mostrar el panel de ligas
    public void mostrarPanelLigas() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "Ligas"); // Mostrar el panel de ligas
    }

    // Métodos para manejar la pantalla completa
    public void activarPantallaCompleta() {
        if (dispositivo.isFullScreenSupported()) {
            this.dispose();
            this.setUndecorated(true);  // Quitar bordes
            dispositivo.setFullScreenWindow(this);
            this.setVisible(true);
            pantallaCompleta = true;
        } else {
            JOptionPane.showMessageDialog(this, "Pantalla completa no soportada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void desactivarPantallaCompleta() {
        dispositivo.setFullScreenWindow(null);  // Salir del modo pantalla completa
        this.dispose();
        this.setUndecorated(false);  // Restaurar borde0s
        this.setSize(800, 600);  // Restaurar el tamaño
        this.setVisible(true);
        pantallaCompleta = false;
    }

    // Métodos para obtener y establecer el estado de pantalla completa
    public boolean isPantallaCompleta() {
        return pantallaCompleta;
    }

    public void setPantallaCompleta(boolean pantallaCompleta) {
        this.pantallaCompleta = pantallaCompleta;
    }

    public static void main(String[] args) {
        new VentanaMenuPrincipal();
    }
}
