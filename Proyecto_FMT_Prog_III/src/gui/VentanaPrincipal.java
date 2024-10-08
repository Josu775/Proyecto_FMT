package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame{
	
	private boolean PantallaCompleta = false;
    private GraphicsDevice device;
	
	public VentanaPrincipal() {
		setTitle("Football Manager Team");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        JLabel label = new JLabel("Menú Principal", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
		
        JButton opcionesBoton = new JButton("Opciones");
        
        panel.add(label);
        panel.add(opcionesBoton);
        
        add(panel, BorderLayout.CENTER);
        
        
        setVisible(true);
        
	}
	
	public static void main(String[] args) {
		new VentanaPrincipal();
	}
	
	
	public void aplicarConfiguracion(int ancho, int alto, boolean pantallaCompleta) {
		
	}
	
}
