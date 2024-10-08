package jugadores;

import java.util.HashMap;
import java.util.Random;

public class Jugador {
    private String nombreCompleto;
    private String nacionalidad;
    private Habilidad habilidad;
    private String posicion;

    public Jugador(Generador_Nombres nombreGenerator) {
        // Obtener un nombre aleatorio del archivo
        String nombreYNacionalidad = nombreGenerator.obtenerNombreAleatorio();
        String[] partes = nombreYNacionalidad.split(", ");
        this.nombreCompleto = partes[0];
        this.nacionalidad = partes[1];
        
        // Asignar posición aleatoria
        this.posicion = asignarPosicionAleatoria();
        this.habilidad = new Habilidad(this.posicion);  // Crear habilidad basada en la posición
    }

    // Método para asignar una posición aleatoria
    private String asignarPosicionAleatoria() {
        String[] posiciones = {"Delantero", "Centrocampista", "Defensor", "Portero"};
        Random random = new Random();
        return posiciones[random.nextInt(posiciones.length)];
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombreCompleto);
        System.out.println("Nacionalidad: " + nacionalidad);
        System.out.println("Posición: " + posicion);
        habilidad.mostrarAtributos();  // Mostrar los atributos del jugador
    }

    public static void main(String[] args) {
        // Crear un generador de nombres a partir del archivo
    	Generador_Nombres nombreGenerador = new Generador_Nombres("recursos/Nombres_jugadores.txt");

        // Crear un jugador con nombre aleatorio
        Jugador jugador = new Jugador(nombreGenerador);
        jugador.mostrarInformacion();
    }
}
