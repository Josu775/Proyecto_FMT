package juego;

public class Jugador {
    private String nombre;
    private int habilidad;
    private String posicion;

    public Jugador(String nombre, int habilidad, String posicion) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHabilidad() {
        return habilidad;
    }

    public String getPosicion() {
        return posicion;
    }

    @Override
    public String toString() {
        return nombre + " (Posición: " + posicion + ", Habilidad: " + habilidad + ")";
    }
}