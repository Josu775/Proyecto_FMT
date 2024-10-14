package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private List<Jugador> jugadores;

    // Constructor
    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>(); // Inicializa la lista de jugadores
    }

    // Método para generar 23 jugadores
    public void generarJugadores() {
        try {
            Generador_Nombres nombreGenerador = new Generador_Nombres("resources/data/Nombres_jugadores.txt");
            for (int i = 0; i < 23; i++) {
                // Crear un nuevo jugador
                Jugador jugador = new Jugador(nombreGenerador);
                jugadores.add(jugador); // Añadir el jugador a la lista
            }
            System.out.println("Jugadores generados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al generar jugadores: " + e.getMessage());
        }
    }

    // Método para mostrar la información de todos los jugadores
    public void mostrarJugadores() {
        System.out.println("Equipo: " + nombre);
        for (Jugador jugador : jugadores) {
            jugador.mostrarInformacion(); // Muestra la información de cada jugador
            System.out.println(); // Espacio entre jugadores
        }
    }

    public static void main(String[] args) {
        // Crear un equipo y generar jugadores
        Equipo equipo = new Equipo("Real Madrid");
        equipo.generarJugadores(); // Genera 23 jugadores
        equipo.mostrarJugadores();  // Muestra la información de los jugadores
    }
}
