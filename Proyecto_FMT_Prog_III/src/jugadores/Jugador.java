package jugadores;

import java.io.IOException;
import java.util.Random;

public class Jugador {
    private String nombreCompleto;
    private String nacionalidad;
    private Habilidad habilidad;
    private String posicion;
    private int edad;
    private boolean esChicoMaravilla;

    private static final int EDAD_MIN = 16;  
    private static final int EDAD_MAX = 42;  
    private static final int EDAD_MAX_CHICO_MARAVILLA = 21;  
    private static final double PROBABILIDAD_CHICO_MARAVILLA = 0.05;  

    public Jugador(Generador_Nombres nombreGenerator) throws IOException {
        String nombreYNacionalidad = nombreGenerator.obtenerNombreAleatorio();
        String[] partes = nombreYNacionalidad.split(", ");
        this.nombreCompleto = partes[0];
        this.nacionalidad = partes[1];
        this.edad = generarEdadAleatoria();
        this.posicion = asignarPosicionAleatoria();
        this.esChicoMaravilla = determinarSiEsChicoMaravilla();
        this.habilidad = new Habilidad(this.posicion);
        ajustarHabilidadesSegunEdad();
    }

    private int generarEdadAleatoria() {
        Random random = new Random();
        return random.nextInt((EDAD_MAX - EDAD_MIN) + 1) + EDAD_MIN;
    }

    private boolean determinarSiEsChicoMaravilla() {
        if (edad > EDAD_MAX_CHICO_MARAVILLA) {
            return false;
        }
        Random random = new Random();
        return random.nextDouble() < PROBABILIDAD_CHICO_MARAVILLA;
    }

    private void ajustarHabilidadesSegunEdad() {
        Random random = new Random();
        int ajusteEdad = calcularAjustePorEdad();
        
        if (esChicoMaravilla) {
            ajustarHabilidadesChicoMaravilla();
        } else {
            habilidad.getAtributosFisicos().replaceAll((key, value) -> Math.min(value + ajusteEdad + random.nextInt(10), 100));
            habilidad.getAtributosMentales().replaceAll((key, value) -> Math.min(value + ajusteEdad + random.nextInt(10), 100));
            habilidad.getAtributosTecnicos().replaceAll((key, value) -> Math.min(value + ajusteEdad + random.nextInt(10), 100));
            if (posicion.equalsIgnoreCase("portero")) {
                habilidad.getAtributosPortero().replaceAll((key, value) -> Math.min(value + ajusteEdad + random.nextInt(10), 100));
            }
        }
    }

    private int calcularAjustePorEdad() {
        if (edad <= 20) {
            return -20;  
        } else if (edad <= 25) {
            return -10;  
        } else if (edad <= 30) {
            return 10;   
        } else {
            return 20;   
        }
    }

    private void ajustarHabilidadesChicoMaravilla() {
        Random random = new Random();
        habilidad.getAtributosFisicos().replaceAll((key, value) -> Math.min(value + random.nextInt(30) + 20, 100));
        habilidad.getAtributosMentales().replaceAll((key, value) -> Math.min(value + random.nextInt(30) + 20, 100));
        habilidad.getAtributosTecnicos().replaceAll((key, value) -> Math.min(value + random.nextInt(30) + 20, 100));
        if (posicion.equalsIgnoreCase("portero")) {
            habilidad.getAtributosPortero().replaceAll((key, value) -> Math.min(value + random.nextInt(30) + 20, 100));
        }
    }

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

    public int getEdad() {
        return edad;
    }

    public boolean esChicoMaravilla() {
        return esChicoMaravilla;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombreCompleto);
        System.out.println("Nacionalidad: " + nacionalidad);
        System.out.println("Edad: " + edad);
        System.out.println("Posición: " + posicion);
        if (esChicoMaravilla) {
            System.out.println("¡Es un chico maravilla!");
        }
        habilidad.mostrarAtributos();  
        Transferencia transferencia = new Transferencia(this);
        transferencia.mostrarInformacionTransferencia();
    }

    public static void main(String[] args) {
        try {
            Generador_Nombres nombreGenerador = new Generador_Nombres("recursos/Nombres_jugadores.txt");
            Jugador jugador = new Jugador(nombreGenerador);
            jugador.mostrarInformacion();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de nombres: " + e.getMessage());
        }
    }
}
