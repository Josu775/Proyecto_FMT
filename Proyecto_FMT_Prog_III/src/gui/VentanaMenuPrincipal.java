package gui;

import javax.swing.*;
import java.awt.*;

public class VentanaMenuPrincipal extends JFrame {

    private boolean pantallaCompleta = false;  // Estado de pantalla completa
    private GraphicsDevice dispositivo;

    public VentanaMenuPrincipal() {
        setTitle("Menú Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        dispositivo = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        JButton botonOpciones = new JButton("Opciones");
        JButton botonSalir = new JButton("Salir");

        // Acción del botón "Opciones"
        botonOpciones.addActionListener(e -> mostrarPantallaDeOpciones());

        // Acción del botón "Salir"
        botonSalir.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(botonOpciones);
        panel.add(botonSalir);

        add(panel);
        setVisible(true);
    }

    // Método para abrir la pantalla de opciones
    public void mostrarPantallaDeOpciones() {
        this.setVisible(false); // Ocultar la ventana principal
        new OpcionesPantalla(this);  // Crear la ventana de opciones (un JFrame independiente)
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
        this.setUndecorated(false);  // Restaurar bordes
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
