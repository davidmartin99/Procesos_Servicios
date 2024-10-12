package Practica_Tema1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class SincronizacionSubprocesos {

    // Definimos las rutas de los archivos como variables de clase
    private static final String RUTA_ARCHIVO1 = "C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\src\\Practica_Tema1\\archivo11.txt";
    private static final String RUTA_ARCHIVO2 = "C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\src\\Practica_Tema1\\archivo22.txt";

    // Método para contar líneas usando PowerShell
    public static int contarLineas(File archivo) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("powershell.exe", "-Command", "Get-Content",
                archivo.getAbsolutePath(), "| Measure-Object -Line");
        Process proceso = builder.start();

        // Capturamos la salida del proceso
        BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        StringBuilder output = new StringBuilder(); // To store raw output
        String linea;

        while ((linea = reader.readLine()) != null) {
            output.append(linea).append("\n"); // Store output
        }

        proceso.waitFor();

        // Print raw PowerShell output for debugging
        System.out.println("PowerShell Output (Count Lines):");
        System.out.println(output.toString());

        // Parse the output for the line count
        String outputString = output.toString();
        if (outputString.contains("Lines")) {
            String[] partes = outputString.split("Lines");
            if (partes.length > 1) {
                String numberString = partes[1].replaceAll("\\D+", "").trim(); // Extrae solo los dígitos
                if (!numberString.isEmpty()) {
                    return Integer.parseInt(numberString);
                }
            }
        }

        System.err.println("No se pudo obtener el número de líneas.");
        return 0;
    }

    // Método para contar palabras usando PowerShell
    public static int contarPalabras(File archivo) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("powershell.exe", "-Command", "Get-Content",
                archivo.getAbsolutePath(), "| Measure-Object -Word");
        Process proceso = builder.start();

        // Capturamos la salida del proceso
        BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        StringBuilder output = new StringBuilder(); // To store raw output
        String linea;

        while ((linea = reader.readLine()) != null) {
            output.append(linea).append("\n"); // Store output
        }

        proceso.waitFor();

        // Print raw PowerShell output for debugging
        System.out.println("PowerShell Output (Count Words):");
        System.out.println(output.toString());

        // Parse the output for the word count
        String outputString = output.toString();
        if (outputString.contains("Words")) {
            String[] partes = outputString.split("Words");
            if (partes.length > 1) {
                String numberString = partes[1].replaceAll("\\D+", "").trim(); // Extrae solo los dígitos
                if (!numberString.isEmpty()) {
                    return Integer.parseInt(numberString);
                }
            }
        }

        System.err.println("No se pudo obtener el número de palabras.");
        return 0;
    }

    public static void main(String[] args) {
        // Archivos de texto que se van a usar
        File archivo1 = new File(RUTA_ARCHIVO1);
        File archivo2 = new File(RUTA_ARCHIVO2);

        // Debug: Check if files exist
        System.out.println("Checking file paths...");
        System.out.println("File 1 exists: " + archivo1.exists());
        System.out.println("File 2 exists: " + archivo2.exists());

        try {
            // Ejecutamos el primer subproceso para contar líneas
            int totalLineas = contarLineas(archivo1);

            // Ejecutamos el segundo subproceso para contar palabras
            int totalPalabras = contarPalabras(archivo2);


            // Mostramos el resultado
            System.out.println("Total líneas en archivo1: " + totalLineas);
            System.out.println("Total palabras en archivo2: " + totalPalabras);
            System.out.println("Total combinado de líneas y palabras: " + (totalLineas + totalPalabras));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
