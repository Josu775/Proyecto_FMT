package domain;

import java.util.HashMap;
import java.util.Random;

public class Habilidad {
    
    private HashMap<String, Integer> atributosFisicos;
    private HashMap<String, Integer> atributosMentales;
    private HashMap<String, Integer> atributosTecnicos;
    private HashMap<String, Integer> atributosPortero;
    private String posicion; // Agregar esta línea para almacenar la posición

    public Habilidad(String posicion) {
        this.posicion = posicion; // Inicializar la posición
        atributosFisicos = new HashMap<>();
        atributosMentales = new HashMap<>();
        atributosTecnicos = new HashMap<>();
        atributosPortero = new HashMap<>();
        inicializarAtributos(posicion);
    }

    private int generarValorAleatorio(int min, int max, Random random) {
        return random.nextInt((max - min) + 1) + min;
    }

    private void inicializarAtributos(String posicion) {
        Random random = new Random();
        int min = 40;
        int max = 80;

        // Inicializar atributos físicos
        atributosFisicos.put("Resistencia", generarValorAleatorio(min, max, random));
        atributosFisicos.put("Velocidad", generarValorAleatorio(min, max, random));
        atributosFisicos.put("Fuerza", generarValorAleatorio(min, max, random));
        atributosFisicos.put("Aceleración", generarValorAleatorio(min, max, random));
        atributosFisicos.put("Salto", generarValorAleatorio(min, max, random));
        atributosFisicos.put("Equilibrio", generarValorAleatorio(min, max, random));
        atributosFisicos.put("Potencia de tiro", generarValorAleatorio(min, max, random));
        atributosFisicos.put("Agilidad", generarValorAleatorio(min, max, random));

        // Inicializar atributos mentales
        atributosMentales.put("Visión de juego", generarValorAleatorio(min, max, random));
        atributosMentales.put("Toma de decisiones", generarValorAleatorio(min, max, random));
        atributosMentales.put("Concentración", generarValorAleatorio(min, max, random));
        atributosMentales.put("Comunicación", generarValorAleatorio(min, max, random));
        atributosMentales.put("Liderazgo", generarValorAleatorio(min, max, random));
        atributosMentales.put("Anticipación", generarValorAleatorio(min, max, random));
        atributosMentales.put("Trabajo en equipo", generarValorAleatorio(min, max, random));
        atributosMentales.put("Determinación", generarValorAleatorio(min, max, random));

        // Inicializar atributos técnicos
        atributosTecnicos.put("Control del balón", generarValorAleatorio(min, max, random));
        atributosTecnicos.put("Regate", generarValorAleatorio(min, max, random));
        atributosTecnicos.put("Tiro", generarValorAleatorio(min, max, random));
        atributosTecnicos.put("Pases", generarValorAleatorio(min, max, random));
        atributosTecnicos.put("Centros", generarValorAleatorio(min, max, random));
        atributosTecnicos.put("Tiros libres", generarValorAleatorio(min, max, random));
        atributosTecnicos.put("Penales", generarValorAleatorio(min, max, random));
        atributosTecnicos.put("Remates de cabeza", generarValorAleatorio(min, max, random));

        // Inicializar atributos de portero
        atributosPortero.put("Paradas", generarValorAleatorio(min, max, random));
        atributosPortero.put("Reflejos", generarValorAleatorio(min, max, random));
        atributosPortero.put("Despejes", generarValorAleatorio(min, max, random));
        atributosPortero.put("Juego aéreo", generarValorAleatorio(min, max, random));
        atributosPortero.put("Colocación", generarValorAleatorio(min, max, random));
        atributosPortero.put("Comunicación", generarValorAleatorio(min, max, random));
        atributosPortero.put("Distribución", generarValorAleatorio(min, max, random));
        atributosPortero.put("Uno contra uno", generarValorAleatorio(min, max, random));

        // Ajustar atributos según la posición del jugador
        ajustarAtributosPorPosicion(posicion);
    }

