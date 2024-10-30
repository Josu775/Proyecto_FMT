package main;

import java.io.IOException;

import domain.Generador_Nombres;
import domain.Jugador;
import gui.VentanaMenuPrincipal; // Importar la clase de la ventana

public class Main {
    public static void main(String[] args) {
        // Manejar la carga de nombres
        try {
            Generador_Nombres nombreGenerador = new Generador_Nombres("resources/data/Nombres_jugadores.txt");
            Jugador jugador = new Jugador(nombreGenerador);
            jugador.mostrarInformacion(); // Esto mostrará la información del jugador
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de nombres: " + e.getMessage());
        }

        // Cargar la ventana principal después de manejar la excepción
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaMenuPrincipal(); // Crear la ventana
        });
    }
}
