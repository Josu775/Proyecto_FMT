package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcionesPantalla extends JFrame {

    public OpcionesPantalla() {
        setTitle("Pantalla de Opciones");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton volverButton = new JButton("Volver al Menú Principal");

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAlMenuPrincipal();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(volverButton);

        add(panel);
    }

    public void volverAlMenuPrincipal() {
    	VentanaMenuPrincipal MenuPrincipal = new VentanaMenuPrincipal();
        MenuPrincipal.setVisible(true);
        this.dispose();
    }
}
