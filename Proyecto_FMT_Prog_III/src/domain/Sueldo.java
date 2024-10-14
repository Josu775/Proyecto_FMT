// Optimizar la clase Sueldo si se puede

package domain;

public class Sueldo {
    private Jugador jugador;
    private double sueldoBase;
    private double sueldoMensual;

    // Valores base ajustables para el cálculo del sueldo
    private static final double SUELDO_BASE_DELANTERO = 20000; // En euros
    private static final double SUELDO_BASE_CENTROCAMPISTA = 18000;
    private static final double SUELDO_BASE_DEFENSOR = 16000;
    private static final double SUELDO_BASE_PORTERO = 15000;
    private static final double AJUSTE_EDAD_JOVEN = 0.8;  // Ajuste para jugadores jóvenes
    private static final double AJUSTE_EDAD_VETERANO = 1.2;  // Ajuste para jugadores veteranos
    private static final double AJUSTE_CHICO_MARAVILLA = 1.5; // Aumento para chicos maravilla

    public Sueldo(Jugador jugador) {
        this.jugador = jugador;
        this.sueldoBase = calcularSueldoBasePorPosicion();
        this.sueldoMensual = calcularSueldoMensual();
    }

    // Calcula el sueldo base según la posición del jugador
    private double calcularSueldoBasePorPosicion() {
        String posicion = jugador.getPosicion().toLowerCase();
        switch (posicion) {
            case "delantero":
                return SUELDO_BASE_DELANTERO;
            case "centrocampista":
                return SUELDO_BASE_CENTROCAMPISTA;
            case "defensor":
                return SUELDO_BASE_DEFENSOR;
            case "portero":
                return SUELDO_BASE_PORTERO;
            default:
                return 10000; // Sueldo mínimo por defecto si no se conoce la posición
        }
    }

    // Ajuste basado en la edad, habilidades, y si es chico maravilla
    private double calcularSueldoMensual() {
        double ajusteEdad = 1.0;
        int edad = jugador.getEdad();
        double mediaHabilidades = jugador.getHabilidad().getMediaGeneral();
        
        // Ajustar el sueldo basado en la edad
        if (edad <= 20) {
            ajusteEdad = AJUSTE_EDAD_JOVEN;
        } else if (edad >= 30) {
            ajusteEdad = AJUSTE_EDAD_VETERANO;
        }

        // Aumentar el sueldo si es un chico maravilla
        if (jugador.esChicoMaravilla()) {
            ajusteEdad *= AJUSTE_CHICO_MARAVILLA;
        }

        // Sueldo ajustado por las habilidades y la edad
        return sueldoBase * ajusteEdad * (mediaHabilidades / 100); // Dividimos la media para ajustarla al rango de 0 a 1
    }

    // Método para mostrar el sueldo del jugador
    public void mostrarSueldo() {
        System.out.printf("Sueldo mensual: %.2f€%n", sueldoMensual);
    }

    // Obtener el sueldo mensual para otros usos
    public double getSueldoMensual() {
        return sueldoMensual;
    }
}
