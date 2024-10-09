package equipos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jugadores.Jugador;
import jugadores.Generador_Nombres;

public class Liga_Nacional {
    public static void main(String[] args) {
        List<Equipo> equipos = new ArrayList<>();
        CargarEquipos generadorEquipos;

        try {
            generadorEquipos = new CargarEquipos("recursos/Archivos_CargarDatos/equipos_ligas.txt");
            
            String[] ligas = {"LaLiga", "Premier League", "Serie A", "Ligue 1", "Bundesliga"};

            for (String liga : ligas) {
                for (int i = 0; i < 20; i++) {
                    String nombreEquipo = generadorEquipos.obtenerNombreAleatorio();
                    equipos.add(new Equipo(nombreEquipo, liga, new Generador_Nombres("recursos/Archivos_CargarDatos/Nombres_Jugadores.txt")));
                }
            }

            Map<String, List<Equipo>> equiposPorLiga = new HashMap<>();
            for (Equipo equipo : equipos) {
                equiposPorLiga
                    .computeIfAbsent(equipo.getLiga(), k -> new ArrayList<>())
                    .add(equipo);
            }

            for (Map.Entry<String, List<Equipo>> entry : equiposPorLiga.entrySet()) {
                String liga = entry.getKey();
                List<Equipo> equiposDeLiga = entry.getValue();

                System.out.println(liga + ":");
                for (Equipo equipo : equiposDeLiga) {
                    System.out.println(" - " + equipo.getNombre());
                    for (Jugador jugador : equipo.getJugadores()) {
                        System.out.println("   * " + jugador.getNombreCompleto());
                    }
                }
                System.out.println(); 
            }
        } catch (IOException e) {
            System.err.println("Error al cargar los nombres de equipos o jugadores: " + e.getMessage());
        }
    }
}
