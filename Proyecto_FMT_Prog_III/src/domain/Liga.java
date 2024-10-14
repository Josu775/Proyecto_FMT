package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Liga {
    private String nombre;   // Nombre de la liga
    private List<Equipo> equipos; // Lista de equipos

    // Constructor
    public Liga(String nombre) {
        this.nombre = nombre;
        this.equipos = new ArrayList<>(); // Inicializa la lista de equipos
    }

    // Método para cargar los equipos desde un archivo .txt
    public void cargarEquiposDesdeArchivo(String archivoTxt) {
        BufferedReader lector = null;
        try {
            lector = new BufferedReader(new FileReader(archivoTxt));
            String linea;
            while ((linea = lector.readLine()) != null) {
                // Crear un nuevo equipo con el nombre leído del archivo
                Equipo equipo = new Equipo(linea);
                equipo.generarJugadores(); // Genera 23 jugadores para este equipo
                equipos.add(equipo); // Añadir el equipo a la lista de equipos
            }
            System.out.println("Equipos cargados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de equipos: " + e.getMessage());
        } finally {
            try {
                if (lector != null) {
                    lector.close(); // Cierra el lector de archivos
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }

    // Método para mostrar todos los equipos y sus jugadores
    public void mostrarEquipos() {
        System.out.println("Liga: " + nombre);
        for (Equipo equipo : equipos) {
            equipo.mostrarJugadores(); // Muestra los jugadores de cada equipo
            System.out.println(); // Espacio entre equipos
        }
    }

    public static void main(String[] args) {
        // Crear una liga y cargar equipos desde el archivo
        Liga liga = new Liga("La Liga");
        liga.cargarEquiposDesdeArchivo("resources/data/equipos_ligas.txt"); // Cambia la ruta si es necesario
        liga.mostrarEquipos(); // Muestra todos los equipos y sus jugadores
    }
}
