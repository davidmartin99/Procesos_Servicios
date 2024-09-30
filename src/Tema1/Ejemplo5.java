package Tema1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Ejemplo5 {
    public static void main(String[] args) throws IOException {
        // Cambia la ruta al directorio donde están los .class
        File directorio = new File("C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\src\\Tema1");

        // Ejecutar la clase EjemploLectura con el nombre completo del paquete
        ProcessBuilder pb = new ProcessBuilder("java", "-cp",
                "C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\out\\production\\Procesos_Servicios",
                "Tema1.EjemploLectura");
        pb.directory(directorio);

        // Se ejecuta el proceso
        Process p = pb.start();

        // Escritura -- envía entrada
        OutputStream os = p.getOutputStream();
        os.write("Hola Manuel\n".getBytes());
        os.flush(); // Vacía el buffer de salida

        // Lectura -- obtiene la salida
        InputStream is = p.getInputStream();
        int c;
        while ((c = is.read()) != -1) {
            System.out.print((char) c);
        }
        is.close();

        // COMPROBACIÓN DE ERROR - 0 bien - 1 mal
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // Manejo de la salida de errores
            InputStream er = p.getErrorStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String line;
            while ((line = brer.readLine()) != null) {
                System.out.println("ERROR: " + line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
