package jugadores;

import java.util.Random;

public class Jugador {
    private String nombre;
    private int edad;
    private String posicion;
    private Habilidad habilidad;


    public Jugador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = asignarPosicionAleatoria();
        this.habilidad = new Habilidad(posicion);
    }
    
    private String asignarPosicionAleatoria() {
        String[] posiciones = {"delantero", "centrocampista", "defensor", "portero"};
        Random random = new Random();
        int indiceAleatorio = random.nextInt(posiciones.length); 
        return posiciones[indiceAleatorio]; 
    }
    
    public void mejorarAtributos() {
        Random random = new Random();
        int mejora = (edad < 30) ? random.nextInt(10) + 1 : random.nextInt(5) + 1; 
        

        habilidad.getAtributosFisicos().forEach((key, value) -> 
            habilidad.getAtributosFisicos().put(key, Math.min(value + mejora, 100))); 

        habilidad.getAtributosMentales().forEach((key, value) -> 
            habilidad.getAtributosMentales().put(key, Math.min(value + mejora, 100))); 
        
        habilidad.getAtributosTecnicos().forEach((key, value) -> 
            habilidad.getAtributosTecnicos().put(key, Math.min(value + mejora, 100)));
        
        habilidad.getAtributosPortero().forEach((key, value) -> 
            habilidad.getAtributosPortero().put(key, Math.min(value + mejora, 100)));
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Posición: " + posicion);
        habilidad.mostrarAtributos();
    }

    public static void main(String[] args) {

        Jugador delantero = new Jugador("Juan", 25);
        Jugador centrocampista = new Jugador("Luis", 28);
        Jugador defensor = new Jugador("Carlos", 32);
        Jugador portero = new Jugador("Miguel", 30);

        delantero.mostrarInformacion();
        System.out.println();
        centrocampista.mostrarInformacion();
        System.out.println();
        defensor.mostrarInformacion();
        System.out.println();
        portero.mostrarInformacion();
    }
}
