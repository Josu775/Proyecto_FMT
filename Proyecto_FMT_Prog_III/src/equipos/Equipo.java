package equipos;

import jugadores.Generador_Nombres; 
import jugadores.Jugador; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String liga;
    private List<Jugador> jugadores; 

    public Equipo(String nombre, String liga, Generador_Nombres generadorNombres) throws IOException {
        this.nombre = nombre;
        this.liga = liga;
        this.jugadores = new ArrayList<>();
        crearJugadores(generadorNombres);
    }

    private void crearJugadores(Generador_Nombres generadorNombres) throws IOException {
        for (int i = 0; i < 23; i++) { 
            jugadores.add(new Jugador(generadorNombres));
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getLiga() {
        return liga;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    @Override
    public String toString() {
        return nombre + " (" + liga + ")";
    }
}
