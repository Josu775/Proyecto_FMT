package jugadores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Estudiarselo

public class Generador_Nombres {
    private List<String> nombres;

    public Generador_Nombres(String archivo) {
        nombres = new ArrayList<>();
        leerArchivo(archivo);
    }

    private void leerArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                nombres.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String obtenerNombreAleatorio() {
        Random random = new Random();
        return nombres.get(random.nextInt(nombres.size()));
    }
}
