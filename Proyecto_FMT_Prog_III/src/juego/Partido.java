package juego;

import java.util.Random;

public class Partido {
    private Equipo equipo1;
    private Equipo equipo2;

    public Partido(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public String jugar() {
        Random random = new Random();
        int resultadoEquipo1 = equipo1.calcularHabilidadTotal() + random.nextInt(10);
        int resultadoEquipo2 = equipo2.calcularHabilidadTotal() + random.nextInt(10);

        if (resultadoEquipo1 > resultadoEquipo2) {
            return equipo1.getNombre() + " gana el partido!";
        } else if (resultadoEquipo1 < resultadoEquipo2) {
            return equipo2.getNombre() + " gana el partido!";
        } else {
            return "El partido entre " + equipo1.getNombre() + " y " + equipo2.getNombre() + " termina en empate!";
        }
    }
}