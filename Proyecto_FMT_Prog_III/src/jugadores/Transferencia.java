package jugadores;

import java.util.HashMap;

public class Transferencia {
    private Jugador jugador;
    private double valor;

    private static final double VALOR_MEDIO = 20_000_000;
    private static final double VALOR_MINIMO = 0;
    private static final double VALOR_MAXIMO = 250_000_000;

    public Transferencia(Jugador jugador) {
        this.jugador = jugador;
        this.valor = calcularValorJugador();
    }

    private double calcularValorJugador() {
        double baseValor = VALOR_MEDIO;
        HashMap<String, Integer> atributosFisicos = jugador.getHabilidad().getAtributosFisicos();
        HashMap<String, Integer> atributosMentales = jugador.getHabilidad().getAtributosMentales();
        HashMap<String, Integer> atributosTecnicos = jugador.getHabilidad().getAtributosTecnicos();

        double sumaAtributos = 0;
        for (Integer valorAtributo : atributosFisicos.values()) {
            sumaAtributos += valorAtributo;
        }
        for (Integer valorAtributo : atributosMentales.values()) {
            sumaAtributos += valorAtributo;
        }
        for (Integer valorAtributo : atributosTecnicos.values()) {
            sumaAtributos += valorAtributo;
        }

        double porcentajeAtributos = sumaAtributos / (atributosFisicos.size() + atributosMentales.size() + atributosTecnicos.size() * 100);
        baseValor *= porcentajeAtributos;

        switch (jugador.getPosicion().toLowerCase()) {
            case "delantero":
                baseValor *= 1.5;
                break;
            case "centrocampista":
                baseValor *= 1.3;
                break;
            case "defensor":
                baseValor *= 1.2;
                break;
            case "portero":
                baseValor *= 1.1;
                break;
        }

        if (jugador.getEdad() < 21) {
            baseValor *= 1.2;
        } else if (jugador.getEdad() > 32) {
            baseValor *= 0.5;
        } else if (jugador.getEdad() >= 30 && jugador.getEdad() <= 32) {
            baseValor *= 0.8;
        }

        if (jugador.getPosicion().equalsIgnoreCase("portero") && jugador.getEdad() > 35) {
            baseValor *= 0.5;
        }

        if (jugador.esChicoMaravilla()) {
            baseValor *= 2.0;
        }

        if (sumaAtributos == (atributosFisicos.size() + atributosMentales.size() + atributosTecnicos.size()) * 100) {
            baseValor = VALOR_MAXIMO;
        }

        return Math.min(Math.max(baseValor, VALOR_MINIMO), VALOR_MAXIMO);
    }

    public void mostrarInformacionTransferencia() {
        System.out.println("Información de Transferencia:");
        System.out.println("Nombre del Jugador: " + jugador.getNombreCompleto());
        System.out.println("Nacionalidad: " + jugador.getNacionalidad());
        System.out.println("Posición: " + jugador.getPosicion());
        System.out.println("Edad: " + jugador.getEdad());
        System.out.printf("Valor del Jugador: %.2f millones de euros%n", valor / 1_000_000);
    }
}
