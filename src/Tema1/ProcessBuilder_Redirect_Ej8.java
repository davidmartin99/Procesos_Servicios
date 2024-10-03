package Tema1;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilder_Redirect_Ej8 {
    public static void main(String[] args) {
        // Cambia la ruta al directorio donde están los .class
        File directorio = new File("C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\src\\Tema1");

        // Archivo de entrada desde donde se tomará la entrada
        File inputFile = new File(directorio, "entrada.txt"); // Usando el directorio para una ruta relativa

        // Archivo de salida donde se guardará la salida
        File outputFile = new File(directorio, "salida.txt"); // Usando el directorio para una ruta relativa

        // Comprobamos si el archivo de entrada existe
        if (!inputFile.exists()) {
            System.err.println("El archivo de entrada no existe: " + inputFile.getAbsolutePath());
            return;
        }

        // Ejecutar la clase EjemploLectura con el nombre completo del paquete
        ProcessBuilder pb = new ProcessBuilder("java", "-cp",
                "C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\out\\production\\Procesos_Servicios",
                "Tema1.EjemploLectura");
        pb.directory(directorio);

        // Redirigir la entrada desde el archivo y la salida al archivo
        pb.redirectInput(inputFile); // Redirige la entrada desde un archivo
        pb.redirectOutput(outputFile); // Redirige la salida a un archivo
        pb.redirectErrorStream(true); // Combina la salida de error y la salida estándar

        try {
            // Se ejecuta el proceso
            Process p = pb.start();

            // Lectura -- obtiene la salida
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            // Mostrar la salida en la consola
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

            // COMPROBACIÓN DE ERROR - 0 bien - 1 mal
            int exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (IOException e) {
            System.err.println("Error al iniciar el proceso: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("El proceso fue interrumpido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
