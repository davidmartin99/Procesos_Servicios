package Tema1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LanzaLeerCadena_6 {
    public static void main(String[] args) throws IOException {
        // Ruta donde están los archivos compilados
        File directorio = new File("C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\out\\production\\Procesos_Servicios");

        // Ejecutar la clase LeerCadenas
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", directorio.getAbsolutePath(), "Tema1.LeerCadena_6");
        pb.directory(directorio);

        // Permitir que el proceso hijo herede la entrada/salida del proceso padre
        pb.inheritIO();

        // Se ejecuta el proceso
        Process p = pb.start();

        // Esperar el resultado del proceso
        try {
            int exitVal = p.waitFor();
            System.out.println("\nValor de salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
