package juego;

import java.util.ArrayList;

public class Liga {
    private ArrayList<Equipo> equipos;

    public Liga() {
        equipos = new ArrayList<>();
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos; // Método agregado para obtener la lista de equipos
    }

    public void jugarLiga() {
        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                Partido partido = new Partido(equipos.get(i), equipos.get(j));
                System.out.println(partido.jugar());
            }
        }
    }

    public void mostrarEstadisticas() {
        for (Equipo equipo : equipos) {
            System.out.println("Estadísticas del equipo " + equipo.getNombre() + ":");
            System.out.println("Jugadores:\n" + equipo.mostrarJugadores());
            System.out.println("Habilidad Total: " + equipo.calcularHabilidadTotal());
            System.out.println();
        }
    }
}