package gui;

import javax.swing.*;
import java.awt.*;

public class OpcionesPantalla extends JFrame {
    private JCheckBox pantallaCompletaCheckbox;
    private VentanaMenuPrincipal ventanaPrincipal;

    public OpcionesPantalla(VentanaMenuPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        setTitle("Pantalla de Opciones");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el checkbox para pantalla completa
        pantallaCompletaCheckbox = new JCheckBox("Pantalla Completa");
        pantallaCompletaCheckbox.setSelected(ventanaPrincipal.isPantallaCompleta());  // Mantener el estado actual

        // Botón "Guardar"
        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> {
            guardarCambios();
            cerrarOpciones();
        });

        // Botón "Cancelar"
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(e -> cerrarOpciones());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(pantallaCompletaCheckbox);
        panel.add(botonGuardar);
        panel.add(botonCancelar);

        add(panel);
        setVisible(true);
    }

    // Método para guardar los cambios
    private void guardarCambios() {
        boolean activarPantallaCompleta = pantallaCompletaCheckbox.isSelected();
        if (activarPantallaCompleta != ventanaPrincipal.isPantallaCompleta()) {
            if (activarPantallaCompleta) {
                ventanaPrincipal.activarPantallaCompleta();
            } else {
                ventanaPrincipal.desactivarPantallaCompleta();
            }
            ventanaPrincipal.setPantallaCompleta(activarPantallaCompleta);  // Actualizar el estado
        }
    }

    // Método para cerrar la ventana de opciones y volver al menú principal
    private void cerrarOpciones() {
        ventanaPrincipal.setVisible(true);  // Mostrar de nuevo la ventana principal
        this.dispose();  // Cerrar la ventana de opciones
    }
}