    private void ajustarAtributosPorPosicion(String posicion) {
        Random random = new Random();

        switch (posicion.toLowerCase()) {
            case "delantero":
                atributosTecnicos.forEach((key, value) ->
                    atributosTecnicos.put(key, Math.min(value + random.nextInt(20) + 10, 100))); 
                ajustarAtributosPortero(); // Establecer atributos de portero a un mínimo para delanteros
                break;

            case "centrocampista":
                atributosMentales.forEach((key, value) ->
                    atributosMentales.put(key, Math.min(value + random.nextInt(10) + 5, 100)));
                ajustarAtributosPortero(); // Establecer atributos de portero a un mínimo para centrocampistas
                break;

            case "defensor":
                atributosFisicos.forEach((key, value) ->
                    atributosFisicos.put(key, Math.min(value + random.nextInt(10) + 5, 100))); 
                atributosTecnicos.replace("Regate", 20); // Ajustar regate para defensores
                ajustarAtributosPortero(); // Establecer atributos de portero a un mínimo para defensores
                break;

            case "portero":
                atributosTecnicos.forEach((key, value) ->
                    atributosTecnicos.put(key, Math.max(value - random.nextInt(30) - 10, 0)));
                break;
        }
    }

    private void ajustarAtributosPortero() {
        atributosPortero.replace("Paradas", 10); 
        atributosPortero.replace("Reflejos", 15);
        atributosPortero.replace("Despejes", 5);
        atributosPortero.replace("Juego aéreo", 5);
        atributosPortero.replace("Colocación", 10);
        atributosPortero.replace("Comunicación", 30);
        atributosPortero.replace("Distribución", 10);
        atributosPortero.replace("Uno contra uno", 5);
    }

    private double calcularMedia(HashMap<String, Integer> atributos) {
        return atributos.values().stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public double getMediaAtributosFisicos() {
        return calcularMedia(atributosFisicos);
    }

    public double getMediaAtributosMentales() {
        return calcularMedia(atributosMentales);
    }

    public double getMediaAtributosTecnicos() {
        return calcularMedia(atributosTecnicos);
    }

    public double getMediaAtributosPortero() {
        return calcularMedia(atributosPortero);
    }

    public double getMediaGeneral() {
        double mediaFisica = getMediaAtributosFisicos();
        double mediaMental = getMediaAtributosMentales();
        double mediaTecnica = getMediaAtributosTecnicos();
        double mediaPortero = getMediaAtributosPortero();
        double sumaMedias = mediaFisica + mediaMental;

        // Si la posición es "portero", incluimos los atributos de portero
        if (this.posicion.equalsIgnoreCase("portero")) {
            sumaMedias += mediaPortero;
            return sumaMedias / 3; // Dividimos por 3 ya que incluimos (físicos, mentales, portero)
        }

        // Para las demás posiciones, se incluyen los atributos técnicos
        sumaMedias += mediaTecnica;
        return sumaMedias / 3;
    }

    public HashMap<String, Integer> getAtributosFisicos() {
        return atributosFisicos;
    }

    public HashMap<String, Integer> getAtributosMentales() {
        return atributosMentales;
    }

    public HashMap<String, Integer> getAtributosTecnicos() {
        return atributosTecnicos;
    }

    public HashMap<String, Integer> getAtributosPortero() {
        return atributosPortero;
    }

    public void mostrarAtributos() {
        System.out.println("Atributos Físicos: " + atributosFisicos);
        System.out.println("Atributos Mentales: " + atributosMentales);
        System.out.println("Atributos Técnicos: " + atributosTecnicos);
        System.out.println("Atributos de Portero: " + atributosPortero);
        System.out.println("Media Atributos Físicos: " + getMediaAtributosFisicos());
        System.out.println("Media Atributos Mentales: " + getMediaAtributosMentales());
        System.out.println("Media Atributos Técnicos: " + getMediaAtributosTecnicos());
        System.out.println("Media Atributos Portero: " + getMediaAtributosPortero());
        System.out.println("Media General de Atributos: " + getMediaGeneral());
    }
}
