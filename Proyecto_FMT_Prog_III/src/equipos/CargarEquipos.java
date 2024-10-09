package equipos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CargarEquipos {
    private List<String> nombresEquipos;
    private Random random;

    public CargarEquipos(String rutaArchivo) throws IOException {
        nombresEquipos = new ArrayList<>();
        cargarNombresDesdeArchivo(rutaArchivo);
        random = new Random();
    }

    private void cargarNombresDesdeArchivo(String rutaArchivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            nombresEquipos.add(linea.trim());
        }
        reader.close();
    }

    public String obtenerNombreAleatorio() {
        return nombresEquipos.get(random.nextInt(nombresEquipos.size()));
    }
}
