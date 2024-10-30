package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String liga; // Añadir liga
    private String rutaLogo; // Añadir rutaLogo
    private List<Jugador> jugadores;

    // Constructor modificado para incluir liga y rutaLogo
    public Equipo(String nombre, String liga, String rutaLogo) {
        this.nombre = nombre;
        this.liga = liga; // Inicializa la liga
        this.rutaLogo = rutaLogo; // Inicializa la ruta del logo
        this.jugadores = new ArrayList<>(); // Inicializa la lista de jugadores
    }

    // Método para generar 23 jugadores
    public void generarJugadores() {
        try {
            Generador_Nombres nombreGenerador = new Generador_Nombres("resources/data/Nombres_jugadores.txt");
            for (int i = 0; i < 23; i++) {
                Jugador jugador = new Jugador(nombreGenerador);
                jugadores.add(jugador); // Añadir el jugador a la lista
            }
            System.out.println("Jugadores generados correctamente para el equipo: " + nombre);
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

    // Getters para obtener los atributos
    public String getNombre() {
        return nombre;
    }

    public String getLiga() {
        return liga;
    }

    public String getRutaLogo() {
        return rutaLogo;
    }
}
