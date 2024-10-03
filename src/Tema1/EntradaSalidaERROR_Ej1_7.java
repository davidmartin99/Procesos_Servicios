package Tema1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/*
    Este programa ejecuta una clase Java (EjemploLectura) desde un archivo .java
    y redirige la entrada desde un archivo externo, la salida a un archivo de texto,
    y la salida de error a otro archivo de texto.
*/

public class EntradaSalidaERROR_Ej1_7 {
    public static void main(String[] args) throws IOException {
        // Cambia la ruta al directorio donde están los .class
        File directorio = new File("C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\src\\Tema1");

        // Ejecutar la clase EjemploLectura con el nombre completo del paquete
        ProcessBuilder pb = new ProcessBuilder("java", "-cp",
                "C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\out\\production\\Procesos_Servicios",
                "Tema1.EjemploLectura");
        pb.directory(directorio);

        // Creamos archivos para redirigir la salida estándar y la salida de error
        File fOut = new File("./src/Tema1/salida.txt"); // Archivo para la salida estándar
        File fErr = new File("./src/Tema1/error.txt");   // Archivo para la salida de error

        // Redirigimos la salida estándar del proceso al archivo de salida
        pb.redirectOutput(fOut);
        // Redirigimos la salida de error del proceso al archivo de error
        pb.redirectError(fErr);

        // Se ejecuta el proceso
        Process p = pb.start();

        // Escritura -- envía entrada
        try (var os = p.getOutputStream()) {
            os.write("Hola Manuel\n".getBytes());
            os.flush(); // Vacía el buffer de salida
        }

        // Esperamos a que el proceso termine y obtenemos el código de salida
        try {
            int exitVal = p.waitFor(); // Espera hasta que el proceso termine
            System.out.println("Valor de Salida: " + exitVal); // Muestra el código de salida del proceso
        } catch (InterruptedException e) {
            e.printStackTrace(); // Manejo de interrupciones
        }

        // Manejo de la salida de errores (si las hubiera)
        try (var er = p.getErrorStream(); BufferedReader brer = new BufferedReader(new InputStreamReader(er))) {
            String line;
            while ((line = brer.readLine()) != null) {
                System.out.println("ERROR: " + line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace(); // Manejo de excepciones de IO
        }
    }
}
