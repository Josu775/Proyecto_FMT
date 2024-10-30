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
                String[] parts = linea.split(","); // Cambia a solo ',' si no hay espacios
                if (parts.length >= 3) { // Asegúrate de que hay suficientes elementos
                    // Crear un nuevo equipo con el nombre, liga y rutaLogo
                    Equipo equipo = new Equipo(parts[0].trim(), parts[1].trim(), parts[2].trim()); // nombre, liga, rutaLogo
                    equipos.add(equipo); // Añadir el equipo a la lista de equipos
                } else {
                    System.err.println("Línea inválida en el archivo: " + linea);
                }
            }
            System.out.println("Equipos cargados correctamente en la liga: " + nombre);
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
            System.out.println("Equipo: " + equipo.getNombre());
            equipo.mostrarJugadores(); // Muestra los jugadores de cada equipo
            System.out.println(); // Espacio entre equipos
        }
    }

    // Métodos getters
    public String getNombre() {
        return nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }
}
