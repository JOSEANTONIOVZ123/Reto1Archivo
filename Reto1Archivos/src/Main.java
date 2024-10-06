import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String rutaCSV = "peliculas.csv";       // Ruta del archivo CSV
        String rutaTemplate = "template.html";  // Ruta de la plantilla HTML
        String carpetaSalida = "salida";        // Carpeta donde se guardarán los archivos HTML
        new File(carpetaSalida).mkdir();        // Crear carpeta "salida" si no existe

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String template = new String(Files.readAllBytes(Paths.get(rutaTemplate))); // Leer la plantilla HTML
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String paginaHTML = template.replace("%%1%%", datos[0])
                        .replace("%%2%%", datos[1])
                        .replace("%%3%%", datos[2])
                        .replace("%%4%%", datos[3])
                        .replace("%%5%%", datos[4]);
                Files.write(Paths.get(carpetaSalida + "/" + datos[1] + " - " + datos[0] + ".html"), paginaHTML.getBytes());
                System.out.println("Archivo generado: " + datos[1] + " - " + datos[0] + ".html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
