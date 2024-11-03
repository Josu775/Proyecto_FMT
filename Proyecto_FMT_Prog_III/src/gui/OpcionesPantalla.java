package gui;

import javax.swing.*;
import java.awt.*;

public class OpcionesPantalla extends JFrame {
    private JCheckBox pantallaCompletaCheckbox;
    private JComboBox<String> resolucionesComboBox;
    private VentanaMenuPrincipal ventanaPrincipal;

    public OpcionesPantalla(VentanaMenuPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        configurarVentana();
        inicializarComponentes();
        setVisible(true);
    }

    private void configurarVentana() {
        setTitle("Pantalla de Opciones");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); // Utilizar GridBagLayout para organizar los componentes
    }

    private void inicializarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Añadir márgenes entre componentes

        // Checkbox de pantalla completa
        pantallaCompletaCheckbox = new JCheckBox("Pantalla Completa", ventanaPrincipal.isPantallaCompleta());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.anchor = GridBagConstraints.WEST;
        add(pantallaCompletaCheckbox, gbc);

        // Label de resolución
        JLabel resolucionLabel = new JLabel("Resolución:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(resolucionLabel, gbc);

        // ComboBox de resoluciones
        String[] resoluciones = {"800x600", "1024x768", "1280x1024", "1920x1080"};
        resolucionesComboBox = new JComboBox<>(resoluciones);
        resolucionesComboBox.setSelectedItem(ventanaPrincipal.getResolucion());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(resolucionesComboBox, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        agregarBotones(panelBotones);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelBotones, gbc);
    }

    private void agregarBotones(JPanel panelBotones) {
        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> guardarYCerrar());
        panelBotones.add(botonGuardar);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(e -> cerrarOpciones());
        panelBotones.add(botonCancelar);

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> cerrarOpciones());
        panelBotones.add(botonVolver);
    }

    private void guardarYCerrar() {
        guardarCambios();
        cerrarOpciones();
    }

    private void guardarCambios() {
        boolean activarPantallaCompleta = pantallaCompletaCheckbox.isSelected();
        String resolucionSeleccionada = (String) resolucionesComboBox.getSelectedItem();

        if (activarPantallaCompleta != ventanaPrincipal.isPantallaCompleta()) {
            if (activarPantallaCompleta) {
                ventanaPrincipal.activarPantallaCompleta();
            } else {
                ventanaPrincipal.desactivarPantallaCompleta();
            }
            ventanaPrincipal.setPantallaCompleta(activarPantallaCompleta);
        }

        if (!ventanaPrincipal.getResolucion().equals(resolucionSeleccionada)) {
            String[] dimensiones = resolucionSeleccionada.split("x");
            int width = Integer.parseInt(dimensiones[0]);
            int height = Integer.parseInt(dimensiones[1]);
            ventanaPrincipal.setSize(width, height);
        }
    }

    private void cerrarOpciones() {
        ventanaPrincipal.setVisible(true);
        dispose();
    }
}

