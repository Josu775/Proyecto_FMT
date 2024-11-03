package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OpcionesPantalla extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox pantallaCompletaCheckbox;
    private JComboBox<String> resolucionesComboBox;
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

        // Crear el JComboBox para seleccionar resoluciones
        String[] resoluciones = {"800x600", "1024x768", "1280x1024", "1920x1080"};
        resolucionesComboBox = new JComboBox<>(resoluciones);
        resolucionesComboBox.setSelectedItem(ventanaPrincipal.getResolucion());  // Mantener la resolución actual

        // Botón "Guardar"
        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(e -> {
            guardarCambios();
            cerrarOpciones();
        });

        // Botón "Cancelar"
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(e -> cerrarOpciones());

        // Botón "Volver"
        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> cerrarOpciones());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(pantallaCompletaCheckbox);
        panel.add(new JLabel("Resolución:"));
        panel.add(resolucionesComboBox);
        panel.add(botonGuardar);
        panel.add(botonCancelar);
        panel.add(botonVolver);

        add(panel);
        setVisible(true);
    }

    // Método para guardar los cambios
    private void guardarCambios() {
        boolean activarPantallaCompleta = pantallaCompletaCheckbox.isSelected();
        String resolucionSeleccionada = (String) resolucionesComboBox.getSelectedItem();

        if (activarPantallaCompleta != ventanaPrincipal.isPantallaCompleta()) {
            if (activarPantallaCompleta) {
                ventanaPrincipal.activarPantallaCompleta();
            } else {
                ventanaPrincipal.desactivarPantallaCompleta();
            }
            ventanaPrincipal.setPantallaCompleta(activarPantallaCompleta);  // Actualizar el estado
        }

        // Cambiar resolución si es diferente
        if (!ventanaPrincipal.getResolucion().equals(resolucionSeleccionada)) {
            String[] dimensiones = resolucionSeleccionada.split("x");
            int width = Integer.parseInt(dimensiones[0]);
            int height = Integer.parseInt(dimensiones[1]);
            ventanaPrincipal.setSize(width, height);  // Cambiar tamaño de la ventana
        }
    }

    // Método para cerrar la ventana de opciones y volver al menú principal
    private void cerrarOpciones() {
        ventanaPrincipal.setVisible(true);  // Mostrar de nuevo la ventana principal
        this.dispose();  // Cerrar la ventana de opciones
    }
}
