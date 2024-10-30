package domain;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {

    private static final String CONFIG_FILE = "config.properties";

    // Valores predeterminados
    private static final boolean PANTALLA_COMPLETA_DEFAULT = false;
    private static final String RESOLUCION_DEFAULT = "800x600";

    // Cargar o crear el archivo de configuración
    public Properties cargarConfiguracion() {
        Properties propiedades = new Properties();

        // Intentar cargar el archivo si existe
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            propiedades.load(input);
            System.out.println("Archivo de configuración cargado exitosamente.");
        } catch (IOException e) {
            System.out.println("No se encontró archivo de configuración. Creando uno nuevo con valores predeterminados...");
            // Si el archivo no existe, crear uno nuevo con los valores predeterminados
            propiedades.setProperty("pantallaCompleta", String.valueOf(PANTALLA_COMPLETA_DEFAULT));
            propiedades.setProperty("resolucion", RESOLUCION_DEFAULT);
            guardarConfiguracion(propiedades); // Guardar con valores predeterminados
        }
        return propiedades;
    }

    // Guardar configuración en el archivo
    public void guardarConfiguracion(Properties propiedades) {
        try (FileOutputStream output = new FileOutputStream(CONFIG_FILE)) {
            propiedades.store(output, "Configuración de Opciones de Pantalla");
            System.out.println("Configuración guardada exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar la configuración: " + e.getMessage());
        }
    }
}
