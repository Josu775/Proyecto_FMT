package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenuPrincipal extends JFrame {

    public VentanaMenuPrincipal() {
        setTitle("Menú Principal");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton botonopciones = new JButton("Opciones");
        JButton botonsalir = new JButton("Salir");

        botonopciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPantallaDeOpciones();
            }
        });
        
        botonsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(botonopciones);
        panel.add(botonsalir);

        add(panel);
        setVisible(true);
    }
    
    

    public void mostrarPantallaDeOpciones() {
        OpcionesPantalla opcionespantalla = new OpcionesPantalla();
        opcionespantalla.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new VentanaMenuPrincipal();
    }
    
    
}
