package main;

import java.io.IOException;

import domain.Generador_Nombres;
import domain.Jugador;

public class Main {
    public static void main(String[] args) {
        try {
            Generador_Nombres nombreGenerador = new Generador_Nombres("resources/data/Nombres_jugadores.txt");
            Jugador jugador = new Jugador(nombreGenerador);
            jugador.mostrarInformacion(); // Esto también mostrará el sueldo calculado
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de nombres: " + e.getMessage());
        }
    }
}
