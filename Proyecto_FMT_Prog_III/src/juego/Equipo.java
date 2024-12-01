package juego;

import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private ArrayList<Jugador> jugadores;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public int calcularHabilidadTotal() {
        int habilidadTotal = 0;
        for (Jugador jugador : jugadores) {
            habilidadTotal += jugador.getHabilidad();
        }
        return habilidadTotal;
    }

    public String mostrarJugadores() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugadores de ").append(nombre).append(":\n");
        for (Jugador jugador : jugadores) {
            sb.append(jugador.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return nombre; // Esto asegura que el JComboBox muestre el nombre del equipo
    }
}